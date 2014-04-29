/**
 * @author baowp
 * @date 20110427
 */

function Maintain() {
	for ( var key in this.onload)
		$(this.onload[key]);
}
Maintain.prototype = {
	onload : {
		bindResizable : function() {
			$(".inBg").addClass("rel");
			$(".moveList").resizable({
				handles : "e",
				stop : maintain.onload.resizedList
			});
		},
		bindSortable : function() {
			$(".moveList").sortable({
				cancel : ".titleLinkColor,input",
				cursor : 'move',
				connectWith : ".moveList",
				revert : !msieLow,
				stop : gtool.onload.showPieceAddLayer
			});
			$(".hunkList").sortable({
				cancel : "#list_nav a",
				cursor : 'move',
				connectWith : ".hunkList",
				revert : !msieLow,
				stop : gtool.onload.showPieceAddLayer
			});
			$("#moveListUl").sortable({
				cancel : "input",
				cursor : 'move',
				revert : !msieLow,
				stop : maintain.relateList.movedList
			})
			$(".hunkList span,#content span").disableSelection();
		},
		resizedList : function() {
			$(".moveList").each(function() {
				$("#width" + this.id.replace(/\D/g, '')).val(this.offsetWidth)
				$(this).css("height", "auto");
			})
		},
		bindChangeSize : function() { // input change
			$("#moveListUl").find("input").each(function() {
				$(this).change(function() {
					$($(this).parent().data("target")).width($(this).val());
				});
			});
		}
	},
	relateList : {
		movedList : function() {
			$("#moveListUl li").each(function(i) {
				$($(this).data("target")).appendTo("#content")
			})
			$(".moveList").each(
					function(i) {
						$(this).removeClass("sort1").removeClass("sort2")
								.removeClass("sort3");
						$(this).addClass("sort" + (i + 1));
					});
		}
	},
	saveProcess : {
		busy : function() {
			busyOverlay(null, {
				text : "saving..."
			});
		},
		gleanLaymod : function() {
			layoutmoduleList = [];
			(function() {
				$.each(arguments, function() {
					this.each(function(i) {
						var $title = $(this).find(".titleName");
						var name = $title.length
								&& !$(this).hasClass(".moveList") ? $title
								.html().trim() : undefined;
						layoutmoduleList.push({
							module : {
								moduleId : $(this).data("piece")
							},
							name : name,
							page : belongPage,
							pieceOrder : i + 1,
							position : $(this).parent().attr("id"),
							layout : layout
						});
					});
				});
			})($("#content1 .moveChild"), $("#content2 .moveChild"),
					$("#content3 .moveChild"), $(".moveList"),
					$("#headList .moveChild"), $("#belowList .moveChild"));
		},
		gleanWidth : function() {
			$.extend(listWidth, {
				layoutId : layout.layoutId,
				page : belongPage,
				one : Id("content1").offsetWidth,
				two : Id("content2").offsetWidth,
				three : Id("content3").offsetWidth
			})
		},
		gleanUserDefined : function() {
			userDefinedList = [];
			$(".user_defined").each(function() {
				if ($(this).data("edited"))
					userDefinedList.push({
						userdefinedId : $(this).data("definedId") || undefined,
						enterpriseId : enterpriseId,
						moduleId : $(this).data("piece"),
						name : $(this).find(".titleName").html(),
						content : $(this).find(".bodyContContent").html(),
						serial : $(this).data("piece").replace(/\D/g, '')
					})
			})
		},
		gleanOther : function() {
			layout.signContent = $.toJSON(jsonSign);
			$.extend(laytheme, {
				theme : pageTheme,
				style : $.toJSON(mytheme.style),
				type : saveLaythemeType,
				layoutId : layout.layoutId
			})
		},
		send : function() {
			$.ajax({
				type : "post",
				url : "/rest/layout/save",
				data : {
					model : layout,
					layoutmoduleList : layoutmoduleList,
					laytheme : laytheme,
					id : layout.layoutId,
					belongPage : belongPage,
					listWidth : listWidth,
					userDefinedList : userDefinedList
				},
				beforeSend : function() {
					this.data = this.data.replace(/%5B(\D+)%5D/g, ".$1");
				},
				complete : function() {
				},
				success : function(result) {
					removeBusy()
				}
			})
		}
	},
	save : function() {
		for ( var key in this.saveProcess)
			this.saveProcess[key]();
	},
	publish : function() {
		$.ajax({
			url : "/rest/static/" + username + "/publish",
			cache : false,
			beforeSend : function() {
				busyOverlay(null, {
					text : "publishing..."
				});
			},
			complete : function() {
				removeBusy()
			}
		});
	}
};

var maintain = new Maintain();