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
package com.baigehuidi.demo.weixin4j.model.pay;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 查询订单结果
 * 该接口提供所有微信支付订单的查询，商户可以通过查询订单接口主动查询订单状态，完成下一步的业务逻辑。
 *
 * 需要调用查询接口的情况：
 *
 * ◆ 当商户后台、网络、服务器等出现异常，商户系统最终未接收到支付通知；
 * ◆ 调用支付接口后，返回系统错误或未知交易状态情况；
 * ◆ 调用付款码支付API，返回USERPAYING的状态；
 * ◆ 调用关单或撤销接口API之前，需确认支付状态；
 *
 * https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_2
 *
 * 注意以下的变量 结尾为 _$n 实则 应设置为 _0 , _1 : 如代金券0  代金券1
 *
 * @author 杨启盛<qsyang@ansitech.com> & zq
 * @since 0.1.1
 */
@XmlRootElement(name = "xml")
public class OrderQueryResult {

    private String return_code;//[返回状态码] String(16) SUCCESS/FAIL 此字段是通信标识,非交易标识,交易是否成功需要查看trade_state来判断
    private String return_msg; //[返回信息] String(128) 当return_code为FAIL时返回信息为错误原因,例如:签名失败,参数格式校验错误
    //以下字段在return_code为SUCCESS的时候有返回 
    private String appid;               //公众账号ID String(32) 微信分配的公众账号ID
    private String mch_id;             //商户号 String(32) 微信支付分配的商户号
    private String nonce_str;          //随机字符串 String(32) 随机字符串,不长于32位,推荐随机数生成算法https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=4_3
    private String sign;               //签名 String(32) 签名,详见签名生成算法https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=4_3
    private String result_code;           //[业务结果] String(16) SUCCESS/FAIL
    private String err_code;            //错误代码 String(32) 当result_code为FAIL时返回错误代码
    private String err_code_des;        //错误代码描述 String(128) 当result_code为FAIL时返回错误描述
    //以下字段在return_code 、result_code、trade_state都为SUCCESS时有返回 ，如trade_state不为 SUCCESS，则只返回out_trade_no（必传）和attach（选传）。
    private String device_info; //[设备号] String(32)
    private String openid;//[用户标识] String(128) 用户在商户appid下的唯一标识
    private String is_subscribe;//[是否关注公众账号] String(1) 用户是否关注公众账号，Y-关注，N-未关注
    private String trade_type; //[交易类型] String(16)
    private String trade_state; //[交易状态] String(32)
    private String bank_type; //[付款银行] String(16) 银行类型,采用字符串类型的银行标识
    private int total_fee; //[标价金额]订单总金额,单位为分
    private int settlement_total_fee; //[应结订单金额] 单位分,当订单使用了免充值型优惠券后返回该参数，应结订单金额=订单金额-免充值优惠券金额。
    private String fee_type; //[标价币种]货币类型，String(8) 符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
    private int cash_fee; //[现金支付金额] 单位为分,现金支付金额订单现金支付金额，详见支付金额
    private String cash_fee_type; //[现金支付币种] String(16) 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
    private int coupon_fee; //[代金券金额]单位分,“代金券”金额<=订单金额，订单金额-“代金券”金额=现金支付金额，详见支付金额
    private int coupon_count; //[代金券使用数量]代金券使用数量
    private String coupon_type_$0; //[代金券类型]String ,CASH--充值代金券 NO_CASH---非充值优惠券,开通免充值券功能，并且订单使用了优惠券后有返回（取值：CASH、NO_CASH）。$n为下标,从0开始编号，举例：coupon_type_$0
    private String 	coupon_id_$0; //[代金券ID]代金券ID, String(20) $n为下标，从0开始编号
    private int coupon_fee_$0; //[单个代金券支付金额] 单个代金券支付金额,$n为下标,从0开始编号
    private String transaction_id;      //微信支付订单号 String(32)
    private String attach;              //附加数据 String(128)
    private String out_trade_no;        //商户订单号 String(32)商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
    private String time_end;            //支付完成时间 String(14)
    private String trade_state_desc;    //交易状态描述 String(256) 对当前查询订单状态的描述和下一步操作的指引


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
    public  boolean resultIsSuccess(){
        if(result_code==null||result_code.equals("")){
            return false;
        }
        return result_code.toUpperCase().equals("SUCCESS");
    }

