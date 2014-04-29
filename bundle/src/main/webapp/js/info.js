$(function(){
	$.fn.jpagebar({ //content holder(Object || css Selector)
                renderTo: $("#pagebarDiv1"),
                //总页码
				totalpage: ${totalpage},
				//当前页码
				currentPage: ${thisPage},
				//分页条样式
				pagebarCssName: 'pagebar',
				//页码样式
				pageNumberCssName:'page_number',
				//首页、上一页、下一页、尾页样式
				pageNameCssName:'pageName',
				//选中首页、上一页或下一页、尾页样式
				currentPageNameCssName:'current_pageName',
				//当前选中页码样式
				currentPageNumberCssName:'current_page_number',
				//显示总页码样式
				totalpageNumberCssName:'totalpage_number',
				//点击页码action
				onClickPage : function(pageIndex){
								$.fn.setCurrentPage(this,pageIndex);
								//这里进行ajax异步数据处理
								//通过提交表单，或着使用location.href
								$("#thisPage").val(pageIndex);
								$("#form1").submit();
							}
	});

})