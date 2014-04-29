<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>询盘管理</title>
<s:include value="../product/product_style.jsp" />
<script type="text/javascript">
	$(function() {
		$("#checkbox").click(
				function() {
					if (this.checked) {
						$(".listTable").find("#" + $(this).attr("name")).each(
								function() {
									$(this).attr("checked", true);
								})
					} else {
						$(".listTable").find("#" + $(this).attr("name")).each(
								function() {
									$(this).attr("checked", false);
								})
					}
				})
	})
	function all_del(ch) {
		var checkboxs = "";
		var result = del();
		if (result == false)
			return false;
		$(".listTable").find("#" + ch).each(function() {
			if (this.checked) {
				checkboxs = checkboxs + $(this).attr("fieldValue") + ","
			}
		})
		if (checkboxs == "") {
			alert('您还未选中你要操作的消息！');
			return;
		}
		window.location.href = "allDel?allId="
				+ checkboxs;

	}
</script>
</head>

<body>
<s:include value="/common/resultMessage.jsp"></s:include>
<div class="listTab">

<dl class="focus">
	<a href="javascript:">客户询盘</a>
</dl>
<input type="button" onclick="all_del('ch')" value="删除询价信息" />&nbsp<a
	href="javascript:searchable()">筛选功能</a></div>
<div class="searchArea"><s:form action="view" namespace="/inquiry">
	<table>
		<tr>
			<td>标题</td>
			<td><s:textfield name="title" /></td>
			<td><s:submit value="查询" /></td>
		</tr>
	</table>
</s:form></div>
<table class="listTable">
	<thead>
		<tr>
			<th width="5%" height="24">
			<div class="f01"><input type="checkbox" name="ch" id="checkbox" /></div>
			</th>
			<th width="4%" height="24" valign="middle"><img
				src="images/fs.png" width="18" height="13" /></th>
			<th width="15%" height="24">
			<div class="f02">发送人</div>
			</th>
			<th width="37%" height="24">
			<div class="f02">主题</div>
			</th>
			<th width="23%" height="24">
			<div class="f02">时间</div>
			</th>
			<th width="16%">
			<div class="f02">操作</div>
			</th>
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
				<td valign="middle" align="center"><input type="checkbox"
					fieldValue="${inquiryId}" name="ch" id="ch" /></td>
				<td valign="middle" align="center">${state=='01'?'<img
				src="/user/images/myali_icon02.gif"/>':'<img
				src="/user/images/icon-mail2.gif"/>'}</td>
				<td valign="middle" align="center">${inquiryName}</td>
				<td valign="middle" align="center"><a href="viewInfo?inquiryId=${inquiryId}"
					title="${title}"> <s:if test="title.length()>18">
					<s:property value="title.substring(0,18)" />
				</s:if><s:else> ${title}</s:else></a></td>
				<td valign="middle" align="center"><s:date name="addTime"
					format="yyyy-MM-dd hh:mm:ss" /></td>

				<td valign="middle" align="center"><a
					href="del?inquiryId=${inquiryId}" onclick="javascript:return del()">删除</a></td>


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
