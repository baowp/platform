<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://www.jqueryajax.com/jquery-1.3.2.min.js"></script>
<script type='text/javascript'>
//滚动公告
function AutoScroll(obj){
$(obj).find("ul:first").animate({
marginTop:"-25px"
},500,function(){
$(this).css({marginTop:"0px"}).find("li:first").appendTo(this);
});
}
var s = true;
var t=0;
function startli()
{
if(s) t = setInterval('AutoScroll(".scrollDiv")',2000);
}

//suggest
$(document).ready(function(){
//滚动公告
startli();
$(".scrollDiv").hover(function(){
clearInterval(t);
var s = false;
},function(){
s = true;
startli();
});
})
</script>

<style type="text/css">
.scrollDiv{border:1px #ccc solid; width:150px; height:25px; overflow:hidden}
.scrollDiv ul{height:25px; line-height:25px; margin:0; padding:0;}
.scrollDiv li{line-height:25px; padding-left:5px; height:25px; overflow:hidden}

</style>
</head>

<body>
<div class="scrollDiv">
<ul>
<li><a href="htp://www.jqueryajax.com">滚动公告一</a></li>
<li><a href="htp://www.jqueryajax.com">滚动公告二</a></li>
<li><a href="htp://www.jqueryajax.com">滚动公告三</a></li>
<li><a href="htp://www.jqueryajax.com">滚动公告四</a></li>
<li><a href="htp://www.jqueryajax.com">滚动公告五</a></li>
<li><a href="htp://www.jqueryajax.com">滚动公告六</a></li>
</ul>
</div>

</body>

</html>