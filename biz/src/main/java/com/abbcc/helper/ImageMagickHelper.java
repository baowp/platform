/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "fgh.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-5           Wangjin                      initial
 */

package com.abbcc.helper;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import magick.CompositeOperator;
import magick.DrawInfo;
import magick.ImageInfo;
import magick.MagickException;
import magick.MagickImage;
import magick.PixelPacket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.abbcc.common.CommonConst;
import com.abbcc.common.ConfConst;
import com.abbcc.models.AbcEnterprise;
import com.abbcc.models.AbcUser;
import com.abbcc.models.AbcWatermark;
import com.abbcc.module.album.WatermarkAction;
import com.abbcc.service.BaseService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.FileUtil;
import com.abbcc.util.StringUtil;

/**
 * *ImageMagickHelper.java
 */
public class ImageMagickHelper {
	private Log log = LogFactory.getLog(this.getClass());
	static {
		System.setProperty("jmagick.systemclassloader", "no");
	}

	/**
	 * 压缩图片
	 * 
	 * @param filePath
	 *            源文件路径
	 * @param toPath
	 *            缩略图路径 *
	 * @param size
	 *            设定生成图片大小，如果宽度超过size执行压缩
	 * 
	 */
	public static void createThumbnail(String filePath, String toPath, int size)
			throws MagickException {
		filePath = CommonConst.REALPATH + StringUtil.pathReplace(filePath);
		toPath = CommonConst.REALPATH + StringUtil.pathReplace(toPath);
		ImageInfo info = null;
		MagickImage image = null;
		Dimension imageDim = null;
		MagickImage scaled = null;
		try {
			info = new ImageInfo(filePath);
			image = new MagickImage(info);
			imageDim = image.getDimension();
			int wideth = imageDim.width;
			int height = imageDim.height;
			// if (wideth > height) {
			if (wideth > size) {
				height = size * height / wideth;
				wideth = size;
			}
			if (height > wideth) {
				height = (wideth / height) * size;
				height = size;
			}
			scaled = image.scaleImage(wideth, height);// 小图片文件的大小.
			scaled.setFileName(toPath);
			scaled.writeImage(info);
		} finally {
			if (scaled != null) {
				scaled.destroyImages();
			}
		}
	}

