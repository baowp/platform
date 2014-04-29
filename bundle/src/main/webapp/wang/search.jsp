<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ABBCC搜索</title>
<link href="/css/searc.css" rel="stylesheet" type="text/css" />
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
<script src="/js/feedbackB/messageB.js" type="text/javascript"></script>
<script>
function setHomepage() {
	 var href = "http://" + location.host;
	 if (document.all) {
	 document.body.style.behavior = 'url(#default#homepage)';
	 document.body.setHomePage(href);
	
	 } else if (window.sidebar) {
	 if (window.netscape) {
	 try {
	 netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
	 var prefs = Components.classes['@mozilla.org/preferences-service;1'].getService(Components.interfaces.nsIPrefBranch);
	 prefs.setCharPref('browser.startup.homepage', href);
	 } catch (e) {
	 alert("\u8be5\u64cd\u4f5c\u88ab\u6d4f\u89c8\u5668\u62d2\u7edd\uff0c\u5982\u679c\u60f3\u542f\u7528\u8be5\u529f\u80fd\uff0c\u8bf7\u5728\u5730\u5740\u680f\u5185\u8f93\u5165 about:config,\u7136\u540e\u5c06\u9879 signed.applets.codebase_principal_support \u503c\u8be5\u4e3atrue");
	 }
	 }
	 }
	} 
</script>
</script>
</head>
<!--[if lte IE 6]>
<script src="/home/js/DD_belatedPNG_0.0.8a.js" type="text/javascript"></script>
    <script type="text/javascript">
        DD_belatedPNG.fix('div, ul, img, li, input , a');
    </script>
<![endif]-->
<body>
<body link="#0000cc">
<div id="out"><div id="in"><div id="wrapper">
<p id="u"><s:if
	test="#session.loginuserid!=null">
	<a href="user/abbcc/index.jsp">用户中心</a> | <a
		href="<s:url value="/user/userLogout"/>?pageType='index'"
		onClick="return logout();">注销</a>
</s:if> <s:else>
	<a href="user/login.jsp">登陆</a> | <a href="<s:url value="/user/reg.jsp"/>">注册</a>
</s:else></p>
<s:form action="byNameSearch" name="form1" id="form1">
				<s:hidden name="thisPage" id="thisPage"/>
				<s:hidden name="entId" id="entId" />
				<s:hidden name="pageTypeHidden" value="%{#request.pageType}" />
<table height="58" width="100%" cellspacing="0" cellpadding="0" align="center"><tr valign="middle"><td nowrap="" width="100%" valign="top" style="padding: 4px 0pt 0pt 8px; width: 137px;"><img height="46" width="142" border="0" alt="到abbcc首页" src="/user/images/logo.png"></td>
<td>&nbsp;&nbsp;&nbsp;</td><td width="100%" valign="top"><div class="Tit">&nbsp;&nbsp;<s:if
						test="%{#request.pageType=='product'}">产品</s:if> <s:else>
						<a href="#" onclick="checkType('product')">产品</a>
					</s:else> &nbsp;&nbsp;&nbsp;<s:if test="%{#request.pageType=='news'}">资讯</s:if> <s:else>
						<a href="#" onclick="checkType('news')">资讯</a>
					</s:else>
&nbsp;&nbsp;&nbsp;&nbsp;<s:if test="%{#request.pageType=='enterprise'}">公司</s:if> <s:else>
						<a href="#" onclick="checkType('enterprise')">公司</a>
					</s:else>
<table cellspacing="0" cellpadding="0"><tr><td nowrap="" valign="top">
 <s:textfield  name="entName" id="entName"
						cssClass="i"
						 value="%{#request.searchName}" size="46"/> <s:submit  value="ABBCC一下" /> &nbsp;&nbsp;&nbsp;</td><td nowrap="" valign="middle">&nbsp;</td>
</tr></table></td><td></td></tr></table>
</s:form>
<table width="100%" cellspacing="0" cellpadding="0" border="0" align="center" class="bi"><tr>
  <td width="23%" nowrap="">&nbsp;&nbsp;&nbsp;<a href="javascript:" onclick="setHomepage()" style="color: rgb(0, 0, 0);" >把abbcc设为主页</td>
<td width="47%" align="right" nowrap="">&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td width="30%" align="left" nowrap="">abbcc一下，找到相关网页约<font color="red">${resultSize}</font>篇，用时${runTime}秒</td>
</tr></table>
  <s:if test="%{#request.pageType=='product'}">
