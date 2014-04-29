<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
function close1(){
	$("#box1").hide();
}
function close2(){
	$("#box2").hide();
}
function setHomepage() {
	 var href = "http://" + location.host;
	 if (document.all) {
	 document.body.style.behavior = 'url(#default#homepage)';
	 document.body.setHomePage(href);
	
	 } else if (window.sidebar) {
	 if (window.netscape) {
	 try {
	 netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
	 var prefs = Components.classes['@mozilla.org/preferences-service;1'].getService(Components.interfaces.nsIPrefBranch);
	 prefs.setCharPref('browser.startup.homepage', href);
	 } catch (e) {
	 alert("\u8be5\u64cd\u4f5c\u88ab\u6d4f\u89c8\u5668\u62d2\u7edd\uff0c\u5982\u679c\u60f3\u542f\u7528\u8be5\u529f\u80fd\uff0c\u8bf7\u5728\u5730\u5740\u680f\u5185\u8f93\u5165 about:config,\u7136\u540e\u5c06\u9879 signed.applets.codebase_principal_support \u503c\u8be5\u4e3atrue");
	 }
	 }
	 }
	} 
</script>
<style>
ul,li {
	margin: 0;
	padding: 0
}
</style>
<style type="text/css">
* {
	padding: 0;
	margin: 0;
}

html {
	height: 100%;
}


.box {
	position: absolute;
}
</style>
<style>
.jc {
	position: absolute;
	left: 80px;
	top: 176px;
	color: #333333;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
}

.jc a:link {
	color: #333333;
	text-decoration: none;
}

.jc a:visited {
	color: #333333;
	text-decoration: none;
}
</style>
<div class="header">
<div class="header_left">
  <ul>
    <li><a href="javascript:addPage()">收藏本站</a></li>
    <li>|</li>
    <li><a href="javascript:setHomepage()">设为主页</a></li>
  </ul>
  </div>
</div>