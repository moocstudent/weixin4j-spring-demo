package com.baigehuidi.demo.weixin4j.util.wxpay;

import com.baigehuidi.demo.loader.WeixinInsLoader;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 微信支付配置类
 */
public class MyIWXPayConfig implements IWXPayConfig {

    private byte[] certData;

    public MyIWXPayConfig() throws Exception {
        String certPath = "D:/baigecert/baigetesthighwaytotingtaoprocess.p12"; //证书路径
        File file = new File(certPath);
        InputStream certStream = new FileInputStream(file);
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);
        certStream.close();
    }

    @Override
    public String getAppID() {
        return "wx6d79ecc42bc067c7";
    }

    @Override
    public String getMchID() {
        return "1391042002";
    }

    @Override
    public String getKey() {
        return WeixinInsLoader.getWeixinInstance().getWeixinPayConfig().getPartnerKey();
    }

    @Override
    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    /**
     * HTTP(S) 连接超时时间，单位毫秒
     * @return
     */
    @Override
    public int getHttpConnectTimeoutMs() {
        return 6*1000;
    }

    /**
     * HTTP(S) 读数据超时时间，单位毫秒
     * @return
     */
    @Override
    public int getHttpReadTimeoutMs() {
        return 8*1000;
    }

    /**
     * 获取WXPayDomain, 用于多域名容灾自动切换
     * @return
     */
    @Override
    public IWXPayDomain getWXPayDomain() {
        return new  MyIWXPayDomain();
    }

    /**
     * 是否自动上报。
     * 若要关闭自动上报，子类中实现该函数返回 false 即可。
     * @return
     */
    @Override
    public boolean shouldAutoReport() {
        return false;
    }

    /**
     * 进行健康上报的线程的数量
     * @return
     */
    @Override
    public int getReportWorkerNum() {
        return 6;
    }

    /**
     * 健康上报缓存消息的最大数量。会有线程去独立上报
     * 粗略计算：加入一条消息200B，10000消息占用空间 2000 KB，约为2MB，可以接受
     * @return
     */
    @Override
    public int getReportQueueMaxSize() {
        return 10000;
    }

    /**
     * 批量上报，一次最多上报多个数据
     * @return
     */
    @Override
    public int getReportBatchSize() {
        return 10;
    }
}
