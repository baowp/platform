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
			<input type="hidden" name="id" value="${model.analysisId}" />
			<table width="95%" border="0" cellpadding="0"
				class="tb tb2 fixpadding">
				<tbody>
					<tr class="hover">
						<td><span class="header">类 型</span></td>
						<td align="left"><f:select path="type"
								items="${spel['T(com.abbcc.util.constant.group.GroupAnalysisType)']}"></f:select>
						</td>
					</tr>
					<tr class="hover">
						<td><span class="header">统计代码</span></td>
						<td align="left"><f:textarea path="code" cols="95"
								rows="12" style="width: 99%; height: 250px;" />
						</td>
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
