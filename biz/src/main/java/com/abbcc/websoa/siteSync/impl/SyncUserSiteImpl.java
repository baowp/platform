/*    */ package com.abbcc.websoa.siteSync.impl;
/*    */ 
/*    */ import com.abbcc.helper.ZipHelper;
/*    */ import com.abbcc.util.ThreeDesUtil;
/*    */ import com.abbcc.websoa.helper.XmlHelper;
/*    */ import com.abbcc.websoa.siteSync.SyncUserSite;
/*    */ import com.abbcc.websoa.sys.CommonConst;
/*    */ import com.abbcc.websoa.sys.SysInfo;
/*    */ import java.io.File;
/*    */ import java.io.PrintStream;
/*    */ import javax.annotation.Resource;
/*    */ import javax.jws.WebService;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.xml.ws.WebServiceContext;
/*    */ import javax.xml.ws.handler.MessageContext;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ 
/*    */ @WebService(endpointInterface="com.abbcc.websoa.siteSync.SyncUserSite")
/*    */ public class SyncUserSiteImpl
/*    */   implements SyncUserSite
/*    */ {
/* 26 */   private Log log = LogFactory.getLog(getClass());
/*    */ 
/*    */   @Resource
/*    */   private WebServiceContext context;
/*    */ 
/*    */   public String syncUserSite(String userName, String sourcePath, String tarPath, String identCode) {
/*    */     try {
/* 34 */       MessageContext ctx = this.context.getMessageContext();
/* 35 */       HttpServletRequest request = 
/* 36 */         (HttpServletRequest)ctx
/* 36 */         .get("HTTP.REQUEST");
/* 37 */       String hostIP = request.getRemoteAddr();
/* 38 */       this.log.info("hostIP->" + hostIP);
/* 39 */       System.out.println("hostIP->" + hostIP);
/* 40 */       if (!hostIP.equals(CommonConst.SYSINFO.hostip)) {
/* 41 */         return "request ip error!";
/*    */       }
/* 43 */       identCode = ThreeDesUtil.decryptMode(identCode);
/* 44 */       String nowIdentCode = userName + sourcePath + tarPath + 
/* 45 */         CommonConst.SYSINFO.deskey;
/* 46 */       if (!identCode.equals(nowIdentCode)) {
/* 47 */         return "identity code error!";
/*    */       }
/* 49 */       userName = ThreeDesUtil.decryptMode(userName);
/* 50 */       sourcePath = ThreeDesUtil.decryptMode(sourcePath);
/* 51 */       tarPath = ThreeDesUtil.decryptMode(tarPath);
/* 52 */       String finalSourcePathString = CommonConst.SYSINFO.ftppath + 
/* 53 */         sourcePath;
/* 54 */       System.out.println("finalSourcePathString->" + 
/* 55 */         finalSourcePathString);
/* 56 */       File file = new File(finalSourcePathString);
/* 57 */       if (!file.exists()) {
/* 58 */         return "file not exist!";
/*    */       }
/* 60 */       String result = new ZipHelper().unZip(tarPath, 
/* 61 */         finalSourcePathString);
/* 62 */       file.delete();
/* 63 */       if (result.equals("true")) {
/* 64 */         return "true";
/*    */       }
/* 66 */       return result;
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/* 70 */       return "false--->" + e.toString();
/*    */     }
/*    */   }
/*    */ 
/*    */   public String syncUserResource(String userName, String sourcePath, String tarPath, String identCode)
/*    */   {
/* 76 */     String result = null;
/* 77 */     result = syncUserSite(userName, sourcePath, tarPath, identCode);
/*    */ 
/* 79 */     return result;
/*    */   }
/*    */ 
/*    */   public String setXmlCon(String nodeName, String nodeVal) {
/*    */     try {
/* 84 */       return XmlHelper.setSysInfoXml(nodeName, nodeVal);
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/* 88 */       return "false--->" + e.toString();
/*    */     }
/*    */   }
/*    */ }

/* Location:           G:\software\abbcc_websoa.jar
 * Qualified Name:     com.abbcc.websoa.siteSync.impl.SyncUserSiteImpl
 * JD-Core Version:    0.6.2
 */