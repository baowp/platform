<#if !command.take("news")??>
	${command.pieceNewsDetail(itemId) }
</#if>
<#assign news=command.take("news")>
<div class="bodyCont mainTextColor">
	<div class="wangpuBodyContTitle glitzColor">
    	<span class="b titleName wangpuTopTitle">${news.title!''}</span>&nbsp;
    </div>
    <div style="margin: 8px auto; min-height: 320px; width: 700px;">
		<div class="newsInfo">
			<span>${lan['news.origin']}${news.origin!''}</span>
			<span>${lan['news.author']}${news.author!''}</span>
			<span>${lan['news.addTime']}${news.addTime?string(lan['news.format'])}/></span>
		</div>
		<div class="newsContent">${news.content!''}</div>
    </div>   
</div>