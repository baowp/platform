/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "AttachAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-3-22           baowp                      initial
 */

package com.abbcc.module.usersite;

import java.util.Date;

import com.abbcc.action.FileUploadAction;
import com.abbcc.models.AbcAttachment;
import com.abbcc.util.ObjectUtil;
import com.abbcc.util.constant.ModelType;

@SuppressWarnings("serial")
public class AttachAction extends FileUploadAction<AbcAttachment> {

	public String callback;

	private String uploaded() {
		uploadImage();
		return "back";
	}
	
	protected void prepareAttach(AbcAttachment attach){
		entity.setBelongType(ModelType.AE);
		ObjectUtil.extend(attach, entity);
	}

	public String topic() {
		return uploaded();
	}
	
	public String sign(){
		return uploaded();
	}
	
	public String nav(){
		return uploaded();
	}
	
	public String title(){
		return uploaded();
	}
	
	public String inBg(){
		return uploaded();
	}
	
	public String outBg(){
		return uploaded();
	}
	
	public String log(){
		return uploaded();
	}
	
	public String flash(){
		doUpload();
		if(uploadFilePath!=null){
			entity.setBelongType(ModelType.AE);
			entity.setServerPath(uploadFilePath);
			entity.setFilename(newFilename);
			entity.setUserId(getCurrentUser().getUserId());
			entity.setUploadTime(new Date());
			attachmentService.save(entity);
		}
		return "back";
	}
}
