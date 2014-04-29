
function Gaim() {
	jsonContent.gaim = jsonContent.gaim || {};
}
Gaim.prototype = {
	openable : true,
	qqFieldSubmit : function(){
		var qq=$("#gaimqqField").val().trim();
		if(!qq){
			alert("请输入QQ");
			return;
		}
		jsonContent.gaim.qq='\
			<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=qqnumber&site=qq&menu=yes">\
			<img border="0" src="http://wpa.qq.com/pa?p=2:qqnumber:41" alt="qqnumber" title="有事请Q我">\
			</a>'.replace(/qqnumber/g,qq);
		$("#gaimqqLink").html(jsonContent.gaim.qq.replace(/<(\/)?a[^>]*>/g,''));
		$("#gaim_qq").dialog("close");
		gaim.qqPosition();
	},
	qqAreaSubmit : function(){
		var str=$("#gaimqqArea").val().trim();
		if(!str){
			alert("请粘贴临时会话代码");
			return;
		}else if(!str.match(/^<a[^>]+>\s*<img[^>]+>\s*<\/a>$/)){
			alert("您复制的代码有误");
			return;
		}
		jsonContent.gaim.qq=str;
		$("#gaimqqLink").html(jsonContent.gaim.qq.replace(/<(\/)?a[^>]*>/g,''));
		$("#gaim_qq").dialog("close");
		gaim.qqPosition();
	},
	qqPosition:function(){
		var $qq=$("#gaim-qq");
		if(Id("qqPositionfixed").checked){
			if($qq.css("position")=="static"){
				$qq.appendTo("#content");
				var param={"#gaim-qq":{position:"fixed",top:"240px",left:"150px",
						_position:"absolute",
						_top:"expression(msie6qqTop())",
						_left:"expression(msie6qqLeft())"}};
				mytheme.setStyle(param);
				gaim.qqDrag();
			}
		}else{
			if($qq.css("position")!="static"){
				$qq.insertAfter("#contactName");
				var param={"#gaim-qq":{position:"static",_position:"static"}};
				mytheme.setStyle(param);
			}
		}
	},
	qqDrag : function(){
		var $qq=$("#gaim-qq");
		if ($qq.css("position") != "static") 
			$qq.draggable( {
				start:function(){
					gaim.openable=false;
				},
				stop:function(){
					setTimeout(function(){
						gaim.openable=true;
					},10);
					var top=$qq[0].offsetTop;
					var left=$qq[0].offsetLeft;
					if(msie6){
						top-=document.documentElement.scrollTop;
						left-=document.documentElement.scrollLeft;
						variable.gaimQQ.top=top+"px";
						variable.gaimQQ.left=left+"px";
					}
					//top=top>560?560:top;
					var values={top:top+"px",left:left+"px"};
					var param={"#gaim-qq":values};
					mytheme.setStyle(param);
					$qq.attr("style","_top:expression(msie6qqTop());_left:expression(msie6qqLeft())");
				}
			});
	},
	removeqq : function(){
		delete jsonContent.gaim.qq;
		$("#gaimqqLink").html("");
	},
	openMsn : function() {
		var foo = document.getElementById("gaim_msn");
		if (foo) {
			foo = $(foo);
			foo.dialog("open");
		}
		else {
			foo=$("<div id='gaim_msn' class='none'>");
			foo.load(contextRoot + "/user/vipsite/trace/gaim.msn.jsp", function() {
				foo.appendTo("#contact_side");
				if(jsonContent.gaim.msn)
					$("#gaimMsnArea").val(jsonContent.gaim.msn);
		
				foo.dialog( {
					title : "设置MSN临时会话",
					width : 620,
					modal : true
				});
			});
		}
	},
	msnAreaSubmit : function(){
		var str=$("#gaimMsnArea").val().trim();
		if(!str){
			alert("请粘贴临时会话代码");
			return;
		}else if(!str.match(/^<a[^>]+>\s*<img[^>]+>\s*<\/a>$/)){
			alert("请复制MSN“状态图标”控件的代码");
			return;
		}
		jsonContent.gaim.msn=str;
		$("#gaimMsnLink").html(jsonContent.gaim.msn.replace(/<(\/)?a[^>]*>/g,''));
		$("#gaim_msn").dialog("close");
	},
	removeMsn : function(){
		delete jsonContent.gaim.msn;
		$("#gaimMsnLink").html("");
	}
}

var gaim=new Gaim();
$(function(){
	gaim.qqDrag();
	if($("#gaim-qq").css("position")!="static"){
		Id("qqPositionfixed")&&(Id("qqPositionfixed").checked=true);
	}else{
		$("#qqPositionstatic").attr("checked",1);
	}
});