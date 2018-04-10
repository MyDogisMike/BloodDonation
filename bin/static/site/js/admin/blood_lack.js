$(function(){
	//点击单选div，因为使用layUI默认的form样式（使form中的radio和select等加上样式）会使自己写的城市三级联动失效（因为select的结构被改变了）所以就模仿layui的样式自己实现功能
	$(".layui-form-checkbox").click(function(){
		if($(this).hasClass('layui-form-checked')){
			$(this).removeClass("layui-form-checked");
			$(this).prev("input").prop("checked",false);
		}else{
			$(this).addClass("layui-form-checked");
			$(this).prev("input").prop("checked",true);
		}
	});
	
	$.ajax({
		type: "POST",
		url: "/admin/getLoginUser",
		dataType: "json",
		success: function(result){
			var address = result.address.split("省");
			var province = address[0];
			address = address[1].split("市");
			var city = address[0]+"市";
			var area = address[1];
			addressInit("cmbProvince", "cmbCity", "cmbArea", province, city, area);
		}
	});
	
	$("#admin_blood_lack_btn").click(function(){
		if(!$(".layui-form-checkbox").hasClass('layui-form-checked')){
			alertMessage("提示", "请先选择血型", function() {});
			return;
		}
		var test = $("#admin_blood_lack_form").serialize();
		debugger;
		$.ajax({
			type: "POST",
			url: "/admin/sendEmails",
			data: $("#admin_blood_lack_form").serialize(),
			dataType: "TEXT",
			success: function(result){
				alertMessage("提示", result, function() {});
			}
		});
	});
});