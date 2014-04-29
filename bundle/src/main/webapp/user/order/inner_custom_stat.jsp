<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<thead><tr><th>企业名称</th><th>订单数量</th><th>订单总额</th></tr></thead>
<s:iterator value="#request['statList']">
<tr>
	<s:iterator>
		<td><s:property/></td>
	</s:iterator>
</tr>
</s:iterator>
