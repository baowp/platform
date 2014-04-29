<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>abbcc</title>
</head>
<body>
<div id=tabContainer>
	<ul>
		<li><a href="#tab1">页面头尾</a></li>
		<li><a href="#tab2">侧边栏</a></li>
		<li><a href="#tab3">主体部分</a></li> 
		<li><a href="#tab4">导航菜单</a></li> 
<!--		<li><a href="#tab5">页面尾部</a></li> -->
	</ul>
	<div id="tab1">
		<div id="tab1_head" class="tab_content tabmin_ul" data-contId="headList">
			<s:checkboxlist list="moduleHeadSet" listKey="moduleId" listValue="name" 
				name="moduleHead" value="moduleIdHeadList"/>
		</div>
		<div id="tab1_below" class="tab_content tabmin_ul" data-contId="belowList">
			<ul id="below_ul" class="list tabmin_ul connectedul">
				
			</ul>
		</div>	
	</div>
	<div id="tab2" class="tab_content" data-contId="content1">
		<s:checkboxlist list="moduleSideSet" listKey="moduleId" listValue="name" 
			name="moduleSide" value="moduleIdSideList"/>
	</div>
	<div id="tab3" class="tab_content" data-contId="content2">
		<s:if test="#parameters['belongPage'][0]==@com.abbcc.util.constant.layout.BelongPage@PRODUCT_DETAIL.name()">
			<s:radio list="moduleProductSet" listKey="moduleId" listValue="name" 
				name="moduleMain" value="moduleIdProductList"/>
		</s:if>
		<s:else>
			<s:checkboxlist list="moduleMainSet" listKey="moduleId" listValue="name" 
				name="moduleMain" value="moduleIdMainList"/>
		</s:else>
	</div>
	<div id="tab4" class="tab_content" data-contId="list_nav">
		<s:checkboxlist list="moduleNavSet" listKey="moduleId" listValue="name" 
			name="moduleNav" value="moduleIdNavList"/>
	</div>
</div>
</body>
</html>