<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="left_top">
<div class="lt" id="gstop1">活跃度</div>
<div class="nt" id="gstop2">产品最多</div>
</div>
<s:action name="ent" namespace="/ranking"></s:action>
<div class="gstop1">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<s:iterator value="#request['enthydList']" status="jp">
		<s:if test="%{#jp.index<10}">
			<tr onmouseOver="this.style.backgroundColor='#F2F8FD';"
				onmouseout="this.style.backgroundColor='white';">
				<td width="36" height="28" align="center" valign="middle"><img
					src="/ranking/images/${jp.index+1}.jpg" width="15" height="16" /></td>
				<td height="28"><a
					href="<s:url value="/"/><s:property value='enterprisePath(enterpriseId)'/>"
					target="_blank"><s:property value="entName(enterpriseId)" /></a></td>
				<td height="28" align="center"></td>
			</tr>
			<s:if test="%{#jp.index==0}">
				<tr onmouseOver="this.style.backgroundColor='#F2F8FD';"
					onmouseout="this.style.backgroundColor='white';">
					<td width="36" height="14" align="center" valign="middle">&nbsp;</td>
					<td height="14">
					<div class="v"><img src="<s:property value="entLogo(enterpriseId)"/> " width="80" height="83" /></div>
					</td>
					<td height="14">&nbsp;</td>
				</tr>
			</s:if>
		</s:if>
	</s:iterator>
</table>
</div>
<div class="gstop2">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<s:iterator value="#request['entcpList']" status="jp">
		<s:if test="%{#jp.index<10}">
			<tr onmouseOver="this.style.backgroundColor='#F2F8FD';"
				onmouseout="this.style.backgroundColor='white';">
				<td width="36" height="28" align="center" valign="middle"><img
					src="/ranking/images/${jp.index+1}.jpg" width="15" height="16" /></td>
				<td height="28"><a
					href="<s:url value="/"/><s:property value='enterprisePath(enterpriseId)'/>"
					target="_blank"><s:property value="entName(enterpriseId)" /></a></td>
				<td height="28" align="center"></td>
			</tr>
			<s:if test="%{#jp.index==0}">
				<tr onmouseOver="this.style.backgroundColor='#F2F8FD';"
					onmouseout="this.style.backgroundColor='white';">
					<td width="36" height="14" align="center" valign="middle">&nbsp;</td>
					<td height="14">
					<div class="v"><img src="<s:property value="entLogo(enterpriseId)"/>" width="80" height="83" /></div>
					</td>
					<td height="14">&nbsp;</td>
				</tr>
			</s:if>
		</s:if>
	</s:iterator>
</table>
</div>
