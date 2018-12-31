package com.baigehuidi.demo.weixin4j.model.pay;

import javax.xml.bind.annotation.XmlElement;

/**
 * 查询退款的返回数据
 */
public class RefundQueryResult {

    //返回状态码 String(16) 必填 示例:SUCCESS
    private String return_code;
    //返回信息 String(128) 必填 示例:OK
    private String return_msg;
    /*============以下字段在return_code为SUCCESS的时候有返回===========*/
    //业务结果 String(16) 必填
    private String result_code;
    //错误码 String(32) 必填
    private String err_code;
    //错误描述 String(128) err_code_des
    private String err_code_des;
    //公众账号ID String(32) 必填
    private String appid;
    //商户号 String(32) 必填
    private String mch_id;
    //随机字符串 String(32) 必填
    private String nonce_str;
    //签名 String(32) 必填
    private String sign;
    //订单总退款次数 int 非必填
    private Integer total_refund_count;
    //微信订单号 String(32) 必填
    private String transaction_id;
    //商户订单号 String(32) 必填
    private String out_trade_no;
    //订单金额 计量:分 int 必填
    private Integer total_fee;
    //应结订单金额 计量:分 int 非必填
    private Integer settlement_total_fee;
    //货币种类 String(8) 非必填
    private String fee_type;
    //现金支付金额 计量:分 int 必填
    private Integer cash_fee;
    //退款笔数 int 必填 当前返回退款笔数
    private Integer refund_count;
    //商户退款单号 String(64) 必填  其中$n为数字如$0
    private String out_refund_no_$n;
    //微信退款单号 String(32) 必填
    private String refund_id_$n;
    //退款渠道 String(16) 非必填
    //ORIGINAL—原路退款
    //BALANCE—退回到余额
    //OTHER_BALANCE—原账户异常退到其他余额账户
    //OTHER_BANKCARD—原银行卡异常退到其他银行卡
    private String refund_channel_$n;
    //申请退款金额 int 必填
    private Integer refund_fee_$n;
    //退款金额 int 非必填
    private Integer settlement_refund_fee_$n;
    //代金券类型 String(8) 非必填
    //CASH--充值代金券
    //NO_CASH---非充值优惠券
    //开通免充值券功能，并且订单使用了优惠券后有返回（取值：CASH、NO_CASH）。$n为下标,$m为下标,从0开始编号，举例：coupon_type_$0_$1
    private String coupon_type_$n_$m;
    //总代金券退款金额 int 非必填 代金券退款金额<=退款金额，退款金额-代金券或立减优惠退款金额为现金，说明详见代金券或立减优惠
    private Integer coupon_refund_fee_$n;
    //退款代金券使用数量 int 非必填 退款代金券使用数量 ,$n为下标,从0开始编号
    private Integer coupon_refund_count_$n;
    //退款代金券ID String(20) 非必填 退款代金券ID, $n为下标，$m为下标，从0开始编号
    private String coupon_refund_id_$n_$m;
    //单个代金券退款金额 int 非必填 单个退款代金券支付金额, $n为下标，$m为下标，从0开始编号
    private Integer coupon_refund_fee_$n_$m;
    //退款状态 String(16) 必填 退款状态：
    //SUCCESS—退款成功
    //REFUNDCLOSE—退款关闭。
    //PROCESSING—退款处理中
    //CHANGE—退款异常，退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，可前往商户平台（pay.weixin.qq.com）-交易中心，手动处理此笔退款。$n为下标，从0开始编号。
    private String refund_status_$n;
    //退款资金来源 String(30) 非必填
    //REFUND_SOURCE_RECHARGE_FUNDS---可用余额退款/基本账户
    //REFUND_SOURCE_UNSETTLED_FUNDS---未结算资金退款
    //$n为下标，从0开始编号。
    private String refund_account_$n;
    //退款入账账户 String(64) 必填 示例: 招商银行信用卡0403
    //取当前退款单的退款入账方
    //1）退回银行卡：
    //{银行名称}{卡类型}{卡尾号}
    //2）退回支付用户零钱:
    //支付用户零钱
    //3）退还商户:
    //商户基本账户
    //商户结算银行账户
    //4）退回支付用户零钱通:
    //支付用户零钱通
    private String refund_recv_accout_$n;
    //退款成功时间 String(20) 非必填  退款成功时间，当退款状态为退款成功时有返回。$n为下标，从0开始编号。
    private String refund_success_time_$n;


    /**
     * 通信是否成功
     *
     * @return 成功返回True，否则返回false
     */
    public boolean isSuccess() {
        if (return_code == null || return_code.equals("")) {
            return false;
        }
        return return_code.toUpperCase().equals("SUCCESS");
    }

