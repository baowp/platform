package com.abbcc.module.tailor;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcSupply;
import com.abbcc.models.AbcTailor;
import com.abbcc.util.StringUtil;
import com.abbcc.util.constant.SupplyType;

public class TailorAction extends BaseAction<AbcTailor>{
	public String edit(){
		if(StringUtil.isNotBlank(entity.getType())&&StringUtil.isNotBlank(entity.getContent())){
			if(entity.getType().equals("BU")){
				DetachedCriteria dc = DetachedCriteria.forClass(AbcSupply.class);
				dc.add(Restrictions.eq("type", SupplyType.valueOf(entity.getType())));
				dc.add(Restrictions.like("title", entity.getContent(), MatchMode.ANYWHERE));
				pageList = baseService.findPageByCriteria(dc, 5, startIndex);
				if(!pageList.getItems().isEmpty()){
					AbcTailor at = new AbcTailor();
					at.setEnterpriseId(getCurrentUser().getEnterpriseId());
					at.setType(entity.getType());
					List<AbcTailor> list = baseService.findByExample(at);
					if(list.isEmpty()){
						at.setContent(entity.getContent());
						at.setUserId(getCurrentUser().getUserId());
						at.setAddTime(new Date());
						at.setState(CommonConst.STATENORMAL);
						baseService.save(at);
					}else{
						list.get(0).setContent(entity.getContent());
						list.get(0).setAddTime(new Date());
						baseService.saveOrUpdate(list.get(0));
					}
					result = JSONArray.fromObject(pageList.getItems()).toString();
				}
			}
		}
		return JSON;
	}
}
