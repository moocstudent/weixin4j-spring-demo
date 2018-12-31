package com.baigehuidi.demo.weixin4j.util.wxpay;

import java.io.InputStream;

public interface IWXPayConfig {

    public abstract String getAppID();

    public abstract String getMchID();

    public abstract String getKey();

    public abstract InputStream getCertStream();

    public int getHttpConnectTimeoutMs();

    public int getHttpReadTimeoutMs();

    public abstract IWXPayDomain getWXPayDomain();

    public boolean shouldAutoReport();

    public int getReportWorkerNum();

    public int getReportQueueMaxSize();

    public int getReportBatchSize();
}
