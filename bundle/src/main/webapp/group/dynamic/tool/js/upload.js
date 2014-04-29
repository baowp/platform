
function UpLoad() {}
UpLoad.prototype = {
	topicDid : function(path) {
		$("#topicImgUploadTemp").empty();
		$("<img>", {
			className : "check2 {selector:'.headTopic',property:'background'}",
			height : 20,
			src : fileServer + path
		}).appendTo("#topicImgUploadTemp").click(tool.fn.setupTopic).trigger("click");
	},
	topicFlashDid : function(path) {
		var uploadTemp=$("#topicFlashUploadTemp");
		uploadTemp.empty();
		tool.fn.setupTopic.apply(this);
		var height=200;
		$("#topic_flash").attr("src",fileServer + path);
		$("#topic_flash").attr("height", height);
		jsonSign.topicFlash=jsonSign.topicFlash||{};
		jsonSign.topicFlash.src=fileServer + path;
		jsonSign.topicFlash.height=height;
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
	signDid : function(path){
		$("#topicBgUploadTemp").empty();
		$("<img>", {
			className : "check2 {selector:'.topbaner',property:'background',value:'url("+fileServer+path+")'}",
			height : 20,
			src : fileServer + path
		}).appendTo("#topicBgUploadTemp").click(function(){mytheme.setup(this)}).trigger("click");
	},
	navDid : function(path){
		$("#menuBgUploadTemp").empty();
		$("<img>", {
			className : "check2 {selector:'.headerMenuBg',property:'background'}",
			height : 18,
			src : fileServer + path
		}).appendTo("#menuBgUploadTemp").click(tool.fn.setupNavBg).trigger("click");
	},
	titleDid : function(path){
		$("#titleBgUploadTemp").empty();
		$("<img>", {
			className : "check2 {selector:'.bodyContTitle',property:'background'}",
			height : 18,
			src : fileServer + path
		}).appendTo("#titleBgUploadTemp").click(tool.fn.setupTitleBg).trigger("click");
	},
	inBgDid : function(path){
		$("#siteBgUploadTemp").empty();
		$("<img>", {
			className : "check2 {selector:'.inBg',property:'background'}",
			height : 30,
			src : fileServer + path
		}).appendTo("#siteBgUploadTemp").click(tool.fn.setupInBg).trigger("click");
	},
	outBgDid : function(path){
		$("#siteBgOutUploadTemp").empty();
		$("<img>", {
			className : "check2 {selector:'.outBg',property:'background'}",
			height : 30,
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