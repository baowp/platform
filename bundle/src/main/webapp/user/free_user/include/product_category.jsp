<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="left_contain">
  <ul>
  <s:iterator value="categoryList">
  <li><span><img src="/user/free_user/images/g-list.gif" width="17" height="15" /></span><a href="/site/${username}/product?categoryId=${categoryId}"><s:if test="name.length()>11"><s:property value="name.substring(0,11)"/></s:if><s:else> ${name}</s:else></a></li>
  </s:iterator>
  </ul>
  </div>