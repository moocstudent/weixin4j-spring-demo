package com.baigehuidi.demo.controller;

import com.baigehuidi.demo.weixin4j.model.message.normal.TextInputMessage;
import com.baigehuidi.demo.weixin4j.model.message.output.TextOutputMessage;
import com.baigehuidi.demo.weixin4j.spi.DefaultNormalMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 网页端与用户进行聊天的"智能回复",根据同微信公众平台智能回复同一个 [关键字&回复字数据库]
 * TODO 需要设置 [关键字&回复字(或其它类型内容)设定表单form] 详见 DefaultNormalMessageHandler
 *
 *
 * 用来回复微信消息 因为微信端使用DefaultNormalMessageHandler进行了消息处理和回复->到用户微信端
 * 网页版本的可以根据获取到的回复信息中的content进行数据回传
 * 网页测试messageChat.jsp
 */
@RestController
@RequestMapping("/chat")
public class WeixinMessageController {

    @Autowired
    private TextInputMessage textInputMessage;
    @Autowired
    private TextOutputMessage textOutputMessage;
    @Autowired
    private DefaultNormalMessageHandler defaultNormalMessageHandler;

    @RequestMapping("/webMessageHandler")
    public Map webMessageHandler(@RequestBody String inputMessage){

        textInputMessage.setContent(inputMessage);
        textOutputMessage = (TextOutputMessage) defaultNormalMessageHandler.textTypeMsg(textInputMessage);
        //get message from outputMessage
        String message = textOutputMessage.getContent();
        //map put code and message
        Map map = new HashMap();
        if(message == null || message.equals("") || message.equals("NULL")){
            map.put("code",0); //code 0 : no message
            map.put("message","无法匹配到关键字");
            return map;
        }
        map.put("code",1); //code 1 : has message
        map.put("message",message);
        return map;

    }
}

