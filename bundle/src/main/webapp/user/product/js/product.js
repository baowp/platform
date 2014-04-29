$.getScript("/js/jquery/json.js");
function showDialog() {
	$('#dialog').dialog('open');
	$('#dialog').dialog( {
		width : 400,
		modal : true
	});
}
function chooseCategory(id, cdesc) {
	$('input[name=category]').val(id);
	$('#cdesc').val(cdesc);
	$('#dialog').dialog('close');
}

function expand() {
	$(".expansion").toggle(300);
}

$(function() {
	$(".main_c05").addClass("expansion");
	$("form:first").submit(function() {
		var $price = $("input[name=price]")
		if (isNaN($price.val())) {
			if ($price.next(".errorSpan"))
				$price.next(".errorSpan").html("请输入数字");
			else
				$price.after('<span class="errorSpan">请输入数字</span>');
			return false;
		}
	});
});

function addRow(node) {
	var $addRow = $(node).parents("tr:first");
	var str = "<tr align='center'><td><input name='paramKey' type='text'/><td><input name='paramValue' type='text'/></td>";
	str = str + "<td><a href='javascript: void(0);' onclick='addRow(this)'>添加</a>|<a href='javascript: void(0);' onclick='delRow(this)'>删除</a></td></tr>" ;
	$addRow.after(str);
}
function delRow(node) {
	var rowLen = document.getElementById("detailTable").rows.length;
	if(rowLen <=2 ) {
		return;
	}
	var $delRow = $(node).parents("tr:first");   
	$delRow.remove();
}
function checkFrm() {
	var obj = {};
	var keys = $("input[name='paramKey']").each(function(i){
		 var key = $.trim($(this).val());
		 if(key.length>0) {
			 var value = $.trim($(this).parent().next().children("input[name='paramValue']").val());
			 obj[key] = value;
		 }
	});
	$("#hidParams").val($.toJSON(obj));
	return true;
}