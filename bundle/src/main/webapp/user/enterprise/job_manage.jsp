<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>招聘管理</title>
<script type="text/javascript" src="/user/js/show_onmouseover.js"></script>
<s:include value="../product/product_style.jsp" />
</head>
<body>
<s:include value="/common/resultMessage.jsp"></s:include>
<div class="listTab">
<dl class="${pageType=='pass'?'focus':'' }">
	<a href="${pageType!='pass'?'recruitManage':'#' }">审核通过</a>
</dl>
<dl class="${pageType=='search'?'focus':'' }">
	<a href="${pageType!='search'?'search':'#' }">审核中</a>
</dl>
<dl class="${pageType=='expired'?'focus':'' }">
	<a href="${pageType!='expired'?'expired':'#' }">已过期</a>
</dl>

	<input type="button"
		onclick="javascript:window.location.href='/user/enterprise/job_add_page.jsp'"
		value="添加招聘" />


	<a href="javascript:searchable()">筛选功能</a>

</div>
<s:form namespace="/user/enterprise/job" action="searchByType">
	<div class="searchArea">
	<table>
		<tr>
			<td><s:textfield name="searchValue" cssClass="txt" /></td>
			<td><s:radio name="searchType"
				list="%{#{'byTitle':'标题','byDuty':'职务'}}" value="'byTitle'" /></td>
			<td><s:submit value="查询" /></td>
		</tr>
	</table>
	</div>
</s:form>
<table class="listTable">
	<thead>
		<tr>
			<th width="20%">标题</th>
			<th width="20%">职务</th>
			<th width="10%">招聘人数</th>
			<th width="15%">有效期</th>
			<th width="20%">状态</th>
			<th width="15%">操作</th>
		</tr>
	</thead>
	<s:if test="pageList.totalCount==0">
		<tr>
			<td align="center" colspan="6">没有相关数据!</td>
		</tr>
	</s:if>
	<tbody>
		<s:iterator var="user" value="pageList.items" status="st">

			<tr>
				<input type="hidden" " name="cerId" value="" />
				<td valign="middle" align="center"><a href="#"
					id="<s:property value='#st.index+1'/>"
					onmouseover="show(this,'<s:property value='#st.index+100'/>')"
					onmouseout="hide('<s:property value='#st.index+100'/>')"><s:property
					value="title" /></a>
				<div id="<s:property value='#st.index+100'/>"
					style="BORDER-RIGHT: black 1px solid; PADDING-RIGHT: 20px; BORDER-TOP: black 1px solid; PADDING-LEFT: 20px; Z-INDEX: 100; VISIBILITY: hidden; PADDING-BOTTOM: 20px; BORDER-LEFT: black 1px solid; WIDTH: 180px; PADDING-TOP: 20px; BORDER-BOTTOM: black 1px solid; POSITION: absolute; background-color: #F0FFFF"
					onmouseover="show(<s:property value='#st.index+100'/>,'<s:property value='#st.index+100'/>')"
					onmouseout="hide('<s:property value='#st.index+100'/>')"><nobr>详细信息</nobr><br>
				<table>
					<tr>
						<td>标题:</td>
						<td>${title}</td>
					</tr>
					<tr>
						<td>职务:</td>
						<td>${duty}</td>
					</tr>
					<tr>
						<td>人数:</td>
						<td>${sum}</td>
					</tr>
					<tr>
						<td>内容:</td>
						<td>${content}</td>
					</tr>
					<tr>
						<td>有效期:</td>
						<td><s:property value="endTimeString()" /></td>
					</tr>
				</table>
				</div>
				</td>

				<td valign="middle" align="center">${duty}</td>
				<td valign="middle" align="center">${sum}</td>
				<td valign="middle" align="center"><s:property
					value="endTimeString()" /></td>
				<td valign="middle" align="center"><s:if test="state==00">
					<font color="red">未审核</font>
				</s:if><s:if test="state==01">通过审核</s:if></td>
				<td valign="middle" align="center"><s:if
					test="%{#request.pageType=='expired'}">
					<a href="resend?id=${jobId}">重发</a>|</s:if><a
					href="showUpdateIssuanceRecruit?recruitId=<s:property value='jobId'/>">修改</a>|<a
					href="delIssuanceRecruit?id=<s:property value='jobId'/>&&pageType=${pageType}"
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
</body>
</html>