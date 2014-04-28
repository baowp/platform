<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>网站模板管理</title>
<link rel="stylesheet" href="<s:url value="/admin/css/common.css"/>"
	type="text/css" />
<link rel="stylesheet" href="<s:url value="/admin/css/tinybox.css"/>"
	type="text/css" />
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<script type="text/javascript">
		$(document).ready(function() {
			$("#searchKey").keyup(function(){
			  var str=$("#searchKey").val();      
			    if(str==""){      
			       $("#suggest").css("display","none");      
			        return;      
			    }  
			    $.ajax({   
       			 type: "post",   
      			 dataType: "json",   
       			 url: "searchUserSite",   
       			 data: {"searchKey":str},   
       			 success: function(data){   
       				 var userlist = eval('(' +data+ ')');
       				 var result="";
       				   $.each(userlist,function(i,n){  
						$("#suggest").css("display","block");      
             	 		 result+="<div onmouseover=\"javascript:suggestOver(this);\"";      
              			 result+="onmouseout=\"javascript:suggestOut(this);\"";      
              			 result+="onclick=\"javascript:setSearch('"+n.usersiteId+"','"+n.username+"("+n.domain+")"+"');\"";      
              			 result+="class=\"suggest_link\">"+n.username+"("+n.domain+")"+"</div>";                           
                    });   
                     $("#suggest").html(result);
               	 }   
      			});   
			});
			$("#add").click(function() {
				document.form1.action="uploadUserTemplate";
				document.form1.submit();
			});
			
		});
		
		function goto(page){
			$("#page").val(page);
			document.form1.submit();
		}
		//mouse over function      
		function suggestOver(div_value){      
		    div_value.className="suggest_link_over";      
		}      
		//mouse out function      
		function suggestOut(div_value){      
		    div_value.className="suggest_link";      
		}      
		//click function      
		function setSearch(id,value){     
			$("#siteId").val(id);
			$("#searchKey").val(value);
			$("#suggest").css("display","none");    
		}  
		function reset(){
			$("#siteId").val("");
			$("#searchKey").val("");
		}
		function deleteTemplate(tempId,siteId){
			if(confirm("确定要删除这个模板？"))
				window.location="deleteUserTemplate.action?id="+tempId+"&siteId="+siteId;
		}
</script>
</head>
<body>
<s:form namespace="/admin" action="listUserTemplate" method="post"
	enctype="multipart/form-data" id="form1" name="form1">
	<div id="nav">
	<ul>
		<li id="man_nav_1" class="bg_image_onclick">网站模板</li>
		<li><s:textfield name="searchKey" id="searchKey"
			autocomplete="off">用户名或者域名：</s:textfield> <s:if
			test="siteId!=''&&siteId!=null">【<a href="javascript:reset()">清空选择</a>】</s:if>
		<div id="suggest"
			style="display: none; border: 1px solid #3366CC; position: absolute; left: 253px; top: 50px"></div>
		<s:hidden name="siteId" id="siteId" /> <s:submit cssClass="search"
			value="查询" /></li>
	</ul>
	</div>
	<div id="sub_info">
	<div id="man_zone"><s:if test="result!=''&&result!=null">
		<div id="warning">${result}</div>
	</s:if> <s:if test="siteId!=''&&siteId!=null">
		<table width="99%" border="0" align="center" cellpadding="3"
			cellspacing="1" id="tablesorter">
			<thead>
				<tr>
					<th class="header" name="username">用户名</th>
					<th class="header" name="domain">域名</th>
					<th class="header" name="name">页面名称</th>
					<th class="header" name="pagename">页面url</th>
					<th class="header" name="addTime">上传时间</th>
					<th class="header" name="userdisplay">用户可见</th>
					<th class="header" name="userdisplay">模板类型</th>
					<th class="header" name="op">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator var="template" value="pageList.items">
					<tr>
						<td><s:property value="soaUser().username" /></td>
						<td><s:property value="soaUser().domain" /></td>
						<td><s:property value="name" /></td>
						<td><s:property value="pageName" /></td>
						<td><s:property value="addTimeString()" /></td>
						<td><s:if test="userdisplay==1">是</s:if>
							<s:if test="userdisplay==0">否</s:if>
						</td>
						<td><s:if test="contentType==00">列表</s:if>
							<s:if test="contentType==01">详细</s:if>
						</td>
						<td><s:url id="url" action="viewUserTemplate">
							<s:param name="id">
								<s:property value="templateId" />
							</s:param>
							<s:param name="siteId">
								<s:property value="siteId" />
							</s:param>
							<s:param name="searchKey">
								<s:property value="searchKey" />
							</s:param>
						</s:url> <s:a href="%{url}">修改</s:a> &nbsp; <a
							href="javascript:deleteTemplate('<s:property value="templateId" />','<s:property value="siteId" />')">删除</a>
						&nbsp; <s:url id="dataurl" action="viewCriteriaUserTemplate">
							<s:param name="id">
								<s:property value="templateId" />
							</s:param>
							<s:param name="siteId">
								<s:property value="siteId" />
							</s:param>
						</s:url> <s:a href="%{dataurl}">设置模板数据</s:a></td>
					</tr>
				</s:iterator>
			</tbody>
			<tfoot>
				<tr>
					<th colspan="8" class="tb_search"><s:include
						value="../../common/page.jsp">
						<s:param name="pageList" value="pageList" />
					</s:include></th>
				</tr>
				<tr>
					<th colspan="8" class="tb_search"><s:textfield name="name">模板名称：</s:textfield>
					<s:textfield name="pageName">页面url：</s:textfield> 
					<s:select name="contentType" list="#{'00':'列表','01':'详细'}">模板类型：</s:select><s:file
						name="upload">选择文件</s:file>
					<button type="button" id="add" class="add">添加模板</button>
					</th>
				</tr>
			</tfoot>
		</table>
	</s:if></div>
	</div>
</s:form>
</body>
</html>
