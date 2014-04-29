<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link type="text/css" href="<s:url value="/user/vipsite/tool/css/tool.css"/>" rel="stylesheet" />
<link type="text/css" href="<s:url value="/css/jquery/mlColorPicker.css"/>" rel="stylesheet" />
<script type="text/javascript" src="<s:url value="/user/vipsite/tool/js/maintain.js"/>"></script>
<script type="text/javascript" src="<s:url value="/user/vipsite/tool/js/mytheme.js"/>"></script>
<script type="text/javascript" src="<s:url value="/user/vipsite/tool/js/onload.js"/>"></script>
<script type="text/javascript" src="<s:url value="/user/vipsite/tool/js/tool.js"/>"></script>
<script type="text/javascript" src="<s:url value="/user/vipsite/tool/js/upload.js"/>"></script>
<script type="text/javascript" src="<s:url value="/user/vipsite/tool/js/effect.js"/>"></script>
<script type="text/javascript" src="<s:url value="/user/vipsite/tool/js/edit.js"/>"></script>
<script type="text/javascript" src="<s:url value="/user/vipsite/trace/js/gaim.js"/>"></script>
<script type="text/javascript" src="<s:url value="/user/vipsite/trace/js/visit.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery/mlColorPicker.js"/>"></script>
<script type="text/javascript" src="/js/xheditor/xheditor-zh-cn.min.js"></script>
<div class="toolBar">
	<div class="toolBarTop">
		<span class="b fl ml10px noneie6">我的编辑</span>
		<!--[if IE 6]>
		<span class="b fl ml10px browserRecommend">
			推荐使用<a href="http://download.firefox.com.cn/releases/webins/4.0/zh-CN/Firefox-latest.exe">火狐浏览器</a>编辑
		</span>
		<![endif]-->
		<span class="fr h30">
			<a href="javascript:saveLayout()">
				<img border="0" src="<s:url value="/user/images/vipsite/save.gif"/>" class="mt4px mr7px ffmb5px" >
			</a>
		</span>		
		<span class="fr h30"><input type=button value=内容布局 onclick="showManage();" class="contentEdit mr7px mt3px"/></span>
	</div>
	
	<div class="toolBarMiddle">
		<div class="fl rel l60">
			<img src="<s:url value="/user/vipsite/tool/images/log.gif"/>" class="ml15px mt4px">
		</div>
		<div class="ctrlList fr">
			<div class="ctrlList_left fl"></div>
			<div class="ctrlList_right fl">
				<ul>
					<li class="fl lin250 mt3px">
						<a class="bg" onfocus="this.blur()" onclick="tool.expand('#setStyle');event.cancelBubble=true;return false;" href="">主题风格</a>
						<s:include value="edit.theme.jsp"/>
					</li>
					<li class="fl lin250 mt3px">
						<a class="bg" onfocus="this.blur()" onclick="tool.expand('#setStyleDetail');event.cancelBubble=true;return false;" href="">风格设计</a>
						<s:include value="edit.style.jsp"/>
					</li>
					<li class="fl lin250 mt3px">
						<a class="bg" onfocus="this.blur()" onclick="tool.expand('#setLayout');event.cancelBubble=true;return false;" href="">网站布局</a>
						<s:include value="edit.layout.jsp"/>
					</li>
					<li class="fl lin250 mt3px">
						<a class="bg" onfocus="this.blur()" onclick="tool.expand('#setAdvance');event.cancelBubble=true;return false;" href="">高级设置</a>
						<s:include value="edit.advance.jsp"/>
					</li>
					<li class="fl lin250 mt3px">
						<a class="bg" onfocus="this.blur()" onclick="tool.expand('#setGaim');event.cancelBubble=true;return false;" href="">通讯设置</a>
						<s:include value="edit.gaim.jsp"/>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>