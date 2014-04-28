package com.abbcc.module.album;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.common.ConfConst;
import com.abbcc.helper.ImageMagickHelper;
import com.abbcc.models.AbcAlbum;
import com.abbcc.models.AbcAttachment;
import com.abbcc.models.AbcUser;
import com.abbcc.models.AbcWatermark;
import com.abbcc.service.AttachmentService;
import com.abbcc.service.LogService;
import com.abbcc.service.UserService;
import com.abbcc.util.IDUtil;
import com.abbcc.util.StringUtil;
import com.abbcc.util.constant.ModelType;
import org.springframework.beans.factory.annotation.Autowired;

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
import java.util.Date;
import java.util.List;

public class UploadUtilAction extends BaseAction<AbcAlbum> {
	@Autowired
	private AttachmentService attachmentService;
	protected String relativeFolder;
	protected String relativeFileFolder;
	@Autowired
	protected UserService userService;
	public String sessionId;
	public String username;
	public String userId;
	private static final int BUFFER_SIZE = 16 * 1024;
	// 保存文件的目录路径
	protected String savePath = ConfConst.FOLDER_UPLOAD;// 缺省值
	// 模块名
	protected String module = CommonConst.FOLDER_PICTURE;// 缺省值
	private File fileupload;

	// Struts2拦截器获得的文件名,命名规则，File的名字+FileName
	// 如此处为 'fileupload' + 'FileName' = 'fileuploadFileName'
	private String fileuploadFileName; // 上传来的文件的名字
	public int sessionSize;

	public File getFileupload() {
		return fileupload;
	}

	public void setFileupload(File fileupload) {
		this.fileupload = fileupload;
	}

	@Autowired
	private LogService logService;

	public String getFileuploadFileName() {
		return fileuploadFileName;
	}

	public void setFileuploadFileName(String fileuploadFileName) {
		this.fileuploadFileName = fileuploadFileName;
	}

	public String uploadFiles() throws Exception {
		/*AbcUser au = getCurrentUser();
		CacheService cacheService = (CacheService) BeansFactory
				.get("cacheService");
		Map session = (Map) cacheService.get(sessionId.split(",")[sessionId
				.split(",").length - 1]);
				if (CommonConst.ISCONTROL) {
			if (session.get("addState").equals(CommonConst.STATEINIT)) {
				DetachedCriteria dc = DetachedCriteria
						.forClass(AbcAttachment.class);
				dc.add(Restrictions.eq("userId", ((AbcUser) session
						.get(CommonConst.SESSIONUSER)).getUserId()))
						.add(Restrictions.eq("belongType", ModelType.AD))
						.add(Restrictions.eq("type", CommonConst.PICTURE))
						.setProjection(Projections.count("attId"));
				int count = attachmentService.getCountByCriteria(dc);
				if (count >= CommonConst.PICCOUNT) {
					output("对不起，您目前还不是高级会员,您只能上传" + CommonConst.PICCOUNT
							+ "张图片!<a href=\"/user/upgrade/show\">升级后不受限制</a>");
					return NONE;
				}
			}
		}*/
		//getSession().getAttribute("",sessionId);
		if(StringUtil.isBlank(username)){
			username = getCurrentUser().getUsername();
		}
		AbcWatermark aw = new AbcWatermark();
		String name = username.toLowerCase();
		AbcUser au = userService.getUserByUsername(name);
		AbcWatermark aww = new AbcWatermark();
		aww.setEnterpriseId(au.getEnterpriseId());
		List<AbcWatermark> list = baseService.findByExample(aww);
		if(list.size()!=0)
			aw = list.get(0);
		else
			aw=null;
		
		setRelativeFolder(name);
		String dstDir = ConfConst.FILE_UPLOAD_DIR + relativeFileFolder;
		File dirFile = new File(dstDir);
		if (!dirFile.exists())
			dirFile.mkdirs();
		String newFilename = setFilename(fileuploadFileName);
		String dstPath = dstDir + CommonConst.SEP + newFilename;
		System.out.println("文件路径：" + dstDir);
		File dstFile = new File(dstPath);
		copy(fileupload, dstFile);
		try {
			ImageMagickHelper.createAllThumbnail(dstPath,aw);
		} catch (Exception e) {
			log.info(e);
			this.result = "图片压缩失败！" + e.toString();
		}

		InetAddress localhost = InetAddress.getLocalHost();
		String ip = localhost.getHostAddress();
		// 修改attachment表里
		AbcAttachment att = new AbcAttachment();
		att.setUserId(entity.getUserId());
		att.setBelongType(ModelType.AD);
		att.setBelongId(entity.getAlbumId());
		att.setType(CommonConst.PICTURE);
		att.setServerPath(relativeFolder + "/" + newFilename);
		att.setUploadTime(new Date());
		att.setFilename(fileuploadFileName);
		att.setServerIp(ip);
		att.setState(CommonConst.STATENORMAL);
		attachmentService.saveOrUpdate(att);
		logService.savaLog("图片", att.getFilename(), CommonConst.LOGSAVE);

		return null; // 这里不需要页面转向，所以返回空就可以了
	}
	public String uploadFlash() throws Exception {
		module = CommonConst.FOLDER_FLASH;
		AbcUser au = new AbcUser();
		 baseService.load(au,userId);
		String name = au.getUsername().toLowerCase();
		setRelativeFolder(name);
		String dstDir = ConfConst.FILE_UPLOAD_DIR + relativeFileFolder;
		File dirFile = new File(dstDir);
		if (!dirFile.exists())
			dirFile.mkdirs();
		String newFilename = setFilename(fileuploadFileName);
		String dstPath = dstDir + CommonConst.SEP + newFilename;
		System.out.println("文件路径：" + dstDir);
		File dstFile = new File(dstPath);
		copy(fileupload, dstFile);

		InetAddress localhost = InetAddress.getLocalHost();
		String ip = localhost.getHostAddress();
		// 修改attachment表里
		AbcAttachment att = new AbcAttachment();
		att.setUserId(au.getUserId());
		att.setBelongType(ModelType.AD);
		att.setBelongId(au.getEnterpriseId());
		att.setType(CommonConst.FLASH);
		att.setServerPath(relativeFolder + "/" + newFilename);
		att.setUploadTime(new Date());
		att.setFilename(fileuploadFileName);
		att.setServerIp(ip);
		att.setState(CommonConst.STATENORMAL);
		attachmentService.saveOrUpdate(att);
		logService.savaLog("flash", att.getFilename(), CommonConst.LOGSAVE);

		return null; // 这里不需要页面转向，所以返回空就可以了
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

	public String setFilename(String filename) {
		String[] str = filename.split("\\.");
		filename = IDUtil.getUUID() + "." + str[str.length - 1];
		return setFilename(filename, str[str.length - 1]);
	}

	public String setFilename(String filename, String ext) {
		filename = IDUtil.getUUID() + "." + ext;
		return filename;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

}
