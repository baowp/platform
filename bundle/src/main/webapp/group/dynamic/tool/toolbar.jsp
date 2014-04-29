<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link type="text/css" href="/css/tabs/jq-tabs.css" rel="stylesheet" />
<link type="text/css" href="/group/dynamic/tool/css/tool.css" rel="stylesheet" />
<link type="text/css" href="/css/jquery/mlColorPicker.css" rel="stylesheet" />
<script type="text/javascript" src="/group/dynamic/tool/js/maintain.js"></script>
<script type="text/javascript" src="/group/dynamic/tool/js/mytheme.js"></script>
<script type="text/javascript" src="/group/dynamic/tool/js/onload.js"></script>
<script type="text/javascript" src="/group/dynamic/tool/js/tool.js"></script>
<script type="text/javascript" src="/group/dynamic/tool/js/grouptool.js"></script>
<script type="text/javascript" src="/group/dynamic/tool/js/upload.js"></script>
<script type="text/javascript" src="/group/dynamic/tool/js/effect.js"></script>
<script type="text/javascript" src="/group/dynamic/tool/js/edit.js"></script>
<script type="text/javascript" src="/group/dynamic/tool/js/visit.js"></script>
<script type="text/javascript" src="/js/jquery/mlColorPicker.js"></script>
<script type="text/javascript" src="/js/xheditor/xheditor-zh-cn.min.js"></script>
<jsp:include page="/js/util/onload_colorbox.jsp"></jsp:include>
<jsp:include page="/group/dynamic/tool/pattern/pattern.jsp"></jsp:include>
<div class="toolBar">
	<div class="toolBarTop">
		<span class="b fl ml10px noneie6">我的编辑</span>
		<!--[if IE 6]>
		<span class="b fl ml10px browserRecommend">
			推荐使用<a href="http://download.firefox.com.cn/releases/webins/4.0/zh-CN/Firefox-latest.exe">火狐浏览器</a>编辑
		</span>
		<![endif]-->
		<span class="fr pat">
		<input id="staticButton" onclick="maintain.publish()" type="button" class="staticPattern contentEdit mt3px" value="静态发布 ">
		</span>
		<span class="fr pat">
			<input id="saveButton"  onclick="maintain.save()" type="button" class="savePattern contentEdit mt3px" value="保存应用 ">
		</span>	
		
		<span class="fr pat">
		<input id="patButton" type="button" class="pattern contentEdit mt3px" value="缩略模式 ">
		</span>	
		
	</div>
	
	<div class="toolBarMiddle">
		<div class="fl rel l60">
			<img src="/group/dynamic/tool/images/log.gif" class="ml15px mt4px">
		</div>
		<div class="ctrlList fr">
			<div class="ctrlList_left fl"></div>
			<div class="ctrlList_right fl">
				<ul>
					<li class="fl lin250 mt3px">
						<a class="bg" onfocus="this.blur()" onclick="tool.expand('#setStyle');event.cancelBubble=true;return false;" href="">主题风格</a>
						<jsp:include page="edit.theme.jsp"/>
					</li>
					<li class="fl lin250 mt3px">
						<a class="bg" onfocus="this.blur()" onclick="tool.expand('#setStyleDetail');event.cancelBubble=true;return false;" href="">风格设计</a>
						<jsp:include page="edit.style.jsp"/>
					</li>
					<li class="fl lin250 mt3px">
						<a class="bg" onfocus="this.blur()" onclick="tool.expand('#setLayout');event.cancelBubble=true;return false;" href="">网站布局</a>
						<jsp:include page="edit.layout.jsp"/>
					</li>
					<li class="fl lin250 mt3px">
						<a class="bg" onfocus="this.blur()" onclick="tool.expand('#setAdvance');event.cancelBubble=true;return false;" href="">高级设置</a>
						<jsp:include page="edit.advance.jsp"/>
					</li>
<!-- 					<li class="fl lin250 mt3px"> -->
<!-- 						<a class="bg" onfocus="this.blur()" onclick="tool.expand('#setGaim');event.cancelBubble=true;return false;" href="">通讯设置</a> -->
<%-- 						<jsp:include page="edit.gaim.jsp"/> --%>
<!-- 					</li> -->
				</ul>
			</div>
		</div>
	</div>
</div>