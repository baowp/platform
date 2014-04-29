<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="/user/style/code.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<s:url value="/js/util/context.jsp"/>"></script>
<script type="text/javascript" src="<s:url value="/js/ckeditor/ckeditor.js"/>"></script>
<script type="text/javascript" src="<s:url value='/js/datePicker/WdatePicker.js'/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
</head>

<body>
<div id="mn">

  <div class="tan">
  <s:form namespace="/user/wallet/paylog" action="apply" >
    <table id="lu" width="100%" border="0" cellpadding="0">
      <tr>
        <td width="60" height="35" colspan="2" align="center" bgcolor="#F0F9FD">付费银行</td>
        <td  align="left">&nbsp;</td>
        <td  align="left"><s:textfield cssClass="txt" name="bank"/><span class="errorSpan">${errors.bank[0]}</span></td>
      </tr>
      <tr>
        <td height="35" colspan="2" align="center" bgcolor="#F0F9FD">银行账号</td>
        <td align="left">&nbsp;</td>
        <td align="left"><s:textfield name="bankAccount" cssClass="txt"/><span class="errorSpan">${errors.bankAccount[0]}</span></td>
      </tr>
      <tr>
        <td height="35" colspan="2" align="center" bgcolor="#F0F9FD">单&nbsp; 据 号</td>
        <td align="left">&nbsp;</td>
        <td align="left"><s:textfield name="documentId" cssClass="txt"/><span class="errorSpan">${errors.documentId[0]}</span></td>
      </tr>
      <tr>
        <td height="35" colspan="2" align="center" bgcolor="#F0F9FD">付费金额</td>
        <td align="left">&nbsp;</td>
        <td align="left"><s:textfield name="amount" cssClass="txt"/><span class="errorSpan">${errors.amount[0]}</span></td>
      </tr>
      <tr>
        <td height="35" colspan="2" align="center" bgcolor="#F0F9FD">开始时间</td>
        <td align="left">&nbsp;</td>
        <td align="left"><input type=text name="startTime" value="<s:date name="startTime" format="yyyy-MM-dd"/>"
				onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate" readonly/><span class="errorSpan">${errors.startTime[0]}</span></td>
      </tr>
      <tr>
        <td height="35" colspan="2" align="center" bgcolor="#F0F9FD">到期时间</td>
        <td align="left">&nbsp;</td>
        <td align="left"><input type=text name="endTime" value="<s:date name="endTime" format="yyyy-MM-dd"/>"
				onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate" readonly="readonly"/><span class="errorSpan">${errors.endTime[0]}</span></td>
      </tr>
      <tr>
        <td height="35" colspan="2" align="center" bgcolor="#F0F9FD">付费时间</td>
        <td align="left">&nbsp;</td>
        <td align="left"><input type=text name="payTime" value="<s:date name="payTime" format="yyyy-MM-dd"/>"
				onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate" readonly="readonly"/><span class="errorSpan">${errors.payTime[0]}</span></td>
      </tr>
      <tr>
        <td height="35" colspan="2" align="center" bgcolor="#F0F9FD">付费方式</td>
        <td align="left">&nbsp;</td>
        <td align="left"><s:select list="@com.abbcc.util.constant.PayType@values()" listKey="name()" name="type"/></td>
      </tr>
      <tr>
        <td height="35" colspan="2" align="center" bgcolor="#F0F9FD">详细说明</td>
        <td align="left">&nbsp;</td>
        <td align="left"><s:textarea name="memo" cssClass="ckeditor"/></td>
      </tr>
      <tr>
        <td height="35" colspan="2" align="center" bgcolor="#F0F9FD">&nbsp;</td>
        <td align="left">&nbsp;</td>
        <td align="left"><input type="submit" name="button" id="button" value="提交" />
            </td>
      </tr>
    </table>
	</s:form>
  </div>
</div>
</body>
</html>
