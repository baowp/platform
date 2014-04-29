
function OnLoad() {};
OnLoad.prototype = {
	common : function() {
		$(".editor").each(function(i, node) {
			node.onclick = function(e) {
				//(e || event).cancelBubble = true;
				$("#mlColorPicker").hide();
			}
		});
		$(".outBg").click(function(e) {
			if($(e.target).parents(".editor").length == 0 && e.button == 0) {
				tool.collapse();
			}
		})
		$(".titleName").click(function() {
			tool.collapse();
			$("#mlColorPicker").hide()//.removeData("mlOnChange");
		})
		tool.useimg = $("#nowTopicImgObj img");
		if ($.browser.msie) {
			tool.style = document.styleSheets['theme2'];
		} else {
			tool.style = $("#theme2");
		}		
	},
	refresh : function(){
		function onTime(){
			tool.setTheme();
			function val(node) {
				var meta = $(node).metadata();
				if (!meta.property)return;
				return mytheme.get(meta.selector)[meta.property] || tool.theme
						&& tool.theme[meta.selector]
						&& tool.theme[meta.selector][meta.property];
			}
			$("select.maintain").each(function(i, node) {
				var value = val(node);
				if (value) {
					node.value = value;
				}
			});
			$(":checkbox.maintain").each(function(i, node) {
				var value = val(node);
				if (value == node.value) {
					node.checked = true;
				}else node.checked=false;
			});
			$(".colorShow").each(function() {
				var value = val(this);
				if (value && !value.match(/^url/i)) {
					$(this).css("background", value);
				}
			});
			$(":radio.maintain").each(function(i, node) {
				if (val(node) == node.value)
					node.checked = true;
			});
			$("#transparentCheckbox").each(function(i,node){
				if (val(node) < 1)
					node.checked = true;
			})
		}
		setTimeout(onTime,500);
	},
	topic : function() {
		$("#topicImgList img").click(tool.fn.setupTopic);	
		$("#topDesInput,#bottomDesInput").each(function(i, node) {
			var k = node.id.replace(/Input/, 'c');
			node.onkeyup = function() {
				$('#' + k).html(this.value)
			};
			node.onchange = function() {
				this.onkeyup();
				jsonSign[k] = this.value
			}
		});
		
		$("select.maintain").each(function(){
			var node=this;
			node.onchange=function(){
				mytheme.setup(node)
			}});
		$(":checkbox.maintain").change(function(){
			if (this.checked)
				mytheme.setup(this);
			else {
				var meta = $(this).metadata();
				mytheme.setup(this,$.parseJSON('{"'+meta.property+'":"'+meta.normal+'"}'));
			}
		});
		
		$(".colorShow:not(#flashBackColor)").each(function() {
			var $node = $(this);
			$node.mlColorPicker( {
				'onChange' : function(val) {
					$node.css("background-color", "#" + val);
					$node.attr("value", "#" + val);
					mytheme.setup($node);
				}
			})
		});
		
		$("#a_uploadTopicImgFile").click(function() {// hunk_flash piece is show ?
			if($("#describe_flash").length > 0) {
				return true;
			} else{
				info("请先将主题动画板块显示出来!");
				return false;
			}
		});
	},
	nav : function(){
		$("#navBgList img").click(tool.fn.setupNavBg);
	},	
	module : function(){
		$("#titleBgList img").click(tool.fn.setupTitleBg);
		$(":radio.maintain").click(function(){mytheme.setup(this)});
	},
	whole : function() {
		$("#bgSetContIn img").click(tool.fn.setupInBg);
		$("#bgSetContOut img").click(tool.fn.setupOutBg);
		$("#transparentCheckbox").click(function(){
			var param = {};
			if (this.checked) {
				param.filter = "Alpha(opacity = 85)";
				param.opacity = "0.85";
			} else {
				param.filter = "Alpha(opacity = 100)";
				param.opacity = "1";
			}
			mytheme.setup(this, param)
		});
		$("#bgSetTitle1 .pointer").click(function() {
			$("#bgSetTitle").attr("class", "bgSetTitleIn");
			$("#bgSetTitle1").attr("class", "check3");
			$("#bgSetTitle2").attr("class", "uncheck3");
			$("#bgSetContIn").show();
			$("#bgSetContOut").hide();
		});
		$("#bgSetTitle2 .pointer").click(function() {
			$("#bgSetTitle").attr("class", "bgSetTitleOut");
			$("#bgSetTitle1").attr("class", "uncheck3");
			$("#bgSetTitle2").attr("class", "check3");
			$("#bgSetContIn").hide();
			$("#bgSetContOut").show();
		})
	},
	layout : function() {
	},
	flash : function() {
		var param = jsonSign.flash || (jsonSign.flash = {
			display : false,
			width : 700,
			height : 450,
			backgroundColor : "white"
		});
		if(param.src){
			$("#flashdoc").show();
			tool.flashLink();
		}else{
			$("#flashupload").show();
		}
		$("#isshowflash0").attr("checked", param.display).change(function() {
			param.display = this.checked;
		});
		(function() {
			$.each(arguments, function(i, key) {
				var k = key.toLowerCase();
				$("#flash" + key).val(param[k]).change(function() {
					var node = this;
					if (isNaN(node.value))
						node.value = param[k];
					param[k] = node.value;
				}).keyup(function() {
					var node = this;
					node.value = node.value.replace(/\D/g, '');
				})
			})
		})("Width", "Height");
		$("#flashBackColor").css("backgroundColor", param.backgroundColor)
				.mlColorPicker( {
					'onChange' : function(val) {
						$("#flashBackColor").css("backgroundColor", "#" + val);
						param.backgroundColor = "#" + val;
					}
				})
	},
	onunload : function() {
	//	onunload = mytheme.save;
	}
}

$(function(){
	onLoad = new OnLoad();
	for ( var key in onLoad)
		onLoad[key]();
});
