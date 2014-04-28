/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "PaylogManageAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-26           yixiugg                      initial
 **/

package com.abbcc.module.pay;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.jdbc.core.RowMapper;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.common.ConfConst;
import com.abbcc.common.PageModel;
import com.abbcc.common.PaginationSupport;
import com.abbcc.models.AbcPaylog;
import com.abbcc.models.AbcPayuser;
import com.abbcc.models.AbcUser;
import com.abbcc.service.PaylogService;
import com.abbcc.service.PayuserService;
import com.abbcc.service.UserService;
import com.abbcc.util.DateUtil;
import com.abbcc.util.FileUtil;
import com.abbcc.util.StringUtil;

/**
 * *PaylogManageAction.java
 */
@SuppressWarnings("serial")
public class PaylogManageAction extends BaseAction<AbcPaylog> {
	private List<AbcPaylog> payLogs;
	private AbcUser user;
	private PaylogService paylogService;
	private UserService userService;
	private Map bankMaps;
	private String LISTUSERPAYLOG = "listUserPaylog";
	private String VIEWSENDPAYENDREMIND = "viewSendPayendRemind";
	private String PAYENDLIST = "payendList";
	private String payendType;
	private Date payendStart;
	private Date payendEnd;
	private PayuserService payuserService;
	public String username;
	public String pageType;
	public int pageSize = 20;
	private PageModel pm;
	private List sublist;

	public PageModel getPm() {
		return pm;
	}

	public void setPm(PageModel pm) {
		this.pm = pm;
	}

	public List getSublist() {
		return sublist;
	}

	public void setSublist(List sublist) {
		this.sublist = sublist;
	}

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	public void setPayuserService(PayuserService payuserService) {
		this.payuserService = payuserService;
	}

	public Map getBankMaps() {
		return bankMaps;
	}

	public void setBankMaps(Map bankMaps) {
		this.bankMaps = bankMaps;
	}

	public AbcUser getUser() {
		return user;
	}

