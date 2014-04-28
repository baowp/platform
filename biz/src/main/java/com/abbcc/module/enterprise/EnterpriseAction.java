package com.abbcc.module.enterprise;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.common.ConfConst;
import com.abbcc.models.AbcAttachment;
import com.abbcc.models.AbcAudit;
import com.abbcc.models.AbcEnterprise;
import com.abbcc.models.AbcSyscode;
import com.abbcc.models.AbcUser;
import com.abbcc.service.AttachmentService;
import com.abbcc.service.EnterpriseService;
import com.abbcc.service.LogService;
import com.abbcc.service.SyscodeService;
import com.abbcc.service.UserService;
import com.abbcc.util.StringUtil;
import com.abbcc.util.checkKey.CheckKey;
import com.abbcc.util.constant.ModelType;

public class EnterpriseAction extends BaseAction<AbcEnterprise> {
	public String attId;
	public String serverPath;
	private UserService userService;
	private EnterpriseService enterpriseService;
	private AttachmentService attachmentService;
	private SyscodeService syscodeService;
	private LogService logService;
	public String mapaddress;

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public void setSyscodeService(SyscodeService syscodeService) {
		this.syscodeService = syscodeService;
	}

	public void setAttachmentService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setEnterpriseService(EnterpriseService enterpriseService) {
		this.enterpriseService = enterpriseService;
	}

	private String message;

	private String entId;

	private String content;

	public String eAddress;
	public String eAddress2;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getEntId() {
		return entId;
	}

	public void setEntId(String entId) {
		this.entId = entId;
	}

