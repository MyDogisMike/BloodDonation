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
    	<script type="text/javascript" src="${request.contextPath}/site/js/admin/edit_activity.js" ></script>
		
	</head>
	<body>
		<input type="hidden" id="activityId" name="id" value="${editActivityId}" />
		<form class="layui-form" id="edit_activity_form" lay-filter="form-edit">
            
            <div class="layui-form-item">
                <label class="layui-form-label">标题</label>
                <div class="layui-input-inline">
                    <input type="text" id="title" name="title" class="layui-input">
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
                <label class="layui-form-label">人数</label>
                <div class="layui-input-inline">
                    <input type="text" id="personCount" name="personCount" class="layui-input" readonly="readonly">
                </div>
                <div class="layui-form-mid layui-word-aux"></div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">日期</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="startDate" name="startDate" placeholder="起始日期">
                </div>
                <div class="layui-form-mid">-</div>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="endDate" name="endDate" placeholder="结束日期">
                </div>
                <div class="layui-form-mid layui-word-aux"></div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">状态</label>
                <div class="layui-input-block">
			      <input type="radio" id="prepare" name="status" value="准备中" title="准备中">
			      <input type="radio" id="progress" name="status" value="进行中" title="进行中">
			      <input type="radio" id="end" name="status" value="结束" title="结束">
			    </div>
            </div>
           
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button type="button" class="layui-btn" id="edit_activity_btn">立即提交</button>
                    
                </div>
            </div>
        </form>
	</body>
<script>
layui.use(['laydate','form'], function(){
  var laydate = layui.laydate,
      form = layui.form;
//执行一个laydate实例
  laydate.render({
    elem: '#startDate' //指定元素
  });
  laydate.render({
	    elem: '#endDate' //指定元素
  });
});
</script>
</html>
