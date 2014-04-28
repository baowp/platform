package com.abbcc.service.impl;


import org.springframework.stereotype.Service;

import com.abbcc.dao.GroupModuleDao;
import com.abbcc.models.GroupModule;
import com.abbcc.service.GroupModuleService;

@Service
public class GroupModuleServiceImpl extends
		ServiceImpl<GroupModule, GroupModuleDao> implements GroupModuleService {
	
}
