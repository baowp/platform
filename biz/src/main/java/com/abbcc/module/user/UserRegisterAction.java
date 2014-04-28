/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "AdminLoginAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-5           wangjin                      initial
 **/

package com.abbcc.module.user;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.helper.FreemarkerHelper;
import com.abbcc.models.AbcCustom;
import com.abbcc.models.AbcEnterprise;
import com.abbcc.models.AbcMenu;
import com.abbcc.models.AbcUser;
import com.abbcc.service.CustomService;
import com.abbcc.service.EnterpriseService;
import com.abbcc.service.MenuService;
import com.abbcc.service.UserService;
import com.abbcc.util.FileUtil;
import com.abbcc.util.MD5EncryptUtil;
import com.abbcc.util.RegexUtil;
import com.abbcc.util.StringUtil;
import com.abbcc.util.checkKey.CheckKey;
import com.abbcc.util.constant.SecondDomain;

import freemarker.template.TemplateException;

@SuppressWarnings("serial")
public class UserRegisterAction extends BaseAction<AbcUser> {
	public String identity;
	private int affectRows;

	public int getAffectRows() {
		return affectRows;
	}

	public void setAffectRows(int affectRows) {
		this.affectRows = affectRows;
	}

	private AbcUser user = new AbcUser();
	private UserService userService;

	private String ValidKey;
	private String message;
	private String eAddress;
	public String eAddress2;
	private String randomString32;

	public String getRandomString32() {
		return randomString32;
	}

	public void setRandomString32(String randomString32) {
		this.randomString32 = randomString32;
	}

	public String geteAddress() {
		return eAddress;
	}

