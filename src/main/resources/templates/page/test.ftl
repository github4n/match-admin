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
<div id="win" style="width: 50%;height:450px;"></div>
<div id="draw" style="width: 50%;height:450px;"></div>
<div id="lose" style="width: 50%;height:450px;"></div>
</body>
<script type="text/javascript">
    var win = echarts.init(document.getElementById('win'));
    var winOption = ${winOption};
    win.setOption(winOption);

    var draw = echarts.init(document.getElementById('draw'));
    var drawOption = ${drawOption};
    draw.setOption(drawOption);

    var lose = echarts.init(document.getElementById('lose'));
    var loseOption = ${loseOption};
    lose.setOption(loseOption);
</script>
</html>