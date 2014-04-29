<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="sign" value="${spel['T(com.abbcc.util.constant.group.GroupPiece).hunk_show'].name()}"/>
		<div id="theme_pic" class="headTopic moveChild headCont"  data-piece="${sign }">
			<div class="clr"></div>
			<div id="description" class="description">
				<div class="topDesc"><span id="topDesc">${layout.jsonSign['topDesc']}</span></div>
				<div class="bottomDesc"><span id="bottomDesc">${layout.jsonSign['bottomDesc']}</span></div>
			</div>
		</div>