	$(function() {
		$("#parent").draggable({
			containment : "parent"
		});

		$("#www").draggable({
			containment : "parent"
		});

		$("#showCom").click(function() {
			if ($(this).attr("checked") == true){
				$("#peLocation").show();
				$("#parent").show();
			}
			else{
				$("#peLocation").hide();
				$("#parent").hide();
			}
				
		})
		$("#showWWW").click(function() {
			if ($(this).attr("checked") == true){
				$("#pdLocation").show();
				$("#www").show();
			}
			else{
				$("#pdLocation").hide();
				$("#www").hide();
			}
				
		})
		$("#eLocation").change(function(){
			toELocation($("#parent"),this)
		})
		$("#dLocation").change(function(){
			toDLocation($("#www"),this)
		})
		

		$("#select6").change(function() {
			jQuery("#parent").css("font-size", $(this).val());
			jQuery("#www").css("font-size", $(this).val());
			if($("#showWWW").attr("checked") == true)
				toELocation($("#parent"),$("#eLocation"))
			if($("#showCom").attr("checked") == true)
				toDLocation($("#www"),$("#dLocation"))
		})

		$(".btn").click(function() {
			$.ajax({
				url : "/user/watermark/save",
				dataType : "json",
				data : {
					id:$("#watermarkId").val(),
					fontSize : $("#select6").val(),
					fontColor : $("#mycolor").val(),
					elocation:$("#eLocation").val(),
					dlocation:$("#dLocation").val(),
					showWWW :$("#showWWW").attr("checked"),
					showCom: $("#showCom").attr("checked")
				},
				success : function(result) {
					art.dialog.tips("水印设置成功", 1);
					art.dialog.close();
				}
			})
		})
	});
	function toELocation(obj,ch){
		var pWidth = obj.width();
		var pHeight = obj.height();
		var tCenter = (400-pWidth)/2;
		var tRight = 400 - pWidth;
		var lCenter = (400-pHeight)/2
									
		if($(ch).val() == 'NorthWest')
			obj.css({
				left:'0px',
				top:'0px'
			})
		if($(ch).val() == 'North')
			obj.css({
				left:tCenter+'px',
				top:'0px'
			})
		if($(ch).val() == 'NorthEast')
			obj.css({
				left:tRight+'px',
				top:'0px'
			})
		if($(ch).val() == 'West')
			obj.css({
				left:'0px',
				top:lCenter+'px'
			})
		if($(ch).val() == 'Center')
			obj.css({
				left:tCenter+'px',
				top:lCenter+'px'
			})
		if($(ch).val() == 'East')
			obj.css({
				left:tRight+'px',
				top:lCenter+'px'
			})
		if($(ch).val() == 'SouthWest')
			obj.css({
				left:'0px',
				top:400-pHeight+'px'
			})
		if($(ch).val() == 'South')
			obj.css({
				left:tCenter+'px',
				top:400-pHeight+'px'
			})
		if($(ch).val() == 'SouthEast')
			obj.css({
				left:tRight+'px',
				top:400-pHeight+'px'
			})
	}
	
	function toDLocation(obj,ch){
		var pWidth = obj.width();	
		var eheight = $("#parent").height();
		if(eheight!='')
			eheight=eheight-5;
		var pHeight = obj.height();
		var tCenter = (400-pWidth)/2;
		var tRight = 400 - pWidth;
		var lCenter = (400-pHeight)/2
								
		if($(ch).val() == 'NorthWest')
			obj.css({
				left:'0px',
				top:-eheight+'px'
			})
		if($(ch).val() == 'North')
			obj.css({
				left:tCenter+'px',
				top:-eheight+'px'
			})
		if($(ch).val() == 'NorthEast')
			obj.css({
				left:tRight+'px',
				top:-eheight+'px'
			})
		if($(ch).val() == 'West')
			obj.css({
				left:'0px',
				top:lCenter-eheight+'px'
			})
		if($(ch).val() == 'Center')
			obj.css({
				left:tCenter+'px',
				top:lCenter-eheight+'px'
			})
		if($(ch).val() == 'East')
			obj.css({
				left:tRight+'px',
				top:lCenter-eheight+'px'
			})
		if($(ch).val() == 'SouthWest')
			obj.css({
				left:'0px',
				top:400-pHeight-eheight+'px'
			})
		if($(ch).val() == 'South')
			obj.css({
				left:tCenter+'px',
				top:400-pHeight-eheight+'px'
			})
		if($(ch).val() == 'SouthEast')
			obj.css({
				left:tRight+'px',
				top:400-pHeight-eheight+'px'
			})
	}