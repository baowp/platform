<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户注册</title>
<link href="style/reg.css" rel="stylesheet" type="text/css" />
<!--[if lte IE 6]>
<script src="js/DD_belatedPNG_0.0.8a.js" type="text/javascript"></script>
    <script type="text/javascript">
        DD_belatedPNG.fix('div, ul, img, li, input , a');
    </script>
<![endif]-->
<link id="artDialogSkin" href="/js/artDialog/skin/default.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/artDialog/artDialog.js"></script>
<script type="text/javascript" src="/user/js/reg_read.js"></script>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/user/js/reg.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<script src="js/DD_belatedPNG_0.0.8a.js" type="text/javascript"></script>
</head>

<body>

<div id="header_bottom"></div>
<div id="contain">
<div class="top">
<ul>
	<li>你当前的位置：</li>
	<li class="top_01"><a href="/">网站首页</a></li>
	<li class="top_01">></li>
	<li class="top_01">免费注册</li>
</ul>
<ul class="top_02">
	<li><a href="/">返回首页</a></li>
	<li>|</li>
	<li><a href="/user/login2.jsp">登陆</a></li>
</ul>
</div>
<div class="contain_top">
<ul>
	<li class="c">设置你的帐户信息</li>
	<li class="top_01">(<span class="font01">*</span>为必填)</li>
</ul>
</div>
<s:form method="post" action="registerUser" id="regForm" namespace="/user">
<div class="contain_center01">

<table width="100%" border="0">
	<tr>
		<td width="2%" height="35" align="right" class="font01">*</td>
		<td width="8%" height="35" align="left">电子邮箱：</td>
		<td width="26%" height="35" align="left"><s:textfield id="email"
			cssClass="form" name="email" maxlength="50" /></td>
		<td width="64%" height="35" align="left">
		<div style="width: 350px;" id="emailTip" class="${errors.email[0]!=null?'onError':''}">${errors.email[0]}</div>
		</td>
	</tr>
	<tr>
		<td height="35" align="right" class="font01">*</td>
		<td height="35" align="left">用户名：</td>
		<td height="35" align="left"><s:textfield id="username"
			cssClass="form" name="username" maxlength="20" /></td>
		<td height="35" align="left">
		<div style="width: 350px;" id="usernameTip" class="${errors.username[0]!=null?'onError':''}">${errors.username[0]}</div>
		</td>
	</tr>
	<tr>
		<td height="35" align="right" class="font01">*</td>
		<td height="35" align="left">登录密码：</td>
		<td height="35" align="left"><s:password cssClass="form"
			maxLength="32" name="password" maxlength="20" id="password" /></td>
		<td height="35" align="left">
		<div style="width: 350px;" id="passwordTip" class="${errors.password[0]!=null?'onError':''}">${errors.password[0]}</div>
		</td>
	</tr>
	<tr>
		<td height="35" align="right" class="font01">*</td>
		<td height="35" align="left">确认密码：</td>
		<td height="35" align="left"><s:password cssClass="form"
			name="password2" maxlength="20" id="password2" /></td>
		<td height="35" align="left">
		<div style="width: 350px;" id="password2Tip"></div>
		</td>
	</tr>
</table>
</div>
<div class="contain_top">
<ul>
	<li class="c">公司信息和联系方式</li>
	<li class="top_01">(<span class="font01">*</span>为必填)</li>
</ul>
</div>
<div class="contain_center">
<table width="100%" border="0">
	<tr>
		<td width="2%" height="35" align="right" class="font01">*</td>
		<td width="8%" height="35" align="left">会员身份：</td>
		<td width="26%" height="35" align="left"><input type="radio"
			name="identity" value="buyer" id="radio"
			<s:if test="%{#request.identity=='buyer'}"> checked="checked" </s:if> />
		买家 <input type="radio" name="identity" value="seller" id="radio2"
			<s:if test="%{#request.identity=='seller'}"> checked="checked" </s:if> />
		卖家 <input type="radio" name="identity" value="both" id="radio3"
			<s:if test="%{#request.identity=='both'}"> checked="checked" </s:if> />
		两者都是</td>
		<td width="64%" height="35" align="left">
		<div style="width: 350px;" id="identityTip" ></div>
</td>
	</tr>
	<tr>
		<td height="35" align="right" class="font01">*</td>
		<td height="35" align="left">姓名：</td>
		<td height="35" align="left"><s:textfield id="name" name="name"
			maxlength="20" cssClass="form" /></td>
		<td height="35" align="left">
		<div style="width: 350px;" id="nameTip" class="${errors.name[0]!=null?'onError':''}">${errors.name[0]}</div>
		</td>
	</tr>
	<tr>
		<td height="35" align="right" class="font01">*</td>
		<td height="35" align="left">公司名称：</td>
		<td height="35" align="left"><s:textfield id="entName"
			name="entName" maxlength="20" cssClass="form"
			value="%{#request.eName}" /></td>
		<td height="35" align="left">
		<div style="width: 350px;" id="entNameTip" class="${errors.entName[0]!=null?'onError':''}">${errors.entName[0]}</div>
		</td>
	</tr>
	<tr>
		<td height="35" align="right" class="font01">*</td>
		<td height="35" align="left">固定电话：</td>
		<td height="35" align="left"><s:textfield cssClass="form"
			maxlength="20" name="phone" id="phone" /></td>
		<td height="35" align="left">
		<div style="width: 350px;" id="phoneTip" class="${errors.phone[0]!=null?'onError':''}">${errors.phone[0]}</div>
		</td>
	</tr>
	<tr>
		<td height="35" align="right" class="font01">*</td>
		<td height="35" align="left">手机号码：</td>
		<td height="35" align="left"><s:textfield name="cellphone"
			maxlength="19" cssClass="form" id="cellphone" /></td>
		<td height="35" align="left">
		<div style="width: 350px;" id="cellphoneTip" class="${errors.cellphone[0]!=null?'onError':''}">${errors.cellphone[0]}</div>
		</td>
	</tr>
	<tr>
		<td height="35" align="right" class="font01">*</td>
		<td height="35" align="left">验证码：</td>
		<td height="35" align="left"><table><tr><td><s:textfield name="ValidKey"
			cssStyle="width:80px;" maxlength="4" id="valcode"/></td><td>
			<img alt="验证码" class="validate-num" id="verifyPic" name="ValidKey"
				src="<s:url value='/veriImg'/>"><a href="javascript:void(0)"
				onclick="refreshCode();"><font color="blue">看不清，点击换</font></a></td></tr></table></td>
		<td height="35" align="left"><div style="width: 350px;" id="valcodeTip"></div></td>
	</tr>
</table>
</div>
<div class="contain_bottom">
<table width="100%" border="0">

	<tr>
		<td width="2%" height="35" align="right" class="font01">&nbsp;</td>
		<td height="35" align="left">&nbsp;</td>
		<td width="26%" height="35" align="left">
		<input type="image" src="/user/images/submit.jpg" title="提交"/><br/><br/><a href="javascript:" id="read">点击阅读Abbcc服务条款</a>
		</td>
		<td width="64%" height="35" align="left">&nbsp;</td>
	</tr>
</table>
</div>
</s:form>
</div>

</body>
</html>
