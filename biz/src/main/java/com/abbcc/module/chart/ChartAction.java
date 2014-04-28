package com.abbcc.module.chart;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.joda.time.DateTime;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcPayuser;
import com.abbcc.models.AbcStat;
import com.abbcc.models.AbcUser;
import com.abbcc.service.BaseService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.ObjectUtil;

public class ChartAction extends BaseAction<AbcUser> {
	private void userDateByMonth() {
		String[] types = { "00", "01", "02", "03" };
		DetachedCriteria dc = DetachedCriteria.forClass(entityClass);
		dc.add(Restrictions.eq("domain", domain)).add(
				Restrictions.in("type", types));
		List<AbcUser> list = baseService.findAllByCriteria(dc);
		DateTime dt = new DateTime(new Date());
		int thisYear = dt.getYear();
		Map mapthisyear = new HashMap();
		Map maplastyear = new HashMap();
		Map mapbeforlastyear = new HashMap();
		int[] thisyear1 = new int[12];
		int[] lastyear1 = new int[12];
		int[] beforlastyear1 = new int[12];
		for (AbcUser au : list) {
			DateTime dtt = new DateTime(au.getAddTime());
			if (dtt.getYear() == thisYear) {
				if (thisyear1[dtt.getMonthOfYear() - 1] == 0) {
					thisyear1[dtt.getMonthOfYear() - 1] = 1;
				} else {
					thisyear1[dtt.getMonthOfYear() - 1] = thisyear1[dtt
							.getMonthOfYear() - 1] + 1;
				}
			}
			if (dtt.getYear() == thisYear - 1) {
				if (lastyear1[dtt.getMonthOfYear() - 1] == 0) {
					lastyear1[dtt.getMonthOfYear() - 1] = 1;
				} else {
					lastyear1[dtt.getMonthOfYear() - 1] = lastyear1[dtt
							.getMonthOfYear() - 1] + 1;
				}
			}
			if (dtt.getYear() == thisYear - 2) {
				if (beforlastyear1[dtt.getMonthOfYear() - 1] == 0) {
					beforlastyear1[dtt.getMonthOfYear() - 1] = 1;
				} else {
					beforlastyear1[dtt.getMonthOfYear() - 1] = beforlastyear1[dtt
							.getMonthOfYear() - 1] + 1;
				}
			}
		}
		mapthisyear.put("name", thisYear + "年");
		mapthisyear.put("data", thisyear1);

		maplastyear.put("name", thisYear - 1 + "年");
		maplastyear.put("data", lastyear1);

		mapbeforlastyear.put("name", thisYear - 2 + "年");
		mapbeforlastyear.put("data", beforlastyear1);

		Map[] m = { mapthisyear, maplastyear, mapbeforlastyear };
		JSONArray json = JSONArray.fromObject(m);
		result = json.toString();
		System.out.println(json);

	}

	// 查询所有当前域名下面的会员升级统计
	private void memberUpgradeDateByMonth() {
		
		DetachedCriteria dc = DetachedCriteria.forClass(AbcPayuser.class,"user");
		if (!(getCurrentAdmin().getUsername().equals("admin"))) {
			dc=ObjectUtil.splitSourceInDc(this.getUserIdsByDomain(), dc, "in", "userId", 900);
		}
		dc.add(Restrictions.or(
				Restrictions.eq("auditState", CommonConst.STATEDEL),
				Restrictions.eq("auditState", CommonConst.STATENORMAL)));
		List<AbcPayuser> l = baseService.findAllByCriteria(dc);
		DateTime dt = new DateTime(new Date());
		int thisYear = dt.getYear();
		Map mapthisyear = new HashMap();
		Map maplastyear = new HashMap();
		Map mapbeforlastyear = new HashMap();
		int[] thisyear1 = new int[12];
		int[] lastyear1 = new int[12];
		int[] beforlastyear1 = new int[12];
		for (AbcPayuser au : l) {
			DateTime dtt = new DateTime(au.getAuditTime());
			if (dtt.getYear() == thisYear) {
				if (thisyear1[dtt.getMonthOfYear() - 1] == 0) {
					thisyear1[dtt.getMonthOfYear() - 1] = 1;
				} else {
					thisyear1[dtt.getMonthOfYear() - 1] = thisyear1[dtt
							.getMonthOfYear() - 1] + 1;
				}
			}
			if (dtt.getYear() == thisYear - 1) {
				if (lastyear1[dtt.getMonthOfYear() - 1] == 0) {
					lastyear1[dtt.getMonthOfYear() - 1] = 1;
				} else {
					lastyear1[dtt.getMonthOfYear() - 1] = lastyear1[dtt
							.getMonthOfYear() - 1] + 1;
				}
			}
			if (dtt.getYear() == thisYear - 2) {
				if (beforlastyear1[dtt.getMonthOfYear() - 1] == 0) {
					beforlastyear1[dtt.getMonthOfYear() - 1] = 1;
				} else {
					beforlastyear1[dtt.getMonthOfYear() - 1] = beforlastyear1[dtt
							.getMonthOfYear() - 1] + 1;
				}
			}
		}
		mapthisyear.put("name", thisYear + "年");
		mapthisyear.put("data", thisyear1);

		maplastyear.put("name", thisYear - 1 + "年");
		maplastyear.put("data", lastyear1);

		mapbeforlastyear.put("name", thisYear - 2 + "年");
		mapbeforlastyear.put("data", beforlastyear1);

		Map[] m = { mapthisyear, maplastyear, mapbeforlastyear };
		JSONArray json = JSONArray.fromObject(m);
		result = json.toString();
		System.out.println(json);

	}

	// 在后台首页显示用户后台使用状态
	public void userStat() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcStat.class);
		detachedCriteria.add(Restrictions.eq("enterpriseId", this
				.getCurrentEnt().getEnterpriseId()));
		// getRequest().setAttribute("sumStat", sum);
		List<AbcStat> list = baseService.findAllByCriteria(detachedCriteria);
		Map login = new HashMap();
		Map pro = new HashMap();
		Map supply = new HashMap();
		Map news = new HashMap();
		Map cert = new HashMap();
		Map job = new HashMap();
		login.put("name", "登陆");
		login.put("data", list.get(0).getLoginfrequency());

		pro.put("name", "产品");
		pro.put("data", list.get(0).getProductfrequency());

		supply.put("name", "供求");
		supply.put("data", list.get(0).getSupplyfrequency());

		news.put("name", "新闻");
		news.put("data", list.get(0).getNewsfrequency());

		cert.put("name", "证书");
		cert.put("data", list.get(0).getCertfrequency());

		job.put("name", "招聘");
		job.put("data", list.get(0).getJobfrequency());

		Map[] m = { login, pro, supply, news, cert, job };
		JSONArray json = JSONArray.fromObject(m);
		result = json.toString();

	}

	private boolean noLogin() {
		if (getCurrentUser() != null || getCurrentAdmin() != null)
			return false;
		else
			return true;
	}

	public String getUserRegisterJsonDate() {
		if (!noLogin())
			userDateByMonth();
		return "json2";
	}

	public String getUserUpgradeJsonDate() {
		if (!noLogin())
			memberUpgradeDateByMonth();
		return "json2";
	}

	public String getUserState() {
		if (!noLogin())
			userStat();
		return "json2";
	}

	public static void main(String[] args) {
		new ChartAction().userDateByMonth();
	}
}
