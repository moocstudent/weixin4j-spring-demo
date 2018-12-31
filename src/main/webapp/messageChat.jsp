<%--
create by ukzq
可实现网页版的聊天关键字回复
根据微信聊天的同样的数据库获取数据
数据录入在后台->人员录入 [关键字] [回复信息] 数据表
可进行模糊关键字匹配
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>聊天回复</title>

    <script src="http://www.jq22.com/jquery/jquery-3.3.1.js"></script>
</head>
<body>
<form id="messageForm">
<input type="text" id="inputMessage" name="inputMessage" />
<input type="button" name="submitBtn" id="submitBtn" value="submit发送消息"/>

</form>

<script>
  $("#submitBtn").click(sendMessage);

  function sendMessage(){
      alert($("#inputMessage").val());
      $.ajax({
          url:"/chat/webMessageHandler",
          data:$("#inputMessage").val(),
          contentType:"application/json",
          method:"POST",
          dataType:"json",
          success:function(data){
              alert("消息返回code:"+data.code);
              alert("回复消息:"+data.message);
          }
      });
  }



</script>


</body>
</html>
