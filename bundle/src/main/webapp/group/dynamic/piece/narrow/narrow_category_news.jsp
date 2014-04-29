<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="sign"
	value="${spel['T(com.abbcc.util.constant.group.GroupPiece).narrow_category_news'].name()}" />
<c:if test="${empty newsRoots}">
	${command.pieceCategoryNews()}
</c:if>
<div id="sIDE_CATEGORY_NEWS" class="bodyCont moveChild" data-piece="${sign }">
<jsp:include page="../../yahooTree.jsp"></jsp:include>
	<script type="text/javascript">
newsRoots = [
         	   <c:forEach items="${newsRoots}" var="cate" varStatus="st">
         	 	  {id:'${cate.categoryId}',name:'${cate.name}',ifLeaf:${cate.ifLeaf}}<c:if test="${!st.last }">,</c:if>	
          	    </c:forEach>
           	 ]; 
</script>
	<div class="clr"></div>
	<div class="bodyContTitle">
		<span class="fl b titleLinkColor titleName"> ${moduleMap[sign]}
		</span>
	</div>
	<div class="bodyContContent mainTextColor">
		<div id=newsCategory class="treeDiv"></div>
	</div>
	<script type="text/javascript"
		src="/group/dynamic/js/news_category_tree.js"></script>
</div>