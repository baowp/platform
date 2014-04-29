$(function() {
	if (window.layout)
		return;

	$("span.titleName").css("cursor", "default");

	if ($("#products").length && $(".headerMenuLiCheck").length
			&& $(".headerMenuLiCheck").find("#piece").val().match(/product/i))
		loadProductsById = function(categoryId) {
			$.ajax({
				url : contextRoot + "/user/vipsite/piece/main_products.jsp",
				data : {
					categoryId : categoryId
				},
				complete : removeBusy,
				beforeSend : function() {
					busyOverlay($("#products")[0], {
						opacity : 0
					}, {
						color : '#00f',
						size : 192,
						type : 'c',
						iradius : 20,
						weight : 6,
						count : 12,
						speed : 2
					});
				},
				success : function(data) {
					$("#products .bodyContContent").html(
							$(data).find(".bodyContContent").html());
				}
			})
		}
	else
		loadProductsById = function(categoryId) {
			location = "product?categoryId=" + categoryId;
		}

	if ($("#news").length && $(".headerMenuLiCheck").length
			&& $(".headerMenuLiCheck").find("#piece").val().match(/news/i))
		loadNewsById = function(categoryId) {
			$.ajax({
				url : contextRoot + "/user/vipsite/piece/main_news.jsp",
				data : {
					newsCategory : categoryId
				},
				complete : removeBusy,
				beforeSend : function() {
					busyOverlay($("#news")[0], {
						opacity : 0
					}, {
						color : '#00f',
						size : 192,
						type : 'c',
						iradius : 20,
						weight : 6,
						count : 12,
						speed : 2
					});
				},
				success : function(data) {
					$("#news .bodyContContent").html(
							$(data).find(".bodyContContent").html());
				}
			})
		}
	else
		loadNewsById = function(categoryId) {
			location = "news?newsCategory=" + categoryId;
		}
})
