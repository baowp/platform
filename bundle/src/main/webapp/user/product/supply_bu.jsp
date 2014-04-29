<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${supplyinfo.title}</title>
<link href="/css/jquery-ui.min.css" rel="stylesheet" />
<script type="text/javascript" src="/js/jquery.min.js"></script>
<script type="text/javascript" src="/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/jquery/form.min.js"></script>
<script type="text/javascript" src="/user/product/js/quote.js"></script>
<link href="/css/dialog/jq_aero.css" rel="stylesheet" />
<!--[if IE 6]><script type="text/javascript" src="/css/dialog/aero_ie6.js"></script><![endif]-->
<script type="text/javascript">
	function openMessage(obj) {
		$("#hid_div").dialog('open');
		$("#hid_div").dialog(
				{
					modal : true,
					width : 900,
					title : "留言",
					autoOpen : true,
					buttons : {
						确认发送报价 : function() {
							$(".ui-button").blur();
							if(clickBtn()){
							$(".ui-button").attr("disabled","disabled");
							$("#quote1").ajaxSubmit(function(data){
								$(".ui-button").attr("disabled","false");
								info(data);
								$("#hid_div").dialog('close');
							})
						}}
					}
				})		
	}

</script>
<link type="text/css"
	href="<s:url value="/user/product/css/supply_bu.css"/>"
	rel="stylesheet">
