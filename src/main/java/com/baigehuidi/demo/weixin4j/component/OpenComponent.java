package com.baigehuidi.demo.weixin4j.component;

import com.alibaba.fastjson.JSONObject;
import com.baigehuidi.demo.weixin4j.Configuration;
import com.baigehuidi.demo.weixin4j.Weixin;
import com.baigehuidi.demo.weixin4j.WeixinException;
import com.baigehuidi.demo.weixin4j.http.HttpsClient;
import com.baigehuidi.demo.weixin4j.http.Response;
import com.baigehuidi.demo.weixin4j.model.sns.SnsAccessToken;
import com.baigehuidi.demo.weixin4j.model.sns.SnsUser;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 开放平台扫码登录相关组件
 */
public class OpenComponent extends AbstractComponent{

    public OpenComponent(Weixin weixin) {
        super(weixin);
    }
    /**
     * 开放平台微信登录(扫码)请求code url
     */
    private String qrconnect_url = "https://open.weixin.qq.com/connect/qrconnect";

    /**
     * open开放平台扫码获取code
     *
     * @param qrconnect_url 授权后重定向的回调链接地址（不用编码）
     * @return 网页安全授权链接
     */
    public String getQrconnectCodeUrlWithSnsApiLogin(String qrconnect_url) {
        return getQrconnectCodeUrl(qrconnect_url,"snsapi_login","DEFAULT");
    }

    /**
     * 开放平台获取code链接
     * @param qrconnect_url
     * @param scope
     * @param state
     * @return
     */
    public String getQrconnectCodeUrl(String qrconnect_url, String scope, String state) {
        try {
//            return authorize_url + "?appid=" + weixin.getAppId() + "&redirect_uri=" + URLEncoder.encode(redirect_uri, "UTF-8") + "&response_type=code&scope=" + scope + "&state=" + state + "&connect_redirect=1#wechat_redirect";
            return qrconnect_url + "?appid=" + weixin.getWeixinConfig().getOpenappid() + "&redirect_uri=" + URLEncoder.encode(qrconnect_url, "UTF-8") + "&response_type=code&scope=" + scope + "&state=" + state + "#wechat_redirect";
        } catch (UnsupportedEncodingException ex) {
            return "";
        }
    }

    /**
     * 拉取用户信息
     * 将第1,2步获取的
     *
     * @param code
     * @return 用户对象
     * @throws WeixinException
     */
    public SnsUser getOpenSnsUserByCode(String code) throws WeixinException {
        //默认简体中文
        return getSnsUserByCode(code, "zh_CN");
    }

    /**
     * 拉取用户信息
     *
     * @param code
     * @param lang 国家地区语言版本 zh_CN 简体，zh_TW 繁体，en 英语
     * @return 网页扫码登录获取用户信息
     * @throws WeixinException
     */
    private SnsUser getSnsUserByCode(String code, String lang) throws WeixinException {
        if (StringUtils.isEmpty(code)) {
            throw new IllegalArgumentException("code can't be null or empty");
        }
        if (StringUtils.isEmpty(lang)) {
            throw new IllegalArgumentException("lang can't be null or empty");
        }
        SnsAccessToken snsAccessToken = getOpenSnsOAuth2AccessToken(code);
        return weixin.sns().getSnsUser(snsAccessToken.getAccess_token(), snsAccessToken.getOpenid(), lang);
    }

    /**
     * 获取扫码登录AccessToken
     * 在执行完 获取授权链接A 后执行此步骤,通过code获取
     *
     * @param code
     * @return 扫码登录AccessToken
     * @throws WeixinException
     * @since 0.1.0
     */
    public SnsAccessToken getOpenSnsOAuth2AccessToken(String code) throws WeixinException {
        if (StringUtils.isEmpty(code)) {
            throw new IllegalArgumentException("code can't be null or empty");
        }
        //拼接参数
        String param = "?appid=" + weixin.getWeixinConfig().getOpenappid() + "&secret=" + weixin.getWeixinConfig().getOpenappsecret() + "&code=" + code + "&grant_type=authorization_code";
        System.out.println("weixin.getOpenAppId():"+weixin.getWeixinConfig().getOpenappid());
        //创建请求对象
        HttpsClient http = new HttpsClient();
        //调用获取access_token接口
        Response res = http.get("https://api.weixin.qq.com/sns/oauth2/access_token" + param);
        //根据请求结果判定，是否验证成功
        JSONObject jsonObj = res.asJSONObject();
        if (jsonObj == null) {
            return null;
        }
        if (Configuration.isDebug()) {
            System.out.println("getOpenSnsOAuth2AccessToken返回json：" + jsonObj.toString());
        }
        Object errcode = jsonObj.get("errcode");
        if (errcode != null) {
            //返回异常信息
            throw new WeixinException(getCause(jsonObj.getIntValue("errcode")));
        }
        return new SnsAccessToken(jsonObj);
    }




}
