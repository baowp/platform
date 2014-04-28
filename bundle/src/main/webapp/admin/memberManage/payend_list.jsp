<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>会员付费到期</title>
<link rel="stylesheet" href="<s:url value="/admin/css/common.css"/>"
	type="text/css" />
<link rel="stylesheet" href="<s:url value="/admin/css/tinybox.css"/>"
	type="text/css" />
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript"
	src="<s:url value='/js/datePicker/WdatePicker.js'/>"></script>
<script type="text/javascript">
		$(document).ready(function() {
			$("#filter").click(function() {
				window.location="searchMember?searchWord="+$("#searchWord").val();
			});
		});
		function changePayendType(){
			var payendType = $("input[name='payendType']:checked").val();
			if(payendType=="0")
			$("#custom").css("display","block");
			else
			$("#custom").css("display","none");
			
		}
		function goto(page){
		$("#page").val(page);
			document.form1.submit();
		}
</script>
</head>
<body onload="changePayendType()">
<s:form namespace="/admin" action="payEnd" method="post" id="form1">
	<s:hidden name="page" id="page"/>
	<div id="nav">
	<ul>
		<li id="man_nav_1" class="bg_image_onclick">付费到期</li>
		<li>到期时间：<s:radio name="payendType"
			list="%{#{'1':'一周','2':'30天','3':'90天','0':'自定义'}}"
			value="payendType" onclick="changePayendType()"/> 
			<span id="custom" style="display:none"><input type="text" name="payendStart"
			class="Wdate" size="20" value="<s:property value="payendStart"/>"
			onfocus="WdatePicker()"/><span
				class="errorSpan">${errors.payendStart[0]}</span>至 <input
			type="text" name="payendEnd" class="Wdate" size="20"
			value="<s:property value="payendEnd"/>"
			onfocus="WdatePicker()"/><span
				class="errorSpan">${errors.payendEnd[0]}</span></span> <s:submit
			value="确定" /></li>
	</ul>
	</div>
	<div id="sub_info">
	<div id="man_zone"><s:if test="result!=''&&result!=null">
		<div id="warning">${result}</div>
	</s:if>
	<table width="99%" border="0" align="center" cellpadding="3"
		cellspacing="1" id="tablesorter">
		<thead>
			<tr>
				<th class="header" name="endTime">到期日期</th>
				<th class="header" name="startTime">开始日期</th>
				<th class="header" name="payTime">付费日期</th>
				<th class="header" name="username">账号</th>
				<th class="header" name="name">联系人</th>
				<th class="header" name="cellphone">手机</th>
				<th class="header" name="email">email</th>
				<th class="header">操作</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator var="user" value="pageList.items">
				<tr>
					<td><s:property value="payEnd()" /></td>
					<td><s:property value="payStart()" /></td>
					<td><s:property value="payDate()" /></td>
					<td><s:property value="user().username" /></td>
					<td><s:property value="user().name" /></td>
					<td><s:property value="user().cellphone" /></td>
					<td><s:property value="user().email" /></td>
					<td><s:url id="url" action= "viewSendPayendRemind">
						<s:param name="payuserId">
							<s:property value="payuserId" />
						</s:param>
					</s:url> <s:a href="%{url}">发送提醒邮件</s:a></td>
				</tr>
			</s:iterator>

		</tbody>
		<tfoot>
			<tr>
				<th colspan="8" class="tb_search"><s:include
					value="../../common/page.jsp">
					<s:param name="pageList" value="pageList" />
				</s:include></th>
			</tr>
		</tfoot>
	</table>
	</div>
	</div>
</s:form>
</body>
</html>
