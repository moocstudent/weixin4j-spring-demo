//package com.baigehuidi.demo.handler;
//
//import com.baigehuidi.demo.weixin4j.model.message.OutputMessage;
//import com.baigehuidi.demo.weixin4j.model.message.event.*;
//import com.baigehuidi.demo.weixin4j.model.message.output.TextOutputMessage;
//import com.baigehuidi.demo.weixin4j.spi.IEventMessageHandler;
//import org.springframework.stereotype.Component;
//
//
//@Component
//public class WeixinEventHandler implements IEventMessageHandler {
//    @Override
//    public OutputMessage subscribe(SubscribeEventMessage subscribeEventMessage) {
//        // TODO
//        return new TextOutputMessage("这里设置关注回复信息或图片等💗");
//    }
//
//    @Override
//    public OutputMessage unSubscribe(UnSubscribeEventMessage unSubscribeEventMessage) {
//        return null;
//    }
//
//    @Override
//    public OutputMessage qrsceneSubscribe(QrsceneSubscribeEventMessage qrsceneSubscribeEventMessage) {
//        return null;
//    }
//
//    @Override
//    public OutputMessage qrsceneScan(QrsceneScanEventMessage qrsceneScanEventMessage) {
//        return null;
//    }
//
//    @Override
//    public OutputMessage location(LocationEventMessage locationEventMessage) {
//        return null;
//    }
//
//    @Override
//    public OutputMessage click(ClickEventMessage clickEventMessage) {
//        return null;
//    }
//
//    @Override
//    public OutputMessage view(ViewEventMessage viewEventMessage) {
//        return null;
//    }
//
//    @Override
//    public OutputMessage scanCodePush(ScanCodePushEventMessage scanCodePushEventMessage) {
//        return null;
//    }
//
//    @Override
//    public OutputMessage scanCodeWaitMsg(ScanCodeWaitMsgEventMessage scanCodeWaitMsgEventMessage) {
//        return null;
//    }
//
//    @Override
//    public OutputMessage picSysPhoto(PicSysPhotoEventMessage picSysPhotoEventMessage) {
//        return null;
//    }
//
//    @Override
//    public OutputMessage picPhotoOrAlbum(PicPhotoOrAlbumEventMessage picPhotoOrAlbumEventMessage) {
//        return null;
//    }
//
//    @Override
//    public OutputMessage picWeixin(PicWeixinEventMessage picWeixinEventMessage) {
//        return null;
//    }
//
//    @Override
//    public OutputMessage locationSelect(LocationSelectEventMessage locationSelectEventMessage) {
//        return null;
//    }
//}
