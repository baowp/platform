<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="check" id="editTopicImg">
	<div class="title">
		<div class="titleList">
			<ul>
				<li><a class="b">主题图片</a></li>
				<li><a onclick="tool.changeTo('#editTopContent')" href="#">招牌风格</a></li>
				<li><a onclick="tool.changeTo('#editMenu');" href="#">栏目风格</a></li>
				<li><a onclick="tool.changeTo('#editModule');" href="#">板块风格</a></li>
				<li><a onclick="tool.changeTo('#editAll');" href="#">整体风格</a></li>
			</ul>
			<div class="clr"></div>
		</div>
	</div>
	<div style="height: 406px;">
		<div style="margin-top: 10px;" class="contTitle"><span class="pl23px">主题图片设置</span></div>
		<div id="topicImgList" class="contCont">
			<div class="clr">
				<label class="fl typeLabel">选择主题图片：</label>
				<div id="imgList4" class="backgroundImgBox2 clr ml15px">
					<ul>
						<c:forEach items="${bannerSet }" var="b">
							<li><a href="#"><img class="uncheck2 {selector:'.headTopic',property:'background'}" src="${b }"></a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
    		<iframe name="uploadFrame" style="display: none"></iframe>
			<div class="mt3px topic_image">
				<label class="fl typeLabel">从相册中选择主题图片：</label><span class="gray">建议尺寸952x200像素</span>
					<div style="margin: 0px; display: inline;">
						<input class="ml20px" size="27" id="uploadTopicImgFile" readonly="readonly" type="text">
						<a class="various3" style="display: inline;" href="/user/album/albumshowUploadPage?valueId=uploadTopicImgFile&callback=upload.topicDid">选择相片</a>
					</div>
					<a href="#" class="ml20px mt3px block" id="topicImgUploadTemp"></a>
			</div>
			<div class="mt3px topic_flash">
				<label class="fl typeLabel">主题动画设置：</label><span class="gray">大小1M内，swf格式</span>
				<div style="margin: 0px;">
					<input class="ml20px" size="15" id="uploadTopicFlashFile" readonly="readonly" type="text"> 
					<a class="various3" id="a_uploadTopicImgFile" style="display: inline;" href="/user/album/albumshowFlashPage?valueId=uploadTopicFlashFile&callback=upload.topicFlashDid">选择flash</a>
				</div>
				<a href="#" class="ml20px mt3px block" id="topicFlashUploadTemp" style="display: inline;"></a>
			</div>
		</div>
		<div class="editLine"></div>
		<div class="contTitle"><span class="pl23px">内容设置</span></div>
		<div class="contCont">
			<ul>
				<li><span>上排文字：</span><input type="text" size="45" maxlength="30" class="ml5px" id="topDesInput" value="${layout.jsonSign['topDesc']}"></li>
				<li>
					<select class="mt5px fl maintain {selector:'.topDesc',property:'font-family'}" style="margin-left: 65px; width: 80px;" id="select1">
					<option value="宋体">宋体</option>
					<option value="黑体">黑体</option>
					<option value="楷体_GB2312">楷体</option>
					<option value="隶书">隶书</option>
					<option value="幼圆">幼圆</option>
					<option value="Arial Black">Arial Black</option>
					<option value="Verdana">Verdana</option>
					<option value="Times New Roman">Times New Roman</option>
					<option value="Comic Sans MS">Comic Sans MS</option>
					<option value="Courier New">Courier New</option>
					</select>
					<select class="mt5px ml5px fl maintain {selector:'.topDesc',property:'font-size'}" id="select2">
					<option value="12px">12</option>
					<option value="14px">14</option>
					<option value="16px">16</option>
					<option value="18px">18</option>
					<option value="20px">20</option>
					<option value="22px">22</option>
					<option value="26px">26</option>
					<option value="30px">30</option>
					</select>
					<span class="fl mt5px ml5px"><input type="checkbox" class="maintain {selector:'.topDesc',property:'font-weight',normal:'normal'}" value="bold" id="checkbox2"><label for="checkbox2">加粗</label></span>
					<span class="fl mt5px ml5px"><input type="checkbox" class="maintain {selector:'.topDesc',property:'font-style',normal:'normal'}" value="italic" id="checkbox3"><label for="checkbox3">斜体</label></span>
					<span class="fl mt5px ml5px lin190">文字颜色：</span><span class="fl block colorShow mt1px mt5px {selector:'.topDesc',property:'color'}" style="background-color:black"></span>
				</li>
				<li>&nbsp;</li>
				<li class="clr"><span>下排文字：</span><input type="text" size="45" class="ml5px" maxlength="30" id="bottomDesInput" value="${layout.jsonSign['bottomDesc']}"></li>
				<li>
					<select class="mt5px fl maintain {selector:'.bottomDesc',property:'font-family'}" style="margin-left: 65px; width: 80px;" id="select3">
					<option value="宋体">宋体</option>
					<option value="黑体">黑体</option>
					<option value="楷体_GB2312">楷体</option>
					<option value="隶书">隶书</option>
					<option value="幼圆">幼圆</option>
					<option value="Arial Black">Arial Black</option>
					<option value="Verdana">Verdana</option>
					<option value="Times New Roman">Times New Roman</option>
					<option value="Comic Sans MS">Comic Sans MS</option>
					<option value="Courier New">Courier New</option>
					</select>
					<select class="mt5px ml5px fl maintain {selector:'.bottomDesc',property:'font-size'}" id="select4">
					<option value="12px">12</option>
					<option value="14px">14</option>
					<option value="16px">16</option>
					<option value="18px">18</option>
					<option value="20px">20</option>
					<option value="22px">22</option>
					<option value="26px">26</option>
					<option value="30px">30</option>
					</select>
					<span class="fl mt5px ml5px"><input type="checkbox" class="maintain {selector:'.bottomDesc',property:'font-weight',normal:'normal'}" value="bold" id="checkbox4"><label for="checkbox4">加粗</label></span>
					<span class="fl mt5px ml5px"><input type="checkbox" class="maintain {selector:'.bottomDesc',property:'font-style',normal:'normal'}" value="italic" id="checkbox5"><label for="checkbox5">斜体</label></span>
					<span class="fl mt5px ml5px lin190">文字颜色：</span><span id="colorShow16" class="fl block colorShow mt1px mt5px {selector:'.bottomDesc',property:'color'}" style="background-color:black"></span>
				</li>
			</ul>
		</div>
	</div>
</div>