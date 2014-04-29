<%@ page language="java" contentType="text/javascript; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:action name="%{parameters.username}/layout" namespace="/vip"></s:action>
(function(){
	var gaimQQ={
	<s:iterator value="#request['layout']['laytheme']['jsonStyle']['#gaim-qq']">
		'${key}':'${value}',
	</s:iterator>
		0:0
	};
	$.extend(variable.gaimQQ,gaimQQ);
})();
