function setDisplay(obj) {
	$.getJSON("newsupdateDisplay?randomNumber="+Math.random(), {
		id:obj.getAttribute('newsId'),
		display:obj.getAttribute("display")
	}, function(result) {
		if(result.affectRows==1){
			if(obj.getAttribute("display")=='01'){
				obj.setAttribute("display",'02');
				obj.innerHTML="显示";
			}else{
				obj.setAttribute("display",'01');
				obj.innerHTML="隐藏";
			}
		}
	});
}
function setImagenews(obj) {
	$.getJSON("newsupdateImagenews?randomNumber="+Math.random(), {
		id:obj.getAttribute('newsId'),
		imagenews:obj.getAttribute("imagenews")
	}, function(result) {
		if(result.affectRows==1){
			if(obj.getAttribute("imagenews")=='01'){
				obj.setAttribute("imagenews",'02');
				obj.innerHTML="图片新闻";
			}else{
				obj.setAttribute("imagenews",'01');
				obj.innerHTML="一般新闻";
			}
		}
	});
}

function all_clear(ch)
{
	var checkboxs="";
    var value= document.getElementsByName(ch);
    for(var i=0;i<value.length;i++){
		if(value[i].checked==true){
			checkboxs=checkboxs+value[i].defaultValue+","
		}
	}
	if(checkboxs==""){
		alert('您还未选中你要操作的新闻！');
		return;
	}
    $.getJSON("newsallDisplay?randomNumber="+Math.random(), {
    	checkboxs:checkboxs
	}, function(result) {
			alert("修改成功");
		    for(var i=0;i<value.length;i++){
				if(value[i].checked==true){
					$(value[i]).parents('tr').find('#display').html('<img src="/user/images/eyeopen.jpg" title="显示"/>');
				}
			}
			
		}
	);
    }
function all_close(ch)
{
	var checkboxs="";
    var value= document.getElementsByName(ch);
    for(var i=0;i<value.length;i++){
		if(value[i].checked==true){
			checkboxs=checkboxs+value[i].defaultValue+","
		}
	}
	if(checkboxs==""){
		alert('您还未选中你要操作的新闻！');
		return;
	}
    $.getJSON("newsallNotDisplay?randomNumber="+Math.random(), {
    	checkboxs:checkboxs
	}, function(result) {
		alert("修改成功");
	    for(var i=0;i<value.length;i++){
			if(value[i].checked==true){
				$(value[i]).parents('tr').find('#display').html('<img src="/user/images/eyeclose.jpg" title="隐藏"/>');
			}
		}
		}
	);
    }
function all_del(ch)
{
	var checkboxs="";
	var result=del();
	if(result==false)
		return false;
    var value= document.getElementsByName(ch);
    for(var i=0;i<value.length;i++){
		if(value[i].checked==true){
			checkboxs=checkboxs+"ch="+value[i].defaultValue+"&"
		}
	}
	if(checkboxs==""){
		alert('您还未选中你要操作的新闻！');
		return;
	}
	window.location.href="newsallDel?"+checkboxs;
	
    }