	private Object fckEditor;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void getMapList() {
		Map<String, String> industyMap = new LinkedHashMap<String, String>();
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcSyscode.class);
		detachedCriteria.add(Restrictions.and(
				Restrictions.ne("state", CommonConst.STATEDEL),
				Restrictions.eq("type", CommonConst.SYSCODETYPEINDUSTY)));
		List<AbcSyscode> list = syscodeService
				.findAllByCriteria(detachedCriteria);
		for (AbcSyscode as : list) {
			industyMap.put(as.getSyscodeId(), as.getName());
		}
		deposit("industyMap", industyMap);
	}

	public void getTypeList() {
		Map<String, String> typeMap = new LinkedHashMap<String, String>();
		typeMap.put("00", "个人用户");
		typeMap.put("01", "企业单位");
		typeMap.put("02", "个体经营");
		typeMap.put("03", "事业单位或者团体");
		deposit("typeMap", typeMap);

	}

	public void getAdderssMap() {
		List<AbcSyscode> pList = new ArrayList<AbcSyscode>();
		Map<AbcSyscode, List<AbcSyscode>> sysMap = new HashMap<AbcSyscode, List<AbcSyscode>>();
		String hql = "from AbcSyscode where type='01' and (state<3 or state>3) order by type";
		List<AbcSyscode> addressList = syscodeService.findByHql(hql);
		for (int i = 0; i < addressList.size(); i++) {
			String hql1 = "from AbcSyscode where type='02' and (state<>3 and fatherdict='"
					+ addressList.get(i).getSyscodeId() + "') order by type";
			List<AbcSyscode> addressList1 = syscodeService.findByHql(hql1);

			sysMap.put(addressList.get(i), addressList1);
		}
		deposit("sysMap", sysMap);
	}

	public void getBusinessType() {
		Map<String, String> BusinessTypeMap = new LinkedHashMap<String, String>();
		BusinessTypeMap.put("00", "生产加工");
		BusinessTypeMap.put("01", "经营批发");
		BusinessTypeMap.put("02", "招商代理");
		BusinessTypeMap.put("03", "商业服务");
		BusinessTypeMap.put("04", "以上都不是");
		deposit("BusinessTypeMap", BusinessTypeMap);
	}

	// 显示公司基本信息
	public String showInformationManage() {
		AbcUser user = getCurrentUser();
		enterpriseService.load(entity, user.getEnterpriseId());
		try {

			if (entity.getAddress() != null) {
				String[] addressOld = entity.getAddress().split(",");
				getRequest().setAttribute("addressOne", addressOld[0]);
				getRequest().setAttribute("addressTwo", addressOld[1]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.getMapList();
		this.getTypeList();
		this.getAdderssMap();
		this.getBusinessType();
		getRequest().setAttribute("AbcUser", user);
		AbcAudit aa = new AbcAudit();
		aa.setEnterpriseId(entity.getEnterpriseId());
		List<AbcAudit> ad = baseService.findByExample(aa);

		deposit("updateMessage", ad.size() == 0 ? null : ad.get(0));

		return "showInformationManage";

	}

	// 补全公司资料

	public String updateInformation() {

		eAddress = eAddress + "," + eAddress2;
		entity.setAddress(eAddress);
		if (CommonConst.ISCONTROL) {
			String j = JSONObject.fromObject(entity).toString();
			new UpdateByJsonAction().saveAuditByJson("ent", j);
		} else {
			enterpriseService.saveOrUpdate(entity);
		}
		logService.savaLog("公司资料", entity.getName(), CommonConst.LOGUPDATE);
		reloadSession(CommonConst.SESSIONENT, entity);
		return JSON;
	}

	// 返回公司补全信息列表
	public String completionList() {
		enterpriseService.load(entity, this.getCurrentUser().getEnterpriseId());
		this.getTypeList();
		this.getAdderssMap();
		getMapList();
		getBusinessType();
		AbcUser userSession = (AbcUser) getSession().getAttribute(
				CommonConst.SESSIONUSER);
		AbcEnterprise ent = enterpriseService.findById(userSession
				.getEnterpriseId());
		try {
			if (ent.getAddress() != null) {
				String[] addressOld = ent.getAddress().split(",");
				getRequest().setAttribute("addressOne", addressOld[0]);
				getRequest().setAttribute("addressTwo", addressOld[1]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// getRequest().setAttribute("AbcEnterprise", ent);
		return "completionList";
	}

	// 修改公司简介
	public String updateEnterpriseInfo() {
		if (CommonConst.ISCONTROL) {
			String j = JSONObject.fromObject(entity).toString();
			new UpdateByJsonAction().saveAuditByJson("entinfo", j);
			this.setTempAttachment(entId, ModelType.AP);
			this.result = "您的申请已经提交，审核通过后，系统将为您修改资料!";
		} else {
			baseService.saveOrUpdate(entity);
			this.result = "修改成功!";
		}
		setUploadState(entity.getEdesc());
		logService.savaLog("公司简介", entity.getName(), CommonConst.LOGUPDATE);
		return "updateEnterpriseInfo";
	}

	// 显示公司简介
	public String showEnterpriseInfo() {
		enterpriseService.load(entity, getCurrentUser().getEnterpriseId());
		return "showEnterpriseInfo";

	}

	public String showTechnic() {
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
		return "showTechnic";
	}

	public String updateTechnic() throws UnknownHostException {
		if (!CheckKey.checkKey(content)) {
			this.addFieldError("content", "存在非法字符！");
			return "technical";
		}
		AbcUser user = (AbcUser) getSession().getAttribute(
				CommonConst.SESSIONUSER);
		AbcAttachment at = new AbcAttachment();
		at.setBelongId(user.getEnterpriseId());
		at.setBelongType(ModelType.AP);
		List list = attachmentService.findByExample(at);
		InetAddress localhost = InetAddress.getLocalHost();
		String ip = localhost.getHostAddress();
		String url = content;
		AbcAttachment att = new AbcAttachment();
		if (list.size() != 0) {
			AbcAttachment att1 = (AbcAttachment) list.iterator().next();
			att.setAttId(att1.getAttId());
		}
		att.setBelongType(ModelType.AP);
		att.setBelongId(user.getEnterpriseId());
		att.setContent(url);
		att.setUserId(user.getUserId());
		att.setServerIp(ip);
		att.setType(CommonConst.ATTACHTYPETECHNIC);
		att.setUploadTime(new Date());

		String j = JSONObject.fromObject(att).toString();
		new UpdateByJsonAction().saveAuditByJson("technic", j);

		logService.savaLog("技术实力", entity.getName(), CommonConst.LOGUPDATE);
		this.result = StringUtil.encode(CommonConst.EDITSUCCESS);
		this.setTempAttachment(entId, ModelType.AP);
		return "updateTechnic";
	}

	public String logo() {
		AbcAttachment at = new AbcAttachment();
		at.setBelongId(getCurrentUser().getEnterpriseId());
		at.setBelongType(ModelType.AP);
		at.setType(CommonConst.ATTACHTYPELOGO);
		@SuppressWarnings("unused")
		List<AbcAttachment> list = attachmentService.findByExample(at);
		if (list.size() != 0)
			getRequest().setAttribute("logoList", list.get(0));
		return "logoList";
	}

	public String uploadLogo() {
		AbcAttachment at = new AbcAttachment();
		if (attId != null && !(attId.equals("")))
			at.setAttId(attId);
		at.setUploadTime(new Date());
		at.setBelongId(this.getCurrentUser().getEnterpriseId());
		at.setBelongType(ModelType.AP);
		at.setType(CommonConst.ATTACHTYPELOGO);
		at.setServerPath(serverPath);
		attachmentService.saveOrUpdate(at);
		getSession().setAttribute("logoEnt",
				ConfConst.FILE_SVR + at.getServerPath());
		result = ConfConst.FILE_SVR + at.getServerPath();
		return JSON;
	}

	public String updateName() {
		entity.setEnterpriseId(getCurrentUser().getEnterpriseId());
		enterpriseService.saveOrUpdate(entity);
		reloadSession(CommonConst.SESSIONENT, entity);
		return JSON;
	}

	// 保存企业地图标识
	public void savemap() {
		enterpriseService.load(entity, getCurrentEnt().getEnterpriseId());
		entity.setMapaddress(mapaddress);
		enterpriseService.saveOrUpdate(entity);
		reloadSession(CommonConst.SESSIONENT, entity);
	}

	public String getMapKey() {
		AbcEnterprise ae = enterpriseService.findById(StringUtil.isBlank(entity.getEnterpriseId())?getCurrentUser().getEnterpriseId():entity.getEnterpriseId());
		ae.setAddress(this.getCurrentUser().getAddress());
		Map map = new HashMap();
		map.put("phone", this.getCurrentUser().getPhone());
		map.put("address", getCurrentUser().getAddress());
		map.put("name", ae.getName());
		map.put("mapaddress", ae.getMapaddress());
		result = JSONArray.fromObject(map).toString();
		return JSON;
	}

	public String key() {
		enterpriseService.load(entity, getCurrentUser().getEnterpriseId());
		return "key";
	}

	public String saveKey() {
		if (!CheckKey.checkKey(entity.getKey())) {
			this.addFieldError("key", "存在非法字符！");
			return "key";
		}
		enterpriseService.saveOrUpdate(entity);
		result = CommonConst.EDITSUCCESS;
		return key();
	}

}
