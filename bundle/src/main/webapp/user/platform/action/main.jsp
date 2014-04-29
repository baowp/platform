<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<LINK rel=stylesheet type=text/css href="../css/main.css" id="skin">
<link id="artDialogSkin" href="/js/artDialog3.0.5/skin/default.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/artDialog3.0.5/artDialog.min.js"></script>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="../js/artCommon.js"></script>
<script type="text/javascript" src="/user/enterprise/js/enterprise.js"></script>
<script type="text/javascript" src="../js/main.js"></script>
<script type="text/javascript" src="/js/jquery/highchart/highcharts.js"></script>
<script src="/js/jquery/tips/jquery.qtip-1.0.0-rc3.min.js"
	type="text/javascript"></script>

<!-- 1a) Optional: add a theme file -->
<!--
			<script type="text/javascript" src="/js/jquery/highchart/themes/gray.js"></script>
		-->

<!-- 1b) Optional: the exporting module -->
<script type="text/javascript"
	src="/js/jquery/highchart/modules/exporting.js"></script>
<script type="text/javascript">
	function loadingIframe(url) {
		location.href = url;
	}
	$(function() {
		$(".win-tabs-list li").mouseover(function() {
			$(".win-tabs-list .selected").removeClass("selected");
			$(this).addClass("selected");
			$(".showThis").removeClass("showThis");
			$("#" + $(this).attr("id") + "_list").addClass("showThis");
		})
	})
	function custom(){
		art.dialog.open('/user/platform/action/custom.jsp', {
			id : 'customIframe',
			skin : 'aero',
			icon: 'succeed',
			title : '定制求购',
			width:320,
			height:70,
			yesFn: function(iframeWin, topWin){
				var txt = iframeWin.document.getElementById('customByFrame').value;
		    	if(txt.trim()==''){
		    		return true;
		    	}
		    	$.ajax({
		    		type:'post',
		    		url:'/user/tailor/edit',
		    		data:{
		    			content:txt,
		    			type:'BU'
		    		},
		    		success:function(result){
		    			if(result!=null){
		    				var supplyList = eval('(' +result+ ')');
		    				 var r="";
		    					$.each(supplyList,function(i,n){  
		    				 		 r+="<tr onmouseOver=\"this.style.backgroundColor='#F2F8FD';\"onmouseout=\"this.style.backgroundColor='white';\"><td height=\"25\"><a href=\"/jump.html?url="+n.url+"\">"+n.title.replace(txt,'<font color=\"red\">'+txt+'</font>')+"</a></td></tr>";      
		    						 
		    			     }); 
		    				$("#tailorTable").html(r);
		    			}else{
		    				alert('没有查询到相关信息!')
		    			}
		    		}
		    	})
		    	return true;
		        //return false; //如果返回false将阻止关闭
		    }
		});
	}
</script>
</head>
<body>
<div class="work-content-r-c">
<div class="work-content-r-c-wrap"><s:action name="proBroadcast"
	namespace="/ranking"></s:action><s:action name="supply"
	namespace="/ranking"></s:action>
