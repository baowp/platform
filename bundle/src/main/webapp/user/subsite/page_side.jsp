<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
	<div class="col-sub">
			<div class="box">
				<div class="category-nav">
					<div class="hd">
						<h3>产品分类</h3>
					</div>
					<div class="bd">
						<div id="category-nav">
							<ul>
							<s:iterator value="#request.categoryList">
								<li>
									<img src="<s:url value="/user/images/subsite/icon_arrow11_right_08x.gif"/> "/>
									<span>
									<a class="topicLink draft_no_link" href="<s:url value="/site/"/>${model.username}/product?categoryId=${categoryId}">${name }</a>
									</span>
								</li>
							</s:iterator>
							</ul>
						</div>
					</div>
				</div>
			</div>
			
			<div class="box">
				<div class="shop-info">
					<div class="hd">
						<h3>联系方式</h3>
					</div>
					<div class="bd">
						<div id="shop-info" class="shop-intro-v2 hslice">
							<ul>
								<li><h2>${name}</h2></li>
								<li><span>电话：</span><em>${phone}</em></li>
								<li><span>手机：</span><em>${cellphone }</em></li>
								<li><span>传真：</span><em>${fax }</em></li>
								<li><span>邮箱：</span><em>${email }</em></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>