<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看客户</title>
<link href="/user/help/style/textd.css" rel="stylesheet" type="text/css" />
<s:include value="../product/product_style.jsp" />
<script type="text/javascript" src="<s:url value='/js/datePicker/WdatePicker.js'/>"></script>
<script type="text/javascript" src="news.js"></script>
</head>

<body>
<s:include value="/common/resultMessage.jsp"></s:include>
<div id="cpcontainer" class="container">
<div class="fv">
<input type="button" onclick="window.location.href='/user/news/newsaddPage'" value="添加新闻"/>&nbsp;&nbsp;<input type="button" onclick="all_clear('ch')" value="批量显示"/>&nbsp;&nbsp;<input type="button" value="批量隐藏" onclick="all_close('ch')"/>&nbsp;&nbsp;<input type="button" onclick="all_del('ch')" value="批量删除"/>
&nbsp<a href="javascript:searchable()">筛选功能</a>
</div>
<div class="searchArea">
<s:form action="newssearch" namespace="/user/news" >
<div class="topq"><div class="gu">
  <div class="nv">标&nbsp;&nbsp;&nbsp;题：</div>
  <div class="bn">
    <input type="text" name="title" id="form05" />
  </div>
  <div class="nv">关&nbsp;键&nbsp;字：</div>
  <div class="bn">
    <input type="text" name="nkey" id="form05" />
  </div>  <div class="nv">所属分类：</div>
  <div class="bn">
    <s:select id="select02" list="#request['sortMap']" listKey="key"
						listValue="value" name="category" value="%{#request.categoryId}"/>
    </div>
  <div class="nv">发布时间：</div>
  <div class="bn">
    <input type="text" name="frontTime" id="form02" onfocus="WdatePicker()" cssClass="Wdate"/>
    <input type="text" name="backTime" id="form03"  onfocus="WdatePicker()" cssClass="Wdate"/>
  </div>
      <div class="nv">新闻类型：</div>
      <div class="bn">
    <s:select id="select03" list="@com.abbcc.module.userNews.DealType@values()"
		listKey="name()" name="dealType" value="dealType.name()" 
		headerKey="" headerValue="全部"/>
    </div>
      <div class="nv">新闻状态：</div>
      <div class="bns">
    <s:select list="@com.abbcc.module.userNews.DealState@values()"
		listKey="name()" name="dealState" value="dealState.name()" 
		headerKey="" headerValue="全部"/>
    </div>
    <div class="ty">
      <input type="submit" name="button6" id="button6" value="查询" />
    </div>
      </div>  
</div>
</s:form>
</div>
<table class="listTable">
<thead>
	<tr>
		<th><s:checkbox id="check" name="check" onclick="check_all(this,'ch')"/>全选</th>
		<th>标题</th>
		<th>栏目</th>
		<th>属性</th>
		<th>显示</th>
		<th>添加时间</th>
		<th>状态</th>
		<th>操作</td>
	</tr>
</thead>
<tbody>
<s:if test="pageList.totalCount==0">
	<tr><td align="center" colspan="8">没有相关数据!</td></tr>
</s:if>
	<s:iterator var="user" value="pageList.items" status="st">

		<tr>

			<td valign="middle" align="center"><s:checkbox fieldValue="%{newsId}" name="ch" id="ch"/></td>
			<td><a href="newsupdatePage?id=${newsId}">${title}</a></td>
			<td valign="middle" align="center"><s:property value="categoryName()" /></td>
			<td valign="middle" align="center"><a href="javascript:void(0)"
				onclick=setImagenews(this) newsId=${newsId
				}
				imagenews=${imagenews eq '01'?'02':'01'}>${imagenews eq '01'?'图片新闻':'一般新闻' }</a></td>
			<td valign="middle" align="center"><a id=display href="javascript:void(0)"
				onclick=setDisplay(this) newsId=${newsId
				}
				display=${display eq '01'?'02':'01'}>${display eq '01'?'显示':'隐藏' }</a></td>
			<td valign="middle" align="center"><s:date name="addTime" format="yyyy-MM-dd hh:mm:ss"/></td></td>
			<td valign="middle" align="center">${state eq '01'?'通过审核':'审核中'}</td>
			<td valign="middle" align="center"><a href="newsupdatePage?id=${newsId}">修改</a>|<a
				href="newsdel?id=${newsId}"
				onclick="javascript:return del()">删除</a></td>

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
	</tfoot>
</table>
</div>
</body>
</html>
