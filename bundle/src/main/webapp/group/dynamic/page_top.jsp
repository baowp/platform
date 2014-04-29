<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${!maintainable }">
	<div>
		<c:import url="page_topbar.jsp"></c:import>
		<c:import url="dolog.jsp"/> 
	</div>
	<div>
<%-- 		<c:import url="../subsite/page_message.jsp"/> --%>
	</div>
</c:if>
