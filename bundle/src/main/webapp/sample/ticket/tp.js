var sendCount = 0;
function sendStart() {
	var $frmdiv = $("#frm");
	var frame = $("<iframe>", {
		name : "sendfrm",
		src : "tp.html"
	}).appendTo($frmdiv);
	frame[0].src = "tp.html";
	// var i=0;
	// sendfrm.document.forms[0].submit();
}
function countSend() {
	sendCount++;
	$("#count").html(sendCount);
}
$(function() {
	$("#start").click(function() {
		$("#frm").empty();
		for ( var i = 0; i < 1000; i++)
			sendStart();
	});
})