/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "SubsiteAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-1-14           baowp                      initial
 */

package com.abbcc.module.usersite;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.beans.factory.annotation.Autowired;

import com.abbcc.common.CommonConst;
import com.abbcc.common.PaginationSupport;
import com.abbcc.models.AbcAlbum;
import com.abbcc.models.AbcAttachment;
import com.abbcc.models.AbcCategory;
import com.abbcc.models.AbcCertificate;
import com.abbcc.models.AbcEnterpcontact;
import com.abbcc.models.AbcEnterprise;
import com.abbcc.models.AbcJob;
import com.abbcc.models.AbcLink;
import com.abbcc.models.AbcNews;
import com.abbcc.models.AbcProduct;
import com.abbcc.models.AbcSupply;
import com.abbcc.models.AbcSyscode;
import com.abbcc.models.AbcUser;
import com.abbcc.service.AlbumService;
import com.abbcc.service.AttachmentService;
import com.abbcc.service.CategoryService;
import com.abbcc.service.EnterpcontactService;
import com.abbcc.service.EnterpriseService;
import com.abbcc.service.LinkService;
import com.abbcc.service.NewsService;
import com.abbcc.service.ProductService;
import com.abbcc.service.SupplyService;
import com.abbcc.service.SyscodeService;
import com.abbcc.service.UserService;
import com.abbcc.util.ObjectUtil;
import com.abbcc.util.StringUtil;
import com.abbcc.util.constant.AlbumType;
import com.abbcc.util.constant.ModelType;
import com.abbcc.util.constant.ProductType;

@SuppressWarnings("serial")
public class SubsiteAction<T> extends SiteBaseAction<AbcUser> {

	protected UserService userService;
	protected CategoryService categoryService;
	protected ProductService productService;
	protected LinkService linkService;
	protected AttachmentService attachmentService;
	protected SupplyService supplyService;
	protected EnterpcontactService enterpcontactService;
	protected NewsService newsService;
	protected SyscodeService syscodeService;
	protected EnterpriseService enterpriseService;
	@Autowired
	protected AlbumService albumService;

	public List<AbcCategory> categoryList;
	public PaginationSupport products;
	public PaginationSupport supplies;
	public AbcAlbum album;
	public AbcProduct product;
	public AbcSupply supply;
	public AbcNews news;
	public String productName;
	public String categoryId;
	public String newsCategory;
	public String albumCategory;
	public String productId;
	public String supplyId;
	public String newsId;
	public String albumId;
	public String keywords;
	public String priceStart;
	public String priceEnd;
	public String http_host;

	public int totalPage;

	public SubsiteAction() {
		pageSize = 16;
	}

	@SuppressWarnings("unchecked")
	protected boolean beforeAction() {
		if (entity.getUsername() != null) {
			entity.setState(CommonConst.STATENORMAL);
			DetachedCriteria dc = DetachedCriteria
					.forClass(AbcEnterprise.class)
					.add(Property.forName("userId").eqProperty("user.userId"))
					.setProjection(Property.forName("enterpriseId"));

			DetachedCriteria detachedCriteria = DetachedCriteria
					.forClass(AbcUser.class, "user")
					.add(Example.create(entity)).add(Subqueries.exists(dc));
			resultList = userService.findAllByCriteria(detachedCriteria);
			if (resultList.size() > 0) {
				ObjectUtil.extend(entity, resultList.get(0));
				if (entity.getEnterprise().getAddress() != null) {
					String addr = "";
					for (String id : entity.getEnterprise().getAddress()
							.split(",")) {
						AbcSyscode syscode = syscodeService.findById(id);
						if (syscode != null)
							addr += syscode.getName();
					}
					entity.getEnterprise().setAddress(addr);
				}
				if (entity.getEnterprise().getIndustry() != null) {
					entity.getEnterprise().setIndustry(
							syscodeService.findById(
									entity.getEnterprise().getIndustry())
									.getName());
				}
				deposit("user", entity);
				deposit("ent",
						enterpriseService.findById(entity.getEnterpriseId()));
				return true;
			}
		}
		return false;
	}

	public String home() {
		return index();
	}

	public String index() {
		if (beforeAction()) {
			pieceCategoryList();
			fetchProducts(pageSize, startIndex);
			TopProducts();
			topEnterprise();
			return "index";
		} else {
			return "none";
		}
	}

	public String person() {
		if (beforeAction()) {
			pieceCategoryList();
			pieceContactList();
		}
		return result;
	}

