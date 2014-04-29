<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<HEAD>
<TITLE>ABBCC・商人自己的网站</TITLE>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<LINK rev="stylesheet" media="screen "
	href="css/ali_register_market.css" type=text/css rel=stylesheet>
<link rel="stylesheet" type="text/css"
	href="<s:url value="/user/css/register.css"/>">
<META content="MSHTML 6.00.2900.5803" name=GENERATOR>
</HEAD>
<link rel="stylesheet" type="text/css"
	href="<s:url value='/user/css/validation.css'/>">
<link rel="stylesheet" type="text/css"
	href="<s:url value='/user/css/login_v3.css'/>" media="screen"
	rev="stylesheet">
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript"
	src="<s:url value="/user/js/register.js"/>"></script>
<script type="text/javascript" src="<s:url value="/user/js/checkValue.js"/>"></script>
<script type="text/javascript" src="<s:url value="/user/js/common.js"/>"></script>
<BODY style="margin: 0 auto">
<s:include value="/user/include/header.jsp"></s:include>


<DIV class="clr main_box1 nav_bot">
<UL class=nav_main style="MARGIN-LEFT: 96px ! important; HEIGHT: 22px">
	<LI class=txt>注册步骤：</LI>
	<LI class="s1a p20 sa">填写注册信息</LI>
	<LI class=sf>&gt;</LI>

	<LI class="s2n p20">注册成功</LI>
	<LI class=stip><FONT color=red>* </FONT>为必填项</LI>
</UL>
<DIV class=clr></DIV>
</DIV>
<!--part up-->
<DIV id=main><!--part middle-->
<DIV id=content><INPUT id=sessionId type=hidden
	value=CDrx2UHG5kmpK2UfJ+A0wUTQsQYRMuCe name=sessionId>
<form name="checkNickForm" action="#" method="post"
	target="frameCheckForm"><input name="TPL_NICK" type="hidden"></form>
