<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>公告管理</title>
<link rel="stylesheet" href="<s:url value="/admin/css/common.css"/>"
	type="text/css" />
<link rel="stylesheet" href="<s:url value="/admin/css/tinybox.css"/>"
	type="text/css" />
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>

<script type="text/javascript">
		$(document).ready(function() {
			$("#delete").click(function(){
				if(confirm("确定要删除这些公告么？"))
				{
				document.form1.action="updateStateAnnounce";
				document.form1.submit();
				}
			});
			$("#publish").click(function(){
				document.form1.action="updateStateAnnounce";
				document.form1.submit();
			});
			$("#add").click(function(){
				window.location="newsMessage/announce_add.jsp"
			});
			$("#entName").keyup(function(){
			  var str=$("#entName").val();      
			    if(str==""){      
			       $("#suggest").css("display","none");      
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
						$("#suggest").css("display","block");      
             	 		 result+="<div onmouseover=\"javascript:suggestOver(this);\"";      
              			 result+="onmouseout=\"javascript:suggestOut(this);\"";      
              			 result+="onclick=\"javascript:setSearch('"+n.enterpriseId+"','"+n.name+"');\"";      
              			 result+="class=\"suggest_link\">"+n.name+"</div>";                           
                    });   
                     $("#suggest").html(result);
               	 }   
      			});   
			});
			$("#checkall").click(function() { 
			   $("input:checkbox[name='newsIds']").attr('checked', true);
			}); 
			
			$("#uncheckall").click(function() { 
			    $("input:checkbox[name='newsIds']").attr('checked', false);
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
			$("#entId").val(id);
			$("#entName").val(value);
			$("#suggest").css("display","none");    
		}  
		function reset(){
			$("#entId").val("");
			$("#entName").val("");
		}   
		function soreDel(){
			if(confirm("确定要删除这些公告么？")){
				return true;
			}else
				return false;
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
<s:form namespace="/admin" action="listAnnounce" method="post"
	id="form1" name="form1">
	<div id="nav" style="position: relative">
	<ul>
		<li id="man_nav_1" class="bg_image_onclick">公告管理</li>

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
		<thead>
			<tr>
				<th class="header" name="title">公告标题</th>
				<th class="header" name="adduser">添加人</th>
				<th class="header" name="addtime">添加时间</th>
				<th class="header">操作</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator var="prod" value="pageList.items">
				<tr>
					<td><a
						href="viewAnnounce?id=<s:property value="newsId" />"
						target="_blank"><s:property value="title" /></a></td>
					<td><s:property value="addAdmin().username" /></td>
					<td><s:property value="addTimeString()" /></td>
					<td><a
						href="viewEditAnnounce?id=<s:property value="newsId" />">修改</a>/<a href="delAnnounce?id=${newsId}" onclick="return soreDel()">删除</a>
					</td>
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
				<button type="button" id="add" class="add">添加</button>
				</th>
			</tr>
		</tfoot>
	</table>
	</div>
	</div>
</s:form>
</body>
</html>
