/*
演示脚本
*/


(function(){
		  
	//判断IE6
	
	//document.getElementById简化函数
	var $ = function (id){
		return 'string' == typeof id ? document.getElementById(id) : id;
	};
	
	//页面就绪，允许你绑定一个在DOM文档载入完成后执行的函数
	var domReady = !+'\v1' ? function(f){(function(){
			try{
				document.documentElement.doScroll('left');
			} catch (error){
				setTimeout(arguments.callee, 0);
				return;
			};
			f();
		})();
	} : function(f){
		document.addEventListener('DOMContentLoaded', f, false);
	};
	
	//在页面就绪后绑定事件,你也可以使用window.onload
	domReady(function(){	

		$('btn2').onclick = function(){
			art.dialog({id:'testIframe',lock:true, iframe:'http://cn.easthardware.net',title:'阿斯顿',yesText:'阅读完毕，关闭' }, function(){
				alert(1)
				return;
			});
			return false;
		};

		
		
		//--------------------------ardDialog演示脚本结束------------------------------//
		
		


	});

})();



