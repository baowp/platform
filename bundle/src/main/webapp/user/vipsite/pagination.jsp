<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="#pageList.pageCount>1">
<s:set var="lan" value="@com.abbcc.common.CommonConst@languagePack"/>
<div class="pagination mainTextColor">
	<div class="fl">
		<s:if test="#pageList.currentPage!=1">		
			<a class="danaiPageUp" href="javascript:prevPage()">${lan['pagination.prev']}</a>
		</s:if>
		<s:action name="*/pagination" ignoreContextParams="true">
			<s:param name="page" value="#pageList.currentPage"/>
			<s:param name="totalPage" value="#pageList.pageCount"/>
		</s:action>
		<s:iterator var="page" value="#request['pagination']">
			<s:if test="#page==#pageList.currentPage">
				<span class="danaiPageCurrent"><s:property value="#page"/></span>
			</s:if>
			<s:else>
				<a href="javascript:toPage(<s:property value="#page"/>)" class="danaiPageNum"><s:property value="#page"/></a>
			</s:else>
		</s:iterator>
		<s:if test="#pageList.currentPage!=#pageList.pageCount">
			<a class="danaiPageDown" href="javascript:nextPage()">${lan['pagination.next']}</a>
		</s:if>
	</div>
	<div class="fl danaiPageDes">
		<span><s:property value="#pageList.currentPage"/>/<s:property value="#pageList.pageCount"/></span> ${lan['pagination.page']}
		<s:if test="#pageList.pageCount>1">
			${lan['pagination.to']} <input id="pageNum" type="text" size="3"/> ${lan['pagination.page']} 
			<input type="button" onclick="toPage(this)" value="${lan['pagination.agree']}">
		</s:if>
	</div>
</div>
</s:if>