package com.baigehuidi.demo.weixin4j.model.pay;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * [下载对账单返回参数]
 */

@XmlRootElement(name = "xml")
public class DownLoadBillResult {

    //失败时返回以下字段 (成功时，数据以文本表格的方式返回，第一行为表头，后面各行为对应的字段内容，字段内容跟查询订单或退款结果一致，具体字段说明可查阅相应接口。)

    //返回状态码 String(16) 必填 SUCCESS
    private String return_code;
    //返回信息 String(128) 必填
    private String return_msg;
//    错误码:
//    SYSTEMERROR	下载失败	系统超时	请尝试再次查询。
//    invalid bill_type	参数错误	请求参数未按指引进行填写	参数错误，请重新检查
//    data format error
//    missing parameter
//    SIGN ERROR
//    NO Bill Exist	账单不存在	当前商户号没有已成交的订单，不生成对账单	请检查当前商户号在指定日期内是否有成功的交易。
//    Bill Creating	账单未生成	当前商户号没有已成交的订单或对账单尚未生成	请先检查当前商户号在指定日期内是否有成功的交易，如指定日期有交易则表示账单正在生成中，请在上午10点以后再下载。
//    CompressGZip Error	账单压缩失败	账单压缩失败，请稍后重试	账单压缩失败，请稍后重试
//    UnCompressGZip Error	账单解压失败	账单解压失败，请稍后重试	账单解压失败，请稍后重试
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
}
