<#assign sign=enums["com.abbcc.util.constant.group.GroupPiece"].hunk_flash>
<div id="describe_flash" class="describe_flash moveChild headCont">
	<embed id="topic_flash" wmode="transparent"
	 width="952" <#if layout.jsonSign['topicFlash']??>src="${layout.jsonSign.topicFlash.src!'' }" height="${layout.jsonSign.topicFlash.height!''}"</#if>>
</div>
