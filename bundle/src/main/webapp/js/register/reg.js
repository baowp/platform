	$(function(){
		var item = $(":radio:checked");
		var len=item.length;
		if(len>0){
			tr_display(item.val())
		}
		if($("#phoneArea").val()==""){
			
		}else{
			$("#phoneAreaLabel").html("");
		}
		if($("#phoneNumber").val()==""){
		}else{
			$("#phoneNumberLabel").html("");
		}
		if($("#loginid").val()==""){
			$("#loginSpan").html("由4-20个字符组成");
		}else{
			$("#loginSpan").html("");
		}
		if($("#password").val()==""){
			$("#passwordSpan").html("由4-20个字符组成");
		}else{
			$("#passwordSpan").html("");
		}
		$("#loginid").focus(function(){
			if($("#loginid").val()=="")
				$("#loginSpan").html("");
		});
		$("#loginid").blur(function(){
			if($("#loginid").val()=="")
			{$("#loginSpan").html("由4-20个字符组成");}else{
				if(!isChn($("#loginid").val())){
					$("#loginIdIcon").html("<font color='red'>用户名不能为中文</font>");
				}else if(checkdata($("#loginid").val())){
					$("#loginIdIcon").html("<font color='red'>用户名不能有空格</font>");
				}else if(!checknum($("#loginid").val())){
					$("#loginIdIcon").html("<font color='red'>用户名只能是字母与数字组合</font>");
				}else{
				$.getJSON("/user/userValidate?randomNumber="+Math.random(), {
					username:$("#loginid").val()
				}, function(result) {
					if(result.affectRows==-1){
						$("#loginIdIcon").html("<font color='red'>用户名已经存在</font>");
						
					}else{
						$("#loginIdIcon").html("<font color='green'>可以注册</font>");
						
					}
				});
				}
			}
		});
		$("#password").focus(function(){
			if($("#password").val()=="")
				$("#passwordSpan").html("");
		});
		$("#password").blur(function(){
			if($("#password").val().length<4){
				$("#passwordIcon").html("<font color='red'>输入错误，密码由4-20字符组成</font>")
			}else{
				$("#passwordIcon").html("")
			}
			if($("#password").val()=="")
			{$("#passwordSpan").html("由4-20个字符组成");}
		});
		
		$("#phoneArea").focus(function(){
			if($("#phoneArea").val()=="")
				$("#phoneAreaSpan").html("");
		});
		$("#phoneArea").blur(function(){
			if($("#phoneArea").val()=="")
			{$("#phoneAreaSpan").html("区号");}
		});
		$("#phoneNumber").focus(function(){
			if($("#phoneNumber").val()=="")
				$("#phoneNumberSpan").html("");
		});
		$("#phoneNumber").blur(function(){
			if($("#phoneNumber").val()=="")
			{$("#phoneNumberSpan").html("电话号码");}
		});
		$("#email").blur(function(){
			if($("#email").val()==''){
				$("#emailIcon").html("<font color='red'>email不能为空</font>");
			}else if(!isEmail($("#email").val())){
				$("#emailIcon").html("<font color='red'>email格式不正确</font>");
			}else{
			$.getJSON("/user/emailValidate?randomNumber="+Math.random(), {
				email:$("#email").val()
			}, function(result) {
				if(result.affectRows==-1){
					$("#emailIcon").html("<font color='red'>email已经存在</font>");
				}else{
					$("#emailIcon").html("<font color='green'>可以使用</font>");
				}
			});}
		});
		
	});
	function isChn(str){
	      var reg = /[^\u4e00-\u9fa5]/;
	      if(!reg.test(str)){
	       return false;
	      }
	      return true;
	}
	function tr_display(type){
		// business_info.className = 'notetrue';
		// business_info.innerHTML =
		// '为了给您提供匹配的产品信息，请填写贵公司主营的产品（或服务）关键字。<br>如有多个，请用逗号分隔。如：布料，拉链';
		if(type == 'buyer'){
			$("#supplyblock1").hide();
			$("#supplyblock2").hide();
			$("#supplyblock3").hide();
			$("#eName").val($("#first_name").val()+'(个人)');
			$("#phoneArea").val("");
			$("#phoneNumber").val("");
			$("#cellphone").val("");
		}else{
			$("#supplyblock1").show();
			$("#supplyblock2").show();
			$("#supplyblock3").show();
		}

	}
	//验证用户名是否存在空格
	function checkdata(obj) 
	{ 
	if(obj.indexOf(" ")!=-1){
	 return true; 
	}
	return false;
	}

	// 验证email
	function isEmail(strEmail) {
		if (strEmail.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) != -1)
			return true;
		else
			return false;
	}
	function refreshCode()
	{
	    document.getElementById("verifyPic").src=document.getElementById("verifyPic").src+"?"+Math.random();
	}
	//只能为数字加字母
	function checknum(value) {
        var Regx = /^[A-Za-z0-9]*$/;
        if (Regx.test(value)) {
            return true;
        }
        else {
            return false;
        }
    }
	function checkForm(){
		var isTrue=true;
		if($("#loginid").val()==""){
			$("#loginIdIcon").html("<font color='red'>用户名不能为空</font>");
			isTrue= false;
		}else if(!checknum($("#loginid").val())){
			$("#loginIdIcon").html("<font color='red'>用户名只能是字母与数字组合</font>");
			isTrue= false;
		}else if(!isChn($("#loginid").val())){
			$("#loginIdIcon").html("<font color='red'>用户名不能为中文</font>");
			isTrue= false;
		}else if($("#loginid").val().length<4){
				$("#loginIdIcon").html("<font color='red'>用户名由4-20个字符组成</font>");
				isTrue= false;
			}else if(checkdata($("#loginid").val())){
				$("#loginIdIcon").html("<font color='red'>用户名不能有空格</font>");
				isTrue= false;
			}else{
				$("#loginIdIcon").html("");
				isTrue= true;
			}
		if($("#password").val()==""){
			$("#passwordIcon").html("<font color='red'>密码不能为空</font>");
			isTrue= false;
		}else if($("#password").val().length<4){
				$("#passwordIcon").html("<font color='red'>密码由4-20个字符组成</font>");
				isTrue= false;
			}else{
				$("#passwordIcon").html("");
				isTrue= true;
			}
		
		if($("#email").val()==''){
			$("#emailIcon").html("<font color='red'>email不能为空</font>");
			isTrue= false;
		}else if(!isEmail($("#email").val())){
			$("#emailIcon").html("<font color='red'>email格式不正确</font>");
			isTrue= false;
		}else{
			$("#emailIcon").html("");
			isTrue= true;
		}
		if($("#first_name").val()==''){
			$("#firstNameIcon").html("<font color='red'>姓名不能为空</font>");
			isTrue= false;
		}else{
			$("#firstNameIcon").html("");
			isTrue= true;
		}
		var item = $(":radio:checked");
		var len=item.length;
		if(len>0){
			if($("#eName").val()==''){
				$("#companyIcon").html("<font color='red'>公司名称不能为空</font>");
				isTrue= false;
			}else{
				$("#companyIcon").html("");
				isTrue= true;
			}
			if($("#phoneNumber").val()==''){
				$("#phoneIcon").html("<font color='red'>电话不能为空</font>");
				isTrue= false;
			}else{
				$("#phoneIcon").html("");
				isTrue= true;
			}
		}else{
			$("#identityIcon").html("<font color='red'>没有选择会员类型</font>");
			isTrue= false;
		}
		if($("#valcode").val()==''){
			$("#valicodeIcon").html("<font color='red'>验证码不能为空</font>");
			isTrue= false;
		}else{
			$("#valicodeIcon").html("");
			isTrue= true;
		}
		
		return isTrue;
		}
