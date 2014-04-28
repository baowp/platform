<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>用户审核</title>
<link rel="stylesheet" href="<s:url value="/admin/css/common.css"/>"
	type="text/css" />
<link rel="stylesheet" href="<s:url value="/admin/css/tinybox.css"/>"
	type="text/css" />
	<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<script type="text/javascript">
		$(document).ready(function() {
			$("#submitform").click(function(){
				document.form1.action="saveUpdateAudit";
				document.form1.submit();
			});
			$("#checkall").click(function() { 
				   $("input:checkbox[id='auditIds-1']").attr('checked', true);
				}); 
				
				$("#uncheckall").click(function() { 
				    $("input:checkbox[id='auditIds-1']").attr('checked', false);
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
<s:form namespace="/admin" action="saveUpdateAudit" method="post"
	id="form1" name="form1">
	<s:hidden name="viewInfo" value="%{#request.viewInfo}"></s:hidden>
	<div id="nav">
	<ul>
		<li id="man_nav_1" class="bg_image_onclick">用户资料审核</li>
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
				<th class="header" name="enterprise">企业名称</th>
				<th class="header" name="addTime">修改时间</th>
				<th class="header" name="name">修改详细</th>
				<th class="header">操作</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator  value="pageList.items">
				<tr>
					<td><s:property value="getEnterprise(enterpriseId).name"/></td>
					<td><s:date name="addTime" format="yy-MM-dd mm:ss" /> </td>
					<td><a href="${viewInfo}?id=${auditId}">查看</a></td>
					<td>
					<s:if test="state=='00'">
					<s:checkboxlist name="auditIds" list="%{#{auditId+';01':'审核通过',auditId+';02':'审核不通过'}}"></s:checkboxlist>
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
			<tr>
				<th colspan="7" class="tb_search">
				<button class="checkall" id="checkall" type="button">全选</button>
				<button class="uncheckall" id="uncheckall" type="button">反选</button>
				
				<button type="button" id="submitform" class="submit">提交审核</button>
				</th>
			</tr>
		</tfoot>
	</table>
	</div>
	</div>
</s:form>
<div id="dialog" style="display:none;">
<div id="desc"></div>
</div>
</body>
</html>
