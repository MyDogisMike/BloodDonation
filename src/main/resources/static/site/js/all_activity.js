var init;
$(function(){
	init = function(page){
		$.ajax({	
			type: "POST",
			url: "/activity/allActivity",
			data: {
				page: page
			},
			datatype: "JSON",
			success: function(result){
				if(result.articles != null){
					//加载文章列表
					$("#list_ul").html("");
					$.each(result.articles, function(n, value){
						var li = $('<li></li>');
						var time = $('<span>'+value.releaseDate+'</span>');
						li.append(time);
						var title = $('<a href="/activity/showActivity?id='+value.id+'" target="_blank">'+value.title+'</a>');
						li.append(title);
						$("#list_ul").append(li);
					});
				}
			},
			error : function() {
				alertMessage("提示", "未知错误！", function() {});
				return;
			}
		});
	};
	$.ajax({
		type: "POST",
		url: "/activity/allActivity",
		data: {
			
		},
		datatype: "JSON",
		success: function(result){
			if(result.articles != null){
				//加载文章列表
				$("#list_ul").html("");
				$.each(result.articles, function(n, value){
					var li = $('<li></li>');
					var time = $('<span>'+value.releaseDate+'</span>');
					li.append(time);
					var title = $('<a href="/activity/showActivity?id='+value.id+'" target="_blank">'+value.title+'</a>');
					li.append(title);
					$("#list_ul").append(li);
				});
				//设置分页信息
				$("#allPage").html(result.allPages);
				$("#total").html(result.allCounts);
				for (var i = 1; i <= result.allPages; i++){
					var option = $("<option value="+i+">"+i+"</option>");
					$("#pageSelect").append(option);
				}
				$("#endPage").click(showPage(result.allPages));
				showPage(1);
			}
		},
		error : function() {
			alertMessage("提示", "未知错误！", function() {});
			return;
		}
	});
});