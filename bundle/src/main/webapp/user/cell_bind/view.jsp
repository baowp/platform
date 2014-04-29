<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>手机绑定</title>
<link rel="stylesheet" type="text/css"
	href="<s:url value='/user/css/validation.css'/>">
<link href="../css/div.css" rel="stylesheet" type="text/css" />
<link href="../css/css.css" rel="stylesheet" type="text/css" />
<link href="/user/css/operation.css" rel="stylesheet" type="text/css" />
<!--[if lte IE 6]>
<script src="/js/DD_belatedPNG_0.0.8a.js" type="text/javascript"></script>
    <script type="text/javascript">
        DD_belatedPNG.fix('div, ul, img, li, input , a');
    </script>
<![endif]-->
</head>

<body>
<div id="body">
  <div id="content">
    <div class="content_left"></div>
    <div class="content_center">
      <div class="content_center01"><h3>手机验证</h3></div>
      <div class="content_center02">
	  <s:form namespace="/user/cellbind" action="bind">
	  <ul>
	  <li class="font_c">请输入您要验证/绑定的手机号码</li>
	  <li class="font_c">
	  <s:checkbox name="check"></s:checkbox>
	    我同意并遵守《ABBCC手机验证/绑定规则》</li>
	  <li class="font_c"><table><tr><td>手机号码<span class="font03">*</span>
	    <s:textfield name="cellphone" cssClass="txt"/><span class="errorSpan">${errors.cellphone[0]}</span></td><td> 
	    <s:submit value="提交"  title="提交"/></td></tr></table>
	  </li>
	  </ul>
	  </s:form>
	  </div>
    </div>
  </div>
</div>
</body>
</html>
