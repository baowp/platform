<%@ page language="java" import="java.util.*,com.abbcc.models.*"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>基本信息管理</title>
<link id="artDialogSkin" href="/js/artDialog3.0.5/skin/default.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/artDialog3.0.5/artDialog.min.js"></script>	
<link href="/user/css/css.css" rel="stylesheet" type="text/css" />
<link href="/user/css/div.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"
	href="<s:url value='/user/css/validation.css'/>"><script
	type="text/javascript"
	src="<s:url value='/js/datePicker/WdatePicker.js'/>"></script> <script
	type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<link href="/user/css/operation.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
<style>
.nobr br {
	display: none
}
</style>
</head>

<body>
<script>
	$(function() {
		$("#submit").click(function(){
			if(checkValue()){
				$.getJSON("/user/enterprise/company/updateInformation?randomNumber=" + Math.random(), {
					id : $("#entId").val(),mainBusiness:$("#mainBusiness").val(),industry:$("#industry").val(),businessType:$("#businessType").val(),type:$("#type").val(),dist:$("#dist").val(),regTime:$("#regTime").val(),legalPre:$("#legalPre").val(),staffSum:$("#staffSum").val(),brand:$("#brand").val(),registeredCapital:$("#registeredCapital").val(),customer:$("#customer").val(),market:$("#market").val(),bank:$("#bank").val(),rdSum:$("#rdSum").val(),monthProd:$("#monthProd").val(),qualityControl:$("#qualityControl").val(),qasyscert:$("#qasyscert").val(),icp:$("#icp").val(),eAddress:$("#eAddress").val(),oem:$("#oem").val(),eAddress2:$("#eAddress2").val()
					,annualTurnover:$("input[name='annualTurnover']").val(),annualExport:$("input[name='annualExport']").val(),annualImport:$("input[name='annualImport']").val(),bankAccount:$("input[name='bankAccount']").val(),factoryArea:$("input[name='factoryArea']").val()
				}, function(result) {
					art.dialog.tips('修改成功！', 1);
					art.dialog.parent.location.reload();
					art.dialog.close(); 
					
				});
			}
		})
	})
	function checkValue(){
		if (isNull($("#mainBusiness").val())) {
			art.dialog.tips('主营行业不能为空！', 1);
			toError($("#mainBusiness"), '主营行业不能为空')
			return false;
		}else{
			$("#mainBusiness").parent().find(".errorSpan").html('');
		}
		if (isNull($("#regTime").val())) {
			art.dialog.tips('请选择公司成立时间！', 1);
			toError($("#regTime"), '请选择公司成立时间')
			return false;
		}else{
			$("#regTime").parent().find(".errorSpan").html('');
		}
		if (isNull($("#legalPre").val())) {
			art.dialog.tips('请输入法定代表人！', 1);
			toError($("#legalPre"), '请输入法定代表人')
			return false;
		}else{
			$("#legalPre").parent().find(".errorSpan").html('');
		}
		if (isNull($("#staffSum").val())) {
			art.dialog.tips('请输入公司员工数量！', 1);
			toError($("#staffSum"), '请输入公司员工数量')
			return false;
		}else{
			$("#staffSum").parent().find(".errorSpan").html('');
		}

		return true;
	}
	function isNull(str) {
		if (str.trim() == '')
			return true;
		else
			return false;
	}
	function toError(obj, str) {
		$(obj).parent().find(".errorSpan").html(str);
	}
