function makeState(value) {
	jQuery("#uState").html(value);
	jQuery("#makestate").show();
	setTimeout(hiddenDiv, 3000)
}
function hiddenDiv() {
	document.getElementById("makestate").style.display = "none";
}
function logoimg(img) {
	jQuery("#indexlogo").attr("src", img);
	// $("#indexlogo").src();
}
jQuery(function() {
	swichStyle("skin", "styleswitch", "images/");
	
})
function showBox(o) {
	if (o == undefined)
		o = document.getElementById("rbbox");
	o.style.height = o.clientHeight + 2 + "px";
	if (o.clientHeight < 200)
		setTimeout(function() {
			showBox(o)
		}, 15);
}
function closeBox() {
	var o = document.getElementById("rbbox");
	if (o.clientHeight >= 2)
		o.style.height = o.clientHeight - 2 + "px";
	(o.clientHeight >= 4)
	setTimeout(function() {
		closeBox()
	}, 15);
}