//package com.baigehuidi.demo.handler;
//
//import com.baigehuidi.demo.weixin4j.model.message.OutputMessage;
//import com.baigehuidi.demo.weixin4j.model.message.normal.*;
//import com.baigehuidi.demo.weixin4j.model.message.output.TextOutputMessage;
//import com.baigehuidi.demo.weixin4j.spi.INormalMessageHandler;
//import org.springframework.stereotype.Component;
//
//
///**
// * 公众号普通消息处理 (不限于普通文字,包含语音,图片,短视频等等)
// */
//@Component
//public class WeixinMessageHandler implements INormalMessageHandler {
//    @Override
//    public OutputMessage textTypeMsg(TextInputMessage textInputMessage) {
//        //
//        return new TextOutputMessage("2018-12-07 你的信息已经收到,请立即设置回复消息库!💗");
//    }
//
//    @Override
//    public OutputMessage imageTypeMsg(ImageInputMessage imageInputMessage) {
//        return null;
//    }
//
//    @Override
//    public OutputMessage voiceTypeMsg(VoiceInputMessage voiceInputMessage) {
//        return null;
//    }
//
//    @Override
//    public OutputMessage videoTypeMsg(VideoInputMessage videoInputMessage) {
//        return null;
//    }
//
//    @Override
//    public OutputMessage shortvideoTypeMsg(ShortVideoInputMessage shortVideoInputMessage) {
//        return null;
//    }
//
//    @Override
//    public OutputMessage locationTypeMsg(LocationInputMessage locationInputMessage) {
//        return null;
//    }
//
//    @Override
//    public OutputMessage linkTypeMsg(LinkInputMessage linkInputMessage) {
//        return null;
//    }
//}
