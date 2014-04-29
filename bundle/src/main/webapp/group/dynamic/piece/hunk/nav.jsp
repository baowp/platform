<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<li id="menu-${nav.page}" class="${belongPage eq nav.page?'headerMenuLiCheck headerMenuLiCheckBg':'headerMenuLi'} moveMenu" data-submenu='${nav.subJson}'>
	<span>
		<a href="${nav.page}">
			${nav.name }
		</a>
	</span>
</li>
