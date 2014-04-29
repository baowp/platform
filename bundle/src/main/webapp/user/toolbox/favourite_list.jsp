<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" href="<s:url value="/css/jquery-ui.css"/>"
	rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="<s:url value="/user/abbcc/css/common.css"/>">
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery-ui.js"/>"></script>
<link href="/css/dialog/jq_aero.css" rel="stylesheet" />
<script type="text/javascript" src="/js/jquery/colorize.js"></script>
<script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
<!--[if IE 6]><script type="text/javascript" src="/css/dialog/aero_ie6.js"></script><![endif]-->
<title>abbcc</title>
<script type="text/javascript">
var pageType = "${pageType}";
</script>
<script type="text/javascript" src="/user/toolbox/favourite.js"></script>
</head>
<body>
<div id="body">
<div class="listTab">
<dl class="${pageType=='product'?'focus':'' }">
	<a
		href="${pageType!='product'?'/user/favour/list?pageType=product':'#' }">产品收藏</a>
</dl>
<dl class="${pageType=='ent'?'focus':'' }">
	<a href="${pageType!='ent'?'/user/favour/list?pageType=ent':'#' }">公司收藏</a>
</dl>
<a href="javascript:" id="contrast">对比选中</a>
</div>
<table class="listTable">
	<thead>
		<tr>
			<th><input type="checkbox" id="check" name="check" onclick="check_all(this,'ch')" />全选</th>
			<th>名称</th>
			<th>收藏时间</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="pageList.items" status="st">
			<tr>
				<td align="center"><s:checkbox fieldValue="%{favId}" name="ch"
					id="ch" /></td>
				<td class="names" id="<s:property value='#st.index+1'/>"
					pDiv="<s:property value='#st.index+100'/>"><s:if
					test="%{pageType==null || pageType=='product'}">
					<a href="/jump.html?url=<s:property value="product().productPath(belongId)"/> " target="_blank"><s:property value="product().name" /></a>
				</s:if><s:else>
				<a href="/jump.html<s:property value="ent().enterprisePath(belongId)"/> " target="_blank"><s:property value="ent().name" /></a>
				</s:else> <s:if test="%{pageType==null || pageType=='product'}">
					<input type="hidden" value="<s:property value="product().proPic"/>" id="img"/>
						<input type="hidden" value="<s:property value="product().name" />" id="proName"/>
						<input type="hidden" value="<s:property value="product().prodtype" />" id="proType"/>
						<input type="hidden" value="<s:property value="product().price" />" id="price"/>
						<input type="hidden" value="<s:property value="entByProId().name" />" id="entName"/>
						<input type="hidden" value="<s:property value="entByProId().businessType" />" id="businessType"/>
						<input type="hidden" value="<s:property value="entByProId().staffSum" />" id="staffSum"/>
						<input type="hidden" value="<s:property value="entByProId().registeredCapital" />" id="registeredCapital"/>
						<input type="hidden" value="<s:property value="entByProId().regTime" />" id="regTime"/>
						<input type="hidden" value="<s:property value="product().productPath(belongId)"/>" id="proHref"/>
					<div id="<s:property value='#st.index+100'/>"
						style="display: none;">
						<table><tr><td> <img alt=""
						src="<s:property value="product().proPic"/>"></td><td><p><h3>名称:<s:property value="product().name" /></h3></p><p>型号:<s:property value="product().prodtype" /></p><p>价格:<s:property value="product().price" /></p><p>电话:<s:property value="userByProId().phone" /></p></td></tr>
						<tr><td align="center"><p><s:property value="collectNumber()"/>人收藏</p></td><td>手机:<s:property value="userByProId().cellphone" /></td></tr>
						</table></div>
				</s:if>
				<s:else>
					<div id="<s:property value='#st.index+100'/>"
						style="display: none;">
						<table><tr><td><p><h3><s:property value="ent().name" /></h3></p><p><s:property value="ent().address" /></p><p>经营模式:<s:property value="ent().businessType"/></p><p>主营:<s:property value="ent().mainBusiness"/> </p><p><s:property value="collectNumber()"/> 人收藏</p> </td></tr> </table>	
					</div>
				</s:else> </td>
				<td align="center"><s:date name="addTime"
					format="yyyy-MM-dd hh:mm:ss" /></td>
				<td align="center"><a href="remove?id=${favId}">删除</a></td>
			</tr>
		</s:iterator>
	</tbody>
			<tfoot>
		<tr><th colspan="9"><s:include value="/common/page.jsp"/></th></tr>
	</tfoot>
</table>
</div>

</body>
</html>