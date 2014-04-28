/*    */ package com.abbcc.websoa.test.client;
/*    */ 
/*    */ import com.abbcc.util.ThreeDesUtil;
/*    */ import com.abbcc.websoa.siteSync.SyncUserSite;
/*    */ import com.abbcc.websoa.sys.SysInfo;
/*    */ import java.io.PrintStream;
/*    */ import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
/*    */ 
/*    */ public class ServiceTest
/*    */ {
/*    */   public static void main(String[] args)
/*    */   {
/* 39 */     JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
/*    */ 
/* 51 */     factory.setServiceClass(SyncUserSite.class);
/* 52 */     factory.setAddress("http://192.168.1.101:8080/Abbcc-WEBSOA/soa/SiteSync");
/* 53 */     Object obj = factory.create();
/* 54 */     if (obj != null) {
/* 55 */       SyncUserSite sus = (SyncUserSite)obj;
/* 56 */       String userName = "ray";
/* 57 */       String sourcePath = "\\zip\\test.zip";
/* 58 */       String tarPath = "E:\\SiteFolder\\unzip";
/*    */ 
/* 64 */       userName = ThreeDesUtil.encryptMode(userName);
/* 65 */       sourcePath = ThreeDesUtil.encryptMode(sourcePath);
/* 66 */       tarPath = ThreeDesUtil.encryptMode(tarPath);
/* 67 */       String indentCode = ThreeDesUtil.encryptMode(userName + sourcePath + tarPath + "abbcc_2010_key");
/*    */ 
/* 70 */       String s = sus.setXmlCon(SysInfo.hostipNode, "127.0.0.1");
/* 71 */       System.out.println("result---------------->" + s);
/*    */     }
/*    */   }
/*    */ }

/* Location:           G:\software\abbcc_websoa.jar
 * Qualified Name:     com.abbcc.websoa.test.client.ServiceTest
 * JD-Core Version:    0.6.2
 */