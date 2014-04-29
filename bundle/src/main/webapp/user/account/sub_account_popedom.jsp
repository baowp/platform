<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript"><!--
function setState(obj) {
	var str="";
	var unCheck="";

     $(":checkbox").each(function(i){

    if($(this).attr("checked")) {
    	str+=$(this).val()+",";
   		}
    else{
    	unCheck+=$(this).val()+",";
     }
	});
	$.getJSON("subAccountPopedomState?randomNumber="+Math.random(), {
		userId:obj.getAttribute('userId'),
		state:01,
		userprivilegeIds:str,
		unCheckId:unCheck
	}, function(result) {
		if(result.affectRows==1){
			alert("修改成功！");
		}
	});
}
$("document").ready(function(){
	$("#check").click(function(){
	$("[name='ch']").attr("checked",'true');//全选		       
	});
	$("#check2").click(function(){
	$("[name='ch']").removeAttr("checked");//取消全选
	})	
});
--></script>
</head>
<body>
<center>
<h2>权限操作</h2>
</center>
<div style="background: #FFEBCD;"><a id="check" name="check"
	href="#">全选</a>&nbsp<a id="check2" name="check2" href="#">取消全选</a></div>
<table width="100%">
	<tr>
		<td>
		<ul style="width: 100%; list-style-type: none;">
			<li style="width: 250px; float: left">
			<table width="100%">
				<tr>
					<td>所属模块</td>
					<td align="left">名称</td>
					<td align="right">操作</td>
				</tr>
			</table>
			</li>
			<li style="width: 250px; float: left">
			<table width="100%">
				<tr>
					<td>所属模块</td>
					<td align="left">名称</td>
					<td align="right">操作</td>
				</tr>
			</table>
			</li>
			<s:iterator var="user" value="pageList.items" status="st">
				<li style="width: 250px; float: left">
				<table width="100%">
					<tr>
						<td><font color="red"><s:property
							value="moudelName(moduleId)" /></font></td>
						<td align="left"><label
							for="<s:property value='#st.index+1'/>">${pname}</label></td>
						<td align="right"><input type="checkbox"
							value="${userprivilegeId}" id="<s:property value='#st.index+1'/>"
							<s:property value="isCheck(#parameters['index'],userprivilegeId)=='01'?'checked=\"true\"':''"/>} name="ch" /></td>
					</tr>
				</table>
				</li>
			</s:iterator>
		</ul>
		</td>
	</tr>
	<tr>
		<td>
		<div style="background: #FFEBCD;" align="center"><a href="#"
			onclick="setState(this)"
			userId='<s:property value="%{#parameters['index']}"/>'>
		<h2>确认提交</h2>
		</a></div>
		</td>
	</tr>
</table>
</body>
</html>