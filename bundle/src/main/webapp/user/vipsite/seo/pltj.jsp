<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form id="form1" name="form1" action="addurl.php">
<table width="774" cellspacing="1" cellpadding="3" border="0"
	bgcolor="#CCCCCC" class="tab">
	<tbody>
		<tr>
			<td width="60" bgcolor="#F1F1F1" align="center">
			<p>标题：</p>
			</td>
			<td width="319" bgcolor="#FFFFFF"><input type="text"
				class="input_txt" size="40" value="Title" onchange="cgtitle()"
				id="title" name="title"></td>
				<td bgcolor="#FFFFFF"></td>
		</tr>
		<tr>
			<td bgcolor="#F1F1F1" align="center">
			<p>网址：</p>
			</td>
			<td bgcolor="#FFFFFF"><input type="text" size="40"
				class="input_txt" value="http://${url}"
				onkeydown="if(event.keyCode==13)startRequest()" url="true" id="url"
				name="url"></td>
			<td  bgcolor="#FFFFFF" colspan="3">向各大搜索引擎提交网址，可以加快网站被搜索引擎收录。
一个网站只需提交一次（首页），搜索引擎会自动收录网页。
不保证一定能收录您提交的网站</td>
		</tr>
		<tr>
			<td bgcolor="#F1F1F1" align="center">
			<p>选择：</p>
			</td>
			<td bgcolor="#FFFFFF">
			<table width="277" cellspacing="1" border="0">
				<tbody>
					<tr>
						<td width="268" colspan="4">
						<div align="left"><input type="checkbox" checked="checked"
							value="1" style="border: 0" id="baidu" name="cbaidu"> 百度
						<input type="checkbox" checked="checked" value="1"
							style="border: 0" id="google" name="cgoogle"> Google</div>
						</td>
					</tr>
					<tr>
						<td height="1" bgcolor="#999999" colspan="4"></td>
					</tr>
					<tr>
						<td colspan="4">
						<div align="left"><input type="checkbox"
							onclick="show('msn','msnform')" value="1" style="border: 0"
							id="msn" name="cmsn"> MSN <input type="checkbox"
							onclick="show('yahoo','yahooform')" value="1" style="border: 0"
							id="yahoo" name="cyahoo"> 雅虎 <input type="checkbox"
							value="1" style="border: 0" id="aiwen" name="caiwen"> 爱问
						<input type="checkbox" onclick="show('tom','tomform')" value="1"
							style="border: 0" id="tom" name="ctom"> TOM <input
							type="checkbox" onclick="show('zhongsou','zhongsouform')"
							value="1" style="border: 0" id="zhongsou" name="czhongsou">
						中搜 <input type="checkbox" onclick="show('youdao','youdaoform')"
							value="1" style="border: 0" id="youdao" name="cyoudao">
						有道</div>
						</td>
					</tr>
					<tr>
						<td height="1" bgcolor="#999999" colspan="4"></td>
					</tr>
					<tr>
						<td colspan="4">
						<div align="left"><input type="checkbox"
							onclick="checkall(this)" style="border: 0" value="checkbox"
							name="checkbox"> 全部选定</div>
						</td>
						<td></td>
					</tr>
				</tbody>
			</table>
			</td>
			<td bgcolor="#FFFFFF"></td>
		</tr>
		<tr>
			<td bgcolor="#FFFFFF" align="center" class="ent" colspan="2"><input
				type="button" onclick="formsubmit()" id="postc" value="按住Ctrl + 单击 "
				name="Submit3"> <input type="reset" value=" 重置 "
				name="Submit4"></td>
				<td bgcolor="#FFFFFF"></td>
		</tr>
	</tbody>
</table>
</form>

