$(function(){
$(".glitzBody").find("a").each(function(){
	$(this).attr("target","");
	$(this).attr("to",$(this).attr("href"));
	$(this).attr("href","javascript:");
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
						  .attr("title","<a href=\""+proHref+"\" target=\"blank\">查看</a>");
			
		})
		$("<div/>").appendTo(".after3d").attr("id","da-vinci-title");
		$("<div/>").appendTo(".after3d").attr("id","da-vinci-alt");
		$("<div/>").appendTo(".after3d").addClass("carouselLeft").attr("id","but1");
		$("<div/>").appendTo(".after3d").addClass("carouselRight").attr("id","but2");
		$(".after3d").attr("style","width: 570px; height: 384px; overflow: scroll;");
		$('#but1').attr("style","position: absolute; top: 20px; right: 64px;");
		$('#but2').attr("style","position: absolute; top: 20px; right: 20px;");
		
		$(".glitzBody").remove();
		$(".after3d").show("slow"); 
		
		if (!/android|iphone|ipod|series60|symbian|windows ce|blackberry/i
				.test(navigator.userAgent)) {
				$("a[rel^='lightbox']").slimbox(
						{/* Put custom options here */},
						null,
						function(el) {
							return (this == el)
									|| ((this.rel.length > 8) && (this.rel == el.rel));
						});
		}
		$(".after3d").CloudCarousel( { 
			reflHeight: 56,
			reflGap:2,
			titleBox: $('#da-vinci-title'),
			altBox: $('#da-vinci-alt'),
			buttonLeft: $('#but1'),
			buttonRight: $('#but2'),
			yRadius:40,
			xPos: 285,
			yPos: 120,
			speed:0.15,
			mouseWheel:true
		});
	})
})