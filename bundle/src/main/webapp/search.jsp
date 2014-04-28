<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Abbcc欢迎您!</title>
<link href="style/div.css" rel="stylesheet" type="text/css" />
<link href="style/css.css" rel="stylesheet" type="text/css" />
<link type="text/css" href="/css/jquery-ui.css" rel="stylesheet" />
	<link id="artDialogSkin" href="/js/artDialog3.0.5/skin/default.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/artDialog3.0.5/artDialog.min.js"></script>
<script type="text/javascript" src="/user/platform/js/artCommon.js"></script>
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/search.js"/>"></script>
<script type="text/javascript"
	src="<s:url value="/js/jquery-ui.min.js"/>"></script>
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
<s:if test="%{#request.pageType=='supply'}">
	<script type="text/javascript">
	$(function(){
		checkValue();
		if($("#entName").val()==''){  
			$("#entName").val("请输入供求名称");
			inputTipText();  //直接调用就OK了  
		}
	}) 
	</script>
</s:if>
<script>
function openInquiry(obj){
	var url = '/inquiry/inquiryPage?productId='+$(obj).attr('pId');
	art.dialog.open(url,{
		id : 'xjj',
		skin: 'aero',
		title: '询价(按esc可关闭)',
		left:100,
		top:0,
		fixed:false,
		width:800,
		height:500
	});
}
function openMap(obj){
	art.dialog({
		id : 'testIframe',
		skin: 'aero',
		title: '公司地图(按esc可关闭)',
		left:150,
		top:40,
		fixed:true,
		width:600,
		height:480,
	    content: '<iframe width="600" height="450" scrolling="no" border="0" frameborder="0" id="mapIframe" src="http://searchbox.mapbar.com/publish/template/template1010/index.jsp?CID=ggggfj&tid=tid1010&nid='+$(this).attr("mapaddress")+'&width=600&height=450&control=2&infopoi=1&infoname=1&zoom=10&showSearchDiv=0"></iframe>'
	});
}
</script>
<style type="text/css" media="all">
/*<![CDATA[*/
iframe {
	v: expression(this.src = 'about:blank', this.outerHTML = '');
	/*使用IE Only 的样式会除所有　IFRAME　*/
}

#mainFrame {
	v: expression() !important
}
;
/*]]>*/
</style>

</head>

<body>
<div id="m">
<div id="header">
<div class="header_left">
<ul>
	<li><a href="/">东方五金首页</a></li>
</ul>
</div>
<div class="header_r">
<ul>
	<s:if test="#session.loginuserid!=null">
		<li><a href="user/platform/index.jsp">用户中心</a></li>
		<li><a href="<s:url value="/user/userLogout"/>?pageType='index'"
			onClick="return logout();">注销</a></li>
	</s:if>
	<s:else>
		<li><a href="user/login.jsp">登陆</a></li>
		<li><a href="<s:url value="/user/reg.jsp"/>">注册</a></li>
	</s:else>
</ul>
</div>
</div>
<div id="logot">
<div class="logot_left"><img src="images/logo.jpg" width="179"
	height="51" /></div>
<div class="sou">
<div class="searc_top">
<ul>
	<li><s:if test="%{#request.pageType=='news'}">资讯</s:if> <s:else>
		<a href="#" onclick="checkType('news')">资讯</a>
	</s:else></li>
	<li><s:if test="%{#request.pageType=='product'}">产品</s:if> <s:else>
		<a href="#" onclick="checkType('product')">产品</a>
	</s:else></li>
	<li><s:if test="%{#request.pageType=='enterprise'}">公司</s:if> <s:else>
		<a href="#" onclick="checkType('enterprise')">公司</a>
	</s:else></li>
	<li><s:if test="%{#request.pageType=='supply'}">求购</s:if> <s:else>
		<a href="#" onclick="checkType('supply')">求购</a>
	</s:else></li>
</ul>
</div>
<div class="searc_center"><s:form action="byNameSearch"
	name="form1" id="form1">
	<s:hidden name="thisPage" id="thisPage" />
	<s:hidden name="entId" id="entId" />
	<s:hidden name="pageTypeHidden" value="%{#request.pageType}" />
	<table width="100%" border="0" cellpadding="0">
		<tr>
			<td width="81%"><s:textfield name="entName" id="entName"
				cssClass="i" value="%{#request.searchName}" size="46" /></td>
			<td width="20%"><input type="submit"
				onclick="javascript:checkValue()" name="button1" id="button1"
				value="ABBCC搜索" /></td>
		</tr>
	</table>
</s:form></div>
</div>
</div>
<div class="top">
<div class="top_left"><a href="javascript:"
	onclick="setHomepage()" style="color: rgb(0, 0, 0);">把abbcc设为主页</a></div>
<div class="top_right">找到 ${resultSize} <!--resultbarnum:131,424-->
个网页，用时 ${runTime} <!--resultbartime:0.025--> 秒</div>
</div>
<div id="contain" style="clear: both;"><s:if
	test="%{#request.pageType=='product'}">
	<div class="leftt"><s:iterator var="user" value="list"
		status="st">
		<div class="cl">
		<div class="ru"><img
			src="<s:property value="productPic(productId)" />" width="90"
			height="90" /></div>
		<h3><a
			href="<s:url value="/"/><s:property value='productPath(enterpriseId,productId)'/>"
			target="_blank">${name}</a></h3>
		<div class="d"><s:property
			value="searchContent(pkey,#request.searchName)" escape="false" />...
		<br> <font color="#0066CC"><a
			href="<s:url value="/"/><s:property value="enterprisePath(enterpriseId)"/>"
			target="_blank"><s:property value='entName(enterpriseId)' /></a>--经营地点:<s:property
			value='entAddress(enterpriseId)' /></font>
			<br/>
			<input type="button" value="企业地图" mapaddress="${mapaddress}" onclick="openMap(this)"/>
		<input type="button" value="联系方式"  onclick="location.href='/jump.html<s:property value="enterprisePhone(enterpriseId)"/>'"/>
		<input type="button" value="点此询价" pId="${productId}" onclick="openInquiry(this)"/>
		</div>
		</div>
	</s:iterator></div>
