<%--
  Created by IntelliJ IDEA.
  User: tianbaolin
  Date: 2016/7/1
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>秒杀成功</title>
  <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
  <meta charset="utf-8">
  <link rel="stylesheet" type="text/css" href="../../resource/dist/lib/weui.min.css" />
  <link rel="stylesheet" type="text/css" href="../../resource/dist/css/jquery-weui.min.css" />
  <link rel="stylesheet" type="text/css" href="../../resource/css/success.css" />


</head>
<body>
<header class="good-header header-title">
  秒杀结果
</header>
<article>
  <div class="message">秒杀成功，商品卷已经加入到我的卡包！</div>
  <div class="directors">
    <a href="http://api.ifengguo.com/seaapp/wx/card.html" class="my-card director">查看我的卡包</a><a href="javascript:;" class="goon director">继续看看</a>
  </div>
</article>
<script src="../../resource/dist/lib/jquery-2.1.4.js" type="text/javascript" charset="utf-8"></script>
<script src="../../resource/dist/js/jquery-weui.min.js" type="text/javascript" charset="utf-8"></script>
<script>
  $.toast.prototype.defaults.duration = 10000000;
  $.toast("操作成功！");
  $(".goon").on("click",function () {
    history.go(-2);
  })

</script>
</body>
</html>