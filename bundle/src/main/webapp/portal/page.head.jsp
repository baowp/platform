<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="primary_site" class="head">
	<div class="site-bar">
	    <div class="m">
	      <div class="buy-sell"> <a href="http://search.paipai.com/?PTAG=20034.1.7" class="buy">我要买</a> <a href="http://auction.paipai.com/cgi-bin/rd?pageId=388&amp;domainId=31&amp;linkId=5&amp;url=http://my.paipai.com/cgi-bin/sell&amp;PTAG=20034.1.8">我要卖</a> <a href="http://member.paipai.com/cgi-bin/c2cUser_auth_agree?PTAG=30407.2.3">免费开店</a> </div>
	      <ul class="site-link">
	        <li id="headLogin" class="login">您好，欢迎来东方五金！<a href="http://member.paipai.com/cgi-bin/login_entry?PTAG=20034.1.1">登录</a>　<a href="http://freereg.qq.com/?PTAG=20034.1.2">免费注册</a></li>
	        <li><a href="http://bbs.paipai.com/main.shtml?PTAG=20034.1.10">社区</a></li>
	        <li id="helpLi" class="help">
	          <p><a href="http://help.paipai.com/index.shtml?PTAG=20034.1.11">帮助</a><span id="helpSpan"></span></p>
	        </li>
	        <li><a href="http://service.qq.com/category/paipai.html">客服</a></li>
	      </ul>
	    </div>
  	</div>
	<div id="search_bar" class="m">
		<div class="search-bar">
	      <div class="logo"></div>
	      <div class="search-form">
	        <form target="_self" method="get" autocomplete="off" onsubmit="return false" name="searchForm" id="searchForm">
	          <div id="searchTab" class="search-tabs">
	          	<p class="now" attr="0"><span>产品</span></p>
	          	<p attr="1" class=""><span>供求</span></p>
	          	<p attr="2" class=""><span>新闻</span></p>
	          </div>
	          <div class="search-input">
							<input type="hidden" value="0" name="searchType" id="searchType"><input type="text" value="" name="KeyWord" id="KeyWord" autocomplete="off"><button id="headSubmitBtn" type="submit"></button>
	            <span class="tl"></span> <span class="tr"></span>
	            <div id="showAutoResult" class="auto-result"></div>
	            <iframe scrolling="no" frameborder="0" id="maskAuto" class="mask-auto" style="display: none;"></iframe>
	          </div>
	        </form>
	        <div class="hot-items" style="visibility: hidden"> 热门：<a href="http://chong.paipai.com/index.shtml?PTAG=20032.1.1" target="_blank">充话费，送返利</a><a href="http://list1.paipai.com/0,21036-0,21037/l---1-40-80-21037--1-4-1--7000--2-2-512-128-1-0-pi,314436413d3c363738393a3b3c3d3e3f20212356272c222f282f2b2a24585d59-sf,3.html?PTAG=20028.16.1" target="_blank">2010新单鞋</a><a href="http://www.paipai.com/shang/2010/01/daren/?PTAG=20028.16.5" target="_blank">化妆品试用</a><a href="http://www.paipai.com/mall/20501.shtml?PTAG=20028.16.2" target="_blank">潮流女装</a><a href="http://list1.paipai.com/0,204260/l---1-40-15-204260--1-4-3----2-2--131200-1-0-sf,21.html" target="_blank">正品手机</a><a href="http://sse1.paipai.com/0,204260/s-4o4pbyzn4479x--1-40-13-204260--1-4-1----2-2--128-0-0.html" target="_blank">音乐手机</a><a href="http://www.paipai.com/man/search/?PTAG=20028.16.9" target="_blank">时尚男装</a> </div>
	      </div>
	      <div class="search-more" style="display: none">
	        <p><a href="http://help.paipai.com/index.shtml?PTAG=20034.1.11" target="_blank">如何开始？</a></p>
	        <p><a href="http://search.paipai.com/search_goods.shtml?PTAG=20028.4.15" target="_blank">高级搜索</a></p>
	      </div>
	    </div>
	</div>
</div>