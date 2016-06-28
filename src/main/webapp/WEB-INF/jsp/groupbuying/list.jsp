<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <title>团购商品列表</title>
  <%@include file="../common/head.jsp" %>
</head>
<body>
  <div class="container">
    <div class="panel panel-default">
      <div class="panel-heading text-center">
        <h2>团购列表</h2>
      </div>
      <div class="panel-body">
        <table class="table table-hover">
          <thead>
            <tr>
              <th>名称</th>
              <th>库存</th>
              <th>开始时间</th>
              <th>结束时间</th>
              <th>创建时间</th>
              <th>详情页</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${list}" var="groupbuying">
            <tr>
              <td>${groupbuying.name}</td>
              <td>${groupbuying.remain}</td>
              <td>
                <fmt:formatDate value="${groupbuying.startTime}" pattern="yyyy-MM-dd HH:mm:ss" />
              </td>
              <td>
                <fmt:formatDate value="${groupbuying.endTime}" pattern="yyyy-MM-dd HH:mm:ss" />
              </td>
              <td>
                <fmt:formatDate value="${groupbuying.createdAt}" pattern="yyyy-MM-dd HH:mm:ss" />
              </td>
              <td><a class="btn btn-info" href="${groupbuying.id}/detail" target="_blank">详情</a></td>
            </tr>
            </c:forEach>
          </tbody>
        </table>

      </div>
    </div>
  </div>



<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</body>
</html>