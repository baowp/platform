<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ABBCC登陆</title>
<link href="/user/css/locss.css" rel="stylesheet" type="text/css" />
<link href="/user/css/lodiv.css" rel="stylesheet" type="text/css" /> 
  
</head>

<body>
<div id="nar">
  <div id="top">
    <div class="top_left"><img src="/user/images/logo.jpg" width="163" height="47" /></div>
    <div class="top_right"><span class="font2"><a href="http://51archetype.com/user">返回登陆</a></span><br />
    <span class="font2">如遇问题请拨打 </span><span class="font01">0579-87171989</span></div>
  </div>
  <div id="cen">
  <s:if test="#parameters.pageType[0]==1">
<table width="100%" height="188" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td height="16" colspan="2" align="left" valign="middle"><div class="hv"></div></td>
      </tr>
      <tr>
        <td height="25" colspan="2" align="left" valign="top"><div class="mac"><span class="font04">找回登录名 </span><img src="/user/images/1_a.gif" width="16" height="16" /> <span class="font02">填写注册邮箱 </span>》<img src="/user/images/2.gif" width="16" height="16" /> <span class="font04">填写资料</span> 》<img src="/user/images/3.gif" width="16" height="16" /> <span class="font04">找回成功</span></div></td>
      </tr>
      <tr>
        <td width="48%" valign="top">
		<s:form namespace="/user/account/password"
		action="showForgetUsernameOrPassword">
		<s:hidden name="pageType" value="1" />
		<table width="100%" border="0" cellpadding="0">
            <tr>
              <td width="30%" height="31"  class="font04">请输入您绑定的邮箱：</td>
              <td width="35%"><s:textfield name="email"/></td>
              <td width="30%"></td>
            </tr>
            <tr>
              <td height="31">&nbsp;</td>
              <td align="left"><input type="submit" name="Submit" value="下一步" /></td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
        </table>
		</s:form>
		</td>
        <td width="52%">&nbsp;</td>
      </tr>
    </table>

</s:if><s:else>
<table width="100%" height="188" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td height="16" colspan="2" align="left" valign="middle"><div class="hv"></div></td>
      </tr>
      <tr>
        <td height="25" colspan="2" align="left" valign="top"><div class="mac"><span class="font04">找回密码 </span><img src="images/1_a.gif" width="16" height="16" /> <span class="font02">填写登陆名 </span>》<img src="images/2.gif" width="16" height="16" /> <span class="font04">填写资料</span> 》<img src="images/3.gif" width="16" height="16" /> <span class="font04">找回成功</span></div></td>
      </tr>
      <tr>
        <td width="48%" valign="top">
		<s:form namespace="/user/account/password"
		action="showForgetUsernameOrPassword">
		<s:hidden name="pageType" value="2" />
		<table width="100%" border="0" cellpadding="0">
            <tr>
              <td width="30%" height="31" align="right" class="font04">请输入您的登陆名：</td>
              <td width="70%" align="left"><s:textfield name="username"/></td>
            </tr>
            <tr>
              <td height="31">&nbsp;</td>
              <td align="left"><input type="submit" name="Submit" value="下一步" /></td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
        </table>
		</s:form>
		</td>
        <td width="52%">&nbsp;</td>
      </tr>
    </table>
</s:else>
   
  </div>
  <div id="bottom">
    <div class="bottom_center">
      <p><span class="font01"><a href="index.html">永康市东方五金电子商务有限公司</a>版权所有2000-2009 　E-mail:<a href="mailto:crm@ykit.net">crm@ykit.net</a> 　<a href="http://net.china.com.cn/index.htm" target="_blank">互联网违法和不良信息举报<br />
      </a><br />
        <a href="http://www.ykit.net/index.html" target="_blank">站点导航: 首页</a>&nbsp;<a href="http://www.ykit.net/website1.html" target="_blank">产品方案</a>&nbsp;<a href=" http://www.ykit.net/news.html" target="_blank">资讯中心</a>&nbsp;<a href="http://www.ykit.net/help.html" target="_blank">客户服务</a> <a href="http://www.ykit.net/seojs.html">建站常识&nbsp;|</a> <a href="http://www.ykit.net/values.html" target="_blank">企业文化</a>&nbsp;| <a href="http://www.ykit.net/about1.html" target="_blank">关于我们</a>&nbsp;| <a href="http://blog.sina.com.cn/dfwjw" target="_blank">企业博客&nbsp;|</a> <a href="http://www.miibeian.gov.cn/state/outPortal/loginPortal.action;jsessionid=w2RQM7nCpyGHrJz9Szm16mW3psLbyYzZKlYfS2TjJbgnNzGnXFXQ!177479300">经营许可证编号：浙B2-20050229 </a><a title="站长统计" href="http://www.cnzz.com/stat/website.php?web_id=1132593" target="_blank"><img border="0" hspace="0" src="http://icon.cnzz.com/pic.gif" /></a><br />
      </span></p>
    </div>
  </div>
</div>
</body>
</html>
