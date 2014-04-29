/*
skin swicth v1.0
用法：
	swichStyle(styleId,swichId,stylePath);
	参数说明：
	styleId : <link rel="stylesheet" type="text/css" href="css/default.css" id="skin" /> 中的id
	swichId ：<ul id="switch-skin"> 切换样式列表的id
	stylePath :css存放的目录
*/

/**
 * swichStyle 
 */
function swichStyle(styleId,swichId,stylePath) {
    var c = readCookie("style");
    if (c !== null) {
        jQuery("#"+styleId).attr("href",c);
    }

    jQuery("."+swichId).click(function(){
        var styleName = jQuery(this).attr("rel")+".css";
        var newCss = stylePath+styleName;
        jQuery("#"+styleId).attr("href",newCss);
        createCookie("style",newCss,365);
    });
}

/**
 *cookie function http://www.quirksmode.org/js/cookies.html
 */
function createCookie(name,value,days) {
	if (days) {
		var date = new Date();
		date.setTime(date.getTime()+(days*24*60*60*1000));
		var expires = "; expires="+date.toGMTString();
	}
	else expires = "";
	document.cookie = name+"="+value+expires+"; path=/";
}

function readCookie(name) {
	var nameEQ = name + "=";
	var ca = document.cookie.split(';');
	for(var i=0;i < ca.length;i++) {
		var c = ca[i];
		while (c.charAt(0)==' ') c = c.substring(1,c.length);
		if (c.indexOf(nameEQ) === 0) return c.substring(nameEQ.length,c.length);
	}
	return null;
}

function eraseCookie(name) {
	createCookie(name,"",-1);
}

