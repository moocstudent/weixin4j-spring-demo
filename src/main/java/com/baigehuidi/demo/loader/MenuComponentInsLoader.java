package com.baigehuidi.demo.loader;

import com.baigehuidi.demo.weixin4j.component.MenuComponent;
import org.springframework.stereotype.Component;


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
