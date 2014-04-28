package com.abbcc.merchants.action;

import java.util.Date;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.merchants.models.Doorvip;
import com.abbcc.merchants.service.DoorvipService;
import com.abbcc.util.MD5EncryptUtil;

public class VipUserAction extends BaseAction<Doorvip> {
	private DoorvipService doorvipService;

	public void setDoorvipService(DoorvipService doorvipService) {
		this.doorvipService = doorvipService;
	}
	public String list() {
		DetachedCriteria dc = DetachedCriteria
		.forClass(entity.getClass());
		if(entity.getUsername()!=null)
			dc.add(Restrictions.like("username","%"+entity.getUsername()+"%"));
		dc.addOrder(Order.desc("userId"));
		pageList = doorvipService.findPageByCriteria(dc, pageSize, startIndex);
		return LIST;
	}
	public String save(){
		entity.setRegDate(new Date());
		entity.setPassword(MD5EncryptUtil.md5Encry16(entity.getPassword()));
		entity.setLockUser("TRUE");
		doorvipService.save(entity);
		return list();
	}
	public String setState(){
		if(entity.getLockUser().trim().equals("TRUE")){
			entity.setLockUser("FALSE");
			result="用户锁定";
		}
			
		else{
			entity.setLockUser("TRUE");
			result="正常使用";
		}
			
		doorvipService.saveOrUpdate(entity);
		return JSON;
	}
	public String remove(){
		doorvipService.delete(entity);
		return list();
	}
	public String setUserPsw(){
		entity.setPassword(MD5EncryptUtil.md5Encry16(entity.getPassword()));
		doorvipService.saveOrUpdate(entity);
		entity.setUsername(null);
		return list();
	}

}
