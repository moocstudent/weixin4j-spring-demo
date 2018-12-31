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
package com.baigehuidi.demo.weixin4j.spi;


import com.baigehuidi.demo.loader.WeixinInsLoader;
import com.baigehuidi.demo.weixin4j.Weixin;
import com.baigehuidi.demo.weixin4j.WeixinException;
import com.baigehuidi.demo.weixin4j.component.UserComponent;
import com.baigehuidi.demo.weixin4j.model.message.Image;
import com.baigehuidi.demo.weixin4j.model.message.MediaType;
import com.baigehuidi.demo.weixin4j.model.message.OutputMessage;
import com.baigehuidi.demo.weixin4j.model.message.event.*;
import com.baigehuidi.demo.weixin4j.model.message.output.ImageOutputMessage;
import com.baigehuidi.demo.weixin4j.model.message.output.TextOutputMessage;
import com.baigehuidi.demo.weixin4j.model.qrcode.Qrcode;
import com.baigehuidi.demo.weixin4j.model.qrcode.QrcodeType;
import com.baigehuidi.demo.weixin4j.model.user.User;
import com.baigehuidi.demo.weixin4j.model.xml.ScanQrcodeXml;
import com.baigehuidi.demo.weixin4j.model.xml.ScanQrcodeXmlLoader;
import com.baigehuidi.demo.weixin4j.util.ModifyImageUtil;
import com.baigehuidi.demo.weixin4j.util.QrcodeSpreadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;

/**
 * 默认事件消息处理器
 *
 * @author 杨启盛<qsyang   @   ansitech.com> & ukzq <cnblogs/ukzq>
 * @since 0.0.7
 */
@Component
public class DefaultEventMessageHandler implements IEventMessageHandler {

    //引入UserComponent,获取用户详情
    @Autowired
    private UserComponent userComponent;

    @Autowired
    private Weixin weixin;

    private OutputMessage allType(EventMessage msg) {
        TextOutputMessage out = new TextOutputMessage();
        out.setContent("你的消息已经收到！");
        return out;
    }

    /**
     * 关注回复事件
     *
     * @param msg 接受消息对象
     * @return
     */
    @Override
    public OutputMessage subscribe(SubscribeEventMessage msg) {
        System.out.println("关注事件");
        String name = weixin.getName();
        System.out.println(" String name = weixin.getName() : "+name);
        TextOutputMessage out = new TextOutputMessage();
        out.setContent("欢迎关注" + name + "微信平台");
        return out;
    }

    /**
     * 取消关注事件
     *
     * @param msg 接受消息对象
     * @return
     */
    @Override
    public OutputMessage unSubscribe(UnSubscribeEventMessage msg) {
        TextOutputMessage out = new TextOutputMessage();
        out.setContent("取消关注事件");
        return out;
    }


    @Override
    public OutputMessage qrsceneSubscribe(QrsceneSubscribeEventMessage msg) {

//        out.setContent("扫码场景关注事件");
        //获取用户扫码并关注时的用户openid
        String openid = msg.getFromUserName();
        System.out.println("msg.getFromUserName():" + openid);
//        String eventKey1 = scanQrcodeXmlLoader.getScanQrcodeXml().getEventKey();
//        System.out.println("eventKey(scanQrcodeXmlLoader):" + eventKey1);
        //先去数据库查询该openid是否已经存在,已经存在的话回复"欢迎回来."+nickname
        /**
         * 数据库操作
         * UserService->查询用户表是否已经存在该用户(已存在则表示已经获取过该推广二维码,则不回复二维码,回复欢迎回来+nickname)
         * 手工获取的话还是可以获取,比如说通过自定义菜单的"获取推广二维码"时的click事件,判断该click的key,获取推广二维码进行回复.
         * 这里用zq的openid做为测试
         */
        /*示例1:  User user = UserService.getUserByOpenID(openid);
         *       if(user != null){
         *        TextOutputMessage out = new TextOutputMessage();
         *        out.setContent("欢迎回来"+user.getNickName()+"!");
         *       }else{
         *        //获取到A君(扫码者)的推广二维码
         *        String imgMediaId = QrcodeSpreadUtil.getNewQrcodeMediaId(openid);
         *        ...
         *        ...
         *        return imgOut;  //返回推广二维码至用户
         *
         *       }
         */


        //test by zq's openid

        //如果存在该用户,那么不推送二维码,只回复文字.
        if (openid.equals("ogOX_0tJryORIwscqTEZBJj1iEAM")) {
            User user = null;
            TextOutputMessage out = new TextOutputMessage();
            try {
                user = WeixinInsLoader.getWeixinInstance().user().info("ogOX_0tJryORIwscqTEZBJj1iEAM");
            } catch (WeixinException e) {
                e.printStackTrace();
            }
            out.setContent("欢迎回来" + user.getNickname() + "!"); //test OK
            return out;
        }else {
            //获取到A君的推广二维码
            String imgMediaId = QrcodeSpreadUtil.getNewQrcodeMediaId(openid);
            /**
             * 输出图片格式
             */
            ImageOutputMessage imgOut = new ImageOutputMessage();
//
            Image image = new Image();
            image.setMediaId(imgMediaId);
            imgOut.setImage(image);
//
            return imgOut;
        }



//
//        return out;
    }


