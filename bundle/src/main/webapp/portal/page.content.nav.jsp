<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div style="margin-top: 0px;" class="navbar fff blod">
    <a href="http://www.eccn.com/"><b>首页</b></a><span>|</span><a href="http://news.eccn.com/"><b>新闻</b></a><span>|</span><a href="http://products.eccn.com/"><b>新品</b></a><span>|</span><a href="http://design.eccn.com/"><b>技术文库</b></a><span>|</span><a target="_blank" href="http://solution.eccn.com/">解决方案</a><span>|</span><a href="http://seminar.eccn.com/">在线座谈</a><span>|</span><a href="http://training.eccn.com/"><b>培训</b></a><span>|</span><a href="http://action.eccn.com/"><b>活动</b></a><span>|</span><a href="http://shop.eccn.com/"><b>商城</b></a><span>|</span><a href="http://video.eccn.com/"><b>视频</b></a><span>|</span><a href="http://baike.eccn.com/"><b>百科</b></a><span>|</span><a href="http://icco.eccn.com/"><b>黄页</b></a><span>|</span><a href="http://download.eccn.com/"><b>下载</b></a><span>|</span><a target="_blank" href="http://blog.eccn.com/"><b>博客</b></a><span>|</span><a target="_blank" href="http://bbs.eccn.com/"><b>论坛</b></a>
</div>
<div class="zhuanti">
	<b class="js_title">行业门户：</b>
	<ul>
		<s:iterator value="#request['syscodeList']">
			<li><a href="<s:url value="/portal/%{syscodeId}"/>">${name }</a><span>|</span></li>
		</s:iterator>
	</ul>
</div>