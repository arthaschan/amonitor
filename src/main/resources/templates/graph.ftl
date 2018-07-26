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
                symbolSize: 1,
                roam: true,
                label: {
                    normal: {
                        show: true
                    }
                },
                edgeSymbol: ['circle', 'arrow'],
                edgeSymbolSize: [1, 5],
                edgeLabel: {
                    normal: {
                        textStyle: {
                            fontSize: 2
                        }
                    }
                },
                data: [{
                    name: '节点1',
                    x: 0,
                    y: 0
                }, {
                    name: '节点2',
                    x: 100,
                    y: 0
                }, {
                    name: '节点3',
                    x: 200,
                    y: 0
                }, {
                    name: '节点4',
                    x: 200,
                    y: 100
                }, {
                    name: '节点5',
                    x: 300,
                    y: 100
                }
                    , {
                        name: '节点6',
                        x: 300,
                        y: 200
                    }  , {
                        name: '节点7',
                        x: 400,
                        y: 200
                    }
                ],
                // links: [],
                links: [  {
                    source: '节点1',
                    target: '节点2'
                }, {
                    source: '节点2',
                    target: '节点3'
                }, {
                    source: '节点3',
                    target: '节点4'
                }, {
                    source: '节点4',
                    target: '节点5'
                }, {
                    source: '节点5',
                    target: '节点6'
                }, {
                    source: '节点6',
                    target: '节点7'
                }
                ],
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