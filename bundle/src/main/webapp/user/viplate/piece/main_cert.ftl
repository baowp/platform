<#if action.root.cert??>
<#else>
	${action.pieceCert()}
</#if>
<#assign cert=action.root.cert>
<div id="cert" class="bodyCont moveChild">
	<div class="clr"></div>
	<div class="bodyContTitle">
		<span class="fl b titleLinkColor titleName" >
				${extract(layout.jsonContent['title'][enums["com.abbcc.util.constant.layout.Piece"].MAIN_CERT.name()],
					moduleMap[enums["com.abbcc.util.constant.layout.Piece"].MAIN_CERT.name()])}
			</span>
	
	</div>
	<div class="bodyContContent rel rightConteWidth">
		<div class="tal mainTextColor break cert">
			<ul class="glitzColor certUl"><li class="pic">${lan['cert.pic']}</li><li class="name">${lan['cert.name']}</li><li class="organize">${lan['cert.organize']}</li></ul>
			<#list cert.items as item>
				<ul class="glitzColor certUl">
					<li class="pic"><a href="${item.picUrl()!''}" class="showCertPic"><img border="0" src="${item.picUrl(5)!''}"/></a></li>
					<li class="name mid"><a href="${item.picUrl()!''}" class="showCertPic topicLink">${item.name!'' }</a></li>
					<li class="organize mid">${item.organize!'' }</li>
				</ul>
			</#list>
		</div>
		<div class="clr"></div>
	</div>
	<script type="text/javascript">
		$(".cert .mid").each(function(){
			var $this=$(this);
			$this.css("marginTop",($this.parent().height()-$this.height())/2);
		});
		if($(".showCertPic").length)$(".showCertPic").fancybox();
	</script>
</div>