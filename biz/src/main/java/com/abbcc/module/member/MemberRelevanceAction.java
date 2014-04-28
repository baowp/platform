package com.abbcc.module.member;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.abbcc.action.BaseAction;
import com.abbcc.common.ConfConst;
import com.abbcc.models.AbcCategory;
import com.abbcc.models.AbcLayout;
import com.abbcc.models.AbcLayoutmodule;
import com.abbcc.models.AbcLaytheme;
import com.abbcc.models.AbcProduct;
import com.abbcc.models.AbcUser;
import com.abbcc.service.LayoutService;
import com.abbcc.service.UserService;
import com.abbcc.soa.client.WebServiceClient;
import com.abbcc.soa.service.UserRelevance;
import com.abbcc.soa.service.dto.LayoutDto;
import com.abbcc.soa.service.dto.LayoutmoduleDto;
import com.abbcc.util.MD5EncryptUtil;
import com.abbcc.util.ObjectUtil;
import com.abbcc.util.constant.layout.BelongPage;

public class MemberRelevanceAction extends BaseAction<AbcUser> {

	private String beginuser;
	private String beginpass;
	private String touser;
	private String topass;
	public String messageInfo;
	@Autowired
	private UserService userService;
	@Autowired
	private LayoutService layoutService;

	public String copyLay() {
		UserRelevance userRelevance = getUserRelevance();
		AbcUser begin = new AbcUser();
		begin.setUsername(beginuser);
		begin.setPassword(MD5EncryptUtil.md5Encry(beginpass));
		AbcUser to = userService.findSeniorUser(touser,
				MD5EncryptUtil.md5Encry(topass));
		if (to != null) {
			LayoutDto ld = userRelevance.relevanceLayout(begin);
			
			if(ld.getEnterpriseId() != null || "".equals(ld.getEnterpriseId())) {
				AbcLayout layout = new AbcLayout();
				ObjectUtil.extend(layout, ld);
				List<AbcLayoutmodule> layoutmodelList = new ArrayList<AbcLayoutmodule>();
				List<LayoutmoduleDto> moduleList = ld.getLayoutmoduleList();
				for(LayoutmoduleDto dto : moduleList) {
					AbcLayoutmodule temp = new AbcLayoutmodule();
					ObjectUtil.extend(temp, dto);
					temp.setBlock(BelongPage.valueOf(dto.getBlock()));
					layoutmodelList.add(temp);
				}
				
				List<AbcLaytheme> themeList = userRelevance.relevanceTheme(layout.getLayoutId());
				layout.setLayoutId(null);
				layout.setLayoutmoduleList(layoutmodelList);
				for(AbcLayoutmodule lay : layoutmodelList) {
					lay.setLayout(layout);
				}
				layout.setUserId(to.getUserId());
				layout.setEnterpriseId(to.getEnterpriseId());
				layoutService.layover(layout, themeList);
				messageInfo = "旺铺信息导入成功！";
			} else if(id == null) {
				messageInfo = "来源用户信息错误！";
			} else {
				messageInfo = "来源用户没有布局信息！";
			}
		} else {
			// 目标用户不存在
			messageInfo = "目标用户信息错误！";
		}
		return "copyOk";
	}
	
	public String copyPro() {
		UserRelevance userRelevance = getUserRelevance();
		AbcUser begin = new AbcUser();
		begin.setUsername(beginuser);
		begin.setPassword(MD5EncryptUtil.md5Encry(beginpass));
		AbcUser to = userService.findSeniorUser(touser,
				MD5EncryptUtil.md5Encry(topass));
		if(to != null) {
			List<AbcCategory> categoryList = userRelevance.relevanceCategory(begin);
			List<AbcProduct> productList = userRelevance.relevanceProduct(begin);
			
			if(categoryList != null && categoryList.size() > 0) {
				for(AbcCategory c : categoryList) {
					c.setEnterpriseId(to.getEnterpriseId());
					c.setAdduserId(to.getUserId());
				}
				for (AbcProduct p : productList) {
					p.setEnterpriseId(to.getEnterpriseId());
					p.setAdduserId(to.getUserId());
				}
				layoutService.layoverCategoryAndProduct(categoryList, productList);
				messageInfo = "产品信息导入成功！";
			} else {
				messageInfo = "来源用户信息错误！！";
			}
		} else {
			messageInfo = "目标用户信息错误！";
		}
		
		return "copyOk";
	}
	
	/**
	 * 获得UserRelevance对象,
	 * 通过webservice调用
	 */
	private UserRelevance getUserRelevance() {
		UserRelevance userRelevance = WebServiceClient.generate(
				UserRelevance.class, "http://" + ConfConst.OppositeDomain
						+ "/service/UserRelevance");
		return userRelevance;
	}

	
	public String getBeginuser() {
		return beginuser;
	}

	public void setBeginuser(String beginuser) {
		this.beginuser = beginuser;
	}

	public String getBeginpass() {
		return beginpass;
	}

	public void setBeginpass(String beginpass) {
		this.beginpass = beginpass;
	}

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getTopass() {
		return topass;
	}

	public void setTopass(String topass) {
		this.topass = topass;
	}

}
