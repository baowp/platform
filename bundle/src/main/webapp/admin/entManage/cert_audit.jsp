<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>证书审核</title>
<link rel="stylesheet" href="<s:url value="/admin/css/common.css"/>"
	type="text/css" />
<link rel="stylesheet" href="<s:url value="/admin/css/tinybox.css"/>"
	type="text/css" />
	<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>

<script type="text/javascript">
		$(document).ready(function() {
			$("#submitform").click(function(){
				document.form1.action="editAuditCert";
				document.form1.submit();
			});
			$("#checkall").click(function() { 
				   $("input:checkbox[id='certIds-1']").attr('checked', true);
				}); 
				
				$("#uncheckall").click(function() { 
				    $("input:checkbox[id='certIds-1']").attr('checked', false);
				});
		});
		function goto(page){
			$("#page").val(page);
			document.form1.submit();
		}
		function sortEnt(id){
			$("#entId").val(id);
			document.form1.submit();
		}
		
</script>
</head>
<body>
<s:form namespace="/admin" action="viewAuditCert" method="post"
	id="form1" name="form1">
	<div id="nav">
	<ul>
		<li id="man_nav_1" class="bg_image_onclick">证书审核</li>
		<li><s:radio name="certState"
			list="%{#{'00':'未审核','01':'已审核','02':'审核不通过'}}" value="certState" /> <s:submit
			value="确定" /></li>
	</ul>
	</div>
	<div id="sub_info">
	<div id="man_zone"><s:if test="result!=''&&result!=null">
		<div id="warning">${result}</div>
	</s:if>
	<table width="99%" border="0" align="center" cellpadding="3"
		cellspacing="1" id="tablesorter">
		<s:hidden name="page" id="page" />
		<s:hidden name="entId" id="entId" />
		<thead>
			<tr>
				<th class="header" name="enterprise">企业</th>
				<th class="header" name="addTime">添加时间</th>
				<th class="header" name="name">证书名称</th>
				<th class="header" name="type">证书类型</th>
				<th class="header" name="organize">证书颁发机构</th>
				<th class="header" name="pic">证书图片</th>
				<th class="header">操作</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator var="cert" value="pageList.items">
				<tr>
					<td><a href="javascript:sortEnt('<s:property value="enterpriseId" />')"><s:property value="enterprise().name" /></a></td>
					<td><s:property value="addTimeString" /></td>
					<td><s:property value="name" /></td>
					<td><s:property value="type" /></td>
					<td><s:property value="organize" /></td>
					<td><a href="<s:property value="picUrl()" />" target="_blank">查看图片<a></td>
					<td>
					<s:if test="state=='00'">
					<s:checkboxlist name="certIds" list="%{#{certificateId+';01':'审核通过',certificateId+';02':'审核不通过'}}"></s:checkboxlist>
					</s:if>
					</td>
				</tr>
			</s:iterator>

		</tbody>
		<tfoot>
			<tr>
				<th colspan="10" class="tb_search"><s:include
					value="../../common/page.jsp">
					<s:param name="pageList" value="pageList" />
				</s:include></th>
			</tr>
			<s:if test="certState=='00'">
			<tr>
				<th colspan="7" class="tb_search">
				<button class="checkall" id="checkall" type="button">全选</button>
				<button class="uncheckall" id="uncheckall" type="button">反选</button>
				
				<button type="button" id="submitform" class="submit">提交审核</button>
				</th>
			</tr>
			</s:if>
		</tfoot>
	</table>
	</div>
	</div>
</s:form>
</body>
</html>
