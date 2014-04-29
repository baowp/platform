<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${metaTitle }</title>
<meta name="description" content="${metaDescription }">
<meta name="keywords" content="${metaKeywords }">
<s:include value="page_style.jsp"/>
</head>
<body class="outBg">
<center>
<s:include value="tool/toolimpl.jsp"/>
<div class="inBg">	
	<s:if test="layout.layoutId!=null">
		<s:include value="page_top.jsp"/>
		<s:include value="page_head.jsp"/>
		<s:include value="page_content.jsp">
			<s:param name="defaultMain" value="layout.mainmoduleList==null&&
				layout.jsonContent['modified'][layout.belongPage]==null?
				'product_default_main.jsp':''"/>
		</s:include>
		<s:include value="page_below.jsp"/>
		<s:include value="page_foot.jsp"/>
	</s:if>
	<s:else>
		<s:include value="page_top.jsp"/>
		<s:include value="default_page_head.jsp"/>
		<s:include value="product_default_content.jsp"/>
		<s:include value="page_below.jsp"/>
		<s:include value="page_foot.jsp"/>
	</s:else>
</div>
</center>
</body>
</html>
