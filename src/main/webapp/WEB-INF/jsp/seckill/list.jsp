<%@ page import="java.util.Enumeration" %>
<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <title>掌上秒杀</title>
  <%@include file="../common/head.jsp" %>
    <link rel="stylesheet" type="text/css" href="../../resource/css/seckill.css" />
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
                    <img src="${sk.logo}" alt="logo">
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
                                        <c:if test="${sk.startTime.time <= System.currentTimeMillis()}">
                                            <div class="seckill_button">
                                                去秒杀
                                            </div>
                                        </c:if>
                                    <c:if test="${sk.startTime.time > System.currentTimeMillis()}">
                                        <div class="seckill_button seckill_background_grey">
                                            待秒杀
                                        </div>
                                    </c:if>

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


<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
  <script src="../../resource/dist/lib/jquery-2.1.4.js" type="text/javascript" charset="utf-8"></script>
  <script src="../../resource/dist/js/jquery-weui.min.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
    $(document).ready(function () {
        //初始化布局
        (function () {
            var width = window.innerWidth;
            console.log(width);
            $("html").css("width",width+"px");
            $(".weui_cell_content").css("width",width-130+'px');
        })();
        //检测token 以及app
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