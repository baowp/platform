<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="search_in_site" class="bodyCont moveChild mainTextColor">
	<s:hidden name="piece" value="%{@com.abbcc.util.constant.layout.Piece@SIDE_SEARCH}"/>
	<div class="clr"></div>
	<div class="bodyContTitle">
		<span class="fl b titleLinkColor titleName">
			<s:property value="layout.jsonContent['title'][@com.abbcc.util.constant.layout.Piece@SIDE_SEARCH.name()]||
				#request['moduleMap'][@com.abbcc.util.constant.layout.Piece@SIDE_SEARCH.name()]"/>
		</span>
	</div>
	<div class="bodyContContent">
		<form action="/search">          
            <label for="keyword" class="proName">${lan['search.productName']}</label><input type="text" class="proNameInput" name="keywords" value="${keywords }" id="keywords_text_1" maxlength="50">
            <label for="price" class="pricelabel">${lan['search.price']}</label><input type="text" class="priceText" name="priceStart" value="${priceStart }" id="priceLowSide">
            <label for="price">${lan['search.to']}</label><input type="text" class="priceText" name="priceEnd" value="${priceEnd }" id="priceHighSide"> 
            <input type="submit" value="搜索" id="searchBtnSide">           
        </form>
	</div>
</div>