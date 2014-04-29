<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div style="z-index: 99; display: none;" class="rel editor" id="setAdvance">
	<div style="font-weight: bold; text-align: left;" class="abs">
		<div class="setContent4 nb black">			
<div class="check" id="editAdvIndex">
	<div class="title">
		<div class="titleList">
			<ul>
				<li><a class="b">形象首页</a></li>
				<li><a onclick="tool.changeTo2('#editLog');" href="#">上传标志</a></li>
				<li><a onclick="tool.changeTo2('#editNe');" href="#">互访连接</a>
				<li><a onclick="tool.changeTo2('#editOption');" href="#">全局设置</a>
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
			<span style="display: none;" id="flashdoc" class="ml20px">您已经上传flash首页！<input type="button" value="重新上传" onclick="$('#flashdoc').hide();$('#flashupload').show()"></span>
			<div style="display: none;" id="flashupload" class="ml20px">上传flash：
				<s:form enctype="multipart/form-data" method="POST" target="uploadFrame" cssStyle="margin: 0px; display: inline;" namespace="/laytach" action="flash">
	               	<s:hidden name="belongId"/>
                   	<s:hidden name="callback"/>
	                <s:file name="upload" size="17" />
	            	<s:submit value="上传" onclick="return window.upload.flash(this.form)" cssClass="ml3px"/>
	            </s:form>
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
				<li><a class="b">上传标志</a></li>
				<li><a onclick="tool.changeTo2('#editNe');" href="#">互访连接</a>
				<li><a onclick="tool.changeTo2('#editOption');" href="#">全局设置</a>
			</ul>
			<div class="clr"></div>
		</div>
	</div>
	<div style="height: 285px;">
		<div style="margin-top: 10px;" class="contTitle"><span class="pl23px b">上传公司标志</span></div>
		<span class="ml20px gray">公司标志为gif或jpg图片，尺寸为80*80象素，大小为200K以内</span>
		<s:form id="logUploadForm" enctype="multipart/form-data" method="POST" target="uploadFrame" namespace="/laytach" action="log">
            <s:hidden name="belongId"/>
            <s:hidden name="callback"/>
            <s:file name="upload" cssClass="ml20px" size="27" id="uploadLogoFile"/>
        	<s:submit value="上传" onclick="return window.upload.log(this.form);"/>
		</s:form>
		<div style="width: 360px; margin-top: 20px; text-align: center;" id="logEdit">					
			<s:if test="layout.jsonSign['log']!=null">
				<img <s:iterator value="layout.jsonSign['log']">${key}="${value}" </s:iterator> />
				<br><a onclick="tool.deleteLog()" href="#" style="display: inline;">删除标志</a>			
			</s:if>		
		</div>	
	</div>
</div>
<div class="uncheck necheck" id="editNe">
	<div class="title">
		<div class="titleList">
			<ul>
				<li><a onclick="tool.changeTo2('#editAdvIndex');" href="#">形象首页</a></li>
				<li><a onclick="tool.changeTo2('#editLog');" href="#" >上传标志</a></li>
				<li><a class="b">互访连接</a></li>
				<li><a onclick="tool.changeTo2('#editOption');" href="#">全局设置</a>
			</ul>
			<div class="clr"></div>
		</div>
	</div>
 <div style="height: 285px;">
   <div style="margin-top: 15px;" class="contTitle"><span class="pl23px b">中英文网站访问链接</span></div>
      <div style="margin-top: 5px; margin-left: 20px;">
		<s:textfield name="visitName" value="%{layout.jsonContent['visit']['name']}" size="28" cssClass="visitName" label="链接名称" theme="xhtml"/>
		</div>
		<div style="margin-left: 20px;">
		<s:textfield name="visitHref" value="%{layout.jsonContent['visit']['href']}" size="28" cssClass="visitName" label="链接地址" theme="xhtml"/>
	   </div>
	   <div class="cbut">
        <input type="button" value="清空" onclick="visit.clear();">
       </div>
  </div>
</div>
<div class="uncheck" id="editOption">
	<div class="title">
		<div class="titleList">
			<ul>
				<li><a onclick="tool.changeTo2('#editAdvIndex');" href="#">形象首页</a></li>
				<li><a onclick="tool.changeTo2('#editLog');" href="#" >上传标志</a></li>
				<li><a onclick="tool.changeTo2('#editNe');" href="#">互访连接</a></li>
				<li><a class="b">全局设置</a>
			</ul>
			<div class="clr"></div>
		</div>
	</div>
 <div style="height: 285px;">
   <div style="margin-top: 15px;" class="contTitle"><span class="pl23px b">全局设置</span></div>
      <div style="margin-top: 5px; margin-left: 20px;">
			<span>页脚显示：</span><s:radio list="#{'display':'显示','none':'不显示' }" name="footer" /><br/>
			<span>产品名称：</span><s:radio list="#{'1':'显示名称','2':'显示型号', '3':'都显示'}" name="pshow" />
	  </div>
  </div>
</div>
</div>
		<div style="top: -33px; left: 0px; color: rgb(255, 115, 0); font-weight: bold; text-align: left;" class="abs">
			<div class="setTitle"><span style="padding-left: 12px; padding-top: 3px; display: block;">高级设置</span></div>
		</div>
	</div>
</div>