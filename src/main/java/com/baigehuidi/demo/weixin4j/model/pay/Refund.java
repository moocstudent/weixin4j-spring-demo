package com.baigehuidi.demo.weixin4j.model.pay;

import java.util.HashMap;
import java.util.Map;

/**
 * 申请退款请求参数
 * 当交易发生之后一段时间内,由于买家或卖家的原因需要退款时,卖家可以通过退款接口将
 * 支付款退还给买家,微信支付将在收到退款请求并且验证成功之后,按照退款规则将支付款
 * 按原路退到买家账号上.
 * 注意: <<<,
 * 1.<< 交易时间超过一年的订单无法提交退款.
 * 2.<< 微信支付退款支持单笔交易分多次退款, 多次退款需要提交原支付订单的商户订单号
 *      和设置不同的退款单号. 申请退款总金额不能超过订单金额.
 *      <b></>>>>一笔退款失败后重新提交,请不要更换退款单号,请使用原商户退款单号<<<</b>
 * 3.<< 请求频率限制 : 150qps, 即每秒钟正常的申请退款请求次数不超过150次
 *      错误或无效请求频率限制 : 6qps, 即每秒钟异常或错误的退款申请请求不超过6次
 * 4.<< 每个支付订单的部分退款次数不能超过50次
 * 是否需要证书!? 是的, 请求需要双向证书. 证书使用 https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=4_3
 * https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_4
 */

public class Refund {
    //退款的请求参数

    //公众账号ID String(32) 微信分配的公众账号ID(企业号corpid即为该appid)
    private String appid;
    //商户号 String(32) 微信支付分配的商户号
    private String mch_id;
    //随机字符串 String(32) 随机字符串不长于32位,见生成算法https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=4_3
    private String nonce_str;
    private String sign; //签名 String(32)
    private String sign_type; //签名类型 String(32)
    private String transaction_id; //微信订单号 String(32) 微信生成的订单号,在支付通知中有返回
    private String out_trade_no; //商户订单号 String(32) 商户系统内部订单号,要求32个字符内,只能是数字,大小写字母_-|*@ ，且在同一个商户号下唯一
    private String out_refund_no; //商户退款单号 String(64) 商户系统内部的退款单号,商户系统内部唯一,只能是数字,大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
    private Integer total_fee; //订单总金额,int 单位为分,只能为整数,详见支付金额
    private Integer refund_fee; //退款金额,int 退款总金额,订单总金额,单位为分, 只能为整数.详见支付金额.
    private String refund_fee_type; //退款币种种类 String(8) 退款货币类型,需与支付一致,或者不填,符合ISO 4217标准的三位字母代码,默认人民币:CNY,其它值列表详见货币类型
    private String refund_desc; //退款原因 String(80) 若商户传入,会在下发给用户的退款消息中体现退款原因
    private String refund_account; //退款资金来源 String(30) 仅针对老资金流商户使用,REFUND_SOURCE_UNSETTLED_FUNDS--未结算资金退款(默认使用未结算资金退款) REFUND_SOURCE_RECHARGE_FUNDS -- 可用余额退款
    private String notify_url; //退款结果通知url String(256) 异步接收微信支付退款结果通知的回调地址,通知URL必须为外网可访问的url,不允许带参数,如果参数中传了notify_url,则商户平台上配置的回调地址将不会生效.

    public Map<String,String> toMap(){
        Map<String,String> map = new HashMap<String,String>();
        map.put("appid",appid);
        map.put("mch_id",mch_id);
        map.put("nonce_str",nonce_str);
        map.put("sign",sign);
        map.put("sign_type",sign_type);
        map.put("transaction_id",transaction_id);
        map.put("out_trade_no",out_trade_no);
        map.put("out_refund_no",out_refund_no);
        map.put("total_fee",String.valueOf(total_fee));
        map.put("refund_fee",String.valueOf(refund_fee));
        map.put("refund_fee_type",refund_fee_type);
        map.put("refund_desc",refund_desc);
        map.put("refund_account",refund_account);
        map.put("notify_url",notify_url);
        return map;
    }

