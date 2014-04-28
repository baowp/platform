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
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript">
		$(document).ready(function() {
			$("#searchWord").change(function() {
				var url = 'searchMembergroup';
                url = url+"?searchName="+$('#searchWord').attr('value');
                //alert(url);
               $.ajax({url:url,dataType : "json",success : callbackFun});
			}); 
			
		});
		function callbackFun(data)
        {
            var userlist = eval('(' +data+ ')');
            for(var i=0;i<userlist.length;i++){
            var user = userlist[i];
            $("#tablebody").html("");
            $("#tablebody").append("<tr><td >"+user.username+"</td><td >"+user.name+"</td><td>"+user.email+"</td><td><input type='checkbox' name='userIds' value='"+user.userId+"'/></td></tr>");   
     		}
        }
        function goto(page){
		$("#page").val(page);
			document.form1.action="listMembergroup";
			document.form1.submit();
		}
        function edsub(){
        	var checkboxs="";
			var value= document.getElementsByName('ch');
		    for(var i=0;i<value.length;i++){
				if(value[i].checked==true){
					checkboxs=checkboxs+"ch="+value[i].defaultValue+"&"
				}
			}
			if(checkboxs==""){
				alert('您还未选中用户！');
				return;
			}
			$("#userIds").val(checkboxs);
			$("#editForm").submit();
        }
</script>
</head>
<body>
<s:form namespace="/admin" action="editMembergroup" id="editForm">
	<s:hidden name="userIds" id="userIds" />
</s:form>
<s:form namespace="/admin" action="listMembergroup" method="post"
	id="form1" name="form1">
	<s:hidden name="usergroupId" />
	<div id="nav">
	<ul>
		<li id="man_nav_1" class="bg_image_onclick">设置成员</li>
		<li>设置“<s:property value="groupname" />”的成员</li>
	</ul>
	</div>
	<div id="sub_info">
	<div id="man_zone"><s:if test="result!=''&&result!=null">
		<div id="warning">${result}</div>
	</s:if>
	<div id="data">
	<table width="99%" border="0" align="center" cellpadding="3"
		cellspacing="1" id="tablesorter">
		<thead>
			<tr>
				<th class="header" name="username">用户名</th>
				<th class="header" name="name">姓名</th>
				<th class="header" name="email">email</th>
				<th class="header">选择</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="pageList.items">
				<tr>
					<td><s:property value="user().username" /></td>
					<td><s:property value="user().name" /></td>
					<td><s:property value="user().email" /></td>
					<td><s:checkbox name="userIds" fieldValue="%{user().userId}"></s:checkbox>
					</td>
				</tr>
			</s:iterator>

		</tbody>
		<tfoot>
			<tr>
				<th colspan="4" class="tb_search"><s:include
					value="../../common/page.jsp">
					<s:param name="pageList" value="pageList" />
				</s:include></th>
			</tr>
		</tfoot>
	</table>
	</div>
	<div id="edit">
	<table width="99%" border="0" align="center" cellpadding="3"
		cellspacing="1" id="tablesorter">
		<thead>
			<tr>
				<th class="header" colspan="4">过滤：<s:textfield id="searchWord" /></th>
			</tr>
			<tr>
				<th class="header" name="username">用户名</th>
				<th class="header" name="name">姓名</th>
				<th class="header" name="email">email</th>
				<th class="header">选择</th>
			</tr>
		</thead>
		<s:action name="allUserMembergroup" namespace="/admin"></s:action>
		<tbody id="tablebody">
			<s:iterator value="%{#request.pageList}" status="st">
				<tr>
					<th class="header" name="username">${username }</th>
					<th class="header" name="name">${name }</th>
					<th class="header" name="email">${email }</th>
					<th class="header"><s:checkbox fieldValue="%{userId}"
						name="ch" id="ch" /></th>
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
				<th colspan="4" class="tb_search">
				<button id="editsubmit" class="submit" onclick="edsub()">提交</button>
				</th>
			</tr>
		</tfoot>
	</table>
	</div>
	</div>
	</div>
</s:form>
</body>
</html>