</script>
<s:form namespace="/user/enterprise/company" action="updateInformation"
	id="form1" method="post">
	<s:hidden name="id" id="entId" value="%{enterpriseId}" />
	<table width="95%" border="0" cellpadding="0" class="tb tb2 fixpadding">
		<tbody>
			<tr class="hover">
				<td width="13%" class="header"><span class="font03">*</span>主营产品</td>
				<td width="34%" align="left"><span class="vtop rowform">
				<s:textfield name="mainBusiness" id="mainBusiness" cssClass="txt" />
				<span class="errorSpan">${errors.mainBusiness[0]}</span> </span></td>
				<td width="13%" class="header">&nbsp;&nbsp;主营行业</td>
				<td colspan="2" align="left"><span class="vtop rowform">
				<s:select list="#request['industyMap']" listKey="key"
					listValue="value" name="industry" id="industry" /> </span></td>
			</tr>
			<tr class="hover">
				<td class="header"><span class="font03">*</span>经营模式</td>
				<td align="left"><span class="vtop rowform"> <s:select
					list="#request['BusinessTypeMap']" listKey="key" listValue="value"
					name="businessType" id="businessType" /> </span></td>
				<td class="header">&nbsp;&nbsp;企业类型</td>
				<td colspan="2" align="left"><span class="vtop rowform">
				<s:select list="#request['typeMap']" listKey="key" listValue="value"
					name="type" id="type" /> </span></td>
			</tr>
			<tr class="hover">
				<td class="header">公司注册地</td>
				<td align="left"><span class="vtop rowform"> <s:textfield
					name="dist" id="dist" cssClass="txt" /> </span></td>
				<td>&nbsp;<span class="header">经营地点</span></td>
				<td colspan="2" align="left"><span class="nobr"> <s:doubleselect
					name="eAddress" id="eAddress" list="#request['sysMap'].keySet()"
					listKey="syscodeId" listValue="name" doubleId="eAddress2" value="%{#request.addressOne}"
					doubleName="eAddress2" doubleList="#request['sysMap'].get(top)"
					doubleListKey="syscodeId" labelposition="left"
					doubleListValue="name" doubleValue="%{#request.addressTwo}"
					theme="simple" /> </span></td>
			</tr>
			<tr class="hover">
				<td class="header"><span class="font03">*</span>成立时间</td>
				<td align="left"><span class="vtop rowform"> <s:textfield
					name="regTime" id="regTime" onfocus="WdatePicker()"
					cssClass="Wdate" readonly="readonly" /></span><span class="errorSpan">${errors.regTime[0]}</span></td>
				<td><span class="font03"><strong>*</strong></span><span
					class="header">法人代表</span></td>
				<td colspan="2" align="left"><span class="vtop rowform">
				<s:textfield name="legalPre" id="legalPre" cssClass="txt" /><span
					class="errorSpan">${errors.legalPre[0]}</span> </span></td>
			</tr>
			<tr class="hover">
				<td class="header">&nbsp;&nbsp;年营业额</td>
				<td align="left"><span class="vtop rowform"> <s:textfield
					name="annualTurnover"
					onkeyup="this.value=this.value.replace(/\D/g,'')"
					onafterpaste="this.value=this.value.replace(/\D/g,'')"
					cssClass="txt" /><span class="errorSpan">${errors.annualTurnover[0]}</span>
				</span></td>
				<td class="header"><span class="font03">*</span>员工人数</td>
				<td colspan="2" align="left"><span class="vtop rowform">
				<s:textfield name="staffSum" id="staffSum"
					onkeyup="this.value=this.value.replace(/\D/g,'')"
					onafterpaste="this.value=this.value.replace(/\D/g,'')"
					cssClass="txt" /><span class="errorSpan">${errors.staffSum[0]}</span>
				</span></td>
			</tr>
			<tr class="hover">
				<td class="header">&nbsp;&nbsp;经营品牌</td>
				<td align="left"><span class="vtop rowform"> <s:textfield
					name="brand" id="brand" cssClass="txt" /><span class="errorSpan">${errors.brand[0]}</span>
				</span></td>
				<td class="header"><span class="font03"></span>注册资本</td>
				<td colspan="2" align="left"><span class="vtop rowform">
				<s:textfield name="registeredCapital" id="registeredCapital"
					onkeyup="this.value=this.value.replace(/\D/g,'')"
					onafterpaste="this.value=this.value.replace(/\D/g,'')"
					cssClass="txt" /> </span><span class="errorSpan">${errors.registeredCapital[0]}</span></td>
			</tr>
			<tr class="hover">
				<td class="header">主要客户群</td>
				<td align="left"><span class="vtop rowform"> <s:textfield
					name="customer" id="customer" cssClass="txt" /><span
					class="errorSpan">${errors.customer[0]}</span> </span></td>
				<td class="header">&nbsp;&nbsp;主要市场</td>
				<td colspan="2" align="left"><span class="vtop rowform">
				<s:textfield name="market" id="market" cssClass="txt" /> </span></td>
			</tr>
			<tr class="hover">
				<td>&nbsp;&nbsp;<span class="header">年出口额</span></td>
				<td align="left"><span class="vtop rowform"> <s:textfield
					name="annualExport"
					onkeyup="this.value=this.value.replace(/\D/g,'')"
					onafterpaste="this.value=this.value.replace(/\D/g,'')"
					cssClass="txt" /><span class="errorSpan">${errors.annualExport[0]}</span>
				</span></td>
				<td class="header">&nbsp;&nbsp;年进口额</td>
				<td colspan="2" align="left"><span class="vtop rowform">
				<s:textfield name="annualImport"
					onkeyup="this.value=this.value.replace(/\D/g,'')"
					onafterpaste="this.value=this.value.replace(/\D/g,'')"
					cssClass="txt" /><span class="errorSpan">${errors.annualImport[0]}</span>
				</span></td>
			</tr>
			<tr>
				<td class="hover">&nbsp;<span class="header">&nbsp;开户银行</span></td>
				<td align="left" class="hover"><span class="vtop rowform">
				<s:textfield name="bank" id="bank" cssClass="txt" /><span
					class="errorSpan">${errors.bank[0]}</span> </span></td>
				<td class="header">&nbsp;&nbsp;&nbsp;帐&nbsp;号</td>
				<td colspan="2" align="left" class="hover"><span
					class="vtop rowform"> <s:textfield name="bankAccount"
					onkeyup="this.value=this.value.replace(/\D/g,'')"
					onafterpaste="this.value=this.value.replace(/\D/g,'')"
					cssClass="txt" /><span class="errorSpan">${errors.bankAccount[0]}</span>
				</span></td>
			</tr>
			<tr>
				<td class="hover">&nbsp;<span class="header">OEM</span></td>
				<td align="left" class="hover"><span class="vtop rowform">
					<select name="oem" id="oem">
					<s:if test="%{oem=='01'}">
					<option value="01" selected="selected">是</option>
						<option value="00">否</option>
					</s:if><s:else>
					<option value="01">是</option>
						<option value="00" selected="">否</option>
					</s:else>
						
					</select>
				</span></td>
				<td class="header"><span class="font03"></span>研发部门人数</td>
				<td colspan="2" align="left" class="hover"><span
					class="vtop rowform"> <s:textfield name="rdSum" id="rdSum"
					onkeyup="this.value=this.value.replace(/\D/g,'')"
					onafterpaste="this.value=this.value.replace(/\D/g,'')"
					cssClass="txt" /><span class="errorSpan">${errors.rdSum[0]}</span>
				</span></td>
			</tr>
			<tr>
				<td class="hover"><span class="font03"></span><span
					class="header">月产量</span></td>
				<td align="left" class="hover"><span class="vtop rowform">
				<s:textfield name="monthProd" id="monthProd"
					onkeyup="this.value=this.value.replace(/\D/g,'')"
					onafterpaste="this.value=this.value.replace(/\D/g,'')"
					cssClass="txt" /><span class="errorSpan">${errors.monthProd[0]}</span>
				</span></td>
				<td class="header">&nbsp;&nbsp;&nbsp;厂房面积</td>
				<td colspan="2" align="left" class="hover"><span
					class="vtop rowform"> <s:textfield name="factoryArea"
					onkeyup="this.value=this.value.replace(/\D/g,'')"
					onafterpaste="this.value=this.value.replace(/\D/g,'')"
					cssClass="txt" style="width:190px;"/>平方米<span class="errorSpan">${errors.factoryArea[0]}</span>
				</span></td>
			</tr>
			<tr>
				<td class="hover">&nbsp;&nbsp;<span class="header">质量控制</span></td>
				<td align="left" class="hover"><span class="vtop rowform">
				<s:textfield name="qualityControl" id="qualityControl"
					cssClass="txt" /><span class="errorSpan">${errors.qualityControl[0]}</span>
				</span></td>
				<td class="header">&nbsp;&nbsp;&nbsp;管理体系认证</td>
				<td colspan="2" align="left" class="hover"><span
					class="vtop rowform"> <s:textfield name="qasyscert"
					id="qasyscert" cssClass="txt" /><span class="errorSpan">${errors.qasysCert[0]}</span>
				</span></td>
			</tr>
			<tr>
				<td class="header">&nbsp;&nbsp;icp备案号</td>
				<td align="left" class="hover"><span class="vtop rowform">
				<s:textfield name="icp" id="icp" cssClass="txt" /> </span></td>
				<td class="header">&nbsp;</td>
				<td width="25%" class="hover"></td>
				<td width="15%" class="hover">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="13">
				<div class="fixsel"><input type="button" value="提交"
					id="submit" /></div>
				</td>
			</tr>
		</tbody>
	</table>
</s:form>
</body>
</html>
