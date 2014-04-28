/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "AdminprivilegeServiceImpl.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-9           Wangjin                      initial
 */

package com.abbcc.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abbcc.common.CommonConst;
import com.abbcc.common.PaginationSupport;
import com.abbcc.dao.AttachmentDAO;
import com.abbcc.dao.ProductDAO;
import com.abbcc.helper.ImageMagickHelper;
import com.abbcc.models.AbcAttachment;
import com.abbcc.models.AbcEnterprise;
import com.abbcc.models.AbcProduct;
import com.abbcc.models.AbcUser;
import com.abbcc.module.product.ProductAction;
import com.abbcc.service.ProductService;
import com.abbcc.util.StringUtil;
import com.abbcc.util.constant.ModelType;

public class ProductServiceImpl extends BaseServiceImpl implements
		ProductService {

	private ProductDAO productDAO;
	private AttachmentDAO attachmentDAO;

	private AbcProduct entity;

	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
		setBaseDAO(productDAO);
	}

	public void setAttachmentDAO(AttachmentDAO attachmentDAO) {
		this.attachmentDAO = attachmentDAO;
	}

	public void save(AbcProduct transientInstance) {
		productDAO.save(transientInstance);
	}

	public void delete(AbcProduct entity) {
		productDAO.delete(entity);
		String hql = "delete from AbcAttachment where belongId=?";
		productDAO.bulkUpdate(hql, entity.getProductId());
	}

	public AbcProduct findById(String id) {
		return productDAO.findById(id);
	}

	public List<AbcProduct> findByExample(AbcProduct instance) {
		return productDAO.findByExample(instance);

	}

	public List<AbcProduct> findAll() {
		return productDAO.findAll();

	}

	public void saveOrUpdate(AbcProduct instance) {
		productDAO.saveOrUpdate(instance);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria) {
		return productDAO.findPageByCriteria(detachedCriteria);
	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int startIndex) {
		return productDAO.findPageByCriteria(detachedCriteria, startIndex);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex) {
		return productDAO.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
	}

	public List findAllByCriteria(DetachedCriteria detachedCriteria) {
		return productDAO.findAllByCriteria(detachedCriteria);

	}

	public int getCountByCriteria(DetachedCriteria detachedCriteria) {
		return productDAO.getCountByCriteria(detachedCriteria);

	}

	public void callProcedure(String procString, List<Object> params)
			throws Exception {
		productDAO.callProcedure(procString, params);
	}

	public List getCallProcedureResult(String procString, List<Object> params)
			throws Exception {
		return productDAO.getCallProcedureResult(procString, params);
	}

	public PaginationSupport getPublished(AbcEnterprise enterprise,
			int pageSize, int startIndex) {
		AbcProduct entity = new AbcProduct();
		entity.setEnterpriseId(enterprise.getEnterpriseId());
		entity.setState(CommonConst.STATENORMAL);
		entity.setPublish("01");
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entity
				.getClass());
		detachedCriteria.add(Example.create(entity));
		detachedCriteria.add(Restrictions.gt("unpublishTime", new Date()));
		detachedCriteria.addOrder(Order.desc("addTime"));
		PaginationSupport pageItems = productDAO.findPageByCriteria(
				detachedCriteria, pageSize, startIndex);
		return pageItems;
	}

	public void save(ProductAction action) {
		AbcUser user = action.getCurrentUser();
		entity = action.getModel();
		entity.setAdduserId(user.getUserId());
		entity.setEnterpriseId(user.getEnterpriseId());
		if (CommonConst.PRODUCTAUDIT)
			entity.setState(CommonConst.STATEINIT);
		else
			entity.setState(CommonConst.STATENORMAL);

		Date date = new Date();
		entity.setAddTime(date);
		entity.setPublishTime(date);
		entity.setPublish("01");
		productDAO.save(entity);
		savePhotos(action);
		action.setTempAttachment(entity.getProductId(), ModelType.BG);
	}

	public void update(ProductAction action) {
		entity = action.getModel();
		entity.setState(CommonConst.STATEINIT);
		productDAO.update(entity);
		savePhotos(action);
		action.setTempAttachment(entity.getProductId(), ModelType.BG);
	}

	private void savePhotos(ProductAction action) {
		ServletContext servletContext = ServletActionContext
				.getServletContext();
		String photoId = action.photoId;
		String photo = action.photo;
		String[] photo2Id = action.photo2Id;
		String[] photo2 = action.photo2;
		String[] updatePic2 = action.updatePic2;
		
		int photo2Count = action.photo2Count;
		String path = servletContext.getRealPath(photo);
		if (StringUtil.isNotBlank(photo)) {
			AbcAttachment attach = null;
			if (StringUtil.isNotBlank(photoId)) {
				attach = attachmentDAO.findById(photoId);
			} else {
				attach = new AbcAttachment();
				attach.setBelongType(ModelType.BG);
				attach.setBelongId(entity.getProductId());
				attach.setType(CommonConst.ATTACHTYPEPIC);
				attach.setFiledesc(CommonConst.ATTACHPICMAIN);
			}
			attach.setServerPath(photo);
			attach.setFilename(photo.replaceAll("^.*/([^/]+)$", "$1"));
			attach.setUserId(action.getCurrentUser().getUserId());
			attach.setUploadTime(new Date());
			attachmentDAO.saveOrUpdate(attach);
			/*
			 * try { ImageMagickHelper.createSpecificThumbnail(path, action
			 * .pathToShow(path), 160); } catch (Exception e) { log.info(e); }
			 */
		}
		if (photo2 != null)
			for (int i = 0; i < photo2.length; i++) {
				String updatePic = "";
				if(updatePic2!=null)
					updatePic = updatePic2[i];
				photo = photo2[i];
				if (photo2Id == null)
					photo2Id = new String[photo2Count];
				photoId = photo2Id[i];
				if (StringUtil.isNotBlank(photo)) {
					path = servletContext.getRealPath(photo);
					AbcAttachment attach = null;
					if (StringUtil.isNotBlank(photoId)) {
						attach = attachmentDAO.findById(photoId);
					} else {
						attach = new AbcAttachment();
						attach.setBelongType(ModelType.BG);
						attach.setBelongId(entity.getProductId());
						attach.setType(CommonConst.ATTACHTYPEPIC);
						attach.setFiledesc(CommonConst.ATTACHPICPERTAIN);
					}
					attach.setServerPath(photo);
					attach.setFilename(photo.replaceAll("^.*/([^/]+)$", "$1"));
					attach.setUserId(action.getCurrentUser().getUserId());
					attach.setUploadTime(new Date());
					attachmentDAO.saveOrUpdate(attach);

				} else {
					if (StringUtil.isNotBlank(updatePic)) {
						AbcAttachment attach = attachmentDAO.findById(photoId);
						attachmentDAO.delete(attach);
					}
				}
			}
	}

	@Override
	public void changeSort(AbcProduct product1, AbcProduct product2) {
		Integer sort = product1.getSort();
		product1.setSort(product2.getSort());
		product2.setSort(sort);
		productDAO.update(product1);
		productDAO.update(product2);
	}
}
