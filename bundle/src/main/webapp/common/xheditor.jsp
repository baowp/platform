<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript" src="/js/xheditor/xheditor-zh-cn.min.js"></script>
<script type="text/javascript">
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
							}, 550, 300);
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
						}, 550, 300);
			}
		}
	};
	$(function() {
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
	})
</script>