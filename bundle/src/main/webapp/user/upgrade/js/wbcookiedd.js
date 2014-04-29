var NewVisiterDo = function(){
this.isNewVister = false;
};
NewVisiterDo.prototype = {
//设置cookie
setWBCookie: function(name, value, expire, domain, path){
value = escape(value);
value += (domain) ? '; domain=' + domain : '';
value += (path) ? "; path=" + path : '';
if (expire){
var date = new Date();
date.setTime(date.getTime() + (expire *24*60*60*1000));
value += "; expires=" + date.toGMTString();
}
document.cookie = name + "=" + value;
},
//检查cookie中的某个值
checkCookieValue: function(name){
var cookieWBValue = getWBCookie('alicnwb');
if(cookieWBValue && cookieWBValue.indexOf(name+'=') != -1 ){
return cookieWBValue.substring(cookieWBValue.indexOf(name+'=')+name.length+1, cookieWBValue.indexOf('|',cookieWBValue.indexOf(name+'=')));
}else{
return false;
}
//获取coookie值
function getWBCookie(name) {
var value = document.cookie.match('(?:^|;)\\s*'+name+'=([^;]*)');
return value ? unescape(value[1]) : '';
}
}
}
try{
var newGuide = new NewVisiterDo();
if(!newGuide.checkCookieValue('firstv')){
newGuide.isNewVister = true;
aliclick(null,'?tracelog=newvisitor');
newGuide.setWBCookie('alicnwb','firstv=1|',1*365*102,'.alibaba.com','/');
}
}catch(e){}
