<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<META HTTP-EQUIV="expires" CONTENT="Wed, 26 Feb 1997 08:21:57 GMT">
<link rel="stylesheet" type="text/css"
	href="<s:url value='/user/css/validation.css'/>">
<title>证书修改</title>

<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="/js/util/onload_colorbox.js"></script>
<link rel="stylesheet" href="<s:url value="/css/style.css"/>" />
<link rel="stylesheet" type="text/css" href="<s:url value="/user/abbcc/css/common.css"/>">
<link href="/user/css/operation.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<s:url value='/user/enterprise/js/impl.js'/>"></script>
<script type="text/javascript" src="/js/common.js"></script>

</head>

<body>
<div id="cpcontainer" class="container">
  <table width="100%" class="tb tb2 " id="tips">
<tbody><tr>
  <th class="partition">证书修改</th>
</tr>
</tbody></table>
<s:form name="form1" id="form1" namespace="/user/enterprise/certificate"
	action="certificateUpdate" method="post" enctype="multipart/form-data" >
	<input type="hidden" name="id"
	value="<s:property value="#request.Certificate.certificateId" />">
<table width="95%" border="0" cellpadding="0" class="tb tb2 fixpadding">
<tbody>
<tr class="hover"><td width="8%" height="94" class="header">&nbsp;</td>
<td width="52%" align="left"><div class="photo"><img id="showPic"
			src="<s:property value="picUrl()"/>"
			width="108px" height="125px" /></div></td>
  <td width="40%" align="left">&nbsp;</td>
</tr>
<tr class="hover">
  <td align="center" class="header">证书图片</td>
  <td align="left"><span class="vtop rowform">
  <s:textfield name="picPath" id="picPath" value="%{picUrlPath()}" readonly="true"/><a
				id="various3" href="<s:url value="/user/album/albumshowUploadPage"/>">点击选择相片</a></span></td>
  <td align="left">&nbsp;</td>
</tr>
<tr class="hover">
  <td align="center" class="header"><span class="header">证书名称</span></td>
<td align="left"><span class="vtop rowform">
  <input type="text" css="txt" name="name" id="name"
			value="<s:property value="#request.Certificate.name" />" /></td>
  <td align="left"><span class="errorSpan">${errors.name[0]}</span></td>
</tr>
<tr class="hover">
  <td align="center" class="header">证书类型</td>
  <td align="left"><div class="certif">
    <select name="select">
     <option value="01">基本证书</option>
				<option value="02">一般证书</option>
				<option value="03">税务证书</option>
				<option value="04">荣誉证书</option>
				<option value="05">其它证书</option>
    </select>
  </div></td>
  <td align="left">&nbsp;</td>
</tr>
<tr class="hover">
  <td align="center" class="header">发证机构</td>
<td align="left"><span class="vtop rowform">
  <input type="text" name="organize" class="txt" id="organize"
			value="<s:property value="#request.Certificate.organize"/>" />
</span></td>
  <td align="left"><span class="errorSpan">${errors.organize[0]}</span></td>
</tr>
<tr>
  <td colspan="11"><div class="fixsel">
    &nbsp;
    &nbsp;<input type="image" src="/images/add.jpg"  border="0" alt="提交" title="提交" onclick="javascript:return update()" />
  &nbsp;
  <img src="/images/fanhui.jpg"  border="0" alt="返回" title="返回" onclick="javascript:history.go(-1);"/>
  </div></td>
</tr>
</tbody></table>
</s:form>
</div>
</body>
</html>
