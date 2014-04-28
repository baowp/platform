package com.abbcc.module.pay;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcPaylog;
import com.abbcc.models.AbcPayuser;
import com.abbcc.models.AbcUser;
import com.abbcc.service.GroupLayoutService;
import com.abbcc.service.LayoutService;
import com.abbcc.service.PaylogService;
import com.abbcc.service.PayuserService;
import com.abbcc.service.UserService;
import com.abbcc.util.DateUtil;
import com.abbcc.util.ObjectUtil;
import com.abbcc.util.StringUtil;

public class MemberAuditManageAction extends BaseAction<AbcPayuser> {
	private PayuserService payuserService;
	private PaylogService paylogService;
	private UserService userService;
	public String searchWord;
	@Autowired
	private LayoutService layoutService;
	@Autowired
	private GroupLayoutService grouplayoutService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setPaylogService(PaylogService paylogService) {
		this.paylogService = paylogService;
	}
	
	public String id;
	public String frontTime;
	public String backTime;
	public String payTime;
	public String pageType;
	public void setPayuserService(PayuserService payuserService) {
		this.payuserService = payuserService;
	}

	// 显示还未验证的,并且是第一次交费的用户
	public String show() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcPayuser.class);
		if (!getCurrentAdmin().getUsername().equals("admin")) {
			List<List> l = ObjectUtil.getInDataByList(getUserIdsByDomain());
			for (int i = 0; i < l.size(); i++) {
				detachedCriteria.add(Restrictions.in("userId", l.get(i)));
			}
		}
		detachedCriteria.add(Restrictions.eq("auditState",
				CommonConst.STATEINIT));
		detachedCriteria.addOrder(Order.desc("payuserId")).addOrder(Order.asc("grade"));
		pageList = payuserService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		
		return "showMemberAuditList";
	}

	// 显示还未验证的,并且是第一次交费的用户
	public String showReNew() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcPayuser.class);
		if (!getCurrentAdmin().getUsername().equals("admin")) {
			List<List> l = ObjectUtil.getInDataByList(getUserIdsByDomain());
			for (int i = 0; i < l.size(); i++) {
				detachedCriteria.add(Restrictions.in("userId", l.get(i)));
			}
		}
		detachedCriteria.add(Restrictions.and(
				Restrictions.eq("paytype", CommonConst.PAYUSERTYPETWO),
				Restrictions.eq("auditState", CommonConst.STATEINIT)));
		detachedCriteria.addOrder(Order.desc("grade"));
		detachedCriteria.addOrder(Order.desc("applyTime"));
		pageList = payuserService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		return "showMemberAuditList";
	}

	public String search() {
		List<AbcPaylog> plogList = new ArrayList();
		List<AbcUser> userList = new ArrayList();
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcPayuser.class);
		if (!getCurrentAdmin().getUsername().equals("admin")) {
			List<List> l = ObjectUtil.getInDataByList(getUserIdsByDomain());
			ObjectUtil.splitSourceInDc(l, detachedCriteria, "in", "userId", 500);
		}
		if(StringUtil.isNotBlank(searchWord)){
			DetachedCriteria dc = DetachedCriteria.forClass(AbcUser.class);
			dc.add(Restrictions.like("username", searchWord.trim(), MatchMode.ANYWHERE))
					.setProjection(Projections.property("userId"));
			List<String> userIds = baseService.findAllByCriteria(dc);
			ObjectUtil.splitSourceInDc(userIds, detachedCriteria, "in", "userId", 500);
		}
		detachedCriteria.add(Restrictions.eq("auditState",
				CommonConst.STATEINIT));
		detachedCriteria.addOrder(Order.desc("grade"));
		detachedCriteria.addOrder(Order.desc("applyTime"));
		pageList = payuserService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		return "showMemberAuditList";

	}

	// 验证用户
	public String pass() {
		entity.setAuditState(CommonConst.STATENORMAL);
		entity.setAuditTime(new Date());
		entity.setAuditAdmin(this.getCurrentAdmin().getAdminId());
		payuserService.saveOrUpdate(entity);
		// 修改用户等级
		AbcUser user = userService.findById(entity.getUserId());
		if(entity.getPaytype().equals(CommonConst.PAYUSERTYPEONE))
			user.setGrade(CommonConst.USERGRADEONE);
		else if(entity.getPaytype().equals(CommonConst.PAYUSERTYPETHREE))
			user.setGrade(CommonConst.USERGRADETWO);
		else
		user.setGrade(entity.getGrade());
		user.setUserId(user.getUserId());
		userService.saveOrUpdate(user);
		// 初始AbcLayout
		if(entity.getPayway().equals("02")){//02表示升级,01表示续费
		if(entity.getPaytype().equals(CommonConst.PAYUSERTYPETHREE))
		grouplayoutService.initUser(user);
		else
		layoutService.initialize(user);
		}
		AbcPaylog al = new AbcPaylog();
		al.setPayuserId(entity.getPayuserId());
		al.setType(entity.getPaytype());
		al.setState(CommonConst.STATENORMAL);
		al.setPayTime(DateUtil.strToDate(payTime));
		al.setStartTime(DateUtil.strToDate(frontTime));
		al.setEndTime(DateUtil.strToDate(backTime));
		al.setAmount(entity.getSum());
		al.setUserId(payuserService.findById(entity.getPayuserId()).getUserId());
		paylogService.save(al);
		if(StringUtil.isNotBlank(pageType)){
		if(pageType.equals("viewMember"))
			return "viewMember";
		}
		return "returnShow";
	}
	public String del(){
		baseService.delete(entity);
		return search();
	}
}
