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
					<div class="shop-recommend">
						<div class="hd">
							<h3>产品展示</h3>
						</div>
						<div class="bd">
							<div id="shop-recommend" class="grid hslice">
								<ul class="shop-list entry-content">
								<s:iterator value="products.items">
									<li>
										<div class="item">
											<div class="pic">
											<a href="<s:url value="/site/"/>${model.username}/product_detail?productId=${productId}" target="">
											<img src="<s:url value="%{attachList[0].serverPath}"/>" />
											</a>
											</div>
											<div class="desc">
											<a class="permalink" href="<s:url value="/site/"/>${model.username}/product_detail?productId=${productId}" > ${name } </a>
											</div>
											<div class="price" style="display:none;">
											<span>一口价</span>											
											<strong>50.00元</strong>
											</div>
										</div>
									</li>
								</s:iterator>
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