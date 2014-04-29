/**
 * 
 */
$.getScript("/js/jquery/json.js")		
$(function() {
	$("#tabs").tabs({
		cookie : {}
	});
	
	$("form").submit(function() {
		$(this).ajaxSubmit({
			data : {
				footerContent : $.toJSON({cnzz:$("#cnzz").val(),"51la":$("#51la").val()})
			},
			success : function() {
				info("保存成功");
			}
		})
		return false;
	})
});