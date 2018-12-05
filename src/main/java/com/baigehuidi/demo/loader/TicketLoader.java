package com.baigehuidi.demo.loader;

import org.springframework.stereotype.Component;
import org.weixin4j.loader.ITicketLoader;
import org.weixin4j.model.js.Ticket;
import org.weixin4j.model.js.TicketType;

@Component
public class TicketLoader implements ITicketLoader {
    /**
     * 获取ticket
     * @param ticketType
     * @return
     */
    @Override
    public Ticket get(TicketType ticketType) {
        String key = "";
        if (null != ticketType) {
            switch (ticketType) {
                case JSAPI:
                    key = "wechat_ticket_jsapi";
                    break;
                case WX_CARD:
                    key = "wechat_ticket_wxcard";
                    break;
                default:
                    key = "wechat_ticket";
                    break;
            }
        }

        return null;
    }

    /**
     * 刷新ticket
     * @param ticket
     */
    @Override
    public void refresh(Ticket ticket) {
        String key = "";
//        if (null != ticket.getTicketType()) {
//            switch (ticket.getTicketType()) {
//                case JSAPI:
//                    key = "wechat_ticket_jsapi_" + appid; //需要获取appid
//                    break;
//                case WX_CARD:
//                    key = "wechat_ticket_wxcard_" + appid;
//                    break;
//                default:
//                    key = "wechat_ticket_" + appid;
//                    break;
//            }
//        }

    }
}
