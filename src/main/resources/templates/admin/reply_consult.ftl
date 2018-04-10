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
    	<script type="text/javascript" src="${request.contextPath}/site/js/admin/reply_consult.js" ></script>
		
	</head>
	<body>
		<input type="hidden" id="consultId" name="id" value="${replyConsultId}" />
		<form class="layui-form" id="reply_consult_form" lay-filter="form-edit">
            
            <div class="layui-form-item">
                <label class="layui-form-label">标题</label>
                <div class="layui-input-inline">
                    <input type="text" id="title" name="title" class="layui-input" readonly="readonly">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">内容</label>
                <div class="layui-input-inline">
                    <textarea id="content" name="content" required class="layui-input" readonly="readonly"></textarea>
                </div>
                <div class="layui-form-mid layui-word-aux"></div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">回复</label>
                <div class="layui-input-inline">
                    <textarea id="reply" name="reply" required class="layui-input"></textarea>
                </div>
                <div class="layui-form-mid layui-word-aux"></div>
            </div>
           
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button type="button" class="layui-btn" id="reply_consult_btn">立即提交</button>
                    
                </div>
            </div>
        </form>
	</body>
<script>
layui.use(['laydate','form'], function(){
  var laydate = layui.laydate,
      form = layui.form;
  
});
</script>
</html>
