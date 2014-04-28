<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>用户管理</title>
<link rel="stylesheet" href="<s:url value="/admin/css/common.css"/>"
	type="text/css" />
<link rel="stylesheet" href="<s:url value="/admin/css/tinybox.css"/>"
	type="text/css" />
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/cvi/busy.js"/>"></script>
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
			
			$(":checkbox").click(function(){
				$.getJSON("/admin/setBroadcastMember?randomNumber="
						+ Math.random(), {
					id : $(this).parents("tr[userId]").attr("userId"),
					broadcast:$(this).attr("name")
				}, function(result) {
					alert("设置成功!");
				});
			})
		});
		function goto(page){
			$("#page").val(page);
			document.form1.submit();
		}
		function suggestOver(div_value){      
		    div_value.className="suggest_link_over";      
		}      
		//mouse out function      
		function suggestOut(div_value){      
		    div_value.className="suggest_link";      
		}     
		function setSearchEntname(id,value){     
			$("#userId").val(id);
			$("#username").val(value);
			$("#suggestUserName").css("display","none");    
		} 
		function reset(){
			$("#userId").val("");
			$("#username").val("");
		} 
		function showAudit(obj){
			var $row=$(obj).parents("tr[userId]");
			$("#payUserId").attr("value",$row.attr('userId'));
			$("#endTime").html($row.attr('endTime'));
			$("#payType").html($row.attr('grade'));
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
		function clearUserName(){
			$("#username").val("");
		}
</script>
<script type="text/javascript">
function setState(obj) {
	$.getJSON("/user/account/password/updateSubState?randomNumber="+Math.random(), {
		userId:obj.getAttribute('userId'),
		state:obj.getAttribute("state")
	}, function(result) {
		if(result.affectRows==1){
			if(obj.getAttribute("state")=='01'){
				obj.setAttribute("state",'00');
				obj.innerHTML="正常";
			}else{
				obj.setAttribute("state",'01');
				obj.innerHTML="停用";
			}
		}
	});
}
</script>
</head>
<body>

<s:form namespace="/admin" action="viewMember" method="post" id="form1">
	<div id="nav">
	<ul>
		<li id="man_nav_1" class="bg_image_onclick">会员查询</li>

		<li><s:textfield  id="entName" name="entName"
			label="企业名" theme="xhtml" /> <s:textfield name="username"
			id="username" label="用户名" theme="xhtml" /> 类型：<s:select
			name="memberGrade"
			list="%{#{'10':'全部','00':'非付费','01':'企业版会员','02':'集团版会员','03':'行业版会员'}}"
			value="memberGrade" /> <s:submit value="查询" cssClass="search" /></li>
	</ul>
	</div>
	<div id="sub_info">
	<div id="man_zone"><s:if test="result!=''&&result!=null">
		<div id="warning">${result}</div>
	</s:if>
	<table width="99%" border="0" align="center" cellpadding="3"
		cellspacing="1" id="tablesorter">
		<s:hidden name="page" id="page" />
		<thead>
			<tr>
				<th class="header" name="enterprise">企业名称</th>
				<th class="header" name="state">账号状态</th>
				<th class="header" name="type">账号类型</th>
				<th class="header" name="grade">付费等级</th>
				<th class="header" name="addTime">注册时间</th>
				<th class="header">审核/推荐</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator var="user" value="pageList.items">
				<tr userId=${userId} endTime="<s:property value="endTime()"/>" grade="<s:property value="gradeName()"/>" >
					<td><a href="infoMember?id=${userId}"><s:property
						value="enterprise().name" />(${username })</a></td>
					<td><a href="javascript:void(0)" onclick=setState(this) title="<s:if test="%{state=='00'}">点击可以激活账号</s:if><s:else>点击可以停用账号</s:else>" userId=${userId} state=${state eq '01'?'00':'01'}>${state eq '01'?'正常':'停用' }</a></td>
					<td><s:property value="typeName()" /></td>
					<td><s:property value="gradeName()" /></td>
					<td><s:property value="addTimeString()" /></td>
					<td><s:if test="%{state!='00'}"><a href="#" onclick="showAudit(this)">付费审核</a>/<input
						type="checkbox" name="<s:property value="isBroadcast()"/>"
						<s:if test="isBroadcast()=='01'">checked="checked"</s:if> /></s:if>/<a href="/admin/delMember?id=${userId}" onclick="if(confirm('确定删除用户?')==true){return true;} else {return false;}">删除用户</a></td>
				</tr>
			</s:iterator>

		</tbody>
		<tfoot>
			<tr>
				<th colspan="10" class="tb_search"><s:include
					value="../../common/page.jsp">
					<s:param name="urlArgs" value="'state='+state+'&'" />
				</s:include></th>
			</tr>
			<tr>
				<th colspan="10" class="tb_search">查询：<s:textfield
					id="searchWord" name="searchWord" />
				<button type="button" id="filter" class="search">提交</button>
				<button type="button" class="refresh"
					onclick="window.location.reload()">刷新</button>
				</th>
			</tr>
		</tfoot>
	</table>
	</div>
	</div>
</s:form>
<s:form namespace="/admin" action="passMemberAudit" id="dialog"
	cssStyle="display:none;" method="get" onsubmit="return checkValue()">
	<s:hidden name="userId" id="payUserId" value="" />
	<s:hidden name="pageType" value="viewMember" />
	<s:hidden name="payway" value="02" />
	<s:hidden name="page" value="%{page}" />
	<table>
	<tr>
	        <td><span style="color:#E9821A">当前用户类型：</span></td>
			<td id="payType"></td>
			<td><span style="color:#E9821A">服务时间：</span></td>
			<td id="endTime"><span style="color:#E9821A"></span></td>
		</tr>
		<tr>
			<td><s:select list="#{'01':'开通企业版','03':'开通集团版','02':'经销商权限'}" label="付款类型"
				name="paytype" listKey="key" listValue="value" theme="xhtml" /></td>
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
