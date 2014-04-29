<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
		<div id="company_name" class="moveChild topbaner headCont" >
			<s:hidden name="piece" value="%{@com.abbcc.util.constant.layout.Piece@HEAD_LOGO}"/>
			<div class="pointer_">
				<div id="log">
					<s:if test="layout.jsonSign['log']!=null">
						<img <s:iterator value="#request['jsonSign']['log']">${key}="${value}" </s:iterator> />
					</s:if>
				</div>
				<div id="companyname">
				<ul>
					<li class="china">
					<span id="chinaname" class="chinaname">${enterprise.name}</span>
					<span class="enname" id="enname"></span>
					</li>
				</ul>
				</div> 
			</div>
		</div>