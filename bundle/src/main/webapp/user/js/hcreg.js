	var rightIcon= "http://my.b2b.hc360.com/my/images/reg/icon_19x19_1.gif";

	/////////////////////////////////////////////////////////////
	//                Setup Class Names
	/////////////////////////////////////////////////////////////
	//default classes for infobox
	var validatedInfo="��д��ȷ��";
	/////////////////////////////////////////////////////////////
	//                Initialize Form
	/////////////////////////////////////////////////////////////
	document.onkeydown=function(evnt){
		if(isIE()&&window.event.keyCode==13){
			document.getElementById("submit").focus();
		}
	}


	/////////////////////////////////////////////////////////////
	//                Base Functions
	/////////////////////////////////////////////////////////////
	function isIE() {
    	if(document.all) return true;
    	return false;
	}

	function getAttrName(str){
    	var s=str.split("=");
    	return s[0];
	}
	function getAttrValue(str){
    	var s=str.split("=");
    	return s[1];
	}

	function getMailServer(str){
    	//be sure str is a correct email address
    	str = str.trim();
    	return str.substr(str.indexOf("@")+1);
	}
	String.prototype.trim = function()
	{
    	return this.replace(/(^\s*)|(\s*$)/g, "");
	}
	/////////////////////////////////////////////////////////////
	//                UI Functions
	/////////////////////////////////////////////////////////////

/**
	function lostFocus(evnt){  
		var obj;
		if (isIE()) {
			obj = event.srcElement;
		}else {
			obj = evnt.target;
		}
		if(obj.type=='text'){
			//ʧȥ����ʱɾ���ı����еĿո�
			obj.value=obj.value.trim();
		}
		showInfo(obj,-1);
		//alert("obj.name"+obj.name);
		if(obj.name=="SelProvince" && obj.value != ''){
			clean_check("city_info");
		}else {
			//info_check("city_info_check");
		}
		errorCode = validateValue(obj);
		if((obj.id == "salekeyword"|| obj.id =="buykeyword") && (errorCode == 5 )){
			document.getElementById("business_info").style.display='none';
			 document.getElementById("showInputInfo").style.display='none';
			return;
		}
		
		if(obj.name=="confirm_question_input"){
		
				if(form.confirm_question_input.value !="" && form.confirm_question.value=="7"){
					document.getElementById("confirm_question_info_check").innerHTML = "<strong>������ʾ����</strong> <span class=\"red\">* </span>";
						clean_check("confirm_question_info");  
						document.getElementById("confirm_question_info").className=infoboxOkClass;
						document.getElementById("confirm_question_info").innerHTML  = "��д��ȷ";			
						document.getElementById("confirm_question_info_check").innerHTML = "&nbsp;<img src="+rightIcon+" width=\"19\" height=\"16\" align=\"absmiddle\"> " + document.getElementById("confirm_question_info_check").innerHTML;
				}else {
					   
						document.getElementById("confirm_question_info").innerHTML  = "";
						var start =  document.getElementById("confirm_question_info_check").innerHTML.indexOf('>');
						var end = document.getElementById("confirm_question_info_check").innerHTML.length;
						document.getElementById("confirm_question_info_check").innerHTML = "<strong>������ʾ����</strong> <span class=\"red\">* </span>";
				}
				return;
		}
		
		
		//�жϵ绰����ĺϷ���CountryCode CityCode phone_number  ext_phone_number
		if(obj.id == 'ext_phone_number' || obj.id == 'CountryCode' || obj.id == 'CityCode' || obj.id == 'phone_number'){
			
			extCode=validateValue(document.getElementById('ext_phone_number'));
			countryCode=validateValue(document.getElementById('CountryCode'));
			cityCode=validateValue(document.getElementById('CityCode'));
			phoneCode=validateValue(document.getElementById('phone_number'));
			if(obj.id=="ext_phone_number" && errorCode<0){
				obj=document.getElementById('phone_number');
				
			}
			if(countryCode!=0){
				 errorCode=countryCode;
				 obj=document.getElementById('CountryCode');
			}else if(cityCode!=0){
				errorCode=cityCode;
				 obj=document.getElementById('CityCode');
			}else if(phoneCode!=0){
				
				 errorCode=phoneCode;
				 
				 obj=document.getElementById('phone_number');
			}else if(extCode>0){
				errorCode=extCode;
				 obj=document.getElementById('ext_phone_number');
			}else	if(countryCode==0 && cityCode==0 && phoneCode==0 && extCode<=0 ){
				errorCode=0;
			}
			
				
			}
			
			
		//�жϴ������ĺϷ���FaxCountryCode FaxCityCode fax_number  ext_fax_number
		if(obj.id == 'ext_fax_number' || obj.id == 'fax_number' || obj.id == 'FaxCityCode' || obj.id == 'FaxCountryCode'){
			extCode=validateValue(document.getElementById('ext_fax_number'));
			faxCode=validateValue(document.getElementById('fax_number'));
			cityCode=validateValue(document.getElementById('FaxCityCode'));
			countryCode=validateValue(document.getElementById('FaxCountryCode')); 
			
			if(obj.id=="ext_fax_number" && errorCode<0){
				obj=document.getElementById('fax_number');
			}
			//alert(extCode+":"+faxCode+":"+cityCode+":"+countryCode+":");
			if(countryCode>0){
				 errorCode=countryCode;
				 obj=document.getElementById('FaxCountryCode');
			}else if(cityCode>0){
				errorCode=cityCode;
				 obj=document.getElementById('FaxCityCode');
			}else if(faxCode>0){
				 errorCode=faxCode;
				 obj=document.getElementById('fax_number');
			}else if(extCode>0){
				errorCode=extCode;
				 obj=document.getElementById('ext_fax_number');
			}else	if(countryCode==0 && cityCode==0 && faxCode==0   ){
				errorCode=0;
			}else{
				document.getElementById('fax_info').style.display='none';
				return;
			}
			
			}
			
		//��������Ϊ��ʱ���²���
		if(obj.value == ''){
			//alert("obj.value=" + obj.value + "obj.id=" + obj.id + "eval(obj.id).c=" + eval(obj.id).c);
			if(obj.id && eval(obj.id).c && document.getElementById(eval(obj.id).c)){
				if(document.getElementById(eval(obj.id).c).innerHTML.indexOf("IMG") > 0 || document.getElementById(eval(obj.id).c).innerHTML.indexOf("img") > 0 ){
					var start =  document.getElementById(eval(obj.id).c).innerHTML.indexOf('>');
					var end = document.getElementById(eval(obj.id).c).innerHTML.length;
					document.getElementById(eval(obj.id).c).innerHTML = document.getElementById(eval(obj.id).c).innerHTML.substring(start +1,end);
				}
			}
			
			if(obj.id && eval(obj.id).c && document.getElementById(eval(obj.id).c)){
				var infobox = getInfobox(obj);
				var errorCode = getInitStatus(obj);
				//alert("infobox===" +infobox + "isRequired(obj)=" + isRequired(obj));
				//alert("form====" + form.isSubmit.value);
				if(form.isSubmit.value =="1") {
					if(infobox && !isRequired(obj)){
						//alert("33333=" + infobox.className);
						if(infobox.className == infoboxErrorClass){
							infobox.className	= infoboxErrorClass;
							infobox.innerHTML	= getErrorMsg(obj,0);	
							//infobox.innerHTML	= "";		
						}
					}
				}else {
					infobox.className	= "note";
					infobox.className = inputNormalClass;
					//infobox.innerHTML = (eval(obj.id).e)[0];	
					infobox.innerHTML	= "";
				}
			}
			return;
		}
		
		
		if(errorCode == 0){
			
			if(obj.id == 'loginid'){			
				checkNick();
				return;
			}
			
			if(obj.id == 'email'){
				checkEmail();
				return;
			}
			
			if(obj.id == 'password'){
				initStatus(document.getElementById('confirm_password'),true);
				document.getElementById('confirm_password').value="";
				
				document.getElementById('confirm_password_info').innerHTML='';
			}
			if(obj.id){
				if(eval(obj.id).c && document.getElementById(eval(obj.id).c)){
					if(document.getElementById(eval(obj.id).c).innerHTML.indexOf("img") < 0 
						&& document.getElementById(eval(obj.id).c).innerHTML.indexOf("IMG") < 0){ 
						document.getElementById(eval(obj.id).c).innerHTML = "&nbsp;<img src="+rightIcon+" width=\"19\" height=\"16\" align=\"absmiddle\"> " + document.getElementById(eval(obj.id).c).innerHTML;
					}
					document.getElementById(eval(obj.id).i).className=infoboxOkClass;
					document.getElementById(eval(obj.id).i).innerHTML = '��д��ȷ';
				}
			}
		}
		if(errorCode >= 1){
			
			if(obj.id){
			//alert(eval(obj.id).i);
			if(eval(obj.id).i && document.getElementById(eval(obj.id).i)) 
			document.getElementById(eval(obj.id).i).className = infoboxErrorClass;
			
			document.getElementById(eval(obj.id).i).innerHTML = (eval(obj.id).e)[errorCode];
			}
			//showStatus(obj,"Error");
			if(document.getElementById(eval(obj.id).c).innerHTML.indexOf("IMG") >= 0 || document.getElementById(eval(obj.id).c).innerHTML.indexOf("img") >= 0){
			var start =  document.getElementById(eval(obj.id).c).innerHTML.indexOf('>');
			var end = document.getElementById(eval(obj.id).c).innerHTML.length;
			document.getElementById(eval(obj.id).c).innerHTML = document.getElementById(eval(obj.id).c).innerHTML.substring(start +1,end);
			}
			
			
		}
		if(errorCode < 0 && eval(obj.id)){
			
			if(document.getElementById(eval(obj.id).c).innerHTML.indexOf("IMG") >= 0 || document.getElementById(eval(obj.id).c).innerHTML.indexOf("img") >= 0){
			var start =  document.getElementById(eval(obj.id).c).innerHTML.indexOf('>');
			var end = document.getElementById(eval(obj.id).c).innerHTML.length;
			document.getElementById(eval(obj.id).c).innerHTML = document.getElementById(eval(obj.id).c).innerHTML.substring(start +1,end);
			}
			
			if(obj.id){
			if(eval(obj.id).i && document.getElementById(eval(obj.id).i) &&obj.id!="validate_code") {
			document.getElementById(eval(obj.id).i).className = infoboxErrorClass;
			}else if(eval(obj.id).i && document.getElementById(eval(obj.id).i) &&obj.id=="validate_code") {
				document.getElementById(eval(obj.id).i).className = infoboxWarningClass;
			}
			document.getElementById(eval(obj.id).i).innerHTML = (eval(obj.id).e)[0];
			}
		}
		
		
	}

*/



