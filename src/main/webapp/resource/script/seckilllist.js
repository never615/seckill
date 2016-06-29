/**
 * Created by tianbaolin on 2016/6/28.
 */
$(document).ready(function () {
    //初始化布局
    (function () {
        var width = window.innerWidth;
        console.log(width);
        $("html").css("width",width+"px");
        $(".weui_cell_content").css("width",width-130+'px');
    })()
})