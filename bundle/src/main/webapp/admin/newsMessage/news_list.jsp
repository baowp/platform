<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>供求查询</title>
<link rel="stylesheet" href="<s:url value="/admin/css/common.css"/>"
	type="text/css" />
<link rel="stylesheet" href="<s:url value="/admin/css/tinybox.css"/>"
	type="text/css" />

<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery-ui.js"/>"></script>
<link type="text/css" href="<s:url value="/css/jquery-ui.css"/>"
	rel="stylesheet" />
<script type="text/javascript">
		$(document).ready(function() {
			$("#delete").click(function(){
				if(confirm("确定要删除这些供求信息么？"))
				{
				document.form1.action="deleteNews";
				document.form1.submit();
				}
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
		function chooseCategory(obj,origin,author){
			$("#newsId").val(obj);
			$("#origin").val(origin);
			$("#author").val(author);
			$("#dialog").dialog('open');
			$("#dialog").dialog({
				title:"选择分类",
				width:480
			});
		}
		function checkSort(){
			if($("#sortMap").val()=='none'){
				alert("请选择系统分类!")
				return false;
			}else if($("#origin").val()==''){
				alert('请输入来源')
				return false;
			}else if($("#author").val()==''){
				alert('请输入作者')
				return false;
			}else{
				return true;
			}
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
<s:form namespace="/admin" action="listNews" method="post"
	id="form1" name="form1">
	<div id="nav" style="position: relative">
	<ul>
		<li id="man_nav_1" class="bg_image_onclick">新闻管理</li>
		<li><s:textfield name="entName"
			id="entName" autocomplete="off">添加企业：</s:textfield> <s:if
			test="entId!=''&&entId!=null">【<a href="javascript:reset()">清空选择</a>】</s:if>
		<div id="suggest"
			style="display: none; border: 1px solid #3366CC; position: absolute; left: 211px; top: 25px"></div>
		<s:textfield name="searchKey" id="searchKey">新闻标题：</s:textfield> <s:submit
			cssClass="search" value="查询" /></li>
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
				<th class="header" name="title"></th>
				<th class="header" name="title">新闻标题</th>
				<th class="header" name="category">新闻栏目</th>
				<th class="header" name="enterprise">添加企业</th>
				<th class="header" name="adduser">添加用户</th>
				<th class="header" name="addtime">添加时间</th>
				<th class="header">操作</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator var="prod" value="pageList.items">
				<tr>
					<td><input type="checkbox" name="newsIds" value="<s:property value="newsId" />;03" /></td>
					<td><a href="viewNews?id=<s:property value="newsId" />" target="_blank"><s:property value="title" /></a></td>
					<td><s:property value="newsCategory().name" /></td>
					<td><s:property value="enterprise().name" /></td>
					<td><s:property value="addUser().username" /></td>
					<td><s:property value="addTimeString()" /></td>
					<td><a href="#" onclick="chooseCategory('${newsId}','${origin}','${author }')">升级为系统新闻</a></td>
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
<s:form namespace="/admin" action="upgoradNews" id="dialog"
	cssStyle="display:none;" onsubmit="return checkSort()" method="get">
	<s:hidden name="id" id="newsId"/>
	<table>
		<tr><td><s:select id="sortMap" list="#session['sortMap']" listKey="key"
				listValue="value" name="category" value="categoryId" headerKey="none" headerValue="请选择系统分类"  label="请选择系统分类" theme="xhtml"/></td></tr>
		<tr><td><s:textfield name="origin" id="origin" label="来源" theme="xhtml"/></td><td><s:textfield name="author" id="author" label="作者" theme="xhtml"/></td></tr>
		<tr><td><s:submit value="提交"/></td></tr>
	</table>
</s:form>
</body>
</html>
