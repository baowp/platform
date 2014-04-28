/*    */ package com.abbcc.websoa.sys;
/*    */ 
/*    */ import com.abbcc.websoa.helper.XmlHelper;
/*    */ import java.io.PrintStream;
/*    */ import java.util.Properties;
/*    */ import javax.servlet.ServletContext;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.http.HttpServlet;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ 
/*    */ public class ConfServlet extends HttpServlet
/*    */ {
/*    */   private static final long serialVersionUID = -2819896512667530335L;
/* 44 */   private static Log log = LogFactory.getLog(ConfServlet.class);
/*    */ 
/*    */   public void destroy()
/*    */   {
/* 57 */     super.destroy();
/*    */   }
/*    */ 
/*    */   public void init()
/*    */     throws ServletException
/*    */   {
/* 68 */     Properties sys = System.getProperties();
/* 69 */     System.setProperty("jmagick.systemclassloader", "no");
/* 70 */     CommonConst.SEP = sys.getProperty("file.separator");
/* 71 */     CommonConst.REALPATH = getServletContext().getRealPath(
/* 72 */       CommonConst.SEP);
/* 73 */     CommonConst.CLASSPATH = CommonConst.REALPATH + CommonConst.SEP + 
/* 74 */       "WEB-INF" + CommonConst.SEP + "classes" + CommonConst.SEP;
/* 75 */     CommonConst.SYSINFOFILEPATH = CommonConst.CLASSPATH + 
/* 76 */       CommonConst.SYSCONFFOLDER + CommonConst.SEP + CommonConst.SYSINFOFILE;
/* 77 */     XmlHelper xmlHelper = new XmlHelper();
/*    */     try {
/* 79 */       CommonConst.SYSINFO = xmlHelper.parseSysInfoFromXml(CommonConst.SYSINFOFILEPATH);
/* 80 */       System.out.println("testSysInfo-------------->" + CommonConst.SYSINFO.hostip);
/*    */     }
/*    */     catch (Exception e) {
/* 83 */       e.printStackTrace();
/* 84 */       log.error("加载site配置文件出错！");
/*    */     }
/*    */   }
/*    */ }

/* Location:           G:\software\abbcc_websoa.jar
 * Qualified Name:     com.abbcc.websoa.sys.ConfServlet
 * JD-Core Version:    0.6.2
 */