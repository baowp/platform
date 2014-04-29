<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="http://style.china.alibaba.com/css/myalibaba/favorites/merge/favorites.css">
<title>Insert title here</title>
</head>
<body>
<div class="favorites" id="new-content">
<div class="favorites-header">
<h1>我的收藏</h1>
<div class="favorites-survey"><a target="_blank"
	title="说说您使用收藏夹的感受"
	href="http://survey.china.alibaba.com/index.php?sid=19588&amp;newtest=Y&amp;lang=zh-Hans&amp;tracelog=favor_userstudy_201011_Backstage">说说您使用<em>收藏夹</em>的感受</a></div>
</div>
<form
	onmousedown="return aliclick(this,'?tracelog=favor_assistant_search');"
	onsubmit="return myalibaba.IFavorites.checkSearchForm(this)"
	action="http://athena.china.alibaba.com/favorites/sale.htm"
	class="favorites-search" name="searchForm" id="searchFormByType"
	method="get"><label for="keywords">在收藏的信息中查找：</label> <select
	id="content_type" name="content_type">
	<option myhref="http://athena.china.alibaba.com/favorites/sale.htm"
		selected="true" value="OFFER_SALE">供应</option>
	<option myhref="http://athena.china.alibaba.com/favorites/buy.htm"
		value="OFFER_BUY">求购</option>
	<option myhref="http://athena.china.alibaba.com/favorites/company.htm"
		value="COMPANY">公司</option>
</select> <input type="text" value="" maxlength="20" id="keywords"
	name="keywords">&nbsp;
<button type="submit">查找</button>
</form>
<ul class="favorites-tab">
	<li><span class="tab-right-unlink"><a
		onmousedown="return aliclick(this,'?tracelog=favor_assistant_tab_sell');"
		title="供应信息" href="http://athena.china.alibaba.com/favorites/sale.htm">供应信息</a>
	</span></li>
	<li class="t1"><span class="tab-right-link"><a
		onmousedown="return aliclick(this,'?tracelog=favor_assistant_tab_buy');"
		title="求购信息" href="http://athena.china.alibaba.com/favorites/buy.htm">求购信息</a></span></li>
	<li class="t1"><span class="tab-right-link"><a
		onmousedown="return aliclick(this,'?tracelog=favor_assistant_tab_com');"
		title="公司信息"
		href="http://athena.china.alibaba.com/favorites/company.htm">公司信息</a></span></li>
	<li class="use-help"><a
		onmousedown="return aliclick(this,'?tracelog=favor_assistant_help');"
		target="_blank" title="使用帮助"
		href="http://service.china.alibaba.com/kf/list/228.html">使用帮助</a></li>

</ul>
<div class="favorites-class"><span class="favorites-class-label">标签：</span>
<span id="favorites-classes" class="favorites-classes"> <span>
<a
	onmousedown="return aliclick(this,'?tracelog=favor_assistant_taglist');"
	title="h"
	href="http://athena.china.alibaba.com/favorites/sale.htm?tag_id=97572">h</a>
<span>(1)、</span> </span> </span></div>
<form id="deleteForm" method="post" name="deleteForm"><input
	type="hidden" value="OFFER_SALE" name="_fmf.b._0.co"> <input
	type="hidden" value="Remove" name="action"> <input
	type="hidden" value="Remove" name="eventSubmit_doRemove"> <input
	type="hidden" value="6cda22498c301bf91438f91d069ac0e1"
	name="_csrf_token">
