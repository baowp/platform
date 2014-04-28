package com.abbcc.soa.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;

import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcCategory;
import com.abbcc.models.AbcLayout;
import com.abbcc.models.AbcLayoutmodule;
import com.abbcc.models.AbcLaytheme;
import com.abbcc.models.AbcProduct;
import com.abbcc.models.AbcUser;
import com.abbcc.service.CategoryService;
import com.abbcc.service.LayoutService;
import com.abbcc.service.LaythemeService;
import com.abbcc.service.ProductService;
import com.abbcc.service.UserService;
import com.abbcc.soa.service.UserRelevance;
import com.abbcc.soa.service.dto.LayoutDto;
import com.abbcc.soa.service.dto.LayoutmoduleDto;
import com.abbcc.util.ObjectUtil;

@WebService(endpointInterface = "com.abbcc.soa.service.UserRelevance")
public class UserRelevanceImpl implements UserRelevance {

	@Autowired
	private UserService userService;
	@Autowired
	private LayoutService layoutService;
	@Autowired
	private LaythemeService laythemeService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductService productService;

	/**
	 * 用户不存在，返回 null, layout 不存在，返回空的实体 LayoutDto
	 */
	@Override
	public LayoutDto relevanceLayout(AbcUser user) {
		String enterPriseId = null;
		List<AbcLayout> list = null;
		LayoutDto ld = new LayoutDto();
		AbcUser currentUser = userService.findByUsernamePassword(
				user.getUsername(), user.getPassword());
		if (currentUser != null) {
			enterPriseId = currentUser.getEnterpriseId();
			// 布局
			AbcLayout example = new AbcLayout();
			example.setEnterpriseId(enterPriseId);
			example.setState(CommonConst.STATENORMAL);
			list = layoutService.findByExample(example);
			if (!list.isEmpty()) {
				AbcLayout layout = list.get(0);
				ObjectUtil.extend(ld, layout);
				List<AbcLayoutmodule> layoutmoduleList = layout
						.getLayoutmoduleList();
				List<LayoutmoduleDto> moduleList = new ArrayList<LayoutmoduleDto>();
				for (AbcLayoutmodule lay : layoutmoduleList) {
					LayoutmoduleDto ldDto = new LayoutmoduleDto();
					lay.setLayoutmoduleId(null);
					ObjectUtil.extend(ldDto, lay);
					ldDto.setBlock(lay.getBlock().name());
					moduleList.add(ldDto);
				}
				ld.setLayoutmoduleList(moduleList);
			}
				return ld;
		}
		return null;
	}

	@Override
	public List<AbcLaytheme> relevanceTheme(String layoutId) {
		List<AbcLaytheme> list = null;
		AbcLaytheme example = new AbcLaytheme();
		example.setLayoutId(layoutId);
		list = laythemeService.findByExample(example);
		for (AbcLaytheme theme : list) {
			theme.setLaythemeId(null);
		}
		return list;
	}

	@Override
	public List<AbcCategory> relevanceCategory(AbcUser user) {
		List<AbcCategory> categoryList = new ArrayList<AbcCategory>();
		if (isEnterprise(user)) {
			String enterpriseId = user.getEnterpriseId();
			categoryList = pieceCategoryList(enterpriseId);	
		} else {
			return null;
		}
		
		return categoryList;
	}

	@Override
	public List<AbcProduct> relevanceProduct(AbcUser user) {
		List<AbcProduct> productList = new ArrayList<AbcProduct>();
		if (isEnterprise(user)) {
			String enterpriseId = user.getEnterpriseId();
			AbcProduct product = new AbcProduct();
			product.setEnterpriseId(enterpriseId);
			product.setState(CommonConst.STATENORMAL);
			productList = productService.findByExample(product);
		} else {
			return null;
		}
		
		return productList;
	}
	
	/**
	 * 查找对应企业产品类别
	 * @param enterprieseId 企业标识
	 */
	private List<AbcCategory> pieceCategoryList(String enterprieseId) {
		AbcCategory example = new AbcCategory();
		example.setEnterpriseId(enterprieseId);
		example.setType(CommonConst.CATEGORYTYPEPRODUCT);
		example.setState(CommonConst.STATENORMAL);
		DetachedCriteria dc = DetachedCriteria.forClass(AbcCategory.class);
		dc.add(Example.create(example));
		List<AbcCategory> categoryList = categoryService.findAllByCriteria(dc);
		return categoryList;
	}

	/**
	 * 判断用户是否包含企业标识，没有就判断用户密码信息, 赋值企业标识
	 * 
	 * @param user
	 * @return true ok, false 用户信息不存在
	 */
	private boolean isEnterprise(AbcUser user) {
		if (user.getEnterpriseId() == null) {
			AbcUser currentUser = userService.findByUsernamePassword(
					user.getUsername(), user.getPassword());
			if (currentUser != null) {
				user.setEnterpriseId(currentUser.getEnterpriseId());
				return true;
			}
		} else {
			return true;
		}
		return false;
	}

}
