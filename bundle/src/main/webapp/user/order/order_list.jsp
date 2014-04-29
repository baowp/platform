<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>abbcc</title>
<link href="/user/css/div.css" rel="stylesheet" type="text/css" />
<link href="/user/css/css.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"
	href="<s:url value="/user/abbcc/css/common.css"/>">
<link href="/user/css/operation.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<s:url value='/js/datePicker/WdatePicker.js'/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery/colorize.js"/>"></script>
<script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
<script type="text/javascript">
function onModify(obj){
	var $row=$(obj).parents("tr:first");
	$row.find("#modifyLink").css("display","none");
	$row.find("#cancelLink").css("display","inline");
	$row.find("#submitLink").css("visibility","visible");
	$row.find("#dealStateSpan").css("display","none");
	$row.find("select[name=dealState]").css("display","");
}
function onCancel(obj){
	var $row=$(obj).parents("tr:first");
	$row.find("#modifyLink").css("display","");
	$row.find("#cancelLink").css("display","none");
	$row.find("#submitLink").css('visibility','hidden');
	$row.find("#dealStateSpan").css("display","");
	$row.find("select[name=dealState]").css("display","none");
	$row.find("select[name=dealState]").val($row.attr("dealState"));
}
function modifyDealState(obj){
	var $row=$(obj).parents("tr:first");
	var $select=$row.find("select[name=dealState]");
	$.getJSON("dealState?randomNumber="+Math.random(), {
		id : $row.attr("orderId"),
		dealState : $select.val()
	}, function() {
		$row.attr("dealState",$select.val());
		$row.find("#dealStateSpan").html($select[0][$select[0].selectedIndex].text);
		onCancel(obj);
	});
}
</script>
</head>
<body>
<div>
<s:form action="list" namespace="/user/order" >
	<table width="76%" height="45" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td height="25" colspan="5" align="left" class="font05">&nbsp;</td>
      </tr>
      <tr>
        <td width="10%" height="25" align="left"><div class="footer01">产品名称</div></td>
        <td width="20%" height="25" align="left"><s:textfield name="model.product.name" cssClass="txt" /></td>
        <td width="10%" height="25" align="left"><div class="footer01">下单企业</div></td>
        <td width="18%" height="25" align="left"><s:textfield name="model.orderEnt.name" cssClass="txt"/></td>
        <td height="25" align="left">&nbsp;</td>
      </tr>
      <tr>
        <td height="25" align="left"><div class="footer01">下单日期</div></td>
        <td height="25" align="left"> <s:textfield name="frontTime" cssStyle="width:55px;"   onfocus="WdatePicker()" cssClass="Wdate"/>
        <s:textfield name="backTime" cssStyle="width:55px;" onfocus="WdatePicker()" cssClass="Wdate"/></td>
        <td height="25" align="left"><div class="footer01">订单状态</div></td>
        <td height="25" align="left"><table><tr><td> <s:select list="@com.abbcc.util.constant.OrderDealState@values()"
		listKey="name()" name="dealState" value="dealState.name()" 
		headerKey="" headerValue="全部"/></td><td align="center" width="75px">
        <s:submit value="提交"/></td></tr></table></td>
        <td height="25" align="left">&nbsp;</td>
      </tr>
    </table>
</s:form>
<br/>
<table class="listTable">
<thead>
	<tr><th></th>
		<th>订单产品</th><th>下单企业</th><th>下单日期</th>
		<th>单价(元)</th><th>数量</th><th>订单总额(元)</th>
		<th>订单状态</th>
		<th>操作</th>
	</tr>
	</thead>
	<s:if test="pageList.totalCount==0">
	<tr><td align="center" colspan="8">没有相关数据!</td></tr>
</s:if>
	<s:iterator value="pageList.items" status="st">
		<tr orderId=${orderId} dealState=${dealState}>
			<td><s:property value="#st.index+1"/></td>
			<td>${product.name }</td>
			<td>${orderEnt.name }</td>
			<td align="center"><s:date name="orderTime" format="yyyy-MM-dd"/></td>
			<td align="right"><s:property value="numFmt.format(product.price*1)"/></td>
			<td align="right">${orderSum }</td>
			<td align="right"><s:property value="numFmt.format(product.price*orderSum)"/></td>
			<td width="120"><span id="dealStateSpan" style=""><s:property value="dealState"/></span>
				<s:select list="@com.abbcc.util.constant.OrderDealState@values()"
					listKey="name()" name="dealState" value="dealState.name()" 
					cssStyle="display:none;"/>
				<a href="javascript:void(0)" id=submitLink onclick="modifyDealState(this)" style="visibility: hidden;">提交</a>
			</td>
			<td>
				<a href="javascript:void(0)" id=modifyLink onclick="onModify(this)">修改状态</a>
				<a href="javascript:void(0)" id=cancelLink onclick="onCancel(this)" style="display: none">取消</a>
				<a href="view?id=${orderId }">查看</a>
			</td>
		</tr>
	</s:iterator>
</table>
<div style="padding:5px;text-align: right">
	<s:include value="/common/page.jsp"/>
</div></div>
</body>
</html>
