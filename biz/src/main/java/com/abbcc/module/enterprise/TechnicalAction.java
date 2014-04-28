package com.abbcc.module.enterprise;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcAttachment;
import com.abbcc.models.AbcUser;
import com.abbcc.service.AttachmentService;
import com.abbcc.service.LogService;
import com.abbcc.util.StringUtil;
import com.abbcc.util.checkKey.CheckKey;
import com.abbcc.util.constant.ModelType;

public class TechnicalAction extends BaseAction<AbcAttachment> {
	private AttachmentService attachmentService;
	private LogService logService;
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	public void setAttachmentService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}
	public String showTechnic(){
		AbcUser user = (AbcUser) getSession().getAttribute(
				CommonConst.SESSIONUSER);
		AbcAttachment att = new AbcAttachment();
		att.setBelongId(user.getEnterpriseId());
		att.setBelongType(ModelType.AP);
		List attList = attachmentService.findByExample(att);
		if (attList.size() != 0) {
			AbcAttachment attachment = (AbcAttachment) attList.iterator()
					.next();
			getRequest().setAttribute("AbcAttachment", attachment);
		}
		return INPUT;
	}
	public String updateTechnic() throws UnknownHostException {
		
		entity.setBelongId(this.getCurrentUser().getEnterpriseId());
		entity.setBelongType(ModelType.AP);
		if(CommonConst.ISCONTROL){
		String j = JSONObject.fromObject(entity).toString();
		new UpdateByJsonAction().saveAuditByJson("technic",j);
		this.result = "您的申请已经提交，审核通过后，系统将为您修改资料!";
		}else{
			baseService.saveOrUpdate(entity);
			this.result = "修改成功!";
		}
		setUploadState(entity.getContent());
		logService.savaLog("技术实力","", CommonConst.LOGUPDATE);
		this.setTempAttachment(this.getCurrentUser().getEnterpriseId(), ModelType.AP);
		return showTechnic();
	}
}
