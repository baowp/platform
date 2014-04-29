  //<![CDATA[
  var map = null;
  var DM = null;
  var M_arr=Array();
  var markerArr=new Array();
  var marker=null;
  var overlay=null;
    function load() {
      if (GBrowserIsCompatible()) {
        map = new GMap2(document.getElementById("map"));
		//map.addControl(new GSmallMapControl());//放大缩小控件
        map.addControl(new GLargeMapControl());//放大缩小控件
		map.addControl(new GMapTypeControl());
		map.addControl(new GScaleControl());//比例
		var Overmap= new GOverviewMapControl(new GSize(150,150));//放小查看
		map.addControl(Overmap);
		
        map.setCenter(new GLatLng(39.948108425907854, 119.5890998840332), 14);
		map.setMapType(G_SATELLITE_MAP);

		//中心坐标
		var center = map.getCenter();
        $("message").innerHTML = center.toString();		 
		
      GEvent.addListener(map,"moveend",function() {		 
		  var center = map.getCenter();
          $("message").innerHTML =" 坐标："+ center.toString()+"等数:"+map.getZoom();
		  //alert(map.getZoom());
		  });
		 //end中心坐标



		 //生成客户端代码		
		 DM = new GClientGeocoder();
      }
    }
	function M_add(op){//添加标注
		if (DM) {
			M_arr=Array();
			map.clearOverlays();
			marker = new GMarker(map.getCenter(),{dragCrossMove:false,draggable:true,title:"增加标注"});
			M_arr[0]=marker;		
            map.addOverlay(marker);	
			GEvent.addListener(marker,"drag",function(){
					var p=this.getPoint().toString();					
					$("message").innerHTML =" 坐标："+ p+map.getZoom();
					if($("LatLng")) $("LatLng").value=p;
					if($("zoom")) $("zoom").value=map.getZoom();

					if($("submt")){
						$("submt").value="添 加";				
				        $("submt").disabled=false;
					}
					if($("badd")){
				        $("badd").disabled=false;
					} 
					M_modify(p);//修改左边的组point
             if(M_arr.length>1){M_line(M_arr);  }
					
					//M_line(M_arr);
					 
				});	
			//呼叫 表单
			GDownloadUrl("./form/addbz.php?op="+op, function(data,test) { //ajax
			     $("LM").innerHTML=data; 
				 if($("LatLng"))$("LatLng").value=map.getCenter().toString();
				 if($("zoom")) $("zoom").value=map.getZoom();			 
				var p=map.getCenter().toString();				
				M_modify(p)
				$("submt").value="请移动左边中心标注图......";				
				$("submt").disabled=true;
			});
			//alert(map.getCenter().toString());
			M_line(M_arr);
           
		   }else{
			   alert("代码可能有错误");
		}
	}

	//表单提交处理
   function Fsub(fid){	   

       if(  Form.Element.getValue("op")!="10001"&& $('name') && Form.Element.getValue("name")==""){
		   alert("请输入标题");
		   $("name").focus();
		   return 0;
	   }
	   if(Form.Element.getValue("op")=="title"){
		   //var LL=$("group");
		  //if((LL.childNodes.length-2)<(M_arr.length-index-1)*$("divHid").childNodes.length;
		  if(M_arr.length<2){
			  alert("错误：增加主题必须为2个结点以上");
			  return false;
		  }
	   }
	   
	   var pars=Form.serialize(fid);//变量	  
	  
	   var url = $(fid).action;
	   Form.disable(fid);
      //这里添加 ajax
	   $("message").innerHTML="数据加载中......";
	    //alert('hello');
	    //var myAjax = new Ajax.Updater('LM', url, {method: 'post', parameters: pars}); 
		 var myAjax = new Ajax.Request( url,{method: 'post', parameters: pars, onComplete: showResponse});
		 function showResponse(originalRequest){
			 //put returned XML in the textarea
			// $('result').value = originalRequest.responseText;
			if(confirm("继续添加否？")){
				if(Form.Element.getValue("op")!="1"){
					M_add(Form.Element.getValue("op"));
				}else{
				Form.enable(fid);
				$("submt").value="继续添加：请移动左边的标注图";
				$("submt").disabled="disabled";
				if($("name"))$("name").value="";
				$("LatLng").disabled="disabled";
			 }
			}else{
				M_clearM();
				$("LM").innerHTML=originalRequest.responseText;
			}			 
			 $("message").innerHTML="数据成功加载";
		}


	    return 0;
   }	
	//end

	function M_menu(pars){
		var url="menu.php";		
		if(parseInt(pars)==1){			
			M_view('1');
		}
		pars="op="+pars;
		$('submenu').value='Loading......';
		var myAjax = new Ajax.Updater('submenu', url, {method: 'post', parameters: pars});
	}

	function M_modify(op){//修改
		alert(op);
	}


	function M_view(op){//查看
		//alert('view='+op);
		var pars="op="+op;//变量	 op=1浏览首页
		var url = "view.php";
		 $("LM").innerHTML="添加数据中......";	    
	    var myAjax = new Ajax.Updater('LM', url, {method: 'post', parameters: pars});       
	    return 0;
	}

	function M_site(mid){//查看站点 在地图上标出
	       if (DM) {
			  GDownloadUrl("point.php?op=site&mid="+mid, function(data,test) { //ajax
			   // GDownloadUrl("etc/test.xml", function(data,test) { //ajax
			     
				 xml = GXml.parse(data);
				 if(!xml||!xml.documentElement)	{
					 alert("无效的XML文件");
					 return;
				}				 
				 //取得你的点进行标准                 

				 var X=new XML(xml,"site");
				 var arr=X.getAV('lat');
				 var point = new GLatLng(X.getAV('lat'),X.getAV('lng'));
				 //alert(arr);
				 var marker = new GMarker(point,{title:X.getAV('name')});
				 map.setCenter(point);
                 arr=X.getAV('zoom');
				 //alert(X[0].getAV('zoom'));
				 map.setZoom(parseInt(arr[0]));
				 arr=X.getAV('name');
				 map.openInfoWindowHtml(point,arr[0]);

				 map.addOverlay(marker);
			});
		   }else{
			   alert("错误：M_site");
		   }
	}

	function M_clear(){//清除图标
	    if (DM) {
			map.clearOverlays();
			markerArr=new Array();
		}else{
			   alert("错误：clear");
		}
	}

	function M_addGroup(gid,hid){//组 增加标注点
	  if (DM) {
		  $("badd").value="请移动左边中心标注图......";
		  $("badd").disabled=true;
		  var i=0;
		 // alert(M_arr.length);
		  //alert(M_arr[0].dragend);
		  
		  var marker2 = new GMarker(marker.getPoint(),{dragCrossMove:false,draggable:true,title:M_arr.length,icon:M_ico("p")});
          map.addOverlay(marker2);//
		  M_arr[M_arr.length-1]=marker2;

		  marker.setPoint(map.getCenter());
		  M_arr[M_arr.length]=marker;
		  M_line(M_arr);
		  for( i=0;i<$(hid).childNodes.length;i++){
               var newnode =$(hid).childNodes[i].cloneNode(true);
			   $(gid).appendChild(newnode);
		  }
		  var p=marker.getPoint().toString();
		  M_modify(p);

		  //为marker2增加动作
		   GEvent.addListener(marker2, "drag", function() {
			   var index = findMarkerIndex(marker2,M_arr);
			   if (index >= 0) { 
						M_arr[index]=marker2;
						M_line(M_arr);
						var LL=$("group");
						var ii=LL.childNodes.length-2-(M_arr.length-index-1)*$("divHid").childNodes.length;
						$("message").innerHTML=LL.childNodes.length+' '+index+' '+ii+' '+(LL.childNodes[ii].getAttribute("value"));//,p);
						LL.childNodes[ii].setAttribute("value",marker2.getPoint().toString());
					}
			}); 

	  }else{
		  alert("错误：addGroup");
	  }
	}

