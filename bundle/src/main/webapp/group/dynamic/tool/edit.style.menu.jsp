<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="uncheck" id="editMenu">
	<div class="title">
		<div class="titleList">
			<ul>
				<li><a onclick="tool.changeTo('#editTopicImg');" href="#">主题图片</a>
				</li>
				<li><a onclick="tool.changeTo('#editTopContent');" href="#">招牌风格</a>
				</li>
				<li><a class="b">栏目风格</a>
				</li>
				<li><a onclick="tool.changeTo('#editModule');" href="#">板块风格</a>
				</li>
				<li><a onclick="tool.changeTo('#editAll');" href="#">整体风格</a>
				</li>
			</ul>
			<div class="clr"></div>
		</div>
	</div>
	<div style="height: 405px;">
		<fieldset
			style="border: 1px solid rgb(211, 211, 211); padding: 5px 0px 10px; width: 412px; margin-left: 7px; margin-top: 5px;">
			<legend style="color: rgb(202, 114, 51);">栏目示例</legend>
			<div class="fl ml10px">
				<div style="width: 390px ! important;" class="headerMenuBorder headerMenuBg">
					<div style="line-height: 100%;" class="headerMenuList">
						<ul>
							<li class="headerMenuLiCheck"><a href="#">首页</a>
							</li>
							<li class="headerMenuLi"><a href="#">供应产品</a>
							</li>
							<li class="headerMenuLi"><a href="#">公司介绍</a>
							</li>
						</ul>
						<div class="clr"></div>
					</div>
				</div>
				<div class="headerMenuBottom"></div>
			</div>
		</fieldset>
		<div style="margin-top: 15px;" class="contTitle">
			<span class="pl23px">当前栏目</span>
		</div>
		<div class="contCont">
			<ul>
				<li class="fl"><span class="fl lin190">文字颜色：</span><span
					id="colorShow9"
					class="fl block colorShow mt1px {selector:'.headerMenuLiCheck a',property:'color'}"></span>
				</li>
				<li style="margin-left: 30px;" class="fl"><span
					class="fl lin190">背景颜色：</span><span id="colorShow10"
					class="fl block colorShow mt1px {selector:'.headerMenuLiCheckBg',property:'background'}"></span>
				</li>
				<li style="margin-left: 30px;" class="fl"><span
					class="fl lin190">边框颜色：</span><span id="colorShow11"
					class="fl block colorShow mt1px {selector:'.headerMenuLiCheck',property:'border-color'}"></span>
				</li>
			</ul>
			<div class="clr"></div>
		</div>
		<div class="editLine"></div>
		<div style="margin-top: 15px;" class="contTitle">
			<span class="pl23px">非当前栏目</span>
		</div>
		<div class="contCont">
			<ul>
				<li class="fl"><span class="fl lin190">文字颜色：</span><span
					id="colorShow12"
					class="fl block colorShow mt1px {selector:'.headerMenuLi a',property:'color'}"></span>
				</li>
				<li style="margin-left: 30px;" class="fl"><span
					class="fl lin190">边框颜色：</span><span id="colorShow13"
					class="fl block colorShow mt1px {selector:'.headerMenuBorder',property:'border-color'}"></span>
				</li>
				<li style="margin-left: 30px;" class="fl"><span
					class="fl lin190">底边颜色：</span><span
					class="fl block colorShow mt1px {selector:'.headerMenuBottom',property:'border-bottom-color'}"></span>
				</li>
				<li class="fl clr"><span class="fl lin190">背景设置：</span>
				</li>
			</ul>
			<div class="clr"></div>
		</div>
		<div id="navBgList" class="contCont">
			<ul class="fl w1">
				<li><label class="fl typeLabel">选择背景颜色：</label><span
					id="colorShow14"
					class="fl block colorShow mt4px {selector:'.headerMenuBg',property:'background'}"></span>
				</li>
			</ul>
			<div class="clr">
				<label class="fl typeLabel">选择背景图片：</label>
				<div id="imgList3" class="backgroundImgBox clr">
					<ul>
						<c:forEach items="${navBgSet }" var="nav">
							<li><a href="#"><img
									class="uncheck2 {selector:'.headerMenuBg',property:'background'}"
									width="30" height="30" src="${nav }">
							</a>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<div class="mt3px clr">
				<label class="typeLabel">从相册中选择背景图片：</label><span class="gray">建议高30像素，大小30k内</span><br>
				<a href="#" class="ml5px fl" id="menuBgUploadTemp"></a>
				<div style="margin: 0px; display: inline;">
					<input class="ml20px" size="27" id="menuBgFile" readonly="readonly" type="text"> 
					<a class="various3" style="display: inline;" href="/user/album/albumshowUploadPage?valueId=menuBgFile&callback=upload.navDid">选择图片</a>
				</div>
			</div>
		</div>
	</div>
</div>