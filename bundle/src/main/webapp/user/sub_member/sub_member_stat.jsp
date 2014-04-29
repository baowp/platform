<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="<s:url value='/user/css/validation.css'/>">
<title>客户统计</title>
<link href="/user/css/div.css" rel="stylesheet" type="text/css" />
<link href="/user/css/css.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<div id="body">
  <div id="top_b">
    <div class="font05"><a href="file:///E:/site/View%20customers.html#"></a>&nbsp;客户统计</div>
    <div class="font_h" id="top_footer"> &nbsp;贵公司<span class="errorSpan"><s:property value="enterpriseName()"/></span>, 目前共有注册客户<span class="errorSpan">${allCount}</span> 人，近一个月注册的有<span class="errorSpan"><a href="<s:url value='/user/subMember/statshowMonth'/>">${monthCount}</a></span> 人，今天注册的有<span class="errorSpan"><a href="<s:url value='/user/subMember/statshowDay'/>">${dayCount}</a></span>人 <span class="font06">(点击数字可查看详细)</span></div>
  </div>
  <div id="gh"><img alt="客户注册统计图" src="<s:url value="/user/subMember/statjfreechart"/>"></div>
</div>
<hr/>

</body>
</html>