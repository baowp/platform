<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台首页</title>
<link href="/user/css/homeiv.css" rel="stylesheet" type="text/css" />
<link href="/user/css/home.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div id="contain">
  <div id="h_top">
    <table width="100%" height="78" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td colspan="2"  width="8" rowspan="3">&nbsp;</td>
        <td align="left" class="font01">尊敬的客户：</td>
      </tr>
      <tr>
        <td align="left">&nbsp&nbsp
<s:if test="#session.abbccuser.grade==00">
				您目前是非付费用户<a
					href="<s:url value='/user/upgrade/show' />">点此升级为高级会员</a>
			</s:if>
			<s:if test="#session.abbccuser.grade==01">
				您目前是<font color="red">经典</font>会员, <s:if
					test="#session.memberState==03">
					<font color="red">您的帐号已过期,<a href="/user/upgrade/show">点此续费</a>
				</s:if>
			</s:if>
			<s:if test="#session.abbccuser.grade==02">
				您目前是<font color="red">精致</font>会员
			</s:if>
			<s:if test="#session.abbccuser.grade!=00">
服务时间为:<span style="color: rgb(0, 102, 204);">
<s:date name="%{#request.payUser.startTime}" format="yyyy-MM-dd" />
--
<s:date name="%{#request.payUser.endTime}" format="yyyy-MM-dd" /></span>,<s:if test="#session.memberState!=03">&nbsp距离到期时间还有<font color="red">${howdate}</font>天</s:if>
</s:if>

</td>
      </tr>

    </table>
  </div>
  <div id="buttom">
    <div class="left">
      <div class="h">
        <div class="h_header">
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="3"><img src="images/cl.png" width="3" height="28" /></td>
              <td align="left" background="images/cb.png" class="font01">&nbsp;消息中心</td>
              <td width="3"><img src="images/cr.png" width="3" height="28" /></td>
            </tr>
          </table>
        </div>
        <div class="h_center">
        <ul>
        <li>网站留言：<a href="message/messagesearchVisitorRecv">收到留言(<span class="errorSpan">${recvSysCount}</span>)</a>
			<a href="message/messagesearchVisitorNotRead">未读留言(<span
				class="errorSpan">${notReadSysCount}</span>)</a></li>
        <li>站内留言：<a
				href="message/messagesearchRecv">收到留言(<span class="errorSpan">${recvCount}</span>)</a><a
				href="message/messagesearchSend">发出留言(<span class="errorSpan">${sendCount}</span>)</a><a
				href="message/messagesearchNotRead">未读留言(<span class="errorSpan">${notReadCount}</span>)</a></li>
        </ul>
        </div>
      </div>
      <div class="h_bottom">
              <div class="h_header">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="3"><img src="images/cl.png" width="3" height="28" /></td>
                    <td align="left" background="images/cb.png" class="font01">&nbsp;重要提醒(${hintCount})</td>
                    <td width="3"><img src="images/cr.png" width="3" height="28" /></td>
                  </tr>
                </table>
              </div>
              <div class="h_centers">
 <s:if test="%{#request.hintCount==0}">
		<br><br>
		<center><h1>暂时没有提示信息!</h1></center>
		</s:if><s:else>
		<ul>
			<s:iterator value="%{#request.hintList}">
				<s:property escape="false" />
			</s:iterator>
		</ul>
		</s:else>
              </div>
            </div>
    </div>
    <div class="right">
      <div class="right01">
        <div class="h_header">
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="3"><img src="images/cl.png" width="3" height="28" /></td>
              <td align="left" background="images/cb.png" class="font01">&nbsp;最新公告</td>
              <td width="3"><img src="images/cr.png" width="3" height="28" /></td>
            </tr>
          </table>
        </div>
        <div class="h_centerss">
          <ul>
          <s:iterator var="user" value="%{#session.announceList}" status="st">
          <s:if test="%{#st.index<6}">
          	<li><span class="font02">&#8226;</span>&nbsp<a href="/user/news/announceshow?id=${newsId}" target="main">${title}</a><s:if test="%{#st.index<3}"><span><img height="10" width="16" src="abbcc/images/new_l_1231470880480.gif"></img></span></s:if> </li>
		  </s:if>
		  </s:iterator>
          </ul>
        </div>
      </div>
      <div class="right01">
              <div class="h_header">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="3"><img src="images/cl.png" width="3" height="28" /></td>
                    <td align="left" background="images/cb.png" class="font01">&nbsp;您的网站</td>
                    <td width="3"><img src="images/cr.png" width="3" height="28" /></td>
                  </tr>
                </table>
              </div>
              <div class="h_centerssd">
              <ul class="site">
              <li><span class="font02">&#8226;</span>免费网站:<a href="http://${sessionScope.abbccuser.domain}/site/${sessionScope.abbccuser.username}" target="blank">点此访问</a></li>
              <li><span class="font02">&#8226;</span>旺铺网站:<s:if test="#session.abbccuser.grade==00">
		无--><a href="<s:url value='/user/upgrade/show' />">点此升级</a>
			</s:if><s:else><a href="http://${sessionScope.abbccuser.username}.${sessionScope.abbccuser.domain}" target="blank">点此访问</a></s:else></li>
		<li class="vn"><span class="font02">&#8226;</span>绑定域名:<s:if test="#session.abbccuser.grade==00">
		无--><a href="<s:url value='/user/upgrade/show' />">点此升级</a>
			</s:if><s:elseif test="#request.bind!=null"><s:property value="%{#request.bind.address}"/></s:elseif></li>
              </ul>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>
