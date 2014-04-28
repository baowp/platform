package com.abbcc.service.impl;


import org.springframework.stereotype.Service;

import com.abbcc.dao.TailorDao;
import com.abbcc.models.AbcTailor;
import com.abbcc.service.TailorService;

@Service
public class TailorServiceImpl extends
		ServiceImpl<AbcTailor, TailorDao> implements TailorService {
	
}
