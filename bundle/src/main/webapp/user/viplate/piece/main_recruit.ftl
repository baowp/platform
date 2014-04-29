${action.pieceRecruit()}
<#assign recruit=action.root.recruit>
<div id="recruit" class="bodyCont moveChild">
	<div class="clr"></div>
	<div class="bodyContTitle">
		<span class="fl b titleLinkColor titleName" >
				${extract(layout.jsonContent['title'][enums["com.abbcc.util.constant.layout.Piece"].MAIN_RECRUIT.name()],
					moduleMap[enums["com.abbcc.util.constant.layout.Piece"].MAIN_RECRUIT.name()])}
			</span>
		<#if layout.belongPage!=enums["com.abbcc.util.constant.layout.BelongPage"].RECRUIT>
			<a class="fr fs12px nb titleLinkColor draft_no_link" href="recruit.html">${lan['more']} &gt;&gt;</a>
		</#if>
	</div>
	<div class="bodyContContent rel rightConteWidth">
		<input type="hidden" id="meta" class="{currentPage:${recruit.currentPage},pageCount:${recruit.pageCount}}"/>
		<div class="tal mainTextColor break recruit">
			<#list recruit.items as item>
				<dl class="glitzColor recruitDl">
					<dt class="title">${item.title!'' }</dt>
					<dt>${lan['recruit.duty']}${item.duty!'' } ${lan['recruit.sum']}${item.sum!'' }</dt>
					<dt>${lan['recruit.addTime']}<#if item.addTime??>${item.addTime?string("yyyy-MM-dd hh:mm:ss")}</#if></dt>
					<dt class="fl">${lan['recruit.requirement']}</dt>
					<dd>${item.content!'' }</dd>
				</dl>
			</#list>
		</div>
		<#if layout.belongPage==enums["com.abbcc.util.constant.layout.BelongPage"].RECRUIT>
		<#assign pageList=recruit>
		<#include "../pagination.ftl">
		</#if>
		<div class="clr"></div>
	</div>
</div>