package com.abbcc.module.log;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.abbcc.action.BaseAction;
import com.abbcc.action.FileUploadAction;
import com.abbcc.common.CommonConst;
import com.abbcc.common.ConfConst;
import com.abbcc.models.AbcAdmin;
import com.abbcc.models.AbcAttachment;
import com.abbcc.models.AbcUser;
import com.abbcc.service.AttachmentService;
import com.abbcc.util.StringUtil;

public class BackupAction extends FileUploadAction<AbcAttachment> {
	private AttachmentService attachmentService;

	public void setAttachmentService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}

	public String show() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entity
				.getClass());
		detachedCriteria.add(Restrictions.eq("type",
				CommonConst.ATTACHADMINBACKUP));
		pageList = attachmentService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		return "show";

	}

	// 添加备份
	public String add() {
		AbcAdmin admin = (AbcAdmin) getCurrentAdmin();
		if (admin == null) {
			return SUCCESS;
		}
		if (entity.getFilename() == null)
			return SUCCESS;
		String name = admin.getUsername().toLowerCase();
		setRelativeFolder(name);
		String dstDir = ConfConst.FILE_UPLOAD_DIR + relativeFileFolder;
		File dirFile = new File(dstDir);
		if (!dirFile.exists())
			dirFile.mkdirs();
		Runtime rt = Runtime.getRuntime();
		Process processexp = null;
		String exp = "exp " + ConfConst.USERNAME + "/" + ConfConst.PASSWORD
				+ ConfConst.URL + " file=" + dirFile + CommonConst.SEP
				+ entity.getFilename() + ".dmp";
		String dstPath = dstDir + CommonConst.SEP + entity.getFilename()
				+ ".dmp";
		try {
			processexp = rt.exec(exp);
			if (processexp.waitFor() != 0) {
				System.err.println("exit   value   =   "
						+ processexp.exitValue());
			}
			System.out.println(processexp);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		InetAddress localhost = null;
		try {
			localhost = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String ip = localhost.getHostAddress();
		entity.setType(CommonConst.ATTACHADMINBACKUP);
		entity.setServerIp(ip);
		entity.setServerPath(dstPath);
		entity.setUploadTime(new Date());
		entity.setUserId(this.getCurrentAdmin().getAdminId());
		attachmentService.save(entity);
		this.result = StringUtil.encode(CommonConst.ADDSUCCESS);
		this.savaAdminLog("日志备份", entity.getFilename(), CommonConst.LOGSAVE);
		return "returnshow";
	}

	// 恢复备份
	public String dataResume() throws IOException {

		Runtime rt = Runtime.getRuntime();
		Process processimp = null;
		// if(checkDir("f:/"+filename)){
		String imp = "imp " + ConfConst.USERNAME + "/" + ConfConst.PASSWORD
				+ ConfConst.URL + " fromuser=" + ConfConst.USERNAME
				+ " touser=" + ConfConst.USERNAME + " file="
				+ entity.getServerPath();
		int success = 0;
		try {
			processimp = rt.exec(imp);
		} catch (IOException e) {
			success = -1;

			e.printStackTrace();
		}
		this.savaAdminLog("日志还原", entity.getFilename(), CommonConst.LOGUPDATE);
		this.result = StringUtil.encode(CommonConst.EDITSUCCESS);
		// }
		return "returnshow";
	}

	public String reduktion() {
		return "returnshow";
	}

	public String del() {
		attachmentService.delete(entity);
		this.result = StringUtil.encode(CommonConst.DELSUCCESS);
		this.savaAdminLog("日志备份", entity.getFilename(), CommonConst.LOGDEL);
		return "returnshow";
	}

}
