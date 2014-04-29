$(document).ready(function(){
	upgrade($("#apply"));
	$("#bankselect").click(function(){
		var bank= $("input[name='bank'][type='radio']:checked").val(); 
		if(bank=='AgriculturalBank')
		window.open("https://easyabc.95599.cn/commbank/netBank/zh_CN/CommLogin.aspx");
		else
		window.open("https://ibsbjstar.ccb.com.cn/app/V5/CN/STY1/login.jsp");
	});
});
function upgrade(obj){
	obj.click(function(){
		var id = $("input[name='id']").val();
		var ename = $("input[name='ename']").val();
		var address = $("input[name='address']").val();
		var position = $("input[name='position']").val();
		var uname = $("input[name='uname']").val();
		var cellphone = $("input[name='cellphone']").val();
		var phone = $("input[name='phone']").val();
		var year= $("input[name='year'][type='radio']:checked").val(); 
		var grade1=$("input[name=grade]").val();
		var url="/user/upgrade/join";
		$.ajax({
			type : "post",
			url : url,
			data : {
				id : id,
				ename:ename,
				address:address,
				position:position,
				uname:uname,
				cellphone:cellphone,
				phone:phone,
				year:year,
				grade1:grade1
			},
			success : function() {
				window.location="/user/upgrade/bankselect";
			}
		});
	});
}
function tip(){
	info("业务已受理,请及时付款并联系管理员");
}