<table id="content-list-b" class="favorites-list">
	<colgroup>
		<col class="favorites-list-check">
		<col class="favorites-list-image">
		<col class="favorites-list-content">
		<col class="favorites-list-operation">
	</colgroup>
	<thead>
		<tr class="bt1">
			<td class="favorites-center favorites-ie-btm"><input
				type="checkbox" id="choose-all-top"></td>
			<td colspan="3"><span class="choose-all">全选</span> <span
				class="favorites-button"> <span
				onmousedown="return aliclick(this,'?tracelog=favor_assistant_sell_contrast');"
				id="topCompareBtn" class="button-contrast button-cp">对比选中的信息</span>
			<span
				onmousedown="return aliclick(this,'?tracelog=favor_assistant_batchdel');"
				id="topDeleteDataBtn" class="button-delete button-cp">删除</span> </span> <span
				class="favorites-page"> <span class="pagination"> <span
				class="page-first">&nbsp;</span> <span class="page-cur">1</span> <span
				class="page-last">下一页</span> </span> <span>共有2条</span> </span></td>
		</tr>
	</thead>
	<tfoot>
		<tr>
			<td class="favorites-center favorites-ie-btm"><input
				type="checkbox" id="choose-all-bottom"></td>
			<td colspan="3"><span class="choose-all">全选</span> <span
				class="favorites-button"> <span
				onmousedown="return aliclick(this,'?tracelog=favor_assistant_sell_contrast');"
				id="bottomCompareBtn" class="button-contrast button-cp">对比选中的信息</span>
			<span
				onmousedown="return aliclick(this,'?tracelog=favor_assistant_batchdel');"
				id="bottomDeleteDataBtn" class="button-delete button-cp">删除</span> </span>
			<span class="favorites-page"> <span class="pagination">
			<span class="page-first">&nbsp;</span> <span class="page-cur">1</span>
			<span class="page-last">下一页</span> </span> <span>共有2条</span> </span></td>
		</tr>
	</tfoot>
	<tbody id="tbody38757749">
		<tr class="favorites-info">
			<td class="favorites-check"><input type="checkbox"
				name="_fmf.b._0.c" value="38757749" id="contentId1" offer="y"></td>
			<td>
			<div class=""><a
				onmousedown="return aliclick(this,'?tracelog=favor_assistant_sell_offerimg');"
				target="_blank" title="供应平板车"
				href="http://detail.china.alibaba.com/buyer/offerdetail/38757749.html">
			<img height="42" width="64" alt="产品图片"
				src="http://i01.c.aliimg.com/img/offer/38/75/77/49/38757749.summ.jpg"
				style="visibility: visible;"> </a></div>
			</td>
			<td class="favorites-details">
			<p class="t1"><a
				onmousedown="return aliclick(this,'?tracelog=favor_assistant_sell_offertitle');"
				target="_blank" title="供应平板车"
				href="http://detail.china.alibaba.com/buyer/offerdetail/38757749.html"
				class="favorites-title">供应平板车</a></p>
			<p><span class="favorites-adr">广东中山市</span> 阿里巴巴价：<span
				class="favorites-price">30000.00</span>元/台</p>
			<p><a
				onmousedown="return aliclick(this,'?tracelog=favor_assistant_sell_com');"
				target="_blank" title="中山市东区海菱机械设备厂"
				href="http://zshigher.cn.alibaba.com/" class="favorites-trust">中山市东区海菱机械设备厂</a>
			<a
				onmousedown="return aliclick(this,'?tracelog=favor_assistant_sell_contact');"
				target="_blank" title="查看联系方式"
				href="http://china.alibaba.com/company/detail/contact/zshigher.html"
				class="favorites-contact">查看联系方式</a> <a
				onmousedown="return aliclick(this,'?tracelog=favor_assistant_sell_ww');"
				alitalk="{id:'zshigher',toIdMemberLevel:'pm',loginId:'zshigher',fromId:'dalynet'}"
				title="在线洽谈" href="#" id="ww_38757749" class="iconAlitalk icon-on">在线洽谈</a>
			</p>
			</td>
			<td class="favorites-operation">
			<p class="blank-line">&nbsp;</p>
			<p id="Purchase_38757749" class="operation-rel"><a
				data-url="http://detail.china.alibaba.com/buyer/offerdetail/38757749.html"
				onmousedown="return aliclick(this,'?tracelog=favor_assistant_sell_shoppingcart');"
				onclick="myalibaba.IFavorites.addPurchaseList('38757749','1')"
				href="javascript:void(0)" class="operation-btn">加入进货单</a></p>
			</td>
		</tr>
		<tr class="favorites-bottom bt1">
			<td class="favorites-right" colspan="2"><span
				class="favorites-num">1</span>人收藏</td>
			<td class="favorites-details"><span class="favorites-view"><a
				onmousedown="return aliclick(this,'?tracelog=favor_assistant_sell_more');"
				target="_blank" title="查看同类供应信息"
				href="http://list.china.alibaba.com/buyer/offerlist/1033622.html">查看同类供应信息</a></span>
			<span class="favorites-tags"> <span> 标签： </span> </span></td>
			<td class="favorites-center"><a
				onmousedown="return aliclick(this,'?tracelog=favor_assistant_sell_edit');"
				onclick="myalibaba.IFavorites.editOffer('38757749','1033622','OFFER_SALE')"
				target="_self" href="javascript:void(0)" class="favorites-edit">编辑</a>&nbsp;|&nbsp;<a
				onmousedown="return aliclick(this,'?tracelog=favor_assistant_del');"
				onclick="myalibaba.IFavorites.removeOffer('38757749')"
				target="_self" href="javascript:void(0)" class="favorites-delete">删除</a></td>
		</tr>
	</tbody>
	<tbody id="tbody884198000">
		<tr class="favorites-info">
			<td class="favorites-check"><input type="checkbox"
				name="_fmf.b._0.c" value="884198000" id="contentId2" offer="y"></td>
			<td>
			<div class=""><a
				onmousedown="return aliclick(this,'?tracelog=favor_assistant_sell_offerimg');"
				target="_blank" title="深圳至北京货运公司-深圳到北京货运公司"
				href="http://detail.china.alibaba.com/buyer/offerdetail/884198000.html">
			<img height="42" width="64" alt="产品图片"
				src="http://i01.c.aliimg.com/img/ibank/2010/825/686/121686528_1718793523.summ.jpg"
				style="visibility: visible;"> </a></div>
			</td>
			<td class="favorites-details">
			<p class="t1"><a
				onmousedown="return aliclick(this,'?tracelog=favor_assistant_sell_offertitle');"
				target="_blank" title="深圳至北京货运公司-深圳到北京货运公司"
				href="http://detail.china.alibaba.com/buyer/offerdetail/884198000.html"
				class="favorites-title">深圳至北京货运公司-深圳到北京货运公司</a></p>
			<p><span class="favorites-adr">广东深圳市罗湖区</span></p>
			<p><a
				onmousedown="return aliclick(this,'?tracelog=favor_assistant_sell_com');"
				target="_blank" title="深圳市恒威物流有限公司"
				href="http://szhwwlx.cn.alibaba.com/" class="favorites-trust">深圳市恒威物流有限公司</a>
			<a
				onmousedown="return aliclick(this,'?tracelog=favor_assistant_sell_contact');"
				target="_blank" title="查看联系方式"
				href="http://china.alibaba.com/company/detail/contact/szhwwlx.html"
				class="favorites-contact">查看联系方式</a> <a
				onmousedown="return aliclick(this,'?tracelog=favor_assistant_sell_ww');"
				alitalk="{id:'szhwwlx',toIdMemberLevel:'pm',loginId:'szhwwlx',fromId:'dalynet'}"
				title="在线洽谈" href="#" id="ww_884198000" class="iconAlitalk icon-on">在线洽谈</a>
			</p>
			</td>
			<td class="favorites-operation">
			<p class="not-support">不支持在线交易</p>
			<div class="operation-rel"><a
				onmousedown="return aliclick(this,'?tracelog=favor_assistant_sell_ww');"
				href="javascript:$('ww_884198000').click();" class="operation-btn">在线洽谈</a>
			</div>
			</td>
		</tr>
		<tr class="favorites-bottom bt1">
			<td class="favorites-right" colspan="2"><span
				class="favorites-num">1</span>人收藏</td>
			<td class="favorites-details"><span class="favorites-view"><a
				onmousedown="return aliclick(this,'?tracelog=favor_assistant_sell_more');"
				target="_blank" title="查看同类供应信息"
				href="http://list.china.alibaba.com/buyer/offerlist/1033622.html">查看同类供应信息</a></span>
			<span class="favorites-tags"> <span> 标签： <a
				onmousedown="return aliclick(this,'?tracelog=favor_assistant_taglist');"
				title="h"
				href="http://athena.china.alibaba.com/favorites/sale.htm?tag_id=97572">h</a>
			</span> </span></td>
			<td class="favorites-center"><a
				onmousedown="return aliclick(this,'?tracelog=favor_assistant_sell_edit');"
				onclick="myalibaba.IFavorites.editOffer('884198000','1033622','OFFER_SALE')"
				target="_self" href="javascript:void(0)" class="favorites-edit">编辑</a>&nbsp;|&nbsp;<a
				onmousedown="return aliclick(this,'?tracelog=favor_assistant_del');"
				onclick="myalibaba.IFavorites.removeOffer('884198000')"
				target="_self" href="javascript:void(0)" class="favorites-delete">删除</a></td>
		</tr>
	</tbody>
