/**
 * 
 */
function GroupTool() {
	for ( var key in this.onload)
		$(this.onload[key]);
}
GroupTool.prototype = {
	slideTime : 300, // 动画时间
	onload : {
		blurButton : function() {
			$(".ui-dialog-buttonset button").live("focus", function() {
				this.blur()
			})
		},
		pieceEdit : function() {
			gtool.addPieceEdit(".moveChild");
			if (msie6)
				$("#show_editBtn").hover(function() {
					$("#edit_piece_hidden").show();
				}, function() {
					$("#edit_piece_hidden").hide();
				});
		},
		pieceEditExpand : function() {
			$("#addPieceBut").click(gtool.openAddPiece);
			$("#delPieceBut").click(gtool.delPiece);
			// gtool.tabs = $("#add_piece_container").html();
			// $("#tabs").remove();
		},
		pieceAddLayerEvent : function() {
			var addLayer = $("#add_pieceLayer").find("div.box-adder");
			addLayer.bind("click", gtool.openAddPiece);
		},
		showPieceAddLayer : function() { // 是否显示 [添加模块]
			var addLayerParent = $("#add_pieceLayer").find("div.box-adder")
					.parent();
			if ($("#headList").children("div.moveChild").size() == 0) {
				$("#headList").children("div.layerParent").remove();
				$("#headList").append(addLayerParent.clone(true));
			} else {
				$("#headList").children("div.layerParent").remove();
			}
			if ($("#belowList").children("div.moveChild").size() == 0) {
				$("#belowList").children("div.layerParent").remove();
				$("#belowList").append(addLayerParent.clone(true));
			} else {
				$("#belowList").children("div.layerParent").remove();
			}
			$("#content").children(".moveList").each(function() {
				if ($(this).children("div.moveChild").size() == 0) {
					$(this).children("div.layerParent").remove();
					$(this).append(addLayerParent.clone(true));
				} else {
					$(this).children("div.layerParent").remove();
				}
			});
			if(msie6)
				gtool.setResizeHandleHeight();
		},
		showEditTitleLayer : function() { // show title layer
			$(".titleName").live('hover', function() {
				$(".titleName").attr('title', "单击可编辑标题");
			});
			$(".titleName").live('click', gtool.editPieceTitle);
		}
	},
	openAddPiece : function() {
		gtool.clickPiece = $(this).parents(".moveChild");
		if (gtool.clickPiece.length == 0) {
			gtool.clickPiece = $(this).parents(".layerParent");
		}
		if (gtool.dialog)
			gtool.dialog.dialog("open");
		else {
			var options = {
				title : "添加模块",
				width : 600,
				height : msie6 ? 320 : undefined,
				modal : true,
				close : function() {
					// dialog.remove();
				},
				buttons : {
					添加 : function() {
						var map = {};
						$("#tabs-1,#tabs-2,#tabs-5,#tabs-6").find(
								"label.item-inexist-check").each(function() {
							map[$(this).data("mod")] = this;
						});
						for ( var key in map) {
							gtool.showAddPiece(key);
						}
						gtool.dialog.dialog("close");
					},
					关闭 : function() {
						gtool.dialog.dialog("close")
					}
				}
			};
			gtool.dialog = $("#tabs").dialog(options);
		}
		gtool.clearTabs();
		$("#tabs")
				.find("label:not(.item-exist)")
				.die("click")
				.live(
						"click",
						function() {
							if ($(this).parents("#tabs-5").length > 0) { // 自定义
								$(this).toggleClass("item-inexist-check");
								var li = $(this).parent();
								if (($(li)[0] == $(li.parent().children(
										"li:last"))[0])
										&& $(this).hasClass(
												"item-inexist-check")) {
									var newDefined = "user_defined"
											+ ($(this).data("mod")
													.substring(12) - 0 + 1); // 下标加1
									li.after($("<li/>").append($("<label/>").addClass(newDefined).data("mod",newDefined).html("自定义")));
								}
							} else {
								$("#tabs ." + $(this).data("mod")).toggleClass(
										"item-inexist-check"); // 内容包含宽窄
							}
						});
	},
	delPiece : function() {
		var piece = $(this).parents(".moveChild").first();
		var parent = piece.parent();
		piece.unbind('mouseover');
		$("#container_edit_piece").append($("#edit_piece"));
		piece.slideUp(this.slideTime, function() {
			$(this).remove();
			if (parent.children("div.moveChild").size() == 0
					&& parent.children("div.layerParent").size() == 0) {
				var addLayerParent = $("#add_pieceLayer").find("div.box-adder")
						.parent();
				parent.append(addLayerParent.clone(true));
			}
		});
	},
	pagePieceMap : function() {
		var map = {};
		$("[data-piece]").each(function() {
			map[$(this).data("piece")] = this;
		});
		return map;
	},
	showAddPiece : function(module) {
		$.ajax({
			url : '/rest/piece/' + username + "/" + module,
			data : {
				username : username,
				belongPage : belongPage,
				itemId : itemId
			},
			success : function(result) {
				$(gtool.clickPiece)
						.before($(result).slideDown(gtool.slideTime));
				var piece = $(gtool.clickPiece).prev();
				gtool.addPieceEdit(piece);
				piece.data("edited", "true");
				piece.find("span.titleName").html(
						moduleMap[piece.data("piece")] || "自定义"); // 标题
				if ($(gtool.clickPiece).hasClass("layerParent")) {
					$(gtool.clickPiece).remove();
				}
				gtool.clickPiece = piece; // 重新赋值 clickPiece
			}
		});
	},
	addPieceEdit : function(obj) { // 用户显示piece右上角的编辑按钮
		var piece = $(obj);
		piece.addClass("rel").hover(function() {
			if(msieLow)
				$(this).css("zIndex",1).next(".moveChild").css("zIndex",0);
			$("#edit_piece").appendTo(this).show();
		}, function() {
			$("#edit_piece").hide();
		});
	},
	editPieceTitle : function(e) {
		var clickTitle = $(this);
		var parentDiv = clickTitle.parents("div:first").parent();
		gtool.checkPieceTitle(clickTitle);
		var editDialog = $("#hid_div").dialog({
			modal : true,
			width : 380,
			title : "标题自定义",
			autoOpen : true,
			buttons : {
				确定 : function() {
					gtool.confirmPieceTitle(clickTitle);
					editDialog.dialog('close');
				},
				取消 : function() {
					gtool.cancelPieceTitle();
					editDialog.dialog('close');
				}
			}
		})
	},
	clearTabs : function() { // 清理
		$("#tabs").tabs();
		$("#tabs").find("span.exist").remove();
		$("#tabs").find("li > label").removeClass("item-inexist-check")
				.removeClass("item-exist");
		for ( var key in gtool.pagePieceMap()) {
			$("#tabs ." + key).after("<span class='exist'>已添加</span>")
					.addClass("item-exist");
		}
		$("#tabs label").hover(function() {
			$(this).addClass("item-hover");
		}, function() {
			$(this).removeClass("item-hover");
		});
	},
	checkPieceTitle : function(clickTitle) { // click title check
		var parentDiv = clickTitle.parents("div:first").parent();
		$("#hid_titleName").val("");
		$("#picPath").val("");
		var htmlValue = $.trim(clickTitle.html());
		$("#hid_titleName").val(htmlValue);
		var str = "#" + parentDiv.attr("id") + " .bodyContTitle";
		var str2 = "#" + parentDiv.attr("id") + " .titleName";
		if (mytheme.has(str)) {
			var oldCss = clickTitle.parents("div:first")
					.css("background-image");
			var exp = new RegExp("url\\(\"?\\http:\\/\\/[^/]+\\/");
			oldCss = oldCss.replace(exp, "").replace(/\"?\)$/, "");
			$("#picPath").val(oldCss);
		} else {
			$("#picPath").val("");
		}
		if (mytheme.has(str2)) {
			$("#chk_title").attr("checked", false);
		} else {
			$("#chk_title").attr("checked", true);
		}
	},
	confirmPieceTitle : function(clickTitle) { // 确定修改 title
		var parentDiv = clickTitle.parents("div:first").parent();
		var picPath = $("#picPath").val();
		var titleName = $.trim($("#hid_titleName").val());
		if (!titleName) {
			alert("标题不可为空");
			$("#hid_titleName").focus().select();
			return;
		}
		clickTitle.html(titleName);
		var str = "#" + parentDiv.attr("id") + " .bodyContTitle";
		var $selector = $(str);
		$selector.metadata().selector = str;
		if (picPath.length > 0) {
			mytheme.setup(str, {
				"background-image" : "url(" + fileServer + picPath + ")"
			});
		} else {
			mytheme.tearDown(str);
		}

		var checkValue = $("#chk_title").attr("checked");
		var str2 = "#" + parentDiv.attr("id") + " .titleName";
		var $selector2 = $(str2);
		$selector2.metadata().selector = str2;
		if (checkValue) {
			mytheme.tearDown(str2);
		} else {
			var param = {};
			param.filter = "Alpha(opacity = 0)";
			param.opacity = "0";
			param.position = "relative\\9"; // 针对IE,覆盖父容器透明问题
			mytheme.setup(str2, param);
		}
		// 更新tabs标题名称
		var piece = parentDiv.data("piece");
		var labelSign = "label." + piece;
		$("#tabs").find(labelSign).text(titleName);
		parentDiv.data("edited", "true"); // 已编辑过
	},
	cancelPieceTitle : function() {
		$("#hid_titleName").val("");
		$("#picPath").val("");
		$("#hid_div").hide();
	},
	setResizeHandleHeight : function() {
		$(".moveList .ui-resizable-e").css("height", function() {
			this.style.height = this.parentNode.offsetHeight + "px";
		})
	},
	clearPic : function() {
		$("#picPath").val("");
	}
};
var gtool = new GroupTool();