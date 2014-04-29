//=====================================
document.domain = "qq.com";
var global_sendLoadDataFlag = -1;
var sendDataTimeId = -1;
var global_time = new Date().getTime();

function FloadJS(url,sucfn,failfn){	
	  var FBrowser=new Object();
		FBrowser.isIE=((navigator.userAgent.indexOf('MSIE')==-1)?false:true);
		FBrowser.isIE7=((FBrowser.isIE&&window.XMLHttpRequest)?true:false);
		FBrowser.isIE6=((FBrowser.isIE&&!window.XMLHttpRequest&&window.ActiveXObject)?true:false);
		FBrowser.isFirefox=((navigator.userAgent.indexOf('Firefox')==-1)?false:true);
		FBrowser.isOpera=((navigator.userAgent.indexOf('Opera')==-1)?false:true);
		
		var h=document.getElementsByTagName('HEAD').item(0);
    var js=document.createElement("script");
    js.type="text/javascript";
    js.onerror=function(){
        if(!Fempty(failfn)&&'function'==typeof(failfn))
        failfn();
    }
    if(FBrowser.isIE){
        js.onreadystatechange=function(){
            if(this.readyState.toLowerCase()!="complete"&&this.readyState.toLowerCase()!="loaded") return;
            if(this.$funExeced!=true&&'function'==typeof(sucfn)){
                this.$funExeced=true;
                h.removeChild( js );
                sucfn();
            }
        }
    }
    else if(FBrowser.isOpera){
        //if('function'==typeof(sucfn))   sucfn();
    }
    else{
        js.onload=function(){
            if('function'==typeof(sucfn))  {sucfn();h.removeChild( js );}
        }
    }
    js.src=url;
    h.appendChild(js);
    if(FBrowser.isOpera && 'function'==typeof(sucfn))   {sucfn();h.removeChild( js );}
}


function SendLoadingData(loadedbytes, totalbytes)
{
		// return;
		if (global_sendLoadDataFlag != -1)
		 {
		 	 	return
		 }	
		  
		 //flash正确加载
		 if (loadedbytes > 0 &&　totalbytes == loadedbytes )
		 {
		 		global_sendLoadDataFlag = 1;
		 		return;
		 }
		  
	   //上报
	   global_sendLoadDataFlag = 1;
	   var para1 = "loadedbytes=" + loadedbytes + "&totalbytes=" +  totalbytes;
     var movie = document.getElementById("testloadingswf");  
     var nPercentLoaded = movie.PercentLoaded();   
     var now_time = new Date().getTime();
     var pasttime = now_time - global_time;
     var para2 = "loadpercent=" + nPercentLoaded + "&loadtime=" + pasttime;
     var para = para1 + "&" + para2;
     
     FloadJS("http://log.minigame.qq.com/qz-cgi/rosary_notify_swfloadingdata?" + para + "&r=" + Math.random(), function(t){}, function(t) {});
     
     window.onerror=testError;
		 function testError(){			
			 window.onerror=null;
			 return true;
			}
}


function SendLoadingDataPerTime()
{
		if (global_sendLoadDataFlag == -1)
		{   
				SendLoadingData(-1, -1);
		}
		else if(sendDataTimeId != -1)
		{
			  clearInterval(sendDataTimeId);
		}
}

function  TimeSendLoadingData()
{		   
	  if (global_sendLoadDataFlag == -1)
		{
				sendDataTimeId = setInterval(SendLoadingDataPerTime, 10000);	
		}
}
