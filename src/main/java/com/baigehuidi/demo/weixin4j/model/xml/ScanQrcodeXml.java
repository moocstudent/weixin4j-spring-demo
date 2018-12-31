package com.baigehuidi.demo.weixin4j.model.xml;

import com.baigehuidi.demo.weixin4j.model.message.event.EventMessage;
import org.springframework.stereotype.Component;

/**
 * 用户扫码获取的xml数据
 */
@Component
public class ScanQrcodeXml extends EventMessage {

    public ScanQrcodeXml(){}

    @Override
    public String getEvent() {
        return this.Event;
    }

    private final String Event = "SCAN";

    private String EventKey;

    private String Ticket;

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }

    public String getTicket() {
        return Ticket;
    }

    public void setTicket(String ticket) {
        Ticket = ticket;
    }
}