<div class="con3 left clear">
<table width="375" cellspacing="1" cellpadding="4" border="0"
	bgcolor="#D8D8D8" class="tab">
	<tbody>
		<tr bgcolor="#FFFFFF" style="display: none" id="baiduform">
			<td width="51%">
			<form target="_blank"
				action="http://utility.baidu.com/addurl/apply.php" method="post"
				name="baidu" id="baidu">百度 <input type="hidden" url="true"
				id="url" name="url" value="http://51archetype.com"> <input
				type="hidden" id="title" name="title2"> <input type="hidden"
				value="AERTV1ASABQ=" name="ivc"> <input type="hidden"
				size="6" value="ERAE" name="code"> <img hspace="3"
				border="0" align="absmiddle"
				src="http://utility.baidu.com/addurl/image_validate_code.php?key=AERTV1ASABQ="
				style="display: none"> <input type="submit" value="提交"
				name="Submit2"></form>
			</td>
		</tr>
		<tr style="display: none" id="googleform">
			<td width="49%" bgcolor="#FFFFFF">
			<form id="google" target="_blank" name="google" method="get"
				action="http://www.google.com/addurl">google <input
				type="hidden" maxlength="256" size="10" url="true" name="q"
				value="h51archetype.comcc.net"> <input type="hidden"
				maxlength="256" size="40" value="" name="dq"> <input
				type="submit" name="submit3" value="登录"></form>
			</td>
		</tr>
		<tr style="display: none" id="yahooform">
			<form id="yahoo" target="_blank" name="yahoo" method="post"
				action="http://search.help.cn.yahoo.com/h4_4.html"></form>
			<td bgcolor="#FFFFFF">
			<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tbody>
					<tr>
						<td width="80">[雅虎]</td>
						<td width="249"><img height="60" width="218"
							src="http://ab.login.cn.yahoo.com/img/.jpg"> <br>
						验证码：<input type="hidden" value="" name="secdata"> <input
							type="text" maxlength="32" size="10" name="secword"> <input
							type="hidden" name="sub_url" size="30" url="true" id="sub_url"
							va51archetype.com://51archetype.com"> <input type="hidden" value="提交"
							name="action"></td>
					</tr>
				</tbody>
			</table>
			</td>

		</tr>
		<tr style="display: none" id="msnform">
			<form id="msn" target="_blank" name="msn" method="post"
				action="http://cn.bing.com/webmaster/SubmitSitePage.aspx"></form>
			<td bgcolor="#FFFFFF">
			<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tbody>
					<tr>
						<td width="80">[MSN]</td>
						<td width="249"><img
							src="http://cn.bing.com/webmaster/Captcha.aspx?encanswer=">
						<br>
						验证码：<input maxlength="1024" value="" size="10" name="useranswer">
						<input type="hidden" maxlength="1024" size="30"
			51archetype.com="http://51archetype.com" name="url" url="true" id="url">
						<input type="hidden" value="" name="encanswer"> <input
							type="hidden" value="提交" name="submit_url"></td>
					</tr>
				</tbody>
			</table>
			</td>

		</tr>
		<tr style="display: none" id="aiwenform">
			<td bgcolor="#FFFFFF">
			<form onsubmit="return submited();" id="aiwen" target="_blank"
				name="aiwen" method="post"
				action="http://feedback.sina.com.cn/websubmit/Donews.php">爱问
			<input type51archetype.com value="http://51archetype.com" url="true"
				name="f_suggest_title"> <input type="hidden" value="0"
				name="searchtype"> <input type="hidden" size="44" value=""
				class="w100" name="f_suggest_str"> <input type="hidden"
				size="50" maxlength="128" name="Getmsl"> <input
				type="hidden" value="" class="btn w100" size="50" name="f_email">
			<input type="hidden" value="MjQ5NjE=" name="authnum"> <input
				type="hidden" value="true" name="havenum"> <input
				type="hidden" align="middle" maxlength="5" size="6" value="24961"
				name="inputnum"> <img border="0" align="top"
				style="display: none" src="keyimg.php?authnum=MjQ5NjE="> <input
				type="submit" value="提交" class="btn" name="submit8"> <input
				type="hidden" name="f_check"> <input type="hidden"
				name="f_username" maxlength="64"> <input type="hidden"
				name="f_passwd" maxlength="64"> <input type="hidden"
				name="f_productid" value="18"> <input type="hidden"
				name="f_appid" value="34"> <input type="hidden" name="f_key"
				value="_searchkey"> <input type="hidden" name="f_succ_html"
				value="http://iask.com/guest/guest_cg.html"> <input
				type="hidden" name="f_fail_html"
				value="http://iask.com/guest/guest_sb.html"> <input
				type="hidden" name="f_url"> <input type="hidden"
				name="f_flag" value="0"></form>
			</td>
		</tr>
		<tr style="display: none" id="sougouform">
			<form id="sougou" target="_blank" name="sougou" method="post"
				action="http://db.sohu.com/regurl/regform.asp?step=create"></form>
			<td bgcolor="#FFFFFF">
			<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tbody>
					<tr>
						<td width="80">[搜狗]</td>
						<td width="76" align="right">验证码：</td>
						<td width="70"><input type="text" size="10" name="GetCode"></td>
						<td width="174"><input type="hidden" url="true" name="URL"
	51archetype.come="50" value="http://51archetype.com"> <input type="hidden"
							value="" name="SiteName" size="50"></td>
					</tr>
				</tbody>
			</table>
			</td>

		</tr>
		<tr style="display: none" id="tomform">
			<form id="tom" target="_blank" name="tom" method="post"
				action="http://search.tom.com/tools/weblog/sub.php"></form>
			<td bgcolor="#FFFFFF">
			<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tbody>
					<tr>
						<td width="80">[TOM]</td>
						<td width="76" align="right">验证码：</td>
						<td width="70"><input type="text" size="10" name="check_code"></td>
						<td width="172"><input type="hidden" name="sub_u51archetype.com		url="true" value="http://51archetype.com"> <input type="hidden"
							value="提交" name="submit6"> <img border="0" alt=""
							src="http://search.tom.com/tools/weblog/kingc.php"></td>
					</tr>
				</tbody>
			</table>
			</td>

		</tr>
		<tr style="display: none" id="zhongsouform">
			<form onsubmit="return validateForm()" id="zhongsou" target="_blank"
				name="zhongsou" method="post"
				action="http://ads.zhongsou.com/register/pageOK.jsp"></form>
			<td bgcolor="#FFFFFF">
			<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tbody>
					<tr>
						<td width="80">[中搜]</td>
						<td width="76" align="right">验证码：</td>
						<td width="70"><input type="text" size="10" name="checkcode"></td>
						<td width="174"><iframe height="35" frameborder="0"
							width="70" scrolling="No"
							src="http://ads.zhongsou.com/register/pageimageservlet.srv"></iframe>
						<input type="hidden" url="t51archetype.com="inputUrl"
							value="http://51archetype.com"> <input type="hidden" value="提交"
							name="cmd" class="int"></td>
					</tr>
				</tbody>
			</table>
			</td>

		</tr>
		<tr style="display: none" id="youdaoform">
			<form id="youdao" target="_blank" name="youdao" method="post"
				action="http://tellbot.youdao.com/report_thanks"></form>
			<td bgcolor="#FFFFFF">
			<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tbody>
					<tr>
						<td width="80">[有道]</td>
						<td width="76" align="right">验证码：</td>
						<td width="70"><input type="text" size="10" name="checkCode"></td>
						<td width="171"><img id="checkimg"
							src="http://tellbot.youdao.com/showimg"> <input
							type="hidden" size="40" name="comment"> <input
							type="hidden" value="0" name="product"> <input
							ty51archetype.comn" url="true" name="url" value="http://51archetype.com">
						<input type="hidden" value="提交" name="buttom"></td>
					</tr>
				</tbody>
			</table>
			</td>

		</tr>
	</tbody>
