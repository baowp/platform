(function($){
	// 改变默认配置
	var d = $.dialog.defaults;
	// 预缓存皮肤，数组第一个为默认皮肤
	d.skin = ['default', 'chrome', 'facebook', 'aero'];
	// 是否开启特效
	d.effect = true;
	// 指定超过此面积的对话框拖动的时候用替身
	//d.showTemp = 100000;
	
})(art);
$(function(){
	$("#wk-notice").click(function(){
		art.dialog.open('include/messageTips.jsp',{
			id : 'testIframe',
			skin: 'aero',
			title: '消息提醒',
			left:250,
			top:40,
			fixed:true,
			width:500,
			height:300
		});
	})
	$("#mapbar").click(function(){
		art.dialog.open('include/mapbar/map.jsp',{
			id : 'mapIframe',
			skin: 'aero',
			title: '地图定位(按esc可关闭)',
			left:150,
			top:40,
			fixed:true,
			width:630,
			height:470
		});
	})
})
function openMenu(){

	art.dialog.open('/user/menu/menuedit',{
		id : 'indexMenuIframe',
		skin: 'aero',
		title: '首页菜单订制',
		left:250,
		top:40,
		fixed:true,
		width:600,
		height:400
	});
}