	public void seteAddress(String eAddress) {
		this.eAddress = eAddress;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getValidKey() {
		return ValidKey;
	}

	public void setValidKey(String validKey) {
		ValidKey = validKey;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	private String genre;
	private String industry;
	private String business_role;
	private String mainPro;
	private String mainBuy;
	public String entName;

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getBusiness_role() {
		return business_role;
	}

	public void setBusiness_role(String business_role) {
		this.business_role = business_role;
	}

	public String getMainPro() {
		return mainPro;
	}

	public void setMainPro(String mainPro) {
		this.mainPro = mainPro;
	}

	public String getMainBuy() {
		return mainBuy;
	}

	public void setMainBuy(String mainBuy) {
		this.mainBuy = mainBuy;
	}

	public String register() throws IOException, TemplateException {

		HttpSession session = this.getSession();
		if (!this.ValidKey.equalsIgnoreCase((String) session
				.getAttribute(CommonConst.VERICODE))) {
			this.addFieldError("valcodeTip", "图片验证码错误！");
			getRequest().setAttribute("identity", identity);
			getRequest().setAttribute("entName", entName);
			return INPUT;
		}
		/*
		 * if(!RegexUtil.checkUsername(entity.getUsername())){
		 * addFieldError("username",
		 * "检验用户名 取值范围为a-z,A-Z,0-9,\"_\",汉字6-20位字符，最大字符位数无限制，不能以\"_\"结尾");
		 * return INPUT; }
		 */
		AbcUser nameIsOrNo = this.userService.getUserByUsername(entity
				.getUsername().toLowerCase());
		if (nameIsOrNo != null || SecondDomain.defined(entity.getUsername())) {
			addFieldError("usernameTip", "用户名已经存在！");
			return INPUT;
		}
		if (StringUtil.isChinese(entity.getUsername())) {
			addFieldError("usernameTip", "用户名不能为中文！");
			return INPUT;
		}
		/*if(RegexUtil.startCheck("^[a-zA-Z0-9]+$",entity.getUsername())){
			addFieldError("usernameTip", "用户名只能为数字跟字母！");
			return INPUT;
		}*/
		if (StringUtil.hasKongge(entity.getUsername())) {
			addFieldError("usernameTip", "用户名不能有空格！");
			return INPUT;
		}
		if (entity.getUsername().indexOf("_") > -1||entity.getUsername().indexOf(".") > -1) {
			addFieldError("usernameTip", "用户名不能有下划线或小数点！");
			return INPUT;
		}
		if (entity.getUsername().indexOf("-old") > -1) {
			addFieldError("usernameTip", "用户名已经被注册");
			return INPUT;
		}
		// if("_".indexOf(arg0))
		AbcUser emailIsOrNo = this.userService
				.getUserByEmail(entity.getEmail());
		if (emailIsOrNo != null) {
			addFieldError("emailTip", "此邮箱已被注册！");
			return INPUT;
		}
		if (!CheckKey.checkKey(entity.getName())) {
			this.addFieldError("nameTip", "存在非法字符！");
			return INPUT;
		}

		if (!CheckKey.checkKey(entName)) {
			this.addFieldError("eNameTip", "存在非法字符！");
			return INPUT;
		}

		if (StringUtil.isBlank(entity.getCellphone())) {
			addFieldError("cellphoneTip", "请输入电话号码！");
			return INPUT;
		}

		String password = MD5EncryptUtil.md5Encry(entity.getPassword());
		String randomString32 = new StringUtil().getRndString(32);
		Calendar cal = Calendar.getInstance();
		entity.setAddTime(cal.getTime());
		entity.setPassword(password);
		entity.setState(CommonConst.STATEINIT);
		entity.setDomain(domain);
		if ("buyer".equals(identity))
			entity.setType("00");
		if ("seller".equals(identity) || "both".equals(identity))
			entity.setType("01");
		entity.setGrade(CommonConst.USERGRADENONE);
		entity.setVericode(randomString32);
		entity.setUsername(entity.getUsername().toLowerCase());
		eAddress = eAddress + "," + eAddress2;
		mainPro = (mainPro==null?"":mainPro);
		mainBuy = (mainBuy==null?"":mainBuy);
		String temp = userService.userRegister(entity, genre, entName,
				eAddress, industry, mainPro, mainBuy, randomString32);

		return "registerSuccess";
	}

	public String userActivation() {
		AbcUser user = this.userService.getUserByUsernameAndRandomString(
				entity.getUsername(), randomString32);
		if (user != null) {
			String uId = user.getUserId();
			user.setUserId(uId);
			user.setState(CommonConst.STATENORMAL);
			userService.saveOrUpdate(user);

			return "validationSuccess";

		} else {
			return ERROR;
		}
	}

	public String usernameValidate() {
		AbcUser user = userService.getUserByUsername(entity.getUsername().toLowerCase());
		if (user != null || SecondDomain.defined(entity.getUsername())) {
			affectRows = -1;
		}
		return "json";
	}

	public String emailValidate() {
		AbcUser u = new AbcUser();
		u.setEmail(entity.getEmail());
		List<AbcUser> user = userService.findByExample(u);
		if (user.size() != 0) {
			affectRows = -1;
		}
		return "json";
	}

	/**
	 * 先不生成静态页，方法先留着
	 * 
	 * @param user
	 * @param enterprise
	 * @throws IOException
	 * @throws TemplateException
	 */
	@SuppressWarnings("unused")
	private void createTemplate(AbcUser user, AbcEnterprise enterprise)
			throws IOException, TemplateException {
		log.info("create " + user.getUsername() + "'s template");

		String content = FreemarkerHelper.getInstance().getEntryIndex(user,
				enterprise);
		String path = servletContext.getRealPath("entry/" + user.getUsername());
		String fileName = "index.html";
		FileUtil.write(path, fileName, content);
		fileName = "user_info.html";
		content = FreemarkerHelper.getInstance().getEntryUserInfo(user);
		FileUtil.write(path, fileName, content);
		fileName = "enterprise_info.html";
		content = FreemarkerHelper.getInstance().getEntryEnterpriseInfo(
				enterprise);
		FileUtil.write(path, fileName, content);
	}

	public static void main(String[] args) {
		String name = "_a_bbcc_aa";
		System.out.println(name.indexOf("_"));
	}
}
