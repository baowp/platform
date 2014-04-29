<!--
//////////////////////////////////////////////////////////////////////////////

var width = "200";
var offsetx = 2;
var offsety = 18;

var x = 0;
var y = 0;
var snow = 0;
var sw = 0;
var cnt = 0;

var dStyle;
document.onmousemove = mEvn;

//显示详细日期资料
function onmOvr(v) {
var s,festival;
var sObj=document.getElementById('SD'+ v);
var d=sObj.innerHTML-1;
var jymenu,jystring ;

//sYear,sMonth,sDay,week,
//lYear,lMonth,lDay,isLeap,
//cYear,cMonth,cDay

if(sObj.innerHTML!='') {

sObj.style.cursor = 'pointer';

if(cld[d].solarTerms == '' && cld[d].solarFestival == '' && cld[d].lunarFestival == '')
festival = '';
else
festival = '<TABLE WIDTH=100% BORDER=0 CELLPADDING=2 CELLSPACING=0 BGCOLOR="#CCFFCC" STYLE="font-size:9pt;"><TR><TD>'+
'<FONT COLOR="#ff0000" STYLE="font-size:9pt;">'+cld[d].solarTerms + ' ' + cld[d].solarFestival + ' ' + cld[d].lunarFestival+'</FONT></TD>'+
'</TR></TABLE>';

if(cld[d].sgz5!=0){jymenu=cld[d].sgz5;}else{jymenu=jcr(cld[d].sgz3);} 

jystring ='<TABLE WIDTH=100% BORDER=0 CELLPADDING=2 CELLSPACING=0><TR><TD>'+
'<FONT COLOR="#ff0000" STYLE="font-size:9pt;"><tr><td BORDER=0 CELLPADDING=2 CELLSPACING=0 bgcolor="#CCFFCC"><font color="black" STYLE="font-size:9pt;">值日:' + cld[d].sgz6 + '</font></td></tr><tr><td BORDER=0 CELLPADDING=2 CELLSPACING=0 BGCOLOR="#CCFFCC"><font color="black" STYLE="font-size:9pt;">' + jymenu + '</font></td></tr>' +'</FONT></TD>'+
'</TR></TABLE>';

s= '<TABLE CELLPADDING="2" CELLSPACING=0 style="width:200px;border:0;background:#000066; "><TR><TD>' +
'<TABLE WIDTH=100% BORDER=0 CELLPADDING=0 CELLSPACING=0><TR><TD ALIGN="right"><FONT COLOR="#ffffff" STYLE="font-size:9pt;">'+
cld[d].sYear+' 年 '+cld[d].sMonth+' 月 '+cld[d].sDay+' 日<br>星期'+cld[d].week+'<br>'+
'<font color="violet">农历'+(cld[d].isLeap?'闰 ':' ')+cld[d].lMonth+' 月 '+cld[d].lDay+' 日</font><br>'+
'<font color="yellow">'+cld[d].cYear+'年 '+cld[d].cMonth+'月 '+cld[d].cDay + '日</font>'+
'</FONT></TD></TR></TABLE>'+ festival +
jystring +
'</TD></TR></TABLE>';



document.getElementById("detail").innerHTML = s;

if (snow == 0) {
dStyle.left = x+offsetx-(width/2)+"px";
dStyle.top = y+offsety+"px" ;
dStyle.visibility = "visible";
snow = 1;
}
}
}

//清除详细日期资料
function onmOut() {
if ( cnt >= 1 ) { sw = 0; }
if ( sw == 0 ) { snow = 0; dStyle.visibility = "hidden";}
else cnt++;
}

//取得位置
function mEvn() {
x=getEvent().x?getEvent().x:getEvent().pageX;
y=getEvent().y?getEvent().y:getEvent().pageY;

if (getScrollXY().x)
{x=x+getScrollXY().x; y=y+getScrollXY().y;}
if (snow){
dStyle.left = x+offsetx-(width/2)+"px";
dStyle.top = y+offsety+"px";
}
}

function getEvent() //同时兼容ie和ff的写法
{  
        if(document.all)   return window.event; 
        func=getEvent.caller;        
        while(func!=null){  
            var arg0=func.arguments[0];
            if(arg0)
            {
              if((arg0.constructor==Event || arg0.constructor ==MouseEvent) || (typeof(arg0)=="object" && arg0.preventDefault && arg0.stopPropagation))
              {  
              return arg0;
              }
            }
            func=func.caller;
        }
        return null;
} 


function getScrollXY(){
var x1,y1;
if(document.body.scrollTop){
  x1=document.body.scrollLeft;
  y1=document.body.scrollTop;
}
else{
  x1=document.documentElement.scrollLeft;
  y1=document.documentElement.scrollTop;
}
return {"x":x1,"y":y1};
} 


///////////////////////////////////////////////////////////////////////////
//-->