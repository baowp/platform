<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="/js/artDialog3.0.5/artDialog.min.js"></script>
<s:if test="result!=''&&result!=null">
<input type="hidden" value="${result}" id="re"/>
		<script>
		if(jQuery("#re").val()!='')
			art.dialog.tips(jQuery("#re").val(), 1);
		</script>
</s:if>