<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${ent.name}-动态</title>
<s:include value="include/page_style.jsp"></s:include>
</head>

<body>
<s:include value="include/head.jsp"></s:include>
<div id="nb">
  
  <s:include value="include/page_front.jsp"/>
  <div class="yu">
  <div class="left">
  <div class="left_top">产品分类</div>
    <s:include value="include/product_category.jsp"></s:include>
  </div>
  <div class="left">
  <div class="left_top">联系我们</div>
  <s:include value="include/contact.jsp"></s:include>
  </div>
  </div>
  <div class="hj">
    <div class="company02">
      <div class="company_top">公司动态</div>
      <div class="company_cotain">
      <ul>
      <s:iterator value="%{#request.commonNews.items}">
      <li><a href="news_detail?newsId=${newsId}">&#8226;${title}</a><span><s:date name="addTime" format="yy-MM-dd"/></span></li>
      </s:iterator>
      </ul>
      </div>
       <div  class="bottom"><s:include value="include/news_page.jsp"/></div>
    </div>
   
    <div class="advav"><img src="/user/free_user/images/ban3.jpg" width="706" height="70" /></div>
  </div>
</div>
<s:include value="include/footer.jsp"></s:include>
</body>
</html>
