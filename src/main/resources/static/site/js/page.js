var showPage;
$(function(){
	$("#prevPage").click(function(){
		var nowPage = parseInt($(".focus").html());
		if(nowPage > 1){
			showPage(nowPage - 1);
		}
	});
	
	$("#nextPage").click(function(){
		var nowPage = parseInt($(".focus").html());
		var allPage = $("#allPage").html();
		debugger;
		if(nowPage < allPage){
			showPage(nowPage + 1);
		}
	});
	
	$("#pageSelect").change(function(){
		var selectPage = $("#pageSelect").find("option:selected").text();
		selectPage = parseInt(selectPage);
		showPage(selectPage);
	});
	
	showPage = function(page){	//page表示当前页数
		page = parseInt(page);
		init(page);	//这样加载分页，在第一次进入文章列表页时，会获取三次文章列表- -
		$("#middlePage").html("");
		var allPage = $("#allPage").html();
		if(allPage == 1){	//只有一页
			$(".homePage").addClass("off");
			$(".endPage").addClass("off");
			var pageA = $('<a class="page focus" href="javascript:void(0);" onclick="showPage(1)">1</a>');
			
			$("#middlePage").append(pageA);
		} else if(page == 1){	//首页
			$(".homePage").addClass("off");
			$(".endPage").removeClass("off");
			var pageA = $('<a class="page focus" href="javascript:void(0);" onclick="showPage(1)">1</a>');
			$("#middlePage").append(pageA);
			var i;
			for(i = 2; i < 8 && i <= allPage; i++){
				pageA = $('<a class="page" href="javascript:void(0);" onclick="showPage('+i+')">'+i+'</a>');
				$("#middlePage").append(pageA);
			}
			if(i < allPage){
				$("#middlePage").html($("#middlePage").html()+"...");
			}
		} else if(page == allPage){	//尾页
			$(".endPage").addClass("off");
			$(".homePage").removeClass("off");
			var pageA = $('<a class="page focus" href="javascript:void(0);" onclick="showPage('+allPage+')">'+allPage+'</a>');
			$("#middlePage").append(pageA);
			var i;
			for (i = allPage; i > allPage - 7 && i >= 1; i--){
				pageA = $('<a class="page" href="javascript:void(0);" onclick="showPage('+i+')">'+i+'</a>');
				$("#middlePage").prepend(pageA);
			}
			if(i > 1){
				$("#middlePage").html("..."+$("#middlePage").html());
			}
		}else{
			$(".endPage").removeClass("off");
			$(".homePage").removeClass("off");
			
			if(allPage < 8){
				for(var i = 1; i <= allPage; i++){
					var pageA = $('<a class="page" href="javascript:void(0);" onclick="showPage('+i+')">'+i+'</a>');
					if(i == page){
						pageA.addClass("focus");
					}
					$("#middlePage").append(pageA);
				}
				$("#middlePage").html($("#middlePage").html()+"...");
			}else{
				if(page <= 4){
					for(var i = 1; i < 8; i++){
						var pageA = $('<a class="page" href="javascript:void(0);" onclick="showPage('+i+')">'+i+'</a>');
						if(i == page){
							pageA.addClass("focus");
						}
						$("#middlePage").append(pageA);
					}
					$("#middlePage").html($("#middlePage").html()+"...");
				}else if(page >= allPage - 6){
					for(var i = allPage; i > allPage - 7; i--){
						var pageA = $('<a class="page" href="javascript:void(0);" onclick="showPage('+i+')">'+i+'</a>');
						if(i == page){
							pageA.addClass("focus");
						}
						$("#middlePage").prepend(pageA);
					}
					$("#middlePage").html("..."+$("#middlePage").html());
				}else{
					for (var i = page - 3; i <= page + 3; i++){
						var pageA = $('<a class="page" href="javascript:void(0);" onclick="showPage('+i+')">'+i+'</a>');
						if(i == page){
							pageA.addClass("focus");
						}
						$("#middlePage").append(pageA);
					}
					$("#middlePage").html("..."+$("#middlePage").html()+"...");
				}
			}
			
		}

	}
	
	
});
