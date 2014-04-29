<#assign sign=enums["com.abbcc.util.constant.group.GroupPiece"].wide_album>
${command.pieceAlbumList() }
<#assign albums=command.take("albums")>

<div id="companyIntro" class="bodyCont moveChild" data-piece="${sign!'' }">
	<div class="clr"></div>
	<div class="bodyContTitle">
		<span class="fl b titleLinkColor titleName">${moduleMap[sign]!'' }
		</span>
		<#if belongPage != 'album'>
			<a class="fr fs12px nb titleLinkColor draft_no_link" href="company">${lan['more']}&gt;&gt;</a>
		</#if>
	</div>
	<div class="bodyContContent rightConteWidth rel mainTextColor"
		id="all_album_column" data-meta='{"currentPage":${albums.currentPage!''},"pageCount":${albums.pageCount!''},"url":"${sign!'' }"}'>
		<div class="no-albums mainTextColor" style="display: none;">暂时没有公开展示的相册。</div>
		<div class="albums-list" style="display: block;">
			<div class="pro_category border">
				<div style="" class="title b mainTextColor">我公司的图片分布于以下相册中，请查看：</div>
				<div class="clr"></div>
			</div>
			<div class="mt18px mb5px tal mainTextColor b">全部相册：</div>
			<ul>
				<#list albums.items as album>
					<li class="fl bodyContContentAlbumLi">
						<div class="albumCover">
							<a class="draft_no_link" target="_blank"
								href="album-detail-${album.albumId2}.html"><img border="0"
								alt="${album.name}" src="${album.mainPic!''}" width="100" height="82"
								style="margin-top: 8px;"> </a>
						</div>
						<div class="textBox">
							<a title="${album.name!''}" target="_blank"
								class="topicLink draft_no_link break"
								href="album-detail-${album.albumId2}.html">${album.name!''}</a>(${album.picCount!''})<br>
							<#if album.addTime??>${album.addTime?string("yyyy-MM-dd")}</#if>
						</div>
					</li>
				</#list>	
			</ul>
			<div class="clr"></div>
			<div class="wangpuBorderBottom3 mt10px mb5px mainTextColor">
			<#if belongPage == 'album'>
				<#assign pageList=albums>
				<#include "../../pagination.ftl">
			</#if>
			</div>
		</div>
	</div>
</div>