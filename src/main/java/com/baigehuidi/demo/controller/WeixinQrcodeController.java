package com.baigehuidi.demo.controller;

import com.baigehuidi.demo.loader.WeixinInsLoader;
import com.baigehuidi.demo.weixin4j.WeixinException;
import com.baigehuidi.demo.weixin4j.model.qrcode.Qrcode;
import com.baigehuidi.demo.weixin4j.model.qrcode.QrcodeType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * 生成带参二维码 原公众平台的openid获取
 */
@Controller
public class WeixinQrcodeController {

    //该方法为用户点击微信登录后请求的地址(第一步)
    @RequestMapping("/wechat/wechatLogin")
    public String wechatLogin(Model model) throws WeixinException {
        //场景字符串使用baige+时间
        String scene_str = "baige"+new Date().getTime();
        //字符串场景临时二维码
        Qrcode qrcode = WeixinInsLoader.getWeixinInstance().qrcode().create(QrcodeType.QR_STR_SCENE,scene_str,600);

        String ticket = qrcode.getTicket();
        String qrcodeUrl = null;
        if(ticket!=null){
            qrcodeUrl = WeixinInsLoader.getWeixinInstance().qrcode().showQrcode(ticket);//方法中已经进行了encode
        }
        model.addAttribute("qrcodeUrl",qrcodeUrl);
        model.addAttribute("scene_str",scene_str);
        //return ModelAndView(qrcode展示页面路径,modelMap);

        //跳转到二维码展示页面 (参数为字母+时间戳)
        return "/wechat/qrcode";
    }




}
