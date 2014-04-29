<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${seo.title }</title>
<meta name="description" content="${seo.description }">
<meta name="keywords" content="${seo.keywords }">
<c:import url="page_style.jsp" />
</head>
<body>
	<div class="outBg">
		<c:import url="tool/toolimpl.jsp" />
		<div class="inBg">
			<c:import url="page_top.jsp" />
			<c:import url="page_head.jsp" /> 
			<c:import url="page_content.jsp" />
			<c:import url="page_below.jsp" />  
		</div>
	</div>
</body>
</html>
