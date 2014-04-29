	
<div id="theme_pic" class="headTopic moveChild">
	<div class="clr"></div>
	<#if layout.jsonSign['topicFlash']??>
		<div id="describe_flash" class="describe_flash">
			<embed id="topic_flash" wmode="transparent"
			 width="952" src="${ layout.jsonSign.topicFlash.src}"
				height="${ layout.jsonSign.topicFlash.height}">
		</div>
	<#else>
		<div id="description" class="description">
			<div class="topDesc"><span id="topDesc">${(layout.jsonSign['topDesc'])!''}</span></div>
			<div class="bottomDesc"><span id="bottomDesc">${(layout.jsonSign['bottomDesc'])!''}</span></div>
		</div>
	</#if>
</div>