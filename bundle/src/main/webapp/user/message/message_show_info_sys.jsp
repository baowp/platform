<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>系统消息</title>
<link href="/user/css/css.css" rel="stylesheet" type="text/css" />
<link href="/user/css/textd.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div id="cpcontainer" class="container"><div class="top">
  <h3>${type=='03'?'用户消息':'系统消息'}</h3>
</div>
  <table width="100%" border="0" cellpadding="0">
    <tr>
      <td width="7%" height="23" align="center" class="font_u">标 题：</td>
      <td colspan="3" align="left" class="font_u">${title}</td>
      <td width="48%">&nbsp;</td>
    </tr>
    
    <tr>
      <td colspan="4" align="left" valign="top" class="font02_t"><div id="gt">
	  <s:textarea readonly="true" dir="ltr" tabindex="0" wrap="soft" value="%{content}" id="source" style="-moz-box-sizing: border-box; overflow-y: hidden; overflow-x: auto; height: 105px; padding-bottom: 32px;"></s:textarea>
      </div></td>
      <td>&nbsp;</td>
    </tr>
    
    <tr>
      <td height="31" align="center" class="font03_t">&nbsp;发&nbsp;表&nbsp;人：</td>
      <td width="11%" height="31" align="left" class="font03_t"><s:property value="userName()" /></td>
      <td width="7%" align="center" class="font03_t">&nbsp;发表公司：</td>
      <td width="27%" align="left" class="font03_t"><s:property value="entName()" /></td>
      <td height="28" rowspan="2">&nbsp;</td>
    </tr>
    <tr>
      <td height="29" align="center" class="font03_t">&nbsp;发表时间：</td>
      <td height="29" colspan="3" align="left" class="font03_t"><s:date name="addTime" format="yyyy-MM-dd hh:mm:ss"/></td>
    </tr>
    <tr>
      <td height="29" colspan="4" align="left" class="font03">&nbsp;
      <input type="button" onclick="javascript:window.location.href='/user/message/messageentry';" value="返回" /></td><td height="28" rowspan="2"></td>
    </tr>
  </table>
</div>
</body>
</html>
