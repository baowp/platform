<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>用户管理</title>

<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>

<link rel="stylesheet" href="<s:url value="/css/jquery/style.css"/>"
	type="text/css" id="" media="print, projection, screen" />
<link rel="stylesheet" href="<s:url value="/admin/css/common.css"/>"
	type="text/css" />

<script type="text/javascript"
	src="<s:url value="/js/jquery/jquery-latest.js"/>"></script>
<script type="text/javascript"
	src="<s:url value="/js/jquery/jquery.tablesorter.js"/>"></script>
<script type="text/javascript" id="js">
	$(document).ready(function() { 
    $("table").tablesorter({widthFixed: true, widgets: ['zebra']}); 
});
</script>

<script type="text/javascript">
		$(document).ready(function() {
			$("#filter").click(function() {
				window.location="searchMember?searchWord="+$("#searchWord").val();
			});
		});
		function closeUser(){
			if (confirm('你确定要关闭此旺铺吗？关闭之后，此旺铺将不能被访问!') == true) {
				
				return true;
			} else {
				return false;
			}
		}
		function revertLayout(){
			if (confirm('你确定要还原该旺铺布局吗？还原之后，此旺铺将还原到初始状态!') == true) {
				return true;
			} else {
				return false;
			}
		}
</script>
<style>
table.tablesorter thead tr th, table.tablesorter tfoot tr th {
    background-color: #E6EEEE;
    border: 1px solid #FFFFFF;
    font-size: 10pt;
    padding: 4px;
}
table.tablesorter {
	background-color: #CDCDCD;
	font-family: arial;
	font-size: 10pt;
	margin: 10px 0 15px;
	text-align: left;
	width: 100%;
}
.header{
  text-align: center;
}
</style>
</head>
<body>
<s:form namespace="/admin" action="payMember" method="post" id="form1">
	<div id="nav">
	<ul>
		<li id="man_nav_1" class="bg_image_onclick">会员查询</li>
		<li><s:textfield label="用户名" theme="xhtml" name="username"></s:textfield><s:textfield
			label="备注" theme="xhtml" name="paydesc"></s:textfield>付费类型：<s:radio
			name="memberGrade" list="%{#{'01':'企业版','02':'集团版','11':'全部'}}"
			value="memberGrade" /> <s:submit value="确定" /></li>
	</ul>
	</div>
	<div id="sub_info">
	<div id="man_zone"><s:if test="result!=''&&result!=null">
		<div id="warning">${result}</div>
	</s:if>
	<table width="99%" border="0" align="center" cellpadding="3"
		cellspacing="1" id="tablesorter" class="tablesorter">
		<thead>
			<tr>
				<th class="header" name="username">用户名</th>
				<th class="header" name="grade">付费等级</th>
				<th class="header" name="payStart">付费开始</th>
				<th class="header" name="payEnd">付费到期</th>
				<th class="header" name="enterprise">企业名称</th>
				<!-- <th class="header" name="cellphone">手机</th> -->
				<th class="header">操作</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator var="user" value="pageList.items">
				<tr>
					<td><a href="infoMember?id=${userId}">${username }</a></td>
					<td>${grade=='01'?'企业版':grade=='02'?'集团版':'未定义' }</td>
					<td><s:property value="payStart()" /></td>
					<td><s:property value="payEnd()" /></td>
					<td align="left"><s:property value="enterprise().name" /></td>
					<!-- <td><s:property value="cellphone" /></td> -->
					<td><s:url id="url" action="viewPaylog">
						<s:param name="payuserId">
							<s:property value="userId" />
						</s:param>
					</s:url> <s:a href="%{url}">付费记录</s:a>/<a
						href="/admin/closeUser?userId=${userId}"
						onclick="return closeUser()">关闭旺铺</a> /<a
						href="/admin/revertLayout?userId=${userId}"
						onclick="return revertLayout()">还原布局</a></td>
				</tr>
			</s:iterator>

		</tbody>
		<tfoot>
			<tr>
				<th colspan="10" class="tb_search"><s:include
					value="../../common/page.jsp">
					<s:param name="pageList" value="pageList" />
				</s:include></th>
			</tr>
			<tr>
				<th style="text-align: left"><img src="images/refresh.png"
					alt="刷新" title="刷新" onclick="window.location.reload()" /></th>
				<th colspan="9" class="tb_search">查询：<s:textfield
					id="searchWord" name="searchWord" />
				<button type="button" id="filter">提交</button>
				</th>
			</tr>
		</tfoot>
	</table>
	</div>
	</div>
</s:form>
</body>
</html>
