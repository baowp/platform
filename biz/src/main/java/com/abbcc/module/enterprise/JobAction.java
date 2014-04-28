package com.abbcc.module.enterprise;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcEnterprise;
import com.abbcc.models.AbcJob;
import com.abbcc.models.AbcUser;
import com.abbcc.module.toolbox.Recycle;
import com.abbcc.service.JobService;
import com.abbcc.service.LogService;
import com.abbcc.util.StringUtil;
import com.abbcc.util.checkKey.CheckKey;
import com.abbcc.util.constant.ModelType;

public class JobAction extends BaseAction<AbcJob> {
	// 添加招聘信息变量
	private JobService jobService;
	private String entId;
	private String duty;
	private String sum;
	private String content;
	private String message;
	private String sDate2;
	private String title;
	private String recruitId;// 接收删除修改传过来的ID
	protected int pageSize = 5;
	public String pageType;
	private LogService logService;
	public String searchType;
	public String searchValue;

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public int getPageSize() {
		return pageSize;
	}

	public String getRecruitId() {
		return recruitId;
	}

	public void setRecruitId(String recruitId) {
		this.recruitId = recruitId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setJobService(JobService jobService) {
		this.jobService = jobService;
	}

	public String getEntId() {
		return entId;
	}

	public void setEntId(String entId) {
		this.entId = entId;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSDate2() {
		return sDate2;
	}

	public void setSDate2(String date2) {
		sDate2 = date2;
	}

	// 显示添加招聘信息页面
	public String showIssuanceRecruit() {

		return "showIssuanceRecruit";

	}

	// 显示招聘信息(通过审核)
	public String recruitManage() {
		AbcEnterprise enterprise = (AbcEnterprise) getSession().getAttribute(
				"abbccEnterprise");
		// 加入分页
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcJob.class);
		if(StringUtil.isNotBlank(entity.getTitle()))
			detachedCriteria.add(Restrictions.like("title", entity.getTitle(), MatchMode.ANYWHERE));
		detachedCriteria.add(Restrictions.eq("state", CommonConst.STATENORMAL));
		detachedCriteria.add(Restrictions.eq("enterpriseId",
				enterprise.getEnterpriseId()));
		detachedCriteria.add(Restrictions.gt("endTime", new Date()));
		detachedCriteria.addOrder(Order.desc("endTime"));
		this.startIndex = (this.page - 1) * pageSize;
		this.pageList = jobService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		getRequest().setAttribute("pageType", "pass");
		return "recruitManage";

	}

	// 显示招聘信息(未通过审核)
	public String search() {
		AbcEnterprise enterprise = (AbcEnterprise) getSession().getAttribute(
				"abbccEnterprise");
		// 加入分页
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcJob.class);
		detachedCriteria.add(Restrictions.eq("state", CommonConst.STATEINIT));
		detachedCriteria.add(Restrictions.eq("enterpriseId",
				enterprise.getEnterpriseId()));
		detachedCriteria.add(Restrictions.gt("endTime", new Date()));
		detachedCriteria.addOrder(Order.desc("endTime"));
		this.startIndex = (this.page - 1) * pageSize;
		this.pageList = jobService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		getRequest().setAttribute("pageType", "search");
		return "returnsearch";
	}

