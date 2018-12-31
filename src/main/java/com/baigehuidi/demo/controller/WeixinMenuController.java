package com.baigehuidi.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baigehuidi.demo.weixin4j.WeixinException;
import com.baigehuidi.demo.weixin4j.component.MenuComponent;
import com.baigehuidi.demo.weixin4j.model.menu.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO 改善通过 -> 过滤器 拦截器 防止调用链接被非操作人员使用
 * 菜单生成 该路径进行过滤时:
 * 1.可根据用户权限和校验码 防止对外暴露
 */
@RestController
@RequestMapping("/menu")
public class WeixinMenuController {

    //获取menuComponent实例,源码中没有@Component,现在有了 12-08
    @Autowired
    private MenuComponent menuComponent;

    /**
     * 需要前端页面form表单进行自定义菜单数据传输
     * 成功1 创建失败0
     *
     * @param menuJson
     */

    @RequestMapping("create")
    public Integer createMenu(@RequestBody String menuJson) {

        System.out.println(menuJson);
        //change menuJson -> (JSONObject)menuJson
        JSONObject jsonObject = JSON.parseObject(menuJson);

        //new a Menu instance
        Menu menu = new Menu(jsonObject);
        try {
            //create the menu
            menuComponent.create(menu);
            System.out.println("createMenu OK.");
            return 1;
        } catch (WeixinException e) {
            e.printStackTrace();
            return 0;
        } finally {
        }

    }


    /**
     * 获取自定义菜单
     * @return Menu (json形式)
     */
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

    /**
     * 删除自定义菜单
     * @return 成功1 删除失败0
     */
    @RequestMapping("delete")
    public Integer deleteMenu() {
        try {
            if (getMenu() == null) {
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
