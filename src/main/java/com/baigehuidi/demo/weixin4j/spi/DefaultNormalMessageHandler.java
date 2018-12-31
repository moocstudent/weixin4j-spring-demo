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
import com.baigehuidi.demo.weixin4j.WeixinException;
import com.baigehuidi.demo.weixin4j.model.message.Image;
import com.baigehuidi.demo.weixin4j.model.message.MediaType;
import com.baigehuidi.demo.weixin4j.model.message.OutputMessage;
import com.baigehuidi.demo.weixin4j.model.message.normal.*;
import com.baigehuidi.demo.weixin4j.model.message.output.ImageOutputMessage;
import com.baigehuidi.demo.weixin4j.model.message.output.TextOutputMessage;
import com.baigehuidi.demo.weixin4j.model.qrcode.Qrcode;
import com.baigehuidi.demo.weixin4j.model.qrcode.QrcodeType;
import com.baigehuidi.demo.weixin4j.util.ModifyImageUtil;
import com.baigehuidi.demo.weixin4j.util.QrcodeSpreadUtil;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;

/**
 * <p>
 * Title: 微信公众平台接受消息处理器</p>
 *
 * <p>
 * Description: 此消息处理器只负责接收消息和返回已收到消息的功能，无特殊功能。</p>
 *
 * @author 杨启盛<qsyang @ ansitech.com>
 * @since 0.0.6
 */
@Component
public class DefaultNormalMessageHandler implements INormalMessageHandler {

    /**
     * 匹配所有类型消息 进行回复 ~
     *
     * @param msg
     * @return
     */
    private OutputMessage allType(NormalMessage msg) {
        TextOutputMessage out = new TextOutputMessage();
        out.setContent("你的消息已经收到！ DefaultNormalMessageHandler");
        return out;
    }

    /**
     * 文本回复 根据前台form提交的数据->智能回复数据表->获取关键字/回复字段/
     *
     * @param msg 接受消息对象
     * @return
     */
    @Override
    public OutputMessage textTypeMsg(TextInputMessage msg) {

        //获取关键字
        String keyWord = msg.getContent();
        //从数据库搜索此关键字
        //select 回复字 from A where 关键字 = ""
        //返回(单个匹配字段数据 或 多个匹配字段(模糊查询的) list 集合 进行随机回复)
        //根据微信平台设定关键字的原样式,几个关键字同时能对应一个或几个回复
        //
        // TODO 设置智能问答库(数据表) 根据用户传递的关键字进行回复字回复.
//        假定messageMapper是从数据库获取回复字段
//        @Autowired
//        private MessageMapper messageMapper;
//        获取符合关键字的字段value,集合,使用random进行随机回复
//        ArrayList<String> valuesList = messageMapper.getValuesByKey(keyWord);
//        或1对1获取
//        String valueMessage = messageMapper.getValueByKey(keyWord);
//        将获取到的valueMesssage返回到前端就可以了
//        当使用多个线程处理消息时:


        /////* 之下为固定字段 */////
        System.out.println("msg.getFromUserName():"+msg.getFromUserName());
        
        
        
        TextOutputMessage out = new TextOutputMessage();
        String inputText = msg.getContent();
        if (inputText.equals("你好")) {
            out.setContent("你也好");
        } else if (inputText.equals("官方网站")) {
            out.setContent("http://www.baigehuidi.com");
        } else if(inputText.equals("推广")) {

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
//
        }
        return out;
    }

    @Override
    public OutputMessage imageTypeMsg(ImageInputMessage msg) {
        return allType(msg);
    }

    @Override
    public OutputMessage voiceTypeMsg(VoiceInputMessage msg) {
        return allType(msg);
    }

    @Override
    public OutputMessage videoTypeMsg(VideoInputMessage msg) {
        return allType(msg);
    }

    @Override
    public OutputMessage shortvideoTypeMsg(ShortVideoInputMessage msg) {
        return allType(msg);
    }

    @Override
    public OutputMessage locationTypeMsg(LocationInputMessage msg) {
        return allType(msg);
    }

    @Override
    public OutputMessage linkTypeMsg(LinkInputMessage msg) {
        return allType(msg);
    }
}
