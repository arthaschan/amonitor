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
    var dataAll = [
        [
            [0.0, 0],
            [10.0, 0],
            [20.0, 10],
            [30.0,10],
            [30.0, 20],
            [40.0, 20],
            [6.0, 7.24],
            [4.0, 4.26],
            [12.0, 10.84],
            [7.0, 4.82],
            [5.0, 5.68]
        ],
        [
            [0.0, 10],
            [10.0, 10],
            [20.0, 0],
            [20, 10],
            [11.0, 9.26],
            [14.0, 8.10],
            [6.0, 6.13],
            [4.0, 3.10],
            [12.0, 9.13],
            [7.0, 7.26],
            [5.0, 4.74]
        ],
        [
            [40, 20],
            [30, 20],
            [30, 10],
            [20, 10],
            [20, 0],
            [10, 0],
            [0, 0],
            [4.0, 5.39],
            [12.0, 8.15],
            [7.0, 6.42],
            [5.0, 5.73]
        ],
        [
            [8.0, 6.58],
            [8.0, 5.76],
            [8.0, 7.71],
            [8.0, 8.84],
            [8.0, 8.47],
            [8.0, 7.04],
            [8.0, 5.25],
            [19.0, 12.50],
            [8.0, 5.56],
            [8.0, 7.91],
            [8.0, 6.89]
        ]
    ];

    var markLineOpt = {
        animation: false,
        label: {
            normal: {
                formatter: 'y = 0.5 * x + 3',
                textStyle: {
                    align: 'right'
                }
            }
        },
        lineStyle: {
            normal: {
                type: 'solid'
            }
        },
        tooltip: {
            formatter: 'y = 0.5 * x + 3'
        },
        data: [[{
            coord: [0, 3],
            symbol: 'none'
        }, {
            coord: [20, 13],
            symbol: 'none'
        }]]
    };

    option = {
        title: {
            text: 'Anscombe\'s quartet',
            x: 'center',
            y: 0
        },
        grid: [
            {x: '7%', y: '7%', width: '38%', height: '38%'},
            {x2: '7%', y: '7%', width: '38%', height: '38%'},
            {x: '7%', y2: '7%', width: '38%', height: '38%'},
            {x2: '7%', y2: '7%', width: '38%', height: '38%'}
        ],
        tooltip: {
            formatter: 'Group {a}: ({c})'
        },
        xAxis: [
            {gridIndex: 0, min: 0, max: 20},
            {gridIndex: 1, min: 0, max: 20},
            {gridIndex: 2, min: 0, max: 20},
            {gridIndex: 3, min: 0, max: 20}
        ],
        yAxis: [
            {gridIndex: 0, min: 0, max: 15},
            {gridIndex: 1, min: 0, max: 15},
            {gridIndex: 2, min: 0, max: 15},
            {gridIndex: 3, min: 0, max: 15}
        ],
        series: [
            {
                name: 'I',
                type: 'scatter',
                xAxisIndex: 0,
                yAxisIndex: 0,
                data: dataAll[0],
                markLine: markLineOpt
            },
            {
                name: 'II',
                type: 'scatter',
                xAxisIndex: 1,
                yAxisIndex: 1,
                data: dataAll[1],
                markLine: markLineOpt
            },
            {
                name: 'III',
                type: 'scatter',
                xAxisIndex: 2,
                yAxisIndex: 2,
                data: dataAll[2],
                markLine: markLineOpt
            },
            {
                name: 'IV',
                type: 'scatter',
                xAxisIndex: 3,
                yAxisIndex: 3,
                data: dataAll[3],
                markLine: markLineOpt
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