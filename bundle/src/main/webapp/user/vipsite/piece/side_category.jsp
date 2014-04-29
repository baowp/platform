<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="#request['categoryList']==null">
	<s:action var="contacts" name="*/pieceCategoryList" namespace="/vip" ignoreContextParams="true">
		<s:param name="enterpriseId" value="enterpriseId"/>
	</s:action>
</s:if>
<script type="text/javascript" src="<s:url value="/user/js"/>/site/category_tree.js"></script>
<script type="text/javascript">
	categoryList = [
	   <s:iterator value="#request['categoryList']">
	   {id : '${categoryId}' , name : '${name}' , image : '${image2}', ifLeaf : ${ifLeaf}},
	   </s:iterator> 0
	   ];
	categoryList.pop();
</script>
	<div id="category_nav" class="bodyCont moveChild">
		<s:hidden name="piece" value="%{@com.abbcc.util.constant.layout.Piece@SIDE_CATEGORY}"/>
		<div class="clr"></div>
		<div class="bodyContTitle">
			<span class="fl b titleLinkColor titleName" >
					<s:property value="layout.jsonContent['title'][@com.abbcc.util.constant.layout.Piece@SIDE_CATEGORY.name()]||
						#request['moduleMap'][@com.abbcc.util.constant.layout.Piece@SIDE_CATEGORY.name()]"/>
				</span>
		</div>
		<div class="bodyContContent mainTextColor">
			<div id=categoryTree class="treeDiv"></div>
		</div>
	</div>