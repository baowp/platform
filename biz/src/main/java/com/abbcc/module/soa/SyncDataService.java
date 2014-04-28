/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "SyncDataService.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-4-24           yixiugg                      initial
 **/

package com.abbcc.module.soa;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abbcc.common.CommonConst;
import com.abbcc.common.PaginationSupport;
import com.abbcc.models.AbcCategory;
import com.abbcc.models.AbcEnterprise;
import com.abbcc.models.AbcJob;
import com.abbcc.models.AbcNews;
import com.abbcc.models.AbcProduct;
import com.abbcc.models.AbcSupply;
import com.abbcc.models.SoaTemplate;
import com.abbcc.models.SoaTemplateCriteria;
import com.abbcc.models.SoaUser;
import com.abbcc.service.BaseService;
import com.abbcc.service.JobService;
import com.abbcc.service.NewsService;
import com.abbcc.service.ProductService;
import com.abbcc.service.SoaTemplateCriteriaService;
import com.abbcc.service.SoaTemplateService;
import com.abbcc.service.SoaUserService;
import com.abbcc.service.SupplyService;
import com.abbcc.util.StringUtil;
import com.abbcc.util.constant.ProductType;
import com.abbcc.util.constant.TemplateDataType;

/**
 * *SyncDataService.java
 */
public class SyncDataService {
	private SoaTemplateService soaTemplateService;
	private SoaUserService soaUserService;
	private SoaTemplateCriteriaService soaTemplateCriteriaService;
	private BaseService baseService;
	private NewsService newsService;
	private SupplyService supplyService;
	private JobService jobService;
	private ProductService productService;

	public SoaTemplateService getSoaTemplateService() {
		return soaTemplateService;
	}

	public void setSoaTemplateService(SoaTemplateService soaTemplateService) {
		this.soaTemplateService = soaTemplateService;
	}

	public SoaUserService getSoaUserService() {
		return soaUserService;
	}

	public void setSoaUserService(SoaUserService soaUserService) {
		this.soaUserService = soaUserService;
	}

	public SoaTemplateCriteriaService getSoaTemplateCriteriaService() {
		return soaTemplateCriteriaService;
	}

	public void setSoaTemplateCriteriaService(
			SoaTemplateCriteriaService soaTemplateCriteriaService) {
		this.soaTemplateCriteriaService = soaTemplateCriteriaService;
	}

