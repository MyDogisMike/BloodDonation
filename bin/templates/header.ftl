<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>我是头部</title>
		<script type="text/javascript" src="${request.contextPath}/site/js/header.js" ></script>
		<link rel="stylesheet" href="${request.contextPath}/site/css/header.css" />

	</head>

	<body>
		<nav class="navbar navbar-inverse navbar-default navbar-static-top">
			<div id="header_nav" class="container">
				<span>您好！现在是：<span id="header_time"></span></span>
				<form class="navbar-form navbar-right">
			        <div class="form-group">
			            <input type="text" placeholder="请输入关键字...">
			          
			        </div>
			        <button type="submit" class="btn-default">搜索</button>
		        </form>
		        <#if user?exists>   
        			<span style="padding:0px 80px;">欢迎&nbsp;&nbsp;<span style="color:red">${user.nickname }</span>&nbsp;&nbsp;进入无偿献血网</span>
        			<a href="/user/info" style="padding-right:80px;">个人中心</a>
        			<a href="/login/out">注销登录</a>
     			<#else >
					<form id="login_form" class="navbar-form navbar-right">
				        <div class="form-group">
				          <span>用户名：</span><input type="text" id="login_name" name="login_name">
				          <span>密码：</span><input type="password" id="login_password" name="login_password">
				        </div>
				        <button type="button" class="btn-default" id="login_btn">登录</button>
				        <span><a href="/reg">注册</a></span>
		        	</form>
     			</#if>
				
		        
			</div>
		</nav>
		<div id="header">
			<div id="header_content">
				<img src="../site/img/title2.jpg" />
				<ul>
					<li><a href="#">网站首页</a></li><li>|</li>
					<li><a href="#">网站首页</a></li><li>|</li>
					<li><a href="#">网站首页</a></li><li>|</li>
					<li><a href="#">网站首页</a></li><li>|</li>
					<li><a href="/map">奉献爱心</a></li><li>|</li>
					<li><a href="#">网站首页</a></li><li>|</li>
					<li><a href="#">网站首页</a></li><li>|</li>
					<li><a href="#">网站首页</a></li><li>|</li>
					<li><a href="#">网站首页</a></li><li>|</li>
					<li><a href="#">网站首页</a></li><li>|</li>
					<li><a href="#">网站首页</a></li><li>|</li>
					<li><a href="#">网站首页</a></li>
				</ul>
			</div>
			
		</div>
		<div id="ip" style="display:none">
			<script type="text/javascript" src="http://ip.chinaz.com/getip.aspx"></script>
		</div>
		
	</body>

</html>