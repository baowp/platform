//=====================================
// 20090104 ר��flashʹ�õĵ�¼�� thinkryzhu

document.domain = "qq.com";

//ҳ����ת�õ�ȫ�ֱ���
var g_Jump_Url_TimeOut_ID = -1;

/*
�޸�obBeginDate������ת�Ŀ�ʼʱ��
�޸�obEndDate  ������ת��ֹͣʱ��
ע��0����һ�£�1������¡����캯��˳��(�ꡢ�¡��ա�Сʱ���֡���)
*/
function inJumpTime()
{
   //ע��:0����һ��
   var obBeginDate = new Date(2009, 9, 28, 15, 00);
   var obEndDate =   new Date(2009, 9, 28, 16, 00);
   var objNow = new Date;
   if (obBeginDate <= objNow && objNow < obEndDate)
   {
        window.top.location.href = "http://minigame.qq.com/act/systemmend.html"
        if (g_Jump_Url_TimeOut_ID != -1)
        {
            clearInterval(g_Jump_Url_TimeOut_ID);
        } 
   }
   else if (objNow < obBeginDate)
   {
        if (g_Jump_Url_TimeOut_ID == -1)
        {
        	g_Jump_Url_TimeOut_ID = setInterval(inJumpTime, 10000);
        }
   }
   else // >=obEndDate
   {
       return;
   }
}

//�ر�Web����
function Close_Web_Service()
{
    //��Ҫ�رյ�Web������ڡ�
    //ֻ��Ҫ�г�����·���ļ򵥼��ɡ�
    //���Url�а��������ַ��������Զ���ת��ϵͳά��ҳ�档
       var arrWebService=["meigui.qq.com"];

       var myUrl = window.location.href;
       for (var ii = 0; ii < arrWebService.length; ++ii)
       {
           if (myUrl.indexOf(arrWebService[ii]) != -1)
           {
	 	        inJumpTime();
                return;
           }
       }  
}    
//Close_Web_Service();

// Ϊ�˱�������, �򵥵�ʹ��ch200901����������ǰ׺
function ch200901Id(id)
{
	return document.getElementById(id);
}

// ������������
function ch200901DumpObject(obj)
{ 
	var strDump = "" ; 
	for (var p in obj)
	{   
		if (typeof(obj[p]) != "function")
		{   
			strDump += p + "=" + obj[p] + "\n";
		}
	}

	trace(strDump);
}

// cookie����
function ch200901GetCookie(name)
{
	var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
	if(arr != null)
	{
		return unescape(arr[2]);
	}
	return "";
}

function ch200901SetCookie(name, value)
{
	document.cookie = name + "=" + value + "; path=/; domain=qq.com";
}

function ch200901DeleteCookie(name)
{
	ch200901SetCookie(name, "");
}

//���URL����
function ch200901GetUrlParams()
{
    var args=new Object();
    if (location.search.length == 0)
    {
    	return args;
    }
    
    var query=location.search.substring(1);//��ȡ��ѯ��
    var pairs=query.split("&");//�ڶ��Ŵ��Ͽ�
    for(var i=0; i < pairs.length; i++ )   
    {   
        var pos=pairs[i].indexOf('=');//����name=value   
            if(pos==-1)   continue;//���û���ҵ�������   
            var argname=pairs[i].substring(0,pos);//��ȡname   
            var value=pairs[i].substring(pos+1);//��ȡvalue   
            args[argname]=unescape(value);//��Ϊ����   
    }
    return args;
}

function ch200901GetUrlParam(strKey)
{
    var objArgs = ch200901GetUrlParams();
    if (typeof objArgs[strKey] != "undefined")
    {
        return objArgs[strKey];
    }
    else
    {
    	return "";
    }
}

//���URL��������ָ���ĵ�URL�������õ�Cookie����ȥ��
function ch200901SetUrlParaToCookie(strKey)
{
    var strValue = ch200901GetUrlParam(strKey);    
    // ch200901SetCookie(strKey, objArgs[strKey]); 
    ch200901SetCookie(strKey, strValue);  
}

// ɾ��cookie
function ch200901Logout(jumpurl, topjump)
{
	ch200901DeleteCookie("uin");
	ch200901DeleteCookie("skey");
    ch200901DeleteCookie("dna_result_key");
    ch200901DeleteCookie("PcacheTime");

	if (jumpurl)
	{
		if (topjump)
		{
			top.location = jumpurl;
		}
		else
		{
			location = jumpurl;
		}
	}
}

