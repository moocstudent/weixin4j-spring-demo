<%--
  Created by IntelliJ IDEA.
  User: SeeClanUkyo
  Date: 2018/11/27
  Time: 10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>公众号设置</title>

    <!--BootStrap的样式文件-->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- bootstrap-select下拉css -->
    <link rel="stylesheet" href="css/bootstrap-select.min.css"/>

    <!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">-->


    <!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
    <script src="https://code.jquery.com/jquery.js"></script>

    <script src="js/jquery.serializejson.js"></script>

    <!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>-->


    <!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
    <!--<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">-->


    <!-- Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <!-- bootstrap-select下拉js -->
    <script src="js/bootstrap-select.min.js"></script>
    <script src="js/defaults-zh_CN.min.js"></script>


    <!-- 新 Bootstrap4 核心 CSS 文件 -->
    <!-- <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">-->


    js1>
    <!-- <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>-->

   js1->
    <!-- <script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>-->

    <!-- 最新的 Bootstrap4 核心 JavaScript 文件 -->
    <!--<script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>-->


</head>
<body>


<div class="container">
    <h1 class="center">公众号配置</h1>
    <div class="row clearfix">
        <div class="col-md-12 column">


            <!--公众号配置-->


            <div class="alert alert-success alert-dismissable">
                公众号配置
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">x</button>
            </div>
            <form role="form" id="wxConfigForm">
                <div class="form-group">
                    <label>AppID</label>
                    <input type="text" class="form-control" name="appid" id="appid"/>
                </div>
                <div class="form-group">
                    <label>AppSecret</label>
                    <input type="text" class="form-control" name="appsecret" id="appsecret"/>
                </div>
                <div class="form-group">
                    <label>EncodingAESKey</label>
                    <input type="text" class="form-control" placeholder="(消息加解密密钥)"
                           name="encodingaeskey" id="encodingaeskey"/>
                </div>
                <div class="form-group">
                    <label>url</label>
                    <input type="text" class="form-control" placeholder="填写的URL需要正确响应微信发送的Token验证"
                           name="url" id="url"/>
                </div>
                <div class="form-group">
                    <label>token</label>
                    <input type="text" class="form-control" placeholder="token"
                           name="token" id="token"/>
                </div>


                <button type="button" id="wxConfigFormBtn" class="btn btn-default">提交(微信接口参数)</button>
            </form>


            <!--将appid,appsecret,encodingAESKey的form表单内容提交到数据库-->
            <script>
                //注册按钮点击执行注册判定
                // $("#regButton").click(registerUser);

                $("#wxConfigFormBtn").click(saveWxConfig);

                //点击设置微信信息的form表单提交按钮后,执行wxConfig的controller层,继而调用存储
                function saveWxConfig() {
                    console.log($("#wxConfigForm").serializeJSON());
                    $.ajax({
                        url: "wxConfig",
                        type: "POST",
                        data: $("#wxConfigForm").serializeJSON(),
                        dataType: "json",
                        success: function (data) {
                            console.log("进入微信api设置form表单提交阶段");
                            console.log(data);
                            if (data.code == 1) {
                                console.log("发送成功");
                            } else {
                                console.log("发送失败");
                            }
                        }
                    })
                }

                // function showJson(){
                //     alert("in showJson method");
                //     console.log("in showJson method");
                //     console.log($("#wxConfig").serializeJSON());
                // }

            </script>


            <!--基础设置-->


            <div class="alert alert-success alert-dismissable">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                基础设置
            </div>
            <form role="form">
                <div class="form-group">
                    <label>默认回复语</label><input type="text" class="form-control" placeholder="(如果未匹配到用户的内容，默认回复该内容)"
                                               name="msgBack" id="msgBack"/>
                </div>
                <div class="form-group">
                    <label>默认微信分享成功提示语</label><input type="text" class="form-control" placeholder="" name="msgShareOk"
                                                     id="msgShareOk"/>
                </div>
                <div class="form-group">
                    <label>默认微信分享描述</label><input type="text" class="form-control" placeholder="" name="msgShareContent"
                                                  id="msgShareContent"/>
                </div>

                <div class="form-group">
                    <label>公众号二维码上传 </label><input type="file" name="qrcodeImgUpload"
                                                   id="qrcodeImgUpload"/>
                    <p class="help-block">
                        公众号二维码图片上传(未设置上传路径)
                    </p>
                </div>

                <button type="submit" class="btn btn-default">Submit</button>

            </form>


            <!--推广二维码设置-->


            <div class="alert alert-success alert-dismissable">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                推广二维码设置<a href="javascript:void(0);" class="alert-link"></a>
            </div>
            <form role="form">
                <div class="form-group">
                    <label>二维码背景图</label><input type="file" name="qrcodeBgImg" id="qrcodeBgImg"/>
                    <p class="help-block">
                        推广二维码的背景图
                    </p>
                </div>
                <div class="form-group">
                    <label>个性定义文字内容</label><input type="textarea" class="form-control" name="AdText" id="AdText"/>
                </div>
                <div class="form-group">
                    <label>个性文字颜色</label><input type="password" class="form-control" name="AdTextColor"
                                                id="AdTextColor"/>
                </div>
                <button type="submit" class="btn btn-default">保存推广二维码设置</button>
            </form>
            <p>这里预览设置的推广图片</p>
            <img>


            <!--添加菜单(微信公众号添加菜单,触发遮罩窗体)-->


            <div class="alert alert-success alert-dismissable">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                公众号菜单<a href="javascript:void(0);" class="alert-link"></a>
            </div>
            <button id="modal-3941" href="#modal-container-3941" role="button" type="button"
                    class="btn btn-lg btn-primary" data-toggle="modal">
                添加自定义菜单
            </button>
            <button type="button" class="btn btn-lg btn-warning">
                将菜单更新至公众号
            </button>
            <button type="button" class="btn btn-default">按钮</button>
            <button type="button" class="btn btn-default">按钮</button>

            <!--遮罩窗体-->


            <div class="col-md-12 column">
                <div class="modal fade" id="modal-container-3941" role="dialog" aria-labelledby="myModalLabel"
                     aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                <h4 class="modal-title" id="myModalLabel">
                                    添加自定义一级菜单(MAX : 3)
                                </h4>
                            </div>
                            <div class="modal-body">
                                <!--遮罩窗体上自定义菜单相关form表单-->
                                <form role="form" id="menuForm">
                                    <div class="form-group">
                                        <label>菜单名称</label><input type="email" class="form-control" name="menuName"
                                                                  id="menuName"/>
                                    </div>

                                    <div class="form-group">
                                        <label>菜单类型</label>
                                        <select class="selectpicker show-tick form-control">
                                            <option value="1">无事件的一级菜单</option>
                                            <option value="2">点击推送事件(再次点击又加一个select选项)</option>
                                            <option value="3">跳转URL(系统页面||自定义网址)</option>
                                            <option value="4">扫码推送事件</option>
                                            <option value="5">扫码带提示</option>
                                            <option value="6">弹出系统拍照发图</option>
                                            <option value="7">弹出拍照或相册发图</option>
                                            <option value="8">弹出微信相册发图器</option>
                                            <option value="9">弹出地理位置选择器</option>
                                        </select>
                                    </div>

                                    <!--<button type="submit" class="btn btn-default">Submit</button>-->
                                </form>

                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                <button type="button" class="btn btn-primary" id="menuFormConserveBtn" name="conserve">
                                    保存
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <!-- <form role="form">
                 <div class="form-group">
                     <label for="exampleInputEmail1">Email address</label><input type="email" class="form-control"
                                                                                 id="exampleInputEmail1"/>
                 </div>
                 <div class="form-group">
                     <label for="exampleInputPassword1">Password</label><input type="password" class="form-control"
                                                                               id="exampleInputPassword1"/>
                 </div>
                 <div class="form-group">
                     <label for="exampleInputFile">File input</label><input type="file" id="exampleInputFile"/>
                     <p class="help-block">
                         Example block-level help text here.
                     </p>
                 </div>
                 <div class="checkbox">
                     <label><input type="checkbox"/>Check me out</label>
                 </div>
                 <button type="submit" class="btn btn-default">Submit</button>
             </form>-->


            <!---->


            <div class="alert alert-success alert-dismissable">
                <div>
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button>
                </div>
                <div>
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button>
                </div>
                事件管理 等 其它暂略
            </div>
        </div>
    </div>
</div>


</body>
</html>
