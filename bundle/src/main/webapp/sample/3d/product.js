/**
 * minScale(選填)
描述： 套用到最遠項目的縮放值
預設值： 0.5
 
reflHeight(選填)
描述： 倒影的高度，若設成 0 表示沒有倒影
預設值： 0
 
reflGap(選填)
描述： 圖片跟倒影的間距
預設值： 0
 
reflOpacity(選填)
描述： 倒影的透明度：0 表示完全透明，而 1 表示不透明
預設值： 0.5
 
xRadius(選填)
描述： 圓的寬度；預設會用容器的寬 / 2.3
預設值： 0
 
yRadius(選填)
描述： 圓的高度，透過修改此值可以改變傾斜度；預設會用容器的高 / 6
預設值： 0
 
xPos(選填)
描述： 圓心的 x 座標(水平)，通常會設成如容器寬度的一半
預設值： 0
 
yPos(選填)
描述： 圓心的 y 座標(垂直)，通常會設成如容器高度的一半
預設值： 0
 
buttonLeft(選填)
描述： 用來控制往左旋轉的元素，不一定要在容器裡面
預設值： null
 
buttonRight(選填)
描述： 用來控制往右旋轉的元素，不一定要在容器裡面
預設值： null
 
titleBox(選填)
描述： 用來顯示圖片 title 屬性用，不一定要在容器裡面(滑鼠移到圖片上時也會有作用)
預設值： null
 
altBox(選填)
描述： 用來顯示圖片 alt 屬性用，不一定要在容器裡面(滑鼠移到圖片上時也會有作用)
預設值： null
 
FPS(選填)
描述： 旋轉時的每秒速度，數字越高會旋轉越快。但如果客戶端電腦效能不好時，可能會造成反效果
預設值： 30
 
speed(選填)
描述： 圖片跟圖片項目間的移動速度，建議值應為 0.1 ~ 0.3 之間。(可允許的值應是大於 0 且小於 1)
預設值： 0.2
 
autoRotate(選填)
描述： 是否自動往左或是往右的旋轉；允許的值有 'no', 'left' 及 'right'
預設值： 'no'
 
autoRotateDelay(選填)
描述： 自動旋轉時的延遲時間，建議最設為 1000(1000毫秒表示1秒)
預設值： 1500
 
mouseWheel(選填)
描述： 如果設為 true 則支援滑鼠滾輪來切換上下張圖片。但需要引用 [link href="http://plugins.jquery.com/project/mousewheel" title="Mouse Wheel Extension" css="boldBlue"]。
預設值： false
 */
$(function(){
$(".glitzBody").find("a").each(function(){
	$(this).attr("target","");
	$(this).attr("to",$(this).attr("href"));
	$(this).attr("href","javascript:");
})
$("#lbImage").attr("style","cursor:pointer;");
$("#lbImage").click(function(){
	window.open($("#tId").attr("href"))
})
	$(".glitzBody").click(function(){
	
		$(this).after("<div class='after3d'></div>");
		$(".after3d").hide();
		$(this).find("img").each(function(i){
		var aObj = $(this).parent().parent().parent().find("div.txt a");
		var productName = aObj.html();
		var proHref = aObj.attr("to");
			$("<a id=\"a"+i+"\" />").appendTo(".after3d");
			$(this).attr("class","cloudcarousel")
					.attr("width","128")
					.attr("height","164")
					.attr("alt",i)
					.attr("title",productName)
					.appendTo("#"+("a"+i));
			$("#"+("a"+i)).attr("href",$(this).attr("src").replace(/_5/gi,"_3"))
						  .attr("rel","lightbox")
						  .attr("title","<a href=\"javascript:view()\" class=\"viewInfo\">查看</a>");
			
		})
		$("<div/>").appendTo(".after3d").attr("id","da-vinci-title");
		$("<div/>").appendTo(".after3d").attr("id","da-vinci-alt");
		$("<div/>").appendTo(".after3d").addClass("carouselLeft").attr("id","but1");
		$("<div/>").appendTo(".after3d").addClass("carouselRight").attr("id","but2");
		$(".after3d").attr("style","width: 570px; height: 384px; overflow: scroll;");
		$('#but1').attr("style","position: absolute; top: 20px; right: 64px;");
		$('#but2').attr("style","position: absolute; top: 20px; right: 20px;");
		
		var selectedEffect = "puff";
 			var options = {};
 			if(selectedEffect == 'scale'){ options = {percent: 0}; }
 			else if(selectedEffect == 'transfer'){ options = { to: "#button", className: 'ui-effects-transfer' }; }
 			else if(selectedEffect == 'size'){ options = { to: {width: 200,height: 60} }; }
 			$(".glitzBody").effect(selectedEffect,options,500,callback);
			function callback(){
				
				setTimeout(function(){
					$(".after3d").fadeIn();
				}, 1000);

			}
		 
		
		if (!/android|iphone|ipod|series60|symbian|windows ce|blackberry/i
				.test(navigator.userAgent)) {
				$("a[rel^='lightbox']").slimbox(
						{
							previousKeys:80,
							nextKeys: 39
						},
						null,
						function(el) {
							return (this == el)
									|| ((this.rel.length > 8) && (this.rel == el.rel));
						});
		}

		$(".after3d").CloudCarousel( { 
			reflHeight: 86,
			reflGap:2,
			titleBox: $('#da-vinci-title'),
			altBox: $('#da-vinci-alt'),
			buttonLeft: $('#but1'),
			buttonRight: $('#but2'),
			yRadius:40,
			xPos: 285,
			yPos: 120,
			speed:0.15,
			autoRotate:'right',
			autoRotateDelay:3000,
			mouseWheel:true
		});

	})
})
function view(){
					$("#lbImage").flip({
						direction: 'rl',
						color: '',
						content: '<table bgcolor="#996600"><tr><td>1</td></tr><tr><td>1</td></tr><tr><td>1</td></tr><tr><td>1</td></tr><tr><td>1</td></tr></table>',
						onBefore: function(){
						$(".viewInfo").attr("href","javascript:returnView()")
						$(".viewInfo").html("返回")

}
					})
					
}
function returnView(){
	$("#lbImage").revertFlip();
	$(".viewInfo").attr("href","javascript:view()")
	$(".viewInfo").html("查看")
}