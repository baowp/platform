<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="z-index: 99; display: none;" class="rel editor" id="setGaim">
	<div style="font-weight: bold; text-align: left;" class="abs">
		<div class="setContent5 nb black">			
<div class="check" id="editGaiIndex">
	<div class="title">
		<div class="titleList">
			<ul>
				<li><a class="b">QQ通讯</a></li>
				<li><a onclick="tool.changeTo3('#editGaiLog');" href="#">MSN通讯</a></li>
			</ul>
			<div class="clr"></div>
		</div>
	</div>
	<div style="height: 258px;">
		<div style="margin-top: 10px;" class="contTitle"><span class="pl23px b">显示位置</span></div>
		<div style="margin-left: 24px;">
		   QQ位置:<input type="radio" id="qqPositionstatic" name="qqPosition" value="static">固定 <input type="radio" id="qqPositionfixed" name="qqPosition" value="fixed">浮动</div>
		<div class="editLine3 clr"></div>
		<div class="contTitle"><span class="pl23px b">输入您的QQ号码并确认</span></div>
		<div style="margin-left: 24px;">
		QQ号码: <input id="gaimqqField" name="gaimqqField" type="text" size="15" />
		<input type="button" value="确定" onclick="gaim.qqFieldSubmit()" />
				<a href="#" onclick="gaim.removeqq()" style="display:inline;">删除</a>
		</div>
	</div>
</div>

<div class="uncheck" id="editGaiLog">
	<div class="title">
		<div class="titleList">
			<ul>
				<li><a onclick="tool.changeTo3('#editGaiIndex');" href="#">QQ通讯</a></li>
				<li><a class="b">MSN通讯</a></li>
			</ul>
			<div class="clr"></div>
		</div>
	</div>
	
	<div style="height: 258px;">
		<div style="margin-top: 10px;" class="contTitle"><span class="pl23px b">设置MSN临时会话，请按以下步骤添加代码</span></div>
		<div style="line-height: 180%;">
			<ol>
				<li>进入<a style="display:inline;" style="" href="http://settings.messenger.live.com/applications/Default.aspx" target="_blank">Windows Live</a>的网络设置页面，用自己的ID号登陆；</li>
				<li>进入“网络设置”，钩选“允许网络上的人查看您是否联机并给您发送消息”，保存;</li>
				<li>进入“创建HTML”，选择“状态图标”，并复制您的HTML代码至以下文本域;</li>
			</ol>
		</div>
		<div align="center">
			<textarea name="gaimMsnArea" id="gaimMsnArea" style="font-size:12px;line-height:11px; width:310px; height:82px; "></textarea>
		</div>
		<div align="right" style="padding-right: 20px;">
			<span><input type="button" value="确定" onclick="gaim.msnAreaSubmit()"/>
			<a href="#" onclick="gaim.removeMsn()">删除</a></span>
		</div>
	</div>
</div>
</div>

</div>
		<div style="top: -33px; left: 0px; color: rgb(255, 115, 0); font-weight: bold; text-align: left;" class="abs">
			<div class="setTitle"><span style="padding-left: 12px; padding-top: 3px; display: block;">通讯设置</span></div>
		</div>
</div>


