<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>东方五金B2B电子商务平台</title>
<s:include value="/home/style/index_style.jsp"></s:include>
<style>
ul,li {
	margin: 0;
	padding: 0
}
</style>
<style type="text/css">
* {
	padding: 0;
	margin: 0;
}

html {
	height: 100%;
}

body {
	height: 200%;
}

.box {
	position: absolute;
}
</style>
</head>
<!--[if lte IE 6]>
<script src="/home/js/DD_belatedPNG_0.0.8a.js" type="text/javascript"></script>
    <script type="text/javascript">
        DD_belatedPNG.fix('div, ul, img, li, input , a');
    </script>
<![endif]-->
<body
	onmouseover="MM_displayStatusMsg('欢迎来到东方五金！');return document.MM_returnValue">
<div class="box" id="box1" style="bottom: 0; left: 0;"><img
	height="63" width="171" src="/home/images/logo.png"></div>
<div class="box" id="box2" style="top: 0; right: 0;"><img
	height="63" width="171" src="/home/images/logo.png"></div>
<div id="n"><s:include value="/home/include/top.jsp"></s:include>
<s:include value="/home/include/top_bar.jsp"></s:include>
<div id="content">
<div id="left">
<div class="ta">
<div class="leftt">
<div class="left_top">
<div class="left_top01">经典旺铺</div>
<div class="left_top02" id="scrollDiv"></div>
<div class="left_top04"></div>
</div>
<div class="left_to">
<div class="left_top0">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="58%" align="left">展会信息</td>
		<td width="15%">&nbsp;</td>
		<td width="27%" align="center" valign="middle"><a
			href="/index/more?newsType=zhzx"><img src="/home/images/more.jpg"
			width="32" height="8" border="0" title="更多" /></td>
	</tr>
</table>
</div>
<div class="left_topz">
<div class="left_topz" id="zhxxList"></div>
</div>
</div>
</div>
<div class="left_c">
<div class="center"><s:include value="/home/include/divPic.jsp"></s:include>
</div>
<div class="shal"="jk">
<div class="jk">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="58%" align="left">供求信息</td>
		<td width="23%">&nbsp;</td>
		<td width="19%" align="center" valign="middle"><a href="#"><img
			src="/home/images/more.jpg" width="32" height="8" border="0" /></a></td>
	</tr>
</table>
</div>
<div class="hk"><s:include value="/home/action/supply.jsp"></s:include>
</div>
</div>
</div>
</div>
<div class="yq"><embed src="/home/flash/201010291953116249.swf"
	width="673" height="70" /></div>
<div class="product">
<div class="product_nav">
<div class="product_nav01">产品分类</div>
<div class="product_nav02"></div>
</div>
<s:action name="show" namespace="/index/syscode" executeResult="true"></s:action>
</div>
<div class="advertising"><embed src="/home/flash/jh_index.swf"
	width="673" height="80" /></div>
<s:action name="indexProduct" namespace="/index/product"
	executeResult="true"></s:action>
<div class="tup"><a href="http://www.ykit.net/top100.html"
	target="_blank"><img src="/home/images/hg.gif" width="673"
	height="87" border="0" /></a></div>
</div>
<div class="right">
<div class="right_login">
<div class="right_login01"><a href="/user/reg.jsp">
<div class="t">会员注册</div>
</a> <a href="/user/login.jsp">
<div class="tk">会员登陆</div>
</a></div>
<div class="th">一站式电子商务解决方案</div>
<div class="tr">
<table width="100%" height="98%" border="0" cellpadding="0">
	<tr>
		<td width="11%" align="center"><img
			src="/home/images/right_tu01.jpg" width="12" height="14" /></td>
		<td width="89%" align="left" class="h3">每天时刻自动为您找客户，实时通知您</td>
	</tr>
	<tr>
		<td align="center"><img src="/home/images/right_tu02.jpg"
			width="17" height="15" /></td>
		<td align="left" class="h3">权威第三方认证，提高企业诚信度</td>
	</tr>
	<tr>
		<td align="center"><img src="/home/images/right_tu03.jpg"
			width="17" height="9" /></td>
		<td align="left" class="h3">精美网站模版，为您展示全新的企业形象</td>
	</tr>
	<tr>
		<td align="center"><img src="/home/images/right_tu04.jpg"
			width="13" height="14" /></td>
		<td align="left" class="h3">独立的网站域名，让您立即拥有企业网站</td>
	</tr>
