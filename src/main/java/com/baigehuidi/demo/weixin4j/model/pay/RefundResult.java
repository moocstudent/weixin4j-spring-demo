package com.baigehuidi.demo.weixin4j.model.pay;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 申请退款返回参数 https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_4
 * 需要双向证书
 */
@XmlRootElement(name = "xml")
public class RefundResult {
    //返回字段

    //返回状态码 String(16) 必填 示例:SUCCESS/FAIL 此字段是通信标识,非交易标识,交易是否成功需要查看trade_state来判断
    private String return_code;
    //返回信息 String(128) 必填 示例:OK 当return_code为FAIL时返回信息为错误原因,例如签名失败,参数格式校验错误
    private String return_msg;
    /* ============以下字段在return_code为SUCCESS的时候有返回============ */
    //业务结果 String(16) 必填 示例:SUCCESS/FAIL SUCCESS退款申请接收成功,结果通过退款查询接口查询,FAIL提交业务失败
    private String result_code;
    //错误代码 String(32) 非必填 示例:SYSTEMERROR 列表详见错误列表https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_4
    private String err_code;
    //错误代码描述 String(128) 非必填 示例:系统超时 结果信息描述
    private String err_code_des;
    //公众账号ID String(32) 必填 示例:wx8888888888888888 微信分配的公众账号ID
    private String appid;
    //商户号 String(32) 必填 示例1900000109 微信支付分配的商户号
    private String mch_id;
    //随机字符串 String(32) 必填 示例:5K8264ILTKCH16CQ2502SI8ZNMTM67VS 随机字符串,不长于32位
    private String nonce_str;
    //签名 String(32) 必填  签名,详见签名算法 示例:5K8264ILTKCH16CQ2502SI8ZNMTM67VS
    private String sign;
    //微信订单号 String(32) 必填 示例:4007752501201407033233368018
    private String transaction_id;
    //商户订单号 String(32) 必填 示例: 33368019 商户系统内部订单号,要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
    private String out_trade_no;
    //商户退款单号 String(64) 必填 示例:121775250 商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
    private String out_refund_no;
    //微信退款单号 String(32) 必填 示例:2007752501201407033233368018
    private String refund_id;
    //退款金额 int 必填 退款总金额,单位为分,可以做部分退款
    private Integer refund_fee;
    //应结退款金额 int 非必填 去掉非充值代金券退款金额后的退款金额,退款金额=申请退款金额-非充值代金券退款金额,退款金额<=申请退款金额
    private Integer settlement_refund_fee;
    //标价金额, 必填 int 订单总金额,单位为分,只能为整数,详见支付金额
    private Integer total_fee;
    //应结订单金额 int 非必填 去掉非充值代金券金额后的订单总金额,应结订单金额=订单金额=非充值代金券金额,应结订单金额<=订单金额
    private Integer settlement_total_fee;
    //标价币种 非必填 String(8) 示例:CNY
    private String fee_type;
    //现金支付金额 必填 int 现金支付金额,单位为分,只能为整数
    private Integer cash_fee;
    //现金支付币种  String(16) 非必填 示例:CNY
    private String cash_fee_type;
    //现金退款金额 int 非必填  现金退款金额,单位为分,只能为整数
    private Integer cash_refund_fee;
    //代金券类型 非必填 String(8) 示例:CASH
    private String coupon_type_$n;
    //代金券退款总金额 非必填 int
    private Integer coupon_refund_fee;
    //单个代金券退款金额 int 非必填
    private Integer coupon_refund_fee_$n;
    //退款代金券使用数量 非必填 int 退款代金券使用数量
    private Integer coupon_refund_count;
    //退款代金券ID String(20) 非必填
    private String coupon_refund_id_$n;


    /**
     * 通信是否成功?
     * @return 成true, 否失败返回false
     */
    public boolean isSuccess(){
        if(return_code==null || return_code.equals("")){
            return false;
        }
        return return_code.toUpperCase().equals("SUCCESS");
    }

    /**
     * 业务查询是否成功?
     * @return
     */
    public boolean resultIsSuccess(){
        if(result_code==null || result_code.equals("")){
            return false;
        }
        return result_code.toUpperCase().equals("SUCCESS");
    }


    public String getReturn_code() {
        return return_code;
    }

