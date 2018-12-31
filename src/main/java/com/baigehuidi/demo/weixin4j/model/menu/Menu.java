/*
 * 微信公众平台(JAVA) SDK
 *
 * Copyright (c) 2014, Ansitech Network Technology Co.,Ltd All rights reserved.
 * 
 * http://www.weixin4j.org/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.baigehuidi.demo.weixin4j.model.menu;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baigehuidi.demo.loader.WeixinInsLoader;
import com.baigehuidi.demo.weixin4j.Weixin;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单对象
 *
 * @author 杨启盛<qsyang@ansitech.com>
 * @since 0.0.1
 */
public class Menu {

    //单一按钮集合 button (List)
    //Menu自定义菜单是由button构成的,所以只有button集合的属性
    private List<SingleButton> button;

    @Autowired
    private Weixin weixin;

    public Menu() {
    }

    //**通过form表单传过来的json串进行自定义菜单的解析
    public Menu(JSONObject jsonObj) {
        //初始化一级自定义菜单
        button = new ArrayList<SingleButton>();

        //获取menu对象
        JSONObject menuObj = jsonObj.getJSONObject("menu");
        if (menuObj != null) {
            //获取button列表
            JSONArray buttonJson = menuObj.getJSONArray("button");
            for (int i = 0; i < buttonJson.size(); i++) {
                //获取button的JSON数组表示中的每一个JSONObject对象 (button也许不为一个)
                JSONObject jsonButton = buttonJson.getJSONObject(i);

                //recursion方法主要为了判定每个按钮的type类型,
                //及根据判定的类型,创建新的单一按钮
                //每个按钮的创建方式不同,因为ViewButton,ClickButton,或者其它.
                //比如在ViewButton中需要根据name和url创建按钮
                recursion(jsonButton, null);
            }
        }
    }


    /**
     * 解析按钮类型
     * @param jsonButton
     * @param menuButton
     */
    private void recursion(JSONObject jsonButton, SingleButton menuButton) {
        String type = null;
        if (jsonButton.containsKey("type")) {
            type = jsonButton.getString("type");
        }
        //声明单个按钮
        SingleButton singleButton = null;
        //判断对象type
        //单一按钮根据类型进行构造
        if (type == null) {
            //有子的自定义菜单
            singleButton = new SingleButton(jsonButton.getString("name"));
        } else if (type.equals(ButtonType.Click.toString())) {
            //转成点击按钮
            singleButton = new ClickButton(jsonButton.getString("name"), jsonButton.getString("key"));
        } else if (type.equals(ButtonType.View.toString())) {
            //转成view按钮  将全部url加入用户信息授权
//            weixin.sns().getOAuth2CodeUserInfoUrl(jsonButton.getString("url"))
            singleButton = new ViewButton(jsonButton.getString("name"),WeixinInsLoader.getWeixinInstance().sns().getOAuth2CodeUserInfoUrl(jsonButton.getString("url")));
        } else if (type.equals(ButtonType.Scancode_Push.toString())) {
            //扫码推事件
            singleButton = new ScancodePushButton(jsonButton.getString("name"), jsonButton.getString("key"));
        } else if (type.equals(ButtonType.Scancode_Waitmsg.toString())) {
            //扫码推事件且弹出“消息接收中”提示框
            singleButton = new ScancodeWaitMsgButton(jsonButton.getString("name"), jsonButton.getString("key"));
        } else if (type.equals(ButtonType.Pic_SysPhoto.toString())) {
            //弹出系统拍照发图
            singleButton = new PicSysPhotoButton(jsonButton.getString("name"), jsonButton.getString("key"));
        } else if (type.equals(ButtonType.Pic_Photo_OR_Album.toString())) {
            //弹出拍照或者相册发图
            singleButton = new PicPhotoOrAlbumButton(jsonButton.getString("name"), jsonButton.getString("key"));
        } else if (type.equals(ButtonType.Pic_Weixin.toString())) {
            //弹出微信相册发图器
            singleButton = new PicWeixinButton(jsonButton.getString("name"), jsonButton.getString("key"));
        } else if (type.equals(ButtonType.Location_Select.toString())) {
            //弹出地理位置选择器
            singleButton = new LocationSelectButton(jsonButton.getString("name"), jsonButton.getString("key"));
        } else if (type.equals(ButtonType.Media_Id.toString())) {
            //永久素材(图片、音频、视频、图文消息)
            singleButton = new MediaIdButton(jsonButton.getString("name"), jsonButton.getString("media_id"));
        } else if (type.equals(ButtonType.View_Limited.toString())) {
            //永久素材(图文消息)
            singleButton = new ViewLimitedButton(jsonButton.getString("name"), jsonButton.getString("media_id"));
        }
        /**
         * 如果jsonButton中包含子菜单(子按钮),并非只有一级按钮的时候
         * 如果jsonButton(jsonSubButton)不再包含关键字"sub_button"时,不会进入if
         * 称呼这个方法为[判断是否有子菜单]
         */
        if (jsonButton.containsKey("sub_button")) {
            //获取每个子菜单(子按钮)
            JSONArray sub_button = jsonButton.getJSONArray("sub_button");
            //判断是否存在子菜单
            if (!sub_button.isEmpty()) {
                //如果不为空，则添加到singleButton中
                for (int i = 0; i < sub_button.size(); i++) {
                    //获取每一个子菜单(子按钮)的JSONObject对象
                    JSONObject jsonSubButton = sub_button.getJSONObject(i);
                    //递归方法内部调用,将子菜单(子按钮)再次进行类别分类判定
                    recursion(jsonSubButton, singleButton);
                    //recursion在根据每次的递归调用时,获取每个子菜单的singleButton实例
                    //在再次进入recursion方法时,因为二级菜单(子按钮)没有sub_button(没有三级按钮了)
                    //所以进入下面的else,将子按钮添加到一级按钮的子按钮集合里
                }
            }
        }
        //判断是否存在子菜单,如果该menuButton(或递归调用的singleButton)不再有子菜单时
        //则执行button.add(singleButton)
        if (menuButton == null) {
            //只添加一级菜单到集合中
            button.add(singleButton);
        } else { //如果menuButton不为空,则只有在[判断是否有子菜单]方法中才不会为null
            //添加到二级自定义菜单中
            menuButton.getSubButton().add(singleButton);

        }
    }

