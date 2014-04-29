<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>abbcc</title>
<link rel="stylesheet" type="text/css" href="<s:url value="/user/abbcc/css/common.css"/>">
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript">
var type={
	address:'<s:property value="@com.abbcc.util.constant.BindType@A.name()"/>',
	domain:'<s:property value="@com.abbcc.util.constant.BindType@D.name()"/>'
};
function ctype(node){
	if(node.value==type.address){
		$('h4').html("旺铺地址绑定(填写具体地址)");
		$('#typeName').html("地址");
	}else{
		$('h4').html("旺铺域名绑定(填写泛域名的顶级域名)");
		$('#typeName').html("域名");
	}
}
$(function(){
	if($('input[name=type]:checked').length==0)
		$('input[name=type]')[0].checked=true;
	$('input[name=type]:checked').change();
});
</script>
</head>
<body>
<s:if test="#parameters['afresh']!=null||!state">
<div>
	<h4>旺铺域名绑定(填写泛域名的顶级域名)</h4>
	<div class="editGrid">
		<s:form namespace="/user/bind" action="save">
			<s:if test="bindId!=null">
				<s:hidden name="id" value="%{bindId }"/>
			</s:if>
			<dl>
				<dt>地址类型</dt>
				<dd><s:radio list="@com.abbcc.util.constant.BindType@values()" listKey="name()"
					name="type" value="type.name()" 
					id="type" onchange="ctype(this)"/></dd>
			</dl>
			<dl>
				<dt id="typeName">域名</dt>
				<dd><s:textfield name="address"/></dd>	
			</dl>
			<dl>
				<dt>icp备案号</dt>
				<dd><s:hidden name="icp" value="%{currentEnt.icp}"/>
					${currentEnt.icp} (<a href="/user/enterprise/company/completionList">修改</a>)
				</dd>	
			</dl>
				<dl>
				<dt>注: 
				</dt>
				<dd>域名申请联系电话: 0579-87171989    联系QQ: 9568027, 78014325</dd>	
			</dl>
			<dl>
				<dt>&nbsp;</dt>
				<dd><s:submit value="提交" /></dd>
			</dl>
		</s:form>
	</div>
</div>
</s:if>
<s:elseif test="state=='00'">
您已申请绑定<s:property value="type"/>${address },<a href="?afresh=1">修改申请</a>
</s:elseif>
<s:elseif test="state=='01'">
您已绑定<s:property value="type"/>${address },<a href="?afresh=1">重新绑定</a>
</s:elseif>
<s:elseif test="state=='02'">
您申请的<s:property value="type"/>${address }没有审核通过,
<p>原因：${denyReason}</p>
<div><a href="?afresh=1">重新申请</a></div>
</s:elseif>
</body>
</html>