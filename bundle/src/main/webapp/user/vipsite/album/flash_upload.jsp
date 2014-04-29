<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/js/jquery/uploadify/uploadify.css" type="text/css"
	rel="stylesheet" />
<link href="/user/vipsite/album/css/uploadPic.css" type="text/css"
	rel="stylesheet" />
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/jquery/uploadify/swfobject.js"></script>
<script type="text/javascript"
	src="/js/jquery/uploadify/jquery.uploadify.v2.1.4.js"></script>
<link href="/user/css/div.css" rel="stylesheet" type="text/css" />
<link href="/user/vipsite/album/css/uploadify.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$("#fileupload")
								.uploadify(
										{
											/*注意前面需要书写path的代码*/
											'uploader' : '/js/jquery/uploadify/uploadify.swf',
											'script' : '/commonutil/uploadFlash',
											'scriptData' : {
												'userId' : '${sessionScope.abbccuser.userId}'
											},
											'buttonImg' : '/user/vipsite/album/images/uploadflash01.jpg',
											'width' : 84,
											'height' : 25,
											'cancelImg' : '/user/vipsite/album/images/btn_close_normal01.png',
											'queueID' : 'fileQueue', //和存放队列的DIV的id一致
											'fileDataName' : 'fileupload', //和以下input的name属性一致
											'auto' : false, //是否自动开始
											'multi' : true,
											'buttonText' : '点击选择', //按钮上的文字
											'simUploadLimit' : 5, //一次同步上传的文件数目
											'sizeLimit' : 19871202, //设置单个文件大小限制
											'queueSizeLimit' : 5, //队列中同时存在的文件个数限制
											'fileDesc' : '支持格式:swf/flv.', //如果配置了以下的'fileExt'属性，那么这个属性是必须的
											'fileExt' : '*.swf;*.flv',//允许的格式  
											onComplete : function(event,
													queueID, fileObj, response,
													data) {
												$(".warning").html(response);
												$(".warning").show();
												$("#load")
														.attr("src",
																"/user/vipsite/album/images/load02.jpg");
											},
											onError : function(event, queueID,
													fileObj) {
												alert("文件:" + fileObj.name
														+ "上传失败");
											},
											onCancel : function(event, queueID,
													fileObj) {
												$("#load")
														.attr("src",
																"/user/vipsite/album/images/load02.jpg");
											},
											onSelect : function() {
												$("#load")
														.attr("src",
																"/user/vipsite/album/images/load01.jpg");
											}
										});
					});
</script>
<script type="text/javascript">
	//必须的
	function uploadifyUpload() {
		$('#fileupload').uploadifyUpload();
	}
	function loadChange() {
		$("#load").attr("src", "/user/vipsite/album/images/load01.jpg");
	}
</script>
</head>
<body>
	<div class="warning" id="warning" style="display: none;"></div>
	<div class="picMain">
		<div class="picMain_header">
			<div class="left">
				<input type="file" name="fileupload" id="fileupload" /><img
					id="load" src="/user/vipsite/album/images/load02.jpg"
					style="margin-left: 5px;" onClick="javascript:uploadifyUpload()"
					id="load01" onmousemove="lead01()" />
			</div>
			<div class="right">
				<img src="/user/vipsite/album/images/deletePic03.jpg"
					onclick="javascript:jQuery('#fileupload').uploadifyClearQueue()" />
					<s:if test="#parameters.pageType[0]==1">
					<img src="/user/vipsite/album/images/return01.jpg" 
					onclick="javascript:window.location='/user/album/albumshowFlash'" />

					</s:if><s:else>
					<img src="/user/vipsite/album/images/return01.jpg" 
					onclick="javascript:window.location='/user/album/albumshowFlashPage?pageType=xheditor'" />
					</s:else>
			</div>
			<p>请选择要上传的照片，点击开始上传</p>
		</div>
		<div class="picContent">
			<div class="picborder">
				<div id="fileQueue"
					style="width: 100%; min-height: 200px; overflow-y: auto;padding:1%;"></div>
				<div class="picInsert02"></div>

				<table>
					<tr>
						<td></td>
						<td>
							<div id="fileQueue"
								style="width: 100%; height: 100%; overflow-y: auto; "></div>
						</td>
					</tr>
					<tr>
						<td></td>
					</tr>
				</table>
			</div>
		</div>

	</div>



</body>
</html>
