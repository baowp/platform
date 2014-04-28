/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "FetchAttachAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-24           baowp                      initial
 */

package com.abbcc.module.product;

import com.abbcc.action.BaseAction;
import com.abbcc.models.AbcAttachment;
import com.abbcc.service.AttachmentService;

@SuppressWarnings("serial")
public class FetchAttachAction extends BaseAction<AbcAttachment> {

	private AttachmentService attachmentService;

	public void fetchAttach() {
		resultList = attachmentService.findByExample(entity);
	}

	public void setAttachmentService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}
}
