package com.baigehuidi.demo.controller;

import com.baigehuidi.demo.loader.WeixinInsLoader;
import com.baigehuidi.demo.weixin4j.WeixinException;
import com.baigehuidi.demo.weixin4j.model.sns.SnsUser;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class WeixinUserInfoController {

    @RequestMapping("/getSnsUserInfoByCode")
    public Map getSnsUserInfoByCode(@RequestBody String code,HttpSession session) throws WeixinException {
        if(code==null || code==""){
            return null;
        }
        Map map = new HashMap();
        System.out.println("code:"+code);
        SnsUser snsUser = WeixinInsLoader.getWeixinInstance().sns().getSnsUserByCode(code);
        session.setAttribute("SnsUser",snsUser);
        map.put("SnsUser",snsUser);
        return map;
    }

    @RequestMapping("/getOpenSnsUserInfoByCode")
    public Map getOpenSnsUserInfoByCode(@RequestBody String code) throws WeixinException {
        if(code==null || code==""){
            return null;
        }
        Map map = new HashMap();
        System.out.println("code:"+code);
        SnsUser snsUser = WeixinInsLoader.getWeixinInstance().open().getOpenSnsUserByCode(code);
        map.put("SnsUser",snsUser);
        return map;
    }


    /**
     * TODO 用户信息snsUser要存入数据库
     * 微信扫码登录回调
     * @param code 扫码登录(开放平台)code
     * @param session 将微信用户数据放入session
     * @return 1|0 状态码
     * @throws WeixinException
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/callBackLogin")
    public Integer callBackLogin(@RequestBody String code, HttpSession session) throws WeixinException, ServletException, IOException {
        System.out.println("callback method");
        System.out.println("code:"+code);
        SnsUser snsUser = WeixinInsLoader.getWeixinInstance().open().getOpenSnsUserByCode(code);
        //存数据库
        System.out.println("snsUser:"+snsUser);
        session.setAttribute("snsUser",snsUser);
        if(session!=null){
            return 1;
        }else{
            return 0;
        }

    }

}
