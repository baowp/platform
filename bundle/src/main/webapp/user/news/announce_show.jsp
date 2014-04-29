<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>公告说明</title>
<link href="/user/css/css.css" rel="stylesheet" type="text/css" />
<link href="/user/css/textd.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div id="cpcontainer" class="container"><div class="top">
  <h3>公告说明</h3>
</div>

  <table width="100%" border="0" cellpadding="0">
    <tr>
      <td width="92%" height="23" align="center" class="font_top">${title}</td>
      <td width="8%" rowspan="2">&nbsp;</td>
    </tr>
    <tr>
      <td height="20" align="center"><h1><span class="font03_t"><s:date name="addTime" format="yyyy-MM-dd hh:mm:ss"/></span></h1></td>
    </tr>
    <tr>
      <td height="811" align="left" valign="top" class="font02_t">&nbsp;&nbsp;&nbsp;&nbsp;${content}</td>
      <td>&nbsp;</td>
    </tr>

  </table>
</div>
</body>
</html>
