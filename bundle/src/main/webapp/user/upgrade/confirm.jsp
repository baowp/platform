<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>确认信息</title>
<link rel="stylesheet" rev="stylesheet"
	href="/user/upgrade/css/confirm.css" type="text/css">
	<link rel="stylesheet" type="text/css"
	href="/css/jquery-ui.min.css">
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/user/upgrade/js/confirm.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/jquery-ui.js"></script>
</head>
<body>
	<div id="confirm_main">
		<div id="confirm_left">
			<div class="cl_bank">
				<div class="pic">
					<img style="vertical-align: top; float: left;"
						src="/user/upgrade/images/left_bk.png" alt="提示"> <img
						src="/user/upgrade/images/agriculturalBank.png"
						style="float: left; vertical-align: middle; margin-top: 10px;" />
					<p>
						<span>开户： 永康市东方五金电子商务有限公司</span><br> <span>开户银行：中国农业银行永康支行金城分理处
						</span><br> <span>银行帐户：1962 5501 0400 0664 7</span>
					</p>

					<p>
						<span>开户： 农业银行</span><br> <span>卡号：6228 4803 8012 5934
							410 </span><br> <span>姓名： 陈春芳</span>
					</p>
					<div style="clear: both;">
						<img src="/user/upgrade/images/ConstructionBank.png"
							style="float: left; vertical-align: middle; margin-top: 10px; margin-left: 25px;" />
						<p>
							<span>开户： 建设银行</span><br> <span>卡号：4367 4214 6504
								7055 632 </span><br> <span>姓名： 陈春芳</span>
						</p>
					</div>
				</div>
			</div>
			<div class="cl_navigation">
				<ul>
					<li><img alt="01" src="/user/upgrade/images/number_gray01.png"><a
						href="/user/upgrade/versionselect"><span>版本选择</span> </a>
					</li>
					<li><img alt="->" src="/user/upgrade/images/point01.png"><img
						alt="02" src="/user/upgrade/images/number02.png"><span><b>确认信息</b>
					</span>
					</li>
					<li><img alt="->" src="/user/upgrade/images/point02.png"><img
						alt="03" src="/user/upgrade/images/number_gray03.png"><span>去银行（柜台或网站）付款</span>
					</li>

				</ul>
			</div>
			<div class="cl_content">
			<div class="ccl">
					<s:hidden name="grade" />
					<s:hidden name="pageType" value="01" />
					<s:hidden name="id" value="%{#session.abbccuser.userId}"></s:hidden>
					<span>公司名称：</span><input type="text" maxlength="18" name="ename"
						value="${sessionScope.abbccEnterprise.name}" id="identityCard">
					<br /> <span>公司地址: </span><input type="text" maxlength="18"
						name="address" value="${sessionScope.abbccuser.address}"
						id="identityCard"><br /> <span>您的职位: </span><input
						type="text" maxlength="18" name="position"
						value="${sessionScope.abbccuser.position}" id="identityCard"><br />
					<span>用户姓名：</span><input type="text" maxlength="18" name="uname"
						value="${sessionScope.abbccuser.name}" id="identityCard"><br />
					<span>联系电话：</span><input type="text" maxlength="18"
						name="cellphone" value="${sessionScope.abbccuser.cellphone}"
						id="identityCard"><br /> <span>固定电话:</span> <input
						type="text" maxlength="18" name="phone"
						value="${sessionScope.abbccuser.phone}" id="identityCard"><br />
					<span>购买年数:</span> <input name="year" value="01" checked="checked"
						type="radio" id="price1"> 一年&nbsp;&nbsp;&nbsp;&nbsp; <input
						name="year" value="02" type="radio" id="price2"> 两年 <input
						type="submit" value="下一步" id="apply"/>
				</div>
				<div class="ccr">
					<ul>
						<li>运营服务：0579-87171989 0579-83840778<br> 0579-88026855</li>
				
						<li>技术支持：0579-83840669 传 真：0579-87173500</li>
					</ul>
				</div>
			</div>
			<div class="cl_bottom">
				<div class="bt_bar"></div>
				<div class="tab_content">
					<div style="float: left; width: 360px;">
						<h4 style="color: #0C61CF; float: left; margin: 0.8em 0;">成为东方五金会员,您将拥有以下超值服务</h4>
						<ul>
							<li>具有个性化网站</li>
							<li>全方位超酷炫展示及智能化职守！炫铺一开，生意就来！</li>
							<li>免费域名绑定</li>
							<li>全部生成静态化，加快用户访问速度！</li>
							<li>独享买家信息，领先竞争对手与买家取得联系！</li>
						</ul>
					</div>

					<div class="help">
						<div class="help_head">
							<img src="/user/upgrade/images/help.jpg" />
						</div>
						<div class="help_content">
							<ul>
								<li>有哪些原因会造成扣款不成功？</li>
								<li></li>
								<li></li>
							</ul>

						</div>
					</div>
				</div>

			</div>
		</div>
		<div id="confirm_right">
			<div class="cr01"></div>
			<div class="cr02">
				<ul>
					<li><a href="http://51archetype.com/jingdian/cpjs.htm"
						target="_blank"><img src="/user/upgrade/images/fun01.jpg" />
							<p>
								<span>信息量不限，并将您</span><br /> <span>的产品发布到东方...</span>
							</p> </a>
					</li>
					<li><a href="h51archetype.comcc.net/jingdian/cpjs.htm"
						target="_blank"><img src="/user/upgrade/images/fun02.jpg" />
							<p>
								<span>超强的网站DIY设计。</span><br /> <span>在这里您可以...</span>
							</p> </a></li>
					<li><a h51archetype.com://51archetype.com/jingdian/cpjs.htm"
						target="_blank"><img src="/user/upgrade/images/fun03.jpg" />
							<p>
								<span>海量图片存储空间，</span><br /> <span>让您的产品图片...</span>
							</p> </a></li>
					<l51archetype.com="http://51archetype.com/jingdian/cpjs.htm"
						target="_blank"><img src="/user/upgrade/images/fun04.jpg" />
							<p>
								<span>精准营销，实时跟踪</span><br /> <span>来访者浏览情况...</span>
							</p> </a></li>
	51archetype.coma href="http://51archetype.com/jingdian/cpjs.htm"
						target="_blank"><img src="/user/upgrade/images/fun05.jpg" />
							<p>
								<span>您的营销做的更精</span><br /> <span>准，就赢得市场...</span>
							</p> </a></li>
				</ul>



				<table cellspacing="0" cellpadding="0" border="0" width="200">
					<tbody>
						<tr>
							<td valign="top">&nbsp;</td>
						</tr>
						<tr>
							<td valign="top">
								<table cellspacing="0" cellpadding="0" border="0" align="center"
									width="200">
									<tbody>
										<tr>
											<td height="28" align="center" width="131" valign="middle"><a
												target="blank"
												href="tencent://message/?uin=1004924027&amp;Site=ykit.net/taolitaowa&amp;Menu=yes"><img
													border="0" alt="请留言"
													src="http://wpa.qq.com/pa?p=1:1004924027:41">
											</a>
											</td>
											<td align="left" valign="middle"><a target="blank"
												href="tencent://message/?uin=1452425603&amp;Site=ykit.net/taolitaowa&amp;Menu=yes"><img
													border="0" alt="请留言"
													src="http://wpa.qq.com/pa?p=1:1452425603:41">
											</a>
											</td>
										</tr>
										<tr>
											<td height="18" align="center" valign="middle" class="c1"><span>市场经理</span>
											</td>
											<td align="left" valign="middle"><span class="c1">
													销售总监</span>
											</td>
										</tr>
										<tr>
											<td height="28" align="center" valign="middle"><a
												target="blank"
												href="tencent://message/?uin=9568027&amp;Site=ykit.net/taolitaowa&amp;Menu=yes"><img
													border="0" alt="请留言"
													src="http://wpa.qq.com/pa?p=1:9568027:41">
											</a>
											</td>
											<td align="left" width="158" valign="middle"><a
												target="blank"
												href="tencent://message/?uin=739284194&amp;Site=ykit.net/taolitaowa&amp;Menu=yes"><img
													border="0" alt="请留言"
													src="http://wpa.qq.com/pa?p=1:739284194:41">
											</a>
											</td>
										</tr>
										<tr>
											<td height="18" align="center" valign="middle" class="c1"><span>客服经理1</span>
											</td>
											<td align="left" valign="middle"><span class="c1">
													客服专员1</span>
											</td>
										</tr>
										<tr>
											<td height="28" align="center" valign="middle" class="c1"><a
												target="blank"
												href="tencent://message/?uin=1016768267&amp;Site=ykit.net/taolitaowa&amp;Menu=yes"><img
													border="0" alt="请留言"
													src="http://wpa.qq.com/pa?p=1:1016768267:41">
											</a>
											</td>
											<td align="left" valign="middle"><a target="blank"
												href="tencent://message/?uin=1121680019&amp;Site=ykit.net/taolitaowa&amp;Menu=yes"><img
													border="0" alt="请留言"
													src="http://wpa.qq.com/pa?p=1:1121680019:41">
											</a>
											</td>
										</tr>
										<tr>
											<td height="18" align="center" valign="middle" class="c1"><span>客服经理2</span>
											</td>
											<td align="left" valign="middle"><span class="c1">
													客服专员2</span>
											</td>
										</tr>
										<tr>
											<td height="28" align="center" valign="middle" class="c1"><a
												target="blank"
												href="tencent://message/?uin=625364522&amp;Site=ykit.net/taolitaowa&amp;Menu=yes"><img
													border="0" alt="请留言"
													src="http://wpa.qq.com/pa?p=1:625364522:41">
											</a>
											</td>
											<td align="left" valign="middle"><a target="blank"
												href="tencent://message/?uin=1258798049&amp;Site=ykit.net/taolitaowa&amp;Menu=yes"><img
													border="0" alt="请留言"
													src="http://wpa.qq.com/pa?p=1:1258798049:41">
											</a>
											</td>
										</tr>
										<tr>
											<td height="18" align="center" valign="middle" class="c1"><span>客服经理3</span>
											</td>
											<td align="left" valign="middle"><span class="c1">
													客服专员3</span>
											</td>
										</tr>
										<tr>
											<td height="28" align="center" valign="middle" class="c1"><a
												target="blank"
												href="tencent://message/?uin=1004924027&amp;Site=ykit.net/taolitaowa&amp;Menu=yes"><img
													border="0" alt="请留言"
													src="http://wpa.qq.com/pa?p=1:1004924027:41">
											</a>
											</td>
											<td align="left" valign="middle"><a target="blank"
												href="tencent://message/?uin=1009553990&amp;Site=ykit.net/taolitaowa&amp;Menu=yes"><img
													border="0" alt="请留言"
													src="http://wpa.qq.com/pa?p=1:1009553990:41">
											</a>
											</td>
										</tr>
										<tr>
											<td height="18" align="center" valign="middle" class="c1"><span>客服经理4</span>
											</td>
											<td align="left" valign="middle"><span class="c1">
													客服专员4</span>
											</td>
										</tr>
										<tr>
											<td height="18" align="center" valign="middle" class="c1">&nbsp;</td>
											<td valign="middle">&nbsp;</td>
										</tr>
									</tbody>
								</table></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>