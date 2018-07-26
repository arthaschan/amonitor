$(function() {

    setInterval(produceMsg, 10000);
    setInterval(receiveMsg, 10000);
    setInterval(sendAtrs, 10000);

    /**
     * 获取发送消息队列的时间
     */

    function produceMsg() {
        $.post("sendTime", {}, function(result) {
            if (result == null || result == "") {
                $("#produceMsgContent").text("暂时没有数据需要发送");
            } else {
                $("#produceMsgContent").text(result);
            }
            //  alert("send ok "+result);
        })
    };


    /**
     * 获取接收消息队列的时间
     */
    function receiveMsg() {
        $.post("receiveTime", {}, function(result) {
            if (result == null || result == "") {
                $("#receiveMsgContent").text("暂时没有收到任何数据");
            } else {
                $("#receiveMsgContent").text(result);
            }
        })
    };

    /**
     * sendAtrs
     */
    function sendAtrs() {
        $.post("sendAtrs", {}, function(result) {
            if (result == null || result == "") {
                $("#sendAtrsContent").text("暂时没有收到任何数据");
            } else {
                $("#sendAtrsContent").text(result);
            }
        })
    };

})