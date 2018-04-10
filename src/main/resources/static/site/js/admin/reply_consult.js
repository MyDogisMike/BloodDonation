$(function(){
	var consultId = $("#consultId").val().split('\\')[1];
	if (consultId == null){
		alertMessage("提示", "获取数据失败，请稍后再试", function() {});
		return;
	}
	$.ajax({
		type: "GET",
		url: "/consult/getOneConsult?id="+consultId,
		dataType: "JSON",
		success: function(result){
			if(result.id == null){
				alertMessage("提示", "获取数据失败，请稍后再试", function() {});
				return;
			}
			$("#title").val(result.title);
			$("#content").val(result.content);
			$("#reply").val(result.reply);
		},
		error: function(data){
			alertMessage("提示", "获取数据失败，请稍后再试", function() {});
			return;
		}
	});
	
	$("#reply_consult_btn").click(function(){
		if($.trim($("#reply").val()) == ""){
			alertMessage("提示", "请回复内容", function() {});
			return;
		}
		$.ajax({
			type: "POST",
			url: "/consult/replyOneConsult?id="+consultId,
			data: $("#reply_consult_form").serialize(),
			dataType: "TEXT",
			success: function(result){
				alertMessage("提示", result, function() {});
				$("button[type='reset']").click();
			},
			error: function(data){
				alertMessage("提示", "请稍后再试", function() {});
				return;
			}
		});
	});
})