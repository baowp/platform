package com.abbcc.module.album;

import java.io.File;
import java.io.FilenameFilter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;

//import magick.MagickException;
import net.sf.json.JSONArray;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.abbcc.action.FileUploadAction;
import com.abbcc.common.CommonConst;
import com.abbcc.common.ConfConst;
import com.abbcc.common.PaginationSupport;
import com.abbcc.models.AbcAdmin;
import com.abbcc.models.AbcAlbum;
import com.abbcc.models.AbcAttachment;
import com.abbcc.module.cache.CacheService;
import com.abbcc.module.toolbox.Recycle;
import com.abbcc.service.AlbumService;
import com.abbcc.service.AttachmentService;
import com.abbcc.service.LogService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.FileUtil;
import com.abbcc.util.StringUtil;
import com.abbcc.util.constant.AlbumType;
import com.abbcc.util.constant.ModelType;

public class AlbumAction extends FileUploadAction<AbcAlbum> {
	private AlbumService albumService;
	public String attId;
	public String desc;
	public String fileName;
	public String pageType;
	public String starPicPage;
	public String starPic;
	public String picText;
	public String hiddenId;
	public int affectRows;
	public int sessionSize;
	private LogService logService;
	Map<String, String> albumList = new LinkedHashMap<String, String>();

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public int getAffectRows() {
		return affectRows;
	}

	int startPic = 0;