</table>
</div>
</div>
<div class="ph"><embed src="/home/flash/innerBanner.swf"
	width="276" height="75" /></div>
<div class="yq"><embed src="/home/flash/Navigation.swf"
	width="276" height="70" /></div>
<div class="succed">
<div class="suceed_01">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="58%" align="left">成功案例</td>
		<td width="23%">&nbsp;</td>
		<td width="19%" align="center" valign="middle"><a href="#"><img
			src="/home/images/more.jpg" width="32" height="8" border="0" /></a></td>
	</tr>
</table>
</div>

<div class="suceed_02">
<div class="suce_f"><img src="/home/images/tu02.jpg" width="74"
	height="56" /></div>
<div class="suce_g">
<ul>
	<li><a href="/html/20101020020143temp_x.html">永嘉县三精阀门有限公司</a></li>
</ul>
<a href="/html/20101020020143temp_x.html">讲述人：李延红（销售主管）<br />
理由:选择东方五金 更显实力</a></a><br />
</div>
</div>
<div class="suceed_03">
<div class="suce_f"><img src="/home/images/tu03.jpg" width="63"
	height="56" /></div>
<div class="suce_g">
<ul>
	<li><a href="/html/20101020020143temp_z.html">杭州宏恩光电有限公司副经理<br />
	</a></li>
</ul>
<a href="/html/20101020020143temp_z.html">讲述人：林恩军（副经理） <br />
理由:带上责任去创业</a><br />
</div>
</div>
</div>
<div class="company">
<div class="compay_top">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="58%" align="left">推荐五金企业</td>
		<td width="23%">&nbsp;</td>
		<td width="19%" align="center" valign="middle"><a
			href="/index/more?newsType=tjwjqy"><img
			src="/home/images/more.jpg" width="32" height="8" border="0" /></a></td>
	</tr>
</table>
</div>
<s:action name="tjwjqy" namespace="/index" executeResult="true">
	<s:param name="genusSign">tjwjqy</s:param>
</s:action></div>
<div class="tu">
<div class="tu01"><embed src="/home/flash/index_2.swf" width="260"
	height="40" /></div>
<div class="tu01"><embed src="/home/flash/index_8.swf" width="260"
	height="40" /></div>
<div class="tu01"><embed src="/home/flash/index_zhaoshang.swf"
	width="260" height="40" /></div>
<div class="tu01"><embed src="/home/flash/index_zya.swf"
	width="260" height="40" /></div>
</div>
<div class="advertising"><embed src="/home/flash/qihao.swf"
	width="260" height="80" /></div>
<s:action name="newNews" namespace="/index" executeResult="true"></s:action>
<div class="di"><img src="/home/images/huanying.gif" width="276"
	height="87" /></div>
</div>
</div>
<div class="medio">
<div class="mel"><span class="font04">站点导航：</span>关于我们--网站指南--欢迎合作--服务条款--友情链接
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="font04">联合站点：</span>东方机电网--东方工具网--东方就业网--东方五金传媒</div>
</div>
<div class="copyright">
<div class="bottom_top">运营服务:0579-87171989 83840778 88026855  
技术支持:0579-83840669 传真：0579-87173500<br />
东方五金传媒版权所有 2000-2006 E-mail: <a href="mailto:dfwj@ykit.net">dfwj@ykit.net</a>  
<a href="#" target="_blank">互联网违法和不良信息举报</a><br />
<a href="http://www.miibeian.gov.cn" target="_blank">经营许可证编号:浙B2-20050229</a>
 网络实名：东方五金传媒，东方五金 <br />
</div>
<div class="bottom_pho"><img src="/home/images/i_lo2.gif"
	width="65" height="70" /> &nbsp;<img src="/home/images/fa.jpg"
	width="56" height="62" /></div>
</div>
</div>
</body>
<script type="text/javascript"> 
var id=function(o){return document.getElementById(o)}
var scroll=function (o){
	var space=0;
	if(o=='box2')
		space=25;
	space=space+id(o).offsetTop;
	id(o).style.top=space+'px';
	void function(){
			var goTo = 0;
			var roll=setInterval(function(){
				var height =document.documentElement.scrollTop+document.body.scrollTop+space;
				var top = parseInt(id(o).style.top);
				if(height!= top){
					goTo = height-parseInt((height - top)*0.9);
					id(o).style.top=goTo+'px';
				}
				//else{if(roll) clearInterval(roll);}
			},50);
	}()
}
scroll('box1');
scroll('box2');
</script>
</html>
