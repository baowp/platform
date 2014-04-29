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
							<h3>联系方式</h3>
						</div>
						<div class="bd">
							<div id=person-info>
								<ul>
									<li><span>姓名：</span><em>${name }</em></li>
									<li><span>所属企业：</span><em>${enterprise.name }</em></li>
									<li><span>职务：</span><em>${position }</em></li>
									<li><span>电话：</span><em>${phone }</em></li>
									<li><span>手机：</span><em>${cellphone }</em></li>
									<li><span>传真：</span><em>${fax }</em></li>
									<li><span>邮箱：</span><em>${email }</em></li>
									<li><span>QQ：</span><em>${qq }</em></li>
									<li><span>所在地：</span><em>${dist }</em></li>
									<li><span>详细地址：</span><em>${address }</em></li>
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