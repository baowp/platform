package com.abbcc.module.enterprise;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.beans.factory.annotation.Autowired;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.common.PaginationSupport;
import com.abbcc.models.AbcAttachment;
import com.abbcc.models.AbcEnterprise;
import com.abbcc.models.AbcProduct;
import com.abbcc.models.AbcStat;
import com.abbcc.models.AbcSupply;
import com.abbcc.models.AbcTailor;
import com.abbcc.models.AbcUser;
import com.abbcc.service.AttachmentService;
import com.abbcc.service.EnterpriseService;
import com.abbcc.service.ProductService;
import com.abbcc.service.StatService;
import com.abbcc.util.StringUtil;
import com.abbcc.util.constant.ModelType;
import com.abbcc.util.constant.ProductType;
import com.abbcc.util.constant.SupplyType;

public class RankingAction extends BaseAction<AbcEnterprise> {
	@Autowired
	private ProductService productService;
	@Autowired
	private EnterpriseService enterpriseService;

	@Autowired
	private StatService statService;
	@Autowired
	private AttachmentService attachmentService;

	public String supplyId;
	public String type;
	public void product() {
		// 精品cp,新品nw
		getRequest().setAttribute("jpList", prequest("CP"));
		getRequest().setAttribute("xpList", prequest("NW"));
		getRequest().setAttribute("rmList", prequest(""));
	}

	public void ent() {
		// 活跃度，发布产品最多
		getRequest().setAttribute("enthydList", erequest(""));
		getRequest().setAttribute("entcpList", erequest("productfrequency"));
	}

	public PaginationSupport erequest(String type) {
		DetachedCriteria user = DetachedCriteria.forClass(AbcUser.class);
		user.add(Restrictions.eq("domain", domain))
				.add(Restrictions.eq("state", CommonConst.STATENORMAL))
				.setProjection(Projections.property("userId"));
		user.add(Property.forName("userId").eqProperty("ent.userId"));
		DetachedCriteria dc = DetachedCriteria.forClass(AbcEnterprise.class,
				"ent");
		dc.add(Restrictions.isNotNull("name"));
		dc.add(Subqueries.exists(user));
		dc.add(Property.forName("enterpriseId").eqProperty("ent.enterpriseId"));
		dc.setProjection(Property.forName("enterpriseId"));
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(
				AbcStat.class, "ent");
		detachedCriteria.add(Subqueries.exists(dc));
		if (StringUtil.isNotBlank(type))
			detachedCriteria.addOrder(Order.desc(type));
		else
			detachedCriteria.addOrder(Order.desc("loginfrequency")
					.desc("productfrequency").desc("newsfrequency")
					.desc("supplyfrequency").desc("certfrequency")
					.desc("certfrequency"));
		return statService.findPageByCriteria(detachedCriteria, 10, startIndex);
	}

	public PaginationSupport prequest(String type) {
		DetachedCriteria dc = DetachedCriteria.forClass(AbcProduct.class);
		if (StringUtil.isNotBlank(type))
			dc.add(Restrictions.eq("type", ProductType.valueOf(type)));
		dc.add(Restrictions.eq("domain", domain))
				.add(Restrictions.eq("state", CommonConst.STATENORMAL))
				.addOrder(Order.desc("viewsum"))
				.addOrder(Order.desc("addTime"));
		return productService.findPageByCriteria(dc, 10, startIndex,
				CriteriaSpecification.ROOT_ENTITY);

	}

	public void proBroadcast() {
		AbcProduct ap = new AbcProduct();
		ap.setBroadcast("01");
		ap.setDomain(domain);
		ap.setState(CommonConst.STATENORMAL);
		DetachedCriteria dc = DetachedCriteria.forClass(AbcProduct.class);
		dc.add(Example.create(ap)).addOrder(Order.desc("viewsum"));
		pageList = baseService.findPageByCriteria(dc, 5, startIndex,
				CriteriaSpecification.ROOT_ENTITY);
		mainPic((AbcProduct[]) pageList.getItems().toArray(new AbcProduct[0]));
		deposit("proList", pageList);
	}

	public void supply() {
		AbcTailor at = new AbcTailor();
		at.setEnterpriseId(getCurrentUser().getEnterpriseId());
		at.setType("BU");
		List<AbcTailor> tailor = baseService.findByExample(at);

		DetachedCriteria dc = DetachedCriteria.forClass(AbcSupply.class);
		dc.add(Restrictions.eq("type", SupplyType.valueOf("BU")));
		if (!tailor.isEmpty()) {
			dc.add(Restrictions.like("title", tailor.get(0).getContent(),
					MatchMode.ANYWHERE));
		}
		dc.add(Restrictions.eq("domain", domain));
		dc.add(Restrictions.gt("endTime", new Date()));
		pageList = baseService.findPageByCriteria(dc, 5, startIndex);
		if(!tailor.isEmpty()){
			List<AbcSupply> l = pageList.getItems();
			for(AbcSupply as:l){
				as.setTitle(as.getTitle().replaceFirst(tailor.get(0).getContent(), "<font color=\"red\">"+tailor.get(0).getContent()+"</font>"));
			}
			deposit("supply", l);
		}else
			deposit("supply", pageList);
	}
	public String supplyinfo(){
		AbcSupply as=(AbcSupply)baseService.findById(AbcSupply.class, supplyId);
		deposit("supplyinfo", as);
		AbcProduct ap =(AbcProduct)baseService.findById(AbcProduct.class, as.getProductId());
		deposit("productInfo", ap);
		AbcEnterprise ae = (AbcEnterprise)baseService.findById(AbcEnterprise.class, as.getEnterpriseId());
		deposit("enterpriseInfo", ae);
		AbcUser au=(AbcUser)baseService.findById(AbcUser.class, ae.getUserId());
		deposit("userInfo" ,au);
		return "showSupplyinfo";
	}
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

}
