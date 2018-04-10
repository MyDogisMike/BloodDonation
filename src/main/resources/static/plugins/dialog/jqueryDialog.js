function alertMessage(a,b,onOk){
	var message = 
	"<div class='message_black'></div>"+
	"<div class='message_box'>"+
		"<div class='message_title'>"+a+"</div>"+
		"<div class='message_con'>"+b+"</div>"+
		"<div class='message_bottom'><button class='okBtn'>确定</button></div>"
	"</div>"
	$("body").append(message);
	$("body").addClass("overflow_hidden");

	$(".okBtn").click(function(){
		$(".message_black,.message_box").remove();
		$("body").removeClass("overflow_hidden");
		onOk();
	})
}