/////////////////////////////////////////////////////////////
//                Validator Functions
/////////////////////////////////////////////////////////////


var checkusername=true
function validateAll(){
	pass = true;
	var focusobj="";
	var focuscount=0;
	//���Ա�͹�˾��������
	document.getElementById("genre_title").style.display="none";
		if (frindname==""){
			//�û���
				errorCode = validateUsername(document.getElementById("loginid"));
				if(errorCode == 0){
					
					if (checkusername){
						document.getElementById("loginid_info").className="promptMsg3";
						document.getElementById("loginid_title").style.display="none";
					}else{
						pass=false;
					}
					
				}else{
					document.getElementById("loginid_info").innerHTML="������д���󡣻�Ա��¼��ֻ����4-15��Ӣ����ĸ���������(��֧�����ġ������ִ�Сд)��";
					info_check_clean("loginid_info_check");
					document.getElementById("loginid_info").className="promptMsg2";
					pass=false;
					focuscount=focuscount+1;
					if (focuscount==1){
						focusobj="loginid";
					}
				}
			//����
				errorCode = validatePassword(document.getElementById("password"));
				if (errorCode==0){
					document.getElementById("password_info").innerHTML=validatedInfo;
					info_check("password_info_check");
					document.getElementById("password_info").className="promptMsg3";
					document.getElementById("password_title").style.display="none";
				}else if (errorCode==2){
					document.getElementById("password_info").innerHTML="Ϊ��ȫ��������벻�����Ա��¼����ͬ���������������룡";
					info_check_clean("password_info_check");
					document.getElementById("password_info").className="promptMsg2";
					pass=false;
					focuscount=focuscount+1;
					if (focuscount==1){
						focusobj="password";
					}
				}else if (errorCode==3){
					document.getElementById("password_info").innerHTML="Ϊ��ȫ��������벻�������������֣��������������룡";
					info_check_clean("password_info_check");
					document.getElementById("password_info").className="promptMsg2";
					pass=false;
					focuscount=focuscount+1;
					if (focuscount==1){
						focusobj="password";
					}
				}else if (errorCode==4 || errorCode==6){
					document.getElementById("password_info").innerHTML="�����������������������6-20λӢ����ĸ��������ɵģ����ִ�Сд!";
					info_check_clean("password_info_check");
					document.getElementById("password_info").className="promptMsg2";
					pass=false;
					focuscount=focuscount+1;
					if (focuscount==1){
						focusobj="password";
					}
				}
			
			//��������
				errorCode = validateSafePassword(document.getElementById("confirm_password"));
				if (errorCode==0){
					document.getElementById("confirm_password_info").innerHTML=validatedInfo;
					info_check("confirm_password_info_check");
					document.getElementById("confirm_password_info").className="promptMsg3";
					document.getElementById("confirm_password_title").style.display="none";
				}else if (errorCode==1){	
					document.getElementById("confirm_password_info").innerHTML="�����������������������6-20λӢ����ĸ��������ɵģ����ִ�Сд!";
					info_check_clean("confirm_password_info_check");
					document.getElementById("confirm_password_info").className="promptMsg2";
					document.getElementById("password_title").style.display="";
					pass=false;
					focuscount=focuscount+1;
					if (focuscount==1){
						focusobj="confirm_password";
					}
				}else if (errorCode==2){
					document.getElementById("confirm_password_info").innerHTML="������������벻��ͬ��������������������롣";
					info_check_clean("confirm_password_info_check");
					document.getElementById("confirm_password_info").className="promptMsg2";
					document.getElementById("password_title").style.display="";
					pass=false;
					focuscount=focuscount+1;
					if (focuscount==1){
						focusobj="confirm_password";
					}
				}else if (errorCode==3){
					document.getElementById("confirm_password_info").innerHTML="Ϊ��ȫ��������벻�����Ա��¼����ͬ���������������룡";
					info_check_clean("confirm_password_info_check");
					document.getElementById("confirm_password_info").className="promptMsg2";
					document.getElementById("password_title").style.display="";
					pass=false;
					focuscount=focuscount+1;
					if (focuscount==1){
						focusobj="confirm_password";
					}
				}
			}
			//��ʵ����
				errorCode = validateFirstName(document.getElementById("first_name"));
				if (errorCode==0){
					document.getElementById("first_name_info").innerHTML=validatedInfo;
					info_check("first_name_info_check");
					document.getElementById("first_name_info").className="promptMsg3";
					document.getElementById("first_name_title").style.display="none";
				}else if (errorCode==1){
					document.getElementById("first_name_info").innerHTML="����������1-32���֡�";
					info_check_clean("first_name_info_check");
					document.getElementById("first_name_info").className="promptMsg2";
					pass=false;
					focuscount=focuscount+1;
					if (focuscount==1){
						focusobj="first_name";
					}
				}else if (errorCode==-1){
					document.getElementById("first_name_info").innerHTML="����Ϊ�����";
					info_check_clean("first_name_info_check");
					document.getElementById("first_name_info").className="promptMsg2";
					pass=false;
					focuscount=focuscount+1;
					if (focuscount==1){
						focusobj="first_name";
					}
				}else if (errorCode==2){
					document.getElementById("first_name_info").innerHTML="�������зǷ��ַ���";
					info_check_clean("first_name_info_check");
					document.getElementById("first_name_info").className="promptMsg2";
					pass=false;
					focuscount=focuscount+1;
					if (focuscount==1){
						focusobj="first_name";
					}
				}

			//ְλ
				errorCode = validateJobTitle(document.getElementById("job_title"));
				if (errorCode==0){
					document.getElementById("job_title_info").innerHTML=validatedInfo;
					info_check("job_title_info_check");
					document.getElementById("job_title_info").className="promptMsg3";
					document.getElementById("job_title_title").style.display="none";
				}else if (errorCode==1){
					document.getElementById("job_title_info").innerHTML="ְλ���Ƴ�����󳤶�16���֡�";
					info_check_clean("job_title_info_check");
					document.getElementById("job_title_info").className="promptMsg2";
					pass=false;
					focuscount=focuscount+1;
					if (focuscount==1){
						focusobj="job_title";
					}
				}else if (errorCode==-1){
					document.getElementById("job_title_info").innerHTML="����Ϊ�����";
					info_check_clean("job_title_info_check");
					document.getElementById("job_title_info").className="promptMsg2";
					pass=false;
					focuscount=focuscount+1;
					if (focuscount==1){
						focusobj="job_title";
					}
				}else if (errorCode==2){
					document.getElementById("job_title_info").innerHTML="ְλ���зǷ��ַ���";
					info_check_clean("job_title_info_check");
					document.getElementById("job_title_info").className="promptMsg2";
					pass=false;
					focuscount=focuscount+1;
					if (focuscount==1){
						focusobj="job_title";
					}
				}
			//����
				errorCode = validateEmail(document.getElementById("email"));
				if (errorCode==0){
					document.getElementById("email_info").innerHTML=validatedInfo;
					info_check("email_info_check");
					document.getElementById("email_info").className="promptMsg3";
					document.getElementById("email_title").style.display="none";
				}else if (errorCode==1){
					document.getElementById("email_info").innerHTML="�����ʼ���ʽ����ȷ��������������ȷ�ĵ��������ʽ��<a href='http://mail.163.com/' target='_blank'>ע����������</a>��<a href='http://mail.sina.com.cn/cgi-bin/register/regMember1.cgi' target='_blank'>ע����������</a>��";
					info_check_clean("email_info_check");
					document.getElementById("email_info").className="promptMsg2";
					pass=false;
					focuscount=focuscount+1;
					if (focuscount==1){
						focusobj="email";
					}
				}

			

			//�绰���Һ���
				errorCode1 = validatePhoneArea(document.getElementById("CountryCode"));
				if (errorCode1==0){
					document.getElementById("phone_info").innerHTML=validatedInfo;
					info_check("phone_info_check");
					document.getElementById("phone_info").className="promptMsg3";
				}else if (errorCode1==-1){
					document.getElementById("phone_info").innerHTML="����Ϊ�����";
					info_check_clean("phone_info_check");
					document.getElementById("phone_info").className="promptMsg2";
					pass=false;
					focuscount=focuscount+1;
					if (focuscount==1){
						focusobj="CountryCode";
					}
				}else if (errorCode1==1){
					document.getElementById("phone_info").innerHTML="�绰����ֻ�������֡�";
					info_check_clean("phone_info_check");
					document.getElementById("phone_info").className="promptMsg2";
					pass=false;
					focuscount=focuscount+1;
					if (focuscount==1){
						focusobj="CountryCode";
					}
				}

			//�绰����
				if (document.getElementById("CityCode").value.trim()=="����"){
					document.getElementById("CityCode").value="";
				}
				errorCode2 = validatePhoneArea(document.getElementById("CityCode"));
				if (errorCode2==0){
					document.getElementById("phone_info").innerHTML=validatedInfo;
					info_check("phone_info_check");
					document.getElementById("phone_info").className="promptMsg3";
				}else if (errorCode2==-1){
					document.getElementById("phone_info").innerHTML="����Ϊ�����";
					info_check_clean("phone_info_check");
					document.getElementById("phone_info").className="promptMsg2";
					pass=false;
					focuscount=focuscount+1;
					if (focuscount==1){
						focusobj="CityCode";
					}
				}else if (errorCode2==1){
					document.getElementById("phone_info").innerHTML="�绰����ֻ�������֡�";
					info_check_clean("phone_info_check");
					document.getElementById("phone_info").className="promptMsg2";
					pass=false;
					focuscount=focuscount+1;
					if (focuscount==1){
						focusobj="CityCode";
					}
				}
				
				if (document.getElementById("CityCode").value.trim()==""){
					document.getElementById("CityCode").value="����";
				}

			//�绰����
				if (document.getElementById("phone_number").value.trim()=="�绰����"){
					document.getElementById("phone_number").value="";
				}
				errorCode3 = validatePhoneNumber(document.getElementById("phone_number"));
				if (errorCode3==0){
					document.getElementById("phone_info").innerHTML=validatedInfo;
					info_check("phone_info_check");
					document.getElementById("phone_info").className="promptMsg3";
				}else if (errorCode3==-1){
					document.getElementById("phone_info").innerHTML="����Ϊ�����";
					info_check_clean("phone_info_check");
					document.getElementById("phone_info").className="promptMsg2";
					pass=false;
					focuscount=focuscount+1;
					if (focuscount==1){
						focusobj="phone_number";
					}
				}else if (errorCode3==1){
					document.getElementById("phone_info").innerHTML="�绰����ֻ�������֡�";
					info_check_clean("phone_info_check");
					document.getElementById("phone_info").className="promptMsg2";
					pass=false;
					focuscount=focuscount+1;
					if (focuscount==1){
						focusobj="phone_number";
					}
				}else if (errorCode3==2){
					document.getElementById("phone_info").innerHTML="�绰��������й������ĵ绰����ֻ����7��8λ��";
					info_check_clean("phone_info_check");
					document.getElementById("phone_info").className="promptMsg2";
					pass=false;
					focuscount=focuscount+1;
					if (focuscount==1){
						focusobj="phone_number";
					}
				}
				
				if (document.getElementById("phone_number").value.trim()==""){
					document.getElementById("phone_number").value="�绰����";
				}

			//�绰�ֻ���
				if (document.getElementById("ext_phone_number").value.trim()=="�ֻ�(ѡ��)"){
					document.getElementById("ext_phone_number").value="";
				}
				errorCode4 = validateExtPhoneNumber(document.getElementById("ext_phone_number"));
				if (errorCode4==0){
				}else if (errorCode4==1){
					document.getElementById("phone_info").innerHTML="�ֻ���ֻ�������ֱ�ʾ��";
					info_check_clean("phone_info_check");
					document.getElementById("phone_info").className="promptMsg2";
					pass=false;
					focuscount=focuscount+1;
					if (focuscount==1){
						focusobj="ext_phone_number";
					}
				}
				
				if (document.getElementById("ext_phone_number").value.trim()==""){
					document.getElementById("ext_phone_number").value="�ֻ�(ѡ��)";
				}
				//����绰���붼��ȷ������
				if (errorCode1==0 && errorCode2==0 && errorCode3==0 && (errorCode4==0 || errorCode4==-1)){
					document.getElementById("phone_title").style.display="none";
				}
				

			//����绰����
				errorCode1 = validateFaxArea(document.getElementById("FaxCountryCode"));
				if (errorCode1==0){
					document.getElementById("fax_info").innerHTML=validatedInfo;
					info_check("fax_info_check");
					document.getElementById("fax_info").className="promptMsg3";
				}else if (errorCode1==-1){
					document.getElementById("fax_info").innerHTML="";
					info_check_clean("fax_info_check");
					document.getElementById("fax_info").className="";
				}else if (errorCode1==1){
					document.getElementById("fax_info").innerHTML="���ź͹��Һ���ֻ��ʹ�����֡�";
					info_check_clean("fax_info_check");
					document.getElementById("fax_info").className="promptMsg2";
					pass=false;
					focuscount=focuscount+1;
					if (focuscount==1){
						focusobj="FaxCountryCode";
					}
				}

			//�������뿪����绰����
				if (document.getElementById("FaxCityCode").value.trim()=="����"){
					document.getElementById("FaxCityCode").value="";
				}
				errorCode2 = validateFaxArea(document.getElementById("FaxCityCode"));
				if (errorCode2==0){
					document.getElementById("fax_info").innerHTML=validatedInfo;
					info_check("fax_info_check");
					document.getElementById("fax_info").className="promptMsg3";
				}else if (errorCode2==-1){
					document.getElementById("fax_info").innerHTML="";
					info_check_clean("fax_info_check");
					document.getElementById("fax_info").className="";
				}else if (errorCode2==1){
					document.getElementById("fax_info").innerHTML="���ź͹��Һ���ֻ��ʹ�����֡�";
					info_check_clean("fax_info_check");
					document.getElementById("fax_info").className="promptMsg2";
					pass=false;
					focuscount=focuscount+1;
					if (focuscount==1){
						focusobj="FaxCityCode";
					}
				}
				if (document.getElementById("FaxCityCode").value.trim()==""){
					document.getElementById("FaxCityCode").value="����";
				}
			//����绰����
				if (document.getElementById("fax_number").value.trim()=="�������"){
					document.getElementById("fax_number").value="";
				}
				errorCode3 = validateFaxNumber(document.getElementById("fax_number"));
				if (errorCode3==0){
					document.getElementById("fax_info").innerHTML=validatedInfo;
					info_check("fax_info_check");
					document.getElementById("fax_info").className="promptMsg3";
				}else if (errorCode3==1){
					document.getElementById("fax_info").innerHTML="�������ֻ��ʹ�����ֻ�\"/\",\"-\"��";
					info_check_clean("fax_info_check");
					document.getElementById("fax_info").className="promptMsg2";
					pass=false;
					focuscount=focuscount+1;
					if (focuscount==1){
						focusobj="fax_number";
					}
				}else if (errorCode3==2){
					document.getElementById("fax_info").innerHTML="�����������й������Ĵ������ֻ����7��8λ��";
					info_check_clean("fax_info_check");
					document.getElementById("fax_info").className="promptMsg2";
					pass=false;
					focuscount=focuscount+1;
					if (focuscount==1){
						focusobj="fax_number";
					}
				}
				if (document.getElementById("fax_number").value.trim()==""){
					document.getElementById("fax_number").value="�������";
				}

			//����绰�ֻ���
				if (document.getElementById("ext_fax_number").value.trim()=="�ֻ�(ѡ��)"){
					document.getElementById("ext_fax_number").value="";
				}
				errorCode4 = validateExtFaxNumber(document.getElementById("ext_fax_number"));
				if (errorCode4==0){
				}else if (errorCode4==1){
					document.getElementById("fax_info").innerHTML="�ֻ���ֻ�������ֱ�ʾ��";
					info_check_clean("fax_info_check");
					document.getElementById("fax_info").className="promptMsg2";
					pass=false;
					focuscount=focuscount+1;
					if (focuscount==1){
						focusobj="ext_fax_number";
					}
				}
				if (document.getElementById("ext_fax_number").value.trim()==""){
					document.getElementById("ext_fax_number").value="�ֻ�(ѡ��)";
				}
			//���������붼��ȷ������
				if ((errorCode1==0 || errorCode1==-1) && (errorCode2==0 || errorCode2==-1 ) && (errorCode3==0 || errorCode3==-1) && (errorCode4==0 || errorCode4==-1)){
					document.getElementById("fax_title").style.display="none";
				}	
			//�ֻ�
				errorCode = validateMobile(document.getElementById("mobile"));
				if (errorCode==0){
					document.getElementById("mobile_info").innerHTML=validatedInfo;
					info_check("mobile_info_check");
					document.getElementById("mobile_info").className="promptMsg3";
					document.getElementById("mobile_title").style.display="none";
				}else if (errorCode==-1){
					document.getElementById("mobile_info").innerHTML="";
					info_check_clean("mobile_info_check");
					document.getElementById("mobile_info").className="";
					document.getElementById("mobile_title").style.display="none";
				}else if (errorCode==1){
					document.getElementById("mobile_info").innerHTML="������11λ���İ���������";
					info_check_clean("mobile_info_check");
					document.getElementById("mobile_info").className="promptMsg2";
					pass=false;
					focuscount=focuscount+1;
					if (focuscount==1){
						focusobj="mobile";
					}	
				}else if (errorCode==2){
					document.getElementById("mobile_info").innerHTML="����д��ȷ���ֻ����롣";
					info_check_clean("mobile_info_check");
					document.getElementById("mobile_info").className="promptMsg2";
					pass=false;
					focuscount=focuscount+1;
					if (focuscount==1){
						focusobj="mobile";
					}
				}
			//�������뿪��˾����
				errorCode = validateCompany(document.getElementById("company"));
				if (errorCode==0){
					document.getElementById("company_info").innerHTML=validatedInfo;
					info_check("company_info_check");
					document.getElementById("company_info").className="promptMsg3";
					document.getElementById("company_title").style.display="none";
				}else if (errorCode==-1){
					document.getElementById("company_info").innerHTML="����Ϊ�����";
					info_check_clean("company_info_check");
					document.getElementById("company_info").className="promptMsg2";
					pass=false;
					focuscount=focuscount+1;
					if (focuscount==1){
						focusobj="company";
					}
				}else if (errorCode==1){
					document.getElementById("company_info").innerHTML="��˾���Ʋ��ܳ���50����";
					info_check_clean("company_info_check");
					document.getElementById("company_info").className="promptMsg2";
					pass=false;
					focuscount=focuscount+1;
					if (focuscount==1){
						focusobj="company";
					}
				}else if (errorCode==2){
					document.getElementById("company_info").innerHTML="��˾���ƺ��зǷ��ַ���";
					info_check_clean("company_info_check");
					document.getElementById("company_info").className="promptMsg2";
					pass=false;
					focuscount=focuscount+1;
					if (focuscount==1){
						focusobj="company";
					}
				}else if (errorCode==3 || errorCode==4){
					document.getElementById("company_info").innerHTML="<font color='#FF0000'>��˾���ƴ���</font><span class='STYLE4'>��˾���Ʋ������������ַ�����ȫʹ�����ֱ�ʾ��";
					info_check_clean("company_info_check");
					document.getElementById("company_info").className="promptMsg2";
					pass=false;
					focuscount=focuscount+1;
					if (focuscount==1){
						focusobj="company";
					}
				}		
				//��˾���ڵ�
				var selCountryTemp = document.getElementById("SelCountryLikeChina").value.trim();
				var notCNCityTemp = document.getElementById("SelCityOther").value;
				var selCountry = document.getElementById("SelCountry").value;
				if((selCountryTemp =='242' || selCountryTemp =='243' || selCountryTemp =='244') && notCNCityTemp == '') {
					//warning_check("city_info",'��ѡ��ʡ�ݻ��߳���');  
					//document.getElementById("city_info").focus(); 
					document.getElementById("city_info").innerHTML="����Ϊ�����";
					document.getElementById("city_info").className="promptMsg2";
					document.getElementById("city_info").style.display="block";
					info_check_clean("city_info_check");
					pass=false;
					focuscount=focuscount+1;
					if (focuscount==1){
						focusobj="SelCityOther";
					}
					
				}else if (document.getElementById("SelCity").value == '' && selCountry =='241'){
					document.getElementById("city_info").innerHTML="����Ϊ�����";
					document.getElementById("city_info").className="promptMsg2";
					document.getElementById("city_info").style.display="block";
					info_check_clean("city_info_check");
					pass=false;
					focuscount=focuscount+1;
					if (focuscount==1){
						focusobj="SelCity";
					}
					
				}else{
					document.getElementById("city_info").innerHTML="";
					info_check("city_info_check");
					document.getElementById("city_info").style.display="none"
					document.getElementById("city_title").style.display="none";
					
				}	
			//�������뿪��Ӫ��ַ
				errorCode = validateAddress(document.getElementById("address"));
				if (errorCode==0){
					document.getElementById("address_info").innerHTML=validatedInfo;
					info_check("address_info_check");
					document.getElementById("address_info").className="promptMsg3";
					document.getElementById("address_title").style.display="none";
				}else if (errorCode==-1){
					document.getElementById("address_info").innerHTML="����Ϊ�����";
					info_check_clean("address_info_check");
					document.getElementById("address_info").className="promptMsg2";
					pass=false;
					focuscount=focuscount+1;
					if (focuscount==1){
						focusobj="address";
					}
				}else if (errorCode==1){
					document.getElementById("address_info").innerHTML="��ϸ��ַ������";
					info_check_clean("address_info_check");
					document.getElementById("address_info").className="promptMsg2";
					pass=false;
					focuscount=focuscount+1;
					if (focuscount==1){
						focusobj="address";
					}
				}else if (errorCode==2){
					document.getElementById("address_info").innerHTML="��ϸ��ַ���зǷ��ַ���";
					info_check_clean("address_info_check");
					document.getElementById("address_info").className="promptMsg2";
					pass=false;
					focuscount=focuscount+1;
					if (focuscount==1){
						focusobj="address";
					}
				}

				if(document.getElementById("category").value == '' ){
					document.getElementById("category_info").innerHTML="��ѡ����Ӫ��ҵ��";  
					info_check_clean("category_info_check");
					document.getElementById("category_info").className="promptMsg2";
					pass = false;
					focuscount=focuscount+1;
							if (focuscount==1){
								focusobj="category";
							}
				}else{
					clean_check("category_info");
					document.getElementById("category_title").style.display="none";  
				}
				
				var keyselected ="";
				var business_roles=document.getElementsByName("business_role");
				for(i=0;i<business_roles.length;i++){
					if(business_roles[i].checked){
					keyselected = business_roles[i].value;
					break;
					}
				}

				if (keyselected==""){
					document.getElementById("business_info").innerHTML="����Ϊ�����";
					info_check_clean("business_info_check");
					document.getElementById("business_info").className="promptMsg2";
					pass=false;
					focuscount=focuscount+1;
					if (focuscount==1){
						focusobj="business_role";
					}
				}

				if(keyselected == 'buyer'){
					errorCode = validateKeyword(document.getElementById("buykeyword"));
					if (errorCode==0){
						document.getElementById("business_info").innerHTML=validatedInfo;
						info_check("business_info_check");
						document.getElementById("business_info").className="promptMsg3";
						document.getElementById("business_title").style.display="none";
					}else if (errorCode==-1){
						document.getElementById("business_info").innerHTML="��Ӫ��Ʒ����Ϊ�գ���������Ӫ��Ʒ��";
						info_check_clean("business_info_check");
						document.getElementById("business_info").className="promptMsg2";
						pass=false;
						focuscount=focuscount+1;
						if (focuscount==1){
							focusobj="buykeyword";
						}
					}else if (errorCode==1){
						document.getElementById("business_info").innerHTML="��Ӫ�ɹ�������";
						info_check_clean("business_info_check");
						document.getElementById("business_info").className="promptMsg2";
						pass=false;
						focuscount=focuscount+1;
						if (focuscount==1){
							focusobj="buykeyword";
						}
					}else if (errorCode==2){
						document.getElementById("business_info").innerHTML="��Ӫ�ɹ����зǷ��ַ���";
						info_check_clean("business_info_check");
						document.getElementById("business_info").className="promptMsg2";
						pass=false;
						focuscount=focuscount+1;
						if (focuscount==1){
							focusobj="buykeyword";
						}
					}
				}else if(keyselected == 'seller'){
					errorCode = validateKeyword(document.getElementById("salekeyword"));
					if (errorCode==0){
						document.getElementById("business_info").innerHTML=validatedInfo;
						info_check("business_info_check");
						document.getElementById("business_info").className="promptMsg3";
						document.getElementById("business_title").style.display="none";
					}else if (errorCode==-1){
						document.getElementById("business_info").innerHTML="��Ӫ��Ʒ����Ϊ�գ���������Ӫ��Ʒ��";
						info_check_clean("business_info_check");
						document.getElementById("business_info").className="promptMsg2";
						pass=false;
						focuscount=focuscount+1;
						if (focuscount==1){
							focusobj="salekeyword";
						}
					}else if (errorCode==1){
						document.getElementById("business_info").innerHTML="��Ӫ��Ʒ������";
						info_check_clean("business_info_check");
						document.getElementById("business_info").className="promptMsg2";
						pass=false;
						focuscount=focuscount+1;
						if (focuscount==1){
							focusobj="salekeyword";
						}
					}else if (errorCode==2){
						document.getElementById("business_info").innerHTML="��Ӫ��Ʒ���зǷ��ַ���";
						info_check_clean("business_info_check");
						document.getElementById("business_info").className="promptMsg2";
						pass=false;
						focuscount=focuscount+1;
						if (focuscount==1){
							focusobj="salekeyword";
						}
					}
				}else if(keyselected == 'both'){
					//�ж�����
					errorCode2 = validateKeyword(document.getElementById("salekeyword"));

					if (errorCode2==5){
						document.getElementById("business_info").innerHTML="��Ӫ��Ʒ����Ϊ�գ���������Ӫ��Ʒ��";
						info_check_clean("business_info_check");
						document.getElementById("business_info").className="promptMsg2";
						pass=false;
						focuscount=focuscount+1;
						if (focuscount==1){
							focusobj="buykeyword";
						}
					}else if (errorCode2==-1){
						document.getElementById("business_info").innerHTML="��Ӫ��Ʒ����Ϊ�գ���������Ӫ��Ʒ��";
						info_check_clean("business_info_check");
						document.getElementById("business_info").className="promptMsg2";
						pass=false;
						focuscount=focuscount+1;
						if (focuscount==1){
							focusobj="salekeyword";
						}
					}else if (errorCode2==1){
						document.getElementById("business_info").innerHTML="��Ӫ��Ʒ������";
						info_check_clean("business_info_check");
						document.getElementById("business_info").className="promptMsg2";
						pass=false;
						focuscount=focuscount+1;
						if (focuscount==1){
							focusobj="salekeyword";
						}
					}else if (errorCode2==2){
						document.getElementById("business_info").innerHTML="��Ӫ��Ʒ���зǷ��ַ���";
						info_check_clean("business_info_check");
						document.getElementById("business_info").className="promptMsg2";
						pass=false;
						focuscount=focuscount+1;
						if (focuscount==1){
							focusobj="salekeyword";
						}
					}
					
					//�ж���
					errorCode1 = validateKeyword(document.getElementById("buykeyword"));
					if (errorCode1==5){
						document.getElementById("business_info").innerHTML="��Ӫ��Ʒ����Ϊ�գ���������Ӫ��Ʒ��";
						info_check_clean("business_info_check");
						document.getElementById("business_info").className="promptMsg2";
						pass=false;
						focuscount=focuscount+1;
						if (focuscount==1){
							focusobj="salekeyword";
						}
					}else if (errorCode1==-1){
						document.getElementById("business_info").innerHTML="��Ӫ��Ʒ����Ϊ�գ���������Ӫ��Ʒ��";
						info_check_clean("business_info_check");
						document.getElementById("business_info").className="promptMsg2";
						pass=false;
						focuscount=focuscount+1;
						if (focuscount==1){
							focusobj="buykeyword";
						}
					}else if (errorCode1==1){
						document.getElementById("business_info").innerHTML="��Ӫ�ɹ�������";
						info_check_clean("business_info_check");
						document.getElementById("business_info").className="promptMsg2";
						pass=false;
						focuscount=focuscount+1;
						if (focuscount==1){
							focusobj="buykeyword";
						}
					}else if (errorCode1==2){
						document.getElementById("business_info").innerHTML="��Ӫ�ɹ����зǷ��ַ���";
						info_check_clean("business_info_check");
						document.getElementById("business_info").className="promptMsg2";
						pass=false;
						focuscount=focuscount+1;
						if (focuscount==1){
							focusobj="buykeyword";
						}
					}
					
					if (errorCode1==0 && errorCode2==0){
						document.getElementById("business_info").innerHTML=validatedInfo;
						info_check("business_info_check");
						document.getElementById("business_info").className="promptMsg3";
						document.getElementById("business_title").style.display="none";
					}
					
					if (errorCode1==-1 && errorCode2==-1){
						document.getElementById("business_info").innerHTML="����Ϊ�����";
						info_check_clean("business_info_check");
						document.getElementById("business_info").className="promptMsg2";
						pass=false;
						focuscount=focuscount+1;
						if (focuscount==1){
							focusobj="salekeyword";
						}
					}
					
				}

			//����֤��Ϊ��return false
			if (document.getElementById("validate_code").value.trim()==""){
					document.getElementById("validate_code_info").innerHTML="����Ϊ�����";
					info_check_clean("validate_code_info_check");
					document.getElementById("validate_code_info").className="promptMsg2";
					pass=false
					focuscount=focuscount+1;
					if (focuscount==1){
						focusobj="validate_code";
					}
				
			}
	//�����֤��ͨ������ʾ����ʾȫ��������		
	if (pass==false){
		document.getElementById("yndisplay").style.display="block";
		window.scrollTo(1,1);
		if (focusobj!=""){
			if (document.getElementById(focusobj)!=null){
				document.getElementById(focusobj).focus();
			}
		}
	}

return pass;
}



