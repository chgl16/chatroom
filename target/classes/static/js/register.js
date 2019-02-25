// $(function ())是文档document加载完自动调用的函数
$(function () {
    /*
     获取form元素，调用其ajaxForm(...)方法
     内部的function(data)的data就是后台返回的数据
    */
    $("#root-form").ajaxForm(function (data) {
            console.log(data);
            if (data == false) {
                alert("用户已经注册了，正在跳转到登录..");
                window.location.href = "login";
            } else {
                alert("注册成功，进入首页..");
                // 存数据
                localStorage.setItem("username", $('input[type="text"]')[0].value);
                window.location.href = "index";
            }
        }
    );
});
