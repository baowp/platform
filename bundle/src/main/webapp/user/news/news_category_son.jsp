<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link type="text/css" href="<s:url value="/css/jquery-ui.css"/>"
	rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="<s:url value='/user/css/validation.css'/>">
<link rel="stylesheet" type="text/css"
	href="<s:url value="/user/abbcc/css/common.css"/>">

<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery/colorize.js"/>"></script>
<script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
<script type="text/javascript">
function setIsdisplay(obj) {
	$.getJSON("categorysisdisplay?randomNumber="+Math.random(), {
		id:obj.getAttribute('categoryId'),
		isdisplay:obj.getAttribute("isdisplay")
	}, function(result) {
		if(result.affectRows==1){
			if(obj.getAttribute("isdisplay")=='1'){
				obj.setAttribute("isdisplay",'0');
				obj.innerHTML="<img src=\"/user/images/eyeopen.jpg\" title=\"显示\"/>";
			}else{
				obj.setAttribute("isdisplay",'1');
				obj.innerHTML="<img src=\"/user/images/eyeclose.jpg\" title=\"隐藏\"/>";
			}
		}
	});
}

function changeSort(obj){
	var $row=$(obj).parents("tr[categoryId]");
	if(!confirm("您确定要调换该分类顺序吗？")){
		obj.value=$row.attr("categoryId")+","+$row.attr("sort");
		return ;
	}
	var info=obj.value.split(",");
	$("input[name=sourceCate]").val($row.attr("categoryId"));
	$("input[name=sourceSort]").val($row.attr("sort"));
	$("input[name=targetCate]").val(info[0]);
	$("input[name=targetSort]").val(info[1]);
	var $form=$("form:first");
	$form.attr("action",$form.attr("action").replace("categorysadd","categoryschange"));
	$form.submit();
}

</script>
<script type="text/javascript">
function setName1(obj) {
	var $row=$(obj).parents("tr:first");
	$.getJSON("categorysupdate?randomNumber="+Math.random(), {
		id:obj.getAttribute('categoryId'),
		name:$row.find("#name").val()
	}, function(result) {
		if(result.affectRows==1){
			alert('修改成功');
		}else{
			alert('修改失败');
		}
	});
}
function checkSonNews(obj,name){
	if(del()){
		$.ajax({
			url : "categoryssonNews",
			dataType:"json",
			async:false,  	//不进行异步操作
			data : {
			id:obj
			},
			success: function( data ) {
				if(data=='true'){
					alert('您当前分类还有新闻，请先删除新闻!');
				}else{
					window.location.href = "categorysdel?id="+obj+"&&parentName="+name;
				}
			}
		});
	}else{
		return;
	}
}
</script>
</head>

<body>
<s:include value="/common/resultMessage.jsp"></s:include>
<br>
<div class="fv">
<s:form action="categorysadd" namespace="/user/news">
	分类名称
	<s:hidden value="%{#request.cId}" name="categoryId"></s:hidden>
	<s:textfield name="name" cssStyle="margin-bottom:5px" value=""/>
	<s:submit value="添加" /><span class="errorSpan">${errors.name[0]}</span>
		<!-- 用于调顺序 -->
	<s:hidden name="sourceCate"/>
	<s:hidden name="sourceSort"/>
	<s:hidden name="targetCate"/>
	<s:hidden name="targetSort"/>
</s:form>
</div>


<table class="listTable">
<thead>
	<tr>
		<th >所属分类</th>
		<th >栏目名称</th>
		<th >排序</th>
		<th >是否显示</th>
		<th >操作</th>
	</tr>
</thead><tbody>
<s:if test="pageList.totalCount==0">
	<tr><td align="center" colspan="5">没有相关数据!</td></tr>
</s:if>
	<s:iterator var="user" value="pageList.items" status="st">

		<tr categoryId=${categoryId} sort=${sort}>
			<td  valign="middle" align="center"><a href="<s:url value="/user/news/categorysshow"/>"><s:property value="rootCategory()"/></a>>>>${name}</td>
			<td  valign="middle" align="center"><s:textfield id="name" name="name" value="%{name}" /></td>
			<td  valign="middle" align="center"><s:select list="#request['sortMap']" listKey="key"
						listValue="value" name="categoryOrder" value="categoryId+','+sort"
						onchange="changeSort(this)" /></td>
			<td  valign="middle" align="center"><a href="javascript:void(0)"
				onclick=setIsdisplay(this) categoryId=${categoryId
				}
				isdisplay=${isdisplay eq '1'?'0':'1'}>${isdisplay eq
			'1'?'显示':'隐藏' }</a></td>
			<td  valign="middle" align="center"><a
				href="javascript:" onclick="javascript:setName1(this)" categoryId="${categoryId
				}">修改</a>|<a
				href="javascript:checkSonNews('${categoryId}','<s:property value="rootCategory()"/>')">删除</a></td>


		</tr>

	</s:iterator>
	</tbody>
	<tfoot>
	<tr>

		<th colspan="8"><s:include
			value="../../common/page.jsp">
			<s:param name="urlArgs" value="'categoryId='+#request.cId+'&'"/>
		</s:include></th>

	</tr></tfoot>
</table>



</body>
</html>
