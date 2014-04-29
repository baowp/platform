<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- We only want the thunbnails to display when javascript is disabled -->
<c:set var="sign" value="${spel['T(com.abbcc.util.constant.group.GroupPiece).detail_album'].name()}"/>
<c:if test="${empty album}">
	${command.pieceAlbumDetail(itemId) }
</c:if>
<div id="container" class="bodyCont moveChild mainTextColor detail" data-piece="${sign }">
<link rel="stylesheet" href="/group/dynamic/js/album/css/galleriffic-2.css" type="text/css" />
<script type="text/javascript" src="/group/dynamic/js/album/jquery.galleriffic.js"></script>
<script type="text/javascript" src="/group/dynamic/js/album/jquery.opacityrollover.js"></script>
<h1><a href="album">我的相册</a> &gt; <c:out value="${album.name }"></c:out></h1>
<div id="thumbs" class="navigation">
<ul class="thumbs noscript">
	<c:forEach items="${pictrues}" var="pList">
	<li><a class="thumb" name="leaf"
		href="${pList.picUrl(3)}"
		title="${pList.filename}"> <img
		src="${pList.picUrl(3)}"
		alt="${pList.filename}" width="75" height="75"/> </a>
	<div class="caption">
	<div class="image-title">${pList.filename}</div>
	<div class="image-desc">${pList.filedesc}</div>
	</div>
	</li>
	</c:forEach>
</ul>
</div>
<!-- Start Advanced Gallery Html Containers -->
<div id="gallery" class="content">
	<div id="controls" class="controls"></div>
	<div class="slideshow-container">
	<div id="loading" class="loader"></div>
	<div id="slideshow" class="slideshow"></div>
	</div>
	
	<div id="caption" class="caption-container"></div>
</div>
<div style="clear: both;"></div>
<script  type="text/javascript">
$('div.navigation').css({
	'width' : '100%'
});
$('div.content').css('display', 'block');

var onMouseOutOpacity = 0.67;
$('#thumbs ul.thumbs li').opacityrollover({
	mouseOutOpacity : onMouseOutOpacity,
	mouseOverOpacity : 1.0,
	fadeSpeed : 'fast',
	exemptionSelector : '.selected'
});

// Initialize Advanced Galleriffic Gallery
var gallery = $('#thumbs').galleriffic(
		{
			delay : 2500,
			numThumbs : 15,
			preloadAhead : 10,
			enableTopPager : true,
			enableBottomPager : true,
			maxPagesToShow : 7,
			imageContainerSel : '#slideshow',
			controlsContainerSel : '#controls',
			captionContainerSel : '#caption',
			loadingContainerSel : '#loading',
			renderSSControls : true,
			renderNavControls : true,
			playLinkText : '自动播放',
			pauseLinkText : '停止播放',
			prevLinkText : '&lsaquo;上一张',
			nextLinkText : '下一张&rsaquo;',
			nextPageLinkText : '下一页 &rsaquo;',
			prevPageLinkText : '&lsaquo; 上一页',
			enableHistory : false,
			autoStart : false,
			syncTransitions : true,
			defaultTransitionDuration : 900,
			onSlideChange : function(prevIndex, nextIndex) {
				// 'this' refers to the gallery, which is an extension of $('#thumbs')
				this.find('ul.thumbs').children().eq(prevIndex)
						.fadeTo('fast', onMouseOutOpacity)
						.end().eq(nextIndex)
						.fadeTo('fast', 1.0);
			},
			onPageTransitionOut : function(callback) {
				this.fadeTo('fast', 0.0, callback);
			},
			onPageTransitionIn : function() {
				this.fadeTo('fast', 1.0);
			}
		});
</script>
</div>
