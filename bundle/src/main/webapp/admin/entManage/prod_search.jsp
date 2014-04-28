<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>产品查询</title>
<link rel="stylesheet" href="<s:url value="/admin/css/common.css"/>"
	type="text/css" />
<link rel="stylesheet" href="<s:url value="/admin/css/tinybox.css"/>"
	type="text/css" />
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<script type="text/javascript">
		$(document).ready(function() {
			$("#delete").click(function(){
				if(confirm("确定要删除这些产品么？"))
				{
				document.form1.action="deleteProd";
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
			   $("input:checkbox[name='prodIds']").attr('checked', true);
			}); 
			
			$("#uncheckall").click(function() { 
			    $("input:checkbox[name='prodIds']").attr('checked', false);
			}); 
			$("#checkwp").click(function(){
				$("#form1").submit();
			})
			$("#isBroadcast").click(function(){
				$("#form1").submit();
					
			})
		});
		function goto(page){
			$("#page").val(page);
			document.form1.submit();
		}
		function setBroadcast(obj){
			$.getJSON("/admin/setBroadcastProd?randomNumber="
					+ Math.random(), {
				id : $(obj).parents("tr[productId]").attr("productId"),
				broadcast:$(obj).attr("name")
			}, function(result) {
				alert("设置成功!");
			});
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
<s:form namespace="/admin" action="searchProd" method="post" id="form1"
	name="form1">
	<div id="nav" style="position: relative">
	<ul>
		<li id="man_nav_1" class="bg_image_onclick">产品查询</li>
		<li><s:textfield name="entName" id="entName" autocomplete="off">产品企业：</s:textfield>
		【<s:checkbox name="checkwp" label="旺铺用户" theme="xhtml" id="checkwp"></s:checkbox><s:checkbox
			name="isBroadcast" label="已推荐" theme="xhtml" id="isBroadcast"></s:checkbox>】
		<div id="suggest"
			style="display: none; border: 1px solid #3366CC; position: absolute; left: 213px; top: 25px"></div>
		<s:textfield name="searchName" id="searchName">产品名称：</s:textfield> <s:submit
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
				<th class="header" name="name">产品名称</th>
				<th class="header" name="category">产品分类</th>
				<th class="header" name="enterprise">添加企业</th>
				<th class="header" name="username">添加用户</th>
				<th class="header" name="addtime">添加时间</th>
				<th class="header" name="proddesc">产品描述</th>
				<th class="header" name="pic">产品图片</th>
				<th class="header">操作</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator var="pro" value="pageList.items" status="st">
				<tr productId=${productId}>
					<td>${name}</td>
					<td><s:property value="categoryName()" /></td>
					<td><a
						href="javascript:sortEnt('<s:property value="enterpriseId" />','<s:property value="enterprise().name" />')"><s:property
						value="enterprise().name" /></a></td>
					<td><s:property value="addUser().username" /></td>
					<td><s:property value="addTimeString()" /></td>
					<td><s:property value="proddesc.substring(0,50)" /></td>
					<td><a
						href="<s:property value="productMainPic().picUrl(3)" />"
						target="_blank">查看图片<a></td>
					<td><s:if test="state=='00'">
						<s:checkboxlist name="prodIds"
							list="%{#{productId+';01':'审核通过',productId+';02':'审核不通过'}}"></s:checkboxlist>
					</s:if> (<input type="checkbox" onchange="setBroadcast(this)"
						name="<s:property value="isBroadcast()"/>"
						<s:if test="isBroadcast()=='01'">checked="checked"</s:if> />)</td>
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
				<th colspan="8" class="tb_search">
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
