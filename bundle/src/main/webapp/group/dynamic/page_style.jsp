<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="/css/jquery-ui.min.css" rel="stylesheet" />
<link href="/images/fancybox/fancybox.css" rel="stylesheet" />
<script type="text/javascript" src="/js/jquery.min.js"></script>
<script type="text/javascript" src="/js/jquery/ui/jquery.ui.core.min.js"></script>
<script type="text/javascript" src="/js/jquery/ui/jquery.ui.widget.min.js"></script>
<script type="text/javascript" src="/js/jquery/ui/jquery.ui.mouse.min.js"></script>
<script type="text/javascript" src="/js/jquery/ui/jquery.ui.draggable.min.js"></script>
<script type="text/javascript" src="/js/jquery/ui/jquery.ui.position.min.js"></script>
<script type="text/javascript" src="/js/jquery/ui/jquery.ui.resizable.min.js"></script>
<script type="text/javascript" src="/js/jquery/ui/jquery.ui.dialog.min.js"></script>
<script type="text/javascript" src="/js/jquery/ui/jquery.ui.button.min.js"></script>
<script type="text/javascript" src="/js/jquery/validate.min.js"></script>
<script type="text/javascript" src="/js/jquery/metadata.js"></script>
<script type="text/javascript" src="/js/jquery/form.min.js"></script>
<script type="text/javascript" src="/js/jquery/json.min.js"></script>
<script type="text/javascript" src="/js/jquery/cookie.js"></script>
<script type="text/javascript" src="/js/jquery/fancybox.pack.js"></script>
<script type="text/javascript" src="/js/util/context.jsp"></script>
<script type="text/javascript" src="/js/common.js"></script>
<link href="/css/dialog/jq_aero.css" rel="stylesheet" />
<!--[if IE 6]><script type="text/javascript" src="/css/dialog/aero_ie6.js"></script><![endif]-->
<c:if test="${maintainable }">
<script type="text/javascript" src="/js/jquery/ui/jquery.ui.sortable.min.js"></script>
<script type="text/javascript" src="/js/jquery/ui/jquery.ui.tabs.min.js"></script>
<script type="text/javascript">
	groupLaythemeType = { 
		lib : '${spel["T(com.abbcc.util.constant.group.GroupLaythemeType).A"]}',
		edit : '${spel["T(com.abbcc.util.constant.group.GroupLaythemeType).B"]}',
		using : '${spel["T(com.abbcc.util.constant.group.GroupLaythemeType).C"]}'
 	} 
 	saveLaythemeType = groupLaythemeType.using;	
 	layout={ 
		layoutId : '${layout.layoutId}'
	}
	belongPage = '${belongPage}'
	listWidth={
		<c:if test="${not empty listWidth}">
		widthId : '${listWidth.widthId}'
		</c:if>
	}
	laytheme = { 
		<c:if test="${not empty laytheme}">
		laythemeId : '${laytheme.laythemeId}',
		theme : { 
			themeId : '${laytheme.theme.themeId}',
			name : '${laytheme.theme.name}',
			folder : '${laytheme.theme.folder}'
		}, 
		layoutId : '${laytheme.layoutId}',
		style : ${laytheme.style},
		type : '${laytheme.type}'
		</c:if>
	}
	layout.laytheme=laytheme;
	pageTheme = { 
		themeId : '${pageTheme.themeId}',
		name : '${pageTheme.name}',
		folder : '${pageTheme.folder}'
	} 
	
	moduleMap = ${command.toJSON(moduleMap)}
	
	jsonSign=${layout.jsonSign};
</script>
</c:if>
<link id="baseTheme" type="text/css" href="/group/dynamic/css/vipsite.css" rel="stylesheet" />
<link id="theme" type="text/css" href="/group/dynamic/theme/${pageTheme.folder}/theme.css" rel="stylesheet" />
<style id="theme2" type="text/css">
<c:forEach items="${laytheme.jsonStyle }" var="lay">${lay.key }{<c:forEach items="${lay.value }" var="lay2">${lay2.key }:${lay2.value };</c:forEach>}</c:forEach>
</style> 
<script type="text/javascript">enterpriseId='${enterprise.enterpriseId}';enterpriseName='${enterprise.name}';username='${user.username}';itemId='${itemId}'</script>
<script type="text/javascript" src="/js/cvi/busy.js"></script> 
<script type="text/javascript" src="/group/dynamic/js/predefined.js"></script>
<script type="text/javascript" src="/group/dynamic/js/variable.js"></script>
<script type="text/javascript" src="/group/dynamic/js/vipsite.js"></script>
<script type="text/javascript" src="/group/dynamic/js/pagination.js"></script>
<script type="text/javascript" src="/group/dynamic/js/util.js"></script>
<script type="text/javascript" src="/group/dynamic/js/backtop.js"></script>
<script type="text/javascript" src="/js/util/language.js"></script>