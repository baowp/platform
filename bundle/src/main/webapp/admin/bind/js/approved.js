
function doDeny() {
	$.ajax( {
		url : 'approvedToDeny',
		cache : false,
		data : {
			id : bindId,
			denyReason : $("#reason").val()
		},
		success : function() {
			location.reload();
		}
	})
}