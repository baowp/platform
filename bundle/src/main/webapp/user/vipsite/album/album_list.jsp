<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理相册</title>
<link href="/user/css/css.css" rel="stylesheet" type="text/css" />
<link href="/user/css/div.css" rel="stylesheet" type="text/css" />
<link type="text/css" href="<s:url value="/css/jquery-ui.css"/>"
	rel="stylesheet" />
<link id="artDialogSkin" href="/js/artDialog3.0.5/skin/default.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/artDialog3.0.5/artDialog.min.js"></script>	
<script type="text/javascript"
	src="<s:url value="/js/util/context.jsp"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery-ui.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<script type="text/javascript">
$(function(){
	$("#watermark").click(function(){
		art.dialog.open('/user/vipsite/album/watermark.jsp', {
			id : 'watermarkIframe',
			skin : 'aero',
			title : '设置水印(按esc可关闭)',
			left : 150,
			top : 40,
			fixed : true,
			width : 800,
			height : 470
		});
	})
})
function delAlbum(obj) {
	if (confirm('确定删除吗？删除后与您照片相关的图片将无法打开!') == true) {
	$.getJSON("albumdel?randomNumber="+Math.random(), {
		id:obj
	}, function(result) {
		
		if(result.affectRows==2){
			alert('默认相册不能删除!');
		}else if(result.affectRows==1){
			alert('删除成功!');
		}else{
			alert('删除失败!');
		}
		window.location.reload()
	});
	}else{
		return false;
	}
}
function showAlbum(obj){
	$("#dialog").dialog('open');
	$("#dialog").dialog({
		title:"添加相册",
		width:480
	});
}
function showUpdate(obj,id,blongType){
	$("#name2").val(obj);
	$("#oldName").val(obj);
	$("#albumId").val(id);
	$("#blongType").val(blongType);
	$("#dialog2").dialog('open');
	$("#dialog2").dialog({
		title:"修改相册属性",
		width:480
	});
}

function checkValue(){
		if  (document.form.name.value.length  ==  0)  {  
		alert("请输入相片名称!");
		document.form.name.focus();
		return  false;
		}
		return true;
}

function checkValue2(){
	var oldname = $("#oldName").val().trim();
	var name2 = $("#name2").val().trim();
	if(oldname!=name2&&oldname=='默认相册'){
		alert("默认相册不能更改名字！");
		return false;
	}
		
	if  (document.form2.name2.value.length  ==  0)  {  
	alert("请输入相片名称!");
	document.form2.name2.focus();
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
</script>
<style>
#ba ul {
    list-style: none outside none;
}#ba ul {
    margin: 0 auto;
    padding: 0;
}#ba li {
    float: left;
    margin: 0 4px;
    padding: 0;
}
.j {
    background: url("/user/vipsite/album/css/../images/QQt.jpg") no-repeat scroll 0 0 transparent;
    color: #FF6600;
    height: 28px;
    line-height: 28px;
    margin: 2px;
    text-align: center;
    width: 104px;
}
</style>
</head>

<body>
<s:include value="/common/resultMessage.jsp"></s:include>
<div id="body">
<div id="main">
<div id="bc">
<table width="100%" height="25" border="0" cellpadding="0"
	cellspacing="0" bgcolor="#FAF1D6">
	<tr>
		<td width="20">&nbsp;</td>
		<td align="left" class="font_r">管理相册</td>
	</tr>
</table>
</div>
<div id="ba">
<ul>
	<li>所有相册:</li>
	<li>共有${albumCount} 本相册，您可以</li>
	<li class="j"><a href="javascript:showAlbum(this)">新建相册</a></li>
	<li class="j"><a href="javascript:" id="watermark">水印设置</a></li>
	
</ul>
</div>
<div id="ba_content"><s:iterator var="user" value="pageList.items"
	status="st">
	<div class="album"
		onmouseout="notShow(<s:property value='#st.index+1'/>);"
		onmousemove="show(<s:property value='#st.index+1'/>)">
	<div class="wrapper normal">
	<div class="hook"></div>
	<div class="cover">
	<div class="cover-img type-0 no-photo"
		onclick="openAlbum('${albumId}')"><s:if test="picCount()==0">
		<img src="<s:url value="/user/vipsite/album/images/no_photo.gif"/>"
			style="width: 108px; height: 125px;" />
	</s:if> <s:else>
		<img onclick="openAlbum('${albumId}')"
			src="<s:property value="picUrl(5)"/>"
			style="width: 108px; height: 125px;" />
	</s:else></div>
	<div class="operation" style="display: none;"
		id="<s:property value='#st.index+1'/>"><a class="ope-edit"
		href="#" onclick="showUpdate('${name}','${albumId}','${blongType}')">编辑</a><a
		class="ope-del" href="#" onclick="delAlbum('${albumId}')">删除</a></div>
	</div>
	<div class="info" onclick="openAlbum('${albumId}')"><span
		class="title type-0" title="asdas">${name}</span><span class="count">(${picCount})</span></div>
	<div class="info" onclick="openAlbum('${albumId}')"><span
		class="datetime"><s:date name="addTime" format="yyyy-MM-dd" /></span></div>
	</div>
	</div>
</s:iterator></div>
</div>
</div>
</div>
</div>
<s:form namespace="/user/album" action="albumadd" id="dialog"
	cssStyle="display:none;" method="get" onsubmit="return checkValue()"
	name="form">
	<table>
		
		<tr>
			<td>相册名称:</td>
			<td align="left"><s:textfield name="name" id="name2" /></td>
			<td><span class="errorSpan">${errors.name[0]}</span></td>
		</tr>
<tr>
			<td>相册简介:</td>
			<td align="left"><s:textarea name="adesc" cols="30" /></td>
			<td></td>
		</tr>

		<tr>
			<td><s:submit value="提交" /></td>
		</tr>
	</table>
</s:form>
<s:form namespace="/user/album" action="albumupdate" id="dialog2"
	cssStyle="display:none;" method="get" onsubmit="return checkValue2()"
	name="form2">
	<s:hidden name="id" id="albumId" value="" />
	<s:hidden name="oldName" id="oldName" />
	<table>
		<tr>
			<td>设置权限:</td>
			<td align="left"><s:select id="blongType" name="blongType"
				value="blongType.name()"
				list="@com.abbcc.util.constant.AlbumType@values()" listKey="name()" /></td>
			<td></td>
		</tr>
		<tr>
			<td>相册名称:</td>
			<td align="left"><s:textfield name="name" id="name2" /></td>
			<td><span class="errorSpan">${errors.name[0]}</span></td>
		</tr>
		<tr>
			<td><s:submit value="提交" /></td>
		</tr>
	</table>
</s:form>
</body>
</html>