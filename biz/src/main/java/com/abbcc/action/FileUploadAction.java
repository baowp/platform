/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "FileUploadAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-7           baowp                      initial
 */

package com.abbcc.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.abbcc.common.CommonConst;
import com.abbcc.common.ConfConst;
import com.abbcc.helper.ImageMagickHelper;
import com.abbcc.models.AbcAdmin;
import com.abbcc.models.AbcAlbum;
import com.abbcc.models.AbcAttachment;
import com.abbcc.models.AbcUser;
import com.abbcc.models.AbcWatermark;
import com.abbcc.news.action.NewsBaseAction;
import com.abbcc.service.AlbumService;
import com.abbcc.service.AttachmentService;
import com.abbcc.service.LogService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.IDUtil;
import com.abbcc.util.constant.AlbumType;
import com.abbcc.util.constant.ModelType;

/**
 * 处理文件上传的Action类
 * 
 * @author
 * @version1.0
 */
@SuppressWarnings("serial")
public class FileUploadAction<T> extends NewsBaseAction<T> {
	protected List tempAttachments = new ArrayList();
	@Autowired
	private AlbumService albumService;
	@Autowired
	private LogService logService;
	private List<File> filedata;
	private List<String> filedataFileName;
	public String uploadType;
	private static final int BUFFER_SIZE = 16 * 1024;
	protected AttachmentService attachmentService;
	protected String title;
	// 上传文件域对象
	protected List<File> upload;
	// 上传文件名
	protected List<String> uploadFileName;

	protected List<String> newFileNameList;
	// 上传文件类型
	protected List<String> uploadContentType;
	protected String uploadFilePath;
	// 保存文件的目录路径
	protected String savePath = ConfConst.FOLDER_UPLOAD;// 缺省值
	// 模块名
	protected String module = CommonConst.FOLDER_PICTURE;// 缺省值

	protected int maximumSize = 1024 * 1024;// 缺省值

	protected int minimumSize;
	// 文件扩展名约束
	protected Set<String> extensionSet;
	// 文件格式不对或大小不对的回馈信息
	protected List<String> feedback;
	// 保存附件的相对路径（文件夹）
	protected String relativeFolder;
	protected String relativeFileFolder;
	protected String newFilename;
	protected String newFilenames = "";
	public AbcAttachment attachment;
	AbcWatermark aw = new AbcWatermark();

