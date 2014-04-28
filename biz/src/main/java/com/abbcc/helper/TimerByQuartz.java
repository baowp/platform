package com.abbcc.helper;

import java.io.InputStream;
import java.net.URL;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

public class TimerByQuartz{
	    public void work()  
	     {
	        try {
	            URL o_url = new URL("http://51archetype.com/news/admin/news/indexStatic");
	            InputStream in = o_url.openStream();
	            in.close();
	            
	        }
	        catch(Exception ex) {
	            ex.printStackTrace();
	        }
	    
	     } 
	    public void checkMember(){
	    	try {
	            URL o_url = new URL("http://51archetype.com/vipUser/allUserCheckMember");
	            InputStream in = o_url.openStream();
	            in.close();
	            
	        }
	        catch(Exception ex) {
	            ex.printStackTrace();
	        }
	    }
}
