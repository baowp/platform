<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<title>我的相册</title>
<link rel="stylesheet" href="/js/jquery/album/css/basic.css" type="text/css" />
<link rel="stylesheet" href="/js/jquery/album/css/galleriffic-2.css" type="text/css" />
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/jquery/album/js/jquery.galleriffic.js"></script>
<script type="text/javascript" src="/js/jquery/album/js/jquery.opacityrollover.js"></script>
<!-- We only want the thunbnails to display when javascript is disabled -->
<script type="text/javascript">
	document.write('<style>.noscript { display: none; }</style>');
	$(function(){
		$.ajax( {
			url : "/user/album/albumgetPicByJson",
			dataType:"json",
			async:false,  	//不进行异步操作
			data : {
			albumId:'${albumId}'
			},
			success : function(data) {
				var userlist = eval('(' +data+ ')');
				 var result="";
			$.each(userlist,function(i,n){ 
				var fName=n.filename;
				if(n.filename.length>5)
					fName=n.filename.substring(0,5)+"...";
				
		 		 result+="<li><a class=\"thumb\" name=\"leaf\" href=\""+n.serverPath+"\"  title=\""+n.filename+"\"> <img src=\""+n.serverPath+"\" alt=\""+n.filename+"\" width=\"75\" height=\"75\"/> </a>";      
				 result+="<div class=\"image-title\">"+fName+"</div><div class=\"image-desc\">"+n.filedesc+"</div></div></li>";
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
		$("#albumId").change(function(){
			url = "photo_detail?albumId="+$(this).val();
			location.href=url;
		})
	})

</script>
</head>
<body>
<div id="page">
<div id="container">
<h1><a href="photo">我的相册</a></h1>

所有相册:<s:select  list="#request.albumList" name="albumId"  listKey="key" listValue="value" value="albumId"  headerKey="0"></s:select>

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
<ul class="thumbs noscript"></ul>
</div>
<div style="clear: both;"></div>
</div>
</div>
<div id="footer">&copy; 技术支持:<a href="http://51archetype.com">东方五金</a></div>

</body>
</html>