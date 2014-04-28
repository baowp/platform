<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table class="tb tb2 ">
		<tbody>
			<tr>
				<td class="td27" width="80">性&nbsp;&nbsp;&nbsp别:</td>
				<td class="vtop rowform"><s:select list="#{'00':'男','01':'女'}"
					 listKey="key" name="#request.user.sex" listValue="value" /></td>
			</tr>
			<tr>
				<td class="td27" >姓&nbsp;&nbsp;&nbsp;名:</td>
			
				<td class="vtop rowform">${user.name}</td>
			</tr>
			<tr>
				<td class="td27" >职&nbsp;&nbsp;&nbsp;务:</td>
			
				<td class="vtop rowform">${user.position }</td>
			</tr>
			<tr>
				<td class="td27" >手&nbsp;&nbsp;&nbsp;机:</td>
			
				<td class="vtop rowform">${user.cellphone}</td>
			</tr>
			<tr>
				<td class="td27" >电&nbsp;&nbsp;&nbsp;话:</td>
			
				<td class="vtop rowform">${user.phone }</td>
			</tr>
			<tr>
				<td class="td27" >传&nbsp;&nbsp;&nbsp;真:</td>
			
				<td class="vtop rowform">${user.fax }</td>
			</tr>
			<tr>
				<td class="td27" >地&nbsp;&nbsp;&nbsp;址:</td>
			
				<td class="vtop rowform">${user.address}</td>
			</tr>
			<td class="td27" >Email:</td>
			
				<td class="vtop rowform">${user.email }</td>
			</tr>
			<td class="td27" >Q&nbsp;&nbsp;&nbsp;Q:</td>
			
				<td class="vtop rowform">${user.qq}</td>
				<td class="vtop tips2"></td>
			</tr>
			<td class="td27" >MSN:</td>
			
				<td class="vtop rowform">${user.msn}</td>
				<td class="vtop tips2"></td>
			</tr>
			<td class="td27" >邮&nbsp;&nbsp;&nbsp;编:</td>
			
				<td class="vtop rowform">${user.zipcode }</td>
				<td class="vtop tips2"></td>
			</tr>
			<td class="td27" >公司主页:</td>
			
				<td class="vtop rowform">${user.url}</td>
				<td class="vtop tips2"><span class="errorSpan">${errors.url[0]}</span></td>
			</tr>
			<tr><td><input type="button" onclick="javascript:history.go(-1)" value="返回"/></td></tr>
		</tbody>
	</table>
</body>
</html>