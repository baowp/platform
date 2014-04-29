

(function(){
	if($.cookie("searchArea")){
		var style="<style type=text/css>.searchArea{display:block;}</style>"
		document.writeln(style);
	}
})();

function searchable() {
	$(".searchArea").toggle(250, function() {
		if($(".searchArea").css("display")!="none")
			$.cookie("searchArea","block",{expired:365})
		else
			$.cookie("searchArea",null)
	});
}
function goto(page){
	$("input[name=page]").val(page);
	$('form').submit();
}
function setDisplay(node) {
	var $node = $(node);
	var meta = $node.metadata();
	var isdisplay = Math.abs(meta.isdisplay - 1);
	$.ajax( {
		url : "display",
		cache : false,
		data : {
			productId : meta.productId,
			isdisplay : isdisplay
		},
		success : function() {
			meta.isdisplay = isdisplay;
			$node.html(isdisplay ? '已显示' : '已隐藏')
		}
	});
}

function change(node) {
	var productId = rowProductId(node);
	if (!confirm("您确定要调换顺序吗？")) {
		node.value = productId;
		return;
	}
	privateChange(productId, node.value)
}

function arrange(node) {
	var productId = rowProductId(node);
	if (!confirm("请确认插序")) {
		node.value = productId;
		return;
	}
	privateChange(productId, node.value,"arrange")
}

function stepUp(node) {
	if (tempPid = prevRowProductId(node))
		privateChange(tempPid, rowProductId(node))
}
function stepDown(node) {
	if (tempPid = nextRowProductId(node))
		privateChange(tempPid, rowProductId(node))
}

function privateChange(product1Id, product2Id,action) {
	var $form = $("form");
	var result = $form.attr("action").replace(/.*\/(\w+)(\.action)?$/, '$1');
	action = $form.attr("action").replace(/^(.*\/)\w+(\.action)?$/, '$1'+(action||'change'));
	$form.append($("<input>", {
		type : 'hidden',
		name : 'product1Id',
		value : product1Id
	})).append($("<input>", {
		type : 'hidden',
		name : 'product2Id',
		value : product2Id
	})).append($("<input>", {
		type : 'hidden',
		name : 'result',
		value : result
	})).attr("action", action);
	$form.submit();
}
function rowProductId(node) {
	return $(node).parents("tr").find(".isdisplay").metadata().productId;
}
function prevRowProductId(node) {
	var row = $(node).parents("tr");
	var sort=row.find("#sort")[0];
	var selectedIndex=sort.selectedIndex;
	if(--selectedIndex>-1){
		return sort[selectedIndex].value;
	}
}
function nextRowProductId(node) {
	var row = $(node).parents("tr");
	var sort=row.find("#sort")[0];
	var selectedIndex=sort.selectedIndex;
	if(++selectedIndex<sort.length){
		return sort[selectedIndex].value;
	}
}
function fleshTime(obj) {

	var id = $(obj).parents("tr").find("input[name='productId']").val();
	var url = "/user/product/fleshTime";
	$.ajax({
		type : "post",
		async : false,
		url : url,
		data : {
			id : id
		},
		success : function(time) {
			$(obj).parents("tr").find("#publishTime").html(time);
		}
	});

}
