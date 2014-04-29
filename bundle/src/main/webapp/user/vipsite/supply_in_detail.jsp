<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<div class="bodyCont mainTextColor">
	<div class="wangpuBodyContTitle">
    	<span class="b titleName wangpuTopTitle">[<s:property value="supply.type"/>]${supply.title}</span>&nbsp;
    </div>
    <div style="margin: 8px auto; min-height: 320px; width: 700px;">
        <div class="sliderWrap">                  
						
		  <table cellspacing="0" cellpadding="0" border="0" width="250">
		  <tbody><tr><td class="picgrayborder">
		    <a style="cursor: pointer;" onclick="">
		      <img  class="picgrayborder" alt="" onload="" src="<s:url value=""/>">
		    </a>
		  </td></tr>
		</tbody></table>
    
		<table cellspacing="0" cellpadding="3" border="0" width="100%">
		  <tbody><tr>
			<td align="center" class="pd-t-7">
				<div class="img_title">
					[<s:property value="supply.type"/>]${supply.title}
				</div>
		  </td></tr>
		</tbody></table>


     </div>
	
  	<ul id="detailCtn">

		<li class="row4 row7">
           <p>${lan['product.address']} <span class="wordwarp">${enterprise.address}</span></p>
										
			<p>${lan['product.unpublishTime']}<span><s:date name="supply.endTime" format="%{lan['product.format']}"/></span></p>
        </li>
      
        <li class="nopaybt">
			<div class="nopaywarp">
				<ul class="nopaybox">
					<li class="nopaytip"></li>
					<li class="nopayww">
						<a target="_blank" class="nopaycontact" href="">${lan['product.contact']}</a>
					</li>
				</ul>
			</div>
		</li>
      </ul>
   </div>
</div>