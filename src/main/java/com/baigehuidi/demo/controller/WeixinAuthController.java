//package com.baigehuidi.demo.controller;
//
//import com.baigehuidi.demo.weixin4j.Weixin;
//import com.baigehuidi.demo.weixin4j.WeixinException;
//import com.baigehuidi.demo.weixin4j.component.SnsComponent;
//import com.baigehuidi.demo.weixin4j.model.sns.SnsUser;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import javax.ws.rs.QueryParam;
//
///**
// * 微信网页端授权
// */
//@Controller
//public class WeixinAuthController {
//
//    @Autowired
//    private SnsComponent snsComponent;
//    @Autowired
//    private Weixin weixin;
//    //通过之下方法获取SnsUser对象
//    //或者获取更多的将其放入一个扩展对象
//    //
//    @RequestMapping(value = "/",method = RequestMethod.GET)
//    public SnsUser getSnsUserByCode(@QueryParam("code") String code) throws WeixinException {
//        System.out.println("getSnsUserByCode(code)" + code);
//
//        System.out.println(snsComponent.getSnsUserByCode(code));
//        return weixin.sns().getSnsUserByCode(code);
//    }
//
//
//
//
//}
