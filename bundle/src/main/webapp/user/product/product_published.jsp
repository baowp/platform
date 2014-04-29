<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>已经发布上网的产品</title>
<s:include value="product_style.jsp" />
<script type="text/javascript">
	function sort(type) {
		$("#sortType").val(type);
		$("#hideType").val($("#type").val())
		$("#sortForm").submit();
	}
</script>
</head>
<body>
<s:include value="/common/resultMessage.jsp"></s:include>
<div class="listTab">
<dl class="focus">
	已发布上网
</dl>
<dl>
	<a href="auditing">审核中</a>
</dl>
<dl>
	<a href="unapprove">审核未通过</a>
</dl>
<dl>
	<a href="expired">已过期</a>
</dl>
<div class="searchable"><a href="javascript:searchable()"
	onfocus="blur()">筛选功能</a></div>
</div>
<s:form namespace="/user/product" action="sortPublished" id="sortForm">
	<s:hidden name="category"></s:hidden>
	<s:hidden name="type" id="hideType"></s:hidden>
	<s:hidden name="sortType" id="sortType"></s:hidden>
</s:form>
<s:form namespace="/user/product" action="published">
	<s:hidden name="page" value="%{pageList.currentPage}" />
	<div class="searchArea">
	<ul>
		<li><s:action namespace="/user/product/category"
			name="expandCategory" ignoreContextParams="true" /> <s:select
			name="category" value="category" list="#request['expandCategory']"
			listKey="categoryId" listValue="cdesc+name" headerKey=""
			headerValue="所有分类" theme="xhtml" label="产品分类" /></li>
		<li><s:select name="type" value="type.name()" id="type"
			list="@com.abbcc.util.constant.ProductType@values()" listKey="name()"
			headerKey="" headerValue="所有类型" theme="xhtml" label="产品类型" /></li>
		<li><s:textfield label="名称/型号" name="model.name" theme="xhtml" />
		</li>
		<li><s:select name="ads" list="#{0:'否',1:'是'}" headerKey=""
			headerValue="" theme="xhtml" label="广告产品" /></li>
		<li><s:submit value="查询" /></li>
	</ul>
	</div>
</s:form>
<table class="listTable">
	<thead>
		<tr>
			<th width="20%">图片</th>
			<th width="">标题</th>
			<th>型号</th>
			<th width="">发布时间</th>
			<th>调序(<a href="javascript:sort(1)">升/降</a>)</th>
			<th>移序</th>
			<th>插序</th>
			<th>操作</th>
		</tr>
	</thead>
	<s:if test="pageList.totalCount==0">
		<tr>
			<td align="center" colspan="7">没有相关数据!</td>
		</tr>
	</s:if>
	<s:iterator value="pageList.items" status="st">
		<tr data-sort='${sort }'>

			<td align="center"><s:action name="fetchAttach" var="att"
				ignoreContextParams="true">
				<s:param name="belongId" value="productId" />
				<s:param name="filedesc"
					value="@com.abbcc.common.CommonConst@ATTACHPICMAIN" />
			</s:action> <img src="<s:property value="%{#att.resultList[0].picUrl(5)}" />"
				width="100" height="90" /></td>
			<td><s:property value="name" /></td>
			<td><s:property value="prodtype" /></td>
			<td align="center" id="publishTime"><s:date name="publishTime" />
			</td>
			<td align="center"><s:select name="sort" value="productId"
				onchange="change(this)" list="#request['sortMap']" /></td>
			<td align="center"><a href="javascript:" onclick="stepUp(this)">上移</a>
			<a href="javascript:" onclick="stepDown(this)">下移</a></td>
			<td align="center"><s:select name="sort" value="productId"
				onchange="arrange(this)" list="#request['sortMap']" /></td>
			<td align="center"><a
				href="edit?id=${productId }&back=showpublished">修改</a>|<a
				href="javascript:" onclick="setDisplay(this)"
				class="isdisplay {productId:'${productId}',isdisplay:'${isdisplay}'}">${isdisplay
			eq 1?'已显示':'已隐藏' }</a>|<a href="javascript:void(0)"
				onclick="fleshTime(this)">刷新</a> <input type=hidden name="productId"
				value='${productId}' />|<a
				href="remove?id=${productId }&back=showpublished" class="remove">删除</a>
			</td>
		</tr>
	</s:iterator>

</table>
<s:include
	value="/common/page.jsp">
<s:param name="urlArgs" value="'category='+(category||'')+'&type='+(type||'')+'&'"></s:param>	
</s:include>
</body>
</html>