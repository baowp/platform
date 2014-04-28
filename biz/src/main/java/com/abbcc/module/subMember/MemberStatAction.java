/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "MemberStatAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-30           wangjin                      initial
 */

package com.abbcc.module.subMember;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.helper.JfreeCharthelper;
import com.abbcc.models.AbcEnterprise;
import com.abbcc.models.AbcPaylog;
import com.abbcc.models.AbcStat;
import com.abbcc.models.AbcUser;
import com.abbcc.service.EnterpriseService;
import com.abbcc.service.PaylogService;
import com.abbcc.service.StatService;
import com.abbcc.service.UserService;
import com.abbcc.util.StringUtil;

public class MemberStatAction extends BaseAction<AbcUser> {
	private UserService userService;
	private String showType = "";
	public String pageType;
	public String entName;
	private static final long serialVersionUID = 5752180822913527064L;
	public String pageOrder;
	private JFreeChart chart;
	private StatService statService;
	private EnterpriseService enterpriseService;
	private PaylogService paylogService;

	public void setPaylogService(PaylogService paylogService) {
		this.paylogService = paylogService;
	}

	public void setEnterpriseService(EnterpriseService enterpriseService) {
		this.enterpriseService = enterpriseService;
	}

	public void setStatService(StatService statService) {
		this.statService = statService;
	}

