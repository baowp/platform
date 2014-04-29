<#assign sign=enums["com.abbcc.util.constant.group.GroupPiece"].wide_news>
${command.pieceNews() }
<#assign commonNews=command.take("commonNews")>
<div id="news" class="bodyCont moveChild">
	<div class="clr"></div>
	<div class="bodyContTitle">
		<span class="fl b titleLinkColor titleName"> ${moduleMap[sign]!''} </span>
		<#if belongPage != 'news'>
			<a class="fr fs12px nb titleLinkColor draft_no_link" href="news.html">${lan['more']}&gt;&gt;</a>
		</#if>
	</div>
	<div class="bodyContContent rel rightConteWidth"
		data-meta='{"currentPage":${commonNews.currentPage!'' },"pageCount":${commonNews.pageCount!''},"parentKey":"newsCategory","parentValue":"${command.take("newsCategory")!''}","url":"${sign!'' }"}'>
		<div class="tal mainTextColor break mainNews">
			<#if command.take("topNews")?? && commonNews.currentPage==1 >
				<#assign topNews=command.take("topNews")>
				<div class="topNews listNews">
					<#list topNews as n>
						<dl class="glitzColor listNewsDl">
							<dt title="${n.title!''}">
								[${lan['news.top']}]<a target="_blank"
									href="news-detail-${n.newsId2}.html" class="topicLink">${n.title!''}&nbsp;</a>
							</dt>
							<dd>${n.origin!''}&nbsp;</dd>
							<dd><#if n.addTime??>${n.addTime?string("yyyy-MM-dd hh:mm:ss")}</#if></dd>
						</dl>
					</#list>
				</div>
			</#if>
			<div class="commonNews listNews">
				<#list commonNews.items as c >
					<dl class="glitzColor listNewsDl">
						<dt title="${c.title!''}"><a target="_blank" href="news-detail-${c.newsId2}.html"
								class="topicLink">${c.title!''}&nbsp;</a>
						</dt>
						<dd>${c.origin!''}&nbsp;</dd>
						<dd><#if c.addTime??>${c.addTime?string("yyyy-MM-dd hh:mm:ss")}</#if></dd>
					</dl>
				</#list>
			</div>
		</div>
		<#if belongPage == 'news'>
			<#assign pageList=commonNews>
			<#include "../../pagination.ftl">
		</#if>
		<div class="clr"></div>
	</div>
</div>