<%@ page language="java" import="java.util.*,com.abbcc.models.AbcJob" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
	<script type="text/javascript" src="<s:url value='/js/datePicker/WdatePicker.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
<link rel="stylesheet" type="text/css" href="<s:url value="/user/abbcc/css/common.css"/>">
<script type="text/javascript" src="/js/common.js"></script>
<s:include value="/user/css/table.jsp"></s:include>
	<script type="text/javascript">
	function checkForm(){
		var keyArray=new Array()
		keyArray[0]=$("#duty").val()
		keyArray[1]=$("#title").val()
		keyArray[2]=$("#content").val()
		checkKeys(keyArray);
		if(rkey!=null){
			alert("您输入的("+rkey+")是非法关键字!");
			return false;
		}
		return true;
			
	}
	</script>
<title>Insert title here</title>
</head>
<body>
	  <form action="<s:url value='updateIssuanceRecruit'/>" method="POST" onsubmit="return checkForm()">
   <center><h1>修改招聘信息</h1></center>
   <hr>
   <input type="hidden" name="jobId" value="<s:property value='#request.jobRequest.jobId' />"/>
   <table>
   		<tr><td>标题:</td><td><input type="text" name="title" value="<s:property value='#request.jobRequest.title' />"/></td><td><span class="errorSpan">${errors.title[0]}</span></td></tr>
   		<tr><td>职务:</td><td><input type="text" name="duty" value="<s:property value='#request.jobRequest.duty' />"/></td><td><span class="errorSpan">${errors.duty[0]}</span></td></tr>
   		<tr><td>人数:</td><td><select name="sum">
   		<%
   			AbcJob job = (AbcJob)request.getAttribute("jobRequest");
   			int jobSum = Integer.parseInt(job.getSum());
   			for(int sum =1;sum<1000;sum++){
   				if(jobSum==sum){
   					%>
   					
   			<option value="<%=sum %>" selected><%=sum %></option>
   					
   					<%
   				}else{
   				
   			%>
   			<option value="<%=sum %>"><%=sum %></option>
   			<%
   				}}
   		 %>
   		</select></td><td></td></tr>
   		<tr><td>要求:</td><td><textarea cols="50" rows="5" name="content"><s:property value='#request.jobRequest.content' /></textarea></td><td><span class="errorSpan">${errors.content[0]}</span></td></tr>
   		<tr><td>有效期:</td><td>截止至<input  name="sDate2"  class="Wdate" type="text" onfocus="WdatePicker()"  value="<s:property value='#request.jobRequest.endTime' />"/></td><td><span class="errorSpan">${errors.sDate2[0]}</span></td></tr>
   		<tr><td><s:submit value="提交"  title="提交" onclick="javascript:return update()"/></td><td><input type="button" value="返回" title="返回" onclick="javascript:history.go(-1);"/></td></tr>
   </table>
  </form>

</body>
</html>