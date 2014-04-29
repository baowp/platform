<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="tipDiv" style="display: none;" class="alr"><strong>亲爱的会员：</strong>
<div id="tip" style="margin: 5px 0pt;"><img height="16" width="16"
	align="absmiddle"
	src="http://style.org.hc360.com/images/my/images/corcenter/icon_alert.gif"></div>
</div>
<table width="760" cellspacing="0" cellpadding="0" border="0"
	align="center">
	<tbody>
		<tr>
			<td>
			产品名称:<a href="/user/product/edit?id=${productId }&back=showpublished"> <s:property value="productName(productId).name"/></a>

			<div
				style="background-color: rgb(255, 245, 216); border: 1px solid rgb(255, 225, 138); padding: 10px;">

			<table cellspacing="0" cellpadding="0">
				<tbody>
					<tr>
						<td height="30">议价标题:&nbsp;<font color="red">*</font>&nbsp;</td>
						<td>${title}</td>
					</tr>
					<tr>
						<td height="30">订货总量：</td>
						<td>${count }个</td>
					</tr>
					<tr>
						<td height="30">期望价格：</td>
						<td>${price }元(单价)</td>
					</tr>
				</tbody>
			</table>
			</div>
			<br>
			<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tbody>
					<tr>
						<td><strong>我想了解的产品信息有：</strong></td>
					</tr>
				</tbody>
			</table>
			<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tbody>
					<tr>
						<td>${toKnow}</td>
					</tr>
				</tbody>
			</table>
			<div align="left"><br>
			·补充信息: <br>
			<textarea
				hckeyworddisp="补充信息" value="added" cols="87" rows="6" name="added" id="added">${added}</textarea> <br>
			我希望
			<s:date name="endTime" format="yy-MM-dd hh:mm:ss"/>
			日前回复！</div>
			<s:if test="%{userType=='02'}">
				<div class="bt">非会员信息</div>
				<table width="100%" cellspacing="0" cellpadding="5" border="0"
					align="center" style="border: 1px solid rgb(224, 224, 224);">
					<tbody>
						<tr bgcolor="#ffffff">
							<td nowrap="nowrap" class="C" colspan="3">
							<table width="100%" cellspacing="0" cellpadding="4" border="0">
								<tbody>
									<tr bgcolor="#fbfbfb">
										<td width="35%" bgcolor="#ffffff"><b>您的姓名：</b> ${name}</td>
										<td width="65%" valign="top" bgcolor="#ffffff"><b>公司名称：</b>
										${companyName }</td>
									</tr>
									<tr bgcolor="#fbfbfb">
										<td bgcolor="#ffffff"><b>电子邮箱：</b> ${email }</td>
										<td bgcolor="#ffffff"><b>联系电话：</b> ${phone}</td>
									</tr>
								</tbody>
								<tbody>
								</tbody>
							</table>
							</td>
						</tr>
					</tbody>
				</table>
			</s:if>
			<s:else>
			<div class="bt">会员信息</div>
				<table width="100%" cellspacing="0" cellpadding="5" border="0"
					align="center" style="border: 1px solid rgb(224, 224, 224);">
					<tbody>
						<tr bgcolor="#ffffff">
							<td nowrap="nowrap" class="C" colspan="3">
							<table width="100%" cellspacing="0" cellpadding="4" border="0">
								<tbody>
									<tr bgcolor="#fbfbfb">
										<td width="35%" bgcolor="#ffffff"><b>您的姓名：</b> <s:property value="user().name"/> </td>
										<td width="65%" valign="top" bgcolor="#ffffff"><b>公司名称：</b>
										<s:property value="ent().name"/></td>
									</tr>
									<tr bgcolor="#fbfbfb">
										<td bgcolor="#ffffff"><b>电子邮箱：</b> <s:property value="user().email"/></td>
										<td bgcolor="#ffffff"><b>联系电话：</b> <s:property value="user().phone"/></td>
									</tr>
								</tbody>
								<tbody>
								</tbody>
							</table>
							</td>
						</tr>
					</tbody>
				</table>
			</s:else>
			
			</td>
		</tr>
	</tbody>
</table>
</body>
</html>