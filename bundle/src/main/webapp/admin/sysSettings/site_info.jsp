<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<s:url value="/admin/css/common.css"/>"
	type="text/css" />
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<title>修改网站信息</title>
</head>
<body>
<div id="nav">
<ul>
	<li id="man_nav_1" class="bg_image_onclick">修改网站信息</li>
</ul>
</div>
<div id="sub_info">
<div id="man_zone"><s:if test="result!=''&&result!=null">
	<div id="warning">${result}</div>
</s:if><s:form namespace="/admin" action="editSiteInfo" method="post">
	<s:token />
	<table width="99%" border="0" align="center" cellpadding="3"
		cellspacing="1" class="table_style">
		<tr>
			<td width="18%" class="left_title_1">网站编号</td>
			<td width="82%"><s:textfield name="siteid" /><span
				class="errorSpan">${errors.siteid[0]}</span></td>
		</tr>
		<tr>
			<td class="left_title_1"><span class="left-title">网站名称</span></td>
			<td><s:textfield name="sitename" /><span class="errorSpan">${errors.sitename[0]}</span></td>
		</tr>
		<tr>
			<td width="18%" class="left_title_1"><span class="left-title">网站网址</span>
			</td>
			<td width="82%"><s:textfield name="url" /><span
				class="errorSpan">${errors.url[0]}</span></td>
		</tr>
		<tr>
			<td width="18%" class="left_title_1"><span class="left-title">公司名称</span>
			</td>
			<td width="82%"><s:textfield name="company" /><span
				class="errorSpan">${errors.company[0]}</span></td>
		</tr>
		<tr>
			<td width="18%" class="left_title_1"><span class="left-title">公司地址</span>
			</td>
			<td width="82%"><s:textfield name="address" /><span
				class="errorSpan">${errors.address[0]}</span></td>
		</tr>
		<tr>
			<td width="18%" class="left_title_1"><span class="left-title">邮编</span>
			</td>
			<td width="82%"><s:textfield name="zipcode" /><span
				class="errorSpan">${errors.zipcode[0]}</span></td>
		</tr>
		<tr>
			<td width="18%" class="left_title_1"><span class="left-title">联系电话</span>
			</td>
			<td width="82%"><s:textfield name="phone" /><span
				class="errorSpan">${errors.phone[0]}</span></td>
		</tr>
		<tr>
			<td width="18%" class="left_title_1"><span class="left-title">传真</span>
			</td>
			<td width="82%"><s:textfield name="fax" /><span
				class="errorSpan">${errors.fax[0]}</span></td>
		</tr>
		<tr>
			<td width="18%" class="left_title_1"><span class="left-title">联系人</span>
			</td>
			<td width="82%"><s:textfield name="contact" /><span
				class="errorSpan">${errors.contact[0]}</span></td>
		</tr>
		<tr>
			<td width="18%" class="left_title_1"><span class="left-title">email</span>
			</td>
			<td width="82%"><s:textfield name="email" /><span
				class="errorSpan">${errors.email[0]}</span></td>
		</tr>
		<tr>
			<td class="left_title_2"></td>
			<td class="left_title_2"><s:submit value="修改" /></td>
		</tr>
	</table>
</s:form></div>
</div>
</body>
</html>
