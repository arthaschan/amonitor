<!DOCTYPE html>
<html>

<head>
    <title>ACDM DT后台</title>
    <script type="text/javascript" src="/jars/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/jars/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/jars/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/js/monitor.js"></script>
    <link rel="stylesheet" type="text/css" href="/jars/easyui/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="/jars/easyui/themes/icon.css" />


    <style>
        .showItem {
            width: 210px;
            height: 150px;
            background: #f8f6ed;
            font-size: 25px;
            font-weight: bold;
            text-align: center;
            border: 0px solid #686868;
            margin: 35px;
            float: left;
        }

        .showContent {
            position: relative;
            top: 50px;
            color: #727272;
        }

        .showItemName {
            position: relative;
            top: 100px;
            color: #929292;
            font-size: 20px;
            font-weight: bold;
        }
    </style>
</head>

<body>
<div class="allItem">

    <div id="produceMsg" class="showItem">
        <span id="produceMsgContent" class="showContent">${sendTime}</span>
        <br>
        <span class="showItemName">AMQ最新发消息的时间</span>
    </div>

    <div id="receiveMsg" class="showItem">
        <span id="receiveMsgContent" class="showContent">${receiveTime}</span>
        <br>
        <span class="showItemName">AMQ最新收消息的时间</span>
    </div>

    <div id="sendAtrs" class="showItem">
        <span id="sendAtrsContent" class="showContent">${sendAtrs}</span>
        <br>
        <span class="showItemName">向ATRS最新发消息时间</span>
    </div>
</div>
<div style="margin: 0 auto; width: 95%; height: 800px;" id="showFailRecord"></div>
</body>

</html>