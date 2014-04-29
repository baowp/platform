<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>审核未通过的产品</title>
<s:include value="product_style.jsp"/>
<script type="text/javascript">

</script>
</head>
<body>
<s:include value="/common/resultMessage.jsp"></s:include>
<div class="listTab">
	<dl>
		<a href="published">已发布上网</a>
	</dl>
	<dl>
		<a href="auditing">审核中</a>
	</dl>
	<dl class="focus">
		审核未通过
	</dl>
	<dl>
		<a href="expired">已过期</a>
	</dl>
</div>
<table class="listTable">
	<thead>
		<tr><th width="20%">图片</th><th width="">标题</th><th width="">发布时间</th><th>操作</th></tr>
	</thead>
	<s:if test="pageList.totalCount==0">
	<tr><td align="center" colspan="4">没有相关数据!</td></tr>
</s:if>
<s:iterator value="pageList.items">
	<tr>
		<td>
			<s:action name="fetchAttach" var="att">
				<s:param name="belongId" value="productId"/>
				<s:param name="filedesc" value="@com.abbcc.common.CommonConst@ATTACHPICMAIN"/>
			</s:action>
			<img src="<s:property value="%{#att.resultList[0].picUrl(5)}" />"/>
		</td>
		<td><s:property value="name"/></td>
		<td><s:date name="publishTime"/></td>
		<td><a href="edit?id=${productId }&back=showunapprove">修改</a> <a href="remove?id=${productId }&back=showunapprove"><img src="/user/images/pic22.gif" alt="删除" title="删除"/></a></td>
	</tr>
</s:iterator>
	<tfoot>
		<tr><th colspan="9"><s:include value="/common/page.jsp"/></th></tr>
	</tfoot>
</table>
</body>
</html>