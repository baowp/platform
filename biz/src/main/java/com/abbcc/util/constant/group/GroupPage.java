package com.abbcc.util.constant.group;

public enum GroupPage {

	flash,

	index,

	product("products", "PRODUCT"),

	product_detail,

	search,

	supply("supplies", "SUPPLY"),

	supply_detail,

	company,

	album("albums", "ALBUM"),

	album_detail,

	contact,

	message,

	technic,

	news("commonNews", "NEWS"),

	news_detail,

	recruit("recruit"),

	cert("cert");

	private String key;
	private String detail;

	private GroupPage() {
	}

	private GroupPage(String key) {
		this.key = key;
	}

	private GroupPage(String key, String detail) {
		this.key = key;
		this.detail = detail;
	}

	public String getKey() {
		return key;
	}

	public GroupStaticDetail getDetail() {
		if (detail == null)
			return null;
		return GroupStaticDetail.valueOf(detail);
	}

	public static boolean contain(String name) {
		for (GroupPage page : values())
			if (page.name().equals(name))
				return true;
		return false;
	}

}
