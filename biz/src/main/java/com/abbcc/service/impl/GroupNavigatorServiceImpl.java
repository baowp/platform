package com.abbcc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abbcc.dao.GroupLayoutDao;
import com.abbcc.dao.GroupNavigatorDao;
import com.abbcc.models.GroupLayout;
import com.abbcc.models.GroupNavigator;
import com.abbcc.service.GroupNavigatorService;

@Service
public class GroupNavigatorServiceImpl extends
		ServiceImpl<GroupNavigator, GroupNavigatorDao> implements
		GroupNavigatorService {
	@Autowired
	GroupLayoutDao layoutDao;

	public void deleteNav(GroupNavigator entity) {

		dao.delete(entity);
		String hql = "from GroupLayout where enterpriseId=?";
		List<GroupLayout> list = layoutDao.find(hql, entity.getEnterpriseId());

		String hql1 = "delete from GroupLaymod where layout.layoutId=? and page=?";
		dao.bulkUpdate(hql1, list.get(0).getLayoutId(), entity.getPage());

		String hql2 = "delete from GroupWidth where layoutId=? and page=?";
		dao.bulkUpdate(hql2, list.get(0).getLayoutId(), entity.getPage());

		hql = "delete from GroupSeo where enterpriseId=? and page=?";
		dao.bulkUpdate(hql, entity.getEnterpriseId(), entity.getPage());
	}

}
