<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="../common/tag.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>团购详情页</title>
    <%@include file="../common/head.jsp" %>
    <link rel="stylesheet" type="text/css" href="../../resource/css/groupbuyingdetail.css" />
</head>
<body>
<header class="good-header header-title">
    商品详情
</header>
<article id='detail-body'>
    <div class="good-header"></div>
    <div class="slide-container swiper-container">
        <div class="swiper-wrapper">
            <c:forEach items="${groupbuying.imageUrls}" var="imageUrl">
                <div class="swiper-slide">
                    <img src="${imageUrl}" alt="">
                </div>
            </c:forEach>
        </div>
        <div class="swiper-pagination"></div>
    </div>
    <div class="goods-part">
        <div class="good-name">
            ${groupbuying.name}
        </div>
        <div class="good-count">
            <span class="good-title">已售${groupbuying.total-groupbuying.remain}</span>
            <div class="good-progress">
                <div class="weui_progress">
                    <div class="weui_progress_bar">
                        <div class="weui_progress_inner_bar js_progress" style="width: ${(groupbuying.total-groupbuying.remain)/groupbuying.total*100}%;">
                        </div>
                    </div>
                </div>
            </div>
            <div class="count-down">
						<span id="remains">
                            <c:if test="${groupbuying.startTime.time <= System.currentTimeMillis()}">距结束</c:if>
							<c:if test="${groupbuying.startTime.time > System.currentTimeMillis()}">距开始</c:if>
						</span>
                <span id="day_show">00天</span>
                <strong id="hour_show">00</strong>
                <span class="time-separator">:</span>
                <strong id="minute_show">00</strong>
                <span class="time-separator">:</span>
                <strong id="second_show">00</strong>
            </div>
        </div>
        <div class="good-des">
            ${groupbuying.describe}
        </div>
    </div>
    <div class="good-details">
        ${groupbuying.details}
        <img src="//m.360buyimg.com/n12/jfs/t1891/336/2668417800/149602/3e7ebe21/56efc039N8d571c49.jpg!q70.jpg" width="100%" />
        <img src="//m.360buyimg.com/n12/jfs/t1891/336/2668417800/149602/3e7ebe21/56efc039N8d571c49.jpg!q70.jpg" width="100%" />
        <img src="//m.360buyimg.com/n12/jfs/t1891/336/2668417800/149602/3e7ebe21/56efc039N8d571c49.jpg!q70.jpg" width="100%" />
    </div>
    <div class="good-footer">
    </div>
</article>
<a href="javascript:;" class="good-footer bottom-fixed">
    <div class="good-prices">
        <div class="good-price-now">
            <span id="now-price">${groupbuying.integral}</span>
        </div>
        <span style="color: #232326;font-size: 12px;">积分</span>
        <div class="good-price-old">
            原价：<s>${groupbuying.originalIntegral}</s>
        </div>
    </div>
    <c:if test="${groupbuying.remain <= 0}">
        <div class="good-buy good-buy-background-grey" id="directorder">
            已售罄
        </div>
    </c:if>
    <c:if test="${groupbuying.remain > 0}">
        <c:if test="${(groupbuying.startTime.time - System.currentTimeMillis())>0}">
            <div class="good-buy good-buy-background-grey" id="directorder">
                等待团购
            </div>
        </c:if>
        <c:if test="${(groupbuying.startTime.time - System.currentTimeMillis())<=0}">
            <div class="good-buy" id="directorder">
                参团
            </div>
        </c:if>
    </c:if>

</a>

<%--<div class="container">--%>
    <%--<div class="panel panel-default text-center">--%>
        <%--<div class="pannel-heading">--%>
            <%--<h1>${groupbuying.name}</h1>--%>
        <%--</div>--%>

        <%--<div class="panel-body">--%>
            <%--<h2 class="text-danger">--%>
                <%--&lt;%&ndash;显示time图标&ndash;%&gt;--%>
                <%--<span class="glyphicon glyphicon-time"></span>--%>
                <%--&lt;%&ndash;展示倒计时&ndash;%&gt;--%>
                <%--<span class="glyphicon" id="groupbuying-box"></span>--%>
            <%--</h2>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>


</body>
<script src="../../resource/dist/lib/jquery-2.1.4.js" type="text/javascript" charset="utf-8"></script>
<script src="../../resource/dist/js/jquery-weui.min.js" type="text/javascript" charset="utf-8"></script>
<script src="../../resource/dist/js/swiper.min.js" type="text/javascript" charset="utf-8"></script>
<script src="/resource/script/groupbuying.js" type="text/javascript"></script>

<script type="text/javascript">
    $(function () {
        //使用EL表达式传入参数
        groupbuying.detail.init({
            groupbuyingId:${groupbuying.id},
            startTime:${groupbuying.startTime.time},//毫秒
            endTime:${groupbuying.endTime.time}
        });
        (function () {
            //滑动效果初始化
            var mySwiper = new Swiper('.swiper-container', {
                autoplay: 5000,//可选选项，自动滑动
                parallax : true,
                loop : true,
                pagination : '.swiper-pagination',
            })
        }());
        //执行秒杀函数
        groupbuying.handlerGroupbuying(${groupbuying.id},${groupbuying.integral});

    })
</script>
</html>