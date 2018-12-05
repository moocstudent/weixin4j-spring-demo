package com.baigehuidi.demo.loader;

import org.springframework.stereotype.Component;
import org.weixin4j.loader.ITokenLoader;
import org.weixin4j.model.base.Token;

@Component
public class AccessTokenLoader implements ITokenLoader {
    /**
     * 获取token
     * @return
     */
    @Override
    public Token get() {
        return null;
    }

    /**
     * 定时刷新token
     * @param token
     */
    @Override
    public void refresh(Token token) {

    }
}
