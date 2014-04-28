<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<s:debug/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<s:url value="/admin/css/common.css"/>"
	type="text/css" />
<link rel="stylesheet" href="<s:url value="/admin/css/tinybox.css"/>"
	type="text/css" />
<script type="text/javascript"
	src="<s:url value='/js/datePicker/WdatePicker.js'/>"></script>
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<title>添加付费记录</title>
</head>
<body>
<div id="nav">
<ul>
	<li id="man_nav_1" class="bg_image_onclick">付费记录</li>
	<li>添加用户付费记录</li>
</ul>
</div>
<div id="sub_info">
<div id="man_zone"><s:if test="result!=''&&result!=null">
	<div id="warning">${result}</div>
</s:if> <s:form namespace="/admin" action="addPaylog" method="post" id="form1">
	<table width="99%" border="0" align="center" cellpadding="3"
		cellspacing="1" class="table_style">
		<s:hidden name="state" value="01"></s:hidden>
		<s:hidden name="payuserId"></s:hidden>
		<tr>
			<td width="18%" class="left_title_1"><span class="left-title">付费类型</span>
			</td>
			<td width="82%"><select name="type" id="type">
				<option value="01">银行汇款</option>
				<option value="02">现金</option>
			</select></td>
		</tr>
		<tr>
			<td width="18%" class="left_title_1"><span class="left-title">付费日期</span>
			</td>
			<td width="82%"><input type="text" name="payTime" class="Wdate"
				size="20" onfocus="WdatePicker()" /><span
				class="errorSpan">${errors.payTime[0]}</span></td>
		</tr>
		<tr> 
			<td class="left_title_1">付费银行</td>
			<td>
		<s:bean name="com.abbcc.common.CommonConst" id="const"/> 
			<s:select list="#const.BANKMAP" listKey="key" listValue="value"  headerKey="00" headerValue="请选择银行"/> 
			<span class="errorSpan">${errors.bank[0]}</span></td>
		</tr>
		<tr>
			<td width="18%" class="left_title_1"><span class="left-title">付费账号</span>
			</td>
			<td width="82%"><s:textfield name="bankAccount" /><span
				class="errorSpan">${errors.bankAccount[0]}</span></td>
		</tr>
		<tr>
			<td width="18%" class="left_title_1"><span class="left-title">付费单号</span>
			</td>
			<td width="82%"><s:textfield name="documentId"></s:textfield><span
				class="errorSpan">${errors.documentId[0]}</span></td>
		</tr>
		<tr>
			<td width="18%" class="left_title_1"><span class="left-title">付费金额</span>
			</td>
			<td width="82%"><s:textfield name="amount"></s:textfield><span
				class="errorSpan">${errors.amount[0]}</span></td>
		</tr>
		<tr>
			<td width="18%" class="left_title_1"><span class="left-title">付费开始时间</span>
			</td>
			<td width="82%"><input type="text" name="startTime"
				class="Wdate" size="20"
				onfocus="WdatePicker()" /><span
				class="errorSpan">${errors.startTime[0]}</span></td>
		</tr>
		<tr>
			<td width="18%" class="left_title_1"><span class="left-title">付费结束时间</span>
			</td>
			<td width="82%"><input type="text" name="endTime" class="Wdate"
				size="20" onfocus="WdatePicker()" /><span
				class="errorSpan">${errors.endTime[0]}</span></td>
		</tr>
		<tr>
			<td class="left_title_2"></td>
			<td class="left_title_2" style="text-align: left"><input
				type="submit" name="save" id="submit" value="提交" />
			<button onclick="window.location='viewPaylog?payuserId=<s:property value="payuserId"/>'">返回</button>
			</td>
		</tr>
	</table>
</s:form></div>
</div>
</body>
</html>
