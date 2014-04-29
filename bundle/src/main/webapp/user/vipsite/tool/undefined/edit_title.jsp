<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/js/util/onload_colorbox.jsp"/>
<!-- 修改栏目标题 隐藏div -->
<div id="hid_div" style="display: none" class="dolog">
	<table>
		<tr><td>标题名称: </td>
			<td><input id="hid_titleName" type="text">
				<input type="checkbox" checked="checked" id="chk_title" name="chk_title"/><label for="chk_title">显示标题</label>
			 </td>
		</tr>
		<tr><td>标题背景: </td>
			<td><input id="picPath" readonly="readonly" type="text">
			<a class="various3" href="<s:url value="/user/album/albumshowUploadPage"/>">选择相片</a>
			<a href="javascript:void(0);" onclick="clearPic();">清除</a>
			</td>
		</tr>
	</table>
</div>