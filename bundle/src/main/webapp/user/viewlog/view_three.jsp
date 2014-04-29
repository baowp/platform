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
var DataList = new Array();
$(document).ready(function(){
	$.ajax({
        type: "post",
        dataType: "json",
        url: contextRoot + "/user/viewlog/viewThreeLog",
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
		var objList = new Array();
		var index = 0;
		var lastDate = formatJsonDateToDate(data[0].viewTime);
		var ipStr = data[0].ip+"|";
		var userStr = data[0].username+"|";
		var pvNum = 1;
		var ipNum = 1;
		var userNum = 1;
		var perNum = 1.00;
		objList.push(data[0]);
		for(var i=1; i<data.length; i++){
			var logObj = data[i];
			var nowDate = formatJsonDateToDate(logObj.viewTime);
			var nowIP = new RegExp((logObj.ip),"g");
			var nowUser = new RegExp((logObj.username),"g");
			if(nowDate==lastDate){
				pvNum++;
				if(!nowIP.test(ipStr)){
					ipNum++;
					ipStr+=logObj.ip+"|";
				}
				if(logObj.username=="" || !nowUser.test(userStr)){
					userNum++;
					ipStr+=logObj.username+"|";
				}
				objList.push(logObj);
			}
			else {
				perNum = parseFloat(pvNum/userNum).toFixed(2);
				var html = "<tr><td valign=\"middle\" align=\"center\">"+lastDate
					+"<a id=\"op"+index+"\" href=\"javascript:showDetail("+index+")\" style=\"margin-left:5px\">展开</a></td>"
					+"<td valign=\"middle\" align=\"center\">"+pvNum+"</td>"
					+"<td valign=\"middle\" align=\"center\">"+ipNum+"</td>"
					+"<td valign=\"middle\" align=\"center\">"+userNum+"</td>"
					+"<td valign=\"middle\" align=\"center\">"+perNum+"</td></tr>"
					+"<tr id=\"detail"+index+"\" style=\"display:none\"></tr>";
				$("#listBody").append(html);
				lastDate = nowDate;
				ipStr = logObj.ip+"|";
				userStr = logObj.username+"|";
				pvNum = 1;
				ipNum = 1;
				userNum = 1;
				perNum = 1.00;
				index++;
				DataList.push(objList);
				objList = new Array();
				objList.push(logObj);
			}
			if(i==(data.length-1)){
				perNum = parseFloat(pvNum/userNum).toFixed(2);
				var html = "<tr><td valign=\"middle\" align=\"center\">"+lastDate
					+"<a id=\"op"+index+"\" href=\"javascript:showDetail("+index+")\" style=\"margin-left:5px\">展开</a></td>"
					+"<td valign=\"middle\" align=\"center\">"+pvNum+"</td>"
					+"<td valign=\"middle\" align=\"center\">"+ipNum+"</td>"
					+"<td valign=\"middle\" align=\"center\">"+userNum+"</td>"
					+"<td valign=\"middle\" align=\"center\">"+perNum+"</td></tr>"
					+"<tr id=\"detail"+index+"\" style=\"display:none\"></tr>";
				$("#listBody").append(html);
				objList.push(logObj);
				DataList.push(objList);
			}
		}
	}
}

showDetail = function(index){
	if($("#detail"+index).css("display")=="none"){
		if($("#detail"+index).html()==""){
			formatDetail(index);
		}
		$("#detail"+index).show();
		$("#op"+index).html("收缩");
	}
	else {
		$("#detail"+index).hide();
		$("#op"+index).html("展开");
	}
}

formatDetail = function(index){
	var html="<td valign=\"middle\" align=\"center\" colspan=\"5\" style=\"padding:2px 5px\">"
		+"<table class=\"listTable\"><thead><tr id=\"listTitleDetail\">"
		+"<td valign=\"middle\" align=\"center\">访问时间</td><td valign=\"middle\" align=\"center\">访问地点</td>"
		+"<td valign=\"middle\" align=\"center\">IP地址</td><td valign=\"middle\" align=\"center\">访问者</td>"
		+"<td valign=\"middle\" align=\"center\">访问排序</td></tr></thead><tbody>";
	var items = DataList[index];
	for(var i=0; i<items.length; i++){
		var logObj = items[i];
		var viewer = "匿名";
		if(logObj.username!="")
			viewer = $.trim(logObj.username);
		html += "<tr><td valign=\"middle\" align=\"center\">"+formatJsonDateToTime(logObj.viewTime)+"</td>"
				+"<td valign=\"middle\" align=\"center\">"+logObj.location+"</td>"
				+"<td valign=\"middle\" align=\"center\">"+logObj.ip+"</td>"
				+"<td valign=\"middle\" align=\"center\">"+viewer+"</td>"
				+"<td valign=\"middle\" align=\"center\">"+(i+1)+"</td></tr>";
	}
	html += "</tbody></table></td>";
	$("#detail"+index).html(html);
}

</script>

</head>
<body>

<br>
<div class="listTab">
	<dl>
		<dt></dt>
		<dd onclick="window.location.href='view_main.jsp'">今日统计<span class="errorSpan"></span>
		<dt></dt>
	</dl>
	<dl class="focus">
		<dt></dt>
		<dd onclick="window.location.href='view_three.jsp'">前三天统计<span class="errorSpan"></span>
		<dt></dt>
	</dl>
	<dl>
		<dt></dt>
		<dd onclick="window.location.href='view_week.jsp'">本周统计<span class="errorSpan"></span>
		<dt></dt>
	</dl>
	<dl>
		<dt></dt>
		<dd onclick="window.location.href='view_thirty.jsp'">前三十天统计<span class="errorSpan"></span>
		<dt></dt>
	</dl>
</div>

<table class="listTable">
	<thead>
		<tr id="listTitle">
			<td >访问时间</td>
			<td >PV数</td>
			<td >单独IP数</td>
			<td >访问者数</td>
			<td >人均访问次数</td>
		</tr>
	</thead>
	<tbody id="listBody">

	</tbody>
</table>


</body>
</html>
