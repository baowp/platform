<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link type="text/css" href="/user/vipsite/css/product.simple.css" rel="stylesheet" />
<script type="text/javascript" src="/user/vipsite/js/product.simple.js"></script>
<link type="text/css" href="/user/vipsite/css/product_detail1.css" rel="stylesheet" />
<script type="text/javascript" src="/user/vipsite/js/product_detail1.js"></script>
<script type="text/javascript">
productId="${product.productId}"
</script>
<div class="bodyCont moveChild mainTextColor">
	<s:hidden name="piece" value="%{@com.abbcc.util.constant.layout.Piece@PRODCUT_DETAIL1}"/>
	<div class="tab">
		<span class="b wangpuTopTitle">${product.name}</span><span style="font-size:10px; margin-left:10px;">(${lan['product.hits']}:<font color="red" id="viewsum">${viewsum}</font>,<a href="javascript:" id="collectByPro">${lan['contacts.collectByPro']}</a>)</span>&nbsp;
	</div>
	<div class="cpzs">
		<div class="cpzs_left">
			<div class="datu">
				<a href="#"><img class="divimg" src="<s:property value="product.mainPic.picUrl(7)"/>"></a>
        	</div>
		</div>
		<div class="cpzs_right">
			<div class="canshu">
				<div class="xinhao">${product.prodtype }</div>
				<div class="jiben"></div>
						</div>
			<ul class="jiaodu">
				<li>
				<div class="divImg glitzBorder glitzColor"><img id="0" src="<s:property value="product.mainPic.picUrl(8)"/>"></div>
				</li>
				<s:iterator value="product.attachList" status="st">
					<li>
					<div class="divImg glitzBorder glitzColor"><img id='<s:property value="#st.index+1"/>' src="<s:property value="picUrl(8)"/>"></div>
					</li>
				</s:iterator>
			</ul>
</div>
	</div>
	
	<div class="xxcs">
		<div class="xxcs_hard">
			<s:if test="product.detailTitle1 != null"><div flag="1" id="open" class="xntd glitzColor">${product.detailTitle1}</div></s:if>
	     	<s:if test="product.detailTitle2 != null"><div flag="2" id="colse" class="xntd glitzColor">${product.detailTitle2}</div></s:if>
	        <s:if test="product.detailTitle3 != null"><div flag="3" id="colse" class="xntd glitzColor">${product.detailTitle3}</div></s:if>
		</div>
		<s:if test="product.detail1 != null">
			<div flag="show1"  class="con_xn con glitzColor ">${product.detail1}</div>
		</s:if>
		<s:if test="product.detail2 != null">
			<div flag="show2"  class="con_xn con glitzColor " style="display: none;">${product.detail2}</div>
		</s:if>
		<s:if test="product.detail3 != null">
			<div flag="show3"  class="con_cs con glitzColor " style="display: none;">
				<table>
					<tbody>
						<tr>
							<td colspan="2" class="xinhao">型号: ${product.prodtype }</td>
						</tr>
					<s:iterator value="product.jsonDetail3" status="st">
									<tr>
										<td>${key }</td>
										<td>${value }</td>
									</tr>
					</s:iterator>
					</tbody>
				</table>
			</div>
		</s:if>
		</div>
		<jsp:include page="product_detail.bottom.jsp"/>
</div>