	public BaseService getBaseService() {
		return baseService;
	}

	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}

	/**
	 * 得到用户网站的模板
	 * 
	 * @param soaUser
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SoaTemplate> getUsersiteTemplate(SoaUser soaUser) {
		SoaTemplate template = new SoaTemplate();
		template.setUsersiteId(soaUser.getUsersiteId());
		return soaTemplateService.findByExample(template);
	}

	/**
	 * 得到模板的查询条件
	 * 
	 * @param template
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SoaTemplateCriteria> getTemplateCriterias(SoaTemplate template) {
		SoaTemplateCriteria criteria = new SoaTemplateCriteria();
		criteria.setTemplateId(template.getTemplateId());
		return soaTemplateCriteriaService.findByExample(criteria);
	}

	/**
	 * 得到模板的map数据
	 * 
	 * @param template
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map getTemplateMapdata(SoaTemplate template, String enterpriseId) {
		Map dataMap = new HashMap();
		List<SoaTemplateCriteria> criterias = getTemplateCriterias(template);
		for (SoaTemplateCriteria criteria : criterias) {
			String name = criteria.getName();
			TemplateDataType dataType = criteria.getDataType();
			String pageType = criteria.getPageType();
			Integer pageSize = criteria.getPageSize();
			String criteriaContent = criteria.getContent();
			String orderContent = criteria.getOrdercontent();
			DetachedCriteria dc = DetachedCriteria.forClass(dataType
					.DataClass());
			// 产品分类
			if (criteria.getDataType().ordinal() == 3)
				dc
						.add(Restrictions.eq("type",
								CommonConst.CATEGORYTYPEPRODUCT));
			// 新闻分类
			if (criteria.getDataType().ordinal() == 0)
				dc.add(Restrictions.eq("type", CommonConst.CATEGORYTYPENEWS));
			// 判断有这个字段则进行匹配和排序
			if (hasProperty(dataType.DataClass(), "addTime"))
				dc.addOrder(Order.desc("addTime"));
			if (hasProperty(dataType.DataClass(), "enterpriseId"))
				dc.add(Restrictions.eq("enterpriseId", enterpriseId));
			if (hasProperty(dataType.DataClass(), "state"))
				dc.add(Restrictions.eq("state", CommonConst.STATENORMAL));
			String[][] contentArray = parseCriteriaContent(criteriaContent);
			String[][] orderArray = parseCriteriaContent(orderContent);
			setCriteriaContent(contentArray, dc, dataType.DataClass().getName());
			setCriteriaOrder(orderArray, dc);
			// 全部
			if (pageType.equals("0")) {
				List list = baseService.findByCriteria(dc);
				dataMap.put(name, list);
			}
			// 分页
			if (pageType.equals("1")) {
				// 分页，查找所有数据
				PaginationSupport ps;
				if (dataType.DataClass() == AbcProduct.class)
					ps = baseService.findPageByCriteria(dc, pageSize, 0,
							CriteriaSpecification.ROOT_ENTITY);
				else
					ps = baseService.findPageByCriteria(dc, pageSize, 0);
				dataMap.put("pageParamName", name);
				dataMap.put(name, ps);
				dataMap.put("pages", new Integer(ps.getPageCount()));
				for (int i = 0; i < ps.getPageCount(); i++) {
					// 每次前面dc调用后，都需要重新设置dc，避免dc调用后参数被修改
					dc = resetDetachedCriteria(enterpriseId, dataType,
							contentArray);
					if (dataType.DataClass() == AbcProduct.class)
						ps = baseService.findPageByCriteria(dc, pageSize, i
								* pageSize, CriteriaSpecification.ROOT_ENTITY);
					else
						ps = baseService.findPageByCriteria(dc, pageSize, i
								* pageSize);
					dataMap.put("page" + i, ps);
				}
				// 对于产品和新闻等需要按照类型分页的
				// 产品列表
				if (criteria.getDataType() == TemplateDataType.PD) {
					List<AbcCategory> productCategory = getCategorys(
							enterpriseId, CommonConst.CATEGORYTYPEPRODUCT);
					dataMap.put("productCategory", new Integer(productCategory
							.size()));
					dataMap.put("productCategorys", productCategory);
					for (int i = 0; i < productCategory.size(); i++) {
						dc = resetDetachedCriteria(enterpriseId, dataType,
								contentArray);
						AbcCategory category = productCategory.get(i);
						dc.add(Restrictions.eq("category", category
								.getCategoryId()));
						ps = baseService.findPageByCriteria(dc, pageSize, 0,
								CriteriaSpecification.ROOT_ENTITY);
						dataMap.put("categoryPages" + i, ps.getPageCount());
						for (int k = 0; k < ps.getPageCount(); k++) {
							dc = resetDetachedCriteria(enterpriseId, dataType,
									contentArray);
							dc.add(Restrictions.eq("category", category
									.getCategoryId()));
							ps = baseService.findPageByCriteria(dc, pageSize, k
									* pageSize,
									CriteriaSpecification.ROOT_ENTITY);
							dataMap.put("pageProduct_" + i + "_" + k, ps);
						}
					}
				}
				// 新闻列表
				if (criteria.getDataType() == TemplateDataType.XW) {
					List<AbcCategory> newsCategory = getCategorys(enterpriseId,
							CommonConst.CATEGORYTYPENEWS);
					dataMap.put("newsCategory",
							new Integer(newsCategory.size()));
					dataMap.put("newsCategorys", newsCategory);
					for (int i = 0; i < newsCategory.size(); i++) {
						dc = resetDetachedCriteria(enterpriseId, dataType,
								contentArray);
						AbcCategory category = newsCategory.get(i);
						dc.add(Restrictions.eq("category", category
								.getCategoryId()));
						ps = baseService.findPageByCriteria(dc, pageSize, 0);
						dataMap.put("categoryPages" + i, ps.getPageCount());
						for (int k = 0; k < ps.getPageCount(); k++) {
							dc = resetDetachedCriteria(enterpriseId, dataType,
									contentArray);
							dc.add(Restrictions.eq("category", category
									.getCategoryId()));
							ps = baseService.findPageByCriteria(dc, pageSize, k
									* pageSize);
							dataMap.put("pageNews_" + i + "_" + k, ps);
						}
					}
				}
			}
			// 一页
			if (pageType.equals("2")) {
				List list;
				if (dataType.DataClass() == AbcProduct.class)
					list = baseService.findPageByCriteria(dc, pageSize, 0,
							CriteriaSpecification.ROOT_ENTITY).getItems();
				else
					list = baseService.findPageByCriteria(dc, pageSize, 0)
							.getItems();
				dataMap.put(name, list);
			}
			// 单条
			if (pageType.equals("3")) {
				List list;
				if (dataType.DataClass() == AbcProduct.class)
					list = baseService.findPageByCriteria(dc, pageSize, 0,
							CriteriaSpecification.ROOT_ENTITY).getItems();
				else
					list = baseService.findPageByCriteria(dc, pageSize, 0)
							.getItems();
				dataMap.put(name, list.get(0));
			}

			// 判断是否含有产品细节的数据，有的话，查询出所有产品放入map
			if (criteria.getDataType() == TemplateDataType.PT) {
				dataMap.put("hasProductDetail", Boolean.TRUE);
				dataMap.put("productDetailName", criteria.getName());
				AbcProduct p = new AbcProduct();
				p.setEnterpriseId(enterpriseId);
				p.setState(CommonConst.STATENORMAL);
				List<AbcProduct> products = productService.findByExample(p);
				dataMap.put("allProducts", products);
			}
			// 判断是否含有新闻细节的数据，有的话，查询出所有产品放入map
			if (criteria.getDataType() == TemplateDataType.XD) {
				dataMap.put("hasNewsDetail", Boolean.TRUE);
				dataMap.put("newsDetailName", criteria.getName());
				AbcNews p = new AbcNews();
				p.setEnterpriseId(enterpriseId);
				p.setState(CommonConst.STATENORMAL);
				List<AbcNews> news = newsService.findByExample(p);
				dataMap.put("allNews", news);
			}
			// 判断是否含有招聘的数据，有的话，查询出所有招聘放入map
			if (criteria.getDataType() == TemplateDataType.ZPXX) {
				dataMap.put("hasJobDetail", Boolean.TRUE);
				dataMap.put("jobDetailName", criteria.getName());
				AbcJob p = new AbcJob();
				p.setEnterpriseId(enterpriseId);
				p.setState(CommonConst.STATENORMAL);
				List<AbcJob> jobs = jobService.findByExample(p);
				dataMap.put("allJobs", jobs);
			}
			// 判断是否含有供求的数据，有的话，查询出所有供求放入map
			if (criteria.getDataType() == TemplateDataType.GQXX) {
				dataMap.put("hasSupplyDetail", Boolean.TRUE);
				dataMap.put("supplyDetailName", criteria.getName());
				AbcSupply p = new AbcSupply();
				p.setEnterpriseId(enterpriseId);
				p.setState(CommonConst.STATENORMAL);
				List<AbcSupply> news = supplyService.findByExample(p);
				dataMap.put("allSupplys", news);
			}
			// //判断是否含有招聘细节的数据，有的话，查询出所有产品放入map
			// if(criteria.getDataType()==TemplateDataType.XD){
			//	
			// }
		}
		return dataMap;
	}

	/**
	 * 解析条件语句
	 * 
	 * @param criteriaContent
	 * @return
	 */
	public String[][] parseCriteriaContent(String criteriaContent) {
		if (StringUtil.isBlank(criteriaContent))
			return null;
		String[] criterias = criteriaContent.split(",");
		String[][] result = new String[criterias.length][2];
		for (int i = 0; i < criterias.length; i++) {
			String[] s = criterias[i].split("=");
			result[i][0] = s[0];
			result[i][1] = s[1];
		}
		return result;
	}

	/**
	 * 解析排序语句
	 * 
	 * @param orderContent
	 * @return
	 */
	public String[][] parseCriteriaOrder(String orderContent) {
		if (StringUtil.isBlank(orderContent))
			return null;
		String[] criterias = orderContent.split(",");
		String[][] result = new String[criterias.length][2];
		for (int i = 0; i < criterias.length; i++) {
			String[] s = criterias[i].split("=");
			result[i][0] = s[0];
			result[i][1] = s[1];
		}
		return result;
	}

	/**
	 * 设置查询条件
	 * 
	 * @param c
	 * @param dc
	 */
	public void setCriteriaContent(String[][] c, DetachedCriteria dc,
			String classname) {
		if (c != null) {
			for (String[] s : c) {
				if (classname.equalsIgnoreCase("com.abbcc.models.AbcProduct")
						&& s[0].equalsIgnoreCase("type")) {
					if (s[1].equalsIgnoreCase("cp"))
						dc.add(Restrictions.eq(s[0], ProductType.CP));
					if (s[1].equalsIgnoreCase("nw"))
						dc.add(Restrictions.eq(s[0], ProductType.NW));
					if (s[1].equalsIgnoreCase("nm"))
						dc.add(Restrictions.eq(s[0], ProductType.NM));
				} else {
					dc.add(Restrictions.eq(s[0], s[1]));
				}
			}
		}
	}

	/**
	 * 设置排序
	 * 
	 * @param c
	 * @param dc
	 */
	public void setCriteriaOrder(String[][] c, DetachedCriteria dc) {
		if (c != null) {
			for (String[] s : c) {
				if (s[0].equalsIgnoreCase("desc"))
					dc.addOrder(Order.desc(s[1]));
				if (s[0].equalsIgnoreCase("asc"))
					dc.addOrder(Order.asc(s[1]));
			}
		}
	}

	/**
	 * 
	 * @param cls
	 * @param properyName
	 * @return
	 */
	public boolean hasProperty(Class cls, String properyName) {
		try {
			cls.getDeclaredField(properyName);
			return true;
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			return false;
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}

	public static void main(String[] args) {
		SyncDataService d = new SyncDataService();
		d.hasProperty(AbcEnterprise.class, "enterpriseId");
	}

	/**
	 * 得到分类
	 * 
	 * @param type
	 * @return
	 */
	public List<AbcCategory> getCategorys(String enterpriseId, String type) {
		AbcCategory category = new AbcCategory();
		category.setEnterpriseId(enterpriseId);
		category.setType(type);
		category.setState(CommonConst.STATENORMAL);
		return baseService.findByExample(category);
	}

	public NewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	public JobService getJobService() {
		return jobService;
	}

	public void setJobService(JobService jobService) {
		this.jobService = jobService;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	/**
	 * 重新设置，避免因为一次操作前后覆盖
	 * 
	 * @param dc
	 * @param enterpriseId
	 * @param dataType
	 */
	public DetachedCriteria resetDetachedCriteria(String enterpriseId,
			TemplateDataType dataType, String[][] contentArray) {
		DetachedCriteria dc = DetachedCriteria.forClass(dataType.DataClass());
		// 判断有这个字段则进行匹配和排序
		if (hasProperty(dataType.DataClass(), "addTime"))
			dc.addOrder(Order.desc("addTime"));
		if (hasProperty(dataType.DataClass(), "enterpriseId"))
			dc.add(Restrictions.eq("enterpriseId", enterpriseId));
		if (hasProperty(dataType.DataClass(), "state"))
			dc.add(Restrictions.eq("state", CommonConst.STATENORMAL));
		setCriteriaContent(contentArray, dc, dataType.DataClass().getName());
		return dc;
	}

	public SupplyService getSupplyService() {
		return supplyService;
	}

	public void setSupplyService(SupplyService supplyService) {
		this.supplyService = supplyService;
	}
}