    /**
     * 转成JSON提交对象
     *
     * @return JSON提交对象
     */
    public JSONObject toJSONObject() {
        JSONObject buttonJson = new JSONObject();
        JSONArray buttonArray = new JSONArray();
        if (this.button != null) {
            //添加一级菜单
            for (SingleButton btn : button) {
                List<SingleButton> subButton = btn.getSubButton();
                //设置顶级菜单信息
                JSONObject btnJson = new JSONObject();
                btnJson.put("name", btn.getName());
                if (btn instanceof ViewButton) {
                    ViewButton cBtn = (ViewButton) btn;
                    btnJson.put("type", cBtn.getType());
                    btnJson.put("url", cBtn.getUrl());
                } else if (btn instanceof ClickButton) {
                    ClickButton cBtn = (ClickButton) btn;
                    btnJson.put("type", cBtn.getType());
                    btnJson.put("key", cBtn.getKey());
                } else if (btn instanceof ScancodePushButton) {
                    ScancodePushButton cBtn = (ScancodePushButton) btn;
                    btnJson.put("type", cBtn.getType());
                    btnJson.put("key", cBtn.getKey());
                } else if (btn instanceof ScancodeWaitMsgButton) {
                    ScancodeWaitMsgButton cBtn = (ScancodeWaitMsgButton) btn;
                    btnJson.put("type", cBtn.getType());
                    btnJson.put("key", cBtn.getKey());
                } else if (btn instanceof PicSysPhotoButton) {
                    PicSysPhotoButton cBtn = (PicSysPhotoButton) btn;
                    btnJson.put("type", cBtn.getType());
                    btnJson.put("key", cBtn.getKey());
                } else if (btn instanceof PicPhotoOrAlbumButton) {
                    PicPhotoOrAlbumButton cBtn = (PicPhotoOrAlbumButton) btn;
                    btnJson.put("type", cBtn.getType());
                    btnJson.put("key", cBtn.getKey());
                } else if (btn instanceof PicWeixinButton) {
                    PicWeixinButton cBtn = (PicWeixinButton) btn;
                    btnJson.put("type", cBtn.getType());
                    btnJson.put("key", cBtn.getKey());
                } else if (btn instanceof LocationSelectButton) {
                    LocationSelectButton cBtn = (LocationSelectButton) btn;
                    btnJson.put("type", cBtn.getType());
                    btnJson.put("key", cBtn.getKey());
                } else if (btn instanceof MediaIdButton) {
                    MediaIdButton cBtn = (MediaIdButton) btn;
                    btnJson.put("type", cBtn.getType());
                    btnJson.put("media_id", cBtn.getMedia_Id());
                } else if (btn instanceof ViewLimitedButton) {
                    ViewLimitedButton cBtn = (ViewLimitedButton) btn;
                    btnJson.put("type", cBtn.getType());
                    btnJson.put("media_id", cBtn.getMedia_Id());
                }
                //设置子菜单信息
                JSONArray subButtonArray = new JSONArray();
                for (SingleButton subBtn : subButton) {
                    JSONObject subBtnJson = new JSONObject();
                    subBtnJson.put("name", subBtn.getName());
                    if (subBtn instanceof ViewButton) {
                        ViewButton cBtn = (ViewButton) subBtn;
                        subBtnJson.put("type", cBtn.getType());
                        subBtnJson.put("url", cBtn.getUrl());
                    } else if (subBtn instanceof ClickButton) {
                        ClickButton cBtn = (ClickButton) subBtn;
                        subBtnJson.put("type", cBtn.getType());
                        subBtnJson.put("key", cBtn.getKey());
                    } else if (subBtn instanceof ScancodePushButton) {
                        ScancodePushButton cBtn = (ScancodePushButton) subBtn;
                        subBtnJson.put("type", cBtn.getType());
                        subBtnJson.put("key", cBtn.getKey());
                    } else if (subBtn instanceof ScancodeWaitMsgButton) {
                        ScancodeWaitMsgButton cBtn = (ScancodeWaitMsgButton) subBtn;
                        subBtnJson.put("type", cBtn.getType());
                        subBtnJson.put("key", cBtn.getKey());
                    } else if (subBtn instanceof PicSysPhotoButton) {
                        PicSysPhotoButton cBtn = (PicSysPhotoButton) subBtn;
                        subBtnJson.put("type", cBtn.getType());
                        subBtnJson.put("key", cBtn.getKey());
                    } else if (subBtn instanceof PicPhotoOrAlbumButton) {
                        PicPhotoOrAlbumButton cBtn = (PicPhotoOrAlbumButton) subBtn;
                        subBtnJson.put("type", cBtn.getType());
                        subBtnJson.put("key", cBtn.getKey());
                    } else if (subBtn instanceof PicWeixinButton) {
                        PicWeixinButton cBtn = (PicWeixinButton) subBtn;
                        subBtnJson.put("type", cBtn.getType());
                        subBtnJson.put("key", cBtn.getKey());
                    } else if (subBtn instanceof LocationSelectButton) {
                        LocationSelectButton cBtn = (LocationSelectButton) subBtn;
                        subBtnJson.put("type", cBtn.getType());
                        subBtnJson.put("key", cBtn.getKey());
                    } else if (subBtn instanceof MediaIdButton) {
                        MediaIdButton cBtn = (MediaIdButton) subBtn;
                        subBtnJson.put("type", cBtn.getType());
                        subBtnJson.put("media_id", cBtn.getMedia_Id());
                    } else if (subBtn instanceof ViewLimitedButton) {
                        ViewLimitedButton cBtn = (ViewLimitedButton) subBtn;
                        subBtnJson.put("type", cBtn.getType());
                        subBtnJson.put("media_id", cBtn.getMedia_Id());
                    }
                    subButtonArray.add(subBtnJson);
                }
                btnJson.put("sub_button", subButtonArray);
                buttonArray.add(btnJson);
            }
        }
        buttonJson.put("button", buttonArray);
        //返回创建JSON BODY
        return buttonJson;
    }

    public List<SingleButton> getButton() {
        if (button == null) {
            button = new ArrayList<SingleButton>(0);
        }
        return button;
    }

    public void setButton(List<SingleButton> button) {
        this.button = button;
    }
}
