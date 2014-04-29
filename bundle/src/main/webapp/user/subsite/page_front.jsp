<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id=hd>
<div>
	<ul class="nav">
		<li class="${empty result?'current':''}"><a href="<s:url value="%{'/site/'+username+''}"/>"><em>首页</em></a></li>
		<li class="${result eq 'product'?'current':''}"><a href="<s:url value="%{'/site/'+username+'/product'}"/>"><em>产品供应</em></a></li>
		<li class="${result eq 'supply'?'current':''}"><a href="<s:url value="%{'/site/'+username+'/supply'}"/>"><em>供求信息</em></a></li>
		<li class="${result eq 'enterprise'?'current':''}"><a href="<s:url value="%{'/site/'+username+'/enterprise'}"/>"><em>公司介绍</em></a></li>
		<li class="${result eq 'person'?'current':''}"><a href="<s:url value="%{'/site/'+username+'/person'}"/>"><em>联系方式</em></a></li>
	</ul>
</div>
</div>