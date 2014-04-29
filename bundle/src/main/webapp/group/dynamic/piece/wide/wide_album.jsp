<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="sign"
	value="${spel['T(com.abbcc.util.constant.group.GroupPiece).wide_album'].name()}" />
<c:if test="${empty albums }">
	${command.pieceAlbumList() }
</c:if>

<div id="companyIntro" class="bodyCont moveChild" data-piece="${sign }">
	<div class="clr"></div>
	<div class="bodyContTitle">
		<span class="fl b titleLinkColor titleName">${moduleMap[sign] }
		</span>
		<c:if test="${belongPage ne 'album' }">
			<a class="fr fs12px nb titleLinkColor draft_no_link" href="company">${lan['more']}&gt;&gt;</a>
		</c:if>
	</div>
	<div class="bodyContContent rightConteWidth rel mainTextColor"
		id="all_album_column" data-meta='{"currentPage":${albums.currentPage},"pageCount":${albums.pageCount},"url":"${sign }"}'>
		<div class="no-albums mainTextColor" style="display: none;">暂时没有公开展示的相册。</div>
		<div class="albums-list" style="display: block;">
			<div class="pro_category border">
				<div style="" class="title b mainTextColor">我公司的图片分布于以下相册中，请查看：</div>
				<div class="clr"></div>
			</div>
			<div class="mt18px mb5px tal mainTextColor b">全部相册：</div>
			<ul>
				<c:forEach items="${albums.items }" var="album">
					<li class="fl bodyContContentAlbumLi">
						<div class="albumCover">
							<a class="draft_no_link" target="_blank"
								href="album_detail?itemId=${album.albumId}"><img border="0"
								alt="${album.name}" src="${album.mainPic}" width="100" height="82"
								style="margin-top: 8px;"> </a>
						</div>
						<div class="textBox">
							<a title="${album.name}" target="_blank"
								class="topicLink draft_no_link break"
								href="album_detail?itemId=${album.albumId}">${album.name}</a>(${album.picCount})<br>
							<fmt:formatDate value="${album.addTime }" pattern="yyyy-MM-dd"/>
						</div>
					</li>
				</c:forEach>
			</ul>
			<div class="clr"></div>
			<div class="wangpuBorderBottom3 mt10px mb5px mainTextColor">
				<c:set var="pageList" value="${albums }" scope="request"/>
				<jsp:include page="../../pagination.jsp"></jsp:include>
			</div>
		</div>
	</div>
</div>