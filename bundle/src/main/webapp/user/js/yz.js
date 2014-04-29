//验证注册信息的合法性
	function checkvalue(){
//通过隐藏域来取得SESSION里的值
	var type=form1.registerType.value;
	
		var isCheck = true;
		

	if(form1.username.value=="")
	{
	document.getElementById("dlm").innerText="用户名不能为空！";
	document.getElementById("dlm").style.color='red'
		form1.username.focus();
		isCheck = false;
	}else if(form1.username.value!=""){
		if(form1.username.value.length<4){
		document.getElementById("dlm").innerText="用户名不能小于4位！";
			form1.username.focus();
			isCheck = false;
		}else if(form1.username.value!="")
		document.getElementById("dlm").innerText="";
	
		
	}
	 if(form1.password.value=="")
	{
		document.getElementById("mm").innerText="密码不能为空！";
		document.getElementById("mm").style.color='red'
		form1.password.focus();
		isCheck = false;
	}else if(form1.password.value!=""){
		if(form1.password.value.length<6){
		document.getElementById("mm").innerText="密码不能小于6位！";

			form1.password.focus();
			isCheck = false;
		}else if(form1.password.value!="")
		document.getElementById("mm").innerText="";
		
	}
	
	var password1=form1.password.value;
	var password2=form1.password2.value;
	if(password1!=password2){
		document.getElementById("rmm").innerText="两次输入的密码不一样！";
		document.getElementById("rmm").style.color='red'
	}else{
		document.getElementById("rmm").innerText="";
	}
	
	 if(form1.email.value=="")
	{
		document.getElementById("eml").innerText="邮件不能为空！";
		document.getElementById("eml").style.color='red'
		form1.email.focus();
		isCheck = false;
	}else if(form1.email.value!=""){
		if(( form1.email.value.length<5 )||(form1.email.value.indexOf("@")==-1)||(form1.email.value.indexOf(".")==-1 )){
		document.getElementById("eml").innerText="您的E-MAIL格式不正确！";

			form1.password.focus();
			isCheck = false;
		}else if(form1.email.value!="")
		document.getElementById("eml").innerText="";
		
	}
	if(type==2){
	 if(form1.name.value=="")
	{
		document.getElementById("gsmc").innerText="公司名称不能为空！";
		document.getElementById("gsmc").style.color='red'
		form1.name.focus();
		isCheck = false;
	}else if(form1.name.value!="")
		document.getElementById("gsmc").innerText="";
		
		 if(form1.legalpre.value=="")
	{
		document.getElementById("fr").innerText="法人不能为空！";
		document.getElementById("fr").style.color='red'
		form1.legalpre.focus();
		isCheck = false;
	}else if(form1.legalpre.value!="")
		document.getElementById("fr").innerText="";
}
	 if(form1.phone.value=="")
	{
		document.getElementById("dh").innerText="电话号码不能为空！";
		document.getElementById("dh").style.color='red'
		form1.phone.focus();
		isCheck = false;
	}else if(form1.phone.value!="")
		document.getElementById("dh").innerText="";
	
		 if(form1.vericode.value=="")
	{
		document.getElementById("yzm").innerText="验证码不能为空！";
		document.getElementById("yzm").style.color='red'
		form1.vericode.focus();
		isCheck = false;
	}else if(form1.vericode.value!="")
		document.getElementById("yzm").innerText="";
	
	if ( !isCheck )
		return false;
	}