	/**
	 * 调用命令行压缩图片
	 * 
	 * @param filePath
	 * @param toPath
	 * @param size
	 * @throws MagickException
	 * @throws Exception
	 */
	public static void createThumbnailByCMD(String filePath, String toPath,
			int size, AbcWatermark aw) throws MagickException, Exception {
		BaseService baseService = (BaseService) BeansFactory.get("baseService");
		String cmd;
		String domain = "";
		String entName = "";
		String f = "";
		filePath = StringUtil.pathReplace(filePath);
		toPath = StringUtil.pathReplace(toPath);

		File _file = new File(filePath); // 读入文件
		Image src = javax.imageio.ImageIO.read(_file); // 构造Image对象
		int wideth = src.getWidth(null); // 得到源图宽
		int height = src.getHeight(null); // 得到源图长
		if (wideth > size || height > size) {
			wideth = height = size;
		}
		// double length = ((double) size) * 1.5;
		if (aw == null)
			cmd = ConfConst.OS_PRFIX + " convert -resize " + wideth + "x"
					+ height + " " + ConfConst.IMAGEMAGICK_PARAM + " "
					+ filePath + " " + toPath;
		else {
			AbcEnterprise ae = (AbcEnterprise) baseService.findById(
					AbcEnterprise.class, aw.getEnterpriseId());
			AbcUser au = (AbcUser) baseService.findById(AbcUser.class,
					ae.getUserId());
			f = ConfConst.FILE_UPLOAD_DIR + "watermark" + CommonConst.SEP
					+ au.getUsername() + CommonConst.SEP;
			FileUtil.writeFileWithUTF8(f, "watermark.txt", ae.getName());

			if (StringUtil.isNotBlank(aw.getElocation())) {
				entName = "-font " + ConfConst.FONT_PATH
						+ " -quality 100  -fill " + aw.getFontColor()
						+ " -pointsize "
						+ aw.getFontSize().replaceAll("px", "") + "  -gravity "
						+ aw.getElocation() + " -annotate 0 @" + f
						+ "watermark.txt ";
				/*
				 * entName = " -font " + ConfConst.FONT_PATH + " -fill " +
				 * aw.getFontColor() + " -pointsize " +
				 * aw.getFontSize().replaceAll("px", "") +
				 * " -gravity "+aw.getElocation()+" -annotate 0"+" @" + f +
				 * "watermark.txt ";
				 */
			}
			if (StringUtil.isNotBlank(aw.getDlocation())) {
				domain = "-fill " + aw.getFontColor() + " -pointsize "
						+ aw.getFontSize().replaceAll("px", "") + "  -gravity "
						+ aw.getDlocation() + " -annotate 0 " + new WatermarkAction().enterprisePath(au.getEnterpriseId())
						+ " ";

				/*
				 * doamin = "-font " + ConfConst.FONT_PATH + "  -pointsize " +
				 * aw.getFontSize().replaceAll("px", "") + " -fill " +
				 * aw.getFontColor() + " -pointsize " +
				 * aw.getFontSize().replaceAll("px", "") +
				 * " -gravity "+aw.getDlocation()+ " -annotate 0 " +
				 * au.getDomain() + " ";
				 */
			}
			cmd =ConfConst.OS_PRFIX + "convert  -resize " + wideth + "x" + height + " " + entName
					+ domain + filePath + " " + toPath;
			/*
			 * cmd = ConfConst.OS_PRFIX + "convert -resize " + wideth + "x" +
			 * height + " -quality 100 " + filePath + entName + " " + doamin +
			 * toPath;
			 */
		}
		String ls_1;

		System.out.println(cmd);
		Process p = Runtime.getRuntime().exec(cmd);
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(p.getInputStream()));
		while ((ls_1 = bufferedReader.readLine()) != null)
			System.out.println(ls_1);
		// FileUtil.delFolder(f + "watermark.txt");
		System.out.println("p.waitFor()----------->" + p.waitFor());
	}

	/**
	 * 水印(图片logo)
	 * 
	 * @param filePath
	 *            源文件路径
	 * @param toImg
	 *            修改图路径
	 * @param logoPath
	 *            logo图路径
	 * @throws MagickException
	 */
	public static void initLogoImg(String filePath, String toImg,
			String logoPath) throws MagickException {
		filePath = CommonConst.REALPATH + StringUtil.pathReplace(filePath);
		toImg = CommonConst.REALPATH + StringUtil.pathReplace(toImg);
		logoPath = CommonConst.REALPATH + StringUtil.pathReplace(logoPath);
		ImageInfo info = new ImageInfo();
		MagickImage fImage = null;
		MagickImage sImage = null;
		MagickImage fLogo = null;
		MagickImage sLogo = null;
		Dimension imageDim = null;
		Dimension logoDim = null;
		try {
			fImage = new MagickImage(new ImageInfo(filePath));
			imageDim = fImage.getDimension();
			int width = imageDim.width;
			int height = imageDim.height;
			if (width > 660) {
				height = 660 * height / width;
				width = 660;
			}
			sImage = fImage.scaleImage(width, height);

			fLogo = new MagickImage(new ImageInfo(logoPath));
			logoDim = fLogo.getDimension();
			int lw = logoDim.width;
			int lh = logoDim.height;
			sLogo = fLogo.scaleImage(lw, lh);

			sImage.compositeImage(CompositeOperator.AtopCompositeOp, sLogo,
					(width - lw) / 2, height / 2);
			sImage.setFileName(toImg);
			sImage.writeImage(info);
		} finally {
			if (sImage != null) {
				sImage.destroyImages();
			}
		}
	}

	/**
	 * 水印文字
	 * 
	 * @param filePath
	 * @param text
	 * @param opacity
	 *            透明度 0 - 100, 0 为不透明
	 * @return
	 */
	public static void addTextToPicture(String picFrom, String picTo,
			String text, int opacity, boolean isEnt) {
		picFrom = "E:\\Abbcc\\" + StringUtil.pathReplace(picFrom);
		// picTo = CommonConst.REALPATH + StringUtil.pathReplace(picTo);
		MagickImage fImage = null;
		Dimension imageDim = null;
		int poxY = 0;
		try {
			// Resize
			fImage = new MagickImage(new ImageInfo(picFrom));
			imageDim = fImage.getDimension();
			int width = imageDim.width;
			int height = imageDim.height;
			ImageInfo info = new ImageInfo(picFrom);
			MagickImage aImage = new MagickImage(info)
					.scaleImage(width, height);
			DrawInfo aInfo = new DrawInfo(info);
			aInfo.setFill(PixelPacket.queryColorDatabase("red"));
			// aInfo.setUnderColor(PixelPacket.queryColorDatabase("black"));
			aInfo.setOpacity(opacity);
			if (width > 400)
				aInfo.setPointsize(40);
			else
				aInfo.setPointsize(20);

			// 注意这里解决中文字体问题，有以下两行才可正常显示
			String fontPath = ConfConst.FONT_PATH;
			aInfo.setFont(fontPath);
			// aInfo.setPointsize(40);
			aInfo.setTextAntialias(true);
			// Step 3: Writing The Text
			aInfo.setText(text);
			int textSize = text.length();
			int poxX = (width - (textSize * (width > 400 ? 40 : 20))) / 2;
			if (isEnt) {
				poxY = height / 2;
			} else {
				poxY = height;
			}

			aInfo.setGeometry("+" + poxX + "+" + poxY + "");
			// Step 4: Annotating
			aImage.annotateImage(aInfo);
			// Step 5: Writing the new file
			aImage.setFileName(picTo);
			aImage.writeImage(info);
			aImage.destroyImages();
			aImage = null;
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * 切图
	 * 
	 * @param imgPath
	 *            源图路径
	 * @param toPath
	 *            修改图路径
	 * @param w
	 * @param h
	 * @param x
	 * @param y
	 * @throws MagickException
	 */
	public static void cutImg(String imgPath, String toPath, int w, int h,
			int x, int y) throws MagickException {
		ImageInfo infoS = null;
		MagickImage image = null;
		MagickImage cropped = null;
		Rectangle rect = null;
		try {
			infoS = new ImageInfo(imgPath);
			image = new MagickImage(infoS);
			rect = new Rectangle(x, y, w, h);
			cropped = image.cropImage(rect);
			cropped.setFileName(toPath);
			cropped.writeImage(infoS);

		} finally {
			if (cropped != null) {
				cropped.destroyImages();
			}
		}
	}

	/**
	 * 调用命令行统统生成缩略图
	 * 
	 * @param srcPath
	 * @throws Exception
	 * @throws MagickException
	 */
	public static void createAllThumbnailByCMD(String srcPath, AbcWatermark aw)
			throws Exception {
		System.out.println("调用命令行统统生成缩略图");
		String extension = "jpg";
		if (srcPath.substring(srcPath.lastIndexOf(".") + 1, srcPath.length())
				.equalsIgnoreCase("gif"))
			extension = "gif";
		// to5主要用于相册显示图片
		String to5 = srcPath.substring(0, srcPath.lastIndexOf(".")) + "_3b."
				+ extension;
		String to1 = srcPath.substring(0, srcPath.lastIndexOf(".")) + "_3."
				+ extension;
		String to2 = srcPath.substring(0, srcPath.lastIndexOf(".")) + "_4."
				+ extension;
		String to3 = srcPath.substring(0, srcPath.lastIndexOf(".")) + "_5."
				+ extension;
		String to4 = srcPath.substring(0, srcPath.lastIndexOf(".")) + "_6."
				+ extension;
		String to7 = srcPath.substring(0, srcPath.lastIndexOf(".")) + "_7."
				+ extension;
		String to8 = srcPath.substring(0, srcPath.lastIndexOf(".")) + "_8."
				+ extension;
		createThumbnailByCMD(srcPath, to5, CommonConst.THUMBNAIL_ALBUM, aw);
		createThumbnailByCMD(srcPath, to1, CommonConst.THUMBNAIL_BIG, aw);
		createThumbnailByCMD(srcPath, to2, CommonConst.THUMBNAIL_MIDDLE, aw);
		createThumbnailByCMD(srcPath, to3, CommonConst.THUMBNAIL_SMALL, aw);
		createThumbnailByCMD(srcPath, to4, CommonConst.THUMBNAIL_LITTLE, aw);
		createThumbnailByCMD(srcPath, to7, CommonConst.THUMBNAIL_BIG2, aw);
		createThumbnailByCMD(srcPath, to8, CommonConst.THUMBNAIL_SMALL2, aw);
	}

	/**
	 * 调用jmagick生成缩略图
	 * 
	 * @param srcPath
	 * @throws MagickException
	 * @throws Exception
	 */
	public static void createAllThumbnailByJmagick(String srcPath)
			throws Exception {
		System.out.println("用imagick生成");
		String to1 = srcPath.substring(0, srcPath.lastIndexOf(".")) + "_3.jpg";
		String to2 = srcPath.substring(0, srcPath.lastIndexOf(".")) + "_4.jpg";
		String to3 = srcPath.substring(0, srcPath.lastIndexOf(".")) + "_5.jpg";
		createThumbnail(srcPath, to1, CommonConst.THUMBNAIL_BIG);
		createThumbnail(srcPath, to2, CommonConst.THUMBNAIL_MIDDLE);
		createThumbnail(srcPath, to3, CommonConst.THUMBNAIL_SMALL);
	}

	/**
	 * 生成缩略图
	 * 
	 * @param srcPath
	 * @throws Exception
	 */
	public static void createAllThumbnail(String srcPath, AbcWatermark aw)
			throws Exception {
		if (ConfConst.USE_JMAGICK)
			createAllThumbnailByJmagick(srcPath);
		else
			createAllThumbnailByCMD(srcPath, aw);
	}

	public static void createSpecificThumbnail(String filePath, String toPath,
			int size) throws Exception {
		if (ConfConst.USE_JMAGICK)
			createThumbnail(filePath, toPath, size);
		else
			createThumbnailByCMD(filePath, toPath, size, null);
	}
}