// �жϵ�ǰҳ���Ƿ�������
var ch200901WebReady = false;
var ch200901SwfReady = false;
function ch200901IsWebReady()
{
	return ch200901WebReady;
	//return document.readyState == "complete";
}

function ch200901SetWebReady()
{
	ch200901WebReady = true;
}

function ch200901IsSwfReady()
{
	return ch200901SwfReady;
}

function ch200901SetSwfReady()
{
	ch200901SwfReady = true;
}


// ��ȡ������Ĵ��ڳߴ���Ϣ
function ch200901WindowSizeInfo(aobjDoc)
{
    var objDoc = aobjDoc || document;
    var obj = (objDoc.compatMode == "CSS1Compat" ? objDoc.documentElement : objDoc.body);
    this.clientWidth = obj.clientWidth;
    this.clientHeight = obj.clientHeight;
    this.scrollLeft = obj.scrollLeft;
    this.scrollTop = obj.scrollTop;
    this.scrollWidth = obj.scrollWidth;
    this.scrollHeight = obj.scrollHeight;
    this.maxWidth = Math.max(this.clientWidth, this.scrollWidth);
    this.maxHeight = Math.max(this.clientHeight, this.scrollHeight);
}

// ��ʾ���ǲ�
function ch200901ShowCoverDiv(iOffsetLeft, iHeight)
{
	var objCover = ch200901Id("chCoverDiv");
	if (!objCover)
	{
		objCover = document.createElement("div");
    	objCover.id = "chCoverDiv";
		document.body.appendChild(objCover);
	}

	var objStyle = objCover.style;
	objStyle.display = "block";
	objStyle.margin = "0px";
	objStyle.padding = "0px";
	objStyle.top = "0px";
	objStyle.left = "0px";
	objStyle.width = iOffsetLeft + "px";
	objStyle.height = iHeight + "px";
	objStyle.position = "absolute";
	objStyle.zIndex = "3";
	objStyle.background = "#000000";
	objStyle.filter = "alpha(opacity=40)";
	objStyle.opacity = 40/100;
	objStyle.MozOpacity = 40/100;
}

// ���ظ��ǲ�
function ch200901HideCoverDiv()
{
	var objCover = ch200901Id("chCoverDiv");
	if (objCover)
	{
		objCover.style.display = "none";
	}
}

// �����Ի����
// ��Ϊ������Ҫ�����һ���Ĵ�С����������Ϊ���ɼ�
function ch200901CreateDialogDiv(strID, strHtmlData, iOffsetTop, iOffsetLeft, iWidth, iHeight, strTitle)
{
	var objDialog = ch200901Id(strID);
	if (!objDialog)
	{
		objDialog = document.createElement("div");
    	objDialog.id = strID;
		document.body.appendChild(objDialog);
	}

	var objStyle = objDialog.style;
	objStyle.display = "block";
	objStyle.margin = "0px";
	objStyle.padding = "0px";
	objStyle.top = iOffsetTop + "px";
	objStyle.left = iOffsetLeft + "px";
	objStyle.width = iWidth + "px";
	objStyle.height = iHeight + "px";
	objStyle.position = "absolute";
	objStyle.zIndex = "5";
	objStyle.background = "#FFFFFF";
	objStyle.border = "solid #ccc 2px";
	objStyle.visibility = "hidden";	// ��Ϊ������Ҫ�����һ���Ĵ�С����������Ϊ���ɼ�
	objDialog.innerHTML = strHtmlData;
	
	if (strTitle)
	{
		var strTitleID = strID + "_title";
		var objTitle = ch200901Id(strTitleID);
		if (!objTitle)
		{
			objTitle = document.createElement("div");
	    	objTitle.id = strTitleID;
			document.body.appendChild(objTitle);
		}
		
		var objStyle2 = objTitle.style;
		objStyle2.display = "block";
		objStyle2.margin = "0px";
		objStyle2.padding = "0px";
		objStyle2.top = (iOffsetTop - 20) + "px";
		objStyle2.left = iOffsetLeft + "px";
		objStyle2.width = iWidth + "px";
		objStyle2.height = "20px";
		objStyle2.position = "absolute";
		objStyle2.zIndex = "5";
		objStyle2.background = "#FFFFFF";
		objStyle2.border = "solid #ccc 2px";
		objStyle2.algin = "right";
		objStyle2.visibility = "hidden";	// ��Ϊ������Ҫ�����һ���Ĵ�С����������Ϊ���ɼ�
		objTitle.innerHTML = "<div style='float:right; margin-top:4px; margin-right:4px'>"
				+ "<span onclick='ch200901HideDialog(\""+strID+"\")' style='font-size:12px; cursor:hand; color:#000'><b>���ر�</b></span></div>"
				+ "<div style='font-size:12px; margin-top:4px; margin-left:5px'><b>"+strTitle+"</b></div>";

	}
}

