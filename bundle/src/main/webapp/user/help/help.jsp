<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>帮助中心</title>
<link href="style/textd.css" rel="stylesheet" type="text/css" />
<script>

function ShowFLT(obj) {
 var  lbmc = document.getElementById(obj);
 lbmc.style.display == 'none'?lbmc.style.display="":lbmc.style.display='none';
}
</script>
</head>

<body>
<div id="cpcontainer" class="container">
  <div class="fva">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="7" background="images/left.jpg"></td>
        <td width="947" align="left" background="images/cernter.jpg">帮助中心</td>
        <td width="7" background="images/right.jpg"></td>
      </tr>
    </table>
  </div>
  <div class="gb">
  <ul>
  <s:iterator value="%{#request.helpList}" status="st">
  <li class="hv" style="cursor:hand;" onClick=javascript:ShowFLT(<s:property value="#st.index+1"/>)><s:property value="#st.index+1"/>.${title}</li>
  <li class="fb" id=<s:property value="#st.index+1"/> style="DISPLAY: none">${content}</li>
</s:iterator>
  </ul>
  </div>
</div>

</body>
</html>
