<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
总共${products.totalCount}条记录，共${products.pageCount}页
<s:if test="products.pageCount>0">，现在第${products.currentPage}页&nbsp;
<a href="?${param.urlArgs}page=1">首页</a>&nbsp;
<s:if test="products.currentPage!=1">
		<a href="?${param.urlArgs}page=${products.currentPage-1}">上一页</a>
	</s:if>&nbsp;
<s:if test="products.currentPage!=products.pageCount">
		<a href="?${param.urlArgs}page=${products.currentPage+1}">下一页</a>
	</s:if>&nbsp;<a href="?${param.urlArgs}page=${products.pageCount}">尾页</a>
	<s:textfield id="goId" cssStyle="width:22px"/><a href="#" onclick="goToPage()">跳转</a>
</s:if>

<script>
function goToPage(){
	var goPage=document.getElementById("goId").value
	if(isNaN(goPage)){
		   alert('输入错误!必须为数字!')
	}else{
		if(goPage>${products.pageCount}){
			alert("输入错误,输入的数字大于总页数");
		}else{
			window.location.href="?${param.urlArgs}page="+goPage;
		}
	}
}
</script>