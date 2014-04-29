<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
	$(function() {
		$("#helpSubmit").click(function() {
			if ($("#form1").val() == '') {
				return;
			} else {
				$.ajax({
					type : "POST",
					url : "/message/helpMessage",
					data : "title="+$("#form1").val(),
					success : function(msg) {
						alert(msg);
					}
				});
			}

		})
	})
</script>
<div class="header">
<ul class="header_left">
	<li>你好，欢迎来到客服中心</li>
	<li></li>
	<li></li>
</ul>
<ul class="header_right">
	<li>加入收藏</li>
	<li>返回首页</li>
</ul>
</div>
<div class="logo">
<div class="logo_left"><a href="/"><img src="images/z1.jpg" width="115"
	height="40" border="0"/></a></div>
</div>
<div class="uh">
<div class="menuf">
<ul>
	<s:if test="#parameters.pageType[0]=='index'||#parameters.pageType[0]==''"><li class="menuf_s">首页</li></s:if><s:else><li class="menuf_l"><a href="index.jsp?pageType=index">首页</a></li></s:else>
	<s:if test="#parameters.pageType[0]=='zz'"><li class="menuf_s">自助服务</li></s:if><s:else><li class="menuf_l"><a href="customer.jsp?pageType=zz">自助服务</a></li></s:else>
</ul>
</div>
<div class="sou">
<table width="100%" border="0" cellpadding="0">
	<tr>
		<td height="3" colspan="4"></td>
	</tr>
	<tr>
		<td width="26%"><input type="text" name="title" id="form1" /></td>
		<td width="8%"><input type="button" name="button2"
			id="helpSubmit" value="我要提问" /></td>
		<td width="57%">&nbsp;</td>
	</tr>
</table>
</div>
</div>