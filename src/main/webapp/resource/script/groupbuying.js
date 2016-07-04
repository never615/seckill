//存放主要交互逻辑的js代码
// javascript 模块化(package.类.方法)
//判断是否是微信浏览器
var isWx = function () {
    var userAgent = navigator.userAgent.toLowerCase();
    var result = userAgent.match(/micromessenger/)
    if (result) {
        return true;
    } else {
        return false;
    }
}
//获取token

(function () {
    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return decodeURI(r[2]);
        return null;
    }
}());
var token = $.getUrlParam("token");
var app = $.getUrlParam("app");


var groupbuying = {

    //封装团购相关ajax的url
    URL: {
        now: function () {
            return '/groupbuying/time/now';
        },
        execution: function (groupbuyingId) {
            return '/groupbuying/' + groupbuyingId + '/execution';
        }
    },


    //详情页团购逻辑
    detail: {
        //详情页初始化
        init: function (params) {
            //计时交互
            var startTime = params['startTime'];
            var endTime = params['endTime'];
            var groupbuyingId = params['groupbuyingId'];
            $.get(groupbuying.URL.now(), {}, function (result) {
                if (result && result['code'] == 0) {
                    var nowTime = result['data'];
                    var toEnd = (endTime - nowTime) / 1000;
                    var toStart = (startTime - nowTime) / 1000;
                    if (toStart > 0) {
                        groupbuying.timer(toStart);
                    } else {
                        groupbuying.timer(toEnd);
                    }
                } else {
                    //请求时间错误
                    console.log('result: ' + result);
                    alert('result: ' + result['msg']);
                }
            });
        }
    },
//倒计时传入倒计时秒数
    timer: function (intDiff) {
        window.setInterval(function () {
            var day = 0,
                hour = 0,
                minute = 0,
                second = 0; //时间默认值
            if (intDiff > 0) {
                day = Math.floor(intDiff / (60 * 60 * 24));
                hour = Math.floor(intDiff / (60 * 60)) - (day * 24);
                minute = Math.floor(intDiff / 60) - (day * 24 * 60) - (hour * 60);
                second = Math.floor(intDiff) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
            }
            if (minute <= 9) minute = '0' + minute;
            if (second <= 9) second = '0' + second;
            $('#day_show').html(day + "天");
            $('#hour_show').html('<s id="h"></s>' + hour);
            $('#minute_show').html('<s></s>' + minute);
            $('#second_show').html('<s></s>' + second);
            intDiff--;
        }, 1000);
    },
    //执行团购
    handlerGroupbuying: function (groupbuyingId) {
        //获取团购地址,控制显示器,执行团购

        var buyingUrl = groupbuying.URL.execution(groupbuyingId);
        console.log("buyingUrl: " + buyingUrl);
        //绑定一次点击事件
        $('#directorder').on('click', function () {
            //检测平台与登录情况
            // token=  'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjg3NSwiaXNzIjoiaHR0cDpcL1wvYXBpLmlmZW5nZ3VvLmNvbVwvd2VjaGF0X2F1dGhcL3B1YmxpY1wvaW5kZXgucGhwXC9zZWF3b3JsZF93ZWNoYXRcL3dlY2hhdF9hdXRoIiwiaWF0IjoxNDY3NjI0NTEzLCJleHAiOjE0NzU0MDA1MTMsIm5iZiI6MTQ2NzYyNDUxMywianRpIjoiNmUwZmM3ZDliM2NlNWRiYjQ0N2RkMWE2M2Q4MmI4MmQifQ.OU7_p1egifcZx4CatwyiEok20BNAklYh0i31XoShL8g';
            // token = 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjg3NSwiaXNzIjoiaHR0cDpcL1wvYXBpLmlmZW5nZ3VvLmNvbVwvd2VjaGF0X2F1dGhcL3B1YmxpY1wvaW5kZXgucGhwXC9zZWF3b3JsZF93ZWNoYXRcL3dlY2hhdF9hdXRoIiwiaWF0IjoxNDY3NjI0MzY5LCJleHAiOjE0NzU0MDAzNjksIm5iZiI6MTQ2NzYyNDM2OSwianRpIjoiN2JjZGMwZDg4MWUwMDM2ZjNmMGQ4YWUzNmI1NDg3NzUifQ.0_MuV18_paosTuL8Gvp62hML2cNowgP3VIgI1mrePAY';
            // token = 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjcxMywiaXNzIjoiaHR0cDpcL1wvYXBpLmlmZW5nZ3VvLmNvbTo4MVwvYXBpXC9hdXRoXC9sb2dpbiIsImlhdCI6MTQ2NjEzNzUyNiwiZXhwIjoxNDczOTEzNTI2LCJuYmYiOjE0NjYxMzc1MjYsImp0aSI6ImEyZDhiMzkxODY4MjU2NzQ5YWY5Yzk5NmQwNDYxYmIxIn0.wfvVmWE9zaEQw-23aM7oQiXiPWwzDubMcG5rsB2ns-4';
            token = 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjM1MSwiaXNzIjoiaHR0cDpcL1wvYXBpLmlmZW5nZ3VvLmNvbVwvd2VjaGF0X2F1dGhcL3B1YmxpY1wvaW5kZXgucGhwXC9zZWF3b3JsZF93ZWNoYXRcL3dlY2hhdF9hdXRoIiwiaWF0IjoxNDY3NjI0MDM0LCJleHAiOjE0NzU0MDAwMzQsIm5iZiI6MTQ2NzYyNDAzNCwianRpIjoiNGRiYTZhMTQyNGI0ZTk3ZGMzODg5NTMzNjRhYjc1YzEifQ.WGjngXYdlkbO2D5wyYUqtFxrYW74sesoOfhxOaI1d7A';
            if (!token){
                if (isWx) {
                    $.alert("获取微信用户信息失败！");
                    return;
                } else if (app) {
                    var href = location.href;
                    href = "zh-login://+" + href;
                    console.log(href);
                    location.href = href;
                    return;

                } else {
                    console.log("非微信非app");
                    //进入app下载地址
                    window.location = "http://a.app.qq.com/o/simple.jsp?pkgname=com.mallto.seaworld";
                    return;
                }
                //微信或者app
            }

            //执行团购请求
            $.ajax({
                method: "POST",
                url: buyingUrl,
                //todo 测试header
                headers: {
                    Authorization: 'Bearer{'+token+'}'
                },
                statusCode: { 500:function () {
                    $.alert("服务器错误！");
                }
                },
                success: function (result,textStatus) {
                    if (result && result['code'] == 0) {
                        var buyingResult = result['data'];
                        var state = buyingResult['state'];
                        var stateInfo = buyingResult['stateInfo'];
                        //显示团购结果
                        if (state == 0) {
                            //跳转到秒杀成功界面
                            window.location.href = "/";
                        } else if (state == 7001){
                            //显示秒杀结果
                            console.log(stateInfo);
                        } else {
                            $.alert(stateInfo);
                        }
                    } else {
                        $.alert(result['msg']);
                    }
                }
            });
        });
    }
};