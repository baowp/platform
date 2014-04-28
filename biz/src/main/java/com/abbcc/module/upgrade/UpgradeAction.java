package com.abbcc.module.upgrade;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcEnterprise;
import com.abbcc.models.AbcPaylog;
import com.abbcc.models.AbcPayuser;
import com.abbcc.models.AbcUser;
import com.abbcc.service.EnterpriseService;
import com.abbcc.service.LayoutService;
import com.abbcc.service.LogService;
import com.abbcc.service.PayuserService;
import com.abbcc.service.UserService;
import com.abbcc.util.DateUtil;

public class UpgradeAction extends BaseAction<AbcUser> {
	private PayuserService payuserService;
	private UserService userService;
	private EnterpriseService enterpriseService;
	public String ename;
	public String grade;
	public String grade1;
	public String email;
	public String phone;
	public String cellphone;
	public String uname;
	public String position;
	public String pageType;
	public String year;
	private LogService logService;
	@Autowired
	private LayoutService layoutService;

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public void setEnterpriseService(EnterpriseService enterpriseService) {
		this.enterpriseService = enterpriseService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setPayuserService(PayuserService payuserService) {
		this.payuserService = payuserService;
	}

	public String show() {
		return "show";
	}

	public String entry() {
		return "entry";
	}

	public String versionselect() {
		return "versionselect";
	}

	public String confirm() {
		return "confirm";
	}

	public String bankselect() {
		return "bankselect";
	}

	public String apply() {
		@SuppressWarnings("unused")
		AbcEnterprise ent = enterpriseService.findById(this.getCurrentUser()
				.getEnterpriseId());
		getRequest().setAttribute("enterprise", ent);
		return "apply";
	}

	public String custom() {
		AbcEnterprise ent = enterpriseService.findById(this.getCurrentUser()
				.getEnterpriseId());
		getRequest().setAttribute("enterprise", ent);
		return "custom";
	}

	public String join() {
		// 修改USER信息
		entity.setCellphone(cellphone);
		entity.setPhone(phone);
		entity.setPosition(position);
		userService.saveOrUpdate(entity);
		// 修改企业名称等
		AbcEnterprise ent = (AbcEnterprise) enterpriseService.findById(entity
				.getEnterpriseId());
		this.reloadSession(CommonConst.SESSIONUSER, entity);
		ent.setName(ename);
		enterpriseService.saveOrUpdate(ent);
		this.reloadSession(CommonConst.SESSIONENT, ent);
		AbcPayuser apl = new AbcPayuser();
		apl.setUserId(this.getCurrentUser().getUserId());
		apl.setGrade(grade1);
		apl.setAuditState(CommonConst.STATEINIT);
		if (grade1.equals(CommonConst.USERGRADEONE))
			apl.setPaytype(CommonConst.PAYUSERTYPEONE);
		else
			apl.setPaytype(CommonConst.PAYUSERTYPETHREE);
		List<AbcPayuser> olduser = payuserService.findByExample(apl);
		if (olduser.size() != 0) {
			AbcPayuser puser = olduser.get(0);
			// 判断是否为自定义网站用户
			puser.setApplyTime(new Date());
			puser.setYear(year);
			payuserService.saveOrUpdate(puser);
			return JSON;
		}
		AbcPayuser ap = new AbcPayuser();
		ap.setGrade(grade1);
		ap.setApplyTime(new Date());
		ap.setAuditState(CommonConst.STATEINIT);
		if (grade1.equals(CommonConst.USERGRADEONE))
			ap.setPaytype(CommonConst.PAYUSERTYPEONE);
		else
			ap.setPaytype(CommonConst.PAYUSERTYPETHREE);
		ap.setUserId(this.getCurrentUser().getUserId());
		ap.setState(CommonConst.STATEINIT);
		ap.setYear(year);
		payuserService.save(ap);
		logService.savaAdminLog("会员", "升级", CommonConst.LOGUPDATE);
		return JSON;
	}

	// 所有非付费用户可以免费试用7天
	public String trial() {

		if (isTrial()) {
			result = "提交失败，您已经免费体验过了!";
			return JSON;
		}

		baseService.load(entity, getCurrentUser().getUserId());
		entity.setGrade(CommonConst.USERGRADEONE);
		baseService.saveOrUpdate(entity);

		AbcPayuser apu = new AbcPayuser();
		apu.setApplyContent("免费试用");
		apu.setApplyTime(new Date());
		apu.setAuditState(CommonConst.STATENORMAL);
		apu.setAuditTime(new Date());
		apu.setGrade(CommonConst.USERGRADEONE);
		apu.setSum("0");
		apu.setPaytype(CommonConst.PAYUSERTYPEONE);
		apu.setUserId(entity.getUserId());
		apu.setPayway(CommonConst.PAYTYPETWO);
		baseService.save(apu);
		AbcPaylog aplog = new AbcPaylog();
		aplog.setPayuserId(apu.getPayuserId());
		aplog.setStartTime(new Date());
		aplog.setEndTime(DateUtil.getDateAfter(new Date(), 7));
		aplog.setPayTime(new Date());
		aplog.setState(CommonConst.STATENORMAL);
		aplog.setAmount("0");
		aplog.setUserId(entity.getUserId());
		aplog.setType(apu.getPaytype());
		baseService.save(aplog);
		layoutService.initialize(entity);
		result = "账号已经成功升级，重新登陆后，生效!";
		return JSON;

	}

	private boolean isTrial() {
		DetachedCriteria ap = DetachedCriteria.forClass(AbcPayuser.class);
		ap.add(Restrictions.eq("payway", "02")).add(
				Restrictions.eq("userId", getCurrentUser().getUserId()));
		List<AbcPayuser> lp = baseService.findAllByCriteria(ap);
		if (lp.size() != 0)
			return true;
		return false;
	}

	public String isTrialByJson() {
		if (isTrial()) {
			result = "1";
		} else {
			result = "0";
		}
		return JSON;
	}

	public String continueToPay() {
		return "continueToPay";
	}
}
