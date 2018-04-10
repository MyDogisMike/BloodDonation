<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>无偿献血</title>
		<#include "headInclude.ftl"/>
		<script type="text/javascript" src="${request.contextPath}/site/js/show_article.js" ></script>
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
					<h3><input type="hidden" id="articleId" value="${articleId?substring(1) }"/></h3>
					<h1 id="article_title"></h1>
					<p id="publish_info">
						发布时间：<span id="release_time"></span>&nbsp;&nbsp;&nbsp;
						发布人：<span id="publisher"></span>&nbsp;&nbsp;&nbsp;
						浏览量：<span id="view"></span>&nbsp;&nbsp;&nbsp;
					</p>
					<div id="article_content">
					</div>
				</div>
			</div>
		</div>
		<div id="comment_div">
				<div id="comment_list">
					<div class="comment_title">
						&nbsp;&nbsp;
						<b style="font-size:14px; color:#FFF">网友回复</b>
						<font style="font-size:12px; color:#ffc9cc">大家对该文章的想法。</font>
					</div>
					<div id="list">
<!-- 						<div class="comment_item"> -->
<!-- 							<p class="comment_info"> -->
<!-- 								<span class="comment_user">评论人</span> -->
<!-- 								<span class="comment_time">2018-02-02 12:33:39</span> -->
<!-- 							</p> -->
<!-- 							<div class="user_comment"> -->
<!-- 								这里是用户的评论 -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="comment_item"> -->
<!-- 							<p class="comment_info"> -->
<!-- 								<span class="comment_user">评论人</span> -->
<!-- 								<span class="comment_time">2018-02-02 12:33:39</span> -->
<!-- 							</p> -->
<!-- 							<div class="user_comment"> -->
<!-- 								这里是用户的评论 -->
<!-- 							</div> -->
<!-- 						</div> -->
					</div>
				</div>
				<div id="add_comment">
					<div class="comment_title">
						&nbsp;&nbsp;
						<b style="font-size:14px; color:#FFF">发表评论</b>
						<font style="font-size:12px; color:#ffc9cc">请发表您的想法信息并提交。</font>
					</div>
					<div id="comment_content">
						<span style="vertical-align: middle;margin-right: 40px;">评论内容：</span>
						<textarea id="comment" style="vertical-align: middle;height:100px; width:500px;"></textarea>
						<p style="margin-left: 120px;">
							<a href="javascript:void(0)" id="submit_comment"><img src="../site/img/tj.jpg" width="81" height="28"></a>
						</p>
					</div>
				</div>
			</div>
		<#include "footer.ftl"/>
	</body>
</html>
