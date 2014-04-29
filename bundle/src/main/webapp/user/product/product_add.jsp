<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.abbcc.common.CommonConst"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>产品发布</title>
<link type="text/css" href="<s:url value="/css/jquery-ui.css"/>"
	rel="stylesheet" />
<script type="text/javascript"
	src="<s:url value="/js/util/context.jsp"/>"></script>
<script type="text/javascript"
	src="<s:url value='/js/datePicker/WdatePicker.js'/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery-ui.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<s:include value="/js/util/onload_colorbox.jsp"></s:include>
<link rel="stylesheet" type="text/css"
	href="<s:url value="/user/abbcc/css/common.css"/>">
<script type="text/javascript"
	src="<s:url value="/user/product/js/product.js"/>"></script>
<link href="../css/div.css" rel="stylesheet" type="text/css" />
<link href="../css/css.css" rel="stylesheet" type="text/css" />
<s:include value="/user/css/table.jsp"></s:include>
<s:include value="/common/xheditor.jsp"></s:include>
<script type="text/javascript">
$.getScript("/js/jquery/json.js")
$(function(){
	$("#thisForm").submit(function(){
		var reg =/((.|^)<script(.*)<\/script>)/i;
		$(".ckeditor").val().replace(reg,"")
		return true;
	})
	$("#tabs").tabs();
})
</script>
</head>
<body>
<s:if test="errors.addState[0]!=null">
	<div id="warning">${errors.addState[0]}</div>
</s:if>
<s:form namespace="/user/product" action="save" method="post"
	enctype="multipart/form-data" id="thisForm">