function M_ico(name){//建立图片
  var icon = new GIcon();
  icon.image = "image/" +name+ ".png";
  icon.shadow = "image/shadow.png";
  icon.iconSize = new GSize(20, 34);
  icon.shadowSize = new GSize(37, 34);
  icon.iconAnchor = new GPoint(9, 34);
  icon.infoWindowAnchor = new GPoint(9, 2);
  icon.infoShadowAnchor = new GPoint(18, 25); 

  return icon;
}
function M_line(M){//画线
	var i=0;
	var str='';
	var parr=new Array();
	var encoded_levels ='';
	if(M.length<2)return 0;
	for(i=0;i<(M.length);i++){
		parr[i]=M[i].getPoint();
		str+=M[i].getPoint().toString()+'<br>';
		encoded_levels+= encodeNumber(3);
	}
	var encode_point=createEncodings(parr);
	//$("message").innerHTML=(encode_point+"\n"+encoded_levels+"<br>"+str);

    if(overlay)map.removeOverlay(overlay);
	overlay = GPolyline.fromEncoded({color: "#0000FF",
                                      weight:5,
                                      points:encode_point,
                                      zoomFactor: 32,
                                      levels: encoded_levels,
                                      numLevels: 4
                                      });
	map.addOverlay(overlay);
   
}
function M_modify(p){
	var LL=$("group");
	if(LL){
		LL.childNodes[LL.childNodes.length-2].setAttribute("value",p);
		//alert(LL.childNodes[LL.childNodes.length-2].getAttribute("value"));
	}
}
function M_group(gid,name){//载入group 连线
        
	       if (DM) {
			   $("message").innerHTML='Loading......';
			    M_clearM();				
			  GDownloadUrl("point.php?op=group&gid="+gid, function(data,test) { //ajax
			 // alert(data);
				 //var M=new Array();
				 var i=0;
				 var point = null;
				 xml = GXml.parse(data);
				 if(!xml||!xml.documentElement)	{
					 alert("无效的XML文件");
					 return;
				}				 
				 //取得你的点进行标准 		 

				 var X=new XML(xml,"group");				
				 var lat=X.getAV('lat');
				 var lng=X.getAV('lng');
				 if(lng.lenght==0){
				      $("message").innerHTML='Fail';
					  return true;
				 }
				 map.setCenter(new GLatLng(lat[0],lng[0]));
				 for(i=0;i<lat.length;i++){
					 point = new GLatLng(lat[i],lng[i]);
					 var mtem = new GMarker(point,{dragCrossMove:false,draggable:true,title:name+':'+(i+1),icon:M_ico("p")});
					 map.addOverlay(mtem);
					  //为mtem增加动作
		           GEvent.addListener(mtem, "drag", function() {
		             	   var index = findMarkerIndex(mtem,M_arr);
			              if (index >= 0) { 
				        		M_arr[index]=mtem;
					        	M_line(M_arr); 
								//$("message").innerHTML=index+':'+M_arr[index].getPoint().toString()+;
								$("message").childNodes[0].nodeValue=index;
								//LL.childNodes[ii].setAttribute("value",marker2.getPoint().toString());
							}
						}); 
					M_arr[i]=mtem;					 
				 }
				 M_line(M_arr);
				 $("message").innerHTML='successful';
			});
		   }else{
			   alert("错误：M_group");
		   }   
}

function M_clearM(){//清楚 刚刚的M_arr
     for(var i=0;i<M_arr.length;i++){
		 if(M_arr[i])map.removeOverlay(M_arr[i]);
		 M_arr[i]=null;
	 }
	 M_arr=new Array();
	 M_arr.length=0;
}
	//
    //]]>
