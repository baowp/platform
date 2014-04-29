<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<s:include value="page_style.jsp"/>
<title>${product.name }-${enterprise.name }</title>
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
					<div class="product-detail">
						<div class="hd" style="display: none">
							<h3>产品供应</h3>
						</div>
						<div class="bd">
							<div id="product-detail" class="grid-detail hslice">
								<div class="detailTitle">
									<span class="b titleName detailTopTitle">${product.name}</span>
								</div>
								<div class="showcase">
									<div class="sliderWrap">
										<div class="pic">
											<img src="<s:property value="product.mainPic.picUrl(4)"/>" height="170">
										</div>
										<div class="product-name">
											${product.name }
										</div>
									</div>
									<ul id="detailCtn">
										<li class="row4 row7">
										<p>所在地： <span class="wordwarp">${enterprise.address}</span></p>
										
										<p>结束时间：<span><s:date name="product.unpublishTime" format="yyyy年MM月dd日 hh:mm"/></span></p>
										
										<a target="_blank" class="nopaycontact" onmouseover="$(this).css('background-position','-100px -310px');"
											onmouseout="$(this).css('background-position','0 -310px');"
												href="<s:url value="/site/"/>${username}/person">联系方式</a>
										
										</li>
									</ul>
								</div>
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