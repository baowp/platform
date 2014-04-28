<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<li>
	用户名：<s:textfield  name="username"/>
	域名/地址：<s:textfield name="address"/> 
			<s:submit cssClass="search" value="查询"/>
</li>