    @XmlElement(name="return_code")
    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    @XmlElement(name="return_msg")
    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public String getResult_code() {
        return result_code;
    }

    @XmlElement(name="result_code")
    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getErr_code() {
        return err_code;
    }

    @XmlElement(name="err_code")
    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public String getErr_code_des() {
        return err_code_des;
    }

    @XmlElement(name="err_code_des")
    public void setErr_code_des(String err_code_des) {
        this.err_code_des = err_code_des;
    }

    public String getAppid() {
        return appid;
    }

    @XmlElement(name="appid")
    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    @XmlElement(name="mch_id")
    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    @XmlElement(name="nonce_str")
    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    @XmlElement(name="sign")
    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    @XmlElement(name="transaction_id")
    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    @XmlElement(name="out_trade_no")
    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getOut_refund_no() {
        return out_refund_no;
    }

    @XmlElement(name="out_refund_no")
    public void setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
    }

    public String getRefund_id() {
        return refund_id;
    }

    @XmlElement(name="refund_id")
    public void setRefund_id(String refund_id) {
        this.refund_id = refund_id;
    }

    public Integer getRefund_fee() {
        return refund_fee;
    }

    @XmlElement(name="refund_fee")
    public void setRefund_fee(Integer refund_fee) {
        this.refund_fee = refund_fee;
    }

    public Integer getSettlement_refund_fee() {
        return settlement_refund_fee;
    }

    @XmlElement(name="settlement_refund_fee")
    public void setSettlement_refund_fee(Integer settlement_refund_fee) {
        this.settlement_refund_fee = settlement_refund_fee;
    }

    public Integer getTotal_fee() {
        return total_fee;
    }

    @XmlElement(name="total_fee")
    public void setTotal_fee(Integer total_fee) {
        this.total_fee = total_fee;
    }

    public Integer getSettlement_total_fee() {
        return settlement_total_fee;
    }

    @XmlElement(name="settlement_total_fee")
    public void setSettlement_total_fee(Integer settlement_total_fee) {
        this.settlement_total_fee = settlement_total_fee;
    }

    public String getFee_type() {
        return fee_type;
    }

    @XmlElement(name="fee_type")
    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public Integer getCash_fee() {
        return cash_fee;
    }

    @XmlElement(name="cash_fee")
    public void setCash_fee(Integer cash_fee) {
        this.cash_fee = cash_fee;
    }

    public String getCash_fee_type() {
        return cash_fee_type;
    }

    @XmlElement(name="cash_fee_type")
    public void setCash_fee_type(String cash_fee_type) {
        this.cash_fee_type = cash_fee_type;
    }

    public Integer getCash_refund_fee() {
        return cash_refund_fee;
    }

    @XmlElement(name="cash_refund_fee")
    public void setCash_refund_fee(Integer cash_refund_fee) {
        this.cash_refund_fee = cash_refund_fee;
    }

    public String getCoupon_type_$n() {
        return coupon_type_$n;
    }

    @XmlElement(name="coupon_type_$n")
    public void setCoupon_type_$n(String coupon_type_$n) {
        this.coupon_type_$n = coupon_type_$n;
    }

    public Integer getCoupon_refund_fee() {
        return coupon_refund_fee;
    }

    @XmlElement(name="coupon_refund_fee")
    public void setCoupon_refund_fee(Integer coupon_refund_fee) {
        this.coupon_refund_fee = coupon_refund_fee;
    }

    public Integer getCoupon_refund_fee_$n() {
        return coupon_refund_fee_$n;
    }

    @XmlElement(name="coupon_refund_fee_$n")
    public void setCoupon_refund_fee_$n(Integer coupon_refund_fee_$n) {
        this.coupon_refund_fee_$n = coupon_refund_fee_$n;
    }

    public Integer getCoupon_refund_count() {
        return coupon_refund_count;
    }

    @XmlElement(name="coupon_refund_count")
    public void setCoupon_refund_count(Integer coupon_refund_count) {
        this.coupon_refund_count = coupon_refund_count;
    }

    public String getCoupon_refund_id_$n() {
        return coupon_refund_id_$n;
    }

    @XmlElement(name="coupon_refund_id_$n")
    public void setCoupon_refund_id_$n(String coupon_refund_id_$n) {
        this.coupon_refund_id_$n = coupon_refund_id_$n;
    }
}
