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
<jsp:include page="/js/util/info.jsp"></jsp:include>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/group/gaim/js/operate.js"></script>
</head>

<body>
	<div id="cpcontainer" class="container">
		<f:form name="form1" id="form1" commandName="model" action="update" onsubmit="return checkForm()" method="post">
			<input type="hidden" name="id" value="${model.gaimId }"/>
			<table width="95%" border="0" cellpadding="0"
				class="tb tb2 fixpadding">
				<tbody>
					<tr class="hover">
						<td><span class="header">名 称</span></td>
						<td align="left"><f:input path="name" /><span class="errorSpan"><f:errors path="name"></f:errors> </span></td>
					</tr>
					<tr class="hover">
						<td><span class="header">账 号</span></td>
						<td align="left"><f:input path="account" /><span class="errorSpan"></span></td>
					</tr>
					<tr class="hover">
						<td><span class="header">类 型</span></td>
						<td align="left"><f:select path="type" items="${spel['T(com.abbcc.util.constant.group.GroupGaimType)']}"></f:select></td>
					</tr>
					<tr class="hover">
						<td><span class="header">代 码</span></td>
						<td align="left"><f:textarea path="code" rows="8" cols="60"/> </td>
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
