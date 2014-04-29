<%@ page import="java.net.InetAddress" %> 
<%@ page import="java.net.UnknownHostException" %>
<%@ page import="com.abbcc.module.user.Visits" %>
<%@ page import="java.util.Date" %>
<% 
InetAddress localhost = null;
try {
	localhost = InetAddress.getLocalHost();
} catch (UnknownHostException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
String ip = localhost.getHostAddress();
Visits c = new Visits();
c.jlcount(new Date(),ip);
%> 
