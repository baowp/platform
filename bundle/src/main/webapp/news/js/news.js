$(document).ready(function() {
	$("#isImagenews").click(function(){
		$("#pic").toggle(300)
	} )
	$("#filePath").click(function(){
		$("#filePath").hide(300)
		$(":file").show()
		$("#qx").show()
	} )
	$("#qx").click(function(){
		$("#filePath").show(300)()
		$(":file").hide()
		$("#qx").hide()
	} )
})