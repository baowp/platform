<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分类目录移动</title>
<link rel="stylesheet" type="text/css" href="<s:url value="/user/abbcc/css/common.css"/>">
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<script type="text/javascript">
function fetchSubList(obj){
	var $cur=$(obj);
	var index=Number($cur.attr('id').replace('category',''));
	var nexts=[],tmp;
	while((tmp=$('#category'+(++index))).length)
		nexts.push(tmp)
	$.each(nexts,function(i){
		this.attr('length',0);
	});
	if(obj.value==$("input[name=id]").val()){
		$(":submit").attr("disabled",true);
		return;
	}
	$.getJSON("fetchSubList?randomNumber="+Math.random(), {
		belongId:obj.value
	}, function(result) {
		var $next=nexts[0]
		$.each(result.resultList,function(){
			if($next)
				$next[0][$next[0].length]=new Option(this.name,this.categoryId)
		});
		$(":submit").attr("disabled",false);
	});
}
function fetchTopList(obj){
	$("select[name=category]").each(function(){
		this.length=0;
	});
	$.getJSON("fetchSubList?randomNumber="+Math.random(), {
		belongId:obj.value
	}, function(result) {
		var $next=$("select[name=category]:first")
		$.each(result.resultList,function(){
			if($next)
				$next[0][$next[0].length]=new Option(this.name,this.categoryId)
		});
		$(":submit").attr("disabled",false);
	});
}
</script>
</head>
<body>
分类：“${name}”移动到
<br />
<br />
<s:form namespace="/user/product/category" action="altering">
<%--	<s:select list="#{'wj':'五金','hg':'化工'}" label="系统分类" theme="xhtml"-->
<!--		name="topId" onchange="fetchTopList(this)" /> --%>
	<br />
	<br />
自定义分类：<br />
	<s:iterator value="#request['infoMap'].values()" status="st">
		<s:select list="['list']" listKey="categoryId" listValue="name"
			name="category" id="%{'category'.concat(#st.index+1)}"
			value="['selectedId']" cssStyle="width:140px;height:200px" size="7"
			onchange="fetchSubList(this)" />
	</s:iterator>
	<s:hidden name="id" value="%{categoryId}" />
	<s:hidden name="page"/>
	<br />
	<s:submit disabled="true" value="确定"/>
	<img src="/images/fanhui.jpg" alt="返回" title="返回" onclick="javascript:history.go(-1);"/>
</s:form>
</body>
</html>