	public JFreeChart getChart() {
		return chart;
	}

	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}

	public String show() {
		AbcUser user = (AbcUser) getSession().getAttribute(
				CommonConst.SESSIONUSER);

		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcUser.class);
		if (pageType == null)
			detachedCriteria.add(Restrictions.eq("enterpriseId",
					user.getEnterpriseId()));
		detachedCriteria.add(Restrictions.eq("type", CommonConst.SUBMEMBER));
		detachedCriteria.add(Restrictions.ne("state", CommonConst.STATEDEL));
		// 查询所有的注册会员
		List userList = userService.findAllByCriteria(detachedCriteria);
		Date date = new Date();
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		detachedCriteria.add(Restrictions.between("addTime", date, new Date()));
		// 查询当天注册的会员
		List thisDayList = userService.findAllByCriteria(detachedCriteria);
		DetachedCriteria detachedCriteria1 = DetachedCriteria
				.forClass(AbcUser.class);
		if (pageType == null)
			detachedCriteria1.add(Restrictions.eq("enterpriseId",
					user.getEnterpriseId()));
		detachedCriteria1.add(Restrictions.eq("type", CommonConst.SUBMEMBER));
		detachedCriteria1.add(Restrictions.ne("state", CommonConst.STATEDEL));
		Date date1 = new Date();
		date1.setDate(1);
		date1.setHours(0);
		date1.setMinutes(0);
		date1.setSeconds(0);
		detachedCriteria1.add(Restrictions
				.between("addTime", date1, new Date()));
		// 查询当月注册的会员
		List thisMonthList = userService.findAllByCriteria(detachedCriteria1);
		getRequest().setAttribute("allCount", userList.size());
		getRequest().setAttribute("dayCount", thisDayList.size());
		getRequest().setAttribute("monthCount", thisMonthList.size());
		return "memberStat";
	}

	public String showDay() {
		AbcUser user = (AbcUser) getSession().getAttribute(
				CommonConst.SESSIONUSER);

		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcUser.class);
		if (pageType == null)
			detachedCriteria.add(Restrictions.eq("enterpriseId",
					user.getEnterpriseId()));
		detachedCriteria.add(Restrictions.eq("type", CommonConst.SUBMEMBER));
		detachedCriteria.add(Restrictions.ne("state", CommonConst.STATEDEL));
		Date date = new Date();
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		detachedCriteria.add(Restrictions.between("addTime", date, new Date()));
		// 查询当天注册的会员
		this.pageList = userService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		return "memberStatShow";
	}

	@SuppressWarnings("deprecation")
	public String showMonth() {
		AbcUser user = (AbcUser) getSession().getAttribute(
				CommonConst.SESSIONUSER);
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcUser.class);
		if (pageType == null)
			detachedCriteria.add(Restrictions.eq("enterpriseId",
					user.getEnterpriseId()));
		detachedCriteria.add(Restrictions.eq("type", CommonConst.SUBMEMBER));
		detachedCriteria.add(Restrictions.ne("state", CommonConst.STATEDEL));
		Date date1 = new Date();
		date1.setDate(1);
		date1.setHours(0);
		date1.setMinutes(0);
		date1.setSeconds(0);
		detachedCriteria
				.add(Restrictions.between("addTime", date1, new Date()));
		// 查询当月注册的会员
		this.pageList = userService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		return "memberStatShow";
	}

	public int getMonthCount(int year, int month) throws ParseException {

		Calendar rightNow1 = Calendar.getInstance();
		rightNow1.set(year, month, 0);
		String strDateStart = year + "-" + month + "-1";
		String strDateEnd = year + "-" + month + "-"
				+ rightNow1.getTime().getDate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date_start = sdf.parse(strDateStart);
		Date date_end = sdf.parse(strDateEnd);
		Calendar cal_start = Calendar.getInstance();
		Calendar cal_end = Calendar.getInstance();
		cal_start.setTime(date_start);
		cal_end.setTime(date_end);

		AbcUser user = (AbcUser) getSession().getAttribute(
				CommonConst.SESSIONUSER);
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcUser.class);
		if (pageType == null)
			detachedCriteria.add(Restrictions.eq("enterpriseId",
					user.getEnterpriseId()));
		detachedCriteria.add(Restrictions.eq("type", CommonConst.SUBMEMBER));
		detachedCriteria.add(Restrictions.ne("state", CommonConst.STATEDEL));

		detachedCriteria.add(Restrictions.between("addTime",
				cal_start.getTime(), cal_end.getTime()));
		// 查询当月注册的会员
		this.pageList = userService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		return pageList.getTotalCount();
	}

	public String jfreechart() throws Exception {
		// 设置数据
		chart = JfreeCharthelper.createBarChart("客户注册量图", "月份", "注册人数",
				getDataSet2());

		return SUCCESS;
	}

	private CategoryDataset getDataSet2() throws ParseException {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		Calendar rightNow = Calendar.getInstance();
		for (int i = 1; i <= 12; i++) {
			dataset.addValue(getMonthCount(rightNow.get(Calendar.YEAR), i), ""
					+ rightNow.get(Calendar.YEAR), "" + i);
			dataset.addValue(getMonthCount(rightNow.get(Calendar.YEAR) - 1, i),
					"" + (rightNow.get(Calendar.YEAR) - 1), "" + i);
		}
		return dataset;

	}

	// 系统后台里用户登陆统计
	public String memberLogin() {
		java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh24:mi:ss");
		// String d = sdf.format(date);
		// String now = sdf.format(new Date());
		String sql = "select sum(frequency) from AbcStat where type='"
				+ CommonConst.STATLOGIN + "'";
		int sum = statService.getStatBySql(sql);
		String todaysql = "select sum(frequency) from AbcStat where type='"
				+ CommonConst.STATLOGIN + "' and lastAddTime > to_date('"
				+ new Date() + "','yyyy-MM-dd HH24:mm:ss')";
		// int todaySum = statService.getStatBySql(todaysql);
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcStat.class);
		if (StringUtil.isNotBlank(entName)) {
			DetachedCriteria ent = DetachedCriteria
					.forClass(AbcEnterprise.class);
			ent.add(Restrictions.like("name", entName, MatchMode.ANYWHERE));
			List<AbcEnterprise> entList = enterpriseService
					.findAllByCriteria(ent);
			if (entList.size() != 0) {
				detachedCriteria.add(Restrictions.eq("enterpriseId", entList
						.get(0).getEnterpriseId()));
			}
		}
		detachedCriteria.add(Restrictions.eq("type", CommonConst.STATLOGIN));
		detachedCriteria.addOrder(Order.desc("frequency"));
		getRequest().setAttribute("sumStat", sum);
		this.pageList = statService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);

		return "loginList";
	}

	// 系统后台里用户发布统计
	public String infoPublish() {
		// String sql =
		// "select sum(frequency) from AbcStat where type='"+CommonConst.STATLOGIN+"'";
		// int sum = statService.getStatBySql(sql);
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcStat.class);
		if (StringUtil.isNotBlank(entName)) {
			DetachedCriteria ent = DetachedCriteria
					.forClass(AbcEnterprise.class);
			ent.add(Restrictions.like("name", entName, MatchMode.ANYWHERE));
			List<AbcEnterprise> entList = enterpriseService
					.findAllByCriteria(ent);
			if (entList.size() != 0) {
				detachedCriteria.add(Restrictions.eq("enterpriseId", entList
						.get(0).getEnterpriseId()));
			}
		}
		detachedCriteria.addOrder(Order.desc("loginfrequency"));
		// getRequest().setAttribute("sumStat", sum);
		this.pageList = statService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);

		return "publishList";
	}

	// 普通会员升级为收费会员统计
	public String upgrade() throws Exception {
		// 设置数据
		chart = JfreeCharthelper.createBarChart("会员升级统计图", "月份", "升级人数",
				getData());

		return SUCCESS;
	}

	private CategoryDataset getData() throws ParseException {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		Calendar rightNow = Calendar.getInstance();
		for (int i = 1; i <= 12; i++) {
			dataset.addValue(
					getMonthUpgradeMember(rightNow.get(Calendar.YEAR), i, 1),
					rightNow.get(Calendar.YEAR) + "经典会员", "" + i);
			dataset.addValue(
					getMonthUpgradeMember(rightNow.get(Calendar.YEAR) - 1, i, 1),
					(rightNow.get(Calendar.YEAR) - 1) + "经典会员", "" + i);
			dataset.addValue(
					getMonthUpgradeMember(rightNow.get(Calendar.YEAR), i, 2),
					rightNow.get(Calendar.YEAR) + "精致会员", "" + i);
			dataset.addValue(
					getMonthUpgradeMember(rightNow.get(Calendar.YEAR) - 1, i, 2),
					(rightNow.get(Calendar.YEAR) - 1) + "精致会员", "" + i);
		}
		return dataset;

	}

	public int getMonthUpgradeMember(int year, int month, int type)
			throws ParseException {

		Calendar rightNow1 = Calendar.getInstance();
		rightNow1.set(year, month, 0);
		String strDateStart = year + "-" + month + "-1";
		String strDateEnd = year + "-" + month + "-"
				+ rightNow1.getTime().getDate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date_start = sdf.parse(strDateStart);
		Date date_end = sdf.parse(strDateEnd);
		Calendar cal_start = Calendar.getInstance();
		Calendar cal_end = Calendar.getInstance();
		cal_start.setTime(date_start);
		cal_end.setTime(date_end);

		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcPaylog.class);
		if (type == 1)
			detachedCriteria.add(Restrictions.eq("type",
					CommonConst.USERGRADEONE));
		if (type == 2)
			detachedCriteria.add(Restrictions.eq("type",
					CommonConst.USERGRADETWO));

		detachedCriteria.add(Restrictions.between("payTime",
				cal_start.getTime(), cal_end.getTime()));
		// 查询当月注册的会员
		this.pageList = paylogService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		return pageList.getTotalCount();
	}

	public String memberUpgrade() {
		return "upgradeStat";
	}

	// 收入统计
	public String earn() {
		String hql = "select sum(amount) from AbcPaylog";
		int sum = paylogService.getStatBySql(hql);
		getRequest().setAttribute("sumCount", sum);
		return "earn";
	}

	public String earnShow() throws ParseException {
		// 设置数据
		chart = JfreeCharthelper.createBarChart("网站收入统计图", "月份", "总收入",
				getEarnData());

		return SUCCESS;
	}

	private CategoryDataset getEarnData() throws ParseException {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		Calendar rightNow = Calendar.getInstance();
		for (int i = 1; i <= 12; i++) {
			dataset.addValue(getMonthEarn(rightNow.get(Calendar.YEAR), i, 1),
					rightNow.get(Calendar.YEAR) + "", "" + i);
			dataset.addValue(
					getMonthEarn(rightNow.get(Calendar.YEAR) - 1, i, 1),
					(rightNow.get(Calendar.YEAR) - 1) + "", "" + i);
		}
		return dataset;

	}

	public int getMonthEarn(int year, int month, int type)
			throws ParseException {

		Calendar rightNow1 = Calendar.getInstance();
		rightNow1.set(year, month, 0);
		String strDateStart = year + "-" + month + "-1";
		String strDateEnd = year + "-" + month + "-"
				+ rightNow1.getTime().getDate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date_start = sdf.parse(strDateStart);
		Date date_end = sdf.parse(strDateEnd);
		Calendar cal_start = Calendar.getInstance();
		Calendar cal_end = Calendar.getInstance();
		cal_start.setTime(date_start);
		cal_end.setTime(date_end);

		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcPaylog.class);
		detachedCriteria.add(Restrictions.between("payTime",
				cal_start.getTime(), cal_end.getTime()));
		// 查询当月注册的会员
		List<AbcPaylog> list = paylogService
				.findAllByCriteria(detachedCriteria);
		int sum = 0;
		for (AbcPaylog al : list)
			sum += Integer.parseInt(al.getAmount());
		return sum;
	}

	// 访问统计，读XML
	public String visit() {
		getRequest().setAttribute("visitCount", CommonConst.SITEINFO.pageCount);
		return "visit";
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

}
