<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<title>查看图片</title>
<script type="text/javascript">
function checkValue(){
	if  (document.form.fileName.value.length  ==  0)  {  
		alert("图片标题不能为空！");
		document.form.fileName.focus();
		return  false;
		}
		return true;
}
function setName(obj) {
	var $filename = $("#fileName").val();
	if($filename==null){
		alert("请输入图片标题");
		return false;
	}
	$.getJSON("albumpicJsonUpdate?randomNumber="+Math.random(), {
		attId:obj.getAttribute('attId'),
		fileName:$("#fileName").val(),
		desc:$("#desc").val()
	}, function(result) {
		if(result){
			alert('修改成功');
		}else{
			alert('修改失败');
		}
	});
}
</script>
</head>
<body>
<div style="background-color: #eff2f9"><font color="#FF7F50">管理相册</font></div>
<br />
<a href="<s:url value="/user/album/albumshow"/>">所有相册</a> > <a href="<s:url value='/user/album/albumopen'/>?id=<s:property value="%{#request.albumInfo.albumId}"/>"><font color="#FF7202"><s:property value="%{#request.albumInfo.name}"/></font></a>><s:property value="%{#request.picInfo.filename}"/>
<br/>
<div>
	<th colspan="8" class="tb_search"><s:include
			value="page.jsp">
			<s:param name="pageList" value="pageList" />
			<s:param name="urlArgs" value="%{'id='+#request.albumInfo.albumId+'&'+'attId='+#request.picInfo.attId+'&'+'starPicPage='+#request.starPic+'&'}"/>
	</s:include></th>
<s:iterator var="user" value="pageList.items" status="st">
 <hr noshade="noshade" size="1">
<img src="<s:property value="picUrl()"/>">
<hr noshade="noshade" size="1">
<s:textfield name="fileName" id="fileName" label="图片标题" theme="xhtml" value="%{filename}"/><br/>
<s:textarea cols="30" name="desc" id="desc" label="图片描述" theme="xhtml" value="%{filedesc}"></s:textarea><br/>
<a href="javascript:void(0);" onclick=setName(this) attId=${attId}>修改</a>
</s:iterator>


</div>
</body>
</html>