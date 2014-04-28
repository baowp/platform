/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "df.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-7           wangjin                      initial
*/
package com.abbcc.service;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.abbcc.common.PaginationSupport;
import com.abbcc.models.AbcEnterprise;
import com.abbcc.models.AbcUser;

public interface UserService extends BaseService{
	public void save(AbcUser transientInstance);
	public void sendMail(String name,String username,String email,String randomString32);
	public void delete(AbcUser persistentInstance);

	public AbcUser findById(String id);
	
	public String userRegister(AbcUser user,String genre,String eName,String eAddress,String industry,String mainPro,String mainBuy,String randomString32);
	
	public AbcUser getUserByUsername(String username);
	
	public AbcUser getUserByEmail(String email);

	public AbcUser getUserByUsernameAndRandomString(String username,String randomString);
	
	public AbcUser findByUsernamePassword(String username,String password);
	
	public AbcUser findSeniorUser(String username, String password);
	
	public AbcUser findSeniorUser(String username, String password,String password2);
	
	public AbcUser findSubUserByUsernamePassword(String username,String password, String mainUrl);

	public List<AbcUser> findByExample(AbcUser instance);

	public List<AbcUser> findAll();
	
	public int updateUser(Serializable id, String user, String value);


	public void saveOrUpdate(AbcUser instance);

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria);

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int startIndex);

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex);

	public List findAllByCriteria(DetachedCriteria detachedCriteria);

	public int getCountByCriteria(DetachedCriteria detachedCriteria);

	public void callProcedure(String procString, List<Object> params)
			throws Exception;

	public List getCallProcedureResult(String procString, List<Object> params)
			throws Exception;
	public List<AbcUser> findUserByParams(String[] fields, String key);
	public  List<AbcUser> findUserByAdminSearch(String key);
	public  List<AbcUser> getUsersByGroupId(String groupId);
	public void sendMemberMail(AbcUser au,String type,String title,String content);
	/**
	 * 根据网站的域名查找到域名对应的用户
	 * 
	 * @param domain 域名
	 * @return 对应用户
	 */
	public AbcUser getUserByDomain(String domain);
}
