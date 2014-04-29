/**
 * 
 */
$(function() {
	$("#tabs").tabs({
		cookie : {}
	});

	$("form").submit(function() {
		$(this).ajaxSubmit({
			success : function() {
				info("保存成功");
			}
		})
		return false;
	})

	$("[name=keywords]").blur(keywords);
	function keywords() {
		$(this).val(function(i, v) {
			return v.replace(/，/g, ',');
		})
	}
	$("#pltj").click(function(){
		art.dialog.open('/user/vipsite/seo/pltj.jsp', {
			id : 'almanac',
			skin : 'aero',
			title : '搜索引擎批量提交(按esc可关闭)',
			left : 150,
			fixed : true,
			top : 0,
			width : 675,
			height : 495
		});
	})
});
$(function(){
	tipss($("input[id^='title-']"),'首页网站标题写法，首页的网站标题写法比较简单，一般的格式是“网站关键词-网站名称”，这里的关键词不要加太多，和关键词中加的一样最佳。 多个关键词中间用“|”隔开，例如：“实木复合门|强化木门|钢木门-上门网”',700);
	tipss($("input[id^='title-'][id!='title-1']"),'栏目页网站标题写法，一般在栏目页网站标题的写法有两种，如果你的栏目页按照我之前建议的用关键词名称命名的话，边可以是“栏目名称-网站名称”（"商业机会_东方五金B2B电子商务平台"），但如果你不是按照我给的建议用关键词命名，网站标题就要换种写法了，大概写法是这样的“栏目名称|栏目关键词-网站名称”（"公司新闻-强化木门|实木复合门-永康市心喜门业有限公司"），这样可以帮助你的栏目获得排名。',700);
	tipss($("textarea[id^='keywords-']"),'每个关键词之间用英文逗号“,”隔开，数量控制在5~8的范围内。例如："五金,五金报价,五金工具,日用五金,刀具,锁具,轴承,门窗五金"',700);
	tipss($("textarea[id^='keywords-'][id!='keywords-1']"),'栏目关键词写法，栏目的关键词可以将其栏目下所有分类列表的名称列出，加上栏目关键字，一般写法是“栏目名称，栏目关键字，栏目分类列表名称”这样可以帮助搜索引擎更好的分辨这个页面，从而让你在同类网站的权重中取得一些优势。 每个关键词之间用英文逗号“,”隔开',700);
	tipss($("textarea[id^='description-']"),'首页网站描述写法，网站描述的写法就是将首页的网站标题、关键词和一些特殊栏目的内容融合到里面，写成简单的介绍形式，不要只写关键词，因为这个是搜索引擎收录首页后显示出来的简介。 例如："东方五金B2B电子商务平台是专业提供五金市场前沿资讯，交易信息，五金企业动态，五金知识，五金展会，轴承、刀具等五金工具，建筑五金，日用五金，五金原材料资讯，产品专题等商务信息服务的网站，是中国最专业的五金行业门户网站"',700);
	tipss($("textarea[id^='description-'][id!='description-1']"),'栏目网站描述写法，将栏目的网站标题、关键字、分类列表名称，尽量的写入网站描述中，但切忌只写关键词，建议仍是尽量写成介绍形式，对你用户在搜索引擎中的浏览会有好处，由此也可以提高你的一些权重优势。',700);
})
function tipss(obj,content,width){
	obj.each(function(){
		 $(this).qtip(
			      {
			         content: content,
			         position: {
			            corner: {
			               tooltip: 'topMiddle',
			               target: 'bottomMiddle'
			            }
			         },
			         style: {
			            tip: true, // Give it a speech bubble tip with automatic corner detection
			            name: 'cream',
			            width:width 
			         }
			      });

	})
}

function seoHelp(){
	art.dialog.open('/user/vipsite/seo/help.html', {
		id : 'helpIframe',
		skin : 'aero',
		title : 'seo优化帮助(按esc可关闭)',
		left : 150,
		top : 20,
		fixed : true,
		lock : true,
		width : 700,
		height : 500
	});
}