<%@ page language="java"
	import="java.util.*,com.abbcc.models.AbcCertificate"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<script type="text/javascript" src="<s:url value='/js/jquery.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/jquery/colorize.js'/>"></script>
<script type="text/javascript" src="<s:url value="/js/util/context.jsp"/>"></script>
<script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
<script type="text/javascript" src="<s:url value='/user/js/common.js'/>"></script>
<script type="text/javascript" src="<s:url value='/user/js/show_onmouseover.js'/>"></script>
<link rel="stylesheet" type="text/css" href="<s:url value='/user/css/validation.css'/>">
<link rel="stylesheet" type="text/css" href="<s:url value='/admin/css/common.css'/>" />
<link rel="stylesheet" type="text/css" href="<s:url value='/user/abbcc/css/common.css'/>">
<script type="text/javascript">
$(document).ready(function(){
	$.ajax({
        type: "post",
        dataType: "json",
        url: contextRoot + "/user/viewlog/viewTodayLog",
        data: { },
        success: function(data){
            data = eval('(' + data + ')');  
			formatData(data);
        }
    });
});

formatData = function(data){
	$("#listBody").empty();
	if(data.length==0){
		$("#listBody").html("<tr><td valign=\"middle\" align=\"center\" colspan=\"5\">暂无访问记录</td></tr>");
	}
	else {
		for(var i=0; i<data.length; i++){
			var logObj = data[i];
			var viewer = "匿名";
			if(logObj.username!="")
				viewer = $.trim(logObj.username);
			var html = "<tr><td valign=\"middle\" align=\"center\">"+formatJsonDateToTime(logObj.viewTime)+"</td>"
				+"<td valign=\"middle\" align=\"center\">"+logObj.location+"</td>"
				+"<td valign=\"middle\" align=\"center\">"+logObj.ip+"</td>"
				+"<td valign=\"middle\" align=\"center\">"+viewer+"</td>"
				+"<td valign=\"middle\" align=\"center\">"+(i+1)+"</td></tr>";
			$("#listBody").append(html);
		}
	}
}
</script>

</head>
<body>

<br>
<div class="listTab">
	<dl class="focus"  onclick="window.location.href='view_main.jsp'">
		今日统计<span class="errorSpan"></span>
	</dl>
	<dl onclick="window.location.href='view_three.jsp'">
		<前三天统计<span class="errorSpan"></span>
	</dl>
	<dl onclick="window.location.href='view_week.jsp'">
		本周统计<span class="errorSpan"></span>
	</dl>
	<dl onclick="window.location.href='view_thirty.jsp'">
		前三十天统计<span class="errorSpan"></span>
	</dl>
</div>

<table class="listTable">
	<thead>
		<tr id="listTitle">
			<td >访问时间</td>
			<td >访问地点</td>
			<td >IP地址</td>
			<td >访问者</td>
			<td >访问排序</td>
		</tr>
	</thead>
	<tbody id="listBody">
	</tbody>
</table>


</body>
</html>
