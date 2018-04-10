$(function(){
	//显示最新的文章
	$.ajax({
		type: "POST",
		url: "/article/showNewArticles",
		data:{
			limit: 21
		},
		dataType: "JSON",
		success: function(result){
			$.each(result, function(n, value){
				var new_li = $("<li></li>");
				var new_a = $("<a title='"+value.title+"' href='/article/showArticle?id="+value.id+"'>"+value.title+"</a>");
				new_li.append(new_a);
				if(n % 3 == 0){
					new_li.addClass("blue_li");
				}
				$("#new_article_list").append(new_li);
			});
		}
	});
	//显示最热的文章
	$.ajax({
		type: "POST",
		url: "/article/showNewArticles",
		data:{
			limit: 6,
			view: "1",	//只要view不为空就会按最热的排序，”1“没有实际意义
		},
		dataType: "JSON",
		success: function(result){
			$.each(result, function(n, value){
				var new_li = $("<li></li>");
				var new_a = $("<a title='"+value.title+"' href='/article/showArticle?id="+value.id+"'>"+value.title+"</a>");
				new_li.append(new_a);
				
				$("#hot_article_list").append(new_li);
			});
		}
	});
});