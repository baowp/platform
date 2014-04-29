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