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
