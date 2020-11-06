<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>参数</title>
<%--    加载JQuery--%>
    <script type="text/javascript" src="https://ajax.aspnetcdn.com/ajax/jquery/jquery-3.5.1.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            //模拟30000个异步请求，进行并发
            var max = 15000;
            for(var i = 1; i <= max; i++){
                $.post({
                    url: "./userRedPacket/grapRedPacket.do?redPacketId=1&userId=" + i,
                    success: function(result){

                    }
                });
            }
        });
    </script>
</head>
<body>
<h2>Hello World!</h2>
</body>
</html>
