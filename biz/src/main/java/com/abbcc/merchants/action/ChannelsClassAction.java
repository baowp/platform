package com.abbcc.merchants.action;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;

import com.abbcc.action.BaseAction;
import com.abbcc.merchants.models.ChannelsClass;
import com.abbcc.merchants.models.ChannelsInfo;
import com.abbcc.merchants.service.ChannelsClassService;
import com.abbcc.merchants.service.ChannelsInfoService;

public class ChannelsClassAction extends BaseAction<ChannelsClass>{
	public String checkboxs;
	public String enterpriseId;
	private ChannelsClassService channelsClassService;
	private ChannelsInfoService channelsInfoService;
	public void setChannelsInfoService(ChannelsInfoService channelsInfoService) {
		this.channelsInfoService = channelsInfoService;
	}
	public void setChannelsClassService(ChannelsClassService channelsClassService) {
		this.channelsClassService = channelsClassService;
	}
	public String list(){
		ChannelsClass cc = new ChannelsClass();
		cc.setDomain(domain);
		DetachedCriteria dc = DetachedCriteria.forClass(entity.getClass());
		dc.add(Example.create(cc));
		dc.addOrder(Order.asc("sort"));
		pageList = channelsClassService.findPageByCriteria(dc, pageSize, startIndex);
		return LIST;
	}
	public String channelsList(){
		ChannelsClass cc = new ChannelsClass();
		cc.setDomain(domain);
		DetachedCriteria dc = DetachedCriteria.forClass(entity.getClass());
		dc.add(Example.create(cc));
		dc.addOrder(Order.asc("sort"));
		pageList = channelsClassService.findPageByCriteria(dc, pageSize, startIndex);
		return "cList";
	}
	public String save(){
		entity.setDomain(domain);
		entity.setSort(newSort("ChannelsClass"));
		channelsClassService.saveOrUpdate(entity);
		return SUCCESS;
	}
	public String remove(){
		channelsClassService.delete(entity);
		return SUCCESS;
	}
	public String saveToInfo(){
		String[] cId = getRequest().getParameterValues("ch");
		for(int i=0;i<cId.length;i++){
			ChannelsInfo ci = new ChannelsInfo();
			ci.setEnterpriseId(enterpriseId);
			ci.setChannelsId(cId[i]);
			ci.setSort(newSort("ChannelsInfo"));
			ci.setAddTime(new Date());
			ci.setDomain(domain);
			channelsInfoService.save(ci);
		}
		return JSON;
	}
	private Integer newSort(String className) {
		String hql = "";

			hql = "select max(sort) from "+className;
		
		@SuppressWarnings("unchecked")
		List<Integer> list = (List<Integer>) channelsClassService.find(hql);
		Integer sort=null;
		if(list.size()>0)
			sort = list.get(0);
		if (sort == null)
			sort = 1;
		else
			sort++;
		return sort;
	}
	

}
