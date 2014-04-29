	function tr_display(type){
		//business_info.className = 'notetrue';
		//business_info.innerHTML = '为了给您提供匹配的产品信息，请填写贵公司主营的产品（或服务）关键字。<br>如有多个，请用逗号分隔。如：布料，拉链';
		if(type == 'buy'){
			$("#sellkeywords").hide();
			$("#salekeyword").val("");
			$("#buykeywords").show();
		}
		if(type == 'sell'){
			$("#buykeywords").hide();
			$("#buykeyword").val("");
			$("#sellkeywords").show();
			}
		if(type == 'both'){
			$("#buykeywords").show();
			$("#sellkeywords").show();
		}
		document.getElementById("business_info").innerHTML="";
		info_check_clean("business_info_check");
		document.getElementById("business_info").className="";
	}


	  function usr(type){
		  if(type=='person'){
			  var name = document.getElementById("first_name").value;
			document.getElementById("ename").value=name+"(个人)";
			

		      }else{
					document.getElementById("ename").value="";
		      
		      }
		   
		  }
	
	function refreshCode()
	{
	    document.getElementById("verifyPic").src=document.getElementById("verifyPic").src+"?"+Math.random();
	}
	
    function formatJsonDateToDate(_date){
        var dateStr = "";
		var month = _date.month+1;
		if(month<10)
			month = "0"+month;
		var date = _date.date;
		if(date<10)
			date = "0"+date;
        dateStr = (_date.year+1900) + "-" + month + "-" + date;
        return dateStr;
    }
	
	function formatJsonDateToTime(_date){
		var dateStr = "";
		var hours = _date.hours;
		if(hours<10)
			hours = "0"+hours;
		var minutes = _date.minutes;
		if(minutes<10)
			minutes = "0"+minutes;
		var seconds = _date.seconds;
		if(seconds<10)
			seconds = "0"+seconds;
        dateStr = hours + ":" + minutes + ":" + seconds;
        return dateStr;
	}
