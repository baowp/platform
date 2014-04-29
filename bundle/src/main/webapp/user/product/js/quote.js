$(document).ready(function() {
	$("#personName").blur(function() {
		if ($("#personName").val().trim() == "")
			$("#errorPersonName").html("姓名不能为空！");
		else
			$("#errorPersonName").html("");
	})
	$("#entName").blur(function() {
		if ($("#entName").val().trim() == "")
			$("#errorEntName").html("公司名称不能为空！");
		else
			$("#errorEntName").html("");
	})
	$("#phone").blur(function() {
		var phone = $("#phone").val().trim();
		if (phone == "")
			$("#errorPhone").html("电话不能为空！");
		else if (validatePhone(phone))
			$("#errorPhone").html("");
	})
	$("#mainBusiness").blur(function() {
		if ($("#mainBusiness").val().trim() == "")
			$("#errorMainBusiness").html("主营产品不能为空！");
		else
			$("#errorMainBusiness").html("");
	})
	$("#title").blur(function() {
		if ($("#title").val().trim() == "")
			$("#errorTitle").html("主题不能为空！");
		else
			$("#errorTitle").html("");
	})
	$("#added").blur(function() {
		if ($("#added").val().trim() == "")
			$("#errorAdded").html("内容不能为空！");
		else
			$("#errorAdded").html("");
	})
});
function validatePhone(phone) {
	var exp = /^[0-9]{3,4}\-[0-9]{7,8}$/;
	if (!exp.test(phone)) {
		$("#errorPhone").html("电话号码格式不正确！");
		return false;
	}
	$("#errorPhone").html("");
	return true;
}
function clickBtn(){
	if($("#title").val()==''||$("#added").val()==''||$("#personName").val()==''||$("#entName").val()==''||$("#phone").val()==''||$("#mainBusiness").val()=='')
	return false;
	return true;
}
