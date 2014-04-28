/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "MembergroupAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-1-4           yixiugg                      initial
 **/

package com.abbcc.module.membergroup;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcAdmin;
import com.abbcc.models.AbcGroupmember;
import com.abbcc.models.AbcUser;
import com.abbcc.models.AbcUsergroup;
import com.abbcc.service.GroupmemberService;
import com.abbcc.service.UserService;
import com.abbcc.service.UsergroupService;
import com.abbcc.util.StringUtil;
import net.sf.json.JSONArray;

/**
 * *MembergroupAction.java
 */
@SuppressWarnings("serial")
public class MembergroupAction extends BaseAction<AbcUsergroup> {
	private String VIEWGROUPS = "viewGroups";
	private String EDITSUCCESS = "editsuccess";
	private UsergroupService usergroupService;
	private UserService userService;
	private GroupmemberService groupmemberService;
	private List<AbcUsergroup> groups;
	private List<AbcGroupmember> members;
	private List<String> userIds;
	private String searchName;

	public List<String> getUserIds() {
		return userIds;
	}

	public void setUserIds(List<String> userIds) {
		this.userIds = userIds;
	}

	/**
	 * 查看群组
	 */
	public String view() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcUsergroup.class);
		String addAdmin = this.adminId();
		detachedCriteria.add(Restrictions.eq("addAdmin", addAdmin));
		detachedCriteria.add(Restrictions.eq("state", CommonConst.STATENORMAL));
		if (StringUtil.isNotBlank(searchName))
			detachedCriteria.add(Restrictions.like("groupname", searchName,
					MatchMode.ANYWHERE));
		groups = usergroupService.findAllByCriteria(detachedCriteria);
		return VIEW;
	}
	public void allUser(){
		DetachedCriteria dc = DetachedCriteria.forClass(AbcUser.class);
		dc.add(Restrictions.eq("domain",domain)).addOrder(Order.desc("userId"));
		
		pageList = baseService.findPageByCriteria(dc,pageSize,startIndex);
		deposit("pageList",pageList);
	}

	/**
	 * 删除群组
	 * 
	 * @return
	 */
	public String delete() {
		AbcUsergroup group = usergroupService.findById(entity.getUsergroupId());
		usergroupService.delete(group);
		this.savaAdminLog("群组",group.getGroupname(),CommonConst.LOGDEL);
		this.result = "删除分组成功！";
		return VIEWGROUPS;
	}

	/**
	 * 添加群组
	 */
	public String add() {
		String addAdmin = this.adminId();
		entity.setAddAdmin(addAdmin);
		entity.setAddTime(new Date());
		entity.setState(CommonConst.STATENORMAL);
		usergroupService.save(entity);
		this.savaAdminLog("群组",entity.getGroupname(),CommonConst.LOGSAVE);
		this.result = "添加分组成功！";
		return VIEWGROUPS;
	}

	/**
	 * 修改群组成员
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String edit() {
		AbcGroupmember temp = new AbcGroupmember();
		temp.setUsergroupId(entity.getUsergroupId());
		List<AbcGroupmember> members = groupmemberService.findByExample(temp);
		for (AbcGroupmember member : members) {
			if (userIds != null && userIds.size() > 0) {
				if (userIds.contains(member.getUserId())) {
					userIds.remove(member.getUserId());
				} else {
					member.setState(CommonConst.STATEDEL);
					groupmemberService.saveOrUpdate(member);
				}
			} else {
				member.setState(CommonConst.STATEDEL);
				groupmemberService.saveOrUpdate(member);
			}
		}
		for (String userId : userIds) {
			AbcGroupmember member = new AbcGroupmember();
			member.setState(CommonConst.STATENORMAL);
			member.setUsergroupId(entity.getUsergroupId());
			member.setUserId(userId);
			groupmemberService.save(member);
		}
		this.savaAdminLog("群组",entity.getGroupname(),CommonConst.LOGUPDATE);
		this.result = "修改用户组成员成功！";
		return EDITSUCCESS;
	}

	/**
	 * 查看群组成员
	 */
	public String list() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcGroupmember.class);
		detachedCriteria.add(Restrictions.eq("usergroupId", entity
				.getUsergroupId()));
		entity = usergroupService.loadById(entity, entity.getUsergroupId());
		this.startIndex = (this.page - 1) * pageSize;
		this.pageList = groupmemberService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		return LIST;
	}

	/**
	 * 查找用户
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String search() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcUser.class);
		if (!StringUtil.isBlank(searchName))
			detachedCriteria.add(Restrictions.or(Restrictions.like("username",
					searchName, MatchMode.ANYWHERE), Restrictions.like("name",
					searchName, MatchMode.ANYWHERE)));
		detachedCriteria.add(Restrictions.eq("state", CommonConst.STATENORMAL));
		List users = userService.findAllByCriteria(detachedCriteria);
		JSONArray jsonArray = JSONArray.fromObject(users);
		this.result = jsonArray.toString();
		return SUCCESS;
	}

	public UsergroupService getUsergroupService() {
		return usergroupService;
	}

	public void setUsergroupService(UsergroupService usergroupService) {
		this.usergroupService = usergroupService;
	}

	public GroupmemberService getGroupmemberService() {
		return groupmemberService;
	}

	public void setGroupmemberService(GroupmemberService groupmemberService) {
		this.groupmemberService = groupmemberService;
	}

	public List<AbcUsergroup> getGroups() {
		return groups;
	}

	public void setGroups(List<AbcUsergroup> groups) {
		this.groups = groups;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public List<AbcGroupmember> getMembers() {
		return members;
	}

	public void setMembers(List<AbcGroupmember> members) {
		this.members = members;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
