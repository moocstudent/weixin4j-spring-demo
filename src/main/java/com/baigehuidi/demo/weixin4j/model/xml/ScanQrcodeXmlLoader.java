package com.baigehuidi.demo.weixin4j.model.xml;

import org.springframework.stereotype.Component;

@Component
public class ScanQrcodeXmlLoader {

    private ScanQrcodeXml scanQrcodeXml;

    public ScanQrcodeXml getScanQrcodeXml() {
        return scanQrcodeXml;
    }

    public void setScanQrcodeXml(ScanQrcodeXml scanQrcodeXml) {
        this.scanQrcodeXml = scanQrcodeXml;
    }
}
