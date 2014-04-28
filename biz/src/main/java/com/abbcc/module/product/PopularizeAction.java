package com.abbcc.module.product;


import java.util.Date;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcPopularize;
import com.abbcc.service.PopularizeService;
import com.abbcc.service.ProductService;
import com.abbcc.util.StringUtil;
import com.abbcc.util.checkKey.CheckKey;

public class PopularizeAction extends BaseAction<AbcPopularize> {
	private PopularizeService popularizeService;

	public void setPopularizeService(PopularizeService popularizeService) {
		this.popularizeService = popularizeService;
	}
	private String entId;
	private String[] prodIds;
	private String entName;
	private String searchKey;
	private String prodState=CommonConst.STATEINIT;
	private String SEARCH="search";
	private String LISTSEARCH="listsearch";
	public String view(){
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(AbcPopularize.class);
			detachedCriteria.add(Restrictions.eq("state", CommonConst.STATENORMAL));
		detachedCriteria.addOrder(Order.desc("addTime"));
		this.startIndex = (this.page - 1) * pageSize;
		this.pageList = popularizeService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		if (pageList.getTotalCount() == 0)
			result = CommonConst.NORESULT;
		return VIEW;
	}
	public String add(){
		return "add";
	}
	public String save(){
		if (!CheckKey.checkKey(entity.getKey())) {
			this.addFieldError("key", "存在非法字符！");
			return INPUT;
		}
		if (!CheckKey.checkKey(entity.getpName())) {
			this.addFieldError("pName", "存在非法字符！");
			return INPUT;
		}

		entity.setAddTime(new Date());
		entity.setAdminId(this.getCurrentAdmin().getAdminId());
		entity.setState(CommonConst.STATENORMAL);
		popularizeService.save(entity);
		this.result=CommonConst.ADDSUCCESS;
		return "save";
	}
	public String del(){
		entity.setState(CommonConst.STATEDEL);
		popularizeService.saveOrUpdate(entity);
		this.result=CommonConst.DELSUCCESS;
		return "save";
	}

}
