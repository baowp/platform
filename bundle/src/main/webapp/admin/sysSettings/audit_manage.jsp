<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>查看管理员</title>
<link rel="stylesheet" href="<s:url value="/admin/css/common.css"/>"
	type="text/css" />
<link rel="stylesheet" href="<s:url value="/admin/css/tinybox.css"/>"
	type="text/css" />
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript">
		var type="";
		var adminId="";
		var state =1;
		function changeActive(id){
			adminId=id;
			var imgId="img"+adminId;
			if($('#'+imgId+'').attr("src")=="images/active.png")
				state=2;
			else
				state=1;
			var url = 'updateAdminState';
			var params = {id:id,state:state};
			$.ajax({url:url,type:"post",data:params,success:callbackFun});
		}
		function callbackFun(data){
			if(state==1){
				var id="img"+adminId;
				$('#'+id+'').attr({src:"images/active.png",alt:"激活",title:"激活"});
			}
			if(state==2){
				var id="img"+adminId;
				$('#'+id+'').attr({src:"images/inactive.png",alt:"注销",title:"注销"});
			}
		}
		$(document).ready(function() {
			$("#filter").click(function() {
				window.location="listAdmin?name="+$("#name").val();
			});
		});
</script>
</head>
<body>
<div id="nav">
<ul>
	<li id="man_nav_1" class="bg_image_onclick">审核管理</li>
</ul>
</div>
<div id="sub_info">
<div id="man_zone">
<s:if test="result!=''&&result!=null">
<div id="warning">
	${result}
</div>
</s:if>
<s:form namespace="/admin" action="editAuditInfo"
	method="post">
<table width="99%" border="0" align="center" cellpadding="3"
	cellspacing="1" id="tablesorter">
	<thead>
		<tr>
			<th class="header" name="name">验证位</th>
			<th class="header" name="sign">标示</th>
			<th class="header" name="state">是否需要验证</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator var="audit" value="auditInfos" status="u">
			<tr>
				<s:hidden name="%{'auditInfos['+#u.index+'].syscodeId'}" value="%{syscodeId}"></s:hidden>
				<s:hidden name="%{'auditInfos['+#u.index+'].type'}" value="%{type}"></s:hidden>
				<td><s:textfield name="%{'auditInfos['+#u.index+'].name'}" value="%{name}" readonly="true"/></td>
				<td><s:textfield name="%{'auditInfos['+#u.index+'].sign'}" value="%{sign}"  readonly="true"/></td>
				<td>
					<s:select name="%{'auditInfos['+#u.index+'].state'}" list="#{'01':'需要验证','00':'不需要验证'}" listKey="key" listValue="value" value="%{state}"/> 
				</td>
			</tr>
		</s:iterator>
		
	</tbody>
	<tfoot>
		<tr>
			<th colspan="3">
			<s:submit cssClass="submit" value="修改"/>
			</th>
		</tr>
	</tfoot>
</table>
</s:form>
</div>
</div>
</body>
</html>
