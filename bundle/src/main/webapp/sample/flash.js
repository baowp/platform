//
// ��ʾflash��js.
// !��������ҳ��������swfobject.js!
//

// ��ʾflash�ĺ���
// sUrl swf��URL
function ch200901InitFlash(sUrl, sId, iWidth, iHeight, sPar, sTips, sLoadingUrl)
{
	if (!document.getElementById(sPar) ||
		!document.getElementById(sTips))
	{
		return;
	}
	
	var sFlash = '<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,124,0" id="'+sId+'" width="'+iWidth+'" height="'+iHeight+'">';
	if (sLoadingUrl.length > 0)
	{
		sFlash += '<param name="movie" value="'+sLoadingUrl+'" />';
		sFlash += '<param name="flashvars" value="realsrc='+ sUrl +'" />';
	}
	else
	{
		sFlash += '<param name="movie" value="'+sUrl+'" />';
	}
	sFlash += '<param name="quality" value="high" />';
	//sFlash += '<param name="wmode" value="Opaque">';
	sFlash += '<param name="allowScriptAccess" value="always" />';
	if (sLoadingUrl.length > 0)
	{
		sFlash += '<embed src="'+sLoadingUrl+'" flashvars="realsrc='+sUrl+'" quality="high" width="'+iWidth+'" height="'+iHeight+'" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" allowScriptAccess="always" name="'+sId+'"><\/embed>';//wmode="Opaque"  
	}
	else
	{
		sFlash += '<embed src="'+sUrl+'" quality="high" width="'+iWidth+'" height="'+iHeight+'" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash"  allowScriptAccess="always" name="'+sId+'"><\/embed>';//wmode="Opaque"
	}
	sFlash += '<\/object>';
	var oPar = document.getElementById(sPar);
	oPar.innerHTML = sFlash;

	if (!ch200901DetectFlash()) // �汾��ͨ��
	{
		oPar.style.visibility = "hidden";
		var shtml='<br><br><p>Flash Player�汾���ͣ�����Ϊ������</p>';
		shtml+='<p><img src="http://qqgamecdnimg.qq.com/login/flashlogin/loading_icon.gif" alt="������" />���Ժ�</p>';
		if (window.navigator.appName.toUpperCase().indexOf("NETSCAPE") >= 0) 
		{
			shtml+='��Ҳ����ѡ��<a class="em" target="_blank" href="http://fpdownload.macromedia.com/get/flashplayer/current/licensing/win/install_flash_player_10.exe">�ֶ����ظ���</a>';
		}
		else
		{
			shtml+='��Ҳ����ѡ��<a class="em" target="_blank" href="http://fpdownload.macromedia.com/get/flashplayer/current/licensing/win/install_flash_player_10_active_x.exe">�ֶ����ظ���</a>';
		}
		shtml+='<p>��װ�ɹ���<B>��ˢ��ҳ��</B></p>';
		var oTips = document.getElementById(sTips);
		oTips.innerHTML = shtml;
		oTips.style.visibility = "";
	}
}

function ch200908InitFlash(sUrl, sId, iWidth, iHeight, sPar, sTips, sLoadingUrl)
{
	if (!document.getElementById(sPar) ||
		!document.getElementById(sTips))
	{
		return;
	}
	
	var sFlash = '<object id="testloadingswf" classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,124,0" id="'+sId+'" width="'+iWidth+'" height="'+iHeight+'">';
	if (sLoadingUrl.length > 0)
	{
		sFlash += '<param name="movie" value="'+sLoadingUrl+'" />';
		sFlash += '<param name="flashvars" value="realsrc='+ sUrl +'" />';
	}
	else
	{
		sFlash += '<param name="movie" value="'+sUrl+'" />';
	}
	sFlash += '<param name="quality" value="high" />';
	sFlash += '<param name="wmode" value="Opaque">';
	sFlash += '<param name="allowScriptAccess" value="always" />';
	if (sLoadingUrl.length > 0)
	{
		sFlash += '<embed src="'+sLoadingUrl+'" flashvars="realsrc='+sUrl+'" quality="high" width="'+iWidth+'" height="'+iHeight+'" wmode="Opaque" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" allowScriptAccess="always" name="'+sId+'"><\/embed>';//wmode="Opaque"  
	}
	else
	{
		sFlash += '<embed src="'+sUrl+'" wmode="Opaque" quality="high" width="'+iWidth+'" height="'+iHeight+'" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash"  allowScriptAccess="always" name="'+sId+'"><\/embed>';//wmode="Opaque"
	}
	sFlash += '<\/object>';
	var oPar = document.getElementById(sPar);
	oPar.innerHTML = sFlash;

	if (!ch200901DetectFlash()) // �汾��ͨ��
	{
		oPar.style.visibility = "hidden";
		var shtml='<br><br><p>Flash Player�汾���ͣ�����Ϊ������</p>';
		shtml+='<p><img src="http://qqgamecdnimg.qq.com/login/flashlogin/loading_icon.gif" alt="������" />���Ժ�</p>';
		if (window.navigator.appName.toUpperCase().indexOf("NETSCAPE") >= 0) 
		{
			shtml+='��Ҳ����ѡ��<a class="em" target="_blank" href="http://fpdownload.macromedia.com/get/flashplayer/current/licensing/win/install_flash_player_10.exe">�ֶ����ظ���</a>';
		}
		else
		{
			shtml+='��Ҳ����ѡ��<a class="em" target="_blank" href="http://fpdownload.macromedia.com/get/flashplayer/current/licensing/win/install_flash_player_10_active_x.exe">�ֶ����ظ���</a>';
		}
		shtml+='<p>��װ�ɹ���<B>��ˢ��ҳ��</B></p>';
		var oTips = document.getElementById(sTips);
		oTips.innerHTML = shtml;
		oTips.style.visibility = "";
	}
}



// ���flash���
function ch200901DetectFlash()
{
	var ver = new deconcept.PlayerVersion([9,0,124]);
	var thisVer = deconcept.SWFObjectUtil.getPlayerVersion();
	return thisVer.versionIsValid(ver);
}