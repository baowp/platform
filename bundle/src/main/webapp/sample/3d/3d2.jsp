<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/css/jquery-ui.css" rel="stylesheet" type="text/css" />
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/jquery.js"></script>
<!--script type="text/javascript" src="/jqueryui/js/jquery-ui-1.7.2.custom.min.js"></script-->
<script type="text/javascript" src="/js/jquery-ui.min.js"></script>

<!-- InstanceBeginEditable name="documentready2" -->
<link rel="stylesheet" href="css/slimbox2.css" type="text/css"
	media="screen" />
<script type="text/JavaScript" src="jquery.mousewheel.js"></script>
<script type="text/JavaScript" src="slimbox2.js"></script>
<script type="text/JavaScript" src="cloud-carousel.1.0.3.min.js"></script>
<script type="text/javascript">
$(function(){
	$(".bodyCont").click(function(){
		$(this).after("<div class='after3d'></div>");
		$(".after3d").hide();
		$(this).find("img").each(function(i){
			$("<a id=\"a"+i+"\"/>").appendTo(".after3d");
			$(this).attr("class","cloudcarousel")
					.attr("width","128")
					.attr("height","164")
					.attr("alt",i)
					.attr("title","title"+i)
					.appendTo("#"+("a"+i));
			$("#"+("a"+i)).attr("href",$(this).attr("src")).attr("rel","lightbox").attr("title","123");
			
		})
		$("<div/>").appendTo(".after3d").attr("id","da-vinci-title");
		$("<div/>").appendTo(".after3d").attr("id","da-vinci-alt");
		$("<div/>").appendTo(".after3d").addClass("carouselLeft").attr("id","but1");
		$("<div/>").appendTo(".after3d").addClass("carouselRight").attr("id","but2");
		$(".after3d").attr("style","width: 570px; height: 384px; overflow: scroll;");
		$('#but1').attr("style","position: absolute; top: 20px; right: 64px;");
		$('#but2').attr("style","position: absolute; top: 20px; right: 20px;");
		
		$(".bodyCont").remove();
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

</script>
</head>
<body>
<div class="bodyCont moveChild glare_type_1" id="products">
<div class="clr"></div>
<div class="bodyContTitle"><span
	class="fl b titleLinkColor titleName" style="cursor: default;">
产品信息 </span>更多 &gt;&gt;</div>
<div class="bodyContContent rightConteWidth rel"><input
	type="hidden" class="{currentPage:1,pageCount:2,parentValue:''}"
	id="meta">
<div class="glitzBody">
<ul>
	<li class="glitzItem ">
	<div class="glitzPic glitzBorder glitzColor"><img border="0"
		src="http://img.cn.easthardware.net/upload/c/c1/caodixa/picture/2010/10/27/89c65393-7d27-4ff5-8e13-2be5008eb21d_5.jpg">
	<div class="imgBorder"></div>
	</div>
</li>
	<li class="glitzItem ">
	<div class="glitzPic glitzBorder glitzColor"><img border="0"
		src="http://img.cn.easthardware.net/upload/c/c1/caodixa/picture/2010/10/27/13ae31bf-2363-44d4-a3f1-aa031d0ae237_5.jpg">
	<div class="imgBorder"></div>
	</div>
</li>
	<li class="glitzItem ">
	<div class="glitzPic glitzBorder glitzColor"><img border="0"
		src="http://img.cn.easthardware.net/upload/c/c1/caodixa/picture/2010/10/27/c18bcf41-2a58-4589-b3e5-a2d293f57a1e_5.jpg">
	<div class="imgBorder"></div>
	</div>
	</li>
	<li class="glitzItem ">
	<div class="glitzPic glitzBorder glitzColor"><img border="0"
		src="http://img.cn.easthardware.net/upload/c/c1/caodixa/picture/2010/10/27/4322d903-34e5-433f-a665-b0eca020f690_5.jpg">
	<div class="imgBorder"></div>
	</div>
	</li>
	<li class="glitzItem ">
	<div class="glitzPic glitzBorder glitzColor"><img border="0"
		src="http://img.cn.easthardware.net/upload/c/c1/caodixa/picture/2010/10/27/abad420d-cd71-46a4-90b0-c6962e362db2_5.jpg">
	<div class="imgBorder"></div>
	</div>
	</li>
	<li class="glitzItem ">
	<div class="glitzPic glitzBorder glitzColor"><img border="0"
		src="http://img.cn.easthardware.net/upload/c/c1/caodixa/picture/2010/10/27/eaf899a3-4601-4afd-96aa-8fe924108484_5.jpg">
	<div class="imgBorder"></div>
	</div>
	</li>
	<li class="glitzItem ">
	<div class="glitzPic glitzBorder glitzColor"><img border="0"
		src="http://img.cn.easthardware.net/upload/c/c1/caodixa/picture/2010/11/01/0f6d5980-b37d-432d-beb5-b91a6b3a6e74_5.jpg">
	<div class="imgBorder"></div>
	</div>
	</li>
	<li class="glitzItem ">
	<div class="glitzPic glitzBorder glitzColor"> <img border="0"
		src="http://img.cn.easthardware.net/upload/c/c1/caodixa/picture/2010/10/27/4c972289-eaa2-4997-9d05-969ed9d8f77a_5.jpg">
	<div class="imgBorder"></div>
	</div>
	</li>
	<li class="glitzItem ">
	<div class="glitzPic glitzBorder glitzColor"><img border="0"
		src="http://img.cn.easthardware.net/upload/c/c1/caodixa/picture/2010/10/27/b70f71e8-4e9e-4e14-8e18-e5ccd8d073c9_5.jpg">
	<div class="imgBorder"></div>
	</div>
	</li>
	<li class="glitzItem ">
	<div class="glitzPic glitzBorder glitzColor"><img border="0"
		src="http://img.cn.easthardware.net/upload/c/c1/caodixa/picture/2010/10/27/f89c636b-83cd-47a1-b890-99601e20af9f_5.jpg">
	<div class="imgBorder"></div>
	</div>
	</li>
	<li class="glitzItem ">
	<div class="glitzPic glitzBorder glitzColor"><img border="0"
		src="http://img.cn.easthardware.net/upload/c/c1/caodixa/picture/2010/10/27/b0f32ba0-4404-45d5-969a-b989c909b6c2_5.jpg">
	<div class="imgBorder"></div>
	</div>
	</li>
	<li class="glitzItem ">
	<div class="glitzPic glitzBorder glitzColor"><img border="0"
		src="http://img.cn.easthardware.net/upload/c/c1/caodixa/picture/2010/10/27/e7b8fe84-4d3f-4de3-a374-149d958d317a_5.jpg">
	<div class="imgBorder"></div>
	</div>
	</li>
</ul>
</div>
</div>
</div>
</body>
</html>