package com.abbcc.util.constant;

public enum AdminPrivType {
	PA("管理首页"),
	PB("个人设置"),
	PC("系统设置"),
	PD("会员管理"),
	PE("新闻留言"),
	PF("企业管理"),
	PG("帮助中心");
	private String value;
	private AdminPrivType(String value){
		this.value = value;
	}
	public String toString() {
		return value;
	}

}