    /**
     * 官方示例版
     * @return
     */
    public String toXMLSample(){
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        sb.append("<appid><![CDATA[").append(appid).append("]]</appid>");
        sb.append("<mch_id><![CDATA[").append(mch_id).append("]]</mch_id>");
        sb.append("<nonce_str><![CDATA[").append(nonce_str).append("]]</nonce_str>");
        sb.append("<out_refund_no><![CDATA[").append(out_refund_no).append("]]</out_refund_no>");
        sb.append("<out_trade_no><![CDATA[").append(out_trade_no).append("]]</out_trade_no>");
        sb.append("<refund_fee><![CDATA[").append(refund_fee).append("]]</refund_fee>");
        sb.append("<total_fee><![CDATA[").append(total_fee).append("]]</total_fee>");
        sb.append("<total_fee><![CDATA[").append(total_fee).append("]]</total_fee>");
        sb.append("<transaction_id><![CDATA[").append(transaction_id).append("]]</transaction_id>");
        sb.append("<sign><![CDATA[").append(sign).append("]]</sign>");
        sb.append("</xml>");
        return sb.toString();
    }

    /**
     * 全套版
     * @return
     */
    public String toXML(){
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        sb.append("<appid><![CDATA[").append(appid).append("]]</appid>");
        sb.append("<mch_id><![CDATA[").append(mch_id).append("]]</mch_id>");
        sb.append("<nonce_str><![CDATA[").append(nonce_str).append("]]</nonce_str>");
        sb.append("<sign><![CDATA[").append(sign).append("]]</sign>");
        sb.append("<sign_type><![CDATA[").append(sign_type).append("]]</sign_type>");
        sb.append("<transaction_id><![CDATA[").append(transaction_id).append("]]</transaction_id>");
        sb.append("<out_trade_no><![CDATA[").append(out_trade_no).append("]]</out_trade_no>");
        sb.append("<out_refund_no><![CDATA[").append(out_refund_no).append("]]</out_refund_no>");
        sb.append("<total_fee><![CDATA[").append(total_fee).append("]]</total_fee>");
        sb.append("<refund_fee><![CDATA[").append(refund_fee).append("]]</refund_fee>");
        sb.append("<refund_fee_type><![CDATA[").append(refund_fee_type).append("]]</refund_fee_type>");
        sb.append("<refund_desc><![CDATA[").append(refund_desc).append("]]</refund_desc>");
        sb.append("<refund_account><![CDATA[").append(refund_account).append("]]</refund_account>");
        sb.append("<notify_url><![CDATA[").append(notify_url).append("]]</notify_url>");
        sb.append("</xml>");
        return sb.toString();
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public void setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
    }

    public void setTotal_fee(Integer total_fee) {
        this.total_fee = total_fee;
    }

    public void setRefund_fee(Integer refund_fee) {
        this.refund_fee = refund_fee;
    }

    public void setRefund_fee_type(String refund_fee_type) {
        this.refund_fee_type = refund_fee_type;
    }

    public void setRefund_desc(String refund_desc) {
        this.refund_desc = refund_desc;
    }

    public void setRefund_account(String refund_account) {
        this.refund_account = refund_account;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getAppid() {
        return appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public String getSign_type() {
        return sign_type;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public String getOut_refund_no() {
        return out_refund_no;
    }

    public Integer getTotal_fee() {
        return total_fee;
    }

    public Integer getRefund_fee() {
        return refund_fee;
    }

    public String getRefund_fee_type() {
        return refund_fee_type;
    }

    public String getRefund_desc() {
        return refund_desc;
    }

    public String getRefund_account() {
        return refund_account;
    }

    public String getNotify_url() {
        return notify_url;
    }
}
