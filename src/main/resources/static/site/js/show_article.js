$(function(){
	//根据文章ID设置页面上的文章内容
	$.ajax({
		type: "POST",
		url: "/article/getArticle",
		data: {id: $("#articleId").val()},
		dataType: "JSON",
		success: function(result){
			$("#article_title").html(result.article.title);
			$("#release_time").html(result.article.releaseDate);
			$("#publisher").html(result.article.publisher);
			$("#view").html(result.article.pageView);
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
			alertMessage("提示", "获取文章出错，请稍后再试", function() {});
		}
	});
	//获取文章评论
	$.ajax({
		type: "POST",
		url: "/article/showComment",
		data: {
			articleId: $("#articleId").val()
		},
		dataType: "JSON",
		success: function(result){
			if(result != null){
				$.each(result, function(n, value){
					addComment(value);
				});
			}
		},
		error: function(){
			alertMessage("提示", "获取文章评论出错，请稍后再试", function() {});
		}
	});
	//提交评论
	$("#submit_comment").click(function(){
		if($("#login_form").length > 0){
			alertMessage("提示", "请登录后再进行评论", function() {});
			return;
		}
		if($.trim($("#comment").val()) == ""){
			alertMessage("提示", "请填写评论后再提交", function() {});
			return;
		}
		$.ajax({
			type: "POST",
			url: "/article/addComment",
			data: {
				content: $("#comment").val(),
				articleId: $("#articleId").val()
			},
			dataType: "JSON",
			success: function(result){
				if(result.id > 0){	//id大于0表示插入成功
					$("#comment").val("");
					addComment(result);
					alertMessage("提示", "添加成功", function() {});
				}else{
					alertMessage("提示", "请稍后再试", function() {});
				}
				
			},
			error: function(){
				alertMessage("提示", "请稍后再试", function() {});
			}
		});
	});
	
	function addComment(result){
		var item = $('<div class="comment_item"></div>');
		var info = $('<p class="comment_info"></p>');
		var user = $('<span class="comment_user"></span>');
		var userName = result.userName;
		if(userName == null || $.trim(userName) == ""){
			userName = "无名人士";
		}
		user.html(userName);
		info.append(user);
		var time = $('<span class="comment_time"></span>');
		time.html(result.time);
		info.append(time);
		item.append(info);
		var content = $('<div class="user_comment"></div>');
		content.html(result.content);
		item.append(content);
		
		$("#list").append(item);
	}
});