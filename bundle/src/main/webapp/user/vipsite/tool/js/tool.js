/**
 */
function Tool() {
}
Tool.prototype = {
	collapse : function(selector) {
		$(selector || ".editor:visible").hide();
	},
	expand : function(selector) {
		tool.collapse();
		$(selector).show();
	},
	change : {
		sideLeft : function() {
			$("#content1").after($("#content2"));
			$("#content1").removeClass("sideRight").addClass("sideLeft");
			$("#content2").removeClass("mainLeft").addClass("mainRight");
			onLoad.layout();
		},
		sideRight : function() {
			$("#content2").after($("#content1"));
			$("#content1").removeClass("sideLeft").addClass("sideRight");
			$("#content2").removeClass("mainRight").addClass("mainLeft");
			onLoad.layout();
		}
	},
	changeTo : function(selector) {
		$("#editTopic").children(".check").attr("class", "uncheck");
		$(selector).attr("class", "check");
	},
	changeTo2 : function(selector) {
		$(".setContent4").children(".check").attr("class", "uncheck");
		$(selector).attr("class", "check");
	},
	changeTo3 : function(selector) {
		$(".setContent5").children(".check").attr("class", "uncheck");
		$(selector).attr("class", "check");
	},
	transfer : function(img, theme, callback) {
		var $img = $(img).clone();
		var pos = $(img).position();
		$img.css({
			position : 'absolute',
			opacity : "0.6"
		}).css(pos).appendTo(img.parentNode);
		var d = 40;
		$img.animate({
			left : pos.left - d / 2 - 2,
			top : pos.top - d / 2 - 2,
			width : img.offsetWidth + d,
			height : img.offsetHeight + d
		}, 300, function() {
			var pos2 = tool.useimg.position();
			$img.animate({
				left : pos2.left + 1,
				top : pos2.top + 1,
				width : img.offsetWidth,
				height : img.offsetHeight
			}, function() {
				tool.useimg.attr("src", $img.attr("src"));
				$img.remove();
				$.extend(pageTheme, theme);
				tool.clearStyle();
				tool.fn.themeHideFlash();
				onLoad.refresh();
				mytheme.style = {};
				$("#theme").attr(
						"href",
						function(i, v) {
							return v.replace(/theme\/.+\/theme/, "theme/"
									+ theme.folder + "/theme");
						});
				if (typeof callback == "function")
					callback();
			})
		})
	},
	setTheme : function() {
		var sheet, rules = {};
		(function() {
			$.each(arguments, function(i, id) {
				$("link,style").each(function(i) {
					if (this.id == id) {
						sheet = document.styleSheets[i];
						return false;
					}
				});
				$.each(sheet.rules || sheet.cssRules, function(i, rule) {
					if (rule.type && rule.type != 1)
						return;
					var r = rules[rule.selectorText]
							|| (rules[rule.selectorText] = {});
					for ( var key in rule.style) {
						var k = key.replace(/([A-Z])/g, "-$1").toLowerCase();
						if (rule.style[key])
							r[k] = rule.style[key]
					}
				});
			})
		})("baseTheme", "theme");
		tool.theme = rules;
	},
	setStyle : function(style) {
		if ($.browser.msie) {
			for ( var key in style)
				for ( var k in style[key])
					tool.style.addRule(key, k + ":"
							+ tool.addContext(style[key][k]))
		} else {
			tool.style.html(tool.addContext(this.toJSON(style)));
		}
	},
	clearStyle : function() {
		if ($.browser.msie) {
			while (tool.style.rules.length)
				tool.style.removeRule();
		} else {
			tool.style.html("");
		}
		mytheme.style = {};
	},
	addContext : function(str) {
		if (!contextRoot)
			return str;
		return str.replace(/url\((?!http)/gi, "url(" + contextRoot)
	},
	regular : new RegExp("url\\(" + contextRoot, "gi"),
	regular2 : new RegExp("^" + contextRoot),
	clearContext : function(param) {
		if (!contextRoot)
			return param;
		if (typeof param == "string")
			return param.replace(tool.regular, "url(");
		if (typeof param == "object") {
			for ( var key in param)
				param[key] = param[key].replace(tool.regular, "url(");
			return param;
		}
	},
	clear2 : function(param) {
		if (!contextRoot)
			return param;
		if (typeof param == "object") {
			for ( var key in param)
				if (typeof param[key] == "object")
					for ( var k in param[key])
						if (typeof param[key][k] == "string")
							param[key][k] = param[key][k].replace(
									tool.regular2, '');
			return param;
		}
	},
	normalPic : function(param) {
		if (typeof param == "string")
			return param.replace(/\.min\.(gif|jpg)/gi, ".$1");
		if (typeof param == "object") {
			for ( var key in param)
				param[key] = param[key].replace(/\.min\.(gif|jpg)/gi, ".$1");
			return param;
		}
	},
	toJSON : function(style) {
		var array = [];
		for ( var key in style) {
			var arr = [];
			for ( var k in style[key]) {
				arr.push(k + ":" + style[key][k])
			}
			array.push(key + "{" + arr.join(";") + "}");
		}
		return array.join("");
	},
	/** 保证layout.layoutId有值 */
	safe : function(callback) {
		if (layout.layoutId)
			callback();
		else
			tool.simpleSave(callback);
	},
	simpleSave : function(callback) {
		$.ajax({
			url : contextRoot + "/layout/simpleSave",
			success : function(data) {
				layout.layoutId = data;
				if (typeof callback == "function")
					callback();
			}
		})
	},
	flashLink : function() {
		document.getElementById('flashLink').onclick = function() {
			var url = "../flash?preview=1";
			for ( var k in jsonSign.flash)
				url += "&" + k + "=" + encodeURIComponent(jsonSign.flash[k]);
			open(url);
		}
	},
	deleteLog : function() {
		if (confirm("确定要删除LOGO？")) {
			$("#log").empty();
			$("#logEdit").empty();
			jsonSign.log = undefined;
		}
	},
	fn : {
		privateSetup : function($node, selector) {
			$(selector + " .check2").removeClass("check2").addClass("uncheck2");
			$node.removeClass("uncheck2").addClass("check2");
			if ($node.attr("tagName") == "IMG") {
				$node.metadata().value = "url(" + $node.attr("src") + ")";
				if (selector == "#topicImgList") {
					tool.fn.hideFlash();
				}
			}
			mytheme.setup($node[0]);
		},
		setupTopic : function() {
			tool.fn.privateSetup($(this), "#topicImgList")
		},
		setupNavBg : function() {
			tool.fn.privateSetup($(this), "#navBgList")
		},
		setupTitleBg : function() {
			tool.fn.privateSetup($(this), "#titleBgList")
		},
		setupInBg : function() {
			tool.fn.privateSetup($(this), "#bgSetContIn")
		},
		setupOutBg : function() {
			tool.fn.privateSetup($(this), "#bgSetContOut")
		},
		hideFlash : function() {
			var param = {
				".headTopic" : {
					height : "200px"
				},
				".description" : {
					display : "block"
				},
				".describe_flash" : {
					display : "none"
				}
			};
			mytheme.setStyle(param);
			delete jsonSign.topicFlash;
		},
		themeHideFlash : function() {
			var param = {
				".headTopic" : {
					height : "200px"
					
				},
				".description" : {
					display : "block"
				},
				".describe_flash" : {
					display : "none"
				}
			};
			mytheme.setStyle(param);
			delete jsonSign.topicFlash;
		}
	},
	theme : null,
	style : null,
	useimg : null,
	href : msie6 ? "#" : "javascript:",
	msie6 : function() {
		$(function() {
			$("a[href=#]").click(function() {
				return false;
			});
		})
	}
}
var tool = new Tool();
tool.msie6();
