function replacePos(strObj, pos, replacetext){
	var str = strObj.substr(0, pos-1) + replacetext + strObj.substring(pos, strObj.length);
	 return str;
}

jQuery(function($) {
	var imgv;
	var imgg = new Array();
	var dangqian = 0;
	var oldSrc;
	imgv = $(".jiaodu").find("img").size();
	for ( var i=0; i<imgv; i++) {
		oldSrc = $("#" + i + "").attr("src");
		var index = oldSrc.lastIndexOf("8")+1;
		if(index != -1) {
			imgg[i] = replacePos(oldSrc,index,"7");
		} else {
			imgg[i] = oldSrc;
		}
	}
	$(".jiaodu img").click(function() {
		dangqian = $(this).attr("id") / 1;
		$(".datu").find("img").attr("src",imgg[dangqian]);
	})
	$(".yanse img").click(function() {
		$(".datu").css("background-image", "url(" + $(this).attr("name") + ")")
	})
	$(".datu").find("img").attr("src",imgg[0]);
	$(".yanse img").mousemove(function() {
		$("#yanses").text($(this).attr("alt"));
	})
	$(".yanse img").mouseout(function() {
		$("#yanses").text("");
	})

	$(".zuodong").click(function() {
		if (dangqian == 0) {
			dangqian = imgv - 1;
			$(".datu").css("background-image", "url(" + imgg[dangqian] + ")");
		} else {
			dangqian = dangqian - 1;
			$(".datu").css("background-image", "url(" + imgg[dangqian] + ")");
		}
	})
	$(".youdong").click(function() {
		if (dangqian == imgv - 1) {
			dangqian = 0;
			$(".datu").css("background-image", "url(" + imgg[dangqian] + ")");
		} else {
			dangqian = dangqian + 1;
			$(".datu").css("background-image", "url(" + imgg[dangqian] + ")");
		}
	})
	$(".xntd").click(function(e) {
		$(".xntd").attr("id","colse");
		$(e.target).attr("id","open");
		var show = $(e.target).attr("flag");
		$("div[flag^=show]").css("display", "none");
		$("div[flag=show"+ show +"]").css("display", "");
		
		//$(".con_xn").css("display", "block");
		//$(".con_cs").css("display", "none");
	})
	$(".jscs").click(function() {
		$(".xntd").attr("id", "colse");
		$(".jscs").attr("id", "open");
		$(".con_xn").css("display", "none");
		$(".con_cs").css("display", "block");
	})
})
