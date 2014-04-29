<!--
            var gNum
            for(i=0;i<6;i++) {
               document.write('<tr align=center>')
               for(j=0;j<7;j++) {
                  gNum = i*7+j
                  
                  document.write('<td height=57 width=60 id="GD' + gNum +'" onClick="mOvr(' + gNum +');Main(' + gNum +');MoonRise(' + gNum +');" onMouseOver="onmOvr(' + gNum +')" onMouseOut="onmOut()"><B><font style="font-family:Arial;font-size:17pt;cursor: hand" id="SD' + gNum +'" ')
                  if(j == 0) document.write(' color=red')
                  if(j == 6)
                     if(i%2==1) document.write(' color=red')
                        else document.write(' color=red')
                  document.write('> </font></B><br><font style="cursor: hand" id="LD' + gNum + '"  style="font-size:9pt"> </font></td>')
               }
               document.write('</tr>')
            }
//-->