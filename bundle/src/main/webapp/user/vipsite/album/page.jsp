<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
总共${pageList.totalCount}条记录，共${pageList.pageCount}张
<s:if test="pageList.pageCount>0">，现在第${pageList.currentPage}张&nbsp;
<s:if test="pageList.currentPage!=1">
		<a href="?${param.urlArgs}page=${pageList.currentPage-1}">上一张</a>
	</s:if>&nbsp;
<s:if test="pageList.currentPage!=pageList.pageCount">
		<a href="?${param.urlArgs}page=${pageList.currentPage+1}">下一张</a>
	</s:if>
</s:if>