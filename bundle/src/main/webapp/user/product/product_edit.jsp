<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/dl/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产品修改</title>
<link type="text/css" href="<s:url value="/css/jquery-ui.css"/>" rel="stylesheet" />
<script type="text/javascript" src="<s:url value="/js/util/context.jsp"/>"></script>
<script type="text/javascript" src="<s:url value='/js/datePicker/WdatePicker.js'/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery-ui.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<s:include value="/js/util/onload_colorbox.jsp"/>
<link href="../css/css.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<s:url value="/user/abbcc/css/common.css"/>">
<script type="text/javascript" src="<s:url value="/user/product/js/product.js"/>"></script>
<s:include value="/user/css/table.jsp"></s:include>
<s:include value="/common/xheditor.jsp"></s:include>
<script type="text/javascript">
function newUpload(node){
	var dd=$(node).parent().parent();
	dd.find(".updatePic2").find("input[name='updatePic2']").val('1');
	dd.find(".old").hide();
	dd.find(".new").show();
}

$(function(){
	$('#cdesc').val(categoryMap[$('#category').val()]);
	$("#tabs").tabs();
});

</script>
<script type="text/javascript">
$(function(){
	$("#thisForm").submit(function(){
		var reg =/((.|^)<script(.*)<\/script>)/i;
		$(".ckeditor").val().replace(reg,"")
		return true;
	})
	
	
})
</script>
</head>
<body>
<s:form namespace="/user/product" action="update" method="post" id="thisForm"
	enctype="multipart/form-data" onsubmit="">
	<s:hidden name="id" value="%{productId}"/>
	<s:hidden name="back"/>
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
				cssClass="txt" maxlength="20" /><span class="errorSpan">${errors.name[0]}</span></td>
			<td width="10%" align="center">产品分类<span class="font03">*</span></td>
			<td width="49%" align="left"><s:hidden name="category" id="category" /> <s:textfield
				name="cdesc" id="cdesc" readonly="1" cssClass="txt" /> <input
				type="button" value="请选择产品" onclick="showDialog()" /><span
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
			<td width="10%" align="center">价格产品</td>
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
			<td width="10%" align="center">联&nbsp;系&nbsp;人<span
				class="font03">*</span></td>
			<td width="30%" align="left"><s:textfield name="contactId"
				cssClass="txt" /><span class="errorSpan">${errors.contactId[0]}</span></td>
			<td width="10%" align="center">产品图片<span class="font03">*</span></td>
			<td width="49%" align="left">
				<s:hidden name="photoId" value="%{mainPic.attId}"/>
				<span class="old">
					<img alt="${mainPic.filename }" src="<s:property value="mainPic.picUrl(5)"/>">
					<input type="button" value="重新上传" onclick="newUpload(this)">
				</span>
				<span class="new none">
					<s:textfield name="photo" id="picPath" readonly="true"/><a
						class="various3" href="<s:url value="/user/album/albumshowUploadPage?valueId=picPath"/>"> 选择相片</a>
				</span></td>
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
			<s:iterator var="attach" value="attachList" status="st">
				<s:if test="#attach">
						<s:hidden name="photo2Id" value="%{attId}"/>	
						<tr>
						<td width="12%" align="center">附属图片</td>
						<td width="38%" align="left">
						<span class="updatePic2"><s:hidden name="updatePic2" value=""/></span>
						<span class="old">
							<img alt="${filename }" src="<s:property value="picUrl(5)"/>">
							<input type="button" value="重新上传" onclick="newUpload(this)">
						</span>
						<span class="new none">
							<s:textfield name="photo2" value="%{photo2[#st.index]}" id="picPath%{#st.index+1}" readonly="true"/><a
								class="various3" href="<s:url value="/user/album/albumshowUploadPage?valueId=picPath%{#st.index+1}"/>"> 选择相片</a>
						</span></td>
					</tr>
					</s:if>
					<s:else>
						<s:hidden name="updatePic2" value=""/>
						<s:hidden name="photo2Id" value=""/>
						<td width="12%" align="center">附属图片</td>
						<td width="38%" align="left"><s:textfield name="photo2" value="%{photo2[#st.index]}" id="picPath%{#st.index+1}" readonly="true"/><a
						class="various3" href="<s:url value="/user/album/albumshowUploadPage?valueId=picPath%{#st.index+1}"/>"> 选择相片</a></td>
						</tr>
					</s:else>
				</s:iterator>
			</table>
			<table width="100%" height="100%" border="0" cellpadding="0"
				cellspacing="1">
				<tr>
					<td width="10%" align="center" valign="top"><s:textfield name="detailTitle1" value="%{detailTitle1}" size="8"></s:textfield>
					<br/><span class="spanInfo">(注:标题为空时,不显示内容)</span></td>
					<td align="left" valign="top" width="88%"><s:textarea name="detail1"
						cssClass="ckeditor" rows="12" cols="95" cssStyle="width:89%;height:180px;"/></td>
				</tr>
			</table>
			<table width="100%" height="100%" border="0" cellpadding="0"
				cellspacing="1">
				<tr>
					<td width="10%" align="center" valign="top"><s:textfield name="detailTitle2" value="%{detailTitle2}" size="8"></s:textfield> 
					<br/><span class="spanInfo">(注:标题为空时,不显示内容)</span></td>
					<td align="left" valign="top" width="88%"><s:textarea name="detail2"
						cssClass="ckeditor" rows="12" cols="95" cssStyle="width:89%;height:180px;"/></td>
				</tr>
			</table>
			<table width="100%" height="100%" border="0" cellpadding="0"
				cellspacing="1">
				<tr>
					<td width="10%" align="center" valign="top"><s:textfield name="detailTitle3" size="8" value="%{detailTitle3}"></s:textfield>
					<br/><span class="spanInfo">(注:标题为空时,不显示内容)</span> </td>
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
								<s:iterator value="jsonDetail3" status="st">
									<tr align="center">
										<td><input name="paramKey" value="${key }" type="text" /></td>
										<td><input name="paramValue" value="${value }" type="text" /></td>
										<td><a href="javascript: void(0);" onclick="addRow(this)">添加</a>|<a href="javascript: void(0);" onclick="delRow(this)">删除</a></td>
									</tr>
								</s:iterator>
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
		<s:submit onclick="return checkFrm()" value="提交"/> <input type="button"  value="返回" onclick="javascript:history.go(-1);"/>
</s:form>
<div id="dialog" title="请选择分类" style="display: none">
	<s:action name="categoryInnerList" namespace="/user/product/category" executeResult="true" ignoreContextParams="true">
	</s:action>
</div>
</body>
</html>
