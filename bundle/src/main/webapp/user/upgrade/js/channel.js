function show_menu(obj_s,obj){
var  s_id = document.getElementById(obj_s);
var  sc_id = document.getElementById(obj);
s_id.style.display = "";
sc_id.className = "ahv";
}
function hide_menu(obj_h,obj){
var  h_id = document.getElementById(obj_h);
var  hc_id = document.getElementById(obj);
h_id.style.display = "none";
hc_id.className = "alk";
}
//������
function focusit(inputobj){
if(inputobj.value.indexOf('��ʼȫ������')!=-1)inputobj.value='';
inputobj.style.color="#000000";
}
function blurit(inputobj){
if(trim(inputobj.value)=='')inputobj.value=inputobj.title;;
inputobj.style.color="#000000";
}
function checkform(frmObj){
var v = trim(frmObj.keywords.value);
if(v.length > 100){
alert("������Ĺؼ��ֹ�����");
return false;
}
if(v == ""  || v.substring(0,3) =="������") {
alert("������ؼ��֣�");
return false;
}
}
/*
* ����˵����ȥ��ͷβ�ո�
* ������	�ַ���
* ����ֵ��	��
* ʱ�䣺2005-5-12
*/
function trim(inputString) {
return inputString.replace(/^ +/,"").replace(/ +$/,"");
}
function swapImage(obj,picUrl) {
if(obj){
obj.src = picUrl;
}
}