	public void setUser(AbcUser user) {
		this.user = user;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String view() {
		payLogs = paylogService.getUserPaylogs(entity.getPayuserId());
		if(payLogs.size()>0)
		user=userService.findById(payLogs.get(0).getUserId());
		if (payLogs.size() == 0)
			result = "该用户没有付费记录";
		return LIST;
	}

	public String viewAdd() {
		bankMaps = CommonConst.BANKMAP;
		return INPUT;
	}

	public String add() {
		paylogService.save(entity);
		result = "添加付费记录成功";
		return LISTUSERPAYLOG;
	}

	public String edit() {
		paylogService.saveOrUpdate(entity);
		result = "修改付费记录成功";
		return LISTUSERPAYLOG;
	}

	public String viewPayend() {
	
		String sql ="SELECT * FROM (SELECT ROW_NUMBER() OVER(PARTITION BY user_id ORDER BY paylog_id DESC)n, abc_paylog.*  FROM abc_paylog) where n=1 and state='01'  ORDER BY paylog_id DESC";
		if (StringUtil.isNotBlank(payendType)) {
			if (payendType.equals(CommonConst.PAYENDWEEK)) {
				sql ="SELECT * FROM (SELECT ROW_NUMBER() OVER(PARTITION BY user_id ORDER BY paylog_id DESC)n, abc_paylog.*  FROM abc_paylog) where n=1 and state='01' and end_time>to_date('"+DateUtil.formatDate(new Date())+"','yyyy-mm-dd hh24:mi:ss')  and end_time<to_date('"+DateUtil.getDateOfDateTime(new Date(),7)+"','yyyy-mm-dd hh24:mi:ss') ORDER BY paylog_id DESC";
			} else if (payendType.equals(CommonConst.PAYENDMONTH)) {
				sql ="SELECT * FROM (SELECT ROW_NUMBER() OVER(PARTITION BY user_id ORDER BY paylog_id DESC)n, abc_paylog.*  FROM abc_paylog) where n=1 and state='01' and end_time>to_date('"+DateUtil.formatDate(new Date())+"','yyyy-mm-dd hh24:mi:ss') and end_time<to_date('"+DateUtil.getDateOfDateTime(new Date(),30)+"','yyyy-mm-dd hh24:mi:ss') ORDER BY paylog_id DESC";
			} else if (payendType.equals(CommonConst.PAYEND3MONTHS)) {
				sql ="SELECT * FROM (SELECT ROW_NUMBER() OVER(PARTITION BY user_id ORDER BY paylog_id DESC)n, abc_paylog.*  FROM abc_paylog) where n=1 and state='01'  and end_time>to_date('"+DateUtil.formatDate(new Date())+"','yyyy-mm-dd hh24:mi:ss') and end_time<to_date('"+DateUtil.getDateOfDateTime(new Date(),90)+"','yyyy-mm-dd hh24:mi:ss') ORDER BY paylog_id DESC";
			} else if (payendType.equals(CommonConst.PAYENDCUSTOM)) {
				if (payendStart == null) {
					this.addFieldError("payendStart", "请输入开始时间");
					return INPUT;
				}
				if (payendStart == null) {
					this.addFieldError("payendEnd", "请输入结束时间");
					return INPUT;
				}
				if (payendEnd.before(payendStart)) {
					this.addFieldError("payendEnd", "结束时间必须大于开始时间");
					return INPUT;
				}
				sql ="SELECT * FROM (SELECT ROW_NUMBER() OVER(PARTITION BY user_id ORDER BY paylog_id DESC)n, abc_paylog.*  FROM abc_paylog) where n=1 and state='01'  and end_time>to_date('"+DateUtil.formatDate(payendStart)+"','yyyy-mm-dd hh24:mi:ss') and end_time<to_date('"+DateUtil.formatDate(payendEnd)+"','yyyy-mm-dd hh24:mi:ss') ORDER BY paylog_id DESC";
			}else if(payendType.equals(CommonConst.PAYENDEXPRIED)){
				sql ="SELECT * FROM (SELECT ROW_NUMBER() OVER(PARTITION BY user_id ORDER BY paylog_id DESC)n, abc_paylog.*  FROM abc_paylog) where n=1 and state='01'  and end_time<to_date('"+DateUtil.formatDate(new Date())+"','yyyy-mm-dd hh24:mi:ss') ORDER BY paylog_id DESC";
			}
		}
		final List<AbcPaylog> pList = new ArrayList();
		 baseService.query(sql,new RowMapper<AbcPaylog>() {
			@Override
			public AbcPaylog mapRow(ResultSet arg0, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				log.info(arg0.getObject("paylog_id"));
				AbcPaylog ap =new AbcPaylog();
				ap.setPaylogId(arg0.getString("paylog_id"));
				ap.setEndTime(arg0.getDate("end_time"));
				ap.setStartTime(arg0.getDate("start_time"));
				ap.setPayTime(arg0.getDate("pay_time"));
				ap.setUserId(arg0.getString("user_id"));
				ap.setState(arg0.getString("state"));
				ap.setType(arg0.getString("type"));
				ap.setIsExpired(expired(arg0.getDate("end_time")));
				pList.add(ap);
				return null;
			}
		});
		 pm = new PageModel(pList,pageSize);
	     sublist = pm.getObjects(page);
		

		return PAYENDLIST;
	}
	public String delPayend(){
		AbcUser au = userService.findById(entity.getUserId());
		AbcPaylog ap = new AbcPaylog();
		ap.setUserId(entity.getUserId());
		List<AbcPaylog> list = baseService.findByExample(ap);
		for(AbcPaylog a:list){
			a.setState(CommonConst.STATEINIT);
			baseService.update(a);
		}
		AbcPayuser apu = new AbcPayuser();
		apu.setUserId(au.getUserId());
		List<AbcPayuser> list1 = baseService.findByExample(apu);
		for(AbcPayuser a:list1){
			if(a.getAuditState().equals(CommonConst.STATEINIT))
				baseService.delete(a);
			else{
				a.setAuditState(CommonConst.STATEDEL);
				baseService.saveOrUpdate(a);
			}
		}
		if (au != null) {
			au.setGrade(CommonConst.USERGRADENONE);
			userService.saveOrUpdate(au);
			FileUtil.delAllFile(ConfConst.FILE_UPLOAD_DIR + "vipsite"
					+ CommonConst.SEP + au.getUsername());
		}
		if(StringUtil.isNotBlank(payendType))
			payendType=CommonConst.PAYENDEXPRIED;
		
		return viewPayend();
	}
	private String expired(Date time){
		if(new Date().after(time))
			return "-1";
		if(time.after(new Date())&&DateUtil.strToDate(DateUtil.getDateOfDateTime(new Date(),7)).after(time))
			return "7";
		if(time.after(new Date())&&DateUtil.strToDate(DateUtil.getDateOfDateTime(new Date(),30)).after(time))
			return "30";
		if(time.after(new Date())&&DateUtil.strToDate(DateUtil.getDateOfDateTime(new Date(),90)).after(time))
			return "90";
		return "0";
	}

	public List<AbcPaylog> getPayLogs() {
		return payLogs;
	}

	public void setPayLogs(List<AbcPaylog> payLogs) {
		this.payLogs = payLogs;
	}

	public PaylogService getPaylogService() {
		return paylogService;
	}

	public void setPaylogService(PaylogService paylogService) {
		this.paylogService = paylogService;
	}

	public String getPayendType() {
		return payendType;
	}

	public void setPayendType(String payendType) {
		this.payendType = payendType;
	}

	public Date getPayendStart() {
		return payendStart;
	}

	public void setPayendStart(Date payendStart) {
		this.payendStart = payendStart;
	}

	public Date getPayendEnd() {
		return payendEnd;
	}

	public void setPayendEnd(Date payendEnd) {
		this.payendEnd = payendEnd;
	}
}
