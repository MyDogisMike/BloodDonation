$(function(){
	var articleId = $("#articleId").val().split('\\')[1];
	if (articleId == null){
		alertMessage("提示", "获取数据失败，请稍后再试", function() {});
		return;
	}
	$.ajax({
		type: "GET",
		url: "/article/getOneArticle?id="+articleId,
		dataType: "JSON",
		success: function(result){
			if(result.id == null){
				alertMessage("提示", "获取数据失败，请稍后再试", function() {});
				return;
			}
			$("#title").val(result.title);
			$("#publisher").val(result.publisher);
			$("#publishingLocation").val(result.publishingLocation);
			$("#pageView").val(result.pageView);
			$("#releaseDate").val(result.releaseDate);
			if(result.status == 0){
				$("#noDisplay").attr("checked","checked");
			}else{
				$("#display").attr("checked","checked");
			}
			
		},
		error: function(data){
			alertMessage("提示", "获取数据失败，请稍后再试", function() {});
			return;
		}
	});
	
	$("#edit_article_btn").click(function(){
		var status = 1;
		if($("#noDisplay").prop("checked")){
			status = 0;
		}
		$.ajax({
			type: "POST",
			url: "/article/editOneArticle?id="+articleId,
			data: {
				title: $("#title").val(),
				pageView: $("#pageView").val(),
				status: status
			},
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