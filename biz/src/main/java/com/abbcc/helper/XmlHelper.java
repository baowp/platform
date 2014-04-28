/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "IDUtil.java is the copyrighted,
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
package com.abbcc.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.abbcc.common.Module;
import com.abbcc.common.SiteInfo;

public class XmlHelper {
	private static Log log = LogFactory.getLog(XmlHelper.class);
	private SAXReader reader = new SAXReader();

	private Document document;

	/**
	 * get site informations from xml file
	 * 
	 * @return
	 * @throws DocumentException
	 */
	public SiteInfo parseSiteInfoFromXml(String filePath)
			throws DocumentException {
		File file = new File(filePath);
		document = reader.read(file);
		SiteInfo siteInfo = new SiteInfo();
		Element root = document.getRootElement();
		Element siteid = root.element("siteid");
		siteInfo.siteid = siteid.getText();
		Element sitename = root.element("sitename");
		siteInfo.sitename = sitename.getText();
		Element url = root.element("url");
		siteInfo.url = url.getText();
		Element company = root.element("company");
		siteInfo.company = company.getText();
		Element address = root.element("address");
		siteInfo.address = address.getText();
		Element zipcode = root.element("zipcode");
		siteInfo.zipcode = zipcode.getText();
		Element phone = root.element("phone");
		siteInfo.phone = phone.getText();
		Element fax = root.element("fax");
		siteInfo.fax = fax.getText();
		Element contact = root.element("contact");
		siteInfo.contact = contact.getText();
		Element email = root.element("email");
		siteInfo.email = email.getText();
		Element smtp = root.element("smtp");
		siteInfo.smtp = smtp.getText();
		Element adminemail = root.element("adminemail");
		siteInfo.adminemail = adminemail.getText();
		Element mailusername = root.element("mailusername");
		siteInfo.mailusername = mailusername.getText();
		Element mailpassword = root.element("mailpassword");
		siteInfo.mailpassword = mailpassword.getText();
		Element syncKey = root.element("syncKey");
		siteInfo.syncKey = syncKey.getText();
		Element pageCount = root.element("pageCount");
		siteInfo.pageCount = pageCount.getText();
		Element staticNumber = root.element("staticNumber");
		siteInfo.staticNumber = staticNumber.getText();
		return siteInfo;
	}

	/**
	 * 得到网站各个模块信息
	 * 
	 * @param filePath
	 * @return
	 * @throws DocumentException
	 */
	public List<Module> parseModulesFromXml(String filePath)
			throws DocumentException {
		List<Module> modules = new ArrayList<Module>();
		File file = new File(filePath);
		document = reader.read(file);
		Element root = document.getRootElement();
		List<Element>elements= root.selectNodes("module");
		for(Element e:elements){
			Module module = new Module();
			Element moduleid = e.element("moduleid");
			module.moduleid = moduleid.getText();
			Element type = e.element("type");
			module.type = type.getText();
			Element grade = e.element("grade");
			module.grade = grade.getText();
			Element name = e.element("name");
			module.name = name.getText();
			Element desc = e.element("desc");
			module.desc = desc.getText();
			Element url = e.element("url");
			module.url = url.getText();
			modules.add(module);
		}
		return modules;
	}
	/*
	 * 写入site info
	 */
	public static void setSiteInfoXml(SiteInfo siteInfo,String filePath){
		File siteInfoXml = new File(filePath);
		Document document = DocumentHelper.createDocument();
		document.setXMLEncoding("UTF-8");
        Element root = document.addElement("siteinfo");
        root.addElement("siteid").addText(siteInfo.siteid);
        root.addElement("sitename").addText(siteInfo.sitename);
        root.addElement("url").addText(siteInfo.url);
        root.addElement("company").addText(siteInfo.company);
        root.addElement("address").addText(siteInfo.address);
        root.addElement("zipcode").addText(siteInfo.zipcode);
        root.addElement("phone").addText(siteInfo.phone);
        root.addElement("fax").addText(siteInfo.fax);
        root.addElement("contact").addText(siteInfo.contact);
        root.addElement("email").addText(siteInfo.email);
        root.addElement("smtp").addText(siteInfo.smtp);
        root.addElement("adminemail").addText(siteInfo.adminemail);
        root.addElement("mailusername").addText(siteInfo.mailusername);
        root.addElement("mailpassword").addText(siteInfo.mailpassword);
        root.addElement("syncKey").addText(siteInfo.syncKey);
        root.addElement("pageCount").addText(siteInfo.pageCount);
        root.addElement("staticNumber").addText(siteInfo.staticNumber);
        try {
			XMLWriter writer = new XMLWriter(new FileOutputStream(siteInfoXml));
			writer.write(document);
			writer.close();
		} catch (Exception e) {
			// TODO: handle exception
		}    
	}
}
