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
					<div class="supply-info">
						<div class="hd">
							<h3>供求信息</h3>
						</div>
						<div class="bd">
							<div id="supply-info" class="grid-supply hslice">
								<div>
								<ul class="list-title entry-content">									
									<li class=li-pic>图片</li><li class=li-name>供求信息</li><li class=li-price>价格</li>
									<li class=li-start>开始时间</li><li class=li-end>结束时间</li>
								</ul>
								<s:iterator value="supplies.items">
								<ul class="list-content">
									<li class=li-pic>
										<div>
											<a><img src="<s:url value="/picture.gif"/>"/></a>
										</div>
									</li>
									<li class="li-name unify"><a href="<s:url value="/site/"/>${model.username}/supply_detail?supplyId=${supplyId}">[<s:property value="type"/>] ${title}</a></li>
									<li class="li-price unify">${empty price?"面议":price }</li>
									<li class="li-start unify"><s:date name="startTime" format="yyyy年MM月dd日 hh:mm"/></li>
									<li class="li-end unify"><s:date name="endTime" format="yyyy年MM月dd日 hh:mm"/></li>
								</ul>
								</s:iterator>
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