//functions for each particular datatype validation
function validateUsername(obj){
	var str = obj.value;
	var patn =   /^[a-zA-Z0-9]+$/; 
	//var patn = /^[^\s]*$/;
	if(!checkByteLength(str,4,15)) return 1;
	if(!patn.test(str)){
		return 1;
	}
	return 0; 
}

function validatePassword(obj){
	var str = obj.value;
	if(!checkByteLength(str,6,20)) return 6;															

	if(str == form.username.value) return 2;
	if(isNumberContinue(str) == 1) return 3;
	if(isSameLetter(str) == 1) return 4;
	
	return 0; 
}
//���������ʾ����
function validatePwdquestion(obj){
	var str = obj.value;
	//if(!checkByteLength(str,5,60)) return 1;
	if(!checkByteLengthForPass(str,1,60)) return 1;
	if(checkDenyWords(str) != ""){
		return 2;
	}
	return 0;
}

function validatePwdquestionInput(obj){
	var str = obj.value;
	//if(!checkByteLength(str,5,60)) return 1;
	if(!checkByteLengthForPass(str,1,60)) return 1;
	if(checkDenyWords(str) != ""){
		return 2;
	}
	return 0;
}

function validatePwdAnswer(obj){
	var str = obj.value;
	//if(!checkByteLength(str,5,60)) return 1;
	if(!checkByteLengthForPass(str,1,60)) return 1;
	if(checkDenyWords(str) != ""){
		return 2;
	}
	if(form.confirm_question.value !="" && form.confirm_question.value!="7"){
			if (str == form.confirm_question.value) {
				return 3;
			}
	}else {
		if (str == form.confirm_question_input.value) {
				return 3;
		}
	}
	//if(str == form.psdQues.value) {
		//return 3;
	//}
	
	if(str == form.username.value) {
		return 4;
	}

	return 0;
}
function validateZipcode(obj){
	var str = obj.value;
	
	var patn = /^[0-9]+$/;
	if(!patn.test(str)) return 1;
	//ȡ��6λ������������ƣ�ֻ��֤����
	//if(!checkByteLength(str,1,6)) return 2;
	//if(str.length!=6)return 2;
	return 0;
}
function validateConfirmType1(obj) {
	var str = obj.value;
	if(str == '') {
		return 1;
	}
	return 0;
}
function validateConfirmMainpro(obj) {
	var str = obj.value;
	if(str == '') {
		return 1;
	}
	if(checkDenyWords(str) != ""){
		return 2;
	}
	return 0;
}

