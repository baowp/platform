<%@ page language="java"
	import="java.util.*,com.abbcc.models.AbcCertificate"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<s:include value="../product/product_style.jsp" />
<script type="text/javascript"
	src="<s:url value="/user/js/show_onmouseover.js"/>"></script>

<script type="text/javascript">
function setState(obj) {
	$.getJSON("updateSubState?randomNumber="+Math.random(), {
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
<s:if test="result!=''&&result!=null">
	<div id="warning" style="DISPLAY: none">${result}</div>
	<script type="text/javascript">
		window.onload   =   new   function()
		{
			parent.makeState($("#warning").html());
		}

		</script>
</s:if>
<div class="fv"><input type="button"
	onclick="window.location.href='/user/account/sub_account_add.jsp'"
	value="添加帐号" /> &nbsp<a href="javascript:searchable()">筛选功能</a></div>
<div class="searchArea"><s:form action="showSubAccount" namespace="/user/account/password">
	<s:textfield name="username" label="账号" theme="xhtml" />
	<s:textfield name="name" label="姓名" theme="xhtml" />
	<s:submit value="查询" />
</s:form></div>
<table class="listTable">
	<thead>
		<tr>
			<th class="up">账号</th>
			<th>姓名</th>
			<th>手机</th>
			<th>权限设置</th>
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
				<input type="hidden" name="userId"
					value="<s:property value='userId'/>" />
				<td valign="middle" align="left"><a href="#"
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
				</table>
				</div>

				</td>

				<td valign="middle" align="left">${name}</td>
				<td valign="middle" align="left">${cellphone}</td>
				<td valign="middle" align="center"><a href="javascript:void(0)"
					onclick=javascript:showModalDialog( "popedomShow?index=${userId}",window,"dialogWidth:600px;dialogHeight:700px;dialogTop:100px;dialogLeft:200px");>设置</a></td>
				<td valign="middle" align="center"><a href="javascript:void(0)"
					onclick=setState(this) userId=${userId
				}
					state=${state eq '01'?'02':'01'}>${state eq '01'?'正常':'停用' }</a></td>
				<td valign="middle" align="center"><a
					href="showUpdateSubAccount?id=${userId}">修改</a>|<a
					href="delSubAccount?id=${userId}" onclick="javascript:return del()">删除</a></td>


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



</body>
</html>
