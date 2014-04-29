<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Flip! A jQuery plugin v0.9.9</title>
<meta name="description"
	content="Flip is a jQuery plugin to apply flip animation to any element." />
<meta name="keywords"
	content="flip, flipping, jquery, jquery plugin, plugin, animation plugin, javascript, css, border animation" />
<meta name="language" content="english" />
<meta name="robots" content="index,follow" />
<meta name="author" content="Luca Manno" />

<meta name="charset" content="utf-8" />

<!--[if IE]>
			<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
			<link rel="stylesheet" type="text/css" href="flip_ie.css"/>
		<![endif]-->
<script type="text/javascript" src="/js/jquery.js"></script>
<script src="/js/jquery-ui.min.js"></script>
<script src="jquery.flip.min.js"></script>
<style type="text/css">
#flipbox {
	background-color: #FF9000;
	color: #FFFFFF;
	font-family: 'ChunkFive Regular', Tahoma, Helvetica;
	font-size: 2.5em;
	height: 200px;
	line-height: 200px;
	text-align: center;
	width: 500px;
}
img{
width: 500px;
height: 200px;
}
</style>

<script type="text/javascript">
			$(function(){
				$("#rId").hide();
				$("#flipPad a:not(.revert)").bind("click",function(){
					var $this = $(this);
					$("#flipbox").flip({
						direction: $this.attr("rel"),
						color: $this.attr("rev"),
						content: '村',
						onBefore: function(){$(".revert").show()}
					})
					$("#rId").show();
					return false;
				});			
				
				$(".revert").bind("click",function(){
					$("#flipbox").revertFlip();
					$("#rId").hide();
					return false;
				});
				
				var changeMailTo = function(){
					var mArr = ["@","smashup","luca",".it"];
					$("#email").attr("href","mailto:"+mArr[2]+mArr[0]+mArr[1]+mArr[3]);
				}
				
				$(".downloadBtn").click(function(){
					pageTracker._trackPageview('download_flip');
				});	
				
				setTimeout(changeMailTo,500);
				
			});
		</script>
</head>
<body>

<div id="flipbox"><img src="http://img.cn.easthardware.net/upload/8/86/88888888/picture/2010/08/29/1a5b8fae-dd5e-4a4e-8f16-9e147becba7a_3.jpg" /></div>
<div id="flipPad"><a href="#" class="left" rel="rl" rev="#39AB3E"
	title="Change content as <em>you</em> like!">left</a> <a href="#"
	class="top" rel="bt" rev="#B0EB17" title="Ohhh yeah!">top</a> <a
	href="#" class="bottom" rel="tb" rev="#82BD2E" title="Hey oh let's go!">bottom</a>
<a href="#" class="right" rel="lr" rev="#C8D97E"
	title="Waiting for css3...">right</a>&nbsp<div id="rId"><a href="#" class="revert">revert!</a></div>
</div>
</body>
</html>
