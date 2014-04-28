<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<s:url value="/admin/css/common.css"/>"
	type="text/css" />
<link rel="stylesheet" href="<s:url value="/admin/css/tinybox.css"/>"
	type="text/css" />
<script type="text/javascript"
	src="<s:url value='/js/datePicker/WdatePicker.js'/>"></script>
<link href="<s:url value='/js/fckeditor/_samples/sample.css'/>"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<s:url value='/js/fckeditor/fckeditor.js'/>"></script>
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript">
	function setSearchRecvname(id,value){     
		$("#recvId").val(id);
		$("#recvName").val(value);
		$("#suggestRecvName").css("display","none");    
	} 
	$(document).ready(function() {
		$("#recvName").keyup(function(){
		  var str=$("#recvName").val();      
		    if(str==""){      
		       $("#suggestRecvName").css("display","none");      
		        return;      
		    }  
		    $.ajax({   
	     			 type: "post",   
	    			 dataType: "json",   
	     			 url: "byNameSearchUser",   
	     			 data: {"searchKey":str},   
	     			 success: function(data){   
	     				 var userlist = eval('(' +data+ ')');
	     				 var result="";
	     				   $.each(userlist,function(i,n){  
					$("#suggestRecvName").css("display","block");      
	           	 		 result+="<div onmouseover=\"javascript:suggestOver(this);\"";      
	            			 result+="onmouseout=\"javascript:suggestOut(this);\"";      
	            			 result+="onclick=\"javascript:setSearchRecvname('"+n.userId+"','"+n.username+"');\"";      
	            			 result+="class=\"suggest_link\">"+n.username+"</div>";                           
	                  });   
	                   $("#suggestRecvName").html(result);
	             	 }   
	    	});   
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
</script>
<style>
.suggest_link_over {
	background: #D5DBF4;
	padding-left: 2px;
}

.suggest_link {
	background: #fff;
	padding-left: 2px;
}
</style>
<title>群发消息</title>
</head>
<body>
<div id="nav">
<ul>
	<li id="man_nav_1" class="bg_image_onclick">群发消息</li>
	<li>请选择用户或者用户组或者选择发送到全部用户</li>
</ul>
</div>
<div id="sub_info">
<div id="man_zone"><s:if test="result!=''&&result!=null">
	<div id="warning">${result}</div>
</s:if> <s:form namespace="/admin" action="sendGroupmessage" method="post"
	id="form1">
	<table width="99%" border="0" align="center" cellpadding="3"
		cellspacing="1" class="table_style">
		<tr>
			<td width="70px" class="left_title_1"><span class="left-title">标题</span>
			</td>
			<td><s:textfield name="title" /></td>
		</tr>
		<tr>
			<td width="70px" class="left_title_1"><span class="left-title">发送类型</span>
			</td>
			<td><s:radio name="isAllUser"
				list="%{#{'0':'全部用户','1':'单个用户','2':'用户群组'}}" value="isAllUser" /></td>
		<s:hidden name="recvId" id="recvId" />
		</tr>
		<tr>
			<td width="70px" class="left_title_1"><span class="left-title">接收用户名</span>
			</td>
			<td>
			<div style="position: relative"><s:textfield name="recvName"
				cssStyle="width:80px" id="recvName" autocomplete="off">接收人：</s:textfield>
			<div id="suggestRecvName"
				style="display: none; border: 1px solid #3366CC; position: absolute; left: 56px; top: 25px"></div>
			</div>
			</td>
		</tr>
		<tr>
			<td width="70px" class="left_title_1"><span class="left-title">发送的群组</span>
			</td>
			<td><s:checkboxlist name="groupIds"
				list="%{#session.usergroups}" listKey="usergroupId"
				listValue="groupname" value="true" /></td>
		</tr>
		<tr>
			<td class="left_title_1"><span class="left-title">留言内容</span></td>
			<td><s:textarea name="content" rows="10" cols="50">
			</s:textarea> <span class="errorSpan">${errors.content[0]}</span></td>
		</tr>
		<tr>
			<td class="left_title_2"></td>
			<td class="left_title_2" style="text-align: left">
			<button type="submit" class="submit" />
			提交
			</button>
			<button onclick="window.location='payEnd'" class="return">返回</button>
			</td>
		</tr>
	</table>
</s:form></div>
</div>
</body>
</html>
