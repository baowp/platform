/*     */ package com.abbcc.websoa.test.impl;
/*     */ 
/*     */ import com.abbcc.websoa.test.HelloWorld;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import java.sql.Connection;
/*     */ import java.sql.DriverManager;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.jws.WebService;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ 
/*     */ @WebService(endpointInterface="com.abbcc.websoa.test.HelloWorld")
/*     */ public class HelloWorldImpl
/*     */   implements HelloWorld
/*     */ {
/*     */   List<String> list;
/*     */   PrintWriter out;
/*     */ 
/*     */   public String sayHello(String name)
/*     */     throws IOException
/*     */   {
/*  48 */     BufferedReader br = new BufferedReader(new FileReader("d:/out.txt"));
/*     */ 
/*  50 */     this.list = new ArrayList();
/*     */     String s;
/*  51 */     while ((s = br.readLine()) != null)
/*     */     {
/*     */      // String s;
/*  52 */       this.list.add(s);
/*     */     }
/*  54 */     br.close();
/*     */ 
/*  56 */     File file = new File("d:/out.txt");
/*  57 */     if (file.exists())
/*  58 */       file.delete();
/*  59 */     file.createNewFile();
/*  60 */     this.out = new PrintWriter(file);
/*     */ 
/*  62 */     Connection con = null;
/*  63 */     Statement state = null;
/*  64 */     File srcFile = null;
/*  65 */     File destFile = null;
/*     */     try {
/*  67 */       srcFile = new File("d://access.mdb");
/*  68 */       destFile = new File("d://temp.mdb");
/*  69 */       FileUtils.copyFile(srcFile, destFile);
/*     */ 
/*  71 */       Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
/*  72 */       String dbUrl = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=d://temp.mdb";
/*     */ 
/*  74 */       con = DriverManager.getConnection(dbUrl);
/*  75 */       con.setAutoCommit(false);
/*  76 */       state = con.createStatement();
/*     */ 
/*  78 */       if (this.list == null) {
/*  79 */         String sql = "create   table   tSc7   (sno   char(5),aB varchar(255))";
/*  80 */         state.addBatch(sql);
/*  81 */         sql = "insert into tsc7 values ('a','b')";
/*  82 */         state.addBatch(sql);
/*     */ 
/*  84 */         sql = "create table ABC_ENTERPRISE(TYPE char(2),REGISTERED_CAPITAL varchar(20),CUSTOMER varchar(200))";
/*  85 */         state.addBatch(sql);
/*  86 */         sql = name;
/*  87 */         this.out.println(sql);
/*  88 */         sql = "insert into ABC_ENTERPRISE values('01','1333','" + sql + 
/*  89 */           "')";
/*  90 */         state.addBatch(sql);
/*     */       } else {
/*  92 */         for (String sql : this.list) {
/*  93 */           state.addBatch(sql);
/*     */         }
/*     */       }
/*  96 */       state.executeBatch();
/*  97 */       con.commit();
/*     */     } catch (Exception e) {
/*     */       try {
/* 100 */         con.rollback();
/*     */       } catch (SQLException localSQLException) {
/*     */       }
/* 103 */       e.printStackTrace();
/*     */     } finally {
/*     */       try {
/* 106 */         state.close();
/*     */       } catch (SQLException localSQLException3) {
/*     */       }
/*     */       try {
/* 110 */         con.close();
/*     */       }
/*     */       catch (SQLException localSQLException4) {
/*     */       }
/* 114 */       this.out.flush();
/* 115 */       this.out.close();
/*     */     }
/*     */ 
/* 118 */     return "sayhello" + name;
/*     */   }
/*     */ }

/* Location:           G:\software\abbcc_websoa.jar
 * Qualified Name:     com.abbcc.websoa.test.impl.HelloWorldImpl
 * JD-Core Version:    0.6.2
 */