</s:if> <s:if test="%{#request.pageType=='enterprise'}">
	<div class="contain_left">
	<ol>
		<s:iterator var="user" value="pageList.items" status="st">
			<li class="c">
			<h3><a
				href="<s:url value="/"/><s:property value='enterprisePath(enterpriseId)'/>"
				target="_blank"><s:property
				value="searchContent(name,#request.searchName)" escape="false" /></a></h3>
			<div class="s"><s:property
				value="searchContent(edesc,#request.searchName)" escape="false" />
			<br> <font color="#181818" class="tu">企业：<a
				href="<s:url value="/"/><s:property value='enterpriseSupper()'/>"
				target="_blank">该公司所有供应信息</a></font><font color="#414141" class="ts">[<s:property
				value='entAddress(enterpriseId)' />]</font>
			</div>
			<font color="#181818" class="tu"><span>经营模式：<a
				href="javascript:"><s:property value="businessTypeName()" /></a></span>
			主营：<a href="javascript:">${mainBusiness}</a></font></li>
		</s:iterator>
	</ol>
	</div>
</s:if> <s:if test="%{#request.pageType=='news'}">
	<div class="contain_left">
	<ol>
		<s:iterator var="user" value="pageList.items" status="st">
			<li class="g">
			<h3><a
				href="<s:url value="/"/>?url=http://${domain}/${staticpath}"
				target="_blank"><s:property
				value="searchContent(title,#request.searchName)" escape="false" /></a></h3>
			<div class="s"><s:property
				value="searchContent(content,#request.searchName)" escape="false" />……
			<br> <font color="#0066cc"><s:date name="addTime"
				format="yyyy-MM-dd hh:mm:ss" /></font>- 
			</div>
			</li>
		</s:iterator>
	</ol>
	</div>
</s:if> <s:if test="%{#request.pageType=='supply'}">
	<div class="contain_left"><s:iterator var="user"
		value="pageList.items" status="st">
		<div id="ev">
		<div class="conta">
		<ol>
			<li class="we">
			<h3><a target="_blank"
				href="<s:url value="/"/><s:property value='supplyPath(supplyId)'/>"><s:property
				value="searchContent(title,#request.searchName)" escape="false" /></a></h3>
			<div class="st">
			<ol>
				<li>价格要求：${price}；</li>
			</ol>
			</div>
			</li>
		</ol>
		</div>
		<div class="con">
		<ol>
			<li class="v">发布时间</li>
			<li class="i"><s:date name="addTime" format="yy-MM-dd" /></li>
		</ol>
		</div>
		<div class="conq">
		<ol>
			<li class="v">联系方式</li>
			<li class="i"><input
				onclick="window.open('/<s:property value="enterprisePhone(enterpriseId)"/>')"
				type="button" value="查看" /></li>
		</ol>
		</div>
		</div>
	</s:iterator></div>
</s:if>
<div class="contain_right">

<ol>
	<s:if test="%{#request.isNull=='01'}">
		<li>暂时没有该产品的推广，如果您想在这里推广您的产品，请致电0579-87171989</li>
	</s:if>
	<s:else>
		<s:iterator value="%{#request.popularizeList}">
			<li class="g">
			<h3><a href="${url}" target="_blank"><s:property
				value="searchContent(pName,#request.searchName)" escape="false" /></a></h3>
			<div class="s"><s:property
				value="searchContent(content,#request.searchName)" escape="false" />
			<br />
			</div>
			</li>
		</s:iterator>
	</s:else>
</ol>
</div>
</div>
<div id="bottom" style="clear: both"><s:if
	test="%{#request.resultSize!=0}">
	<div class="gt" style="clear: both">
	<div class="x">相关搜索</div>
	<div class="x_right">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<s:iterator value="listSearch" status="st" id="stNumber">
			<s:if test="%{#st.index==0}">
				<tr>
			</s:if>

			<td align="left"><s:property escape="false" /></td>
			<s:if test="%{#st.index==3}">
				</tr>
				<tr>
			</s:if>
			<s:if test="%{#st.index==7}">
				</tr>
			</s:if>
		</s:iterator>
	</table>




	</div>
	</div>
</s:if> <s:if test="%{#request.resultSize!=0}">
	<div class="shu"><s:include value="/common/infoPagination.jsp"></s:include></div>
</s:if></div>
<s:if test="%{#request.resultSize!=0}">
	<div class="tj">
	<div class="sou">
	<div class="searc_center">
	<table width="100%" border="0" cellpadding="0">
		<tr>
			<td width="81%" align="center"><s:textfield name="entName"
				id="entName1" value="%{#request.searchName}" /></td>
			<td width="20%"><input type="button" name="button1"
				onclick="javascript:$('#entName').val($('#entName1').val());$('#form1').submit()"
				id="button1" value="ABBCC搜索" /></td>
		</tr>
	</table>
	</div>
	</div>
	</div>

	<div id="fh">
	<ul>
		<li><a href="http://easthardware.com" target="_parent">Abbcc首页</a></li>
		<li><a href="#" id="StranLink">繁体中文</a><script
			language="javascript" src="js/Std_StranJF.Js"></script></li>
		<li>&copy;2010 easthardware.com</li>
	</ul>
	</div>
</s:if></div>
</body>
</html>