	public void setAttachmentService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}

	public void setAlbumService(AlbumService albumService) {
		this.albumService = albumService;
	}

	public String show() {
		pageList = albumPageList();
		return "show";
	}

	public PaginationSupport albumPageList() {

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entity
				.getClass());
		detachedCriteria.add(Restrictions.and(Restrictions.eq("belongId", this
				.getCurrentUser().getEnterpriseId()), Restrictions.ne("state",
				CommonConst.STATEDEL)));
		PaginationSupport pageList1 = albumService.findPageByCriteria(
				detachedCriteria, pageSize, startIndex);
		if (pageList1.getTotalCount() != 0) {
			for (AbcAlbum al : (List<AbcAlbum>) pageList1.getItems()) {
				albumList.put(al.getAlbumId(), al.getName());
			}
		}
		if (pageList1.getTotalCount() == 0)
			result = CommonConst.NORESULT;
		getRequest().setAttribute("albumCount", pageList1.getTotalCount());
		deposit("albumList", albumList);
		return pageList1;
	}

	public String add() {
		entity.setAddTime(new Date());
		entity.setBelongId(this.getCurrentUser().getEnterpriseId());
		entity.setBlongType(AlbumType.AP.name());
		entity.setState(CommonConst.STATENORMAL);
		entity.setUserId(this.getCurrentUser().getUserId());
		albumService.save(entity);
		logService.savaLog("相册", entity.getName(), CommonConst.LOGSAVE);
		result = CommonConst.ADDSUCCESS;
		return "returnShow";
	}

	public String update() {
		albumService.saveOrUpdate(entity);
		logService.savaLog("相册", entity.getName(), CommonConst.LOGUPDATE);
		result = CommonConst.EDITSUCCESS;
		return "returnShow";
	}

	public String openUpdate() {
		albumService.saveOrUpdate(entity);
		result = CommonConst.EDITSUCCESS;
		logService.savaLog("相册", entity.getName(), CommonConst.LOGUPDATE);
		return "returnOpen";
	}

	@SuppressWarnings("unchecked")
	@Recycle(name = "name", module = "企业相册")
	public String del() {
		if (entity.getName().equals("默认相册")) {
			affectRows = 2;
			return JSON;
		}
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcAttachment.class);
		detachedCriteria.add(Restrictions.eq("belongId", entity.getAlbumId()));
		List<AbcAttachment> attList = attachmentService
				.findAllByCriteria(detachedCriteria);
		if (attList.size() != 0 && attList != null) {
			for (AbcAttachment att : attList) {
				removeAll(att.getServerPath());// 删除图片时删除硬盘路径
			}
		}
		albumService.delete(entity);
		logService.savaLog("相册", entity.getName(), CommonConst.LOGDEL);
		affectRows = 1;
		return JSON;
	}
	public String delFlash() {
		AbcAttachment att=new AbcAttachment();
		if(StringUtil.isNotBlank(attId))
			baseService.load(att, attId);
		FileUtil.delAllFile(att.getServerPath());
		baseService.delete(att);
		logService.savaLog("flash", att.getFilename(), CommonConst.LOGDEL);
		affectRows = 1;
		return JSON;
	}
	public String open() {
		albumList();

		List<AbcAttachment> picList = new ArrayList();
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcAttachment.class);
		detachedCriteria.add(Restrictions.and(
				Restrictions.ne("state", CommonConst.STATEDEL),
				Restrictions.eq("belongId", entity.getAlbumId())));
		detachedCriteria.addOrder(Order.desc("uploadTime"));
		@SuppressWarnings("unchecked")
		List<AbcAttachment> list = attachmentService
				.findAllByCriteria(detachedCriteria);
		for (AbcAttachment att : list) {
			String srcPath = att.getServerPath();
			String url = "";
			try {

				url = srcPath.substring(srcPath.lastIndexOf(".") + 1,
						srcPath.length());
				if (url.equalsIgnoreCase("gif"))
					url = srcPath.substring(0, srcPath.lastIndexOf(".")) + "_4"
							+ ".gif";
				else
					url = srcPath.substring(0, srcPath.lastIndexOf(".")) + "_4"
							+ ".jpg";
			} catch (Exception e) {
				url = null;

			}
			// String to1 = srcPath.substring(0, srcPath.lastIndexOf(".")) +
			// "_3."+url;
			att.setServerPath(ConfConst.FILE_SVR + url);
			picList.add(att);
		}
		deposit("attList", picList);

		return "open";
	}

	public String showFlash() {
		deposit("user", getCurrentUser());
		DetachedCriteria dc = DetachedCriteria.forClass(AbcAttachment.class);
		dc.add(Restrictions.eq("userId", getCurrentUser().getUserId()))
				.add(Restrictions.eq("belongId", getCurrentUser()
						.getEnterpriseId()))
				.add(Restrictions.eq("belongType", ModelType.AD))
				.add(Restrictions.eq("type", CommonConst.FLASH))
				.add(Restrictions.eq("state", CommonConst.STATENORMAL));
		pageList = attachmentService.findPageByCriteria(dc, pageSize,
				startIndex);
		return "showFlash";
	}
	public String editFlash(){
		AbcAttachment att = new AbcAttachment();
		baseService.load(att, attId);
		att.setFilename(fileName);
		baseService.update(att);
		return showFlash();
	}

	private void albumList() {
		Map<String, String> albumList = new LinkedHashMap<String, String>();
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcAlbum.class);
		detachedCriteria.add(Restrictions.ne("blongType", AlbumType.BP.name()));
		detachedCriteria.add(Restrictions.and(
				Restrictions.eq("belongId", getCurrentEnt().getEnterpriseId()),
				Restrictions.ne("state", CommonConst.STATEDEL)));
		List<AbcAlbum> aList = albumService.findAllByCriteria(detachedCriteria);
		for (AbcAlbum aa : aList) {
			albumList.put(aa.getAlbumId(), aa.getName());
		}
		deposit("albumList", albumList);
	}

	// 主要用于用户选择相册时触发
	public String chooseAlbum() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcAttachment.class);
		if (StringUtil.isNotBlank(fileName))
			detachedCriteria.add(Restrictions.ilike("filename", "%" + fileName
					+ "%"));
		detachedCriteria.add(Restrictions.and(
				Restrictions.ne("state", CommonConst.STATEDEL),
				Restrictions.eq("belongId", entity.getAlbumId())));
		pageList = attachmentService.findPageByCriteria(detachedCriteria, 100,
				startIndex);
		if (pageList.getTotalCount() == 0)
			result = CommonConst.NORESULT;
		if (hiddenId == null)
			hiddenId = "";
		getRequest().setAttribute("albumIdisNull", entity.getAlbumId());
		albumPageList();
		return "showPic";
	}

	public String uploadPic() {
		Map<String, String> albumMap = new LinkedHashMap<String, String>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entity
				.getClass());
		detachedCriteria.add(Restrictions.and(Restrictions.eq("belongId", this
				.getCurrentUser().getEnterpriseId()), Restrictions.ne("state",
				CommonConst.STATEDEL)));
		List<AbcAlbum> albumList = albumService
				.findAllByCriteria(detachedCriteria);
		if (albumList.size() != 0) {
			for (AbcAlbum ab : albumList) {
				albumMap.put(ab.getAlbumId(), ab.getName());
			}
			deposit("albumMap", albumMap);
		}
		// 把session SID传到页面上
		getRequest().setAttribute("username", getCurrentUser().getUsername());
		getRequest().setAttribute("albumInfo", albumService.findById(id));
		return "uploadPage";
	}

	/**
	 * 用于照片上传
	 * 
	 * @return
	 * @throws UnknownHostException
	 * @throws MagickException
	 */
	@SuppressWarnings("unchecked")
	public String upload() throws UnknownHostException {
		if (upload != null) {
			uploadImage();
			@SuppressWarnings("unused")
			InetAddress localhost = InetAddress.getLocalHost();
			String ip = localhost.getHostAddress();
			// String[] newPicName = newFilenames.split(",");
			// sessionSize = tempAttachments.size();
			// 修改attachment表里
			if (CommonConst.ISCONTROL) {
				if (getSession().getAttribute("addState").equals(
						CommonConst.STATEINIT)) {
					DetachedCriteria dc = DetachedCriteria
							.forClass(AbcAttachment.class);
					dc.add(Restrictions.eq("userId", this.getCurrentUser()
							.getUserId()))
							.add(Restrictions.eq("belongType", ModelType.AD))
							.add(Restrictions.eq("type", CommonConst.PICTURE))
							.setProjection(Projections.count("attId"));
					int count = attachmentService.getCountByCriteria(dc);
					if (count >= CommonConst.PICCOUNT) {
						addFieldError(
								"addState",
								"免费用户现在只能上传"
										+ CommonConst.PICCOUNT
										+ "张!<a href=\"/user/upgrade/show\">升级后不受限制</a>");
						return "uploadPictrue";
					}
				}
			}
			for (int j = 0; j < tempAttachments.size(); j++) {
				AbcAttachment attachment = (AbcAttachment) tempAttachments
						.get(j);
				AbcAdmin admin = getCurrentAdmin();
				if (admin != null)
					attachment.setUserId(admin.getAdminId());
				else
					attachment.setUserId(getCurrentUser().getUserId());
				// attachment.setFilename(newFilename);
				attachment.setBelongType(ModelType.AD);
				attachment.setBelongId(entity.getAlbumId());
				attachment.setType(CommonConst.PICTURE);
				// attachment.setServerPath(relativeFolder + "/" +
				// newPicName[i]);
				attachment.setUploadTime(new Date());
				// attachment.setFilename(getUploadFileName().get(i));
				attachment.setServerIp(ip);
				attachment.setState(CommonConst.STATENORMAL);
				uploadFilePath = relativeFolder + "/" + newFilename;
				// attachment.setServerPath(uploadFilePath);
				attachmentService.saveOrUpdate(attachment);
				logService.savaLog("图片", attachment.getFilename(),
						CommonConst.LOGSAVE);
			}

		}
		if (pageType != null) {
			redirectDomain();
			return "returnChoose";
		}
		return "returnOpen";
	}

	/**
	 * 用于批量上传
	 **/
	public String uploadAll() throws UnknownHostException {
		if (upload != null) {
			uploadImage();
			@SuppressWarnings("unused")
			List<AbcAttachment> tempAttachments = (List) getSession()
					.getAttribute(CommonConst.SESSIONATTACHMENTS);
			InetAddress localhost = InetAddress.getLocalHost();
			String ip = localhost.getHostAddress();
			String[] newPicName = newFilenames.split(",");
			sessionSize = tempAttachments.size();
			// 修改attachment表里
			for (int i = 0; i < upload.size(); i++) {
				sessionSize--;
				AbcAttachment att = attachmentService.findById(tempAttachments
						.get(sessionSize).getAttId());
				att.setUserId(this.getCurrentUser().getUserId());
				att.setBelongType(ModelType.AD);
				att.setBelongId(entity.getAlbumId());
				att.setType(CommonConst.PICTURE);
				att.setServerPath(relativeFolder + "/" + newPicName[i]);
				att.setUploadTime(new Date());
				att.setFilename(getUploadFileName().get(i));
				att.setServerIp(ip);
				att.setState(CommonConst.STATENORMAL);
				attachmentService.saveOrUpdate(att);
				logService
						.savaLog("图片", att.getFilename(), CommonConst.LOGSAVE);
				tempAttachments.remove(sessionSize);
			}

		}
		return SUCCESS;
	}

	public String picDel() {
		AbcAttachment att = attachmentService.findById(attId);
		removeAll(att.getServerPath());// 删除图片时删除硬盘路径
		attachmentService.delete(att);
		logService.savaLog("图片", att.getFilename(), CommonConst.LOGDEL);
		result = CommonConst.DELSUCCESS;
		return "returnOpen";
	}

	private void removeAll(String srcPath) {
		final String fileName = srcPath.replaceAll("^.*/|\\.jpg$", "");
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

	// 修改相片简介
	public String picUpdate() {
		AbcAttachment att = attachmentService.findById(attId);
		att.setFiledesc(desc);
		att.setFilename(fileName);
		attachmentService.saveOrUpdate(att);
		logService.savaLog("相册简介", att.getFilename(), CommonConst.LOGUPDATE);
		result = CommonConst.EDITSUCCESS;
		return "returnOpen";
	}

	public String picJsonUpdate() {
		AbcAttachment att = attachmentService.findById(attId);
		att.setFiledesc(desc);
		att.setFilename(fileName);
		attachmentService.saveOrUpdate(att);
		return JSON;
	}

	public String openPic() {
		if (starPic != null)
			startPic = Integer.parseInt(starPic) - 1;
		if (starPicPage != null) {
			startPic = page - 1;

		}
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcAttachment.class);
		detachedCriteria.add(Restrictions.and(
				Restrictions.ne("state", CommonConst.STATEDEL),
				Restrictions.eq("belongId", entity.getAlbumId())));
		detachedCriteria.addOrder(Order.desc("uploadTime"));
		pageList = attachmentService.findPageByCriteria(detachedCriteria, 1,
				startPic);
		// 把所有路径加上_3
		for (AbcAttachment at : (List<AbcAttachment>) pageList.getItems()) {
			String[] name = at.getServerPath().split("\\.");
			String newName = name[0] + "_3." + "jpg";
			at.setServerPath(newName);
		}

		getRequest().setAttribute("starPic", startPic);
		getRequest().setAttribute("albumInfo", albumService.findById(id));
		getRequest().setAttribute("picInfo", attachmentService.findById(attId));

		return "picInfo";
	}

	@SuppressWarnings({ "unchecked", "unchecked" })
	public String getPicByJson() {
		List<AbcAttachment> picList = new ArrayList();
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcAttachment.class);
		detachedCriteria.add(Restrictions.and(
				Restrictions.ne("state", CommonConst.STATEDEL),
				Restrictions.eq("belongId", entity.getAlbumId())));
		detachedCriteria.addOrder(Order.desc("uploadTime"));
		@SuppressWarnings("unchecked")
		List<AbcAttachment> list = attachmentService
				.findAllByCriteria(detachedCriteria);
		for (AbcAttachment att : list) {
			String srcPath = att.getServerPath();

			String url = srcPath.substring(srcPath.lastIndexOf(".") + 1,
					srcPath.length());
			if (url.equalsIgnoreCase("gif"))
				url = srcPath.substring(0, srcPath.lastIndexOf(".")) + "_4"
						+ ".gif";
			else
				url = srcPath.substring(0, srcPath.lastIndexOf(".")) + "_4"
						+ ".jpg";

			// String to1 = srcPath.substring(0, srcPath.lastIndexOf(".")) +
			// "_3."+url;
			att.setServerPath(ConfConst.FILE_SVR + url);
			picList.add(att);
		}
		@SuppressWarnings("unchecked")
		JSONArray jsonArray = JSONArray.fromObject(picList);
		this.result = jsonArray.toString();
		return "json2";
	}

	public String showUploadPage() {
		albumPageList();
		if(StringUtil.isBlank(entity.getAlbumId())){
			Map aa = albumList;
			Set<String> key = aa.keySet();
			if (key.size() != 0) {
				if (key != null)
					entity.setAlbumId((String) key.iterator().next());
			}
		}
		
		
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcAttachment.class);
		detachedCriteria.add(Restrictions.and(
				Restrictions.ne("state", CommonConst.STATEDEL),
				Restrictions.eq("belongId", entity.getAlbumId())));
		detachedCriteria.addOrder(Order.desc("attId"));
		pageList = attachmentService.findPageByCriteria(detachedCriteria, 100,
				startIndex);
		getRequest().setAttribute("albumIdisNull", entity.getAlbumId());
			if(StringUtil.isNotBlank(pageType))
				if(pageType.equals("xheditor"))
					return "showPicByXheditor";
		return "showPic";
	}
	public String showFlashPage() {
		
		DetachedCriteria dc = DetachedCriteria.forClass(AbcAttachment.class);
		dc.add(Restrictions.eq("userId", getCurrentUser().getUserId()))
				.add(Restrictions.eq("belongId", getCurrentUser()
						.getEnterpriseId()))
				.add(Restrictions.eq("belongType", ModelType.AD))
				.add(Restrictions.eq("type", CommonConst.FLASH))
				.add(Restrictions.eq("state", CommonConst.STATENORMAL));
		pageList = attachmentService.findPageByCriteria(dc, pageSize,
				startIndex);
		if(StringUtil.isNotBlank(pageType))
			if(pageType.equals("xheditor"))
				return "showFlashByXheditor";
		return "showFlash1";
	}
	private void redirectDomain() {
		String redirect = "";
		Cookie[] cookie = getRequest().getCookies();
		for (Cookie c : cookie) {
			if (c.getName().equals("redirectDomain"))
				redirect = c.getValue();
		}
		if (!redirect.isEmpty()) {
			redirect = "http://" + redirect + "/user/album/";
		}
		setRedirectDomain(redirect);
	}
}
