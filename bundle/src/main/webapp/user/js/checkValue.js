	//判断注册页是否进行了修改
	function judgeret(){
		if (GE("loginid").value == '' && GE("password").value == '' && GE("confirm_password").value == '' && GE("email").value == '' && GE("first_name").value  =='' && GE("job_title").value == '' && GE("CityCode").value == '' && GE("phone_number").value == '' && GE("ext_phone_number").value == '' && GE("mobile").value == '' && GE("FaxCityCode").value == '' && GE("fax_number").value == '' && GE("ext_fax_number").value == '' && GE("company").value == '' && GE("address").value == '' && GE("salekeyword").value == '' && GE("buykeyword").value == ''  && GE("validate_code").value == ''){
		  return true;
		 }else{
		  return false;
		 }
		
	}
	
	//根据注册页是否修改返回不同得参数
	function judgemodify(){
		var writeuserconstants;
		if (judgeret()){
		 	writeuserconstants = "1";
		 }else{
		 	writeuserconstants = "2";
		 }
		 return writeuserconstants;
	}
	
	//写用户关闭，刷新，重定向操作日志
	function setwriteuser(){
	    dowriteuser("","0","0",judgemodify(),"0",judgemodify())
		}

	
	//记录登录人数
	function fwrstj(){
		
		//对于非提交表单离开时
	 	if (document.form.submitflag.value == 0){
	 		setwriteuser();
	 	}else{
	 		document.form.submitflag.value == 0;
	 	}
	}
	
	//检测公司名称
	function checkCompanyName() {
		var nick = document.getElementById("company").value;

		if (nick == '') {
			return false;
		}
		
		return true;
	}
	
	//检测邮件
	function checkEmail() {
		var email = document.getElementById("email").value;
		if (email == '') {
			return false;
		}
		document.checkEmailForm.HC_EMAIL.value = email;
		document.getElementById("email_info").innerHTML = "检测中，请稍等...";
		document.getElementById("email_info").className = "notetrue";
		matchSubcribe();
		
		return true;
	}
 
	



	//initForm();
	
	function setBusinessStyle(){
		business_info.className = 'notetrue';	
	}
	
	function tr_display(type){
		//business_info.className = 'notetrue';
		//business_info.innerHTML = '为了给您提供匹配的产品信息，请填写贵公司主营的产品（或服务）关键字。<br>如有多个，请用逗号分隔。如：布料，拉链';
		if(type == 'buy'){
			document.getElementById("buykeywords").style.display = 'block';
			document.getElementById("salekeyword").value="";
			document.getElementById("sellkeywords").style.display = 'none';
		}
		if(type == 'sell'){
			document.getElementById("buykeyword").value="";
			document.getElementById("buykeywords").style.display = 'none';
			document.getElementById("sellkeywords").style.display = 'block';
		}
		if(type == 'both'){
			document.getElementById("buykeywords").style.display = 'block';
			document.getElementById("sellkeywords").style.display = 'block';
		}
		document.getElementById("business_info").innerHTML="";
		info_check_clean("business_info_check");
		document.getElementById("business_info").className="";
	}
	
	function goinitstyle(obj){
		document.getElementById("business_info").className = infoboxErrorClass;
		document.getElementById("business_info").innerHTML = '为了给您提供匹配的产品信息，请填写贵公司主营的产品（或服务）关键字。<br>如有多个，请用逗号分隔。如：布料，拉链';
		var pass = true;
		var keyselected ='';
		for(i=0;i<document.form.business_role.length;i++){
			if(document.form.business_role[i].checked){
			keyselected = document.form.business_role[i].value;
			break;
			}
		}
		if(keyselected == 'buyer'){
			if(document.getElementById("buykeyword").value != ''){
				if(document.getElementById("keywords_info_check").innerHTML.indexOf("IMG") < 0 ){ 
					document.getElementById("keywords_info_check").innerHTML = "&nbsp;<img src="+rightIcon+" width=\"19\" height=\"16\" align=\"absmiddle\"> ";
				}
				document.getElementById("business_info").className = infoboxOkClass;	
				document.getElementById("business_info").innerHTML = '填写正确。'
			}else{
				if(document.getElementById("keywords_info_check").innerHTML.indexOf("IMG") > 0 ){
					var start =  document.getElementById("keywords_info_check").innerHTML.indexOf('>');
					var end = document.getElementById("keywords_info_check").innerHTML.length;
					document.getElementById("keywords_info_check").innerHTML = document.getElementById("keywords_info_check").innerHTML.substring(start +1,end);
				}
			}
		}else if(keyselected == 'seller'){
			if(document.getElementById("salekeyword").value != ''){
				if(document.getElementById("keywords_info_check").innerHTML.indexOf("IMG") < 0 ){ 
					document.getElementById("keywords_info_check").innerHTML = "&nbsp;<img src="+rightIcon+" width=\"19\" height=\"16\" align=\"absmiddle\"> ";
				}
				document.getElementById("business_info").className = infoboxOkClass;	
				document.getElementById("business_info").innerHTML = '填写正确。'
			}else{
				if(document.getElementById("keywords_info_check").innerHTML.indexOf("IMG") > 0 ){
					var start =  document.getElementById("keywords_info_check").innerHTML.indexOf('>');
					var end = document.getElementById("keywords_info_check").innerHTML.length;
					document.getElementById("keywords_info_check").innerHTML = document.getElementById("keywords_info_check").innerHTML.substring(start +1,end);
				}
			}
		}else if(keyselected == 'both'){
			if(document.getElementById("salekeyword").value != '' && document.getElementById("buykeyword").value != ''){
				if(document.getElementById("keywords_info_check").innerHTML.indexOf("IMG") < 0 ){ 
					document.getElementById("keywords_info_check").innerHTML = "&nbsp;<img src="+rightIcon+" width=\"19\" height=\"16\" align=\"absmiddle\"> ";
				}
				document.getElementById("business_info").className = infoboxOkClass;	
				document.getElementById("business_info").innerHTML = '填写正确。'
			}else{
				if(document.getElementById("keywords_info_check").innerHTML.indexOf("IMG") > 0 ){
					var start =  document.getElementById("keywords_info_check").innerHTML.indexOf('>');
					var end = document.getElementById("keywords_info_check").innerHTML.length;
					document.getElementById("keywords_info_check").innerHTML = document.getElementById("keywords_info_check").innerHTML.substring(start +1,end);
				}
			}
		}
	}
	
	function info_check(obj){
			document.getElementById(obj).innerHTML = "&nbsp;<img src="+rightIcon+" width=\"12\" height=\"12\" align=\"absmiddle\"> ";
	}
	
	function info_check_clean(objcheck){
		document.getElementById(objcheck).innerHTML="";
	}
	
	function warning_check(obj,message){
		document.getElementById(obj).innerHTML = message;
		document.getElementById(obj).className= infoboxErrorClass;
	}
	
	function clean_check(objinfo){	
		document.getElementById(objinfo).innerHTML = "";
		document.getElementById(objinfo).className= "";
		document.getElementById(objinfo).style.display= "none";
	}
	/********************************** chinese ***************************************/
	/**
	*校验字符串是否为中文
	*返回值：
	*如果为空，定义校验通过，           返回true
	*如果字串为中文，校验通过，         返回true
	*如果字串为非中文，             返回false    参考提示信息：必须为中文！
	*/
	function checkIsChinese(str){
		//如果值为空，通过校验
		if (str == "")
		return true;
		var pattern = /^([\u4E00-\u9FA5]|[\uFE30-\uFFA0])*$/gi;
		if (pattern.test(str))
		return true;
		else
		return false;
	}
	

	
	function changeConvertSub(flag){
		if(flag == 0) {
			auto_convert_info.innerHTML = "未进行使用邮箱订阅信息导入操作！&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		}else if(flag ==1){
			auto_convert_info.innerHTML = "将您使用邮箱订阅的“供应信息”导入商务中心关键字订阅！";
		}
	}
	function changeConvertAnymsbuy(flag){
		if(flag == 0) {
			auto_convert_info.innerHTML = "未进行使用邮箱订阅信息导入操作！&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		}else if(flag ==1){
			auto_convert_info.innerHTML = "将您使用邮箱订阅的“供应信息”导入商务中心关键字订阅！";
		}
	}
	
	function phonefocus(obj,str){
		if (obj.value.trim()==str.trim()){
			obj.value="";
			obj.style.color="000000";
		}
	}
	
	function phoneblur(obj,str){
		if (obj.value.trim()==""){
			obj.value=str;
			obj.style.color="999999";
		}
	}
	//初始化检查邮箱地址
	//checkEmail();
