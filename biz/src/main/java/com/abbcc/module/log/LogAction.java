package com.abbcc.module.log;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
//import org.junit.Before;
//import org.junit.Test;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcLog;
import com.abbcc.service.LogService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.StringUtil;

public class LogAction extends BaseAction<AbcLog> {
	private LogService logService;

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	public String show(){
		/**
		 * 在查询之前先对当前日期的前30天以前的数据进行删除
		 */
		GregorianCalendar thisday = new GregorianCalendar();
		thisday.add(GregorianCalendar.DATE, -30);
		Date date = thisday.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(date);
		String hql = "delete from AbcLog where isadmin='"+CommonConst.LOGUSER+"' and (enterpriseId='"+this.getCurrentUser().getEnterpriseId()+"' and addTime<to_date('"+dateString+"', 'yyyy-mm-dd hh24:mi:ss'))";
		logService.bulkUpdate(hql);

		DetachedCriteria detachedCriteria = DetachedCriteria
		.forClass(AbcLog.class);
		detachedCriteria.add(Restrictions.and(Restrictions.eq("isadmin", CommonConst.LOGUSER), Restrictions.eq("enterpriseId",this.getCurrentUser().getEnterpriseId())));
		detachedCriteria.add(Restrictions.eq("domain",domain));
		detachedCriteria.addOrder(Order.desc("addTime"));
		pageList = logService.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
		return "show";
	}
	public String del(){
		AbcLog al = new AbcLog();
		al.setEnterpriseId(this.getCurrentUser().getEnterpriseId());
		List<AbcLog> logList =logService.findByExample(al);
		if(logList.size()!=0)
			logService.deleteAll(logList);
		this.result=StringUtil.encode("清除日志成功");
		return "returnshow";
	}
	public String adminShow(){
		/**
		 * 在查询之前先对当前日期的前30天以前的数据进行删除
		 */
		GregorianCalendar thisday = new GregorianCalendar();
		thisday.add(GregorianCalendar.DATE, -30);
		Date date = thisday.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(date);
		String hql = "delete from AbcLog where isadmin='"+CommonConst.LOGADMIN+"' and addTime<to_date('"+dateString+"', 'yyyy-mm-dd hh24:mi:ss')";
		logService.bulkUpdate(hql);

		DetachedCriteria detachedCriteria = DetachedCriteria
		.forClass(AbcLog.class);
		detachedCriteria.add(Restrictions.eq("isadmin",CommonConst.LOGADMIN));
		detachedCriteria.addOrder(Order.desc("addTime"));
		pageList = logService.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
		return "show";
	}
}
