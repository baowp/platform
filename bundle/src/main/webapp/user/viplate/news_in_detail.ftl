<#assign news=action.root.news>
	<style>
#passit_def_div {
    display: inline;
}
.newsContent {
    clear: both;
}
</style>
<div class="bodyCont mainTextColor">
	<div class="wangpuBodyContTitle glitzColor">
    	<span class="b titleName wangpuTopTitle">${news.title!''}</span>&nbsp;
    </div>
    <div style="margin: 8px auto; min-height: 320px; width: 700px;">
		<div class="newsInfo">
			<span><!--Passit BUTTON BEGIN-->
				<script type="text/javascript">
bookmark_service_div="kxzt,qqxy,baiduHi,bookmark,baidu,douban,sohuweibo,163weibo,qqweibo,more";
bookmark_service="qqkj,sinaweibo,xnzt,qq,more";</script>
<div class="passit_def_div"><a class="passit_default" href="http://www.passit.cn/bookmark.html" target="_blank">${lan['news.share']}:</a></div>
<script type="text/javascript">
var passit_title = ${news.title!''};//自定义分享标题，删除和留空表示使用默认
var passit_url = "";//自定义分享网址，删除和留空表示使用默认
var passit_content= ${news.content!''};//自定义分享内容，删除和留空表示使用默认
</script>
<script type="text/javascript" src="http://www.passit.cn/js/passit_default_new.js?pub=0&simple=1" charset="UTF-8"></script>
<!--Passit BUTTON END-->
			<span>${lan['news.origin']}${news.origin!''}</span>
			<span>${lan['news.author']}${news.author!''}</span>
			<span>${lan['news.addTime']}<#if news.addTime??>${news.addTime?string(lan['news.format'])}</#if></span>
</span>
		</div>
		<div class="newsContent">${news.content !''}</div>
    </div>   
</div>
<script type="text/javascript">
$(function(){
$(".passit_default").mousemove(function(){
	$("#passit_box_con_bottom").remove();
	$("#passit_box_con_top").remove();
})
	
})
</script>