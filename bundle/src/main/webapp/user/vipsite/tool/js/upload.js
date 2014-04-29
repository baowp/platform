
function UpLoad() {}
UpLoad.prototype = {
	topic : function(form, method) {
		if (!form.elements['upload'].value)
			return false;
		function fn() {
			form.elements['belongId'].value = layout.layoutId;
			form.elements['callback'].value = method || "upload.topicDid";
			form.submit();
		}
		tool.safe(fn);
		return false;
	},
	topicDid : function(path) {
		$("#topicImgUploadTemp").empty();
		$("<img>", {
			className : "check2 {selector:'.headTopic',property:'background'}",
			height : 20,
			width : 100,
			src : fileServer + path
		}).appendTo("#topicImgUploadTemp").click(tool.fn.setupTopic).trigger("click");
	},
	topicFlash : function(form) {
		return this.topic(form, "upload.topicFlashDid");
	},
	topicFlashDid : function(path) {
		var uploadTemp=$("#topicFlashUploadTemp");
		uploadTemp.empty();
		var a=$("<button>", {
			className : "ml20px fl {selector:'.headTopic',property:'height',value:'auto'}"
		}).appendTo(uploadTemp);
		$("<embed>", {
			className : "check2",
			height : 20,
			width : 100,
			src : fileServer + path
		}).appendTo(a);
		a.click(function(){
			tool.fn.setupTopic.apply(this);
			var height=200;
			$("#topic_flash").attr("src",fileServer + path);
			$("#topic_flash").attr("height", height);
			var param={".headTopic":{height:"auto",background:"none"},".description":{display:"none"},
					".describe_flash":{display:"block"}};
			mytheme.setStyle(param);
			jsonSign.topicFlash=jsonSign.topicFlash||{};
			jsonSign.topicFlash.src=fileServer + path;
			jsonSign.topicFlash.height=height;
		}).trigger("click");
		uploadTemp.append($("<span>高度</span>")).append($("<input>",{
			type:"text",
			size:6,
			change : function() {
				if (!isNaN(this.value)) {
					jsonSign.topicFlash.height = this.value;
					$("#topic_flash").attr("height", this.value);
				}
			}
		}));
	},
	sign : function(form){
		return this.topic(form, "upload.signDid");
	},
	signDid : function(path){
		$("#topicBgUploadTemp").empty();
		$("<img>", {
			className : "check2 {selector:'.topbaner',property:'background',value:'url("+fileServer+path+")'}",
			height : 20,
			width : 100,
			src : fileServer + path
		}).appendTo("#topicBgUploadTemp").click(function(){mytheme.setup(this)}).trigger("click");
	},
	nav : function(form){
		return this.topic(form, "upload.navDid");
	},
	navDid : function(path){
		$("#menuBgUploadTemp").empty();
		$("<img>", {
			className : "check2 {selector:'.headerMenuBorder',property:'background'}",
			height : 18,
			width : 18,
			src : fileServer + path
		}).appendTo("#menuBgUploadTemp").click(tool.fn.setupNavBg).trigger("click");
	},
	title : function(form){
		return this.topic(form, "upload.titleDid");
	},
	titleDid : function(path){
		$("#titleBgUploadTemp").empty();
		$("<img>", {
			className : "check2 {selector:'.bodyContTitle',property:'background'}",
			height : 18,
			width : 18,
			src : fileServer + path
		}).appendTo("#titleBgUploadTemp").click(tool.fn.setupTitleBg).trigger("click");
	},
	inBg : function(form){
		return this.topic(form, "upload.inBgDid");
	},
	inBgDid : function(path){
		$("#siteBgUploadTemp").empty();
		$("<img>", {
			className : "check2 {selector:'.inBg',property:'background'}",
			height : 30,
			width : 30,
			src : fileServer + path
		}).appendTo("#siteBgUploadTemp").click(tool.fn.setupInBg).trigger("click");
	},
	outBg : function(form){
		return this.topic(form, "upload.outBgDid");
	},
	outBgDid : function(path){
		$("#siteBgOutUploadTemp").empty();
		$("<img>", {
			className : "check2 {selector:'.outBg',property:'background'}",
			height : 30,
			width : 30,
			src : fileServer + path
		}).appendTo("#siteBgOutUploadTemp").click(tool.fn.setupOutBg).trigger("click");
	},
	log : function(form){
		return this.topic(form, "upload.logDid");
	},
	logDid : function(path) {
		var img=$("<img>",{
			src : fileServer + path,
			css:{position:'absolute',visibility:'hidden'}
		}).appendTo("body");
		var k = img.width() > img.height() ? 'width' : 'height';
		jsonSign.log = {src : img.attr("src")};
		img.attr(k,jsonSign.log[k] = 80);
		$("#log").empty().append(img.clone().css({position:'',visibility:''}));
		$("#logEdit").empty().append(img.clone().css({position:'',visibility:''})).append("<br>").append($("<a>",{
			href : tool.href,
			innerHTML : '删除标志',
			click : tool.deleteLog,
			focus : function(){this.blur()},
			css : {display:'inline'}
		}));
		img.remove();
	},
	flash : function(form){
		return this.topic(form, "upload.flashDid");
	},
	flashDid : function(path) {
		jsonSign.flash.src = fileServer + path;
		$('#flashdoc').show();
		$('#flashupload').hide();
		tool.flashLink();
	}
}

var upload = new UpLoad();