


function showDialog() {
	$('#dialog').dialog('open');
	$('#dialog').dialog( {
		width : 400,
		modal : true
	});
}
function chooseProduct(id, name) {
	$('input[name=productId]').val(id);
	$('#productName').val(name);
	$('#dialog').dialog('close');
}