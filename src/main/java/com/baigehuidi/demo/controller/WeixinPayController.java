package com.baigehuidi.demo.controller;

import com.baigehuidi.demo.loader.WeixinInsLoader;
import com.baigehuidi.demo.weixin4j.component.PayComponent;
import com.baigehuidi.demo.weixin4j.model.pay.UnifiedOrder;
import com.baigehuidi.demo.weixin4j.model.pay.UnifiedOrderResult;
import com.baigehuidi.demo.weixin4j.model.pay.WCPay;
import com.baigehuidi.demo.weixin4j.model.xml.ScanQrcodeXmlLoader;
import com.baigehuidi.demo.weixin4j.util.SignUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信支付控制层
 */
@RestController
@RequestMapping("payOne")
public class WeixinPayController {


    @Autowired
    private ScanQrcodeXmlLoader scanQrcodeXmlLoader;

    @Autowired
    private PayComponent payComponent;
    /**
     * 接受前端用户选定物品下单,传递过来的一些物品的参数
     * @param unifiedOrder
     * @return
     */
    @RequestMapping("buyNew")
    public Map<String,Object> jsApiPay(UnifiedOrder unifiedOrder) throws Exception {
        System.out.println("jsApiPay opened ...");
//
        String appid = WeixinInsLoader.getWeixinInstance().getAppId();
        System.out.println(appid);
        String mch_id = WeixinInsLoader.getWeixinInstance().getWeixinPayConfig().getPartnerId();
        System.out.println(mch_id);
        String nonce_str = java.util.UUID.randomUUID().toString().substring(0, 15);
        System.out.println(nonce_str);
        //openid : trade_type=JSAPI时（即JSAPI支付），此参数必传，此参数为微信用户在商户对应appid下的唯一标识
        //获取openid
//        String openid = scanQrcodeXmlLoader.getScanQrcodeXml().getFromUserName();

        String body = unifiedOrder.getBody();
        System.out.println(body);
        String out_trade_no = "hellotest"+new Date().getTime();
        System.out.println(out_trade_no);
        String total_fee = unifiedOrder.getTotal_fee();
        System.out.println(total_fee);
        String spbill_create_ip = "123.12.12.123";
        System.out.println(spbill_create_ip);
        String notify_url = WeixinInsLoader.getWeixinInstance().getWeixinPayConfig().getNotifyUrl();
        System.out.println(notify_url);
        String trade_type = "JSAPI";
        System.out.println(trade_type);

        Map<String,String> paramsMap = new HashMap<>();
        paramsMap.put("appid",appid);
        paramsMap.put("mch_id",mch_id);
        paramsMap.put("nonce_str",nonce_str);
        paramsMap.put("body",body);
        paramsMap.put("out_trade_no",out_trade_no);
        paramsMap.put("total_fee",total_fee);
        paramsMap.put("spbill_create_ip",spbill_create_ip);
        paramsMap.put("notify_url",notify_url);
        paramsMap.put("trade_type",trade_type);
//        paramsMap.put("openid",openid);//正式时使用
        paramsMap.put("openid","o8tDTw5SzaMcsAs662AycwCareyQ");//测试时使用


        String APIKey = WeixinInsLoader.getWeixinInstance().getWeixinPayConfig().getPartnerKey();
        System.out.println(APIKey);
        String sign = SignUtil.getSign(paramsMap,APIKey);
        System.out.println(sign);
        paramsMap.put("sign",sign);

        /*system.out*/
        System.out.println(total_fee);
//        System.out.println("openid:"+openid);

        /*统一下单参数设置*/


        //设置用户openid (谁下的单)
//        unifiedOrder.setOpenid(openid); //正常使用时
        //设置用户openid (测试)
        unifiedOrder.setOpenid("o8tDTw5SzaMcsAs662AycwCareyQ"); //测试时使用

        //设置公众平台(服务号)appid
        unifiedOrder.setAppid(appid);
        //设置商户平台id
        unifiedOrder.setMch_id(mch_id);
        //设置随机字符串
        unifiedOrder.setNonce_str(nonce_str);
        //
        unifiedOrder.setBody(body);
        //设置商户订单号
        unifiedOrder.setOut_trade_no(out_trade_no);
        //
        unifiedOrder.setTotal_fee(total_fee);
        //设置终端ip地址
        unifiedOrder.setSpbill_create_ip(spbill_create_ip);
        //
        unifiedOrder.setNotify_url(notify_url);
        //设置支付方式 JSAPI 公众号支付
        unifiedOrder.setTrade_type(trade_type);
        //设置签名
        unifiedOrder.setSign(sign);


        /**
         * 调用统一下单方法
         */
        UnifiedOrderResult unifiedOrderResult = payComponent.payUnifiedOrder(unifiedOrder);
        System.out.println("unifiedOrderResult.toString():"+unifiedOrderResult.toString());

        /*获取统一下单返回参数*/


        /*返回数据*/
        System.out.println("unifiedOrderResult.toString():"+unifiedOrderResult.toString());

        //预支付id
        String prepay_id = unifiedOrderResult.getPrepay_id();


        /*判定返回是否成功*/
        boolean success = unifiedOrderResult.isSuccess();
        System.out.println("success:"+success);
        boolean resultIsSuccess = unifiedOrderResult.resultIsSuccess();
        System.out.println("resultIsSuccess:"+resultIsSuccess);

        WCPay wcPay = null;
        Map<String,Object> map = null;
        if(success && resultIsSuccess){
            //验签
            map = new HashMap();
//            if(signOk){
//                生成新的响应数据到前端,用于调起支付
                wcPay = new WCPay(appid,prepay_id,APIKey);
//            }
        }

        /**
         * 返回WCPay (网页支付调用参数)
         *     private final String appId;             //公众号Id
         *     private final String timeStamp;         //时间戳
         *     private final String nonceStr;          //随机字符串
         *     private final String packages;          //订单详情扩展字符串 统一下单接口返回的prepay_id参数值，提交格式如：prepay_id=***
         *     private final String signType = "MD5";  //签名方式 签名算法，暂支持MD5
         *     private final String paySign;           //签名
         */
        System.out.println(wcPay.toString());
        map.put("WCPAY",wcPay);
        return map;
    }





}
