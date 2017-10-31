<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>ECharts</title>
    <!-- 引入 echarts.js -->
    <script src="../static/echarts.min.js"></script>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 100%;height:900px;"></div>
</body>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option1 = {
        title: {
            text: '未来一周气温变化',
            subtext: '纯属虚构'
        },
        tooltip: {trigger: 'axis'},
        toolbox: {show: true,feature: {dataZoom: {yAxisIndex: 'none'},dataView: {readOnly: false},magicType: {type: ['line', 'bar']},restore: {},saveAsImage: {}}},
        yAxis: {type: 'value',axisLabel: {formatter: '{value}'}},

        legend: {data:['最高气温','最低气温']},
        xAxis:  {type: 'category',boundaryGap: false,data: ['周一','周二','周三','周四','周五','周六','周日']},
        series: [
            {
                name:'最高气温',
                type:'line',
                data:['', 11, 15, 13, 12, 13, 10]
            },
            {
                name:'最低气温',
                type:'line',
                data:[1, -2, 2, 5, 3, 2, 0]
            }
        ]
    };

    var option = ${option};

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>
</html>