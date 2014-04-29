<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="sign" value="${spel['T(com.abbcc.util.constant.group.GroupPiece).hunk_logo'].name()}"/>
		<div id="company_name" class="moveChild topbaner headCont"  data-piece="${sign }">
			<div class="topbanerCont">
				<div id="log">
					<c:if test="${!empty layout.jsonSign['log']}">
						<img <c:forEach items="${layout.jsonSign['log'] }" var="log">${log.key}="${log.value}" </c:forEach>></img>
					</c:if>
				</div>
				<div id="companyname">
				<ul>
					<li class="china">
					<span id="chinaname" class="chinaname">${enterprise.name}</span>
					<span class="enname" id="enname"></span>
					</li>
				</ul>
				</div> 
			</div>
		</div>