//��֤ע����Ϣ�ĺϷ���
	function checkvalue(){
//ͨ����������ȡ��SESSION���ֵ
	var type=form1.registerType.value;
	
		var isCheck = true;
		

	if(form1.username.value=="")
	{
	document.getElementById("dlm").innerText="�û�������Ϊ�գ�";
	document.getElementById("dlm").style.color='red'
		form1.username.focus();
		isCheck = false;
	}else if(form1.username.value!=""){
		if(form1.username.value.length<4){
		document.getElementById("dlm").innerText="�û�������С��4λ��";
			form1.username.focus();
			isCheck = false;
		}else if(form1.username.value!="")
		document.getElementById("dlm").innerText="";
	
		
	}
	 if(form1.password.value=="")
	{
		document.getElementById("mm").innerText="���벻��Ϊ�գ�";
		document.getElementById("mm").style.color='red'
		form1.password.focus();
		isCheck = false;
	}else if(form1.password.value!=""){
		if(form1.password.value.length<6){
		document.getElementById("mm").innerText="���벻��С��6λ��";

			form1.password.focus();
			isCheck = false;
		}else if(form1.password.value!="")
		document.getElementById("mm").innerText="";
		
	}
	
	var password1=form1.password.value;
	var password2=form1.password2.value;
	if(password1!=password2){
		document.getElementById("rmm").innerText="������������벻һ����";
		document.getElementById("rmm").style.color='red'
	}else{
		document.getElementById("rmm").innerText="";
	}
	
	 if(form1.email.value=="")
	{
		document.getElementById("eml").innerText="�ʼ�����Ϊ�գ�";
		document.getElementById("eml").style.color='red'
		form1.email.focus();
		isCheck = false;
	}else if(form1.email.value!=""){
		if(( form1.email.value.length<5 )||(form1.email.value.indexOf("@")==-1)||(form1.email.value.indexOf(".")==-1 )){
		document.getElementById("eml").innerText="����E-MAIL��ʽ����ȷ��";

			form1.password.focus();
			isCheck = false;
		}else if(form1.email.value!="")
		document.getElementById("eml").innerText="";
		
	}
	if(type==2){
	 if(form1.name.value=="")
	{
		document.getElementById("gsmc").innerText="��˾���Ʋ���Ϊ�գ�";
		document.getElementById("gsmc").style.color='red'
		form1.name.focus();
		isCheck = false;
	}else if(form1.name.value!="")
		document.getElementById("gsmc").innerText="";
		
		 if(form1.legalpre.value=="")
	{
		document.getElementById("fr").innerText="���˲���Ϊ�գ�";
		document.getElementById("fr").style.color='red'
		form1.legalpre.focus();
		isCheck = false;
	}else if(form1.legalpre.value!="")
		document.getElementById("fr").innerText="";
}
	 if(form1.phone.value=="")
	{
		document.getElementById("dh").innerText="�绰���벻��Ϊ�գ�";
		document.getElementById("dh").style.color='red'
		form1.phone.focus();
		isCheck = false;
	}else if(form1.phone.value!="")
		document.getElementById("dh").innerText="";
	
		 if(form1.vericode.value=="")
	{
		document.getElementById("yzm").innerText="��֤�벻��Ϊ�գ�";
		document.getElementById("yzm").style.color='red'
		form1.vericode.focus();
		isCheck = false;
	}else if(form1.vericode.value!="")
		document.getElementById("yzm").innerText="";
	
	if ( !isCheck )
		return false;
	}

