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
<script type="text/javascript" src="js/jquery/validate.min.js"></script>
<script type="text/javascript" src="js/jquery/metadata.js"></script>
<script type="text/javascript" src="js/jquery/form.min.js"></script>
<script type="text/javascript" src="js/jquery/json.min.js"></script>
<script type="text/javascript" src="js/jquery/cookie.js"></script>
<script type="text/javascript" src="js/jquery/fancybox.pack.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<link href="css/dialog/jq_aero.css" rel="stylesheet" />
<!--[if IE 6]><script type="text/javascript" src="css/dialog/aero_ie6.js"></script><![endif]-->
<link href="css/vipsite.css" rel="stylesheet" />
<link href="theme/theme.css" rel="stylesheet" />
<#if laytheme??>
<style type="text/css">
	<#list laytheme.jsonStyle?keys as key>${key}{<#list laytheme.jsonStyle[key]?keys as k>${k}:${laytheme.jsonStyle[key][k]};</#list>}</#list>
</style>	
</#if>
<script type="text/javascript">enterpriseId='${enterprise.enterpriseId}';enterpriseName='${enterprise.name}';username='${user.username}'</script>
<script type="text/javascript" src="js/cvi/busy.js"></script> 
<script type="text/javascript" src="js/site/predefined.js"></script>
<script type="text/javascript" src="js/site/variable.js"></script>
<script type="text/javascript" src="js/site/vipsite.js"></script>
<script type="text/javascript" src="js/site/pagination.js"></script>
<script type="text/javascript" src="js/site/util.js"></script>
<script type="text/javascript" src="js/site/backtop.js"></script>
<script type="text/javascript" src="js/util/language.js"></script>
<#function server>
	<#if serverHttpDomain??>
	<#else><#assign serverHttpDomain="http://51archetype.com"></#if>
	<#return serverHttpDomain>
</#function>
