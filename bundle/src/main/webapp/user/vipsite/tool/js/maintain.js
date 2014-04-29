/**
 * 
 */

function bindSortable() {
	$("#content1,#content2").sortable({
		handle : ".bodyContTitle",
		cancel : ".titleLinkColor,input",
		cursor : 'move',
		revert : !msieLow
	});
	$("#list_nav").sortable({
		// handle : "li",
		cursor : 'move',
		revert : !msieLow
	});
	$("#content").sortable({
		handle : ".bodyContContent",
		// cancel : "span,a,img",
		cursor : 'move',
		revert : !msieLow,
		stop : function() {
			if ($(this).children(":first")[0] == $("#content1")[0]) {
				$("#content1").attr("class", function(i, v) {
					return v.replace("sideRight", "sideLeft");
				})
				$("#content2").attr("class", function(i, v) {
					return v.replace("mainLeft", "mainRight");
				})
			} else {
				$("#content2").attr("class", function(i, v) {
					return v.replace("mainRight", "mainLeft");
				})
				$("#content1").attr("class", function(i, v) {
					return v.replace("sideLeft", "sideRight");
				})
			}
			onLoad.layout();
		}
	});
	$("#headList,#belowList").sortable({
		cancel : "#list_nav li",
		cursor : 'move',
		connectWith : ".connectedList",
		revert : !msieLow
	});
	$("#headList span,#content span").disableSelection();
}

