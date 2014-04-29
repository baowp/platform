<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css"> 
*{padding:0;margin:0;}
html{height:100%;}
body{height:200%;}
.box{position:absolute;width:100px;height:100px;background:#ccc}
</style>
</head>
<body bgcolor="#F7F7F7">
<div class="box" id="box1" style="bottom:0;left:0;">AD1</div>
<div class="box" id="box2" style="top:0;right:0;">AD2</div>
</body>
<script type="text/javascript"> 
var id=function(o){return document.getElementById(o)}
var scroll=function (o){
	var space=id(o).offsetTop;
	id(o).style.top=space+'px';
	void function(){
			var goTo = 0;
			var roll=setInterval(function(){
				var height =document.documentElement.scrollTop+document.body.scrollTop+space;
				var top = parseInt(id(o).style.top);
				if(height!= top){
					goTo = height-parseInt((height - top)*0.9);
					id(o).style.top=goTo+'px';
				}
				//else{if(roll) clearInterval(roll);}
			},50);
	}()
}
scroll('box1');
scroll('box2');
</script>

</html>