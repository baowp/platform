package com.abbcc.module.enterprise;

import java.util.Date;
import java.util.List;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcAudit;
import com.abbcc.service.BaseService;
import com.abbcc.util.BeansFactory;

public class UpdateByJsonAction extends BaseAction<AbcAudit>{
	public void saveAuditByJson(String className,String jsonObject){
		BaseService baseService = (BaseService) BeansFactory.get("baseService");
		AbcAudit aa = new AbcAudit();
		aa.setClassName(className);
		aa.setEnterpriseId(this.getCurrentUser().getEnterpriseId());
		List<AbcAudit> au =  baseService.findByExample(aa);
		if(au.size()!=0){
			AbcAudit ad = au.get(0);
			ad.setAddTime(new Date());
			ad.setJsonContent(jsonObject);
			ad.setState(CommonConst.STATEINIT);
			baseService.saveOrUpdate(ad);
		}else{
			AbcAudit ad = new AbcAudit();
			ad.setAddTime(new Date());
			ad.setClassName(className);
			ad.setEnterpriseId(getCurrentUser().getEnterpriseId());
			ad.setJsonContent(jsonObject);
			ad.setState(CommonConst.STATEINIT);
			baseService.save(ad);
		}
	}

}
