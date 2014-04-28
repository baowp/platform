	var chart;
	$(document).ready(
			function() {
				$.ajax({
					type : "post",
					url : "/chart/getUserRegisterJsonDate",
					data : "",
					success : function(data) {
						var strjson = eval('('+data+')');
						chart = new Highcharts.Chart({
							chart : {
								renderTo : 'container',
								defaultSeriesType : 'line',
								marginRight : 130,
								marginBottom : 25
							},
							title : {
								text : '会员注册量统计图',
								x : -20
							//center
							},
							subtitle : {
								text : '东方五金',
								x : -20
							},
							xAxis : {
								categories : [ '1月份', '2月份', '3月份', '4月份',
										'5月份', '6月份', '7月份', '8月份', '9月份',
										'10月份', '11月份', '12月份' ]
							},
							yAxis : {
								title : {
									text : '个数'
								},
								plotLines : [ {
									value : 0,
									width : 1,
									color : '#808080'
								} ]
							},
							tooltip : {
								formatter : function() {
									return '<b>' + this.series.name
											+ '</b><br/>' + this.x + ': '
											+ this.y + '个';
								}
							},
							legend : {
								layout : 'vertical',
								align : 'right',
								verticalAlign : 'top',
								x : -10,
								y : 100,
								borderWidth : 0
							},
							series : strjson
						});

					}
				})
			});