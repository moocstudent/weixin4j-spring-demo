package com.baigehuidi.demo.weixin4j.model.qrcode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 设置推广二维码的qrcodeX,qrcodeY坐标,以及用户头像展示的headpicX,headpicY坐标
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QrcodeSpreadAxis {

    //原二维码(不含海报)的X坐标
    private double qrcodeX;
    //原二维码(不含海报)的Y坐标
    private double qrcodeY;
    //用户头像在图片上的X坐标
    private double headpicX;
    //用户头像在图片上的Y坐标
    private double headpicY;


}
