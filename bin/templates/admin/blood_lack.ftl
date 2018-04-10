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
    	<script type="text/javascript" src="${request.contextPath}/plugins/JQuery/jquery-1.12.4.min.js" ></script>
    	<script type="text/javascript" src="${request.contextPath}/plugins/layui/layui.js"></script>
    	<script type="text/javascript" src="${request.contextPath}/site/js/admin/blood_lack.js" ></script>
		
	</head>
	<body>
		<form class="layui-form" id="admin_blood_lack_form" lay-filter="form-edit">
            
            <div class="layui-form-item">
                <label class="layui-form-label">城市</label>
                <div style="margin-left: -10px;" class="layui-input-block">
                	<div class="layui-inline"><select style="display:inline" id="cmbProvince" name="cmbProvince" class="layui-input layui-unselect"></select></div>
                    <div class="layui-inline"><select style="display:inline" id="cmbCity" name="cmbCity" class="layui-input layui-unselect"></select></div>
                    <div class="layui-inline"><select style="display:none" id="cmbArea" name="cmbArea" class="layui-input layui-unselect"></select></div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">血型</label>
                <div style="margin-left: -10px;" class="layui-input-block">
                	<div class="layui-inline">
                		<input type="checkbox" value="0" name="bloodType" title="O型">
                		<div class="layui-unselect layui-form-checkbox" lay-skin><span>O型血</span><i class="layui-icon"></i></div>
      					<input type="checkbox" value="1" name="bloodType" title="A型">
      					<div class="layui-unselect layui-form-checkbox" lay-skin><span>A型血</span><i class="layui-icon"></i></div>
      					<input type="checkbox" value="2" name="bloodType" title="B型">
      					<div class="layui-unselect layui-form-checkbox" lay-skin><span>B型血</span><i class="layui-icon"></i></div>
      					<input type="checkbox" value="3" name="bloodType" title="AB型">
      					<div class="layui-unselect layui-form-checkbox" lay-skin><span>AB型血</span><i class="layui-icon"></i></div>
      					<input type="checkbox" value="4" name="bloodType" title="稀有血型">
                		<div class="layui-unselect layui-form-checkbox" lay-skin><span>稀有型血</span><i class="layui-icon"></i></div>
                	</div>
                      
                </div>
            </div>
           
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button type="button" class="layui-btn" id="admin_blood_lack_btn">立即提交</button>
                </div>
            </div>
        </form>
	</body>
<script>
layui.use(['laydate'], function(){
  var laydate = layui.laydate;
  
  //执行一个laydate实例
  laydate.render({
    elem: '#birthday' //指定元素
  });
});
</script>
</html>
