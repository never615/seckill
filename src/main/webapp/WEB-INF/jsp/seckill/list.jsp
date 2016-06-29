<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <title>掌上秒杀</title>
  <%@include file="../common/head.jsp" %>
</head>
<body>
<header class="header-title  top-fixed">
    掌上秒杀
</header>
<article id='seckill-body'>
    <div class="header-title"></div>
    <div class="slide-container">
        <img src="http://image.bitauto.com/dealer/news/1601163/3e0c97c8-d392-44c8-9671-4039491f0749.jpg" width="100%" height="160px" />
    </div>
    <div class="seckill-list">
        <div class="weui_cells weui_cells_access">
            <c:forEach items="${list}" var="sk">
            <a class="weui_cell" href="${sk.id}/detail">
                <div class="weui_cell_hd" >
                    <img src="${sk.logo}" alt="icon">
                </div>
                <div class="weui_cell_content">
                    <div class="seckill-dis skill-content">
                        ${sk.name}
                    </div>
                    <div style="height: 10%;">
                    </div>
                    <div class="seckill_inf skill_content">
                        <div class="seckill_price_button">
                            <div class="seckill_price">
                                <span class="price">${sk.integral}</span>
                                    <span style="color: #232326;font-size: 12px;">积分</span>
                            </div>

                                <c:if test="${sk.remain <= 0}">
                                    <div class="seckill_button seckill_background_grey">
                                     秒杀完
                                    </div>
                                </c:if>
                                <c:if test="${sk.remain > 0}">
                                    <div class="seckill_button">
                                        去秒杀
                                    </div>
                                </c:if>
                        </div>
                        <div class="seckill_dprice_remain">
                            <%--原价--%>
                            <%--<div class="seckill_dprice">--%>
                                <%--<s>￥800</s>--%>
                            <%--</div>--%>
                            <div class="sale_count">
                                已秒${sk.total - sk.remain}/${sk.total}
                            </div>
                            <div class="weui_progress">
                                <div class="weui_progress_bar">
                                    <div class="weui_progress_inner_bar js_progress" style="width: ${(sk.total - sk.remain)/(sk.total)*100}%;">
                                    </div>
                                </div>
                            </div>
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
        <%--<h2>掌上秒杀</h2>--%>
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
            <%--<c:forEach items="${list}" var="sk">--%>
            <%--<tr>--%>
              <%--<td>${sk.name}</td>--%>
              <%--<td>${sk.remain}</td>--%>
              <%--<td>--%>
                <%--<fmt:formatDate value="${sk.startTime}" pattern="yyyy-MM-dd HH:mm:ss" />--%>
              <%--</td>--%>
              <%--<td>--%>
                <%--<fmt:formatDate value="${sk.endTime}" pattern="yyyy-MM-dd HH:mm:ss" />--%>
              <%--</td>--%>
              <%--<td>--%>
                <%--<fmt:formatDate value="${sk.createdAt}" pattern="yyyy-MM-dd HH:mm:ss" />--%>
              <%--</td>--%>
              <%--<td><a class="btn btn-info" href="${sk.id}/detail" target="_blank">详情</a></td>--%>
            <%--</tr>--%>
            <%--</c:forEach>--%>
          <%--</tbody>--%>
        <%--</table>--%>

      <%--</div>--%>
    <%--</div>--%>
  <%--</div>--%>



<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
  <script src="../../resource/dist/lib/jquery-2.1.4.js" type="text/javascript" charset="utf-8"></script>
  <script src="../../resource/dist/js/jquery-weui.min.js" type="text/javascript" charset="utf-8"></script>
  <script src="../../resource/script/seckilllist.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>