function checkForm() {
		var account = $.trim($("#account").val());
		var name = $.trim($("#name").val());
		var type = $("#type").val();
		var code = $.trim($("#code").val());
		if(!account || !name) {
			info("请输入名称或帐号!");
			return false;
		}
		if(type == "MSN") {
			if(!code){
				info("请粘贴临时会话代码");
				return false;
			}
		return true;
	}
}
