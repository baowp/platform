<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:action name="*/pieceNewsCategory" namespace="/vip" ignoreContextParams="true">
	<s:param name="enterpriseId" value="enterpriseId"/>
</s:action>
<script type="text/javascript" src="<s:url value="/user/js"/>/site/news_category_tree.js"></script>
<script type="text/javascript">
	newsRoots = [
	   <s:iterator value="#request['newsRoots']">
	   {id : '${categoryId}' , name : '${name}' , ifLeaf : ${ifLeaf}},
	   </s:iterator> 0
	   ];
	newsRoots.pop();
</script>
	<div id="sIDE_CATEGORY_NEWS" class="bodyCont moveChild">
		<s:hidden name="piece" value="%{@com.abbcc.util.constant.layout.Piece@SIDE_CATEGORY_NEWS}"/>
		<div class="clr"></div>
		<div class="bodyContTitle">
			<span class="fl b titleLinkColor titleName" >
					<s:property value="layout.jsonContent['title'][@com.abbcc.util.constant.layout.Piece@SIDE_CATEGORY_NEWS.name()]||
						#request['moduleMap'][@com.abbcc.util.constant.layout.Piece@SIDE_CATEGORY_NEWS.name()]"/>
				</span>
		</div>
		<div class="bodyContContent mainTextColor">
			<div id=newsCategory class="treeDiv"></div>
		</div>
	</div>