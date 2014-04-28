package com.abbcc.module.usersite;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Subqueries;

import com.abbcc.models.AbcEnterprise;
import com.abbcc.models.AbcUser;
import com.abbcc.models.AbcViewlog;
import com.abbcc.service.UserService;
import com.abbcc.service.ViewlogService;
import com.abbcc.util.DateUtil;
import com.abbcc.util.ipUtil.IPSeeker;

/**
 * @author RayStone
 * 
 */
@SuppressWarnings("serial")
public class ViewLogAction extends SiteBaseAction<AbcViewlog> {
	private ViewlogService viewlogService;
	private UserService userService;

	public String showMainPage() {
		return "viewLogMain";
	}

	public List<?> viewMain(Date startDate, Date endDate) {
		AbcUser user = getCurrentUser();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entity
				.getClass());
		detachedCriteria.addOrder(Order.asc("viewTime"));
		AbcViewlog abcViewlog = new AbcViewlog();
		abcViewlog.setBeviewId(user.getEnterpriseId());
		List<?> list = viewlogService.findByExampleByDate(abcViewlog,
				detachedCriteria, startDate, endDate);
		System.out.println("saterDate---------->" + startDate);
		System.out.println("endDate---------->" + endDate);
		System.out.println("size---------->" + list.size());
		return list;
	}

	/**
	 * 查看当天的访问记录
	 * 
	 * @return
	 */
	public String viewToday() {
		Date startDate = DateUtil.getCurrentDateStart();
		Date endDate = DateUtil.getCurrentDateEnd();
		JSONArray json = JSONArray.fromObject(viewMain(startDate, endDate));
		this.result = json.toString();
		return JSON;
	}

	/**
	 * 查看前三天的访问记录
	 * 
	 * @return
	 */
	public String viewThree() {
		Calendar cal = Calendar.getInstance();
		Date startDate = new Date(cal.getTime().getTime()
				- (1000 * 60 * 60 * 24 * 2));
		startDate = DateUtil.getDateStart(startDate);
		Date endDate = DateUtil.getDateEnd(cal.getTime());
		JSONArray json = JSONArray.fromObject(viewMain(startDate, endDate));
		this.result = json.toString();
		return JSON;
	}

	/**
	 * 查看本周的访问量
	 * 
	 * @return
	 */
	public String viewWeek() {
		Calendar cal = Calendar.getInstance();
		@SuppressWarnings("deprecation")
		Date startDate = new Date(cal.getTime().getTime()
				- (1000 * 60 * 60 * 24 * (cal.getTime().getDay() - 1)));
		startDate = DateUtil.getDateStart(startDate);
		Date endDate = DateUtil.getDateEnd(cal.getTime());
		JSONArray json = JSONArray.fromObject(viewMain(startDate, endDate));
		this.result = json.toString();
		return JSON;
	}

	/**
	 * 查看最30天的访问量
	 * 
	 * @return
	 */
	public String viewThirty() {
		Calendar cal = Calendar.getInstance();
		Date startDate = new Date(cal.getTime().getTime()
				- (1000 * 60 * 60 * 24 * 29L));
		startDate = DateUtil.getDateStart(startDate);
		Date endDate = DateUtil.getDateEnd(cal.getTime());
		JSONArray json = JSONArray.fromObject(viewMain(startDate, endDate));
		this.result = json.toString();
		return JSON;
	}

	public String addView() {
		if (entity.getBeviewId() == null || entity.getIp() == null)
			return JSON;
		DetachedCriteria dc = DetachedCriteria.forClass(AbcEnterprise.class)
				.add(Property.forName("userId").eqProperty("user.userId"))
				.add(Property.forName("enterpriseId").eq(entity.getBeviewId()))
				.setProjection(Property.forName("enterpriseId"));

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(
				AbcUser.class, "user").add(Subqueries.exists(dc));
		@SuppressWarnings("unchecked")
		List<AbcUser> list = userService.findAllByCriteria(detachedCriteria);
		if (list.size() > 0) {
			AbcUser user = list.get(0);
			entity.setUserId(user.getUserId());
			entity.setUsername(user.getUsername());
			entity.setLocation(IPSeeker.getInstance()
					.getAddress(entity.getIp()));
			entity.setViewTime(Calendar.getInstance().getTime());
			viewlogService.save(entity);
		}
		return JSON;
	}

	public ViewlogService getViewlogService() {
		return viewlogService;
	}

	public void setViewlogService(ViewlogService viewlogService) {
		this.viewlogService = viewlogService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
