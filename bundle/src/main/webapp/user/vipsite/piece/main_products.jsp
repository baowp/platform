<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
	<s:if test="#request['products']==null">
		<s:action var="prodreq" name="*/pieceProducts" namespace="/vip" >
			<s:param name="enterpriseId" value="enterpriseId"/>
		</s:action>
	</s:if>
		<div id="products" class="bodyCont moveChild glare_type_1">
			<s:hidden name="piece" value="%{@com.abbcc.util.constant.layout.Piece@MAIN_PRODUCTS}"/>
			<div class="clr"></div>
			<div class="bodyContTitle">
				<span class="fl b titleLinkColor titleName" >
					<s:property value="layout.jsonContent['title'][@com.abbcc.util.constant.layout.Piece@MAIN_PRODUCTS.name()]||
						#request['moduleMap'][@com.abbcc.util.constant.layout.Piece@MAIN_PRODUCTS.name()]"/>
				</span>
				<s:if test="layout.belongPage!=@com.abbcc.util.constant.layout.BelongPage@PRODUCT">
					<a class="fr fs12px nb titleLinkColor draft_no_link" href="product">${lan['more']} &gt;&gt;</a>
				</s:if>
			</div>
			<div class="bodyContContent rightConteWidth rel">
				<s:hidden name="meta" cssClass="{currentPage:%{#request['products'].currentPage},pageCount:%{#request['products'].pageCount},parentValue:'%{#parameters['categoryId']}'}"/>
				<div class="glitzBody">
					<ul>
					<s:iterator value="#request.products.items">
						<li class="glitzItem ${loginView eq 1?'isLoged':''}">
							<div class="glitzPic glitzBorder glitzColor">
								<a href="product_detail?productId=${productId}" target="_blank">
									<img border="0" src="<s:property value="mainPic.picUrl(5)"/>" />
								</a>
								<div class="imgBorder"></div>
							</div>
							<div class="txt">
								<s:if test="#request.setting.pshow & 1">
									<div>
										<a class="topicLink draft_no_link" target="_blank" href="product_detail?productId=${productId}">${name}</a>
									</div>
								</s:if>
								<s:if test="#request.setting.pshow & 2">
									<div>
										<a class="topicLink draft_no_link" target="_blank" href="product_detail?productId=${productId}">${prodtype}</a>
									</div>
								</s:if>
							</div>
							<span class="price"></span>
						</li>
					</s:iterator>
					</ul>
				</div>
					<s:set name="pageList" value="#request['products']"/>
					<s:include value="../pagination.jsp"/>
			</div>
		</div>