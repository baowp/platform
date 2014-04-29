<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:action namespace="/vip" name="*/pieceRecruit">
	<s:param name="enterpriseId" value="enterpriseId"/>
</s:action>
<div id="recruit" class="bodyCont moveChild">
	<s:hidden name="piece" value="%{@com.abbcc.util.constant.layout.Piece@MAIN_RECRUIT}"/>
	<div class="clr"></div>
	<div class="bodyContTitle">
		<span class="fl b titleLinkColor titleName" >
				<s:property value="layout.jsonContent['title'][@com.abbcc.util.constant.layout.Piece@MAIN_RECRUIT.name()]||
					#request['moduleMap'][@com.abbcc.util.constant.layout.Piece@MAIN_RECRUIT.name()]"/>
			</span>
		<s:if test="layout.belongPage!=@com.abbcc.util.constant.layout.BelongPage@RECRUIT">
			<a class="fr fs12px nb titleLinkColor draft_no_link" href="recruit">${lan['more']} &gt;&gt;</a>
		</s:if>
	</div>
	<div class="bodyContContent rel rightConteWidth">
		<s:hidden name="meta" cssClass="{currentPage:%{#request['recruit'].currentPage},pageCount:%{#request['recruit'].pageCount}}"/>
		<div class="tal mainTextColor break recruit">
			<s:iterator value="#request['recruit'].items">
				<dl class="glitzColor recruitDl">
					<dt class="title">${title }</dt>
					<dt>${lan['recruit.duty']}${duty } ${lan['recruit.sum']}${sum }</dt>
					<dt>${lan['recruit.addTime']}<s:date name="addTime" format="yyyy-MM-dd hh:mm:ss"/></dt>
					<dt class="fl">${lan['recruit.requirement']}</dt>
					<dd>${content }</dd>
				</dl>
			</s:iterator>
		</div>
			<s:set name="pageList" value="#request['recruit']"/>
			<s:include value="../pagination.jsp"/>
		<div class="clr"></div>
	</div>
</div>