<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.io.*"%>
<%@ page import="java.net.*"%>
<%!
    String proxy = "http://searchbox.mapbar.com/publish"; 
    void pipe(URLConnection uc, HttpServletResponse response) {
	    InputStream is = null;
	    ServletOutputStream out = null;
	    try {
	        response.setContentType(uc.getContentType());
	        is = uc.getInputStream();
	        out = response.getOutputStream();
	
			byte[] buf = new byte[4096];
			int bytesRead;
			while ((bytesRead = is.read(buf)) != -1) {
			    out.write(buf, 0, bytesRead);
			}
	    } catch (IOException e) {
	    	System.out.println(e.toString());
	    } finally {
	        if(is != null) try { is.close(); is = null; } catch(Exception e) {};
	        if(out != null) try { out.close(); out = null; } catch(Exception e) {};
	    }    	
    }
    String getTarget(HttpServletRequest request) {
      
      String url = "";
      String qry = request.getQueryString();
      if (qry.indexOf("api=keyword") >=0 ) {
        url = proxy+"/common/proxy.jsp?"+request.getQueryString();
			} else if (qry.indexOf("api=getCityByName") >=0 ) {
        url = proxy+"/common/proxy.jsp?"+request.getQueryString();
      } else if (qry.indexOf("api=template1000") >=0 ) {
      	url = proxy+"/template/template1000/?"+qry.substring(qry.indexOf("&")+1);
			} else if (qry.indexOf("api=poiUpdate") >=0 ) {
      	url = proxy+"/common/proxy.jsp?"+request.getQueryString();
      }
      
      return url;
    }
%>
<%
		out.clear();   
		out = pageContext.pushBody();
		try {
	    	String target = getTarget(request);
	    	
	        URL url = new URL(target);
	        URLConnection uc = url.openConnection();
	    	pipe(uc, response);
	    } catch (Exception e) {
	    	System.out.println(e.toString());
	    }
%>