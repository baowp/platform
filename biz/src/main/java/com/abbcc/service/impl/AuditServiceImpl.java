/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "LaythemeServiceImpl.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-3-9           baowp                      initial
 */

package com.abbcc.service.impl;

import org.springframework.stereotype.Service;

import com.abbcc.dao.AuditDAO;
import com.abbcc.models.AbcAudit;
import com.abbcc.service.AuditService;

@Service
public class AuditServiceImpl extends
		ServiceImpl<AbcAudit, AuditDAO> implements AuditService {

}
