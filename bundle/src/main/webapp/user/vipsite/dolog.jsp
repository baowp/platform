<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link type="text/css" href="<s:url value="/user/css/site/log.css"/>" rel="stylesheet" />
<script type="text/javascript" src="<s:url value="/user/js/site/log.js"/>"></script>
<div id=dolog>
	<div id=registerDiv class="dolog" style="display: none;">
		<s:form action="register" namespace="/subuser" cssClass="validateForm">
			<div class="area">
				<div class="list">	
					<div class="item clearfix registerId">						
						<div class="nameTxt">${lan['dolog.name']}<font color="red"> * </font></div>
						<div class="con">
							<input maxlength="20" id="registerId" name="username" class="input noime {required:true,messages:{required:'${lan['dolog.requiredName']}'}} " autocomplete="off" disableautocomplete type="text">
							<div id="suggestLoginid" style="display: none;">
							</div>
						</div>
						<div class="tip">
							<div id="loginIdTip" class="no"></div>
						</div>					
					</div>
					<div class="item clearfix password">
						<div class="nameTxt">${lan['dolog.password']}<font color="red"> * </font></div>
						<div class="con"><input type="password" class="input {required:true,messages:{required:'${lan['dolog.requiredPassword']}'}}" name="password" maxlength="20" id="registerPassword"></div>
						<div class="tip">
							<div class="no" id="passwordTip"></div>
						</div>
					</div>
					<div class="item clearfix confirm_password">
						<div class="nameTxt">${lan['dolog.confirmPassword']}<font color="red"> * </font></div>
						<div class="con"><input type="password" class="input {required:true,equalTo:'#registerPassword',messages:{required:'${lan['dolog.requiredCPassword']}',equalTo:'${lan['dolog.matchPassword']}'}}" name="confirmPassword" maxlength="20" id="confirmPassword"></div>
						<div class="tip"><div id="confirmPasswordTip"></div></div>
					</div>
					<div class="item clearfix email">
						<div class="nameTxt">${lan['dolog.email']}<font color="red"> * </font></div>
						<div class="con">
							<input id="emailName" name="email" maxlength="50" class="emailName input noime  {required:true,email:true,messages:{required:'${lan['dolog.requiredEmail']}',email:'${lan['dolog.matchEmail']}'}}">
						</div>
						<div class="tip">
							<div class="" id="emailTip"></div>
						</div>	
					</div>
					
					<div class="item">
						<div class="nameTxt"><font color="red">  </font></div>
						<div class="con" style="text-align: right;">
							<s:submit value="%{lan['submit']}"/>
							<s:reset value="%{lan['reset']}"/>
						</div>
					</div>
				</div>
			</div>
			<s:hidden name="enterpriseId" value="%{enterprise.enterpriseId}"/>
		</s:form>
	</div>
	
	<div id=loginDiv class="dolog" style="display: none;">
		<s:form action="login" namespace="/subuser" cssClass="validateForm">
			<div class="area">
				<div class="list">	
					<div class="item" id=logintip style="display: none;height:22px;color:#C53032;">
						<div class="nameTxt">&nbsp;</div>
						<div class="con">
							<div id=logFlag>${lan['dolog.loginError']}</div>
						</div>
					</div>
					<div class="item clearfix loginid">						
						<div class="nameTxt">${lan['dolog.username']}</div>
						<div class="con">
							<input maxlength="20" id="loginid" name="username" class="input noime {required:true,messages:{required:'${lan['dolog.requiredUsername']}'}} " type="text">
							<div id="suggestLoginid" style="display: none;">
							</div>
						</div>
						<div class="tip">
							<div id="loginIdTip" class="no"></div>
						</div>					
					</div>
					<div class="item clearfix password">
						<div class="nameTxt">${lan['dolog.password']}</div>
						<div class="con"><input type="password" class="input {required:true,messages:{required:'${lan['dolog.requiredPassword']}'}}" name="password" maxlength="20" id="password"></div>
						<div class="tip">
							<div class="no" id="passwordTip"></div>
						</div>
					</div>
					<div class="item">
						<div class="nameTxt"><font color="red">  </font></div>
						<div class="con" style="text-align: right;">
							<s:submit value="%{lan['login']}"/>
							<s:reset value="%{lan['reset']}"/>
						</div>
					</div>
				</div>
			</div>
			<s:hidden name="enterpriseId" value="%{enterprise.enterpriseId}"/>
		</s:form>
	</div>
</div>