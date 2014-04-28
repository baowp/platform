package com.abbcc.springrest.controller.user.site.piece;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abbcc.common.CommonConst;
import com.abbcc.common.PaginationSupport;
import com.abbcc.models.AbcAlbum;
import com.abbcc.models.AbcAttachment;
import com.abbcc.models.AbcCertificate;
import com.abbcc.models.AbcEnterpcontact;
import com.abbcc.models.AbcJob;
import com.abbcc.models.AbcNews;
import com.abbcc.models.AbcProduct;
import com.abbcc.models.AbcSupply;
import com.abbcc.models.AbcUser;
import com.abbcc.util.StringUtil;
import com.abbcc.util.constant.AlbumType;
import com.abbcc.util.constant.ModelType;
import com.abbcc.util.constant.ProductType;

@SuppressWarnings("serial")
public class WideContentPiece<T> extends HeadBelowPiece<AbcUser> {

	private String productName;
	private String categoryId;
	private String newsCategory;
	protected String keywords;
	protected String priceStart;
	protected String priceEnd;

	@RequestMapping(value = "/{username}/wide_products")
	public String pieceProducts() {
		this.before();
		fetchProducts(pageSize, startIndex);
		return viewName("group/dynamic/piece/wide/wide_products");
	}

	@RequestMapping(value = "/{username}/wide_supplies")
	public String pieceSupplies() {
		this.before();
		fetchSupplies(pageSize, startIndex);
		return viewName("group/dynamic/piece/wide/wide_supplies");
	}