<div id="body">
<div id="tabs">
	<ul> 
		<li><a href="#tab1">基本参数</a></li> 
		<li><a href="#tab2">扩展属性</a></li> 
	</ul> 
	<div id="tab1">
	<div class="main_c04">
		<s:action name="brandList" />
	<table width="100%" height="25" border="0" cellpadding="0"
		cellspacing="0">
		<tr>
			<td width="10%" align="center">产品品牌
						<td width="30%" align="left"><s:select
				name="model.brand.brandId" value="brand.brandId"
				list="#request['brandList']" listKey="brandId" listValue="name" /></td>
			<td width="10%" align="center">是否显示</td>
			<td width="49%" align="left"><s:select name="isdisplay"
				value="isdisplay" list="#{1:'显示',0:'隐藏'}" />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;广告产品&nbsp; 
			<s:select name="ads"  value="ads" list="#{0:'否',1:'是'}" />
			</td>
		</tr>
	</table>
	</div>
	<div class="main_c04">
	<table width="100%" height="25" border="0" cellpadding="0"
		cellspacing="0">
		<tr>
			<td width="10%" align="center">产品类型</td>
			<td width="30%" align="left"><s:select name="type"
				value="type.name()"
				list="@com.abbcc.util.constant.ProductType@values()"
				listKey="name()" /></td>

			<td width="10%" align="center">查看权限</td>
			<td width="49%" align="left"><s:select name="loginView"
				value="loginView" list="#{0:'访问者可见',1:'登录后可见'}" /></td>
		</tr>
	</table>
	</div>
	<div class="main_c04">
	<table width="100%" height="27" border="0" cellpadding="0"
		cellspacing="0">
		<tr>
			<td width="10%" align="center">产品名称<span class="font03">*</span></td>
			<td width="30%" align="left"><s:textfield name="name"
				cssClass="txt" maxlength="60" /><span class="errorSpan">${errors.name[0]}</span></td>
			<td width="10%" align="center">产品分类<span class="font03">*</span></td>
			<td width="49%" align="left"><s:hidden name="category" /> <s:textfield
				name="cdesc" id="cdesc" readonly="1" cssClass="txt" /> <input
				type="button" value="请选择分类" onclick="showDialog()" /><span
				class="errorSpan">${errors.category[0]}</span></td>
		</tr>
	</table>
	</div>
	<div class="main_c04">
	<table width="100%" height="25" border="0" cellpadding="0"
		cellspacing="0">
		<tr>
			<td width="10%" align="center">产品型号</td>
			<td width="30%" align="left"><s:textfield name="prodtype"
				cssClass="txt" /><span class="errorSpan">${errors.prodtype[0]}</span></td>
			<td width="10%" align="center">关&nbsp;键&nbsp;字<span
				class="font03">*</span></td>
			<td width="49%" align="left"><s:textfield name="pkey"
				cssClass="txt" /><span class="errorSpan">${errors.pkey[0]}</span></td>
		</tr>
	</table>
	</div>

	<div class="main_c04">
	<table width="100%" height="25" border="0" cellpadding="0"
		cellspacing="0">
		<tr>
			<td width="10%" align="center">产品价格</td>
			<td width="30%" align="left"><s:textfield name="price"
				cssClass="txt" /><span class="errorSpan">${errors.price[0]}</span></td>
			<td width="10%" align="center">计数单位</td>
			<td width="49%" align="left"><s:textfield name="unit"
				cssClass="txt" /></td>
		</tr>
	</table>
	</div>
	<div class="main_c04">
	<table width="100%" height="25" border="0" cellpadding="0"
		cellspacing="0">
		<tr>
			<td width="10%" align="center">联&nbsp;系&nbsp;人</td>
			<td width="30%" align="left"><s:textfield name="contactId"
				cssClass="txt" value="%{#session.abbccuser.name}"/><span class="errorSpan">${errors.contactId[0]}</span></td>
			<td width="10%" align="center">产品图片<span class="font03">*</span></td>
			<td width="49%" align="left"><s:textfield name="photo"
				id="picPath" readonly="true" /><a id="various3"
				href="<s:url value="/user/album/albumshowUploadPage"/>"> 选择相片</a></td>
		</tr>
	</table>
	</div>
	<div>
	<table width="100%" height="95%" border="0" cellpadding="0"
		cellspacing="0">
		<tr>
			<td width="10%" align="center" valign="top">产品简介</td>
			<td align="left" valign="top" width="88%"><s:textarea name="proddesc"
				cssClass="ckeditor" rows="12" cols="95" cssStyle="width:89%;height:250px;"/><span class="errorSpan">${errors.contactId[0]}</span></td>
		</tr>
	</table>
	</div>
	</div>
	<DIV id="tab2">
			<table width="100%" height="50" border="0" cellpadding="0"
				cellspacing="1">
				<s:iterator value="photo2" status="st">
					<s:if test="#st.odd">
						<tr>
					</s:if>
					<s:if test="#st.last">
						<td width="12%" align="center">附属图片</td>
						<td width="38%" align="left"><s:textfield name="photo2"
							value="%{photo2[#st.index]}" id="picPath%{#st.index}"
							cssClass="txt" readonly="true" /><a class="various3"
							href="<s:url value="/user/album/albumshowUploadPage?valueId=picPath%{#st.index}"/>">
						选择相片</a></td>
					</s:if>
					<s:else>
						<td width="12%" align="center">附属图片</td>
						<td width="38%" align="left"><s:textfield name="photo2"
							value="%{photo2[#st.index]}" id="picPath%{#st.index}"
							cssClass="txt" readonly="true" /><a class="various3"
							href="<s:url value="/user/album/albumshowUploadPage?valueId=picPath%{#st.index}"/>">
						选择相片</a></td>
					</s:else>
					<s:if test="#st.event">
						</tr>
					</s:if>
				</s:iterator>
			</table>
			<table width="100%" height="100%" border="0" cellpadding="0"
				cellspacing="1">
				<tr>
					<td width="10%" align="center" valign="top"><s:textfield name="detailTitle1" value="%{@com.abbcc.common.CommonConst@DETAIL_TITLE1}" size="8">
					</s:textfield> 
						<br/>
					<span class="spanInfo">(注:标题为空时,不显示内容)</span>
					</td>
					<td align="left" valign="top" width="88%"><s:textarea name="detail1"
						cssClass="ckeditor" rows="12" cols="95" cssStyle="width:89%;height:180px;"/></td>
				</tr>
			</table>
			<table width="100%" height="100%" border="0" cellpadding="0"
				cellspacing="1">
				<tr>
					<td width="10%" align="center" valign="top"><s:textfield name="detailTitle2" value="%{@com.abbcc.common.CommonConst@DETAIL_TITLE2}" size="8"></s:textfield> 
					<br/>
					<span class="spanInfo">(注:标题为空时,不显示内容)</span>
					</td>
					<td align="left" valign="top" width="88%"><s:textarea name="detail2"
						cssClass="ckeditor" rows="12" cols="95" cssStyle="width:89%;height:180px;"/></td>
				</tr>
			</table>
			<table width="100%" height="100%" border="0" cellpadding="0"
				cellspacing="1">
				<tr>
					<td width="10%" align="center" valign="top"><s:textfield name="detailTitle3" size="8" value="%{@com.abbcc.common.CommonConst@DETAIL_TITLE3}"></s:textfield> 
					<br/><span class="spanInfo">(注:标题为空时,不显示内容)</span></td>
					<td align="left" valign="top" width="88%">
						<s:hidden id="hidParams" name="detail3"></s:hidden>
						<table id="detailTable" class="listTable" style="width: 500px;">
							<thead>
								<tr align="center">
									<th width="26%">名称</th>
									<th>数据</th>
									<th width="15%">操作</th>
								</tr>
							</thead>
							<tbody>
								<tr align="center">
									<td><input name="paramKey" type="text" /></td>
									<td><input name="paramValue" type="text" /></td>
									<td><a href="javascript: void(0);" onclick="addRow(this)">添加</a>|<a href="javascript: void(0);" onclick="delRow(this)">删除</a></td>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>
			</table>
	</DIV>
	</div>
	<s:submit onclick="return checkFrm()" value="提交"></s:submit>
</div>
</s:form>
<div id="dialog" title="请选择分类" style="display: none"><s:action
	name="categoryInnerList" namespace="/user/product/category"
	executeResult="true" ignoreContextParams="true">
</s:action>
</div>
</body>
</html>
