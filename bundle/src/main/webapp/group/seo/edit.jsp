<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<META HTTP-EQUIV="expires" CONTENT="Wed, 26 Feb 1997 08:21:57 GMT">
<title></title>
<script type="text/javascript" src="<c:url value="/js/jquery.js"/>"></script>
<link href="/user/css/operation.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/common.js"></script>
</head>

<body>
	<div id="cpcontainer" class="container">
		<f:form name="form1" id="form1" commandName="model" action="save"
			method="post">
			<input type="hidden" name="id" value="${model.seoId}" />
			<input type="hidden" name="page" value="${param.page }" />
			<input type="hidden" name="navigatorId" value="${param.navigatorId }" />
			<table width="95%" border="0" cellpadding="0"
				class="tb tb2 fixpadding">
				<tbody>
					<tr class="hover">
						<td><span class="header">页面路径</span>
						</td>
						<td align="left">${param.name }</td>
					</tr>
					<tr class="hover">
						<td><span class="header">页面标题</span>
						</td>
						<td align="left"><f:input path="title" /></td>
					</tr>
					<tr class="hover">
						<td><span class="header">关 键 字</span>
						</td>
						<td align="left"><f:input path="keywords" /></td>
					</tr>
					<tr class="hover">
						<td><span class="header">描&nbsp;&nbsp;&nbsp;&nbsp;述</span>
						</td>
						<td align="left"><f:textarea path="description" cols="95"
								rows="8" style="width: 80%; height: 150px;" /></td>
					</tr>
					<tr>
						<td colspan="3">
							<div class="fixsel">
								&nbsp; &nbsp; <input type="submit" value="提交"> &nbsp; <input
									type="button" value="返回" onclick="javascript:history.go(-1);" />
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</f:form>
	</div>
</body>
</html>
