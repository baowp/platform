<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style type="text/css">
.odd {
}

.even {
	background-color: #f0f9fd /* pale blue for even rows */
}

.hover {
	background-color: #F2F8FD /* pale blue for even rows */
}
</style>
<script type="text/javascript">
$(function(){
	$('td').addClass('odd');
	$('td:even').addClass('even'); //奇偶变色，添加样式
	$('dd').addClass('odd');
	$('dt:even').addClass('even'); //奇偶变色，添加样式
	$('tr').hover(
			  function () {
			    $(this).addClass('hover');
			  },
			  function () {
			    $(this).removeClass('hover');
			  }
			);
	$('dl').hover(
			  function () {
			    $(this).addClass('hover');
			  },
			  function () {
			    $(this).removeClass('hover');
			  }
			);
})
</script>