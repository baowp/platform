<%@ page language="java"
	import="java.util.*,com.abbcc.models.AbcCertificate"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">

<link type="text/css" href="<s:url value="/css/jquery-ui.css"/>"
	rel="stylesheet" />

<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery/colorize.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery-ui.js"/>"></script>
<script type="text/javascript"
	src="<s:url value="/user/js/show_onmouseover.js"/>"></script>
<script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
<link rel="stylesheet" type="text/css"
	href="<s:url value="/user/abbcc/css/common.css"/>">
</head>

<body>
<br>
<div
	style="width: 100%; text-align: left; color: #ce0000; font-size: 15px; font-weight: bold">
统计管理
<hr />
</div>


<table class="listTable">
	<thead>
		<tr>
			<td >帐号</td>
			<td >姓名</td>
			<td >手机</td>
			<td >注册时间</td>
			<td >状态</td>
		</tr>
	</thead>
	<tbody>
		<s:iterator var="user" value="pageList.items" status="st">

			<tr>
				<input type="hidden" name="userId"
					value="<s:property value='userId'/>" />
				<td  valign="middle" align="center"><a href="#"
					id="<s:property value='#st.index+1'/>"
					onmouseover="show(this,'<s:property value='#st.index+100'/>')"
					onmouseout="hide('<s:property value='#st.index+100'/>')">${username}</a>
				<div id="<s:property value='#st.index+100'/>"
					style="BORDER-RIGHT: black 1px solid; PADDING-RIGHT: 20px; BORDER-TOP: black 1px solid; PADDING-LEFT: 20px; Z-INDEX: 100; VISIBILITY: hidden; PADDING-BOTTOM: 20px; BORDER-LEFT: black 1px solid; WIDTH: 180px; PADDING-TOP: 20px; BORDER-BOTTOM: black 1px solid; POSITION: absolute; background-color: #F0FFFF"
					onmouseover="show(i,'<s:property value='#st.index+100'/>')"
					onmouseout="hide('<s:property value='#st.index+100'/>')"><nobr>详细信息</nobr><br>
				<table>
					<tr>
						<td>姓名:</td>
						<td>${name}</td>
					</tr>
					<tr>
						<td>email:</td>
						<td>${email}</td>
					</tr>
					<tr>
						<td>QQ:</td>
						<td>${qq}</td>
					</tr>
					<tr>
						<td>电话:</td>
						<td>${phone}</td>
					</tr>
					<tr>
						<td>手机:</td>
						<td>${cellphone}</td>
					</tr>
					<tr>
						<td>地址:</td>
						<td>${address}</td>
					</tr>
					<tr>
						<td>注册时间:</td>
						<td><s:property value="addTimeString()" /></td>
					</tr>
				</table>
				</div>

				</td>

				<td  valign="middle" align="center">${name}</td>
				<td  valign="middle" align="center">${cellphone}</td>
				<td  valign="middle" align="center"><s:property
					value="addTimeString()" /></td>
				<td  valign="middle" align="center">${state eq '01'?'正常':'<font
				color="red">停用</font>' }</td>



			</tr>

		</s:iterator>
	</tbody>
	<tr>


		<th colspan="8"><s:include
			value="../../common/page.jsp">
			<s:param name="pageList" value="pageList" />
		</s:include></th>

	</tr>
</table>
<img src="/images/fanhui.jpg" alt="返回" title="返回" onclick="javascript:history.go(-1);"/>

</body>
</html>
