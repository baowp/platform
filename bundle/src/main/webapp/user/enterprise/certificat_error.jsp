<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Error page</title>
  </head>
  <body>
   错误：<h1><s:property value="message" /><a href="enterprise/certificate_add.jsp">点击上传证书</a></h1>
  </body>
</html>