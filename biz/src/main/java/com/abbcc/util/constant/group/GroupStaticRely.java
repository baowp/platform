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

package com.abbcc.util.constant.group;

public enum GroupStaticRely {

	A("js", "jquery.min.js,jquery-ui.min.js,common.js,DD_belatedPNG_0.0.8a-min.js"), 
	B("js/jquery", "album/css/basic.css,album/css/galleriffic-2.css,album/js/jquery.opacityrollover.js,album/js/jquery.galleriffic.js,colorize.js,cookie.js,fancybox.pack.js,form.min.js,json.min.js,metadata.js,validate.min.js,jcarousel.min.js"),
	B1("js/jquery/ui","jquery.ui.core.min.js,jquery.ui.widget.min.js,jquery.ui.mouse.min.js,jquery.ui.draggable.min.js,jquery.ui.position.min.js,jquery.ui.resizable.min.js,jquery.ui.dialog.min.js"),
	C("js/cvi","busy.js"),
	D("js/yui","build/treeview/assets/skins/sam/*,build/yahoo-dom-event/yahoo-dom-event.js,build/connection/connection-min.js,build/treeview/treeview-min.js"),
	E("group/dynamic/css","*","css"),
	F("group/dynamic/js","*","js/site"),
	G("group/dynamic/images","*","images/"),
//	H("user/vipsite/css","*","css/site"),
	I("css","jquery-ui.css,jquery-ui.min.css,images/*"),
//	J("images/jquery-ui","*"),
	K("images/fancybox","*"),
	L("group/dynamic/js/static", "*", "js/site"),
	M("group/dynamic/js/album", "*", "js/jquery/album"),
	J("group/dynamic/js/album/css","*","js/jquery/album/css"),
	N("js/util","language.js"),
	O("","nginx404.html"),
	P("images/error", "*"),
	Q("css/dialog","*","css/dialog"),
	R("js/jquery/superfish/","*");

	private String resourceDir;
	private String resource;
	private String build;

	private GroupStaticRely(String dir, String res) {
		resourceDir = build = dir;
		resource = res;
	}

	private GroupStaticRely(String dir, String resource, String build) {
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
