/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "StaticRely.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-5-18           baowp                      initial
 */

package com.abbcc.util.constant.layout;

public enum StaticRely {

	A("js", "jquery.min.js,jquery-ui.min.js,common.js,DD_belatedPNG_0.0.8a-min.js"), 
	B("js/jquery", "album/css/basic.css,album/css/galleriffic-2.css,album/js/jquery.opacityrollover.js,album/js/jquery.galleriffic.js,colorize.js,cookie.js,fancybox.pack.js,form.js,json.min.js,metadata.js,validate.js,jcarousel.min.js"),
	B1("js/jquery/ui","jquery.ui.core.min.js,jquery.ui.widget.min.js,jquery.ui.mouse.min.js,jquery.ui.draggable.min.js,jquery.ui.position.min.js,jquery.ui.resizable.min.js,jquery.ui.dialog.min.js"),
	C("js/cvi","busy.js"),
	D("js/yui","build/treeview/assets/skins/sam/*,build/yahoo-dom-event/yahoo-dom-event.js,build/connection/connection-min.js,build/treeview/treeview-min.js"),
	E("user/css/site","*","css/site"),
	F("user/js/site","*","js/site"),
	G("user/images/vipsite","*","images/vipsite"),
	H("user/vipsite/css","*","css/site"),
	I("css","jquery-ui.css,jquery-ui.min.css,images/*"),
//	J("images/jquery-ui","*"),
	K("images/fancybox","*"),
	L("user/js/static", "*", "js/site"),
	M("user/vipsite/js","*","js/site"),
	N("js/util","language.js"),
	O("","nginx404.html"),
	P("images/error", "*");

	private String resourceDir;
	private String resource;
	private String build;

	private StaticRely(String dir, String res) {
		resourceDir = build = dir;
		resource = res;
	}

	private StaticRely(String dir, String resource, String build) {
		resourceDir = dir;
		this.resource = resource;
		this.build = build;
	}

	public String getResource() {
		return resource;
	}

	public String getBuild() {
		return build;
	}

	public String getResourceDir() {
		return resourceDir;
	}
}
