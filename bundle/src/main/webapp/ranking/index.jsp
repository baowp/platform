<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ABBCC排行榜</title>
<link href="/ranking/style/css.css" rel="stylesheet"  type="text/css" />
<link href="/ranking/style/div.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/ranking/js/top.js"></script>
<script type="text/javascript" src="/ranking/js/index.js"></script>
</head>

<body>
<div id="t">
 <div id="header">
   <div class="header_left"><img src="/ranking/images/logo.png" width="173" height="49" /></div>
   <div class="header_right">
     <div class="searc_top">
           <ul>
             <li class="jn" id="news">资讯</li>
             <li class="ut" id="product">产品</li>
             <li class="jn" id="enterprise">公司</li>
             <li class="jn" id="supply">求购</li>
        </ul>
      </div>
      <div class="login">
      <s:if test="#session.loginuserid!=null">
		<a href="/user/platform/index.jsp">用户中心</a> | <a href="<s:url value="/user/userLogout"/>?pageType='index'"	onClick="return logout();">注销</a>
	</s:if>
	<s:else>
		<a href="/user/login.jsp">登陆</a> | <a href="<s:url value="/user/reg.jsp"/>">注册</a>
	</s:else>
      </div>
      <div class="searc_center">
      <s:form action="byNameSearch" namespace="/"
	name="form1" id="form1">
        <table width="100%" border="0" align="left" cellpadding="0">
          <tr>
          <th><s:hidden name="pageTypeHidden" id="pageTypeHidden"/></th>
            <th width="50%" align="left">
              <s:textfield name="entName" id="form1"/>
            </th>
            <th width="50%" align="left">
              <input type="submit" name="button" id="button1" value="ABBCC搜索" />
            </th>
          </tr>
        </table>
        </s:form>
      </div>
      </div>
 </div>
  <div id="left">
  <ul>
  <li class="h">所有排行榜</li>
  <li class="p">产品排行</li>
  <li class="p">公司排行</li>
  </ul>
  </div>
  <div class="jk">
  <div id="contain">
    <div class="ht"></div>
    <div class="gh">
      <div class="f">
      <ul>
      <li>产品排行</li>
      <li>公司排行</li>
      </ul>
      </div>
      <div class="contain_b">
        <div class="left_l">
          <s:include value="/ranking/action/cp.jsp"></s:include>
        </div>
        <div class="left_l">
        	<s:include value="/ranking/action/gs.jsp"></s:include>
        </div>
      </div>
    </div>
  </div>
  <div class="bottom_right"><img src="/ranking/images/fe.jpg" width="790" height="67" border="0" /></div>
  </div>
  <div class="copyright">
    <div id="t2">
      <div>经营许可证编号：浙B2-20050229</div>
    </div>
  </div>
  </div>
</body>
</html>

