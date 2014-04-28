/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "ZipTest.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-2-23           yixiugg                      initial
**/

package com.abbcc.util;

import java.util.Calendar;

/**
 **ZipTest.java
 **/
public class ZipTest {

	/**
	 * @param args
	 */
		 // GENERAL_PUNCTUATION 判断中文的“号

		 // CJK_SYMBOLS_AND_PUNCTUATION 判断中文的。号

		 // HALFWIDTH_AND_FULLWIDTH_FORMS 判断中文的，号

		   public static boolean isChinese(char c) {
		      Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		      if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
		        || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
		        || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
		        || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
		        || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
		        || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS){
		       return true;
		      }
		      return false;
		   }
		   public static void isChinese(String strName) {
		      char[] ch = strName.toCharArray();
		      for (int i = 0; i < ch.length; i++) {
		    char c = ch[i];
		    if(isChinese(c)==true){
		     System.out.println(isChinese(c));
		    return;
		   }else{
		     System.out.println(isChinese(c));
		     return ;
		    }
		   }
		   }
		   
		   public static void main(String[] args){
/*		    isChinese("zhongguo");
		    isChinese("中国");*/
			   
			        Calendar cal=Calendar.getInstance();//返回默认地域和时区的一个Calendar对象
			        System.out.println(cal.get(Calendar.YEAR)+"年"+cal.get(Calendar.MONTH)+"月"+cal.get(Calendar.DATE));

		  }

		 

}

