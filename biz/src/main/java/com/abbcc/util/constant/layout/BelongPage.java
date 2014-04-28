package com.abbcc.util.constant.layout;

import java.util.ArrayList;
import java.util.List;

public enum BelongPage {

	HOME(Piece.NAV_HOME),

	PRODUCT(Piece.NAV_PRODUCT),

	PRODUCT_DETAIL,

	SEARCH,

	SUPPLY(Piece.NAV_SUPPLY),

	SUPPLY_DETAIL,

	COMPANY(Piece.NAV_COMPANY),

	PHOTO(Piece.NAV_PHOTO),

	PHOTO_DETAIL,

	CONTACT(Piece.NAV_CONTACT),

	MESSAGE(Piece.NAV_MESSAGE),

	TECHNIC(Piece.NAV_TECHNIC),

	NEWS(Piece.NAV_NEWS),

	NEWS_DETAIL,

	RECRUIT(Piece.NAV_RECRUIT),

	CERT(Piece.NAV_CERT);

	private Piece navigator;

	private BelongPage() {
	}

	private BelongPage(Piece navigator) {
		this.navigator = navigator;
	}

	public Piece getNavigator() {
		return navigator;
	}

	public static BelongPage toPage(Piece nav) {
		for (BelongPage page : values()) {
			if (page.navigator == nav)
				return page;
		}
		return null;
	}

	public static List<BelongPage> toPages(List<Piece> navs) {
		List<BelongPage> pages = new ArrayList<BelongPage>();
		for (Piece nav : navs) {
			pages.add(toPage(nav));
		}
		return pages;
	}

	public static List<BelongPage> toPageList(List<String> navs) {
		List<Piece> pieces = new ArrayList<Piece>();
		for (String nav : navs) {
			pieces.add(Piece.valueOf(nav));
		}
		return toPages(pieces);
	}
}
