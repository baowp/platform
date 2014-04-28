/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "ConfServlet.java is the copyrighted,
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

package com.abbcc.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.abbcc.common.CommonConst;
import com.abbcc.helper.XmlHelper;
import com.abbcc.models.AbcSyscode;
import com.abbcc.service.SyscodeService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.FileUtil;
import com.abbcc.util.constant.layout.BelongPage;

/**
 * *ConfServlet.java
 */
public class ConfServlet extends HttpServlet {
	/**
	 * serial id
	 */
	private static final long serialVersionUID = -2819896512667530335L;
	private static Log log = LogFactory.getLog(ConfServlet.class);

	/**
	 * Constructor of the object.
	 */
	public ConfServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		Properties sys = System.getProperties();
		System.setProperty("jmagick.systemclassloader", "no");
		CommonConst.SEP = sys.getProperty("file.separator");
		CommonConst.REALPATH = this.getServletContext().getRealPath(
				CommonConst.SEP);
		CommonConst.CONTEXTROOT = this.getServletContext().getContextPath();
        String confPath=Thread.currentThread().getContextClassLoader().getResource("conf.properties").getPath();
        int lastSep=confPath.lastIndexOf('/');
		CommonConst.CLASSPATH = confPath.substring(0,lastSep+1);
		CommonConst.SITEINFOFILEPATH = CommonConst.CLASSPATH
				+ CommonConst.SITECONFFOLDER + CommonConst.SEP
				+ CommonConst.SITEINFOFILE;
		CommonConst.SITEMODULESFILEPATH = CommonConst.CLASSPATH
				+ CommonConst.SITECONFFOLDER + CommonConst.SEP
				+ CommonConst.SITEMODULESFILE;
		XmlHelper xmlHelper = new XmlHelper();
		try {
			CommonConst.MODULES = xmlHelper
					.parseModulesFromXml(CommonConst.SITEMODULESFILEPATH);
			CommonConst.SITEINFO = xmlHelper
					.parseSiteInfoFromXml(CommonConst.SITEINFOFILEPATH);
			// Put your code here
		} catch (Exception e) {
			e.printStackTrace();
			log.error("加载site配置文件出错！");
			// TODO: handle exception
		}
		try {
			loadConfigProperties("conf.properties",
					"com.abbcc.common.ConfConst");
			loadLanguageProperties("language.properties");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			loadAuditFlag();
		} catch (Exception e) {
			// TODO: handle exception
		}
		BelongPage.values();// necessary,or else this Piece arg will be null
	}

	/**
	 * 读取配置文件，并且将配置文件中的值赋予配置的常量类
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * 
	 * @throws Exception
	 */
	private void loadConfigProperties(String filePath, String className)
			throws IOException, ClassNotFoundException {
		//ServletContext context = this.getServletContext();
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
		Properties p = new Properties();
		p.load(in);
		Class<?> cls = Class.forName(className);
		for (Map.Entry<?, ?> entry : p.entrySet()) {
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();
			try {
				Field field = cls.getField(key);
				fillField(field, value);
			} catch (Exception e) {
				try {
					Field field = cls.getField(key.replaceAll("\\.", "_"));
					fillField(field, value);
				} catch (Exception e1) {
				}
			}
		}
		log.info("load properties finished");
	}

	private void fillField(Field field, String value) throws Exception {
		if (field.getType() == int.class)
			field.setInt(null, Integer.parseInt(value));
		else if (field.getType() == boolean.class)
			field.setBoolean(null, Boolean.parseBoolean(value));
		else
			field.set(null, value);
	}

	/**
	 * 设置审核标志
	 */
	private void loadAuditFlag() {
		AbcSyscode syscode = new AbcSyscode();
		syscode.setType(CommonConst.SYSCODETYPEAUDIT);
		SyscodeService syscodeService = (SyscodeService) BeansFactory
				.get("syscodeService");
		List<AbcSyscode> list = syscodeService.findByExample(syscode);
		for (AbcSyscode s : list) {
			if (s.getSign().equals(CommonConst.PRODUCTAUDITSIGN)) {
				if (s.getState().equals(CommonConst.STATEINIT))
					CommonConst.PRODUCTAUDIT = false;
				if (s.getState().equals(CommonConst.STATENORMAL))
					CommonConst.PRODUCTAUDIT = true;
			}
			if (s.getSign().equals(CommonConst.NEWSAUDITSIGN)) {
				if (s.getState().equals(CommonConst.STATEINIT))
					CommonConst.NEWSAUDIT = false;
				if (s.getState().equals(CommonConst.STATENORMAL))
					CommonConst.NEWSAUDIT = true;
			}
			if (s.getSign().equals(CommonConst.JOBAUDITSIGN)) {
				if (s.getState().equals(CommonConst.STATEINIT))
					CommonConst.JOBAUDIT = false;
				if (s.getState().equals(CommonConst.STATENORMAL))
					CommonConst.JOBAUDIT = true;
			}
			if (s.getSign().equals(CommonConst.NEEDSAUDITSIGN)) {
				if (s.getState().equals(CommonConst.STATEINIT))
					CommonConst.NEEDSAUDIT = false;
				if (s.getState().equals(CommonConst.STATENORMAL))
					CommonConst.NEEDSAUDIT = true;
			}
		}

	}

	/**
	 * 加载语言包的配置
	 * 
	 * @param filePath
	 * @param className
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void loadLanguageProperties(String filePath) throws IOException,
			ClassNotFoundException {
		//ServletContext context = this.getServletContext();
		InputStream in =  Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
		Properties p = new Properties();
		p.load(in);
		StringBuffer sb = new StringBuffer();
		sb.append("var lan={");
		for (Map.Entry<?, ?> entry : p.entrySet()) {
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();
			CommonConst.languagePack.put(key, value);
			key = "'" + key + "'";
			value = "'" + value + "'";
			sb.append(key).append(":").append(value).append(",");
		}
		sb.append("0:0};");
		String path = this.getServletContext().getRealPath("/js/util");
		FileUtil.writeFileWithUTF8(path, "language.js", sb.toString());		// 生成 language.js 文件
		log.info("load properties finished");
	}

}
