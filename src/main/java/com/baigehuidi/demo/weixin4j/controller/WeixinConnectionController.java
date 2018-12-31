package com.baigehuidi.demo.weixin4j.controller;

import com.baigehuidi.demo.loader.WeixinInsLoader;
import com.baigehuidi.demo.weixin4j.WeixinException;
import com.baigehuidi.demo.weixin4j.model.sns.SnsUser;
import com.baigehuidi.demo.weixin4j.model.user.User;
import com.baigehuidi.demo.weixin4j.model.xml.ScanQrcodeXmlLoader;
import com.baigehuidi.demo.weixin4j.spi.IMessageHandler;
import com.baigehuidi.demo.weixin4j.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信开发者接入 直接使用, 抛弃了继承层
 * 微信开发者接入 与微信公众平台建立链接的入口
 * 见微信官方文档 https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421135319
 *
 */
@Controller
@RequestMapping("WeixinContact")
public class WeixinConnectionController {

    @Resource
    private IMessageHandler iMessageHandler;

    public WeixinConnectionController() {
    }

    //GET请求 配置服务器
    @RequestMapping(method = {RequestMethod.GET})
    public void get(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String token = TokenUtil.get();
        String echostr = request.getParameter("echostr");
        if (TokenUtil.checkSignature(token, signature, timestamp, nonce)) {
            response.getWriter().write(echostr);
        }

    }


//    private ScanQrcodeXml xmlObj;

    //POST请求 处理消息事件生成菜单等
    @RequestMapping(method = {RequestMethod.POST})
    public void post(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
//        String code = request.getParameter("code");
//        System.out.println("code:"+code);
        String token = TokenUtil.get();
        if (!TokenUtil.checkSignature(token, signature, timestamp, nonce)) {
            response.getWriter().write("");
        } else {
            try {

                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/xml");
                ServletInputStream in = request.getInputStream();

                String xml = this.iMessageHandler.invoke(in);
                System.out.println("post - xml : " + xml);

//                如果用户是扫临时码
//                if(xmlMsg.contains("Ticket")){



                /**
                 * 该方法会报错,暂时这么用,待后期改善
                 */
//                try {
//                    Map<String, String> xmlMap = MapUtil.xmlToMap(xml);
//                    if(xmlMap.get("ToUserName")!=null){
//                        xmlObj = new ScanQrcodeXml();
//                        xmlObj.setToUserName(xmlMap.get("ToUserName")); //openid
//                        if(xmlMap.get("EventKey")!=null && xmlMap.get("EventKey").startsWith("qrscene_")){
//                            System.out.println("xmlMap.get(\"EventKey\"):"+xmlMap.get("EventKey"));
//                        }
//                    }
//                } catch (Exception e) {
//
//                }
//                xmlObj.setFromUserName(xmlMap.get("FromUserName"));
//                xmlObj.setCreateTime(Long.valueOf(xmlMap.get("CreateTime")));
//                xmlObj.setEventKey(xmlMap.get("EventKey"));
//                xmlObj.setTicket(xmlMap.get("Ticket"));
////                }
//                System.err.println("ConnectionController: xmlObj : "+xmlObj.getFromUserName());
//                System.err.println("ConnectionController: xmlObj : "+xmlObj.getToUserName());

                response.getWriter().write(xml);
            } catch (IOException var9) {
                response.getWriter().write("");
            } catch (WeixinException var10) {
                response.getWriter().write("");
            }

        }
    }


    @Autowired
    private ScanQrcodeXmlLoader scanQrcodeXmlLoader;

    /**
     * TODO 如果数据库要进行更改,可以根据当时生成的scene_str查询用户openid或具体信息
     * 根据缓存进行查询
     * 该方法为
     * @param scene_str
     * @return
     */
    @ResponseBody
    @RequestMapping("/wechat/checkLogin")
    public Map wechatCheckLogin(String scene_str,HttpSession session) throws WeixinException {
        System.err.println("scene_str : " + scene_str);

        String openid = null;//用户openid
        if (scanQrcodeXmlLoader.getScanQrcodeXml() != null) {
            openid = scanQrcodeXmlLoader.getScanQrcodeXml().getFromUserName();
            System.out.println("Connection checkLogin openid:" + openid);

        }
        //其中scene_str为场景字符串,使用了baige+时间戳 而toUserName则是扫码用户的openid
        if (scene_str != null && scene_str != "" && openid != null && openid != "") {
            System.err.println("wechatCheckLogin(String scene_str)->WeixinInsLoader.getWeixinInstance().getToken().getAccess_token()::::::"+WeixinInsLoader.getWeixinInstance().getToken().getAccess_token());
            User user = WeixinInsLoader.getWeixinInstance().user().info(openid);
            System.out.println("user:"+user);
            Map map = new HashMap();
            if(user!=null){
                //成功获取用户状态码
//                model.addAttribute(user);
                session.setAttribute("nickname",user.getNickname());
                map.put("code",1);
//                map.put("user",user);
                return map;
            }else if(user==null && openid!=null && openid !=""){
                //虽然有用户openid,但是无法获取用户信息,可能是用户取消了关注
                //需要跳转到重新扫码生成界面
                map.put("code",0);
                return map;
            } else if(scene_str == null && scene_str == ""){
                //如果场景字符串为空 -1 检查场景字符串时间戳生成是否正确
                map.put("code",-1);
                return map;
            }else if(openid == null && openid == ""){
                //检查openid为什么没正确传入
                map.put("code",-2);
                return map;
            }else{
                //两者都为空,系统挂了吗
                map.put("code",-3);
                return map;
            }

        }
        return null;

    }


    /**
     * 将用户信息放入session
     *
     * @param
     * @return
     * @throws WeixinException
     */
    @ResponseBody
    @RequestMapping(value = "/wechat/callbackAndGetUserInfo", method = RequestMethod.POST)
    public Integer callBackAndGetUserInfo(HttpSession session) throws WeixinException {
        System.out.println("callBackAndGetUserInfo method");

        String access_token = WeixinInsLoader.getWeixinInstance().getToken().getAccess_token();
        System.err.println("Connection:Access_token:" + access_token);
        SnsUser snsUser = null;

        String openid = scanQrcodeXmlLoader.getScanQrcodeXml().getFromUserName();
        System.out.println("openid:" + openid);


        User user = null;
        if (openid != null && openid != "") {
            //查询该openid下的其它表数据,如账户表等,放入一个实体传回到首页
            //这里先只展示用户信息

            user = WeixinInsLoader.getWeixinInstance().user().info(openid);
            session.setAttribute("user",user);
//            session.setAttribute("openid",openid);

        }

        //存数据库



        if (user != null) {
            return 1;
        } else {
            return 0;
        }
    }


}
