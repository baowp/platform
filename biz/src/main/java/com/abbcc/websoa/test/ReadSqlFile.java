/*    */ package com.abbcc.websoa.test;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.File;
/*    */ import java.io.FileReader;
/*    */ import java.io.IOException;
/*    */ import java.io.PrintStream;
/*    */ import java.sql.Connection;
/*    */ import java.sql.DriverManager;
/*    */ import java.sql.SQLException;
/*    */ import java.sql.Statement;
/*    */ import org.apache.commons.io.FileUtils;
/*    */ //import org.junit.Test;
/*    */ 
/*    */ public class ReadSqlFile
/*    */ {
/*    */   public String getSql(String filepath)
/*    */     throws IOException
/*    */   {
/* 36 */     BufferedReader br = new BufferedReader(new FileReader(filepath));
/* 37 */     String str = "";
/*    */     String s;
/* 38 */     while ((s = br.readLine()) != null)
/*    */     {
/*    */       //String s;
/* 39 */       str = str + s + "\r\n";
/*    */     }
/* 41 */     System.out.println(str);
/* 42 */     return str;
/*    */   }
/*    */ 
/*    */   //@Test
/*    */   public void t() throws IOException {
/* 47 */     getSql("d:/t.txt");
/*    */   }
/*    */ 
/*    */   public void test()
/*    */   {
/* 52 */     Connection con = null;
/* 53 */     Statement state = null;
/* 54 */     File srcFile = null;
/* 55 */     File destFile = null;
/*    */     try {
/* 57 */       srcFile = new File("d://access.mdb");
/* 58 */       destFile = new File("d://temp.mdb");
/* 59 */       FileUtils.copyFile(srcFile, destFile);
/*    */ 
/* 61 */       Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
/* 62 */       String dbUrl = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=d://temp.mdb";
/*    */ 
/* 64 */       con = DriverManager.getConnection(dbUrl);
/* 65 */       con.setAutoCommit(false);
/* 66 */       state = con.createStatement();
/*    */ 
/* 68 */       String sql = "create   table   tSc7   (sno   char(5),aB varchar(255))";
/* 69 */       state.addBatch(sql);
/* 70 */       sql = "insert into tsc7 values ('a','b')";
/* 71 */       state.addBatch(sql);
/*    */ 
/* 73 */       sql = "create table ABC_ENTERPRISE(TYPE char(2),REGISTERED_CAPITAL varchar(20),CUSTOMER varchar(200))";
/* 74 */       state.addBatch(sql);
/* 75 */       sql = "insert into ABC_ENTERPRISE values('01','1333','电脑')";
/* 76 */       state.addBatch(sql);
/*    */ 
/* 78 */       state.executeBatch();
/* 79 */       con.commit();
/*    */     } catch (Exception e) {
/*    */       try {
/* 82 */         con.rollback();
/*    */       } catch (SQLException localSQLException) {
/*    */       }
/* 85 */       e.printStackTrace();
/*    */     } finally {
/*    */       try {
/* 88 */         state.close();
/*    */       } catch (SQLException localSQLException3) {
/*    */       }
/*    */       try {
/* 92 */         con.close();
/*    */       }
/*    */       catch (SQLException localSQLException4) {
/*    */       }
/* 96 */       System.out.println();
/*    */     }
/*    */   }
/*    */ }

/* Location:           G:\software\abbcc_websoa.jar
 * Qualified Name:     com.abbcc.websoa.test.ReadSqlFile
 * JD-Core Version:    0.6.2
 */