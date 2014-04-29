/*
演示脚本
 */

(function() {

	// 判断IE6

	// document.getElementById简化函数
	var $ = function(id) {
		return 'string' == typeof id ? document.getElementById(id) : id;
	};

	// 页面就绪，允许你绑定一个在DOM文档载入完成后执行的函数
	var domReady = !+'\v1' ? function(f) {
		(function() {
			try {
				document.documentElement.doScroll('left');
			} catch (error) {
				setTimeout(arguments.callee, 0);
				return;
			}
			;
			f();
		})();
	} : function(f) {
		document.addEventListener('DOMContentLoaded', f, false);
	};

	// 在页面就绪后绑定事件,你也可以使用window.onload
	domReady(function() {

		$('read').onclick = function() {
			art.dialog({
				id : 'testIframe',
				width : 700,
				height : 400,
				lock : true,
				iframe : 'http://51archetype.com/user/protocol.jsp',
				title : '东方五金服务条款',
				yesText : '阅读完毕，关闭'
			}, function() {
				return;
			});
			return false;
		};
		

		// --------------------------ardDialog演示脚本结束------------------------------//

	});

})();
