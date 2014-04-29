<#assign sign=enums["com.abbcc.util.constant.group.GroupPiece"].narrow_search>
<div id="search_in_site" class="bodyCont moveChild mainTextColor">
	<div class="clr"></div>
	<div class="bodyContTitle">
		<span class="fl b titleLinkColor titleName"> ${moduleMap[sign]!''}
		</span>
	</div>
	<div class="bodyContContent">
		<form action="search">
			<label for="keyword" class="proName">${lan['search.productName']}</label><input type="text"
				class="proNameInput" name="keywords" value="${command.keywords!'' }"
				id="keywords_text_1" maxlength="50"> <label for="price"
				class="pricelabel">${lan['search.price']}</label><input type="text" class="priceText"
				name="priceStart" value="${command.priceStart!'' }" id="priceLowSide">
			<label for="price">${lan['search.to']}</label><input type="text" class="priceText"
				name="priceEnd" value="${command.priceEnd!'' }" id="priceHighSide"> <input
				type="submit" value="搜索" id="searchBtnSide">
		</form>
	</div>
	<script type="text/javascript" src="js/site/side_search.js"></script>
</div>