    /**
     * 结果查询是否成功
     * @return
     */
    public boolean resultIsSuccess() {
        if (result_code == null || result_code.equals("")) {
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

    public Integer getTotal_refund_count() {
        return total_refund_count;
    }

    @XmlElement(name="total_refund_count")
    public void setTotal_refund_count(Integer total_refund_count) {
        this.total_refund_count = total_refund_count;
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

    public Integer getRefund_count() {
        return refund_count;
    }

    @XmlElement(name="refund_count")
    public void setRefund_count(Integer refund_count) {
        this.refund_count = refund_count;
    }

    public String getOut_refund_no_$n() {
        return out_refund_no_$n;
    }

    @XmlElement(name="out_refund_no_$n")
    public void setOut_refund_no_$n(String out_refund_no_$n) {
        this.out_refund_no_$n = out_refund_no_$n;
    }

    public String getRefund_id_$n() {
        return refund_id_$n;
    }

    @XmlElement(name="refund_id_$n")
    public void setRefund_id_$n(String refund_id_$n) {
        this.refund_id_$n = refund_id_$n;
    }

    public String getRefund_channel_$n() {
        return refund_channel_$n;
    }

    @XmlElement(name="refund_channel_$n")
    public void setRefund_channel_$n(String refund_channel_$n) {
        this.refund_channel_$n = refund_channel_$n;
    }

    public Integer getRefund_fee_$n() {
        return refund_fee_$n;
    }

    @XmlElement(name="refund_fee_$n")
    public void setRefund_fee_$n(Integer refund_fee_$n) {
        this.refund_fee_$n = refund_fee_$n;
    }

    public Integer getSettlement_refund_fee_$n() {
        return settlement_refund_fee_$n;
    }

    @XmlElement(name="settlement_refund_fee_$n")
    public void setSettlement_refund_fee_$n(Integer settlement_refund_fee_$n) {
        this.settlement_refund_fee_$n = settlement_refund_fee_$n;
    }

    public String getCoupon_type_$n_$m() {
        return coupon_type_$n_$m;
    }

    @XmlElement(name="coupon_type_$n_$m")
    public void setCoupon_type_$n_$m(String coupon_type_$n_$m) {
        this.coupon_type_$n_$m = coupon_type_$n_$m;
    }

    public Integer getCoupon_refund_fee_$n() {
        return coupon_refund_fee_$n;
    }

    @XmlElement(name="coupon_refund_fee_$n")
    public void setCoupon_refund_fee_$n(Integer coupon_refund_fee_$n) {
        this.coupon_refund_fee_$n = coupon_refund_fee_$n;
    }

    public Integer getCoupon_refund_count_$n() {
        return coupon_refund_count_$n;
    }

    @XmlElement(name="coupon_refund_count_$n")
    public void setCoupon_refund_count_$n(Integer coupon_refund_count_$n) {
        this.coupon_refund_count_$n = coupon_refund_count_$n;
    }

    public String getCoupon_refund_id_$n_$m() {
        return coupon_refund_id_$n_$m;
    }

    @XmlElement(name="coupon_refund_id_$n_$m")
    public void setCoupon_refund_id_$n_$m(String coupon_refund_id_$n_$m) {
        this.coupon_refund_id_$n_$m = coupon_refund_id_$n_$m;
    }

    public Integer getCoupon_refund_fee_$n_$m() {
        return coupon_refund_fee_$n_$m;
    }

    @XmlElement(name="coupon_refund_fee_$n_$m")
    public void setCoupon_refund_fee_$n_$m(Integer coupon_refund_fee_$n_$m) {
        this.coupon_refund_fee_$n_$m = coupon_refund_fee_$n_$m;
    }

    public String getRefund_status_$n() {
        return refund_status_$n;
    }

    @XmlElement(name="refund_status_$n")
    public void setRefund_status_$n(String refund_status_$n) {
        this.refund_status_$n = refund_status_$n;
    }

    public String getRefund_account_$n() {
        return refund_account_$n;
    }

    @XmlElement(name="refund_account_$n")
    public void setRefund_account_$n(String refund_account_$n) {
        this.refund_account_$n = refund_account_$n;
    }

    public String getRefund_recv_accout_$n() {
        return refund_recv_accout_$n;
    }

    @XmlElement(name="refund_recv_accout_$n")
    public void setRefund_recv_accout_$n(String refund_recv_accout_$n) {
        this.refund_recv_accout_$n = refund_recv_accout_$n;
    }

    public String getRefund_success_time_$n() {
        return refund_success_time_$n;
    }

    @XmlElement(name="refund_success_time_$n")
    public void setRefund_success_time_$n(String refund_success_time_$n) {
        this.refund_success_time_$n = refund_success_time_$n;
    }
}
