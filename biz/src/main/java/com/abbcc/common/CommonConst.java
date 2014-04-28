/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "IdentifierGeneratorHelper.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-3           yixiugg                      initial
 **/
package com.abbcc.common;

import com.abbcc.models.AbcAdminprivilege;
import com.abbcc.service.AdminprivilegeService;
import com.abbcc.service.SyscodeService;
import com.abbcc.util.BeansFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonConst {
	/**
	 * 网站信息
	 */
	// 文件夹分隔符
	public static String SEP;
	// 应用部署在硬盘上的路径
	public static String REALPATH;
	// jee应用对应WEB-INF/classes的硬盘路径
	public static String CLASSPATH;

	public static String CONTEXTROOT;
	public static String SITEINFOFILEPATH;
	public static String SITEMODULESFILEPATH;

	public static String SITECONFFOLDER = "site";
	public static String SITEINFOFILE = "site_info.xml";
	public static String SITEMODULESFILE = "site_modules.xml";
	public static String SUCCESS = "success";
	public static String FAILURE = "failure";
	public static String STATEINIT = "00";
	public static String STATENORMAL = "01";
	public static String STATEDENY = "02";
	public static String STATEDEL = "03";
	public static String STATEALL = "10";
	public static String NOTLOGIN = "请先登录！";
	public static String LOGINERROR = "用户名或者密码错误";
	public static String VERIFYCODEERROR = "验证码错误";
	public static String ENCRYPTERROR = "加密失败";
	public static String PASSWORDNULLERROR = "密码不能为空";
	public static String USERSTATEERROR = "用户被删除或者被屏蔽";
	public static String ENTERPRISENULLERROR = "企业信息为空";
	public static String NOTENTERPRISEERROR = "对不起!您不是企业会员，无权操纵此项";
	public static String JOBNULLERROR = "招聘信息为空或您没有权限查看";
	public static String USERNAMEEROOR = "用户名存在";
	public static String USERNAMEEXISTEDEROOR = "用户名已经被注册";
	public static String EMAILEXISTEDDROOR = "email地址已经被注册";
	public static String UPDATEEROOR = "修改失败，请稍候再试!";
	public static String NULLCERTIFCATE = "您当前没有证书！";
	public static String ADDROSE = "添加失败，请稍后再试！";
	public static String NULLSUBMEMBERERROR = "对不起！您暂时还没有二级会员";
	public static String NOTACTIVEERROR = "对不起您的帐户还末激活";
	public static String SESSIONLOGINUSERID = "loginuserid";
	public static String SESSIONUSER = "abbccuser";
	public static String SESSIONVIPUSER = "vipuser";
	//用户访问主平台的SESSION，做统计用
	public static String SESSIONVISITS = "visits";
	
	public static String SESSIONSUBACCOUNT = "abbccsubaccount";
	public static String SESSIONADMINNEWSCATEGORY = "adminNewsCategory";

	public static String SESSIONENT = "abbccEnterprise";
	public static String SESSIONADMIN = "abbccadmin";
	// public static String SESSIONJOB = "abbccJob";
	public static String SESSIONSUBMEMBER = "abbccSubMember";
	public static String SESSIONMODULES = "abbccModules";
	public static String SESSIONADMINPRIVILEGE = "abbccAdminprivilege";
	public static String SESSIONUSERPRIVILEGE = "abbccuserpriv";
	public static String SESSIONWEBSERVERS = "webservers";
	public static String SESSIONATTACHMENTS = "tempattachments";

	public static List<AbcAdminprivilege> ADMINPRIVILEGES = ((AdminprivilegeService) BeansFactory
			.get("adminprivilegeService")).findAll();
	public static List<Module> MODULES;
	public static SiteInfo SITEINFO;
	public static String USERLOGINPAGE = "/user/login.jsp";
	public static String ADMINLOGINPAGE = "/admin/login.jsp";
	public static String ADMINNOPRIV = "/admin/nopriv.htmll";
	public static String SITENAME = "Abbcc电子商务平台";
	public static String ADMINPRIVS = "adminPrivs";
	public static String ADMINTYPESUPER = "05";
	public static String ADMINTYPENORMAL = "01";
	public static String ADMINTYPESTUFF = "02";
	public static String GENDERMALE = "00";
	public static String GENDERFEMALE = "01";
	public static String ADDSUCCESSREFLESH = "添加成功，请刷新页面查看最新数据！";
	public static String SENDSUCCESS = "消息发送成功！";
	public static String ADDSUCCESS = "添加成功！";
	public static String ADDFAILED = "添加失败！";
	public static String EDITSUCCESS = "修改成功！";
	public static String DELSUCCESS = "删除成功！";
	public static String EDITFAILED = "修改失败！";
	public static String USERNAMERULE = "用户名只能为数字、英文字母、汉字和\"_\"，并且长度在6-20位";
	public static String EMAILRULE = "email必须为xx@xx.xx格式";
	public static String POSTCODERULE = "邮编必须是6位数字";
	public static String PHONENUMBERRULE = "电话号码格式：010-67676767";
	public static String CELLPHONENUMBERRULE = "手机号码格式：11位数字且以1开头";
	public static String PASSWORDRULE = "密码必须为6-15位，且必须有英文字母和数字";
	public static String REGEXUSERNAME = "[\\w\u4e00-\u9fa5]{6,20}(?<!_)";
	public static String REGEXEMAIL = "\\w+\\@\\w+\\.\\w{2,}";
	public static String REGEXPOSTCODD = "^[1-9]\\d{5}";
	public static String REGEXPHONENUMBER = "^[0]\\d{2,3}\\-\\d{7,8}";
	public static String REGEXPASSWORD = "^[a-zA-z]{1}[a-zA-Z0-9]{5,14}";
	public static String REGEXCELLPHONE = "^[1][\\d]{10}";
	public static String VERICODE = "vericode";
	public static String NORESULT = "没有相应的查询结果";

	/**
	 * 系统编码表类型
	 */
	public static String SYSCODETYPEAUDIT = "11";
	public static String SYSCODETYPEPROVINCE = "01";
	public static String SYSCODETYPECITY = "02";
	public static String SYSCODETYPEINDUSTY = "20"; // 所属行业
	public static String SYSCODETYPEPROCATA = "30";
	public static String SYSCODETYPEBANNEDKEY = "40";
	public static String SYSCODETYPE = "50";
	public static String SYSCODEBANK = "51";
	public static String SYSCODENCOMPANY = "60";//国家(英文数据库系统表)
	/**
	 * log操作的类型
	 */
	public static String LOGLOGOUT = "05";
	public static String LOGLOGIN = "04";
	public static String LOGDEL = "03";
	public static String LOGUPDATE = "02";
	public static String LOGSAVE = "01";
	public static String LOGRESEND = "00";
	public static String LOGUSER = "01";
	public static String LOGADMIN = "00";

	/**
	 * 新闻类型
	 */
	public static String NEWSTYPESYS = "01";
	public static String NEWSTYPEADMIN = "02";
	public static String NEWSTYPEUSER = "11";
	public static String NEWSTYPESUBUSER = "12";
	public static String NEWSANNOUNCE = "13";
	public static String NEWSIMAGENEWS = "01";
	public static String NEWSCOMMON = "02";
	public static String NEWSTOPNEWS = "01"; // 置顶新闻
	public static String NEWSROLLINGNEWS = "01"; // 滚动新闻
	public static String NEWSDISPLAY = "01";
	public static String NEWSHIDDEN = "02";

	/**
	 * 注册用户类型
	 */

	public static String USERTYPEPERSON = "00";
	public static String USERTYPEENTERPRISE = "01";
	public static String USERTYPEGROUP = "03";
	public static String USERTYPEINDIVI = "02";
	// 用户二级会员类型
	public static String SUBACCOUNT = "04";
	public static String SUBMEMBER = "05";
	public static String CONTACTNAME = "06";// 企业联系人

	/**
	 * 付费用户等级(grade) '00':'非付费','01':'企业版用户','02':'集团版用户'
	 */
	public static String USERGRADENONE = "00";
	public static String USERGRADEONE = "01";
	public static String USERGRADETWO = "02";
	public static String USERGRADEALL = "10";
	public static String USERGRADEPAY = "11";

	/**
	 * 会员类型数组
	 */
	public static String[] memberGrade = { CommonConst.USERGRADENONE,
			CommonConst.USERGRADEONE, CommonConst.USERGRADETWO };
	public static String[] memberType = { "00", "01", "02", "03","11","12","13","14","15","20" };
	/**
	 * 付费会员类型(abcPaylog->type;abcPayuser->paytype) 01：开通企业版，02：经销商，03开通集团版 
	 */
	public static String PAYUSERTYPEONE = "01";
	public static String PAYUSERTYPETWO = "02";
	public static String PAYUSERTYPETHREE = "03";
	/**
	 * 付款方式 01:'银行汇款',02:'7天免费试用'
	 */
	
	public static String PAYTYPEONE="01";
	public static String PAYTYPETWO="02";
	

	/**
	 * 通用分类表类型abc_category
	 */
	public static String CATEGORYTYPEJOB = "15";
	public static String CATEGORYTYPEPRODUCT = "10";
	public static String CATEGORYTYPEADMINNEWS = "12";
	public static String CATEGORYTYPENEWS = "11";
	public static String CATEGORYISROOT = "01";
	public static String CATEGORYFIRST = "01";
	public static String CATEGORYNOTROOT = "00";
	public static String CATEGORYISDISPLAY = "1";
	public static String CATEGORYNOTDISPLAY = "0";
	public static String CERTIFICATEPICTURE = "13"; // 证书图片类型
	public static String HELPCENTER = "14"; //帮助中心类型
	
	/**
	 * 产品
	 */
	public static String PRODUCTDISPLAY = "1";
	/**
	 * 附件表类型(type)
	 */
	public static String ATTACHTYPEPIC = "01";// 图片
	public static String ATTACHTYPERAR = "02";// 压缩包
	public static String ATTACHTYPETXT = "03";// 文本
	public static String ATTACHTYPETECHNIC = "sl";
	public static String ATTACHUSERBACKUP = "04";// 用户后台备份文件
	public static String ATTACHADMINBACKUP = "05";// 系统后台备份文件
	public static String ATTACHTYPELOGO = "15";// 企业LOGO
	/**
	 * 附件表文件说明(filedesc)
	 */
	public static String ATTACHPICMAIN = "main_pic";// 主图片
	public static String ATTACHPICPERTAIN = "pertain_pic";// 附属图片

	/**
	 * 银行列表
	 * 
	 */
	public static Map<String, String> BANKMAP = ((SyscodeService) BeansFactory
			.get("syscodeService")).getBankMaps();

	public static Map<String, String> getBANKMAP() {
		return BANKMAP;
	}

	public static void setBANKMAP(Map<String, String> bankmap) {
		BANKMAP = bankmap;
	}

	/**
	 * 付费到期类型
	 */
	public static String PAYENDWEEK = "1";
	public static String PAYENDMONTH = "2";
	public static String PAYEND3MONTHS = "3";
	public static String PAYENDCUSTOM = "0";
	public static String PAYENDEXPRIED = "-1";
	/**
	 * 消息类型
	 */
	public static String MESSAGEISREAD = "01"; // 已读消息
	public static String MESSAGENOTREAD = "02";// 未读消息
	public static String MESSAGEUSER = "03"; // 用户消息
	public static String MESSAGEBROWSER = "05"; // 浏览者消息
	public static String MESSAGESYS = "04"; // 系统消息
	public static String MESSAGESEND = "01"; // 表示是发送的消息
	public static String MESSAGERECV = "01"; // 表示是接收的消息

	/**
	 * 邮件类型
	 */
	public static String MAILSUBMEMBER = "01"; // 对二级会员发送邮件
	public static String MAILPAYENDREMIND = "11"; // 付费到期提醒
	public static String MAILUPGRADEINVITE = "12"; // 升级邀请
	public static String MAILGROUPMAIL = "31"; // 管理员群发邮件

	/**
	 * 邮件标题
	 */
	public static String MAILTITLEPAYENDREMIND = "Abbcc网站付费到期提醒"; // 网站付费到期提醒
	public static String MAILTITLEUPGRADEINVITE = "Abbcc网站升级邀请"; // 网站升级邀请

	/**
	 * 审核标志位
	 */
	public static String PRODUCTAUDITSIGN = "product_audit";
	public static String NEWSAUDITSIGN = "news_audit";
	public static String JOBAUDITSIGN = "job_audit";
	public static String NEEDSAUDITSIGN = "needs_audit";
	public static boolean PRODUCTAUDIT = false;
	public static boolean NEWSAUDIT = true;
	public static boolean JOBAUDIT = true;
	public static boolean NEEDSAUDIT = true;
	/**
	 * 附件表类型
	 */
	public static String PICTURE = "01";
	public static String NEWSTITLEPICTURE = "03";
	public static String MAILATTACHMENT = "02";
	public static String FLASH = "05";
	/**
	 * 用户菜单类型
	 */
	public static String DISPLAY = "01";
	/**
	 * 统计类型
	 */
	public static String STATLOGIN = "01";
	public static String STATADDPRODUCT = "02";
	public static String STATADDNEWS = "03";
	public static String STATADDJOB = "04";
	public static String STATADDSUPPLY = "05";
	public static String STATADDCERT = "06";

	/**
	 * 模板数据类型
	 */

	public static String TEMPLATE_DATA_ENTERPRISE = "enterprise";
	public static String TEMPLATE_DATA_NEWS_LIST = "news_list";
	public static String TEMPLATE_DATA_NEWS_IMAGE = "news_image";
	public static String TEMPLATE_DATA_NEWS_TOP = "news_top";
	public static String TEMPLATE_DATA_NEWS_ROLLING = "news_rolling";
	public static String TEMPLATE_DATA_NEWS_DETAIL = "news_detail";
	public static String TEMPLATE_DATA_PRODUCT_LIST = "product_list";
	public static String TEMPLATE_DATA_PRODUCT_NEW = "product_new";
	public static String TEMPLATE_DATA_PRODUCT_CATEGORY = "product_category";
	public static String TEMPLATE_DATA_PRODUCT_CURRENT_CATEGORY = "current_category";
	public static String TEMPLATE_DATA_PRODUCT_DETAIL = "product_detail";
	public static String TEMPLATE_DATA_JOB = "job";
	public static String TEMPLATE_DATA_SUPPLY = "supply";
	public static String TEMPLATE_DATA_CONTACT = "contact";
	public static String TEMPLATE_DATA_INTRO = "intro";
	public static String TEMPLATE_DATA_CERT = "cert";

	/**
	 * 模版htmll名称
	 */
	public static String TEMPLATE_HTML_INDEX = "index.html";
	public static String TEMPLATE_HTML_NEWS = "news.html";
	public static String TEMPLATE_HTML_PRODUCT = "product.html";
	public static String TEMPLATE_HTML_PRODUCT_CATEGORY = "product_category.html";
	public static String TEMPLATE_HTML_JOB = "jobs.html";
	public static String TEMPLATE_HTML_ABOUTUS = "aboutus.html";
	public static String TEMPLATE_HTML_CERT = "cert.html";
	public static String TEMPLATE_HTML_CONTACT = "contact.html";
	public static String TEMPLATE_HTML_SUPPLY = "supply.html";

	/**
	 * 网站同步的变量
	 */
	public static String FOLDER_TEMPLATE = "template";
	public static String FOLDER_HTML = "html";
	public static String FOLDER_HTML_WANGPU = "wangpu";
	public static String FOLDER_PICTURE = "picture";
	public static String FOLDER_FLASH = "flash";

	/**
	 * 上传文件夹
	 */

	/**
	 * 图片上传后生成水印及缩略图说明
	 * 图片上传后图片名字+_1的为水印图片，+_3的为缩略图(宽度为800),+_4的为缩略图(宽度为100),+_5的为缩略图(宽度为20),
	 * 如：你上传的图片为login.jpg，你生成的水印图片地址为login_1.jpg,缩略图名为login_3.jpg
	 */
	public static int THUMBNAIL_ALBUM = 800;
	public static int THUMBNAIL_BIG = 600;
	public static int THUMBNAIL_BIG2 = 500;
	public static int THUMBNAIL_MIDDLE = 300;
	public static int THUMBNAIL_SMALL = 160;
	public static int THUMBNAIL_SMALL2 = 80;
	public static int THUMBNAIL_LITTLE = 40;
	/**
	 * 相册水印类型，格式为：1，2，1不为空代表显示企业网站域名(a.51archetype.com),2不为空表示显示公司名称，里面（1：中间显示，2：左右边角显示，3：右下角显示）
	 */

	/**
	 * jms queue
	 */
	public static String JMS_MAIL = "ABBCCMAIL";

	/**
	 * 网站的title
	 */
	public static String ABBCC_TITLE = "ABBCC，商人自己的网站";
	/**
	 * 企业经营模式类型 ("00", "生产加工"); ("01", "经营批发"); ("02", "招商代理"); ("03", "商业服务");
	 * ("04", "以上都不是");
	 */

	public static String MAIL_TYPE_ADMIN_GROUP_MAIL = "01";
	public static String MAIL_TYPE_USERNAME_PASSWORD = "02";
	public static String MAIL_TYPE_NORMAL_MAIL = "03";
	public static String MAIL_TYPE_REGISTER = "04";
	public static String DFWJ="02"; //东方五金网类型
	public static String DOORVIP="20"; //上门 网VIP类型
	public static String DOMAINABBCC="51archetype.com";
	
	public static Map<String,String> languagePack = new HashMap<String,String>();
	/**
	 * 免费用户或收费过期用户发布限制
	 */
	public static int SUPPLYCOUNT = 5;
	public static int PRODUCTCOUNT = 5;
	public static int NEWSCOUNT = 5;
	public static int CERTCOUNT = 1;
	public static int JOBCOUNT = 1;
	public static int MESSAGECOUNT = 50;
	public static int PICCOUNT = 10;
	//是否进行发布控制(免费用户)
	public static boolean ISCONTROL = false;
	
	/**
	 * 产品其他参数
	 */
	public static String DETAIL_TITLE1 = "产品描述";
	public static String DETAIL_TITLE2 = "产品功能";
	public static String DETAIL_TITLE3 = "产品参数";
	/**
	 * 产品询盘、报价01:询盘，02:报价
	 * 
	 */
	public static String INQUIRYONE = "01";
	public static String INQUIRYTWO = "02";
}
