<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<s:include value="page_style.jsp"/>
<title>${enterprise.name }</title>
</head>
<body>
<div id=page>
<div id=content class=eshop>
<s:include value="page_front.jsp"/>	
<div id=bd>
	<div class="grid-c2">
		<div class="col-main">
			<div class="main-wrap">
				<div class="box">
					<div class="">
						<div class="hd">
							<h3>公司介绍</h3>
						</div>
						<div class="bd">
							<div id=enterprise-info>
								${enterprise.edesc }
							</div>
						</div>
					</div>
				</div>
				
				<div class="box">
					<div class="">
						<div class="hd">
							<h3>详细信息</h3>
						</div>
						<div class="bd">
							<div id=enterprise-info>
								<ul>
									<li><span>企业名称：</span><em>${enterprise.name }</em></li>
									<li><span>企业类型：</span><em>${enterprise.type }</em></li>
									<li><span>主营行业：</span><em>${enterprise.industry }</em></li>
									<li><span>主营产品或服务：</span><em>${enterprise.mainBusiness }</em></li>
									<li><span>法定代表/负责人：</span><em>${enterprise.legalPre }</em></li>
									<li><span>注册资金：</span><em>${enterprise.registeredCapital }</em></li>
									<li><span>开户银行：</span><em>${enterprise.bank }</em></li>
									<li><span>公司注册地：</span><em>${enterprise.dist }</em></li>
									<li><span>经营地址：</span><em>${enterprise.address }</em></li>
									<li><span>成立时间：</span><em>${enterprise.regTime }</em></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>		
		</div>
		
		<s:include value="page_side.jsp"/>
	</div>
</div>
</div>
</div>
<s:include value="page_message.jsp"/>
</body>
</html>