<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="z-index: 99; display: none;" class="rel editor" id="setAdvance">
	<div style="font-weight: bold; text-align: left;" class="abs">
		<div class="setContent4 nb black">			
<div class="check" id="editAdvIndex">
	<div class="title">
		<div class="titleList">
			<ul>
				<li><a class="b">形象首页</a></li>
				<li><a onclick="tool.changeTo2('#editLog');" href="#">公司标志</a></li>
				<li><a onclick="tool.changeTo2('#editNe');" href="#">互访连接</a>
			</ul>
			<div class="clr"></div>
		</div>
	</div>
	<div style="height: 285px;">
		<div style="margin-top: 10px;" class="contTitle"><span class="pl23px b">是否显示形象首页</span></div>
		<div><input type="checkbox" class="fl ml20px mt4px ffmt8px" id="isshowflash0"><label for="isshowflash0" class="fl ml3px">显示</label><span class="gray fl ml10px">取消显示后，形象首页将不出现 </span></div>
		<div class="editLine3 clr"></div>
		<div class="contTitle"><span class="pl23px b">flash设置</span></div>
		<div>
			<span style="display: none;" id="flashdoc" class="ml20px">您的网站已有了flash首页！<input type="button" value="重新选择" onclick="$('#flashdoc').hide();$('#flashupload').show()"></span>
			<div style="display: none;" id="flashupload" class="ml20px">选择flash文件：
				<input size="15" id="flashFilePath" readonly="readonly" type="text"> 
				<a class="various3" style="display: inline;" href="/user/album/albumshowFlashPage?valueId=flashFilePath&callback=upload.flashDid">选择flash</a>
            </div>
		</div>
		<span style="margin-left: 24px;" class="gray">flash大小控制在1M以内，播放时间控制在20秒以内</span>
		<span class="ml20px fl">flash尺寸：宽 <input type="text" size="5" id="flashWidth"> px, 高 <input type="text" size="5" id="flashHeight"> px</span>
		<div class="editLine3 clr"></div>
		<div class="contTitle"><span class="pl23px b">形象首页背景</span></div>
		<span class="fl pl23px">选择背景颜色：</span><span class="fl block colorShow mt4px" id="flashBackColor"></span>
	</div>
	<div style="width: 360px;" class="clr"><a href="#" class="fl" style="margin-left: 135px ! important;" id="flashLink">预览形象首页</a></div>
</div>

<div class="uncheck" id="editLog">
	<div class="title">
		<div class="titleList">
			<ul>
				<li><a onclick="tool.changeTo2('#editAdvIndex');" href="#">形象首页</a></li>
				<li><a class="b">公司标志</a></li>
				<li><a onclick="tool.changeTo2('#editNe');" href="#">互访连接</a>
			</ul>
			<div class="clr"></div>
		</div>
	</div>
	<div style="height: 285px;">
		<div style="margin-top: 10px;" class="contTitle"><span class="pl23px b">从相册中选择公司标志</span></div>
		<span class="ml20px gray">图片尺寸为80*80象素，大小为200K以内</span>
		<div style="margin: 0px; display: inline;">
			<input class="ml20px" size="35" id="uploadLogoFile" readonly="readonly" type="text"> 
			<a class="various3" style="display: inline;" href="/user/album/albumshowUploadPage?valueId=uploadLogoFile&callback=upload.logDid">选择图片</a>
		</div>
		<div style="width: 360px; margin-top: 20px; text-align: center;" id="logEdit">	
			<c:if test="${not empty layout.jsonSign['log']}">
				<img <c:forEach items="${layout.jsonSign['log']}" var="log">${log.key }="${log.value }"</c:forEach>/>
				<br><a onclick="tool.deleteLog()" href="javascript: void(0) " style="display: inline;">删除标志</a>	
			</c:if>				
		</div>	
	</div>
</div>
<div class="uncheck necheck" id="editNe">
	<div class="title">
		<div class="titleList">
			<ul>
				<li><a onclick="tool.changeTo2('#editAdvIndex');" href="#">形象首页</a></li>
				<li><a onclick="tool.changeTo2('#editLog');" href="#" >公司标志</a></li>
				<li><a class="b">互访连接</a></li>
			</ul>
			<div class="clr"></div>
		</div>
	</div>
 <div style="height: 285px;">
   <div style="margin-top: 15px;" class="contTitle"><span class="pl23px b">中英文网站访问链接</span></div>
      <div style="margin-top: 5px; margin-left: 20px;">
      	链接名称: <input type="text" value="${layout.jsonSign['visit']['name']}" id="visitName" name="visitName" value=""  size="28" class="visitName">
		</div>
		<div style="margin-left: 20px;">
		链接地址: <input type="text" id="visitHref" value="${layout.jsonSign['visit']['href']}" name="visitHref" value=""  size="28" class="visitName">
	   </div>
	   <div class="cbut">
        <input type="button" value="清空" onclick="visit.clear();">
       </div>
  </div>
</div>
</div>
		<div style="top: -33px; left: 0px; color: rgb(255, 115, 0); font-weight: bold; text-align: left;" class="abs">
			<div class="setTitle"><span style="padding-left: 12px; padding-top: 3px; display: block;">高级设置</span></div>
		</div>
	</div>
</div>