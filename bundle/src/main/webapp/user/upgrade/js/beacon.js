if(!dmtrack){var dmtrack={}}dmtrack.MSG_Img;dmtrack.SendMessage=function(e,b,c,a){function g(v){var p="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";var r,t,o;var u,s,q;o=v.length;t=0;r="";while(t<o){u=v.charCodeAt(t++)&255;if(t==o){r+=p.charAt(u>>2);r+=p.charAt((u&3)<<4);r+="==";break}s=v.charCodeAt(t++);if(t==o){r+=p.charAt(u>>2);r+=p.charAt(((u&3)<<4)|((s&240)>>4));r+=p.charAt((s&15)<<2);r+="=";break}q=v.charCodeAt(t++);r+=p.charAt(u>>2);r+=p.charAt(((u&3)<<4)|((s&240)>>4));r+=p.charAt(((s&15)<<2)|((q&192)>>6));r+=p.charAt(q&63)}return r}var j="";var l="";var m=new Date();var n=e.length;try{if(b){for(k in b){j+=k.toString()+"="+b[k].toString()+"&"}j=j.substring(0,j.length-1)}j=g(j);if(c){for(k in c){l+=k.toString()+"="+c[k].toString()+"&"}l=l.substring(0,l.length-1)}if(e.indexOf("?")==-1){if(!j&&l){e+="?"+l+"&ver=30&time="+m.getTime()}else{if(!l&&j){e+="?"+j+"&ver=30&time="+m.getTime()}else{if(l&&j){e+="?"+j+"&"+l+"&ver=30&time="+m.getTime()}}}}else{var i=e.split("?");if(!j&&l){if(!i[1]){e=""+i[0]+"?"+i[1]+l+"&ver=30&time="+m.getTime()}else{e=""+i[0]+"?"+i[1]+"&"+l+"&ver=30&time="+m.getTime()}}else{if(!l&&j){if(!i[1]){e=""+i[0]+"?"+j+i[1]+"&ver=30&time="+m.getTime()}else{e=""+i[0]+"?"+j+"&"+i[1]+"&ver=30&time="+m.getTime()}}else{if(l&&j){if(!i[1]){e=""+i[0]+"?"+j+i[1]+"&"+l+"&ver=30&time="+m.getTime()}else{e=""+i[0]+"?"+j+"&"+i[1]+"&"+l+"&ver=30&time="+m.getTime()}}}}}if(e.length==n){if(e.indexOf("?")==-1){e+="?ver=30&time="+m.getTime()}else{if(e.indexOf("?")==e.length-1){e+="ver=30&time="+m.getTime()}else{e+="&ver=30&time="+m.getTime()}}}if(a=="docwrite"){document.write("<img style='display:none' src = "+e+">")}else{if(a=="newimg"||!a){dmtrack.MSG_Img=new Image();dmtrack.MSG_Img.src=e}}}catch(h){var f="http://stat.china.alibaba.com/dw/error.html?exception="+encodeURIComponent(h.toString())+"&url="+encodeURIComponent(document.URL.toString());if(a=="docwrite"){document.write("<img style='display:none' src = "+f+">")}else{if(a=="newimg"||!a){dmtrack.MSG_Img=new Image();dmtrack.MSG_Img.src=f}}}};dmtrack.sk_getRand=function(){var a;try{a=dmtrack_pageid}catch(g){a=""}if(!a){a="001"+Math.round(Math.random()*10000000000)+""+Math.round(Math.random()*10000000000)}else{a=a.substr(0,20)}var j=new Date();var f=[a,j.getTime()].join("");if(f.length<42){var h=""+Math.round(Math.random()*10000000000)+Math.round(Math.random()*10000000000)+Math.round(Math.random()*10000000000);var b=0;h=h.split("");for(var c=f.length;c<42;c++){f=f+h[b];b++}}else{for(var c=f.length;c>42;c--){f=f.substring(0,c-1)}}return f};dmtrack.get_cookie=function(c){var b="(?:; )?"+c+"=([^;]*);?";var a=new RegExp(b);if(a.test(document.cookie)){var e=decodeURIComponent(RegExp["$1"]);if(e.Trim().length>0){return e}else{return"-"}}else{return"-"}};function sk_dmtracking(){if(dmtrack.isDmTracked){return}try{dmtrack_pageid=dmtrack.sk_getRand();var r=document.referrer;if(!r&&document.opener){r=document.opener.location}if(!r){r="-"}var m=2;var a="GET";var n=document.URL.indexOf("://");var b=document.URL.substr(n+2);var c=dmtrack.get_cookie("ali_apache_track");var o=(document.URL.substring(0,document.URL.indexOf("://")));var j=o+"://dmtracking.alibaba.com/b.jpg?";var l=o+"://dmtracking.alixueyuan.net/b.jpg?";try{if(!dmtrack_c){dmtrack_c="{-}"}}catch(p){dmtrack_c="{-}"}var h="aliBeacon_bcookie";var s=dmtrack.get_cookie(h);s=s.replace(/ali_resin_trace=/,"");if("{-}"==dmtrack_c){dmtrack_c="{"+s+"}"}else{dmtrack_c=dmtrack_c.split("}");if("-"==s){dmtrack_c[1]="}"}else{dmtrack_c[1]="|";dmtrack_c.push(s);dmtrack_c.push("}")}dmtrack_c=dmtrack_c.join("")}dmtrack.del_cookie(h,"/");var i=document.domain;if(-1!=i.indexOf("alixueyuan.net")){var q=4;dmtrack.SendMessage(j,{p:"{"+q+"}",u:"{"+b+"}",m:"{"+a+"}",s:"{200}",r:"{"+r+"}",a:"{"+c+"}",b:"{-}",c:dmtrack_c},{pageid:dmtrack_pageid})}else{dmtrack.SendMessage(j,{p:"{"+m+"}",u:"{"+b+"}",m:"{"+a+"}",s:"{200}",r:"{"+r+"}",a:"{"+c+"}",b:"{-}",c:dmtrack_c},{pageid:dmtrack_pageid})}}catch(g){var f="http://stat.china.alibaba.com/dw/error.html?exception="+encodeURIComponent(g.toString())+"&url="+encodeURIComponent(document.URL.toString());dmtrack.SendMessage(f)}dmtrack.isDmTracked=true}dmtrack.beacon_click=function(f,m){try{var n=m;if(n=="-"||!n){n=encodeURI(document.URL)}d=new Date();var j=2;var l=f.indexOf("://");var b=f.substr(l+2);var a="GET";var c=dmtrack.get_cookie("ali_apache_track");var i=(document.URL.substring(0,document.URL.indexOf("://")));var h=i+"://dmtracking.alibaba.com/c.jpg?";dmtrack.SendMessage(h,{p:"{"+j+"}",u:"{"+b+"}",m:"{"+a+"}",s:"{200}",r:"{"+n+"}",a:"{"+c+"}",b:"{-}",c:"{-}"})}catch(g){var e="http://stat.china.alibaba.com/dw/error.html?exception="+encodeURIComponent(g.toString())+"&url="+encodeURIComponent(document.URL.toString());dmtrack.SendMessage(e)}};dmtrack.tracelog=function(c){var b="http://stat.china.alibaba.com/tracelog/click.html";var a={tracelog:c};dmtrack.clickstat(b,a)};dmtrack.clickstat=function(b,e,j){try{try{if(!dmtrack_pageid){dmtrack_pageid=""}}catch(f){dmtrack_pageid=""}if(typeof(e)=="string"){if("?"==b.substring(b.length-1,b.length)){b=b.replace("?","")}if("?"==e.substring(0,1)){e=e.replace("?","")}var n=e.split("&");var a={};for(var g=0;g<n.length;g++){var h=n[g].split("=");var m=h[0];var l=h[1];a[m]=l}a.st_page_id=dmtrack_pageid;dmtrack.SendMessage(b,{},a)}else{if(typeof(e)=="object"){e.st_page_id=dmtrack_pageid;dmtrack.SendMessage(b,{},e)}}}catch(f){var c="http://stat.china.alibaba.com/dw/error.html?exception="+encodeURIComponent(f.toString())+"&url="+encodeURIComponent(document.URL.toString());dmtrack.SendMessage(c)}};dmtrack.flash_dmtracking=function(b,l){try{dmtrack_pageid=dmtrack.sk_getRand();var i=2;var a="GET";var c=dmtrack.get_cookie("ali_apache_track");var h="http://dmtracking.alibaba.com/b.jpg?";try{if(!dmtrack_c){dmtrack_c="{-}"}}catch(j){dmtrack_c="{-}"}dmtrack.SendMessage(h,{p:"{"+i+"}",u:"{"+b+"}",m:"{"+a+"}",s:"{200}",r:"{"+l+"}",a:"{"+c+"}",b:"{-}",c:dmtrack_c},{pageid:dmtrack_pageid,dmtrack_type:"xuanwangpu"})}catch(g){var f="http://stat.china.alibaba.com/dw/error.html?exception="+encodeURIComponent(g.toString())+"&url="+encodeURIComponent(document.URL.toString());dmtrack.SendMessage(f)}dmtrack.isDmTracked=true};dmtrack.feedback=function(c){var f="http://page.china.alibaba.com/shtml/static/forfeedbacklog.html";if(c.indexOf("?")>-1){f=f+c}else{f=f+"?"+c}var a="";try{a=document.cookie.match(/track_cookie[^;]*cosite=(\w+)/)[1]}catch(b){}if(a.length>0){f=f+"&fromsite="+a}dmtrack.beacon_click(f,"-");dmtrack.SendMessage(f,{},{});return true};dmtrack.feedbackTraceLog=function(b,a){var c="";dmtrack.feedback("?toid=tjtiandaqiehanshebei&fromid=notmember&sourcetype="+b+"&domainType="+c);return dmtrack.tracelog(a)};dmtrack.acookie=function(){function a(c){return Math.floor(Math.random()*c)+1}var b=escape(document.referrer);dmtrack.SendMessage("http://acookie.alibaba.com/1.gif",{},{cache:a(9999),pre:b})};String.prototype.Trim=function(){return this.replace(/(^\s*)|(\s*$)/g,"")};dmtrack.set_cookie=function(g,h,b,c,a,f){var e=g+"="+encodeURIComponent(h);if(b){e+="; expires="+b.toGMTString()}if(c){e+="; path="+c}if(a){e+="; domain="+a}if(f){e+="; secure"}document.cookie=e};dmtrack.del_cookie=function(b,a){dmtrack.set_cookie(b,"",new Date(0),a,".alibaba.com")};dmtrack.acookie();