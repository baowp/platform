<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="uncheck" id="editTopContent">
	<div class="title">
		<div class="titleList">
			<ul>
				<li><a onclick="tool.changeTo('#editTopicImg');" href="#">主题图片</a></li>
				<li><a class="b">招牌风格</a></li>
				<li><a onclick="tool.changeTo('#editMenu');" href="#">栏目风格</a></li>
				<li><a onclick="tool.changeTo('#editModule');" href="#">板块风格</a></li>
				<li><a onclick="tool.changeTo('#editAll');" href="#">整体风格</a></li>
			</ul>
			<div class="clr"></div>
		</div>
	</div>
	<div style="height: 426px;">
		<fieldset style="border: 1px solid rgb(211, 211, 211); padding: 8px 20px; width: 362px; margin-left: 7px;">
			<legend style="color: rgb(202, 114, 51);">招牌示例</legend>
			<ul style="padding: 5px; width: 350px;text-align:left;" class="fl topbaner">
		   		<li class="china">
		    		<span style="width: 350px;" class="chinaname" id="chinaname">${enterprise.name}</span>
		    		<span style="width: 350px;" class="enname" id="enname"></span>
		   		</li>
  			</ul>
		</fieldset>
		<div class="clr"></div>
		<div class="contTitle"><span class="pl23px">公司名称设置</span></div>
		<div class="contCont">
			<ul>
				<li>
					<span class="mt5px fl">中文名：</span>
					<select class="mt5px fl maintain {selector:'.chinaname',property:'font-family'}" style="width: 80px;" id="select5">
					<option value="宋体" >宋体</option>
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
					<select class="mt5px ml5px fl maintain {selector:'.chinaname',property:'font-size'}" id="select6">
					<option value="5px">5</option>
					<option value="10px">10</option>
					<option value="12px">12</option>
					<option value="14px">14</option>
					<option value="16px">16</option>
					<option value="18px">18</option>
					<option value="20px">20</option>
					<option value="22px">22</option>
					<option value="26px">26</option>
					<option value="30px">30</option>
					</select>
					<select class="mt5px ml5px fl maintain {selector:'.chinaname',property:'visibility'}" id="select6-1">
					<option value="visible">显示</option>
					<option value="hidden">隐藏</option>
					</select>
					<span class="fl mt5px ml5px"><input type="checkbox" class="maintain {selector:'.chinaname',property:'font-weight',normal:'normal'}" value="bold" id="checkbox6"><label for="checkbox6">加粗</label></span>
					<span class="fl mt5px ml5px"><input type="checkbox" class="maintain {selector:'.chinaname',property:'font-style',normal:'normal'}" value="italic" id="checkbox7"><label for="checkbox7">斜体</label></span>
					<span class="fl mt6px ml5px">文字颜色：</span><span id="colorShow17" class="fl block colorShow mt1px mt5px {selector:'.chinaname',property:'color'}"></span>
				</li>
			</ul>
			<div class="clr"></div>
		</div>
		<div style="margin-top: 14px;" class="editLine"></div>
		<div class="contTitle"><span class="pl23px">默认招牌</span></div>
		<div class="contCont">
				<select class="mt5px ml5px fl maintain {selector:'.chinaname',property:'text-align'}" id="select77">
					<option value="left">居左</option>
					<option value="center">居中</option>
					<option value="right">居右</option>
				</select>
				
		</div>
		<div style="margin-top: 14px;" class="editLine"></div>
		<div class="contTitle"><span class="pl23px">招牌背景</span></div>
		<div class="contCont">
			<ul class="fl w1">
				<li><label class="fl typeLabel">选择背景颜色：</label><span><span id="colorShow19" class="fl block colorShow mt4px {selector:'.topbaner',property:'background'}"></span></span></li>
			</ul>
			<div class="mt3px clr">
				<label class="fl typeLabel">从相册中选择背景图片：</label><span class="gray">建议尺寸952x90像素，大小200k内</span><br>
				<div style="margin: 0px; display: inline;">
					<input class="ml20px" size="27" id="uploadTopicBgFile" readonly="readonly" type="text">
					<a class="various3" style="display: inline;" href="/user/album/albumshowUploadPage?valueId=uploadTopicBgFile&callback=upload.signDid">选择图片</a>
                </div>
                <a href="#" class="ml20px block" id="topicBgUploadTemp"></a>
			</div>
		</div>
	</div>
</div>