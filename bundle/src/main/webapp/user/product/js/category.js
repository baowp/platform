


function setDisplay(node) {
	var $node = $(node);
	var meta = $node.metadata();
	var isdisplay = Math.abs(meta.isdisplay - 1);
	$.ajax( {
		url : "display",
		cache : false,
		data : {
			categoryId : meta.categoryId,
			isdisplay : isdisplay
		},
		success : function() {
			meta.isdisplay = isdisplay;
			$node.html(isdisplay ? '已显示' : '已隐藏')
		}
	});
}

function change(node) {
	var categoryId = rowCategoryId(node);
	if (!confirm("您确定要调换顺序吗？")) {
		node.value = categoryId;
		return;
	}
	privateChange(categoryId, node.value)
}

function stepUp(node) {
	if (tempPid = prevRowCategoryId(node))
		privateChange(tempPid, rowCategoryId(node))
}
function stepDown(node) {
	if (tempPid = nextRowCategoryId(node))
		privateChange(tempPid, rowCategoryId(node))
}

function privateChange(category1Id, category2Id) {
	var $form = $("form:first");
	var action = $form.attr("action").replace(/^(.*\/)\w+(\.action)?$/, '$1change');
	$form.append($("<input>", {
		type : 'hidden',
		name : 'category1Id',
		value : category1Id
	})).append($("<input>", {
		type : 'hidden',
		name : 'category2Id',
		value : category2Id
	})).attr("action", action);
	$form.submit();
}
function rowCategoryId(node) {
	return $(node).parents("tr").find(".isdisplay").metadata().categoryId;
}
function modCategoryId2(node) {
	return $(node).parents("tr").prev().find(".isdisplay").metadata().categoryId;
}
function prevRowCategoryId(node) {
	var row = $(node).parents("tr");
	var sort=row.find("#sort")[0];
	var selectedIndex=sort.selectedIndex;
	if(--selectedIndex>-1){
		return sort[selectedIndex].value;
	}
}
function nextRowCategoryId(node) {
	var row = $(node).parents("tr");
	var sort=row.find("#sort")[0];
	var selectedIndex=sort.selectedIndex;
	if(++selectedIndex<sort.length){
		return sort[selectedIndex].value;
	}
}

function onRemove(node){
	var pertain;
	$.ajax({
		url: "pertain",
		cache: false,
		data: {
			belongId:rowCategoryId(node)
		},		  
		dataType:"json",
		async:false,
		success: function(data){
			pertain=data.affectRows;
	    }
	});
	if(pertain>0){
		alert("该分类存在下属类别，不能删除");
		return false;
	}
	return del();
}
function prepareModify(obj){
	var $field=$("#hid_name");
	var $image=$("#hid_image");
	if(!$field.val().replace(/\s/g,'')){
		return ;
	}	
	$.ajax({
		type:"post",
		url: "modify",
		data: {
			id:modCategoryId2(obj),
			name:$field.val(),
			image:$image.val()
		},		  
		success: function(){
			$("#hid_tr").hide();
			var $spanName = $field.parents("tr").prev().find("#name");
			var $cellImage = $spanName.parent().next();
			if($image.val()) {
				$cellImage.html('<img src='+fileServer+$image.val()+' />');
			} else {
				$cellImage.html('');
			}
			$spanName.html($field.val());
	    }
	});
}

function clearPicImg() {
	$("#hid_image").attr("value","");
}

function onModify(obj){
	var hid = $("#hid_tr");
	var $parentTr = $(obj).parents("tr");
	var $next = $parentTr.next();
	if(hid[0] == $next[0] && hid.is(":visible")) {
		return;
	}
	// 清空属性
	$("#hid_name").attr("value","");
	$("#hid_image").attr("value","");
	var $rowModify=$(obj).parents('tr:first');
	var $spanName=$rowModify.find("#name");
	var $cellImage = $rowModify.children("td:first").next();
	var image = null;
	if($cellImage.children("img").size()) {
		var image = $cellImage.find("img").attr("src").replace(fileServer,'');
	}
	$("#hid_name").attr("value",$spanName.text());
	$("#hid_image").attr("value",image);
	hid.insertAfter($parentTr);
	hid.show();
}

function cancel(obj){
	$("#hid_name").attr("value","");
	$("#hid_image").attr("value","");
	$("#hid_tr").hide();
// var $cellModify=$(obj).parents('td:first');
// var $cellName=$cellModify.prev().find("#name");
// $cellName.html($cellName.attr('oldHtml'));
// $cellModify.html($cellModify.attr('oldHtml'));
}

function modifyOnPress(obj,e){
	if(e.keyCode==13){
		$("#hid_btn").trigger('onclick');
		return false;
	}
}



function showSyscode(){
	$("#dialog").dialog('open');
	$("#dialog").dialog({
		title:"系统分类",
		width:480
	});
}
function fetchSyscodes(obj){
	var $cur=$(obj);
	var index=Number($cur.attr('id').replace('syscode',''));
	var nexts=[],tmp;
	while((tmp=$('#syscode'+(++index))).length)
		nexts.push(tmp)
	$.each(nexts,function(i){
		this.attr('length',0);
	});
	$.getJSON("../syscode/subSyscodes?randomNumber="+Math.random(), {
		fatherdict:obj.value
	}, function(result) {
		var $next=nexts[0]
		$.each(result.syscodeList,function(){
			if($next)
				$next[0][$next[0].length]=new Option(this.name,this.syscodeId)
		});
		if($next.attr("length")<1)
			$(":submit:last").attr("disabled",false);
		else 
			$(":submit:last").attr("disabled",true);
	});
}
function prepareLastForm(){
	var belongId,desc="";
	$("form#dialog select").each(function(){
		if(this.selectedIndex>-1){
			belongId=this.value;
			desc+=this[this.selectedIndex].text+' > ';
		}
	});
	$("form#dialog input[name=belongId]").val(belongId);
	$("form#dialog input[name=cdesc]").val(desc);
}