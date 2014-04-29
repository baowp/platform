<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="flash">${ent.name}</div>
  <div class="nav">
  <ul>
  <li class="${empty result?'nav_left':'nav_right'}"><a href="<s:url value="%{'/site/'+username+''}"/>">网站首页</a></li>
  <li class="${result eq 'product'?'nav_left':'nav_right' }"><a href="<s:url value="%{'/site/'+username+'/product'}"/>">供应产品</a></li>
  <li class="${result eq 'enterprise'?'nav_left':'nav_right' }"><a href="<s:url value="%{'/site/'+username+'/enterprise'}"/>">公司简介</a></li>
  <li class="${result eq 'news'?'nav_left':'nav_right' }"><a href="<s:url value="%{'/site/'+username+'/news'}"/>">公司动态</a></li>
  <li class="${result eq 'recruit'?'nav_left':'nav_right' }"><a href="<s:url value="%{'/site/'+username+'/recruit'}"/>">招聘信息</a></li>
  <li class="${result eq 'cert'?'nav_left':'nav_right' }"><a href="<s:url value="%{'/site/'+username+'/cert'}"/>">资质证书</a></li>
  <li class="${result eq 'contact'?'nav_left':'nav_right' }"><a href="<s:url value="%{'/site/'+username+'/contact'}"/>">联系我们</a></li>
  </ul>
  </div>