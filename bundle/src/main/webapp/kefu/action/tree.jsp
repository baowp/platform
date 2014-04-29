<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="menu_10001" class="menu">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="14%"><a target="_self" href="javascript:"><img id="treeImg"
			src="images/tree2_1257575066701.png" width="9" height="9" border="0" /></a></td>
		<td width="89%" align="left"><a id="menu10001" class="linkItem"
			href="javascript:" target="_self">全部问题</a></td>
	</tr>
</table>
</div>
<div id="children_10001" class="itembox" style="display: none">
<ul>
<s:iterator value="list">
	<li><span><img src="images/tree3_1257575066703.png"
		width="9" height="9" border="0" /></span><a href="/${staticpath }" target="main">${title}</a></li>
</s:iterator>
</ul>
</div>