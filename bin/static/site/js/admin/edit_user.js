$(function(){
	
	var userId = $("#userId").val().split('\\')[1];
	if (userId == null){
		alertMessage("提示", "获取数据失败，请稍后再试", function() {});
		return;
	}
	$.ajax({
		type: "GET",
		url: "/admin/getOneUser?id="+userId,
		dataType: "JSON",
		success: function(result){
			if(result.id == null){
				alertMessage("提示", "获取数据失败，请稍后再试", function() {});
				return;
			}
			$("#name").val(result.name);
			$("#nickname").val(result.nickname);
			$("#phoneNumber").val(result.phoneNumber);
			$("#birthday").val(result.birthday);
			if(result.gender == 0){
				$("#secret").attr("checked","checked");
				$("#secret").prev().addClass("layui-form-radioed");
			}else if (result.gender == 1){
				$("#man").attr("checked","checked");
				$("#man").prev().addClass("layui-form-radioed");
			}else{
				$("#woman").attr("checked","checked");
				$("#woman").prev().addClass("layui-form-radioed");
			}
			var address = result.address.split("省");
			var province = address[0];
			address = address[1].split("市");
			var city = address[0]+"市";
			var area = address[1];
			addressInit("cmbProvince", "cmbCity", "cmbArea", province, city, area);
			$("#bloodType").val(result.bloodType);
			if(result.type == 2){
				$("#normalUser").attr("checked","checked");
				$("#normalUser").prev().addClass("layui-form-radioed");
			}else if(result.type == 1){
				$("#areaAdmin").attr("checked","checked");
				$("#areaAdmin").prev().addClass("layui-form-radioed");
			}
			$("#status").val(result.status);
		},
		error: function(data){
			alertMessage("提示", "获取数据失败，请稍后再试", function() {});
			return;
		}
	});
	//点击单选div，因为使用layUI默认的form样式（使form中的radio和select等加上样式）会使自己写的城市三级联动失效（因为select的结构被改变了）所以就模仿layui的样式自己实现功能
	$(".layui-form-radio").click(function(){
		$(this).siblings().removeClass("layui-form-radioed");
		$(this).addClass("layui-form-radioed");
		$(this).next().attr("checked","checked");
	});
	
	$("#admin_edit_user_btn").click(function(){
		
		var address = $("#cmbProvince").val()+"省"+$("#cmbCity").val()+$("#cmbArea").val();
		$.ajax({
			type: "POST",
			url: "/admin/editOneUser?address="+address+"&id="+userId,
			data: $("#admin_edit_user_form").serialize(),
			dataType: "TEXT",
			success: function(result){
				alertMessage("提示", result, function() {});
				$("button[type='reset']").click();
			}
		});
	});
});