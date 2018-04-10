<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>无偿献血</title>
		<#include "headInclude.ftl"/>
		<script type="text/javascript" src="${request.contextPath}/site/js/show_activity.js" ></script>
		<link rel="stylesheet" href="${request.contextPath}/site/css/show_article.css" />
	</head>
	<body>
		<#include "header.ftl"/>
		<div id="show_article_container">
			<div id="show_article_center">
				<h2 class="user_position">
					当前位置：<a href="/">首页</a>>>文章浏览
				</h2>
				<div id="article_container">
					<h3><input type="hidden" id="activityId" value="${activityId?substring(1) }"/></h3>
					<h1 id="article_title"></h1>
					<p id="publish_info">
						发布时间：<span id="release_time"></span>&nbsp;&nbsp;&nbsp;
						发布人：<span id="publisher"></span>&nbsp;&nbsp;&nbsp;
						浏览量：<span id="view"></span>&nbsp;&nbsp;&nbsp;
					</p>
					<div id="article_content">
					</div>
				</div>
				<div></div>
			</div>
			<p>已参与人数：<span id="personCount" style="margin-right:100px;"></span><button id="in">点击参与</button><button id="out" style="display:none">退出活动</button></p>
		</div>
		
		<#include "footer.ftl"/>
	</body>
</html>
