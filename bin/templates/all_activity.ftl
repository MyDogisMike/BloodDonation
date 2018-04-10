<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>无偿献血</title>
		<#include "headInclude.ftl"/>
		<script type="text/javascript" src="${request.contextPath}/site/js/all_activity.js" ></script>
		<link rel="stylesheet" href="${request.contextPath}/site/css/all_articles.css" />
		<script type="text/javascript" src="${request.contextPath}/site/js/page.js" ></script>
		<link rel="stylesheet" href="${request.contextPath}/site/css/page.css" />
	</head>
	<body>
		<#include "header.ftl"/>
		<div id="all_articles_container">
			<div id="all_articles_center">
				<h2 class="user_position">
					当前位置：<a href="/">首页</a>>>活动列表
				</h2>
				<div id="article_list">
					<ul id="list_ul">
<!-- 						<li> -->
<!-- 							<span>2018-04-02</span> -->
<!-- 							<a href="/show_content.php?id=55114" target="_blank"><font style="font-size:14px">株洲市中心血站荣获“湖南省临床检验室间质量评价活动先进单位”</font></a> -->
<!-- 						</li> -->
<!-- 						<li> -->
<!-- 							<span>2018-04-02</span> -->
<!-- 							<a href="/show_content.php?id=55114" target="_blank"><font style="">株洲市中心血站荣获“湖南省临床检验室间质量评价活动先进单位”</font></a> -->
<!-- 						</li> -->
					</ul>
				</div>
			</div>
			<div class="list_page">
				<div class="page_fy">
					<span class="homePage" onclick="showPage(1)">首页</span>
					<span id="prevPage" class="homePage">上一页</span>
					<div id="middlePage" style="display: inline;">
<!-- 					... -->
<!-- 					<a class="page" href="javascript:void(0);" onclick="showPage(2)">2</a> -->
<!-- 					<a class="page" href="javascript:void(0);" onclick="showPage(3)">3</a> -->
<!-- 					<a class="page" href="javascript:void(0);" onclick="showPage(4)">4</a> -->
<!-- 					<a class="page focus" href="javascript:void(0);" onclick="showPage(5)">5</a> -->
<!-- 					<a class="page" href="javascript:void(0);" onclick="showPage(6)">6</a> -->
<!-- 					<a class="page" href="javascript:void(0);" onclick="showPage(7)">7</a> -->
<!-- 					<a class="page" href="javascript:void(0);" onclick="showPage(8)">8</a> -->
<!-- 					... -->
					</div>
					<span id="nextPage" class="endPage">下一页</span>
					<span class="endPage" href="javascript:void(0);">尾页</span><span>转到<select id="pageSelect" style="width:40px;"></select>
					共<span id="total"></span>,当前第<span id="nowPage">1</span>/<span id="allPage"></span>
					
				</div>
			</div>
		</div>
		<#include "footer.ftl"/>
	</body>
</html>