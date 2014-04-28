package com.abbcc.module.entManage;

import net.sf.json.JSONObject;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.common.PaginationSupport;
import com.abbcc.models.AbcAttachment;
import com.abbcc.models.AbcAudit;
import com.abbcc.models.AbcEnterprise;
import com.abbcc.models.AbcUser;
import com.abbcc.module.jms.MessageSender;
import com.abbcc.util.StringUtil;

public class UpdateAuditAction extends BaseAction<AbcAudit> {
	public String[] auditIds;
	private String USER = "user";
	private String ENT = "ent";
	private String ENTINFO = "entinfo";
	private String TECHNIC = "technic";
	public String viewInfo;
	@Autowired
	private MessageSender messageSender;

	public String user() {
		pageList = uList(USER);
		deposit("viewInfo", "userPageUpdateAudit");
		return USER;
	}

	public String ent() {
		pageList = uList(ENT);
		deposit("viewInfo", "entPageUpdateAudit");
		return USER;
	}
	public String entInfo(){
		pageList = uList(ENTINFO);
		deposit("viewInfo", "entInfoPageUpdateAudit");
		return USER;
	}
	public String technic(){
		pageList = uList(TECHNIC);
		deposit("viewInfo", "technicPageUpdateAudit");
		return USER;
	}

	public String save() {
		for (String aId : auditIds) {
			String[] auditIdAndState = StringUtil.splitBySemicolon(aId);
			baseService.load(entity, auditIdAndState[0]);
			if (auditIdAndState[1].equals("02")) {
				entity.setState(auditIdAndState[1]);
				baseService.saveOrUpdate(entity);
				AbcEnterprise ae = new AbcEnterprise();
				AbcUser au = new AbcUser();
				baseService.load(ae, entity.getEnterpriseId());
				baseService.load(au,ae.getUserId());
				String content = "尊敬的会员，您于"+entity.getAddTime()+"修改的资料未通过审核，原因可能是因为您填写的资料里含有非法信息，请重新检查再提交!";
				messageSender.sendString(CommonConst.JMS_MAIL,au.getEmail()+"[**]"+"[**]"+"[**]"+"尊敬的会员您提交的资料审核未通过!"+"[**]"+content+"[**]"+"[**]");
			} else {
				JSONObject js = JSONObject.fromObject(entity.getJsonContent());
				if (viewInfo.equals("userPageUpdateAudit")) {
					AbcUser au = (AbcUser) JSONObject.toBean(js, AbcUser.class);
					baseService.saveOrUpdate(au);
				}
				if (viewInfo.equals("entPageUpdateAudit")) {
					AbcEnterprise au = (AbcEnterprise) JSONObject.toBean(js, AbcEnterprise.class);
					baseService.saveOrUpdate(au);
				}
				if (viewInfo.equals("entInfoPageUpdateAudit")) {
					AbcEnterprise au = (AbcEnterprise) JSONObject.toBean(js, AbcEnterprise.class);
					baseService.saveOrUpdate(au);
				}if (viewInfo.equals("technicPageUpdateAudit")) {
					AbcAttachment au = (AbcAttachment) JSONObject.toBean(js, AbcAttachment.class);
					baseService.saveOrUpdate(au);
				}
				baseService.delete(entity);
			}
		}
		return user();
	}

	public String userPage() {
		JSONObject js = JSONObject.fromObject(entity.getJsonContent());
		AbcUser au = (AbcUser) JSONObject.toBean(js, AbcUser.class);
		deposit("user", au);
		return "userpage";
	}

	public String entPage() {
		JSONObject js = JSONObject.fromObject(entity.getJsonContent());
		AbcEnterprise ent = (AbcEnterprise) JSONObject.toBean(js,
				AbcEnterprise.class);
		ent.setIndustry(new AbcAudit().industry(ent.getIndustry()).getName());
		ent.setBusinessType(new AbcAudit().businessTypeName(ent
				.getBusinessType()));
		deposit("ent", ent);
		return "entpage";
	}
	public String entInfoPage() {
		JSONObject js = JSONObject.fromObject(entity.getJsonContent());
		AbcEnterprise au = (AbcEnterprise) JSONObject.toBean(js, AbcEnterprise.class);
		deposit("entInfoOrTechnic", au.getEdesc());
		return "entInfoOrTechnic";
	}
	public String technicPage() {
		JSONObject js = JSONObject.fromObject(entity.getJsonContent());
		AbcAttachment au = (AbcAttachment) JSONObject.toBean(js, AbcAttachment.class);
		deposit("entInfoOrTechnic", au.getContent());
		return "entInfoOrTechnic";
	}
	private PaginationSupport uList(String className) {
		DetachedCriteria dc = DetachedCriteria.forClass(entity.getClass());
		dc.add(Restrictions.eq("className", className)).add(
				Restrictions.eq("state", CommonConst.STATEINIT));
		return baseService.findPageByCriteria(dc, pageSize, startIndex);
	}
}