// ��ʾ�Ի����
function ch200901ShowDialogDiv(strID)
{
	var objDialog = ch200901Id(strID);
	if (objDialog)
	{
		objDialog.style.display = "block";
		objDialog.style.visibility = "visible";
	}

	var objTitle = ch200901Id(strID + "_title");
	if (objTitle)
	{
		objTitle.style.display = "block";
		objTitle.style.visibility = "visible";
	}
}

// ���ضԻ����
function ch200901HideDialogDiv(strID)
{
	var objDialog = ch200901Id(strID);

	if (objDialog)
	{
		objDialog.style.display = "none";
	}
	
	var objTitle = ch200901Id(strID + "_title");
	if (objTitle)
	{
		objTitle.style.display = "none";
	}
}

// ��ʾ�Ի���
function ch200901ShowDialog(strID, strHtmlData, iWidth, iHeight, strAlignH, iOffsetH, strAlignV, iOffsetV, strTitle)
{
	strAlignH = strAlignH || "center";
	strAlignV = strAlignV || "center";
	iWidth = iWidth || 0;
	iHeight = iHeight || 0;
	iOffsetH = iOffsetH || 0;
	iOffsetV = iOffsetV || 0;
	
	// ����ƫ����
	var objSizeInfo = new ch200901WindowSizeInfo();
	var iOffsetTop = 0;
	var iOffsetLeft = 0;
	if (strAlignH == "center")
	{
		iOffsetLeft = objSizeInfo.scrollLeft + (objSizeInfo.clientWidth - iWidth) / 2 + iOffsetH;
	}
	else if (alignH == "right")
	{
		iOffsetLeft = objSizeInfo.scrollLeft + (objSizeInfo.clientWidth - iWidth) + iOffsetH - 23;
	}
	else
	{
		iOffsetLeft = objSizeInfo.scrollLeft + iOffsetH;
	}
	
	if (strAlignV == "center")
	{
		iOffsetTop = objSizeInfo.scrollTop + (objSizeInfo.clientHeight - iHeight) / 2 + iOffsetV;
	}
	else if (strAlignV == "bottom")
	{
		iOffsetTop = objSizeInfo.scrollTop + (objSizeInfo.clientHeight - iHeight) + iOffsetV - 33;
	}
	else
	{
		iOffsetTop = objSizeInfo.scrollTop + iOffsetV;
	}
	
	// ��ʾ���ǲ�
	ch200901ShowCoverDiv(objSizeInfo.maxWidth, objSizeInfo.maxHeight);
	
	// ��ʾ�Ի����
	ch200901CreateDialogDiv(strID, strHtmlData, iOffsetTop, iOffsetLeft, iWidth, iHeight, strTitle);
}

// �رնԻ���
function ch200901HideDialog(strID)
{
	ch200901HideDialogDiv(strID);
	ch200901HideCoverDiv();
}

function ch200901NeedLogin(strAlignH, iOffsetH, strAlignV, iOffsetV)
{
    var objFlashBG = ch200901Id("divbg");	
	  if (objFlashBG)
	  {
	  	objFlashBG.style.display = "";
	  }
	  
    var objFlashDiv = ch200901Id("flashAvatar");	
		if (objFlashDiv)
		{
			objFlashDiv.style.display = "none";			
		}			
		
		var str = '<iframe id="login_iframe" allowTransparency="true" marginwidth="0" marginheight="0" hspace="0"' +
			          ' vspace="0" frameborder="0" scrolling="no" style="border:none;width:373px;height:284px" ' +
			  		  ' src="http://ui.ptlogin2.qq.com/cgi-bin/login?appid=7000201&target=self&f_url=loginerroralert'+
			  		  '&s_url=http://meigui.qq.com/login/flashlogin/loginok.html' + 
			  		  '&qlogin_jumpname=jump&qlogin_param=' + escape("u1="+escape(top.location))
			  		  + '"></iframe>';
		ch200901ShowDialog("login_div", str, 373, 284, strAlignH, iOffsetH, strAlignV, iOffsetV);
		ch200901ShowDialogDiv("login_div");
}

