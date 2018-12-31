package com.baigehuidi.demo.loader;

import com.baigehuidi.demo.weixin4j.Weixin;
import org.springframework.stereotype.Component;


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
