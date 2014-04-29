/**
* author:ali-wd ym
*/
//<![CDATA[
var searchActivedItem=1;
var tracelogStr="";
var otherParamStr="";
var searchFormObj = null;
var tracelogInput = null;
var searchinputbox = null;
function searchInit(num,tracelog,otherParam){
tracelogStr = tracelog;
otherParamStr = otherParam;
if(document.getElementById("sform_"+num)!=null){
searchFormObj = document.getElementById("sform_01");
}else{
if(document.getElementsByName("sform_"+num).length!=0){
searchFormObj = document.getElementById("sform_"+num);
}
}
doclick(document.getElementById("node"+num),num);
}
function doclick(srcObj,searchID){
var tabList = srcObj.parentNode.getElementsByTagName("li");
if(srcObj.className.indexOf("activedtab")!=-1)return;
for(var i=0;i<tabList.length;i++){
if(tabList[i].className.indexOf("activedtab")!=-1)
{
tabList[i].className="ntab"+(tabList[i].className).replace("activedtab","");
}
if(tabList[i]==srcObj){
var searchdivobj = document.getElementById("sform_"+searchID);
var activedivobj = document.getElementById("sform_"+searchActivedItem);
activedivobj.style.display="none";
searchdivobj.style.display="block";
var v = document.getElementById("sform_"+searchActivedItem).getElementsByTagName("form")[0].keywords.value;
if(trim(v) == "" || v.substring(0,3) =="请输入"){
searchdivobj.getElementsByTagName("form")[0].keywords.value = searchdivobj.getElementsByTagName("form")[0].keywords.title;
}else{
searchdivobj.getElementsByTagName("form")[0].keywords.value = activedivobj.getElementsByTagName("form")[0].keywords.value;
}
if(document.all){
if(srcObj.offsetWidth==67){
document.getElementById("searchbox").style.backgroundImage="url(http://img.china.alibaba.com/images/cn/home/searchbg/index0312/activebg2.gif)";
}else{
document.getElementById("searchbox").style.backgroundImage="url(http://img.china.alibaba.com/images/cn/home/searchbg/index0312/activebg4.gif)";
}
document.getElementById("searchbox").style.backgroundPosition=srcObj.offsetLeft+"px";
}
}
}
searchActivedItem = searchID;
srcObj.className = "activedtab"+(srcObj.className).replace("otab","").replace("ntab","");//TAB切换
}
function checkform(frmObj){
var v = trim(frmObj.keywords.value);
if(v.length > 100){
alert("您输入的关键字过长！");
return false;
}
if(v == ""  || v.substring(0,3) =="请输入") {
alert("请输入关键字！");
return false;
}
//商友专用
if(searchActivedItem==11){
var searchType = document.getElementsByName("searchTypeInput");
for(var i =0;i<searchType.length;i++){
if(searchType[i].checked==true){
document.getElementsByName("searchType")[0].value = searchType[i].value;
}
}
}
}
/*
* 函数说明：去除头尾空格
* 参数：	字符串
* 返回值：	无
* 时间：2005-5-12
*/
function trim(inputString) {
return inputString.replace(/^ +/,"").replace(/ +$/,"");
}
/*
* 函数说明：取cookie值
* 参数：	cookie字段名
* 返回值：	cookie值
* 时间：2005-5-12
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
function overtab(srcObj){
if(srcObj.className.indexOf("activedtab")!=-1)return;
else
srcObj.className = "otab"+(srcObj.className).replace("ntab","");
}
function outtab(srcObj){
if(srcObj.className.indexOf("activedtab")!=-1)return;
else
srcObj.className = "ntab"+(srcObj.className).replace("otab","");
}
function focusit(inputobj){
if(inputobj.value.indexOf('请输入')!=-1)inputobj.value='';
inputobj.style.color="#000000";
}
function clickfriend(obj){
document.getElementById("sform_11").getElementsByTagName("form")[0].keywords.value=obj.title;
}
function MM_swapImgRestore() { //v3.0
var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}
function MM_preloadImages() { //v3.0
var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}
function MM_findObj(n, d) { //v4.01
var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
if(!x && d.getElementById) x=d.getElementById(n); return x;
}
function MM_swapImage() { //v3.0
var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
/*标签事件*/
function tabOver(obj,tabId,auto){
var tabObj = obj.parentNode.parentNode;
var tabArr=obj.parentNode.getElementsByTagName("li");
if(auto){
if(tabObj.getAttribute("autoRoll")=="no")return;
}else{
tabObj.setAttribute("autoRoll","no")
}
if(parseInt(tabObj.getAttribute("activeTab"))!=tabId){
if(document.all){
tabArr[parseInt(tabObj.getAttribute("activeTab")-1)].className="out";
obj.className="over";
}else{
tabArr[parseInt(tabObj.getAttribute("activeTab")-1)].setAttribute("class","out");
obj.setAttribute("class","over");
}
getElementsByClassName("tabContent",tabObj.getElementsByTagName("div")[0])[tabId-1].style.display="block";
getElementsByClassName("tabContent",tabObj.getElementsByTagName("div")[0])[parseInt(tabObj.getAttribute("activeTab")-1)].style.display="none";
tabObj.setAttribute("activeTab",tabId);
}
}
function tabOut(obj,tabId){
var tabObj = obj.parentNode.parentNode;
var tabArr=obj.parentNode.childNodes;
var tabObjId=tabObj.id;
if(tabObj.getAttribute("canautoTab")!="auto")return;
var tabUl=document.getElementById(tabObjId).getElementsByTagName("ul")[0];
tabObj.setAttribute("autoRoll","auto")
autoRoll(tabObjId,tabId,tabUl.getElementsByTagName("li").length)
}
/*内容事件*/
function tabconOver(obj){
var tabObj = obj.parentNode;
tabObj.setAttribute("autoRoll","no")
}
function tabConOut(obj){
var tabObj = obj.parentNode;
var tabObjId = tabObj.id;
if(tabObj.getAttribute("canautoTab")!="auto")return;
var tabUl=tabObj.getElementsByTagName("ul")[0];
tabObj.setAttribute("autoRoll","auto")
autoRoll(tabObjId,parseInt(tabObj.getAttribute("activeTab")),tabUl.getElementsByTagName("li").length)
}
/*自动播放*/
function autoRoll(tabObjId,currentNum,maxNum,tTime){
currentNum = parseInt(currentNum);
maxNum = parseInt(maxNum);
var tabObj = document.getElementById(tabObjId);
var tabLi= tabObj.getElementsByTagName("ul")[0].getElementsByTagName("li")[currentNum-1];
if(tabObj.getAttribute("autoRoll")!="auto")return;
tabOver(tabLi,currentNum,1);
document.getElementById(tabObjId).setAttribute("activeTab",currentNum);
if(currentNum<maxNum){
if( tabObj.getAttribute("autoRoll")=="auto")window.setTimeout('autoRoll("'+tabObjId+'","'+(currentNum+1)+'","'+maxNum+'","'+tTime+'")',tTime);
}else{
if(tabObj.getAttribute("autoRoll")=="auto")window.setTimeout('autoRoll("'+tabObjId+'","1","'+maxNum+'","'+tTime+'")',tTime);
}
}
function initTab(tabObjId,tabId,isAutoRoll,tTime){
var tabObj=document.getElementById(tabObjId);
var tabUl=document.getElementById(tabObjId).getElementsByTagName("ul")[0];
tabObj.setAttribute("activeTab",tabId);
tabObj.setAttribute("canautoTab",isAutoRoll);//记录初始化时tab是否允许自动播放
tabUl.getElementsByTagName("li")[0].className="out";
tabUl.getElementsByTagName("li")[tabId-1].className="over";
getElementsByClassName("tabContent",tabObj.getElementsByTagName("div")[0])[tabId-1].style.display="block";
tabObj.getElementsByTagName("div")[0].getElementsByTagName("div")[tabId-1].style.display="block";
if(isAutoRoll=="auto"){
tabObj.setAttribute("autoRoll","auto")
autoRoll(tabObjId,tabId,tabUl.getElementsByTagName("li").length,tTime)
}
}
function getElementsByClassName(className, parentElement) {
var children = (parentElement || document.body).getElementsByTagName('*');
var elements = [], child;
for (var i = 0, length = children.length; i < length; i++) {
child = children[i];
if (hasClassName(child, className))
elements.push(child);
}
return elements;
}
function hasClassName(element, className) {
var elementClassName = element.className;
if (elementClassName.length == 0) return false;
if (elementClassName == className ||
elementClassName.match(new RegExp("(^|\\s)" + className + "(\\s|$)")))
return true;
return false;
}
//]]>
