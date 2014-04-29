function color() {
	$(".text2").html(document.documentElement.scrollTop)
	return 'orange';
}
function navy() {
	$(".text2").html("navy");
	return 'navy';
}
function red() {
	$(".text2").html("red");
	return 'red';
}
$(function(){
	$("#b1").click(function(){
		//$(".text3").html($(".text1").css("color"));
		$(".text1").attr("style","");
		$(".text1")[0].setAttribute("style","color: expression(red());");
	});
});