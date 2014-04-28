package com.abbcc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abbcc.dao.GroupLaymodDao;
import com.abbcc.dao.GroupLayoutDao;
import com.abbcc.dao.GroupUserdefinedDao;
import com.abbcc.models.GroupUserdefined;
import com.abbcc.service.GroupUserdefinedService;
@Service
public class GroupUserdefinedServiceImpl extends ServiceImpl<GroupUserdefined, GroupUserdefinedDao> implements GroupUserdefinedService {

	@Autowired
	private GroupUserdefinedDao userdefinedDao;
	@Autowired
	private GroupLayoutDao layoutDao;
	@Autowired
	private GroupLaymodDao laymodDao;
	
	@Override
	public void cascadeDel(GroupUserdefined obj) {
		String layoutId = null;
		@SuppressWarnings("rawtypes")
		List list = layoutDao.find("select layoutId from GroupLayout where state=? and enterpriseId=?", "01", obj.getEnterpriseId());
		if(!list.isEmpty()) {
			layoutId = (String) list.get(0);
		}
		// 删除layoutmodel表中数据
		String hql = "delete from GroupLaymod where module.moduleId=? and layout.layoutId=?";
		laymodDao.bulkUpdate(hql, obj.getModuleId(), layoutId);
		userdefinedDao.delete(obj);
	}


}
