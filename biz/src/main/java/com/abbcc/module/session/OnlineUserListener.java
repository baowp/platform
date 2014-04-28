package com.abbcc.module.session;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionEvent;

import org.apache.struts2.ServletActionContext;

import com.abbcc.common.CommonConst;
import com.abbcc.common.ConfConst;
import com.abbcc.models.AbcAttachment;
import com.abbcc.models.AbcUser;
import com.abbcc.module.cache.CacheService;
import com.abbcc.service.UserService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class OnlineUserListener extends HttpServlet  implements HttpSessionListener {

	//private static final ServletActionContext ServletActionContext = null;
    public void sessionCreated(HttpSessionEvent event) {
    }

    public void sessionDestroyed(HttpSessionEvent event) {
    	//Cookie[] cookie = HttpServletRequest.this.getCookies();
    	CacheService cacheService =(CacheService) BeansFactory.get("cacheService");
    	String sid = cacheService.getKey();
    	Map session = (Map)cacheService.get(sid);
    	AbcUser user = (AbcUser)session.get(CommonConst.SESSIONUSER);
        // 取得登录的用户名
       // AbcUser user = (AbcUser) session.getAttribute(CommonConst.SESSIONUSER);
		//删除临时文件，session里的
		List<AbcAttachment> tempAttachments = (List) session.get(CommonConst.SESSIONATTACHMENTS);
		if(tempAttachments!=null&&tempAttachments.size()!=0){
			for(AbcAttachment att:tempAttachments){
				removeAll(att.getServerPath());
			}
		}
		if(user!=null){
			System.out.println(user.getUsername() + "超时退出。");
			session.clear();
		}

    }
    private void removeAll(String srcPath){
		String to1 = srcPath.substring(0, srcPath.lastIndexOf(".")) + "_3.jpg";
		String to2 = srcPath.substring(0, srcPath.lastIndexOf(".")) + "_4.jpg";
		String to3 = srcPath.substring(0, srcPath.lastIndexOf(".")) + "_5.jpg";
		deleteFile(srcPath);
		deleteFile(to1);
		deleteFile(to2);
		deleteFile(to3);

	}
	 public boolean deleteFile(String sPath) {  
	     Boolean flag = false;  
	     sPath = ConfConst.FILE_UPLOAD_DIR+StringUtil.pathReplace(sPath);
	     File file = new File(sPath);  
	     // 路径为文件且不为空则进行删除  
	     if (file.isFile() && file.exists()) {  
	         file.delete();  
	         flag = true;  
	     }  
	     return flag;  
	}  

}

