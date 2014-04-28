package com.abbcc.module.enterprise;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcAudit;
import com.abbcc.models.AbcUser;
import com.abbcc.service.BaseService;
import com.abbcc.service.LogService;
import com.abbcc.service.UserService;
import com.abbcc.util.StringUtil;
import com.abbcc.util.checkKey.CheckKey;

public class UserAction extends BaseAction<AbcUser>{
	private UserService userService;
	private LogService logService;
	@Autowired
	private BaseService baseService;
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	// 修改个人信息
	public String update() {
		if(!CheckKey.checkKey(entity.getName())){
			this.addFieldError("name", "存在非法字符！");
			return INPUT;
		}
		if(!CheckKey.checkKey(entity.getPosition())){
			this.addFieldError("position", "存在非法字符！");
			return INPUT;
		}
		if(!CheckKey.checkKey(entity.getAddress())){
			this.addFieldError("address", "存在非法字符！");
			return INPUT;
		}
			//userService.saveOrUpdate(entity);
		if(CommonConst.ISCONTROL){
			String j = JSONObject.fromObject(entity).toString();
			new UpdateByJsonAction().saveAuditByJson("user",j);
			this.result = StringUtil.encode("已经提交，正在审核。");
		}else{
			baseService.saveOrUpdate(entity);
			this.result = StringUtil.encode("修改成功");
		}

			getSession().setAttribute(CommonConst.SESSIONUSER,entity);
			return JSON;

	}
	public String edit() {
		userService.load(entity,this.getCurrentUser().getUserId());
		return EDIT;
	}


}
