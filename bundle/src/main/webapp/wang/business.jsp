<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商业机会_东方五金B2B电子商务平台</title>
<meta name="Keywords" content="电动工具,机械制品,日用五金,园林工具五金,门窗,防盗用品,建筑五金,手动工具,汽车配件,摩托车配件" />
<meta name="Description" content="东方五金B2B电子商务平台_商业机会频道，主要包括电动工具,机械制品,手动工具,通用零部件,五金工具,五金机械,五金模具,整机及服务器等信息。" />
<s:include value="/home/style/index_style.jsp"></s:include>
<script type="text/javascript" src="/home/js/business_xq.js"></script>
<script type="text/javascript" src="/home/js/common.js"></script>
<script language="javascript" type="text/javascript" src="js/flash.js"></script>
<script type="text/javascript">
<!--
function MM_displayStatusMsg(msgStr) { //v1.0
  window.status=msgStr;
  document.MM_returnValue = true;
}
//-->
</script>


<style>
#b_xq {
	height: 150px;
	min-height: 25px;
	line-height: 25px;
	overflow: hidden
}

#b_xq li {
	height: 25px;
	padding-left: 10px;
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
<div id="n"><s:include value="/home/include/top.jsp"></s:include>
<div id="nav">
<div class="nav_01">
<div><a href="/">首 页</a></div>
</div>
<div class="nav_02">
<div class="tt">商业机会</div>
</div>
<div class="nav_03"><a href="product.jsp">产品展厅</a></div>
<div class="nav_04"><a href="merchants.jsp">招商加盟</a></div>
<div class="nav_04"><a href="job.jsp">人才招聘</a></div>
<div class="nav_04"><a href="introduction.jsp">业务介绍</a></div>
<div class="nav_04"><a href="information.jsp">资讯频道</a></div>
<div class="nav_04"><a href="http://expo.easthardware.com/">五金展会</a></div>
</div>
<div id="contentss">
<div id="left">
<div class="leftt01">
<script language="javascript" type="text/javascript">
writeflashhtml("_swf=home/flash/xixi.swf", "_width=675", "_height=250" ,"_wmode=transparent");
</script>
</div>
<div class="productm0">
<div class="product_nav">
<div class="product_nav01">最新商机</div>
</div>
<div class="product_content02">
<div class="content_ttt">
<div class="ggs">
<div class="gg_02" id="newGy">最新供应</div>
<div class="gg_01" id="newHz">最新合作</div>
<div class="gg_01" id="newDl">最新代理</div>
</div>
<div class="gg">最新求购</div>
</div>
<div class="gig">
<div class="tt01" id="supply"><s:action name="listSupply"
	namespace="/index/supply" executeResult="true"></s:action></div>
<div class="tt02" id="b_xq">

<s:action name="listBU"
	namespace="/index/supply" executeResult="true"><s:param name="pageType" value="'b_xq'"></s:param> </s:action>

</div>
<div class="tt03">
<div class="tt03_v">
<div class="tt03_g"></div>
<div class="tt04_g">商机图文</div>
<div class="tt05_g"></div>
</div>
<s:action name="sjtw" namespace="/index" executeResult="true"></s:action>

</div>
</div>
</div>
</div>
<div class="productf">
<div class="product_nav">
<div class="product_nav01">商业机会</div>

</div>
<s:action name="bShow" namespace="/index/syscode" executeResult="true"></s:action>
</div>
<div class="gw">
<div class="g_to">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="58%" align="left">推荐产品</td>
		<td width="28%">&nbsp;</td>
		<td width="14%" align="center" valign="middle"><a href="#"><img
			src="/home/images/more.jpg" width="32" height="8" border="0" /></a></td>
	</tr>
</table>
</div>
<s:action name="indexProduct" namespace="/index/product"
	executeResult="true">
	<s:param name="pageType" value="'b'" />
</s:action></div>
</div>
<div class="right">
<div class="right_login">
<div class="right_login01">
<div class="t" onclick="javascript:window.open('/user/supply/add')">发布供求信息</div>
<div class="tk">信息指南</div>
</div>
<div class="th02">
<embed src="/home/flash/wangpu.swf"
	width="255" height="120" wmode="transparent" menu="true"/>
</div>
</div>
<div class="phf">
<ul>
	<li>&middot;注册3分钟，轻松入门做生意</li>
	<li>&middot;发布产品供求信息，让生意自动找上门生意</li>
	<li>&middot;看商情、交商友，时刻掌握行业最新资讯</li>
</ul>
</div>
<div class="company">
<div class="compay_top">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="58%" align="left">企业推荐</td>
		<td width="23%">&nbsp;</td>
		<td width="19%" align="center" valign="middle"><a
			href="/index/more"><img src="/home/images/more.jpg" width="32"
			height="8" border="0" /></a></td>
	</tr>
</table>
</div>
<s:action name="tjwjqy" namespace="/index" executeResult="true"></s:action>
</div>
<div class="tu0x">
        <div class="tu_f"><a href="http://bendaotools.cn.easthardware.net/product-detail-717.html" target="_blank"><img src="/home/images/3fb5f0d5-920a-41fb-b10c-469a82035397.gif" width="264" height="60" border="0" /></a></div>
        <div class="tu_f"><a href="http://hengpeng.cn.easthardware.net/index.html" target="_blank"><img src="/home/images/7f0e7bfe-6610-4598-ae83-8b0bcee39fa1.gif" width="264" height="60" border="0" /></a></div>
        <div class="tu_f"><a href="http://zswj.cn.easthardware.net/index.html" target="_blank"><img src="/home/images/7289d77d-049f-4ab7-bf0c-fcb20d7266c8.gif" width="264" height="60" border="0" /></a></div>
        <div class="tu_c"><a href="http://ykhy.cn.easthardware.net/index.html" target="_blank"><img src="/home/images/bdcc04d4-0e4c-43e2-8e4c-3832b171e4c7.gif" width="264" height="60" border="0" /></a></div>
        </div>
<div class="bff">
<div class="b_nava">商业资讯</div>
<div class="b_c">
<div class="compay_centersc"><s:action name="syzx"
	namespace="/index" executeResult="true">
	<s:param name="genusSign" value="'syzx'"></s:param>
</s:action></div>
</div>
</div>
<div class="bffv">
<div class="b_nava">行业动态</div>
<div class="b_c02">
              <ul>
              <li>&middot;<a href="/html/2010101_a.html">发展低碳经济 五金企业任重道远</a></li>
              <li>&middot;<a href="/html/2010101_b.html">江门中小五金企业优劣势分析 抱团发道远</a></li>
              <li>&middot;<a href="/html/2010101_c.html">五金行业“贺岁档”：看你爱好哪一</a></li>
              <li>&middot;<a href="/html/2010101_d.html">卫浴五金受房产波及较大 空间利润陡</a></li>
              <li>&middot;<a href="/html/2010101_e.html">卫浴五金市场2010下半年发展十点个发</a></li>
              <li>&middot;<a href="/html/2010101_f.html">2010中国五金工具中高端市场划分分</a></li>
              <li>&middot;<a href="/html/2010101_j.html">简析电子商务助五金企业提高效率六</a></li>
              <li>&middot;<a href="/html/2010101_k.html">五金代理商生存之道 如何突破渠道管</a></li>
              <li>&middot;<a href="/html/2010101_l.html">品牌战略成首选 一周五金行业热点资讯盘点</a></li>
             </ul>
  </div>
</div>
</div>
</div>
<s:include value="home/include/footer.jsp"></s:include>
</div>
</body>
</html>
