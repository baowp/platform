package com.abbcc.soa.service;

import java.util.List;

import javax.jws.WebService;

import com.abbcc.models.AbcCategory;
import com.abbcc.models.AbcLaytheme;
import com.abbcc.models.AbcProduct;
import com.abbcc.models.AbcUser;
import com.abbcc.soa.service.dto.LayoutDto;

@WebService
public interface UserRelevance {

	LayoutDto relevanceLayout(AbcUser user);

	List<AbcLaytheme> relevanceTheme(String layoutId);
	
	List<AbcCategory> relevanceCategory(AbcUser user);
	
	List<AbcProduct> relevanceProduct(AbcUser user);
}
