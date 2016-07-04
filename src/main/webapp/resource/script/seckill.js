//存放主要交互逻辑的js代码
// javascript 模块化(package.类.方法)
//检测微信平台
var isWx = function() {
    var userAgent = navigator.userAgent.toLowerCase();
    var result = userAgent.match(/micromessenger/)
    if (result) {
        return true;
    } else {
        return false;
    }
}
//获取token

(function() {
    $.getUrlParam = function(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return decodeURI(r[2]);
        return null;
    }
}());
var token = $.getUrlParam("token");
var app = $.getUrlParam("app");

var seckill = {

    //封装秒杀相关ajax的url
    URL: {
        now: function () {
            return '/seckill/time/now';
        },
        //获取秒杀地址及其它信息
        exposer: function (seckillId) {
            return '/seckill/' + seckillId + '/exposer';
        },
        //秒杀地址
        execution: function (seckillId, md5) {
            return '/seckill/' + seckillId + '/' + md5 + '/execution';
        }
    },
    //详情页秒杀逻辑
    detail: {
        //详情页初始化
        init: function (params) {
            var startTime = params['startTime'];
            var endTime = params['endTime'];
            var seckillId = params['seckillId'];

            //秒杀倒计时
            $.ajax({
                type: "GET",
                url: seckill.URL.now(),
                async: true,
                success: function (result) {
                    if (result && result['code'] == 0) {
                        var nowTime = result['data'];
                        //秒杀倒计时
                        var toEnd = (endTime - nowTime) / 1000;
                        var toStart = (startTime - nowTime) / 1000;
                        if (toStart > 0) {
                            seckill.timer(toStart);
                        } else {
                            seckill.timer(toEnd);
                        }

                    } else {
                        console.log('result: ' + result);
                        $.alert('result: ' + result['msg']);
                    }
                }
            })


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
    //执行秒杀
    handlerSeckill: function (seckillId) {
        //获取秒杀地址,控制显示器,执行秒杀
        $.get(seckill.URL.exposer(seckillId), {}, function (result) {
            //在回调函数中执行交互流程
            if (result && result['code'] == 0) {
                var exposer = result['data'];
                if (exposer['exposed']) {
                    //开启秒杀
                    //获取秒杀地址
                    var md5 = exposer['md5'];
                    var killUrl = seckill.URL.execution(seckillId, md5);
                    console.log("killUrl: " + killUrl);
                    //绑定一次点击事件
                    $('#directorder').on('click', function () {
                        //检测是否存在token
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
                        //执行秒杀请求
                        //1.先禁用按钮
                        //2.发送秒杀请求执行秒杀
                        $.ajax({
                            method: "POST",
                            url: killUrl,
                            //todo 测试header
                            headers: {
                                Authorization: 'Bearer{eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjcxMywiaXNzIjoiaHR0cDpcL1wvYXBpLmlmZW5nZ3VvLmNvbTo4MVwvYXBpXC9hdXRoXC9sb2dpbiIsImlhdCI6MTQ2NjEzNzUyNiwiZXhwIjoxNDczOTEzNTI2LCJuYmYiOjE0NjYxMzc1MjYsImp0aSI6ImEyZDhiMzkxODY4MjU2NzQ5YWY5Yzk5NmQwNDYxYmIxIn0.wfvVmWE9zaEQw-23aM7oQiXiPWwzDubMcG5rsB2ns-4}'
                            },
                            statusCode: {
                                500: function () {
                                    $.alert("服务器错误！");
                                }
                            },
                            success: function (result) {
                                if (result && result['code'] == 0) {
                                    var killResult = result['data'];
                                    var state = killResult['state'];
                                    var stateInfo = killResult['stateInfo'];
                                    if (state == 0) {
                                        //跳转到秒杀成功界面
                                        window.location.href = "/";
                                    } else {
                                        //显示秒杀结果
                                        $.alert(stateInfo);
                                    }
                                } else {
                                    $.alert(result['msg']);
                                }
                            }
                        });

                    });

                } else {
                    //未开启秒杀(浏览器计时偏差)
                    var now = exposer['now'];
                    var start = exposer['start'];
                    var end = exposer['end'];
                    var toStart = (start - now) / 1000;
                    //进行浏览器时间校正
                    seckill.timer(toStart);
                    console.log("秒杀还未开始！");
                    // $("#directorder").on("click",function () {
                    //     $.alert("秒杀还未开始！","温馨提示");
                    // })
                }
            } else {
                $.alert(result['msg']);
            }
        });

    }

}


