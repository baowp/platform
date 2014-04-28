/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "CertificateManageAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-1-10           yixiugg                      initial
**/

package com.abbcc.module.entManage;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcCertificate;
import com.abbcc.service.CertificateService;
import com.abbcc.util.ObjectUtil;
import com.abbcc.util.StringUtil;

/**
 **CertificateManageAction.java
 **/
@SuppressWarnings("serial")
public class CertificateManageAction extends BaseAction<AbcCertificate>{
	private String certState=CommonConst.STATEINIT;
	private CertificateService certificateService;
	private String[] certIds;
	private String searchName;
	private String entName;
	private String entId;
	private String org;
	public String getSearchName() {
		return searchName;
	}
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	public String getEntId() {
		return entId;
	}
	public void setEntId(String entId) {
		this.entId = entId;
	}
	/**
	 * 查看要审核的证书
	 * @return
	 */
	public String viewAudit(){
		DetachedCriteria detachedCriteria = DetachedCriteria
		.forClass(AbcCertificate.class);
		if (!getCurrentAdmin().getUsername().equals("admin")) {
			detachedCriteria.add(Restrictions.eq("domain",getCurrentAdmin().getDomain()));
		}
			detachedCriteria.add(Restrictions.eq("state", certState));
		detachedCriteria.addOrder(Order.desc("addTime"));
		if(StringUtil.isNotBlank(entId))
			detachedCriteria.add(Restrictions.eq("enterpriseId", entId));
		this.startIndex = (this.page - 1) * pageSize;
		this.pageList = certificateService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		if (pageList.getTotalCount() == 0)
			result = CommonConst.NORESULT;
		entId="";
		return INPUT;
	}
	/**
	 * 审核证书
	 * @return
	 */
	public String editAudit(){
		for(String certId:certIds){
			String[]certIdAndState = StringUtil.splitBySemicolon(certId);
			AbcCertificate cert = certificateService.findById(certIdAndState[0]);
			cert.setState(certIdAndState[1]);
			certificateService.saveOrUpdate(cert);
			this.savaAdminLog("证书审核",cert.getName(), CommonConst.LOGSAVE);
		}
		result="审核成功！";
		return VIEW;
	}
	
	/**
	 * 查询证书
	 * @return
	 */
	public String search(){
		DetachedCriteria detachedCriteria = DetachedCriteria
		.forClass(AbcCertificate.class);
		if (!getCurrentAdmin().getUsername().equals("admin")) {
			detachedCriteria.add(Restrictions.eq("domain",getCurrentAdmin().getDomain()));
		}
			detachedCriteria.add(Restrictions.eq("state", CommonConst.STATENORMAL));
			if(StringUtil.isNotBlank(entId))
				detachedCriteria.add(Restrictions.eq("enterpriseId", entId));
			if(StringUtil.isNotBlank(org))
				detachedCriteria.add(Restrictions.like("organize", org,MatchMode.ANYWHERE));
			if(StringUtil.isNotBlank(searchName))
				detachedCriteria.add(Restrictions.like("name", searchName,MatchMode.ANYWHERE));
			this.startIndex = (this.page - 1) * pageSize;
			this.pageList = certificateService.findPageByCriteria(detachedCriteria,
					pageSize, startIndex);
			if (pageList.getTotalCount() == 0)
				result = CommonConst.NORESULT;
			entId="";
		return LIST;
	}
	public String getCertState() {
		return certState;
	}
	public void setCertState(String certState) {
		this.certState = certState;
	}
	public CertificateService getCertificateService() {
		return certificateService;
	}
	public void setCertificateService(CertificateService certificateService) {
		this.certificateService = certificateService;
	}
	public String[] getCertIds() {
		return certIds;
	}
	public void setCertIds(String[] certIds) {
		this.certIds = certIds;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public String getEntName() {
		return entName;
	}
	public void setEntName(String entName) {
		this.entName = entName;
	}

}

