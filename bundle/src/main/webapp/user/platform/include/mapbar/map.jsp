<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$.ajax({
		type:"post",
		url:"/user/enterprise/company/getMapKey",
		data:"",
		success:function(data){
			var entList = eval('(' +data+ ')');
			$.each(entList,function(i,n){
				$("#name").val(n.name);
				$("#phone").val(n.phone);
				$("#address").val(n.address);
				$("#nid").val(n.mapaddress);
				$("#mapIframe").attr("src","http://searchbox.mapbar.com/publish/template/template1010/index.jsp?CID=ggggfj&tid=tid1010&nid="+n.mapaddress+"&width=600&height=450&control=2&infopoi=1&infoname=1&zoom=10&showSearchDiv=0")
			})
		}
	})
	$("#cxbd").click(function(){
		insertMap();
	})
})
function insertMap() {
    var url = "proxy.jsp?api=template1000&CID=ggggfj&tid=tid1000&width=600&height=400&zoom=10&control=2&cityName=&nid="+$("#nid").val()+
          "&name="+encodeURIComponent(document.getElementById("name").value)+
          "&address="+encodeURIComponent($("#address").val())+
          "&phone="+encodeURIComponent($("#phone").val());
    var winname = window.open(url, '_blank');
  }
function setNid(nid) {
	$.ajax({
		type:"post",
		url:"/user/enterprise/company/savemap",
		data:"mapaddress="+nid,
		success:function(){
		}
	})
	$("#mapIframe").attr("src","http://searchbox.mapbar.com/publish/template/template1010/index.jsp?CID=ggggfj&tid=tid1010&nid="+nid+"&width=600&height=450&control=2&infopoi=1&infoname=1&zoom=10&showSearchDiv=0")
	//top.$("#mapbar").parent().parent().remove();
	//parent.$("#mapbar").parent().parent().remove();
	parent.location.reload();
	//top.$("#mainFrame").attr("src","action/main.jsp");
  }
</script>
</head>
<body>
<div style="background-color:blue;" align="center">
<s:if test="%{#session.abbccuser!=null}">
<input id="name" type="hidden" name="name"/><input id="address" type="hidden" name="address" value=""/><input id="phone" type="hidden" name="phone" value=""/><input type="hidden" name="nid" id="nid"/>
<input type="button" id="cxbd" value="点我标点"></s:if></div>
<iframe width="600" height="450" scrolling="no" border="0"
	frameborder="0" id="mapIframe"
	src="http://searchbox.mapbar.com/publish/template/template1010/index.jsp?CID=ggggfj&tid=tid1010&nid=MAPBYNZMSOCPTIFEYAHMX&width=600&height=450&control=2&infopoi=1&infoname=1&zoom=10&showSearchDiv=0"></iframe>
</body>
</html>