function bindTitleEdit() {
	var input;
	var titleSpan;
	var blurInput = function() {
		if (input) {
			var value = $.trim(input.val());
			if (!value) {
				info("标题不可为空")
			} else {
				titleSpan.text(value)
				if (typeof jsonContent.title != "object")
					jsonContent.title = {};
				jsonContent.title[(titleSpan.parents(".moveMenu").length
						&& titleSpan.parents(".moveMenu") || titleSpan
						.parents(".moveChild")).find("#piece").val()] = value;
				input.remove();
			}
		}
	}
	$(".moveChild").click(function() {
		blurInput();
	})
	$(".moveMenu").dblclick(
			function() {
				if ($("#titleEditInput").length)
					return;
				var moveMenu = $(this);
				input = $("<input>", {
					id : 'titleEditInput',
					type : 'text',
					maxlength : 30,
					value : $.trim(moveMenu.find("span").text()),
					css : {
						position : 'absolute',
						height : moveMenu.height()
								- moveMenu.css("marginTop").replace(/\D/g, ''),
						width : moveMenu.width(),
						fontSize : '14px',
						paddingTop : '4px',
						textAlign : 'center',
						zIndex : 900
					}
				});
				input.css(moveMenu.offset());
				input.appendTo(this);
				input.css("top", function(i, top) {
					return top.replace(/\D/g, '')
							- moveMenu.css("marginTop").replace(/\D/g, '');
				})
				input.focus();
				input.blur(function() {
					blurInput();
				})
				input.click(false)
				titleSpan = moveMenu.find("span a");
				return false;
			})
	$(".moveMenu").attr("title", "双击可编辑名称");
   
	var clickTitle;
	$(".titleName").click(function(e) {
		 clickTitle = $(this);
		 $("#hid_titleName").val("");
		 $("#picPath").val("");
		 var htmlValue = $.trim(clickTitle.html());
		 var parentDiv = clickTitle.parents("div:first").parent();
		 var str = "#" + parentDiv.attr("id") + " .bodyContTitle";
		 var str2 =  "#" + parentDiv.attr("id") + " .titleName";
		 if(mytheme.has(str)) {
			 var oldCss = clickTitle.parents("div:first").css("background-image");
			 oldCss = oldCss.replace(/url\("?\http:\/\/[^/]+\//, "").replace(/\"?\)$/,"");
			 $("#picPath").val(oldCss);
		 } else {
			 $("#picPath").val("");
		 }
		 $("#hid_titleName").val(htmlValue);
		 
		 // 显示标题 checkbox
		 if(mytheme.has(str2)) {
			 $("#chk_title").attr("checked",false);
		 } else {
			 $("#chk_title").attr("checked",true);
		 }
		 
         var editDialog = $("#hid_div").dialog({
				modal : true,
				width : 380,
				title : "标题自定义",
				autoOpen : true,
				buttons : {
					确定 : function() {
						var picPath = $("#picPath").val();
						var titleName = $.trim($("#hid_titleName").val());
						if(!titleName) {
							alert("标题不可为空");
							$("#hid_titleName").focus().select();
							return;
						}
						clickTitle.html(titleName);
						var str = "#" + parentDiv.attr("id") + " .bodyContTitle";
						var $selector= $(str);
						$selector.metadata().selector=str;
						if(picPath.length>0) {
							mytheme.setup(str,{"background-image":"url("+fileServer+picPath+")"})
						} else {
							mytheme.tearDown(str);
						}
						
						if (typeof jsonContent.title != "object")
							jsonContent.title = {};
						jsonContent.title[(clickTitle.parents(".moveMenu").length
								&& clickTitle.parents(".moveMenu") || clickTitle
								.parents(".moveChild")).find("#piece").val()] = titleName;
						 editDialog.dialog('close');
						 
						// 是否显示标题
						var checkValue = $("#chk_title").attr("checked");
						var str2 =  "#" + parentDiv.attr("id") + " .titleName";
						var $selector2= $(str2);
						$selector2.metadata().selector=str2;
						if(checkValue) {
								mytheme.tearDown(str2);
						} else {
							var param = {};
							param.filter = "Alpha(opacity = 0)";
							param.opacity = "0";
							param.position = "relative\\9"; //针对IE,覆盖父容器透明问题
							mytheme.setup(str2, param);
						}
					},
					取消 : function() {
						 $("#hid_titleName").val("");
						 $("#picPath").val("");
						 $("#hid_div").hide();
						 editDialog.dialog('close');
					}
				}
		})
	})
}


$(function() {
	bindSortable();
	bindTitleEdit();
})

function clearPic() {
	$("#picPath").val("");
}
function glean() {
	var list = [];
	(function() {
		$.each(arguments, function() {
			this.each(function(i) {
				var map = {
					module : {
						moduleId : $(this).find("#piece").val()
					},
					block : layout.belongPage,
					blockOrder : i + 1,
					position : $(this).parent().attr("id")
				};
				list.push(map);
			})
		})
	})($("#content1 .moveChild"), $("#content2 .moveChild"),
			$("#headList .moveChild"), $("#list_nav .moveMenu"),
			$("#belowList .moveChild"))
	layout.layoutmoduleList = list;
}

function commonGlean() {
	if (typeof jsonContent.modified != "object")
		jsonContent.modified = {};
	jsonContent.modified[layout.belongPage] = 1;
	layout.bodyContent = $.toJSON(jsonContent);
	layout.bannerContent = $.toJSON(jsonBanner);
	layout.signContent = $.toJSON(tool.clear2(jsonSign));
	layout.footerContent = $.toJSON(jsonFooter);
	{
		$.extend(layout.laytheme, {
			theme : pageTheme,
			style : tool.clearContext($.toJSON(mytheme.style)),
			state : saveLaythemeState
		})
	}
}

function sendToSave(reload) {
	commonGlean();
	$.ajax({
		type : "post",
		url : contextRoot + "/layout/save",
		data : {
			model : layout
		},
		beforeSend : function() {
			this.data = this.data.replace(/%5B(\D+)%5D/g, ".$1");
		},
		complete : function() {
		},
		success : function() {
			removeBusy()
			if (reload)
				location.reload();
		}
	})
}

function saveLayout() {
	busyOverlay(null, {
		text : "saving..."
	});
	glean();
	sendToSave();
}

function glean2() {
	var list = [];
	(function() {
		$.each(arguments, function() {
			this.each(function(i) {
				var map = {
					module : {
						moduleId : $(this).val()
					},
					block : layout.belongPage,
					blockOrder : i + 1,
					position:$(this).parents(".tab_content").data("contId")
				};
				list.push(map);
			})
		})
	})($("input[name=moduleHead]:checked"),
			$("input[name=moduleSide]:checked"),
			$("input[name=moduleMain]:checked"),
			$("input[name=moduleNav]:checked"))
	layout.layoutmoduleList = list;
}
function saveLayout2() {
	 //busyOverlay();
	glean2();
	sendToSave(1);
}

function showManage() {
	var managePage = document.getElementById("managePage");
	if (managePage) {
		managePage = $(managePage);
		managePage.dialog('open');
	} else {
		managePage = $("<div id='managePage' style='display:none'>");
		managePage
				.load(
						contextRoot + "/vip/*/manage?belongPage="
								+ layout.belongPage + "&randomNumber="
								+ Math.random(),
						function() {
							managePage.appendTo(document.body);
							$('#tabContainer').tabs();
							$("#tabContainer :checkbox")
									.each(
											function() {
												$(this)
														.add(
																$("label[for="
																		+ this.id
																		+ "]"))
														.wrapAll(
																"<li class=item>");
											})
							$("#tabContainer .tab_content").each(function() {
								$(this).find("li").wrapAll("<ul class=list>")
							});
							$("#tab1_head ul").addClass("connectedul").addClass("tabmin_ul");
							$("#belowList .moveChild").each(function(){
								var piece=$(this).find("#piece").val();
								$("#tab1_head input[value="+piece+"]").attr("checked",1).parent().appendTo("#below_ul")
							})
							$("#tabContainer div ul").sortable({
								revert : !msie6
							});
							$("#tab1_head ul,#below_ul").sortable({
								connectWith : ".connectedul",
								revert : !msie6
							})
							$("#tabContainer [name=moduleNav]")
									.change(
											function() {
												if ($("#tabContainer [name=moduleNav]:checked").length > 8) {
													this.checked = 0;
													alert("最多同时显示8个菜单！");
												}
											});
							defaultSort();

							managePage.dialog({
								title : "内容布局",
								width : 680,
								// show : "blind",
								modal : true,
								close : function() {
									// managePage.remove();
								},
								buttons : {
									保存 : function() {
										saveLayout2();
									},
									取消 : function() {
										managePage.dialog('close');
									}
								}
							});
						});
	}
}

function defaultSort() {
	if (jsonContent.modified[layout.belongPage])
		return;
	(function() {
		$
				.each(
						arguments,
						function() {
							var ul = this.find(".list:first");
							var items = ul.find(".item");
							var remain = items;
							var list = [];
							$(this.attr("moveChild"))
									.each(
											function(i, child) {
												items
														.each(function(j, item) {
															if ($(child).find(
																	"#piece")
																	.val() == $(
																	item)
																	.find(
																			":checkbox")
																	.val()) {
																$(item)
																		.find(
																				":checkbox")
																		.attr(
																				"checked",
																				true);
																remain = remain
																		.not(item);
																list.push(item);
																return false;
															}
														})
											})
							remain.each(function() {
								list.push(this);
							})
							ul.empty();
							$.each(list, function() {
								ul.append(this)
							})
						})
	})($("#tab1").attr("moveChild", "#headList .moveChild"), $("#tab2").attr(
			"moveChild", "#content1 .moveChild"), $("#tab3").attr("moveChild",
			"#content2 .moveChild"), $("#tab4").attr("moveChild",
			"#list_nav .moveMenu"))
}
