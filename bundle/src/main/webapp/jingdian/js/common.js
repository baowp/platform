/**
 * 
 */
contextRoot = window.contextRoot != null ? contextRoot:"/Abbcc"
var rkey=null;

function Id(id){
	return document.getElementById(id);
}
function refreshCode(id) {
	id=id||"verifyPic";
	document.getElementById(id).src = document
			.getElementById(id).src
			+ "?" + Math.random();
}
function del() {
	var msg = "您真的确定要删除吗？";
	if (confirm(msg) == true) {
		return true;
	} else {
		return false;
	}
}
function update() {
	var msg = "确认修改？";
	if (confirm(msg) == true) {
		return true;
	} else {
		return false;
	}
}
// 多选框全选或全不选
function check_all(obj, cName) {
	var checkboxs = document.getElementsByName(cName);
	for (var i = 0; i < checkboxs.length; i++) {
		checkboxs[i].checked = obj.checked;
	}
}
function checkUploadFiletype(str) {
	if (str == "") {
		alert("请选择上传的图片！");
		return false;
	}
	var pos = str.lastIndexOf(".");
	var lastname = str.substring(pos, str.length) // 此处文件后缀名也可用数组方式获得str.split(".")
	if (lastname.toLowerCase() != ".jpg" && lastname.toLowerCase() != ".gif"
			&& lastname.toLowerCase() != ".jpeg"
			&& lastname.toLowerCase() != ".bmp"
			&& lastname.toLowerCase() != ".png") {
		alert("您上传的文件类型为" + lastname + "，图片必须为.jpg,.gif,.jpeg类型");
		return false;
	} else {
		return true;
	}
}
function getFileSize(filePath) {
	var image = new Image();
	image.dynsrc = filePath;
	return image.fileSize;
}
function checkUploadFilesize(filePath) {
	var size = getFileSize(filePath);
	if (size > 500000) {
		alert("上传的图片不能大于500K");
		return false;
	} else {
		return true;
	}

}
function checkKey(obj){
	$.getJSON("/user/checkValueBannedkey?randomNumber="+Math.random(), {
		keyValue:$(obj).val()
	}, function(result) {
		if(result.affectRows==-1){
			$("#keySpan").html("您输入的信息中含有有非法信息")
			return false;
		}else{
			return true;
		}
	});
}
function checkKeys(obj){
	var a = obj.join(",");
	$.ajax( {
		url : "/user/checkKeysBannedkey",
		dataType:"json",
		async:false,  	// 不进行异步操作
		data : {
		keyValues:a
		},
		success : function(data) {
			rkey=data;
			return;
			
		}
	})
}
String.prototype.trim = function()
{
	return this.replace(/(^\s*)|(\s*$)/g, "");
}
String.prototype.ltrim = function()
{
	return this.replace(/(^\s*)/g, "");
}
String.prototype.rtrim = function()
{
	return this.replace(/(\s*$)/g, "");
}
String.prototype.replaceAll = function(s1, s2) {
	return this.replace(new RegExp(s1, "gm"), s2);
}

// ///jquery控件
jQuery.fn.extend({
  disable: function() {		// 提交时候按钮不可用
  	$(this).attr("disabled", "disabled");
    $(this).val("提交中...");
    $(this).html("提交中...");

  },
  
  enable: function(text) {	// 提交完成后按钮功能还原
    $(this).val(text);
    $(this).html(text);
	$(this).attr("disabled", "");
  }
});

(function($){
	msie6=false,msie7=false;
	if($.browser.msie){
		if($.browser.version=="6.0")
			msie6=true;
		else if($.browser.version=="7.0")
			msie7=true;
	}
	msieLow=msie6||msie7;
})(jQuery);

(function($){
	$(function(){
		var grid=$(".listTable");
		if(grid.length&&$.fn.colorize){
			var param={
					altColor :'',
					hoverColor : '#f0f9fd',
					hiliteColor:'#f0f9fd' ,
					banRows : [],
					oneClick : true
				};
			var m=/({.*})/.exec( grid.attr("class") );
			if(m)
				$.extend(param,$.parseJSON(m[1]))
			grid.colorize(param);
		}
	});
	
	$(function(){
		$(".remove").live("click",(function(){
			if(!confirm("请确认删除")){
				return false;
			}else{
				currentEventNode=this;
			}
		}));
		$(".update").click(function(){
			if(!confirm("请确认修改")){
				return false;
			}
		})
	});
	
	info=function(str,fn){
		var inf=$("<div>",{title:"提示信息",innerHTML:str});
		inf.dialog({
			close : function() {
				inf.remove();
				if(typeof fn=="function")
					fn();
			},
			buttons : {
				确定 : function() {
					inf.dialog("close");
				}
			}
		})		
	}	

	getLink=function(url) {
		return $("<link>", {
			rel : "stylesheet",
			href : url
		}).appendTo("head");
	}
})(jQuery);

function goToByHref(url){
	window.location.href=url;
	
}
function goToByOpen(url){
	window.open(url);
}


