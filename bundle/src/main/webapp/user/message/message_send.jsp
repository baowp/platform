<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>发送信息</title>
<link href="css/css.css" rel="stylesheet" type="text/css" />
<link href="css/textd.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<s:url value="/admin/css/common.css"/>"
	type="text/css" />
</head>

<body>
<s:form namespace="/user/message" action="messagesend" method="post">
	<s:hidden id="userId"></s:hidden>
  <table width="100%" height="363" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td colspan="2" align="left" class="font01"><div class="fv">
          <input type="submit" name="button" id="button" value="发送" />
      </div></td>
    </tr>
    <tr>
      <td colspan="2" align="left" bgcolor="#F5FBFE" class="font01">
      <table>
      <tr><td>收 信 人：</td><td><s:textfield name="username"  id="textfield" size="30" /><span class="errorSpan">${errors.username[0]==null?'请输入对方用户名':errors.username[0]}</span></td></tr>
      <tr><td>留言主题：</td><td><s:textfield name="title" id="textfield" size="30" /><span class="errorSpan">${errors.title[0]}</span></td></tr>
      <tr><td>留言内容：</td><td><s:textarea name="content" dir="ltr" tabindex="0" wrap="soft" id="source" cssStyle="-moz-box-sizing: border-box; overflow-y: hidden; overflow-x: auto; padding-bottom: 32px;"/></td></tr>
      </table>
      </td>
    </tr>  

  </table>
  </s:form>
</div>
</body>
</html>
