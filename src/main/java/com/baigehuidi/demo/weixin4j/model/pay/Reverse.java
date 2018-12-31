package com.baigehuidi.demo.weixin4j.model.pay;

import java.util.HashMap;
import java.util.Map;

/**
 * [撤销订单请求参数] https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_11
 * 支付交易返回失败或支付系统超时,调用该接口撤销交易. 如果此订单用户支付失败,
 * 微信支付系统会将此订单关闭;如果用户支付成功,微信支付系统会将此订单资金退还给用户.
 * 注意: <<<, 7天以内的交易单可调用撤销,其它正常支付的单如需实现相同功能请调用申请退款API.
 * 提交支付交易后调用[查询订单API(OrderQuery)],没有明确的支付结果再调用[撤销订单API].
 * >>>>>调用支付接口后请勿立即调用撤销订单API,建议支付后至少15s后再调用撤销订单接口.
 * 接口链接: https://api.mch.weixin.qq.com/secapi/pay/reverse
 * 是否需要证书: 需要双向证书 : 证书使用: https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=4_3
 */
public class Reverse {
    //发送的请求撤销订单的参数

    //公众账号ID
    private String appid;
    //商户号
    private String mch_id;
    //微信订单号
    private String transaction_id;
    //商户订单号
    private String out_trade_no;
    //随机字符串
    private String nonce_str;
    //签名
    private String sign;
    //签名类型
    private String sign_type;

    public Map<String,String> toMap(){
        Map<String,String> map = new HashMap<String,String>();
        map.put("appid",appid);
        map.put("mch_id",mch_id);
        map.put("transaction_id",transaction_id);
        map.put("out_trade_no",out_trade_no);
        map.put("nonce_str",nonce_str);
        map.put("sign",sign);
        map.put("sign_type",sign_type);
        return map;
    }

    public String toXML(){
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        sb.append("<appid><![CDATA[").append(appid).append("]]</appid>");
        sb.append("<mch_id><![CDATA[").append(mch_id).append("]]</mch_id>");
        sb.append("<transaction_id><![CDATA[").append(transaction_id).append("]]</transaction_id>");
        sb.append("<out_trade_no><![CDATA[").append(out_trade_no).append("]]</out_trade_no>");
        sb.append("<nonce_str><![CDATA[").append(nonce_str).append("]]</nonce_str>");
        sb.append("<sign><![CDATA[").append(sign).append("]]</sign>");
        sb.append("<sign_type><![CDATA[").append(sign_type).append("]]</sign_type>");
        sb.append("</xml>");
        return sb.toString();
    }


    //get&set 好像只应该设置set,不过无妨


    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }
}
