$(function(){
	
	var emailReg = /^([0-9a-zA-Z._]|-)+@{1}([0-9a-zA-Z._]|-)+\.{1}([0-9a-zA-Z._]|-)+$/;
	var passwordReg = /^(?=.*[0-9])(?=.*[a-zA-Z]).{8,16}$/;
	
	//用户名输入校验
	$("#name").on("keyup", function() {
		if ($(this).val() != "") {
			$.ajax({
				type : "post",
				url : "/reg/validateName",
				data : {
					name : $(this).val()
				},
				dataType : "text",
				success : function(data) {
					if (data != "" && data != null) {
						$("#name").addClass('warn');
						$("#name").siblings('span').text("该用户名已经被占用了！");
					} else {
						$("#name").removeClass('warn');
						$("#name").siblings('span').text("");
					}
					
				}
			});
		} else {
			$("#name").siblings('span').text("用户名不能为空");
		}
	});
	// 邮箱输入校验
	$("#email").on("keyup", function() {
		if ($(this).val() != "" && !emailReg.test($(this).val())) {
			$(this).addClass('warn');
			$(this).siblings('span').text("邮箱格式不正确");
		} else {
			if ($(this).val() != "") {
				//在ajax模块中不能用$(this)代替$("#email")
				$.ajax({
					type : "post",
					url : "/reg/validateEmail",
					data : {
						email : $(this).val()
					},
					dataType : "text",
					success : function(data) {
						if (data != "" && data != null) {
							$("#email").addClass('warn');
							$("#email").siblings('span').text("该邮箱已经注册！");
						} else {
							$("#email").removeClass('warn');
							$("#email").siblings('span').text("");
						}
						
					}
				});
			} else {
				$("#email").siblings('span').text("邮箱不能为空");
			}
		}
	});
	
	// 密码输入校验
	$("#password").on("keyup",function() {
				if ($("#password").val() != "" && !passwordReg.test($("#password").val())) {
					$(this).addClass('warn');
					$(this).siblings('span').text("密码8-16位，必须包含字母和数字");
				} else {
					$(this).removeClass('warn');
					$(this).siblings('span').text("");
				}
	});
	$("#password2").on("keyup",function() {
			if ($("#password2").val() != "" && $("#password2").val() != $("#password").val()) {
				$(this).addClass('warn');
				$(this).siblings('span').text("两次密码不一致");
			} else {
				$(this).removeClass('warn');
				$(this).siblings('span').text("");
			}
	});
	
	$("#reg_btn").click(function(){
		var flag = false;
		$("#reg_table input").each(function(n) {
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
			type : "post",
			url : "/reg/add",
			data : $("#reg_form").serialize(),
			dataType : "text",
			beforeSend: function () {
				$("#reg_btn").attr({ disabled: "disabled" });
			    ShowDiv();
			},
			complete: function () {
			    HiddenDiv();
			},
			success : function(data) {
				if(data == "success"){
					alertMessage("提示", "注册成功，请去邮箱激活！", function() {location.href = '/';});
				}else if(data == "emailError"){
					alertMessage("提示", "邮件发送失败！", function() {});
				}else if(data == "regError"){
					alertMessage("提示", "注册失败！", function() {});
				}else{
					alertMessage("提示", "未知错误！", function() {});
				}
			},
			error : function() {
				alertMessage("提示", "未知错误！", function() {});
				return;
			}
		});
	});
});
//显示加载数据
function ShowDiv() {
$("#loading").show();
}

//隐藏加载数据
function HiddenDiv() {
$("#loading").hide();
}