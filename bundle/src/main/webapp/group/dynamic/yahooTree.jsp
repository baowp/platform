<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 判断yahoo树的资源文件是否已经引入 --%>
<c:if test="${!importYahooTree}">
	<c:set var="importYahooTree" scope="request" value="true"></c:set>
	<link rel="stylesheet" type="text/css" href="/js/yui/build/treeview/assets/skins/sam/treeview.css" />
	<script type="text/javascript" src="/js/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
	<script type="text/javascript" src="/js/yui/build/connection/connection-min.js"></script>
	<script type="text/javascript" src="/js/yui/build/treeview/treeview-min.js"></script>
</c:if>