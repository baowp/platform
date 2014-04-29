<#assign sign=enums["com.abbcc.util.constant.group.GroupPiece"].narrow_category>
<#if !command.take("categoryList")??>
	${command.pieceCategoryList() }
</#if>
<#assign categoryList=command.take("categoryList")>
<div id="category_nav" class="bodyCont moveChild">
<#include "../../yahooTree.ftl">
<script type="text/javascript">
	categoryList = ${statics["net.sf.json.JSONSerializer"].toJSON(categoryList)};
 </script>
	<div class="clr"></div>
		<div class="bodyContTitle">
			<span class="fl b titleLinkColor titleName" >
				${moduleMap[sign]!'' }
			</span>
		</div>
		<div class="bodyContContent mainTextColor">
			<div id=categoryTree class="treeDiv"></div>
		</div>
	<script type="text/javascript" src="js/site/category_tree.js"></script>
</div>