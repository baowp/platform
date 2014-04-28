package com.abbcc.merchants.action;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.merchants.models.ChannelsInfo;
import com.abbcc.merchants.service.ChannelsClassService;
import com.abbcc.merchants.service.ChannelsInfoService;
import com.abbcc.models.AbcCategory;
import com.abbcc.models.AbcCertificate;
import com.abbcc.models.AbcEnterprise;
import com.abbcc.models.AbcUser;
import com.abbcc.service.EnterpriseService;
import com.abbcc.util.ObjectUtil;

public class ChannelsInfoAction extends BaseAction<ChannelsInfo>{
	public int stepType;
	public String cId;
	public String sourceCate;
	public int sourceSort;
	public String targetCate;
	public int targetSort;
	private ChannelsClassService channelsClassService;
	private EnterpriseService enterpriseService;
	private ChannelsInfoService channelsInfoService;
	public void setChannelsInfoService(ChannelsInfoService channelsInfoService) {
		this.channelsInfoService = channelsInfoService;
	}
	public void setEnterpriseService(EnterpriseService enterpriseService) {
		this.enterpriseService = enterpriseService;
	}
	public void setChannelsClassService(ChannelsClassService channelsClassService) {
		this.channelsClassService = channelsClassService;
	}
	public String list(){
		ChannelsInfo cc = new ChannelsInfo();
		cc.setDomain(domain);
		cc.setChannelsId(cId);
		DetachedCriteria dc = DetachedCriteria.forClass(entity.getClass());
		dc.add(Example.create(cc));
		dc.addOrder(Order.asc("sort"));
		pageList = channelsClassService.findPageByCriteria(dc, pageSize, startIndex);
		resultList = channelsClassService.findAllByCriteria(dc);
		// 构造结果集在同级的排序

		int i = 1;
		Map<String, Integer> sortMap = new LinkedHashMap<String, Integer>();
		for (ChannelsInfo cate :resultList)
			sortMap.put(cate.getInfoId() + "," + cate.getSort(), i++);
		getRequest().setAttribute("sortMap", sortMap);
		getRequest().setAttribute("cId", cId);
		return LIST;
	}
	public String save(){
		entity.setDomain(domain);
		entity.setSort(newSort());
		entity.setEsbablished(CommonConst.STATEINIT);
		entity.setStorm(CommonConst.STATEINIT);
		channelsClassService.saveOrUpdate(entity);
		return SUCCESS;
	}
	public String remove(){
		channelsInfoService.delete(entity);
		return SUCCESS;
	}
	public String enterprise(){
		DetachedCriteria dc = DetachedCriteria.forClass(AbcEnterprise.class);
		List<List> l = ObjectUtil.getInDataByList(getEnterpriseIdsByDomain());
		for(int i=0;i<l.size();i++)
			dc.add(Restrictions.in("enterpriseId",l.get(i)));
		pageList = enterpriseService.findPageByCriteria(dc, pageSize, startIndex);
		return "entlist";
	}
	private Integer newSort() {
		String hql = "";

			hql = "select max(sort) from ChannelsClass";
		
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
	public String step() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entity
				.getClass());
		detachedCriteria.add(Restrictions.eq("channelsId", cId));
		
		if (stepType == 1) {
			detachedCriteria.addOrder(Order.asc("sort"));
			detachedCriteria.add(Restrictions.gt("sort", sourceSort));
		} else {
			detachedCriteria.addOrder(Order.desc("sort"));
			detachedCriteria.add(Restrictions.lt("sort", sourceSort));
		}
		pageList = channelsClassService.findPageByCriteria(detachedCriteria, 1, 0);
		for (ChannelsInfo ac : (List<ChannelsInfo>) pageList.getItems()) {
			changeSort(sourceCate, sourceSort, ac.getInfoId(), ac.getSort());
		}
		return SUCCESS;
	}
	public String change() {
		changeSort(sourceCate, sourceSort, targetCate, targetSort);
		return SUCCESS;
	}
	private int[] changeSort(String sourceCate, int sourceSort,
			String targetCate, int targetSort) {
		if (sourceCate == null || sourceSort == 0 || targetCate == null
				|| targetSort == 0)
			return new int[0];
		String[] sql = new String[2];
		sql[0] = "update channels_info set sort=" + targetSort
				+ " where info_id='" + sourceCate + "'";
		sql[1] = "update channels_info set sort=" + sourceSort
				+ " where info_id='" + targetCate + "'";
		return channelsClassService.batchUpdate(sql);
	}
	

}