	public String message() {
		if (beforeAction()) {
			pieceCategoryList();
		}
		return result;
	}

	public String photo() {
		if (beforeAction()) {
			pieceCategoryList();
		}
		return result;
	}

	public String photo_detail() {
		if (beforeAction()) {
			Map<String, String> albumList = new LinkedHashMap<String, String>();
			DetachedCriteria detachedCriteria = DetachedCriteria
					.forClass(AbcAlbum.class);
			detachedCriteria.add(Restrictions.ne("blongType",
					AlbumType.BP.name()));
			detachedCriteria.add(Restrictions.and(
					Restrictions.eq("belongId", entity.getEnterpriseId()),
					Restrictions.ne("state", CommonConst.STATEDEL)));
			List<AbcAlbum> aList = albumService
					.findAllByCriteria(detachedCriteria);
			for (AbcAlbum aa : aList) {
				albumList.put(aa.getAlbumId(), aa.getName());
			}
			deposit("albumId", albumId);
			deposit("albumList", albumList);
		}
		return result;
	}

	public String enterprise() {
		if (beforeAction()) {
			pieceCategoryList();
		}
		return result;
	}

	public String product() {
		if (beforeAction()) {
			pieceCategoryList();
			fetchProducts(pageSize, startIndex);
			TopProducts();
			topEnterprise();
		}
		return result;
	}

	public String contact() {
		if (beforeAction()) {
			pieceCategoryList();
			pieceContactList();
		}
		return result;
	}

	public String product_detail() {
		if (beforeAction()) {
			// 取具体产品
			product = productService.findById(productId);
			productPic(product);
			deposit("product", product);
			pieceCategoryList();
		}
		return result;
	}

	public String supply() {
		if (beforeAction()) {
		}
		return result;
	}

	public String supply_detail() {
		if (beforeAction()) {
			// 供求信息
			supply = supplyService.findById(supplyId);
			deposit("supply", supply);
		}
		return result;
	}

	public String technic() {
		beforeAction();
		return result;
	}

	public String news() {
		if (beforeAction()) {
			pieceCategoryList();
			commonNews();
		}
		return result;
	}

	public String news_detail() {
		if (beforeAction()) {
			news = newsService.findById(newsId);
			deposit("news", news);
		}
		return result;
	}

	public String recruit() {
		if (beforeAction()) {
			pieceCategoryList();
			pieceRecruit();
		}
		return result;
	}

	public String cert() {
		if (beforeAction()) {
			pieceCategoryList();
			pieceCert();
		}
		return result;
	}

	public String search() {
		if (beforeAction()) {
			Float price1 = null, price2 = null;
			try {
				price1 = Float.parseFloat(priceStart);
			} catch (NumberFormatException ex) {
			}
			try {
				price2 = Float.parseFloat(priceEnd);
			} catch (NumberFormatException ex) {
			}
			DetachedCriteria detachedCriteria = DetachedCriteria
					.forClass(AbcProduct.class);
			if (StringUtil.isNotBlank(keywords))
				detachedCriteria.add(Property.forName("name").like(keywords,
						MatchMode.ANYWHERE));
			if (price1 != null)
				detachedCriteria.add(Property.forName("price").ge(price1));
			if (price2 != null)
				detachedCriteria.add(Property.forName("price").le(price2));
			detachedCriteria.add(Property.forName("enterpriseId").eq(
					entity.getEnterpriseId()));
			detachedCriteria.add(
					Property.forName("state").eq(CommonConst.STATENORMAL)).add(
					Property.forName("isdisplay")
							.eq(CommonConst.PRODUCTDISPLAY));
			Date date = new Date();
			detachedCriteria.add(Property.forName("publishTime").lt(date)).add(
					Restrictions.or(Property.forName("unpublishTime").isNull(),
							Property.forName("unpublishTime").gt(date)));
			products = productService.findPageByCriteria(detachedCriteria,
					pageSize, startIndex, CriteriaSpecification.ROOT_ENTITY);
			mainPic((AbcProduct[]) products.getItems().toArray(
					new AbcProduct[0]));
			deposit("products", products);
		}
		return result;
	}

