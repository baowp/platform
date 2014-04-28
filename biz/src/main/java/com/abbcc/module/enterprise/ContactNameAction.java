package com.abbcc.module.enterprise;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcCustom;
import com.abbcc.models.AbcEnterpcontact;
import com.abbcc.models.AbcUser;
import com.abbcc.module.toolbox.Recycle;
import com.abbcc.service.EnterpcontactService;
import com.abbcc.service.LogService;
import com.abbcc.util.StringUtil;
import com.abbcc.util.checkKey.CheckKey;

public class ContactNameAction extends BaseAction<AbcEnterpcontact> {
	public int stepType;
	public String sourceCate;
	public int sourceSort;
	public String targetCate;
	public int targetSort;
	private EnterpcontactService enterpcontactService;
	private LogService logService;

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public void setEnterpcontactService(
			EnterpcontactService enterpcontactService) {
		this.enterpcontactService = enterpcontactService;
	}

	public String show() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcEnterpcontact.class);
		detachedCriteria.addOrder(Order.asc("sort"));
		detachedCriteria.add(Restrictions.and(Restrictions.ne("state",
				CommonConst.STATEDEL), Restrictions.eq("enterpriseId", this
				.getCurrentUser().getEnterpriseId())));
		this.pageList = enterpcontactService.findPageByCriteria(
				detachedCriteria, pageSize, startIndex);
		return "show";
	}

	public String add() {
		if(!CheckKey.checkKey(entity.getName())){
			this.addFieldError("name", "存在非法字符！");
			return INPUT;
		}
		if(!CheckKey.checkKey(entity.getPosition())){
			this.addFieldError("position", "存在非法字符！");
			return INPUT;
		}
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcEnterpcontact.class);
		detachedCriteria.add(Restrictions.and(Restrictions.ne("state",
				CommonConst.STATEDEL), Restrictions.eq("enterpriseId", this
				.getCurrentUser().getEnterpriseId())));
		List<AbcEnterpcontact> list = enterpcontactService
				.findAllByCriteria(detachedCriteria);
		if (list.size() >4) {
			this.addFieldError("message", "对不起，企业联系人不能超过五个");
			return INPUT;
		}
		entity.setState(CommonConst.STATENORMAL);
		entity.setSort(newSort());
		entity.setAdduserId(this.getCurrentUser().getUserId());
		entity.setEnterpriseId(this.getCurrentUser().getEnterpriseId());
		enterpcontactService.save(entity);
		logService.savaLog("企业联系人",entity.getName(), CommonConst.LOGSAVE);
		this.result=StringUtil.encode(CommonConst.ADDSUCCESS);
		return "returnshow";
	}

	@Recycle(name = "name")
	public String del() {
		enterpcontactService.delete(entity);
		logService.savaLog("企业联系人",entity.getName(), CommonConst.LOGDEL);
		this.result=StringUtil.encode(CommonConst.DELSUCCESS);
		return "returnshow";
	}
	public String updatePage(){
		return "update";
	}
	public String update(){
		if(!CheckKey.checkKey(entity.getName())){
			this.addFieldError("name", "存在非法字符！");
			return INPUT;
		}
		if(!CheckKey.checkKey(entity.getPosition())){
			this.addFieldError("position", "存在非法字符！");
			return INPUT;
		}
		enterpcontactService.saveOrUpdate(entity);
		return "returnshow";
	}
	public String step() {
		AbcUser user = getCurrentUser();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entity
				.getClass());
		detachedCriteria.add(Restrictions.eq("enterpriseId", user
				.getEnterpriseId()));
		
		if (stepType == 1) {
			detachedCriteria.addOrder(Order.asc("sort"));
			detachedCriteria.add(Restrictions.gt("sort", sourceSort));
		} else {
			detachedCriteria.addOrder(Order.desc("sort"));
			detachedCriteria.add(Restrictions.lt("sort", sourceSort));
		}
		pageList = enterpcontactService.findPageByCriteria(detachedCriteria, 1, 0);
		for (AbcEnterpcontact ac : (List<AbcEnterpcontact>) pageList.getItems()) {
			changeSort(sourceCate, sourceSort, ac.getEnterpcontactId(), ac.getSort());
		}
		List<AbcEnterpcontact> customList = MenuList();
		getSession().setAttribute("customList", customList);
		return "returnshow";
	}
	private int[] changeSort(String sourceCate, int sourceSort,
			String targetCate, int targetSort) {
		if (sourceCate == null || sourceSort == 0 || targetCate == null
				|| targetSort == 0)
			return new int[0];
		String[] sql = new String[2];
		sql[0] = "update abc_enterpcontact set sort=" + targetSort + " where enterpcontact_id='"
				+ sourceCate + "'";
		sql[1] = "update abc_enterpcontact set sort=" + sourceSort + " where enterpcontact_id='"
				+ targetCate + "'";
		return enterpcontactService.batchUpdate(sql);
	}
	private List MenuList(){
		DetachedCriteria detachedCriteria = DetachedCriteria
		.forClass(AbcEnterpcontact.class);
		detachedCriteria.add(Restrictions.eq("enterpriseId", this.getCurrentUser().getEnterpriseId()));
		detachedCriteria.addOrder(Order.asc("sort"));
		List<AbcEnterpcontact> customList = enterpcontactService.findAllByCriteria(detachedCriteria);
		return customList;
	}
	private Integer newSort() {
		String hql = "select max(sort) from AbcEnterpcontact";

		@SuppressWarnings("unchecked")
		List<Integer> list = (List<Integer>) enterpcontactService.find(hql);
		Integer sort = null;
		if (list.size() > 0)
			sort = list.get(0);
		if (sort == null)
			sort = 1;
		else
			sort++;
		return sort;
	}
}
