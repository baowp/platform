<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="z-index: 99; display: none;" class="rel editor"
	id="setLayout">
	<div
		style="background: rgb(255, 255, 255) none repeat scroll 0% 0%; text-align: left;"
		class="abs">
		<div id=layoutEditor class="setContent2 nb black">
			<div class="contTitle ml7px mt8px">
				<span class="pl23px b">调整网站布局</span>
			</div>
			<div class="row">
				<ul id="moveListUl">
					<c:forEach items="${content}" var="laymod" varStatus="vs">
						<c:set var="i"
							value="${laymod.module.msign.replaceAll('\\\\D','')}" />
						<li data-target="#content${i}"><div>列${i}(宽)</div> <input
							id="width${i}" type="text" />px</li>
					</c:forEach>
				</ul>
			</div>
			<div class="clr"></div>
		</div>
		<div
			style="top: -33px; left: 0px; color: rgb(255, 115, 0); font-weight: bold; text-align: left;"
			class="abs">
			<div class="setTitle">
				<span style="padding-left: 12px; padding-top: 3px; display: block;">网站布局</span>
			</div>
		</div>
	</div>
</div>