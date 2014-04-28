<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<link rel="stylesheet" href="<s:url value="/admin/css/common.css"/>"
	type="text/css" />
<link rel="stylesheet" href="<s:url value="/admin/css/tinybox.css"/>"
	type="text/css" />
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script language="javascript">
	function reverLay() {
		if(confirm('操作会删除目标用户的旺铺布局数据，确定吗？')) {
		 	$form = $("#form1");
			action = $form.attr("action").replace(/^(.*\/)\w+(\.action)?$/, '$1copyLay');
			$form.attr("action", action); 
			$("#productBtn").attr("disabled",true);
			$("#layoutBtn").attr("disabled",true);
			$form.submit();
		}
	}
	function reverPro() {
		if(confirm('操作会删除目标用户的分类与产品数据，确定吗？')) {
			$form = $("#form1");
			action = $form.attr("action").replace(/^(.*\/)\w+(\.action)?$/, '$1copyPro');
			$form.attr("action", action);
			$("#productBtn").attr("disabled",true);
			$("#layoutBtn").attr("disabled",true);
			$form.submit();
		}
	}
</script>
</head>
<body>
<s:if test="messageInfo != null && messageInfo != ''">
<div id="warning">
	${messageInfo}
</div>
</s:if>
<div id="data">
	<s:form action="copyLay" method="post" id="form1">
		<table width="95%" border="0" align="left" cellpadding="3"
			cellspacing="1" id="tablesorter">
		<thead>
			<tr>
				<th>中英文数据互导</th>
			</tr>
		</thead>
		<tr>
			<td>来源用户（<s:property value="@com.abbcc.common.ConfConst@OppositeEdition"/>）：<s:textfield name="beginuser"></s:textfield><span class="errorSpan">${errors.beginuser[0]}</span>
				密  码：<s:password name="beginpass"></s:password><span class="errorSpan">${errors.beginpass[0]}</span></td>
		</tr>
		<tr>
			<td>目标用户（当前版）：<s:textfield name="touser"></s:textfield><span class="errorSpan">${errors.touser[0]}</span>
			     密  码：<s:password name="topass"/><span class="errorSpan">${errors.topass[0]}</span></td>
		</tr>
		<tr>
			<td>
				<input id="layoutBtn" type="button" value="导入旺铺布局" onclick="reverLay()">&nbsp;&nbsp;&nbsp;
				<input id="productBtn" type="button" value="导入分类与产品" onclick="reverPro()">&nbsp;&nbsp;&nbsp;
			</td>
		</tr>
		</table>
	</s:form>
</div>
</body>
</html>