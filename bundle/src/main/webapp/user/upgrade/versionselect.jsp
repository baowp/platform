<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" rev="stylesheet"
	href="/user/upgrade/css/versionselect.css" type="text/css">
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$
								.ajax({
									type : "post",
									url : "/user/upgrade/isTrialByJson",
									data : "",
									success : function(data) {
										if (data == '1')
											$("#trial")
													.attr("src",
															"/user/upgrade/images/buy/experienced.jpg");
									}
								})
						$("#trial")
								.click(
										function() {
											if (confirm('确定免费试用?每个用户ID只有一次免费试用机会。') == true) {
												$("#trial").unbind("click");
											} else {
												return;
											}
											$("#trial")
													.attr("src",
															"/user/upgrade/images/buy/experienced.jpg");
											$.ajax({
												type : "post",
												url : "/user/upgrade/trial",
												data : "",
												success : function(data) {
													alert(data)
												}
											})
										})
					});
</script>
</head>
<body>
	<div class="versionselect">
		<div class="bordertop">
			<div class="bordertop_left"></div>
			<div class="bordertop_middle"></div>
			<div class="bordertop_right"></div>
		</div>

		<div class="borderhead">
			<div class="borderhead_left"></div>
			<div class="borderhead_middle"></div>
			<div class="borderhead_right"></div>
		</div>
		<div class="borderline">
			<div class="borderline_left"></div>
			<div class="borderline_middle"></div>
			<div class="borderline_right"></div>
		</div>
		<div class="main">
			<div class="main_left"></div>
			<div class="main_middle">
				<div class="cl_navigation">
					<ul>
						<li><img src="/user/upgrade/images/number01.png"
							alt="01"><span><b>版本选择</b></span>
						
						</li>
						<li><img src="/user/upgrade/images/point01.png" alt="-&gt;"><img
							src="/user/upgrade/images/number_gray02.png" alt="02"><span>确认信息
						</span>
						</li>
						<li><img src="/user/upgrade/images/point02.png" alt="-&gt;"><img
							src="/user/upgrade/images/number_gray03.png" alt="03"><span>去银行（柜台或网站）付款</span>
						</li>

					</ul>
				</div>
				<div class="versions">
					<ul>
						<li><img src="/user/upgrade/images/buy/buy09.jpg" id="trial"
							style="cursor: pointer;" /></li>
						<li><s:if test="#session.abbccuser.grade!=01"><a href="/user/upgrade/confirm?grade=01" garget="main"><img
								src="/user/upgrade/images/buy/buy10.jpg" />
						</a></s:if><s:else><img src="/user/upgrade/images/buy/buy10.jpg" /></s:else></li>
						<li><s:if test="#session.abbccuser.grade!=02"><a href="/user/upgrade/confirm?grade=02" garget="main"><img
								src="/user/upgrade/images/buy/buy11.jpg" />
						</a></s:if><s:else><img src="/user/upgrade/images/buy/buy11.jpg" /></s:else></li>
						<li><a href="javascript:void(0)"><img
								src="/user/upgrade/images/buy/buy12.jpg" />
						</a></li>
					</ul>
				</div>
			</div>
			<div class="main_right"></div>
		</div>
		<div class="bottom"></div>
	</div>
</body>
</html>