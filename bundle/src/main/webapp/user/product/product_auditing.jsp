<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>审核中的产品</title>
<s:include value="product_style.jsp"/>
</head>
<body>
<s:include value="/common/resultMessage.jsp"></s:include>
<div class="listTab">
	<dl>
		<a href="published">已发布上网</a>
	</dl>
	<dl class="focus">
		审核中
	</dl>
	<dl>
		<a href="unapprove">审核未通过</a>
	</dl>
	<dl>
		<a href="expired">已过期</a>
	</dl>
</div>

<table class="listTable">
	<thead>
		<tr><th width="20%">图片</th><th>标题</th><th>发布时间</th><th>操作</th></tr>
	</thead>
	<s:if test="pageList.totalCount==0">
	<tr><td align="center" colspan="4">没有相关数据!</td></tr>
</s:if>
<s:iterator value="pageList.items">
	<tr>
		<td  align="center">
			<s:action name="fetchAttach" var="att">
				<s:param name="belongId" value="productId"/>
				<s:param name="filedesc" value="@com.abbcc.common.CommonConst@ATTACHPICMAIN"/>
			</s:action>
			<img width="100" height="90"  src="<s:property value="%{#att.resultList[0].picUrl(5)}" />"/>
		</td>
		<td align="center"><s:property value="name"/></td>
		<td align="center"><s:date name="publishTime"/></td>
		<td align="center"><a href="edit?id=${productId }&back=showauditing">修改</a> </td>
	</tr>
</s:iterator>
	<tfoot>
		<tr><th colspan="9"><s:include value="/common/page.jsp"/></th></tr>
	</tfoot>
</table>
</body>
</html>