<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>后台管理</title>
    <link rel="stylesheet" href="${request.contextPath}/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${request.contextPath}/plugins/build/css/app.css" media="all">
    <link rel="stylesheet" href="${request.contextPath}/plugins/build/css/themes/red.css" media="all">
	<script src="${request.contextPath}/plugins/layui/layui.js"></script>
</head>

<body class="kit-theme">
    <div class="layui-layout layui-layout-admin kit-layout-admin">
        <div class="layui-header">
            <div class="layui-logo">后台管理</div>
            <ul class="layui-nav layui-layout-right kit-nav">
                <li class="layui-nav-item">
                    <a href="javascript:;">
                        <i class="layui-icon">&#xe612;</i>Van
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">基本资料</a></dd>
                        <dd><a href="javascript:;">安全设置</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="javascript:;">注销</a></li>
            </ul>
        </div>

        <div class="layui-side layui-bg-black kit-side">
            <div class="layui-side-scroll">
                <div class="kit-side-fold"><i class="layui-icon">&#xe647;</i></div>
                <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
                <ul class="layui-nav layui-nav-tree" lay-filter="kitNavbar" kit-navbar>
                    
                    <li class="layui-nav-item">
                        <a href="javascript:;" kit-target data-options="{url:'/admin/users',icon:'&#xe613;',title:'人员列表',id:'1'}" data-title="人员列表" kit-loader><span><i class="layui-icon">&#xe613;</i> 人员列表</span></a>
                    </li>
                    <li class="layui-nav-item">
                        <a href="javascript:;" kit-target data-options="{url:'/admin/articles',icon:'&#xe705;',title:'文章列表',id:'2'}" data-title="文章列表" kit-loader><i class="layui-icon">&#xe705;</i><span> 文章列表</span></a>
                    </li>
                    <li class="layui-nav-item">
                        <a href="javascript:;" kit-target data-options="{url:'/admin/consult',icon:'&#xe611;',title:'咨询列表',id:'3'}" data-title="咨询列表" kit-loader><i class="layui-icon">&#xe611;</i><span> 咨询列表</span></a>
                    </li>
                    <li class="layui-nav-item">
                        <a href="javascript:;" kit-target data-options="{url:'/admin/activity',icon:'&#xe62a;',title:'咨询列表',id:'4'}" data-title="活动列表" kit-loader><i class="layui-icon">&#xe62a;</i><span> 活动列表</span></a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="layui-body" id="container">
            <!-- 内容主体区域 -->
            <div style="padding: 15px;">主体内容加载中,请稍等...</div>
        </div>

        <div class="layui-footer">
            <!-- 底部固定区域 -->
            2018 &copy;
            <a href="http://www.blooddonation.org/">www.blooddonation.org/</a> TC license

        </div>
    </div>
    
    
    <script>
        var message;
        layui.config({
            base: '${request.contextPath}/plugins/build/js/'
        }).use(['app', 'message'], function() {
            var app = layui.app,
                $ = layui.jquery,
                layer = layui.layer;
            //将message设置为全局以便子页面调用
            message = layui.message;
            //主入口
            app.set({
                type: 'iframe'
            }).init();
        });
    </script>
</body>

</html>