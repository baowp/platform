<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>搜索结果</title>
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<link type="text/css" href="/css/jquery-ui.css" rel="stylesheet" />
<script type="text/javascript" src="<s:url value="/js/jquery-ui.min.js"/>"></script>
<script type="text/javascript"
	src="<s:url value="/js/util/context.jsp"/>"></script>
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<s:if test="%{#request.pageType==''}">
	<script type="text/javascript" src="<s:url value="/js/index.js"/>"></script>
</s:if>
<s:if test="%{#request.pageType=='product'}">
	<script type="text/javascript" src="<s:url value="/js/index.js"/>"></script>
</s:if>
<s:if test="%{#request.pageType=='enterprise'}">
	<script type="text/javascript"
		src="<s:url value="/js/index_enterprise.js"/>"></script>
</s:if>
<s:if test="%{#request.pageType=='news'}">
	<script type="text/javascript" src="<s:url value="/js/index_news.js"/>"></script>
</s:if>
<link rel="stylesheet" rev="stylesheet"
	href="<s:url value='/css/info.css'/>" type="text/css">

</head>
<body>
<table width="100%">
	<tr>
		<td align="right"><s:if test="#session.loginuserid!=null">
			<div align="right"><a href="user/platform/index.jsp">用户中心</a>&nbsp<a
				href="<s:url value="/user/userLogout"/>?pageType='index'"
				onClick="return logout();">注销</a></div>
		</s:if> <s:else>
			<div align="right"><a href="user/login.jsp">登陆</a>&nbsp<a
				href="<s:url value="/user/show"/>">注册</a></div>
		</s:else></td>
	</tr>
	<tr>
		<td><s:form action="byNameSearch" name="form1" id="form1">
			<table>
				<s:hidden name="thisPage" id="thisPage"/>
				<s:hidden name="entId" id="entId" />
				<s:hidden name="pageTypeHidden" value="%{#request.pageType}" />
				<tr>
					<td><a href="<s:url value="/index.jsp"/>"><img
						valign="top" src="<s:url value="/images/logo.gif"/>" height="40"
						alt="Abbcc" /></a></td>
					<td colspan="2" style="font-size: 16px; font-weight: bold"><s:if
						test="%{#request.pageType=='product'}">产品</s:if> <s:else>
						<a href="#" onclick="checkType('product')">产品</a>
					</s:else>&nbsp <s:if test="%{#request.pageType=='enterprise'}">公司</s:if> <s:else>
						<a href="#" onclick="checkType('enterprise')">公司</a>
					</s:else>&nbsp <s:if test="%{#request.pageType=='news'}">资讯</s:if> <s:else>
						<a href="#" onclick="checkType('news')">资讯</a>
					</s:else></td>
				</tr>
				<tr>
					<td><s:textfield  name="entName" id="entName"
						cssStyle="width:300px;height:20px;font-size:14px"
						autocomplete="off" value="%{#request.searchName}" />
					<div id="suggestEntName"
						style="display: none; border: 1px solid #3366CC; width: 300px; position: absolute; left: 1; top: 49; background-color: White; z-index: 0"></div>
					</td>
					<td><input type="submit" value="搜索一下" style="font-size: 14px"
						style="width: 80px; height: 28px;" /></td>
				</tr>
			</table>
		</s:form>
		<hr>
		<div align="right" style="background: #FFEBCD; margin-right: 2px">搜索<font
			color="red">${searchName}</font>的结果约为<font color="red">${resultSize}</font>条，本页显示1~20条信息，搜索时间为<font
			color="red">${runTime}</font>秒!</div>
		<br />
		<ul style="width: 98%; list-style-type: none;" id="shopping_list">

			<s:iterator value="%{#request.searchList}">
				<li style="width: 75%; float: left">
				<table>
					<s:if test="%{#request.pageType=='product'}">
						<tr>
							<td><a
								href="<s:url value="/"/><s:property value='productPath(enterpriseId,productId)'/>"
								target="_blank">${name}</a></td>
						</tr>
						<tr>
							<td><s:property
								value="searchContent(pkey,#request.searchName)" escape="false" /></td>
						</tr>
						<tr>
							<td><a
								href="<s:url value="/"/><s:property value="enterprisePath(enterpriseId)"/>"
								target="_blank"><s:property value='entName(enterpriseId)' /></a></td>
							<td>经营地点:<s:property value='entAddress(enterpriseId)' /></td>
						</tr>
					</s:if>
					<s:if test="%{#request.pageType=='enterprise'}">
						<tr>
							<td><a
								href="<s:url value="/"/><s:property value='enterprisePath()'/>"
								target="_blank"><s:property
								value="searchContent(name,#request.searchName)" escape="false" /></a></td>
						</tr>
						<tr>
							<td><s:property
								value="searchContent(edesc,#request.searchName)" escape="false" /></td>
						</tr>
						<tr>
							<td><a
								href="<s:url value="/"/><s:property value='enterpriseSupper()'/>"
								target="_blank">该公司所有供应信息</a></td>
						</tr>
					</s:if>
					<s:if test="%{#request.pageType=='news'}">
						<tr>
							<td><a
								href="<s:url value="/"/>?url=<s:property value="staticpath"/>"
								target="_blank"><s:property
								value="searchContent(title,#request.searchName)" escape="false" /></a></td>
							<td><s:date name="addTime" format="yyyy-MM-dd hh:mm:ss" /></td>
						</tr>
						<tr>
							<td><s:property
								value="searchContent(nkey,#request.searchName)" escape="false" /></td>
						</tr>
					</s:if>
				</table>

				<br />
				</li>
			</s:iterator>

			<div align="right" style="margin-right: 2px"><s:if
				test="%{#request.isNull=='01'}">
				<table style="background-color: #F0FFFF;">
					<tr>
						<td>暂时没有该产品的推广，如果您想在这里推广您的产品，请致电0579-87171989</td>
					</tr>

				</table>
			</s:if> <s:else>
				<s:iterator value="%{#request.popularizeList}">

					<table style="background-color: #F0FFFF;">
						<tr>
							<td><a href="${url}" target="_blank"><s:property
								value="searchContent(pName,#request.searchName)" escape="false" /></a></td>
						</tr>
						<tr>
							<td><s:property
								value="searchContent(content,#request.searchName)"
								escape="false" /></td>
						</tr>
					</table>
				</s:iterator>
			</s:else></div>
		</ul>
		</td>
	</tr>
	<s:if test="%{#request.resultSize!=0}">
	<tr>
		<td><s:include value="/common/infoPagination.jsp"></s:include></td>
	</tr>
	</s:if>
	<s:if test="%{#request.resultSize!=0}">
		<tr>
			<td align="center"><s:form action="byNameSearch">
				<table>
					<s:hidden name="entId" id="entId" />
					<s:hidden name="pageTypeHidden" value="%{#request.pageType}" />
					<tr>
						<td><s:textfield name="entName" id="entName"
							cssStyle="width:300px;height:20px;font-size:14px"
							autocomplete="off" value="%{#request.searchName}" />
						<div id="suggestEntName"
							style="display: none; border: 1px solid #3366CC; width: 300px; position: absolute; left: 1; top: 49; background-color: White; z-index: 0; padding: 2px"></div>
						</td>
						<td><input type="submit" value="搜索一下" style="font-size: 14px"
							style="width: 80px; height: 28px;" / /></td>
					</tr>
				</table>
			</s:form></td>
		</tr>
	</s:if>
</table>
</body>
</html>