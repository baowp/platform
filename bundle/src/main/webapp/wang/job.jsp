<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>人才招聘_东方五金B2B电子商务平台</title>
<meta name="Keywords" content="电动工具,日用五金,园林工具五金,门窗,防盗用品,建筑五金,手动工具,汽摩配件" />
<meta name="Description" content="东方五金B2B电子商务平台_人才招聘频道，主要包括电动工具,机械制品,手动工具,通用零部件,五金工具,五金机械,五金模具等厂家发布的最新的人才招聘信息。" />
<s:include value="/home/style/index_style.jsp"></s:include>
<link href="/home/css/divr.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
<!--
function MM_displayStatusMsg (msgStr) { //v1.0
  window.status=msgStr;
  document.MM_returnValue = true;
}
//-->
</script>
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
<div class="nav_01"><a href="/">首页</a></div>
<div class="nav_02"><a href="business.jsp">商业机会</a></div>
<div class="nav_03"><a href="product.jsp">产品展厅</a></div>
<div class="nav_04"><a href="merchants.jsp">招商加盟</a></div>
<div class="nav_04">
<div class="tt">人才招聘</div>
</div>
<div class="nav_04"><a href="introduction.jsp">业务介绍</a></div>
<div class="nav_04"><a href="information.jsp">资讯频道</a></div>
<div class="nav_04"><a href="http://expo.easthardware.com/">五金展会</a></div>
</div>
<div id="contf03">
<div class="renc_left">
<div class="renc_left01">职位分类导航</div>
<s:action name="indexCategory" namespace="/index/job"
	executeResult="true"></s:action></div>
<div class="renc_right">
<div class="renc_right01">最新招聘信息</div>
<div class="renc_right02"><iframe name="jobMain"
	src="/index/job/indexJob" frameborder="0" width="100%"></iframe></div>
</div>
</div>
<s:include value="home/include/footer.jsp"></s:include>
</div>
</body>
</html>
