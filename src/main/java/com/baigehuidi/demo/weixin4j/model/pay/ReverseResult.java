package com.baigehuidi.demo.weixin4j.model.pay;

import javax.xml.bind.annotation.XmlElement;

/**
 * [撤销订单返回参数] 详见 com.baigehuidi.demo.weixin4j.model.pay.Reverse
 */
public class ReverseResult {

    //撤销订单返回参数

    //返回状态码	return_code	是必填	String(16)	SUCCESS /SUCCESS/FAIL /此字段是通信标识，非交易标识，交易是否成功需要查看trade_state来判断
    private String return_code;
    //返回信息	return_msg	是必填	String(128)	OK 当return_code为FAIL时返回信息为错误原因 ，例如 签名失败 参数格式校验错误
    private String return_msg;

    /*============当return_code为SUCCESS的时候,还会包括以下字段============*/
    //公众账号ID	appid	是必填	String(32)	wx8888888888888888	返回提交的公众账号ID
    private String appid;
    //商户号	mch_id	是必填	String(32)	1900000109	返回提交的商户号
    private String mch_id;
    //随机字符串	nonce_str	是必填	String(32)	5K8264ILTKCH16CQ2502SI8ZNMTM67VS	微信返回的随机字符串
    private String nonce_str;
    //签名	sign	是	String(32)	C380BEC2BFD727A4B6845133519F3AD6	返回数据的签名，详见签名算法
    private String sign;
    //业务结果	result_code	是	String(16)	SUCCESS	SUCCESS/FAIL
    private String result_code;
    //错误代码	err_code	否	String(32)	SYSTEMERROR	详细参见错误列表
    private String err_code;
    //错误描述	err_code_des	否	String(128)	系统错误	结果信息描述
    private String err_code_des;
    //是否重调	recall	是	String(1)	Y	是否需要继续调用撤销，Y-需要，N-不需要
    private String recall;


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

    public String getRecall() {
        return recall;
    }

    @XmlElement(name="recall")
    public void setRecall(String recall) {
        this.recall = recall;
    }
}
