<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	<s:if test="uploadFilePath==null">
		<s:if test="feedback.size>0">
			parent.alert('<s:property value="feedback[0]"/>');
		</s:if>
	</s:if>
	<s:else>
		parent.${callback}('${uploadFilePath}');
	</s:else>
</script>
</head>
<body>

</body>
</html>