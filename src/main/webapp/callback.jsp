<%--
  回调页面 将页面拼接的code传给/user/callBackLogin后
  回传1(执行获取对象成功)或0(获取对象失败)进行页面跳转
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>正在跳转...</title>
    <script src="http://www.jq22.com/jquery/jquery-3.3.1.js"></script>
</head>
<body>

<%=request.getParameter("code")%>

正在跳转...

<script>

    $.ajax({
        // url: "/user/getOpenSnsUserInfoByCode",
        url: "/user/callBackLogin",
        data: "<%=request.getParameter("code")%>",
        contentType: "application/json",
        dataType: "json",
        method: "POST",
        success: function (data) {

            if(data===1){
                //right code -> to index
                window.location.href="http://ngrok.ykmimi.com";
            }else{
                //wrong code -> show error page
                window.location.href="http://ngrok.ykmimi.com/error";
            }

        }
    });

</script>

</body>
</html>
