<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<thead>
<tr><th>产品名称</th><th>产品单价</th><th>销售数量</th><th>销售总额</th></tr></thead>
<s:iterator value="#request['statList']">
<tr>
	<s:iterator>
		<td><s:property/></td>
	</s:iterator>
</tr>
</s:iterator>
