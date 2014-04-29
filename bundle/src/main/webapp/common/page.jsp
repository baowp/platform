<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<style type="text/css">
.body {
	background-image: url(text.txt); /* for IE6 */
	background-attachment: fixed;
}

#bottomNav {
	background-color: #DCDCDC;
	z-index: 999;
	position: fixed;
	bottom: 0;
	left: 0;
	width: 100%;
	_position: absolute; /* for IE6 */
	_top: expression(documentElement.scrollTop +  
		documentElement.clientHeight-this.offsetHeight); /* for IE6 */
	overflow: visible;
	text-align:center;
}

</style>
<script type="text/javascript" src="/common/DivCoverSelect.js"></script>
<script>

$(function(){
$('#bottomNav').bgiframe();
})
</script>
<div id="bottomNav">
总共${pageList.totalCount}条记录，共${pageList.pageCount}页
<s:if test="pageList.pageCount>0">，现在第${pageList.currentPage}页&nbsp;
<s:if test="pageList.currentPage!=1">
<a href="?${param.urlArgs}page=1">首页</a>&nbsp;
		<a href="?${param.urlArgs}page=${pageList.currentPage-1}">上一页</a>
	</s:if>&nbsp;
<s:if test="pageList.currentPage!=pageList.pageCount">
		<a href="?${param.urlArgs}page=${pageList.currentPage+1}">下一页</a>
	&nbsp;<a href="?${param.urlArgs}page=${pageList.pageCount}">尾页</a></s:if>
	<s:textfield id="goId" cssStyle="width:22px"/><a href="#" onclick="goToPage()">跳转</a>
</s:if>
<script>
function goToPage(){
	var goPage=document.getElementById("goId").value
	if(isNaN(goPage)){
		   alert('输入错误!必须为数字!')
	}else{
		if(goPage>${pageList.pageCount}){
			alert("输入错误,输入的数字大于总页数");
		}else{
			window.location.href="?${param.urlArgs}page="+goPage;
		}
	}
}
</script>
</div>