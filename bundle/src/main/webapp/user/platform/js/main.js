var chart;
$(function() {
	$.ajax({
		type : "post",
		url : "/chart/getUserState",
		data : "",
		success : function(data) {
			var strjson = eval('(' + data + ')');

			chart = new Highcharts.Chart({
				chart : {
					renderTo : 'container',
					defaultSeriesType : 'column'
				},
				title : {
					text : '平台统计'
				},
				xAxis : {
					categories : [ ' ' ]
				},
				yAxis : {
					title : {
						text : '次数'
					},
					plotLines : [ {
						value : 0,
						width : 1,
						color : '#808080'
					} ]
				},
				tooltip : {
					formatter : function() {
						return '' + this.series.name + ': ' + this.y + '次';
					}
				},
				credits : {
					enabled : false
				},
				series : strjson
			});
		}
	})
	$("#almanac").click(function() {
		art.dialog.open('/common/almanac/index.html', {
			id : 'almanac',
			skin : 'aero',
			title : '我的黄历(按esc可关闭)',
			left : 150,
			fixed : true,
			top : 0,
			width : 675,
			height : 495
		});
	})
	$("#calc").click(function() {
		art.dialog.open('/common/calc/index.html', {
			id : 'calc',
			skin : 'aero',
			title : '我的计算器(按esc可关闭)',
			left : 250,
			top : 20,
			fixed : true,
			width : 430,
			height : 350
		});
	})
	$("#weather").click(function() {
		art.dialog.open('/common/weather/index.html', {
			id : 'weather',
			skin : 'aero',
			title : '天气预报(按esc可关闭)',
			left : 200,
			fixed : true,
			top : 20,
			width : 550,
			height : 370
		});
	})
	$("#photoshop").click(function() {
		art.dialog.open('http://www.tuyitu.com/photoshop/photoshop.htm', {
			id : 'photoshop',
			skin : 'aero',
			title : '在线photoshop处理(按esc可关闭)',
			left : 100,
			top : 0,
			fixed : true,
			width : 800,
			height : 600
		});
	})
	$("#mapbar").click(function() {
		art.dialog.open('include/mapbar/map.jsp', {
			id : 'mapIframe',
			skin : 'aero',
			title : '地图定位(按esc可关闭)',
			left : 150,
			top : 40,
			fixed : true,
			width : 630,
			height : 470
		});
	})
})
