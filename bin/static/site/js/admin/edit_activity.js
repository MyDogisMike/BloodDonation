$(function(){
	var activityId = $("#activityId").val().split('\\')[1];
	if (activityId == null){
		alertMessage("提示", "获取数据失败，请稍后再试", function() {});
		return;
	}
	$.ajax({
		type: "GET",
		url: "/activity/getOneActivity?id="+activityId,
		dataType: "JSON",
		success: function(result){
			if(result.id == null){
				alertMessage("提示", "获取数据失败，请稍后再试", function() {});
				return;
			}
			$("#title").val(result.title);
			$("#publisher").val(result.publisher);
			$("#publishingLocation").val(result.publishingLocation);
			$("#personCount").val(result.personCount);
			$("#startDate").val(result.startDate);
			$("#endDate").val(result.startDate);
			if(result.status == 0){
				$("#prepare").attr("checked","checked");
			}else if(result.status == 1){
				$("#progress").attr("checked","checked");
			}else{
				$("#end").attr("checked","checked");
			}
			
		},
		error: function(data){
			alertMessage("提示", "获取数据失败，请稍后再试", function() {});
			return;
		}
	});
	
	$("#edit_activity_btn").click(function(){
		var status = 2;
		if($("#prepare").prop("checked")){
			status = 0;
		}else if($("#progress").prop("checked")){
			status = 1;
		}
		$.ajax({
			type: "POST",
			url: "/activity/editOneActivity?id="+activityId,
			data: {
				title: $("#title").val(),
				start: $("#startDate").val(),
				end: $("#endDate").val(),
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