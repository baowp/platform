<!DOCTYPE wml PUBLIC "-//WAPFORUM//DTD WML 1.1//EN" "http://www.wapfourm.org/DTD/wml_1.1.xml">
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<title>我的相册</title>
<link rel="stylesheet" href="/js/jquery/album/css/basic.css" type="text/css" />
<link rel="stylesheet" href="/js/jquery/album/css/galleriffic-2.css" type="text/css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="/js/jquery/album/js/jquery.galleriffic.js"></script>
<script type="text/javascript" src="js/jquery/album/js/jquery.opacityrollover.js"></script>
<!-- We only want the thunbnails to display when javascript is disabled -->
<script type="text/javascript">
	document.write('<style>.noscript { display: none; }</style>');
</script>
</head>
<body>
<div id="page">
<div id="container">
<h1><a href="photo.html">我的相册</a></h1>
所有相册:<select id="AlbumList">
<#list albumSelect?keys as key>  
<option value ="${key}">${albumSelect[key]}</option>  
</#list>
</select>
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
<#list pictrues as pList>
	<li><a class="thumb" name="leaf"
		href="http://img.51archetype.com/${pList.serverPath!''}"
		title="${pList.filename!''}"> <img
		src="http:51archetype.comcc.net/${pList.serverPath!''}"
		alt="${pList.filename!''}" width="75" height="75"/> </a>
	<div class="caption">
	<div class="image-title">${pList.filename!''}</div>
	<div class="image-desc">${pList.filedesc!''}</div>
	</div>
	</li>
</#list>
	
</ul>
</div>
<div style="clear: both;"></div>
</div>
</div>
<div id="footer">&copy;  技术支持:<a h51archetype.com://51archetype.com">东方五金</a></div>
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
</body>
</html>
