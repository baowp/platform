<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>公告说明</title>
<link href="/user/css/tex.css" rel="stylesheet" type="text/css" />
<link href="/user/css/operation.css" rel="stylesheet" type="text/css" />
<link href="/user/css/div.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript">
$(function(){
	$(".lidisplay").hide()
	$("#more").click(function(){
		if($("#more").html()=='取消更多'){
			$("#more").html("上传更多")
		}else{
			$("#more").html("取消更多")
		}
		$(".lidisplay").toggle(300)
		
	})

})
</script>
</head>
<style>
 li {list-style-type:none;}
</style>
<body>
<div class="container"><div class="top">
  <h3>上传图片</h3>
</div>
<s:if test="errors.addState[0]!=null">
	<div id="warning">${errors.addState[0]}</div>
</s:if>
<s:form namespace="/user/album" action="albumupload" method="post" enctype="multipart/form-data">
<s:hidden name="pageType" value="uploadPictrue"/>
  <div class="cf">
    <div id="t_01">
请选择您要上传的相册<s:select list="#request['albumMap']" name="id"  listKey="key" listValue="value" value="%{#request.albumInfo.albumId}"  headerKey="0"></s:select></div>
    <div id="t_02">
	<ul>
	<li>
	    <input type="file" name="upload" class="txt"/><input type=button value=删除 onclick="$(this).prev().val('')" />
	    <a href="#" id="more">上传更多</a>&nbsp&nbsp<img src="/images/fanhui.jpg" alt="返回" title="返回" onclick="javascript:history.go(-1);"/>
	</li>
	<li  class="lidisplay">
	    <input type="file" name="upload" class="txt"/><input type=button value=删除 onclick="$(this).prev().val('')" />
	</li>
	<li  class="lidisplay">
	    <input type="file" name="upload" class="txt"/><input type=button value=删除 onclick="$(this).prev().val('')" />
	</li>
		<li  class="lidisplay">
	    <input type="file" name="upload" class="txt"/><input type=button value=删除 onclick="$(this).prev().val('')" />
	</li>
		<li  class="lidisplay">
	    <input type="file" name="upload" class="txt"/><input type=button value=删除 onclick="$(this).prev().val('')" />
	</li>
	  </ul>
	<p><input type="image" src="/user/images/btn04_usercpphoto.jpg" width="98" height="27" border="0" /></p>
    </div>
  </div>
</div>
</s:form>
</body>
</html>
