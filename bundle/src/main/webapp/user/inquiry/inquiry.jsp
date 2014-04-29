<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>东方五金-议价单</title>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript"
	src="<s:url value='/js/datePicker/WdatePicker.js'/>"></script>
<script type="text/javascript">
$(function() {
	$("#autoSelect").change(function() {
		$("#added").val($(this).val() + $("#added").val())
	})
	$("#subButton").click(function() {
		if ($("#title").val() == '') {
			art.dialog.tips('请输入标题！', 1);
			return;
		}
		if ($("#userId").val() == '') {
			if ($("#name").val() == '') {
				art.dialog.tips('请输入姓名！', 1);
				return;
			}
			if ($("#email").val() == '') {
				art.dialog.tips('请输入电子邮箱，以方便卖家的回复!', 1);
				return;
			}
			if ($("#added").length > 200) {
				art.dialog.tips('补充问题请控制在200字以内!', 1);
				return;
			}
		}
		$.ajax({
			url : "/inquiry/save",
			dataType : "json",
			async : false, //不进行异步操作
			data : {
				title:$("#title").val(),
				recvEnt:$("#recvEnt").val(),
				productId:$("#productId").val(),
				count:$("#count").val(),
				price:$("#price").val(),
				requestInfo:$("#requestInfo").val(),
				added:$("#added").val(),
				endTime:$("#endTime").val(),
				enterpriseId:$("#userId").val(),
				companyName:$("#companyName").val(),
				email:$("#email").val(),
				phone:$("#phone").val(),
				name:$("#name").val()
			},
			success : function() {
				art.dialog.tips('询价成功，请等待商家回复！', 1);
				art.dialog.close();
			}
		})
	})
})
</script>
</head>
<body>


<table height="30" width="760" cellspacing="0" cellpadding="0"
	border="0" align="center"
	style="border-bottom: 1px solid rgb(153, 153, 153);">
	<tbody>
		<tr>
			<td valign="middle" bgcolor="#eb191d" align="center"
				style="font-size: 14px; color: rgb(255, 255, 255); font-weight: bold;">
			议价单</td>
		</tr>
	</tbody>
