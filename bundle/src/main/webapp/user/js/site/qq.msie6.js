function qqDragStop() {
	variable.gaimQQ.top = this.offsetTop - document.documentElement.scrollTop
			+ "px";
	variable.gaimQQ.left = this.offsetLeft
			- document.documentElement.scrollLeft + "px";
	$("#gaim-qq").attr("style", "");
}

function msie6qqTop() {
	var top = 240, w;
	if (w = variable.gaimQQ.top)
		top = Number(w.replace(/\D/g, ''));
	return document.documentElement.scrollTop + top + "px";
}

function msie6qqLeft() {
	var left = 150, w;
	if (w = variable.gaimQQ.left)
		left = Number(w.replace(/\D/g, ''));
	return document.documentElement.scrollLeft + left + "px";
}

$.getScript("http://51archetype.com/js/util/winport.jsp?username=" + username);
