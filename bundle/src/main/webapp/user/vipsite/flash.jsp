<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="refresh" content="40;url=index">
<STYLE type="text/css">
body{margin: 5px;font-size:12px;background:<s:property value="#request.backgroundColor||#parameters.backgroundColor"/>}
.media{margin-top: 0px}
</STYLE>
<title>${metaTitle }</title>
<meta name="description" content="${metaDescription }">
<meta name="keywords" content="${metaKeywords }">
</head>
<body>
<center>
	<div class="media">
		<embed src="<s:url value="%{#request['src']||#parameters.src}"/>" width="<s:property value="#request.width||#parameters.width"/>" 
			height="<s:property value="#request.height||#parameters.height"/>" allowScriptAccess="always">
	</div>
</center>
</body>
</html>