function validateSafePassword(obj){
	var str = obj.value;
	if(!checkByteLength(str,6,20)) return 1;															
	//var patn1 =   /^[a-zA-Z0-9_]+$/;
	//if(!patn1.test(str) ) return 1;

	if(str == form.username.value) return 3;

	if(str != document.getElementById("password").value) return 2;
	return 0;
}
function validateEmail(obj){
	var str = obj.value;
	
	if(!checkByteLength(str,1,50)) return 1;
		var patn = /^\w[-._\w]*\w@\w[-._\w]*\w\.\w{2,6}$/;
	if(patn.test(str)){
		return 0;
	}else{
		return 1; //incorrect format
	}
}
function validateNum(obj){
	var str = obj.value;
	var patn = new RegExp("\\d{"+getAttrValueByName(obj,"minlen")+","+getAttrValueByName(obj,"maxlen")+"}"); 
	if(patn.test(str)) return 0;
	return 1; 	
}
function validateMobile(obj){
	var str1 = obj.value;
	var str = tot(str1); 
	obj.value = str;
	if(str.length == 0){
		return -1;
	}
	
	if(str.length != 11){
		return 1;
	}
	
	var patn = /^[0-9]+$/;
	if(patn.test(str)) return 0;
	return 2; 	
}

function validatePhoneArea(obj){
	var str1 = obj.value;
	var str = tot(str1); 
	obj.value = str;
	if(str.length == 0){
		return -1;
	}
	var patn = /^[0-9]+$/;
	if(!patn.test(str)) return 1;
	
	return validatePhone();
	//return validatePhone(); 
}

