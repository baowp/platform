<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.net.URL"%>
<%@page import="java.net.URLConnection"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.net.MalformedURLException"%>
<%@page import="java.io.IOException"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>My JSP 'weather.jsp' starting page</title>
</head>

<body >  
<%  
        StringBuffer document = new StringBuffer();  
        String today = "";  
    try {  
        Date nowDate=new Date();  
          
        String []date={"日","一","二","三","四","五","六"};  
        int day=nowDate.getDay();  
        String dayFinal="";  

        for(int i=0;i<date.length;i++){  
            if(i==day){  
                dayFinal=date[i];  
            }  
        }  
        today=nowDate.getYear()+1900+"年"+(nowDate.getMonth()+1)+"月"+nowDate.getDate()+"日"+" "+"星期"+dayFinal;  

        URL url = new URL("http://www.raychou.com/weather/rss.php?id=59293");  
        URLConnection conn = url.openConnection();   

        InputStreamReader in = new InputStreamReader(conn.getInputStream(),"utf-8");  
        BufferedReader reader = new BufferedReader(in);   

        String line = null;   
        conn.setConnectTimeout(10000);  
        String line1 = null;  

        while ((line = reader.readLine()) != null)   

        document.append(line + "\n");   
        reader.close();   
        in.close();in = null;//释放流  

        String abc=new String(document.toString().getBytes("utf-8"),"utf-8");  

        String finalValue="";  
          
        int start=abc.indexOf("<description>");  
        int end=abc.indexOf("</description>");  
          
        if(start!=-1 && end!=-1 ){  
            finalValue="河源:"+abc.substring(start+"</description>".length()+2,end);  
        }  

%>  
    <table>  
        <tr>  
            <td width="85%">  
            <%=finalValue%>
            </td>  
            <td width="15%" nowrap><%=today%>             
            </td>  
        </tr>  
    </table>  
<%  
    }catch (MalformedURLException e) {  
        System.out.println(e);  
    }catch (IOException e){  
        System.out.println(e);  
        out.println("<table><tr><td width=\"85%\"></td><td width=\"15%\" nowrap>"+today+"           </td></tr></table>");  
    }  
%>  

</body>
</html>