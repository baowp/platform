<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>关键字管理</title>
<link href="/user/css/css.css" rel="stylesheet" type="text/css" />
<link href="/user/css/textd.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/jquery.js"></script>
	<script type="text/javascript">
	function checkForm(){
		var keyArray=new Array()
		keyArray[0]=$("#source").val()
		checkKeys(keyArray);
		if(rkey!=null){
			alert("您输入的("+rkey+")是非法关键字!");
			return false;
		}
		return true;
			
	}
	</script>
</head>

<body>
<s:include value="/common/resultMessage.jsp"></s:include>
<div id="cpcontainer" class="container"><div class="top">
  <h3>关键字管理(seo优化,主要旺铺用户在网站静态化的时候会在页面里出现，便于搜索)</h3>
</div>
<font color="red">${errors.key}</font>
<s:form action="saveKey" namespace="/user/enterprise" onsubmit="return checkForm()">
<s:hidden name="id" value="%{enterpriseId}"></s:hidden>
  <table width="100%" border="0" cellpadding="0">
    <tr>
      <td width="48%" height="23" align="center" class="font_top">&nbsp;</td>
      <td width="52%">&nbsp;</td>
    </tr>
    
    <tr>
      <td align="left" valign="top" class="font02_t"><div id="gt">
	  <s:textarea dir="ltr" tabindex="0" wrap="soft" name="key" id="source" cssStyle="-moz-box-sizing: border-box; overflow-y: hidden; overflow-x: auto; height: 105px; padding-bottom: 32px;"/>

      </div></td>
      <td>&nbsp;</td>
    </tr>
    
    <tr>
      <td height="28" align="left" class="font03"><s:submit value="提交" title="提交"/></td>
      <td height="28">&nbsp;</td>
    </tr>
  </table>
  </s:form>
</div>
</body>
</html>