function validateFaxArea(obj){
	var str1 = obj.value;
	var str = tot(str1); 
	obj.value = str;
	if(str.length == 0){
		
	return -1;
	return 
	}
	var patn = /^[0-9]+$/;
	if(!patn.test(str)) return 1;
	
	return validateFax(); 
}
function validateArea(obj){
	var str1 = obj.value;
	var str = tot(str1); 
	obj.value = str;
	if(str.length == 0){
	return -1;
	}
	var patn = /^[0-9]+$/;
	if(!patn.test(str)) return 1;   
	return 0; 
}
function validateMainBuy(obj){
	var str = obj.value;
	if(checkDenyWords(str) != ""){
		return 3;
	}
	return 0;
}

function validatePhone(){
	if(validateArea(document.getElementById("CountryCode")) == 0 && validateArea(document.getElementById("CityCode")) == 0 && validateNumber(document.getElementById("phone_number")) == 0){
		return 0;
	}else{
		return -1
	}
}
function validateFax(){
	if(validateArea(document.getElementById("FaxCountryCode")) == 0 && validateArea(document.getElementById("FaxCityCode")) == 0 && validateNumber(document.getElementById("fax_number")) == 0){
		return 0;
	}else{
		return -1
	}
}
function validateNumber(obj){
	var str1 = obj.value;
	var str = tot(str1); 
	obj.value = str;
	if(str.length == 0){
		return -1;
	}
	var patn = /^[0-9-\/]+$/;
	if(!patn.test(str)) return 1;
	return 0;
}
function validatePhoneNumber(obj){

	var str1 = obj.value;
	var str = tot(str1); 
	obj.value = str;
	if(str.length == 0){
		return -1;
	}
	var patn = /^[0-9-\/]+$/;
	if(!patn.test(str)) return 1;
	
	var countryCode = document.getElementById("CountryCode");
	if(str.length!=7&&str.length!=8 && countryCode.value.trim()=='86')
		return 2;
	return validatePhone(); 
}
function validateExtPhoneNumber(obj){
	var str1 = obj.value;
	var str = tot(str1); 
	if(str.length == 0){
		return -1;
	}
	var patn = /^[0-9-\/]+$/;
	if(!patn.test(str)){ return 1}else{return 0};
}
function validateFaxNumber(obj){

	var str1 = obj.value;
	var str = tot(str1); 
	obj.value = str;
	if(str.length == 0){
		return -1;
	}
	var patn = /^[0-9-\/]+$/;
	if(!patn.test(str)) return 1;
	
	var countryCode = document.getElementById("CountryCode");
	if(str.length!=7&&str.length!=8 && countryCode.value.trim()=='86')return 2;
	
	return validateFax(); 
}
function validateExtFaxNumber(obj){
	var str1 = obj.value;
	var str = tot(str1); 
	if(str.length == 0){
		return -1;
	}
	var patn = /^[0-9-\/]+$/;
	if(!patn.test(str)) { return 1}else{return 0};
}
function validateKeyword(obj){
	//alert(obj.id)
	var str = obj.value;
	if(str.length > 500){
		return 1;
	}
	//alert(str.length)
	if(str.length == 0){
		return -1;
	}


	var pass = true;
	var keyselected ='';
	for(i=0;i<document.form.business_role.length;i++){
		if(document.form.business_role[i].checked){
		keyselected = document.form.business_role[i].value;
		break;
		}
	}
	
	if(keyselected == 'buyer'){
		if(document.getElementById("buykeyword").value == ''){
			pass = false;
		}
	}else if(keyselected == 'seller'){
		if(document.getElementById("salekeyword").value == ''){
			pass = false; 
		} 
	}else if(keyselected == 'both'){
		if(document.getElementById("salekeyword").value == '' || document.getElementById("buykeyword").value == ''){
			
			return 5;
		}
	}
	
	if(checkDenyWords(str) != ""){
			return 2;
	}
	
	if(pass){
		return 0;
	}
	return -1;
}

function validateCompany(obj){
	var str = obj.value;
	
	if(str.length > 50){
		return 1;
	}
	if(str.length == 0){
		return -1;
	}
	if(checkDenyWords(str) != ""){
		return 2;
	}
	var patn = /^[0-9]+$/;
	if(patn.test(str)) return 3;
	
	if(isSameLetter(str)==1) return 4;
	return 0;
}

function validateJobTitle(obj){
	var str = obj.value;
	if(str.length > 16){
		return 1;
	}
	if(str.length == 0){
		return -1;
	}
	
	if(checkDenyWords(str) != ""){
		return 2;
	}
	
	return 0;
}
function validateFirstName(obj){
	var str = obj.value;
	if(str.length > 32){
		return 1;
	}
	
	if(str.length == 0){
		return -1;
	}
	
	if(checkDenyWords(str) != ""){
		return 2;
	}
	return 0;
}

function validateAddress(obj){
	var str = obj.value;
	if(str.length > 100){
		return 1;
	}
	if(str.length == 0){
		return -1;
	}
	if(checkDenyWords(str) != ""){
		return 2;
	}
	return 0;
}

function setmainpage(){
	document.body.style.behavior='url(#default#homepage)';
	document.body.setHomePage('http://www.hc360.com')
}

function bookmarkit(){
      window.external.addFavorite('http://www.hc360.com','�۴��� �й����ȵ�b2b����������վ')
   }

//�ύ��
function submitForm(obj) {
	document.form.ValidKey.value = tot(document.form.ValidKey.value);
	//��¼��ԴURL
	var referUrl = document.referrer;    
	form.sourceurl.value=referUrl;
	form.isSubmit.value="1";
	if (document.form.submitflag != null){
		document.form.submitflag.value="1";
	}

	//var ret = validateAll(obj);
	var ret=validateAll()
	//alert("ret==" + ret);
	if(ret){
	     	setmainpage();

		//if (document.form.eventSubmit_doPreview) {
		//	document.form.eventSubmit_doPreview.disabled = true;
		//}
	}else {
		form.isSubmit.value="0";
		if (document.form.submitflag != null){
			document.form.submitflag.value="0";
		}
		return false;
	}
	return ret;
}


