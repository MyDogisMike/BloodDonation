$(function(){
	//获取出生日期
	var birthday = $("#birthday").val();
	if(birthday != null && birthday != ""){
		birthday = $("#birthday").val().split("-");
		$("#birthdayYear").val(birthday[0]);
		$("#birthdayMonth").val(birthday[1]);
		$("#birthdayDay").val(birthday[2]);
	}
	//获取地址
	var address = $("#address").val().split("省");
	var province = address[0];
	
	address = address[1].split("市");
	var city = address[0]+"市";
	var area = address[1];
	addressInit("cmbProvince", "cmbCity", "cmbArea", province, city, area);
	//获取性别
	var gender = $("#gender").val();
	if(gender == 0){
		$("#secret").attr("checked","checked");
	}else if (gender == 1){
		$("#man").attr("checked","checked");
	}else{
		$("#woman").attr("checked","checked");
	}
	//获取血液类型
	var bloodType = $("#bloodTypeValue").val();
	$("#bloodType").val(bloodType);
	//用户信息保存按钮
	$("#user_update_btn").click(function(){
		var birthday = $("#birthdayYear").val()+"-"+$("#birthdayMonth").val()+"-"+$("#birthdayDay").val();
		var address = $("#cmbProvince").val()+"省"+$("#cmbCity").val()+$("#cmbArea").val();
		$.ajax({
			type: "POST",
			url: "/user/update",
			data: {
				birthday: birthday,
				address: address,
				gender: $("#form_div input[name='sex']:checked").val(),
				bloodType: $("#bloodType").val(),
				phoneNumber: $("#phoneNumber").val(),
				nickname: $("#nickname").val()
			},
			datatype: "TEXT",
			success: function(result){
				if(result == "success"){
					alertMessage("提示", "修改成功！", function() {location.href = '/user/info';});
				}else{
					alertMessage("提示", "修改失败，请稍后再试！", function() {});
				}
			},
			error : function() {
				alertMessage("提示", "未知错误！", function() {});
				return;
			}
		});
	});
	
	//左侧菜单项项点击
	$(".user_item").click(function() {
		$(this).parent().parent().find("a").removeClass("selected");
		$(this).addClass("selected");
		var formId = "#" + $(this).attr("id") + "_form";
		$(formId).siblings().hide();
		$(formId).show();
	});
	// 密码输入校验
	var passwordReg = /^(?=.*[0-9])(?=.*[a-zA-Z]).{8,16}$/;
	$("#oldPass,#newPass").on("keyup",function() {
				if ($(this).val() != "" && !passwordReg.test($(this).val())) {
					$(this).addClass('warn');
					$(this).siblings('span').text("密码8-16位，包含字母和数字");
				} else {
					$(this).removeClass('warn');
					$(this).siblings('span').text("");
				}
	});
	$("#confirmPass").on("keyup",function() {
			if ($("#confirmPass").val() != "" && $("#confirmPass").val() != $("#newPass").val()) {
				$(this).addClass('warn');
				$(this).siblings('span').text("两次密码不一致");
			} else {
				$(this).removeClass('warn');
				$(this).siblings('span').text("");
			}
	});
	//修改密码
	$("#modify_pass_btn").click(function(){
		var flag = false;
		$("#modify_pass_form input").each(function(n) {
			if ($(this).val() == "") {
				alertMessage("提示", "请将信息填写完整！", function() {});
				flag = true;
				return false;
			}
		});
		if(flag){
			return;
		}
		if ($(".hint").text() != "") {
			alertMessage("提示", "请按要求填写信息", function() {});
			return;
		}
		$.ajax({
			type: "POST",
			url: "/user/modifyPass",
			data: {
				oldPass : $("#oldPass").val(),
				newPass : $("#newPass").val()
			},
			datatype: "TEXT",
			success: function(result){
				alertMessage("提示", result, function() {location.href = '/user/info';});
			},
			error : function() {
				alertMessage("提示", "未知错误！", function() {});
				return;
			}
		});
	});
	//添加咨询
	$("#add_consult_btn").click(function(){
		$.ajax({
			type: "POST",
			url: "/consult/addConsult",
			data: $("#add_consult_form").serialize(),
			datatype: "TEXT",
			success: function(result){
				$("#add_consult_form input").val("");
				$("#add_consult_form textarea").val("");
				alertMessage("提示", result, function() {});
			},
			error : function() {
				alertMessage("提示", "未知错误，请稍后再试！", function() {});
				return;
			}
		});
	});
	
	//咨询列表
	$("#manage_consult").click(function(){
		$.ajax({
			type: "POST",
			url: "/consult/showConsult",
			datatype: "JSON",
			success: function(result){
				$("#consult_table").html("");
				if(result != null){
					$.each(result, function(n, value){
						var tr = $("<tr></tr>");
						var title = $("<td style='width:40%'>"+value.title+"</td>");
						tr.append(title);
						var time = $('<td style="width:20%">'+value.time+'</td>');
						tr.append(time);
						var statusTd = $('<td style="width:10%"></td>');
						var status = $('<span>未回复</span>');
						if(value.status != 0){
							status.html("已回复");
						}
						statusTd.append(status);
						tr.append(statusTd);
						var operate = $('<td style="width:30%"></td>');
						var check = $('<a class="check_consult" id="check_'+value.id+'" href="javascript:void(0);" onclick="checkConsult(this)">查看</a>');
						var del = $('<a class="del_consult" id="del_'+value.id+'" href="javascript:void(0);" onclick="delConsult(this)">删除</a>');
						operate.append(check);
						operate.html(operate.html()+" | ");
						operate.append(del);
						tr.append(operate);
						$("#consult_table").append(tr);
					});
					
				}
			},
			error : function() {
				alertMessage("提示", "未知错误，请稍后再试！", function() {});
				return;
			}
		});
	});
	
});
//咨询管理中的查看点击
function checkConsult(obj){
	var id = $(obj).attr("id");
	var consultId = id.split("_")[1];
	$.ajax({
		type: "POST",
		url: "/consult/checkConsult",
		data: {consultId: consultId},
		datatype: "JSON",
		success: function(result){
			$("#consult_title").html(result.title);
			$("#consult_time").html(result.time);
			$("#consult_content").html(result.content);
			$("#consult_reply").html(result.reply);
		},
		error : function() {
			alertMessage("提示", "未知错误，请稍后再试！", function() {});
			return;
		}
	});
	$(".user_item").removeClass("selected");
	$("#check_consult_div").siblings().hide();
	$("#check_consult_div").show();
}
//删除咨询
function delConsult(obj){
	var id = $(obj).attr("id");
	var consultId = id.split("_")[1];
	id = "#"+id;
	$.ajax({
		type: "POST",
		url: "/consult/deleteConsult",
		data: {consultId: consultId},
		datatype: "TEXT",
		success: function(result){
			alertMessage("提示", result, function() {$(id).parent().parent().remove();});
			
		},
		error : function() {
			alertMessage("提示", "未知错误，请稍后再试！", function() {});
			return;
		}
	});
}