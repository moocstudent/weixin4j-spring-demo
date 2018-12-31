package com.baigehuidi.demo.weixin4j.util.wxpay;

/**
 * 域名管理实现类
 */
public class MyIWXPayDomain implements IWXPayDomain{

    @Override
    public void report(String domain, long elapsedTimeMillis, Exception ex) {

    }

    @Override
    public DomainInfo getDomain(IWXPayConfig config) {

        return  new DomainInfo(WXPayConstants.DOMAIN_API,true);
    }
}
