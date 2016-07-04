<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <title>团购商品列表</title>
  <%@include file="../common/head.jsp" %>
    <link rel="stylesheet" type="text/css" href="../../resource/css/groupbuying.css" />
</head>
<body>
<header class="header-title  top-fixed">
    团购列表
</header>
<article id='groupbuying-body'>
    <div class="header-title"></div>
    <div class="slide-container">
        <img src="http://pic2.cxtuku.com/00/12/02/b655719e74ec.jpg" width="100%" height="160px" />
    </div>
    <div class="groupbuying-list">
        <div class="weui_cells weui_cells_access">
            <c:forEach items="${list}" var="sk">
                <a class="weui_cell" href="${sk.id}/detail">
                    <div class="weui_cell_hd" >
                        <img src="${sk.logo}" alt="logo">
                    </div>
                    <div class="weui_cell_content">
                        <div class="groupbuying-dis skill-content">
                                ${sk.name}
                        </div>
                        <%--<div style="height: 10%;">--%>
                        <%--</div>--%>
                        <div class="groupbuying_inf skill_content">
                            <div class="groupbuying_price_button">
                                <div class="groupbuying_price">
                                    <span class="price">${sk.integral}</span>
                                    <span style="color: #232326;font-size: 12px;">积分</span>
                                </div>
                                <c:if test="${sk.remain <= 0}">
                                    <div class="groupbuying_button groupbuying_background_grey">
                                        已售罄
                                    </div>
                                </c:if>
                                <c:if test="${sk.remain > 0}">
                                    <c:if test="${sk.startTime.time <= System.currentTimeMillis()}">
                                        <div class="groupbuying_button">
                                            参团
                                        </div>
                                    </c:if>
                                    <c:if test="${sk.startTime.time > System.currentTimeMillis()}">
                                        <div class="groupbuying_button groupbuying_background_grey">
                                            未开始
                                        </div>
                                    </c:if>

                                </c:if>
                            </div>
                            <div class="groupbuying_dprice_remain">
                                    <div class="groupbuying_dprice">
                                    原价<s> ${sk.originalIntegral}</s>
                                    </div><div class="sale_count">
                                    已团 ${sk.total - sk.remain}
                                </div>
                                <%--去掉进度条--%>
                                <%--<div class="weui_progress">--%>
                                    <%--<div class="weui_progress_bar">--%>
                                        <%--<div class="weui_progress_inner_bar js_progress" style="width: ${(sk.total - sk.remain)/(sk.total)*100}%;">--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            </div>
                        </div>
                    </div>
                </a>
            </c:forEach>
        </div>
    </div>
</article>



  <%--<div class="container">--%>
    <%--<div class="panel panel-default">--%>
      <%--<div class="panel-heading text-center">--%>
        <%--<h2>团购列表</h2>--%>
      <%--</div>--%>
      <%--<div class="panel-body">--%>
        <%--<table class="table table-hover">--%>
          <%--<thead>--%>
            <%--<tr>--%>
              <%--<th>名称</th>--%>
              <%--<th>库存</th>--%>
              <%--<th>开始时间</th>--%>
              <%--<th>结束时间</th>--%>
              <%--<th>创建时间</th>--%>
              <%--<th>详情页</th>--%>
            <%--</tr>--%>
          <%--</thead>--%>
          <%--<tbody>--%>
            <%--<c:forEach items="${list}" var="groupbuying">--%>
            <%--<tr>--%>
              <%--<td>${groupbuying.name}</td>--%>
              <%--<td>${groupbuying.remain}</td>--%>
              <%--<td>--%>
                <%--<fmt:formatDate value="${groupbuying.startTime}" pattern="yyyy-MM-dd HH:mm:ss" />--%>
              <%--</td>--%>
              <%--<td>--%>
                <%--<fmt:formatDate value="${groupbuying.endTime}" pattern="yyyy-MM-dd HH:mm:ss" />--%>
              <%--</td>--%>
              <%--<td>--%>
                <%--<fmt:formatDate value="${groupbuying.createdAt}" pattern="yyyy-MM-dd HH:mm:ss" />--%>
              <%--</td>--%>
              <%--<td><a class="btn btn-info" href="${groupbuying.id}/detail" target="_blank">详情</a></td>--%>
            <%--</tr>--%>
            <%--</c:forEach>--%>
          <%--</tbody>--%>
        <%--</table>--%>

      <%--</div>--%>
    <%--</div>--%>
  <%--</div>--%>

  <script src="../../resource/dist/lib/jquery-2.1.4.js" type="text/javascript" charset="utf-8"></script>
  <script src="../../resource/dist/js/jquery-weui.min.js" type="text/javascript" charset="utf-8"></script>
  <script type="text/javascript" charset="utf-8">
      $(document).ready(function () {
          (function () {
              var width = window.innerWidth;
              $("html").css("width",width+"px");
              $(".weui_cell_content").css("width",width-130+'px');
          }());
          (function() {
              $.getUrlParam = function(name) {
                  var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
                  var r = window.location.search.substr(1).match(reg);
                  if (r != null) return decodeURI(r[2]);
                  return null;
              }
          }());
          var  token = $.getUrlParam("token");
          if (!token) {
              token = <% out.println(request.getHeader("X-MALLTO-AUTHORIZATION")); %>;
          }
          var app =  <% out.println(request.getHeader("X-MALLTO-SYSTEM")); %>;

          if (token && app) {
              $(".weui_cell").each(function () {
                  this.href += ("?token="+token+"&app="+app);
              });
          } else if (token) {
              $(".weui_cell").each(function () {
                  this.href += ("?token="+token);
              });
          } else if (app) {
              $(".weui_cell").each(function () {
                  this.href += ("?app="+app);
              });
          }
      })
  </script>

</body>
</html>