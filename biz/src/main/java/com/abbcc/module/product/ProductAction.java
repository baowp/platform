/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "ProductAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-21           baowp                      initial
 */

package com.abbcc.module.product;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.abbcc.action.FileUploadAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcAttachment;
import com.abbcc.models.AbcBrand;
import com.abbcc.models.AbcProduct;
import com.abbcc.module.toolbox.Recycle;
import com.abbcc.service.ProductService;
import com.abbcc.util.ObjectUtil;
import com.abbcc.util.StringUtil;
import com.abbcc.util.checkKey.CheckKey;
import com.abbcc.util.constant.ModelType;

@SuppressWarnings("serial")
public class ProductAction extends FileUploadAction<AbcProduct> {
	@Autowired
	private ProductService productService;

	public String product1Id;
	public String product2Id;
	public String cdesc;
	public String photoId;
	public String photo;
	public String[] photo2Id;
	public String[] photo2;
	public String[] updatePic2;
	public final int photo2Count = 4;
	public String sortType;

	public String back;

	public String add() {
		photo2 = new String[photo2Count];
		return ADD;
	}

	public String save() {
		/*
		 * if (pvalidate()) return INPUT;
		 */
		entity.setSort(newSort());
		entity.setViewsum("0");
		entity.setDomain(domain);
		productService.save(this);
		// 设置通过editor上传上来图片的状态
		setUploadState(entity.getProddesc());
		result = CommonConst.ADDSUCCESS;
		savaLog("产品", entity.getName(), CommonConst.LOGSAVE);
		return SUCCESS;
	}

	protected void prepareAttach(AbcAttachment attach) {
		attach.setBelongType(ModelType.BG);
		attach.setBelongId(entity.getProductId());
	}

	public String edit() {
		initAttach();
		return EDIT;
	}

	public String update() {
		/*
		 * if (pvalidate()) return INPUT;
		 */
		productService.update(this);
		result = CommonConst.EDITSUCCESS;
		setUploadState(entity.getProddesc());
		savaLog("产品", entity.getName(), CommonConst.LOGUPDATE);
		return back;
	}

	public void validateUpdate() {
		if (getFieldErrors().size() > 0)
			initAttach();
	}

	private void initAttach() {
		AbcAttachment example = new AbcAttachment();
		example.setBelongId(entity.getProductId());
		example.setBelongType(ModelType.BG);
		example.setType(CommonConst.ATTACHTYPEPIC);
		entity.setAttachList(new ArrayList<AbcAttachment>());
		for (AbcAttachment attach : attachmentService.findByExample(example)) {
			if (CommonConst.ATTACHPICMAIN.equals(attach.getFiledesc())) {
				entity.setMainPic(attach);
			} else {
				entity.getAttachList().add(attach);
			}
		}
		int count = entity.getAttachList().size();
		for (int i = 0; i < photo2Count - count; i++)
			entity.getAttachList().add(null);
	}

	@Recycle
	public String remove() {
		entity.setState(CommonConst.STATEDEL);
		productService.delete(entity);
		result = CommonConst.DELSUCCESS;
		savaLog("产品", entity.getName(), CommonConst.LOGDEL);
		return back;
	}

