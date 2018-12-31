package com.baigehuidi.demo.controller;

import com.baigehuidi.demo.weixin4j.model.qrcode.QrcodeSpreadAxis;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 该控制层只会接收后台传过来的要把[二维码]放置到海报图的什么位置上[qrcodeX,qrcodeY]坐标
 * 也会设置头像的[headpicX,headpicY]坐标
 */
@RestController
@RequestMapping("/qrcodeSpread")  //推广二维码
public class WeixinQrcodeSpreadController {

    @RequestMapping("/setAxis") //设置坐标轴 (用户头像和二维码在海报上的位置)
    public Integer setAxis(QrcodeSpreadAxis qrcodeSpreadAxis){
        return 1;
    }


}