function checkByteLengthForPass(str,minlen,maxlen) {
	if (str.length > maxlen || str.length < minlen) {
		return false;
	}
	return true;
}      
	
function checkByteLength(str,minlen,maxlen) {
	if (str == null) return false;
	var l = str.length;
	var blen = 0;
	for(i=0; i<l; i++) {
		if ((str.charCodeAt(i) & 0xff00) != 0) {
			blen ++;
		}
		blen ++;
	}
	if (blen > maxlen || blen < minlen) {
		return false;
	}
	return true;
}

function tot(mobnumber){                        
	while(mobnumber.indexOf("��")!=-1){           
		mobnumber = mobnumber.replace("��","0");        
	}                                               
	while(mobnumber.indexOf("��")!=-1){             
	mobnumber = mobnumber.replace("��","1");}       
	while(mobnumber.indexOf("��")!=-1){             
	mobnumber = mobnumber.replace("��","2");}       
	while(mobnumber.indexOf("��")!=-1){             
	mobnumber = mobnumber.replace("��","3");}       
	while(mobnumber.indexOf("��")!=-1){             
	mobnumber = mobnumber.replace("��","4");}       
	while(mobnumber.indexOf("��")!=-1){             
	mobnumber = mobnumber.replace("��","5");}       
	while(mobnumber.indexOf("��")!=-1){             
	mobnumber = mobnumber.replace("��","6");}       
	while(mobnumber.indexOf("��")!=-1){             
	mobnumber = mobnumber.replace("��","7");}       
	while(mobnumber.indexOf("��")!=-1){             
	mobnumber = mobnumber.replace("��","8");}       
	while(mobnumber.indexOf("��")!=-1){             
	mobnumber = mobnumber.replace("��","9");}       
	return mobnumber;                               
}
function isNumberContinue(str){
	var patn1 =   /^[0-9_]+$/;
	var ascendNumber=0;
	var descendNumber=0;
	
	for (var i = 1; i < str.length; i++) {
		if (str.charAt(i).charCodeAt() != (str.charAt(i-1).charCodeAt() + 1)) {
			ascendNumber = 1;
			break;
		}
	}	
	
	for (i = 0; i < (str.length - 1); i++) {
		if (str.charAt(i).charCodeAt() != (str.charAt(i+1).charCodeAt() + 1)) {
			descendNumber = 1;
			break;
		}
	}
	if(descendNumber == 0 || ascendNumber == 0){
		return 1;
	}else{
		return 0;
	}
}

function isSameLetter(str){
	var sameNumberFlag = 1;
	var patn1 =   /^[0-9]+$/;
	if(patn1.test(str) ){
		for (var i = 0; i < str.length; i++) {
		  if (str.charAt(0) != str.charAt(i)) {
			  sameNumberFlag = 0;
			  break;
		  }
		}          
	} else {
		for (var i = 0; i < str.length; i++) {
		    if (str.charAt(0) != str.charAt(i)) {
			    sameNumberFlag = 0;
				break;
		    }
		}
	}
	return sameNumberFlag;
}

function isOnlyDigitOrChar(str) {
	var digit = /^\d+$/;
	var words  = /^[a-zA-Z]+$/;
	if(digit.test(str) || words.test(str)) {
		return 1;
	}else {
		return 0;
	}
}




//�Ƴ�ҳ���Ƿ񵯳�����
var exitpop=false;
function PopPage(strURL, name, width, height, left, top)
{   
	if(width==null) width=800;
	if(height==null) height=500;
	if(left==null) left = ( screen.width - width ) / 2;
	if(top==null) top  = ( screen.height - height ) / 2;
	temp = "width="+width+",height="+height+",left="+left+",top="+top+",scrollbars=1,toolbar=no,location=no,directories=no,status=no,resizable=no";
	w = window.open(strURL,name,temp);
	w.focus();
}

function GetCookie(cookiename)
{
	var thebigcookie = document.cookie;
	var firstchar = thebigcookie.indexOf(cookiename);
	if (firstchar != -1) {
	firstchar += cookiename.length + 1;
	lastchar = thebigcookie.indexOf(";",firstchar);
	if(lastchar == -1) lastchar = thebigcookie.length;
	return unescape(thebigcookie.substring(firstchar, lastchar));
	}
	return "";
}
function setCookie(cookiename,cookievalue,cookieexpdate,domainname)
{
	document.cookie = cookiename + "=" + cookievalue
	+ "; domain=" + domainname
	+ "; path=" + "/"
	+ "; expires=" + cookieexpdate.toGMTString();
}

function unloadpopup(cookiename,popurl,popwidth,popheight,domainname,tr)
{
	try {
	if (!tr)
	return;
	if( GetCookie(cookiename) == "" )
	{
	var expdate = new Date();
	expdate.setTime(expdate.getTime() + 1 * (24 * 60 * 60 * 1000)); //+1 day
	setCookie(cookiename,"yes",expdate,domainname);
	if( exitpop )
	{
	w = window.open(popurl,cookiename,"width="+popwidth+",height="+popheight+",scrollbars=1,toolbar=yes,location=yes,menubar=yes,status=yes,resizable=yes");
	w.focus;
	}
	}
	}catch (e) {}
}

function setCheckboxes(theForm, elementName, isChecked)
	{
	var chkboxes = document.forms[theForm].elements[elementName];
	var count = chkboxes.length;
	if (count)
	{
	for (var i = 0; i < count; i++)
		{
		chkboxes[i].checked = isChecked;
		}
		}
		else
		{
		chkboxes.checked = isChecked;
		}
		return true;
}


var imageObject;
function ResizeImage(obj, MaxW, MaxH)
		{
		if (obj != null) imageObject = obj;
		var state=imageObject.readyState;
		var oldImage = new Image();
		oldImage.src = imageObject.src;
		var dW=oldImage.width; var dH=oldImage.height;
		if(dW>MaxW || dH>MaxH) {
		a=dW/MaxW; b=dH/MaxH;
		if(b>a) a=b;
		dW=dW/a; dH=dH/a;
		}
		if(dW > 0 && dH > 0)
		imageObject.width=dW;imageObject.height=dH;
		if(state!='complete' || imageObject.width>MaxW || imageObject.height>MaxH) {
		setTimeout("ResizeImage(null,"+MaxW+","+MaxH+")",40);
		}
}


function focustoRemind(objid){
			//���������û���
			if (objid=="loginid"){
				document.getElementById("loginid_info").innerHTML="��4-15����ĸ��������ɣ���֧�����ģ������ִ�Сд��ע��ɹ��󲻿��޸ġ�";
				document.getElementById("loginid_info").className="promptMsg";
				return;
			}
			
			//������������
			if (objid=="password"){
				document.getElementById("password_info").innerHTML="6-20����ĸ�����ִ�Сд����������ɡ�";
				document.getElementById("password_info").className="promptMsg";
				return;
			}
			
			//������������ȷ��
			if (objid=="confirm_password"){
				document.getElementById("confirm_password_info").innerHTML="��������һ��������д�����롣";
				document.getElementById("confirm_password_info").className="promptMsg";
				return;
			}
			
			//��������ʵ����
			if (objid=="first_name"){
				document.getElementById("first_name_info").innerHTML="&nbsp;";
				document.getElementById("first_name_info").className="";
				return;
			}
			
			//������ְλ
			if (objid=="job_title"){
				document.getElementById("job_title_info").innerHTML="&nbsp;";
				document.getElementById("job_title_info").className="";
				return;
			}
			
			//����������
			if (objid=="email"){
				document.getElementById("email_info").innerHTML="���ǿͻ�������ϵ����ѡ��ʽ���������д��";
				document.getElementById("email_info").className="promptMsg";
				return;
			}
			
			//�������ֻ�
			if (objid=="mobile"){
				document.getElementById("mobile_info").innerHTML="����ͻ���ʱ��ϵ�������۴ϲ����κ��շѷ���";
				document.getElementById("mobile_info").className="promptMsg";
				return;
			}
			
			//�����㹫˾����
			if (objid=="company"){
				document.getElementById("company_info").innerHTML="����д��ҵȫ�ƣ����徭Ӫ������дִ���ϵ�����������ע���徭Ӫ���磺���ģ����徭Ӫ����";
				document.getElementById("company_info").className="promptMsg";
				return;
			}
			
			//�����㾭Ӫ��ַ
			if (objid=="address"){
				document.getElementById("address_info").innerHTML="����ϸ��д��˾��Ӫ��ַ���硰�й�.����.��������ֱ�ű����42��1001���䡣��";
				document.getElementById("address_info").className="promptMsg";
				return;
			}
			
			//����������
			if (objid=="salekeyword"){
				document.getElementById("business_info").innerHTML="Ϊ�˸����ṩƥ��Ĳ�Ʒ��Ϣ������д��˾����Ӫ��Ʒ������񣩹ؼ��֡�<br>���ж�������ö��ŷָ����磺���ϣ�������";
				document.getElementById("business_info").className="promptMsg";
				return;
			}
			
			//������ɹ�
			if (objid=="buykeyword"){
				document.getElementById("business_info").innerHTML="Ϊ�˸����ṩƥ��Ĳ�Ʒ��Ϣ������д��˾����Ӫ��Ʒ������񣩹ؼ��֡�<br>���ж�������ö��ŷָ����磺���ϣ�������";
				document.getElementById("business_info").className="promptMsg";
				return;
			}
			//��������֤��
			if (objid=="validate_code"){
				document.getElementById("validate_code_info").innerHTML="�뽫���ͼƬ������׼ȷ����д���ı����С�";
				document.getElementById("validate_code_info").className="promptMsg";
				return;
			}
			
		}
		
