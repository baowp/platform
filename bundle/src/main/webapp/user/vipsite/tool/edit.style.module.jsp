<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="uncheck" id="editModule">
	<div class="title">
		<div class="titleList">
			<ul>
				<li><a onclick="tool.changeTo('#editTopicImg');" href="#">主题图片</a></li>
				<li><a onclick="tool.changeTo('#editTopContent');" href="#">招牌风格</a></li>
				<li><a onclick="tool.changeTo('#editMenu');" href="#">栏目风格</a></li>
				<li><a class="b">板块风格</a></li>
				<li><a onclick="tool.changeTo('#editAll');" href="#">整体风格</a></li>
			</ul>
			<div class="clr"></div>
		</div>
	</div>
	<div style="height: 420px; padding-top: 10px;">
		<div class="contTitle"><span class="pl23px">板块标题设置</span></div>
		<div class="contCont">
			<ul>
				<li class="fl w1"><span class="fl mt5px">文字颜色：</span><span id="colorShow5" class="fl block colorShow mt3px {selector:'.bodyContTitle .titleLinkColor',property:'color'}"></span></li>
				<li class="fl w2"><span class="fl gray mt6px">示例：</span><span class="bodyContTitle b fs14px block fl"><span class="titleLinkColor">精品推荐</span></span></li>
			</ul>
			<div class="clr"></div>
		</div>
		<div id="titleBgList" class="contCont">
			<span class="lin190">背景设置：</span>
			<ul class="fl w1">
				<li><label class="fl typeLabel">选择背景颜色：</label><span id="colorShow6" class="fl block colorShow mt4px {selector:'.bodyContTitle',property:'background'}"></span></li>
			</ul>
			<div class="clr">
				<label class="fl typeLabel">选择背景图片：</label>
				<div id="imgList2" class="backgroundImgBox disabled clr">
					<ul>
					<s:iterator value="#request['titleBgSet']">
						<li><a href="#"><img class="uncheck2 {selector:'.bodyContTitle',property:'background'}" height="30" width="30" src="<s:property/>"></a></li>
					</s:iterator>
					</ul>
				</div>
			</div>
			<div class="mt3px">
				<label class="typeLabel">上传背景图片：</label><span class="gray">建议高30像素，大小30k内，jpg或gif格式</span><br>
				<a href="#" class="ml7px fl" id="titleBgUploadTemp"></a>
				<s:form enctype="multipart/form-data" method="POST" target="uploadFrame" cssStyle="margin: 0px; display: inline;" namespace="/laytach" action="title">
                    <s:hidden name="belongId"/>
                   	<s:hidden name="callback"/>
                    <s:file name="upload" cssClass="fl ml10px" size="27" id="uploadTitleBgFile"/>
                	<s:submit value="上传" cssClass="fl" onclick="return window.upload.title(this.form);"/>
    			</s:form>
				<div class="clr"></div>
			</div>
		</div>
		<div class="editLine"></div>
		<div class="contTitle"><span class="pl23px">板块内容设置</span></div>
		<div style="margin-top: 3px;" class="contCont">
			<ul class="fl w1">
				<li><span class="fl lin250">背景颜色：</span><span id="colorShow7" class="fl block colorShow lin250 mt3px {selector:'.bodyCont',property:'background'}"></span></li>
				<li class="clr"><span class="fl lin250">边框颜色：</span><span id="colorShow8" class="fl block colorShow lin250 mt3px {selector:'.bodyCont',property:'border-color'}"></span>
					<input type="radio" name="bodyContBorderRadio" value="solid" id="bodyContBorderRadio1" class="ml20px mt6px maintain {selector:'.bodyCont',property:'border-style'}"><label for="bodyContBorderRadio1">实线 </label>
					<input type="radio" name="bodyContBorderRadio" value="dashed" id="bodyContBorderRadio2" class="mt6px maintain {selector:'.bodyCont',property:'border-style'}"><label for="bodyContBorderRadio2">虚线</label>
					<input type="radio" name="bodyContBorderRadio" value="none" id="bodyContBorderRadio3" class="mt6px maintain {selector:'.bodyCont',property:'border-style'}"><label for="bodyContBorderRadio3">无</label></li>
			</ul>
			<ul class="fl w2">
				<li class="fl mt18px gray">示例：</li>
				<li class="fl"><div style="width: 43px; height: 43px;" class="bodyCont"></div></li>
			</ul>
			<div class="clr"></div>
		</div>
		<div class="editLine"></div>
		<div class="contTitle"><span class="pl23px">产品图片边框</span></div>
		<div class="contCont">
			<ul class="fl w1">
				<li><span class="fl mt10px">边框颜色：</span><span id="colorShow3" class="fl block colorShow mt10px {selector:'.glitzColor',property:'border-color'}"></span>
					<input type="radio" name="imgBorderRadio" value="solid" id="imgBorderRadio1" class="ml20px maintain {selector:'.glitzBorder',property:'border-style'}"><label for="imgBorderRadio1">实线</label>
					<input type="radio" name="imgBorderRadio" value="dashed" id="imgBorderRadio2" class="maintain {selector:'.glitzBorder',property:'border-style'}"><label for="imgBorderRadio2">虚线</label>
					<input type="radio" name="imgBorderRadio" value="none" id="imgBorderRadio3" class="maintain {selector:'.glitzBorder',property:'border-style'}"><label for="imgBorderRadio3">无</label></li>
			</ul>
			<ul class="fl w2">
				<li class="fl mt14px gray">示例：</li>
				<li class="fl"><div class="glitzBorder glitzColor" style="width: 43px; height: 43px;"></div></li>
			</ul>
			<div class="clr"></div>
		</div>
	</div>
</div>