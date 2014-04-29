<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加品牌</title>
<link type="text/css" href="<s:url value="/css/jquery-ui.css"/>" rel="stylesheet" />
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery-ui.js"/>"></script>
<link rel="stylesheet" type="text/css" href="<s:url value="/user/abbcc/css/common.css"/>">
<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript">
	function checkForm(){
		var keyArray=new Array()
		keyArray[0]=$("#name").val()

		checkKeys(keyArray);
		if(rkey!=null){
			alert("您输入的("+rkey+")是非法关键字!");
			return false;
		}
		return true;
			
	}
	</script>
<script type="text/javascript">
function showSyscode(){
	$("#dialog").dialog('open');
	$("#dialog").dialog({
		title:"系统分类",
		width:480
	});
}
function fetchSyscodes(obj){
	var $cur=$(obj);
	var index=Number($cur.attr('id').replace('syscode',''));
	var nexts=[],tmp;
	while((tmp=$('#syscode'+(++index))).length)
		nexts.push(tmp)
	$.each(nexts,function(i){
		this.attr('length',0);
	});
	$.getJSON("../syscode/subSyscodes?randomNumber="+Math.random(), {
		fatherdict:obj.value
	}, function(result) {
		var $next=nexts[0]
		$.each(result.syscodeList,function(){
			if($next)
				$next[0][$next[0].length]=new Option(this.name,this.syscodeId)
		});
	});
}

function fixIndustry(){
	var $select=$('select');
	var key="";
	var value="";
	$select.each(function(){
		if(this.selectedIndex>-1){
			key=this.value;
			value+=this[this.selectedIndex].text+" > ";
		}
	});
	$('input[name=industry]').val(key);
	$('input[name=industryName]').val(value);
	$('#dialog').dialog('close');
}
</script>
</head>
<body>
<s:form namespace="/user/product/brand" action="save" onsubmit="return checkForm()">
<s:hidden name="id" value="%{brandId}"/>
<div class="editGrid">
	<dl>
		<dt>品牌名称：</dt>
		<s:textfield name="name" id="name"/>${errors.name[0] }
	</dl>
	<dl>
		<dt>行业：</dt>
		
			<s:hidden name="industry" /> 
			<s:if test="industry!=null&&industryName==null">
				<s:action name="syscodePath" namespace="/user/product/syscode" var="sys">
					<s:param name="syscodeId" value="industry"/>
				</s:action>
			</s:if>
			<s:textfield name="industryName" readonly="1"
				value="%{industry==null?'':industryName!=null?industryName:#sys.syspath}"/>
			<input type=button value=请选择行业 onclick=showSyscode() />
			${errors.industry[0] }
		
	</dl>
	<dl>
		<dt>&nbsp;</dt>
		<s:submit value="提交" title="提交"/>
			<s:if test="brandId!=null"><input type="button" value="返回" title="返回" onclick="javascript:history.go(-1);"/></s:if>
		
	</dl>
</div>
</s:form>
<div id="dialog" style="display: none">
	<s:action name="rootIndustry" namespace="/user/product/syscode" var="indu" ignoreContextParams="true"/>
	<div>
		<s:select list="#indu.syscodeList" listKey="syscodeId" listValue="name"
			cssStyle="width:140px;height:200px" size="7" id="syscode1" name="syscodeSelect"
			onchange="fetchSyscodes(this)" /> 
		<s:select list="#{}"
			cssStyle="width:140px;height:200px" size="7" id="syscode2" name="syscodeSelect"
			onchange="fetchSyscodes(this)" /> 
		<s:select list="#{}"
			cssStyle="width:140px;height:200px" size="7" id="syscode3" name="syscodeSelect"
			onchange="$(':submit:last').attr('disabled',false)" />
	</div>
	<div style="text-align: right;padding:10px">
		<s:submit value="提交" title="提交" onclick="fixIndustry()" />
	</div>
</div>
</body>
</html>