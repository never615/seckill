<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="../common/tag.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>秒杀详情</title>
    <%@include file="../common/head.jsp" %>
    <link rel="stylesheet" type="text/css" href="../../resource/css/seckilldetail.css" />
</head>
<body>

<header class="good-header header-title">
    商品详情
</header>
<article id='detail-body'>
    <div class="good-header"></div>
    <div class="slide-container swiper-container">
        <div class="swiper-wrapper">
            <c:forEach items="${seckill.imageUrls}" var="imageUrl">
                <div class="swiper-slide">
                    <img src="${imageUrl}" alt="">
                </div>
            </c:forEach>
        </div>
        <div class="swiper-pagination"></div>
    </div>
    <div class="goods-part">
        <div class="good-name">
            ${seckill.name}
        </div>
        <div class="good-count">
            <span class="good-title">已售${seckill.total-seckill.remain}</span>
            <div class="good-progress">
                <div class="weui_progress">
                    <div class="weui_progress_bar">
                        <div class="weui_progress_inner_bar js_progress" style="width: ${(seckill.total-seckill.remain)/seckill.total*100}%;">
                        </div>
                    </div>
                </div>
            </div>
            <div class="count-down">
						<span id="remains">
                            <c:if test="${seckill.startTime.time <= System.currentTimeMillis()}">距结束</c:if>
							<c:if test="${seckill.startTime.time > System.currentTimeMillis()}">距开始</c:if>
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
            ${seckill.describe}
        </div>
    </div>
    <div class="good-details">
        ${seckill.details}
    </div>
    <div class="good-footer">
    </div>
</article>
<a href="javascript:;" class="good-footer bottom-fixed">
    <div class="good-prices">
        <div class="good-price-now">
            <span id="now-price">${seckill.integral}</span>
        </div>
        <span style="color: #232326;font-size: 12px;">积分</span>
        <div class="good-price-old">
            <%--秒杀不需要原价--%>
            <%--<s>${seckill.originalIntegral}</s>--%>
        </div>
    </div>
    <c:if test="${seckill.remain <= 0}">
        <div class="good-buy good-buy-background-grey" id="directorder">
            已抢购完
        </div>
    </c:if>
    <c:if test="${seckill.remain > 0}">
        <c:if test="${(seckill.startTime.time - System.currentTimeMillis())>0}">
            <div class="good-buy good-buy-background-grey" id="directorder">
                等待秒杀
            </div>
        </c:if>
        <c:if test="${(seckill.startTime.time - System.currentTimeMillis())<=0}">
            <div class="good-buy" id="directorder">
                立即秒杀
            </div>
        </c:if>
    </c:if>

</a>
</body>
<script src="../../resource/dist/lib/jquery-2.1.4.js" type="text/javascript" charset="utf-8"></script>
<script src="../../resource/dist/js/jquery-weui.min.js" type="text/javascript" charset="utf-8"></script>
<script src="../../resource/dist/js/swiper.min.js" type="text/javascript" charset="utf-8"></script>
<script src="../../resource/script/seckill.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function () {
        //使用EL表达式传入参数
      seckill.detail.init({
            seckillId:${seckill.id},
            startTime:${seckill.startTime.time},//毫秒
            endTime:${seckill.endTime.time}
        });
        (function () {
            //滑动效果初始化
            var mySwiper = new Swiper('.swiper-container', {
                autoplay: 5000,//可选选项，自动滑动
                parallax : true,
                loop : true,
                pagination : '.swiper-pagination',
            })
        })()

    })
     seckill.handlerSeckill(${seckill.id},${seckill.integral});

</script>
</html>