<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>会员付费到期</title>
<link rel="stylesheet" href="<s:url value="/admin/css/common.css"/>"
	type="text/css" />
<link rel="stylesheet" href="<s:url value="/admin/css/tinybox.css"/>"
	type="text/css" />
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<link type="text/css" href="<s:url value="/css/jquery-ui.css"/>"
	rel="stylesheet" />
<script type="text/javascript" src="<s:url value="/js/jquery-ui.js"/>"></script>
<script type="text/javascript"
	src="<s:url value='/js/datePicker/WdatePicker.js'/>"></script>
<script type="text/javascript">
		$(document).ready(function() {
			$("#filter").click(function() {
				window.location="searchMember?searchWord="+$("#searchWord").val();
			});
		});
		function changePayendType(){
			var payendType = $("input[name='payendType']:checked").val();
			if(payendType=="0")
			$("#custom").css("display","block");
			else
			$("#custom").css("display","none");
			
		}
		function goto(page){
		$("#page").val(page);
			document.form1.submit();
		}
		
		function showAudit(obj){
			$("#payUserId").attr("value",obj);
			$("#dialog").dialog('open');
			$("#dialog").dialog({
				title:"审核管理",
				width:480
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
<style>
.isExpired td{
	background-color: #DCDCDC;
}
.isExpired7 td{
	background-color: #5F9EA0;

}
.isExpired30 td{

}
</style>
</head>
<body onload="changePayendType()">
<s:form namespace="/admin" action="payEnd" method="post" id="form1">
	<s:hidden name="page" id="page" />
	<div id="nav">
	<ul>
		<li id="man_nav_1" class="bg_image_onclick">付费到期</li>
		<li>到期时间：<s:radio name="payendType"
			list="%{#{'1':'一周','2':'30天','3':'90天','0':'自定义'}}"
			value="payendType" onclick="changePayendType()" /> <span id="custom"
			style="display: none"><input type="text" name="payendStart"
			class="Wdate" size="20" value="<s:property value="payendStart"/>"
			onfocus="WdatePicker()" /><span class="errorSpan">${errors.payendStart[0]}</span>至
		<input type="text" name="payendEnd" class="Wdate" size="20"
			value="<s:property value="payendEnd"/>" onfocus="WdatePicker()" /><span
			class="errorSpan">${errors.payendEnd[0]}</span></span><s:textfield
			name="username" label="用户名" theme="xhtml"></s:textfield> <s:submit
			value="确定" /></li>
	</ul>
	</div>
	<div id="sub_info">
	<div id="man_zone"><s:if test="result!=''&&result!=null">
		<div id="warning">${result}</div>
	</s:if>
	<table width="99%" border="0" align="center" cellpadding="3"
		cellspacing="1" id="tablesorter">
		<thead>
			<tr>
				<th class="header" name="endTime">到期日期</th>
				<th class="header" name="startTime">开始日期</th>
				<th class="header" name="payTime">企业名称</th>
				<th class="header" name="username">账号</th>
				<th class="header" name="name">联系人</th>
				<th class="header" name="cellphone">手机</th>
				<th class="header">操作</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator var="user" value="sublist">
				<tr  class="${isExpired=='-1'?'isExpired':isExpired=='7'?'isExpired7':'isExpired30'}">
					<td>${endTime}</td>
					<td><s:property value="payStart()" /></td>
					<td><s:property value="ent().name" /></td>
					<td><s:property value="user().username" /></td>
					<td><s:property value="user().name" /></td>
					<td><s:property value="user().cellphone" /></td>
					<td><a href="/admin/viewSendPayendRemind?userId=${userId}">发送提醒邮件</a> <s:if test="%{isExpired=='-1'}">/<a href="#" onclick="showAudit('${userId}')">付费审核</a>/<a href="/admin/delPayend?userId=${userId}&&payendType=${payendType}" onclick="if(confirm('确定还原到免费用户?')==true){return true;} else {return false;}">关闭旺铺</a></s:if> 
					</td>
				</tr>
			</s:iterator>

		</tbody>
		<tfoot>
			<tr>
				<th colspan="8" class="tb_search"><s:include
					value="/common/ListPage.jsp">
					<s:param name="urlArgs" value="'payendType='+payendType+'&'"></s:param>
				</s:include></th>
			</tr>
		</tfoot>
	</table>
	</div>
	</div>
</s:form>

<s:form namespace="/admin" action="passMember" id="dialog"
	cssStyle="display:none;" method="get" onsubmit="return checkValue()">
	<s:hidden name="userId" id="payUserId" value="" />
	<s:hidden name="pageType" value="payend" />
	<table>
		<tr>
			<td><s:select list="#{'01':'开通旺铺','02':'经销商权限'}" label="付款类型"
				name="payType" listKey="key" listValue="value" theme="xhtml" /></td>
		</tr>
		<tr>
			<td><s:select list="#{'01':'银行汇款','02':'在线支付','03':'现金支付'}"
				label="付款方式" name="payway" listKey="key" listValue="value"
				theme="xhtml" /></td>
		</tr>
		<tr>
			<td><s:textfield name="sum" id="sum" label="付款总额" theme="xhtml"
				onkeyup="this.value=this.value.replace(/\D/g,'')"
				onafterpaste="this.value=this.value.replace(/\D/g,'')" /></td>
		</tr>
		<tr>
			<td><s:textfield name="payTime" id="payTime" size="12"
				cssStyle="margin-bottom:5px" onfocus="WdatePicker()"
				cssClass="Wdate" label="付费日期" theme="xhtml" /></td>
		</tr>
		<tr>
			<td>服务时间:
			<td><s:textfield name="frontTime" id="frontTime" size="12"
				cssStyle="margin-bottom:5px" onfocus="WdatePicker()"
				cssClass="Wdate" /> - <s:textfield name="backTime" id="backTime"
				size="12" onfocus="WdatePicker()" cssClass="Wdate" /></td>
		</tr>
		<tr>
			<td><s:textarea label="审批意见" theme="xhtml" cols="20"
				name="applyContent"></s:textarea></td>
		</tr>
		<tr>
			<td><s:submit value="提交" /></td>
		</tr>
	</table>
</s:form>
</body>
</html>
