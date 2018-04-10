$(function(){
	addressInit("cmbProvince", "cmbCity", "cmbArea", "湖南", "永州市", "冷水滩区");
	
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
						$("#name").parent().siblings('div').html("该用户名已经被占用了！");
					} else {
						$("#name").parent().siblings('div').html("");
					}
					
				}
			});
		} else {
			$("#name").parent().siblings('div').html("用户名不能为空");
		}
	});
	
	$("#admin_add_user_btn").click(function(){
		if ($(".layui-word-aux").html() != "") {
			alertMessage("提示", "请按要求填写信息", function() {});
			return;
		}
		var address = $("#cmbProvince").val()+"省"+$("#cmbCity").val()+$("#cmbArea").val();
		$.ajax({
			type: "POST",
			url: "/admin/addOneUser?address="+address,
			data: $("#admin_add_user_form").serialize(),
			dataType: "TEXT",
			success: function(result){
				alertMessage("提示", result, function() {});
				$("button[type='reset']").click();
			}
		});
	});
});
