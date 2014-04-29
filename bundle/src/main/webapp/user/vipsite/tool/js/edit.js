/**
 * 
 */
	var allPlugin = {
			testImages : {
				c : 'xheIcon xheBtnImg',
				t : '选择图片',
				s : 'ctrl+8',
				e : function() {
					var _this = this;
					_this.showIframeModal('选择图片',
							'/user/album/albumshowUploadPage?pageType=xheditor',
							function(v) {
								_this.loadBookmark();
								_this.pasteHTML(v);
							}, 500, 300);
				}
			},
		testFlash : {
			c : 'xheIcon xheBtnFlash',
			t : '选择flash',
			s : 'ctrl+7',
			e : function() {
				var _this = this;
				_this.showIframeModal('选择flash',
						'/user/album/albumshowFlashPage?pageType=xheditor',
						function(v) {
							_this.loadBookmark();
							_this.pasteHTML(v);
						}, 500, 300);
			}
		}
	};
$(function() {
	function ckeditor() {
		$(".ckeditor")
				.xheditor(
						{
							plugins : allPlugin,
							tools : 'Fullscreen,Source,Table,testImages,testFlash,FontColor,BackColor,Align,Strikethrough,Underline,Italic,Bold,FontSize,Fontface',
							skin : 'vista',
							layerShadow : 2,
							html5Upload : false,
							upBtnText : '浏览',
							upLinkExt : 'jpg,png,bmp',
							upImgUrl : '/fileUpload/uploadByJson',
							upFlashUrl : '/fileUpload/uploadByJson',
							upFlashExt : "swf"
						})
	}
	$(".editCtrl").click(
			function() {
				var piece = $(this).parents(".moveChild:first");
				var obj = piece.find(".headContContent").length ? piece
						.find(".headContContent") : piece
						.find(".bodyContContent");
				var value = obj.html();
				$("#user_defined_content").val(value);
				var foo = $("#user_defined").dialog({
					modal : true,
					width : 900,
					title : "模块自定义",
					autoOpen : true,
					buttons : {
						确定 : function() {
							var value = $("#user_defined_content").val();
							if (value.match("moveChild")) {
								alert("存在非法数据,请重新输入");
								return;
							}
							obj.html(value);
							jsonBanner[piece.find("#piece").val()] = value;
							foo.dialog("close");
						},
						取消 : function() {
							foo.dialog("close");
						}
					}
				});
				ckeditor();
			});
});