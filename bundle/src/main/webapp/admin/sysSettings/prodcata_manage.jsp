<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>产品分类管理</title>
<link rel="stylesheet" href="<s:url value="/admin/css/common.css"/>"
	type="text/css" />
<link rel="stylesheet" href="<s:url value="/admin/css/tinybox.css"/>"
	type="text/css" />
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript">
var selectId="";
var tdId="";
function getSubprodcata(id,id2){
	selectId=id;
	tdId =id2;
	var params ={id:id};
	$.ajax({url : "subProdcata",type:"post",dataType:"json",data:params,success:parseSub});
}
function parseSub(result) { 
	var json = eval( "("+result+")" );
	var sId = "#select"+selectId;
	var tId = "#td"+tdId;
	$(sId).css("background","#E6EEEE");
	$(tId).css("background","#E6EEEE");
	var html="类别名称：<input name=\"name"+selectId+"\" value=\"\"><input type=\"button\" onclick=\"addSub()\" value=\"添加\"/>";
	$(tId).html(html);
}
function addSub(){
	var nameId = "name"+selectId;
	alert(nameId);
	alert(document.getElementById(nameId).value);
}
</script>
</head>
<body>
<div id="nav">
<ul>
	<li id="man_nav_1" class="bg_image_onclick">产品类别管理</li>
</ul>
</div>
<div id="sub_info">
<div id="man_zone"><s:if test="result!=''&&result!=null">
	<div id="warning">${result}</div>
</s:if>
<div id="list">
<table width="99%" border="0" align="center" cellpadding="3"
	cellspacing="1" class="table_style">
	<thead>
		<tr>
			<th class="left_title_1" name="name" width="33%">行业</th>
			<th class="left_title_1" name="name" width="33%">产品大类</th>
			<th class="left_title_1" name="name" width="33%">产品子类</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator var="industrys" value="industrys">
			<tr>
				<td width="20%"><s:property value="name" />(可添加多个，多个用,隔开)</td>
				<td width="20%"><s:form namespace="/admin" action="addProdcata"
					method="post">
					<s:hidden name="fatherdict" value="%{#industrys.getSyscodeId()}" />
					<s:hidden name="type" value="30" />
					<s:hidden name="state" value="01" />
					<s:iterator var="prodcata" value="subSyscodes()">
						<li class="prodcata" id="select<s:property value="syscodeId" />"><s:property
							value="name" /><img style="cursor: pointer" alt="删除" title="删除"
							onclick="if(confirm('确定要删除？'))window.location='deleteProdcata?id=<s:property value="syscodeId"/>'"
							src="<s:url value="/images/cancel.png"/>" /></li>
					</s:iterator>
					<li class="prodcata">分类名称：<s:textfield name="name" value="" /><s:submit
						value="添加" /></li>
				</s:form></td>
				<td width="20%">
				<s:iterator var="prodcata" value="subSyscodes()">
				<s:form namespace="/admin" action="addProdcata"
					method="post">
					<div id="sub<s:property value="syscodeId" />" class="subprodcata">
					<li class="prodcata" ><s:property value="name" />的子类：</li>
					<s:iterator var="subprodcata" value="subSyscodes()">
					<li class="prodcata" ><s:property
							value="name" /><img style="cursor: pointer" alt="删除" title="删除"
							onclick="if(confirm('确定要删除？'))window.location='deleteProdcata?id=<s:property value="syscodeId"/>'"
							src="<s:url value="/images/cancel.png"/>" /></li>
					</s:iterator>
					<s:hidden name="type" value="30"/>
					<s:hidden name="state" value="01"/>
					<s:hidden name="fatherdict" value="%{#prodcata.syscodeId}"/>
					<li class="prodcata" >类别名称<s:textfield name="name" value=""></s:textfield><s:submit value="添加"/></li>
					</div>
					</s:form>
				</s:iterator>
				</td>
				
			</tr>
		</s:iterator>
	</tbody>
	<tfoot>
		<tr>
			<th colspan="3" class="left_title_1"></th>
		</tr>
	</tfoot>
</table>
</div>
</div>
</body>
</html>
