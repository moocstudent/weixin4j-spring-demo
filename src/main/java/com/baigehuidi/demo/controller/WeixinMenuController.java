package com.baigehuidi.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.baigehuidi.demo.loader.MenuComponentInsLoader;
import com.baigehuidi.demo.loader.WeixinInsLoader;
import com.baigehuidi.demo.service.WeixinComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.weixin4j.WeixinException;
import org.weixin4j.component.MenuComponent;
import org.weixin4j.model.menu.Menu;

@RestController
@RequestMapping("/menu")
public class WeixinMenuController {

    private static MenuComponent menuComponent = WeixinComponentService.getMenuComponent();

    /**
     * 需要前端页面form表单进行自定义菜单数据传输 TODO
     * @param jsonObject
     */
    @RequestMapping("create")
    public void createMenu(JSONObject jsonObject) {

        Menu menu = new Menu(jsonObject);
        try {
            menuComponent.create(menu);
        } catch (WeixinException e) {
            e.printStackTrace();
        } finally {
        }
        System.out.println("createMenu OK.");
    }

    @RequestMapping("get")
    public Menu getMenu() {
        try {
            return menuComponent.get();
        } catch (WeixinException e) {
            e.printStackTrace();
        } finally {
        }
        return null;
    }

    @RequestMapping("delete")
    public void deleteMenu() {
        try {
            menuComponent.delete();
        } catch (WeixinException e) {
            e.printStackTrace();
        } finally {
        }
        System.out.println("deleteMenu ok.");
    }

}
