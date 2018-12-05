package com.baigehuidi.demo.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.weixin4j.Weixin;
import org.weixin4j.component.MenuComponent;

@Component
public class MenuComponentInsLoader {

    private static MenuComponent menuComponent;

    public synchronized static MenuComponent getMenuComponent(){
        if(menuComponent == null){
            menuComponent = new MenuComponent(WeixinInsLoader.getWeixinInstance());
        }
        return menuComponent;
    }

}