<s:form action="registerUser" name="form" method="post"
	onsubmit="return validateAll()">
	<DIV class=rightinfo></DIV>
	<DIV class=area>
	<DIV class=title>
	<H2>设置您的账户信息</H2>
	</DIV>
	<table>
		<tr>
			<td align="right" height="32" width="103"><span
				id="loginid_info_check"></span><strong>会员登录名</strong> <font
				color="red">*</font></td>
			<td width="271"><s:textfield name="username"
				style="width: 262px;" maxlength="50" id="loginid"
				onfocus="focustoRemind(this.id)"
				onblur="return blurtoRemind(this.id)" /></td>
			<td style="line-height: 14px;" width="289">
			<div class="" id="loginid_info"><font color="red">
			${errors.username[0]}</font></div>
			</td>
		</tr>
		<tr>
			<td align="right" height="32" width="103"><span
				id="password_info_check"></span><strong>密码</strong> <font
				color="red">*</font></td>
			<td width="271"><s:password id="password" name="password"
				style="width: 262px;" maxlength="50"
				onfocus="focustoRemind(this.id)"
				onblur="return blurtoRemind(this.id)" /></td>
			<td style="line-height: 14px;" width="289">
			<div class="" id="password_info"><font color="red">${errors.password[0]}</font></div>
			</td>
		</tr>
		<tr>
			<td align="right" height="32" width="103"><span
				id="confirm_password_info_check"></span><strong>再重复一次</strong> <font
				color="red">*</font></td>
			<td width="271"><s:password name="password2"
				style="width: 262px;" maxlength="50" id="confirm_password"
				onfocus="focustoRemind(this.id)"
				onblur="return blurtoRemind(this.id)" /></td>
			<td style="line-height: 14px;" width="289">
			<div class="" id="confirm_password_info"><font color="red">${errors.password[0]}</font></div>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<td align="right" height="32" width="103"><span
				id="first_name_info_check"></span><strong>真实姓名</strong> <font
				color="red">*</font></td>
			<td width="269"><s:textfield id="first_name" name="name"
				maxlength="50" style="width: 150px;" size="27"
				onfocus="focustoRemind(this.id)"
				onblur="return blurtoRemind(this.id)" /></td>
			<td style="line-height: 14px;" width="289">
			<div class="" id="first_name_info"><font color="red">${errors.name[0]}</font></div>
			</td>
		</tr>
		<tr>
			<td align="right" height="32" width="103"><span
				id="job_title_info_check"></span><strong>您的职位</strong> <font
				color="red">*</font></td>
			<td width="271"><s:textfield id="job_title" name="position"
				style="width: 262px;" maxlength="50"
				onfocus="focustoRemind(this.id)"
				onblur="return blurtoRemind(this.id)" /></td>
			<td style="line-height: 14px;" width="289">
			<div class="" id="job_title_info"><font color="red">${errors.position[0]}</font></div>
			</td>
		</tr>
		<tr>
			<td align="right" height="32" width="103"><span
				id="email_info_check"></span><strong>电子邮箱</strong> <font color="red">*</font></td>
			<td width="271"><s:textfield id="email" name="email"
				style="width: 262px;" maxlength="50"
				onfocus="focustoRemind(this.id)"
				onblur="return blurtoRemind(this.id)" /></td>
			<td style="line-height: 14px;" width="289">
			<div class="" id="email_info"><font color="red">${errors.email[0]}</font></div>
			</td>
		</tr>
		<tr id="phone_title">
			<td align="right" height="32" width="108"><span
				id="phone_info_check"></span><strong>固定电话</strong> <font color="red">*</font></td>
			<td width="269"><input id="CountryCode"
				onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/\D/g,''))"
				onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"
				onfocus="focustoRemind(this.id)"
				onblur="return blurtoRemind(this.id)" maxlength="5" size="3"
				name="telePhone1" value="86"> - <input id="CityCode"
				onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/\D/g,''))"
				onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"
				onfocus="focustoRemind(this.id);phonefocus(this,'区号')"
				onblur="phoneblur(this,'区号');return blurtoRemind(this.id);"
				maxlength="5" size="4" name="telePhone2" value="区号"
				style="color: rgb(153, 153, 153);"> - <input
				id="phone_number"
				onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/\D/g,''))"
				id="phone_number"
				onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"
				onfocus="phonefocus(this,'电话号码');focustoRemind(this.id)"
				onblur="phoneblur(this,'电话号码');return blurtoRemind(this.id)"
				maxlength="28" size="11" name="telePhone3" value="电话号码"
				style="color: rgb(153, 153, 153);"></td>
			<td width="289">
			<div class="" id="phone_info">&nbsp;<font color="red">${errors.telePhone3[0]}</font></div>
			</td>
		</tr>
		<tr id="fax_title">
			<td align="right" height="32" width="108"><span
				id="fax_info_check"></span><strong>传真&nbsp;</strong></td>
			<td width="269"><input
				onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/\D/g,''))"
				id="FaxCountryCode"
				onkeyup="if(event.keyCode!=37&amp;&amp;event.keyCode!=39)value=value.replace(/\D/g,'');"
				onfocus="focustoRemind(this.id)"
				onblur="return blurtoRemind(this.id)" maxlength="5" size="3"
				name="fax1" value="86"> - <input id="FaxCityCode"
				onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/\D/g,''))"
				onkeyup="if(event.keyCode!=37&amp;&amp;event.keyCode!=39)value=value.replace(/\D/g,'');"
				onfocus="phonefocus(this,'区号');focustoRemind(this.id)"
				onblur="phoneblur(this,'区号');return blurtoRemind(this.id)"
				maxlength="5" size="4" name="fax2" value="区号"
				style="color: rgb(153, 153, 153);"> - <input id="fax_number"
				onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/\D/g,''))"
				onkeyup="if(event.keyCode!=37&amp;&amp;event.keyCode!=39)value=value.replace(/\D/g,'');"
				onfocus="phonefocus(this,'传真号码');focustoRemind(this.id)"
				onblur="phoneblur(this,'传真号码');return blurtoRemind(this.id)"
				maxlength="28" size="11" name="fax3" value="传真号码"
				style="color: rgb(153, 153, 153);"></td>
			<td width="289">
			<div class="" id="fax_info">&nbsp;</div>
			</td>
		</tr>
		<tr>
			<td align="right" height="32" width="103"><span
				id="mobile_info_check"></span><strong>手机号码</strong> <font
				color="red">* </font></td>
			<td width="271"><input type="text" name="cellphone" id="mobile"
				style="width: 262px;" maxlength="50"
				onfocus="focustoRemind(this.id)"
				onblur="return blurtoRemind(this.id)"
				onkeyup="if(event.keyCode!=37&amp;&amp;event.keyCode!=39)value=value.replace(/\D/g,'');" /></td>
			<td width="289">
			<div class="" id="mobile_info"><font color="red">${errors.cellphone[0]}</font></div>
			</td>
		</tr>


	</table>

	<DIV class=title>
	<H2>公司名称和主营业务</H2>
	</DIV>
	<br />

	<table>
		<tr id="genre_title">
			<td align="right" valign="top" width="103"><strong>公司类型</strong>
			<font color="red">* </font></td>
			<td colspan="2"><input onfocus="usr('ent')" checked="checked"
				value="01" name="genre" style="border: 0pt none;" type="radio">企业单位&nbsp
			&nbsp <input onfocus="usr('ent')" value="02" name="genre"
				style="border: 0pt none;" type="radio">个体经营 <br>
			<br />
			<input onfocus="usr('ent')" value="03" name="genre"
				style="border: 0pt none;" type="radio">事业单位或社会团体&nbsp &nbsp
			<input onclick="usr('person')" value="00" name="genre" id="genre"
				style="border: 0pt none;" type="radio">未经工商注册，个人<br>
			</td>
		</tr>
		<tr id="company1">
			<td align="right" height="32" width="103"><span
				id="company_info_check"></span><strong>贵公司名称</strong> <font
				color="red">* </font></td>
			<td width="271"><s:textfield name="eName" id="ename"
				style="width: 262px;" maxlength="50"
				onfocus="focustoRemind(this.id)"
				onblur="return blurtoRemind(this.id)" /></td>
			<td style="line-height: 14px;" width="292"><div class="" id="company_info"><font color="red">${errors.eName[0]}</font></div></td>
		</tr>
		<tr id="company2">
			<td align="right" height="32" width="103"><strong>经营地址</strong><font
				color="red">* </font></td>
			<td width="271"><s:doubleselect name="eAddress"
				list="#session['sysMap'].keySet()" listKey="syscodeId"
				listValue="name" doubleName="eAddress2"
				doubleList="#session['sysMap'].get(top)" doubleListKey="syscodeId"
				labelposition="left" doubleListValue="name" theme="simple" /> <span
				class="errorSpan">${errors.eAddress[0]}</span></td>
		</tr>
		<tr id="company3">
			<td align="right" height="32" width="103"><strong>主营行业</strong><font
				color="red">* </font></td>

			<td width="271"><s:select list="#session['industyMap']"
				listKey="key" listValue="value" name="industry" /> <span
				class="errorSpan">${errors.industry[0]}</span></td>
		</tr>
		<tr></tr>
		<tr id="company4">
			<td align="right" valign="top" width="103"><span
				id="business_info_check"></span><strong>主营产品</strong> <font
				color="red">* </font></td>
			<td width="271"><span class="input"> <input
				onclick="tr_display('sell');" value="seller" name="business_role"
				id="business_role" style="border: 0pt none;" type="radio"
				checked="checked">销售 <input onclick="tr_display('buy');"
				value="buyer" name="business_role" id="business_role"
				style="border: 0pt none;" type="radio">采购 <input
				onclick="tr_display('both');" value="both"  name="business_role"
				id="business_role" style="border: 0pt none;" type="radio">两者都有
			<span class="errorSpan"></span> </span>

			<div id="sellkeywords">
			<div id="sellkey"><font color="#ff0000">销售</font>的产品（提供的服务）：<br>
			<s:textfield id="salekeyword" style="width: 262px;" class="f2"
				maxlength="500" size="40" name="mainPro"
				onfocus="focustoRemind(this.id)"
				onblur="return blurtoRemind(this.id)" /></div>
			</div>

			<div id="buykeywords" style="display: none;"><font
				color="#ff0000">采购</font>的产品（需要的服务）：<br>
			<s:textfield cssStyle="width: 200px;" class="f2" id="buykeyword"
				maxlength="500" size="40" name="mainBuy"
				onfocus="focustoRemind(this.id)"
				onblur="return blurtoRemind(this.id)" /></div>
			</td>

			<td style="line-height: 14px;" valign="top" width="292">
			<div class="" id="business_info"></div>
			</td>
		</tr>
		<tr></tr>
		<tr></tr>

		<tr id="validate_code_title">
			<td align="right" height="32" width="103"><span
				id="validate_code_info_check"></span><strong>验证码</strong> <font
				color="red">* </font></td>
			<td width="271">
			<table>
				<tr>
					<td><input id="validate_code" size="6" name="ValidKey"></td>
					<td><img alt="验证码" class="validate-num" id="verifyPic"
						name="ValidKey" src="<s:url value='/veriImg'/>"></td>
					<td><span class="errorSpan">${errors.valiCode[0]}</span><a
						href="javascript:void(0)" onclick="refreshCode();"><font
						color="blue">看不清，点击更换</font></a></td>
				</tr>
			</table>


			</td>
		</tr>

	</table>
	<hr size="1" style="border: 1px dotted #999999">
	<center>
	<table>
		<tr>
			<td colspan="3">
			<div align="center"><s:checkbox style="border: 0px none ;"
				checked="checked" value="1" name="yAllowEmail" />我愿意收到我感兴趣的买卖信息</div>
			</td>
		</tr>
		<tr>
			<td width="32%">&nbsp;</td>
			<td width="36%">
			<div align="center"><a
				href="<s:url value="/user/protocol.jsp"/>" target="_blank">点此阅读ABBCC网会员注册服务条款</a></div>
			</td>
			<td width="32%">&nbsp;</td>
		</tr>
		<tr>
			<td colspan="3">
			<div align="center"><s:submit
				style="font-size: 14px; height: 30px;" value="同意服务条款,提交注册信息"
				name="eventSubmit_doRegisteraction"
				onClick="javascript:my_submit();" /></div>
			</td>
		</tr>
	</table>
	</center>
