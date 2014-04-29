String.prototype.Trim=function(){return this.replace(/(^\s*)|(\s*$)/g,"")};function getElementsByClassName(f,e){var a=new Array();var c=document.getElementsByTagName(e);for(var b,d=0;(elem=c[d]);d++){if(hasClass(f,elem)){a[a.length]=elem}}return a}function hasClass(c,b){var a;var d=b.className;a=new RegExp("(?:^|\\s+)"+c+"(?:\\s+|$)");return a.test(d)?1:0}function hasClassElement(c,d,b){var f=d;var e=f.getElementsByTagName(b);for(var a=0;a<e.length;a++){if(e[a].className==c){return e[a]}}return false}function stopDefault(a){var a=window.event||a;if(a.preventDefault){a.preventDefault()}else{a.returnValue=false}return false}function addLoadEvent(a){var b=window.onload;if(typeof window.onload!="function"){window.onload=a}else{window.onload=function(){b();a()}}}function __clear(a){clearTimeout(a);clearInterval(a);return null}function __attach_event(a,b){if(window.addEventListener){window.addEventListener(a,b,false)}else{if(window.attachEvent){window.attachEvent("on"+a,b)}}}function __domReady(a){if(__domReady.done){return a()}if(__domReady.timer){__domReady.ready.push(a)}else{__attach_event("load",__isDOMReady);__domReady.ready=[a];__domReady.timer=setInterval(__isDOMReady,100)}}function __isDOMReady(){if(__domReady.done){return false}if(document&&document.getElementsByTagName&&document.getElementById&&document.body){__clear(__domReady.timer);__domReady.timer=null;for(var a=0;a<__domReady.ready.length;a++){__domReady.ready[a]()}__domReady.ready=null;__domReady.done=true}};
/*	SWFObject v2.2 beta1 <http://code.google.com/p/swfobject/>
is released under the MIT License <http://www.opensource.org/licenses/mit-license.php>
*/
var swfobject=function(){var D="undefined",r="object",S="Shockwave Flash",W="ShockwaveFlash.ShockwaveFlash",q="application/x-shockwave-flash",R="SWFObjectExprInst",x="onreadystatechange",O=window,j=document,t=navigator,T=false,U=[h],o=[],N=[],I=[],l,Q,E,B,J=false,a=false,n,G,m=true,M=function(){var aa=typeof j.getElementById!=D&&typeof j.getElementsByTagName!=D&&typeof j.createElement!=D,ah=t.userAgent.toLowerCase(),Y=t.platform.toLowerCase(),ae=Y?/win/.test(Y):/win/.test(ah),ac=Y?/mac/.test(Y):/mac/.test(ah),af=/webkit/.test(ah)?parseFloat(ah.replace(/^.*webkit\/(\d+(\.\d+)?).*$/,"$1")):false,X=!+"\v1",ag=[0,0,0],ab=null;if(typeof t.plugins!=D&&typeof t.plugins[S]==r){ab=t.plugins[S].description;if(ab&&!(typeof t.mimeTypes!=D&&t.mimeTypes[q]&&!t.mimeTypes[q].enabledPlugin)){T=true;X=false;ab=ab.replace(/^.*\s+(\S+\s+\S+$)/,"$1");ag[0]=parseInt(ab.replace(/^(.*)\..*$/,"$1"),10);ag[1]=parseInt(ab.replace(/^.*\.(.*)\s.*$/,"$1"),10);ag[2]=/[a-zA-Z]/.test(ab)?parseInt(ab.replace(/^.*[a-zA-Z]+(.*)$/,"$1"),10):0}}else{if(typeof O.ActiveXObject!=D){try{var ad=new ActiveXObject(W);if(ad){ab=ad.GetVariable("$version");if(ab){X=true;ab=ab.split(" ")[1].split(",");ag=[parseInt(ab[0],10),parseInt(ab[1],10),parseInt(ab[2],10)]}}}catch(Z){}}}return{w3:aa,pv:ag,wk:af,ie:X,win:ae,mac:ac}}(),k=function(){if(!M.w3){return}if((typeof j.readyState!=D&&j.readyState=="complete")||(typeof j.readyState==D&&(j.getElementsByTagName("body")[0]||j.body))){f()}if(!J){if(typeof j.addEventListener!=D){j.addEventListener("DOMContentLoaded",f,false)}if(M.ie&&M.win){j.attachEvent(x,function(){if(j.readyState=="complete"){j.detachEvent(x,arguments.callee);f()}});if(O==top){(function(){if(J){return}try{j.documentElement.doScroll("left")}catch(X){setTimeout(arguments.callee,0);return}f()})()}}if(M.wk){(function(){if(J){return}if(!/loaded|complete/.test(j.readyState)){setTimeout(arguments.callee,0);return}f()})()}s(f)}}();function f(){if(J){return}try{var Z=j.getElementsByTagName("body")[0].appendChild(C("span"));Z.parentNode.removeChild(Z)}catch(aa){return}J=true;var X=U.length;for(var Y=0;Y<X;Y++){U[Y]()}}function K(X){if(J){X()}else{U[U.length]=X}}function s(Y){if(typeof O.addEventListener!=D){O.addEventListener("load",Y,false)}else{if(typeof j.addEventListener!=D){j.addEventListener("load",Y,false)}else{if(typeof O.attachEvent!=D){i(O,"onload",Y)}else{if(typeof O.onload=="function"){var X=O.onload;O.onload=function(){X();Y()}}else{O.onload=Y}}}}}function h(){if(T){V()}else{H()}}function V(){var X=j.getElementsByTagName("body")[0];var aa=C(r);aa.setAttribute("type",q);var Z=X.appendChild(aa);if(Z){var Y=0;(function(){if(typeof Z.GetVariable!=D){var ab=Z.GetVariable("$version");if(ab){ab=ab.split(" ")[1].split(",");M.pv=[parseInt(ab[0],10),parseInt(ab[1],10),parseInt(ab[2],10)]}}else{if(Y<10){Y++;setTimeout(arguments.callee,10);return}}X.removeChild(aa);Z=null;H()})()}else{H()}}function H(){var ag=o.length;if(ag>0){for(var af=0;af<ag;af++){var Y=o[af].id;var ab=o[af].callbackFn;var aa={success:false,id:Y};if(M.pv[0]>0){var ae=c(Y);if(ae){if(F(o[af].swfVersion)&&!(M.wk&&M.wk<312)){w(Y,true);if(ab){aa.success=true;aa.ref=z(Y);ab(aa)}}else{if(o[af].expressInstall&&A()){var ai={};ai.data=o[af].expressInstall;ai.width=ae.getAttribute("width")||"0";ai.height=ae.getAttribute("height")||"0";if(ae.getAttribute("class")){ai.styleclass=ae.getAttribute("class")}if(ae.getAttribute("align")){ai.align=ae.getAttribute("align")}var ah={};var X=ae.getElementsByTagName("param");var ac=X.length;for(var ad=0;ad<ac;ad++){if(X[ad].getAttribute("name").toLowerCase()!="movie"){ah[X[ad].getAttribute("name")]=X[ad].getAttribute("value")}}P(ai,ah,Y,ab)}else{p(ae);if(ab){ab(aa)}}}}}else{w(Y,true);if(ab){var Z=z(Y);if(Z&&typeof Z.SetVariable!=D){aa.success=true;aa.ref=Z}ab(aa)}}}}}function z(aa){var X=null;var Y=c(aa);if(Y&&Y.nodeName=="OBJECT"){if(typeof Y.SetVariable!=D){X=Y}else{var Z=Y.getElementsByTagName(r)[0];if(Z){X=Z}}}return X}function A(){return !a&&F("6.0.65")&&(M.win||M.mac)&&!(M.wk&&M.wk<312)}function P(aa,ab,X,Z){a=true;E=Z||null;B={success:false,id:X};var ae=c(X);if(ae){if(ae.nodeName=="OBJECT"){l=g(ae);Q=null}else{l=ae;Q=X}aa.id=R;if(typeof aa.width==D||(!/%$/.test(aa.width)&&parseInt(aa.width,10)<310)){aa.width="310"}if(typeof aa.height==D||(!/%$/.test(aa.height)&&parseInt(aa.height,10)<137)){aa.height="137"}j.title=j.title.slice(0,47)+" - Flash Player Installation";var ad=M.ie&&M.win?"ActiveX":"PlugIn",ac="MMredirectURL="+O.location.toString().replace(/&/g,"%26")+"&MMplayerType="+ad+"&MMdoctitle="+j.title;if(typeof ab.flashvars!=D){ab.flashvars+="&"+ac}else{ab.flashvars=ac}if(M.ie&&M.win&&ae.readyState!=4){var Y=C("div");X+="SWFObjectNew";Y.setAttribute("id",X);ae.parentNode.insertBefore(Y,ae);ae.style.display="none";(function(){if(ae.readyState==4){ae.parentNode.removeChild(ae)}else{setTimeout(arguments.callee,10)}})()}u(aa,ab,X)}}function p(Y){if(M.ie&&M.win&&Y.readyState!=4){var X=C("div");Y.parentNode.insertBefore(X,Y);X.parentNode.replaceChild(g(Y),X);Y.style.display="none";(function(){if(Y.readyState==4){Y.parentNode.removeChild(Y)}else{setTimeout(arguments.callee,10)}})()}else{Y.parentNode.replaceChild(g(Y),Y)}}function g(ab){var aa=C("div");if(M.win&&M.ie){aa.innerHTML=ab.innerHTML}else{var Y=ab.getElementsByTagName(r)[0];if(Y){var ad=Y.childNodes;if(ad){var X=ad.length;for(var Z=0;Z<X;Z++){if(!(ad[Z].nodeType==1&&ad[Z].nodeName=="PARAM")&&!(ad[Z].nodeType==8)){aa.appendChild(ad[Z].cloneNode(true))}}}}}return aa}function u(ai,ag,Y){var X,aa=c(Y);if(M.wk&&M.wk<312){return X}if(aa){if(typeof ai.id==D){ai.id=Y}if(M.ie&&M.win){var ah="";for(var ae in ai){if(ai[ae]!=Object.prototype[ae]){if(ae.toLowerCase()=="data"){ag.movie=ai[ae]}else{if(ae.toLowerCase()=="styleclass"){ah+=' class="'+ai[ae]+'"'}else{if(ae.toLowerCase()!="classid"){ah+=" "+ae+'="'+ai[ae]+'"'}}}}}var af="";for(var ad in ag){if(ag[ad]!=Object.prototype[ad]){af+='<param name="'+ad+'" value="'+ag[ad]+'" />'}}aa.outerHTML='<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"'+ah+">"+af+"</object>";N[N.length]=ai.id;X=c(ai.id)}else{var Z=C(r);Z.setAttribute("type",q);for(var ac in ai){if(ai[ac]!=Object.prototype[ac]){if(ac.toLowerCase()=="styleclass"){Z.setAttribute("class",ai[ac])}else{if(ac.toLowerCase()!="classid"){Z.setAttribute(ac,ai[ac])}}}}for(var ab in ag){if(ag[ab]!=Object.prototype[ab]&&ab.toLowerCase()!="movie"){e(Z,ab,ag[ab])}}aa.parentNode.replaceChild(Z,aa);X=Z}}return X}function e(Z,X,Y){var aa=C("param");aa.setAttribute("name",X);aa.setAttribute("value",Y);Z.appendChild(aa)}function y(Y){var X=c(Y);if(X&&X.nodeName=="OBJECT"){if(M.ie&&M.win){X.style.display="none";(function(){if(X.readyState==4){b(Y)}else{setTimeout(arguments.callee,10)}})()}else{X.parentNode.removeChild(X)}}}function b(Z){var Y=c(Z);if(Y){for(var X in Y){if(typeof Y[X]=="function"){Y[X]=null}}Y.parentNode.removeChild(Y)}}function c(Z){var X=null;try{X=j.getElementById(Z)}catch(Y){}return X}function C(X){return j.createElement(X)}function i(Z,X,Y){Z.attachEvent(X,Y);I[I.length]=[Z,X,Y]}function F(Z){var Y=M.pv,X=Z.split(".");X[0]=parseInt(X[0],10);X[1]=parseInt(X[1],10)||0;X[2]=parseInt(X[2],10)||0;return(Y[0]>X[0]||(Y[0]==X[0]&&Y[1]>X[1])||(Y[0]==X[0]&&Y[1]==X[1]&&Y[2]>=X[2]))?true:false}function v(ac,Y,ad,ab){if(M.ie&&M.mac){return}var aa=j.getElementsByTagName("head")[0];if(!aa){return}var X=(ad&&typeof ad=="string")?ad:"screen";if(ab){n=null;G=null}if(!n||G!=X){var Z=C("style");Z.setAttribute("type","text/css");Z.setAttribute("media",X);n=aa.appendChild(Z);if(M.ie&&M.win&&typeof j.styleSheets!=D&&j.styleSheets.length>0){n=j.styleSheets[j.styleSheets.length-1]}G=X}if(M.ie&&M.win){if(n&&typeof n.addRule==r){n.addRule(ac,Y)}}else{if(n&&typeof j.createTextNode!=D){n.appendChild(j.createTextNode(ac+" {"+Y+"}"))}}}function w(Z,X){if(!m){return}var Y=X?"visible":"hidden";if(J&&c(Z)){c(Z).style.visibility=Y}else{v("#"+Z,"visibility:"+Y)}}function L(Y){var Z=/[\\\"<>\.;]/;var X=Z.exec(Y)!=null;return X&&typeof encodeURIComponent!=D?encodeURIComponent(Y):Y}var d=function(){if(M.ie&&M.win){window.attachEvent("onunload",function(){var ac=I.length;for(var ab=0;ab<ac;ab++){I[ab][0].detachEvent(I[ab][1],I[ab][2])}var Z=N.length;for(var aa=0;aa<Z;aa++){y(N[aa])}for(var Y in M){M[Y]=null}M=null;for(var X in swfobject){swfobject[X]=null}swfobject=null})}}();return{registerObject:function(ab,X,aa,Z){if(M.w3&&ab&&X){var Y={};Y.id=ab;Y.swfVersion=X;Y.expressInstall=aa;Y.callbackFn=Z;o[o.length]=Y;w(ab,false)}else{if(Z){Z({success:false,id:ab})}}},getObjectById:function(X){if(M.w3){return z(X)}},embedSWF:function(ab,ah,ae,ag,Y,aa,Z,ad,af,ac){var X={success:false,id:ah};if(M.w3&&!(M.wk&&M.wk<312)&&ab&&ah&&ae&&ag&&Y){w(ah,false);K(function(){ae+="";ag+="";var aj={};if(af&&typeof af===r){for(var al in af){aj[al]=af[al]}}aj.data=ab;aj.width=ae;aj.height=ag;var am={};if(ad&&typeof ad===r){for(var ak in ad){am[ak]=ad[ak]}}if(Z&&typeof Z===r){for(var ai in Z){if(typeof am.flashvars!=D){am.flashvars+="&"+ai+"="+Z[ai]}else{am.flashvars=ai+"="+Z[ai]}}}if(F(Y)){var an=u(aj,am,ah);if(aj.id==ah){w(ah,true)}X.success=true;X.ref=an}else{if(aa&&A()){aj.data=aa;P(aj,am,ah,ac);return}else{w(ah,true)}}if(ac){ac(X)}})}else{if(ac){ac(X)}}},switchOffAutoHideShow:function(){m=false},ua:M,getFlashPlayerVersion:function(){return{major:M.pv[0],minor:M.pv[1],release:M.pv[2]}},hasFlashPlayerVersion:F,createSWF:function(Z,Y,X){if(M.w3){return u(Z,Y,X)}else{return undefined}},showExpressInstall:function(Z,aa,X,Y){if(M.w3&&A()){P(Z,aa,X,Y)}},removeSWF:function(X){if(M.w3){y(X)}},createCSS:function(aa,Z,Y,X){if(M.w3){v(aa,Z,Y,X)}},addDomLoadEvent:K,addLoadEvent:s,getQueryParamValue:function(aa){var Z=j.location.search||j.location.hash;if(Z){if(/\?/.test(Z)){Z=Z.split("?")[1]}if(aa==null){return L(Z)}var Y=Z.split("&");for(var X=0;X<Y.length;X++){if(Y[X].substring(0,Y[X].indexOf("="))==aa){return L(Y[X].substring((Y[X].indexOf("=")+1)))}}}return""},expressInstallCallback:function(){if(a){var X=c(R);if(X&&l){X.parentNode.replaceChild(l,X);if(Q){w(Q,true);if(M.ie&&M.win){l.style.display="block"}}if(E){E(B)}}a=false}}}}();
function newtab_init(){
if(document.getElementById("newtab_title")){
var newtab_title = document.getElementById("newtab_title");
var newtab_more = document.getElementById("newtab_more");
newtab_title.onmouseover = function(){
newtab_more.style.display = "block";
}
newtab_more.onmouseover = function(){
this.style.display = "block";
}
newtab_more.onmouseout = function(){
this.style.display = "none";
}
}
}
/**
* @fileoverview ��������(ó��ͨ��)WEBǰ��Ӧ��.
* 2007.6.13
* @author:aliued-wd zhujunbiao
* @version 0.1
*/
$ = function(el){
return document.getElementById(el);
}
/**
* )չ���鷽��add
* @param {Object} obj
*/
Array.prototype.add = function(obj){
var add_flag = true;
for(var i=0;i<this.length;i++){
if(this[i]==obj){
add_flag = false;
break;
}
}
if(add_flag==true){
this[this.length] = obj;
}
}
if (typeof AliEvent == "undefined") {
var AliEvent = {};
}
(function(){
AliEvent = {
/**
* �¼�����.
* @param {Object} el ������Ķ���.
* @param {Object} eventType �¼���������.
* @param {Object} fn �¼���������.
*/
addListener: function(el,eventType,fn){
if(el.addEventListener){
el.addEventListener(eventType,fn,false);
}else if(el.attachEvent){
el.attachEvent("on" + eventType,fn);
}else{
el["on"+eventType] = fn;
}
}
};
})();
/**
* �����װ����.
* @param online ��������.
* @param size ͼƬ�ߴ�.
* @param uid �û�id.
* @param imgObj ͼƬ����.
* @param eventObj �¼�����.
* @param docObj �İ�����.
* @param onlineDoc �����İ�.
* @param offlineDoc �������İ�.
* @param telonlineDoc �ֻ������İ�.
* @param onlineAlt ������ʾ�İ�.
* @param offlineAlt ������ʾ�İ�.
* @param telonlineAlt �ֻ�������ʾ�İ�.
* @param siteid ��վǰ׺.
* @param isencrypt �Ƿ����.
*/
function Alitalkparam(){
this.online = 0;
this.size = 16;
this.uid = null;
this.imgObj = null;
this.eventObjs = new Array();
this.docObj = null;
this.onlineDoc = "����������,���Ϻ���Ǣ̸!";
this.offlineDoc = "�����ڲ�������,���������Ϣ��!";
this.telonlineDoc = "���ֻ�����,���Ϻ���Ǣ̸!";
this.notInstalledDoc = "δ��װó��ͨ";
this.onlineAlt = "����������,���Ϻ���Ǣ̸!";
this.offlineAlt = "�����ڲ�������,���������Ϣ��!";
this.telonlineAlt = "���ֻ�����,���Ϻ���Ǣ̸!";
this.notInstalledAlt = "δ��װó��ͨ";
this.moreProperties = "";
this.siteid = "cnalichn";
this.isencrypt = false;
this.verify = 0;//��Ϊ���ѵ�ʱ���Ƿ���Ҫ��֤
this.gid = 0;//���ѷ���ID
this.fromUid = '';//��������ID
}
var OnLine = 0;
var online = new Array();
var alitalkVersion = 5;
if (typeof Alitalk == "undefined") {
var Alitalk = {};
}
/**
* ��������(ó��ͨ��)WEBǰ��Ӧ�ÿ��ƾ�̬��.
*/
(function(){
Alitalk = {
/**
* �������ת�����ַ���;��������ж϶���û���ó��ͨ״̬.
* @param {Object} arr
*/
arrToString:function(arr){
var tempStr="";
var encryptFlag = false;
for(var i=0;i<arr.length;i++){
if(arr[i].isencrypt==true){
encryptFlag = true;
}
tempStr = tempStr+arr[i].uid+";"
}
if(tempStr.length>0){
tempStr = tempStr.substring(0,tempStr.length-1);
}
if(encryptFlag==true){
tempStr = tempStr +"&encrypt=1";
}
return tempStr;
},
/**
* ��ʼ������ó��ͨ״̬.
* @param {Object} param �����װ����.
*/
initSingleStat:function(param){
if(param.uid!=null){
if(param.isencrypt){
document.write("<script src='http://amos.im.alisoft.com/userstatus3.aw?uid="+param.uid+"&encrypt=1&site=cnalichn'><"+"/"+"script>");
}else{
document.write("<script src='http://amos.im.alisoft.com/userstatus3.aw?uid="+param.uid+"&site=cnalichn'><"+"/"+"script>");
}
}
},
/**
* ��ʼ�����ó��ͨ״̬.
* @param {Object} arr
*/
initMultStat:function(arr){
document.write("<script src='http://amos.im.alisoft.com/muliuserstatus.aw?uids="+this.arrToString(arr)+"&site=cnalichn'><"+"/"+"script>");
},
/**
* ��ñ�ʾAlitalk����״̬��ͼƬURL
* @param {Object} parm �����װ����.
*/
getAlitalkImgSrc:function(parm){
if(parm.online==0||parm.online==2||parm.online==6){
if(parm.size==16){
return "http://img.china.alibaba.com/others/images/myt_offline.gif";
}else if(parm.size==24){
return "http://img.china.alibaba.com/images/cn/market/trade/list/070423/list_ww_off.gif";
}else if(parm.size==32){
return "http://img.china.alibaba.com/images/buyer/list/list_mytlogo_offline.gif";
}else{
return "http://img.china.alibaba.com/others/images/myt_offline.gif";
}
}else if(parm.online==1){
if(parm.size==16){
return "http://img.china.alibaba.com/others/images/myt_online.gif";
}else if(parm.size==24){
return "http://img.china.alibaba.com/images/cn/market/trade/list/070423/list_ww_on.gif";
}else if(parm.size==32){
return "http://img.china.alibaba.com/images/buyer/list/list_mytlogo_online.gif";
}else{
return "http://img.china.alibaba.com/others/images/myt_online.gif";
}
}else if(parm.online==4||parm.online==5){
if(parm.size==16){
return "http://img.china.alibaba.com/others/images/myt_online_mobile.gif";
}else if(parm.size==26){
return "http://img.china.alibaba.com/images/buyer/list/myt_26_sms.gif";
}else if(parm.size==24){
return "http://img.china.alibaba.com/images/cn/market/trade/list/070423/list_ww_phone.gif";
}else if(parm.size==32){
return "http://img.china.alibaba.com/images/buyer/list/myt_32_sms.gif";
}else{
return "http://img.china.alibaba.com/others/images/myt_online_mobile.gif";
}
}else{
return "http://img.china.alibaba.com/others/images/myt_offline.gif";
}
},
/**
* ��õ�ǰ״̬�µ���ʾ�İ�.
* @param {Object} parm �����װ����.
*/
getAlt:function(parm){
if(parm.online==0||parm.online==2||parm.online==6){
return parm.offlineAlt;
}else if(parm.online==1){
return parm.onlineAlt;
}else if(parm.online==4||parm.online==5){
return parm.telonlineAlt;
}else{
return "";
}
},
/**
* ��õ�ǰ״̬�µ���ʾ�İ�.
* @param {Object} parm �����װ����.
*/
getDoc:function(parm){
if(parm.online==0||parm.online==2||parm.online==6){
return parm.offlineDoc;
}else if(parm.online==1){
return parm.onlineDoc;
}else if(parm.online==4||parm.online==5){
return parm.telonlineDoc;
}else{
return "";
}
},
/**
* ����ó��ͨ��ʾͼƬ����.
* @param {Object} parm �����װ����.
*/
setImgSrc:function(parm){
if(parm.imgObj!=null){
parm.imgObj.src=this.getAlitalkImgSrc(parm);
parm.imgObj.alt=this.getAlt(parm);
}
},
/**
* ����ó��ͨ��ʾ�İ�.
* @param {Object} parm �����װ����.
*/
setDoc:function(parm){
if(parm.docObj!=null){
parm.docObj.innerHTML=this.getDoc(parm);
}
},
/**
* �����¼�����.
* @param {Object} parm �����װ����.
*/
setEvent:function(parm){
for(var i=0;i<parm.eventObjs.length;i++){
if(!parm.isencrypt){
AliEvent.addListener(parm.eventObjs[i],'click',function(){Alitalk.openAliwangwang(parm)});
}
}
},
setImg:function(parm){
this.setImgSrc(parm);
},
/**
* ����ó��ͨ��ز���.
* @param {Object} parm �����װ����.
*/
setAlitalk:function(parm){
this.setDoc(parm);
this.setEvent(parm);
this.setImg(parm);
},
/**
* ��ص���ó��ͨ״̬����.
* @param {Object} parm �����װ����.
*/
addListener:function(parm){
parm.online = OnLine;
this.setAlitalk(parm);
},
/**
* ��ض��ó��ͨ״̬����.
* @param {Object} parmArr �����װ����.
*/
addListenerMult:function(parmArr){
for(var i=0;i<online.length;i++){
if(parmArr[i]){
parmArr[i].online = online[i];
this.setAlitalk(parmArr[i]);
}
}
},
/**
* �����������촰��.
* @param {Object} parm �����װ����.
*/
openAliwangwang:function(parm){
if(parm.siteid=="cnalichn"){
this.openAlitalk(parm);
}else{
this.openWangwang(parm);
}
},
/**
* �ж��Ƿ��Ѿ���װó��ͨ.
*/
isInstallAltalk:function(){
var obj5,obj6;
try{
obj5 = new ActiveXObject("Ali_Check.InfoCheck");
}catch(e){
obj5 = null;
}
if(obj5!=null){
alitalkVersion = 5;
return true;
}
try{
obj6 = new ActiveXObject("aliimx.wangwangx");
}catch(e){
obj6 = null;
}
if (obj6 != null) {
alitalkVersion = 6;
return true;
}
if(null!=obj5||null!=obj6){
return true;
}else{
return false;
}
},
/**
* �ж��Ƿ��Ѿ���װ�Ա�����.
*/
isInstallWangwang:function(){
var obj5,obj6;
try{
obj5 = new ActiveXObject("angWangX.WangWangObj");
}catch(e){
obj5 = null;
}
if (obj5 != null) {
alitalkVersion = 5;
return true;
}
try{
obj6 = new ActiveXObject("aliimx.wangwangx");
}catch(e){
obj6 = null;
}
if (obj6 != null) {
alitalkVersion = 6;
return true;
}
return false;
},
/**
* ��ó��ͨ���촰��.
* @param {Object} parm
*/
openAlitalk:function(parm){
if(this.isInstallAltalk()){
if(parm.online==4){
if(alitalkVersion==5){
window.location = "Alitalk:SendSms?"+parm.uid+"&siteid=cnalichn&status="+parm.online+parm.moreProperties;
}else{
window.location = "aliim:smssendmsg?touid=cnalichn"+parm.uid+parm.moreProperties;
}
}else{
if(alitalkVersion==5){
window.location = "Alitalk:SendIM?"+parm.uid+"&siteid=cnalichn&status="+parm.online+parm.moreProperties;
}else{
window.location = "aliim:sendmsg?touid=cnalichn"+parm.uid+"&siteid=cnalichn&status="+parm.online+parm.moreProperties;
}
}
}else{
this.downloadAlitalk();
}
},
/**
* ��Ϊ����
* @param {Object} parm
*/
addContact:function(parm){
if(this.isInstallAltalk()){
if(alitalkVersion==5){
window.location = "Alitalk:AddContact?uid="+parm.uid+"&siteid=cnalichn";
}else{
window.location = "aliim:addcontact?uid=&touid=cnalichn"+parm.uid+"&gid="+parm.gid+"&verify="+parm.verify+parm.moreProperties;
}
}else{
this.downloadAlitalk();
}
},
getFocus:function(){
document.body.focus();
},
/**
* �Զ���¼
*/
autoLogin:function(){
if(this.isInstallAltalk()){
if(alitalkVersion==5){
document.write ("<iframe id='alitalkIframe' onload='Alitalk.getFocus()' src='alitalk:MyAlibaba?-hideframe' frameborder=no width=0 height=0 border=0 marginwidth=0 marginheight=0></iframe>");
}else{
document.write ("<iframe id='alitalkIframe' onload='Alitalk.getFocus()' src='aliim:login' frameborder=no width=0 height=0 border=0 marginwidth=0 marginheight=0></iframe>");
}
}
},
autoLogin2:function(){
if(this.isInstallAltalk()){
if(alitalkVersion==5){
window.location.href="alitalk:";
}else{
window.location.href="aliim:login";
}
}
},
/**
* ���Ա��������촰��.
* @param {Object} parm
*/
openWangwang:function(parm){
if(this.isInstallWangwang()){
window.location = "wangwang:SendIM?"+parm.uid+"&siteid=cnalichn&status="+parm.online+parm.moreProperties;
}else{
this.downloadWangwang();
}
},
/**
* ����ó��ͨ����ҳ��.
*/
downloadAlitalk:function(){
window.target="_blank";
window.open("http://alitalk.alibaba.com.cn/");
},
/**
* �����Ա���������ҳ��.
*/
downloadWangwang:function(){
window.target="_blank";
window.open("http://www.taobao.com/wangwang/index.php");
},
run:function(from){
if(this.isInstallAltalk()){
window.location = "Alitalk:" + from
}
}
}
})();
function addFriend(uid){
var alitalkparam = new Alitalkparam();
alitalkparam.uid = uid;
Alitalk.addContact(alitalkparam);
}
function openSendWindow(uid,status,moreProperties){
var alitalkparam = new Alitalkparam();
alitalkparam.uid = uid;
if(status) alitalkparam.online = status;
if(moreProperties) alitalkparam.moreProperties = moreProperties;
Alitalk.openAlitalk(alitalkparam);
}
/**
* �������
* @param {Object} uid
*/
function checkId(uid){
openSendWindow(uid);
}
/**
* ʹ��web������ĵ���
* δ��װ����ͻ���ʱ�ᵯ��web������
* @param {Object} uid
*/
function checkIdForWebWW(uid){
//�ж��û��Ƿ�װ��ó��ͨ����װ�ľ͵���ó��ͨ�ͻ��˶Ի�����û��װ���web��ó��ͨ
if(Alitalk.isInstallAltalk()){
openSendWindow(uid);
}else{
AsyncScript.script("http://china.alibaba.com/misc/login_status.htm",
function(){
openWebWW(loginStatus,uid);
});
}
}
/**
* ��web��ó��ͨ
* @param {Object} uid
* @param {Object} loginStatus
*/
function openWebWW(loginStatus,uid){
if(loginStatus==1){
//��¼��ʱ���webWW
document.getElementById("altalkWindow").style.display = "";
centralize(document.getElementById("altalkWindow"));
document.webwwform.memberid.value = getRandomMemberid();
document.webwwform.targetid.value = "cnalichn" + uid;
document.webwwform.submit();
}else if(loginStatus==0){
//δ��¼���ȵ�¼Ȼ���ٴ�webWW
document.getElementById("altalkWindow").style.display = "";
centralize(document.getElementById("altalkWindow"));
document.webwwform.action = getWebWWLoginURL(uid);
document.webwwform.submit();
document.getElementById("needLogin").value = "1";
}else{
//�������ԭ4���߼�
openSendWindow(uid);
}
}
/**
* �õ�web��ó��ͨ��¼t��
* @param {Object} uid
*/
function getWebWWLoginURL(uid){
var loginURL = "http://china.alibaba.com/member/signin.htm";
var webWWURL = "http://onlineww.im.alisoft.com/wangwang/webim.jsp";
var memberidVal = getRandomMemberid();
var targetidVal = "cnalichn"+uid;
var initiativeVal = "1";
var siteFlagVal = "cnalichn";
var openVal = "1";
return  loginURL+"?done="+webWWURL
+"%3Fmemberid%3D"+memberidVal
+"%26targetid%3D"+targetidVal
+"%26initiative%3D"+initiativeVal
+"%26siteFlag%3D"+siteFlagVal
+"%26open%3D"+openVal;
}
/**
* �õ�web��ó��ͨ�����û���
* "m"+12λ���������
*/
function getRandomMemberid(){
var max = 999999999999;
var min = 100000000000;
return "m"+Math.ceil(Math.random()*(max-min)+min);
}
function closeWebWW(){
document.getElementById('altalkWindow').style.display = 'none';
//������¼��Ҫˢ����ҳ��4��֤cookie�ĵ�¼��Ϣ���Զ�ȡ��
if(document.getElementById("needLogin").value == "1"){
window.location.reload();
}
}
/**
* �õ�web��ó��ͨ��λ��
*/
function getWinScroll(){
var t, l, w, h;
if (document.documentElement && document.documentElement.scrollTop) {
t = document.documentElement.scrollTop;
l = document.documentElement.scrollLeft;
w = document.documentElement.scrollWidth;
h = document.documentElement.scrollHeight;
} else if (document.body) {
t = document.body.scrollTop;
l = document.body.scrollLeft;
w = document.body.scrollWidth;
h = document.body.scrollHeight;
}
return { t: t, l: l, w: w, h: h };
}
/**
* ����web��ó��ͨ��λ�õ���Ļ�м�
*/
function centralize(obj){
var windowScroll = new getWinScroll;
var top = windowScroll.t+(windowScroll.h-parseInt(obj.offsetHeight))/2-275;
if(top <= 0){
top = 100;
}
obj.style.top = top+"px";
obj.style.left = windowScroll.l+(windowScroll.w-parseInt(obj.offsetWidth))/2+"px";
}
/**
*
* @param {Object} alitalkparam param����
* @param {String} url1param ó��ͨ���ĵ�һ��URL����ֵ
* @param {String} url2param ó��ͨ���ĵڶ���URL����ֵ
* @param {String} gid offerId,����ó��ͨ���촰���ұ�OFFER��ʾ
*/
function addMoreProperties(alitalkparam,url1param,url2param,gid){
var url1Str="&url1=http://amis1.sh1.china.alibaba.com/potentialContact.dll?";
var url2Str="&url2=http://stat.china.alibaba.com/feedbackfromalitalk.html?";
var gidStr="&gid=";
var brefer="#refer="
var moreProp="";
if(gid && gid.length > 0) moreProp=gidStr+gid;
if (url1param && url1param.length > 0) moreProp=moreProp+url1Str+url1param;
if (url2param && url2param.length > 0)
{
var cosite = "";
try{
cosite = document.cookie.match(/track_cookie[^;]*cosite=(\w+)/)[1];
}
catch(e){}
if(cosite.length > 0){
url2param=url2param+"#fromsite=" + cosite;
}
var r=encodeURI(document.URL);
r= r.replace(/&/g,"$");//��Ҫȫ���滻
url2param = url2param+brefer+r;
var d = new Date();
url2param=url2param+"#time="+d.getTime();
moreProp=moreProp+url2Str+url2param;
}
alitalkparam.moreProperties = moreProp;
}
function addMorePropertiesSearch(url1param,url2param,gid){
var url1Str="&url1=http://amis1.sh1.china.alibaba.com/potentialContact.dll?";
var url2Str="&url2=http://stat.china.alibaba.com/feedbackfromalitalk.html?";
var gidStr="&gid=";
var brefer="#refer="
var moreProp="";
if(gid && gid.length > 0) moreProp=gidStr+gid;
if (url1param && url1param.length > 0) moreProp=moreProp+url1Str+url1param;
if (url2param && url2param.length > 0)
{
var cosite = "";
try{
cosite = document.cookie.match(/track_cookie[^;]*cosite=(\w+)/)[1];
}
catch(e){}
if(cosite.length > 0){
url2param=url2param+"#fromsite=" + cosite;
}
var r=encodeURI(document.URL);
r= r.replace(/&/g,"$");//��Ҫȫ���滻
url2param = url2param+brefer+r;
var d = new Date();
url2param=url2param+"#time="+d.getTime();
moreProp=moreProp+url2Str+url2param;
}
return moreProp;
}
/*
* ����˵��ѡ����������
* ����	actionUrl:����Ŀ��ҳ�桡��tracelog:���ٲ���
* ����ֵ��	��
* ʱ�䣺2005-5-12
*modify
*ʱ��:20061109
*˵��:����getElementById
*modify
*ʱ��:20061123
*˵��:�ṹ������3���
*/
var searchActivedItem=1;
var tracelogStr="";
var otherParamStr="";
var searchFormObj = null;
var tracelogInput = null;
//�ṹ������
function SB_OnBlur_x(ctrlObj,ctrlMenuID, event){
if(searchActivedItem == 1){
return SB_OnBlur(ctrlObj,ctrlMenuID, event)
}else{
return true;
}
}
function SB_OnKeyDown_x(ctrlObj,ctrlMenuID, event){
if(searchActivedItem == 1){
return SB_OnKeyDown(ctrlObj,ctrlMenuID, event)
}else{
return true;
}
}
function SB_OnKeyUp_x(ctrlObj,ctrlMenuID, event){
if(searchActivedItem == 1){
return SB_OnKeyUp(ctrlObj,ctrlMenuID, event)
}else{
return true;
}
}
function searchInit(num,tracelog,otherParam){
tracelogStr = tracelog;
otherParamStr = otherParam;
document.searchform.method=="get";
if(document.getElementById("searchform")!=null){
searchFormObj = document.getElementById("searchform");
}else{
if(document.getElementsByName("searchform").length!=0){
searchFormObj = document.getElementsByName("searchform")[0];
}
}
if(document.searchform.tracelog){
}else{
tracelogInput = document.createElement("input");
tracelogInput.setAttribute("name","tracelog");
tracelogInput.setAttribute("type","hidden");
document.searchform.appendChild(tracelogInput);
}
doclick(document.getElementById("node"+num),num);
}
function doclick(srcObj,searchID){
var tabList = srcObj.parentNode.getElementsByTagName("li");
if(srcObj.className=="activedTab")return;
for(var i=0;i<tabList.length;i++){
if(tabList[i].className=="activedTab")tabList[i].className="nTab";
}
searchActivedItem = searchID;
srcObj.className = "activedTab";//TAB�л�
if(document.getElementById("linkKwords"))document.getElementById("linkKwords").innerHTML = linkKeywords[searchID-1].surl//���Źؼ��ָ�ֵ
var v = searchFormObj.keywords.value;
if(trim(v) == "" || v.substring(0,3) =="������"){searchFormObj.keywords.value = data[searchID-1].title;searchFormObj.keywords.title = data[searchID-1].title}
}
function checkform(){
var v = trim(searchFormObj.keywords.value);
if(v.length > 100){
alert("������Ĺؼ��ֹ�");
return false;
}
if(v == ""  || v.substring(0,3) =="������") {
alert("������ؼ��֣�");
return false;
}
var s ="&";
if((data[searchActivedItem-1].surl).indexOf("?")==-1)s="?";
if(searchActivedItem == 11){
var action_url = data[searchActivedItem-1].surl + "?keywords="+searchFormObj.keywords.value+"&action=BusinessFriendSearchBar&event_submit_do_searchFriendBar=true&searchType=1&tracelog="+tracelogStr+data[searchActivedItem-1].tracelog+otherParamStr;}
else{
var action_url = data[searchActivedItem-1].surl + s + "keywords=" + searchFormObj.keywords.value + "&tracelog="+tracelogStr+data[searchActivedItem-1].tracelog+otherParamStr;}
searchFormObj.action = action_url; //��form��action��ֵ
if(tracelogInput!=null)tracelogInput.value=tracelogStr+data[searchActivedItem-1].tracelog;//��form��tracelog��ֵ
}
/*
* menu������ѹf����
* author:peng.zhoup
* date:2009-12-08
*/
// ��̬����script��ǩ����
function loadScript(callback, src, charset) {
var src = src || "http://test.menu.china.alibaba.com:25100/home/get_navigate_menu.htm";
var charset = charset || "GB2312";
var script = document.createElement('script');
script.setAttribute('language', 'javascript');
if (charset) {
script.setAttribute('charset', charset);
}
script.setAttribute('src', src);
document.getElementsByTagName("head")[0].appendChild(script);
if(document.all) {
script.onreadystatechange = function() {
if(this.readyState == 4 || this.readyState == 'complete' || this.readyState == 'loaded') {
callback();
}
};
} else {
script.onload = function() {
callback();
};
}
}
function loadScriptCallback(){
}
/*
* ����˵��ȥ��ͷβ�ո�
* ����	�ַ�
* ����ֵ��	��
* ʱ�䣺2005-5-12
*/
function trim(inputString) {
return inputString.replace(/^ +/,"").replace(/ +$/,"");
}
function focusit(o){
if("������"==o.value.substring(0,3)){
o.value = "";
}
}
function blurit(o){
if(""==o.value){
o.value =o.title;
}
}
/*
* ����˵��ȡcookieֵ
* ����	cookie�ֶ���
* ����ֵ��	cookieֵ
* ʱ�䣺2005-5-12
*/
function getCookie(sName) {
var aCookie = document.cookie.split("; ");
for (var i=0; i < aCookie.length; i++)
{
var aCrumb = aCookie[i].split("=");
if (sName == aCrumb[0])
return unescape(aCrumb[1]);
}
return null;
}
var data = [
{id:1,name:"��Ӧ",surl:"http://search.china.alibaba.com/search/offer_search.htm" , title:"�����������Ȥ�Ĳ�Ʒ��ƣ�" , tracelog:"searchbuyer"},
{id:2,name:"��",surl:"http://search.china.alibaba.com/search/search.htm" , title:"�����������Ȥ�Ĳ�Ʒ��ƣ�", tracelog:"searchsale"},
{id:3,name:"��˾",surl:"http://search.china.alibaba.com/search/company_search.htm" , title:"�������Ʒ���˾��ؼ�ʣ�" , tracelog:"searchcom"},
{id:4,name:"����",surl:"http://search.china.alibaba.com/search/news_search.htm",title:"�������Ʒ�ؼ��֣��鿴���µĲ�Ʒ���飡" , tracelog:"searchinfor"},
{id:5,name:"����",surl:"http://search.china.alibaba.com/search/forum_search.htm" , title:"��������ҵ����ؼ�ʣ��鿴���̾�Ӫ֮�#�" , tracelog:"searchclub"},
{id:6,name:"����",surl:"http://search.china.alibaba.com/search/blog_search.htm" , title:"�����������Ȥ�Ĺؼ�ʲ������ӣ�",tracelog:"searchblog"},
{id:7,name:"����",surl:"http://search.china.alibaba.com/search/business_search.htm" , title:"�������Ʒ�ؼ�ʲ�����ر�����Ϣ��" , tracelog:"searchbaojia"},
{id:8,name:"����",surl:"http://search.china.alibaba.com/search/offer_search.htm?categoryId=51" , title:"�������Ʒ�ؼ�ʲ�����ش�����Ϣ��" , tracelog:"searchdaili"},
{id:9,name:"�ӹ�",surl:"http://search.china.alibaba.com/search/offer_search.htm?categoryId=2805" , title:"�������Ʒ�ؼ�ʲ�����ؼӹ���Ϣ��" , tracelog:"searchjiagong"},
{id:10,name:"��Ʒ",surl:"http://search.china.alibaba.com/search/sample_search.htm" , title:"�������Ʒ�ؼ�ʲ�����ز�Ʒ��Ϣ��" , tracelog:"searchsample"},
{id:11,name:"����",surl:"http://search.china.alibaba.com/search/profile_search.htm" , title:"���������ѵ���ƹ�˾��ҵ�����Թؼ�ʣ��ҵ������ҵ��ˣ�" , tracelog:"searchfriend"},
{id:12,name:"���⾭",surl:"http://search.china.alibaba.com/search/wiki_answer_search.htm" , title:"�������������ؼ��֣�ǧ�����̰�����" , tracelog:"searchhelp"}
]
var linkKeywords = [
{id:1,name:"��Ӧ",surl:"���Źؼ��֣�<a href=\"http://search.china.alibaba.com/selloffer/%E5%9D%AF%E5%B8%83.html\" target=_blank onMouseDown=\"return aliclick(this,'?tracelog=ui_homepage_searchbuykey');\" class=textwhite>��</a> <a href=\"http://search.china.alibaba.com/selloffer/%E5%B7%A5%E8%89%BA%E5%93%81.html\" target=_blank onMouseDown=\"return aliclick(this,'?tracelog=ui_homepage_searchbuykey');\" class=textwhite>����Ʒ</a> <a href=\"http://search.china.alibaba.com/selloffer/%E6%9A%96%E6%B0%94%E7%89%87.html\" target=_blank onMouseDown=\"return aliclick(this,'?tracelog=ui_homepage_searchbuykey');\" class=textwhite>ů��Ƭ</a>  <a href=\"http://search.china.alibaba.com/selloffer/%E9%92%A3%E9%87%91.html\" target=_blank onMouseDown=\"return aliclick(this,'?tracelog=ui_homepage_searchbuykey');\" class=textwhite>�ӽ�</a> <a href=\"http://search.china.alibaba.com/selloffer/%E5%B0%BC%E9%BE%99%E7%AE%A1.html\" target=_blank onMouseDown=\"return aliclick(this,'?tracelog=ui_homepage_searchbuykey');\" class=textwhite>�����</a>"},
{id:2,name:"��",surl:"���Źؼ��֣�<a href=\"http://search.china.alibaba.com/buyoffer/%E8%B8%8F%E6%AD%A5%E6%9C%BA.html?bidding=hothome\" target=_blank onMouseDown=\"return aliclick(this,'?tracelog=ui_homepage_searchsellerkey');\" class=textwhite>̤����</a> <a href=\"http://search.china.alibaba.com/buyoffer/MP3.html?bidding=hothome\" target=_blank onMouseDown=\"return aliclick(this,'?tracelog=ui_homepage_searchsellerkey');\" class=textwhite>MP3</a> <a href=\"http://search.china.alibaba.com/buyoffer/%E7%A4%BC%E5%93%81.html?bidding=hothome\" target=_blank onMouseDown=\"return aliclick(this,'?tracelog=ui_homepage_searchsellerkey');\" class=textwhite>��Ʒ</a> <a href=\"http://search.china.alibaba.com/buyoffer/%E5%8E%8B%E7%BC%A9%E6%AF%9B%E5%B7%BE.html?bidding=hothome\" target=_blank onMouseDown=\"return aliclick(this,'?tracelog=ui_homepage_searchsellerkey');\" class=textwhite>ѹ��ë��</a> </a><a href=\"http://search.china.alibaba.com/buyoffer/%E6%A8%A1%E5%85%B7.html?bidding=hothome\" target=_blank onMouseDown=\"return aliclick(this,'?tracelog=ui_homepage_searchsellerkey');\" class=textwhite>ģ��</a> <a href=\"http://search.china.alibaba.com/buyoffer/%E6%89%8B%E8%A1%A8.html?bidding=hothome\" target=_blank onMouseDown=\"return aliclick(this,'?tracelog=ui_homepage_searchsellerkey');\" class=textwhite>�ֱ�</a> <a href=\"http://search.china.alibaba.com/buyoffer/MP4.html?bidding=hothome\" target=_blank onMouseDown=\"return aliclick(this,'?tracelog=ui_homepage_searchsellerkey');\" class=textwhite>MP4</a> "},
{id:3,name:"��˾",surl:'���Źؼ��֣�<a href="http://china.alibaba.com/company/index.html" target=_blank onMouseDown="return aliclick(this,\'?tracelog=ui_homepage_searchcompanykey\');"  class=textwhite>������ҵ����</a> <a href="http://search.china.alibaba.com/search/company_search.htm?province=%C9%CF%BA%A3&biztype=1" target=_blank onMouseDown="return aliclick(this,\'?tracelog=ui_homepage_searchcompanykey\');" class=textwhite>�Ϻ��������ҵ</a> <a href="http://search.china.alibaba.com/search/company_search.htm?keywords=%B7%FE%D7%B0" target=_blank onMouseDown="return aliclick(this,\'?tracelog=ui_homepage_searchcompanykey\');" class=textwhite>��װ</a> <a href="http://search.china.alibaba.com/search/company_search.htm?keywords=%C0%F1%C6%B7" target=_blank onMouseDown="return aliclick(this,\'?tracelog=ui_homepage_searchcompanykey\');" class=textwhite>��Ʒ</a>'},
{id:4,name:"����",surl:'���Źؼ��֣� <a href="http://p-104304.hotnews.alibaba.com.cn/" target=_blank onMouseDown="return aliclick(this,\'?ui_homepage_searchnewskey\');" class=textwhite>����</a> <a href="http://p-cotton.hotnews.alibaba.com.cn/" target=_blank onMouseDown="return aliclick(this,\'?tracelog=ui_homepage_searchnewskey\');" class=textwhite>�޻�</a> <a href="http://b-112446.hotnews.alibaba.com.cn/" target=_blank onMouseDown="return aliclick(this,\'?tracelog=ui_homepage_searchnewskey\');" class=textwhite>�˲�</a> <a href="http://b-112251.hotnews.alibaba.com.cn/" target=_blank onMouseDown="return aliclick(this,\'?tracelog=ui_homepage_searchnewskey\');" class=textwhite>����</a> <a href="http://b-111663.hotnews.alibaba.com.cn/" target=_blank onMouseDown="return aliclick(this,\'?tracelog=ui_homepage_searchnewskey\');" class=textwhite>���</a> <a href="http://b-112105.hotnews.alibaba.com.cn/" target=_blank onMouseDown="return aliclick(this,\'?tracelog=ui_homepage_searchnewskey\');" class=textwhite>��ҵ</a> <a href="http://b-112586.hotnews.alibaba.com.cn/" target=_blank onMouseDown="return aliclick(this,\'?tracelog=ui_homepage_searchnewskey\');" class=textwhite>ƭ��</a> <a href="http://hotnews.alibaba.com.cn/" target=_blank onMouseDown="return aliclick(this,\'?tracelog=ui_homepage_searchnewskey\');" class=textwhite>���</a>'},
{id:5,name:"����",surl:'���Źؼ��֣� <a href="http://club.china.alibaba.com/club/post/search?keyword=��ҵ" target=_blank onMouseDown="return aliclick(this,\'?tracelog=ui_homepage_searchclubkey\');" class=textwhite>��ҵ</a> <a href="http://club.china.alibaba.com/club/post/search?keyword=�Ƹ�" target=_blank onMouseDown="return aliclick(this,\'?tracelog=ui_homepage_searchclubkey\');" class=textwhite>�Ƹ�</a> <a href="http://club.china.alibaba.com/club/post/search?keyword=ó��" target=_blank onMouseDown="return aliclick(this,\'?tracelog=ui_homepage_searchclubkey\');" class=textwhite>ó��</a> <a href="http://club.china.alibaba.com/club/post/search?keyword=����" target=_blank onMouseDown="return aliclick(this,\'?tracelog=ui_homepage_searchclubkey\');" class=textwhite>����</a> <a href="http://club.china.alibaba.com/club/post/search?keyword=����" target=_blank onMouseDown="return aliclick(this,\'?tracelog=ui_homepage_searchclubkey\');" class=textwhite>����</a></a> <a href="http://club.china.alibaba.com/club/post/search?keyword=Ӫ��" target=_blank onMouseDown="return aliclick(this,\'?tracelog=ui_homepage_searchclubkey\');" class=textwhite>Ӫ��</a> <a href="http://club.china.alibaba.com/club/post/search?keyword=׬Ǯ" target=_blank onMouseDown="return aliclick(this,\'?tracelog=ui_homepage_searchclubkey\');" class=textwhite>׬Ǯ</a> <a href="http://club.china.alibaba.com/html/bbs-paihang.html" target=_blank onMouseDown="return aliclick(this,\'?tracelog=ui_homepage_searchclubkey\');" class=textwhite>���</a>'},
{id:6,name:"����",surl:'���Źؼ��֣� '},
{id:7,name:"����",surl:'���Źؼ��֣�<a href="http://search.china.alibaba.com/search/business_search.htm?search_type=&keywords=%C0%F1%C6%B7&categoryId=&tracelog=auction_exchannel_search" target=_blank onMouseDown="return aliclick(this,\'?tracelog=ui_homepage_searchbaojiakey\');" class=textwhite>��Ʒ</a> <a href="http://search.china.alibaba.com/search/business_search.htm?search_type=&keywords=%B9%A4%D2%D5%C6%B7&categoryId=&tracelog=auction_exchannel_search" target=_blank onMouseDown="return aliclick(this,\'?tracelog=ui_homepage_searchbaojiakey\');" class=textwhite>����Ʒ</a> <a href="http://search.china.alibaba.com/search/business_search.htm?search_type=&keywords=T%D0%F4&categoryId=&tracelog=auction_exchannel_search" target=_blank onMouseDown="return aliclick(this,\'?tracelog=ui_homepage_searchbaojiakey\');" class=textwhite>T��</a> <a href="http://search.china.alibaba.com/search/business_search.htm?search_type=&keywords=%B5%E7%C4%D4&categoryId=&tracelog=auction_exchannel_search" target=_blank onMouseDown="return aliclick(this,\'?tracelog=ui_homepage_searchbaojiakey\');" class=textwhite>����</a> <a href="http://search.china.alibaba.com/search/business_search.htm?search_type=&keywords=MP3&categoryId=&tracelog=auction_exchannel_search" target=_blank onMouseDown="return aliclick(this,\'?tracelog=ui_homepage_searchbaojiakey\');" class=textwhite>MP3</a> <a href="http://page.china.alibaba.com/shtml/exchange/exchannel.shtml" target=_blank onMouseDown="return aliclick(this,\'?tracelog=ui_homepage_searchbaojiakey\');" class=textwhite>���</a>'},
{id:8,name:"����",surl:'���Źؼ��֣�<a href="http://search.china.alibaba.com/search/offer_search.htm?keywords=%BC%D2%BE%D3&direction=sale&categoryId=51" target=_blank onMouseDown="return aliclick(this,\'?tracelog=ui_homepage_searchdailikey\');" class=textwhite>�Ҿ�</a>  <a href="http://search.china.alibaba.com/search/offer_search.htm?keywords=%B1%A3%BD%A1%C6%B7&direction=sale&categoryId=51" target=_blank onMouseDown="return aliclick(this,\'?tracelog=ui_homepage_searchdailikey\');" class=textwhite>����Ʒ</a> <a href="http://search.china.alibaba.com/search/offer_search.htm?keywords=%C6%FB%C4%A6&direction=sale&categoryId=51" target=_blank onMouseDown="return aliclick(this,\'?tracelog=ui_homepage_searchdailikey\');" class=textwhite>��Ħ</a> <a href="http://search.china.alibaba.com/search/offer_search.htm?keywords=%BD%F8%B3%F6%BF%DA&direction=sale&categoryId=51" target=_blank onMouseDown="return aliclick(this,\'?tracelog=ui_homepage_searchdailikey\');" class=textwhite>����</a><a href="http://china.alibaba.com/buy/trade/51.html" target=_blank onMouseDown="return aliclick(this,\'?tracelog=ui_homepage_searchdailikey\');" class=textwhite>���</a>'},
{id:9,name:"�ӹ�",surl:'���Źؼ��֣�<a href="http://search.china.alibaba.com/search/offer_search.htm?keywords=%B0%FC%D7%B0%D3%A1%CB%A2&direction=sale&categoryId=2805" target="_blank" onMouseDown="return aliclick(this,\'?tracelog=ui_homepage_searchjiagongkey\');" class=textwhite>��װӡˢ</a> <a href="http://search.china.alibaba.com/search/offer_search.htm?keywords=%CE%E5%BD%F0%BC%D3%B9%A4&direction=sale&categoryId=2805" target="_blank" onMouseDown="return aliclick(this,\'?tracelog=ui_homepage_searchjiagongkey\');" class=textwhite>���ӹ�</a> <a href="http://search.china.alibaba.com/search/offer_search.htm?keywords=%D0%E5%BB%A8%BC%D3%B9%A4&direction=sale&categoryId=2805" target="_blank" onMouseDown="return aliclick(this,\'?tracelog=ui_homepage_searchjiagongkey\');" class=textwhite>�廨�ӹ�</a><a href="http://china.alibaba.com/buy/trade/2805.html" target=_blank onMouseDown="return aliclick(this,\'?tracelog=ui_homepage_searchjiagongkey\');" class="textwhite">���</a>'},
{id:10,name:"��Ʒ",surl:'���Źؼ��֣� '},
{id:11,name:"����",surl:'���Źؼ��֣�<a href="http://search.china.alibaba.com/search/profile_search.htm?province=�㶫" title="�㶫����" target="_blank">�㶫����</a> <a href="http://search.china.alibaba.com/search/profile_search.htm?province=�㽭&city=����" title="��������"  target="_blank">��������</a> <a href="http://search.china.alibaba.com/search/profile_search.htm?province=�㶫&city=����" title="��������"  target="_blank">��������</a> <a href="http://search.china.alibaba.com/search/profile_search.htm?goodField=1" title="��óר��"  target="_blank">��óר��</a> '},
{id:12,name:"���⾭",surl:'���Źؼ��֣�<a href="http://search.china.alibaba.com/search/wiki_answer_search.htm?keywords=%BF%CD%BB%A7" target="_blank" >�ͻ�</a> <a href="http://search.china.alibaba.com/search/wiki_answer_search.htm?keywords=%B7%C0%C6%AD" target="_blank">��ƭ</a> <a href="http://search.china.alibaba.com/search/wiki_answer_search.htm?keywords=%B3%F6%BF%DA" target="_blank">���</a> <a href="http://search.china.alibaba.com/search/wiki_answer_search.htm?keywords=%B6%A9%B5%A5" target="_blank" >����</a> <a href="http://search.china.alibaba.com/search/wiki_answer_search.htm?keywords=%B4%B4%D2%B5" target="_blank" >��ҵ</a> <a href="http://search.china.alibaba.com/search/wiki_answer_search.htm?keywords=%BA%CF%CD%AC" target="_blank" >��ͬ</a> <a href="http://search.china.alibaba.com/search/wiki_answer_search.htm?keywords=%B2%C9%B9%BA" target="_blank" >�ɹ�</a>'}
]
var keys_str = getCookie('h_keys');
if(keys_str != null  )
{
var keys_array = keys_str.split("#");
if(keys_array.length >= 3){
var strlen =0;
var str="��������¼��";
for(var i=0;i<keys_array.length && i<6;i++){
var key = keys_array[i];
if(key.indexOf("[") != -1 && key.indexOf("]") != -1) {
// �����ϵ�cookie��ʽ
key = key.substring(0, key.length - 3);
}
strlen = strlen + key.length;
if(strlen < 18){
str+=" <a target=_blank href=http://search.china.alibaba.com/selloffer/"+encodeURI(key)+".html?tracelog=jy_homepage_historyky class=textwhite onMouseDown=\"return aliclick(this,'?tracelog=ui_homepage_searchbuy');\">"+ key +"</a> ";
}
}
linkKeywords[0].surl=str;
linkKeywords[1].surl=str;
}
}
function aliclick(u,param)
{
var url = "http://stat.china.alibaba.com/tracelog/click.html";
return baseClick(url,param);
}
function etcclick(u, param) {
var url = "http://stat.china.alibaba.com/etclistquery.html";
return baseClick(url,param);
}
function eeclick(u, param) {
var url ="http://stat.china.alibaba.com/ee.html";
return baseClick(url,param);
}
function aliclickType(u, param){
var urlTxt = window.location.href;
if(urlTxt){
var urlType = urlTxt.substring(urlTxt.lastIndexOf('/')+1,urlTxt.lastIndexOf('.'));
}
aliclick(u, param+'_'+urlType);
}
function baseClick(url,param)
{
try{
if(!dmtrack)
{
var dmtrack = parent.dmtrack;
}
dmtrack.clickstat(url,param);
}
catch(err)
{
}
return true
}
/*
function aliclickType(u, param){
var urlTxt = window.location.href;
if(urlTxt){
var urlType = urlTxt.substring(urlTxt.lastIndexOf('/')+1,urlTxt.lastIndexOf('.'));
}
aliclick(u, param+'_'+urlType);
}
function aliclick(u, param) {
d = new Date();
if(document.images) {
var img_aliclick = new Image();
img_aliclick.src="http://stat.china.alibaba.com/tracelog/click.html" + param + "&time=" + d.getTime();
}
return true;
}
function etcclick(u, param) {
d = new Date();
if(document.images) {
var img_etc_aliclick = new Image();
img_etc_aliclick.src="http://stat.china.alibaba.com/etclistquery.html" + param + "&time=" + d.getTime();
}
return true;
}
function eeclick(u, param) {
d = new Date();
if (document.images) {
(new Image()).src = "http://stat.china.alibaba.com/ee.html" + param + "&time=" + d.getTime();
}
return true;
}
*/
var activedTree;function initHead(a){var c=document.getElementById("header-ul").getElementsByTagName("li");var b=document.getElementById("header_m-ul").getElementsByTagName("li");if(a>6){a=a-6;var d=b[a==1?0:a*2-2].getElementsByTagName("a")[0].innerHTML.replace(/&nbsp;/g,"").length;b[a==1?0:a*2-2].className="nav-f"+(d<4?3:4)+"_on"}else{var d=c[a==1?0:a*2-2].getElementsByTagName("a")[0].innerHTML.replace(/&nbsp;/g,"").length;c[a==1?0:a*2-2].className="nav-f"+(d<4?3:4)+"_on"}}function addFavourite(){if(document.getElementById("header_favourite")){var a=document.getElementById("header_favourite");a.onclick=function(b){if(window.navigator.userAgent.indexOf("IE")!=-1){stopDefault(b);window.external.addFavorite("http://china.alibaba.com/member/myalibaba.htm","\u6b22\u8fce\u5149\u4e34\u963f\u91cc\u52a9\u624b")}else{stopDefault(b);window.sidebar.addPanel("\u6b22\u8fce\u5149\u4e34\u963f\u91cc\u52a9\u624b","http://china.alibaba.com/member/myalibaba.htm","")}}}}function newtab_init(){if(document.getElementById("newtab_title")){var a=document.getElementById("newtab_title");var b=document.getElementById("newtab_more");a.onmouseover=function(){b.style.display="block"};b.onmouseover=function(){this.style.display="block"};b.onmouseout=function(){this.style.display="none"}}}function initGuide(a){activedTree=a}function initTreeHeight(){if(document.getElementById("new-content")&&document.getElementById("leftmenu")){var a=document.getElementById("new-content").offsetHeight;var b=document.getElementById("leftmenu").offsetHeight;if(b<a&&a>400){document.getElementById("leftmenu").style.height=a+"px"}else{if(b<400&&a<400){document.getElementById("leftmenu").style.height=400+"px"}else{document.getElementById("leftmenu").style.height=b+"px"}}}if(document.getElementById("login-content")){var c=document.getElementById("login-content").offsetHeight;if(c<400){document.getElementById("login-content").style.height=400+"px"}}}var initNavTimer;function initNavClose(){document.getElementById("nav-f3_main").className="nav-f3";document.getElementById("guidedetail").style.display="none"}function initNav(){if(document.getElementById("nav-f3_main")){var b=document.getElementById("nav-f3_main");b.style.cursor="pointer";var a=document.getElementById("guidedetail");b.onmouseover=function(){if(document.getElementById("menuInit").src!=""){clearTimeout(initNavTimer);this.className="nav-f3_chk";a.style.display="block"}else{clearTimeout(initNavTimer);menuInitBefore();this.className="nav-f3_chk";a.style.display="block"}};b.onmouseout=function(){initNavTimer=setTimeout("initNavClose()",250)};a.onmouseover=function(){clearTimeout(initNavTimer);b.className="nav-f3_chk";this.style.display="block"};a.onmouseout=function(){initNavTimer=setTimeout("initNavClose()",250)}}}function initSearch(){if(document.getElementById("search_new_form")){var e=document.getElementById("search_new_form");var d=document.getElementById("search_new_item");var b=document.getElementById("search_submit");var a=document.getElementById("search_new_text");var c=document.getElementById("search_tracelog");a.value="\u8bf7\u8f93\u5165\u60a8\u611f\u5174\u8da3\u7684\u4ea7\u54c1\u540d\u79f0\uff01";a.title="\u8bf7\u8f93\u5165\u60a8\u611f\u5174\u8da3\u7684\u4ea7\u54c1\u540d\u79f0\uff01";a.onclick=function(){if(this.value.indexOf("\u8bf7\u8f93\u5165")!=-1){this.value="";this.className="search_text_on"}else{this.className="search_text_on"}};b.onclick=function(){if(a.value.indexOf("\u8bf7\u8f93\u5165")==-1&&String(a.value).Trim()!=""){e.submit()}else{if(d.value=="offer_search"){c.value="search_sellofferlist_searchbuyer";if(String(a.value).Trim()==""||a.value.indexOf("\u8bf7\u8f93\u5165")!=-1){a.value="\u8bf7\u8f93\u5165\u60a8\u611f\u5174\u8da3\u7684\u4ea7\u54c1\u540d\u79f0\uff01";a.className="search_text"}else{a.className="search_text_on"}e.action="http://search.china.alibaba.com/search/offer_search.htm";a.title="\u8bf7\u8f93\u5165\u60a8\u611f\u5174\u8da3\u7684\u4ea7\u54c1\u540d\u79f0\uff01"}else{if(d.value=="company_search"){c.value="search_sellofferlist_searchcom";if(String(a.value).Trim()==""||a.value.indexOf("\u8bf7\u8f93\u5165")!=-1){a.value="\u8bf7\u8f93\u5165\u4ea7\u54c1\u540d\u6216\u516c\u53f8\u540d\u5173\u952e\u8bcd\uff01";a.className="search_text"}else{a.className="search_text_on"}e.action="http://search.china.alibaba.com/search/company_search.htm";a.title="\u8bf7\u8f93\u5165\u4ea7\u54c1\u540d\u6216\u516c\u53f8\u540d\u5173\u952e\u8bcd\uff01"}else{if(d.value=="search"){c.value="search_sellofferlist_searchsale";if(String(a.value).Trim()==""||a.value.indexOf("\u8bf7\u8f93\u5165")!=-1){a.value="\u8bf7\u8f93\u5165\u60a8\u611f\u5174\u8da3\u7684\u4ea7\u54c1\u540d\u79f0\uff01";a.className="search_text"}else{a.className="search_text_on"}e.action="http://search.china.alibaba.com/search/search.htm";a.title="\u8bf7\u8f93\u5165\u60a8\u611f\u5174\u8da3\u7684\u4ea7\u54c1\u540d\u79f0\uff01"}else{if(d.value=="news_search"){c.value="search_sellofferlist_searchinfor";if(String(a.value).Trim()==""||a.value.indexOf("\u8bf7\u8f93\u5165")!=-1){a.value="\u8bf7\u8f93\u5165\u4ea7\u54c1\u5173\u952e\u5b57\uff0c\u67e5\u770b\u6700\u65b0\u7684\u4ea7\u54c1\u884c\u60c5\uff01";a.className="search_text"}else{a.className="search_text_on"}e.action="http://search.china.alibaba.com/search/news_search.htm";a.title="\u8bf7\u8f93\u5165\u4ea7\u54c1\u5173\u952e\u5b57\uff0c\u67e5\u770b\u6700\u65b0\u7684\u4ea7\u54c1\u884c\u60c5\uff01"}}}}}};d.onchange=function(){if(this.value=="offer_search"){c.value="search_sellofferlist_searchbuyer";if(String(a.value).Trim()==""||a.value.indexOf("\u8bf7\u8f93\u5165")!=-1){a.value="\u8bf7\u8f93\u5165\u60a8\u611f\u5174\u8da3\u7684\u4ea7\u54c1\u540d\u79f0\uff01";a.className="search_text"}else{a.className="search_text_on"}e.action="http://search.china.alibaba.com/search/offer_search.htm";a.title="\u8bf7\u8f93\u5165\u60a8\u611f\u5174\u8da3\u7684\u4ea7\u54c1\u540d\u79f0\uff01"}else{if(this.value=="company_search"){c.value="search_sellofferlist_searchcom";if(String(a.value).Trim()==""||a.value.indexOf("\u8bf7\u8f93\u5165")!=-1){a.value="\u8bf7\u8f93\u5165\u4ea7\u54c1\u540d\u6216\u516c\u53f8\u540d\u5173\u952e\u8bcd\uff01";a.className="search_text"}else{a.className="search_text_on"}e.action="http://search.china.alibaba.com/search/company_search.htm";a.title="\u8bf7\u8f93\u5165\u4ea7\u54c1\u540d\u6216\u516c\u53f8\u540d\u5173\u952e\u8bcd\uff01"}else{if(this.value=="search"){c.value="search_sellofferlist_searchsale";if(String(a.value).Trim()==""||a.value.indexOf("\u8bf7\u8f93\u5165")!=-1){a.value="\u8bf7\u8f93\u5165\u60a8\u611f\u5174\u8da3\u7684\u4ea7\u54c1\u540d\u79f0\uff01";a.className="search_text"}else{a.className="search_text_on"}e.action="http://search.china.alibaba.com/search/search.htm";a.title="\u8bf7\u8f93\u5165\u60a8\u611f\u5174\u8da3\u7684\u4ea7\u54c1\u540d\u79f0\uff01"}else{if(this.value=="news_search"){c.value="search_sellofferlist_searchinfor";if(String(a.value).Trim()==""||a.value.indexOf("\u8bf7\u8f93\u5165")!=-1){a.value="\u8bf7\u8f93\u5165\u4ea7\u54c1\u5173\u952e\u5b57\uff0c\u67e5\u770b\u6700\u65b0\u7684\u4ea7\u54c1\u884c\u60c5\uff01";a.className="search_text"}else{a.className="search_text_on"}e.action="http://search.china.alibaba.com/search/news_search.htm";a.title="\u8bf7\u8f93\u5165\u4ea7\u54c1\u5173\u952e\u5b57\uff0c\u67e5\u770b\u6700\u65b0\u7684\u4ea7\u54c1\u884c\u60c5\uff01"}}}}}}}function initNote(){var c=getElementsByClassName("new-notice-close","div");var b=getElementsByClassName("new-notice","div");if(c.length>0){for(var a=0;a<c.length;a++){(function(){var d=a;c[d].onclick=function(){b[d].style.display="none"}})()}}}__domReady(function(){});window.onload=function(){initSearch();initSelectBox();initNav();addFavourite();newtab_init();if(typeof(selectBoxInit)!="undefined"){selectBoxInit()}initNote();setTimeout("initTreeHeight()",50)};
function initDiyGuide(){if(document.getElementById("content-layout")){var a=document.getElementById("content-layout");a.className="content-layout";if(document.body.scrollHeight){a.style.height=document.body.scrollHeight+"px"}if(document.body.clientWidth&&document.body.clientWidth>837){a.style.width=document.body.clientWidth+"px"}a.style.display="block"}}function initDiyResize(){if(document.getElementById("content-layout")){var a=document.getElementById("content-layout");if(a.className=="content-layout"){if(document.body.scrollHeight){a.style.height=document.body.scrollHeight+100+"px"}if(document.body.clientWidth&&document.body.clientWidth>837){a.style.width=document.body.clientWidth+"px"}else{a.style.width="837px"}}}}function close_layout(){layout.className="layout_off"};
var gboxCountVar=7;function addEvent(d,a,c,b){var b=b||false;d=document.getElementById(d)||document;if(d.addEventListener){d.addEventListener(a,c,b)}else{if(d.attachEvent){d.attachEvent("on"+a,c)}}}function $(a){return document.getElementById(a)}function OnOff(a){if($(a).className.indexOf("on")!="-1"){$(a).className=$(a).className.replace("on","off")}else{$(a).className=$(a).className.replace("off","on")}}var mempro=new Array();for(var x=0;x<gboxCountVar;x++){mempro[x]="Gbox"+x}function initSelectBox(){if(document.getElementById("Gbox0")){for(var b=0;b<gboxCountVar;b++){(function(){var c=b;if(document.getElementById(mempro[c])){addEvent(mempro[c],"mouseover",function(){$(mempro[c]).className="Gbox_on"})}})()}for(var a=0;a<gboxCountVar;a++){(function(){var c=a;if(document.getElementById(mempro[c])){addEvent(mempro[c],"mouseout",function(){$(mempro[c]).className="Gbox_off"})}})()}}};
var flag=true;var pure_date="";function btn_event(a,b){stopDefault(a);if(document.getElementById("keeper_dis").style.display=="block"||document.getElementById("keeper_dis").style.display==""){b.parentNode.className="keeper_btn_on";document.getElementById("keeper_dis").style.display="none";document.getElementById("keeper_dis_open").style.display="block"}else{b.parentNode.className="keeper_btn_off";document.getElementById("keeper_dis").style.display="block";document.getElementById("keeper_dis_open").style.display="none"}setMainHeight()}function setMainHeight(){var d=document.getElementById("main_tree");if(!d){return}var a=d.offsetHeight;d=document.getElementById("main_right");if(!d){return}var b=d.offsetHeight;d.style.height="auto";var c=d.offsetHeight;if(!document.getElementById("main_body")){return}if(a>c){document.getElementById("main_right").style.height=document.getElementById("main_tree").offsetHeight+"px";document.getElementById("main_body").style.height=document.getElementById("main_tree").offsetHeight+28+"px"}else{document.getElementById("main_body").style.height=document.getElementById("main_right").offsetHeight+28+"px"}}function calendar(w,g,h,e,m,B,x){var c,j;if(w=="day"){c=day_start_date;j=day_stop_date}else{if(w=="week"){c=week_start_date;j=week_stop_date}}var z=c.split("-")[0]+c.split("-")[1];var b=j.split("-")[0]+j.split("-")[1];var a=parseInt((new Date(B)).format("yyyyMM"));if(B){if(a<z||a>b){return}}z=c.replace(/\-/g,"");b=j.replace(/\-/g,"");a=parseInt((new Date(B)).format("yyyyMMdd"));var t=document.getElementById(g);var A=document.getElementById(m).getElementsByTagName("li");var o=document.getElementById(m).getElementsByTagName("ul");var v=document.getElementById(h);var p=document.getElementById(e);dayList=new Array();if((typeof cur_type)!="undefined"&&cur_type==w&&(typeof cur_date)!="undefined"&&cur_date){var f=new Array();f=cur_date.split("-");now=new Date(f[0],f[1]-1,f[2]);pure_date=cur_date;cur_date=null}else{f=new Array();f=j.split("-");now=(B?new Date(B):new Date(f[0],f[1]-1,f[2]))}var k;t.innerHTML=now.getFullYear()+"\u5e74"+(now.getMonth()+1)+"\u6708";if(now.getDay()+1==now.getDate()%7){k=7*parseInt(now.getDate()/7)+now.getDay()}else{if(now.getDay()+1>now.getDate()%7){k=7*parseInt(now.getDate()/7)+now.getDay()}else{k=7*(parseInt(now.getDate()/7)+1)+now.getDay()}}for(i=0;i<42;i++){if(i<k){var s=new Date(+now-(k-i)*86400000)}else{var s=new Date(+now+(i-k)*86400000)}dayList.push(s);A[i].className="n";A[i].parclass="n";if(s.getMonth()==now.getMonth()){A[i].innerHTML=s.getDate();if((typeof cur_type)!="undefined"&&cur_type==w&&(typeof pure_date)!="undefined"&&pure_date){if(cur_type=="day"&&s.format("yyyy-MM-dd")==pure_date){A[i].className="y"}if(cur_type=="week"&&calendar_week(pure_date,s)){A[i].className="y"}}A[i].date=s.format("yyyy-MM-dd");if(z&&b){if(A[i].date.replace(/\-/g,"")<z||A[i].date.replace(/\-/g,"")>b){A[i].className="s";A[i].onclick=null}else{A[i].onclick=function(){if(w=="day"){for(var l=0;l<A.length;l++){if(A[l].className=="y"){A[l].className="n"}}this.className="y";this.parclass="y"}else{for(var d=0;d<o.length;d++){var C=o[d].getElementsByTagName("li");for(var n=0;n<C.length;n++){if(C[n].className=="y"){C[n].className="n"}}}var r=this.parentNode.getElementsByTagName("li");for(var u=0;u<r.length;u++){if(r[u].parclass=="n"){r[u].parclass="y";r[u].className="y"}}}func_link(this.date,w,x)}}}}else{A[i].innerHTML="";A[i].onclick=null;A[i].className="s"}}v.date=String(new Date(now.getFullYear(),(now.getMonth()-1)));p.date=String(new Date(now.getFullYear(),(now.getMonth()+1)));v.onclick=function(){calendar(w,g,h,e,m,v.date,x)};p.onclick=function(){calendar(w,g,h,e,m,p.date,x)};if(w=="week"){for(var q=0;q<o.length;q++){o[q].onmouseover=function(){var d=this.getElementsByTagName("li");for(var l=0;l<d.length;l++){d[l].parclass=d[l].className;d[l].className="y"}};o[q].onmouseout=function(){var d=this.getElementsByTagName("li");for(var l=0;l<d.length;l++){d[l].className=d[l].parclass}}}}else{for(var y=0;y<A.length;y++){A[y].onmouseover=function(){this.parclass=this.className;this.className="y"};A[y].onmouseout=function(){this.className=this.parclass}}}}function calendar_week(e,d){var b=e.split("-");var e=new Date(b[0],b[1]-1,b[2]);var c=[];for(var a=0;a<7;a++){if(a<e.getDay()){c.push(+e-86400000*(e.getDay()-a))}else{if(a>e.getDay()){c.push(+e+86400000*(a-e.getDay()))}else{c.push(+e)}}}switch((new Date(d)).getTime()){case c[0]:return true;case c[1]:return true;case c[2]:return true;case c[3]:return true;case c[4]:return true;case c[5]:return true;case c[6]:return true;default:return false}return false}function keeper_init(){if((typeof init)!="undefined"){init()}if(document.getElementById("cal_2")){calendar("week","cal_now_2","cal_left_2","cal_right_2","cal_day_2",null,"keeper_p_week")}if(document.getElementById("cal_5")){calendar("week","cal_now_5","cal_left_5","cal_right_5","cal_day_5",null,"keeper_c_week")}if(document.getElementById("cal_6")){calendar("day","cal_now_6","cal_left_6","cal_right_6","cal_day_6",null,"keeper_c_day")}if(document.getElementById("keeper_p_week")){document.getElementById("keeper_p_week").preClass=document.getElementById("keeper_p_week").className;document.getElementById("keeper_p_month").preClass=document.getElementById("keeper_p_month").className;document.getElementById("keeper_month_l").preClass=document.getElementById("keeper_month_l").className;document.getElementById("keeper_p_week").onclick=function(){if(document.getElementById("keeper_p_week").className==document.getElementById("keeper_p_week").preClass){document.getElementById("keeper_p_week").className="keeper_per2_up";document.getElementById("cal_2").style.display="block";document.getElementById("keeper_p_month").className=document.getElementById("keeper_p_month").preClass;document.getElementById("keeper_month_l").style.display="none"}else{document.getElementById("keeper_p_week").className=document.getElementById("keeper_p_week").preClass;document.getElementById("cal_2").style.display="none"}}}if(document.getElementById("keeper_month_l")){document.getElementById("keeper_month_l").preClass=document.getElementById("keeper_month_l").className;document.getElementById("keeper_p_week").preClass=document.getElementById("keeper_p_week").className;document.getElementById("keeper_p_month").preClass=document.getElementById("keeper_p_month").className;document.getElementById("keeper_p_month").onclick=function(){if(document.getElementById("keeper_month_l").style.display=="none"||document.getElementById("keeper_month_l").style.display==""){document.getElementById("keeper_month_l").style.display="block";document.getElementById("keeper_p_month").className="keeper_per2_up";document.getElementById("keeper_p_week").className=document.getElementById("keeper_p_week").preClass;document.getElementById("cal_2").style.display="none"}else{document.getElementById("keeper_month_l").style.display="none";document.getElementById("keeper_p_month").className=document.getElementById("keeper_p_month").preClass}};var e=document.getElementById("keeper_month_l").getElementsByTagName("li");for(var o=0;o<e.length;o++){e[o].onmouseover=function(){this.className="on"};e[o].onmouseout=function(){this.className=""}}}if(document.getElementById("keeper_c_week")){document.getElementById("keeper_c_week").preClass=document.getElementById("keeper_c_week").className;document.getElementById("keeper_c_week").onclick=function(){if(document.getElementById("keeper_c_week").className==document.getElementById("keeper_c_week").preClass){document.getElementById("keeper_c_week").className="keeper_per2_up";document.getElementById("cal_5").style.display="block";document.getElementById("keeper_c_day").className=document.getElementById("keeper_c_day").preClass;document.getElementById("cal_6").style.display="none"}else{document.getElementById("keeper_c_week").className=document.getElementById("keeper_c_week").preClass;document.getElementById("cal_5").style.display="none"}}}if(document.getElementById("keeper_c_day")){document.getElementById("keeper_c_day").preClass=document.getElementById("keeper_c_day").className;document.getElementById("keeper_c_week").preClass=document.getElementById("keeper_c_week").className;document.getElementById("keeper_c_day").onclick=function(){if(document.getElementById("keeper_c_day").className==document.getElementById("keeper_c_day").preClass){document.getElementById("keeper_c_day").className="keeper_per2_up";document.getElementById("cal_6").style.display="block";document.getElementById("keeper_c_week").className=document.getElementById("keeper_c_week").preClass;document.getElementById("cal_5").style.display="none"}else{document.getElementById("keeper_c_day").className=document.getElementById("keeper_c_day").preClass;document.getElementById("cal_6").style.display="none"}}}if(document.getElementById("keeper_help")){document.getElementById("keeper_help").onmouseover=function(){document.getElementById("keeper_help_box").style.display="block"};document.getElementById("keeper_help").onmouseout=function(){document.getElementById("keeper_help_box").style.display="none"}}if(document.getElementById("keeper_help3")){var r=getElementsByClassName("keeper_h_t","div");var n=r[0].innerHTML.split("\u201c");var c=n[1].split("\u201d")[0].length;var g=getElementsByClassName("keeper_r14w","span");var k=g[0].innerHTML;var b=k.length;var a=c*16+b*10+400;var q=c*16+b*10+310;document.getElementById("keeper_help3").style.marginLeft=a+"px";document.getElementById("keeper_help_box").style.marginLeft=q+"px";document.getElementById("keeper_help3").onmouseover=function(){document.getElementById("keeper_help_box").style.display="block"};document.getElementById("keeper_help3").onmouseout=function(){document.getElementById("keeper_help_box").style.display="none"}}if(document.getElementById("k_items")){var p=document.getElementById("k_items").getElementsByTagName("li");var m=document.getElementById("k_left_b");var f=document.getElementById("k_right_b");f.className="k_right_b";m.className="k_left_b";for(var l=0;l<p.length;l++){p[l].target=l}if(p.length>6){for(var h=0;h<p.length;h++){if(h==1&&p[h].style.display=="none"){m.className="k_left_b_on"}if(h==p.length-1&&p[h].style.display=="none"){f.className="k_right_b_on"}}m.onclick=function(){if(this.className=="k_left_b"){return}var s=new Array();for(var j=0;j<p.length;j++){if(p[j].style.display!="none"&&(p[j].className!="k_left_b"&&p[j].className!="k_left_b_on")){s.push(p[j].target)}}if(s.length==6){if(s[0]>1&&s[s.length-1]<=p.length-1){for(var t=1;t<p.length;t++){p[t].style.display="none"}for(t=0;t<s.length;t++){p[s[t]-1].style.display="block"}}if(s[0]==2){this.className="k_left_b"}f.className="k_right_b_on"}else{if(s.length<6){if(s[0]>1&&s[s.length-1]<=p.length-1){for(var t=1;t<p.length;t++){p[t].style.display="none"}for(t=0;t<s.length;t++){p[s[t]-1].style.display="block"}p[s[s.length-1]].style.display="block"}if(s[0]==2){this.className="k_left_b"}f.className="k_right_b_on"}}if(s[0]==2){this.className="k_left_b"}};f.onclick=function(){if(this.className=="k_right_b"){return}var s=new Array();for(var j=0;j<p.length;j++){if(p[j].style.display!="none"&&(p[j].className!="k_left_b"&&p[j].className!="k_left_b_on")){s.push(p[j].target)}}if(s.length==6){if(s[0]>0&&s[s.length-1]<p.length-1){for(var t=1;t<p.length;t++){p[t].style.display="none"}for(t=0;t<s.length;t++){p[s[t]+1].style.display="block"}}if(s[s.length-1]==p.length-1){this.className="k_right_b"}m.className="k_left_b_on"}else{if(s.length<6){if(s[0]>0&&s[s.length-1]<p.length-1){for(var t=1;t<p.length;t++){p[t].style.display="none"}for(t=0;t<s.length;t++){p[s[t]+1].style.display="block"}p[s[0]].style.display="block"}if(s[s.length-1]==p.length-1){this.className="k_right_b"}m.className="k_left_b_on"}}}}else{var d=false;for(var h=0;h<p.length;h++){if(h>=1&&p[h].style.display=="none"){f.className="k_right_b_on";d=true;break}}if(d&&f.className=="k_right_b_on"){f.onclick=function(){for(var j=p.length-2;j>0;j--){if(p[j].style.display=="none"&&(p[j+1].style.display==""||p[j+1].style.display=="block")){p[j].style.display="block";break}}if(p[1].style.display==""||p[1].style.display=="block"){f.className="k_right_b"}}}}}if(document.getElementById("keeper_bg")){document.getElementById("keeper_bg").onmouseover=function(){document.getElementById("keeper_bg_tips").style.display="block"};document.getElementById("keeper_bg").onmouseout=function(){document.getElementById("keeper_bg_tips").style.display="none"}}if(document.getElementById("keeper_dj")){document.getElementById("keeper_dj").onmouseover=function(){document.getElementById("keeper_dj_tips").style.display="block"};document.getElementById("keeper_dj").onmouseout=function(){document.getElementById("keeper_dj_tips").style.display="none"}}if(document.getElementById("keeper_fk")){document.getElementById("keeper_fk").onmouseover=function(){document.getElementById("keeper_fk_tips").style.display="block"};document.getElementById("keeper_fk").onmouseout=function(){document.getElementById("keeper_fk_tips").style.display="none"}}if(document.getElementById("eadvisorKeyword")){search_on()}}function stopDefault(a){var a=window.event||a;if(a.preventDefault){a.preventDefault()}else{a.returnValue=false}return false}var clickcount=0;Date.prototype.format=function(b){var c={"M+":this.getMonth()+1,"d+":this.getDate(),"h+":this.getHours(),"m+":this.getMinutes(),"s+":this.getSeconds(),"q+":Math.floor((this.getMonth()+3)/3),S:this.getMilliseconds()};if(/(y+)/.test(b)){b=b.replace(RegExp.$1,(this.getFullYear()+"").substr(4-RegExp.$1.length))}for(var a in c){if(new RegExp("("+a+")").test(b)){b=b.replace(RegExp.$1,RegExp.$1.length==1?c[a]:("00"+c[a]).substr((""+c[a]).length))}}return b};function mask_init(){if(!document.getElementById("keeper_mask")){return}var b=document.getElementById("keeper_mask");var a=document.getElementById("keeper_note");b.className="keeper_mask";b.style.height=document.body.clientWidth+"px";if(window.navigator.userAgent.indexOf("6.0")!=-1){b.style.width=document.body.clientWidth+"px"}else{b.style.width="100%"}if(document.body.scrollHeight){b.style.height=document.body.scrollHeight+100+"px"}document.getElementById("keeper_note_cls2").onclick=close_layout;document.getElementById("keeper_note_cls").onclick=close_layout;a.style.display="block"}function close_layout(){var b=document.getElementById("keeper_mask");b.className="keeper_mask_off";var a=document.getElementById("keeper_note");a.style.display="none"}function search_on(){var a=document.getElementById("eadvisorKeyword");a.onfocus=function(){if(this.value=="\u8bf7\u8f93\u5165\u5173\u952e\u8bcd\u4e86\u89e3\u4e70\u5bb6\u641c\u7d22\u60c5\u51b5"){this.value=""}else{this.style.color="#000000"}};a.onkeypress=function(){if(this.value!="\u8bf7\u8f93\u5165\u5173\u952e\u8bcd\u4e86\u89e3\u4e70\u5bb6\u641c\u7d22\u60c5\u51b5"){this.style.color="#000000"}};a.onclick=function(){if(this.value!="\u8bf7\u8f93\u5165\u5173\u952e\u8bcd\u4e86\u89e3\u4e70\u5bb6\u641c\u7d22\u60c5\u51b5"){this.style.color="#000000"}}}function pageOnload(){mask_init();keeper_init()}function keeper_load(){mask_init();keeper_init()}function addLoadEvent(a){var b=window.onload;if(typeof window.onload!="function"){window.onload=a}else{window.onload=function(){b();a()}}}addLoadEvent(keeper_load);
function initCurrentTree(d){if(d==null){return}var b=d.split(".");var a=document.getElementById("tree1");var e=getElementsByClassName("tree_item","div");var c=getElementsByClassName("tree_title","div");if(b.length==1){c[parseInt(b[0])-1].id="tree_title_now"}else{if(b.length==2){var f=hasClassElement("tree_list",e[b[0]-1],"ul");if(f){f.style.display="block";f.getElementsByTagName("li")[b[1]-1].id="tree_list_now"}}}}function menuInitBefore(){try{if(document.getElementById("menuInit")&&navigateMenuUrl!=undefined){document.getElementById("menuInit").src=navigateMenuUrl}}catch(a){}}function menuInit(){if(navigateMenuJson!=""&&(typeof navigateMenuJson)!="undefined"){var c='<iframe width="764" height="157" frameborder="0" scrolling="no" class="frame_bg"></iframe>';var a=navigateMenuJson;var k=c+'<div class="guidemain">';for(var d=0;d<a.length;d++){if(d==0){var h='<div class="guidemain_1"><ul><li class="guidemain_title">'+a[d].name+"</li>";var e=a[d]["childMenu"];for(var b=0;b<e.length;b++){if(e[b].style.indexOf("BLANK")!=-1){h+='<li><a target="_blank" href="'+e[b].url+'">'+e[b].name+"</a></li>"}else{h+='<li><a href="'+e[b].url+'">'+e[b].name+"</a></li>"}}h+="</ul></div>";k+=h}else{if(d==1){var e=a[d]["childMenu"];var g='<div class="guidemain_2"><div class="guidemain_list_title">'+a[d].name+"</div>";for(var b=0;b<e.length;b++){if(b==0){g+='<ul class="guidemain_list">'}if(e[b].style.indexOf("BLANK")!=-1){g+='<li><a target="_blank" href="'+e[b].url+'">'+e[b].name+"</a></li>"}else{g+='<li><a href="'+e[b].url+'">'+e[b].name+"</a></li>"}if(b==4){g+='</ul><ul class="guidemain_list">'}}g+="</ul></div>";k+=g}else{var f='<div class="guidemain_3"><ul><li class="guidemain_title">'+a[d].name+"</li>";var e=a[d]["childMenu"];for(var b=0;b<e.length;b++){if(e[b].style.indexOf("BLANK")!=-1){f+='<li><a target="_blank" href="'+e[b].url+'">'+e[b].name+"</a></li>"}else{f+='<li><a href="'+e[b].url+'">'+e[b].name+"</a></li>"}}f+="</ul></div>";k+=f}}}document.getElementById("guidedetail").innerHTML=k}}function initTreeHTML(){if(document.getElementById("tree1")&&(typeof menuJson)=="object"&&document.getElementById("leftmenu")){var l=document.getElementById("leftmenu");var b=document.getElementById("tree1");var q=b.innerHTML;b.innerHTML="";var p;if(menuJson.childMenu==undefined){p=menuJson[0]["childMenu"]}else{p=menuJson.childMenu}var m=true;var o=false;for(var e=0;e<p.length;e++){var c=document.createElement("div");c.treeId=p[e].id;c.className=getClassName(p[e].style,1,"none");var h=document.createElement("div");if(p[e]["childMenu"].length==0){if(p[e].url!=""){if(p[e].style.indexOf("TARGET_BLANK")!=-1){h.innerHTML=getStyleIcon(p[e].style,'<a target="_blank" href="'+p[e].url+'">'+p[e].name+"</a>")}else{h.innerHTML=getStyleIcon(p[e].style,'<a target="_self" href="'+p[e].url+'">'+p[e].name+"</a>")}}else{if(p[e].style.indexOf("TARGET_BLANK")!=-1){h.innerHTML=getStyleIcon(p[e].style,'<a target="_blank" >'+p[e].name+"</a>")}else{h.innerHTML=getStyleIcon(p[e].style,'<a target="_self" >'+p[e].name+"</a>")}}h.treeId=p[e].id;if(p[e].id==activedTree){h.id=getClassName(p[e].style,2,"now")}else{h.className=getClassName(p[e].style,2,"none")}c.appendChild(h)}else{if(p[e]["childMenu"].length>0){h.innerHTML=p[e].name;h.treeId=p[e].id;if((typeof activedTree)=="undefined"&&m){h.className=getClassName(p[e].style,2,"open");if(menuJson[0]["name"].indexOf("\u9996\u9875")!=-1){m=true;o=true}else{m=false;o=true}}else{if(p[e].id==activedTree){h.className=getClassName(p[e].style,2,"open");o=true}else{h.className=getClassName(p[e].style,2,"close")}}c.appendChild(h);var a=p[e]["childMenu"];var j=document.createElement("ul");j.className=getClassName(p[e].style,3,"none");if(o){j.style.display="block";o=false}else{if((typeof activedTree)=="undefined"&&o){j.style.display="block";if(menuJson[0]["name"].indexOf("\u9996\u9875")!=-1){o=true}else{o=false}}else{j.style.display="none"}}(function(){var i=p[e].style;var k=j;h.onclick=function(){if(this.className==getClassName(i,2,"close")){k.style.display="block";this.className=getClassName(i,2,"open")}else{this.className=getClassName(i,2,"close");k.style.display="none"}l.style.height="";if(l.offsetHeight<559){l.style.height="559px"}initTreeHeight()}})();for(var d=0;d<a.length;d++){var n=document.createElement("li");var g;if(a[d].style.indexOf("TARGET_BLANK")!=-1){if(a[d].url==""){g="<a >"+a[d].name+"</a>"}else{g='<a target="_blank" href="'+a[d].url+'">'+a[d].name+"</a>"}}else{if(a[d].url==""){g="<a >"+a[d].name+"</a>"}else{g='<a target="_self" href="'+a[d].url+'">'+a[d].name+"</a>"}}n.innerHTML=getStyleIcon(a[d].style,g);n.treeId=a[d].id;n.className=getClassName(a[d].style,4,"none");j.appendChild(n);if(a[d].id==activedTree){j.style.display="block";n.id=getClassName(a[d].style,4,"now");h.className=getClassName(p[e].style,2,"open")}}c.appendChild(j)}}b.appendChild(c)}if((typeof serviceCenterHtml)!="undefined"&&serviceCenterHtml!=""){var f=document.createElement("div");f.innerHTML=serviceCenterHtml;b.appendChild(f)}}}function getStyleIcon(b,a){if(b.indexOf("ICON_NEW")!=-1){a+='<img class="tree_icon" src="http://img.china.alibaba.com/images/myalibaba/icon/target_new.gif">';return a}if(b.indexOf("ICON_NO")!=-1){a+='<img class="tree_icon" src="http://img.china.alibaba.com/images/myalibaba/icon/target_no.gif">';return a}if(b.indexOf("ICON_HOT")!=-1){a+='<img class="tree_icon" src="http://img.china.alibaba.com/images/myalibaba/icon/target_hot.gif">';return a}if(b.indexOf("ICON_FREE")!=-1){a+='<img class="tree_icon" src="http://img.china.alibaba.com/images/myalibaba/icon/target_free.gif">';return a}if(b.indexOf("ICON_TRUST")!=-1){a+='<img class="tree_icon" src="http://img.china.alibaba.com/images/myalibaba/icon/target_cxt.gif">';return a}return a}function getClassName(b,c,a){if(b==undefined){b=""}if(c==1){return"tree_item"}if(c==2){if(b.lastIndexOf("GRAY")!=-1&&a!="now"){return"tree_title_gray"}else{if(b.lastIndexOf("GRAY")!=-1&&a=="now"){return"tree_title_now"}else{if(b.lastIndexOf("GRAY")!=-1){return"tree_title_gray"}else{if(a=="none"){return"tree_title"}else{if(a=="open"){return"tree_title_open"}else{if(a=="close"){return"tree_title_close"}else{if(a=="now"){return"tree_title_now"}}}}}}}}if(c==3){return"tree_list"}if(c==4){if(a=="now"&&b.indexOf("GRAY")!=-1){return"tree_list_now_gray"}else{if(a=="now"){return"tree_list_now"}else{if(b.indexOf("GRAY")!=-1){return"tree_list_gray"}else{return"tree_list"}}}}};
function closeNote(){
if(document.getElementById("inforbox_1055")){
var obj = document.getElementById("inforbox_1055");
if(obj.innerHTML.indexOf("���������й�Ӧ��Ϣ�ķ������޸Ľ�ȫ����")!=-1){
obj.style.display = "none";
}
}
}
addLoadEvent(closeNote);
