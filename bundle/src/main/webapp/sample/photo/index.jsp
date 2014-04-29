<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<title>Galleriffic | Thumbnail rollover effects and slideshow
crossfades</title>
<link rel="stylesheet" href="css/basic.css" type="text/css" />
<link rel="stylesheet" href="css/galleriffic-2.css" type="text/css" />
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.galleriffic.js"></script>

<script type="text/javascript" src="js/jquery.opacityrollover.js"></script>
<!-- We only want the thunbnails to display when javascript is disabled -->
<script type="text/javascript">
	document.write('<style>.noscript { display: none; }</style>');
</script>
</head>
<body>
<div id="page">
<div id="container">
<h1><a href="index.html">Galleriffic</a></h1>

<h2>Thumbnail rollover effects and slideshow crossfades</h2>

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
	<li><a class="thumb" name="leaf"
		href="http://image.zcool.com.cn/cover/20/37/1260160601001.jpg"
		title="Title #0"> <img
		src="http://image.zcool.com.cn/cover/20/37/1260160601001.jpg"
		alt="Title #0" width="75" height="75"/> </a>
	<div class="caption">

	<div class="download"><a
		href="http://farm4.static.flickr.com/3261/2538183196_8baf9a8015_b.jpg">Download
	Original</a></div>
	<div class="image-title">Title #0</div>
	<div class="image-desc">Description</div>
	</div>
	</li>

	
</ul>
</div>
<div style="clear: both;"></div>
</div>
</div>
<div id="footer">&copy; 2009 Trent Foley</div>

<script type="text/javascript">
$(function(){
	$.ajax( {
		url : "/user/album/albumgetPicByJson",
		dataType:"json",
		async:false,  	//不进行异步操作
		data : {
		albumId:'Album_00000000000000000000000034'
		},
		success : function(data) {
			var userlist = eval('(' +data+ ')');
			 var result="";
		$.each(userlist,function(i,n){  
	 		 result+="<li><a class=\"thumb\" name=\"leaf\" href=\""+n.serverPath+"\"  title=\""+n.filename+"\"> <img src=\""+n.serverPath+"\" alt=\""+n.filename+"\" width=\"75\" height=\"75\"/> </a>";      
			 result+="<div class=\"caption\"><div class=\"download\"><a href=\"http://farm4.static.flickr.com/3261/2538183196_8baf9a8015_b.jpg\">Download Original</a></div>";  
			 result+="<div class=\"image-title\">"+n.filename+"</div><div class=\"image-desc\">"+n.filedesc+"</div></div></li>";
     });   
      		$(".thumbs").html(result);

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
						nextPageLinkText : '上一页 &rsaquo;',
						prevPageLinkText : '&lsaquo; 下一页',
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
		}
	})
})

</script>
</body>
</html>