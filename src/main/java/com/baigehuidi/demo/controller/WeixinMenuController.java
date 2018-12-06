package com.baigehuidi.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.baigehuidi.demo.service.WeixinComponentService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.weixin4j.WeixinException;
import org.weixin4j.component.MenuComponent;
import org.weixin4j.model.menu.Menu;

@RestController
@RequestMapping("/menu")
public class WeixinMenuController {

    //获取menuComponent实例,源码中没有@Component
    private static MenuComponent menuComponent = WeixinComponentService.getMenuComponent();

    /**
     * 需要前端页面form表单进行自定义菜单数据传输 TODO
     * @param  menuJson
     */


    @RequestMapping("create")
    public void createMenu(@RequestBody String menuJson) {

//        System.out.println(menuJson);
        //change menuJson -> (JSONObject)menuJson
        JSONObject jsonObject = (JSONObject) JSONObject.parse(menuJson);
//        System.out.println(jsonObject);
        //new a Menu instance
        Menu menu = new Menu(jsonObject);
        try {
            //create the menu
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
            System.out.println("获取失败,菜单可能不存在.");
            e.printStackTrace();
        } finally {
        }
        return null;
    }

    @RequestMapping("delete")
    public Integer deleteMenu() {
        try {
            if(getMenu()==null){
                //如果菜单本来就是null,那么返回0
                System.out.println("自定义菜单为null~");
                return 0;
            }
            menuComponent.delete();
        } catch (WeixinException e) {
            e.printStackTrace();
        } finally {
        }
        System.out.println("deleteMenu ok.");
        //有菜单被删除,返回1
        return 1;
    }

}