</head>
<body>
	<div class="abbccbar lightgray">
		<div class="w970 c abbccbar-container">
			<div class="account-sign-status left h27">
				<ul>
					<li class="account-welcome">您好，<span id="accountId"><a
							target="_self"
							href=""
							class="account-signed"> ${sessionScope.abbccuser.name }</a> </span>&nbsp;
					</li>
					<li id="accountSignOut"><a title="退出"
						href="">退出</a>
					</li>
				</ul>
			</div>
			
		</div>
	</div>
	<div class="h100 offer-head">
		<div class="w970 h100 offer-head-content">
			<div class="left h100 offer-head-logo">
				<img src="/images/logot.jpg" alt="东方五金" class="mt29">
			</div>
			<div class="left h100 offer-head-push">求购推送</div>
		</div>
	</div>

	<div class="offer-main w970 c o">
		<div class="h27 t title">
			<h4>${supplyinfo.title}</h4>
		</div>
		<div class="content">
			<div class="h320 offer-attr">
				<div class="left offer-attr-l">
					<ul>
						<li><img src="/user/product/images/supply_bg.jpg"
							class="productimg">
						</li>
						<li><img src="/user/product/images/star.jpg" class="v"/> <a>收藏此信息</a>
						</li>
					</ul>
				</div>
				<div class="o left offer-attr-m">
					<ul>
						<li>产品名称：<span>${supplyinfo.title}</span></li>
						<li>价格要求：<span>${supplyinfo.price}</span></li>
						<li>发布日期：<span><s:date format="yyyy-MM-dd" name="%{#request.supplyinfo.addTime}"></s:date></span></li>
						<li>过期日期：<span><s:date format="yyyy-MM-dd" name="%{#request.supplyinfo.endTime}"></s:date></span></li>
					</ul>
					<div class="lightgray p10">
						作为普通会员，您有<span>3</span>次报价机会，目前还剩<span>3</span>次机会
					</div>
					<input type="button" id="quote" name="quote" onclick="openMessage(this)"/>
				</div>

				<div class="left offer-attr-l">
					<div class="o member-basic-inner">
						<div class="m-title">
							<h2 class="left brown f14 b ml10">买家信息</h2>
							<span class="right write f12 mr40">${sessionScope.abbccuser.grade=='00'?'普通用户':'高级用户'} &nbsp;&nbsp;</span>
						</div>
						<div class="o m-con">
							<s:if test="#session.abbccuser.grade=='00'">
							<div class="o mm-bd no-login">
								<div class="f12 free-mm-tips">
									<p>您是普通会员，无法查看买家联系方式</p>
								</div>
								<p class="f12 mm-p">
									升级为高级会员，即可享受查看买家联系方式特权，直接与买家洽谈。<br /> <a title="升级为高级会员"
										class="block pl5 cxt-up" target="_blank"
										href="/user/upgrade/show">升级为高级会员</a>
								</p>
								<p class="mm-p">咨询热线：400-809-5188</p>
								<p id="J_aliService2TP" class="o mm-p">
									<a href="tencent://message/?uin=83503261&amp;Site=www.tair.com.cn&amp;Menu=yes" target="blank"><img height="29" width="68" border="0" src="http://wpa.qq.com/pa?p=1:83503261:6" alt="请留言"></a>
								</p>
							</div>
							</s:if>
							<s:else>
								<div class="o mm-bd">
									<ul class="f12 mm-contact ">
										<li>公司名称：${enterpriseInfo.name }</li>
										<li>公司类型：${type=='00'?'个人用户':type=='01'?'有限责任公司':type=='02'?'个体经营':type=='03'?'事业单位或团体':'其它' }</li>
										<li>主营产品：${enterpriseInfo.mainBusiness }</li>
										<li>主营行业：${enterpriseInfo.industry }</li>
										
										<li>公司地址：${userInfo.address }</li>
										<li>联系人：${supplyinfo.contactId }</li>
										<li>联系方式：${supplyinfo.contact}</li>
									</ul>
								
								</div>
							</s:else>
						</div>
					</div>
				</div>
			</div>

			<div class="p10 o offer-desc-box">
				<div class="h27 od-hd">
					<h2 class="left brown f14 b ml10">详细信息</h2>
				</div>
				<div class="od-bd">
					<table class="offer-key-attr">
						<tbody>
							<tr>
								<td class="b t f12 f-name">产品名称</td>
								<td class="pl5">${supplyinfo.title}</td>
								<td class="b t f12 f-name">价格要求</td>
								<td class="pl5">${supplyinfo.price}元</td>
							</tr>
							<tr>
								<td class="b t f12 f-name">发布日期</td>
								<td class="pl5"><s:date format="yyyy-MM-dd" name="%{#request.supplyinfo.addTime}"></s:date></td>
								<td class="b t f12 f-name">到期日期</td>
								<td class="pl5"><s:date format="yyyy-MM-dd" name="%{#request.supplyinfo.endTime}"></s:date></td>
							</tr>
						</tbody>
					</table>

					<div class="f12 mt10">
						<font color="red">${supplyinfo.wdesc }</font>
					</div>
					<div class="contact-widget">
						<a id="contact" name="contact"></a>
						<div class="h30 write f14 contact-widget-hd">
							<div class="contact-widget-wrap">
								<h4>联系方式</h4>
							</div>
						</div>
						<s:if test="#session.abbccuser.grade=='00'">
						<div class="p10 contact-widget-bd">
							<div class="c notp-tips">
								<p class="f12 notp-tips-b">升级为高级会员，即可享受查看买家联系方式特权，直接与买家洽谈。</p>
								<p class="pl20 btn-box">
									<a title="升级为高级会员" class="block cxt-up" target="_blank"
										href="/user/upgrade/show">升级为高级会员</a>
								</p>
							</div>
							
						</div>
						</s:if>
						<s:else>
								<div class="p10 contact-widget-bd">
									<ul class="f12 mm-contact ">
										<li>联系人：${supplyinfo.contactId }</li>
										<li>联系电话：${userInfo.phone}&nbsp;${userInfo.cellphone}</li>
										<li>联系地址：${userInfo.address }</li>
									</ul>
								
								</div>
							</s:else>
					</div>
					<div class="fav2report">
						<div class="c w200">
						<a dmt="bofferd_fav2" class="block left favorite-link"
							target="_blank" href="">收藏此信息</a> <a dmt="bofferd_flag"
							class="block left report-link" target="_blank" href="">举报此信息</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="f12 c product-statement-widget">
				<span></span>
				<p>免责声明： 以上所展示的信息由会员自行提供，内容的真实性、准确性和合法性由发布会员负责。东方五金对此不承担任何责任。 </p>
				<p>友情提醒：为规避购买风险，建议您在购买相关产品前务必确认供应商资质及产品质量。</p>
	</div>
	<div id="hid_div" style="display:none;" class="quoteMsg">
		<s:form id="quote1" action="quotePrice" namespace="/inquiry" >
		<s:hidden name="type" value="02"/>
		<s:hidden name="supplyId" value="%{#request.supplyinfo.supplyId}"/>
		
		<div class="m10">
		<div class="f16 lightblue"><span>以下是您对</span><span  class="orange">"${supplyinfo.title}"</span><span>信息的留言</span></div>
		<div class="msgtitle orange pl25"><div class="msgtitle_m"><span>联系信息</span></div></div>
			<ul class="msgcont">
				<li><em>买家将通过以下信息与您联系，请确认属实。这些信息同时将更新您的会员资料：</em></li>
				<li><span>姓名：<em class="red">* </em></span><s:textfield cssClass="w200" name="personName" id="personName" value="%{#session.abbccuser.name}"/><em class="red" id="errorPersonName"></em></li>
				<li><span>公司名：<em class="red">*</em></span><s:textfield cssClass="w200" name="entName" id="entName" value="%{#session.abbccEnterprise.name}"/><em class="red" id="errorEntName"></em></li>
				<li><span>电话：<em class="red">*</em></span><s:textfield cssClass="w200" name="phone" id="phone" value="%{#session.abbccuser.phone}"/><em class="red" id="errorPhone"></em></li>
				<li><span>手机：</span><s:textfield cssClass="w200" name="cellphone" id="cellphone" value="%{#session.abbccuser.cellphone}"/></li>
				<li><span>主营产品：<em class="red">*</em></span><s:textfield cssClass="w200" name="mainBusiness" id="mainBusiness" value="%{#session.abbccEnterprise.mainBusiness}"/><em class="red" id="errorMainBusiness"></em></li>
			</ul>
		<div class="msgtitle orange pl25"><div class="msgtitle_m"><span>留言信息</span></div></div>
			<ul class="msgcont">
				<li><span>主题：<em class="red">*</em> </span><s:textfield cssClass="w400"  name="title" id="title" value="我对您在东方五金发布的“%{#request.supplyinfo.title}”很感兴趣！"/><em class="red" id="errorTitle"></em></li>
				<li><span>主要内容：<em class="red">*</em><br/>( 限1500字 )</span><s:textarea cssClass="msgcontent" name="added" id="added"/><em class="red" id="errorAdded"></em></li>
			</ul>
		</div>
		<input type="button" name="dd" value="dfdf" ></input>
		
		</s:form>
	</div>
</body>
</html>
