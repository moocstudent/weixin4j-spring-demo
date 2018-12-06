package com.baigehuidi.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.weixin4j.spring.web.WeixinJieruController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 微信开发者接入
 */
@Controller
@RequestMapping("/weixin")
public class WeixinController extends WeixinJieruController{

    public void get(HttpServletRequest request,HttpServletResponse response) throws IOException {
        super.get(request,response);
    }

    public void post(HttpServletRequest request,HttpServletResponse response) throws IOException {
        super.post(request,response);
    }
}