</s:form>
<DIV id=subTip>
<DIV class=title>注册成为ABBCC会员，<BR> 您将免费享受以下会员服务: 
</DIV>
<UL class=con>
	<LI><IMG src="images/rgicon1.gif"><SPAN class=t1>发布产品供求信息</SPAN><BR>
	<SPAN class=t2>让生意自动找上门!</SPAN>
	<LI><IMG src="images/rgicon2.gif"><SPAN class=t1>网上推广公司</SPAN><BR>
	<SPAN class=t2>提升企业知名度!</SPAN>
	<LI><IMG src="images/rgicon3.gif"><SPAN class=t1>贸易通在线洽谈生意</SPAN><BR>
	<SPAN class=t2>让生意更快达成!</SPAN>
	<LI><IMG src="images/rgicon4.gif"><SPAN class=t1>邮箱订阅最新商机</SPAN><BR>
	<SPAN class=t2>不错过任何一个商机!</SPAN></LI>
</UL>
<IMG src="images/rgicon5.gif"></DIV>
</DIV>
<IFRAME id=privacyhiddenIfr src="about:blank" frameBorder=0></IFRAME>
<DIV id=privacyIfrLayer><IFRAME id=privacyIfr src="about:blank"
	frameBorder=0 width=800 scrolling=no height=600></IFRAME>
<DIV class=btn>
<BUTTON id=privacyBtn>阅读完毕，关闭窗口</BUTTON>
</DIV>
</DIV>
<BR>
<s:include value="/user/include/footer.jsp"></s:include>
</body>
</html>



