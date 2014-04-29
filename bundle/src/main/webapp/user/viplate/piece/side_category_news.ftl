<#if action.root.newsRoots??>
<#else>
	${action.pieceNewsCategory()} 
</#if>
<#assign categoryList=action.root.newsRoots>
<script type="text/javascript" src="js/site/news_category_tree.js"></script>
<script type="text/javascript">
	newsRoots = ${statics["net.sf.json.JSONSerializer"].toJSON(categoryList)};
</script>
	<div id="sIDE_CATEGORY_NEWS" class="bodyCont moveChild">
		<div class="clr"></div>
		<div class="bodyContTitle">
			<span class="fl b titleLinkColor titleName" >
					${extract(layout.jsonContent['title'][enums["com.abbcc.util.constant.layout.Piece"].SIDE_CATEGORY_NEWS.name()],
						moduleMap[enums["com.abbcc.util.constant.layout.Piece"].SIDE_CATEGORY_NEWS.name()])}
				</span>
		</div>
		<div class="bodyContContent mainTextColor">
			<div id=newsCategory class="treeDiv"></div>
		</div>
	</div>