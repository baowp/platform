package com.abbcc.merchants.action;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.merchants.models.Merchants;
import com.abbcc.merchants.service.MerchantsService;

public class MerchantsManageAction extends BaseAction<Merchants>{
	private MerchantsService merchantsService;

	public void setMerchantsService(MerchantsService merchantsService) {
		this.merchantsService = merchantsService;
	}
	public String list(){
		DetachedCriteria dc = DetachedCriteria
		.forClass(entity.getClass());
		dc.add(Restrictions.eq("domain",domain));
		dc.addOrder(Order.desc("merchantsId"));
		pageList = merchantsService.findPageByCriteria(dc, pageSize,
				startIndex);
		return LIST;
	}
	public String save(){
		merchantsService.saveOrUpdate(entity);
		return SUCCESS;
	}
	public String setState(){
		if(entity.getState().equals(CommonConst.STATEINIT)){
			entity.setState(CommonConst.STATENORMAL);
			result="通过审核";
		}
			
		else{
			entity.setState(CommonConst.STATEINIT);
			result="未处理";
		}
			
		merchantsService.saveOrUpdate(entity);
		return JSON;
	}
	public String remove(){
		merchantsService.delete(entity);
		return SUCCESS;
	}
}
