/*
 * 微信公众平台(JAVA) SDK
 *
 * Copyright (c) 2014, Ansitech Network Technology Co.,Ltd All rights reserved.
 *
 * http://www.weixin4j.org/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.baigehuidi.demo.weixin4j.component;


import com.baigehuidi.demo.loader.WeixinInsLoader;
import com.baigehuidi.demo.weixin4j.Configuration;
import com.baigehuidi.demo.weixin4j.Weixin;
import com.baigehuidi.demo.weixin4j.WeixinException;
import com.baigehuidi.demo.weixin4j.http.HttpsClient;
import com.baigehuidi.demo.weixin4j.http.Response;
import com.baigehuidi.demo.weixin4j.model.pay.*;
import com.baigehuidi.demo.weixin4j.util.wxpay.WXPay;
import com.baigehuidi.demo.weixin4j.util.wxpay.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

/**
 * 支付组件
 *
 * @author 杨启盛<qsyang@ansitech.com>
 * @since 0.1.0
 */
@Component
public class PayComponent extends AbstractComponent {
    @Autowired
    private WXPay wxPay;

    public PayComponent(Weixin weixin) {
        super(weixin);
    }

    /**
     * 统一下单
     * 除付款码支付场景以外，商户系统先调用该接口在微信支付服务后台生成预支付交易单，
     * 返回正确的预支付交易会话标识后再按Native、JSAPI、APP等不同场景生成交易串调起支付。
     * https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1
     *
     * @param unifiedorder 统一下单对象
     * @return 下单返回结果对象
     * @throws
     */
    public UnifiedOrderResult payUnifiedOrder(UnifiedOrder unifiedorder) throws Exception {
        //将统一下单对象转成XML
        String xmlPost = unifiedorder.toXML();

        boolean postValidWithSign = WXPayUtil.isSignatureValid(xmlPost, WeixinInsLoader.getWeixinInstance().getWeixinPayConfig().getPartnerKey());
        System.out.println("postValidWithSign:"+postValidWithSign);


        if (Configuration.isDebug()) {
            System.out.println("调试模式_统一下单接口 提交XML数据：" + xmlPost);
        }
        //创建请求对象
        HttpsClient http = new HttpsClient();
        //提交xml格式数据
        Response res = http.postXml("https://api.mch.weixin.qq.com/pay/unifiedorder", xmlPost);
        //获取微信平台下单接口返回数据
        String xmlResult = res.asString();

        boolean resultValid = WXPayUtil.isSignatureValid(xmlResult, WeixinInsLoader.getWeixinInstance().getWeixinPayConfig().getPartnerKey());

        System.out.println("resultValid:"+resultValid);
        try {
            if (Configuration.isDebug()) {
                System.out.println("调试模式_统一下单接口 接口返回的XML数据: " + xmlResult);
            }

            JAXBContext context = JAXBContext.newInstance(UnifiedOrderResult.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            UnifiedOrderResult result = (UnifiedOrderResult) unmarshaller.unmarshal(new StringReader(xmlResult));
            return result;
        } catch (JAXBException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 查询订单
     * 该接口提供所有微信支付订单的查询，商户可以通过查询订单接口主动查询订单状态，完成下一步的业务逻辑。
     * >>需要调用查询接口的情况：
     * ◆ 当商户后台、网络、服务器等出现异常，商户系统最终未接收到支付通知；
     * ◆ 调用支付接口后，返回系统错误或未知交易状态情况；
     * ◆ 调用付款码支付API，返回USERPAYING的状态；
     * ◆ 调用关单或撤销接口API之前，需确认支付状态；
     * https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_2
     *
     * @param orderQuery 订单查询对象
     * @return 订单查询结果
     * @throws
     */
    public OrderQueryResult payOrderQuery(OrderQuery orderQuery) throws WeixinException {
        //将查询订单对象转成XML
        String xmlPost = orderQuery.toXML();
        if (Configuration.isDebug()) {
            System.out.println("调试模式_查询订单接口 提交XML数据：" + xmlPost);
        }
        //创建请求对象
        HttpsClient http = new HttpsClient();
        //提交xml格式数据
        Response res = http.postXml("https://api.mch.weixin.qq.com/pay/orderquery", xmlPost);
        //获取微信平台查询订单接口返回数据
        String xmlResult = res.asString();
        try {
            if (Configuration.isDebug()) {
                System.out.println("调试模式_查询订单接口 接收XML数据：" + xmlResult);
            }
            JAXBContext context = JAXBContext.newInstance(OrderQueryResult.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            OrderQueryResult result = (OrderQueryResult) unmarshaller.unmarshal(new StringReader(xmlResult));
            return result;
        } catch (JAXBException ex) {
            ex.printStackTrace();
            return null;
        }
    }


    /**
     * 关闭订单方法
     * 以下情况需要调用关单接口: 商户订单支付失败需要生成新单号重新发起支付,要对原订单号调用关单,
     * 避免重复支付; 系统下单后,用户支付超时,系统退出不再受理,避免用户继续, 请调用关单接口.
     *
     * @param closeOrder
     * @return
     * @throws WeixinException
     */
    public CloseOrderResult payCloseOrder(CloseOrder closeOrder) throws WeixinException {
        //将关闭订单对象转换为XML
        String xmlPost = closeOrder.toXML();
        if (Configuration.isDebug()) {
            System.out.println("调试模式_关闭订单 提交XML数据：" + xmlPost);
        }
        //创建请求对象
        HttpsClient http = new HttpsClient();
        //提交xml格式数据
        Response res = http.postXml("https://api.mch.weixin.qq.com/pay/closeorder", xmlPost);
        //获取微信平台关闭订单返回数据
        String xmlResult = res.asString();

        try {
            if (Configuration.isDebug()) {
                System.out.println("调试模式_关闭订单 接收返回的XML数据: " + xmlResult);
            }
            JAXBContext context = JAXBContext.newInstance(CloseOrderResult.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            CloseOrderResult result = (CloseOrderResult) unmarshaller.unmarshal(new StringReader(xmlResult));
            return result;
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }

    }


    /**
     * 撤掉订单方法
     * 支付交易返回失败或支付系统超时，调用该接口撤销交易。如果此订单用户支付失败，微信支付系统会将此订单关闭；
     * 如果用户支付成功，微信支付系统会将此订单资金退还给用户。
     * 注意：7天以内的交易单可调用撤销，其他正常支付的单如需实现相同功能请调用申请退款API。
     * 提交支付交易后调用【查询订单API】，没有明确的支付结果再调用【撤销订单API】。
     * 调用支付接口后请勿立即调用撤销订单API，建议支付后至少15s后再调用撤销订单接口。
     *
     * @param reverse
     * @return
     * @throws WeixinException
     */
    public ReverseResult payReverse(Reverse reverse) throws WeixinException {
        //step1:将请求参数转换为XML(用于进行请求)
        String xmlPost = reverse.toXML();
        if(Configuration.isDebug()){
            System.out.println("调试模式_撤销订单 提交发送的XML数据: " + xmlPost);
        }
        //创建请求对象
        HttpsClient http = new HttpsClient();
        //提交xml格式数据
        Response res = http.postXml("https://api.mch.weixin.qq.com/secapi/pay/reverse", xmlPost);
        //获取微信平台返回数据
        String xmlResult = res.asString();

        try {
            if(Configuration.isDebug()){
                System.out.println("调试模式_撤销订单 接收返回的XML数据: " + xmlResult);
            }
            JAXBContext context = JAXBContext.newInstance(ReverseResult.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            ReverseResult result = (ReverseResult)unmarshaller.unmarshal(new StringReader(xmlResult));
            return result;
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }

    }


    /**
     * 申请退款
     * 当交易发生之后一段时间内,由于买家或卖家的原因需要退款时,卖家可以通过退款接口将支付款退还给买家,微信支付
     * 将在收到退款请求并且验证成功之后,按照退款规则将支付款按原路退到买家账号上.
     * 注意:<<<<
     * 1.交易时间超过1年的订单无法提交退款
     * 2.微信支付退款支持单笔交易分多次退款,多次退款需要提交原支付订单的商户订单号和设置
     * 不同的退款单号,申请退款总金额不能超过订单金额.
     * >>>一笔退款失败后重新提交,请不要更换退款单号,请使用原商户退款单号<<<
     * 3.请求频率限制: 150qps,即每秒钟正常的申请退款请求次数不超过150次
     * 错误或无效请求频率限制: 6qps,即每秒钟异常或错误的退款申请请求不超过6次
     * 4.每个支付订单的部分退款次数不能超过50次.
     * 接口链接：https://api.mch.weixin.qq.com/secapi/pay/refund
     * 是否需要证书: 是的,请求需要双向证书.
     * 文档:https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_4
     *
     * @param refund
     * @return
     */
    public RefundResult payRefund(Refund refund) throws WeixinException {
        //step1:将请求参数转换为XML (用于进行请求)
        String xmlPost = refund.toXML();
        if (Configuration.isDebug()) {
            System.out.println("调试模式_申请退款发送的请求参数XML: " + xmlPost);
        }
        //创建请求对象
        HttpsClient http = new HttpsClient();
        //提交xml数据到申请退款的api请求链接
        Response res = http.postXml("https://api.mch.weixin.qq.com/secapi/pay/refund", xmlPost);
        //获得微信平台申请退款请求后返回的响应数据
        String xmlResult = res.asString();


        try {
            if (Configuration.isDebug()) {
                System.out.println("调试模式_申请退款方法接收的返回数据XML: " + xmlResult);
            }
            JAXBContext context = JAXBContext.newInstance(RefundResult.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            RefundResult result = (RefundResult) unmarshaller.unmarshal(new StringReader(xmlResult));
            return result;
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }

    }


    /**
     * 查询退款
     * 提交退款申请后，通过调用该接口查询退款状态。退款有一定延时，用零钱支付的退款20分钟内到账，
     * 银行卡支付的退款3个工作日后重新查询退款状态。
     * 注意：如果单个支付订单部分退款次数超过20次请使用退款单号查询
     * 分页查询
     * 当一个订单部分退款超过10笔后，商户用微信订单号或商户订单号调退款查询API查询退款时，
     * 默认返回前10笔和total_refund_count（订单总退款次数）。商户需要查询同一订单下超过10笔的退款单时，
     * 可传入订单号及offset来查询，微信支付会返回offset及后面的10笔，以此类推。
     * 当商户传入的offset超过total_refund_count，则系统会返回报错PARAM_ERROR。
     * 举例：
     * 一笔订单下的退款单有36笔，当商户想查询第25笔时，可传入订单号及offset=24，
     * 微信支付平台会返回第25笔到第35笔的退款单信息，或商户可直接传入退款单号查询退款
     *
     *
     * 接口链接：https://api.mch.weixin.qq.com/pay/refundquery
     *
     * 文档: https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_5
     * @param refundQuery
     * @return
     */
    public RefundQueryResult payRefundQuery(RefundQuery refundQuery) throws WeixinException {
        //step1:将退款查询请求转换为XML
        String xmlPost = refundQuery.toXML();
        if (Configuration.isDebug()) {
            System.out.println("调试模式_查询退款发送的请求参数XML: " + xmlPost);
        }
        //创建请求对象
        HttpsClient http = new HttpsClient();
        //提交xml数据到查询退款的api请求链接
        Response res = http.postXml("https://api.mch.weixin.qq.com/pay/refundquery", xmlPost);
        //获得微信平台查询退款请求后返回的响应数据
        String xmlResult = res.asString();

        try {
            if (Configuration.isDebug()) {
                System.out.println("调试模式_查询退款方法接收的返回数据XML: " + xmlResult);
            }
            JAXBContext context = JAXBContext.newInstance(RefundQueryResult.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            RefundQueryResult result = (RefundQueryResult) unmarshaller.unmarshal(new StringReader(xmlResult));
            return result;
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }

    }


    /**
     * 下单对账单
     * 商户可以通过该接口下载历史交易清单。比如掉单、系统错误等导致商户侧和微信侧数据不一致，通过对账单核对后可校正支付状态。
     * 注意：
     * 1、微信侧未成功下单的交易不会出现在对账单中。支付成功后撤销的交易会出现在对账单中，跟原支付单订单号一致；
     * 2、微信在次日9点启动生成前一天的对账单，建议商户10点后再获取；
     * 3、对账单中涉及金额的字段单位为“元”。
     * 4、对账单接口只能下载三个月以内的账单。
     * 5、对账单是以商户号纬度来生成的，如一个商户号与多个appid有绑定关系，则使用其中任何一个appid都可以请求下载对账单。
     * 对账单中的appid取自交易时候提交的appid，与请求下载对账单时使用的appid无关。
     * @param downLoadBill 请求参数
     * @return 成功时，数据以文本表格的方式返回，第一行为表头，后面各行为对应的字段内容，
     *         字段内容跟查询订单或退款结果一致，具体字段说明可查阅相应接口。
     *
     *         失败返回失败码
     *
     * 接口链接: https://api.mch.weixin.qq.com/pay/downloadbill
     * @throws WeixinException
     */
    public DownLoadBillResult payDownLoadBill(DownLoadBill downLoadBill) throws WeixinException {
        //step1:下载对账单请求转换为XML
        String xmlPost = downLoadBill.toXML();
        if (Configuration.isDebug()) {
            System.out.println("调试模式_下载对账单发送的请求参数XML: " + xmlPost);
        }
        //创建请求对象
        HttpsClient http = new HttpsClient();
        //提交xml数据到下载对账单的api请求链接
        Response res = http.postXml("https://api.mch.weixin.qq.com/pay/downloadbill", xmlPost);
        //获得微信平台下载对账单请求后返回的响应数据
        String xmlResult = res.asString();

        try {
            if (Configuration.isDebug()) {
                System.out.println("调试模式_下载对账单方法接收的返回数据XML: " + xmlResult);
            }
            JAXBContext context = JAXBContext.newInstance(DownLoadBillResult.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            DownLoadBillResult result = (DownLoadBillResult) unmarshaller.unmarshal(new StringReader(xmlResult));
            return result;
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }

    }

}
