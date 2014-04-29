<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" rev="stylesheet" href="<s:url value='/user/upgrade/css/css.css'/>"
	type="text/css" media="screen">
<link type="text/css" rel="stylesheet" rev="stylesheet"
	href="<s:url value='/user/upgrade/css/default.css'/>">
<link type="text/css" rel="stylesheet" rev="stylesheet"
	href="<s:url value='/user/upgrade/css/cxtbd.css'/>">
<link rel="stylesheet" type="text/css"
	href="<s:url value='/user/css/validation.css'/>">
<title>电话中心</title>
<link href="/user/css/Remittance.css" rel="stylesheet" type="text/css" />
<!--[if lte IE 6]>
<script src="js/DD_belatedPNG_0.0.8a.js" type="text/javascript"></script>
    <script type="text/javascript">
        DD_belatedPNG.fix('div, ul, img, li, input , a');
    </script>
<![endif]-->
<link href="/user/css/operation.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div id="nav">
  <div id="top">
    <table width="100%" height="75" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="16%"><img src="/user/images/logo.png" width="163" height="47" /></td>
        <td width="1%">&nbsp;</td>
        <td width="66%" align="left">&nbsp;</td>
        <td width="17%" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td><a href="http://51archetype.com" target="_blank">返回首页</a><span class="font"> </td>
            </tr>
        </table></td>
      </tr>
    </table>
  </div>
  <div id="contents">
  						<form name="aliform" method="post"
							action="<s:url value='/user/upgrade/join'/>"
							onsubmit="return checkForm(this)">
							<s:hidden name="pageType" value="01"/>
    <table width="95%" border="0" cellpadding="0" class="tb tb2 fixpadding">
      <tbody>
        <tr class="hover">
          <td width="15%" align="left" bgcolor="#EAF2FB" class="header">&nbsp;&nbsp;电话申请</td>
          <td colspan="2" align="left" bgcolor="#EAF2FB" class="font5">我们专业的服务人员会为您介绍高级服务，并确认您的申请要求！</td>
        </tr>
        <tr class="hover">
          <td bgcolor="#EAF2FB" class="header">&nbsp;</td>
          <td colspan="2" align="left" bgcolor="#EAF2FB" class="font4">申请或咨询服务，请拨打 <strong>0579-87171989</strong><br />
            服务人员将会拨打您留下的电话，与您确认申请要求！</td>
        </tr>
        <tr class="hover">
          <td align="left" bgcolor="#EAF2FB">&nbsp;<span class="header">&nbsp;网上申请</span></td>
          <td colspan="2" align="left" bgcolor="#EAF2FB" class="font5">请确认您的基本信息，收到您的申请后，我们将安排服务人员与您联系！</td>
        </tr>
        <tr class="hover">
          <td align="left"><span class="header"><span class="font">*</span></span>公司名称</td>
          <td align="left"><span class="vtop rowform">
            <input type="text" class="txts" value="${requestScope.enterprise.name}" name="ename" />
          </span></td>
          <td width="41%"><span class="errorSpan">${errors.ename[0]}</span></td>
        </tr>
        <tr class="hover">
          <td align="left"><span class="header"><span class="font">*</span></span>公司所在地</td>
          <td align="left"><span class="vtop rowform">
            <input type="text" name="address" value="${requestScope.enterprise.dist}" class="txts"/>
          </span></td>
          <td class="header">&nbsp;</td>
        </tr>
        <tr class="hover">
          <td align="left"><span class="header"><span class="font">*</span></span>你的职位</td>
          <td align="left"><span class="vtop rowform">
            <input class="txts"
										 id="memberJobtitle" name="position"
										size="36" maxlength="16"
										value="${sessionScope.abbccuser.position}" type="text">
          </span></td>
          <td class="header">&nbsp;</td>
        </tr>
        <tr class="hover">
          <td align="left"><span class="header"><span class="font">*</span></span>你的姓名</td>
          <td align="left"><span class="vtop rowform">
            <input class="txts" maxlength="32" id="memberName"
										size="15" name="uname" value="${sessionScope.abbccuser.name}"
										type="text"> <input name="gender" checked="checked"
										value="M" type="radio"> 先生 <input name="gender"
										value="F" type="radio"> 女士
          </span></td>
          <td class="header"><span class="errorSpan">${errors.uname[0]}</span></td>
        </tr>
        <tr class="hover">
          <td align="left"><span class="font">*</span>固定电话</td>
          <td align="left"><span class="vtop rowform">
            <input type="text" name="phone"
										value="${sessionScope.abbccuser.phone}" class="txts"/>
          </span></td>
          <td class="header">&nbsp;</td>
        </tr>
        <tr>
          <td align="left" class="hover">&nbsp;&nbsp;手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机</td>
          <td align="left" class="hover"><span class="vtop rowform">
           <input size="30" maxlength="16"
										id="mobileNo" class="txts"
										name="cellphone" value="${sessionScope.abbccuser.cellphone}"
										 type="text">
          </span></td>
          <td class="header"><span class="errorSpan">${errors.cellphone[0]}</span></td>
        </tr>
        <tr>
          <td align="left" class="hover"><span class="font">*</span>E-mail </td>
          <td align="left" class="hover"><span class="vtop rowform">
            <input id="companyEmail"
										name="email" class="txts"
										size="30" maxlength="50"
										value="${sessionScope.abbccuser.email}" type="text">
          </span></td>
          <td class="header"><span
										class="errorSpan">${errors.email[0]}</span></td>
        </tr>
        <tr>
          <td align="left" class="hover"><span class="font">*</span>购买高级会员服务年数</td>
          <td align="left" class="hover"><input name="year" value="01"
										checked="checked" type="radio">
									一年&nbsp;&nbsp;&nbsp;&nbsp; <input name="year" value="02"
										type="radio"> 两年</td>
          <td class="header">&nbsp;</td>
        </tr>
        <tr>
          <td class="hover">&nbsp;</td>
          <td colspan="2" align="left" class="hover"><img src="images/icon_arrow06_right_16x.gif" width="16" height="16" /><a href="#" target="_blank"><U>点此阅读高级会员服务协议</U></a></td>
        </tr>
        <tr>
          <td class="header">&nbsp;</td>
          <td align="left" class="hover">点击以下“确认提交”按钮，即表示您已详细阅读并同意高级会员服务协议</td>
          <td class="header"></td>
        </tr>
        <tr>
          <td class="header">&nbsp;</td>
          <td align="left" class="hover"><input type="submit" name="Submit" value="确认提交" /></td>
          <td class="header"></td>
        </tr>

      </tbody>
    </table>
  </div>
  <div id="bottoms">
    <table width="100%" height="150" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td height="20">&nbsp;<span class="font5">&nbsp;会员成功故事</span></td>
      </tr>
      <tr>
        <td><table width="100%" height="120" border="0" cellpadding="0">
            <tr>
              <td align="center"><img src="images/people.jpg" width="108" height="125" /></td>
              <td align="left" valign="top"><table height="120" cellpadding="0" cellspacing="0">
                  <tr>
                    <td valign="top" width="147" align="left">陈芳 女士 <br />
                      职位：销售经理 <br />
                      主营：金属制品</td>
                  </tr>
                  <tr>
                    <td valign="top" align="left">·<a href="#" target="_blank">神奇！高级真的有用</a><br />
                      <a href="#" target="_blank">深圳华凯家具有限公司</a></td>
                  </tr>
              </table></td>
              <td><img src="images/cxt_cghy_01.jpg" width="120" height="120" /></td>
              <td><table height="120" cellpadding="0" cellspacing="0">
                  <tr>
                    <td valign="top" width="147" align="left">潘伟 先生 <br />
                      职位：销售主管 <br />
                      主营：报警系统等</td>
                  </tr>
                  <tr>
                    <td valign="top" align="left">·<a href="#" target="_blank">我们最好的业务员 </a><br />
                      <a href="#" target="_blank">上海乾日贸易</a></td>
                  </tr>
              </table></td>
              <td><img src="images/bf0721d6b9b841358e324004554bc05b.jpg" width="100" height="100" /></td>
              <td><table height="120" cellpadding="0" cellspacing="0">
                  <tr>
                    <td valign="top" width="147" align="left">徐敏 女士 <br />
                      职位：厂长<br />
                      主营：文教用品   等</td>
                  </tr>
                  <tr>
                    <td valign="top" align="left">·<a href="#" target="_blank">每个星期都有订单</a><br />
                      <a href="#" target="_blank">武义协力文具用品厂</a></td>
                  </tr>
              </table></td>
            </tr>
        </table></td>
      </tr>
    </table>
	</form>
  </div>
</div>
</body>
</html>
