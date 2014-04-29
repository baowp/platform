<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta   http-equiv= "pragma "   content= "no-cache ">
<meta   http-equiv= "Cache-Control "   content= "no-cache,must-revalidate "> 
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript">
	function setState(obj) {
		var str = "";
		var unCheck = "";
		if ($(":checkbox:checked").length > 10) {
			alert('首页栏目最多可以选中10项')
			return;
		}
		$(":checkbox").each(function(i) {

			if ($(this).attr("checked")) {
				str += $(this).val() + ",";
			} else {
				unCheck += $(this).val() + ",";
			}
		});
		$.getJSON("menuhomeEdit?randomNumber=" + Math.random(), {
			menuIds : str,
			unCheckId : unCheck
		}, function(result) {
			art.dialog.tips('修改成功！', 1);
			art.dialog.parent.location.reload();
			art.dialog.close(); 
			return false
		});
	}
	$("document").ready(function() {
		$("#check").click(function() {
			$("[name='ch']").attr("checked", 'true');//全选		       
		});
		$("#check2").click(function() {
			$("[name='ch']").removeAttr("checked");//取消全选
		})
	});
</script>
</head>
<body>
<center>
<h2>首页定制</h2>
</center>
<div style="background: #FFEBCD;"><input type="button" id="check"
	name="check" value="全选" />&nbsp<input type="button" id="check2"
	name="check2" value="取消全选" />&nbsp<input type="button"
	onclick="setState(this)"
	userId='<s:property value="%{#parameters['index']}"/>' value="提交" /></div>
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
			<s:iterator var="user" value="#request.menuList" status="st">
				<li style="width: 250px; float: left">
				<table width="100%">
					<tr>
						<td><font color="red">${moduleName}</font></td>
						<td align="left"><label
							for="<s:property value='#st.index+1'/>">${mname}</label></td>
						<td align="right"><input type="checkbox" value="${menuId}"
							id="<s:property value='#st.index+1'/>"
							<s:property value="isCheck(#parameters['index'],menuId)=='01'?'checked=\"true\"':''"/>} name="ch" /></td>
					</tr>
				</table>
				</li>
			</s:iterator>
		</ul>
		</td>
	</tr>
</table>
</body>
</html>