$(function(){
	//时间
	function current(){ 
		var d=new Date(),str=''; 
		str +=d.getFullYear()+'年'; //获取当前年份 
		str +=d.getMonth()+1+'月'; //获取当前月份（0——11） 
		str +=d.getDate()+'日'; 
		str +=d.getHours()+'时'; 
		str +=d.getMinutes()+'分'; 
		str +=d.getSeconds()+'秒'; 
		return str; 
		} 
		setInterval(function(){$("#header_time").html(current)},1000); 
	$("#login_password").keypress(function(e){
		// 回车键事件  
	   if(e.which == 13) {  
		   $("#login_password").blur();
		   login();
	   }  
	});
	$("#login_btn").click(function(){
		login();
	});
});

function login(){
	if($("#login_name").val() == ""){
		alertMessage("提示", "请输入账号！", function() {$("#login_name").focus();});
		return;
	}
	if($("#login_password").val() == ""){
		alertMessage("提示", "请输入密码！", function() {$("#login_password").focus();});
		return;
	}
	//根据外部的IP接口获取IP值，如果没获取到则传null，后台根据是否有值进行相应的操作
	var address = $("#ip").html().split("\'");
	if(address.length > 3){
		address = address[3];
		address = address.split(" ")[0];
	}else{
		address = null;
	}
	$.ajax({
		type : "post",
		url : "/login/in?address="+address,
		data : $("#login_form").serialize(),
		dataType : "text",
		success : function(data) {
			if(data == "success"){
				location.href = '/user/info';
			}else{
				alertMessage("提示", data, function() {$("#login_password").focus();});
			}
		},
		error : function() {
			alertMessage("提示", "未知错误！", function() {});
			return;
		}
	});
}