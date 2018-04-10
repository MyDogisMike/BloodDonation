$(function(){
	//根据文章ID设置页面上的文章内容
	$.ajax({
		type: "POST",
		url: "/activity/getActivity",
		data: {id: $("#activityId").val()},
		dataType: "JSON",
		success: function(result){
			$("#article_title").html(result.activity.title);
			$("#release_time").html(result.activity.releaseDate);
			$("#publisher").html(result.activity.publisher);
			$("#view").html(result.activity.pageView);
			$("#personCount").html(result.activity.personCount);
			$.each(result.content, function(n, value){
				if(value.type == 1){	//表示为文字段落
					var characterDiv = $("<div class='characters'></div>");
					characterDiv.html("&nbsp;&nbsp&nbsp;&nbsp"+value.content);
					$("#article_content").append(characterDiv);
				}else if(value.type == 2){	//表示为图片段落
					var pictureDiv = $("<div class='picture'></div>");
					var pic = $("<img src=/"+value.content+" />");
					pictureDiv.append(pic);
					$("#article_content").append(pictureDiv);
				}
			});
		},
		error: function(){
			alertMessage("提示", "获取活动出错，请稍后再试", function() {});
		}
	});
	var flag = 0;
	if($("#login_form").length < 1){
		flag = 1;
		$.ajax({
			type: "POST",
			url: "/record/getCountFromActivity",
			data: {id: $("#activityId").val()},
			dataType: "TEXT",
			success: function(result){
				if(result > 0){
					$("#in").hide();
					$("#out").show();
				}else{
					$("#in").show();
					$("#out").hide();
				}
			},
			error: function(){
				alertMessage("提示", "获取活动出错，请稍后再试", function() {});
			}
		});
	}
	
	$("#in").click(function(){
		if(flag == 0){
			alertMessage("提示", "请先登录", function() {});
			return;
		}
		$.ajax({
			type: "POST",
			url: "/record/activitIn",
			data: {activityId: $("#activityId").val()},
			dataType: "TEXT",
			success: function(result){
				if(result == "success"){
					alertMessage("提示", "参加活动成功", function() {});
					var count = parseInt($("#personCount").html());
					$("#personCount").html(count + 1);
					$("#in").hide();
					$("#out").show();
				}else{
					alertMessage("提示", "参加活动出错，请稍后再试", function() {});
				}
				
			},
			error: function(){
				alertMessage("提示", "参加活动出错，请稍后再试", function() {});
			}
		});
	});
	
	$("#out").click(function(){
		$.ajax({
			type: "POST",
			url: "/record/activitOut",
			data: {activityId: $("#activityId").val()},
			dataType: "TEXT",
			success: function(result){
				if(result == "success"){
					alertMessage("提示", "退出活动成功", function() {});
					var count = parseInt($("#personCount").html());
					$("#personCount").html(count - 1);
					$("#in").show();
					$("#out").hide();
				}else{
					alertMessage("提示", "退出活动出错，请稍后再试", function() {});
				}
				
			},
			error: function(){
				alertMessage("提示", "退出活动出错，请稍后再试", function() {});
			}
		});
	});
	
});