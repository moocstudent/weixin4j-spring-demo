package com.baigehuidi.demo.weixin4j.util;

import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class ModifyImageUtil {

    private static Graphics2D g = null;

    /**
     * 导入本地图片到缓冲区
     *
     * @param imgName
     * @return
     */
    public static BufferedImage loadImageLocal(String imgName) {
        try {
            return ImageIO.read(new File(imgName));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 混合两张图片,x,y为位置设置
     *
     * @param top
     * @param bottom
     * @param x
     * @param y
     * @return
     */
    public static BufferedImage modifyImageTogether(BufferedImage top, BufferedImage bottom, int x, int y) {
        try {
            int w = top.getWidth();
            int h = top.getHeight();

            g = bottom.createGraphics();
            g.drawImage(top, x, y, w, h, null);
            g.dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bottom;
    }

    /**
     * 生成新图片到本地 返回新的图片路径
     *
     * @param newImage
     * @param img
     */
    public static String writeImageLocal(String newImage, BufferedImage img) {

        try {
            if (newImage != null && img != null) {

                File outputfile = new File(newImage);
                ImageIO.write(img, "jpg", outputfile);
                return newImage;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

//    public static void main(String[] args) {
//        ModifyImageUtil modi = new ModifyImageUtil();
//        BufferedImage top = modi.loadImageLocal("D:/img/1545126275953ogOX_0tJryORIwscqTEZBJj1iEAM.jpg");
//        BufferedImage bottom = modi.loadImageLocal("D:/img/action-adventure-flight-918648.jpg");
//
//        modi.writeImageLocal("D:/img/new.jpg", modi.modifyImageTogether(top, bottom, 20, 20));
//
//        System.out.println("SUCCESS");
//    }


    /* 之下为新加的方法,将图片处理为圆形图片 */
    public static BufferedImage transferImgForRoundImage(String url){
        BufferedImage resultImg = null;
        try {

            if (StringUtils.isEmpty(url)) {
                return null;
            }
            BufferedImage buffImg1 = ImageIO.read(new File(url));
            resultImg = new BufferedImage(buffImg1.getWidth(), buffImg1.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g = resultImg.createGraphics();
            Ellipse2D.Double shape = new Ellipse2D.Double(0, 0, buffImg1.getWidth(), buffImg1.getHeight());
            //使用setRenderingHint 设置抗锯齿
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            resultImg = g.getDeviceConfiguration().createCompatibleImage(buffImg1.getWidth(), buffImg1.getHeight(),
                    Transparency.TRANSLUCENT);
            g = resultImg.createGraphics();
            // 使用 setRenderingHint 设置抗锯齿
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setClip(shape);
            g.drawImage(buffImg1, 0, 0, null);
            g.dispose();
        }catch(MalformedURLException e){
            e.printStackTrace();
            System.err.println("MalformedURLException in ModifyImageUtil.java");
        }catch(IOException e){
            e.printStackTrace();
            System.err.println("IOException in ModifyImageUtil.java");
        }
        return resultImg;
    }
}
