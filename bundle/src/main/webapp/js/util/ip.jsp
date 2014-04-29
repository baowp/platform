<%@ page language="java" contentType="text/javascript; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
var hostIP="<s:property value="@org.apache.struts2.ServletActionContext@getRequest().getHeader('X-Real-IP')"/>";
