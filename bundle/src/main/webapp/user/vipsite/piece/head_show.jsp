<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
		<div id="theme_pic" class="headTopic moveChild headCont">
			<s:hidden name="piece" value="%{@com.abbcc.util.constant.layout.Piece@HEAD_SHOW}"/>
			<div class="clr"></div>
			<div id="description" class="description">
				<div class="topDesc"><span id="topDesc">${layout.jsonSign['topDesc']}</span></div>
				<div class="bottomDesc"><span id="bottomDesc">${layout.jsonSign['bottomDesc']}</span></div>
			</div>
			<div id="describe_flash" class="describe_flash">
				<embed id="topic_flash" wmode="transparent"
				 width="952" <s:if test="layout.jsonSign['topicFlash']">src="${ layout.jsonSign.topicFlash.src}"
					height="${ layout.jsonSign.topicFlash.height}" </s:if>>
			</div>
		</div>