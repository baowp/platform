!function(){
function $(el){return typeof el == 'string' ?  document.getElementById(el) : el;};
function inputTrim(inp,clear){
inp = $(inp);
var val  = (inp.value||'').replace(/^\s+|\s+$/,'');
if(clear) inp.value = val;
return val;
}
function addClass(el,cls){
if(el.className.indexOf(cls) == -1){
el.className += ' ' + cls;
}
}
function removeClass(el,cls){
el.className = el.className.replace(new RegExp('(^|\\s)' + cls + '(?:\\s|$)'), '$1');
}
function addEvent(el,name,fn){
el = $(el); if(!el) return;
if(el.addEventListener) return el.addEventListener(name,fn,false);
return el.attachEvent('on'+name,fn);
}
function setDisplay(el,display){
$(el).style.display = display || 'inline';
return arguments.callee;
}
//iframe方式打点，能够监控PV和Session
window.aliregclick = function(tracelog){
if(!tracelog) return;
try{
var ifr = document.createElement('iframe');
ifr.style.display = 'none';
ifr.onload = function(){
setTimeout(function(){
document.body.removeChild(ifr);
ifr = null;
},0);
}
ifr.src = 'http://page.china.alibaba.com/shtml/reg/tracelog.html?tracelog='+tracelog+'&r='+Math.random();
document.body.appendChild(ifr);
}catch(e){}
}
//检测登录名是否已经存在
function checkLoginidExist(existcallback,notcallback){
var regType = $('suggestLoginid');
if(regType)
{
var v = $('loginid').value,
url = ALIBABA_SERVER_URL+'/member/checkLoginIdAndRecommend.htm',
r = new AjaxRequest('post',url,false);
r.setParameter('TPL_NICK',v);
r.send(null);
r.onresult = function(){
var text = r.getText();
var suggestionIdArray = text.split(",");
var len = suggestionIdArray.length;
var suggestionType= suggestionIdArray[0].replace(/[^\d]/g,'');
if(suggestionType == '1')
{
if(len == 1){
$('suggestLoginid').style.display = "none";
$('suggestLoginid').parentNode.parentNode.style.height = 34+"px";
aliregclick(formCfg.loginid.tracelog.suggestError);
}else{
var suggestionInner = '<h6 class="h6">您可以使用以下登录名：</h6><ul class="clearfix uct">';
$('suggestLoginid').parentNode.parentNode.style.height = "auto";
for(var i = 1;i < len;i++)
{
suggestionInner = suggestionInner + '<li><input type="radio" onclick="AliRegMarket.suggestLoginid(this)" id="sln'+i+'" name="sln"/><label for="sln'+i+'">'+suggestionIdArray[i]+'</label></li>';
}
suggestionInner = suggestionInner + '</ul>';
$('suggestLoginid').innerHTML = suggestionInner;
$('suggestLoginid').style.display = "";
aliregclick(formCfg.loginid.tracelog.suggestOk);
}
}
else if(suggestionType == '2'){
$('suggestLoginid').style.display = "none";
$('suggestLoginid').parentNode.parentNode.style.height = 34+"px";
}
if(suggestionIdArray[0].replace(/\s/g,'') == 1 || suggestionIdArray[0].replace(/\s/g,'') == 2){
existcallback && existcallback();
}else{
$('suggestLoginid').style.display = "none";
$('suggestLoginid').parentNode.parentNode.style.height = 34+"px";
notcallback && notcallback();
}
}
return;
}
var v = $('loginid').value,
url = ALIBABA_SERVER_URL+'/member/check_login_id_tmp.htm',
r = new AjaxRequest('post',url,false);
r.setParameter('TPL_NICK',v);
r.send(null);
r.onresult = function(){
var text = r.getText();
if(text.replace(/\s/g,'') == 1){
existcallback && existcallback();
//登录名已经存在时打点
aliregclick(formCfg.loginid.tracelog.exist)
}else{
notcallback && notcallback();
}
}
}
//检测邮箱是否已经存在
function checkEmailExist(existcallback,notcallback){
var email = $('emailName').value+'@'+$('emailType').value;
if(!(/^[\w\-]+(\.[\w\-]*)*@[\w\-]+([\.][\w\-]+)+$/.test(email))) return;
var url = ALIBABA_SERVER_URL+'/member/check_email_tmp.htm',
r = new AjaxRequest('post',url,false);
r.setParameter('TPL_EMAIL',email);
r.send(null);
r.onresult = function(){
var text = r.getText().split(';');
if(text[0] != 0 && text[1]){
existcallback && existcallback(text[1],email);
//邮箱已经存在时打点
aliregclick(formCfg.emailName.tracelog.exist);
}else{
notcallback && notcallback();
}
}
}
/*
*通过AJAX检查字段是否是违禁词
*@param value 需要检查字段
*@param denyId 违禁词类型
*@param feild 检查对象相关参数
*/
function checkDenyWord(value, denyId, feild, el){
var tip = $(feild.tipId);
tip.className = 'normal';
tip.innerHTML = '检测中，请稍等...';
var url = ALIBABA_SERVER_URL+'/member/checkLimitedWord.htm', r = new AjaxRequest('post',url,false);
r.setParameter("limitedWord",value); //要判断的限制词的值
r.setParameter("limitedId",denyId); //要判断的限制词类型
r.send(null);
r.onresult = function(){
if(r.getText().replace(/\s/g,'') == 'true'){
//是违禁词
tip.innerHTML = feild.txt.denyWords;
tip.className = 'error';
//给输入框添加错误样式
addClass(el,'iTextError');
//违禁词打点,销售和采购目前还没有监控参数,需要先判断
if(feild.tracelog.denyWords) aliregclick(feild.tracelog.denyWords);
if(feild.tracelog.total) aliregclick(feild.tracelog.total);
}else{
//不是违禁词
tip.innerHTML = '&nbsp;';
tip.className = 'correct';
//给输入框删除错误样式和得到焦点时的样式
removeClass(el,'iTextError');
removeClass(el,'iTextNormal');
//正确打点
aliregclick(feild.tracelog.success);
}
}
}
//隐藏公司所在地的错误信息
function hideCompanyError(){
$('companyAreaTip').className = 'none';
$('companyAreaTip').innerHTML = '';
}
//通过script方式加载公司所在地的数据，数据放在countryAreaData的变量里
function loadAreaData(callback){
if(typeof countryAreaData == 'undefined'){
var script = document.createElement('script');
script.type = 'text/javascript';
if(document.all){
script.onreadystatechange = function(){
if(this.readyState == 'loaded' || this.readyState == 'complete'){
script.onreadystatechange = null;
callback && callback(dealAreaData(countryAreaData));
}
}
}else{
script.onload = function(){
callback && callback(dealAreaData(countryAreaData));
}
}
script.src = ALIBABA_SERVER_URL+'/member/join_form_company_area_interface.do';
document.body.appendChild(script);
}else{
callback && callback(countryAreaData);
}
}
//把数组形式的数据转换为对象形式--2009-8-10
function dealAreaData(cad){
if(cad instanceof Array){
for(var i = 0, l = cad.length, result = {}; i < l; i++){
for(var a in cad[i]){
result[a] = cad[i][a];
break;
}
}
return countryAreaData = result;
}else{
return cad;
}
}
//创建个片段并添加个option
function createFlagMent(v,t){
var docFlagment = document.createDocumentFragment(),
opt = document.createElement('option');
opt.value = v;
opt.text = opt.innerText = t;
docFlagment.appendChild(opt);
return docFlagment;
}
//展开国家
function showCountry(){
hideCompanyError();
loadAreaData(function(data){
var docFlagment = document.createDocumentFragment(),
areaCountry = $('areaCountry'),
currentCountryValue = areaCountry.value,
i = pos = 0;
for(var name in data){
i++;
name = name.split('_');
var opt = document.createElement('option');
opt.value = name[1];
opt.text = opt.innerText = name[0];
if(name[1] == 'CN'){
i--;
if(docFlagment.firstChild) docFlagment.insertBefore(opt,docFlagment.firstChild);
else docFlagment.appendChild(opt);
}else{
docFlagment.appendChild(opt);
if(name[1] == currentCountryValue) pos = i;
}
}
areaCountry.options.length = 0;
areaCountry.appendChild(docFlagment);
//ie6下要通过延时来设置选中状态
setTimeout(function(){
areaCountry.options[pos].selected = true;
showProvince();
},0);
})
}
//显示省份
var provinceReseted = false;
function showProvince(){
hideCompanyError();
loadAreaData(function(data){
//初始化省份数据
if($('areaCountry').value != 'CN' || $('areaCountry').value == 'CN' && !provinceReseted){
var currentCountry = $('areaCountry').options[$('areaCountry').selectedIndex].text+'_'+$('areaCountry').value,
provinces = data[currentCountry],
i = 0,
pos = 0;
var docFlagment = createFlagMent('',$('areaCountry').value == 'CN' ? '省份' : '');
for(var name in provinces){
i++;
name = name.split('_');
var opt = document.createElement('option');
opt.value = name[1];
opt.text = opt.innerText = name[0];
if($('areaProvince').value == name[1]) pos = i;
docFlagment.appendChild(opt);
}
}
if($('areaCountry').value == 'CN'){
//将中国的省份，城市，区县下拉框显示
setDisplay('areaProvince')('areaCity')('areaCounty');
//将其他国家的省份下拉框，省份输入框，城市输入框隐藏
setDisplay('otherProvince','none')('otherCity','none')('areaOtherProvince','none');
//初始化
if(!provinceReseted){
$('areaProvince').options.length = 0;
$('areaProvince').appendChild(docFlagment);
if(!provinceReseted && pos) {
//ie6下要通过延时来设置选中状态
setTimeout(function(){
$('areaProvince').options[pos].selected = true;
showCity();
},0);
}else{
showCity();
}
provinceReseted = true;
}
}else{
setDisplay('areaProvince','none')('areaCity','none')('areaCounty','none');
//其他国家含有身份
if(i>1){
setDisplay('areaOtherProvince');
setDisplay('otherProvince','none');
$('areaOtherProvince').options.length = 0;
$('areaOtherProvince').appendChild(docFlagment);
}else{
setDisplay('areaOtherProvince','none');
setDisplay('otherProvince');
}
setDisplay('otherCity');
}
})
}
//显示城市
var cityReseted = false;
function showCity(){
hideCompanyError();
loadAreaData(function(data){
//城市下拉框只会出现在中国中
if($('areaCountry').value != 'CN') return;
var docFlagment = createFlagMent('','地级市');
//如果省份中没值的话，则只有 “地级市”
if(!$('areaProvince').value){
$('areaCity').options.length = 0;
$('areaCity').appendChild(docFlagment);
showCounty();
}else{
var currentCountry = $('areaCountry').options[$('areaCountry').selectedIndex].text+'_'+$('areaCountry').value,
provinces = data[currentCountry],
currentProvince = $('areaProvince').options[$('areaProvince').selectedIndex].text+'_'+$('areaProvince').value,
cities = provinces[currentProvince];
var i = 0,
pos = 0;
for(var name in cities){
i++;
name = name.split('_');
var opt = document.createElement('option');
opt.value = name[1];
opt.text = opt.innerText = name[0];
if(!cityReseted && name[1] == $('areaCity').value) pos = i;
docFlagment.appendChild(opt);
}
$('areaCity').options.length = 0;
$('areaCity').appendChild(docFlagment);
if(!cityReseted && pos){
//ie6下要通过延时来设置选中状态
setTimeout(function(){
$('areaCity').options[pos].selected = true;
showCounty();
},0);
}else{
showCounty();
}
cityReseted = true;
}
})
}
//显示县级市
var countyReseted = false;
function showCounty(){
hideCompanyError();
loadAreaData(function(data){
//城市下拉框只会出现在中国中
if($('areaCountry').value != 'CN') return;
var docFlagment = createFlagMent('','市、县级市、县');
if(!$('areaProvince').value || !$('areaCity').value){
$('areaCounty').options.length = 0;
$('areaCounty').appendChild(docFlagment);
}else{
var currentCountry = $('areaCountry').options[$('areaCountry').selectedIndex].text+'_'+$('areaCountry').value,
provinces = data[currentCountry],
currentProvince = $('areaProvince').options[$('areaProvince').selectedIndex].text+'_'+$('areaProvince').value,
cities = provinces[currentProvince],
currentCity = $('areaCity').options[$('areaCity').selectedIndex].text+'_'+$('areaCity').value;
counties = cities[currentCity];
var pos = 0;
for(var i=0,length=counties.length;i<length;i++){
var name = counties[i].split('_');
var opt = document.createElement('option');
opt.value = name[1];
opt.text = opt.innerText = name[0];
if(!countyReseted && name[1] == $('areaCounty').value) pos = i+1;
docFlagment.appendChild(opt);
}
$('areaCounty').options.length = 0;
$('areaCounty').appendChild(docFlagment);
if(!countyReseted && pos){
//ie6下要通过延时来设置选中状态
setTimeout(function(){
$('areaCounty').options[pos].selected = true;
},0);
}
countyReseted = true;
}
})
}
//市场注册的全局对象
window.AliRegMarket = {
//change validate code image
changeValCode:function(imgid,aid,focus){
imgid = $(imgid);
var src = imgid.src;
imgid.src = src+'&r='+Math.random();
setTimeout(function(){
if(focus) $('valcode').focus();
},50);
aliregclick('reg_market_chgcode');
},
//bind placeholder for input
placeHolderBind:function(inputid,labelid){
labelid = $(labelid);
inputid = $(inputid);
setTimeout(function(){
//if document focus on this element,do not show label tips
if(inputid.className.indexOf('iTextNormal') == -1){
setDisplay(labelid,inputTrim(inputid,true) ? 'none' : 'inline');
}
},500)
addEvent(inputid,'blur',function(){
!inputTrim(inputid,true) && setDisplay(labelid,'inline');
})
addEvent(inputid,'focus',function(){
setDisplay(labelid,'none');
})
},
initEmail:function(){
var email = $('email').value;
if(!(/^[\w\-]+(\.[\w\-]*)*@[\w\-]+([\.][\w\-]+)+$/.test(email))) return ;
email = email.split('@');
$('emailName').value = email[0];
$('emailType').value = email[1];
},
showSuggestLoginid:function(show,height){
var loginid = $('suggestLoginid');
if(show){
loginid.style.display = 'block';
loginid.parentNode.parentNode.style.height = height;
}else{
loginid.style.display = 'none';
loginid.parentNode.parentNode.style.height = "36px";
removeClass($('loginid'),'iTextError');
}
},
checkEmail:function(id,checklen){
if('emailName' == id || 'emailType' == id || checklen){
var emailName = $('emailName'), emailType = $('emailType'), tip = $('emailTip'), email = emailName.value+'@'+emailType.value;
//从前缀或者后缀推动焦点，判断 或者 提交表单时检查
$('email').value = email;//提交表单的时候要给隐藏的email字段赋值
if(inputTrim(emailName) != '' && inputTrim(emailType) != '' || checklen){
if (!(/^[\w\-]+(\.[\w\-]*)*@[\w\-]+([\.][\w\-]+)+$/.test(email))) {
addClass(tip,'error');
if(checklen && inputTrim(emailName) == '' && inputTrim(emailType) == ''){
tip.innerHTML = nullTip;
}else{
tip.innerHTML = formCfg['emailType'].txt.notValid;
}
if(!checklen){
//如果是按提交按钮,就不用添加样式
if(!(/^[\w\-]+(\.[\w\-]*)*$/.test(emailName.value))){
//前缀不对
addClass(emailName,'iTextError');
}else{
removeClass(emailName,'iTextError');
}
if(!(/^[\w\-]+([\.][\w\-]+)+$/.test(emailType.value))){
//后缀不对
addClass(emailType,'iTextError');
}else{
removeClass(emailType,'iTextError');
}
}
return false;
}
//总长度超过50
if(email.length > 50){
addClass(tip,'error');
tip.innerHTML = '电子邮箱超出最大长度50个字！';
addClass(emailName,'iTextError');
addClass(emailType,'iTextError');
return false;
}
}else{
removeClass(emailName,'iTextError');
removeClass(emailType,'iTextError');
removeClass(emailName,'iTextNormal');
removeClass(emailType,'iTextNormal');
tip.innerHTML = '&nbsp;';
tip.className = '';
return false;
}
}
return true;
},
//初始化表单，给表单添加focus和blur事件
initForm:function(){
var tRe = /iTextError|iTextNormal/g;
for(var id in formCfg){
var el = $(id);
if(!el) continue;
with({id:id,el:el}){
//对登录名加keyup事情，检查第一个字母是不是英文字母
if(id == 'loginid'){
var tip = $(formCfg[id].tipId);
addEvent(id,'keyup',function(){
var v = $(id).value, temp = formCfg[id];
if((/[a-zA-Z]/.test(v.charAt(0))) || v == ''){
removeClass(el,'iTextError');
tip.className = temp.cls.normal;
tip.innerHTML = temp.txt.normal;
}else{
//el.className.replace(tRe,'')+' iTextError';
addClass(el,'iTextError');
tip.className = temp.cls.notValid;
tip.innerHTML = temp.txt.notValid;
}
});
}
addEvent(id,'focus',function(){
var tip = $(formCfg[id].tipId), cls = tip.className;
if('loginid' == id){
if($('suggestLoginid').style.display == ""){
$('suggestLoginid').style.display = "none";
$('suggestLoginid').parentNode.parentNode.style.height = 34+"px";
}
}
if(cls.indexOf('error') > -1 ) $(id).select();
if((cls.indexOf('error') > -1 && tip.innerHTML != nullTip)  || cls.indexOf('correct') > -1 ) return;
//对验证码特殊处理
if('valcode' == id && $('valcode').value.length == 4){
tip.className = 'none';
tip.innerHTML = chgCodeInitTxt;
return ;
}
if(formCfg[id].txt.normal){
tip.className = formCfg[id].cls.normal || 'normal';
tip.innerHTML = formCfg[id].txt.normal;
el.className = el.className.replace(tRe,'')+' iTextNormal';
}
});
addEvent(id,'change',function(){
aliregclick(formCfg[id].tracelog.change);//通用注册表单值改变时监控参数
});
addEvent(id,'blur',function(){
var v = inputTrim(el,true), tip = $(formCfg[id].tipId);
v = v.replace(/(^\s*)|(\s*$)/g, "");
if(('emailName' == id || 'emailType' == id) && emailFlag) return;//点击注册邮箱的链接时
if('emailName' == id || 'emailType' == id){
if(AliRegMarket.checkEmail(id) == false) return;
}
//内容为空时，去掉提示信息
if(!inputTrim(el,true)){
if('valcode' == id && codeFlag) return;
tip.className = '';
tip.innerHTML = '';
el.className = el.className.replace(tRe,'');
if('valcode' == id && !codeFlag) tip.innerHTML = chgCodeInitTxt;
if('valcode' == id && !codeFlag) tip.innerHTML = chgCodeInitTxt;
/*如果有推荐ID，则由于新内容为空，从而隐藏推荐ID*/
if($('suggestLoginid') && $('loginid').value == ''){
$('suggestLoginid').style.display = "none";
}
return;
}
//对检测的结果进行判断
var result = formCfg[id].check.call(el,v,v.toLowerCase(),v.toUpperCase());
if(result == 'notValid' && id == 'loginid'){
AliRegMarket.showSuggestLoginid(false,'34px');
}
if(typeof result == 'string'){
if (result !== 'denywordcheck') {
tip.className = formCfg[id].cls[result] || 'error';
tip.innerHTML = formCfg[id].txt[result];
el.className = el.className.replace(tRe, '') + ' iTextError';
//错误打点
aliregclick(formCfg[id].tracelog[result]);
aliregclick(formCfg[id].tracelog.total);
}
}else{
//对登录名和邮箱进行是否已经存在校验处理
if('loginid' == id || 'emailType' == id || 'emailName' == id){
var fn = 'loginid' == id ? checkLoginidExist : checkEmailExist;
fn(function(){
var result = 'exist';
tip.className = formCfg[id].cls[result] || 'error';
var ih = formCfg[id].txt[result];
if('loginid' != id){
var url = ALIBABA_SERVER_URL+'/member/cancelEmailValidation.htm?email='+arguments[1]+'&loginId='+arguments[0];
ih = ih.replace('{#}',url);
}
tip.innerHTML = ih;
el.className = el.className.replace(tRe,'')+' iTextError';
if('loginid' == id){
addClass(el,'iTextError');
}else{
addClass($('emailName'),'iTextError');
addClass($('emailType'),'iTextError');
}
//登录名和邮箱存在时打点
aliregclick(formCfg[id].tracelog[result]);
},function(){
tip.className = 'correct';
tip.innerHTML  = '&nbsp;';
if('loginid' == id) el.className = el.className.replace(tRe,'');
else{
var name = $('emailName'), type = $('emailType');
name.className = name.className.replace(tRe,'');
type.className = type.className.replace(tRe,'');
}
});
}else{
var f = true;
if('phoneCountry' == id || 'phoneArea' == id || 'phoneNumber' == id){
for(var i=0,a=['phoneCountry','phoneArea','phoneNumber'],len=a.length;i<len;i++){
if(formCfg[a[i]].check.call($(a[i]),$(a[i]).value)){
f = false;
break;
}
}
}
if('faxCountry' == id || 'faxArea' == id || 'faxNumber' == id){
for(var i=0,a=['faxCountry','faxArea','faxNumber'],len=a.length;i<len;i++){
if(formCfg[a[i]].check.call($(a[i]),$(a[i]).value)){
f = false;
break;
}
}
}
if('valcode' == id){
f = false;
tip.innerHTML = chgCodeInitTxt;
}else{
tip.innerHTML  = '&nbsp;';
}
tip.className = f ? 'correct' : 'none';
el.className = el.className.replace(tRe,'');
tip.innerHTML = '&nbsp;'
//信息正确时打点
if(f) aliregclick(formCfg[id].tracelog.success);
}
}
})
}
}
},
//登录名提示
suggestLoginid:function(el){
var tip = $('loginIdTip'),sugget = $('suggestLoginid'),login = $('loginid');
tip.className = 'correct';
tip.innerHTML  = '&nbsp;';
login.value = el.parentNode.getElementsByTagName('label')[0].innerHTML || '';
removeClass(login,'iTextError');
sugget.style.display = 'none';
sugget.parentNode.parentNode.style.height = 34+"px";
el.checked = false;
aliregclick(formCfg.loginid.tracelog.suggestUsed);
},
//初始化公司所在地数据，延时500ms加载数据
initArea:function(){
addEvent('areaCountry','change',showProvince);
addEvent('areaProvince','change',showCity);
addEvent('areaCity','change',showCounty);
showCountry();
addEvent('otherProvinceTxt','change',hideCompanyError);
addEvent('otherCityTxt','change',hideCompanyError);
},
//初始化主营行业和主营方向
initKeyword:function(){
var category = $('category'),_this = this, tip = $('categoryTip');
this.dealSelect(category,'color');
addEvent(category,'change',function(){
_this.dealSelect(category,'color');
if(category.value != ''){
tip.className = '';
tip.innerHTML = '&nbsp;';
}else{
tip.className = 'error';
tip.innerHTML = '请选择您的主营行业。';
}
//主营行业改变时打点
aliregclick('reg_market_chg16');
});
var businessRoleSale = $('business_role_sale'),businessRoleBuy = $('business_role_buy'),
keywordTipBuy = $('keywordTipBuy'),keywordTipSale = $('keywordTipSale'),
salekeyword = $('salekeyword'),buykeyword = $('buykeyword');
addEvent(businessRoleSale,'click',function(){
if(businessRoleSale.checked){
salekeyword.className = 'ka';
salekeyword.focus();
}else{
salekeyword.value = '';
salekeyword.className = 'kn';
keywordTipSale.className = 'none';
}
});
addEvent(businessRoleBuy,'click',function(){
if(businessRoleBuy.checked){
buykeyword.className = 'ka';
buykeyword.focus();
}else{
buykeyword.value = '';
buykeyword.className = 'kn';
keywordTipBuy.className = 'none';
}
});
addEvent(salekeyword,'focus',function(){
businessRoleSale.checked = true;
if(keywordTipSale.className.indexOf('error') == -1 && keywordTipSale.className.indexOf('correct') == -1){
addClass(salekeyword,'ka');
addClass(salekeyword,'iTextNormal');
}
});
addEvent(buykeyword,'focus',function(){
businessRoleBuy.checked = true;
if(keywordTipBuy.className.indexOf('error') == -1 && keywordTipBuy.className.indexOf('correct') == -1){
addClass(buykeyword,'ka');
addClass(buykeyword,'iTextNormal');
}
});
setTimeout(function(){
salekeyword.className = inputTrim(salekeyword) ||  businessRoleSale.checked ? 'ka' : 'kn';
buykeyword.className = inputTrim(buykeyword) ||  businessRoleBuy.checked ? 'ka' : 'kn';
},200)
},
dealSelect:function(select,style){
select.style[style] = select.options[select.selectedIndex].style[style];
},
//表单提交数据检测并打点
formSubmit:function(){
var flag = true;
for(var id in formCfg){
var el = $(id), v = inputTrim(el,true);
v = v.replace(/(^\s*)|(\s*$)/g, "");
if(!v){
if(formCfg[id].required){
flag = false;
var tip = $(formCfg[id].tipId);
tip.className = 'error';
tip.innerHTML = nullTip;
//为空时打点
aliregclick(formCfg[id].tracelog.isNull);
}
}else if (formCfg[id].ajaxCheck) {
continue;//如果是通过ajax校验的(目前只包含违禁词的检查),点击提交按钮的时候就不用管，直接提交给后台处理。
}else{
var result = formCfg[id].check.call(el,v,v.toLowerCase(),v.toUpperCase());
if(result){
flag = false;
aliregclick(formCfg[id].tracelog[result]);
aliregclick(formCfg[id].tracelog.total);
var tip = $(formCfg[id].tipId);
if(formCfg[id].txt[result]){
tip.className = formCfg[id].cls[result] || 'error';
tip.innerHTML = formCfg[id].txt[result];
}
}else{
//正确时打点
aliregclick(formCfg[id].tracelog.success);
}
}
}
//检测公司类型，打点
if ($('company_type_5').checked) {
aliregclick('company_type_5');
}else if ($('company_type_6').checked) {
aliregclick('company_type_6');
}else if ($('company_type_7').checked) {
aliregclick('company_type_7');
}else if($('company_type_8').checked){
aliregclick('company_type_8');
}
var category = $('category');
//检测主营行业
if(category.value == ''){
var tip = $('categoryTip');
tip.className = 'error';
tip.innerHTML = '请选择您的主营行业。';
flag = false;
//为空时打点
aliregclick('reg_market_null16');
}else{
//正确时打点
aliregclick('reg_market_s16');
}
//检测主营方向
var sale = $('salekeyword'), buy = $('buykeyword'), saleDD = formCfg.salekeyword,buyDD = formCfg.buykeyword,
saleTip = $('keywordTipSale'), buyTip = $('keywordTipBuy');
if(sale.value == '' && buy.value == ''){
flag = false;
//销售
saleTip.className = 'error';
saleTip.innerHTML = saleDD.txt.normal;
//销售为空时打点
aliregclick(saleDD.tracelog.isNull);
//采购
buyTip.className = 'error';
buyTip.innerHTML = buyDD.txt.normal;
flag = false;
//采购为空时打点
aliregclick(buyDD.tracelog.isNull);
}else{
if(sale.value != ''){
saleTip.className = 'correct';
saleTip.innerHTML = '&nbsp;';
if(buy.value == ''){
buyTip.className = '';
buyTip.innerHTML = '&nbsp;';
}
//销售正确时打点
aliregclick(buyDD.tracelog.success);
}
if(buy.value != ''){
buyTip.className = 'correct';
buyTip.innerHTML = '&nbsp;';
if(sale.value == ''){
saleTip.className = '';
saleTip.innerHTML = '&nbsp;';
}
//采购正确时打点
aliregclick(buyDD.tracelog.success);
}
//都为空时添加样式
removeClass(sale.parentNode.parentNode,'error');
}
//检测公司所在地
loadAreaData(function(){
$('country').value = $('areaCountry').value;
if($('areaCountry').value == 'CN'){
$('province').value = $('areaProvince').value;
if($('SelectCapitalId')) $('SelectCapitalId').value = $('areaCity').value;
$('city').value = $('areaCounty').value;
}else{
$('province').value = $('otherProvinceTxt').value || $('areaOtherProvince').value;
$('city').value = $('otherCityTxt').value;
}
if(!$('country').value || !$('city').value){
$('companyAreaTip').className = 'error';
$('companyAreaTip').innerHTML = '请选择国家或城市。';
flag = false;
//为空时时打点
aliregclick('reg_market_null17');
}else{
//正确时打点
aliregclick('reg_market_s17');
}
});
if(AliRegMarket.checkEmail('',true) === false){
flag = false;
}
return flag;
},
//服务条款
bindPrivacy:function(openA,ifr,pri,btn,pro){
pro = pro || {height:500,width:800};
addEvent($(openA),'click',function(event){
event = event || window.event;
if (event.preventDefault) event.preventDefault();
else event.returnValue = false;
var hiddenLayer = $('privacyhiddenIfr');
with(hiddenLayer.style){
display = 'block';
height = document.body.scrollHeight+'px';
width = document.body.scrollWidth+'px';
}
var privacyLayer = $('privacyIfr');
if(privacyLayer.src.indexOf('iframe_delete')==-1){
privacyLayer.src = 'http://page.china.alibaba.com/shtml/coop/terms2.shtml?iframe_delete=true';
}
var privacyIfrLayer = $('privacyIfrLayer');
privacyIfrLayer.style.display = 'block';
privacyIfrLayer.style.left = ((window.innerWidth ||  document.documentElement && document.documentElement.clientWidth || document.body.offsetWidth)-pro.width)/2+'px';
privacyIfrLayer.style.top = (document.body.scrollTop||document.documentElement.scrollTop)+((window.innerHeight ||  document.documentElement && document.documentElement.clientHeight || document.body.offsetHeight)-pro.height)/2+'px';
})
addEvent($('privacyBtn'),'click',function(){
$('privacyhiddenIfr').style.display = 'none';
$('privacyIfrLayer').style.display = 'none';
})
},
//初始化公司黄页
initYellowPage:function(){
addEvent('submit','mouseover',function(){
if($('isAddCompany').checked == true){
$('cypage').style.display = 'block';
}
addClass($('submit'),'over');
});
addEvent('submit','mouseout',function(){
$('cypage').style.display = 'none';
removeClass($('submit'),'over');
});
//点击按钮打点
addEvent('submit','click',function(){
aliregclick('reg_market_infosub_click');
});
addEvent('cypageLable','mouseover',function(){
if($('isAddCompany').checked == true){
$('cypage').style.display = 'block';
}
});
addEvent('cypageLable','mouseout',function(){
$('cypage').style.display = 'none';
});
//公司类型中的'个人'添加事件，避免修改模版，暂时在公司黄页的方法中实现
addEvent('company_type_8','click',function(){
if($('firstName').value && !$('company').value){
$('company').value = $('firstName').value+'（个人）';
$('company').focus();
}
});
},
regEmailOver:function(){
emailFlag = true;
},
regEmailOut:function(){
emailFlag = false;
},
regEmailClick:function(){
if($('emailName').className.indexOf('iTextNormal') > -1) $('emailName').focus();
else $('emailType').focus();
emailFlag = false;
},
regChgCodeOver:function(){
codeFlag = true;
},
regChgCodeOut:function(){
codeFlag = false;
}
}
var setSafePwdTxt = '<a href="http://info.china.alibaba.com/helpcenter/tips/s5011167-d5506534.html" target="_blank">如何安全设置密码？</a>',
emailTip = '请填写有效的电子邮箱，便于找回密码。如：abc@yahoo.com 没有电子邮箱？<a href="https://member.cn.yahoo.com/cnreg/reginfo_ycn.html" target="_blank" onmouseover="AliRegMarket.regEmailOver()" onmouseout="AliRegMarket.regEmailOut()" onclick="AliRegMarket.regEmailClick()" tabindex="998">注册雅虎邮箱</a> | <a href="http://reg.email.163.com/mailregAll/reg0.jsp" target="_blank" onmouseover="AliRegMarket.regEmailOver()" onmouseout="AliRegMarket.regEmailOut()" onclick="AliRegMarket.regEmailClick()" tabindex="999">注册网易邮箱</a>',
emailFlag = false,
chgCodeTxt = '<a href="#" hidefocus="true" onmouseover="AliRegMarket.regChgCodeOver()" onmouseout="AliRegMarket.regChgCodeOut()" onclick="AliRegMarket.changeValCode(\'regValImg\',\'regValA\',true);return false" class="hideFocus" id="regValA">图片验证码看不清，换一张。</a>',
codeFlag = false,
chgCodeInitTxt = '<a href="#" hidefocus="true" onclick="AliRegMarket.changeValCode(\'regValImg\',\'regValA\');return false" class="chgImg hideFocus" id="regValA">图片验证码看不清，换一张。</a>',
nullTip = '此项为必填项！';
var formCfg = {
loginid:{
required:true,
tipId:'loginIdTip',
txt:{
normal:'以英文字母开头的4-20个字母或数字，不能用中文，注册成功后不可修改。',
notValid:'请您输入由英文字母开头的4-20字母和数字，不支持中文和特殊字符（@、#、$、%等）。',
exist:'您输入的登录名已经存在，请重新填写。'
},
cls:{
normal:'normal',
notValid:'error'
},
tracelog:{
change:'reg_market_chg1',
isNull:'reg_market_null1',
exist:'reg_market_el2',
notValid:'reg_market_el1',
total:'reg_market_e_loginid',
success:'reg_market_s1',
suggestOk:'reg_suggest_ok',
suggestError:'reg_suggest_error',
suggestUsed:'reg_suggest_used'
},
check:function(v,lv,uv){
this.value = lv;
if(/^\d/.test(lv)){
aliregclick('reg_market_e_loginid1');
}else if(!(/^[a-z0-9]+$/i.test(lv))){
aliregclick('reg_market_e_loginid2');
}else if(!(/.{4,20}/.test(lv))){
aliregclick('reg_market_e_loginid3');
}
if(!(/^[a-z][a-z0-9]{3,19}$/i.test(lv))) return 'notValid';
}
},
password:{
required:true,
tipId:'passwordTip',
txt:{
normal:'6-20个英文字母或数字，不要包含登录名、姓名等个人信息，不要使用相同和连续的字母或数字。',
notValid:'6-20个英文字母或数字，不要包含登录名、姓名等个人信息，不要使用相同和连续的字母或数字。',
isLoginId:'请不要使用登录名作为密码。'+setSafePwdTxt,
conLoginId:'请不要在密码中包含登录名。'+setSafePwdTxt,
loginIdCon:'请不要将登录名的一部分作为密码。'+setSafePwdTxt,
conFirstName:'请不要在密码中包含姓名。'+setSafePwdTxt,
conNumber:'请不要使用连续的数字作为密码。'+setSafePwdTxt,
conAChar:'请不要使用连续的大写字母作为密码。'+setSafePwdTxt,
conaChar:'请不要使用连续的小写字母作为密码。'+setSafePwdTxt,
uniNumber:'请不要使用相同的数字作为密码。'+setSafePwdTxt,
uniChar:'请不要使用相同的字母作为密码。'+setSafePwdTxt,
equalEmail:'请不要使用邮箱前缀作为密码。'+setSafePwdTxt,
equalMobile:'请不要使用手机号作为密码。'+setSafePwdTxt,
equalZip:'请不要使用邮编作为密码。'+setSafePwdTxt,
equalPhone:'请不要使用电话号码作为密码。'+setSafePwdTxt,
equalFax:'请不要使用传真号码作为密码。'+setSafePwdTxt,
isPassword:'请不要使用"password"作为密码。'+setSafePwdTxt
},
cls:{
normal:'normal',
notValid:'error'
},
tracelog:{
change:'reg_market_chg2',
total:'reg_market_e_password',
isNull:'reg_market_null2',
notValid:'reg_market_ep14',
isLoginId:'reg_market_ep1',
conLoginId:'reg_market_ep2',
loginIdCon:'reg_market_ep3',
conFirstName:'reg_market_ep10',
conNumber:'reg_market_ep4',
conAChar:'reg_market_ep5',
conaChar:'reg_market_ep6',
uniNumber:'reg_market_ep7',
uniChar:'reg_market_ep8',
equalEmail:'reg_market_ep9',
equalMobile:'reg_market_ep11',
equalZip:'',
equalPhone:'reg_market_ep12',
equalFax:'',
isPassword:'reg_market_ep13',
success:'reg_market_s2'
},
check:function(v,lv,uv){
if(!(/^[a-z0-9]{6,20}$/i.test(v))) return 'notValid';
var loginId = $('loginid');
if(loginId){
loginId = loginId.value.toLowerCase();
//与登录名相同
if(loginId == lv) return 'isLoginId';
//登录名包含密码
if(loginId.indexOf(lv)!=-1) return 'loginIdCon';
//密码包含登录名
if(loginId && lv.indexOf(loginId)!=-1) return 'conLoginId';
}
//包含真实姓名
if($('firstName') && $('firstName').value && lv.indexOf($('firstName').value.toLowerCase())>-1) return 'conFirstName';
//连续的数字
var n = '0123456789';
if(n.indexOf(lv) > -1 || n.indexOf(lv.split('').reverse().join(''))>-1) return 'conNumber';
//连续的大写字母
var az = 'abcdefghijklmnopqrstuvwxyz';
if(v == uv && (az.indexOf(lv) > -1 || az.indexOf(lv.split('').reverse().join(''))>-1)) return 'conAChar';
//连续的小写字母
if(v == lv && (az.indexOf(lv) > -1 || az.indexOf(lv.split('').reverse().join(''))>-1)) return 'conaChar';
//相同的数字
if(/^(\d)\1+$/.test(lv)) return 'uniNumber';
//相同的字母
if(/^([a-zA-Z])\1+$/.test(lv)) return 'uniChar';
//与邮箱前缀相同
if($('emailName') && ($('emailName').value||'').toLowerCase() == lv) return 'equalEmail';
//与手机号码相同
if($('mobile') && $('mobile').value.toLowerCase() == lv) return 'equalMobile';
//与邮编相同
if($('zip') && $('zip').value.toLowerCase() == lv) return 'equalZip';
//与电话号码相同
if($('phoneNumber') && $('phoneNumber').value.toLowerCase() == lv) return 'equalPhone';
//与传真相同
if($('faxNumber') && $('faxNumber').value.toLowerCase() == lv) return 'equalFax';
//与password相同
if(lv == 'password') return 'isPassword';
}
},
confirmPassword:{
required:true,
tipId:'confirmPasswordTip',
txt:{
normal:'请再输入一遍您上面填写的密码。',
notValid:'您两次输入的密码不一致，请重新填写。'
},
cls:{
normal:'normal',
notValid:'error'
},
tracelog:{
change:'reg_market_chg3',
isNull:'reg_market_null3',
notValid:'reg_market_ecp1',
success:'reg_market_s3'
},
check:function(v,lv,uv){v = v.replace(/^\s+|\s+$/,'');
if(v != $('password').value) return 'notValid';
}
},
firstName:{
required:true,
ajaxCheck:true,
tipId:'firstNameTip',
txt:{
normal:'请填写您的姓名',
denyWords:'您的姓名含有非法词，请重新填写。',
specialWord:'您的姓名含有特殊字符（@、#、$、%等），请重新填写。'
},
cls:{
normal:'normal',
notValid:'error',
exist:'error'
},
tracelog:{
change:'reg_market_chg4',
denyWords:'reg_market_en2',
specialWord:'reg_market_en1',
isNull:'reg_market_null4',
total:'reg_market_e_firstname',
success:'reg_market_s4'
},
check:function(v,lv,uv){
if(v.indexOf('@') > -1 || v.indexOf('#') > -1 || v.indexOf('$') > -1 || v.indexOf('%') > -1) return 'specialWord';
checkDenyWord(v,'firstNameDenyID', formCfg.firstName, $('firstName'));
return 'denywordcheck';//通知主判断程序不用接着操作,因为已经在异步进行校验了.
}
},
jobTitle:{
required:true,
ajaxCheck:true,
tipId:'jobTitleTip',
txt:{
normal:'请填写您的职位。',
denyWords:'您的职位中含有非法词，请重新填写。',
specialWord:'您的姓名含有特殊字符（@、#、$、%等），请重新填写。'
},
cls:{
normal:'normal',
notValid:'error',
exist:'error'
},
tracelog:{
change:'reg_market_chg11',
denyWords:'reg_market_jt1',
specialWord:'reg_market_jt2',
isNull:'reg_market_null11',
total:'reg_market_jt',
success:'reg_market_s11'
},
check:function(v,lv,uv){
if(v.indexOf('@') > -1 || v.indexOf('#') > -1 || v.indexOf('$') > -1 || v.indexOf('%') > -1) return 'specialWord';
checkDenyWord(v,'jobTitleDenyID', formCfg.jobTitle,$('jobTitle'));
return 'denywordcheck';
}
},
company:{
required:true,
ajaxCheck:true,
tipId:'companyTip',
txt:{
normal:'注册企业请填写工商局注册的全称，无商号的个体经营者请填写执照上的名称，并标注个体经营。如：张三（个体经营）',
denyWords:'您的公司名含非法词，请重新填写。',
specialWords:'您的公司名含有特殊字符（@、#、$、%等），请重新填写。',
allNumbers:'建议您使用中文填写公司名称。'
},
cls:{
normal:'normal',
notValid:'error',
exist:'error'
},
tracelog:{
change:'reg_market_chg5',
isNull:'reg_market_null5',
allNumbers:'reg_market_ec3',
denyWords:'reg_market_ec2',
specialWords:'reg_market_ec1',
total:'reg_market_e_company',
success:'reg_market_s5'
},
check:function(v,lv,uv){
if(/^\d+$/.test(v)) return 'allNumbers';
if(v.indexOf('@') > -1 || v.indexOf('#') > -1 || v.indexOf('$') > -1 || v.indexOf('%') > -1) return 'specialWords';
checkDenyWord(v,'companyNameDenyID', formCfg.company,$('company'));
return 'denywordcheck';
}
},
emailName:{
required:true,
tipId:'emailTip',
txt:{
normal:emailTip,
notValid:'您输入的电子邮箱格式不正确，请重新填写。如：abc@yahoo.cn',
exist:'您输入的邮箱已经被使用，请重新填写其他邮箱。<br />您想使用这个邮箱并启用新会员登录名，<a href="{#}" target="_blank">请点此进入</a>。'
},
cls:{
normal:'normal',
notValid:'error'
},
tracelog:{
change:'reg_market_chg6',
isNull:'reg_market_null6',
notValid:'reg_market_ee1',
success:'reg_market_s6'
},
check:function(v,lv,uv){
if(!(/^[\w\-]+(\.[\w\-]*)*$/.test(v))) return 'notValid';
}
},
emailType:{
required:true,
tipId:'emailTip',
txt:{
normal:emailTip,
notValid:'您输入的电子邮箱格式不正确，请重新填写。如：abc@yahoo.cn',
exist:'您输入的邮箱已经被使用，请重新填写其他邮箱。<br />您想使用这个邮箱并启用新会员登录名，<a href="{#}" target="_blank">请点此进入</a>。'
},
cls:{
normal:'normal',
notValid:'error'
},
tracelog:{
change:'reg_market_chg9',
isNull:'reg_market_null7',
notValid:'reg_market_ee3',
total:'reg_market_e_email',
exist:'reg_market_ee2',
success:'reg_market_s7'
},
check:function(v,lv,uv){
if(!(/^[\w\-]+([\.][\w\-]+)+$/.test(v))) return 'notValid';
}
},
phoneCountry:{
required:true,
tipId:'phoneTip',
txt:{
normal:'请填写国家号码。',
notValid:'区号和国家号码只能使用数字。'
},
cls:{
normal:'normal',
notValid:'error'
},
tracelog:{
notValid:''
},
check:function(v,lv,uv){
if(!(/^\d+$/.test(v))) return 'notValid';
}
},
phoneArea:{
required:true,
tipId:'phoneTip',
txt:{
normal:'请填写区号。',
notValid:'区号和国家号码只能使用数字。'
},
cls:{
normal:'normal',
notValid:'error'
},
tracelog:{
change:'',
isNull:'',
notValid:''
},
check:function(v,lv,uv){
if(!(/^\d+$/.test(v))) return 'notValid';
}
},
phoneNumber:{
required:true,
tipId:'phoneTip',
txt:{
normal:'真实的电话号码可以提高您的信誉。多个号码，请用“/”分隔；分机号码，请用“-”分隔。',
notValid:'请您输入数字，“/”分隔多个电话号码，“-”分隔分机号码。',
lessLength:'您输入的电话号码少于7位，请重新填写。',
sameNumber:'请您不要输入相同的数字。'
},
cls:{
normal:'normal',
notValid:'error',
exist:'error'
},
tracelog:{
change:'reg_market_chg7',
isNull:'reg_market_null8',
notValid:'reg_market_epn1',
lessLength:'reg_market_epn2',
sameNumber:'reg_market_epn3',
total:'reg_market_e_phonenumber',
success:'reg_market_s8'
},
check:function(v,lv,uv){
if(!(/^[\d\/\-]+$/.test(v)) || !(/\d/.test(v))) return 'notValid';
if(v.length < 7) return 'lessLength';
if(/^(\d)\1+$/.test(v)) return 'sameNumber';
}
},
faxCountry:{
required:false,
tipId:'faxTip',
txt:{
normal:'请填写国家号码。',
notValid:'区号和国家号码只能使用数字。'
},
cls:{
normal:'normal',
notValid:'error'
},
tracelog:{
notValid:''
},
check:function(v,lv,uv){
if(!(/^\d+$/.test(v))) return 'notValid';
}
},
faxArea:{
required:false,
tipId:'faxTip',
txt:{
normal:'请填写区号。',
notValid:'区号和国家号码只能使用数字。'
},
cls:{
normal:'normal',
notValid:'error'
},
tracelog:{
change:'',
isNull:'',
notValid:''
},
check:function(v,lv,uv){
if(!(/^\d+$/.test(v))) return 'notValid';
}
},
faxNumber:{
required:false,
tipId:'faxTip',
txt:{
normal:'请填写传真号码。多个号码请用"/"隔开，分级号码请用"-"隔开。',
notValid:'传真号码只能使用数字或"/","_"。',
lessLength:'传真号码不能少于7位，请重新填写。',
sameNumber:'请不要使用重复的数字作为传真号码。'
},
cls:{
normal:'normal',
notValid:'error',
exist:'error'
},
tracelog:{
change:'reg_market_chg12',
isNull:'reg_market_null12',
notValid:'reg_market_fax1',
lessLength:'reg_market_fax2',
sameNumber:'reg_market_fax3',
total:'reg_market_fax',
success:'reg_market_s12'
},
check:function(v,lv,uv){
if(!(/^[\d\/\-]+$/.test(v)) || !(/\d/.test(v))) return 'notValid';
if(v.length < 7) return 'lessLength';
if(/^(\d)\1+$/.test(v)) return 'sameNumber';
}
},
mobile:{
required:false,
tipId:'mobileTip',
txt:{
normal:'方便客户联系您！请勿填写小灵通号码。<br />阿里巴巴不绑定任何收费服务。',
notNumbers:'手机号码只能是数字，请重新填写。'
},
cls:{
normal:'normal',
notNumbers:'error'
},
tracelog:{
change:'reg_market_chg8',
isNull:'reg_market_null9',
notNumbers:'reg_market_em1',
success:'reg_market_s9'
},
check:function(v,lv,uv){
if(!(/^\d+$/.test(v))) return 'notNumbers';
}
},
address:{
tipId:'addressTip',
required:true,
txt:{
normal:'请详细填写贵公司经营地址。如：红岭中路南国大厦1栋8层。'
},
cls:{
normal:'normal',
notValid:'error'
},
tracelog:{
change:'reg_market_chg8',
isNull:'reg_market_null9',
notNumbers:'reg_market_em1',
success:'reg_market_s9'
},
check:function(){
return false;
}
},
salekeyword:{
tipId:'keywordTipSale',
required:false,
ajaxCheck:true,
txt:{
normal:'请至少填写一项贵公司主营产品（或服务）的关键字，如：服装，布料，拉链。多个用逗号隔开。',
denyWords:'您的输入含有非法词，请重新填写。'
},
cls:{
normal:'normal',
notValid:'error'
},
tracelog:{
change:'reg_market_chg14',
isNull:'reg_market_null14',
success:'reg_market_s14'
},
check:function(v,lv,uv){
checkDenyWord(v,'saleKeywordsDenyID', formCfg.salekeyword,$('salekeyword'));
return 'denywordcheck';
}
},
buykeyword:{
tipId:'keywordTipBuy',
required:false,
ajaxCheck:true,
txt:{
normal:'请至少填写一项贵公司主营产品（或服务）的关键字，如：服装，布料，拉链。多个用逗号隔开。',
denyWords:'您的输入含有非法词，请重新填写。'
},
cls:{
normal:'normal',
notValid:'error'
},
tracelog:{
change:'reg_market_chg15',
isNull:'reg_market_null15',
success:'reg_market_s15'
},
check:function(v,lv,uv){
checkDenyWord(v,'buyKeywordsDenyID', formCfg.buykeyword,$('buykeyword'));
return 'denywordcheck';
}
},
valcode:{
required:true,
tipId:'valcodeTip',
txt:{
normal:'请填写四位验证码。'+chgCodeTxt,
notValid:'请填写正确的四位验证码。'+chgCodeTxt
},
cls:{
normal:'normal',
notValid:'error'
},
tracelog:{
change:'reg_market_chg10',
isNull:'reg_market_null10',
notValid:'reg_market_ev1',
success:'reg_market_s10'
},
check:function(v,lv,uv){
$('valcodeTip').innerHTML = "<a id=\"regValA\" class=\"chgImg hideFocus\" onclick=\"AliRegMarket.changeValCode('regValImg','regValA');return false\" hidefocus=\"true\" href=\"#\">图片验证码看不清，换一张。</a>"
if(!(/^\d{4}$/.test(v))) return 'notValid';
}
}
}
}()