<div id="mod-wp-container" class="mod-wp-container">
<div id="wp-tab-index" class="mod-wp-tab wp-tab-index">
<div class="wp-tab-index-wrap">
<table class="work-content-table">
	<tbody>
		<tr>
			<td class="table-td-layout work-content-r-l">
			<div class="work-content-r-l-wrap">
			<div class="mod-wp-win">
			<div class="win-head-wrap">
			<div id="mod-info-center-head" class="win-head">
			<p>
			<table>
				<tr>
					<td class="win-head" style="font-size:14px;">热门产品</td>
					<td><img src="../images/hot-010.gif" border="0" /></td>
				</tr>
			</table>
			</p>
			</div>
			</div>
			<div id="mod-line-chart" class="mod-line-chart clr">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" style="table-layout: fixed">
				<s:iterator value="#request['proList']" status="jp">
					<tr onmouseOver="this.style.backgroundColor='#F2F8FD';"
						onmouseout="this.style.backgroundColor='white';">
						<td width="36" height="25" align="center" valign="middle"><img
							src="/ranking/images/${jp.index+1}.jpg" width="15" height="16" /></td>
						<td height="25" width="190"><a href="/jump.html?url=${productPath}"
							target="_blank" id="pro-${jp.index+1 }"
							rel="<img src='${mainPic.picUrl(5)}'/>">${name}</a></td>
						<td height="25" align="center">${viewsum}</td>
					</tr>
				</s:iterator>
			</table>
			</div>
			</div>
			<div class="mod-wp-win">
			<div class="win-head-wrap">
			<div id="mod-info-center-head" class="win-head"><span
				style="float: left;font-size:14px;"><a href="/user/product/supply_bu.jsp" target="_blank">求购推送</a></span><input type="button" onclick="custom()" id="customs" value="1" class="tailor" />
			</div>
			</div>
			<div id="mod-line-chart" class="mod-line-chart clr">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" id="tailorTable"  style="table-layout: fixed">
				<s:iterator value="#request['supply']" status="jp">
					<tr onmouseOver="this.style.backgroundColor='#F2F8FD';"
						onmouseout="this.style.backgroundColor='white';">
						<td height="25" width="90%"><a target="_blank" href="/ranking/supplyinfo?supplyId=${supplyId}">${title}</a></td>
					</tr>

				</s:iterator>
			</table>
			</div>
			</div>
			<div id="mod-line-chart-win" class="mod-wp-win mt8">
			<div id="mod-line-chart" >
			<div id="container"
				style="width: 540px; height: 220px; margin: 0 auto"></div>
			</div>
			</div>

			<!--mod-wp-win mt8--></div>

			</td>
			<td class="table-td-layout work-content-r-r">
			<div class="mod-wp-win-tabs">
			<div class="" id="win-tabs-wrap">
			<ul class="win-tabs-list clr">
				<li class="f-tab-t selected" id="notice"><em>推荐企业</em></li>
				<li class="f-tab-t" id="interaction"><em>平台公告</em></li>
				<li class="f-tab-t" id="help"><em id="mod-public-tip">帮助引导</em></li>
			</ul>
			<div class="mod-public-bulletin showThis" id="notice_list"><s:action
				name="rightent" namespace="/user"></s:action>
			<ul class="notice-list">
				<s:iterator value="%{#request.ent.items}">
					<li><a href=" http://${url}" target="_blank">${name}</a></li>
				</s:iterator>
			</ul>
			</div>
			<div id="interaction_list" class="mod-public-bulletin">
			<ul class="notice-list">
				<s:iterator var="user" value="%{#session.announceList}" status="st">
					<s:if test="%{#st.index<5}">
						<li><a href="/user/news/announceshow?id=${newsId}"><s:if
							test="title.length()>15">
							<s:property value="title.substring(0,15)" />
						</s:if><s:else>${title}</s:else></a><s:if test="%{#st.index<3}">
							<span><img height="10" width="16"
								src="/user/abbcc/images/new_l_1231470880480.gif"></img></span>
						</s:if></li>
					</s:if>
				</s:iterator>
			</ul>
			</div>
			<div id="help_list" class="mod-public-tip mod-public-bulletin">
			<ul class="tip">
				<li class="no-dot"><a href="/user/help/show" id="helplink"
					title="工作平台使用帮助">工作平台使用帮助</a></li>
			</ul>
			</div>

			</div>
			</div>

			<div class="mod-wp-win">
			<div class="win-head-wrap">
			<div id="mod-mytools" class="win-head">
			<p>实用工具：</p>
			</div>
			</div>
			<div id="mod-mytools" class="mod-mytools">
			<div class="mod-mytools-wrap"><a class="tool-v12"
				href="javascript:" id="almanac" title="黄历查询">黄历查询</a> <a
				class="tool-v22" href="javascript:" id="calc" title="计算器">计算器</a> <a
				class="tool-v82" href="javascript:" id="weather" title="天气预报">天气预报</a>
			<a class="tool-v92" href="javascript:" id="photoshop"
				title="在线photoshop">在线PS</a></div>
			</div>
			</div>
			</td>
		</tr>
	</tbody>
</table>
</div>
</div>
</div>
</div>
</div>
<div id="hidDiv" style="display:none;"><input type="text" value="1230"/> </div>
<script type="text/javascript">
	$(function() {
		$("a[id^='pro-']").each(function(){
			$(this).qtip({
				// Simply use an HTML img tag within the HTML string
				content : $(this).attr("rel")
			});
		});
	})
</script>
</body>
</html>