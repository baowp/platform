<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="#request['photos']==null">
		<s:action var="photourl" name="*/piecePhotoList" namespace="/vip" >
			<s:param name="enterpriseId" value="enterpriseId"/>
		</s:action>
	</s:if>
<div id="companyIntro" class="bodyCont moveChild"><s:hidden
	name="piece"
	value="%{@com.abbcc.util.constant.layout.Piece@MAIN_PHOTO}" />
<div class="clr"></div>
<div class="bodyContTitle"><span
	class="fl b titleLinkColor titleName"> <s:property
	value="layout.jsonContent['title'][@com.abbcc.util.constant.layout.Piece@MAIN_PHOTO.name()]||
							#request['moduleMap'][@com.abbcc.util.constant.layout.Piece@MAIN_PHOTO.name()]" />
</span> <s:if
	test="layout.belongPage!=@com.abbcc.util.constant.layout.BelongPage@PHOTO">
	<a class="fr fs12px nb titleLinkColor draft_no_link" href="company">${lan['more']}
	&gt;&gt;</a>
</s:if></div>
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
<s:iterator value="#request.photos.items">
	<li class="fl bodyContContentAlbumLi">
	<div class="albumCover"><a class="draft_no_link" target="_blank"
		href="photo_detail?albumId=${albumId}"><img
		border="0" alt="${name}"
		src="${mainPic}" width="100" height="82" style="margin-top: 8px;"></a></div>
	<div class="textBox"><a title="${name}" target="_blank"
		class="topicLink draft_no_link break"
		href="photo_detail?albumId=${albumId}">${name}</a>(${picCount})<br>
	<s:date	name="addTime" format="yyyy-MM-dd" /></div>
	</li>
</s:iterator>
</ul>
<div class="clr"></div>
<div class="wangpuBorderBottom3 mt10px mb5px mainTextColor">
<s:set name="pageList" value="#request['photos']"/>
					<s:include value="../pagination.jsp"/>
</div>
</div>
</div>
</div>