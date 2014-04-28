package com.abbcc.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.abbcc.common.CommonConst;
import com.abbcc.common.ConfConst;

public class FileUtil {

	/**
	 * 不存在则创建，存在则覆盖
	 * 
	 * @param path
	 * @param fileName
	 * @param content
	 * @throws IOException
	 */
	public static void write(String path, String fileName, String content)
			throws IOException {
		File dir = new File(path);
		if (!dir.exists())
			dir.mkdirs();
		File file = new File(path + "/" + fileName);
		if (!file.exists())
			file.createNewFile();

		BufferedWriter output = new BufferedWriter(new FileWriter(file));
		output.write(content);
		output.close();
	}
	
	/**
	 * 不存在则创建，存在则覆盖
	 * 
	 * @param path
	 * @param fileName
	 * @param content
	 * @throws IOException
	 */
	public static void writeFileWithUTF8(String path, String fileName, String content)
			throws IOException {
		File dir = new File(path);
		if (!dir.exists())
			dir.mkdirs();
		String filePath = path + "/" + fileName;
		File file = new File(filePath);
		if (!file.exists())
			file.createNewFile();
		// 写入 utf-8 文件,使用 FileOutputStream 类
		FileOutputStream fos = new FileOutputStream(filePath);
		OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
		osw.write(content);
		osw.flush();
		osw.close();
	}

	/**
	 * read to string
	 * 
	 * @param file
	 * @return
	 */
	public static String readToString(File file) {
		try {
			FileInputStream fr = new FileInputStream(file);
			InputStreamReader is = new InputStreamReader(fr, "UTF-8");
			StringBuilder sb = new StringBuilder();
			BufferedReader br = new BufferedReader(is);
			while (br.ready()) {
				String line = br.readLine();
				sb.append(line);
				sb.append("\n");
			}
			br.close();
			fr.close();
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
			// TODO: handle exception
		}
	}

	// 删除指定文件夹下所有文件
	// param path 文件夹完整绝对路径
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}

	// 删除文件夹
	// param folderPath 文件夹完整绝对路径

	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 删除图片(包括缩略图)
	**/
	public static void removeAll(String srcPath) {
		final String fileName = srcPath.substring(srcPath.lastIndexOf("/")+1, srcPath.lastIndexOf("."));
		File folder = new File((ConfConst.FILE_UPLOAD_DIR + srcPath))
				.getParentFile();
		if (folder.exists()) {
			for (File img : folder.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					if (name.startsWith(fileName))
						return true;
					return false;
				}
			}))
				img.delete();
		}

	}	
}