<table width="30%" cellspacing="0" cellpadding="0" border="0" align="right"><tr>
<td align="left" style="padding-right: 10px;">
<div style="border-left: 1px solid rgb(225, 225, 225); padding-left: 10px;">

  <div class="r ec_bdtg">
<div class="fsblock">
	<div class="title"><a target="_blank" href="#"><font size="3">abbcc推广您的产品</font></div>
    <s:if test="%{#request.isNull=='01'}">
			<div class="right_c01"><a target="_blank" href="#">暂时没有该产品的推广，如果您想在这里推广您的产品，请致电0579-87171989</a>
			</div>
		</s:if> <s:else>
		<s:iterator value="%{#request.popularizeList}">
			<div class="right_c01"><a href="${url}" target="_blank"><s:property
								value="searchContent(pName,#request.searchName)" escape="false" /></a></div>
			<div class="right_c02"><s:property
								value="searchContent(content,#request.searchName)"
								escape="false" /></div><br/>
		</s:iterator>
		</s:else>   
</div>
</div>

<br>
</div>

<br>
</td></tr></table></s:if>
<s:iterator var="user" value="pageList.items" status="st">
	<s:if test="%{#request.pageType=='product'}">
		<table cellspacing="0" cellpadding="0" id="2" class="result">
	  <tr>
	  <td height="105" align="center" width="110" class=""><div class="photo"><img height="105" width="100" src="<s:property value="productPic(productId)" />"></div></td>
  <td class="f"><font size="3"><a
			href="<s:url value="/"/><s:property value='productPath(enterpriseId,productId)'/>"
			target="_blank">${name}</a></font><br>
    <font size="-1"><s:property
			value="searchContent(pkey,#request.searchName)" escape="false" />...<br>
      <font color="#008000"><a
			href="<s:url value="/"/><s:property value="enterprisePath(enterpriseId)"/>"
			target="_blank"><s:property value='entName(enterpriseId)' /></a>--经营地点:<s:property
			value='entAddress(enterpriseId)' /></span><br /></font><br>
    </font></td></tr></table>
	</s:if>
	<s:if test="%{#request.pageType=='enterprise'}">
		<div class="center_c02">
		<div class="center_top01"><a
			href="<s:url value="/"/><s:property value='enterprisePath()'/>"
			target="_blank"><s:property
			value="searchContent(name,#request.searchName)" escape="false" /></a></div>
		<div class="center_top02"><span class="font01"><s:property
			value="searchContent(edesc,#request.searchName)" escape="false" />...<br />
		<a href="<s:url value="/"/><s:property value='enterpriseSupper()'/>"
			target="_blank"><font color="#008000">该公司所有供应信息</font></a></span>
		</div>
		</div><br/>
	</s:if>
	<s:if test="%{#request.pageType=='news'}">
<table cellspacing="0" cellpadding="0" id="1"><tbody><tr><td class="f"><a
			href="<s:url value="/"/>?url=<s:property value="staticpath"/>"
			target="_blank"><s:property
			value="searchContent(title,#request.searchName)" escape="false" /></a><br>
	  <s:property
			value="searchContent(nkey,#request.searchName)" escape="false" />
	  <br><font color="#008000">v</font><font size="-1"><font color="#008000"> <s:date
			name="addTime" format="yyyy-MM-dd hh:mm:ss" /> </font></font><br></font></td>
	</tr></tbody></table><br/>

	</s:if>
</s:iterator>

	<p>&nbsp;</p>
	<p>&nbsp;</p>
	<p>&nbsp;</p>
	<p>&nbsp;</p>
	<p><br clear="all">
	  </p><s:if test="%{#request.resultSize!=0}">
	<div class="p"><s:include value="/common/infoPagination.jsp"></s:include></div>

<table cellspacing="0" cellpadding="0" style="margin-left: 18px; height: 60px;">
<tr valign="middle"><td nowrap=""><s:textfield  name="entName" id="entName1"
						cssClass="i"
						 value="%{#request.searchName}" size="46"/><input type="button" onclick="javascript:$('#entName').val($('#entName1').val());$('#form1').submit()" class="btn" value="ABBCC一下"> 
&nbsp;&nbsp;&nbsp;</td>
</tr></table></s:if>
</div>
</div></div>
</body>
</html>