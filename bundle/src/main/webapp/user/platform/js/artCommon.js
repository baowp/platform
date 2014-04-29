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