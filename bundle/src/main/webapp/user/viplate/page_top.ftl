<div>
	<#include "page_topbar.ftl"/>
	<#include "dolog.ftl"/>
</div>
<div>
	<#include "page_message.ftl">
</div>
<#--
<div>
	<div class="clr"></div>
	<table cellspacing="0" cellpadding="0" border="0" width="100%">
		  <tbody><tr>
		    <td style="background-image: url(images/vipsite/title_103002.gif); background-repeat: repeat-x;">
			<div style="float: left; margin-top: 5px; margin-left: 10px ! important;">	
				<b style="float: left; margin-top: 3px;">
		            <a class="draft_no_link black b" href="javascript:addFav()">${lan['addFavourite']}</a> |
		      		<a class="draft_no_link black b" href="javascript:setHomepage()">${lan['setHomepage']}</a> |
		     		<a class="black" href="${(layout.jsonContent['visit']['href'])!''}">${(layout.jsonContent['visit']['name'])!''}</a>
		     	</b>
			 </div>
			     </td>
		    <td width="47"><div style="margin: 0pt; padding: 0pt; height: 42px;"><img height="42" border="0" width="47" src="images/vipsite/title_103001.gif"/></div></td>
		    <td align="right" width="425" style="background-image: url(images/vipsite/title_103003.gif); padding-top: 12px; background-repeat: repeat-x;">
				<table>
		    		<tbody><tr>
		    			<td width="60">
		    			</td>
		            		</tr>
		    	</tbody></table>
		      </td>
		  </tr>
		</tbody>
	</table>
</div>
-->