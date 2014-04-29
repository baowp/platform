$(function() {
	var url = location.search;
	var img = $.query.get('img').split(",");
	var proName = $.query.get('proName').split(",");
	var proHref = $.query.get('proHref').split(",");
	var proType = $.query.get('proType').split(",");
	var price = $.query.get('price').split(",");
	var entName = $.query.get('entName').split(",");
	var businessType = $.query.get('businessType').split(",");
	var staffSum = $.query.get('staffSum').split(",");
	var registeredCapital = $.query.get('registeredCapital').split(",");
	var regTime = $.query.get('regTime').split(",");
	var table1 = $('#table');
	var table_head = $("<tr></tr>");
	var  table_img= $("<tr></tr>");
	var table_proName = $("<tr></tr>");
	var table_entName = $("<tr></tr>");
	var table_price = $("<tr></tr>");
	var table_proType = $("<tr></tr>");
	var table_businessType = $("<tr></tr>");
	var table_staffSum = $("<tr></tr>");
	var table_registeredCapital = $("<tr></tr>");
	var table_regTime = $("<tr></tr>");
	var table_footer = $("<tr></tr>");
	$.each(img, function(i) {
		var td = $("<th id=\"td"+i+"\"></th>");
		if(i==0){
			table_head.append("<th></th>");
			td.append($("<a href=\"javascript:removeL('td"+i+"')\">移除</a>"));
			table_head.append(td);
		}else{
			td.append($("<a href=\"javascript:removeL('td"+i+"')\">移除</a>"));
			table_head.append(td);
		}
		table_head.append(td);
	});
	$.each(img, function(i) {
		var td = $("<td align=\"center\" id=\"td"+i+"\"></td>");
		td.append($("<img src=\""+img[i]+"\"/>"));
		if(i==0){
			table_img.append("<td align=\"center\">图片</td>");
			table_img.append(td);
		}
		else
			table_img.append(td);
	});
	$.each(proName, function(i) {
		var td = $("<td align=\"center\" id=\"td"+i+"\"></td>");
		td.append($("<span><a href=\"/jump.html?url="+proHref[i]+"\"  target=\"_blank\">"+proName[i]+"</a></span>"));
		if(i==0){
			table_proName.append("<td align=\"center\">产品信息</td>");
			table_proName.append(td);
		}
		else
			table_proName.append(td);
	});
	$.each(proType, function(i) {
		var td = $("<td align=\"center\" id=\"td"+i+"\"></td>");
		td.append($("<span>"+proType[i]+"</span>"));
		if(i==0){
			table_proType.append("<td align=\"center\">产品型号</td>");
			table_proType.append(td);
		}
		else
			table_proType.append(td);
	});

	$.each(price, function(i) {
		var td = $("<td align=\"center\" id=\"td"+i+"\"></td>");
		td.append($("<span>"+price[i]+"</span>"));
		if(i==0){
			table_price.append("<td align=\"center\">产品价格</td>");
			table_price.append(td);
		}
		else
			table_price.append(td);
	});
	$.each(entName, function(i) {
		var td = $("<td align=\"center\" id=\"td"+i+"\"></td>");
		td.append($("<span>"+entName[i]+"</span>"));
		if(i==0){
			table_entName.append("<td align=\"center\">公司名称</td>");
			table_entName.append(td);
		}
		else
			table_entName.append(td);
	});
	$.each(businessType, function(i) {
		var td = $("<td align=\"center\" id=\"td"+i+"\"></td>");
		td.append($("<span>"+businessType[i]+"</span>"));
		if(i==0){
			table_businessType.append("<td align=\"center\">主营行业</td>");
			table_businessType.append(td);
		}
		else
			table_businessType.append(td);
	});
	$.each(staffSum, function(i) {
		var td = $("<td align=\"center\" id=\"td"+i+"\"></td>");
		td.append($("<span>"+staffSum[i]+"</span>"));
		if(i==0){
			table_staffSum.append("<td align=\"center\">员工人数</td>");
			table_staffSum.append(td);
		}
		else
			table_staffSum.append(td);
	});
	$.each(registeredCapital, function(i) {
		var td = $("<td align=\"center\" id=\"td"+i+"\"></td>");
		td.append($("<span>"+registeredCapital[i]+"</span>"));
		if(i==0){
			table_registeredCapital.append("<td align=\"center\">注册资金</td>");
			table_registeredCapital.append(td);
		}
		else
			table_registeredCapital.append(td);
	});
	$.each(regTime, function(i) {
		var td = $("<td align=\"center\" id=\"td"+i+"\"></td>");
		td.append($("<span>"+regTime[i]+"</span>"));
		if(i==0){
			table_regTime.append("<td align=\"center\">成立时间</td>");
			table_regTime.append(td);
		}
		else
			table_regTime.append(td);
	});
	$.each(img, function(i) {
		var td = $("<th id=\"td"+i+"\"></th>");
		if(i==0){
			table_footer.append("<th></th>");
			td.append($("<a href=\"javascript:removeL('td"+i+"')\">移除</a>"));
			table_footer.append(td);
		}else{
			td.append($("<a href=\"javascript:removeL('td"+i+"')\">移除</a>"));
			table_footer.append(td);
		}
		table_footer.append(td);
	});
	table1.append(table_head);
	table1.append(table_img);
	table1.append(table_proName);
	table1.append(table_entName);
	table1.append(table_price);
	
	table1.append(table_proType);
	table1.append(table_businessType);
	table1.append(table_staffSum);
	table1.append(table_registeredCapital);
	table1.append(table_regTime);
	table1.append(table_footer);
	$("#displayNum").html(img.length)
	
})
function removeL(obj){
	$("table").find("#"+obj).each(function(){
		$(this).remove()
	})
	$("#displayNum").html(parseInt($("#displayNum").html().trim())-1)
}