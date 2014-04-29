<#if pageList.pageCount gt 1>
<div class="pagination mainTextColor">
	<div class="fl">
		<#if pageList.currentPage!=1>		
			<a class="danaiPageUp" href="javascript:prevPage()">${lan['pagination.prev']}</a>
		</#if>
		${action.pagination(pageList.currentPage,pageList.pageCount)}
		<#list action.root.pagination as page>
			<#if page==pageList.currentPage>
				<span class="danaiPageCurrent">${page}</span>
			<#else>
				<a href="javascript:toPage(${page})" class="danaiPageNum">${page}</a>
			</#if>
		</#list>
		<#if pageList.currentPage!=pageList.pageCount>
			<a class="danaiPageDown" href="javascript:nextPage()">${lan['pagination.next']}</a>
		</#if>
	</div>
	<div class="fl danaiPageDes">
		<span>${pageList.currentPage}/${pageList.pageCount}</span> ${lan['pagination.page']}
		<#if pageList.pageCount gt 1>
			${lan['pagination.to']} <input id="pageNum" type="text" size="3"/> ${lan['pagination.page']} 
			<input type="button" onclick="toPage(this)" value="${lan['pagination.agree']}">
		</#if>
	</div>
</div>
</#if>