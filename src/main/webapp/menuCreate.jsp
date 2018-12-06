<%--
  Created by IntelliJ IDEA.
  User: SeeClanUkyo
  Date: 2018/12/06
  Time: 13:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>自定义菜单生成</title>

    <script src="http://www.jq22.com/jquery/jquery-3.3.1.js"></script>
</head>
<body>
<h3>示例menu数据</h3>
<pre>
{
    "menu": {
        "button": [
            {
                "type": "click",
                "name": "今日歌曲",
                "key": "V1001_TODAY_MUSIC",
                "sub_button": [ ]
            },
            {
                "type": "click",
                "name": "歌手简介",
                "key": "V1001_TODAY_SINGER",
                "sub_button": [ ]
            },
            {
                "name": "菜单",
                "sub_button": [
                    {
                        "type": "view",
                        "name": "搜索",
                        "url": "http://www.soso.com/",
                        "sub_button": [ ]
                    },
                    {
                        "type": "view",
                        "name": "视频",
                        "url": "http://v.qq.com/",
                        "sub_button": [ ]
                    },
                    {
                        "type": "click",
                        "name": "赞一下我们",
                        "key": "V1001_GOOD",
                        "sub_button": [ ]
                    }
                ]
            }
        ]
    }
}
</pre>

<button id="createMenuBtn">点击提交--create自定义菜单</button>

<button id="getMenuBtn">获取--get自定义菜单数据</button>

<button id="deleteMenuBtn">删除--delete自定义菜单(全部)</button>
<script>
    //  如果 menuJson 就是 自定义菜单转换 json 后的数据.
    var menuJson = '{"menu":{"button":[{"type":"click","name":"今日歌曲","key":"V1001_TODAY_MUSIC","sub_button":[]},{"type":"click","name":"歌手简介","key":"V1001_TODAY_SINGER","sub_button":[]},{"name":"菜单","sub_button":[{"type":"view","name":"搜索","url":"http://www.soso.com/","sub_button":[]},{"type":"view","name":"视频","url":"http://v.qq.com/","sub_button":[]},{"type":"click","name":"赞一下我们","key":"V1001_GOOD","sub_button":[]}]}]}}';


    // var menuJsonObj = JSON.parse(menuJson);  //string 转 object
    // var menuJsonStr = JSON.stringify(menuJson);  //string 转 转义后的字符
    //点击创建自定义菜单按钮后 执行方法 postMenuJson()
    $("#createMenuBtn").click(postMenuJson);

    //传送menuJson数据到后台/menu/create创建自定义菜单
    function postMenuJson(){
        // alert("menuJsonStr :　"+menuJsonStr);
        alert("menuJson : "+menuJson);
        // alert("menuJsonObj : "+menuJsonObj);
        // alert("JSON.stringify(menuJsonObj) : "+JSON.stringify(menuJsonObj));

        $.ajax({
            url:"/menu/create",
            data:menuJson,
            contentType:"application/json",
            method:"POST",
            dataType:"json",
            success:function(data){
                alert("submit ok");
            }
        })
    }

    //点击获取菜单数据, 进行展示
    $("#getMenuBtn").click(getMenuJson);

    //获取菜单数据 alert展示
    function getMenuJson(){

        $.ajax({
            url:"/menu/get",
            data:null,
            success:function (data) {
                alert(JSON.stringify(data));
            }
        })
    }


    //点击删除自定义菜单按钮执行删除方法
    $("#deleteMenuBtn").click(deleteMenuOfAll);

    //删除自定义菜单方法
    function deleteMenuOfAll(){
        $.ajax({
            url:"/menu/delete",
            data:null,
            success:function(data){
                alert(data);
            }
        })
    }

</script>






</body>
</html>
