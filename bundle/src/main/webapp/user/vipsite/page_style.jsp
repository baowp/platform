<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link href="<s:url value="/css/jquery-ui.min.css"/>" rel="stylesheet" />
<link href="<s:url value="/images/fancybox/fancybox.css"/>" rel="stylesheet" />
<script type="text/javascript" src="<s:url value="/js/jquery.min.js"/>"></script>
<script type="text/javascript" src="/js/jquery/ui/jquery.ui.core.min.js"></script>
<script type="text/javascript" src="/js/jquery/ui/jquery.ui.widget.min.js"></script>
<script type="text/javascript" src="/js/jquery/ui/jquery.ui.mouse.min.js"></script>
<script type="text/javascript" src="/js/jquery/ui/jquery.ui.draggable.min.js"></script>
<script type="text/javascript" src="/js/jquery/ui/jquery.ui.position.min.js"></script>
<script type="text/javascript" src="/js/jquery/ui/jquery.ui.resizable.min.js"></script>
<script type="text/javascript" src="/js/jquery/ui/jquery.ui.dialog.min.js"></script>
<script type="text/javascript" src="<s:url value="/js/jquery/validate.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery/metadata.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery/form.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery/json.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery/cookie.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery/fancybox.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/util/context.jsp"/>"></script>
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<s:if test="maintainable">
<script type="text/javascript" src="/js/jquery/ui/jquery.ui.sortable.min.js"></script>
<script type="text/javascript" src="/js/jquery/ui/jquery.ui.tabs.min.js"></script>
<script type="text/javascript">
	bodyType={
		sl : '<s:property value="@com.abbcc.util.constant.layout.BodyType@SL"/>',
		sr : '<s:property value="@com.abbcc.util.constant.layout.BodyType@SR"/>'
	}
	laythemeState = {
		lib : '<s:property value="@com.abbcc.util.constant.layout.LaythemeState@A"/>',
		edit : '<s:property value="@com.abbcc.util.constant.layout.LaythemeState@B"/>',
		using : '<s:property value="@com.abbcc.util.constant.layout.LaythemeState@C"/>'
	}
	saveLaythemeState = laythemeState.using;	
	layout={
		<s:if test="layout.layoutId!=null">
		layoutId : '${layout.layoutId}',
		</s:if>
		layoutmoduleList : [],
		laytheme : {
			<s:if test="layout.laytheme!=null">
			laythemeId : '${layout.laytheme.laythemeId}',
			theme : {
				themeId : '${layout.laytheme.theme.themeId}',
				name : '${layout.laytheme.theme.name}',
				folder : '${layout.laytheme.theme.folder}'
			},
			name : '${layout.laytheme.name}',
			layoutId : '${layout.laytheme.layoutId}',
			style : <s:property value="layout.laytheme.style" default="{}" escape="false"/>,
			state : '${layout.laytheme.state}'
			</s:if>
		},
		belongPage : '${layout.belongPage}',
		bodyType: <s:property value="'\"'+layout.bodyType+'\"'" default="bodyType.sl" escape="false"/>
	}
	pageTheme = {
		themeId : '${pageTheme.themeId}',
		name : '${pageTheme.name}',
		folder : '${pageTheme.folder}'
	}
	jsonContent=<s:property value="layout.jsonContent" default="{}" escape="false" />;
	jsonBanner=${layout.jsonBanner};
	jsonSign=${layout.jsonSign};
	jsonFooter=${layout.jsonFooter}
	setting=${setting}
	jsonSign.setting=setting;
</script>
</s:if>
<link id="baseTheme" type="text/css" href="<s:url value="/user/css/site/vipsite.css"/>" rel="stylesheet" />
<link id="theme" type="text/css" href="<s:url value="/user/vipsite/theme/%{pageTheme.folder}/theme.css"/>" rel="stylesheet" />
<style id="theme2" type="text/css">
<s:iterator value="layout.laytheme.jsonStyle">${key}{<s:iterator value="value">${key}:${value};</s:iterator>}</s:iterator>
</style>
<script type="text/javascript">enterpriseId='${enterpriseId}';enterpriseName='${enterprise.name}';username='${username}'</script>
<script type="text/javascript" src="<s:url value="/js/cvi/busy.js"/>"></script> 
<script type="text/javascript" src="<s:url value="/user/js/dynamic/predefined.js"/>"></script>
<script type="text/javascript" src="<s:url value="/user/js/site/variable.js"/>"></script>
<script type="text/javascript" src="<s:url value="/user/js/site/vipsite.js"/>"></script>
<script type="text/javascript" src="<s:url value="/user/js/site/pagination.js"/>"></script>
<script type="text/javascript" src="<s:url value="/user/js/site/util.js"/>"></script>
<link rel="stylesheet" type="text/css" href="<s:url value="/js"/>/yui/build/treeview/assets/skins/sam/treeview.css" />
<script type="text/javascript" src="<s:url value="/js"/>/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="<s:url value="/js"/>/yui/build/connection/connection-min.js"></script>
<script type="text/javascript" src="<s:url value="/js"/>/yui/build/treeview/treeview-min.js"></script>