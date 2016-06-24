<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="../common/tag.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>团购详情页</title>
    <%@include file="../common/head.jsp" %>
</head>
<body>

请登录

<div class="container">
    <div class="panel panel-default text-center">
        <div class="pannel-heading">
            <h1>${groupbuying.name}</h1>
        </div>

        <div class="panel-body">
            <h2 class="text-danger">
                <%--显示time图标--%>
                <span class="glyphicon glyphicon-time"></span>
                <%--展示倒计时--%>
                <span class="glyphicon" id="groupbuying-box"></span>
            </h2>
        </div>
    </div>
</div>


</body>
<%--jQery文件,务必在bootstrap.min.js之前引入--%>
<script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>
<script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<%--使用CDN 获取公共js http://www.bootcdn.cn/--%>
<%--jQuery Cookie操作插件--%>
<script src="http://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<%--jQuery countDown倒计时插件--%>
<script src="http://cdn.bootcss.com/jquery.countdown/2.1.0/jquery.countdown.min.js"></script>

<script src="/resource/script/groupbuying.js" type="text/javascript"></script>

<script type="text/javascript">
    $(function () {
        //使用EL表达式传入参数
        groupbuying.detail.init({
            groupbuyingId:${groupbuying.id},
            startTime:${groupbuying.startTime.time},//毫秒
            endTime:${groupbuying.endTime.time}
        });
    })
</script>
</html>