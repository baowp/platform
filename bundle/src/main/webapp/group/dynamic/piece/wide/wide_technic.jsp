<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="sign" value="${spel['T(com.abbcc.util.constant.group.GroupPiece).wide_technic'].name()}"/>
${command.pieceTechnic() }
<div id="technic" class="bodyCont moveChild" data-piece="${sign }">
	<div class="clr"></div>
	<div class="bodyContTitle">
	<span class="fl b titleLinkColor titleName" >
		${moduleMap[sign] }
	</span>
	
	</div>
	<div class="bodyContContent rel rightConteWidth">
	<div class="bodyContContentRightCont fl tal mainTextColor break">
	  <span>${technic.content }</span>
	</div>
	<div class="clr"></div>
	</div>
</div>