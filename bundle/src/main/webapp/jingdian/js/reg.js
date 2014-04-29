checkRegName = function() {
            soaObj.RegisterData.regName = $.trim($("#username").val());
            soaObj.checkRegName();
        }
        
        checkRegEmail = function() { 
            soaObj.RegisterData.regEmail = $.trim($("#email").val());
            soaObj.checkRegEmail();
        }
        register = function() {
			soaObj.RegisterData.regEmail = $.trim($("#email").val());
			soaObj.RegisterData.regUsername = $.trim($("#username").val());
            soaObj.RegisterData.regPwd = $.trim($("#password").val());
            soaObj.RegisterData.regName = $.trim($("#name").val());
			soaObj.RegisterData.regEntName = $.trim($("#entName").val());
            soaObj.RegisterData.regPhone = $.trim($("#phone").val());
			soaObj.RegisterData.regCellphone = $.trim($("#cellphone").val());
            soaObj.register();
        }	

$(function() {
	$("#email").bind(
			{
				focus : function() {
					siteState($(this).attr("id"), "请输入您的email", "onFocus");
				},
				blur : function() {
					if ($(this).val() == '') {
						siteState($(this).attr("id"), "email不能为空!", "onError");
					} else {
						if (validateEmail($(this).val()) == 1) {
							siteState($(this).attr("id"), "不是有效的email",
									"onError");
						} else {
							checkRegEmail()
						}
					}
				}
			});

	$("#username").bind(
			{
				focus : function() {
					siteState($(this).attr("id"),
							"请输入您的用户名,由4-20个组成，不能有包含中文、小数点,空格,_", "onFocus");
				},
				blur : function() {
					if ($(this).val() == '') {
						siteState($(this).attr("id"), "用户名不能为空!", "onError");
					} else {
						if ($(this).val().length < 4) {
							siteState($(this).attr("id"), "用户名不能小于4位!",
									"onError");
							return;
						}
						if(!isChn($(this).val())){
							siteState($(this).attr("id"), "用户名不能为中文!",
							"onError");
							return;
						}
						if(!isFh($(this).val())){
							siteState($(this).attr("id"), "用户名只能为数字跟字符!",
							"onError");
							return;
						}
						if ($(this).val().indexOf(" ") != -1
								|| $(this).val().indexOf("_") != -1|| $(this).val().indexOf(".") != -1) {
							siteState($(this).attr("id"), "用户名不能包括空格、小数点或者下划线!",
									"onError");
							return;
						}
						checkRegName()

					}
				}
			});
	$("#password").bind(
			{
				focus : function() {
					siteState($(this).attr("id"),
							"长度6-14位，区分大小写，建议数字、字母、下划线混合！", "onFocus");
				},
				blur : function() {
					if ($(this).val().length < 6) {
						siteState($(this).attr("id"), "长度应该在6-14位之间！",
								"onError");
					} else {
						siteState($(this).attr("id"), "填写正确", "onSuccess");
					}
				}
			});
	$("#password2").bind( {
		focus : function() {
			siteState($(this).attr("id"), "再输入一次！", "onFocus");
		},
		blur : function() {
			if ($(this).val() == '' || $(this).val().length < 6) {
				siteState($(this).attr("id"), "输入不能为空且不能小于6位！", "onError");
			} else {
				if ($.trim($(this).val()) != $.trim($("#password").val())) {
					siteState($(this).attr("id"), "两次输入不一样！", "onError");
				} else {
					siteState($(this).attr("id"), "填写正确", "onSuccess");
				}
			}
		}
	});
	$("#radio").bind( {
		click : function() {
			$("#entName").val($("#name").val()+"(个人)");
		}
	});
	
	$("#eName").bind( {
		focus : function() {
			siteState($(this).attr("id"), "请您输入贵公司名称，最多可输入20个字！", "onFocus");
		},
		blur : function() {
			if ($(this).val() == '') {
				siteState($(this).attr("id"), "输入不能为空！", "onError");
			} else {
				siteState($(this).attr("id"), "填写正确", "onSuccess");
			}
		}
	});
	$("#entName").bind( {
		focus : function() {
			siteState($(this).attr("id"), "请您输入贵公司名称，最多可输入20个字！", "onFocus");
		},
		blur : function() {
			if ($(this).val() == '') {
				siteState($(this).attr("id"), "输入不能为空！", "onError");
			} else {
				siteState($(this).attr("id"), "填写正确", "onSuccess");
			}
		}
	});
	$("#phone").bind( {
		focus : function() {
			siteState($(this).attr("id"), "请输入数字，如0571-12345678！", "onFocus");
		},
		blur : function() {
			if ($.trim($(this).val()) == '') {
				siteState($(this).attr("id"), "输入不能为空！", "onError");
			} else{
				siteState($(this).attr("id"), "填写正确", "onSuccess");
			}
		}
	});
	$("#cellphone").bind( {
		focus : function() {
			siteState($(this).attr("id"), "请输入数字，如13100000000！", "onFocus");
		},
		blur : function() {
			if ($(this).val() == '') {
				siteState($(this).attr("id"), "输入不能为空！", "onError");
			} else if (!validateCellPhone($(this).val())) {
				siteState($(this).attr("id"), "输入错误！", "onError");
			} else {
				siteState($(this).attr("id"), "填写正确", "onSuccess");
			}
		}
	});

	$("#name").bind( {
		focus : function() {
			siteState($(this).attr("id"), "请输入姓名，如张三！", "onFocus");
		},
		blur : function() {
			if ($(this).val() == '') {
				siteState($(this).attr("id"), "输入不能为空！", "onError");
			} else {
				siteState($(this).attr("id"), "填写正确", "onSuccess");
			}
		}
	});
$("#valcode").bind( {
		focus : function() {
			siteState($(this).attr("id"), "请输入图片中的数字！", "onFocus");
		},
		blur : function() {
			if ($(this).val() == '') {
				siteState($(this).attr("id"), "输入不能为空！", "onError");
			}
		}
	});
$("input[name='identity']").bind({
	click:function(){
		siteState("identity", "已选择", "onSuccess");
	}
});
	$("#subA").click(function(){
		if($("input[name='identity']:checked").val()==null){
			siteState("identity", "请选择会员类型！", "onError");
			return false;
		}
		if($(this).find(".onError").length>0)
			return false;
		else{
			$("#subA").attr("disabled","true")
		    register();
		}
			
	});
});
function siteState(obj, text, type) {
	$("#" + obj + "Tip").attr("class", type);
	$("#" + obj + "Tip").html(text);
}
function validateEmail(email) {
	var patrn = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,4}))$/;
	if (!patrn.exec(email)) {
		return 1;
	} else {
		return 2;
	}
}
function isChn(str){
    var reg = /[^\u4e00-\u9fa5]/;
    if(!reg.test(str)){
     return false;
    }
    return true;
}
function isFh(str){
	var reg = /^[a-zA-Z0-9]+$/;
    if(!reg.test(str)){
     return false;
    }
    return true;
}
function validatePhone(phone){
	var exp = /^[0-9]{4}\-[0-9]{8}$/;
	if(!exp.test(phone)){
		return false;
	}
	return true;
	
}
function validateCellPhone(cellphone){
	var exp=/^[0-9]{11,}$/;
	if(!exp.test(cellphone)){
		return false;
	}
	return true;
}

