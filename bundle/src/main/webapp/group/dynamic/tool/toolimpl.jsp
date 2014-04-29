<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${maintainable }">
	<div class="maintain">
			<jsp:include page="toolbar.jsp"></jsp:include>
			<jsp:include page="undefined/editor.jsp"></jsp:include>
			<jsp:include page="undefined/edit_title.jsp"></jsp:include> 
			<jsp:include page="undefined/edit_piece.jsp"></jsp:include>
			<jsp:include page="undefined/add_piece.jsp"></jsp:include>	<!-- 鼠标 hover事件 -->
			<jsp:include page="pieceadd/add_piece.jsp"></jsp:include>	<!-- 弹出layer -->
	</div>
</c:if>