<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link type="text/css" href="<s:url value="/user/css/site/leave.css"/>" rel="stylesheet" />
<script type="text/javascript" src="<s:url value="/user/js/site/leave.js"/>"></script>
<div id="floatwindow" >
	<div style="clear: both;">
	</div>
	<div id="ecustomerboxFF" >
		<div class="relativediv">
			<div id="ecustomerbuttonFF" class="ecustomerbuttonminFF">
			</div>
			<div id="ecustomerbdFF" class="ecustomerbdFF">
				<div class="relativediv">
					<div id="ecustomerautodivFF">
						<s:form namespace="/site_message" action="leave">
							<div id="ECScontent" class="ECScontent">
								<div class="EMiddle">
									<ul>
										<li class="dis">
											${lan['leave.yourLeave']}
										</li>
										<li>
											<textarea id="leaveWord" name="content" class="{required:true,leaveWord:true,messages:{required:'${lan['leave.requiredLeave']}',leaveWord:'${lan['leave.requiredLeave']}'}}">${lan['leave.sendPrompt']}</textarea>
										</li>
										<li class="tip"></li>
										<li class="name">
											<span>${lan['leave.name']}</span><span class="orange">*</span><input type="text" id="sendName" name="name" class="inputName {required:true,messages:{required:'${lan['leave.requiredName']}'}}"/>
										</li>
										<li class="tip"></li>
										<li class="name">
											<span>${lan['leave.email']}</span><span class="orange">*</span><input type="text" id="emailAdd" name="email" class="inputName {required:true,email:true,messages:{required:'${lan['leave.requiredEmail']}',email:'${lan['leave.matchEmail']}'}}"/>
										</li>
										<li class="tip"></li>
										<li class="name">      
											<span>${lan['leave.mobile']} </span><input type="text" id="telPhone" name="phone" class="inputPhone" />
										</li>
										<li class="tip"></li>
										<%-- <li class="name">
											<span>${lan['valiCode']}</span><span class="orange">*</span><input type="text" id="confir" name="valiCode" class="inputConfir {required:true,messages:{required:'${lan['requiredValiCode']}'}}" onkeyup="$('#authcodeLi').empty()"/>
											<img height="18" align="top" width="70" id="checkPic" onclick="refreshCode('checkPic')" src="<s:url value="/comm/veriImg"/>"/>
										</li>--%>
										<li id="authcodeLi" class="tip"></li>
										<li class="submit">
											<a id="msgBtn" href="javascript:" onclick="$('#leave').submit()">${lan['leave.sendLeave']}</a>
										</li>
									</ul>
								</div>
							</div>
							<s:hidden name="recvUser" value="%{userId}"/>
					 		<s:hidden name="recvEnt" value="%{enterprise.enterpriseId}"/>
						</s:form>    
					</div>
				</div>
			</div>
		</div>
	</div>
</div>