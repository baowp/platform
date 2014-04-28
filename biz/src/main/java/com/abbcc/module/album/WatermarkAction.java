package com.abbcc.module.album;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcEnterprise;
import com.abbcc.models.AbcUser;
import com.abbcc.models.AbcWatermark;
import com.abbcc.models.SoaUser;
import com.abbcc.service.EnterpriseService;
import com.abbcc.service.SoaUserService;
import com.abbcc.service.UserService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.StringUtil;

public class WatermarkAction extends BaseAction<AbcWatermark>{
	public boolean showCom;
	public boolean showWWW;
	public String save(){
		if(StringUtil.isBlank(entity.getWatermarkId())){
			entity.setEnterpriseId(getCurrentUser().getEnterpriseId());
			entity.setAddUser(getCurrentUser().getUserId());
		}
		if(!showWWW)
			entity.setDlocation("");
		if(!showCom)
			entity.setElocation("");
		entity.setAddTime(new Date());
		baseService.saveOrUpdate(entity);
		result = "保存成功!";
		return JSON;
	}
	public void getWatermark(){
		DetachedCriteria dc = DetachedCriteria.forClass(entityClass);
		dc.add(Restrictions.eq("enterpriseId", getCurrentUser().getEnterpriseId()));
		List<AbcWatermark> list = baseService.findAllByCriteria(dc);
		if(list.size()!=0){
			String url = enterprisePath(list.get(0).getEnterpriseId());
			deposit("watermark",list.get(0));
			deposit("domainUrl", url);
		}
		
	}
	public String enterprisePath(String entId) {
		EnterpriseService enterpriseService = (EnterpriseService) BeansFactory
				.get("enterpriseService");
		UserService userService = (UserService) BeansFactory.get("userService");
		SoaUserService soaUserService = (SoaUserService) BeansFactory
				.get("soaUserService");
		AbcEnterprise ent = enterpriseService.findById(entId);
		AbcUser userIds = userService.findById(ent.getUserId());
		SoaUser su = new SoaUser();
		su.setUsername(userIds.getUsername());
		List<SoaUser> sou = (List) soaUserService.findByExample(su);
		if (sou.size() != 0) {
			String url =  sou.get(0).getDomain();
			return url;
		}

		String path = "";
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcUser.class);
		String[] types = { "00", "01", "02", "03" };
		detachedCriteria.add(Restrictions.in("type", types));
		detachedCriteria.add(Restrictions.and(
				Restrictions.eq("enterpriseId", entId),
				Restrictions.ne("state", CommonConst.STATEDEL)));
		List<AbcUser> userList = userService
				.findAllByCriteria(detachedCriteria);
		if (userList.size() != 0) {
			path = userList.get(0).getUsername();
			if (userList.get(0).getGrade().equals(CommonConst.USERGRADENONE)) {
				return userList.get(0).getDomain()+"/site/" + path;
			} else if (userList.get(0).getGrade()
					.equals(CommonConst.USERGRADEONE)) {
				return path + "."
						+ userList.get(0).getDomain();
			}
		}
		return  path + ".51archetype.com";
	}
}
