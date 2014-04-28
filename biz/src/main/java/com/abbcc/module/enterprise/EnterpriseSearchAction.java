/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "EnterpriseSearchAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-1-11           yixiugg                      initial
 **/

package com.abbcc.module.enterprise;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONArray;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.abbcc.action.BaseAction;
import com.abbcc.models.AbcEnterprise;
import com.abbcc.models.AbcJob;
import com.abbcc.service.EnterpriseService;
import com.abbcc.util.StringUtil;

/**
 * *EnterpriseSearchAction.java
 */
@SuppressWarnings("serial")
public class EnterpriseSearchAction extends BaseAction<AbcEnterprise> {
	private EnterpriseService enterpriseService;
	private String searchKey;
	public String term;
	private Set<String> nameSet;
	public Set<String> getNameSet() {
		return nameSet;
	}
	public EnterpriseService getEnterpriseService() {
		return enterpriseService;
	}

	public void setEnterpriseService(EnterpriseService enterpriseService) {
		this.enterpriseService = enterpriseService;
	}
	/**
	 * 根据名字查找企业
	 * @return
	 * @throws Exception 
	 */
	public String byName() throws Exception {
		String[] name={"name"};
		List<AbcEnterprise> ents = enterpriseService.findUserByParamsByPage(name,term, AbcEnterprise.class, page, DEFAULT_PAGESIZE);
		nameSet=new HashSet<String>();
		int i=0;
		for(AbcEnterprise ae:ents){
			if(i>10)
				break;
			if(StringUtil.isNotBlank(ae.getName())){
				nameSet.add(ae.getName());
				i++;
			}
				
			
		}
		return SUCCESS;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

}
