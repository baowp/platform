package com.abbcc.service.impl;


import org.springframework.stereotype.Service;

import com.abbcc.dao.WatermarkDao;
import com.abbcc.models.AbcWatermark;
import com.abbcc.service.WatermarkService;

@Service
public class WatermarkServiceImpl extends
		ServiceImpl<AbcWatermark, WatermarkDao> implements WatermarkService {
	
}
