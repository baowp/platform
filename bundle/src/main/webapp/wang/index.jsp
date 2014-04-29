<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>东方五金B2B电子商务平台</title>
<meta name="Keywords" content="五金,五金报价,五金工具,日用五金,刀具,锁具,轴承,门窗五金,水暖五金,模具,紧固件,弹簧" />
<meta name="Description" content="东方五金B2B电子商务平台是专业提供五金市场前沿资讯，交易信息，五金企业动态，五金知识，五金展会，轴承、刀具等五金工具，建筑五金，日用五金，五金原材料资讯，产品专题等商务信息服务的网站，是中国最专业的五金行业门户网站" />
<s:include value="/home/style/index_style.jsp"></s:include>
<script>
function close1(){
	$("#box1").hide();
}
function close2(){
	$("#box2").hide();
}
</script>
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
<style>
.jc {
	position: absolute;
	left: 80px;
	top: 176px;
	color: #333333;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
}

.jc a:link {
	color: #333333;
	text-decoration: none;
}

.jc a:visited {
	color: #333333;
	text-decoration: none;
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

<div class="box" id="box1" style="top: 0; left: 0;"><a
	href="/wang/index.html" target="_blank"><img
	src="/home/images/index_gg.jpg" border="0"></a>
<div align="right" class="jc"><a href="javascript:" id="box1Close"
	onclick="close1()">关闭</a></div>
</div>
<div class="box" id="box2" style="top: 0; right: 0;"><a
	href="/wang/index.html" target="_blank"><img
	src="/home/images/001.jpg" border="0"></a>
<div align="right" class="jc"><a href="javascript:" id="box2Close"
	onclick="close2()">关闭</a></div>
</div>
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
			width="32" height="8" border="0" title="更多" alt="更多"/></td>
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
<div class="yq"><embed src="/home/flash/jincheng.swf"
	width="675" height="70" wmode="transparent" menu="true" /></div>
<div class="product">
<div class="product_nav">
<div class="product_nav01">产品分类</div>
<div class="product_nav02"></div>
</div>
<s:action name="show" namespace="/index/syscode" executeResult="true"></s:action>
</div>
<div class="advertising"><embed src="/home/flash/jh_index.swf"
	width="673" height="80" wmode="transparent" menu="true" /></div>
<s:action name="indexProduct" namespace="/index/product"
	executeResult="true"></s:action>
<div class="tup"><embed src="/home/flash/201010291953116249.swf"
	width="673" height="87" wmode="transparent" menu="true"></embed></div>
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
<div class="ph"><a href="http://zjykyt.com/" target="blank"><img src="/home/images/jiankang.jpg" alt="" border="0"  width="276" height="75"/> </a></div>
<div class="yq"><embed src="/home/flash/Navigation.swf"
	wmode="transparent" menu="true" width="275" height="75" /></div>
<div class="succed">
		  <div class="suceed_01">
		    <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="58%" align="left">品牌企业</td>
                <td width="23%">&nbsp;</td>
                <td width="19%" align="center" valign="middle"><a href="#"><img src="/home/images/more.jpg" width="32" height="8" border="0" /></a></td></tr>
            </table>
		  </div>
          <table width="100%" border="0" cellpadding="0">
            <tr>
              <td height="8" colspan="3" align="center"></td>
            </tr>
            <tr>
              <td height="60" align="center"><a href="http://huaying.cn.easthardware.net/" target="_blank"><img src="/home/images/wd.jpg" width="80" height="60" border="0" /></a></td>
              <td align="center"><a href="http://cnshijing.cn.easthardware.net/" target="_blank"><img src="/home/images/shijing.jpg" width="80" height="60" border="0" /></a></td>
              <td align="center"><a href="http://zjzhonghuan.cn.easthardware.net/" target="_blank"><img src="/home/images/zhonghuan.jpg" width="80" height="60" border="0" /></a></td>
            </tr>
            <tr>
              <td height="25" align="center"><a href="http://huaying.cn.easthardware.net/" target="_blank">武义华鹰厨具</a></td>
              <td align="center"><a href="http://cnshijing.cn.easthardware.net/" target="_blank">永康师敬校具</a></td>
              <td align="center"><a href="http://zjzhonghuan.cn.easthardware.net/" target="_blank">中环机械制造</a></td>
            </tr>
            <tr>
              <td height="60" align="center"><a href="http://meishengya.cn.easthardware.net/" target="_blank"><img src="/home/images/mei.jpg" width="80" height="60" border="0" /></a></td>
              <td align="center"><a href="http://shirong.cn.easthardware.net/company.html" target="_blank"><img src="/home/images/shirong.jpg" width="80" height="60" border="0" /></a></td>
              <td align="center"><a href="http://libiaohardware.cn.easthardware.net/" target="_blank"><img src="/home/images/libiao.jpg" width="80" height="60" border="0" /></a></td>
            </tr>
            <tr>
              <td height="23" align="center"><a href="http://meishengya.cn.easthardware.net/" target="_blank">美盛雅装饰</a></td>
              <td align="center"><a href="http://shirong.cn.easthardware.net/company.html" target="_blank">世荣家居用品</a></td>
              <td align="center"><a href="http://libiaohardware.cn.easthardware.net/" target="_blank">永康立标工具</a></td>
            </tr>
          </table>
          <div class="suceed_02"></div>
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
	height="40" wmode="transparent" menu="true" /></div>
<div class="tu01"><embed src="/home/flash/index_8.swf" width="260"
	height="40" wmode="transparent" menu="true" /></div>
<div class="tu01"><embed src="/home/flash/index_zhaoshang.swf"
	width="260" height="40" wmode="transparent" menu="true" /></div>
<div class="tu01"><embed src="/home/flash/index_zya.swf"
	width="260" height="40" wmode="transparent" menu="true" /></div>
</div>
<div class="advertising"><embed src="/home/flash/qihao.swf"
	width="275" height="80" wmode="transparent" menu="true" /></div>
<s:action name="newNews" namespace="/index" executeResult="true"></s:action>
<div class="di"><img src="/home/images/huanying.gif" width="276"
	height="87" /></div>
</div>
</div>
<s:include value="home/include/footer.jsp"></s:include>
</div>
<!-- 浮动广告 -->
<script src="home/js/index_buttom.js"></script>
</body>
</html>
