<!DOCTYPE html>
<html lang="zh-cn">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>控制面板</title>
    <link rel="stylesheet" href="${request.contextPath}/plugins/layui/css/layui.css" media="all">
    <style>
        .info-box {
            height: 85px;
            background-color: white;
            background-color: #ecf0f5;
        }
        
        .info-box .info-box-icon {
            border-top-left-radius: 2px;
            border-top-right-radius: 0;
            border-bottom-right-radius: 0;
            border-bottom-left-radius: 2px;
            display: block;
            float: left;
            height: 85px;
            width: 85px;
            text-align: center;
            font-size: 45px;
            line-height: 85px;
            background: rgba(0, 0, 0, 0.2);
        }
        
        .info-box .info-box-content {
            padding: 5px 10px;
            margin-left: 85px;
        }
        
        .info-box .info-box-content .info-box-text {
            display: block;
            font-size: 14px;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
            text-transform: uppercase;
        }
        
        .info-box .info-box-content .info-box-number {
            display: block;
            font-weight: bold;
            font-size: 18px;
        }
        
        .major {
            font-weight: 10px;
            color: #01AAED;
        }
        
        .main {
            margin-top: 25px;
        }
        
        .main .layui-row {
            margin: 10px 0;
        }
    </style>
</head>

<body>
    <div class="layui-fluid main">
        <div class="layui-row layui-col-space15">
            <div class="layui-col-md3">
                <div class="info-box">
                    <span class="info-box-icon" style="background-color:#00c0ef !important;color:white;"><i class="layui-icon" style="font-size: 40px;">&#xe613;</i></span>
                    <div class="info-box-content">
                        <span class="info-box-text">CPU Traffic</span>
                        <span class="info-box-number">90%</span>
                    </div>
                </div>
            </div>
            <div class="layui-col-md3">
                <div class="info-box">
                    <span class="info-box-icon" style="background-color:#dd4b39 !important;color:white;"><i class="layui-icon" style="font-size: 40px;">&#xe705;</i></span>
                    <div class="info-box-content">
                        <span class="info-box-text">Likes</span>
                        <span class="info-box-number">25,412</span>
                    </div>
                </div>
            </div>
            <div class="layui-col-md3">
                <div class="info-box">
                    <span class="info-box-icon" style="background-color:#00a65a !important;color:white;"><i class="layui-icon" style="font-size: 40px;">&#xe612;</i></span>
                    <div class="info-box-content">
                        <span class="info-box-text">Sales</span>
                        <span class="info-box-number">654</span>
                    </div>
                </div>
            </div>
            <div class="layui-col-md3">
                <div class="info-box">
                    <span class="info-box-icon" style="background-color:#f39c12 !important;color:white;"><i class="layui-icon" style="font-size: 40px;">&#xe611;</i></span>
                    <div class="info-box-content">
                        <span class="info-box-text">New Members</span>
                        <span class="info-box-number">85</span>
                    </div>
                </div>
            </div>
        </div>
        
    </div>
    
</body>

</html>