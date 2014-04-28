package com.abbcc.springrest.controller.user.site.piece;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.abbcc.common.CommonConst;
import com.abbcc.common.PaginationSupport;
import com.abbcc.models.AbcCategory;
import com.abbcc.models.AbcLink;
import com.abbcc.models.AbcProduct;
import com.abbcc.models.AbcUser;
import com.abbcc.models.GroupGaim;

@SuppressWarnings("serial")
public class NarrowContentPiece<T> extends WideContentPiece<AbcUser> {

	private List<AbcCategory> categoryList;

	@RequestMapping("/{username}/fetchCategoryChildren")
	@ResponseBody
	public List<AbcCategory> fetchCategoryChildren(String categoryId) {
		return fetchCategoryChildren(Property.forName("sort").asc(), categoryId);
	}

	@RequestMapping("/{username}/fetchCategoryChildren2")
	@ResponseBody
	// Ajax支持标记
	public List<AbcCategory> fetchCategoryChildren2(String categoryId) {
		return fetchCategoryChildren(Property.forName("sort").desc(),
				categoryId);
	}

	@SuppressWarnings("unchecked")
	private List<AbcCategory> fetchCategoryChildren(Order order,
			String categoryId) {
		AbcCategory example = new AbcCategory();
		example.setBelongId(categoryId);
		example.setState(CommonConst.STATENORMAL);
		example.setIsdisplay(CommonConst.CATEGORYISDISPLAY);
		DetachedCriteria dc = DetachedCriteria.forClass(AbcCategory.class);
		dc.add(Example.create(example)).addOrder(order);

		categoryList = categoryService.findAllByCriteria(dc);
		// ifLeaf
		setupCategory(categoryList.toArray(new AbcCategory[0]));
		return categoryList;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/{username}/narrow_category")
	public String pieceCategoryList() {
		this.before();
		AbcCategory example = new AbcCategory();
		example.setEnterpriseId(entity.getEnterpriseId());
		example.setType(CommonConst.CATEGORYTYPEPRODUCT);
		example.setState(CommonConst.STATENORMAL);
		example.setIsdisplay(CommonConst.CATEGORYISDISPLAY);
		example.setIsroot(CommonConst.CATEGORYISROOT);
		DetachedCriteria dc = DetachedCriteria.forClass(AbcCategory.class);
		dc.add(Example.create(example))
				.addOrder(Property.forName("sort").asc());
		categoryList = categoryService.findAllByCriteria(dc);
		// ifLeaf
		setupCategory(categoryList.toArray(new AbcCategory[0]));
		deposit("categoryList", categoryList);
		return viewName("group/dynamic/piece/narrow/narrow_category");
	}

	@RequestMapping(value = "/{username}/narrow_contact")
	public String pieceContact() {
		this.before();
		return viewName("group/dynamic/piece/narrow/narrow_contact");
	}

	@RequestMapping(value = "/{username}/narrow_search")
	public String pieceSearch() {
		this.before();
		return viewName("group/dynamic/piece/narrow/narrow_search");
	}

	@RequestMapping(value = "/{username}/narrow_link")
	public String pieceLink() {
		this.before();
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcLink.class);
		AbcLink example = new AbcLink();
		example.setEnterpriseId(entity.getEnterpriseId());
		detachedCriteria.add(Example.create(example));
		detachedCriteria.addOrder(Property.forName("lorder").asc());
		List narrowLink = linkService.findAllByCriteria(detachedCriteria);
		deposit("narrowLink", narrowLink);
		return viewName("group/dynamic/piece/narrow/narrow_link");
	}
	
	@RequestMapping(value = "/{username}/narrow_gaim")
	public String pieceGaim() {
		this.before();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(GroupGaim.class);
		GroupGaim example = new GroupGaim();
		example.setEnterpriseId(entity.getEnterpriseId());
		example.setDisplay("1");
		detachedCriteria.add(Example.create(example));
		detachedCriteria.addOrder(Property.forName("sort").asc());
		List<GroupGaim> narrowGaim = gaimService.findAllByCriteria(detachedCriteria);
		deposit("narrowGaim", narrowGaim);
		return viewName("group/dynamic/piece/narrow/narrow_gaim");
	}

	@RequestMapping(value = "/{username}/narrow_category_news")
	public String pieceCategoryNews() {
		if (before()) {
			AbcCategory example = new AbcCategory();
			example.setEnterpriseId(entity.getEnterpriseId());
			example.setType(CommonConst.CATEGORYTYPENEWS);
			example.setState(CommonConst.STATENORMAL);
			example.setIsdisplay(CommonConst.CATEGORYISDISPLAY);
			example.setIsroot(CommonConst.CATEGORYISROOT);
			DetachedCriteria dc = DetachedCriteria.forClass(AbcCategory.class);
			dc.add(Example.create(example)).addOrder(
					Property.forName("sort").desc());

			categoryList = categoryService.findAllByCriteria(dc);

			// ifLeaf
			setupCategory(categoryList.toArray(new AbcCategory[0]));

			deposit("newsRoots", categoryList);
		}
		return viewName("group/dynamic/piece/narrow/narrow_category_news");
	}

	@RequestMapping(value = "/{username}/narrow_ads_products")
	public String pieceSideAdsProducts( ) {
		this.before();
	     int pageSize = 4;
	DetachedCriteria detachedCriteria = DetachedCriteria
			.forClass(AbcProduct.class);
	AbcProduct example = new AbcProduct();
	example.setEnterpriseId(entity.getEnterpriseId());
	example.setState(CommonConst.STATENORMAL);
	example.setIsdisplay(CommonConst.PRODUCTDISPLAY);
	example.setAds("1");
	detachedCriteria.add(Example.create(example));
	Date date = new Date();
	detachedCriteria.add(Property.forName("publishTime").lt(date)).add(
			Restrictions.or(Property.forName("unpublishTime").isNull(),
					Property.forName("unpublishTime").gt(date)));
	PaginationSupport sideAdsproducts = productService.findPageByCriteria(
			detachedCriteria,pageSize, 0,
			CriteriaSpecification.ROOT_ENTITY);
	mainPic((AbcProduct[]) sideAdsproducts.getItems()
			.toArray(new AbcProduct[0]));
	deposit("sideAdsProducts", sideAdsproducts);
	return viewName("group/dynamic/piece/narrow/narrow_ads_products");
}

	@SuppressWarnings("unchecked")
	protected void setupCategory(AbcCategory... categories) {
		for (AbcCategory cate : categories) {
			String hql = "select count(*) from AbcCategory where belongId=? and state=? and isdisplay=?";
			List<Long> list = (List<Long>) categoryService.find(hql,
					cate.getCategoryId(), CommonConst.STATENORMAL,
					CommonConst.CATEGORYISDISPLAY);
			int count = list.get(0).intValue();
			cate.setIfLeaf(count == 0);
		}
	}
}