</table>
</div>
<script type="text/javascript">
//按ctrl+enter提交 
function ctrlenter(event){ 
   if(event.ctrlKey && event.keyCode == 13) { 
	document.getElementById("postc").click();
   } 
} 
function cgtitle(){
   document.baidu.title.value=document.form1.title.value;
   document.google.dq.value=document.form1.title.value;  
   document.aiwen.f_suggest_str.value=document.form1.title.value;
   document.youdao.comment.value=document.form1.title.value;
}
/*function cgurl(){
   document.baidu.url.value=document.form1.url.value;
   document.google.q.value=document.form1.url.value;
   document.yahoo.sub_url.value=document.form1.url.value;
   document.aiwen.f_suggest_title.value=document.form1.url.value;   
   document.msn.url.value=document.form1.url.value;  
   document.zhongsou.inputUrl.value=document.form1.url.value;
   document.tom.sub_url.value=document.form1.url.value;
   document.youdao.url.value=document.form1.url.value;
}*/


function addEvent(eventHandler){
	var tags = document.getElementsByTagName('input');
	for(var i=0;i<tags.length;i++)
	{
		if(tags[i].getAttribute('url') == 'true')
		{
			if(tags[i].addEventListener)
			{
				tags[i].addEventListener('keyup',eventHandler,true);
			}
			else
			{
				tags[i].attachEvent('onkeyup',eventHandler);
			}
		}
	}
}
function addInput(e){
	var obj = e.target ? e.target : e.srcElement;
	var tags = document.getElementsByTagName('input');
	for(var i=0;i<tags.length;i++)
	{
		if(tags[i].getAttribute('url') == 'true'&&tags[i]!=obj)
		{
			tags[i].value = obj.value;
		}
	}
}
window.onload = function(){
	addEvent(addInput);
}


function show(t,v){
  if (document.getElementById(t).checked==true)
      document.getElementById(v).style.display="";
  else
      document.getElementById(v).style.display="none";
  //document.getElementById(v).style.display="";
}
function formsubmit(){
  if (document.form1.title.value=="") alert("请正确填写网站的名称！");
  else if (document.form1.url.value=="http://") alert("请正确填写网站的地址!");
  else {
　   if (document.form1.cyahoo.checked==true) {document.yahoo.submit();}
     if (document.form1.cbaidu.checked==true) {document.baidu.submit();}
     if (document.form1.cgoogle.checked==true) {document.google.submit();}
	 if (document.form1.czhongsou.checked==true) {document.zhongsou.submit();}
	 if (document.form1.caiwen.checked==true) {document.aiwen.submit();}
	 if (document.form1.cmsn.checked==true) {document.msn.submit();}
	 if (document.form1.ctom.checked==true) {document.tom.submit();}
	 if (document.form1.cyoudao.checked==true) {document.youdao.submit();}
   }
}
function checkall(ch) {
 var v = ch.checked;
 var f = ch.form;
 f.cbaidu.checked = v;
 f.cgoogle.checked = v;
 f.cmsn.checked = v;
 f.cyahoo.checked = v; 
 f.czhongsou.checked = v;
 f.ctom.checked = v; 
 f.caiwen.checked = v; 
 f.cyoudao.checked = v;
 if (v==true) d="";
 else d="none";
 document.getElementById('yahooform').style.display=d;
 document.getElementById('zhongsouform').style.display=d;
}

</script> <!--Google Analytics Begin--> <script type="text/javascript">
  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-2325717-4']);
  _gaq.push(['_setDomainName', '.onexin.net']);
  _gaq.push(['_trackPageview']);
  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();
</script> <!--Google Analytics End-->
</body>
</html>