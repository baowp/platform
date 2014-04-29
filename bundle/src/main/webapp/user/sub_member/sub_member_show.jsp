<%@ page language="java"
	import="java.util.*,com.abbcc.models.AbcCertificate"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查看客户</title>
<link href="/user/help/style/textd.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<s:url value='/js/datePicker/WdatePicker.js'/>"></script>
<script type="text/javascript"
	src="<s:url value="/user/js/show_onmouseover.js"/>"></script>
<s:include value="../product/product_style.jsp" />
<script type="text/javascript">
function setState(obj) {
	$.getJSON("subMemberupdateState?randomNumber="+Math.random(), {
		userId:obj.getAttribute('userId'),
		state:obj.getAttribute("state")
	}, function(result) {
		if(result.affectRows==1){
			if(obj.getAttribute("state")=='01'){
				obj.setAttribute("state",'02');
				obj.innerHTML="正常";
			}else{
				obj.setAttribute("state",'01');
				obj.innerHTML="停用";
			}
		}
	});
}
</script>
</head>

<body>
<s:include value="/common/resultMessage.jsp"></s:include>
<div id="cpcontainer" class="container">
<div class="fv"><input type="button"
	onclick="window.location.href='/user/sub_member/sub_member_add.jsp'"
	value="添加客户" />&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input
	onclick="javascript:showModalDialog('subMembersendEmail',window,'dialogWidth:600px;dialogHeight:800px;dialogTop:100px;dialogLeft:300px')"
	type="button" value="群发Email" />&nbsp<a href="javascript:searchable()">筛选功能</a></div>
<div class="searchArea"><s:form action="subMembersearch" namespace="/user/subMember">
	<div class="topq">
	<div class="gus">
	<div class="nv">用&nbsp;户&nbsp;名：</div>
	<div class="bn"><input type="text" name="username" id="form05" />
	</div>
	<div class="nv">姓&nbsp;&nbsp;&nbsp;名：</div>
	<div class="bn"><input type="text" name="name" id="form05" /></div>
	<div class="nv">注册时间：</div>
	<div class="bn"><input type="text" name="frontTime" id="form02"
		onfocus="WdatePicker()" class="Wdate" /> <input type="text"
		name="backTime" id="form03" onfocus="WdatePicker()" class="Wdate" /></div>
	<div class="nv">用户状态：</div>
	<div class="bns"><s:select id="select04"
		list="@com.abbcc.module.subMember.DealState@values()" listKey="name()"
		name="dealState" value="dealState.name()" headerKey=""
		headerValue="全部" /></div>
	<div class="ty"><input type="submit" name="button6" id="button6"
		value="查询" /></div>
	</div>
	</div>
</s:form></div>
<table class="listTable">
	<thead>
		<tr>
			<th>帐号</th>
			<th>姓名</th>
			<th>手机</th>
			<th>注册时间</th>
			<th>状态</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:if test="pageList.totalCount==0">
			<tr>
				<td align="center" colspan="6">没有相关数据!</td>
			</tr>
		</s:if>
		<s:iterator var="user" value="pageList.items" status="st">

			<tr>
				<td valign="middle" align="left"><input type="hidden"
					name="userId" value="<s:property value='userId'/>" /><a href="#"
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

				<td valign="middle" align="center">${name}</td>
				<td valign="middle" align="center">${cellphone}</td>
				<td valign="middle" align="center"><s:property
					value="addTimeString()" /></td>
				<td valign="middle" align="center"><a href="javascript:void(0)"
					onclick=setState(this) userId=${userId
					} state=${state eq '01'?'02':'01'}>${state eq '01'?'正常':'停用' }</a></td>
				<td valign="middle" align="center"><a href="javascript:void(0)"
					onclick="javascript:showModalDialog('subMembersendEmail1?receiver=${email}',window,'dialogWidth:600px;dialogHeight:800px;dialogTop:100px;dialogLeft:300px')">Email</a>
				| <a href="subMemberdel?id=${userId}"
					onclick="javascript:return del()">删除</a></td>


			</tr>

		</s:iterator>
	</tbody>
	<tfoot>
		<tr>


			<th colspan="8"><s:include value="../../common/page.jsp">
				<s:param name="pageList" value="pageList" />
			</s:include></th>

		</tr>
	</tfoot>
</table>
</div>
</body>
</html>
