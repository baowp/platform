<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.abbcc.common.CommonConst"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户登陆</title>
<script type="text/javascript" src="/js/artDialog3.0.5/artDialog.min.js"></script>	
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<link href="style/locss.css" rel="stylesheet" type="text/css" />
<link href="style/lodiv.css" rel="stylesheet" type="text/css" /> 
<style>
.center_right_t {
    background-image: url("/user/images/login2.jpg");
    height: 300px;
}
</style>
<LINK rev=stylesheet media=screen
	href="<s:url value="/user/css/validation.css"/>" type=text/css
	rel=stylesheet>
<script type="text/javascript">
function login(){
	$("#form1").submit();
}
function sendMail(userId){
	$.getJSON("mailreplaceSendMail?randomNumber="+Math.random(), {
		userId:userId
	}, function(result) {
		alert("邮件重发成功");
	});
}
$(function(){
	textTips($("#username"),'输入您的用户名或email');
})
function textTips(obj,txt,type){
	
	obj.val(txt)
	obj // 所有样式名中含有grayTips的input
	.each(function() {
		var oldVal = txt; // 默认的提示性文本
		$(this).css({
			"color" : "#DCDCDC"
		}) // 灰色
		.focus(function() {
			
			if ($(this).val() != oldVal) {
				$(this).css({
					"color" : "#000000"
				})
			} else {
				$(this).val("").css({
					"color" : "#DCDCDC"
				})
			}
		}).blur(function() {
			if ($(this).val() == "") {
				
				$(this).val(oldVal).css({
					"color" : "#DCDCDC"
				})
			}
		}).keydown(function() {
			
			$(this).css({
				"color" : "#000000"
			})
		})

	})
}
</script>

<script language="JavaScript">
		if (window != top)
			top.location.href = location.href;
		</script> 
</head>
<!--[if lte IE 6]>
<script src="/js/DD_belatedPNG_0.0.8a.js" type="text/javascript"></script>
    <script type="text/javascript">
        DD_belatedPNG.fix('div, ul, img, li, input , a');
    </script>
<![endif]-->
<body>
<div id="nav">
  <div id="top">
    <div class="top_left"></div>
    <div class="top_right"><br />
    <span class="font2"></div>
  </div>
  <div id="center">
    <div id="center_left">
      <div class="center_left_t"><img src="images/tupian0.jpg" width="559" height="226" /></div>
      <div class="center_left_b">
        <table width="90%" height="150" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td colspan="2" class="font02">登录后，您即可享受以下会员服务：</td>
          </tr>
          <tr>
            <td width="28" align="center"><img src="images/icon_1.gif" width="16" height="16" /></td>
            <td width="475" class="font04">发布产品供求信息，让生意自动找上门</td>
          </tr>
          <tr>
            <td width="28" align="center"><img src="images/icon_2.gif" width="16" height="16" /></td>
            <td class="font04">发布公司介绍，提升企业知名度</td>
          </tr>
          <tr>
            <td width="28" align="center"><img src="images/icon_3.jpg" width="16" height="16" /></td>
            <td class="font04">申请企业旺铺，量身打造全能企业网站</td>
          </tr>
          <tr>
            <td width="28" align="center"><img src="images/icon_4.gif" width="16" height="16" /></td>
            <td class="font04">交商友、看商情，时刻掌握行业最新资讯</td>
          </tr>
        </table>
      </div>
    </div>
    <div id="center_right">
          <s:form name="loginform" action="login" namespace="/user" id="loginForm">
     <s:hidden name="loginPage" value="/user/login2.jsp"></s:hidden>
      <div class="center_right_t">
        <div class="login_t">      
        <div style="text-align:left;"><span class="errorSpan">${errors.username[0]!=null?errors.username[0]:errors.password[0]!=null?errors.password[0]:errors.valiCode[0]!=null?errors.valiCode[0]:errors.topError[0]}</span></div>
		
          <div class="login_t01">用户名：<span class="form1">
           <s:textfield name="username" id="username" maxLength="20" size="20"
				cssClass="form01"/>
          </span> &nbsp;&nbsp;&nbsp;<a href="account/account_forgot_page.jsp?pageType=1">找回登录名</a></div>
          <div class="login_t02">密&nbsp;&nbsp;&nbsp;&nbsp;码：<span class="form1">
            <s:password cssClass="form01" type="password" size="20"
				maxLength="20" name="password" id="password"/>
          </span>&nbsp;&nbsp;&nbsp;<a href="account/account_forgot_page.jsp?pageType=2">找回密码</a></div>
        </div>
        <div id="login_c">
          <div id="login_c_r">验证码：<span class="form1">
            <s:textfield name="valiCode" cssClass="form03"/>
          </span></div>
          <div id="login_c_f"><img class="validate-num" id="verifyPic" name="verifyPic" width="56" height="25" valign="bottom"
				src="<s:url value="/veriImg"/>"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a onclick="refreshCode();" href="#">换一张？</a></div>
        </div>
        <div class="login_b">
          <table width="100%" height="35" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td width="3%" class="font03">&nbsp;</td>
              <td width="43%" align="left"><span class="font03"><a href="javascript:void(0)"><input type="image" src="images/denglu.gif" width="121" height="40" border="0"/></a></span></td>
              <td width="54%" align="left"><a href="reg2.jsp"><img src="images/anniuce.jpg" width="121" height="40" border="0" /></a></td>
            </tr>
          </table>
        </div>
		
      </div>
      </s:form>
      <div class="center_right_b"></div>
    </div>
  </div>
  
</div>
</body>
<script type="text/javascript" src="/js/unIframe.js"></script>
</html>
