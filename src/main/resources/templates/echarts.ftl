<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>ECharts</title>
    <!-- 引入 echarts.js -->
    <script src="/jars/echarts.js"></script>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>
<div>
    <font>
    <#list userList as item>
    ${item!}<br />
    </#list>
    </font>
</div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    option = {
        title: {
            text: 'Graph 简单示例'
        },
        tooltip: {},
        animationDurationUpdate: 1500,
        animationEasingUpdate: 'quinticInOut',
        series : [
            {
                type: 'graph',
                layout: 'none',
                symbolSize: 50,
                roam: true,
                label: {
                    normal: {
                        show: true
                    }
                },
                edgeSymbol: ['circle', 'arrow'],
                edgeSymbolSize: [4, 10],
                edgeLabel: {
                    normal: {
                        textStyle: {
                            fontSize: 20
                        }
                    }
                },
                data: [{
                    name: '节点1',
                    x: 300,
                    y: 300
                }, {
                    name: '节点2',
                    x: 800,
                    y: 300
                }, {
                    name: '节点3',
                    x: 550,
                    y: 100
                }, {
                    name: '节点4',
                    x: 550,
                    y: 500
                }],
                // links: [],
                links: [{
                    source: 0,
                    target: 1,
                    symbolSize: [5, 20],
                    label: {
                        normal: {
                            show: true
                        }
                    },
                    lineStyle: {
                        normal: {
                            width: 5,
                            curveness: 0.2
                        }
                    }
                }, {
                    source: '节点2',
                    target: '节点1',
                    label: {
                        normal: {
                            show: true
                        }
                    },
                    lineStyle: {
                        normal: { curveness: 0.2 }
                    }
                }, {
                    source: '节点1',
                    target: '节点3'
                }, {
                    source: '节点2',
                    target: '节点3'
                }, {
                    source: '节点2',
                    target: '节点4'
                }, {
                    source: '节点1',
                    target: '节点4'
                }],
                lineStyle: {
                    normal: {
                        opacity: 0.9,
                        width: 2,
                        curveness: 0
                    }
                }
            }
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    myChart.on('click', function (params) {
        window.open('https://www.baidu.com/s?wd=' + encodeURIComponent(params.name));
    });
</script>
</body>
</html>