<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<META HTTP-EQUIV="expires" CONTENT="Wed, 26 Feb 1997 08:21:57 GMT">
<title>修改友情链接</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/user/abbcc/css/common.css"/>">
<script type="text/javascript" src="<c:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="/js/common.js"></script>
<link href="/user/css/operation.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function checkForm(){
		var name = $.trim($("#name").val());
		var url = $.trim($("#url").val());
		if(!name.length || !url.length) {
			alert("信息输入不完整");
			return false;
		} 
		return true;
	}
	
	</script>
</head>

<body>
	<div id="cpcontainer" class="container">
		<f:form name="form1" id="form1" commandName="model" action="update"
			method="post" onsubmit="return checkForm()">
			<input type="hidden" name="id" value="${model.linkId }"/>
			<table width="95%" border="0" cellpadding="0"
				class="tb tb2 fixpadding">
				<tbody>
					<tr class="hover">
						<td align="center" class="header">链接名称</td>
						<td align="left">
							<span class="vtop rowform">
								<f:input path="name"  class="txt" id="name"/>
							</span>
						</td>
						<td align="left"><span class="errorSpan"><f:errors path="name"/></span>
						</td>
					</tr>
					<tr class="hover">
						<td align="center" class="header">链接地址</td>
						<td align="left"><span class="vtop rowform">
							<f:input path="url" class="txt" id="url" />
						</span>
						</td>
						<td align="left"><span class="errorSpan"><f:errors path="url"/></span>
						</td>
					</tr>
					<tr>
						<td colspan="11">
							<div class="fixsel">
								&nbsp; &nbsp; <input type="submit" value="提交" title="提交" />
								&nbsp; <input type="button" value="返回"
									onclick="javascript:history.go(-1);" src="" />
							</div></td>
					</tr>
				</tbody>
			</table>
		</f:form>
	</div>
</body>
</html>
