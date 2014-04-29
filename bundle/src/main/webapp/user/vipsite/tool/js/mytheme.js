function MyTheme() {
	var instance = this;
	this.style = layout.laytheme.style || {};
	this.put = function(key, value) {
		this.style[key] = value;
	}, this.get = function(key) {
		return this.style[key] || (this.style[key] = {});
	}, this.has = function(key) {
		return this.style[key];
	}, this.setup = function(element, param) {
		var $e = $(element);
		var meta = $e.metadata();
		var selector = meta.selector;
		var property = meta.property;
		var value = meta.value || $e.val() || $e.attr("value");
		param = param || $.parseJSON('{"' + property + '":"' + value + '"}');
		tool.normalPic(param);
		$.extend(this.get(selector), param);

		if ($.browser.msie) {
			for ( var key in param)
				tool.style.addRule(selector, key + ":" + param[key]);
		} else
			tool.style.html((this.toJSON()));
	}, this.tear = function(element, param) {
		var $e = $(element);
		var meta = $e.metadata();
		var selector = meta.selector;
		this.tearDown(selector);
	}, this.tearDown = function(selector) {
		delete this.style[selector];
		if ($.browser.msie) {
			for ( var i = 0; i < tool.style.rules.length; i++) {
				if (tool.style.rules[i].selectorText == selector) {
					tool.style.removeRule(i--);
				}
			}
		} else
			tool.style.html((this.toJSON()));
	}, this.setStyle = function(param) {
		for ( var selector in param) {
			var pa = param[selector];
			$.extend(this.get(selector), pa);
			for ( var key in pa) {
				if ($.browser.msie)
					tool.style.addRule(selector, key + ":" + pa[key]);
				else
					tool.style.html(this.toJSON());
			}
		}
	}, this.toJSON = function() {
		return tool.toJSON(this.style);
	}, this.args = function(arg) {
		return $.param({
			model : $.extend({}, {
				layoutId : layout.layoutId,
				theme : {
					themeId : pageTheme.themeId
				},
				style : tool.clearContext($.toJSON(instance.style))
			}, arg)
		}).replace(/%5B(\D+)%5D/g, ".$1")
	}, this.save = function() {
		if (layout.layoutId)
			$.get(contextRoot + "/laytheme/save?" + instance.args({
				laythemeId : layout.laytheme.laythemeId
			}));
	}, this.saveAs = function() {
		$("#emptyTips").hide();
		if ($("#myStyleList li:not(#emptyTips)").length == 5) {
			alert("您最多只能保存5个性化风格");
			return;
		}
		if (jsonSign.topicFlash) {
			alert("不能保存包含主题flash的风格");
			return;
		}
		var tempLi = $("<li>", {
			css : {
				visibility : 'hidden',
				overflow : "hidden"
			}
		}).append($("<a>", {
			className : "block",
			focus : function() {
				this.blur();
			},
			click : function() {
				instance.resume(this)
			},
			href : tool.href
		}).append($("<img>", {
			src : tool.useimg.attr("src")
		}))).append($("<a>", {
			className : "fl delTopic",
			href : tool.href,
			click : function() {
				instance.remove(this)
			},
			innerHTML : '删除'
		})).appendTo("#myStyleList");

		var $img = tool.useimg.clone();
		var pos = tool.useimg.position();
		$img.css({
			position : 'absolute',
			opacity : "0.6"
		}).css(pos).appendTo(tool.useimg.parent());
		function saveAs() {
			$.ajax({
				url : contextRoot + "/laytheme/saveAs?" + instance.args(),
				dataType : "text json",
				cache : false,
				error : function() {
					tempLi.remove();
				},
				success : function(data) {
					if (typeof data == "object") {
						tempLi.attr("laythemeId", data.laythemeId);
					} else {
						tempLi.remove();
					}
				}
			});
		}
		tool.safe(saveAs);
		$img.animate(tempLi.find('img').position(), function() {
			tempLi.css("visibility", "visible"), $img.remove();
		})
	}, this.remove = function(link) {
		var tempLi = $(link).parent();
		if (confirm("您确定要删除吗？")) {
			$.ajax({
				url : contextRoot + "/laytheme/delete?laythemeId="
						+ tempLi.attr("laythemeId"),
				success : function(data) {
					if (data == "success") {
						tempLi.animate({
							width : 0,
							marginLeft : 0,
							marginRight : 0
						}, 400, function() {
							tempLi.remove()
						});
					}
				}
			});
		}
		link.blur();
	}, this.resume = function(link) {
		var tempLi = $(link).parent();
		$.get(contextRoot + "/laytheme/refresh", {
			laythemeId : tempLi.attr("laythemeId")
		}, function(entity) {
			tool.transfer(link.firstChild, entity.theme, function() {
				tool.setStyle(entity.jsonStyle);
				instance.style = $.parseJSON(tool.addContext(entity.style));
			});
		})
	}
}

var mytheme = new MyTheme();