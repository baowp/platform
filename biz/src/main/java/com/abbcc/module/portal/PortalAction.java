/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "PortalAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-2-26           baowp                      initial
 */

package com.abbcc.module.portal;

import java.util.ArrayList;
import java.util.List;

import com.abbcc.common.CommonConst;
import com.abbcc.common.PaginationSupport;
import com.abbcc.models.AbcEnterprise;
import com.abbcc.models.AbcProduct;
import com.abbcc.models.AbcSupply;
import com.abbcc.models.AbcSyscode;
import com.abbcc.models.AbcUser;

@SuppressWarnings("serial")
public class PortalAction extends PortalBaseAction<AbcEnterprise> {
	
	@SuppressWarnings("unchecked")
	public String index() {
		//fetch industry
		AbcSyscode syscode=new AbcSyscode();
		syscode.setType(CommonConst.SYSCODETYPEINDUSTY);
		syscode.setState(CommonConst.STATENORMAL);
		List<AbcSyscode> syscodeList = syscodeService.findByExample(syscode);
		getRequest().setAttribute("syscodeList", syscodeList);
		
		//products,supplies,news
		enterpriseItems = enterpriseService.getEnterprisesByIndustry(entity.getIndustry(), pageSize, startIndex);
		List<PaginationSupport> productContainer = new ArrayList<PaginationSupport>();
		List<PaginationSupport> supplyContainer = new ArrayList<PaginationSupport>();
		List<PaginationSupport> newsContainer = new ArrayList<PaginationSupport>();
		for(AbcEnterprise enterprise:(List<AbcEnterprise>)enterpriseItems.getItems()){
			AbcUser user = userService.findById(enterprise.getUserId());
			
			PaginationSupport products = productService.getPublished(enterprise, pageSize, startIndex);
			for (AbcProduct product : (List<AbcProduct>) products.getItems())
				product.setUsername(user.getUsername());
			productContainer.add(products);
			
			PaginationSupport supplies = supplyService.getPublished(enterprise, pageSize, startIndex);
			for (AbcSupply supply : (List<AbcSupply>) supplies.getItems())
				supply.setUsername(user.getUsername());
			supplyContainer.add(supplies);
			
			newsContainer.add(newsService.getNewsByEnterprise(enterprise,pageSize, startIndex));
		}
		getRequest().setAttribute("products", process(productContainer));
		getRequest().setAttribute("supplies", process(supplyContainer));
		getRequest().setAttribute("news", process(newsContainer));
		return "index";
	}
	
	@SuppressWarnings("unchecked")
	private List process(List<PaginationSupport> list) {
		List result = new ArrayList();
		for (PaginationSupport p : list) {
			result.addAll(p.getItems());
		}
		return result;
	}
}