</table>
<table width="760" cellspacing="0" cellpadding="0" border="0"
	align="center">
	<tbody>
		<tr>
			<td>
			<table width="100%" cellspacing="0" cellpadding="0" border="0"
				style="margin-top: 4px; text-align: left;">
				<tbody>
					<tr>
						<td valign="top">
						<table width="500" cellspacing="0" cellpadding="0" border="0"
							style="margin: 0pt auto;">
							<tbody>
								<tr>
									<td width="110" align="center"><img height="100"
										width="100"
										src="${product.proPic }">
									</td>
									<td>
									<table width="100%" cellspacing="0" cellpadding="0"
										style="margin: 25px 0px;">
										<tbody>
											<tr>
												<td height="40" width="30%" align="right">货品名称：</td>
												<td width="70%"><a target="_blank" href="/jump.html?url=${product.url}">${product.name }</a></td>
											</tr>

											<tr>
												<td height="40" align="right">当 前 价：</td>
												<td>${product.price }</td>
											</tr>
											<tr>
											</tr>
											<tr>
												<td height="40" align="right">企业名称：</td>
												<td>
												<div style="width: 100%;">
												<dt style="float: left;"><a title="gdliangcai"
													target="_blank" href="<%=request.getParameter("entHref") %>">${ent.name}</a></dt>
												&nbsp;
												<dt style="float: left;"><span id="TmLayer0">
												
												</span></dt>
												
												</div>
												</td>
											</tr>
										</tbody>
									</table>
									</td>
								</tr>
							</tbody>
						</table>
						</td>
					</tr>
				</tbody>
			</table>

			<s:form action="save" namespace="/inquiry" id="inguiryForm">
			
			<div
				style="background-color: rgb(255, 245, 216); border: 1px solid rgb(255, 225, 138); padding: 10px;">

			<table cellspacing="0" cellpadding="0">
				<tbody>
					<tr>
						<td height="30">议价标题:&nbsp;<font color="red">*</font>&nbsp;</td>
						<td><input hckeyworddisp="留言标题" id="title"
							value="我对您在东方五金网发布的“${product.name}”很感兴趣" name="title"
							size="70" maxlength="50"></td>
					</tr>
					<tr>
						<td height="30">订货总量：</td>
						<td><input value="" name="count" id="count" maxlength="10" size="12">
						个 ( 1 个 起购 ，可购 100000 个 )</td>
					</tr>
					<tr>
						<td height="30">期望价格：</td>
						<td><input value="" name="price" id="price" maxlength="10" size="12">
						元 (单价)</td>
					</tr>
				</tbody>
			</table>
			</div>
			
			<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tbody>
					<tr>
						<td><strong>我想了解的产品信息有：</strong></td>
					</tr>
				</tbody>
			</table>
			<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tbody>
					<tr>
						<td><input type="checkbox" name="requestInfo" value="产品规格、型号">
						产品规格、型号 <input type="checkbox" name="requestInfo" value="价格条款">
						价格条款 <input type="checkbox" name="requestInfo" value="原产地">
						原产地 <input type="checkbox" name="requestInfo" value="能否提供样品">
						能否提供样品 <input type="checkbox" name="requestInfo" value="最小订货量">
						最小订货量 <input type="checkbox" name="requestInfo" value="交货期">
						交货期 <input type="checkbox" name="requestInfo" value="供货能力">
						供货能力 <input type="checkbox" name="requestInfo" value=" 销售条款及附加条件">
						销售条款及附加条件 <input type="checkbox" name="requestInfo" value="包装方式">
						包装方式 <input type="checkbox" name="requestInfo" value="质量/安全认证">
						质量/安全认证</td>
					</tr>
				</tbody>
			</table>
			<div align="left">
			·补充信息：<span class="STYLE1">(可在此进行补充)</span> 
			<select name="autoSelect" id="autoSelect" style="width: 400px;">
				<option selected="">请选择常用问题</option>
				<option value="我对贵公司的产品非常感兴趣，能否发一些详细资料给我参考？">我对贵公司的产品非常感兴趣，能否发一些详细资料给我参考？</option>
				<option value="请您发一份比较详细的产品规格说明，谢谢！">请您发一份比较详细的产品规格说明，谢谢！</option>
				<option value="请问贵公司产品是否可以代理？代理条件是什么？">请问贵公司产品是否可以代理？代理条件是什么？</option>
				<option value="您好，我公司对此产品的需求量较大，请问价格是否还能优惠？">您好，我公司对此产品的需求量较大，请问价格是否还能优惠？</option>
				<option value="您好，我公司对此产品长期有需求，希望建立合作关系，请问价格是否还能优惠？">您好，我公司对此产品长期有需求，希望建立合作关系，请问价格..</option>
			</select> <span class="STYLE1">(懒得打字？“快速提问”帮您忙！)</span> <textarea
				hckeyworddisp="补充信息" cols="87" rows="6" name="added" id="added"></textarea> 
			我希望
			<div style="position: absolute;" id="divStartDate"></div>
			<input name="endTime"  id="endTime" type="text" onfocus="WdatePicker()" class="Wdate" readonly="readonly" />
			日前回复！</div>
			<s:hidden name="enterpriseId" id="userId" value="%{#session.abbccuser.enterpriseId}"></s:hidden>
			<s:if test="%{#session.abbccuser==null}">
				<div class="bt">非会员补充信息</div>
				<table width="100%" cellspacing="0" cellpadding="5" border="0"
					align="center" style="border: 1px solid rgb(224, 224, 224);">
					<tbody>
						<tr bgcolor="#ffffff">
							<td nowrap="nowrap" class="C" colspan="3">
							<table width="100%" cellspacing="0" cellpadding="4" border="0">
								<tbody>
									<tr bgcolor="#fbfbfb">
										<td width="35%" bgcolor="#ffffff"><b>您的姓名：</b> <font
											color="red">*</font> <input hckeyworddisp="姓名" value=""
											name="name" size="16" id="name" maxlength="30"></td>
										<td width="65%" valign="top" bgcolor="#ffffff"><b>公司名称：</b>
										<input hckeyworddisp="公司名称" value="" name="companyName" id="companyName" size="16"
											maxlength="50"></td>
									</tr>
									<tr bgcolor="#fbfbfb">
										<td bgcolor="#ffffff"><b>电子邮箱：</b> <font color="red">*</font>
										<input value="" name="email" size="16" maxlength="50" id="email"></td>
										<td bgcolor="#ffffff"><b>联系电话：</b> <input
										    id="phone" value="" name="phone" size="16"
											maxlength="40"></td>
									</tr>
								</tbody>
								<tbody>
								</tbody>
							</table>
							</td>
						</tr>
					</tbody>
				</table>
			</s:if>
			
			<table width="230" cellspacing="0" cellpadding="0" border="0"
				align="center">
				<tbody>
					<tr>
						<td align="center"><input type="button" id="subButton" style="font-weight: bold; height: 36px;"
							 value="发送议价单 &gt;&gt;">&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
			<input type="hidden" name="recvEnt" id="recvEnt" value="${product.enterpriseId}"/>
			<input type="hidden"  name="productId" id="productId" value="${product.productId }"/>
			</s:form>
			</td>
		</tr>
	</tbody>
</table>

</body>
</html>