    /**
     * 该事件为用户临时二维码事件
     *
     * @param msg 接受消息对象
     * @return
     */
    @Override
    public OutputMessage qrsceneScan(QrsceneScanEventMessage msg) {
        TextOutputMessage out = new TextOutputMessage();
        String ticket = msg.getTicket();
        System.err.println("qrsceneScan:获取msg中ticket:\n" + ticket + "\n");
        System.out.println("qrsceneScan:获取msg:\n" + msg + "\n");
        String fromUserName = msg.getFromUserName();

        System.err.println("默认事件消息控制器qrsceneScan方法获取openid:\n" + fromUserName + "\n");
        out.setContent("扫码场景事件:" + fromUserName);
        return out;
    }

    @Override
    public OutputMessage location(LocationEventMessage msg) {
        TextOutputMessage out = new TextOutputMessage();
        out.setContent("发送地理信息事件");
        return out;
    }

    @Override
    public OutputMessage click(ClickEventMessage msg) {
        TextOutputMessage out = new TextOutputMessage();
        System.out.println("ClickEventMessage msg: " + msg);
        System.out.println("msg.getEventKey():"+msg.getEventKey());
        if(msg.getEventKey().equals("V1001_QRCODE")){
            String imgMediaId = QrcodeSpreadUtil.getNewQrcodeMediaId(msg.getFromUserName());
            /**
             * 输出图片格式
             */
            ImageOutputMessage imgOut = new ImageOutputMessage();
//
            Image image = new Image();
            image.setMediaId(imgMediaId);
            imgOut.setImage(image);
//
            return imgOut;
        }else{
            out.setContent("点击事件");

            return out;
        }

    }


    //页面跳转事件
    @Override
    public OutputMessage view(ViewEventMessage msg) {

        //获取发送者名称 即openid
        String fromUserName = msg.getFromUserName();
        System.err.println("public OutputMessage view(ViewEventMessage msg)");
        System.err.println("fromUserName:" + fromUserName);
//        String code = request.getParameter("code");
//        System.err.println("code:"+code);

        return null;
//        User userInfo = null;
//        try {
//            userInfo = userComponent.info(fromUserName);
//        } catch (WeixinException e) {
//            e.printStackTrace();
//        }
//        //test↓
//        System.err.println(userInfo.getNickname());
//        System.err.println(userInfo.getCity());
//        System.err.println(userInfo.getSex());
//        //test↑
//        TextOutputMessage textOutputMessage = new TextOutputMessage();
//        textOutputMessage.setContent("你好"+userInfo.getNickname());
//        return textOutputMessage;

    }

    @Override
    public OutputMessage scanCodePush(ScanCodePushEventMessage msg) {
        TextOutputMessage out = new TextOutputMessage();
        out.setContent("扫码推送事件");
        return out;
    }

    @Override
    public OutputMessage scanCodeWaitMsg(ScanCodeWaitMsgEventMessage msg) {
        TextOutputMessage out = new TextOutputMessage();
        out.setContent("扫码事件");
        return out;
    }

    @Override
    public OutputMessage picSysPhoto(PicSysPhotoEventMessage msg) {
        TextOutputMessage out = new TextOutputMessage();
        out.setContent("图片事件");
        return out;
    }

    @Override
    public OutputMessage picPhotoOrAlbum(PicPhotoOrAlbumEventMessage msg) {
        TextOutputMessage out = new TextOutputMessage();
        out.setContent("拍照或发图事件");
        return out;
    }

    @Override
    public OutputMessage picWeixin(PicWeixinEventMessage msg) {
        TextOutputMessage out = new TextOutputMessage();
        out.setContent("微信图片事件");
        return out;
    }

    @Override
    public OutputMessage locationSelect(LocationSelectEventMessage msg) {
        TextOutputMessage out = new TextOutputMessage();
        out.setContent("地址选择事件");
        return out;
    }

}
