<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${ent.name}-联系方式</title>
<s:include value="include/page_style.jsp"></s:include>
</head>

<body>
<s:include value="include/head.jsp"></s:include>
<div id="nb"><s:include value="include/page_front.jsp" />
<div class="yu">
<div class="left">
<div class="left_top">产品分类</div>
<s:include value="include/product_category.jsp"></s:include></div>
<div class="sou">
<div class="sou_top">产品搜索</div>
<s:include value="include/product_search.jsp"></s:include>
</div>
</div>
<div class="hj">
<div class="company02">
<div class="company_top">联系我们</div>
<div class="company_cotain">
         <table width="100%" border="0" cellpadding="0">
         <s:iterator value="%{#request.contactList}" status="c">
         <tr>
            <td height="30" colspan="4" bgcolor="#BEEAFA">联系方式${c.index+1}</td>
          </tr>
         <tr>
            <td width="20%" height="30" align="center" bgcolor="#CCE4F7">姓&nbsp;&nbsp;&nbsp;&nbsp;名：</td>
            <td width="30%" height="30" align="left" bgcolor="#EFF9FC">${name}(${sex eq'00'?'男':'女'})</td>
            <td width="20%" height="30" align="center" bgcolor="#CCE4F7">职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位：</td>
            <td width="25%" height="30" align="left" bgcolor="#EFF9FC">${position}</td>
          </tr>
          <tr>
            <td height="30" align="center" bgcolor="#CCE4F7">电&nbsp;&nbsp;&nbsp;&nbsp;话：</td>
            <td height="30" align="left" bgcolor="#EFF9FC">${phone}</td>
            <td height="30" align="center" bgcolor="#CCE4F7">传&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;真：</td>
            <td height="30" align="left" bgcolor="#EFF9FC">${fax}</td>
          </tr>
          <tr>
            <td height="30" align="center" bgcolor="#CCE4F7">手&nbsp;&nbsp;&nbsp;&nbsp;机：</td>
            <td height="30" align="left" bgcolor="#EFF9FC">${cellphone}</td>
            <td height="30" align="center" bgcolor="#CCE4F7">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：</td>
            <td height="30" align="left" bgcolor="#EFF9FC">${email}</td>
          </tr>
         </s:iterator>
        </table>
</div>
</div>
<div class="advav"><img src="/user/free_user/images/ban3.jpg"
	width="706" height="70" /></div>
</div>
</div>
<s:include value="include/footer.jsp"></s:include>
</body>
</html>
