function changeThis(obj){
	$(".searc_top").find(".ut").attr("class","jn");
	$(obj).attr("class","ut");
}
$(function(){
	$(".searc_top").find("ul li").click(function(){
		$(".searc_top").find(".ut").attr("class","jn");
		$(this).attr("class","ut");
		$("#pageTypeHidden").val($(this).attr("id"))
	})
	$("#form1").submit(function(){
		if($("input[name='entName']").val()=='')
			return false;
	})
})