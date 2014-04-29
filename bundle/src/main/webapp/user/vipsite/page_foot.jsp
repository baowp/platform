<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="#request.setting.footer!='none'">
	<div class="mod-footer glitzColor mainTextColor">
		<p>&nbsp;</p>
		<p>
			<s:iterator value="layout.navmoduleList" status="st">
				<a href="<s:property value="@com.abbcc.util.constant.layout.Piece@valueOf(module.moduleId).action"/>">
					<s:property value="layout.jsonContent['title'][module.moduleId]||
						#request['moduleMap'][module.moduleId]"/>
				</a> <s:if test="#st.index<layout.navmoduleList.size-1">|</s:if>
			</s:iterator>
		</p>
		<p>
			${enterprise.name }
			${lan['address']}${address }
		</p>
		<p>
			<a href="http://www.miibeian.gov.cn/" target="_blank">${enterprise.icp }</a>
			&nbsp;&nbsp;
			${lan['support']}
			<span class='dongfang'></span><a target="_blank" href="http://51archetype.com" class="draft_no_link">${lan['domain.home'] }</a>
			<span class="analysis_tool"><s:property value="layout.jsonFooter['cnzz']" escape="false"/></span> 
			<span class="analysis_tool"><s:property value="layout.jsonFooter['51la']" escape="false"/></span>
		</p>
	</div>
</s:if>