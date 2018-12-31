package com.baigehuidi.demo.weixin4j.util;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import static java.net.URLEncoder.encode;


public class CommonUtil {

    /**
     * URL编码 转换url路径拼接微信api路径 该方法暂时用不到,使用weixin.sns.get...可以获取
     *
     * @param source
     * @return
     */
    public static String urlEncodeUTF8(String source) {
        String resultURL = source;
        try {
            resultURL = encode(source, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
        }

        return resultURL;

    }

    /**
     * 保存网络图片到本地
     * @param imgUrl
     * @param fileName
     * @return
     * @throws IOException
     */
    public static String savePic(String imgUrl, String fileName) throws IOException {
        URL url = new URL(imgUrl);
        // 打开URL连接
        URLConnection con = url.openConnection();
        // 得到URL的输入流
        InputStream input = con.getInputStream();
        // 设置数据缓冲
        byte[] bs = new byte[1024 * 2];
        // 读取到的数据长度
        int len;
        // 输出的文件流保存图片至本地
        OutputStream os = new FileOutputStream(fileName);
        while ((len = input.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
           os.close();
           input.close();
           return fileName;
    }


    /**
     * 获取微信服务器ip地址集
     * @return callbackIps
     */
//    public static List<String> getWeixinIPs(){
//        try {
//            List<String> callbackIps = WeixinInsLoader.getWeixinInstance().base().getCallbackIp();
//            System.out.println("获取微信服务器ip地址中...");
//            return callbackIps;
//        } catch (WeixinException e) {
//            System.out.println("获取微信服务器ip地址失败...");
//            e.printStackTrace();
//            return null;
//        } finally {
//        }
//
//    }
}