	// 自己封装的一个把源文件对象复制成目标文件对象
	protected void copy(File src, File dst) {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
			out = new BufferedOutputStream(new FileOutputStream(dst),
					BUFFER_SIZE);
			byte[] buffer = new byte[BUFFER_SIZE];
			int len = 0;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	protected boolean checkUploadFile() {
		feedback = new ArrayList<String>(upload.size());
		for (int i = 0; i < upload.size(); i++) {
			if (upload.get(i) == null) {
				feedback.add("");
				continue;
			}
			String[] str = uploadFileName.get(i).split("\\.");
			String extension = str.length == 1 ? "" : str[str.length - 1];
			if (extensionSet != null
					&& !extensionSet.contains(extension.toLowerCase())) {
				feedback.add("上传文件格式不对");
				return false;
			}
			if (upload.get(i).length() <= minimumSize) {
				feedback.add("上传文件应大于" + minimumSize + "字节");
				return false;
			}
			if (upload.get(i).length() > maximumSize) {
				feedback.add("上传文件不得超出" + maximumSize + "字节");
				return false;
			}
			feedback.add("");
		}
		return true;
	}

	protected boolean checkUploadFile(Set extensionSet) {
		feedback = new ArrayList<String>(upload.size());
		for (int i = 0; i < upload.size(); i++) {
			if (upload.get(i) == null) {
				feedback.add("");
				continue;
			}
			String[] str = uploadFileName.get(i).split("\\.");
			String extension = str.length == 1 ? "" : str[str.length - 1];
			if (extensionSet != null
					&& !extensionSet.contains(extension.toLowerCase())) {
				feedback.add("上传文件格式不对");
				return false;
			}
			if (upload.get(i).length() <= minimumSize) {
				feedback.add("上传文件应大于" + minimumSize + "字节");
				return false;
			}
			if (upload.get(i).length() > maximumSize) {
				feedback.add("上传文件不得超出" + maximumSize + "字节");
				return false;
			}
			feedback.add("");
		}
		return true;
	}

	/**
	 * @return
	 */
	public String doUpload() {
		AbcUser user = (AbcUser) getCurrentUser();
		if (user == null) {
			return SUCCESS;
		}

		if (upload == null)
			return SUCCESS;
		if (!checkUploadFile())
			return SUCCESS;
		String name = user.getUsername().toLowerCase();
		setRelativeFolder(name);

		// String dstDir = servletContext.getRealPath(relativeFolder);
		String dstDir = ConfConst.FILE_UPLOAD_DIR + relativeFileFolder;
		File dirFile = new File(dstDir);
		if (!dirFile.exists())
			dirFile.mkdirs();

		for (int i = 0; i < upload.size(); i++) {
			if (upload.get(i) == null)
				continue;
			newFilename = setFilename(this.getUploadFileName().get(i));
			String dstPath = dstDir + CommonConst.SEP + newFilename;
			File dstFile = new File(dstPath);
			copy(this.upload.get(i), dstFile);
			uploadFilePath = relativeFolder + "/" + newFilename;
		}

		return SUCCESS;
	}

	/**
	 * FileUploadAction 上传文件操作
	 */
	@SuppressWarnings("rawtypes")
	public String uploadImage() {
		tempAttachments = (List) this.getSession().getAttribute(
				CommonConst.SESSIONATTACHMENTS);
		if (tempAttachments == null)
			tempAttachments = new ArrayList();
		this.getSession().setAttribute(CommonConst.SESSIONATTACHMENTS,
				tempAttachments);
		String name = "";

		if (upload == null)
			return SUCCESS;
		if (!checkUploadFile())
			return SUCCESS;

		if ((AbcUser) getCurrentUser() != null) {
			AbcUser user = (AbcUser) getCurrentUser();
			name = user.getUsername().toLowerCase();
			AbcWatermark aww = new AbcWatermark();
			aww.setEnterpriseId(getCurrentUser().getEnterpriseId());
			List<AbcWatermark> list = baseService.findByExample(aww);
			if(list.size()!=0)
				aw = list.get(0);
			else
				aw=null;
		} else if (this.getCurrentAdmin() != null) {
			AbcAdmin admin = (AbcAdmin) getCurrentAdmin();
			name = "admin_" + admin.getUsername().toLowerCase();
		} else if (uploadType != null) {
			name = uploadType;
		} else {
			feedback.add("请先登陆再上传文件");
			return SUCCESS;
		}

		setRelativeFolder(name);

		// 根据服务器的文件保存地址和原文件名创建目录文件全路径
		// String dstDir = servletContext.getRealPath(relativeFolder);
		String dstDir = ConfConst.FILE_UPLOAD_DIR + relativeFileFolder;

		File dirFile = new File(dstDir);
		if (!dirFile.exists())
			dirFile.mkdirs();

		for (int i = 0; i < upload.size(); i++) {
			attachment = new AbcAttachment();
			if (upload.get(i) == null)
				continue;
			String newFilename = setFilename(this.getUploadFileName().get(i));
			attachment.setState(CommonConst.STATEINIT);
			attachment.setUploadTime(new Date());
			AbcAdmin admin = getCurrentAdmin();
			if (admin != null)
				attachment.setUserId(admin.getAdminId());
			else
				attachment.setUserId(getCurrentUser().getUserId());
			attachment.setFilename(this.getUploadFileName().get(i));
			if (newFilenames == "")
				newFilenames = newFilename;
			else
				newFilenames = newFilenames + "," + newFilename;
			String dstPath = dstDir + CommonConst.SEP + newFilename;
			System.out.println("文件路径：" + dstDir);
			File dstFile = new File(dstPath);
			copy(this.upload.get(i), dstFile);
			try {
				ImageMagickHelper.createAllThumbnail(dstPath,aw);
			} catch (Exception e) {
				log.info(e);
				this.result = "图片压缩失败！" + e.toString();
			}
			uploadFilePath = relativeFolder + "/" + newFilename;
			attachment.setServerPath(uploadFilePath);
			prepareAttach(attachment);
			if (attachmentService == null)
				attachmentService = (AttachmentService) BeansFactory
						.get("attachmentService");
			attachmentService.save(attachment);
			tempAttachments.add(attachment);
		}
		return SUCCESS;
	}

	public String uploadByJson() throws IOException {
		
		if (getCurrentUser() == null && getCurrentAdmin() == null) {
			uploadByJsonCallBackMsg("请登陆后再上传!", "");
		} else {
			String name = "";
			if (getCurrentAdmin() == null)
				name = getCurrentUser().getUsername().toLowerCase();
			else
				name = this.getCurrentAdmin().getUsername().toLowerCase();
			setRelativeFolder(name);
			String dstDir = ConfConst.FILE_UPLOAD_DIR + relativeFileFolder;
			File dirFile = new File(dstDir);
			if (!dirFile.exists())
				dirFile.mkdirs();
			for (int i = 0; i < filedata.size(); i++) {
				String newFilename = setFilename(filedataFileName.get(i));
				String dstPath = dstDir + CommonConst.SEP + newFilename;
				System.out.println("文件路径：" + dstDir);
				File dstFile = new File(dstPath);
				copy(filedata.get(i), dstFile);
				try {
					ImageMagickHelper.createAllThumbnail(dstPath,null);
				} catch (Exception e) {
					log.info(e);
					this.result = "图片压缩失败！" + e.toString();
				}

				InetAddress localhost = InetAddress.getLocalHost();
				String ip = localhost.getHostAddress();
				// 修改attachment表里
				AbcAttachment att = new AbcAttachment();
				att.setUserId(getCurrentAdmin() == null ? getCurrentUser()
						.getUserId() : getCurrentAdmin().getAdminId());
				att.setBelongType(ModelType.AD);
				att.setBelongId(returnAlbumId());
				att.setType(CommonConst.PICTURE);
				att.setServerPath(relativeFolder + "/" + newFilename);
				att.setUploadTime(new Date());
				att.setFilename(filedataFileName.get(i));
				att.setServerIp(ip);
				att.setFiledesc("xheditor");
				att.setState(CommonConst.STATEINIT);
				attachmentService.saveOrUpdate(att);

				try {
					logService.savaLog("图片", att.getFilename(),
							CommonConst.LOGSAVE);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					uploadByJsonCallBackMsg("",
							ConfConst.FILE_SVR + att.getServerPath());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
		return NONE;
	}

	/**
	 * 保存attachment前设置附件属性，供子类重写使用
	 * 
	 * @param attach
	 */
	protected void prepareAttach(AbcAttachment attach) {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<File> getUpload() {
		return upload;
	}

	public void setUpload(List<File> upload) {
		this.upload = upload;
	}

	public List<File> getFile() {
		return upload;
	}

	public void setFile(List<File> upload) {
		this.upload = upload;
	}

	public List<String> getFileFileName() {
		return uploadFileName;
	}

	public void setFileFileName(List<String> uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public List<String> getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(List<String> uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public List<String> getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(List<String> uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public List<String> getFeedback() {
		return feedback;
	}

	public void setFeedback(List<String> feedback) {
		this.feedback = feedback;
	}

	public int getMaximumSize() {
		return maximumSize;
	}

	public void setMaximumSize(int maximumSize) {
		this.maximumSize = maximumSize;
	}

	public int getminimumSize() {
		return minimumSize;
	}

	public void setminimumSize(int minimumSize) {
		this.minimumSize = minimumSize;
	}

	public Set<String> getExtensionSet() {
		return extensionSet;
	}

	public void setAllowedExtension(String allowedExtension) {
		extensionSet = new HashSet<String>();
		for (String s : allowedExtension.replaceAll("\\s", "").split(","))
			extensionSet.add(s.toLowerCase());
	}

	public String getUploadFilePath() {
		return uploadFilePath;
	}

	public void setUploadFilePath(String uploadFilePath) {
		this.uploadFilePath = uploadFilePath;
	}

	public String setFilename(String filename) {
		String[] str = filename.split("\\.");
		filename = IDUtil.getUUID() + "." + str[str.length - 1];
		return setFilename(filename, str[str.length - 1]);
	}

	public String setFilename(String filename, String ext) {
		filename = IDUtil.getUUID() + "." + ext;
		return filename;
	}

	public void setRelativeFolder(String name) {
		StringBuffer uri = new StringBuffer(getSavePath()).append('/');
		char c0 = name.charAt(0);
		uri.append(c0).append('/').append(c0);
		char c = name.charAt(1);
		if (c >= 'a' && c < 'f')
			uri.append(1);
		else if (c >= 'f' && c < 'k')
			uri.append(2);
		else if (c >= 'k' && c < 'p')
			uri.append(3);
		else if (c >= 'p' && c < 'u')
			uri.append(4);
		else if (c >= 'u' && c < 'z')
			uri.append(5);
		else
			uri.append(6);
		uri.append('/').append(name).append('/').append(module);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(new Date());
		for (String d : date.split("-"))
			uri.append('/').append(d);
		relativeFolder = uri.toString();
		relativeFileFolder = relativeFolder.replaceAll("/", CommonConst.SEP
				+ CommonConst.SEP);
	}

	public static String getUserfolder(String name) {
		StringBuffer uri = new StringBuffer("");
		char c0 = name.charAt(0);
		uri.append(c0).append(CommonConst.SEP).append(c0);
		char c = name.charAt(1);
		if (c >= 'a' && c < 'f')
			uri.append(1);
		else if (c >= 'f' && c < 'k')
			uri.append(2);
		else if (c >= 'k' && c < 'p')
			uri.append(3);
		else if (c >= 'p' && c < 'u')
			uri.append(4);
		else if (c >= 'u' && c < 'z')
			uri.append(5);
		else
			uri.append(6);
		return uri.toString();
	}

	public static String getUserPersonalfolder(String name) {
		StringBuffer uri = new StringBuffer("");
		char c0 = name.charAt(0);
		uri.append(c0).append("/").append(c0);
		char c = name.charAt(1);
		if (c >= 'a' && c < 'f')
			uri.append(1);
		else if (c >= 'f' && c < 'k')
			uri.append(2);
		else if (c >= 'k' && c < 'p')
			uri.append(3);
		else if (c >= 'p' && c < 'u')
			uri.append(4);
		else if (c >= 'u' && c < 'z')
			uri.append(5);
		else
			uri.append(6);
		uri.append("/");
		uri.append(name);
		return uri.toString();
	}

	public static String getUserfolderWithDate(String name) {
		StringBuffer uri = new StringBuffer(getUserfolder(name));
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(new Date());
		for (String d : date.split("-"))
			uri.append('/').append(d);
		return uri.toString();
	}

	protected void uploadByJsonCallBackMsg(String err, String msg)
			throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("err", err);
		map.put("msg", msg == "" ? "" : ("!" + msg));
		result = JSONObject.fromObject(map).toString();
		output(result);
	}

	protected String returnAlbumId() {
		if (getCurrentAdmin() != null)
			return "";
		AbcAlbum aa = new AbcAlbum();
		aa.setName("默认相册");
		aa.setBlongType(AlbumType.AP.name());
		aa.setBelongId(this.getCurrentUser().getEnterpriseId());
		aa.setState(CommonConst.STATENORMAL);
		aa.setUserId(getCurrentUser().getUserId());
		List<AbcAlbum> list = albumService.findByExample(aa);
		if (list.size() != 0)
			return list.get(0).getAlbumId();
		else
			return "没有默认相册";

	}

	public List<String> getNewFileNameList() {
		return newFileNameList;
	}

	public void setNewFileNameList(List<String> newFileNameList) {
		this.newFileNameList = newFileNameList;
	}

	public AttachmentService getAttachmentService() {
		return attachmentService;
	}

	public void setAttachmentService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}

	public List<String> getFiledataFileName() {
		return filedataFileName;
	}

	public void setFiledataFileName(List<String> filedataFileName) {
		this.filedataFileName = filedataFileName;
	}

	public List<File> getFiledata() {
		return filedata;
	}

	public void setFiledata(List<File> filedata) {
		this.filedata = filedata;
	}
}
