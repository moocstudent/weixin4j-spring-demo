# weixin4j-spring-demo
### 使用weixin4j(岸思版)springboot创建Java微信开发demo一例
### 2018-12-05 实现微信配置连接,自定义菜单增(未有前端form表单TODO)查删,消息回复,事件响应.
### TODO 消息回复和事件响应扩展单独出自己的消息库和事件库
### TODO 前端form表单提交自定义菜单信息 可参照 http://www.xwcms.net/js/bddm/43937.html 和 http://www.jq22.com/jquery-info18771 (仿照)
### TODO 连接(配置连接路径已经菜单路径)的过滤 (可通过判定appid等) doFilter放行.
### TODO 扫码登陆以及获取用户信息.
### TODO 支付及退款.

---

### 2018-12-06 
### 展示menuCreate测试页 menuCreate.jsp
### 接收create创建菜单的参数转换为String,内部进行JSONObject的转换
### 对delete进行了判定是否为空的操作(0,无菜单可删除; 1删除菜单成功)
### 加入servlet路径以及jsp解析前后缀
### tomcat-embed-core-9.0.13依然报错.(不影响运行)
### 前端页面menuCreate.jsp (假定的form数据,并无form样式)
![image](https://github.com/deadzq/weixin4j-spring-demo/blob/master/weixinMenu.gif)


---