	@RequestMapping(value = "/{username}/wide_album")
	public String pieceAlbumList() {
		this.before();
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcAlbum.class);
		detachedCriteria.add(Restrictions.ne("blongType", AlbumType.BP.name()));
		detachedCriteria.add(Restrictions.and(
				Restrictions.eq("belongId", entity.getEnterpriseId()),
				Restrictions.ne("state", CommonConst.STATEDEL)));
		pageList = albumService.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
		// deposit("albumList",
		// albumService.findAllByCriteria(detachedCriteria));
		deposit("albums", pageList);
		return viewName("group/dynamic/piece/wide/wide_album");
	}

	@RequestMapping(value = "/{username}/wide_contacts")
	public String pieceContactList() {
		this.before();
		AbcEnterpcontact example = new AbcEnterpcontact();
		example.setEnterpriseId(entity.getEnterpriseId());
		example.setState(CommonConst.STATENORMAL);
		@SuppressWarnings("unchecked")
		List<AbcEnterpcontact> contactList = enterpcontactService
				.findByExample(example);
		if (contactList.isEmpty()) {
			if (entity.getEmail() == null) {
				userService.refresh(entity);
			}
			AbcEnterpcontact contact = new AbcEnterpcontact();
			contact.setCellphone(entity.getCellphone());
			contact.setEmail(entity.getEmail());
			contact.setFax(entity.getFax());
			contact.setName(entity.getName());
			contact.setPhone(entity.getPhone());
			contact.setPosition(entity.getPosition());
			contact.setUrl(entity.getUrl());
			contact.setSex(entity.getSex());
			contact.setAddress(entity.getAddress());
			contactList.add(contact);
		}
		deposit("contactList", contactList);
		return viewName("group/dynamic/piece/wide/wide_contacts");
	}

	@RequestMapping(value = "/{username}/wide_technic")
	public String pieceTechnic() {
		this.before();
		AbcAttachment att = new AbcAttachment();
		att.setBelongId(entity.getEnterpriseId());
		att.setBelongType(ModelType.AP);
		List<AbcAttachment> attList = attachmentService.findByExample(att);
		if (attList.size() != 0) {
			AbcAttachment attachment = attList.get(0);
			deposit("technic", attachment);
		} else {
			AbcAttachment attachment = new AbcAttachment();
			attachment.setContent("还未填写数据!");
			deposit("technic", attachment);
		}
		return viewName("group/dynamic/piece/wide/wide_technic");
	}

	@RequestMapping(value = "/{username}/wide_message")
	public String pieceMessage() {
		this.before();
		return viewName("group/dynamic/piece/wide/wide_message");
	}

	@RequestMapping(value = "/{username}/wide_news")
	public String pieceNews() {
		this.before();
		if (page == 1)
			topNews();
		commonNews();
		return viewName("group/dynamic/piece/wide/wide_news");
	}

	@RequestMapping(value = "/{username}/wide_recruit")
	public String pieceRecruit() {
		this.before();
		DetachedCriteria dc = DetachedCriteria.forClass(AbcJob.class);
		dc.add(Property.forName("enterpriseId").eq(entity.getEnterpriseId()))
				.add(Property.forName("state").eq(CommonConst.STATENORMAL))
				.add(Property.forName("endTime").gt(new Date()));
		dc.addOrder(Property.forName("addTime").desc());
		PaginationSupport recruit = baseService.findPageByCriteria(dc,
				pageSize, startIndex);
		deposit("recruit", recruit);
		return viewName("group/dynamic/piece/wide/wide_recruit");
	}

	@RequestMapping(value = "/{username}/wide_intro")
	public String pieceIntro() {
		this.before();
		return viewName("group/dynamic/piece/wide/wide_intro");
	}

	@RequestMapping(value = "/{username}/wide_cert")
	public String pieceCert() {
		this.before();
		DetachedCriteria dc = DetachedCriteria.forClass(AbcCertificate.class);
		dc.add(Property.forName("enterpriseId").eq(entity.getEnterpriseId()))
				.add(Property.forName("state").eq(CommonConst.STATENORMAL));
		dc.addOrder(Property.forName("addTime").desc());
		PaginationSupport cert = baseService.findPageByCriteria(dc, pageSize,
				startIndex);
		deposit("cert", cert);
		return viewName("group/dynamic/piece/wide/wide_cert");
	}

	@RequestMapping(value = "/{username}/wide_ads_products")
	public String pieceAdsProducts() {
		this.before();
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
		PaginationSupport adsproducts = productService.findPageByCriteria(
				detachedCriteria, pageSize, 0,
				CriteriaSpecification.ROOT_ENTITY);
		mainPic((AbcProduct[]) adsproducts.getItems()
				.toArray(new AbcProduct[0]));
		deposit("adsProducts", adsproducts);
		return viewName("group/dynamic/piece/wide/wide_ads_products");
	}

	@RequestMapping(value = "/{username}/wide_company_location")
	public String pieceCompanyLocation() {
		this.before();
		return viewName("group/dynamic/piece/wide/wide_company_location");
	}

	@RequestMapping(value = "/{username}/wide_competitive_products")
	public String pieceCompetitiveProducts() {
		this.before();
		PaginationSupport products = specialProducts(ProductType.CP);
		deposit("competitiveProducts", products);
		return viewName("group/dynamic/piece/wide/wide_competitive_products");
	}

	@RequestMapping(value = "/{username}/wide_new_products")
	public String pieceNewProducts() {
		this.before();
		PaginationSupport products = specialProducts(ProductType.NW);
		deposit("newProducts", products);
		return viewName("group/dynamic/piece/wide/wide_new_products");
	}

	public void topNews() {
		AbcNews example = new AbcNews();
		example.setState(CommonConst.STATENORMAL);
		example.setDisplay(CommonConst.NEWSDISPLAY);
		example.setTopnews(CommonConst.NEWSTOPNEWS);
		if (newsCategory != null)
			example.setCategory(newsCategory);
		else
			example.setEnterpriseId(entity.getEnterpriseId());
		DetachedCriteria dc = DetachedCriteria.forClass(AbcNews.class);
		dc.add(Example.create(example))
				.addOrder(Property.forName("sort").desc())
				.addOrder(Property.forName("addTime").desc());
		@SuppressWarnings("unchecked")
		List<AbcNews> list = newsService.findAllByCriteria(dc);
		deposit("topNews", list);
	}

	public void commonNews() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcNews.class);
		if (newsCategory == null)
			detachedCriteria.add(Property.forName("enterpriseId").eq(
					entity.getEnterpriseId()));
		else
			detachedCriteria.add(Property.forName("category").eq(newsCategory));

		detachedCriteria
				.add(Property.forName("state").eq(CommonConst.STATENORMAL))
				.add(Property.forName("display").eq(CommonConst.NEWSDISPLAY))
				.add(Property.forName("topnews").isNull());
		detachedCriteria.addOrder(Property.forName("sort").desc()).addOrder(
				Property.forName("addTime").desc());
		PaginationSupport news = newsService.findPageByCriteria(
				detachedCriteria, pageSize, startIndex);
		deposit("commonNews", news);
	}

	@SuppressWarnings("unchecked")
	private void fetchProducts(int pageSize, int startIndex) {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcProduct.class);
		if (StringUtil.isNotBlank(productName)) {
			detachedCriteria
					.add(Restrictions.or(Restrictions.like("pkey", productName,
							MatchMode.ANYWHERE), Restrictions.like("name",
							productName, MatchMode.ANYWHERE)));
		}
		if (categoryId == null)
			detachedCriteria.add(Property.forName("enterpriseId").eq(
					entity.getEnterpriseId()));
		else {
			List<String> categoryIds = categoryService
					.cascadeVisibleIds(categoryId);
			detachedCriteria.add(Property.forName("category").in(categoryIds));
		}
		ifSearch(detachedCriteria);
		detachedCriteria.add(
				Property.forName("state").eq(CommonConst.STATENORMAL)).add(
				Property.forName("isdisplay").eq(CommonConst.PRODUCTDISPLAY));
		Date date = new Date();
		detachedCriteria.add(Property.forName("publishTime").lt(date)).add(
				Restrictions.or(Property.forName("unpublishTime").isNull(),
						Property.forName("unpublishTime").gt(date)));
		detachedCriteria.addOrder(Property.forName("sort").desc());
		PaginationSupport<AbcProduct> products = productService
				.findPageByCriteria(detachedCriteria, pageSize, startIndex,
						CriteriaSpecification.ROOT_ENTITY);
		mainPic((AbcProduct[]) products.getItems().toArray(new AbcProduct[0]));
		deposit("products", products);
	}

	private void ifSearch(DetachedCriteria detachedCriteria) {
		Float price1 = null, price2 = null;
		try {
			if (priceStart != null)
				price1 = Float.parseFloat(priceStart);
		} catch (NumberFormatException ex) {
		}
		try {
			if (priceEnd != null)
				price2 = Float.parseFloat(priceEnd);
		} catch (NumberFormatException ex) {
		}
		if (StringUtil.isNotBlank(keywords))
			detachedCriteria.add(Property.forName("name").like(keywords,
					MatchMode.ANYWHERE));
		if (price1 != null)
			detachedCriteria.add(Property.forName("price").ge(price1));
		if (price2 != null)
			detachedCriteria.add(Property.forName("price").le(price2));
	}

	@SuppressWarnings("unchecked")
	private void fetchSupplies(int pageSize, int startIndex) {
		AbcSupply example = new AbcSupply();
		example.setEnterpriseId(entity.getEnterpriseId());
		example.setState(CommonConst.STATENORMAL);
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(
				AbcSupply.class).add(Example.create(example));
		Date date = new Date();
		detachedCriteria.add(Property.forName("endTime").gt(date)).add(
				Property.forName("startTime").lt(date));
		PaginationSupport<AbcProduct> supplies;
		supplies = supplyService.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
		deposit("supplies", supplies);
	}

	/**
	 * 取产品主图
	 * 
	 * @param abcProducts
	 */
	protected void mainPic(AbcProduct... abcProducts) {
		for (AbcProduct product : abcProducts) {
			AbcAttachment example = new AbcAttachment();
			example.setBelongId(product.getProductId());
			example.setBelongType(ModelType.BG);
			example.setType(CommonConst.ATTACHTYPEPIC);
			example.setFiledesc(CommonConst.ATTACHPICMAIN);
			for (AbcAttachment attach : attachmentService
					.findByExample(example)) {
				product.setMainPic(attach);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private PaginationSupport specialProducts(ProductType pt) {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcProduct.class);
		AbcProduct example = new AbcProduct();
		example.setEnterpriseId(entity.getEnterpriseId());
		example.setState(CommonConst.STATENORMAL);
		example.setIsdisplay(CommonConst.PRODUCTDISPLAY);
		example.setType(pt);
		detachedCriteria.add(Example.create(example));
		Date date = new Date();
		detachedCriteria.add(Property.forName("publishTime").lt(date)).add(
				Restrictions.or(Property.forName("unpublishTime").isNull(),
						Property.forName("unpublishTime").gt(date)));
		PaginationSupport products = productService.findPageByCriteria(
				detachedCriteria, pageSize, 0,
				CriteriaSpecification.ROOT_ENTITY);
		mainPic((AbcProduct[]) products.getItems().toArray(new AbcProduct[0]));
		return products;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getNewsCategory() {
		return newsCategory;
	}

	public void setNewsCategory(String newsCategory) {
		this.newsCategory = newsCategory;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getPriceStart() {
		return priceStart;
	}

	public void setPriceStart(String priceStart) {
		this.priceStart = priceStart;
	}

	public String getPriceEnd() {
		return priceEnd;
	}

	public void setPriceEnd(String priceEnd) {
		this.priceEnd = priceEnd;
	}

}
