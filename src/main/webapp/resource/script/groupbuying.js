//存放主要交互逻辑的js代码
// javascript 模块化(package.类.方法)

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
            //已经登录
            //计时交互
            var startTime = params['startTime'];
            var endTime = params['endTime'];
            var groupbuyingId = params['groupbuyingId'];
            $.get(groupbuying.URL.now(), {}, function (result) {
                if (result && result['code'] == 0) {
                    var nowTime = result['data'];
                    //时间判断 计时交互
                    groupbuying.countDown(groupbuyingId, nowTime, startTime, endTime);
                } else {
                    console.log('result: ' + result);
                    alert('result: ' + result['msg']);
                }
            });
        }
    },

    //执行团购
    handlerGroupbuying: function (groupbuyingId, node) {
        //获取团购地址,控制显示器,执行团购
        node.hide().html('<button class="btn btn-primary btn-lg" id="buyingBtn">开始团购</button>');

        var buyingUrl = groupbuying.URL.execution(groupbuyingId);
        console.log("buyingUrl: " + buyingUrl);
        //绑定一次点击事件
        $('#buyingBtn').one('click', function () {
            //执行团购请求
            //1.先禁用按钮
            $(this).addClass('disabled');
            //2.发送团购请求执行团购


            $.ajax({
                method: "POST",
                url: buyingUrl,
                //todo 测试header
                headers: {
                    Authorization: 'Bearer{eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjcxMywiaXNzIjoiaHR0cDpcL1wvYXBpLmlmZW5nZ3VvLmNvbTo4MVwvYXBpXC9hdXRoXC9sb2dpbiIsImlhdCI6MTQ2NjEzNzUyNiwiZXhwIjoxNDczOTEzNTI2LCJuYmYiOjE0NjYxMzc1MjYsImp0aSI6ImEyZDhiMzkxODY4MjU2NzQ5YWY5Yzk5NmQwNDYxYmIxIn0.wfvVmWE9zaEQw-23aM7oQiXiPWwzDubMcG5rsB2ns-4}'
                },
                success: function (result) {
                    if (result && result['code'] == 0) {
                        var buyingResult = result['data'];
                        var state = buyingResult['state'];
                        var stateInfo = buyingResult['stateInfo'];
                        //显示团购结果
                        node.html('<span class="label label-success">' + stateInfo + '</span>');
                    } else {
                        alert('result: ' + result['msg']);
                    }
                }
            });
        });
        node.show();
    },

    //倒计时逻辑处理
    countDown: function (groupbuyingId, nowTime, startTime, endTime) {
        console.log(groupbuyingId + '_' + nowTime + '_' + startTime + '_' + endTime);
        var groupbuyingBox = $('#groupbuying-box');
        if (nowTime > endTime) {
            //团购结束
            groupbuyingBox.html('团购结束!');
        } else if (nowTime < startTime) {
            //团购未开始,计时事件绑定
            var buyingTime = new Date(startTime + 1000);//todo 防止时间偏移
            groupbuyingBox.countdown(buyingTime, function (event) {
                //时间格式
                var format = event.strftime('团购倒计时: %D天 %H时 %M分 %S秒 ');
                groupbuyingBox.html(format);
            }).on('finish.countdown', function () {
                //时间完成后回调事件
                //获取团购地址,控制现实逻辑,执行团购
                console.log('______fininsh.countdown');
                groupbuying.handlerGroupbuying(groupbuyingId, groupbuyingBox);
            });
        } else {
            //团购开始
            groupbuying.handlerGroupbuying(groupbuyingId, groupbuyingBox);
        }
    }
};