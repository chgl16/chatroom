var sock = new SockJS("/endpointChat");
var stomp = Stomp.over(sock);
stomp.connect('guest', 'guest', function (frame) {
    stomp.subscribe("/user/notification", showGetMsg);
});


/**
 * 点击发送信息
 */
$('#chat-form').submit(function (e) {
    e.preventDefault();
    var text = $('input')[0].value;
    var receiver = $('input')[2].value;
    console.log("发送的内容: [" + text + "] || 要发送给的对象: [" + receiver + "]");
    showSendMsg(text);
    stomp.send("/chat", {}, JSON.stringify({"text": text, "receiver": receiver}));
});

/**
 * 显示发送的信息
 * @param message
 */
function showSendMsg(message) {
    // 这个API时间格式化函数别人写的是，这里用hh ...
    var now = new Date().Format("yyyy-MM-dd hh:mm:ss");
    // 这里显示还没有完善
    $('#receive')[0].innerHTML += '<span>'.concat(now).concat("<br> 我: ").concat(message).concat('</span><br><hr>');
    // 删除输入框内容
    $('input')[0].value = "";
}

/**
 * 显示收到的信息
 * @param message
 */
function showGetMsg(message) {
    console.log("收到服务器转发的的信息（别人发来的）:" + message);
    // 这个API时间格式化函数别人写的是，这里用hh ...
    var now = new Date().Format("yyyy-MM-dd hh:mm:ss");
    // 这里显示还没有完善(此外：异步回调的message是一个http数据包，数据在body)
    $('#receive')[0].innerHTML += '<span>'.concat(now).concat("<br>").concat(message.body).concat('</span><br><hr>');
}

// 对Date的扩展，将 Date 转化为指定格式的String   
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，   
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)   
// 例子：   
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423   
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18   
Date.prototype.Format = function(fmt)
{ //author: meizz   
    var o = {
        "M+" : this.getMonth()+1,                 //月份   
        "d+" : this.getDate(),                    //日   
        "h+" : this.getHours(),                   //小时   
        "m+" : this.getMinutes(),                 //分   
        "s+" : this.getSeconds(),                 //秒   
        "q+" : Math.floor((this.getMonth()+3)/3), //季度   
        "S"  : this.getMilliseconds()             //毫秒   
    };
    if(/(y+)/.test(fmt))
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("("+ k +")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
    return fmt;
}

