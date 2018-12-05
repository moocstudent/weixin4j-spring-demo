package com.baigehuidi.demo.loader;

import org.springframework.stereotype.Component;
import org.weixin4j.Weixin;

@Component
public class WeixinInsLoader {

    private static Weixin weixin;
    public synchronized static Weixin getWeixinInstance(){
        if(weixin == null){
            weixin = new Weixin();
        }
        return weixin;
    }
}
