package com.abbcc.util.constant.layout;

public enum Piece {

	HEAD_LOGO,

	HEAD_NAVIGATOR,

	HEAD_SHOW,
	
	HEAD_USER_DEFINED1,
	
	HEAD_USER_DEFINED2,
	
	HEAD_USER_DEFINED3,

	MAIN_INTRO,

	MAIN_PRODUCTS,

	MAIN_COMPETITIVE_PRODUCTS,

	MAIN_NEW_PRODUCTS,
	
	MAIN_ADS_PRODUCTS,

	MAIN_SUPPLIES,

	MAIN_CONTACTS,

	MAIN_MESSAGE,

	MAIN_PHOTO,

	MAIN_TECHNIC,

	MAIN_NEWS,

	MAIN_RECRUIT,

	MAIN_CERT,
	
	MAIN_COMPANY_LOCATION,
	
	MAIN_USER_DEFINED1,
	
	MAIN_USER_DEFINED2,
	
	MAIN_USER_DEFINED3,
	
	MAIN_USER_DEFINED4,
	
	MAIN_USER_DEFINED5,
	
	MAIN_USER_DEFINED6,

	SIDE_CATEGORY,

	SIDE_SEARCH,

	SIDE_CONTACT,
	
	SIDE_LINK,

	SIDE_CATEGORY_NEWS,
	
	SIDE_ADS_PRODUCTS,
	
	SIDE_USER_DEFINED1,
	
	SIDE_USER_DEFINED2,
	
	SIDE_USER_DEFINED3,

	NAV_HOME("index"),

	NAV_PRODUCT("product", "products", StaticDetail.PRODUCT),

	NAV_SUPPLY("supply", "supplies", StaticDetail.SUPPLY),

	NAV_COMPANY("company"),

	NAV_PHOTO("photo", "photos", StaticDetail.PHOTO),

	NAV_MESSAGE("message"),

	NAV_CONTACT("contact"),

	NAV_TECHNIC("technic"),

	NAV_NEWS("news", "commonNews", StaticDetail.NEWS),

	NAV_RECRUIT("recruit", "recruit"),

	NAV_CERT("cert"),
	
	PRODUCT_SIMPLE1,
	
	PRODUCT_SIMPLE1_L,
	
	PRODUCT_SIMPLE1_R,
	
	PRODUCT_SIMPLE2,
	
	PRODUCT_SIMPLE3,
	
	PRODUCT_SIMPLE3_L,
	
	PRODUCT_SIMPLE3_R,
	
	PRODCUT_SIMPLE4,
	
	PRODCUT_DETAIL1;
	
	private String action;
	private String key;
	private StaticDetail detail;

	private Piece() {
	}

	private Piece(String action) {
		this.action = action;
	}

	private Piece(String action, String key) {
		this.action = action;
		this.key = key;
	}

	private Piece(String action, String key, StaticDetail detail) {
		this.action = action;
		this.key = key;
		this.detail = detail;
	}

	public String getAction() {
		return action;
	}

	public StaticDetail getDetail() {
		return detail;
	}

	public String getKey() {
		return key;
	}

	public static Piece[] defaultNavs() {
		Piece[] navPieces = { Piece.NAV_HOME, Piece.NAV_PRODUCT,
				Piece.NAV_SUPPLY, Piece.NAV_COMPANY, Piece.NAV_PHOTO,
				Piece.NAV_MESSAGE, Piece.NAV_CONTACT };
		return navPieces;
	}
}
