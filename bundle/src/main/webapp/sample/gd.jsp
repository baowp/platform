<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ckeditor</title>
<script type="text/javascript" src="/js/jquery.js"></script>
<style type="text/css">
img {
	border: none;
}

table.album {
	margin: 10px auto 0 10px;
	background: url(images/product_show_bg.jpg) no-repeat center center;
	width: 577px;
	height: 276px;
}

img.thumb_img {
	cursor: pointer;
	display: block;
	margin-bottom: 10px;
}

img#main_img {
	cursor: pointer;
	display: block;
}

#gotop {
	cursor: pointer;
	display: block;
}

#gobottom {
	cursor: pointer;
	display: block;
}

#showArea {
	height: 206px;
	margin: 10px;
	overflow: hidden;
}

#showArea img {
	padding: 2px;
	border: 1px solid #ccc;
}
</style>
<script type="text/javascript">
$(function(){
	/*---------------album ----------------*/
	var MyMar;
	var speed = 1; 
	var spec = 1;
	var ipath = 'images/';
	var thumbs = $(".thumb_img");

	$(".thumb_img").click(function(){
	var src = $(this).attr("src");
	$("#main_img").attr("src",src);
	});

	$("#gotop").mouseover(function(){
	MyMar=setInterval(gotop,speed);
	});

	$("#gotop").mouseout (function() {
	clearInterval(MyMar);
	});

	$("#gobottom").mouseover(function() {
	MyMar=setInterval(gobottom,speed);
	});

	$("#gobottom").mouseout(function() {
	clearInterval(MyMar);
	});

	function gotop() {
	$("#showArea").animate({ scrollTop : "-="+spec } , 0);
	}

	function gobottom() {
	$("#showArea").animate({ scrollTop : "+="+spec } , 0);
	}
	});
</script>
</head>
<body>
<table class="album" align="center" border="0" cellpadding="0" cellspacing="5">
<tbody><tr>
<td align="center"><img src="/user/images/bf0721d6b9b841358e324004554bc05b.jpg" id="main_img" width="425" border="0" height="258"></td>
<td valign="top" align="center">
<img src="images/gotop.jpg" id="gotop" width="106" height="18">
<div id="showArea">
<img src="/user/images/bf0721d6b9b841358e324004554bc05b.jpg" class="thumb_img" width="106" border="0" height="55">
<img src="/user/images/business.jpg" class="thumb_img" width="106" border="0" height="55">
<img src="/user/images/cxt_cghy_01.jpg" class="thumb_img" width="106" border="0" height="55">
<img src="/user/images/login_pic.jpg" class="thumb_img" width="106" border="0" height="55">
<img src="/user/images/people.jpg" class="thumb_img" width="106" border="0" height="55">
<img src="/user/images/shant.png" class="thumb_img" width="106" border="0" height="55">
<img src="/user/images/tea.jpg" class="thumb_img" width="106" border="0" height="55">
<img src="/user/images/tianjia.png" class="thumb_img" width="106" border="0" height="55">
<img src="/user/images/tupian0.jpg" class="thumb_img" width="106" border="0" height="55">
<img src="/user/images/tupian.jpg" class="thumb_img" width="106" border="0" height="55">
<img src="/user/images/womam.jpg" class="thumb_img" width="106" border="0" height="55">
<img src="/user/images/bf0721d6b9b841358e324004554bc05b.jpg" class="thumb_img" width="106" border="0" height="55">
<img src="/user/images/bf0721d6b9b841358e324004554bc05b.jpg" class="thumb_img" width="106" border="0" height="55">
<img src="/user/images/bf0721d6b9b841358e324004554bc05b.jpg" class="thumb_img" width="106" border="0" height="55">
<img src="/user/images/bf0721d6b9b841358e324004554bc05b.jpg" class="thumb_img" width="106" border="0" height="55">
<img src="/user/images/bf0721d6b9b841358e324004554bc05b.jpg" class="thumb_img" width="106" border="0" height="55">
</div>
<img src="images/gobottom.jpg" id="gobottom" width="106" height="18"></td>
</tr>
</tbody></table>  
</body>
</html>