</table>
</form>
<div id="myalibaba-favorites-guess">
<div class="mod-guess-link">
<div class="cell-header class-header">
<h4 class="title">猜您喜欢</h4>
<div class="float-rt round-rt-up">圆角右上</div>
</div>
<div class="content">
<ul class="list-product class-img-100">
	<li>
	<dl class="cell-product-2nd">
		<dt><a
			onmousedown="FD.sys.fly.Utils.iClick({'page':15,'objectId':'885859590','recId':'1010','alg':'010210','objectType':'offer','pid':'819067_1008'})"
			target="_blank"
			href="http://detail.china.alibaba.com/buyer/offerdetail/885859590.html"
			title="广州LED电子显示屏，顾通最新报价" class="a-img"><img
			alt="广州LED电子显示屏，顾通最新报价"
			src="http://img.china.alibaba.com/img/ibank/2010/870/185/227581078_1130096677.summ.jpg"></a></dt>
		<dd class="desc"><a
			onmousedown="FD.sys.fly.Utils.iClick({'page':15,'objectId':'885859590','recId':'1010','alg':'010210','objectType':'offer','pid':'819067_1008'})"
			title="广州LED电子显示屏，顾通最新报价" target="_blank"
			href="http://detail.china.alibaba.com/buyer/offerdetail/885859590.html">广州LED电子显示屏，顾通最新报价</a></dd>
		<dd class="price"><span class="cny">¥</span><em class="value">4800.00</em></dd>
		<dd class="factory"><a
			onmousedown="FD.sys.fly.Utils.iClick({'page':15,'objectId':'885859590','recId':'1010','alg':'010210','objectType':'offer','pid':'819067_1008'})"
			title="深圳市顾通科技有限公司（LED显示屏事业部）" target="_blank"
			href="http://china.alibaba.com/company/detail/gtled2010.html">深圳市顾通科技有限公司（LED显示屏事业部）</a></dd>
		<dd class="talk"><a
			onmousedown="FD.sys.fly.Utils.flyClick('', 1);FD.sys.fly.Utils.iClick({'page':15,'objectId':'885859590','recId':'1010','alg':'010210','objectType':'offer','pid':'819067_1008'});"
			alitalk="{id:'gtled2010'}" href="#" class="iconAlitalk icon-off"
			title="旺旺留言">旺旺留言</a></dd>
	</dl>
	</li>
	<li>
	<dl class="cell-product-2nd">
		<dt><a
			onmousedown="FD.sys.fly.Utils.iClick({'page':15,'objectId':'851415023','recId':'1010','alg':'010210','objectType':'offer','pid':'819067_1008'})"
			target="_blank"
			href="http://detail.china.alibaba.com/buyer/offerdetail/851415023.html"
			title="室内全彩显示屏厂家，室内全彩显示屏价格" class="a-img"><img
			alt="室内全彩显示屏厂家，室内全彩显示屏价格"
			src="http://img.china.alibaba.com/img/ibank/2010/517/031/153130715_749047063.summ.jpg"></a></dt>
		<dd class="desc"><a
			onmousedown="FD.sys.fly.Utils.iClick({'page':15,'objectId':'851415023','recId':'1010','alg':'010210','objectType':'offer','pid':'819067_1008'})"
			title="室内全彩显示屏厂家，室内全彩显示屏价格" target="_blank"
			href="http://detail.china.alibaba.com/buyer/offerdetail/851415023.html">室内全彩显示屏厂家，室内全彩显示屏价格</a></dd>
		<dd class="price"><span class="cny">¥</span><em class="value">6600.00</em></dd>
		<dd class="factory"><a
			onmousedown="FD.sys.fly.Utils.iClick({'page':15,'objectId':'851415023','recId':'1010','alg':'010210','objectType':'offer','pid':'819067_1008'})"
			title="深圳华远翔科技有限公司" target="_blank"
			href="http://china.alibaba.com/company/detail/ncczhy521.html">深圳华远翔科技有限公司</a></dd>
		<dd class="talk"><a
			onmousedown="FD.sys.fly.Utils.flyClick('', 1);FD.sys.fly.Utils.iClick({'page':15,'objectId':'851415023','recId':'1010','alg':'010210','objectType':'offer','pid':'819067_1008'});"
			alitalk="{id:'ncczhy521'}" href="#" class="iconAlitalk icon-on"
			title="在线洽谈">在线洽谈</a></dd>
	</dl>
	</li>
	<li>
	<dl class="cell-product-2nd">
		<dt><a
			onmousedown="FD.sys.fly.Utils.iClick({'page':15,'objectId':'906008100','recId':'1010','alg':'010210','objectType':'offer','pid':'819067_1008'})"
			target="_blank"
			href="http://detail.china.alibaba.com/buyer/offerdetail/906008100.html"
			title="新亚胜湖南P5室内全彩显示屏|长沙电子显示屏公司" class="a-img"><img
			alt="新亚胜湖南P5室内全彩显示屏|长沙电子显示屏公司"
			src="http://img.china.alibaba.com/img/ibank/2011/030/155/253551030_99430213.summ.jpg"></a></dt>
		<dd class="desc"><a
			onmousedown="FD.sys.fly.Utils.iClick({'page':15,'objectId':'906008100','recId':'1010','alg':'010210','objectType':'offer','pid':'819067_1008'})"
			title="新亚胜湖南P5室内全彩显示屏|长沙电子显示屏公司" target="_blank"
			href="http://detail.china.alibaba.com/buyer/offerdetail/906008100.html">新亚胜湖南P5室内全彩显示屏|长沙电子显示屏公司</a></dd>
		<dd class="price"><span class="cny">¥</span><em class="value">1.00</em></dd>
		<dd class="factory"><a
			onmousedown="FD.sys.fly.Utils.iClick({'page':15,'objectId':'906008100','recId':'1010','alg':'010210','objectType':'offer','pid':'819067_1008'})"
			title="湖南新亚胜科技发展有限公司" target="_blank"
			href="http://hnled.cn.alibaba.com/">湖南新亚胜科技发展有限公司</a></dd>
		<dd class="talk"><a
			onmousedown="FD.sys.fly.Utils.flyClick('', 1);FD.sys.fly.Utils.iClick({'page':15,'objectId':'906008100','recId':'1010','alg':'010210','objectType':'offer','pid':'819067_1008'});"
			alitalk="{id:'hnled'}" href="#" class="iconAlitalk icon-on"
			title="在线洽谈">在线洽谈</a><span class="icon-trust"><span
			class="hide">诚信通</span></span></dd>
	</dl>
	</li>
	<li>
	<dl class="cell-product-2nd">
		<dt><a
			onmousedown="FD.sys.fly.Utils.iClick({'page':15,'objectId':'913325588','recId':'1010','alg':'010210','objectType':'offer','pid':'819067_1008'})"
			target="_blank"
			href="http://detail.china.alibaba.com/buyer/offerdetail/913325588.html"
			title="大量供应LED电子显示屏5V40A的200W开关电源" class="a-img"><img
			alt="大量供应LED电子显示屏5V40A的200W开关电源"
			src="http://img.china.alibaba.com/img/ibank/2011/721/386/256683127_620502632.summ.jpg"></a></dt>
		<dd class="desc"><a
			onmousedown="FD.sys.fly.Utils.iClick({'page':15,'objectId':'913325588','recId':'1010','alg':'010210','objectType':'offer','pid':'819067_1008'})"
			title="大量供应LED电子显示屏5V40A的200W开关电源" target="_blank"
			href="http://detail.china.alibaba.com/buyer/offerdetail/913325588.html">大量供应LED电子显示屏5V40A的200W开关电源</a></dd>
		<dd class="price"><span class="cny">¥</span><em class="value">55.00</em></dd>
		<dd class="factory"><a
			onmousedown="FD.sys.fly.Utils.iClick({'page':15,'objectId':'913325588','recId':'1010','alg':'010210','objectType':'offer','pid':'819067_1008'})"
			title="海鸿实业责任有限公司" target="_blank"
			href="http://dreamwu2009.cn.alibaba.com/">海鸿实业责任有限公司</a></dd>
		<dd class="talk"><a
			onmousedown="FD.sys.fly.Utils.flyClick('', 1);FD.sys.fly.Utils.iClick({'page':15,'objectId':'913325588','recId':'1010','alg':'010210','objectType':'offer','pid':'819067_1008'});"
			alitalk="{id:'dreamwu2009'}" href="#" class="iconAlitalk icon-on"
			title="在线洽谈">在线洽谈</a><span class="icon-trust"><span
			class="hide">诚信通</span></span></dd>
	</dl>
	</li>
	<li>
	<dl class="cell-product-2nd">
		<dt><a
			onmousedown="FD.sys.fly.Utils.iClick({'page':15,'objectId':'736167737','recId':'1010','alg':'010210','objectType':'offer','pid':'819067_1008'})"
			target="_blank"
			href="http://detail.china.alibaba.com/buyer/offerdetail/736167737.html"
			title="厂家直销PH10LED室内全彩显示屏/颜色可选" class="a-img"><img
			alt="厂家直销PH10LED室内全彩显示屏/颜色可选"
			src="http://img.china.alibaba.com/img/ibank/2010/768/565/122565867_925005503.summ.jpg"></a></dt>
		<dd class="desc"><a
			onmousedown="FD.sys.fly.Utils.iClick({'page':15,'objectId':'736167737','recId':'1010','alg':'010210','objectType':'offer','pid':'819067_1008'})"
			title="厂家直销PH10LED室内全彩显示屏/颜色可选" target="_blank"
			href="http://detail.china.alibaba.com/buyer/offerdetail/736167737.html">厂家直销PH10LED室内全彩显示屏/颜色可选</a></dd>
		<dd class="price"><span class="cny">¥</span><em class="value">10000.00</em></dd>
		<dd class="factory"><a
			onmousedown="FD.sys.fly.Utils.iClick({'page':15,'objectId':'736167737','recId':'1010','alg':'010210','objectType':'offer','pid':'819067_1008'})"
			title="广州创美电子有限公司" target="_blank"
			href="http://charmiglighting.cn.alibaba.com/">广州创美电子有限公司</a></dd>
		<dd class="talk"><a
			onmousedown="FD.sys.fly.Utils.flyClick('', 1);FD.sys.fly.Utils.iClick({'page':15,'objectId':'736167737','recId':'1010','alg':'010210','objectType':'offer','pid':'819067_1008'});"
			alitalk="{id:'charmiglighting'}" href="#" class="iconAlitalk icon-on"
			title="在线洽谈">在线洽谈</a><span class="icon-trust"><span
			class="hide">诚信通</span></span></dd>
	</dl>
	</li>
	<li>
	<dl class="cell-product-2nd">
		<dt><a
			onmousedown="FD.sys.fly.Utils.iClick({'page':15,'objectId':'852054469','recId':'1010','alg':'010210','objectType':'offer','pid':'819067_1008'})"
			target="_blank"
			href="http://detail.china.alibaba.com/buyer/offerdetail/852054469.html"
			title="P10户外全彩显示屏 项目工程案例" class="a-img"><img
			alt="P10户外全彩显示屏 项目工程案例"
			src="http://img.china.alibaba.com/img/ibank/2010/502/867/207768205_22352312.summ.jpg"></a></dt>
		<dd class="desc"><a
			onmousedown="FD.sys.fly.Utils.iClick({'page':15,'objectId':'852054469','recId':'1010','alg':'010210','objectType':'offer','pid':'819067_1008'})"
			title="P10户外全彩显示屏 项目工程案例" target="_blank"
			href="http://detail.china.alibaba.com/buyer/offerdetail/852054469.html">P10户外全彩显示屏
		项目工程案例</a></dd>
		<dd class="price"><span class="cny">¥</span><em class="value">180.00</em></dd>
		<dd class="factory"><a
			onmousedown="FD.sys.fly.Utils.iClick({'page':15,'objectId':'852054469','recId':'1010','alg':'010210','objectType':'offer','pid':'819067_1008'})"
			title="深圳市贵创光电科技有限公司成都办" target="_blank"
			href="http://china.alibaba.com/company/detail/gcled810.html">深圳市贵创光电科技有限公司成都办</a></dd>
		<dd class="talk"><a
			onmousedown="FD.sys.fly.Utils.flyClick('', 1);FD.sys.fly.Utils.iClick({'page':15,'objectId':'852054469','recId':'1010','alg':'010210','objectType':'offer','pid':'819067_1008'});"
			alitalk="{id:'gcled810'}" href="#" class="iconAlitalk icon-off"
			title="旺旺留言">旺旺留言</a></dd>
	</dl>
	</li>
</ul>
</div>
</div>
</div>
</div>
</body>
</html>