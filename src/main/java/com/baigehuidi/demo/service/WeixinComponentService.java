package com.baigehuidi.demo.service;

import com.baigehuidi.demo.loader.MenuComponentInsLoader;
import com.baigehuidi.demo.loader.WeixinInsLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.weixin4j.component.MenuComponent;

@Service
public class WeixinComponentService {

    @Autowired
    private static MenuComponentInsLoader menuComponentLoader;

    public static MenuComponent getMenuComponent() {
        return menuComponentLoader.getMenuComponent();
    }
}
