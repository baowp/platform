<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="refresh" content="20;url=index">
<STYLE type="text/css">						
body{margin: 5px;font-size:12px;background:${empty backgroundColor?param.backgroundColor:backgroundColor }}
.media{margin-top: 0px}
</STYLE>
<title>${metaTitle }</title>
<meta name="description" content="${metaDescription }">
<meta name="keywords" content="${metaKeywords }">
</head>
<body>
<center>
	<div class="media">
		<embed src="${empty src? param.src:src }" width="${empty width? param.width:width }" 
			height="${empty height? param.height:height }" allowScriptAccess="always">
	</div>
</center>
</body>
</html>