	public void piecePhotoList() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcAlbum.class);
		detachedCriteria.add(Restrictions.ne("blongType", AlbumType.BP.name()));
		detachedCriteria.add(Restrictions.and(
				Restrictions.eq("belongId", entity.getEnterpriseId()),
				Restrictions.ne("state", CommonConst.STATEDEL)));
		pageList = albumService.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
		deposit("albumList", albumService.findAllByCriteria(detachedCriteria));
		deposit("photos", pageList);
	}

	@SuppressWarnings("unchecked")
	public void pieceCategoryList() {
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
	}

	@SuppressWarnings("unchecked")
	public void pieceNewsCategory() {
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

	public String fetchCategoryChildren() {
		return fetchCategoryChildren(Property.forName("sort").asc());
	}

	public String fetchCategoryChildren2() {
		return fetchCategoryChildren(Property.forName("sort").desc());
	}

	@SuppressWarnings("unchecked")
	private String fetchCategoryChildren(Order order) {
		AbcCategory example = new AbcCategory();
		example.setBelongId(categoryId);
		example.setState(CommonConst.STATENORMAL);
		example.setIsdisplay(CommonConst.CATEGORYISDISPLAY);
		DetachedCriteria dc = DetachedCriteria.forClass(AbcCategory.class);
		dc.add(Example.create(example)).addOrder(order);

		categoryList = categoryService.findAllByCriteria(dc);
		// ifLeaf
		setupCategory(categoryList.toArray(new AbcCategory[0]));
		return JSON;
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

	public void pieceProducts() {
		fetchProducts(pageSize, startIndex);
	}

	public void pieceSupplies() {
		fetchSupplies(pageSize, startIndex);
	}

	private void fetchSupplies(int pageSize, int startIndex) {
		AbcSupply example = new AbcSupply();
		example.setEnterpriseId(entity.getEnterpriseId());
		example.setState(CommonConst.STATENORMAL);
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(
				AbcSupply.class).add(Example.create(example));
		Date date = new Date();
		detachedCriteria.add(Property.forName("endTime").gt(date)).add(
				Property.forName("startTime").lt(date));
		supplies = supplyService.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
		deposit("supplies", supplies);
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

		detachedCriteria.add(
				Property.forName("state").eq(CommonConst.STATENORMAL)).add(
				Property.forName("isdisplay").eq(CommonConst.PRODUCTDISPLAY));
		Date date = new Date();
		detachedCriteria.add(Property.forName("publishTime").lt(date)).add(
				Restrictions.or(Property.forName("unpublishTime").isNull(),
						Property.forName("unpublishTime").gt(date)));
		detachedCriteria.addOrder(Property.forName("sort").desc());
		products = productService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex, CriteriaSpecification.ROOT_ENTITY);
		mainPic((AbcProduct[]) products.getItems().toArray(new AbcProduct[0]));
		deposit("products", products);
	}

	@SuppressWarnings({ "unchecked", "unused" })
	private void TopProducts() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcProduct.class);
		detachedCriteria.add(Restrictions.eq("broadcast", "01"));
		detachedCriteria.add(
				Property.forName("state").eq(CommonConst.STATENORMAL)).add(
				Property.forName("isdisplay").eq(CommonConst.PRODUCTDISPLAY));
		List<AbcProduct> ap = productService
				.findAllByCriteria(detachedCriteria);
		mainPic((AbcProduct[]) ap.toArray(new AbcProduct[0]));
		deposit("topProduct", ap);
	}

	public void pieceCompetitiveProducts() {
		PaginationSupport products = specialProducts(ProductType.CP);
		deposit("competitiveProducts", products);
	}

	public void pieceNewProducts() {
		PaginationSupport products = specialProducts(ProductType.NW);
		deposit("newProducts", products);
	}

	public void pieceAdsProducts() {
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
	}

	public void pieceSideAdsProducts( ) {
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
	}

	public void pieceSideLink() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcLink.class);
		AbcLink example = new AbcLink();
		example.setEnterpriseId(entity.getEnterpriseId());
		detachedCriteria.add(Example.create(example));
		detachedCriteria.addOrder(Property.forName("lorder").asc());
		List sideLink = linkService.findAllByCriteria(detachedCriteria);
		deposit("sideLink", sideLink);
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

	/**
	 * 取产品图
	 * 
	 * @param abcProducts
	 */
	protected void productPic(AbcProduct... abcProducts) {
		for (AbcProduct product : abcProducts) {
			AbcAttachment example = new AbcAttachment();
			example.setBelongId(product.getProductId());
			example.setBelongType(ModelType.BG);
			example.setType(CommonConst.ATTACHTYPEPIC);
			for (AbcAttachment attach : attachmentService
					.findByExample(example)) {
				if (CommonConst.ATTACHPICMAIN.equals(attach.getFiledesc()))
					product.setMainPic(attach);
				else {
					if (product.getAttachList() == null)
						product.setAttachList(new ArrayList<AbcAttachment>());
					product.getAttachList().add(attach);
				}
			}
		}
	}

	public void pieceContactList() {
		AbcEnterpcontact example = new AbcEnterpcontact();
		example.setEnterpriseId(entity.getEnterpriseId());
		example.setState(CommonConst.STATENORMAL);
		@SuppressWarnings("unchecked")
		List<AbcEnterpcontact> contactList = enterpcontactService
				.findByExample(example);
		if (contactList.isEmpty()) {
			if(entity.getEmail() == null) {
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
	}

	public void pieceTechnic() {
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
	}

	public void pieceNews() {
		if (page == 1)
			topNews();
		commonNews();
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

	public void pieceRecruit() {
		DetachedCriteria dc = DetachedCriteria.forClass(AbcJob.class);
		dc.add(Property.forName("enterpriseId").eq(entity.getEnterpriseId()))
				.add(Property.forName("state").eq(CommonConst.STATENORMAL))
				.add(Property.forName("endTime").gt(new Date()));
		dc.addOrder(Property.forName("addTime").desc());
		PaginationSupport recruit = baseService.findPageByCriteria(dc,
				pageSize, startIndex);
		deposit("recruit", recruit);
	}

	public void pieceCert() {
		DetachedCriteria dc = DetachedCriteria.forClass(AbcCertificate.class);
		dc.add(Property.forName("enterpriseId").eq(entity.getEnterpriseId()))
				.add(Property.forName("state").eq(CommonConst.STATENORMAL));
		dc.addOrder(Property.forName("addTime").desc());
		PaginationSupport cert = baseService.findPageByCriteria(dc, pageSize,
				startIndex);
		deposit("cert", cert);
	}

	@SuppressWarnings("unchecked")
	public void topEnterprise() {
		List<AbcEnterprise> newEntList = new ArrayList<AbcEnterprise>();
		DetachedCriteria dc = DetachedCriteria.forClass(AbcEnterprise.class);
		dc.add(Restrictions.eq("broadcast", "01"));
		dc.add(Restrictions.isNotNull("name"));
		List<AbcEnterprise> eList = enterpriseService.findAllByCriteria(dc);
		for (AbcEnterprise ae : eList) {
			ae.setUrl(entUrlByUserId(ae.getUserId()));
			newEntList.add(ae);
		}
		deposit("topEntList", newEntList);
	}

	private String entUrlByUserId(String uId) {
		AbcUser au = userService.findById(uId);
		if (au != null) {
			if (au.getGrade().equals(CommonConst.USERGRADEONE)) {
				return "http://" + au.getUsername() + "." + au.getDomain();
			} else {
				return "http://" + au.getDomain() + "/site/" + au.getUsername();
			}
		}
		return domain;
	}

	public void pagination() {
		pagination(page, totalPage);
	}

	public void pagination(int page, int totalPage) {
		int start = 1, end = totalPage;
		if (totalPage > 10) {
			start = page - 4;
			end = page + 5;
			if (start < 1) {
				start = 1;
				end += 1 - start;
			} else if (end > totalPage) {
				start -= end - totalPage;
				end = totalPage;
			}
		}
		List<Integer> list = new ArrayList<Integer>();
		for (int i = start; i <= end; i++)
			list.add(i);
		deposit("pagination", list);
	}

	protected void deposit(String key, Object value) {
		getRequest().setAttribute(key, value);
	}

	protected Object take(String key) {
		return getRequest().getAttribute(key);
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setAttachmentService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setSupplyService(SupplyService supplyService) {
		this.supplyService = supplyService;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	public void setEnterpcontactService(
			EnterpcontactService enterpcontactService) {
		this.enterpcontactService = enterpcontactService;
	}

	public List<AbcCategory> getCategoryList() {
		return categoryList;
	}

	public void setSyscodeService(SyscodeService syscodeService) {
		this.syscodeService = syscodeService;
	}

	public void setEnterpriseService(EnterpriseService enterpriseService) {
		this.enterpriseService = enterpriseService;
	}

	public String getKeywords() {
		return keywords;
	}

	public String getPriceStart() {
		return priceStart;
	}

	public String getPriceEnd() {
		return priceEnd;
	}

	public void setLinkService(LinkService linkService) {
		this.linkService = linkService;
	}

}
