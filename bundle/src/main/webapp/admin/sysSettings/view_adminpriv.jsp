<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>查看管理员权限</title>
<link rel="stylesheet" href="<s:url value="/admin/css/common.css"/>"
	type="text/css" />
<link rel="stylesheet" href="<s:url value="/admin/css/tinybox.css"/>"
	type="text/css" />
	<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<script type="text/javascript">
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
	$.getJSON("editAdminpriv?randomNumber="+Math.random(), {
		adminId:obj.getAttribute('userId'),
		state:1,
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
</script>
</head>
<body>
<div id="nav">
<ul>
	<li id="man_nav_1" class="bg_image_onclick">修改权限</li>
</ul>
</div>
<div id="sub_info">
<div id="man_zone"><s:if test="result!=''&&result!=null">
	<div id="warning">${result}</div>
</s:if>
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
							value="${adminprivilegeId}" id="<s:property value='#st.index+1'/>"
							<s:property value="isCheck(adminId,adminprivilegeId)=='01'?'checked=\"true\"':''"/>} name="ch" /></td>
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
			userId='${adminId}'>
		<h2>确认提交</h2>
		</a></div>
		</td>
	</tr>
</table>
</body>
</html>
