<#assign sign=enums["com.abbcc.util.constant.group.GroupPiece"].wide_recruit>
${command.pieceRecruit() }
<#assign recruit=command.take("recruit")>

<div id="recruit" class="bodyCont moveChild" data-piece="${sign }">
	<div class="clr"></div>
	<div class="bodyContTitle">
		<span class="fl b titleLinkColor titleName" >${moduleMap[sign]!'' }</span>
		<#if belongPage != 'recruit'>
			<a class="fr fs12px nb titleLinkColor draft_no_link" href="recruit.html">${lan['more']} &gt;&gt;</a>
		</#if>
	</div>
	<div class="bodyContContent rel rightConteWidth" data-meta='{"currentPage":${recruit.currentPage!''},"pageCount":${recruit.pageCount!''},"url":"${sign!''}"}'>
		<div class="tal mainTextColor break recruit">
			<#list recruit.items as c>
				<dl class="glitzColor recruitDl">
					<dt class="title">${c.title!'' }</dt>
					<dt>${lan['recruit.duty']}${c.duty!'' } ${lan['recruit.sum']}${c.sum!'' }</dt>
					<dt>${lan['recruit.addTime']!''}
					<#if c.addTime??>${c.addTime?string("yyyy-MM-dd hh:mm:ss")}</#if>
					<dt class="fl">${lan['recruit.requirement']}</dt>
					<dd>${c.content!'' }</dd>
				</dl>
			</#list>
		</div>
				<#assign pageList=recruit>
				<#include "../../pagination.ftl">
		<div class="clr"></div>
	</div>
</div>