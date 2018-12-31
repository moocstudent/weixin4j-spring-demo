package com.baigehuidi.demo.weixin4j.util;

import com.baigehuidi.demo.loader.WeixinInsLoader;
import com.baigehuidi.demo.weixin4j.WeixinException;
import com.baigehuidi.demo.weixin4j.model.message.MediaType;
import com.baigehuidi.demo.weixin4j.model.qrcode.Qrcode;
import com.baigehuidi.demo.weixin4j.model.qrcode.QrcodeType;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;

/**
 * TODO 待完善将D:盘符下的图片(底部海报)改为参数传入.(涉及到Weixin类) 2018-12-19\
 * 推广二维码 A-扫>B码->>A获取码
 */
public class QrcodeSpreadUtil {

    /**
     * 该方法将用户A扫码用户B后,关注微信平台时,可以获取到一个含有A自身openid参数的推广二维码
     * @param openid 传入用户的openid
     * @return 新的二维码图片的MediaId
     */
    public static String getNewQrcodeMediaId(String openid) {
        Qrcode qrcode = null;
        String qrcodeUrl = null;
        String picUrlOnServer = null;
        String imgMediaId = null;

        try {
            //永久二维码过期时间不会被加入参数
            qrcode = WeixinInsLoader.getWeixinInstance().qrcode().create(QrcodeType.QR_LIMIT_STR_SCENE, openid, 99);
            //获取推广二维码图片URL
//                qrcodeUrl = WeixinInsLoader.getWeixinInstance().qrcode().showQrcode(qrcode.getTicket());
            //将二维码图片保存
            //图片保存地址: 从weixin4j.properties中获取保存路径,图片格式为.jpg
            picUrlOnServer = WeixinInsLoader.getWeixinInstance().getWeixinConfig().getQrcodeSaveUrl() + new Date().getTime() + openid + ".jpg";
            //保存单纯的推广二维码到服务器路径
            WeixinInsLoader.getWeixinInstance().qrcode().saveQrcode(qrcode.getTicket(), picUrlOnServer);

            /**
             * 设置图片重叠,将二维码放到推广海报上.
             */
            //顶部二维码
            BufferedImage top = ModifyImageUtil.loadImageLocal(picUrlOnServer);
            //底部海报
            BufferedImage bottom = ModifyImageUtil.loadImageLocal(WeixinInsLoader.getWeixinInstance().getWeixinConfig().getPosterImgUrl());
            //生成带海报的二维码,x,y值需要根据前端设定
            String qrcodeImgUrl = ModifyImageUtil.writeImageLocal(picUrlOnServer, ModifyImageUtil.modifyImageTogether(top, bottom, 200, 220));

            //获取用户头像
            String headPicUrl = WeixinInsLoader.getWeixinInstance().user().info(openid).getHeadimgurl();
            //String headPic = WeixinInsLoader.getWeixinInstance().base().shortUrl(headPicUrl);
            String headPicLocal =  "D:/img/headpic"+openid+".jpg";
            try{
                CommonUtil.savePic(headPicUrl,headPicLocal);
            }catch(Exception e){
                e.printStackTrace();
            }

            System.out.println("headPicLocal:"+headPicLocal);
//            BufferedImage headPicTop = ModifyImageUtil.loadImageLocal(headPicLocal);
            BufferedImage headPicTop = ModifyImageUtil.transferImgForRoundImage(headPicLocal);
            //新的二维码海报图像
            BufferedImage newQrcodeImgUrl = ModifyImageUtil.loadImageLocal(qrcodeImgUrl);
            //将用户头像再次叠加到有二维码的推广海报上
            String endImgUrl = ModifyImageUtil.writeImageLocal(headPicLocal,ModifyImageUtil.modifyImageTogether(headPicTop,newQrcodeImgUrl,20,20));


            //上传至微信服务器素材中心,获取图片的mediaId 用户回复给用户
            imgMediaId = WeixinInsLoader.getWeixinInstance().media().upload(MediaType.Image, new File(endImgUrl));
            System.err.println("imgMediaId:" + imgMediaId);
            return imgMediaId;

        } catch (WeixinException e) {
            e.printStackTrace();
            return null;
        }

    }
}
