package com.baigehuidi.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ShowController {


    /**
     * @return 测试自定义菜单页面
     */
    @RequestMapping("menuCreate")
    public String menuCreate() {
        return "menuCreate";
    }

    /**
     *
     * @return 页面聊天页面(关键字答复)
     */
    @RequestMapping("messageChat")
    public String messageChat() {
        return "messageChat";
    }

    /**
     * 首页
     * @return
     */
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/index1")
    public String index1() {
        return "index1";
    }

    @RequestMapping("/wxMenu")
    public String wxMenu() {
        return "wxMenu";
    }

    /**
     * 微信支付测试jsp 2018-12-20
     * @return
     */
//    @RequestMapping("/testpay")
//    public String wechatpaytest(){
//        return "/wechat/wechatpaytest";
//    }

    /**
     * 登录页面 (集成多种登录形式)
     * @return
     */
    @RequestMapping("/login")
    public String login(){ return "login"; }

//    @RequestMapping("/goIndex")
//    public String goIndex(){ return "goIndex"; }

    /**
     * 扫码登录开放平台版 二维码生成页面 (可嵌入其它页面)
     * @return
     */
    @RequestMapping("/qrcode")
    public String qrcode(){
        return "qrcode";
    }

    /**扫码登录开放平台版 回调页面
     * @return 回调页面(默许执行ajax-> 执行/user/callBackLogin方法,传递code值)
     */
    @RequestMapping("/callback")
    public String callback(){
        return "callback";
    }

    /**
     * 扫码登录 临时带参二维码版 qrcode二维码展示页面
     * @return
     */
    @RequestMapping("/wechat/qrcode")
    public String wqrcode(){ return "/wechat/qrcode"; }

    /**
     * 扫码登录 临时带参二维码版 callback 回调页面
     * @return
     */
    @RequestMapping("/wechat/callback")
    public String wcallback(){ return "/wechat/callback"; }

    @RequestMapping("/wxpay/notify")
    public String wxpayCallback(){ return "/wechat/wechatpaytest"; }

    @RequestMapping("/app/index")
    public String phoneIndex(){ return "/wechat/index"; }



}
