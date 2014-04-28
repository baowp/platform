<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link href="images/skin.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"
	href="<s:url value='/user/css/validation.css'/>">

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
                           
<body>

<table style="width: 100%; height: 100%" border="0">

	<tr>
		<td valign="top" align="left">
		<div  
			style="background-color: #F0F8FF; BORDER-RIGHT: #aaa 1px solid; BORDER-TOP: #aaa 1px solid; BORDER-LEFT: #aaa 1px solid; BORDER-BOTTOM: #aaa 1px solid;padding:5px"">
		 当前共有会员<span
			style="color: red">${memberCount}</span>个，非付费会员<span style="color: red">${freeMember}</span>个,企业版会员<span style="color: red">${CommonMember}</span>个，集团版会员<span style="color: red">${advancedMember}</span>个,今天新注册<span
			style="color: red">${todayMember}</span>个:
		<hr style="border: 1px #cccccc dotted;" />
		<div style="color: #f6aa3d; font-size: 14px;padding:5px"><a href="payEnd?payendType=3">一个月以内到期的会员有<font color="red">${monthMember}</font>个</a></div>
		<div style="color: #f6aa3d; font-size: 14px;padding:5px"><a href="payEnd.action?payendType=-1">已经到期的会员有<font color="red">${expiredMember}</font>个</a></div>
		<div style="color: #f6aa3d; font-size: 14px;padding:5px"><a href="searchMemberAudit?pageType=notAuditMember">待审核的会员有<font color="red">${notAuditMember}</font>个</a></div>
		</div>
		</td>
	</tr>
	<tr>
		<td valign="top" align="left">
		<div
			style="background-color: #f6e0e0; BORDER-RIGHT: #cf5f5f 1px solid; BORDER-TOP: #cf5f5f 1px solid; BORDER-LEFT: #cf5f5f 1px solid; BORDER-BOTTOM: #cf5f5f 1px solid;padding:5px">
		<a href="#" style="width: 120px"></a>提醒信息
		<hr style="border: 1px #cccccc dotted;" />
		<div style="color: #f6aa3d; font-size: 14px;padding:5px"><a href="viewAuditNews">待审核的新闻有<font color="red">${news}</font>个</a></div>
		<div style="color: #f6aa3d; font-size: 14px;padding:5px"><a href="viewAuditCert">待审核的证书有<font color="red">${cert}</font>个</a></div>
		<div style="color: #f6aa3d; font-size: 14px;padding:5px"><a href="viewRecuitAudit">待审核的招聘有<font color="red">${job}</font>个</a></div>
		<div style="color: #f6aa3d; font-size: 14px;padding:5px"><a href="viewAuditSupply">待审核的供求有<font color="red">${supply}</font>个</a></div>
		<div style="color: #f6aa3d; font-size: 14px;padding:5px"><a href="viewAuditProd">待审核的产品有<font color="red">${prod}</font>个</a></div>
		
		<div style="color: #f6aa3d; font-size: 14px;padding:5px"><a href="userUpdateAudit">待审核的用户资料有<font color="red">${userUpdate}</font>个</a></div>
		<div style="color: #f6aa3d; font-size: 14px;padding:5px"><a href="entUpdateAudit">待审核的企业资料有<font color="red">${entUpdate}</font>个</a></div>
		<div style="color: #f6aa3d; font-size: 14px;padding:5px"><a href="entInfoUpdateAudit">待审核的企业简介有<font color="red">${entInfoUpdate}</font>个</a></div>
		<div style="color: #f6aa3d; font-size: 14px;padding:5px"><a href="technicUpdateAudit">待审核的技术实力有<font color="red">${technicUpdate}</font>个</a></div>
		</td>
	</tr>

</table>



</body>

