<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="${request.contextPath}/plugins/layui/css/layui.css" media="all">
    	<link rel="stylesheet" href="${request.contextPath}/plugins/build/css/app.css" media="all">
    	<link rel="stylesheet" href="${request.contextPath}/plugins/build/css/themes/red.css" media="all">
    	<script type="text/javascript" src="${request.contextPath}/site/js/Jsaddress.js" ></script>
    	<script type="text/javascript" src="${request.contextPath}/plugins/JQuery/jquery-1.12.4.min.js" ></script>
    	<script type="text/javascript" src="${request.contextPath}/plugins/layui/layui.js"></script>
    	<script type="text/javascript" src="${request.contextPath}/site/js/admin/add_user.js" ></script>
		
	</head>
	<body>
		<form class="layui-form" id="admin_add_user_form" lay-filter="form-edit">
            <input type="hidden" name="id" />
            <div class="layui-form-item">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-inline">
                    <input type="text" id="name" name="name" required lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux"></div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">昵称</label>
                <div class="layui-input-inline">
                    <input type="text" name="nickname" required lay-verify="required" placeholder="请输入昵称" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux"></div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">密码框</label>
                <div class="layui-input-inline">
                    <input type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux"></div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">日期</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="birthday" name="birthday">
                </div>
                <div class="layui-form-mid layui-word-aux"></div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-block">
                	<input type="radio" name="gender" value="0" title="保密" checked/>
                    <input type="radio" name="gender" value="1" title="男" />
                    <input type="radio" name="gender" value="2" title="女" />
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">城市</label>
                <div class="layui-input-block">
                	<div class="layui-inline"><select  id="cmbProvince" name="cmbProvince"></select> </div>
                    <div class="layui-inline"><select class="layui-select" id="cmbCity" name="cmbCity"></select></div>
                    <div class="layui-inline"><select class="layui-select" id="cmbArea" name="cmbArea"></select></div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">血型</label>
                <div class="layui-input-block">
                	<div class="layui-inline">
                		<select class="layui-select" id="bloodType" name="bloodType">
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
                 <div class="layui-input-block">
                    <input type="radio" name="type" value="2" title="普通用户" checked/>
                    <input type="radio" name="type" value="1" title="区域管理员" />
                </div>
            </div>
           
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button type="button" class="layui-btn" id="admin_add_user_btn">立即提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </form>
	</body>
<script>
layui.use(['laydate', 'form'], function(){
  var laydate = layui.laydate,
  	  form = layui.form;
  
  //执行一个laydate实例
  laydate.render({
    elem: '#birthday' //指定元素
  });
});
</script>
</html>
