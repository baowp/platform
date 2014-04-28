package com.abbcc.module.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcEnterprise;
import com.abbcc.models.AbcSyscode;
import com.abbcc.service.SyscodeService;

public class RegisterAction extends BaseAction<AbcEnterprise> {
	private SyscodeService syscodeService;

	public void setSyscodeService(SyscodeService syscodeService) {
		this.syscodeService = syscodeService;
	}

	/**
	 * 主要是用于注册页面的select
	 * 
	 * @return
	 */
	public String show() {
		Map<String, String> industyMap = new LinkedHashMap<String, String>();
		Map<String, String> addressMap = new LinkedHashMap<String, String>();
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcSyscode.class);
		detachedCriteria.add(Restrictions.and(Restrictions.ne("state",
				CommonConst.STATEDEL), Restrictions.eq("type",
				CommonConst.SYSCODETYPEINDUSTY)));
		List<AbcSyscode> list = syscodeService
				.findAllByCriteria(detachedCriteria);
		for (AbcSyscode as : list) {
			industyMap.put(as.getSyscodeId(), as.getName());
		}

		getSession().setAttribute("industyMap", industyMap);
		
		List<AbcSyscode> pList = new ArrayList<AbcSyscode>();
		Map<AbcSyscode,List<AbcSyscode>> sysMap=new HashMap<AbcSyscode,List<AbcSyscode>>();
		String hql = "from AbcSyscode where type='01' and (state<'03' or state>'03') order by type";
		List<AbcSyscode> addressList = syscodeService.findByHql(hql);
		for(int i=0;i<addressList.size();i++){
			String hql1 = "from AbcSyscode where type='02' and ((state<'03' or state>'03') and fatherdict='"+addressList.get(i).getSyscodeId()+"') order by type";
			List<AbcSyscode> addressList1 = syscodeService.findByHql(hql1);
			
			sysMap.put(addressList.get(i), addressList1);
		}
		getSession().setAttribute("sysMap", sysMap);

		return "show";

	}

	

}
