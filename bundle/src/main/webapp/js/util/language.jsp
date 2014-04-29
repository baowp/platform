<%@ page language="java" contentType="text/javascript; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
var lan={
<s:iterator value="@com.abbcc.common.CommonConst@languagePack.entrySet()">
	"${key}" : "${value}",
</s:iterator>
	0:0
};
