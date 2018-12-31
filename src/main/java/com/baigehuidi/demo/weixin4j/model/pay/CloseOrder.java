package com.baigehuidi.demo.weixin4j.model.pay;

import java.util.HashMap;
import java.util.Map;

/**
 * 关闭订单请求参数
 * 以下情况需要调用关单接口:商户订单支付失败需要生成新单号重新发起支付,要对原订单号调用关单,
 * 避免重复支付;系统下单后,用户支付超时,系统退出不再受理,避免用户继续,请调用关单接口.
 * 注意: <<<, 订单生成后不能马上调用关单接口,最短调用时间间隔为5分钟.
 * https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_3
 * 是否需要整数: 不需要
 */
public class CloseOrder {

    private String appid; //公众账号ID String(32) 微信分配的公众账号ID（企业号corpid即为此appId）
    private String mch_id; //商户号 String(32) 微信支付分配的商户号
    private String out_trade_no; //商户订单号 String(32) 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
    private String nonce_str; //随机字符串 String(32) 随机字符串,不长于32位,推挤随机数生成算法https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=4_3
    private String sign; //签名 String(32) 签名,详见上面同样的生成算法
    private String sign_type; //签名类型 String(32) 签名类型，目前支持HMAC-SHA256和MD5，默认为MD5


    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("appid", appid);
        map.put("mch_id", mch_id);
        map.put("out_trade_no", out_trade_no);
        map.put("nonce_str", nonce_str);
        map.put("sign", sign);
        map.put("sign_type", sign_type);
        return map;
    }

    public String toXML(){
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        sb.append("<appid><![CDATA[").append(appid).append("]]</appid>");
        sb.append("<mch_id><![CDATA[").append(mch_id).append("]]></mch_id>");
        sb.append("<out_trade_no><![CDATA[").append(out_trade_no).append("]]></out_trade_no>");
        sb.append("<nonce_str><![CDATA[").append(nonce_str).append("]]></nonce_str>");
        sb.append("<sign><![CDATA[").append(sign).append("]]></sign>");
        sb.append("<sign_type><![CDATA[").append(sign_type).append("]]></sign_type>");
        sb.append("</xml>");
        return sb.toString();
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
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

    public String getAppid() {
        return appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
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


}
