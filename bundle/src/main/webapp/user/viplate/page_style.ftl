<link href="css/jquery-ui.min.css" rel="stylesheet" />
<link href="images/fancybox/fancybox.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery/ui/jquery.ui.core.min.js"></script>
<script type="text/javascript" src="js/jquery/ui/jquery.ui.widget.min.js"></script>
<script type="text/javascript" src="js/jquery/ui/jquery.ui.mouse.min.js"></script>
<script type="text/javascript" src="js/jquery/ui/jquery.ui.draggable.min.js"></script>
<script type="text/javascript" src="js/jquery/ui/jquery.ui.position.min.js"></script>
<script type="text/javascript" src="js/jquery/ui/jquery.ui.resizable.min.js"></script>
<script type="text/javascript" src="js/jquery/ui/jquery.ui.dialog.min.js"></script>
<script type="text/javascript" src="js/jquery/validate.js"></script>
<script type="text/javascript" src="js/jquery/metadata.js"></script>
<script type="text/javascript" src="js/jquery/form.js"></script>
<script type="text/javascript" src="js/jquery/json.min.js"></script>
<script type="text/javascript" src="js/jquery/cookie.js"></script>
<script type="text/javascript" src="js/jquery/fancybox.pack.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<link type="text/css" href="css/site/vipsite.css" rel="stylesheet" />
<link type="text/css" href="theme/theme.css" rel="stylesheet" />
<#if layout.laytheme??><style type="text/css">
<#list layout.laytheme.jsonStyle?keys as key>${key}{<#list layout.laytheme.jsonStyle[key]?keys as k>${k}:${layout.laytheme.jsonStyle[key][k]};</#list>}</#list>
</style></#if>
<script type="text/javascript">enterpriseId='${user.enterpriseId}';enterpriseName='${user.getEnterprise().name!'company'}';username='${user.username}'</script>
<script type="text/javascript" src="js/cvi/busy.js"></script> 
<script type="text/javascript" src="js/site/predefined.js"></script>
<script type="text/javascript" src="js/site/variable.js"></script>
<script type="text/javascript" src="js/site/vipsite.js"></script>
<script type="text/javascript" src="js/site/pagination.js"></script>
<script type="text/javascript" src="js/site/util.js"></script>
<link rel="stylesheet" type="text/css" href="js/yui/build/treeview/assets/skins/sam/treeview.css" />
<script type="text/javascript" src="js/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="js/yui/build/connection/connection-min.js"></script>
<script type="text/javascript" src="js/yui/build/treeview/treeview-min.js"></script>
<#function extract objs...>
	<#list objs as obj>
		<#if obj??>
			<#return obj>
		</#if>
	</#list>
</#function>
<#function server>
	<#if serverHttpDomain??>
	<#else><#assign serverHttpDomain="http://51archetype.com"></#if>
	<#return serverHttpDomain>
</#function>
