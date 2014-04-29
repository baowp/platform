<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="sign"
	value="${spel['T(com.abbcc.util.constant.group.GroupPiece).narrow_search'].name()}" />

<div id="search_in_site" class="bodyCont moveChild mainTextColor"
	data-piece="${sign }">
	<div class="clr"></div>
	<div class="bodyContTitle">
		<span class="fl b titleLinkColor titleName"> ${moduleMap[sign]}
		</span>
	</div>
	<div class="bodyContContent">
		<form action="search">
			<label for="keyword" class="proName">产品名</label><input type="text"
				class="proNameInput" name="keywords" value="${command.keywords }"
				id="keywords_text_1" maxlength="50"> <label for="price"
				class="pricelabel">价格</label><input type="text" class="priceText"
				name="priceStart" value="${command.priceStart }" id="priceLowSide">
			<label for="price">到</label><input type="text" class="priceText"
				name="priceEnd" value="${command.priceEnd }" id="priceHighSide"> <input
				type="submit" value="搜索" id="searchBtnSide">
		</form>
	</div>
</div>