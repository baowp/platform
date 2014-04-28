<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="<s:url value='/user/css/validation.css'/>">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<center><h2>会员统计</h2>点击数字可查看详细</center>
<hr>

贵公司, 目前共有注册会员<span class="errorSpan">${allCount}</span> 人，近一个月注册的有<span class="errorSpan"><a href="<s:url value='/admin/showMonthStat'/>?pageType=admin">${monthCount}</a></span> 人，今天注册的有<span class="errorSpan"><a href="<s:url value='/admin/showDayStat'/>?pageType=admin">${dayCount}</a></span>人

</body>
</html>