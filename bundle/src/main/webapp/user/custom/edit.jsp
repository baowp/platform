<%@ page language="java" import="java.util.*;" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>首页栏目设置</title>
<link rel="stylesheet" type="text/css"
	href="<s:url value="/user/abbcc/css/common.css"/>">
<link id="artDialogSkin" href="/js/artDialog3.0.5/skin/default.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/artDialog3.0.5/artDialog.min.js"></script>	
<script
	type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script> <script
	type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
<script type="text/javascript"
	src="<s:url value="/js/jquery/colorize.js"/>"></script>
</head>

<body>
<script>
(function($){
	// 改变默认配置
	var d = $.dialog.defaults;
	// 预缓存皮肤，数组第一个为默认皮肤
	d.skin = ['default', 'chrome', 'facebook', 'aero'];
	// 是否开启特效
	d.effect = true;
	// 指定超过此面积的对话框拖动的时候用替身
	//d.showTemp = 100000;
	
})(art);

$(function(){
	updateCustom();
})
function OpenModalDialog(newURL,wLen,hLen)
{
	art.dialog.open(newURL,{
		id : 'custom',
		skin: 'aero',
		title: '首页定制(按esc可关闭)',
		left:150,
		top:40,
		fixed:true,
		width:700,
		height:500
	});

}
function updateCustom(){
	var indexhtml="";
	$(".listTable tbody").find("#name").each(function(i){
		if($(this).parent().parent().find("#customState").attr("display")=='00'){
			var mId = $(this).parents("tr:first").attr("menuid");
			if(mId=='Menu_000000000000000000000000036'||mId=='Menu_000000000000000000000000038'||mId=='Menu_000000000000000000000000035'){
				indexhtml+="<a class=\"menu-bg lv-2\" onclick=\"openDia(this,true)\" hidefocus=\"\" id=\"isVip\" href=\""+$(this).parents("td").attr("action")+"\" target=\"_blank\"><span class=\"menu-bg lv-2-txt\">"+$(this).val()+"</span></a>";
			}else{
				indexhtml+="<a class=\"menu-bg lv-2\" onclick=\"openDia(this,true)\" hidefocus=\"\" onclick=\"loadingIframe('')\" href=\""+$(this).parents("td").attr("action")+"\" target=\"main\"><span class=\"menu-bg lv-2-txt\">"+$(this).val()+"</span></a>";
			}
		}
	});
	if(indexhtml=='')
		indexhtml="<h3>(还未定制任何栏目<br/>请点击'首页定制')</h3>";
	top.$("#indexCustom").html(indexhtml);
}
function setName(obj) {
	var $row=$(obj).parents("tr:first");
	$.getJSON("menusetName?randomNumber="+Math.random(), {
		id:$row.find("#updatename").attr('customId'),
		name:$row.find("#name").val()
	}, function(result) {
		if(result){
			updateCustom();
		}else{
			alert('修改失败');
		}
	});
}
function setdisplay(obj) {
	$.getJSON("menudisplay?randomNumber="+Math.random(), {
		id:obj.getAttribute('customId'),
		display:obj.getAttribute("display")
	}, function(result) {
			if(obj.getAttribute("display")=='01'){
				obj.setAttribute("display",'00');
				obj.innerHTML="显示";
			}else{
				obj.setAttribute("display",'01');
				obj.innerHTML="隐藏";
			}
			updateCustom();
	});
}
function changeSort(obj){
	var $row=$(obj).parents("tr[customId]");
	if(!confirm("您确定要调换该分类顺序吗？")){
		obj.value=$row.attr("customId")+","+$row.attr("sort");
		return ;
	}
	var info=obj.value.split(",");
	$("input[name=sourceCate]").val($row.attr("customId"));
	$("input[name=sourceSort]").val($row.attr("sort"));
	$("input[name=targetCate]").val(info[0]);
	$("input[name=targetSort]").val(info[1]);
	var $form=$("form:first");
	$form.submit();
	updateCustom();
}
function showChange(obj){
	var $form=$("form:first");
	$form.submit();
}

function stepSort(obj,type){
	var $row=$(obj).parents("tr[customId]");
	$("input[name=sourceCate]").val($row.attr("customId"));
	$("input[name=sourceSort]").val($row.attr("sort"));
	$("input[name=stepType]").val(type);
	var $form=$("form");
	$form.attr("action",$form.attr("action").replace("menuchange","menustep"));
	$form.submit();
	updateCustom();
}
</script>
<br>
<div>

<s:form action="menuchange" namespace="/user/menu">


	<!-- 用于调顺序 -->
	<s:hidden name="sourceCate" />
	<s:hidden name="sourceSort" />
	<s:hidden name="targetCate" />
	<s:hidden name="targetSort" />
	<s:hidden name="stepType" />
</s:form>

</div>
<div class="fv">
<input type="button" onclick="OpenModalDialog( '/user/menu/menuallMenuList?index=${sessionScope.abbccuser.enterpriseId}','700','600')" value="设置首页栏目"/>
</div>
<div>
<table class="listTable">
<thead>
	<tr>
		<th>所属模块</th>
		<th>栏目名称</th>
		<th>别名</th>
		<th>属性</th>
		<th>排序</th>
		<th>操作</th>
	</tr>
</thead>
<tbody>
	<s:iterator var="user" value="pageList.items" status="st">

		<tr customId=${customId } menuId=${menuId} sort=${sort}>
			<td  valign="middle" align="center" showState=${display }><a href="#">${moduleName}</a></td>
			<td valign="middle" align="center" ><s:property value="oldName()"/></td>
			<td valign="middle" align="center" action="${action}"><s:textfield id="name" onblur="setName(this)" name="name" value="%{name}"/></td>
			<td valign="middle" align="center" ><a href="javascript:void(0)" id="customState"
				onclick=setdisplay(this) customId=${customId
				}
				display=${display eq '01'?'00':'01'}>${display eq
			'01'?'显示':'隐藏' }</a></td>
			<td valign="middle" align="center" ><s:select list="#request['sortMap']" listKey="key"
						listValue="value" name="categoryOrder" value="customId+','+sort"
						onchange="changeSort(this)" /></td>
			
			<td  valign="middle" align="center"><s:if test="customId==resultList[0].customId">上移</s:if>
				<s:else>
					<a href="#" onclick=stepSort(this,2)>上移</a>
				</s:else>|
				<s:if test="customId==resultList[resultList.size()-1].customId">下移</s:if>
				<s:else>
					<a href="#" onclick=stepSort(this,1)>下移</a>
				</s:else>|<a href="#" onclick=setName(this) id="updatename" customId=${customId} name=>修改</a></td>


		</tr>

	</s:iterator>
	</tbody>
	<tfoot>
	<tr>


		<th colspan="8"><s:include
			value="../../common/page.jsp">
			<s:param name="pageList" value="pageList" />
		</s:include></th>

	</tr></tfoot>
</table>
</div>

</body>
</html>