	public String auditing() {
		AbcProduct example = ObjectUtil.extendz(entity);
		example.setEnterpriseId(getCurrentUser().getEnterpriseId());
		example.setState(CommonConst.STATEINIT);
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(example
				.getClass());

		conditions(example, detachedCriteria);
		detachedCriteria.add(Example.create(example));
		detachedCriteria.addOrder(Property.forName("sort").desc()).addOrder(
				Order.desc("addTime"));
		sortMap(detachedCriteria);
		pageList = productService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex, CriteriaSpecification.ROOT_ENTITY);
		return "auditing";
	}

	public String unapprove() {
		AbcProduct example = ObjectUtil.extendz(entity);
		example.setEnterpriseId(getCurrentUser().getEnterpriseId());
		example.setState(CommonConst.STATEDENY);
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(example
				.getClass());

		conditions(example, detachedCriteria);
		detachedCriteria.add(Example.create(example));
		detachedCriteria.addOrder(Property.forName("sort").desc()).addOrder(
				Order.desc("addTime"));
		sortMap(detachedCriteria);
		pageList = productService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex, CriteriaSpecification.ROOT_ENTITY);
		return "unapprove";
	}

	public String expired() {
		AbcProduct example = ObjectUtil.extendz(entity);
		example.setEnterpriseId(getCurrentUser().getEnterpriseId());
		example.setState(CommonConst.STATENORMAL);
		// example.setPublish("03");
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(example
				.getClass());

		conditions(example, detachedCriteria);
		detachedCriteria.add(Example.create(example));
		detachedCriteria.add(Restrictions.lt("unpublishTime", new Date()));
		detachedCriteria.addOrder(Property.forName("sort").desc()).addOrder(
				Order.desc("addTime"));
		sortMap(detachedCriteria);
		pageList = productService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex, CriteriaSpecification.ROOT_ENTITY);
		return "expired";
	}

	public String published() {
		AbcProduct example = ObjectUtil.extendz(entity);
		example.setEnterpriseId(getCurrentUser().getEnterpriseId());
		example.setState(CommonConst.STATENORMAL);
		example.setPublish("01");
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(example
				.getClass());
		conditions(example, detachedCriteria);
		detachedCriteria.add(Example.create(example));
		detachedCriteria.add(Restrictions.or(
				Restrictions.isNull("unpublishTime"),
				Restrictions.gt("unpublishTime", new Date())));
		detachedCriteria.addOrder(Property.forName("sort").desc()).addOrder(
				Order.desc("addTime"));
		sortMap(detachedCriteria);
		pageList = productService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex, CriteriaSpecification.ROOT_ENTITY);
		return "published";
	}

	public String sortPublished() {
		if (StringUtil.isNotBlank(sortType)) {
			List<Integer> l = getPublishedList(true);
			Integer[] a = new Integer[l.size()];
			for (int i = 0; i < l.size(); i++)
				a[i] = l.get(i);
			if (sortType.equals("1"))
				java.util.Arrays.sort(a);
			if (sortType.equals("-1")) {
				Integer[] n = new Integer[a.length];
				int j = 0;
				for (int i = a.length - 1; i >= 0; i--) {
					n[j] = a[i];
					j++;
				}
				a = n;
			}
			List<AbcProduct> plist = getPublishedList(false);
			for (int i = 0; i < plist.size(); i++) {
				plist.get(i).setSort(a[i]);
			}
			productService.saveOrUpdateAll(plist);

		}
		return published();
	}

	public List getPublishedList(Boolean type) {
		AbcProduct example = ObjectUtil.extendz(entity);
		example.setEnterpriseId(getCurrentUser().getEnterpriseId());
		example.setState(CommonConst.STATENORMAL);
		example.setPublish("01");
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(example
				.getClass());
		conditions(example, detachedCriteria);
		detachedCriteria.add(Example.create(example));
		detachedCriteria.add(Restrictions.or(
				Restrictions.isNull("unpublishTime"),
				Restrictions.gt("unpublishTime", new Date())));
		detachedCriteria.addOrder(Order.desc("sort"));
		if (type)
			detachedCriteria.setProjection(Projections.property("sort"));
		List list = productService.findAllByCriteria(detachedCriteria);
		return list;
	}

	private void conditions(AbcProduct example,
			DetachedCriteria detachedCriteria) {
		if (example.getCategory() != null
				&& example.getCategory().length() == 0)
			example.setCategory(null);
		if (example.getName() != null) {
			String name = example.getName();
			example.setName(null);
			if (name.length() > 0) {
				detachedCriteria.add(Restrictions
						.or(Property.forName("name").like(name,
								MatchMode.ANYWHERE),
								Property.forName("prodtype").like(name,
										MatchMode.ANYWHERE)));
			}
		}
		if (StringUtil.isNotBlank(example.getAds())) {
			if (example.getAds().equals("1")) {
				detachedCriteria.add(Property.forName("ads").eq(
						example.getAds()));
			} else if (example.getAds().equals("0")) {
				detachedCriteria.add(Restrictions.or(Property.forName("ads")
						.isNull(), Property.forName("ads").ne("1")));
			}
		}
		example.setAds(null);
	}

	@SuppressWarnings("unchecked")
	private void sortMap(DetachedCriteria detachedCriteria) {
		detachedCriteria.setProjection(Property.forName("productId"));
		List<String> list = productService.findAllByCriteria(detachedCriteria);
		int count = 1;
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		for (String s : list) {
			map.put(s, count++);
		}
		detachedCriteria.setProjection(null);
		getRequest().setAttribute("sortMap", map);
	}

	@SuppressWarnings("deprecation")
	public String publishAgain() {
		Date d = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		calendar.set(Calendar.MONTH, d.getMonth() + 1);

		entity.setUnpublishTime(calendar.getTime());
		productService.update(entity);
		result = CommonConst.EDITSUCCESS;
		return "showexpired";
	}

	@SuppressWarnings("unchecked")
	public String productInnerList() {
		AbcProduct example = new AbcProduct();
		example.setEnterpriseId(getCurrentUser().getEnterpriseId());
		example.setState(CommonConst.STATENORMAL);
		example.setPublish("01");
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entity
				.getClass());
		detachedCriteria.add(Example.create(example));
		detachedCriteria.add(Restrictions.or(
				Restrictions.isNull("unpublishTime"),
				Restrictions.gt("unpublishTime", new Date())));
		detachedCriteria.addOrder(Property.forName("sort").desc()).addOrder(
				Order.desc("addTime"));
		resultList = productService.findAllByCriteria(detachedCriteria);
		return "innerList";
	}

	public void validateProductInnerList() {
		this.clearFieldErrors();
	}

	public String change() {
		AbcProduct product1 = productService.findById(product1Id);
		AbcProduct product2 = productService.findById(product2Id);
		productService.changeSort(product1, product2);
		return published();
	}

	public String arrange() {
		AbcProduct product1 = productService.findById(product1Id);
		AbcProduct product2 = productService.findById(product2Id);
		Integer min = 0, max = 0;
		Order order = null;
		if (product1.getSort() > product2.getSort()) {
			min = product2.getSort();
			max = product1.getSort();
			order = Property.forName("sort").desc();
		} else if (product1.getSort() < product2.getSort()) {
			min = product1.getSort();
			max = product2.getSort();
			order = Property.forName("sort").asc();
		}
		AbcProduct example = new AbcProduct();
		example.setEnterpriseId(getCurrentUser().getEnterpriseId());
		if (StringUtil.isNotBlank(entity.getCategory()))
			example.setCategory(entity.getCategory());
		if (entity.getType() != null)
			example.setType(entity.getType());
		if (StringUtil.isNotBlank(entity.getName()))
			example.setName(entity.getName());
		if (StringUtil.isNotBlank(entity.getAds()))
			example.setAds(entity.getAds());

		DetachedCriteria dc = DetachedCriteria.forClass(entityClass);
		dc.add(Example.create(example))
				.add(Property.forName("sort").between(min, max))
				.addOrder(order);
		@SuppressWarnings("unchecked")
		List<AbcProduct> list = productService.findAllByCriteria(dc);
		if (!list.isEmpty()) {
			int lastSort = list.get(list.size() - 1).getSort();
			int sort;
			for (AbcProduct product : list) {
				sort = product.getSort();
				product.setSort(lastSort);
				lastSort = sort;
			}
			productService.saveOrUpdateAll(list);
		}
		return published();
	}

	public void display() {
		String hql = "update AbcProduct set isdisplay=? where productId=?";
		productService.bulkUpdate(hql, entity.getIsdisplay(),
				entity.getProductId());
	}

	public void brandList() {
		AbcBrand brand = new AbcBrand();
		brand.setEnterpriseId(getCurrentUser().getEnterpriseId());
		brand.setState(CommonConst.STATENORMAL);
		getRequest().setAttribute("brandList",
				productService.findByExample(brand));
	}

	public void validateBrandList() {
		clearFieldErrors();
	}

	public void detailTitle() {
		DetachedCriteria dc = DetachedCriteria.forClass(entityClass);

	}

	private Integer newSort() {
		DetachedCriteria dc = DetachedCriteria
				.forClass(entity.getClass())
				.add(Property.forName("enterpriseId").eq(
						getCurrentUser().getEnterpriseId()))
				.setProjection(Projections.max("sort"));
		@SuppressWarnings("unchecked")
		List<Integer> list = productService.findAllByCriteria(dc);
		Integer sort = list.get(0);
		if (sort == null)
			sort = 0;
		else
			sort++;
		return sort;
	}

	public String addNumber() {
		if (StringUtil.isNotBlank(entity.getProductId())) {
			entity.setViewsum(String.valueOf(Integer.parseInt(entity
					.getViewsum() == null ? "0" : entity.getViewsum()) + 1));
			productService.saveOrUpdate(entity);
			result = entity.getViewsum();
		}
		return JSON;
	}

	public boolean pvalidate() {
		if (CommonConst.ISCONTROL) {
			if (getSession().getAttribute("addState").equals(
					CommonConst.STATEINIT)) {
				DetachedCriteria dc = DetachedCriteria.forClass(entity
						.getClass());
				dc.add(Restrictions.eq("enterpriseId", getCurrentUser()
						.getEnterpriseId()))
						.add(Restrictions.eq("domain", domain))
						.setProjection(Projections.count("productId"));
				int i = productService.getCountByCriteria(dc);
				if (i >= CommonConst.PRODUCTCOUNT) {
					addFieldError("addState", "对不起，您目前还不是高级会员，产品的发布数量不能超过"
							+ CommonConst.PRODUCTCOUNT
							+ "条!<a href=\"/user/upgrade/show\">升级后将不受限制</a>");
					return true;
				}
			}
		}
		if (!CheckKey.checkKey(entity.getName())) {
			this.addFieldError("name", "存在非法字符！");
			return true;
		}
		if (!CheckKey.checkKey(entity.getPkey())) {
			this.addFieldError("pkey", "存在非法字符！");
			return true;
		}
		if (!CheckKey.checkKey(entity.getContactId())) {
			this.addFieldError("contactId", "存在非法字符！");
			return true;
		}
		if (!CheckKey.checkKey(entity.getProddesc())) {
			this.addFieldError("proddesc", "存在非法字符！");
			return true;
		}
		return false;
	}

	public String isLogin() {
		if (getCurrentUser() != null)
			result = this.getCurrentUser().getName();
		else
			result = "0";
		return JSON;

	}

	public String pathToSml(String path) {
		return StringUtil.pathToSml(path);
	}

	public String pathToShow(String path) {
		return StringUtil.pathToSuf(path, 160);
	}

	public String fleshTime() {
		String hql = "update AbcProduct set publishTime=? where productId=?";
		productService.bulkUpdate(hql, new Date(), entity.getProductId());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		result = sdf.format(new Date());
		return JSON;
	}
}
