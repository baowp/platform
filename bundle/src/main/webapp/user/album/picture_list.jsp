<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
body {
	font: 12px/1.2 Verdana, Arial, san-serrif;
	padding: 0 10px;
}

a:link,a:visited {
	text-decoration: none;
	color: #416CE5;
	border-bottom: 1px solid #416CE5;
}

h2 {
	font-size: 13px;
	margin: 15px 0 0 0;
}
</style>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript"
	src="/js/jquery/colorbox/jquery.colorbox-min.js"></script>
<link rel="stylesheet" type="text/css"
	href="/js/jquery/colorbox/colorbox.css" media="screen" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#various3").colorbox({
			width : "80%",
			height : "80%",
			iframe : true
		});

	});
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择相片</title>
</head>
<body>
<body>

<a id="various3" href="/user/album/album_list.jsp">请选择相片</a>

</body>
</html>