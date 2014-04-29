<#assign sign=enums["com.abbcc.util.constant.group.GroupPiece"].wide_cert>
${command.pieceCert() }
<#assign cert=command.take("cert")>
<div id="cert" class="bodyCont moveChild" data-piece="${sign!'' }">
	<div class="clr"></div>
	<div class="bodyContTitle">
		<span class="fl b titleLinkColor titleName" >
			${moduleMap[sign]!'' }
		</span>
		<#if belongPage != 'cert'>
			<a class="fr fs12px nb titleLinkColor draft_no_link" href="cert.html">${lan['more']}&gt;&gt;</a>
		</#if>
	</div>
	<div class="bodyContContent rel rightConteWidth" data-meta='{"currentPage":${cert.currentPage!''},"pageCount":${cert.pageCount!''},"url":"${sign }"}'>
		<div class="tal mainTextColor break cert">
			<ul class="glitzColor certUl"><li class="pic">${lan['cert.pic']}</li><li class="name">${lan['cert.name']}</li><li class="organize">${lan['cert.organize']}</li></ul>
			<#list cert.items as c>
				<#assign pic="c.picUrl()!''">
				<ul class="glitzColor certUl">
					<li class="pic"><a href="${pic }" class="showCertPic"><img border="0" src="${c.picUrl(5)!''}"/></a></li>
					<li class="name mid"><a href="${pic!'' }" class="showCertPic topicLink">${c.name!'' }</a></li>
					<li class="organize mid">${c.organize!'' }</li>
				</ul>
			</#list>
		</div>
		<div class="clr"></div>
		<div class="wangpuBorderBottom3 mt10px mb5px mainTextColor">
			<#if belongPage == 'cert'>
				<#assign pageList=cert>
				<#include "../../pagination.ftl">
			</#if>
		</div>
	</div>
	<script type="text/javascript">
		$(".cert .mid").each(function(){
			var $this=$(this);
			$this.css("marginTop",($this.parent().height()-$this.height())/2);
		});
		if($(".showCertPic").length)$(".showCertPic").fancybox();
	</script>
</div>