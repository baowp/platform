$(function() {
	$("#menu_10001").click(function() {
		$("#children_10001").slideToggle(600, function() {
			if ($(this).is(":hidden"))
				$("#treeImg").attr("src", "images/tree2_1257575066701.png");

			else
				$("#treeImg").attr("src", "images/tree3_1257575066703.png");
		});
	})
})