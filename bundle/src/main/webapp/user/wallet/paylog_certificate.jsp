<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="/user/style/code.css" rel="stylesheet" type="text/css" />
<link type="text/css" href="<s:url value="/user/css/util.css"/>" rel="stylesheet" />
<link type="text/css" href="<s:url value="/css/jquery-ui.css"/>" rel="stylesheet" />
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery-ui.js"/>"></script>
<script type="text/javascript">
function showDialog(){
	$('#dialog').dialog('open');
	$('#dialog').dialog({
		width: 700,
		modal :true
	});
	$('#dialog').load("dialogRecords");
}
function goto(page){
	$('#dialog').load("dialogRecords?page="+page);
}
function choosePaylog(paylogId,documentId){
	$('#belongId').val(paylogId);
	$('#documentId').val(documentId);
	$('#dialog').dialog('close');
}
</script>
</head>

<body>
<div id="mn">
  <div class="top" style="display:block;">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="16" height="36" background="/user/images/left.jpg">&nbsp;</td>
        <td height="36" align="left" background="/user/images/center.jpg" class="font01">付费凭证</td>
        <td width="109" height="36" background="/user/images/right.jpg">&nbsp;</td>
      </tr>
    </table>
  </div>
  <div class="tan">
    <div class="tan_right">
      <div class="y">请填写真实资料</div>
      <div class="u">
	  <s:form namespace="/user/wallet/paylog" action="upload" enctype="multipart/form-data" method="post">
<s:hidden name="attach.belongId" id="belongId"/>
        <table width="100%" border="0" cellpadding="0">
          <tr>
            <td width="29%" height="32">文件说明</td>
            <td width="71%" height="32" align="left"><s:textfield cssClass="txt" name="attach.filedesc"/></td>
          </tr>
          <tr>
            <td height="32">所属记录</td>
            <td height="32" align="left"><s:textfield name="documentId" cssClass="txt" id="documentId" readonly="1" /><input type=button value=选择 onclick="showDialog()"/><span class="errorSpan">${errors["attach.belongId"][0]}</span></td>
          </tr>
          <tr>
            <td height="32">凭证上传</td>
            <td height="32" align="left"><s:file name="upload" cssClass="txt"/><input type=button value=删除 onclick="$(this).prev().val('')"/>
			<span class="errorSpan">${errors.upload[0] }</span></td>
          </tr>
          <tr>
            <td height="32">&nbsp;</td>
            <td height="32" align="left"><s:submit value="提交"/></td>
          </tr>
        </table>
		</s:form>
      </div>
    </div>
  </div>
</div>
<div id="dialog" title="付费记录" style="display: none;"></div>
</body>
</html>
