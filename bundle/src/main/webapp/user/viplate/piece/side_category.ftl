<#if action.root.categoryList??>
<#else>
	${action.pieceCategoryList()}
</#if>
<#assign categoryList=action.root.categoryList>
<script type="text/javascript" src="js/site/category_tree.js"></script>
<script type="text/javascript">
	categoryList = ${statics["net.sf.json.JSONSerializer"].toJSON(categoryList)};
</script>
	<div id="category_nav" class="bodyCont moveChild">
		<div class="clr"></div>
		<div class="bodyContTitle">
			<span class="fl b titleLinkColor titleName" >
					${extract(layout.jsonContent['title'][enums["com.abbcc.util.constant.layout.Piece"].SIDE_CATEGORY.name()],
						moduleMap[enums["com.abbcc.util.constant.layout.Piece"].SIDE_CATEGORY.name()])}
				</span>
		</div>
		<div class="bodyContContent mainTextColor">
			<div id=categoryTree class="treeDiv"></div>
		</div>
	</div>