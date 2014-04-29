<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="/group/dynamic/tool/pieceadd/css/add_piece.css"
	rel="stylesheet" />
<div id="add_piece_container" class="none">
	<div id="tabs">
		<ul>
			<li><a href="#tabs-1">首尾模块</a>
			</li>
			<li><a href="#tabs-2">内容模块</a>
			</li>
<!-- 			<li><a href="#tabs-3">内容大模块</a> -->
<!-- 			</li> -->
<!-- 			<li><a href="#tabs-4">内容小模块</a> -->
<!-- 			</li> -->
			<li><a href="#tabs-5">自定义模块</a>
			</li>
			<c:if test="${not empty itemId }">
				<li><a href="#tabs-6">详细页模块</a>
				</li>
			</c:if>
		</ul>
		<div id="tabs-1">
			<ul class="items">
				<c:forEach items="${headbelowList }" var="p" varStatus="index">
					<li><label class="${p.moduleId }" data-mod="${p.moduleId }">${p.name
							}</label>
					</li>
				</c:forEach>
			</ul>
		</div>
		<div id="tabs-2">
			<div class="titleSpan">大模块</div>
			<ul class="items">
				<c:forEach items="${wideList }" var="p">
					<li><label class="${p.moduleId }" data-mod="${p.moduleId }">${p.name}</label>
					</li>
				</c:forEach>
			</ul>
			<div class="titleSpan">小模块</div>
			<ul class="items">
				<c:forEach items="${narrowList }" var="p">
					<li><label class="${p.moduleId }" data-mod="${p.moduleId }">${p.name}</label>
					</li>
				</c:forEach>
			</ul>
		</div>
<!-- 		<div id="tabs-3"> -->
<!-- 			<ul class="items"> -->
<%-- 				<c:forEach items="${wideList }" var="p"> --%>
<%-- 					<li><label class="${p.moduleId }" data-mod="${p.moduleId }">${p.name}</label> --%>
<!-- 					</li> -->
<%-- 				</c:forEach> --%>
<!-- 			</ul> -->
<!-- 		</div> -->
<!-- 		<div id="tabs-4"> -->
<!-- 			<ul class="items"> -->
<%-- 				<c:forEach items="${narrowList }" var="p"> --%>
<%-- 					<li><label class="${p.moduleId }" data-mod="${p.moduleId }">${p.name}</label> --%>
<!-- 					</li> -->
<%-- 				</c:forEach> --%>
<!-- 			</ul> -->
<!-- 		</div> -->
		<div id="tabs-5">
			<ul class="items">
				<c:set var="newDefined" value="user_defined1"></c:set>
				<c:forEach items="${userDefinedList }" var="p" varStatus="st">
					<li><label class="${p.moduleId }" data-mod="${p.moduleId }">${p.name}</label>
					</li>
					<c:if test="${st.last }">
						<c:set var="newDefined"
							value="user_defined${p.moduleId.substring(12)+1 }"></c:set>
					</c:if>
				</c:forEach>
				<li><label class="${newDefined }" data-mod="${newDefined }">自定义</label>
				</li>
			</ul>
		</div>
		<c:if test="${not empty itemId }">
			<div id="tabs-6">
				<ul class="items">
					<c:forEach items="${detailList }" var="p">
						<li><label class="${p.moduleId }" data-mod="${p.moduleId }">${p.name}</label>
						</li>
					</c:forEach>
				</ul>
			</div>
		</c:if>
	</div>
</div>