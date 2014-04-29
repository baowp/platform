${action.piecePhotoList()}
<#assign photos=action.root.photos>
		<div id="products" class="bodyCont moveChild glare_type_1">
			<div class="clr"></div>
			<div class="bodyContTitle">
				<span class="fl b titleLinkColor titleName" >
					${extract(layout.jsonContent['title'][enums["com.abbcc.util.constant.layout.Piece"].MAIN_PHOTO.name()],
						moduleMap[enums["com.abbcc.util.constant.layout.Piece"].MAIN_PHOTO.name()])}
				</span>
				<#if layout.belongPage!=enums["com.abbcc.util.constant.layout.BelongPage"].PHOTO>
					<a class="fr fs12px nb titleLinkColor draft_no_link" href="photo.html">${lan['more']} &gt;&gt;</a>
				</#if>
			</div>
<div class="bodyContContent rightConteWidth rel mainTextColor"
	id="all_album_column">
<div class="no-albums mainTextColor" style="display: none;">暂时没有公开展示的相册。</div>
<div class="albums-list" style="display: block;">
<div class="pro_category border">
<div style="" class="title b mainTextColor">我公司的图片分布于以下相册中，请查看：</div>
<div class="clr"></div>
</div>
<div class="mt18px mb5px tal mainTextColor b">全部相册：</div>
<ul>
<#list photos.items as photo> 
	<li class="fl bodyContContentAlbumLi">
	<div class="albumCover"><a class="draft_no_link" target="_blank"
		href="photo-detail-${photo.albumId2}.html"><img
		border="0" alt="${photo.name!''}"
		src="${photo.mainPic!''}" width="100" height="82"  style="margin-top: 8px;"></a></div>
	<div class="textBox"><a title="${photo.name!''}" target="_blank"
		class="topicLink draft_no_link break"
		href="photo-detail-${photo.albumId2}.html">${photo.name!''}</a>(${photo.picCount!''})<br>
	${photo.addTime!''}</div>
	</li>
</#list>
</ul>
<div class="clr"></div>
<div class="wangpuBorderBottom3 mt10px mb5px mainTextColor">
				<#if layout.belongPage==enums["com.abbcc.util.constant.layout.BelongPage"].PHOTO>
				<#assign pageList=photos>
				<#include "../pagination.ftl">
				</#if>
</div>
</div>
</div>			
		</div>