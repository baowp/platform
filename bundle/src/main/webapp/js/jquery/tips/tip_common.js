/**
 * obj为要提示的对象，比如$("#div"),txt为提示字符，width为提示div的宽度
 */
tips = function(obj,txt,width){
	obj.bt(
			txt,
			{
				fill : '#FFF',
				cornerRadius : 10,
				strokeWidth : 0,
				shadow : true,
				width: width,
				shadowOffsetX : 3,
				shadowOffsetY : 3,
				shadowBlur : 8,
				shadowColor : 'rgba(0,0,0,.9)',
				shadowOverlap : false,
				noShadowOpts : {
					strokeStyle : '#999',
					strokeWidth : 2
				},
				positions : [ 'left', 'bottom' ]
			});
}