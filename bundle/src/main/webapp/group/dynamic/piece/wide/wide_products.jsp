<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="sign" value="${spel['T(com.abbcc.util.constant.group.GroupPiece).wide_products'].name()}"/>
<c:if test="${empty products }">
	${command.pieceProducts() }
</c:if>
<div id="products" class="bodyCont moveChild glare_type_1" data-piece="${sign }">
			<div class="clr"></div>
			<div class="bodyContTitle">
				<span class="fl b titleLinkColor titleName" >
					${moduleMap[sign] }
				</span>
				<c:if test="${belongPage ne 'product' }">
					<a class="fr fs12px nb titleLinkColor draft_no_link" href="product">${lan['more']} &gt;&gt;</a>
				</c:if>
			</div>
			<div class="bodyContContent rightConteWidth rel" data-meta='{"currentPage":${products.currentPage},"pageCount":${products.pageCount},"parentValue":"${param.categoryId }","url":"${sign }"}'>
				<div class="glitzBody">
					<ul>
					<c:forEach items="${products.items }" var="p">
						<li class="glitzItem ${loginView eq 1?'isLoged':''}">
							<div class="glitzPic glitzBorder glitzColor">
								<a href="product_detail?itemId=${p.productId}" target="_blank">
									<img border="0" src="${p.mainPic.picUrl(5)}" />
								</a>
								<div class="imgBorder"></div>
							</div>
							<div class="txt">
								<a class="topicLink draft_no_link" target="_blank" href="product_detail?itemId=${p.productId}">${p.name }</a>
							</div>
							<span class="price"></span>
						</li>
					</c:forEach>
					</ul>
				</div>
					<c:set var="pageList" value="${products }" scope="request"/>
					<jsp:include page="../../pagination.jsp"></jsp:include>
			</div>
</div>