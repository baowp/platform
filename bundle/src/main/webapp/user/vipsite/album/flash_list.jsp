<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/user/css/css.css" rel="stylesheet" type="text/css" />
<link href="/user/css/div.css" rel="stylesheet" type="text/css" />
<link type="text/css" href="<s:url value="/css/jquery-ui.css"/>"
	rel="stylesheet" />
<script type="text/javascript"
	src="/js/util/context.jsp"></script>
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery-ui.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<script type="text/javascript">
function show(object){
	document.getElementById(object).style.display="";
}
function notShow(object){
	document.getElementById(object).style.display="none";
}
function delFlash(obj) {
	if (confirm('确定删除吗？删除后与您的flash在页面中将无法显示!') == true) {
	$.getJSON("albumdelFlash?randomNumber="+Math.random(), {
		attId:obj
	}, function(result) {
		if(result.affectRows==1){
			alert('删除成功!');
		}else{
			alert('删除失败!');
		}
		window.location.reload()
	});
	}
}
function showUpdate(id,name){
	$("#attId").val(id)
	$("#filename").val(name)
	$("#dialog").dialog('open');
	$("#dialog").dialog({
		title:"修改FLASH属性",
		width:480
	});
}

function checkValue(){
		if  (document.form.filename.value.length  ==  0)  {  
		alert("请输入flash名称!");
		document.form.name.focus();
		return  false;
		}
		return true;
}
</script>
<style type="text/css">
.album .cover {
    background: url("") no-repeat scroll 50% 0 transparent;
}
</style>
</head>
<body>
<div id="body">
<div id="main">
<div id="bc">
<table width="100%" height="25" border="0" cellpadding="0"
	cellspacing="0" bgcolor="#FAF1D6">
	<tr>
		<td width="20">&nbsp;</td>
		<td align="left" class="font_r">管理FLASH</td>
	</tr>
</table>
</div>
<div id="ba">
<table width="100%" height="30" border="0" cellpadding="0"
	cellspacing="0" bgcolor="#FFFFFF">
	<tr>
		<td width="7">&nbsp;</td>
		<td width="12"><img src="/user/images/2002912.gif" width="12"
			height="12" /></td>
		<td width="167">&nbsp;<span class="font_h">共有
		${pageList.totalCount} 个flash，您可以</span></td>
		<td width="99" align="left"><a
			href="/user/vipsite/album/flash_upload.jsp?pageType=1">上传flash</a></td>
		<td width="600" align="left" class="font_h"></td>

	</tr>
</table>
</div>
<div id="ba_content"><s:iterator var="user" value="pageList.items"
	status="st">
	<div class="album"
		onmouseout="notShow(<s:property value='#st.index+1'/>);"
		onmousemove="show(<s:property value='#st.index+1'/>)">
	<div class="wrapper normal">
	<div class="hook"></div>
	<div class="cover">
	<div class="cover-img type-0"><embed
		src="<s:property value="flashUrl(serverPath)"/>" wmode="transparent"
		menu="true" /></div>
	<div class="operation" style="display: none;"
		id="<s:property value='#st.index+1'/>"><a class="ope-edit"
		href="#" onclick="showUpdate('${attId}','${filename}')">编辑</a><a class="ope-del" href="javascript:delFlash('${attId}')">删除</a></div>
	</div>
	<div class="info"><span class="title type-0" title="asdas">${filename}</span></div>
	<div class="info"><span
		class="datetime"><s:date name="uploadTime" format="yyyy-MM-dd" /></span></div>
	</div>
	</div>
</s:iterator></div>
</div>
</div>
<s:form namespace="/user/album" action="albumeditFlash" id="dialog"
	cssStyle="display:none;" method="get" onsubmit="return checkValue()"
	name="form">
	<table>
		<s:hidden name="attId" id="attId"></s:hidden>
		<tr>
			<td>Flash名称:</td>
			<td align="left"><s:textfield name="fileName" id="filename" /></td>
			<td><span class="errorSpan">${errors.name[0]}</span></td>
		</tr>
		<tr>
			<td><s:submit value="提交" /></td>
		</tr>
	</table>
</s:form>
</body>
</html>