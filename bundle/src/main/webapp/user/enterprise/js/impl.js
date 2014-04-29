
	function picType(){
		$("#pic2").val("01");
	}
	$(document).ready(function() {
		 
		$("#isText").change(function() {
			var  lbmc = $("#watermark");
			 lbmc.toggle();
		}); 
		$("#checkPic").change(function() {
			var  lbmc = $("#pictrue");
			if($("#checkPic").attr("checked")==false){
				$("#picPath").val("");
				$("#certificatePic").show();
			}else{
				$("#certificatePic").hide();
				$("#watermark").hide();
			}
			 var file = $("#cardpic");  
			 file.after(file.clone().val(""));  
			file.remove();
			 lbmc.toggle();
		}); 
		$("#textPic").click(function() {
			document.getElementById("addText").style.display="";
			document.getElementById("addPic").style.display='none';
		});
		$("#textPic2").click(function() {
			document.getElementById("addText").value="";
			document.getElementById("addPic").style.display="";
			document.getElementById("addText").style.display='none';
		});
		$("#cid").click(function() {
			$.getJSON("user/enterprise/certificate/certificateDelWatermark.action?randomNumber="+Math.random(), {
				id:this.getAttribute('cId')
			}, function(result) {
				
				alert('删除成功！');
				document.getElementById("pictrue").src=document.getElementById("pictrue").src;
			});
		});
		
	});

