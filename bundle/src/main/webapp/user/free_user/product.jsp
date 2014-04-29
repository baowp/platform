<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${ent.name}-供应产品</title>
<s:include value="include/page_style.jsp"></s:include>
</head>

<body>
<s:include value="include/head.jsp"></s:include>
<div id="nb"><s:include value="include/page_front.jsp" />
<div class="yu">
<div class="left">
<div class="left_top">产品分类</div>
<s:include value="include/product_category.jsp"></s:include></div>
<div class="you">
<div class="you_top">热门产品</div>
<s:include value="include/product_top.jsp"></s:include></div>
<div class="sou">
<div class="sou_top">产品搜索</div>
<s:include value="include/product_search.jsp"></s:include></div>
<div class="sou">
<div class="sou_top">推荐企业</div>
<s:include value="include/topEnt.jsp"></s:include></div>
<div class="sou">
<div class="sou_top">联系我们</div>
<s:include value="include/contact.jsp"></s:include></div>
</div>
<div class="hj">
<div class="contain">
<div class="contain_top">最新产品</div>
<div class="contain_right">
<ul>
	<s:iterator value="products.items">
		<li>
		<p class="hn"><a
			href="/site/${user.username}/product_detail?productId=${productId}"
			target="_blank"><img
			src="<s:property value="mainPic.picUrl(5)"/>" width="90" height="92"
			border="0" /></a></p>
		<p class="nh"><a
			href="/site/${user.username}/product_detail?productId=${productId}"
			target="_blank"><s:if test="name.length()<8">${name}</s:if><s:else>
			<s:property value="name.substring(0,8)" />
		</s:else></a></p>
		</li>

	</s:iterator>
</ul>
</div>
<div class="bottom"><s:include value="include/page.jsp" /></div>
</div>
<div class="advav"><img src="/user/free_user/images/ban3.jpg"
	width="706" height="70" /></div>
</div>

</div>
</div>
<s:include value="include/footer.jsp"></s:include>
</body>
</html>
