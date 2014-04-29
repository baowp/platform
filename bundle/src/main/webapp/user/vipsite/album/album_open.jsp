<%@ page language="java" import="java.util.*;" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>图片管理</title>
<link rel="stylesheet" href="/user/vipsite/album/css/basic.css"
	type="text/css" />
<link rel="stylesheet" href="/user/vipsite/album/css/galleriffic-2.css"
	type="text/css" />
<style type="text/css">
li.j {
	background: url("/user/vipsite/album/css/../images/QQt.jpg") no-repeat
		scroll 0 0 transparent;
	color: #FF6600;
	height: 28px;
	line-height: 28px;
	margin: 2px;
	text-align: center;
	width: 104px;
}

.topbar ul {
	margin: 0 auto;
	padding: 0;
}

.topbar ul {
	list-style: none outside none;
}

.topbar li {
	float: left;
	margin: 0 4px;
	padding: 0;
}
</style>
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript"
	src="/js/jquery/album/js/jquery.galleriffic.js"></script>
<script type="text/javascript"
	src="/js/jquery/album/js/jquery.opacityrollover.js"></script>
<script type="text/javascript">
	document.write('<style>.noscript { display: none; }</style>');
</script>
<script type="text/javascript">
	function openAlbum(obj) {
		window.location.href = "<s:url value='/user/album/albumopen'/>?id="
				+ obj;
	}
	function openPic(album, attId, starPic) {
		window.location.href = "<s:url value='/user/album/albumopenPic'/>?id="
				+ album + "&&attId=" + attId + "&&starPic=" + starPic;
	}
</script>
</head>

<body>
<s:include value="/common/resultMessage.jsp"></s:include>
<div id="page">
<div id="container">
<div class="topbar">
<ul>
	<li>所有相册:</li>
	<li><s:select list="#request.albumList" name="albumId"
		listKey="key" listValue="value" value="albumId" headerKey="0"></s:select></li>
	<li class="j"><a href="/user/album/albumuploadPic?id=${albumId}&&pageType=2">添加新图片</a></li>
	<li><a href="/user/album/albumshow">返回</a></li>
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
<div id="thumbs" class="navigation">
<ul class="thumbs noscript">
	<s:iterator value="#request.attList" status="st">
		<li><a class="thumb" name="leaf" href="${serverPath}"
			title="${filename}"> <img src="${serverPath}" alt="${filename}"
			title="${filename}" width="75" height="75" /> </a>
		<div class="caption">
		<div class="download"><a onclick="javascript:return del()"
			href="/user/album/albumpicDel?attId=${attId}&&id=${albumId}">删除</a></div>
		<div class="image-title">${filename}</div>
		<div class="image-desc">${filedesc}</div>
		</div>
		</li>
	</s:iterator>
</ul>
</div>
<div style="clear: both;"></div>
</div>
</div>
<script type="text/javascript">
	jQuery(document).ready(
			function($) {
				$("#albumId").change(function() {
					url = "/user/album/albumopen?id=" + $(this).val();
					location.href = url;
				})
				// We only want these styles applied when javascript is enabled
				$('div.navigation').css({
					'width' : '300px',
					'float' : 'left'
				});
				$('div.content').css('display', 'block');

				// Initially set opacity on thumbs and add
				// additional styling for hover effect on thumbs
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
							numThumbs : 9,
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
							prevLinkText : '&lsaquo; 上一张',
							nextLinkText : '下一张 &rsaquo;',
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
			});
</script>
</body>
</html>
