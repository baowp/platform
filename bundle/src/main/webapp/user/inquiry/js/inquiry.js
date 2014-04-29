$(function() {
	$("#autoSelect").change(function() {
		$("#added").val($(this).val() + $("#added").val())
	})
	$("#subButton").click(function() {
		if ($("#title").val() == '') {
			art.dialog.tips('请输入标题！', 1);
			return;
		}
		if ($("#userId").val() == '') {
			if ($("#name").val() == '') {
				art.dialog.tips('请输入姓名！', 1);
				return;
			}
			if ($("#email").val() == '') {
				art.dialog.tips('请输入电子邮箱，以方便卖家的回复!', 1);
				return;
			}
			if ($("#added").length > 200) {
				art.dialog.tips('补充问题请控制在200字以内!', 1);
				return;
			}
		}
		$.ajax({
			url : "/inquiry/save",
			dataType : "json",
			async : false, //不进行异步操作
			data : {
				title:$("#title").val(),
				count:$("#count").val(),
				price:$("#price").val(),
				requestInfo:$("#requestInfo").val(),
				added:$("#added").val(),
				endTime:$("#endTime").val(),
				enterpriseId:$("#enterpriseId").val(),
				companyName:$("#companyName").val(),
				email:$("#email").val(),
				phone:$("#phone").val(),
				name:$("#name").val()
			},
			success : function() {
				art.dialog.tips('询价成功，请等待商家回复！', 1);
				art.dialog.close();
			}
		})
	})
})