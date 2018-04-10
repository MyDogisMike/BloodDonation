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
    	<script type="text/javascript" src="${request.contextPath}/site/js/admin/edit_user.js" ></script>
		
	</head>
	<body>
		<input type="hidden" id="userId" name="id" value="${editUserId}" />
		<form class="layui-form" id="admin_edit_user_form" lay-filter="form-edit">
            
            <div class="layui-form-item">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-inline">
                    <input type="text" id="name" name="name" required lay-verify="required" autocomplete="off" class="layui-input" readonly="readonly">
                </div>
                <div class="layui-form-mid layui-word-aux">用户名不能修改</div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">昵称</label>
                <div class="layui-input-inline">
                    <input type="text" id="nickname" name="nickname" required lay-verify="required" placeholder="请输入昵称" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux"></div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">密码框</label>
                <div class="layui-input-inline">
                    <input type="password" name="password" required lay-verify="required" placeholder="请输入新密码" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux">不输入则原密码不会修改</div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">手机号码</label>
                <div class="layui-input-inline">
                    <input type="text" id="phoneNumber" name="phoneNumber" required lay-verify="required" placeholder="请输入手机号码" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux"></div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">日期</label>
                <div class="layui-input-inline">
                    <input type="text" id="birthday" class="layui-input" id="birthday" name="birthday">
                </div>
                <div class="layui-form-mid layui-word-aux"></div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">性别</label>
                <div style="margin-left: -10px;" class="layui-input-block">
                	<div class="layui-unselect layui-form-radio">
                		<i class="layui-anim layui-icon"></i>
                		<span>保密</span>
                	</div>
                	<input type="radio" id="secret" name="gender" value="0" title="保密" checked/>
                	<div class="layui-unselect layui-form-radio">
                		<i class="layui-anim layui-icon"></i>
                		<span>男</span>
                	</div>
                	<input type="radio" id="man" name="gender" value="1" title="男" />
                	<div class="layui-unselect layui-form-radio">
                		<i class="layui-anim layui-icon"></i>
                		<span>女</span>
                	</div>
                    <input type="radio" id="woman" name="gender" value="2" title="女" />
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">城市</label>
                <div style="margin-left: -10px;" class="layui-input-block">
                	<div class="layui-inline"><select style="display:inline" id="cmbProvince" name="cmbProvince" class="layui-input layui-unselect"></select></div>
                    <div class="layui-inline"><select style="display:inline" id="cmbCity" name="cmbCity" class="layui-input layui-unselect"></select></div>
                    <div class="layui-inline"><select style="display:inline" id="cmbArea" name="cmbArea" class="layui-input layui-unselect"></select></div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">血型</label>
                <div style="margin-left: -10px;" class="layui-input-block">
                	<div class="layui-inline">
                		<select style="display:inline" class="layui-input layui-unselect" id="bloodType" name="bloodType">
	                    	<option value="5">保密</option>
	                    	<option value="0">O型血</option>
	                    	<option value="1">A型血</option>
	                    	<option value="2">B型血</option>
	                    	<option value="3">AB型血</option>
	                    	<option value="4">稀有血型</option>
                    	</select>
                	</div>
                      
                </div>
            </div>
            
            <div class="layui-form-item">
                <label class="layui-form-label">身份</label>
                 <div style="margin-left: -10px;" class="layui-input-block">
                 	<div class="layui-unselect layui-form-radio">
                		<i class="layui-anim layui-icon"></i>
                		<span>普通用户</span>
                	</div>
                    <input type="radio" id="normalUser" name="type" value="2" title="普通用户" checked/>
                    <div class="layui-unselect layui-form-radio">
                		<i class="layui-anim layui-icon"></i>
                		<span>区域管理员</span>
                	</div>
                    <input type="radio" id="areaAdmin" name="type" value="1" title="区域管理员" />
                </div>
            </div>
            
            <div class="layui-form-item">
                <label class="layui-form-label">状态</label>
                 <div style="margin-left: -10px;" class="layui-input-block">
                 	<div class="layui-inline">
	                    <select style="display:inline" class="layui-input layui-unselect" id="status" name="status">
		                    	<option value="0">停用</option>
		                    	<option value="1">可用</option>
		                    	<option value="2">未激活</option>
		                    	<option value="3">已激活</option>
	                    	</select>
	                 </div>
                </div>
            </div>
           
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button type="button" class="layui-btn" id="admin_edit_user_btn">立即提交</button>
                    
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
