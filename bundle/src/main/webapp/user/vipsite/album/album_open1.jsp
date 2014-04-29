<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<link type="text/css" href="<s:url value="/css/jquery-ui.css"/>"
	rel="stylesheet" />
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery-ui.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<link type="text/css" rel="stylesheet" href="/js/jquery/galleryView/galleryview.css" />
<script type="text/javascript" src="/js/jquery/galleryView/js/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="/js/jquery/galleryView/js/jquery.galleryview-2.1.1.js"></script>
<script type="text/javascript" src="/js/jquery/galleryView/js/jquery.timers-1.2.js"></script>
<script type="text/javascript">
$(document).ready(function () {
	$.ajax( {
		url : "/user/album/albumgetPicByJson",
		dataType:"json",
		async:false,  	//不进行异步操作
		data : {
		albumId:'${albumId}'
		},
		success : function(data) {
			var userlist = eval('(' +data+ ')');
			 var result="";
		$.each(userlist,function(i,n){  
	 		 result+="<li><span class=\"panel-overlay\">"+n.filename+"</span>";      
			 result+="<img src='"+n.serverPath+"' /> </li>";                           
     });   
      		$("#photos").html(result);
      		
			$('#photos').galleryView({
	            panel_width: 800,
	            panel_height: 300,
	            frame_width: 100,
	            frame_height: 100
	            
	        });
		}
	})
    
    
});

function showUpdate(obj,id){
	$("#name2").attr("value",obj);
	$("#albumId").attr("value",id);
	$("#dialog2").dialog('open');
	$("#dialog2").dialog({
		title:"修改相册属性",
		width:480
	});
}
function picUpdate(obj,filename,filedesc){
	$("#picId").attr("value",obj);
	$("#fileName").attr("value",filename);
	$("#desc").attr("value",filedesc);
	$("#dialog3").dialog('open');
	$("#dialog3").dialog({
		title:"修改相片简介",
		width:480
	});
}
function checkValue2(){
	if  (document.form2.name2.value.length  ==  0)  {  
	alert("请输入相片名称!");
	document.form2.name2.focus();
	return  false;
	}
	return true;
}
function checkValue3(){
	if  (document.form3.desc.value.length  ==  0)  {  
	alert("请输入相片简介!");
	document.form3.desc.focus();
	return  false;
	}
	return true;
}
function show(object){
	document.getElementById(object).style.display="";
}
function notShow(object){
	document.getElementById(object).style.display="none";
}
function openAlbum(obj){
	window.location.href="<s:url value='/user/album/albumopen'/>?id="+obj; 
}
function openPic(album,attId,starPic){
	window.location.href="<s:url value='/user/album/albumopenPic'/>?id="+album+"&&attId="+attId+"&&starPic="+starPic;
}
</script>
<title>管理相册</title>
</head>
<body>
<s:include value="/common/resultMessage.jsp"></s:include>
 <a href="<s:url value="/user/album/albumshow"/>">所有相册</a> > <font color="#FF7202"><s:property value="%{#request.albumInfo.name}"/></font>(共有 <font color="red">${picCount}</font>张)<a onclick="showUpdate('<s:property value="%{#request.albumInfo.name}"/>','<s:property value="%{#request.albumInfo.albumId}"/>')" href="#">修改相册属性</a>
 <a href="<s:url value='/user/album/albumuploadPic'/>?id=<s:property value="%{#request.albumInfo.albumId}"/>">添加新图片</a>&nbsp&nbsp <a href="<s:url value='/user/album/albumshow'/>">返回相册列表</a>
<br />
<hr noshade="noshade" size="1">
<s:if test="result!=''&&result!=null">
	<div id="warning">${result}</div>
</s:if>
<ul id="photos"></ul>
<s:form namespace="/user/album" action="albumadd" id="dialog"
	cssStyle="display:none;" method="get" onsubmit="return checkValue()"
	name="form">
	<table>
		<tr>
			<td><s:textfield label="相册名称" theme="xhtml" name="name"
				id="name" /><span class="errorSpan">${errors.name[0]}</span></td>
		</tr>
		<tr>
			<td><s:textarea label="相册简介" theme="xhtml" name="adesc"
				cols="30" /></td>
		</tr>
		<tr>
			<td><s:submit value="提交" /></td>
		</tr>
	</table>
</s:form>
<s:form namespace="/user/album" action="albumopenUpdate" id="dialog2"
	cssStyle="display:none;" method="get" onsubmit="return checkValue2()"
	name="form2">
	<s:hidden name="id" id="albumId" value="" />
	<table>
		<tr>
			<td><s:textfield label="相册名称" theme="xhtml" name="name"
				id="name2" /><span class="errorSpan">${errors.name[0]}</span></td>
		</tr>
		<tr>
			<td><s:submit value="提交" /></td>
		</tr>
	</table>
</s:form>
<s:form namespace="/user/album" action="albumpicUpdate" id="dialog3"
	cssStyle="display:none;" method="get" onsubmit="return checkValue3()"
	name="form3">
	<s:hidden name="attId" id="picId" value="" />
	<s:hidden name="id" value="%{#request.albumInfo.albumId}"/>
	<table>
	<tr><td>
	<s:textfield name="fileName" label="图片标题" theme="xhtml" id="fileName"/>
	</td></tr>
		<tr>
			<td><s:textarea label="图片简介" theme="xhtml" name="desc"
				id="desc" cols="30"/></td>
		</tr>
		<tr>
			<td><s:submit value="提交" /></td>
		</tr>
	</table>
</s:form>

</body>
</html>