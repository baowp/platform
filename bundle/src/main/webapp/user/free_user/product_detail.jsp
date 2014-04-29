<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${ent.name}-产品信息</title>
<s:include value="include/page_style.jsp"></s:include>
<script type="text/javascript">
$(function(){
	$.ajax({
		   type: "POST",
		   url: "/product/addNumber",
		   data: "id=${product.productId}",
		   success: function(msg){
		     $(".viewNumber").html(msg)
		   }
	});
})
</script>
</head>

<body>
<s:include value="include/head.jsp"></s:include>
<div id="nb">
  
<s:include value="include/page_front.jsp"/>
  <div class="yu">
  <div class="left">
  <div class="left_top">产品分类</div>
  <s:include value="include/product_category.jsp"></s:include>
  </div>
  <div class="sou">
  <div class="sou_top">联系我们</div>
  <s:include value="include/contact.jsp"></s:include>
  </div>
  </div>
  <div class="hj">
  <div class="contain">
  <div class="contain_top">供应信息</div>
  <div class="contain_right">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="20%" height="30" align="center">${product.name}</td>
        <td width="80%" height="30">&nbsp;</td>
      </tr>
      <tr>
        <td height="30" align="center"><ul>
          <li>
            <p class="hn"><a href="#" target="_blank"><img src="<s:property value="product.mainPic.picUrl(5)"/>" width="90" height="92" border="0" /></a></p>
            </li>
        </ul>
        </td>
        <td height="30"><table  border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="24"><strong>供货总量：</strong></td>
            <td height="24" align="left">1000套</td>
          </tr>
          <tr>
            <td height="24"><strong>产品单价：</strong></td>
            <td height="24" align="left">${product.price }</td>
          </tr>
          <tr>
            <td height="24"><strong>发布日期：</strong></td>
            <td height="24" align="left"><s:date name="product.publishTime" format="yy-MM-dd"/></td>
          </tr>
          <tr>
            <td height="24"><strong>过期时间：</strong></td>
            <td height="24" align="left">${product.unpublishTime}</td>
          </tr>
          <tr>
            <td height="24"><strong>浏览次数：</strong></td>
            <td height="24" align="left"><font class="viewNumber" color="red">${product.viewsum}</font></td>
          </tr>
        </table></td>
      </tr>
    </table>
  </div>
  </div>
  <div class="contain">
  <div class="contain_top">详细信息</div>
  <div class="contain_right020">
    ${product.proddesc}
    </div>
  </div>
  <div class="advav"><img src="/user/free_user/images/ban3.jpg" width="706" height="70" /></div>
  </div>
</div>
<s:include value="include/footer.jsp"></s:include>
</body>
</html>
