# weixin4j-spring-demo
### 使用weixin4j(岸思版)springboot创建Java微信开发demo一例
### 2018-12-05 实现微信配置连接,自定义菜单增(未有前端form表单TODO)查删,消息回复,事件响应.
### TODO 消息回复和事件响应扩展单独出自己的消息库和事件库
### TODO 前端form表单提交自定义菜单信息 可参照 http://www.xwcms.net/js/bddm/43937.html 和 http://www.jq22.com/jquery-info18771 (仿照)
### TODO 连接(配置连接路径已经菜单路径)的过滤 (可通过判定appid等) doFilter放行.
### TODO 扫码登陆以及获取用户信息. << 2018-12-08 done
### TODO 支付及退款. 2018-12-21

---

### 2018-12-06 
### 展示menuCreate测试页 menuCreate.jsp done
### 接收create创建菜单的参数转换为String,内部进行JSONObject的转换 done
### 对delete进行了判定是否为空的操作(0,无菜单可删除; 1删除菜单成功)
### 加入servlet路径以及jsp解析前后缀
### tomcat-embed-core-9.0.13依然报错.(不影响运行) done
### 前端页面menuCreate.jsp (假定的form数据,并无form样式)
![image](https://github.com/deadzq/weixin4j-spring-demo/blob/master/weixinMenu.gif)


---

### TODO 获取用户信息 将信息存到数据库
### TODO 扫码登录 同时获取用户信息 done
### TODO messageChat.jsp页面的加强 (加入假定的关键词匹配数据进行存储)
### DefaultNormalMessageHandler 的关键字回复方法(从数据库获取再进行)

---

### 微信支付测试 2018-12-21 使用测试号appid,secret
### 微信支付必须经过配置支付服务号的服务器配置才能正式调起支付.可以先梳理下单后的逻辑.
### 微信支付异常支付用例进行相关处理 2018-12-24

---
### [扫码登录及网页授权的UML图](http://assets.processon.com/chart_image/5c1e3ac3e4b0b71ee505f5b8.png)
### 2018-12-24
### 网页端使用判定是否是内置微信浏览器来推断是否该通过code获取用户信息或通过扫码来获取用户信息.

### TODO
### 对接寄件功能 

### 生成推广二维码的转发 用户头像+二维码+推广海报