    public String getReturn_code() {
        return return_code;
    }

    @XmlElement(name = "return_code")
    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    @XmlElement(name = "return_msg")
    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public String getAppid() {
        return appid;
    }

    @XmlElement(name = "appid")
    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    @XmlElement(name = "mch_id")
    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    @XmlElement(name = "nonce_str")
    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    @XmlElement(name = "sign")
    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getResult_code() {
        return result_code;
    }

    @XmlElement(name = "result_code")
    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getErr_code() {
        return err_code;
    }

    @XmlElement(name = "err_code")
    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public String getErr_code_des() {
        return err_code_des;
    }

    @XmlElement(name = "err_code_des")
    public void setErr_code_des(String err_code_des) {
        this.err_code_des = err_code_des;
    }

    public String getDevice_info() {
        return device_info;
    }

    @XmlElement(name = "device_info")
    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getOpenid() {
        return openid;
    }

    @XmlElement(name = "openid")
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getIs_subscribe() {
        return is_subscribe;
    }

    @XmlElement(name = "is_subscribe")
    public void setIs_subscribe(String is_subscribe) {
        this.is_subscribe = is_subscribe;
    }

    public String getTrade_type() {
        return trade_type;
    }

    @XmlElement(name = "trade_type")
    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getTrade_state() {
        return trade_state;
    }

    @XmlElement(name = "trade_state")
    public void setTrade_state(String trade_state) {
        this.trade_state = trade_state;
    }

    public String getBank_type() {
        return bank_type;
    }

    @XmlElement(name = "bank_type")
    public void setBank_type(String bank_type) {
        this.bank_type = bank_type;
    }

    public int getTotal_fee() {
        return total_fee;
    }

    @XmlElement(name = "total_fee")
    public void setTotal_fee(int total_fee) {
        this.total_fee = total_fee;
    }

    public int getSettlement_total_fee() {
        return settlement_total_fee;
    }

    @XmlElement(name = "settlement_total_fee")
    public void setSettlement_total_fee(int settlement_total_fee) {
        this.settlement_total_fee = settlement_total_fee;
    }

    public String getFee_type() {
        return fee_type;
    }

    @XmlElement(name = "fee_type")
    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public int getCash_fee() {
        return cash_fee;
    }

    @XmlElement(name = "cash_fee")
    public void setCash_fee(int cash_fee) {
        this.cash_fee = cash_fee;
    }

    public String getCash_fee_type() {
        return cash_fee_type;
    }

    @XmlElement(name = "cash_fee_type")
    public void setCash_fee_type(String cash_fee_type) {
        this.cash_fee_type = cash_fee_type;
    }

    public int getCoupon_fee() {
        return coupon_fee;
    }

    @XmlElement(name = "coupon_fee")
    public void setCoupon_fee(int coupon_fee) {
        this.coupon_fee = coupon_fee;
    }

    public int getCoupon_count() {
        return coupon_count;
    }

    @XmlElement(name = "coupon_count")
    public void setCoupon_count(int coupon_count) {
        this.coupon_count = coupon_count;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    @XmlElement(name = "transaction_id")
    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getAttach() {
        return attach;
    }

    @XmlElement(name = "attach")
    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    @XmlElement(name = "out_trade_no")
    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getTime_end() {
        return time_end;
    }

    @XmlElement(name = "time_end")
    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    public String getTrade_state_desc() {
        return trade_state_desc;
    }

    @XmlElement(name = "trade_state_desc")
    public void setTrade_state_desc(String trade_state_desc) {
        this.trade_state_desc = trade_state_desc;
    }
}
