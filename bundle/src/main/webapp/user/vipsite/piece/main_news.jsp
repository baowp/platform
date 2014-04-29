<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:action namespace="/vip" name="*/pieceNews">
	<s:param name="enterpriseId" value="enterpriseId"/>
</s:action>
<div id="news" class="bodyCont moveChild">
	<s:hidden name="piece" value="%{@com.abbcc.util.constant.layout.Piece@MAIN_NEWS}"/>
	<div class="clr"></div>
	<div class="bodyContTitle">
		<span class="fl b titleLinkColor titleName" >
				<s:property value="layout.jsonContent['title'][@com.abbcc.util.constant.layout.Piece@MAIN_NEWS.name()]||
					#request['moduleMap'][@com.abbcc.util.constant.layout.Piece@MAIN_NEWS.name()]"/>
			</span>
		<s:if test="layout.belongPage!=@com.abbcc.util.constant.layout.BelongPage@NEWS">
			<a class="fr fs12px nb titleLinkColor draft_no_link" href="news">${lan['more']} &gt;&gt;</a>
		</s:if>
	</div>
	<div class="bodyContContent rel rightConteWidth">
		<s:hidden name="meta" cssClass="{currentPage:%{#request['commonNews'].currentPage},pageCount:%{#request['commonNews'].pageCount},parentKey:'newsCategory',parentValue:'%{#parameters['newsCategory']}'}"/>
		<div class="tal mainTextColor break mainNews">
			<div class="topNews listNews">
			  	<s:iterator value="#request['topNews']">
			  		<dl class="glitzColor listNewsDl">
			  			<dt title="${title}">&nbsp;[${lan['news.top']}]<a target="_blank" href="news_detail?newsId=${newsId}" class="topicLink">${title}</a></dt>
			  			&nbsp;<dd>${origin}</dd>
			  			<dd><s:date name="addTime" format="yyyy-MM-dd hh:mm:ss"/></dd>
			  		</dl>
			  	</s:iterator>
		  	</div>
		  	<div class="commonNews listNews">
			  	<s:iterator value="#request['commonNews'].items">
			  		<dl class="glitzColor listNewsDl">
			  			<dt title="${title}">&nbsp;<a target="_blank" href="news_detail?newsId=${newsId}" class="topicLink">${title}</a></dt>
			  			&nbsp;<dd>${origin}</dd>
			  			<dd><s:date name="addTime" format="yyyy-MM-dd hh:mm:ss"/></dd>
			  		</dl>
			  	</s:iterator>
		  	</div>
		</div>
			<s:set name="pageList" value="#request['commonNews']"/>
			<s:include value="../pagination.jsp"/>
		<div class="clr"></div>
	</div>
</div>