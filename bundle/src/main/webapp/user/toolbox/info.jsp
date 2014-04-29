<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产品信息对比</title>
<link type="text/css" href="/user/toolbox/css/info.css" rel="stylesheet" />
<style type="text/css">
body {
	font: normal 11px auto "Trebuchet MS", Verdana, Arial, Helvetica,
		sans-serif;
	color: #4f6b72;
	background: #E6EAE9;
}

a {
	color: #c75f3e;
}

#table {
	padding: 0;
	margin: 0;
}

caption {
	padding: 0 0 5px 0;
	width: 700px;
	font: italic 11px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
	text-align: right;
}

th {
	font: bold 11px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
	color: #4f6b72;
	border-right: 1px solid #C1DAD7;
	border-bottom: 1px solid #C1DAD7;
	border-top: 1px solid #C1DAD7;
	letter-spacing: 2px;
	text-transform: uppercase;
	text-align: center;
	padding: 6px 6px 6px 12px;
	background: #CAE8EA no-repeat;
}

th.nobg {
	border-top: 0;
	border-left: 0;
	border-right: 1px solid #C1DAD7;
	background: none;
}

td {
	border-right: 1px solid #C1DAD7;
	border-bottom: 1px solid #C1DAD7;
	background: #fff;
	font-size: 11px;
	padding: 6px 6px 6px 12px;
	color: #4f6b72;
}

td.alt {
	background: #F5FAFA;
	color: #797268;
}

th.spec {
	border-left: 1px solid #C1DAD7;
	border-top: 0;
	background: #fff no-repeat;
	font: bold 10px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
}

th.specalt {
	border-left: 1px solid #C1DAD7;
	border-top: 0;
	background: #f5fafa no-repeat;
	font: bold 10px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
	color: #797268;
}

/*---------for IE 5.x bug*/
html>body td {
	font-size: 11px;
}

body,td,th {
	font-family: 宋体, Arial;
	font-size: 12px;
}
</style>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/jquery/query.js"></script>
<script type="text/javascript" src="/user/toolbox/info.js"></script>
</head>
<body>
<table cellspacing="0" cellpadding="0" border="0" width="98%">
	<tbody>
		<tr>
			<td>
			<table cellspacing="0" cellpadding="0" border="0" align="center"
				width="100%">
				<tbody>
					<tr>
						<td bgcolor="#f6f6f6"
							style="border-top: #ff7300 2px solid; padding: 2 10px;" class="S">
						<table cellspacing="0" cellpadding="2" border="0" width="100%">
							<tbody>
								<tr>
									<td width="75%" style="padding: 5 0px" class="S lh15">欢迎您使用<font
										color="#FF0000">供应信息对比</font>功能
									<br>
									对比结果：候选供应信息(共<font color="#FF0000"><a id="displayNum">
									7 </a></font>条，您最多可以选择<font color="#FF0000"> 10 </font>条同时进行对比)</td>
									<td align="right" width="25%" class="S">&nbsp;</td>
								</tr>
							</tbody>
						</table>
						</td>
					</tr>
				</tbody>
			</table>
			<table cellspacing="0" cellpadding="0" border="0" width="100%">
				<tbody>
					<tr>
						<td height="25px" class="s"><font color="#cc3300">您选择的产品信息可能不属于同一类产品，因此下表仅为供应商对比。</font>
						</td>
					</tr>
				</tbody>
			</table>

			</td>
		</tr>
	</tbody>
</table>
<table id="table"></table>
</body>
</html>