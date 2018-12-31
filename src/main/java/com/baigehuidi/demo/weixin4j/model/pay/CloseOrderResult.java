package com.baigehuidi.demo.weixin4j.model.pay;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 关闭订单时返回的参数
 * 以下情况需要调用关闭接口: 商户订单支付失败需要生成新单号重新发起支付,
 * 要对原订单号调用关单, 避免重复支付;系统下单后,用户支付超时,系统退出不再受理.
 * 避免用户继续,请调用关单接口.
 * 注意: <<<, 订单生成后不能马上调用关单接口, 最短调用时间间隔为5分钟.
 * https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_3
 */
@XmlRootElement(name = "xml")
public class CloseOrderResult {

    private String return_code; //返回状态码 String(16)
    private String return_msg; //返回信息 String(128)
    //以下字段在return_code为SUCCESS的时候有返回
    private String appid; //公众账号ID String(32) 微信分配的公众账号ID
    private String mch_id; //商户号 String(32)
    private String nonce_str; //随机字符串 String(32)
    private String sign; //签名 String(32)
    private String result_code; //业务结果 String(16)
    private String result_msg; //业务结果描述 String(16)
    private String err_code; //错误代码 String(32)
    private String err_code_des; //错误代码描述 String(128)

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

    public String getResult_msg() {
        return result_msg;
    }

    @XmlElement(name="result_msg")
    public void setResult_msg(String result_msg) {
        this.result_msg = result_msg;
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
}
