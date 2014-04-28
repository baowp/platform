<%@ page language="java" import="java.util.*,com.abbcc.models.*"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #F8F9FA;
}
-->
</style>
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<META HTTP-EQUIV="expires" CONTENT="Wed, 26 Feb 1997 08:21:57 GMT">
<link href="<s:url value='enterprise/css/skin.css'/>" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" href="<s:url value="/admin/css/common.css"/>"
	type="text/css" />
<link rel="stylesheet" rev="stylesheet"
	href="<s:url value='/user/enterprise/css/mainframe.css'/>"
	type="text/css">
<link rel="stylesheet" rev="stylesheet"
	href="<s:url value='/user/enterprise/css/content.css'/>"
	type="text/css">

<head>
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
</head>

<body>

<s:form>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="17" height="29" valign="top"
			background="<s:url value='/user/enterprise/images/mail_leftbg.gif'/>"
			width="17" height="29" /></td>
		<td width="935" height="29" valign="top"
			background="<s:url value='/user/enterprise/images/content-bg.gif'/>">
		</td>
		<td width="16" valign="top"
			background="<s:url value='/user/enterprise/images/mail_rightbg.gif'/>"></td>
	</tr>
	<tr>
		<td height="71" valign="middle"
			background="<s:url value='/user/enterprise/images/mail_leftbg.gif'/>">&nbsp;</td>
		<td valign="top" bgcolor="#F7F8F9">
		<table width="100%" height="138" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="13" valign="top">&nbsp;</td>
			</tr>
			<tr>
				<td valign="top">



				<table align="center" border="0" width="100%" cellpadding="0"
					cellspacing="0">
					<tbody>
						<tr>
							<td class="smalltitle" bgcolor="#CCCCCC">详细信息(<font color="red"><s:property value='#request.AbcEnterprise.name' /></font>)</td>
						</tr>
					</tbody>
				</table>
				<table style="border: 1px solid rgb(224, 224, 224);" align="center"
					border="0" width="100%" cellpadding="5" cellspacing="0">
					<tbody>
						<tr valign="top">
							<td class="S lh15" valign="middle">
							<table border="0" width="100%" cellpadding="4" cellspacing="2">
								<tbody>
									<tr bgcolor="#f6f6f6" valign="top">
										<td class="S" style="" align="right" bgcolor="dcecff"
											width="20%"><b>主营产品或服务：</b><br />
										</td>
										<td class="S" style="" align="left" width="30%"><span
											class="S" style=""><s:property
											value='#request.AbcEnterprise.mainBusiness' /></span></td>
										<td class="S" style="" align="right" bgcolor="dcecff"
											width="20%"><span class="S" style=""><b>主营行业：</b><br />
										</span></td>
										<td class="S" style="" align="left" width="30%"><span
											class="S" style=""><span class="S" style=""><s:select
											list="#request['industyMap']" listKey="key" listValue="value"
											name="industry" value="#request.AbcEnterprise.industry" disabled="true" /></span></span></td>
									</tr>
									<tr bgcolor="#ffffff" valign="top">
										<td class="S" style="" align="right" bgcolor="dcecff"><b>经营模式：</b><br />
										</td>
										<td class="S" style="" align="left"><span class="S"
											style=""> <s:select list="#request['BusinessTypeMap']" listKey="key"
						listValue="value" name="type" value="#request.AbcEnterprise.businessType" disabled="true"/> </span></td>
										<td class="S" style="" align="right" bgcolor="dcecff"><span
											class="S" style=""><b>企业类型：</b><br />
										</span></td>
										<td class="S" style="" align="left"><span class="S"
											style=""><span class="S" style=""> <s:if
											test="%{#request.AbcEnterprise.type==00}">个人用户</s:if><s:if
											test="%{#request.AbcEnterprise.type==01}">有限责任公司</s:if><s:if
											test="%{#request.AbcEnterprise.type==02}">个体经营</s:if><s:if
											test="%{#request.AbcEnterprise.type==03}">事业单位或团体</s:if></span></span></td>
									</tr>
									<tr bgcolor="#f6f6f6" valign="top">
										<td class="S" style="" align="right" bgcolor="dcecff"><b>公司注册地：</b><br />
										</td>
										<td class="S" style="" align="left"><span class="S"
											style=""><s:property
											value='#request.AbcEnterprise.dist' /></span></td>
										<td class="S" style="" align="right" bgcolor="dcecff"><span
											class="S" style=""><b>主要经营地点：</b><br />
										</span></td>
										<td class="S" style="" align="left"><span class="S"
											style=""><s:doubleselect name="eAddress" list="#request['sysMap'].keySet()" listKey="syscodeId" listValue="name" value="%{#request.addressOne}"
   doubleName="eAddress2" doubleList="#request['sysMap'].get(top)" doubleListKey="syscodeId" labelposition="left"  doubleListValue="name" doubleValue="%{#request.addressTwo}" theme="simple" disabled="true"/></span></td>
									</tr>
									<tr bgcolor="#ffffff" valign="top">
										<td class="S" style="" align="right" bgcolor="dcecff"><b>公司成立时间：</b><br />
										</td>
										<td class="S" style="" align="left"><span class="S"
											style=""><s:property
											value='#request.AbcEnterprise.regTime' /></span></td>
										<td class="S" style="" align="right" bgcolor="dcecff"><span
											class="S" style=""><b>法定代表人/负责人：</b><br />
										</span></td>
										<td class="S" style="" align="left"><span class="S"
											style=""><span class="S" style=""><s:property
											value='#request.AbcEnterprise.legalPre' /></span></span></td>
									</tr>
									<tr bgcolor="#f6f6f6" valign="top">
										<td class="S" style="" align="right" bgcolor="dcecff"><b>年营业额：</b><br />
										</td>
										<td class="S" style="" align="left"><span class="S"
											style=""><s:property
											value='#request.AbcEnterprise.annualTurnover' /> </span></td>
										<td class="S" style="" align="right" bgcolor="dcecff"><span
											class="S" style=""><b>员工人数：</b><br />
										</span></td>
										<td class="S" style="" align="left"><span class="S"
											style=""><span class="S" style=""><s:property
											value='#request.AbcEnterprise.staffSum' /> </span></span></td>
									</tr>
									<tr>
										<td class="S" style="" align="right" bgcolor="dcecff"><b>经营品牌：</b><br />
										</td>
										<td class="S" style="" align="left"><span class="S"
											style=""><s:property
											value='#request.AbcEnterprise.brand' /></span></td>
										<td class="S" style="" align="right" bgcolor="dcecff"><span
											class="S" style=""><b>注册资本：</b><br />
										</span></td>
										<td class="S" style="" align="left"><span class="S"
											style=""><span class="S" style=""> <s:property
											value='#request.AbcEnterprise.registeredCapital' /></span></span></td>
									</tr>
									<tr bgcolor="#f6f6f6" valign="top">
										<td class="S" style="" align="right" bgcolor="dcecff"><b>主要客户群：</b><br />
										</td>
										<td class="S" style="" align="left"><s:property
											value='#request.AbcEnterprise.customer' /></td>
										<td class="S" style="" align="right" bgcolor="dcecff"><span
											class="S" style=""><b>主要市场：</b><br />
										</span></td>
										<td class="S" style="" align="left"><span class="S"
											style=""><span class="S" style=""><s:property
											value='#request.AbcEnterprise.market' /></span></span></td>
									</tr>
									<tr>
										<td class="S" style="" align="right" bgcolor="dcecff"><b>年出口额：</b><br />
										</td>
										<td class="S" style="" align="left"><s:property
											value='#request.AbcEnterprise.annualExport' /></td>
										<td class="S" style="" align="right" bgcolor="dcecff"><span
											class="S" style=""><b>年进口额：</b></span></td>
										<td class="S" style="" align="left"><s:property
											value='#request.AbcEnterprise.annualImport' /></td>
									</tr>
									<tr bgcolor="#f6f6f6" valign="top">
										<td class="S" style="" align="right" bgcolor="dcecff"><b>开户银行：</b><br />
										</td>
										<td class="S" style="" align="left"><span class="S"
											style=""><s:property
											value='#request.AbcEnterprise.bank' /></span></td>
										<td class="S" style="" align="right" bgcolor="dcecff"><span
											class="S" style=""><b>帐号：</b><br />
										</span></td>
										<td class="S" style="" align="left"><span class="S"
											style=""><span class="S" style=""><s:property
											value='#request.AbcEnterprise.bankAccount' /></span></span></td>
									</tr>
									<tr>
										<td class="S" style="" align="right" bgcolor="dcecff"><b>是否提供OEM服务？</b><br />
										</td>
										<td class="S" style="" align="left"><span class="S"
											style=""> <s:if test="#request.AbcEnterprise.oem==01">
												是
											</s:if> <s:else>
												否
											</s:else> </span></td>
										<td class="S" style="" align="right" bgcolor="dcecff"><span
											class="S" style=""><b>研发部门人数：</b></span></td>
										<td class="S" style="" align="left"><s:property
											value='#request.AbcEnterprise.rdSum' /></td>
									</tr>
									<tr bgcolor="#f6f6f6" valign="top">
										<td class="S" style="" align="right" bgcolor="dcecff"><b>月产量：</b><br />
										</td>
										<td class="S" style="" align="left"><span class="S"
											style=""><s:property
											value='#request.AbcEnterprise.monthProd' /></span></td>
										<td class="S" style="" align="right" bgcolor="dcecff"><span
											class="S" style=""><b>厂房面积：</b><br />
										</span></td>
										<td class="S" style="" align="left"><span class="S"
											style=""><span class="S" style=""> <s:property
											value='#request.AbcEnterprise.factoryArea' />平方米</span></span></td>
									</tr>
									<tr valign="top">
										<td class="S" style="" align="right" bgcolor="dcecff"><b>质量控制：</b><br />
										</td>
										<td class="S" style="" align="left"><span class="S"
											style=""><s:property
											value='#request.AbcEnterprise.qualityControl' /></span></td>
										<td class="S" style="" align="right" bgcolor="dcecff"><span
											class="S" style=""><b>管理体系认证：</b><br />
										</span></td>
										<td class="S" style="" align="left"><span class="S"
											style=""><span class="S" style=""><s:property
											value='#request.AbcEnterprise.qasyscert' /></span></span></td>
									</tr>
								</tbody>
							</table>
							</td>
						</tr>
					</tbody>
				</table>
				<table align="center" border="0" width="100%" cellpadding="0"
					cellspacing="0">
					<tbody>
						<tr>
							<td class="smalltitle" bgcolor="#CCCCCC">联系方式</td>
						</tr>
					</tbody>
				</table>
				<table style="border: 1px solid rgb(224, 224, 224);" align="center"
					border="0" width="100%" cellpadding="5" cellspacing="0">
					<tbody>
						<tr valign="top">
							<td class="S lh15" align="center" valign="middle">
							<div class="bodyContContent rel" style="text-align: center;">
							</div>
							<table border="0" width="442" cellpadding="0" cellspacing="0">
								<tbody>
								</tbody>
							</table>
							<table align="center" border="0" width="98%" cellpadding="0"
								cellspacing="0">
								<tbody>
									<tr>
										<td class="S lh15"
											style="border-left: 1px solid rgb(255, 255, 255); border-right: 1px solid rgb(255, 255, 255);"
											align="left" height="28">

										<ul class="mainTextColor">
											<li><s:property value='#request.AbcUser.name' />(<s:property
												value='#request.AbcUser.position' />)</li>
											<li>电 话：<s:property value='#request.AbcUser.phone' /></li>
											<li>传 真：<s:property value='#request.AbcUser.fax' /></li>
											<li>地 址：<s:property value='#request.AbcUser.address' /></li>
											<li>EMAIL：<s:property value='#request.AbcUser.email' /></li>
											<li>QQ：<s:property value='#request.AbcUser.qq' /></li>
											<li>MSN：<s:property value='#request.AbcUser.msn' /></li>
											<li>邮 编：<s:property value='#request.AbcUser.zipcode' /></li>
											<li>公司主页：<a
												href="<s:property value='#request.AbcUser.url' />"><s:property
												value='#request.AbcUser.url' /></a></li>
										</ul>
										</td>
									</tr>
								</tbody>
							</table>
							<br />
							<br />
							</td>
						</tr>
					</tbody>
				</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</s:form>

</body>
</html>