function ch200901NeedVerify(strUrl, strAlignH, iOffsetH, strAlignV, iOffsetV)
{
    var objFlashBG = ch200901Id("divbg");
      if (objFlashBG)
      {
        objFlashBG.style.display = "";
      }

    var objFlashDiv = ch200901Id("flashAvatar");
    if (objFlashDiv)
    {
        objFlashDiv.style.display = "none";
    }
    var str = '<iframe id="verify_iframe" allowTransparency="true" marginwidth="0" marginheight="0" hspace="0"' + 
              ' vspace="0" frameborder="0" scrolling="no" style="border:none;width:373px;height:290px" ' + 
              'src="' + strUrl+'"></iframe>';
    ch200901ShowDialog("verify_div", str, 373, 284, strAlignH, iOffsetH, strAlignV, iOffsetV);
    ch200901ShowDialogDiv("verify_div");
}

function ch200901NotifyFlashVerifyOK(strFlashID)
{
	ch200901HideDialog("verify_div");
    var obj = ch200901Id(strFlashID);
    if (obj)
    {
        obj.OnVerifyOK();
    }
}

// ��¼��Ҫ��ҳ��ʵ���������
function ptlogin2_onResize(iWidth, iHeight)
{
	// ��ø���Div����
	var objDiv = ch200901Id("login_div");
	var objFrame = ch200901Id("login_iframe");
	if (objDiv && objFrame)
	{
		//�������ô�Сע�⣬һ��Ҫ��px������firefox��������
		objDiv.style.width = iWidth + "px";
		objDiv.style.height = iHeight + "px";

		objFrame.style.width = iWidth + "px";
		objFrame.style.height = iHeight + "px";	
	}
	ch200901ShowDialogDiv("login_div");
}

// ��¼��Ҫ��ҳ��ʵ���������
function ptlogin2_onLogin()
{
//	ch200901HideDialog("login_div");
	ch200901HideDialog("verify_div");
	return true;
}

// ��¼��Ҫ��ҳ��ʵ���������
function ptlogin2_onClose()
{
	ch200901HideDialog("login_div");
	ch200901HideDialog("verify_div");
	window.location.href = window.location.href;
}


function ch200908NeedLogin(strAlignH, iOffsetH, strAlignV, iOffsetV)
{	  		  
	var str = '<iframe id="login_iframe" allowTransparency="true" marginwidth="0" marginheight="0" hspace="0"' +
			          ' vspace="0" frameborder="0" scrolling="no" style="border:none;width:373px;height:284px" ' +
			  		  ' src="http://ui.ptlogin2.qq.com/cgi-bin/login?appid=7000201&target=self&f_url=loginerroralert'+
			  		  '&s_url=http://meigui.qq.com/login/flashlogin/loginok0908.html' + 
			  		  '&qlogin_jumpname=jump&qlogin_param=' + escape("u1="+escape(top.location))
			  		  + '"></iframe>';
			  		  
	ch200901ShowDialog("login_div", str, 373, 284, strAlignH, iOffsetH, strAlignV, iOffsetV);
	ch200901ShowDialogDiv("login_div");
}

function ch200908NeedVerify(strUrl, strAlignH, iOffsetH, strAlignV, iOffsetV)
{
    var str = '<iframe id="verify_iframe" allowTransparency="true" marginwidth="0" marginheight="0" hspace="0"' + 
              ' vspace="0" frameborder="0" scrolling="no" style="border:none;width:373px;height:290px" ' + 
              'src="' + strUrl+'"></iframe>';
    ch200901ShowDialog("verify_div", str, 373, 284, strAlignH, iOffsetH, strAlignV, iOffsetV);
    ch200901ShowDialogDiv("verify_div");
}

function ch200908NotifyFlashLoginOK(strFlashID)
{
    ch200901HideDialog("login_div");
    var obj = ch200901Id(strFlashID);
    if (obj)
    {
        obj.OnLoginOK();
    }
}

function ch200908NotifyFlashVerifyOK(strFlashID)
{
	ch200901HideDialog("verify_div");
    var obj = ch200901Id(strFlashID);
    if (obj)
    {
        obj.OnVerifyOK();
    }
}