	public String expired() {
		AbcEnterprise enterprise = (AbcEnterprise) getSession().getAttribute(
				"abbccEnterprise");
		// 加入分页
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcJob.class);
		detachedCriteria.add(Restrictions.ne("state", CommonConst.STATEDEL));
		detachedCriteria.add(Restrictions.eq("enterpriseId",
				enterprise.getEnterpriseId()));
		detachedCriteria.add(Restrictions.lt("endTime", new Date()));
		detachedCriteria.addOrder(Order.desc("endTime"));
		this.startIndex = (this.page - 1) * pageSize;
		this.pageList = jobService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		getRequest().setAttribute("pageType", "expired");
		return "recruitManage";
	}

	public String resend() {
		Date d = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		calendar.set(Calendar.MONTH, d.getMonth() + 1);
		entity.setEndTime(calendar.getTime());
		jobService.saveOrUpdate(entity);
		logService.savaLog("招聘", entity.getTitle(), CommonConst.LOGRESEND);
		this.setTempAttachment(entity.getJobId(), ModelType.AS);
		result = StringUtil.encode(CommonConst.EDITSUCCESS);
		return "returnResend";
	}

	// 添加招聘信息
	public String issuanceRecruit() {
		if (jValidate()) {
			return INPUT;
		}
		AbcEnterprise enterprise = (AbcEnterprise) getSession().getAttribute(
				"abbccEnterprise");

		AbcUser user = (AbcUser) getSession().getAttribute(
				CommonConst.SESSIONUSER);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = null;
		try {
			date = sdf.parse(sDate2.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Calendar cal = Calendar.getInstance();
		entity.setAddTime(cal.getTime());
		if (CommonConst.JOBAUDIT)
			entity.setState(CommonConst.STATEINIT);
		else
			entity.setState(CommonConst.STATENORMAL);
		entity.setEndTime(date);
		entity.setAddUser(user.getName());

		jobService.save(entity);
		logService.savaLog("招聘", entity.getTitle(), CommonConst.LOGSAVE);
		this.setTempAttachment(entity.getJobId(), ModelType.AS);
		result = StringUtil.encode(CommonConst.ADDSUCCESS);
		return "issuanceRecruit";

	}

	// 显示修改招聘信息
	public String showUpdateIssuanceRecruit() {
		AbcJob job = jobService.findById(recruitId);
		getRequest().setAttribute("jobRequest", job);
		return "showUpdateIssuanceRecruit";
	}

	// 修改招聘信息
	public String updateIssuanceRecruit() {
		if (jValidate())
			return INPUT;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		java.util.Date date = null;
		try {
			date = sdf.parse(sDate2.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AbcJob job = jobService.findById(entity.getJobId());
		job.setContent(entity.getContent());
		job.setSum(entity.getSum());
		job.setTitle(entity.getTitle());
		job.setDuty(entity.getDuty());
		job.setEndTime(date);
		jobService.saveOrUpdate(job);
		logService.savaLog("招聘", entity.getTitle(), CommonConst.LOGUPDATE);
		result = StringUtil.encode(CommonConst.EDITSUCCESS);
		this.setTempAttachment(entity.getJobId(), ModelType.AS);
		return "issuanceRecruit";
	}

	// 删除招聘信息
	@Recycle(name = "title")
	public String delIssuanceRecruit() {
		jobService.delete(entity);
		logService.savaLog("招聘", entity.getTitle(), CommonConst.LOGDEL);
		if (pageType.equals("search"))
			return "returnSearchAction";
		if (pageType.equals("expired"))
			return "returnexpired";
		result = StringUtil.encode(CommonConst.DELSUCCESS);
		return "delIssuanceRecruit";
	}

	public String showJobContent() {
		AbcJob job = jobService.findById(recruitId);
		getRequest().setAttribute("jobRequest", job);
		return "showJobContent";
	}

	public String searchByType() {
		AbcEnterprise enterprise = (AbcEnterprise) getSession().getAttribute(
				"abbccEnterprise");
		// 加入分页
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcJob.class);
		detachedCriteria.add(Restrictions.eq("enterpriseId",
				enterprise.getEnterpriseId()));
		if (searchType != null) {
			if (searchType.equals("byTitle"))
				detachedCriteria.add(Restrictions.eq("title", searchValue));
			if (searchType.equals("byDuty"))
				detachedCriteria.add(Restrictions.eq("duty", searchValue));
		}
		detachedCriteria.addOrder(Order.desc("endTime"));
		this.startIndex = (this.page - 1) * pageSize;
		this.pageList = jobService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		return "returnsearch";
	}

	public boolean jValidate() {
		if (CommonConst.ISCONTROL) {
			if (getSession().getAttribute("addState").equals(
					CommonConst.STATEINIT)) {
				DetachedCriteria dc = DetachedCriteria.forClass(entity
						.getClass());
				dc.add(Restrictions.eq("enterpriseId", getCurrentUser()
						.getEnterpriseId()))
						.add(Restrictions.eq("domain", domain))
						.setProjection(Projections.count("jobId"));
				int i = jobService.getCountByCriteria(dc);
				if (i >= CommonConst.JOBCOUNT) {
					addFieldError("addState", "对不起，您目前还不是高级会员，招聘的发布数量不能超过"
							+ CommonConst.JOBCOUNT
							+ "条!<a href=\"/user/upgrade/show\">升级后将不受限制</a>");
					return true;
				}
			}
		}
		if (!CheckKey.checkKey(entity.getTitle())) {
			this.addFieldError("title", "存在非法字符！");
			return true;
		}
		if (!CheckKey.checkKey(entity.getDuty())) {
			this.addFieldError("duty", "存在非法字符！");
			return true;
		}
		if (!CheckKey.checkKey(entity.getContent())) {
			this.addFieldError("content", "存在非法字符！");
			return true;
		}
		return false;
	}
}
