

function fetchGenus(obj) {
	$.ajax( {
		url : "fetchGenus",
		data : {
			classSign : obj.value
		},
		dataType : "json",
		cache : false,
		success : function(data) {
			var sel=$("select[name=genusSign]")[0];
			sel.length=1;
			for(var i in data){
				sel.options[sel.length]=new Option(data[i].name,data[i].sign);
			}
		}
	})
}