<div id="search_in_site" class="bodyCont moveChild mainTextColor">
	<div class="clr"></div>
	<div class="bodyContTitle">
		<span class="fl b titleLinkColor titleName">
			${extract(layout.jsonContent['title'][enums["com.abbcc.util.constant.layout.Piece"].SIDE_SEARCH.name()],
				moduleMap[enums["com.abbcc.util.constant.layout.Piece"].SIDE_SEARCH.name()])}
		</span>
	</div>
	<div class="bodyContContent">
		<form>          
            <label for="keyword" class="proName">${lan['search.productName']}</label><input type="text" class="proNameInput" name="keywords" value="<#if action.keywords??>${action.keywords}</#if>" id="keywords_text_1" maxlength="50">
            <label for="price" class="pricelabel">${lan['search.price']}</label><input type="text" class="priceText" name="priceStart" value="<#if action.priceStart??>${action.priceStart}</#if>" id="priceLowSide">
            <label for="price">${lan['search.to']}</label><input type="text" class="priceText" name="priceEnd" value="<#if action.priceEnd??>${action.priceEnd}</#if>" id="priceHighSide"> 
            <input type="submit" value="搜索" id="searchBtnSide">           
        </form>
	</div>
	<script type="text/javascript" src="js/site/side_search.js"></script>
</div>