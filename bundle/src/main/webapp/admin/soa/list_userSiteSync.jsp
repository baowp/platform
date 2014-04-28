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
<script type="text/javascript"
	src="<s:url value='/js/datePicker/WdatePicker.js'/>"></script>
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
			$("#sync").click(function() {
				if("确定要同步网站信息么？"){
				$("#sync").disable();
				document.form1.action="syncsite/syncsite";
				document.form1.submit();
				
				}
			});
			
		});
		
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
			$("#id").val(id);
			$("#searchKey").val(value);
			$("#suggest").css("display","none");    
		}  
		function reset(){
			$("#id").val("");
			$("#searchKey").val("");
		}
</script>
</head>
<body>
<s:form namespace="/admin" action="listSiteSync" method="post"
	id="form1" name="form1">
	<div id="nav">
	<ul>
		<li id="man_nav_1" class="bg_image_onclick">网站同步</li>
		<li><s:textfield name="searchKey" id="searchKey"
			autocomplete="off">用户名或者域名：</s:textfield> <s:if
			test="id!=''&&id!=null">【<a href="javascript:reset()">清空选择</a>】</s:if>
		<div id="suggest"
			style="display: none; border: 1px solid #3366CC; position: absolute; left: 253px; top: 50px"></div>
		<s:hidden name="id" id="id" /> <s:submit cssClass="search" value="查询" /></li>
	</ul>
	</div>
	<div id="sub_info">
	<div id="man_zone"><s:if test="result!=''&&result!=null">
		<div id="warning">${result}</div>
	</s:if> <s:if test="model.usersiteId!=null">
		<table width="99%" border="0" align="center" cellpadding="3"
			cellspacing="1" id="tablesorter">
			<thead>
				<tr>
					<th class="header" name="username">用户名</th>
					<th class="header" name="domain">域名</th>
					<th class="header" name="server">所属服务器</th>
					<th class="header" name="path">部署路径</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><s:property value="username" /></td>
					<td><s:property value="domain" /></td>
					<td><s:property value="webServer().name" /></td>
					<td><s:property value="folder" /></td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<th colspan="5" class="tb_search">
					<button type="button" id="sync" class="sync">同步</button>
					</th>
				</tr>
			</tfoot>
		</table>
	</s:if> <s:else>
		<h2>请先查询到要同步的网站或者用户！</h2>
	</s:else></div>
	</div>
</s:form>
</body>
</html>
