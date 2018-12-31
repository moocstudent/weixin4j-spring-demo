package com.baigehuidi.demo.weixin4j.model.pay;

import java.util.HashMap;
import java.util.Map;

/**
 * 提交退款申请后,通过调用该接口查询退款状态. 退款有一定延时,用零钱支付的退款20分钟内到账,
 * 银行卡支付的退款3个工作日后重新查询退款状态.
 * >>> 注意: 如果单个支付订单部分退款次数超过20次请使用退款单号查询.
 * >> 分页查询: 当一个订单部分退款超过10笔后,商户用微信订单号或商户订单号调退款查询API查询退款时,默认
 *             返回前10笔和total_refund_count(订单总退款次数).商户需要查询同一订单下超过10笔的退款
 *             单时,可传入订单号及offset来查询,微信支付会返回offset及后面的10笔,以此类推.当商户传入
 *             的offset超过total_refund_count,则系统会返回报错PARAM_ERROR.
 * 举例:
 * 一笔订单下的退款有36笔,当商户想查询第25笔时,可传入订单号及offset=24,微信支付平台会返回第25笔到第35笔
 * 的退款单信息,或商户可直接传入退款单号查询退款
 * 流程图: http://assets.processon.com/chart_image/5c177ad7e4b0d84f9a3288da.png
 */
public class RefundQuery {
    //查询退款发送参数:

    //公众账号ID String(32) 必填 微信支付分配的公众账号ID
    private String appid;
    //商户号 String(32) 必填 微信支付分配的商户号
    private String mch_id;
    //随机字符串 String(32) 必填
    private String nonce_str;
    //签名 String(32) 必填
    private String sign;
    //签名类型 String(32) 非必填
    private String sign_type;
    //微信订单号查询的优先级是： refund_id > out_refund_no > transaction_id > out_trade_no
    //微信订单号 String(32) 四选一之一
    private String transaction_id;
    //商户订单号 String(32) 四选一之一
    private String out_trade_no;
    //商户退款单号 String(64) 四选一之一
    private String out_refund_no;
    //微信退款单号 String(32) 四选一之一
    private String refund_id;
    //偏移量 int 当部分退款次数超过10次时可使用,表示返回的查询结果从这个偏移量开始取记录
    private Integer offset;

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
        map.put("refund_id",refund_id);
        map.put("offset",String.valueOf(offset));
        return map;
    }

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
        sb.append("<refund_id><![CDATA[").append(refund_id).append("]]</refund_id>");
        sb.append("<offset><![CDATA[").append(offset).append("]]</offset>");
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

    public String getOut_refund_no() {
        return out_refund_no;
    }

    public void setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
    }

    public String getRefund_id() {
        return refund_id;
    }

    public void setRefund_id(String refund_id) {
        this.refund_id = refund_id;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
