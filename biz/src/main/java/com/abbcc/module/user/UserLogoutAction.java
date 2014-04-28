package com.abbcc.module.user;



import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.common.ConfConst;
import com.abbcc.models.AbcAttachment;
import com.abbcc.models.AbcUser;
import com.abbcc.module.cache.CacheService;
import com.abbcc.service.AttachmentService;
import com.abbcc.service.LogService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;

public class UserLogoutAction extends BaseAction<Object> {
	public String pageType;
	public String getPageType() {
		return pageType;
	}
	private AttachmentService attachmentService;
	private LogService logService;
	public void setAttachmentService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}
	/**
	 * serial id
	 */
	private static final long serialVersionUID = -6264596900443037095L;

	/**
	 * 用户退出登陆
	 * 
	 * @return
	 */
	public String logout() {
		this.getSession().invalidate();
		if(pageType!=null)
			return "index";
		return "login";
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
