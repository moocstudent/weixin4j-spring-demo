//package com.baigehuidi.demo.service;
//
//import com.alibaba.fastjson.JSONObject;
//import org.springframework.stereotype.Service;
//import org.weixin4j.Weixin;
//import org.weixin4j.WeixinException;
//import org.weixin4j.component.MenuComponent;
//import org.weixin4j.model.menu.Menu;
// Controller 取代 <<<<<
//@Service
//public class WeixinMenuService {
//
//    public void createMenu(JSONObject jsonObject){
//        Menu menu = new Menu(jsonObject);
//        try {
//            menuComponent.create(menu);
//        } catch (WeixinException e) {
//            e.printStackTrace();
//        } finally {
//        }
//        System.out.println("createMenu OK.");
//    }
//
//    public Menu getMenu(){
//        try {
//            return menuComponent.get();
//        } catch (WeixinException e) {
//            e.printStackTrace();
//        } finally {
//        }
//        return null;
//    }
//
//    public void deleteMenu(){
//        try {
//            menuComponent.delete();
//        } catch (WeixinException e) {
//            e.printStackTrace();
//        } finally {
//        }
//        System.out.println("deleteMenu ok.");
//    }
//}
