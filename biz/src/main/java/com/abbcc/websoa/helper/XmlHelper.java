/*    */ package com.abbcc.websoa.helper;
/*    */ 
/*    */ import com.abbcc.websoa.sys.CommonConst;
/*    */ import com.abbcc.websoa.sys.SysInfo;
/*    */ import java.io.File;
/*    */ import java.io.FileOutputStream;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ import org.dom4j.Document;
/*    */ import org.dom4j.DocumentException;
/*    */ import org.dom4j.DocumentHelper;
/*    */ import org.dom4j.Element;
/*    */ import org.dom4j.io.SAXReader;
/*    */ import org.dom4j.io.XMLWriter;
/*    */ 
/*    */ public class XmlHelper
/*    */ {
/* 39 */   private static Log log = LogFactory.getLog(XmlHelper.class);
/* 40 */   private SAXReader reader = new SAXReader();
/*    */   private Document document;
/*    */ 
/*    */   public SysInfo parseSysInfoFromXml(String filePath)
/*    */     throws DocumentException
/*    */   {
/* 52 */     File file = new File(filePath);
/* 53 */     this.document = this.reader.read(file);
/* 54 */     SysInfo sysInfo = new SysInfo();
/* 55 */     Element root = this.document.getRootElement();
/* 56 */     Element deskey = root.element("deskey");
/* 57 */     sysInfo.deskey = deskey.getText();
/* 58 */     Element hostip = root.element("hostip");
/* 59 */     sysInfo.hostip = hostip.getText();
/* 60 */     Element ftppath = root.element("ftppath");
/* 61 */     sysInfo.ftppath = ftppath.getText();
/* 62 */     return sysInfo;
/*    */   }
/*    */ 
/*    */   public static String setSysInfoXml(String nodeName, String nodeVal)
/*    */   {
/* 69 */     SysInfo sysInfo = CommonConst.SYSINFO;
/* 70 */     if (nodeName.equals(SysInfo.deskeyNode)) {
/* 71 */       sysInfo.deskey = nodeVal;
/*    */     }
/* 73 */     else if (nodeName.equals(SysInfo.hostipNode)) {
/* 74 */       sysInfo.hostip = nodeVal;
/*    */     }
/* 76 */     else if (nodeName.equals(SysInfo.ftppathNode)) {
/* 77 */       sysInfo.ftppath = nodeVal;
/*    */     }
/*    */     else {
/* 80 */       return "nodeName error";
/*    */     }
/* 82 */     File sysInfoXml = new File(CommonConst.SYSINFOFILEPATH);
/* 83 */     Document document = DocumentHelper.createDocument();
/* 84 */     document.setXMLEncoding("UTF-8");
/* 85 */     Element root = document.addElement("sys");
/* 86 */     root.addElement("deskey").addText(sysInfo.deskey);
/* 87 */     root.addElement("ftppath").addText(sysInfo.ftppath);
/* 88 */     root.addElement("hostip").addText(sysInfo.hostip);
/*    */     try {
/* 90 */       XMLWriter writer = new XMLWriter(new FileOutputStream(sysInfoXml));
/* 91 */       writer.write(document);
/* 92 */       writer.close();
/* 93 */       return "true";
/*    */     }
/*    */     catch (Exception e) {
/* 96 */       return "Exception_" + e.toString();
/*    */     }
/*    */   }
/*    */ }

/* Location:           G:\software\abbcc_websoa.jar
 * Qualified Name:     com.abbcc.websoa.helper.XmlHelper
 * JD-Core Version:    0.6.2
 */