<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="left_top">
<div class="lt" id="cptop1">精品</div>
<div class="nt" id="cptop2">新品</div>
<div class="nt" id="cptop3">热门</div>
</div>
<s:action name="product" namespace="/ranking"></s:action>
<div class="cptop1">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<s:iterator value="#request['jpList']" status="jp">
		<s:if test="%{#jp.index<10}">
			<tr onmouseOver="this.style.backgroundColor='#F2F8FD';"
				onmouseout="this.style.backgroundColor='white';">
				<td width="36" height="28" align="center" valign="middle"><img
					src="/ranking/images/${jp.index+1}.jpg" width="15" height="16" /></td>
				<td height="28"><a
					href="<s:url value="/"/><s:property value='productPath(enterpriseId,productId)'/>"
					target="_blank">${name}</a></td>
				<td height="28" align="center">${viewsum}</td>
			</tr>
			<s:if test="%{#jp.index==0}">
				<tr onmouseOver="this.style.backgroundColor='#F2F8FD';"
					onmouseout="this.style.backgroundColor='white';">
					<td width="36" height="14" align="center" valign="middle">&nbsp;</td>
					<td height="14">
					<div class="v"><img
						src="<s:property value="productPic(productId)" />" width="80"
						height="83" /></div>
					</td>
					<td height="14">&nbsp;</td>
				</tr>
			</s:if>
		</s:if>
	</s:iterator>
</table>
</div>
<div class="cptop2">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<s:iterator value="#request['nwList']" status="jp">
		<s:if test="%{#jp.index<10}">
			<tr onmouseOver="this.style.backgroundColor='#F2F8FD';"
				onmouseout="this.style.backgroundColor='white';">
				<td width="36" height="28" align="center" valign="middle"><img
					src="/ranking/images/${jp.index+1}.jpg" width="15" height="16" /></td>
				<td height="28"><a
					href="<s:url value="/"/><s:property value='productPath(enterpriseId,productId)'/>"
					target="_blank">${name}</a></td>
				<td height="28" align="center">${viewsum}</td>
			</tr>
			<s:if test="%{#jp.index==0}">
				<tr onmouseOver="this.style.backgroundColor='#F2F8FD';"
					onmouseout="this.style.backgroundColor='white';">
					<td width="36" height="14" align="center" valign="middle">&nbsp;</td>
					<td height="14">
					<div class="v"><img
						src="<s:property value="productPic(productId)" />" width="80"
						height="83" /></div>
					</td>
					<td height="14">&nbsp;</td>
				</tr>
			</s:if>
		</s:if>
	</s:iterator>
</table>
</div>
<div class="cptop3">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<s:iterator value="#request['rmList']" status="jp">
		<s:if test="%{#jp.index<10}">
			<tr onmouseOver="this.style.backgroundColor='#F2F8FD';"
				onmouseout="this.style.backgroundColor='white';">
				<td width="36" height="28" align="center" valign="middle"><img
					src="/ranking/images/${jp.index+1}.jpg" width="15" height="16" /></td>
				<td height="28"><a
					href="<s:url value="/"/><s:property value='productPath(enterpriseId,productId)'/>"
					target="_blank">${name}</a></td>
				<td height="28" align="center">${viewsum}</td>
			</tr>
			<s:if test="%{#jp.index==0}">
				<tr onmouseOver="this.style.backgroundColor='#F2F8FD';"
					onmouseout="this.style.backgroundColor='white';">
					<td width="36" height="14" align="center" valign="middle">&nbsp;</td>
					<td height="14">
					<div class="v"><img
						src="<s:property value="productPic(productId)" />" width="80"
						height="83" /></div>
					</td>
					<td height="14">&nbsp;</td>
				</tr>
			</s:if>
		</s:if>
	</s:iterator>
</table>
</div>