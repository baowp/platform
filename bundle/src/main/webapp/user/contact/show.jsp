<%@ page language="java"
	import="java.util.*,com.abbcc.models.AbcCertificate"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<script type="text/javascript"
	src="<s:url value='/js/datePicker/WdatePicker.js'/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery/colorize.js"/>"></script>
<script type="text/javascript"
	src="<s:url value="/user/js/show_onmouseover.js"/>"></script>
<script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
<link rel="stylesheet" type="text/css" href="<s:url value="/user/abbcc/css/common.css"/>">
</head>
<body>
<s:if test="result!=''&&result!=null">
		<div id="warning" style="DISPLAY: none">${result}</div>
		<script type="text/javascript">
		window.onload   =   new   function()
		{
			parent.makeState($("#warning").html());
		}

		</script>
</s:if>
<br>
<div
	style="width: 100%; text-align: left; color: #ce0000; font-size: 15px; font-weight: bold">
<s:form action="subMembersearch" namespace="/user/subMember">
	<s:textfield name="username" theme="xhtml" label="用户名"
		cssStyle="margin-bottom:5px" />
	<s:textfield name="name" theme="xhtml" label="姓名"
		cssStyle="margin-bottom:5px" />
	<br />
	注册时间: <s:textfield name="frontTime" size="12"
		cssStyle="margin-bottom:5px" onfocus="WdatePicker()" cssClass="Wdate" /> 
			- <s:textfield name="backTime" size="12" onfocus="WdatePicker()"
		cssClass="Wdate" />
	<s:select list="@com.abbcc.module.subMember.DealState@values()"
		listKey="name()" name="dealState" value="dealState.name()"
		theme="xhtml" label="用户状态" headerKey="" headerValue="全部" />
	<input type="image" src="/images/chaxun0.jpg" alt="查询" title="查询"/>
</s:form> <input type="button" onclick="window.location.href='/user/sub_member/sub_member_add.jsp'" value="添加联系人"/>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<a
	href="javascript:void(0)"
	onclick="javascript:showModalDialog('subMembersendEmail',window,'dialogWidth:600px;dialogHeight:800px;dialogTop:100px;dialogLeft:300px')"><input type="button" value="群发EMAIL"/></a>
</div>

<div>

<table class="listTable">
<thead>
	<tr>
		<td >帐号</td>
		<td >姓名</td>
		<td >手机</td>
		<td >添加时间</td>
		<td >操作</td>
	</tr>
</thead>
<tbody>
<s:if test="pageList.totalCount==0">
	<tr><td align="center" colspan="5">没有相关数据!</td></tr>
</s:if>
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
					<td>添加时间:</td>
					<td><s:property value="addTimeString()" /></td>
				</tr>
			</table>
			</div>

			</td>

			<td  valign="middle" align="center">${name}</td>
			<td  valign="middle" align="center">${cellphone}</td>
			<td  valign="middle" align="center"><s:property
				value="addTimeString()" /></td>
			<td  valign="middle" align="center"><a href="javascript:void(0)"
				onclick="javascript:showModalDialog('subMembersendEmail1?receiver=${email}',window,'dialogWidth:600px;dialogHeight:800px;dialogTop:100px;dialogLeft:300px')">发送EMAIL</a>|<a
				href="subMemberdel?userId=${userId}"
				onclick="javascript:return del()">删除</a></td>


		</tr>

	</s:iterator>
	</tbody>
	<tr>


		<th colspan="8" class="tb_search"><s:include
			value="../../common/page.jsp">
			<s:param name="pageList" value="pageList" />
		</s:include></th>

	</tr>
</table>
</div>


</body>
</html>
