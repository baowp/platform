<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>查看留言</title>
<link rel="stylesheet" href="<s:url value="/admin/css/common.css"/>"
	type="text/css" />
<link rel="stylesheet" href="<s:url value="/admin/css/tinybox.css"/>"
	type="text/css" />
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript">
		$(document).ready(function() {
			$("#delete").click(function(){
				if(confirm("确定要删除这些消息么？"))
				{
				document.form1.action="editStateMessage";
				document.form1.submit();
				}
			});
			$("#entName").keyup(function(){
			  var str=$("#entName").val();      
			    if(str==""){      
			       $("#suggestEntName").css("display","none");      
			        return;      
			    }  
			    $.ajax({   
       			 type: "post",   
      			 dataType: "json",   
       			 url: "byNameSearchEnterprise",   
       			 data: {"searchKey":str},   
       			 success: function(data){   
       				 var userlist = eval('(' +data+ ')');
       				 var result="";
       				   $.each(userlist,function(i,n){  
						$("#suggestEntName").css("display","block");      
             	 		 result+="<div onmouseover=\"javascript:suggestOver(this);\"";      
              			 result+="onmouseout=\"javascript:suggestOut(this);\"";      
              			 result+="onclick=\"javascript:setSearchEntname('"+n.enterpriseId+"','"+n.name+"');\"";      
              			 result+="class=\"suggest_link\">"+n.name+"</div>";                           
                    });   
                     $("#suggestEntName").html(result);
               	 }   
      			});   
			});
			$("#sendName").keyup(function(){
			  var str=$("#sendName").val();      
			    if(str==""){      
			       $("#suggestSendName").css("display","none");      
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
						$("#suggestSendName").css("display","block");      
             	 		 result+="<div onmouseover=\"javascript:suggestOver(this);\"";      
              			 result+="onmouseout=\"javascript:suggestOut(this);\"";      
              			 result+="onclick=\"javascript:setSearchSendname('"+n.userId+"','"+n.username+"');\"";      
              			 result+="class=\"suggest_link\">"+n.username+"</div>";                           
                    });   
                     $("#suggestSendName").html(result);
               	 }   
      			});   
			});
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
			$("#checkall").click(function() { 
			   $("input:checkbox[name='messageIds']").attr('checked', true);
			}); 
			
			$("#uncheckall").click(function() { 
			    $("input:checkbox[name='messageIds']").attr('checked', false);
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
		function setSearchEntname(id,value){     
			$("#entId").val(id);
			$("#entName").val(value);
			$("#suggestEntName").css("display","none");    
		}  
		function setSearchSendname(id,value){     
				$("#sendId").val(id);
				$("#sendName").val(value);
				$("#suggestSendName").css("display","none");    
			}  
		function setSearchRecvname(id,value){     
				$("#recvId").val(id);
				$("#recvName").val(value);
				$("#suggestRecvName").css("display","none");    
			}  
		function reset(){
			$("#entId").val("");
			$("#entName").val("");
			$("#sendId").val("");
			$("#sendName").val("");
			$("#recvId").val("");
			$("#recvName").val("");
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
</head>
<body>
<s:form namespace="/admin" action="listMessage" method="post" id="form1"
	name="form1">
	<div id="nav" style="position: relative">
	<ul>
		<li id="man_nav_1" class="bg_image_onclick">留言查看</li>
		<li><a href="browserMessage">查看网站留言</a></li>
		<li><s:textfield name="sendName" cssStyle="width:80px"
			id="sendName" autocomplete="off">发送人：</s:textfield> <s:textfield
			name="recvName" cssStyle="width:80px" id="recvName"
			autocomplete="off">接收人：</s:textfield> <s:textfield name="entName"
			cssStyle="width:80px" id="entName" autocomplete="off">接收企业：</s:textfield>
		<s:if
			test="(entId!=''&&entId!=null)||(sendId!=''&&sendId!=null)||(recvId!=''&&recvId!=null)">【<a
				href="javascript:reset()">清空选择</a>】</s:if>
		<div id="suggestSendName"
			style="display: none; border: 1px solid #3366CC; position: absolute; left: 200px; top: 25px"></div>
		<div id="suggestRecvName"
			style="display: none; border: 1px solid #3366CC; position: absolute; left: 340px; top: 25px"></div>
		<div id="suggestEntName"
			style="display: none; border: 1px solid #3366CC; position: absolute; left: 495px; top: 25px"></div>
		<s:textfield name="searchKey" id="searchKey" cssStyle="width:80px">留言内容：</s:textfield>
		<s:submit cssClass="search" value="查询" /></li>
	</ul>
	</div>
	<div id="sub_info">
	<div id="man_zone"><s:if test="result!=''&&result!=null">
		<div id="warning">${result}</div>
	</s:if>
	<table width="99%" border="0" align="center" cellpadding="3"
		cellspacing="1" id="tablesorter">
		<s:hidden name="page" id="page" />
		<s:hidden name="entId" id="entId" />
		<s:hidden name="sendId" id="sendId" />
		<s:hidden name="recvId" id="recvId" />
		<thead>
			<tr>
				<th class="header" name="send">发送人</th>
				<th class="header" name="recv">接收人</th>
				<th class="header" name="enterprise">接收企业</th>
				<th class="header" name="addtime">发送时间</th>
				<th class="header" name="title">标题</th>
				<th class="header" name="content">内容</th>
				<th class="header">操作</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator var="message" value="pageList.items">
				<tr>
					<td><s:property value="sender().username" /></td>
					<td><s:property value="recver().username" /></td>
					<td><s:property value="enterprise().name" /></td>
					<td><s:property value="addTimeString()" /></td>
					<td><s:property value="title" /></td>
					<td><s:property value="content" /></td>
					<td><input type="checkbox" name="messageIds"
						value="<s:property value="messageId" />;03" /></td>
				</tr>
			</s:iterator>

		</tbody>
		<tfoot>
			<tr>
				<th colspan="7" class="tb_search"><s:include
					value="../../common/page.jsp">
					<s:param name="pageList" value="pageList" />
				</s:include></th>
			</tr>
			<tr>
				<th colspan="7" class="tb_search">
				<button type="button" id="checkall" class="checkall">全选</button>
				<button type="button" id="uncheckall" class="uncheckall">反选</button>
				<button type="button" id="delete" class="delete">删除</button>
				</th>
			</tr>
		</tfoot>
	</table>
	</div>
	</div>
</s:form>
</body>
</html>
