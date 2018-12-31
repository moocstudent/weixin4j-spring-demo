package com.baigehuidi.demo.weixin4j.model.pay;

import java.util.HashMap;
import java.util.Map;

/**
 * [下载对账单请求参数]
 * 文档:https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_6
 * 商户可以通过该接口下载历史交易清单.比如掉单,系统错误等导致商户侧和微信侧数据不一致,
 * 通过对账单核对后可校正支付状态.
 * 注意:<<<,
 * 1.微信侧未成功下单的交易不会出现在对账单中.支付成功后撤销的交易会出现在对账单中,
 * 跟原支付订单号一致;
 * 2.微信在次日9点启动生成前一天的对账单,建议商户10点后再获取;
 * 3.对账单中涉及金额的字段单位为"元";
 * 4.对账单接口只能下载三个月以内的账单.
 * 5.对账单是以商户号纬度来生成的,如一个商户号与多个appid有绑定关系,则使用其中
 * 任何一个appid都可以请求下载对账单.对账单中的appid取自交易时候提交的appid,
 * 与请求下载对账单的使用的appid无关.
 * 接口链接: https://api.mch.weixin.qq.com/pay/downloadbill
 * 是否需要证书: NO
 */
public class DownLoadBill {
    //下载对账单请求参数

    //公众账号ID String(32) 必填
    private String appid;
    //商户号 String(32) 必填
    private String mch_id;
    //随机字符串 String(32) 必填
    private String nonce_str;
    //签名 String(32) 必填
    private String sign;
    //签名类型 String(32) 非
    private String sign_type;
    //对账单日期 String(8) 必填 示例: 20140603
    private String bill_date;
    //账单类型 String(8) 必填 示例: ALL,返回当日所有订单信息,默认值. SUCCESS,返回当日成功支付的订单. REFUND,返回当日退款订单. RECHARGE_REFUND,返回当日充值退款订单
    private String bill_type;
    //压缩账单 String(8) 非 示例:GZIP ,返回格式为.gzip的压缩包账单,不传则默认为数据流形式.
    private String tar_type;

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("appid", appid);
        map.put("mch_id", mch_id);
        map.put("nonce_str", nonce_str);
        map.put("sign", sign);
        map.put("sign_type", sign_type);
        map.put("bill_date", bill_date);
        map.put("bill_type", bill_type);
        map.put("tar_type", tar_type);
        return map;
    }

    public String toXML(){
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        sb.append("<appid><![CDATA[").append(appid).append("]]</appid>");
        sb.append("<mch_id><![CDATA[").append(mch_id).append("]]></mch_id>");
        sb.append("<nonce_str><![CDATA[").append(nonce_str).append("]]></nonce_str>");
        sb.append("<sign><![CDATA[").append(sign).append("]]></sign>");
        sb.append("<sign_type><![CDATA[").append(sign_type).append("]]></sign_type>");
        sb.append("<bill_date><![CDATA[").append(bill_date).append("]]></bill_date>");
        sb.append("<bill_type><![CDATA[").append(bill_type).append("]]></bill_type>");
        sb.append("<tar_type><![CDATA[").append(tar_type).append("]]></tar_type>");
        sb.append("</xml>");
        return sb.toString();
    }

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

    public String getBill_date() {
        return bill_date;
    }

    public void setBill_date(String bill_date) {
        this.bill_date = bill_date;
    }

    public String getBill_type() {
        return bill_type;
    }

    public void setBill_type(String bill_type) {
        this.bill_type = bill_type;
    }

    public String getTar_type() {
        return tar_type;
    }

    public void setTar_type(String tar_type) {
        this.tar_type = tar_type;
    }
}
