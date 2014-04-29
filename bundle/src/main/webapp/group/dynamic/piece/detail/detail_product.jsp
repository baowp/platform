<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link type="text/css" href="/group/dynamic/css/product.simple.css" rel="stylesheet" />
<script type="text/javascript" src="/group/dynamic/js/product.simple.js"></script>
<link type="text/css" href="/group/dynamic/css/product_detail1.css" rel="stylesheet" />
<script type="text/javascript" src="/group/dynamic/js/product_detail1.js"></script>
<c:set var="sign" value="${spel['T(com.abbcc.util.constant.group.GroupPiece).detail_product'].name()}"/>
<c:if test="${empty product}">
	${command.pieceProductDetail(itemId) }
</c:if>
<div class="bodyCont moveChild mainTextColor productDetail1 detail" data-piece="${sign }">
	<div class="tab">
		<span class="b wangpuTopTitle">${product.name}</span>&nbsp;
	</div>
	<div class="cpzs">
		<div class="cpzs_left">
			<div class="datu">
				<a href="#"><img class="divimg" src="${product.mainPic.picUrl(7) }"></a>
        	</div>
		</div>
		<div class="cpzs_right">
			<div class="canshu">
				<div class="xinhao">${product.prodtype }</div>
				<div class="jiben"></div>
						</div>
			<ul class="jiaodu">
				<li>
				<div class="divImg glitzBorder glitzColor"><img id="0" src="${product.mainPic.picUrl(8) }"></div>
				</li>
				<c:forEach items="${product.attachList }" var="p" varStatus="stat">
				<li>
					<div class="divImg glitzBorder glitzColor"><img id="${stat.index+1 }" src="${p.picUrl(8) }"></div>
				</li>
				</c:forEach>
			</ul>
</div>
	</div>
	
	<div class="xxcs">
		<div class="xxcs_hard">
			<c:if test="${product.detailTitle1 != null}"><div flag="1" id="open" class="xntd glitzColor">${product.detailTitle1}</div></c:if>
	     	<c:if test="${product.detailTitle2 != null}"><div flag="2" id="colse" class="xntd glitzColor">${product.detailTitle2}</div></c:if>
	        <c:if test="${product.detailTitle3 != null}"><div flag="3" id="colse" class="xntd glitzColor">${product.detailTitle3}</div></c:if>
		</div>
		<c:if test="${product.detail1 != null}">
			<div flag="show1"  class="con_xn con glitzColor ">${product.detail1}</div>
		</c:if>
		<c:if test="${product.detail2 != null}">
			<div flag="show2"  class="con_xn con glitzColor " style="display: none;">${product.detail2}</div>
		</c:if>
		<c:if test="${product.detail3 != null}">
			<div flag="show3"  class="con_cs con glitzColor " style="display: none;">
				<table>
					<tbody>
						<tr>
							<td colspan="2" class="xinhao">型号: ${product.prodtype }</td>
						</tr>
						<c:forEach items="${product.jsonDetail3 }" var="j">
							<tr>
								<td>${j.key }</td>
								<td>${j.value }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</c:if>
		</div>
</div>