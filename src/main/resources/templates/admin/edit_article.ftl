<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<#include "headInclude.ftl"/>
		<link rel="stylesheet" href="${request.contextPath}/plugins/layui/css/layui.css" media="all">
    	<link rel="stylesheet" href="${request.contextPath}/plugins/build/css/app.css" media="all">
    	<link rel="stylesheet" href="${request.contextPath}/plugins/build/css/themes/red.css" media="all">
    	<script type="text/javascript" src="${request.contextPath}/site/js/Jsaddress.js" ></script>
    	<script type="text/javascript" src="${request.contextPath}/plugins/layui/layui.js"></script>
    	<script type="text/javascript" src="${request.contextPath}/site/js/admin/edit_article.js" ></script>
		
	</head>
	<body>
		<input type="hidden" id="articleId" name="id" value="${editArticleId}" />
		<form class="layui-form" id="edit_article_form" lay-filter="form-edit">
            
            <div class="layui-form-item">
                <label class="layui-form-label">标题</label>
                <div class="layui-input-inline">
                    <input type="text" id="title" name="title" required lay-verify="required" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">发布人</label>
                <div class="layui-input-inline">
                    <input type="text" id="publisher" name="publisher" class="layui-input" readonly="readonly">
                </div>
                <div class="layui-form-mid layui-word-aux"></div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">发布地区</label>
                <div class="layui-input-inline">
                    <input type="text" id="publishingLocation" name="publishingLocation"  class="layui-input" readonly="readonly">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">热度</label>
                <div class="layui-input-inline">
                    <input type="text" id="pageView" name="pageView" required lay-verify="required" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux"></div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">日期</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="releaseDate" name="releaseDate" readonly="readonly">
                </div>
                <div class="layui-form-mid layui-word-aux"></div>
            </div>
            
            
            <div class="layui-form-item">
                <label class="layui-form-label">状态</label>
                <div class="layui-input-block">
			      <input type="radio" id="noDisplay" name="status" value="不展示" title="不展示">
			      <input type="radio" id="display" name="status" value="展示" title="展示" checked>
			    </div>
            </div>
           
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button type="button" class="layui-btn" id="edit_article_btn">立即提交</button>
                    
                </div>
            </div>
        </form>
	</body>
<script>
layui.use(['form'], function(){
  var laydate = layui.laydate,
      form = layui.form;
  
});
</script>
</html>
