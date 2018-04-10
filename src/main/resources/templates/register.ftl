<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>无偿献血</title>
	<#include "headInclude.ftl"/>
	<script type="text/javascript" src="${request.contextPath}/site/js/register.js" ></script>
	<link rel="stylesheet" href="${request.contextPath}/site/css/register.css" />
</head>
<body>
	<#include "header.ftl"/>
	<div id="reg_container">
			<div id="reg_container_center">
				<h2 class="user_position">
					当前位置：<a href="/">首页</a>>>用户注册
				</h2>
				<div id="reg_form_container">
					<form id="reg_form" method="POST">
						<table  id="reg_table" cellpadding="0" cellspacing="0" width="100%" id="reg_tb">
							<tbody>
								<tr>
									<td style="width:20%;text-align:right">用户名：</td>
									<td style="width:80%"><input name="name" id="name" style="width:40%;padding:3px 0;" value=""><span class="hint" style="color:red;padding:0 8px;">*</span></td>
								</tr>
								<tr>
									<td style="width:20%;text-align:right">昵称：</td>
									<td style="width:80%"><input name="nickname" id="nickname" style="width:40%;padding:3px 0;" value=""><span class="hint" style="color:red;padding:0 8px;"></span></td>
								</tr>
								<tr>
									<td style="width:20%;text-align:right">登陆密码：</td>
									<td style="width:80%"><input type="password" name="password" id="password" style="width:40%;padding:3px 0;" value=""><span class="hint" style="color:red;padding:0 8px;">*</span></td>
								</tr>
								<tr>
									<td style="width:20%;text-align:right">确认密码：</td>
									<td style="width:80%"><input type="password" name="password2" id="password2" style="width:40%;padding:3px 0;" value=""><span class="hint" style="color:red;padding:0 8px;">*</span></td>
								</tr>
								<tr>
									<td style="width:20%;text-align:right">电子邮箱：</td>
									<td style="width:80%"><input name="email" id="email" style="width:40%;padding:3px 0;" value=""><span class="hint" style="color:red;padding:0 8px;">*</span></td>
								</tr>
							</tbody>
						</table>
						<div id="reg_btn_div">
							<input type="button" id="reg_btn" style="background-image:url('../site/img/reg_input.gif');width:110px;height:40px;" />
							<a href="#">已经注册！立即登录</a>
						</div>
					</form>
				</div>
			</div>
		</div>
		<#include "footer.ftl"/>
		<div id="loading"><img src="../site/img/loading.gif" alt="正在加载数据,请稍候..."/>正在加载数据,请稍候...</div>
</body>
</html>