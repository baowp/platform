<link rel="stylesheet" href="js/site/album/css/basic.css" type="text/css" />
<link rel="stylesheet" href="js/site/album/css/galleriffic-2.css" type="text/css" />
<script type="text/javascript" src="js/site/album/jquery.galleriffic.js"></script>
<script type="text/javascript" src="js/site/album/jquery.opacityrollover.js"></script>
<#if !command.take("album")??>
	${command.pieceAlbumDetail(itemId) }
</#if>
<#assign album=command.take("album")>
<div id="container" class="bodyCont moveChild mainTextColor detail">
<h1><a href="album">我的相册</a> &gt; ${album.name!'' }</h1>
<div id="thumbs" class="navigation">
<ul class="thumbs noscript">
	<#list pictrues as pList>
	<li><a class="thumb" name="leaf"
		href="${pList.picUrl(3)!''}"
		title="${pList.filename!''}"> <img
		src="${pList.picUrl(3)!''}"
		alt="${pList.filename!''}" width="75" height="75"/> </a>
	<div class="caption">
	<div class="image-title">${pList.filename!''}</div>
	<div class="image-desc">${pList.filedesc!''}</div>
	</div>
	</li>
</#list>
</ul>
</div>
<div id="gallery" class="content">
	<div id="controls" class="controls"></div>
	<div class="slideshow-container">
	<div id="loading" class="loader"></div>
	<div id="slideshow" class="slideshow"></div>
	</div>
	
	<div id="caption" class="caption-container"></div>
</div>
<div style="clear: both;"></div>
</div>
<script  type="text/javascript">
$(function(){
	$("#AlbumList").change(function(){
		location.href=$(this).val();
	})
})
$('div.navigation').css({
	'width' : '300px',
	'float' : 'left'
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