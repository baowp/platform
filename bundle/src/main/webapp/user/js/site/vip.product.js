$(function() {
	$(".thumb li").mouseover(function() {
		$(".thumb li").removeClass("selected");
		var $node = $(this);
		$node.addClass("selected");
		var src = $node.find("img").attr("src");
		$("#booth").attr("src", src.replace("_6", "_4"));
		$(".zooming img").attr("src", src.replace(/_6(\.\w+)$/, "$1"));
		setTimeout(initScan,100);
	});
	$(".thumb li:first").mouseover();

	{
		gallery();
		$(".booth").mousemove(doScan);
		$(".booth").hover(function() {
			if (!isZoom())
				return;
			if ($zoomer.hasClass("hidden"))
				$zoomer.removeClass("hidden");
			$zooming.removeClass("hidden");
			$zoomIcon.addClass("hidden");
		}, function() {
			$zoomer.addClass("hidden");
			$zooming.addClass("hidden");
			if (!isZoom())
				return;
			$zoomIcon.removeClass("hidden");
		});
	}
});

var zoom = {
	srcX : null, // 原图大小
	srcY : null,
	bigX : null, // 预览窗大小
	bigY : null,
	boxX : null, // 展示区域宽
	boxY : null,
	smallX : null, // 缩略图宽度
	smallY : null,
	viewX : null,// 预览范围
	viewY : null,
	padX : null, // 展示区x补丁
	padY : null,
	scale : null, // 缩小比例
	prop : null, // bigX/srcX
	bl : null
// bigY/bigX
};

function gallery() {
	var $gallery = $(".gallery");
	$zoomer = $(".zoomer");
	$zooming = $(".zooming");
	$zoomingDiv = $(".zooming div");
	$zoomIcon = $("#zoomIcon");
	$zoomingImg = $(".zooming img");
	var $window = $(window);
	$window.resize(function() {
		$zooming.css("left", $gallery.position().left + $gallery.width() + 10);
	});
	$window.resize();

	if (msie6) {
		$(".zoomer").css("cursor", "default");
	}
}

function initScan() {
	if (!zoom.boxX) {
		var big = $(".zooming");
		var box = $(".booth");
		$.extend(zoom, {
			bigX : big.width(),
			bigY : big.height(),
			boxX : box.width(),
			boxY : box.height(),
			bl : big.height() / big.width()
		});
	}
	$zoomingImg = window.$zoomingImg || $(".zooming img");
	$zoomIcon = window.$zoomIcon || $("#zoomIcon");
	var width = $zoomingImg.width();
	var height = $zoomingImg.height();
	var sml = $("#booth");
	var wid = sml.width(), hei = sml.height();

	if (width > wid * 2) {
		$zoomIcon.show();
	} else {
		$zoomIcon.hide();
	}

	$.extend(zoom, {
		srcX : width,
		srcY : height,
		smallX : wid,
		smallY : hei,
		padX : (zoom.boxX - wid) / 2,
		padY : (zoom.boxY - hei) / 2,
		scale : sml.width() / width
	});
	var g = width >= height ? width : height;
	if (width >= height) {
		zoom.prop = zoom.bigX / g;
		zoom.viewX = zoom.prop * zoom.smallX;
		zoom.viewY = zoom.viewX * zoom.bl;
	} else if (width < height) {
		zoom.prop = zoom.bigY / g;
		zoom.viewY = zoom.prop * zoom.smallY;
		zoom.viewX = zoom.viewY / zoom.bl;
	}

	$zoomer = window.$zoomer || $(".zoomer");
	$zoomer.css( {
		width : zoom.viewX,
		height : zoom.viewY
	});
}

function doScan(e) {
	if (!isZoom()){
		$zoomer.addClass("hidden");
		return;
	}

	var offsetX = e.pageX - this.offsetLeft;
	var offsetY = e.pageY - this.offsetTop;
	var left = offsetX - zoom.viewX / 2;
	var top = offsetY - zoom.viewY / 2;
	if (left < 0)
		left = 0;
	if (top < 0)
		top = 0;
	if (left + zoom.viewX > this.offsetWidth)
		left = this.offsetWidth - zoom.viewX;
	if (top + zoom.viewY > this.offsetHeight)
		top = this.offsetHeight - zoom.viewY;

	$zoomer.css( {
		left : left,
		top : top
	});
	$zoomingDiv.css( {
		marginLeft : -(left - zoom.padX) / zoom.scale,
		marginTop : -(top - zoom.padY) / zoom.scale
	});
}

function isZoom() {
	return $zoomIcon.css("display") != "none";
}
