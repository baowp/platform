<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产品分类管理</title>
<style type="text/css">
.top{line-height: 27px;padding-bottom: 4px;}.nav{font-size: 14px;}
.ifLeaf{margin: 0 2px;}.ifLeaf img{position:relative;top:2px;}
</style>
<link type="text/css" href="<s:url value="/css/jquery-ui.css"/>" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="<s:url value="/user/abbcc/css/common.css"/>">
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery-ui.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery/colorize.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery/bgiframe.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery/metadata.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/util/context.jsp"/>"></script>
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<script type="text/javascript" src="<s:url value="/user/product/js/category.js"/>"></script>
<s:include value="/js/util/onload_colorbox.jsp"></s:include>
<script type="text/javascript">
</script>
</head>
<body>
<s:include value="/common/resultMessage.jsp"></s:include>
<s:form namespace="/user/product/category" action="save">
	<s:hidden name="page" value="%{pageList.currentPage}"/>
	<div class="top">
		<s:if test="!hasBelongId">
			<div class="fv">
				分类管理：
				<input type="button" onclick="javascript:showSyscode()" value="添加请先选择系统分类"/>
			</div>
		</s:if>
		<s:else>
			<div class="fv">
				分类管理：${empty requestScope.headPath ? cdesc : requestScope.headPath }
				<s:iterator value="#request['position'].entrySet()">
					<a href="show?belongId=${key}">${value }</a> >
				</s:iterator>
				<s:if test="!hasSyscode">
					<a href="show?belongId=${categoryId}">${name }</a> >
				</s:if>
				<s:hidden name="cdesc" value="%{cdesc+(name==null?'':name+' > ')}"/>
				<input type=hidden name=belongId value=<s:property value="categoryId||syscode"/> />
				<input type=hidden name=grade
					value="0${empty categoryId?1:fn:trim(grade)+1 }" />
				<input type=hidden name=isroot value=${empty categoryId?'01':'00'} />
			</div>
			<div>
				新分类：<s:textfield name="name" value="" /><span class="errorSpan">${errors.name[0] }${param.clue}</span>
				类别图片：<s:textfield id="picPath" name="image" value="" readonly="true"/>
				<a class="various3" href="<s:url value="/user/album/albumshowUploadPage"/>">
				选择相片</a>
				<s:submit value="提交"></s:submit>
			</div>
		</s:else>
	</div>	
</s:form>
	<table class="listTable">
		<thead>
			<tr>
				<th width="20%">分类名称</th>
				<th>分类图片</th>
				<th>修改名称</th>
				<th>对调顺序</th>
				<th>上下移动</th>
				<th>操作</th>
			</tr>
		</thead>
		<s:if test="pageList.totalCount==0">
	<tr><td align="center" colspan="5">没有相关数据!</td></tr>
		</s:if>
		<s:iterator value="pageList.items" status="st">
			<tr>
				<td><a href="<s:property value="ifLeaf?'javascript:':'show?belongId='+categoryId"/>" class="ifLeaf">
					<img src="<s:url value="/images/jia%{ifLeaf?'n':''}hao.jpg"/>"></img></a>
					<span id="name"><s:property value="name" /></span></td>
				<td align="center">${image2}</td>
				<td  valign="middle" align="center"><a href=javascript: onclick=onModify(this)>修改</a></td>
				<td  valign="middle" align="center"><s:select name="sort" value="categoryId" onchange="change(this)" list="#request['sortMap']"/></td>
				<td  valign="middle" align="center"><a href="javascript:" onclick="stepUp(this)">上移</a>|<a href="javascript:" onclick="stepDown(this)">下移</a>
				</td>
				<td  valign="middle" align="center">
				<a href="alter?id=${categoryId}&page=${page}">移动目录</a>|
				<a href="show?belongId=${categoryId }" >${ifLeaf?'添加下属':'下属分类' }</a>|
				<a href="javascript:" onclick=setDisplay(this) 
					class="isdisplay {categoryId:'${categoryId}',isdisplay:'${isdisplay}'}"
					>${isdisplay eq '1'?'已显示':'已隐藏' }</a>|
				<a href="remove?id=${categoryId}" onclick="return onRemove(this);">删除</a>
				
				
				</td>
			</tr>
		</s:iterator>
		<tr id="hid_tr" style="display:none;">
			<td colspan="6">
				分类名称：<input id="hid_name" type=text name="name" value="" onkeypress="return modifyOnPress(this,event)" />
				类别图片：<s:textfield id="hid_image" name="image" value="" readonly="true"/>
				<a class="various3" href="<s:url value="/user/album/albumshowUploadPage?valueId=hid_image"/>">
				选择图片</a>
				<a href="javascript:void(0);" onclick="clearPicImg();">
				清除图片</a>
				<input id="hid_btn" type="button" value="确定修改 " onclick="prepareModify(this)"/>
				<input type="button" value="取消"  onclick="cancel(this)"/>
				</td>
			</tr>
		<tfoot>
			<tr>
				<th colspan="9">
				
					<s:include value="/common/page.jsp">
						<s:param name="urlArgs"	value="hasBelongId?'belongId='+(hasSyscode?syscode:categoryId)+'&':''" />
					</s:include>
				</th>
			</tr>
		</tfoot>
	</table>
<s:if test="!hasBelongId">
<s:form namespace="/user/product/category" action="show" id="dialog" 
	cssStyle="display:none;" method="get" onsubmit="prepareLastForm()">
	<s:hidden name="belongId" />
	<s:hidden name="cdesc" />
	<div><s:select list="#request['syscodeList']" listKey="syscodeId" listValue="name"
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
		<s:submit disabled="1" value="确定"/>
	</div>
</s:form>
<script type="text/javascript">
</script>
</s:if>
</body>
</html>
