CKEDITOR.dialog.add('uploadimage', function(editor) {
	var escape = function(value) {
		return value;
	};
	return {
		title : '上传图片到服务器',
		resizable : CKEDITOR.DIALOG_RESIZE_BOTH,
		minWidth : 500,
		minHeight : 50,
		contents : [{
			id : 'ui',
			name : 'ui',
			label : 'ui',
			title : 'ui',
			elements : [{
				type : 'file',
				style : 'width:700px;height:420px',
				label : '<strong>请选择要上传的图片，格式只能为jpg、gif、png、bmp、jpeg，大小不能超过500k。<br/>请先选择图片然后点击“<font color=#f0f0f0>上传</font>”，上传完成后再点击“<font color=#f0f0f0>确定</font>”完成图片的插入。</strong>',
				name : 'upload',
				id : 'upload',
				style : 'height:50px',
				action : "/fileUpload/uploadImage",
				target : '_self',
				value : '请选择图片',
				size : 38
			}, {
				type : 'html',
				html : '<div id="uploadedimagetitle" style="margin:2px">已经上传的图片:</div><div id="uploadedimages" style="margin:2px"></div>'
			}, {
				type : 'button',
				label : '上传',
				name : 'uploadbutton',
				id : 'uploadbutton',
				onClick : function() {
					var frames = document.getElementsByTagName("iframe");
					var currentWindows = document
							.getElementsByTagName("iframe")[frames.length - 1].contentWindow;
					var test = currentWindows.document
							.getElementsByName("upload")[0];
					if (test == undefined)
						currentWindows = document
								.getElementsByTagName("iframe")[frames.length
								- 2].contentWindow;
					var filename = currentWindows.document
							.getElementsByName("upload")[0].value;
					var form = currentWindows.document
							.getElementsByTagName("form")[0];
					if (checkUploadFiletype(filename)) {
						//if (checkUploadFilesize(filename))
							form.submit();
					}
				},
				onShow : function() {
					document.getElementById("uploadedimages").innerHTML = "";
				}

			}]
		}],
		onOk : function() {
			var html = document.getElementById("uploadedimages").innerHTML;
			html = html.replaceAll("50px", "auto");
			editor.insertHtml(html);
		},
		onLoad : function() {
		}
	};
});