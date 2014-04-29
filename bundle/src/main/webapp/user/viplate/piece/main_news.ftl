${action.pieceNews()}
<#assign commonNews=action.root.commonNews>
<div id="news" class="bodyCont moveChild">
	<div class="clr"></div>
	<div class="bodyContTitle">
		<span class="fl b titleLinkColor titleName" >
				${extract(layout.jsonContent['title'][enums["com.abbcc.util.constant.layout.Piece"].MAIN_NEWS.name()],
					moduleMap[enums["com.abbcc.util.constant.layout.Piece"].MAIN_NEWS.name()])}
			</span>
		<#if layout.belongPage!=enums["com.abbcc.util.constant.layout.BelongPage"].NEWS>
			<a class="fr fs12px nb titleLinkColor draft_no_link" href="news.html">${lan['more']} &gt;&gt;</a>
		</#if>
	</div>
	<div class="bodyContContent rel rightConteWidth">
		<input type="hidden" id="meta" class="{currentPage:${commonNews.currentPage},pageCount:${commonNews.pageCount},parentKey:'${newsCategory!''}',parentValue:'${newsCategory!''}'}"/>
		<div class="tal mainTextColor break mainNews">
			<#if action.root.topNews?? && action.page==1 >
			<#assign topNews=action.root.topNews>
			<div class="topNews listNews">
			  	<#list topNews as item>
			  		<dl class="glitzColor listNewsDl">
			  			<dt title="${item.title!''}">&nbsp;[${lan['news.top']}]<a target="_blank" href="news-detail-${item.newsId2}.html" class="topicLink">${item.title!''}</a></dt>
			  			<dd>&nbsp;${item.origin!''}</dd>
			  			<dd><#if item.addTime??>${item.addTime?string("yyyy-MM-dd hh:mm:ss")}</#if></dd>
			  		</dl>
			  	</#list>				 
		  	</div>
		  	</#if>
		  	<div class="commonNews listNews">
			  	<#list commonNews.items as item>
			  		<dl class="glitzColor listNewsDl">
			  			<dt title="${item.title!''}">&nbsp;<a target="_blank" href="news-detail-${item.newsId2}.html" class="topicLink">${item.title!''}</a></dt>
			  			<dd>&nbsp;${item.origin!''}</dd>
			  			<dd><#if item.addTime??>${item.addTime?string("yyyy-MM-dd hh:mm:ss")}</#if></dd>
			  		</dl>
			  	</#list>
		  	</div>
		</div>
		<#if layout.belongPage==enums["com.abbcc.util.constant.layout.BelongPage"].NEWS>
			<#assign pageList=commonNews>
			<#include "../pagination.ftl">
		</#if>
		<div class="clr"></div>
	</div>
</div>