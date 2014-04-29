<#assign sign=enums["com.abbcc.util.constant.group.GroupPiece"].narrow_category_news>
<#if !command.take("newsRoots")??>
	${command.pieceCategoryNews() }
</#if>
<#assign newsRoots=command.take("newsRoots")>

<div id="sIDE_CATEGORY_NEWS" class="bodyCont moveChild">
<#include "../../yahooTree.ftl">
<script type="text/javascript"
		src="js/site/news_category_tree.js"></script>
<script type="text/javascript">
	newsRoots = ${statics["net.sf.json.JSONSerializer"].toJSON(newsRoots)};
</script>
	<div class="clr"></div>
	<div class="bodyContTitle">
		<span class="fl b titleLinkColor titleName"> ${moduleMap[sign]!''}
		</span>
	</div>
	<div class="bodyContContent mainTextColor">
		<div id=newsCategory class="treeDiv"></div>
	</div>
</div>