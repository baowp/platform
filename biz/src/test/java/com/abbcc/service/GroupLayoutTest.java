package com.abbcc.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcUser;
import com.abbcc.models.GroupLayout;
import com.abbcc.util.MD5EncryptUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring/application*.xml")
@Transactional
public class GroupLayoutTest {
	org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(getClass());

	@Autowired
	private GroupLayoutService groupLayoutService;
	@Autowired
	private UserService userService;
	@Autowired
	private GroupNavigatorService groupNavigatorService;

	/** 这里改成你的用户名 与密码 **/
	String username = "abbcc";
	String password = "123456";
	AbcUser user;

	@Before
	public void before() {
		CommonConst.CLASSPATH = "conf/";
		user = userService.findSeniorUser(username,
				MD5EncryptUtil.md5Encry(password));
	}

	/**
	 * 初始旺铺布局
	 */
	@Test
	@Rollback(value = false)
	public void initLay() {
		if (user != null) {
			groupLayoutService.initLay(user);
		}
	}

	/**
	 * 恢复旺铺布局
	 */
	@Test
	@Rollback(value = false)
	public void revertLayout() {
		if (user != null) {
			groupLayoutService.revertLayout(user);
		}
	}

	@Test
	@Rollback(value = false)
	public void addLayout() {
		if (user != null) {
			GroupLayout layout = new GroupLayout();
			layout.setEnterpriseId(user.getEnterpriseId());
			layout.setUserId(user.getUserId());
			groupLayoutService.save(layout);
		}
	}

	/**
	 * 初始化话,企业导航菜单
	 */
	@Test
	@Rollback(value = false)
	public void initNav() {
		if (user != null) {
			groupLayoutService.initNav(user);
		}
	}
}
