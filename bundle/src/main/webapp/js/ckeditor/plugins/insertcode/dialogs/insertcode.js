CKEDITOR.dialog.add('insertcode', function(editor){
    var escape = function(value){
        return value;
    };
    return {
        title: '上传图片',
        resizable: CKEDITOR.DIALOG_RESIZE_BOTH,
        minWidth: 500,
        minHeight: 50,
        contents: [{
            id: 'cb',
            name: 'cb',
            label: 'cb',
            title: 'cb',
            elements: [ {
                type: 'file',
                style: 'width:700px;height:420px',
                label: 'File',
                name:'filepath',
                id: 'filepath',
				style: 'height:40px',
				action:'test.action',
				target:'_self',
				value:'请选择图片',
				size : 38
            }]
        }],
        onOk: function(){
			var filename =document.getElementsByTagName("iframe")[2].contentWindow.document.getElementsByName("filepath")[0].value;
			var form = document.getElementsByTagName("iframe")[2].contentWindow.document.getElementsByTagName("form")[0];
       		if(checkUploadFiletype(filename)){
       			form.submit();
       		}
        },
        onLoad: function(){
        }
    };
});