function blurtoRemind(objid){
			//�������뿪�û���
			if (objid=="loginid"){
				errorCode = validateUsername(document.getElementById(objid));
				if(errorCode == 0){
					checkusername=true;
					checkNick();
					if (checkusername){

						document.getElementById("loginid_info").className="promptMsg3";
					}
					return;
				}else{
					document.getElementById("loginid_info").innerHTML="������д���󡣻�Ա��¼��ֻ����4-15��Ӣ����ĸ���������(��֧�����ġ������ִ�Сд)��";
					info_check_clean("loginid_info_check");
					document.getElementById("loginid_info").className="promptMsg2";
					return;
				}
			}
			

			//�������뿪��������ʡ
			if (objid=="SelCityOther"){
				selCountryTemp1 = document.getElementById("SelCountryLikeChina").value.trim();
				notCNCityTemp1 = document.getElementById("SelCityOther").value;
				if (((selCountryTemp1 =='242' || selCountryTemp1 =='243' || selCountryTemp1 =='244') && notCNCityTemp1 != '')){
					document.getElementById("city_info").style.display="none"
					info_check("city_info_check");
				}else if (((selCountryTemp1 =='242' || selCountryTemp1 =='243' || selCountryTemp1 =='244') && notCNCityTemp1 == '')) {
					document.getElementById("city_info").innerHTML="����Ϊ�����";
					info_check_clean("city_info_check");
					document.getElementById("city_info").style.display="block";
					document.getElementById("city_info").className="promptMsg2";
					return;
				}
			}
			
			//�������뿪����
			if (objid=="password"){
				errorCode = validatePassword(document.getElementById(objid));
				if (errorCode==0){
					document.getElementById("password_info").innerHTML=validatedInfo;
					info_check("password_info_check");
					document.getElementById("password_info").className="promptMsg3";
					return;
				}else if (errorCode==2){
					document.getElementById("password_info").innerHTML="Ϊ��ȫ��������벻�����Ա��¼����ͬ���������������룡";
					info_check_clean("password_info_check");
					document.getElementById("password_info").className="promptMsg2";
					return;
				}else if (errorCode==3){
					document.getElementById("password_info").innerHTML="Ϊ��ȫ��������벻�������������֣��������������룡";
					info_check_clean("password_info_check");
					document.getElementById("password_info").className="promptMsg2";
					return;
				}else if (errorCode==4 || errorCode==6){
					document.getElementById("password_info").innerHTML="�����������������������6-20λӢ����ĸ��������ɵģ����ִ�Сд!";
					info_check_clean("password_info_check");
					document.getElementById("password_info").className="promptMsg2";
					return;
				}
			}
			
			//�������뿪��������
			if (objid=="confirm_password"){
				errorCode = validateSafePassword(document.getElementById(objid));
				if (errorCode==0){
					document.getElementById("confirm_password_info").innerHTML=validatedInfo;
					info_check("confirm_password_info_check");
					document.getElementById("confirm_password_info").className="promptMsg3";
					return;
				}else if (errorCode==1){	
					document.getElementById("confirm_password_info").innerHTML="�����������������������6-20λӢ����ĸ��������ɵģ����ִ�Сд!";
					info_check_clean("confirm_password_info_check");
					document.getElementById("confirm_password_info").className="promptMsg2";
					return;
				}else if (errorCode==2){
					document.getElementById("confirm_password_info").innerHTML="������������벻��ͬ��������������������롣";
					info_check_clean("confirm_password_info_check");
					document.getElementById("confirm_password_info").className="promptMsg2";
					return;
				}else if (errorCode==3){
					document.getElementById("confirm_password_info").innerHTML="Ϊ��ȫ��������벻�����Ա��¼����ͬ���������������룡";
					info_check_clean("confirm_password_info_check");
					document.getElementById("confirm_password_info").className="promptMsg2";
				}
			}
			
			//�������뿪��ʵ����
			if (objid=="first_name"){
				errorCode = validateFirstName(document.getElementById(objid));
				if (errorCode==0){
					document.getElementById("first_name_info").innerHTML=validatedInfo;
					info_check("first_name_info_check");
					document.getElementById("first_name_info").className="promptMsg3";
					return;
				}else if (errorCode==1){
					document.getElementById("first_name_info").innerHTML="����������1-32���֡�";
					info_check_clean("first_name_info_check");
					document.getElementById("first_name_info").className="promptMsg2";
					return;
				}else if (errorCode==-1){
					document.getElementById("first_name_info").innerHTML="����Ϊ�����";
					info_check_clean("first_name_info_check");
					document.getElementById("first_name_info").className="promptMsg2";
					return;
				}else if (errorCode==2){
					document.getElementById("first_name_info").innerHTML="�������зǷ��ַ���";
					info_check_clean("first_name_info_check");
					document.getElementById("first_name_info").className="promptMsg2";
					return;
				}
			}
			
			//�������뿪ְλ
			if (objid=="job_title"){
				errorCode = validateJobTitle(document.getElementById(objid));
				if (errorCode==0){
					document.getElementById("job_title_info").innerHTML=validatedInfo;
					info_check("job_title_info_check");
					document.getElementById("job_title_info").className="promptMsg3";
					return;
				}else if (errorCode==1){
					document.getElementById("job_title_info").innerHTML="ְλ���Ƴ�����󳤶�16���֡�";
					info_check_clean("job_title_info_check");
					document.getElementById("job_title_info").className="promptMsg2";
					return;
				}else if (errorCode==-1){
					document.getElementById("job_title_info").innerHTML="����Ϊ�����";
					info_check_clean("job_title_info_check");
					document.getElementById("job_title_info").className="promptMsg2";
					return;
				}else if (errorCode==2){
					document.getElementById("job_title_info").innerHTML="ְλ���зǷ��ַ���";
					info_check_clean("job_title_info_check");
					document.getElementById("job_title_info").className="promptMsg2";
					return;
				}
			}
			
			//�������뿪����
			if (objid=="email"){
				errorCode = validateEmail(document.getElementById(objid));
				if (errorCode==0){
					document.getElementById("email_info").innerHTML=validatedInfo;
					info_check("email_info_check");
					document.getElementById("email_info").className="promptMsg3";
					return;
				}else if (errorCode==1){
					document.getElementById("email_info").innerHTML="�����ʼ���ʽ����ȷ��������������ȷ�ĵ��������ʽ��<a href='http://mail.163.com/' target='_blank'>ע����������</a>��<a href='http://mail.sina.com.cn/cgi-bin/register/regMember1.cgi' target='_blank'>ע����������</a>��";
					info_check_clean("email_info_check");
					document.getElementById("email_info").className="promptMsg2";
					return;
				}
			}
			
			//�������뿪�ֻ�
			if (objid=="mobile"){
				errorCode = validateMobile(document.getElementById(objid));
				if (errorCode==0){
					document.getElementById("mobile_info").innerHTML=validatedInfo;
					info_check("mobile_info_check");
					document.getElementById("mobile_info").className="promptMsg3";
					return;
				}else if (errorCode==-1){
					document.getElementById("mobile_info").innerHTML="";
					info_check_clean("mobile_info_check");
					document.getElementById("mobile_info").className="";
					return;	
				}else if (errorCode==1){
					document.getElementById("mobile_info").innerHTML="������11λ���İ���������";
					info_check_clean("mobile_info_check");
					document.getElementById("mobile_info").className="promptMsg2";
					return;		
				}else if (errorCode==2){
					document.getElementById("mobile_info").innerHTML="����д��ȷ���ֻ����롣";
					info_check_clean("mobile_info_check");
					document.getElementById("mobile_info").className="promptMsg2";
					return;
				}
			}
			
			//�������뿪�绰����
			if (objid=="CountryCode"){
				errorCode = validatePhoneArea(document.getElementById(objid));
				if (errorCode==0){
					document.getElementById("phone_info").innerHTML=validatedInfo;
					info_check("phone_info_check");
					document.getElementById("phone_info").className="promptMsg3";
					return;
				}else if (errorCode==-1){
					document.getElementById("phone_info").innerHTML="";
					info_check_clean("phone_info_check");
					document.getElementById("phone_info").className="";
					return;
				}else if (errorCode==1){
					document.getElementById("phone_info").innerHTML="�绰����ֻ�������֡�";
					info_check_clean("phone_info_check");
					document.getElementById("phone_info").className="promptMsg2";
					return;
				}
			}
			
			//�������뿪�绰����
			if (objid=="CityCode"){
				if (document.getElementById(objid).value.trim()!="����"){
					errorCode = validatePhoneArea(document.getElementById(objid));
					if (errorCode==0){
						document.getElementById("phone_info").innerHTML=validatedInfo;
						info_check("phone_info_check");
						document.getElementById("phone_info").className="promptMsg3";
						return;
					}else if (errorCode==-1){
						document.getElementById("phone_info").innerHTML="";
						info_check_clean("phone_info_check");
						document.getElementById("phone_info").className="";
						return;
					}else if (errorCode==1){
						document.getElementById("phone_info").innerHTML="�绰����ֻ�������֡�";
						info_check_clean("phone_info_check");
						document.getElementById("phone_info").className="promptMsg2";
						return;
					}
				}else{
					document.getElementById("phone_info").innerHTML="";
					info_check_clean("phone_info_check");
					document.getElementById("phone_info").className="";
					return;
				}
			}
			
			//�������뿪�绰����
			if (objid=="phone_number"){
				if (document.getElementById(objid).value.trim()!="�绰����"){
					errorCode = validatePhoneNumber(document.getElementById(objid));
					if (errorCode==0){
						document.getElementById("phone_info").innerHTML=validatedInfo;
						info_check("phone_info_check");
						document.getElementById("phone_info").className="promptMsg3";
						return;
					}else if (errorCode==-1){
						document.getElementById("phone_info").innerHTML="";
						info_check_clean("phone_info_check");
						document.getElementById("phone_info").className="";
						return;
					}else if (errorCode==1){
						document.getElementById("phone_info").innerHTML="�绰����ֻ�������֡�";
						info_check_clean("phone_info_check");
						document.getElementById("phone_info").className="promptMsg2";
						return;
					}else if (errorCode==2){
						document.getElementById("phone_info").innerHTML="�绰��������й������ĵ绰����ֻ����7��8λ��";
						info_check_clean("phone_info_check");
						document.getElementById("phone_info").className="promptMsg2";
						return;
					}
				}else{
					document.getElementById("phone_info").innerHTML="";
					info_check_clean("phone_info_check");
					document.getElementById("phone_info").className="";
					return;
				}
			}
			
			//�������뿪�绰�ֻ���
			if (objid=="ext_phone_number"){
				if (document.getElementById(objid).value.trim()!="�ֻ�(ѡ��)"){
					errorCode = validateExtPhoneNumber(document.getElementById(objid));
					if (errorCode==0){
						document.getElementById("phone_info").innerHTML=validatedInfo;
						return;
					}else if (errorCode==1){
						document.getElementById("phone_info").innerHTML="�ֻ���ֻ�������ֱ�ʾ��";
						info_check_clean("phone_info_check");
						document.getElementById("phone_info").className="promptMsg2";
						return;
					}
				}else{
					document.getElementById("phone_info").innerHTML="";
					info_check_clean("phone_info_check");
					document.getElementById("phone_info").className="";
					return;
				}
			}
			
			//�������뿪����绰����
			if (objid=="FaxCountryCode"){
				errorCode = validateFaxArea(document.getElementById(objid));
				if (errorCode==0){
					document.getElementById("fax_info").innerHTML=validatedInfo;
					info_check("fax_info_check");
					document.getElementById("fax_info").className="promptMsg3";
					return;
				}else if (errorCode==-1){
					document.getElementById("fax_info").innerHTML="";
					info_check_clean("fax_info_check");
					document.getElementById("fax_info").className="";
					return;
				}else if (errorCode==1){
					document.getElementById("fax_info").innerHTML="���Һ���ֻ��ʹ�����֡�";
					info_check_clean("fax_info_check");
					document.getElementById("fax_info").className="promptMsg2";
					return;
				}
			}
			
			//�������뿪����绰����
			if (objid=="FaxCityCode"){
				if (document.getElementById(objid).value.trim()!="����"){
					errorCode = validateFaxArea(document.getElementById(objid));
					if (errorCode==0){
						document.getElementById("fax_info").innerHTML=validatedInfo;
						info_check("fax_info_check");
						document.getElementById("fax_info").className="promptMsg3";
						return;
					}else if (errorCode==-1){
						document.getElementById("fax_info").innerHTML="";
						info_check_clean("fax_info_check");
						document.getElementById("fax_info").className="";
						return;
					}else if (errorCode==1){
						document.getElementById("fax_info").innerHTML="����ֻ��ʹ�����֡�";
						info_check_clean("fax_info_check");
						document.getElementById("fax_info").className="promptMsg2";
						return;
					}
				}else{
					document.getElementById("fax_info").innerHTML="";
					info_check_clean("fax_info_check");
					document.getElementById("fax_info").className="";
					return;
				}
			}
			
			//�������뿪����绰����
			if (objid=="fax_number"){
				if (document.getElementById(objid).value.trim()!="�������"){
					errorCode = validateFaxNumber(document.getElementById(objid));
					if (errorCode==0){
						document.getElementById("fax_info").innerHTML=validatedInfo;
						info_check("fax_info_check");
						document.getElementById("fax_info").className="promptMsg3";
						return;
					}else if (errorCode==1){
						document.getElementById("fax_info").innerHTML="�������ֻ��ʹ������";
						info_check_clean("fax_info_check");
						document.getElementById("fax_info").className="promptMsg2";
						return;
					}else if (errorCode==2){
						document.getElementById("fax_info").innerHTML="�����������й������Ĵ������ֻ����7��8λ��";
						info_check_clean("fax_info_check");
						document.getElementById("fax_info").className="promptMsg2";
						return;
					}
				}else{
					document.getElementById("fax_info").innerHTML="";
					info_check_clean("fax_info_check");
					document.getElementById("fax_info").className="";
					return;
				}
			}
			
			//�������뿪����绰�ֻ���
			if (objid=="ext_fax_number"){
				if (document.getElementById(objid).value.trim()!="�ֻ�(ѡ��)"){
					errorCode = validateExtFaxNumber(document.getElementById(objid));
					if (errorCode==0){
						document.getElementById("fax_info").innerHTML=validatedInfo;
						return;
					}else if (errorCode==1){
						document.getElementById("fax_info").innerHTML="�ֻ���ֻ�������ֱ�ʾ��";
						info_check_clean("fax_info_check");
						document.getElementById("fax_info").className="promptMsg2";
						return;
					}
				}else{
					document.getElementById("fax_info").innerHTML="";
					info_check_clean("fax_info_check");
					document.getElementById("fax_info").className="";
					return;
				}
			}
			
			//�������뿪��˾����
			if (objid=="company"){
				errorCode = validateCompany(document.getElementById(objid));
				if (errorCode==0){
					document.getElementById("company_info").innerHTML=validatedInfo;
					info_check("company_info_check");
					document.getElementById("company_info").className="promptMsg3";
					return;
				}else if (errorCode==-1){
					document.getElementById("company_info").innerHTML="����Ϊ�����";
					info_check_clean("company_info_check");
					document.getElementById("company_info").className="promptMsg2";
					return;
				}else if (errorCode==1){
					document.getElementById("company_info").innerHTML="��˾���Ʋ��ܳ���50����";
					info_check_clean("company_info_check");
					document.getElementById("company_info").className="promptMsg2";
					return;
				}else if (errorCode==2){
					document.getElementById("company_info").innerHTML="��˾���ƺ��зǷ��ַ���";
					info_check_clean("company_info_check");
					document.getElementById("company_info").className="promptMsg2";
					return;
				}else if (errorCode==3 || errorCode==4){
					document.getElementById("company_info").innerHTML="<font color='#FF0000'>��˾���ƴ���</font><span class='STYLE4'>��˾���Ʋ������������ַ�����ȫʹ�����ֱ�ʾ��";
					info_check_clean("company_info_check");
					document.getElementById("company_info").className="promptMsg2";
					return;
				}
			}
			
			//�������뿪��Ӫ��ַ
			if (objid=="address"){
				errorCode = validateAddress(document.getElementById(objid));
				if (errorCode==0){
					document.getElementById("address_info").innerHTML=validatedInfo;
					info_check("address_info_check");
					document.getElementById("address_info").className="promptMsg3";
					return;
				}else if (errorCode==-1){
					document.getElementById("address_info").innerHTML="����Ϊ�����";
					info_check_clean("address_info_check");
					document.getElementById("address_info").className="promptMsg2";
					return;
				}else if (errorCode==1){
					document.getElementById("address_info").innerHTML="��ϸ��ַ������";
					info_check_clean("address_info_check");
					document.getElementById("address_info").className="promptMsg2";
					return;
				}else if (errorCode==2){
					document.getElementById("address_info").innerHTML="��ϸ��ַ���зǷ��ַ���";
					info_check_clean("address_info_check");
					document.getElementById("address_info").className="promptMsg2";
					return;
				}
			}
			
			//�������뿪�ɹ�
			if (objid=="buykeyword" ){
				errorCode = validateKeyword(document.getElementById(objid));
				if (errorCode==0){
					document.getElementById("business_info").innerHTML=validatedInfo;
					info_check("business_info_check");
					document.getElementById("business_info").className="promptMsg3";
					return;
				}else if (errorCode==5){
					document.getElementById("business_info").innerHTML="��Ӫ��Ʒ����Ϊ�գ���������Ӫ��Ʒ��";
					info_check_clean("business_info_check");
					document.getElementById("business_info").className="promptMsg2";
					return;
				}else if (errorCode==-1){
					document.getElementById("business_info").innerHTML="��Ӫ��Ʒ����Ϊ�գ���������Ӫ��Ʒ��";
					info_check_clean("business_info_check");
					document.getElementById("business_info").className="promptMsg2";
					return;
				}else if (errorCode==1){
					document.getElementById("business_info").innerHTML="��Ӫ�ɹ�������";
					info_check_clean("business_info_check");
					document.getElementById("business_info").className="promptMsg2";
					return;
				}else if (errorCode==2){
					document.getElementById("business_info").innerHTML="��Ӫ�ɹ����зǷ��ַ���";
					info_check_clean("business_info_check");
					document.getElementById("business_info").className="promptMsg2";
					return;
				}

			}
			
			//�������뿪����
			if (objid=="salekeyword" ){
				errorCode = validateKeyword(document.getElementById(objid));
				if (errorCode==0){
					document.getElementById("business_info").innerHTML=validatedInfo;
					info_check("business_info_check");
					document.getElementById("business_info").className="promptMsg3";
					return;
				}else if (errorCode==5){
					document.getElementById("business_info").innerHTML="��Ӫ��Ʒ����Ϊ�գ���������Ӫ��Ʒ��";
					info_check_clean("business_info_check");
					document.getElementById("business_info").className="promptMsg2";
					return;
				}else if (errorCode==-1){
					document.getElementById("business_info").innerHTML="��Ӫ��Ʒ����Ϊ�գ���������Ӫ��Ʒ��";
					info_check_clean("business_info_check");
					document.getElementById("business_info").className="promptMsg2";
					return;
				}else if (errorCode==1){
					document.getElementById("business_info").innerHTML="��Ӫ��Ʒ��";
					info_check_clean("business_info_check");
					document.getElementById("business_info").className="promptMsg2";
					return;
				}else if (errorCode==2){
					document.getElementById("address_info").innerHTML="��Ӫ��Ʒ���зǷ��ַ���";
					info_check_clean("business_info_check");
					document.getElementById("business_info").className="promptMsg2";
					return;
				}
			}
		}
		
function showalltitle(){
	document.getElementById("loginid_title").style.display="";
	document.getElementById("password_title").style.display="";
	document.getElementById("confirm_password_title").style.display="";
	document.getElementById("first_name_title").style.display="";
	document.getElementById("job_title_title").style.display="";
	document.getElementById("email_title").style.display="";
	document.getElementById("phone_title").style.display="";
	document.getElementById("fax_title").style.display="";
	document.getElementById("mobile_title").style.display="";
	document.getElementById("genre_title").style.display="";
	document.getElementById("company_title").style.display="";
	document.getElementById("city_title").style.display="";
	document.getElementById("address_title").style.display="";
	document.getElementById("category_title").style.display="";
	document.getElementById("business_title").style.display="";
	document.getElementById("validate_code_title").style.display="";
	document.getElementById("yndisplay").style.display="none";
}
		