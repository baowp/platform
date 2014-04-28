package com.abbcc.module.inquiry;

import java.util.Date;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcEnterprise;
import com.abbcc.models.AbcInquiry;
import com.abbcc.models.AbcProduct;
import com.abbcc.models.AbcUser;
import com.abbcc.util.StringUtil;

public class InquiryAction extends BaseAction<AbcInquiry> {
	public String requestInfo;
	public String allId;
	private String userId;
	private String personName;
	private String entName;
	private String phone;
	private String cellphone;
	private String mainBusiness;
	public String supplyId;

	public String inquiryPage() {
		if (StringUtil.isNotBlank(entity.getProductId())) {
			AbcProduct ap = new AbcProduct();
			baseService.load(ap, entity.getProductId());
			deposit("product", ap);
		}
		deposit("ent", getCurrentEnt());
		return "inquiryPage";
	}

	public String save() {
		if (StringUtil.isBlank(entity.getTitle())) {
			this.addFieldError("title", "标题不能为空！");
			return INPUT;
		}
		entity.setToKnow(requestInfo);
		entity.setState(CommonConst.STATEINIT);
		entity.setAddTime(new Date());
		if (StringUtil.isBlank(entity.getEnterpriseId())) {
			entity.setUserType("02");
		} else {
			entity.setUserType("01");
		}
		baseService.save(entity);
		return JSON;
	}

	public String view() {
		DetachedCriteria dc = DetachedCriteria.forClass(entity.getClass());
		dc.add(Restrictions.eq("recvEnt", getCurrentUser().getEnterpriseId()));
		if (StringUtil.isNotBlank(entity.getTitle())) {
			dc.add(Restrictions.like("title", entity.getTitle(),
					MatchMode.ANYWHERE));
		}
		dc.addOrder(Order.asc("state"));
		dc.addOrder(Order.desc("inquiryId"));
		pageList = baseService.findPageByCriteria(dc, pageSize, startIndex);
		return VIEW;
	}

	public String viewInfo() {
		baseService.load(entity, entity.getInquiryId());
		if (entity.getState().equals(CommonConst.STATEINIT)) {
			entity.setState(CommonConst.STATENORMAL);
			baseService.saveOrUpdate(entity);
		}
		return "viewInfo";
	}

	public String del() {
		// baseService.load(entity,entity.getInquiryId());

		baseService.delete(baseService.findById(entity.getClass(),
				entity.getInquiryId()));
		result = CommonConst.DELSUCCESS;
		return view();
	}

	public String allDel() {
		String[] iId = allId.split(",");
		for (int i = 0; i < iId.length; i++) {
			baseService.delete(baseService.findById(entity.getClass(), iId[i]));
		}
		result = CommonConst.DELSUCCESS;
		return view();
	}

	public String quotePrice() {
		if (entity.getType() != null
				&& entity.getType().equals(CommonConst.INQUIRYTWO)) {
			AbcUser au = getCurrentUser();
			au.setName(getPersonName());
			au.setPhone(getPhone());
			au.setCellphone(getCellphone());
			baseService.saveOrUpdate(au);
			AbcEnterprise ae = new AbcEnterprise();
			baseService.load(ae, getCurrentUser().getEnterpriseId());
			ae.setName(getEntName());
			ae.setMainBusiness(getMainBusiness());
			baseService.saveOrUpdate(ae);
			entity.setAddTime(new Date());
			entity.setUserType(getCurrentUser().getGrade());
			entity.setProductId(supplyId);
			entity.setEnterpriseId(getCurrentEnt().getEnterpriseId());
			baseService.saveOrUpdate(entity);
			this.result="报价发送成功!";
		}
		return JSON;
	}


	public String getPersonName() {
		
		return personName;
	}

	public void setPersonName(String personName) {
		
		this.personName = personName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEntName() {
		return entName;
	}

	public void setEntName(String entName) {
		this.entName = entName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getMainBusiness() {
		return mainBusiness;
	}

	public void setMainBusiness(String mainBusiness) {
		this.mainBusiness = mainBusiness;
	}

	
}
