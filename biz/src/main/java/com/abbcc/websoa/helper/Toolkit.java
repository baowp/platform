/*    */ package com.abbcc.websoa.helper;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import javax.annotation.Resource;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.xml.ws.WebServiceContext;
/*    */ import javax.xml.ws.handler.MessageContext;
/*    */ 
/*    */ public class Toolkit
/*    */ {
/*    */ 
/*    */   @Resource
/*    */   private WebServiceContext context;
/*    */ 
/*    */   public String getHostIP()
/*    */   {
/*    */     try
/*    */     {
/* 27 */       MessageContext ctx = this.context.getMessageContext();
/* 28 */       HttpServletRequest request = 
/* 29 */         (HttpServletRequest)ctx
/* 29 */         .get("HTTP.RESPONSE");
/* 30 */       System.out.println("request---------->" + request);
/* 31 */       String ip = request.getRemoteAddr();
/* 32 */       return request.toString();
/*    */     } catch (Exception e) {
/* 34 */       return "Exception_" + e.toString();
/*    */     }
/*    */   }
/*    */ }

/* Location:           G:\software\abbcc_websoa.jar
 * Qualified Name:     com.abbcc.websoa.helper.Toolkit
 * JD-Core Version:    0.6.2
 */