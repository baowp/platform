package com.abbcc.service.impl;


import org.springframework.stereotype.Service;

import com.abbcc.dao.GroupThemeDao;
import com.abbcc.models.GroupTheme;
import com.abbcc.service.GroupThemeService;

@Service
public class GroupThemeServiceImpl extends
		ServiceImpl<GroupTheme, GroupThemeDao> implements GroupThemeService {
	
}
