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
package com.abbcc.util;

/*
 * 字符串 DESede(3DES) 加密
 */

import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class ThreeDesUtil {

	private static final String Algorithm = "DESede"; // 定义 加密算法,可用
														// DES,DESede,Blowfish
	// keybyte为加密密钥，长度为24字节
	private static final byte[] keyBytes = { 0x22, 0x44, 0x4F, 0x58,
			(byte) 0x88, 0x10, 0x42, 0x38, 0x28, 0x21, 0x79, 0x51, (byte) 0xCB,
			(byte) 0xDD, 0x55, 0x66, 0x77, 0x28, 0x14, (byte) 0x98, 0x30, 0x40,
			0x36, (byte) 0xE2 };

	
	public static String encryptMode(String str) {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(keyBytes, Algorithm);

			// 加密
			byte[] src = str.getBytes();

			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			return byte2hex(c1.doFinal(src));
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	// keybyte为加密密钥，长度为24字节
	public static String decryptMode(String str) {
		try {
			SecretKey deskey = new SecretKeySpec(keyBytes, Algorithm);

			// 解密
			byte[] src = hex2byte(str);
			// 获取密钥提供商，不加此注册 在部分环境中会提示密码算法不存在的错误 wuyongfeng @ 2009-2-11
			Security.addProvider(new com.sun.crypto.provider.SunJCE());
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.DECRYPT_MODE, deskey);
			return new String(c1.doFinal(src));
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	// 转换成十六进制字符串
	/**
	 * public static String byte2hex(byte[] b) { String hs=""; String stmp="";
	 * 
	 * for (int n=0;n<b.length;n++) { stmp=(java.lang.Integer.toHexString(b[n] &
	 * 0XFF)); if (stmp.length()==1) hs=hs+"0"+stmp; else hs=hs+stmp; if (n<b.length-1)
	 * hs=hs+":"; } return hs.toUpperCase(); }
	 */
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int i = 0; i < b.length; i++) {
			stmp = Integer.toHexString(b[i] & 0xFF);
			if (stmp.length() == 1) {
				hs += "0" + stmp;
			} else {
				hs += stmp;
			}
		}
		return hs.toUpperCase();
	}

	public static byte[] hex2byte(String hex) throws IllegalArgumentException {
		if (hex.length() % 2 != 0) {
			throw new IllegalArgumentException();
		}
		char[] arr = hex.toCharArray();
		byte[] b = new byte[hex.length() / 2];
		for (int i = 0, j = 0, l = hex.length(); i < l; i++, j++) {
			String swap = "" + arr[i++] + arr[i];
			int byteint = Integer.parseInt(swap, 16) & 0xFF;
			b[j] = new Integer(byteint).byteValue();
		}
		return b;
	}

	public static void main(String[] args) {
		// 添加新安全算法,如果用JCE就要把它添加进去
		// Security.addProvider(new com.sun.crypto.provider.SunJCE());

		String str = "smtp.263xmail.com";
		System.out.println("加密前的字符串:" + str);

		String encoded = encryptMode(str);
		System.out.println("加密后的字符串:" + encoded);

		String decoded = decryptMode("6066EDB6EC440DEF6B503FDE47A1464C");
		// System.out.println("加密后的字符串字节码:" + byte2hex(encoded));
		System.out.println("解密后的字符串:" + decoded);
	}

}
