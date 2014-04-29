<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="sign" value="${spel['T(com.abbcc.util.constant.group.GroupPiece).wide_supplies'].name()}"/>
<c:if test="${empty supplies }">
	${command.pieceSupplies() }
</c:if>
	<div id="supplies" class="bodyCont moveChild glare_type_1" data-piece="${sign }">
		<div class="clr"></div>
		<div class="bodyContTitle">
			<span class="fl b titleLinkColor titleName" >
				${moduleMap[sign] } 
			</span>
			<c:if test="${belongPage ne 'supply' }">
				<a class="fr fs12px nb titleLinkColor draft_no_link" href="supply">${lan['more']} &gt;&gt;</a>
			</c:if>
		</div>
		<div class="bodyContContent rightConteWidth rel" data-meta='{"currentPage":${supplies.currentPage},"pageCount":${supplies.pageCount},"url":"${sign }"}'>
			<table class="prolist_table mainTextColor   bodyContContentImg-150" style="width: 98%">
				<tbody>
					<tr style="color: rgb(0, 0, 0);" class="title">							
						<td width="35% " bgcolor="#e7e7e7">${lan['supplies.info']}</td>
						<td width="20%" bgcolor="#e7e7e7">${lan['supplies.price']}</td>
						<td width="45%" bgcolor="#e7e7e7">${lan['supplies.publishTime']}</td>	
					 </tr>
					 <c:forEach items="${supplies.items }" var="supply">
					 	 <tr>						 		
   							<td align="left">
   								<a class="draft_no_link topicLink underline" href="supply_detail?itemId=${supply.supplyId}"" target="_blank">[${supply.type.toString() }] ${supply.title}</a>
							</td>
   							<td>${supply.price }</td>
   							<td><fmt:formatDate value="${supply.startTime }" pattern="${lan['supplies.format']}"/></td>     
					 	</tr>
					 </c:forEach>
				</tbody>
			</table>
			<div class="wangpuBorderBottom3 mt10px mb5px mainTextColor">
				<c:set var="pageList" value="${supplies }" scope="request"/>
				<jsp:include page="../../pagination.jsp"></jsp:include>
			</div>
		</div>
	</div>