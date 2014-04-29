<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="uncheck" id="editAll">
	<div class="title">
		<div class="titleList">
			<ul>
				<li><a onclick="tool.changeTo('#editTopicImg');" href="#">主题图片</a></li>
				<li><a onclick="tool.changeTo('#editTopContent');" href="#">招牌风格</a></li>
				<li><a onclick="tool.changeTo('#editMenu');" href="#">栏目风格</a></li>
				<li><a onclick="tool.changeTo('#editModule');" href="#">板块风格</a></li>
				<li><a class="b">整体风格</a></li>
			</ul>
			<div class="clr"></div>
		</div>
	</div>
	<div style="height: 420px; padding-top: 10px;">
		<div class="contTitle"><span class="pl23px">正文与链接颜色</span></div>
		<div class="contCont">
			<ul>
				<li class="fl w1"><span class="fl lin190">正文颜色：</span><span class="fl block colorShow mt1px {selector:'.mainTextColor',property:'color'}"></span></li>
				<li class="fl w2"><span class="fl gray lin190">正文示例：</span><span class="mainTextColor lin190">东方五金</span></li>
			</ul>
			<ul>
				<li class="fl w3"><span class="fl mt2px lin190">链接颜色：</span><span class="fl block colorShow mt3px {selector:'.topicLink',property:'color'}"></span></li>
				<li class="fl w3"><span class="fl mt2px lin190">鼠标停留：</span><span class="fl block colorShow mt3px {selector:'.topicLink:hover',property:'color'}"></span></li>
				<li class="fl w2"><span class="fl gray lin190">链接示例：</span><a class="topicLink lin190" href="#">东方五金</a></li>
			</ul>
			<div class="clr"></div>
		</div>
		<div class="editLine"></div>
		<div class="contTitle fl"><span class="pl23px">网站背景设置</span></div>
		<div class="clr"></div>
		<div class="bgSetTitleIn" id="bgSetTitle">
			<div class="check3" id="bgSetTitle1"><img border="0" class="pointer" src="<s:url value="/user/vipsite/tool/images/inBgSample.gif"/>"><span class="block">内背景</span></div>
			<div style="padding-left: 19px;" class="uncheck3" id="bgSetTitle2"><img border="0" class="pointer" src="<s:url value="/user/vipsite/tool/images/outBgSample.gif"/>"><span class="block">外背景</span></div>
		</div>
		<div class="bgSetCont" id="bgSetContIn">
			<div style="padding-left: 13px;" class="contCont">
				<ul class="fl w1">
					<li style="line-height: 150%;"><label class="fl typeLabel">选择内背景颜色：</label><span id="colorShow4" class="fl block mt4px colorShow {selector:'.inBg',property:'background'}"></span></li>
				</ul>
				<div class="clr">
					<label class="fl typeLabel">选择内背景图片：</label><span class="fr disabled pr50px lin200"><input type="checkbox" class="{selector:'.filter',property:'opacity'}" id="transparentCheckbox"><label for="transparentCheckbox">背景透明</label></span>
					<div id="imgList1" style="height: 56px;" class="backgroundImgBox2 disabled clr">
						<ul>
						<s:iterator value="#request['bgSet']">
							<li><a href="#"><img class="uncheck2 {selector:'.inBg',property:'background'}" height="30" width="30" src="<s:property/>"></a></li>
						</s:iterator>
						</ul>
					</div>
				</div>
				<div class="mt3px">
					<label class="typeLabel">上传内背景图片：</label><span class="gray">大小200k内,jpg或gif格式</span><br>
					<s:form enctype="multipart/form-data" method="POST" target="uploadFrame" cssStyle="margin: 0px; display: inline;" namespace="/laytach" action="inBg">
                        <s:hidden name="belongId"/>
                   		<s:hidden name="callback"/>
                        <s:file name="upload" cssClass="fl ml20px" size="27"/>
                    	<s:submit value="上传" cssClass="fl" onclick="return window.upload.inBg(this.form);"/>
        			</s:form>&nbsp;
					<a href="#" class="ml20px block" id="siteBgUploadTemp"></a>
					<div class="clr"></div>
				</div>
			</div>
		</div>
		<div style="display: none;" class="bgSetCont" id="bgSetContOut">
			<div style="padding-left: 13px;" class="contCont">
				<ul class="fl w1">
					<li style="line-height: 150%;"><label class="fl typeLabel">选择外背景颜色：</label><span id="colorShow20" class="fl block mt4px colorShow {selector:'.outBg',property:'background'}"></span></li>
				</ul>
				<div class="clr">
					<label class="fl typeLabel">选择外背景图片：</label>
					<div id="imgList5" style="height: 56px;" class="backgroundImgBox2 disabled clr">
						<ul>
						<s:iterator value="#request['bgSet']">
							<li><a href="#"><img class="uncheck2 {selector:'.outBg',property:'background'}" height="30" width="30" src="<s:property/>"></a></li>
						</s:iterator>
						</ul>
					</div>
				</div>
				<div class="mt3px">
					<label class="typeLabel">上传外背景图片：</label><span class="gray">大小200k内,jpg或gif格式</span><br>
					<s:form enctype="multipart/form-data" method="POST" target="uploadFrame" cssStyle="margin: 0px; display: inline;" namespace="/laytach" action="outBg">
                        <s:hidden name="belongId"/>
                   		<s:hidden name="callback"/>
                        <s:file name="upload" cssClass="fl ml20px" size="27"/>
                    	<s:submit value="上传" cssClass="fl" onclick="return window.upload.outBg(this.form);"/>
        			</s:form>&nbsp;
					<a href="#" class="ml20px block" id="siteBgOutUploadTemp"></a>
					<div class="clr"></div>
				</div>
			</div>
		</div>
	</div>
</div>