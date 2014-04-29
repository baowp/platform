<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="sign" value="${spel['T(com.abbcc.util.constant.group.GroupPiece).narrow_category'].name()}"/>
<c:if test="${empty categoryList }">
	${command.pieceCategoryList() }
</c:if>
<div id="category_nav" class="bodyCont moveChild" data-piece="${sign }">
<jsp:include page="../../yahooTree.jsp"></jsp:include>
	<script type="text/javascript">
	categoryList = [ 
	   <c:forEach items="${categoryList}" var="cate" varStatus="st">
	 	  {id:'${cate.categoryId}',name:'${cate.name}',image:'${cate.image2}',ifLeaf:${cate.ifLeaf}}<c:if test="${!st.last }">,</c:if>	
 	    </c:forEach>
  	 ]; 
 </script>
	<div class="clr"></div>
		<div class="bodyContTitle">
			<span class="fl b titleLinkColor titleName" >
				${moduleMap[sign] }
			</span>
		</div>
		<div class="bodyContContent mainTextColor">
			<div id=categoryTree class="treeDiv"></div>
		</div>
	<script type="text/javascript" src="/group/dynamic/js/category_tree.js"></script>
</div>