//-->
//检查敏感词
	var frindname = '';
	
	//检查敏感词
	function checkDenyWords(content) {
	    if (content == null || content.trim() == "") 
	    	return "";
	    var wordsArrays = new Array();
	    wordsArrays = new Array("共匪","炸药","麻醉枪","毒品","大麻","可卡因","K粉","氯胺酮","冰毒","鸦片","吗啡","甲基苯丙胺","迷药","三唑仑","迷烟","咖啡因","窃听器","手机监听器","针孔摄像机","烟感型摄像机","窃照器","手包式暗访摄像机","摇头丸","赌具","吃角子老虎机","透视扑克","透视麻将","六合彩","上海千术","千术教学","赌场作弊器","爆炸性武器及模型","三棱刀","三棱尖刀","匕首","弹簧刀","跳刀","万能钥匙","电表调教器","无线隐形耳机","反屏蔽反探测对讲系统","发票代开","代开发票","發票代開","發票代开","失忆水","希爱力","税票代开","代开增值税","代开广州发票","军刀","代开发 票","增值税发票","长沙办证","催眠香水","催眠力月","脉冲上分器","电子主板上分器","上门开锁","公积金取现","套现/信用卡","代办信用卡,提现","供应人体器官","供应人体肾脏","信用卡分期套现","信用卡分期社保套现","信用卡空卡翻倍","取现/信用卡","百家乐作弊","百乐二号破解","办正","出售仿真假钞","出售考前答案","出售身份证 ","英语专业四级答案","DVR手表","暗访摄象包","暗访摄象机","暗访摄像机","办证刻章","报关员考试考前答案","北京昌平办证","北京昌平刻章办证","北京海淀办证","北京西城刻章办证","仿真假钱","假钱","提现","病假条","弹弓","地磅的遥控器","电鱼机","拦截器","肾脏","婚外情调查","外遇调查","离婚取证","第三者调查","行踪调查","情人调查","票据","增值税","税嘌","点数低","夫妻性生活频道","电子捕兔机","捕兔机","转帐支票换现金","水果机遥控器","信息群发","防身 器材","老虎/机遥控器","游戏机上分/器","水果机干扰/器 ","刷卡提现","国地税票","个人防身","自我保护器材","低息免担保","商机发布软件","诱鱼器","枪支柜","彩金宏辉","破译","成熟少妇","公司丽人","骚妇","壮阳延时","伟哥","活睾丸","探鱼机","火药钢珠枪","气狗","钢珠枪","火狗","书生商友","投注","美女上门","办证","上门按摩","复制手机卡","电子基盘","疯狂女郎","超级三色","成熟少妇 ","销魂娱乐","夜总会招聘","少妇","情妇","野心","陪台","荤台","素台","爹地","妈咪","妈眯","出街","色情","私人助理","夜场小姐","陪客","夜总会兼职","妓女","男妓","卖淫","嫖客","特殊服务","娱乐场所公关","夜总会公关","高级服务","少爷","小妹","夜场","二奶","男保姆","厅房DJ","个性化服务","VIP服务生","夜生活","陪酒","三陪","按摩女","男招待","女招待","鸭仔","男公关","坐台","世界通","天基权","A-BIKE","贵度","A-bike","原味丝袜","原味内衣","激情视频","汽枪","发票","公章","塑钢土","苍蝇水","迷幻","民运","阻垢分散剂PTP-0100","美国清力","舒野","叶大夫","防暴枪","砒霜","批箱","信石","红矾","真空隐形尺","尺子短信接收器","考试答案","安乐死","电脑家庭教师","收藏虎","狼牙棒","弹弓及其配件","防狼器","吹箭","笔式短信接收器","短信群发器","移动电话拦截","无线微型摄像头","反雷达测速器","枪柜","地磅摇控器","地磅控制器","电子捕鱼器","手机卡窃听器","领带形高清晰摄像机","防身药水","透衣裤透视镜","监听设备","监听手机","游戏机上分器","夜视眼镜","人体器官买卖","他达那非","西力士","赌毯","牌九","赌桌","透视药水","透视眼镜","枪支","弹药","反雷达测速仪","反测速雷达","麻醉药品","精神药品","毒性药品","放射性药品","气枪","氰化物","醋托啡","乙酰阿法甲基芬太尼","醋美沙朵","阿芬太尼","烯丙罗定","阿醋美沙朵","阿法美罗定","阿法美沙朵","阿法甲基芬太尼","阿法甲基硫代芬太尼","阿法罗定","阿尼利定","苄替啶","苄吗啡","倍醋美沙朵","倍他羟基芬太尼","倍他美沙朵","倍他美罗定","倍他罗定","贝齐米特","大麻与大麻树脂","氯尼他秦","古柯叶","可多克辛","罂粟秆浓缩物","三硝基甲苯","海洛因","弓弩","警徽","警用器械","电警棍","警棍","狐狸甩棍","电击器","笔式接收机","反屏蔽短信笔式接收器","新橡皮短信接收机","反屏蔽橡皮短信接收器","钱包式反屏蔽接收器","移动电话拦截系统","反雷达探测机","电子眼喷剂","翻转车牌架","遥控车牌架","上分定位器","枪绳","喷雾直流枪","消防镰刀","自卫器","代开汕头发票","士的宁","甲基麻黄碱","麻黄碱","司来吉兰","普罗林坦","苯丁胺","苯甲吗啉","二甲苯吗啉","苯双甲吗啉","尼可刹米","莫达芬尼","尼可刹咪","司立吉林","苯咯戊烷","麦索卡","美芬丁胺","依替福林","莰苯乙胺","氯苄雷司","卡非多","布罗曼坦","安非他尼","阿米苯唑","二乙胺苯丙酮","艾捉非尼","阿屈非尼","右吗拉米","屈大麻酚","大麻酯","麦角胺咖啡因","可多克辛可多克辛","扎来普隆","地索吗啡","唑吡坦","右吗拉胺","地恩丙胺","乙烯比妥","四氢西泮","二乙噻丁","替马西泮","地芬诺辛","仲丁比妥","二氢埃托啡","吡咯戊酮","普拉西泮","哌苯甲醇","双氢吗啡双氢吗啡","匹那西泮","地美沙朵","芬特明","地美庚醇","苯巴比妥","苯甲曲秦","二甲噻丁","匹莫林","吗苯丁酯","地芬诺酯","奥沙唑仑","奥沙西泮","去甲西泮","硝西泮","地匹哌酮","羟蒂巴酚","尼美西泮","芽子碱","纳布啡及其注射剂","乙甲噻丁","咪达唑仑","甲乙哌酮","依托尼秦","埃托啡","甲苯巴比妥","依托利定","芬太尼","呋替啶","甲丙氨酯","氢可酮","氢吗啡醇","美芬雷司","氢吗啡酮","美达西泮","羟哌替啶","氯甲西泮","异美沙酮","凯托米酮","左美沙芬","左吗拉胺","左芬啡烷","劳拉西泮","左啡诺","氯普唑仑","美他佐辛","美沙酮","美沙酮中间体","甲地索啡","甲二氢吗啡","买卖鲸目","买卖赤颈鹤"," 买卖锦鸡","买卖天鹅","买卖白天鹅","催眠喷雾香水","催情喷雾香水","办假证","爆炸性武器","爆炸性武器模型","反屏蔽接收器","法轮功","枪手代考","手机短信群发平台","力月西","高价火车票","高价转让火车票","美托酮","吗拉胺中间体","去甲左啡诺","去甲吗啡","诺匹哌酮","对氟芬太尼","苯哌利定","匹米诺定","哌腈米特","罂粟壳","丙哌利定","消旋甲啡烷","消旋吗拉胺","消旋啡烷","舒芬太尼","醋氢可酮","蒂巴因","硫代芬太尼","替利定","三甲利定","醋氢可待因","右丙氧芬","双氢可待因","尼可待因","尼二氢可待因","去甲可待因","福尔可定","丙吡兰","乙环利定","乙色胺","麦角二乙胺","甲米雷司","赛洛新","赛洛西宾","咯环利定","二甲氧基甲苯异丙胺","替诺环定","甲氯喹酮","马吲哚","左甲苯丙胺","去氧麻黄碱","甲喹酮","哌醋甲酯","莫达非尼","苯环利定","芬美曲秦","司可巴比妥","齐培丙醇","四氢大麻酚及其立体化学变体","异戊巴比妥","布他比妥","布托啡诺及其注射剂","去甲伪麻黄碱","安钠咖","环已巴比妥","地佐辛及其注射剂","氟硝西泮","格鲁米特","氯氮卓","氯硝西泮","氯氟卓乙酯","氟西泮","哈拉西泮","凯他唑仑","盐酸羟亚胺","电表节电解码器","手机卡服务密码破解软件","汽车车牌隐形喷剂","万能解码器","地磅干拢器","开锁培训","老虎机干扰器","短信，群发 ","透视专用麻将","透视魔术麻将","千术","空卡套现","信用卡分期多倍套现","代开发/票","短信猫","手机监控器","窃听","窃照","哌甲酯","亚砷酸","去乙酰毛花甙丙","氢溴酸后马托品","三氧化二砷","升汞","代开税票","增值税代开","代开上海发票","代開真發票","代開公司發票","代開物流發票","代開商品銷售發票","稅票代開","增值稅代開","代开商业发票","商业发票代开","广告税代开","代开服务发票","服务发票代开","代开餐饮发票","餐饮发票代开","代开票据","票据代开","代开商业通用发票","发票对外代开","代开正规发票","发票正规代开","代开商品销售统一发票","代 开发 票","代开福建发票","服务通用发票","商业零售发票","代开货物发票","催情极品","催情蓝魔","熟睡娇妻","销魂水","日本伟哥","老虎机定位器","水果机摇控器","信用卡套现","3-甲基硫代芬太尼","3-甲基芬太尼","1-甲基-4-苯基-4-哌啶丙酸酯 ","吗啡-Ｎ-氧化物","4-甲基硫基安非他明","上海办理证/票/章","杭州办理证/票/章","河北办理证/票/章","倍他羟基-3-甲基芬太尼","γ-羟丁酸","老虎机上分器","老虎机破解仪器 ","单挑王破解器","电玩上分药水","电子地磅遥控器","电子地磅干扰器","电子磅转发器 ","地磅遥控器","转盘机破解","考试作弊器","奔驰宝马作弊器","透视魔术扑克 ","阿桔片","阿托品","洋地黄毒甙","毛果芸香碱","水杨酸毒扁豆碱","亚砷酸钾","氢溴酸东菪莨碱","士的年","代辦發票","代開發票","代開稅票","代開增值稅","代開深圳發票","提供性服务","昏迷喷雾","听话乖乖水","催情水","强效昏睡液","美国D水","迷幻水","万能遥控上分器","万能下币上分器","奔驰宝马摇控器","手机追踪定位卡","地磅调节器","手机跟踪软件卡","短信拦截录音","手机跟踪器","手机号码定位卡","CT透视仪","透视器","CT透视器","地磅解码器","地磅遥控器  ","迷情药","mi烟","诱骗少女","迷魂烟","少女迷情粉","喷雾催情香水","金宵迷情粉","橡皮短信接收器","春药","催情口服液","女用催情粉剂","无线卫星监听一卡通","上分器","回分器","09高考答案","远距离监听器","汽车干扰器","汽车隐形喷雾","汽车隐形喷剂","纽扣摄像机","防电子眼喷剂 ","无线隐型耳机","运输业发票","透视仪","仿真假钞","监听王","火药制作","法輪功","信号拦截器","出售手枪","法lun功","广告发票","自动翻牌器","翻转牌照架","男女情粉","催情喷雾催眠香水","赌厂提钱机","信用卡取现","隐形眼镜扑克","手机偷听卡","高效春药","万能开锁工具","信用卡分期翻倍","提现/信用卡","信用卡分期提现","信用卡代还提现","供应人体肝脏","信用卡空卡套现","信用卡社保套现","信用卡刷卡消费套现","信用卡换现金 ","信用卡支取现金","信用卡代还款","电表效正器","电表倒转器","电表节电器","电表控制器","电表校准器","电表调校仪","电子表专用调校器","电子表调校仪","大功率调校器","机械表调校器","三相调校器 ","电表调校器  ","考试作弊仪","电子地磅调校仪","地磅调控器","神奇透视器","红外线睹场提款机","电子地磅调校扰器","百乐2号破解器    ","办证 章子 发票","游戏机破解仪器","手机密码破解","代办各类证/件","追债公司","资职类办证","制作伪币","支票兑换现金","游戏破解方案","英语专业四级考前答案","隐形眼镜白光扑克","信用卡翻倍","外用催情","手机鉴听器 ","09年高考答案","09年公务员考试答案   ","2009高考答案","46级考前答案","CT透视王","MBA考试考前答案","白光扑克麻将 ","百家乐破解方法 ","办真毕业证","办上网文凭 ","出售仿真假币","出售仿真假钱","出售仿真人民币","代办香港身份证","代开湖北工业税票","代开统一发票","代考","倒卖火车票","买卖象牙","买卖玳瑁制品","吗哌利定","吗啡甲溴化物","五价氮吗啡衍生物","麦罗啡","尼可吗啡","诺美沙朵","去甲美沙酮","阿片","羟考酮","羟吗啡酮","哌替啶","苯吗庚酮","非那丙胺","非那佐辛","非诺啡烷","普罗庚嗪","瑞芬太尼","布桂嗪","可待因","复方樟脑酊","乙基吗啡","甲羟芬胺","乙芬胺","羟芬胺","六氢大麻酚","副甲氧基安非他明","替苯丙胺","三甲氧基安非他明","苯丙胺","安非拉酮","安咪奈丁","左苯丙胺","右旋芬氟拉明","芬氟拉明","呋芬雷司","喷他佐辛","戊巴比妥","丙己君","阿洛巴比妥","阿普唑仑","阿米雷司","巴比妥","苄非他明","溴西泮","溴替唑仑","丁巴比妥","卡马西泮","氯噻西泮","地洛西泮","乙氯维诺","乙非他明","芬坎法明","利非他明","代開正規發票","代開廣州發票","代開商業發票","代开深圳发票","发票销售","代开定额发票","定额发票代开","服务发票代理","代开普通发票","代开专用发票","代开国税发票","代开物流发票","代开公司发票","代开真发票","代理学历  ","代售仿真假币","代售仿真假钞","代售仿真假钱","代售仿真人民币","催情喷雾","法律专业答案","暗访摄像包","背心式无线耳机","催情乳液","电子表调校器","发行假钞","仿真假币","供应46级证","仿真人民币","消防灭火枪","氯化钡","答案","透视","DVR手表  ","假证","刻章","爆炸","作弊","捕鱼机","捕鱼器","破解","代办","代开","代開","發//票 ","發票","苍蝇王","老虎机","假币","假钞","出售身份证","手枪","催眠","催情","调校器","折刀","火车票","地磅 遥控器 ","点火枪","电鱼器","电子磅称控制器","电子表调校器  ","赌","人体器官","学历","迷情剂","破解仪","烟草","卖肝","任你摆布","收数","清债","追债","密码破解","套现","性服务","乖乖水","空卡翻倍","最小的DV","智能控电王 ","诱骗","隐型强力稀土耳机","遥控信号干扰器","遥控器隐蔽性强","遥控麻将机白光眼镜","遥控麻将机","奔驰宝马 ","笔式摄像机","毕业证","车牌反光","电磁干扰仪","定位追蹤器","赌场搭档","赌场高科器","赌场高科仪器","微波捕鱼器","微型米粒耳机","微型无线耳机","文凭类办证","透衣裤","透视镜","无锅卫星电视","无锅卫星天线","无线耳机短信接收器","五星宏辉机","手机短信拦截卡","手位定位仪","手机侦查探秘","手机隐私揭秘","叶猴","梅花鹿","短信","涉枪","仿真枪","氰化钠","氰化金钾","铁氰化钾","硫氰酸钾","硫氢化钠","氰化钾","氰化银钾","氧化汞","硝酸钡","碘化汞","溴化汞","硝酸汞","醋酸亚汞","硫氰酸汞","硫酸汞","氢化钠","氟化钠","氯化汞","甩棍","买卖鲣鸟","买卖马鹿","买卖水獭","氯口恶唑仑","艾司唑仑","炔已蚁胺","芬普雷司","氟地西泮","代開加工發票","代開上海發票","商業發票代開","代開廣告稅","廣告稅代開","代開服務發票","服務發票代開","代開餐飲發票","餐飲發票代開","代開“電腦版”運輸發票","代開票據","票據代開","代開南京發票","代開工業發票","代開增值稅專用發票","代開商業通用發票","代開番隅增值稅發票","發票對外代開","銷售發票","發票銷售","代開定額發票","服務發票代理","代開普通發票","代開專用發票","代開地稅發票","代開國稅發票","定額發票代開","代开广告税","代开南京发票","代开工业发票","代开增值税专用发票","代开番隅增值税发票","销售发票","代开地税发票","代办发票","代开商品销售发票","代开加工发票","乖乖粉","手机群发广告","手机群发公司","卖棋牌游戏","诈金花","梭哈","手机监","警用器材","防身香水","防身器材","短信群发","群发软件","群发信息","偷听","间谍","短信拦截","手机定","位器","偷啪","手机监视","兼职妹妹","清纯漂亮","学生妹妹","极品美女","风情万种","无锅信号接收天线","小锅卫星信号","卫星信号接收器","强抗干扰","手机监听卡","卫星监听","手机情报局","定位追踪","听手机","搜取证据","定位追踪器","一察到底","卧底密探","卧底软件","跟踪软件","魔卡复制卡","SIM复制卡","香烟","锁定目标","商务短信","开北京餐费","住宿费","机打票","代理各类票据","手机监听","定位器","监听系统","号码随意更改","成人频道","跟踪器","卫星定位器","私家侦探","调查公司","婚姻维权","行踪监控","布苯丙胺","卡西酮","二乙基色胺","二甲氧基安非他明","二甲基色胺","二甲氧基乙基安非他明","二亚甲基双氧安非他明","麦司卡林","甲卡西酮","四氢大麻酚","氯巴占","氯拉卓酸","地西泮","卤沙唑仑","糖脂宁","代开病假条","卫星电视器","暗访包","麻将药水","作弊器","超声波捕鱼机","手机追踪器","手机监听 ","考虑机破解仪器","奔驰宝马作弊器 ","手机智能监听卡","犀利士","信用卡提现","买卖烟草","烟草销售","代开电脑版运输发票","税票代办","公积金取现 ","北京办证","阴茎增大","迷幻药","暗访取证器材","发嘌","解码器  ","干扰器","猎枪","肝脏","卖肾","迷情粉","情粉","智能节电王","伪币","游戏机退币器","隐形耳机","隐私车牌架","隐蔽式语音图像监控摄像机","动情苍蝇粉","苍蝇粉","办理各种证","暗访","追蹤器","微波捕鱼机","微型摄象笔","微型腰带耳机","微型针孔摄象机","卫星监听仪器","卧底监听","无锅平板卫星天线","无锅室内卫星天线","无锅卫星接收器","无锅卫星数字接收机","手表摄像机","手机jian听卡","手机变声器","手机定位","手机跟踪","豚尾猴","偷听器","无锅天线接收机","手机监察器","室内卫星天线","卫星接收机","卫星天线","信号接收处理锅","捕猎器","游戏机定位","游戏机遥控器","水果机干扰/器","遥控地磅","蹭网卡","票据服务","无线隔墙听","成人大片","催q药","各类票据","空卡多倍提现","辦証","妹妹服务","醱票","四六级成绩单","婚姻调查","调查取证","亲密镜头","偷拍","婚外情","重婚取证","死帐","烂账","三角债","防身器","贴现","成绩单办理","百家乐","百乐","成人节目","地磅调控解码器","成人生活频道","点数优惠","录音取证","隐蔽性","纽扣式","口香糖式","高利贷","免抵押","个人诚信贷款","企业抵押贷款","中星九号","商机发布引擎","轉換軟件","诱鱼机","电子商务软件","天助","点金","免费发布商机","最佳营销软件","发，票","商务快车","网络营销软件","手指指","真人龙虎","江苏老虎","上分/器 ","老虎/机","卫星电视安装","卫星电视专业安装 ","调控解码器","卫星信号接收","枪枝柜","拦截","地沟油","fa票","网络营销软","烟感隐蔽型摄像机","刻私章","胎儿性别鉴定","性别鉴定","丝袜小姐","学生妹","闻骚味","性幻想","清醇学妹","办理文凭","群发短信");
	    for(var k=0;k<wordsArrays.length;k++){
	        if (content.indexOf(wordsArrays[k])!=-1){
	            return wordsArrays[k];
	        }
	    }
	    return "";
	}
	function checkUserName(username){
		
		hello.getUsernameByuser(username,callback)
	}
	function callback(date){
		
		alert(date);
	}

window.onload = new function()
{
	 var obj=document.getElementsByName('business_role');     
	         for(var i=0;i<obj.length;i++){     
	         if(obj[i].checked){     
	             alert(obj[i].value);  
	             return obj[i].value;     
	            }     
	        }    
}
function my_submit(){
	document.form.submit();
	document.form.eventSubmit_doRegisteraction.disabled=true;
}