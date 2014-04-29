function show(o,obj){
	  var m=document.getElementById(obj);
	  m.style.visibility='';
	}
	//隐藏
	function hide(obj){
	 
	document.getElementById(obj).style.visibility='hidden';
	}  
	//取得左边的位移
	function getL(e){
	  var l=e.offsetLeft;
	  while(e=e.offsetParent){
	    l+=e.offsetLeft;
	  }
	  return l;
	}
	//取得顶部的位移
	function getT(e){
	  var t=e.offsetTop;
	  while(e=e.offsetParent){
	    t+=e.offsetTop;
	  }
	  return t;
	}