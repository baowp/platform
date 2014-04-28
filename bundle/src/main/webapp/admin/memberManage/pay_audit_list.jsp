<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>用户管理</title>
<link rel="stylesheet" href="<s:url value="/admin/css/common.css"/>"
	type="text/css" />
	<link rel="stylesheet" href="<s:url value="/css/jquery-ui.css"/>" type="text/css" />
<link rel="stylesheet" href="<s:url value="/css/jquery/style.css"/>" type="text/css" id="" media="print, projection, screen" />
<link rel="stylesheet" href="<s:url value="/admin/css/tinybox.css"/>"
	type="text/css" />
<script type="text/javascript" src="<s:url value='/js/datePicker/WdatePicker.js'/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery/jquery-latest.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery-ui.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery/jquery.tablesorter.js"/>"></script>
<script type="text/javascript" id="js">
	$(document).ready(function() { 
    $("table").tablesorter({widthFixed: true, widgets: ['zebra']}); 
});
</script>
<script type="text/javascript">
		$(document).ready(function() {
			$("#filter").click(function() {
				window.location="searchMemberAudit?searchWord="+$("#searchWord").val();
			});
		});
		function showAudit(obj){
			var $row=$(obj).parents("tr[payuserId]");
			$("#grade1").attr("value",$row.attr('grade1'));
			$("#payway").attr("value",$row.attr('payway'));
			$("#payuserId").attr("value",$row.attr('payuserId'));
			$("#endTime").html($row.attr('endTime'));
			$("#payType").html($row.attr('grade'));
			$("#paytype").attr("value",$row.attr('paytype'));
			$("#dialog").dialog('open');
			$("#dialog").dialog({
				title:"审核管理",
				width:550
			});
		}
function checkValue(){
			
			if ($("#sum").val()==""){  
				alert("请输入付款金额!");
				return  false;
			}
			if($("#payTime").val()==""){
				alert("请输入付款日期!");
				return  false;
			}
			if($("#frontTime").val()==""){
				alert("请输入开始时间!");
				return  false;
			}
			if($("#backTime").val()==""){
				alert("请输入结束时间!");
				return  false;
			}
				return true;
		}

</script>
</head>
<body>
<div id="nav">
	<ul>
		<li class="bg_image_onclick" id="man_nav_1">会员审核</li>
	</ul>
	</div>
<s:form namespace="/admin" action="viewMember" method="post" id="form1">
	<div id="sub_info">
	<div id="man_zone"><s:if test="result!=''&&result!=null">
		<div id="warning">${result}</div>
	</s:if>
	<table width="99%" border="0" align="center" cellpadding="3"
		cellspacing="1" class="tablesorter">
		<thead>
			<tr>
				<th class="header" name="username">用户名</th>
				<th class="header" name="grade">付费类型</th>
				<th class="header" name="payType">升级/续费</th>
				<th class="header" name="year">申请年数</th>
				<th class="header" name="payDate">申请日期</th>
				<th class="header" name="enterprise">企业名称</th>
				<th class="header" name="cellphone">手机</th>
				<th class="header">操作</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator var="user" value="pageList.items">
				<s:hidden id="garde" name="grade" ></s:hidden>
				<tr payuserId=${payuserId} endTime="<s:property value="endTime()"/>" payway="<s:if test="gradeName().equals(currentGradeName())">01</s:if><s:else>02</s:else>" grade="<s:property value="currentGradeName()"/>" grade1=${grade} paytype=${paytype}>
					<td><s:property value="user().username" /></td>
					<td><s:property value="gradeName()" /></td>
					<td><s:if test="gradeName().equals(currentGradeName())">（续费）</s:if><s:else>（升级）</s:else></td>
					<td><s:property value="yearName()" /></td>
					<td><s:date name="applyTime" format="yyyy-MM-dd hh:mm:ss" /></td>
					<td><s:property value="enterprise().name" /></td>
					<td><s:property value="user().cellphone" /></td>
					<td><s:hidden name="endTime" value="a"/><s:a href="#" onclick="showAudit(this)">付费审核</s:a>/<a href="/admin/delMemberAudit?id=${payuserId}" onclick="if(confirm('确定删除?')==true){return true;} else {return false;}">删除</a></td>
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
				<th style="text-align: left"><img src="images/refresh.png"
					alt="刷新" title="刷新" onclick="window.location.reload()" /></th>
				<th colspan="9" class="tb_search">查询：<s:textfield
					id="searchWord" name="searchWord" />
				<button type="button" id="filter">提交</button>
				</th>
			</tr>
		</tfoot>
	</table>
	</div>
	</div>
</s:form>
<s:form namespace="/admin" action="passMemberAudit"  id="dialog"
	cssStyle="display:none;" method="get" onsubmit="return checkValue()">
	<s:hidden name="id" id="payuserId"/>
	<s:hidden name="grade" id ="grade1"/>
	<s:hidden name="payway" id ="payway"/>
	<s:hidden name="paytype" id ="paytype"/>
	<table>
		<tr>
	        <td><span style="color:#E9821A">当前用户类型：</span></td>
			<td id="payType"></td>
			<td><span style="color:#E9821A">服务时间：</span></td>
			<td id="endTime"><span style="color:#E9821A"></span></td>
		</tr>
		
		<tr><td><s:textfield name="sum" id="sum" label="付款总额" theme="xhtml" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/></td></tr>
		<tr><td><s:textfield name="payTime" id="payTime" size="12" cssStyle="margin-bottom:5px" onfocus="WdatePicker()" cssClass="Wdate" label="付费日期" theme="xhtml"/> </td></tr>
		<tr><td>服务时间:</td><td><s:textfield name="frontTime" id="frontTime" size="12" cssStyle="margin-bottom:5px" onfocus="WdatePicker()" cssClass="Wdate" /> 
			- <s:textfield name="backTime" id="backTime" size="12" onfocus="WdatePicker()" cssClass="Wdate"/></td></tr>
		<tr><td><s:textarea label="审批意见" theme="xhtml" cols="20" name="applyContent"></s:textarea></td></tr>
		<tr><td><s:submit value="提交"/></td></tr>
	</table>
</s:form>
</body>
</html>
