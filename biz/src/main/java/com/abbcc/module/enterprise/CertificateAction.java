package com.abbcc.module.enterprise;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//import magick.MagickException;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.abbcc.action.FileUploadAction;
import com.abbcc.common.CommonConst;
import com.abbcc.helper.ImageMagickHelper;
import com.abbcc.models.AbcAttachment;
import com.abbcc.models.AbcCertificate;
import com.abbcc.models.AbcEnterpcontact;
import com.abbcc.models.AbcEnterprise;
import com.abbcc.models.AbcLog;
import com.abbcc.models.AbcUser;
import com.abbcc.module.toolbox.Recycle;
import com.abbcc.service.AttachmentService;
import com.abbcc.service.CertificateService;
import com.abbcc.service.LogService;
import com.abbcc.service.UserService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.StringUtil;
import com.abbcc.util.checkKey.CheckKey;
import com.abbcc.util.constant.ModelType;

public class CertificateAction extends FileUploadAction<AbcCertificate> {
	public int stepType;
	public String sourceCate;
	public int sourceSort;
	public String targetCate;
	public int targetSort;
	private CertificateService certificateService;
	private UserService userService;
	private AttachmentService attachmentService;
	public String picText;
	public String uploadType;
	public String picPath;
	private String message;
	public String name;
	private String type;
	private String organize;
	private String attId;
	protected int pageSize = 5;
	private String myCerPic;
	private LogService logService;

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public void setAttachmentService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getMyCerPic() {
		return myCerPic;
	}

	public void setMyCerPic(String myCerPic) {
		this.myCerPic = myCerPic;
	}

	public int getPageSize() {
		return pageSize;
	}

	public CertificateService getCertificateService() {
		return certificateService;
	}

	public void setCertificateService(CertificateService certificateService) {
		this.certificateService = certificateService;
	}

	public String getAttId() {
		return attId;
	}

	public void setAttId(String attId) {
		this.attId = attId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOrganize() {
		return organize;
	}

	public void setOrganize(String organize) {
		this.organize = organize;
	}

	/**
	 * 显示证书
	 * 
	 * @return
	 */

	public String showCertificate() {

		// 加入分页
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcCertificate.class);
		if (StringUtil.isNotBlank(this.getCurrentUser().getEnterpriseId()))
			detachedCriteria
					.add(Restrictions.ne("state", CommonConst.STATEDEL));
		if (StringUtil.isNotBlank(entity.getName()))
			detachedCriteria.add(Restrictions.eq("name", entity.getName()));
		detachedCriteria.add(Restrictions.eq("enterpriseId", getCurrentUser()
				.getEnterpriseId()));
		detachedCriteria.addOrder(Order.asc("sort"));
		this.startIndex = (this.page - 1) * pageSize;
		this.pageList = certificateService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);

		return "showCertificate";

	}

	// 上传证书
	public String addCertificate() throws Exception {
		if (CommonConst.ISCONTROL) {
			if (getSession().getAttribute("addState").equals(
					CommonConst.STATEINIT)) {
				DetachedCriteria dc = DetachedCriteria.forClass(entity
						.getClass());
				dc.add(Restrictions.eq("enterpriseId", getCurrentUser()
						.getEnterpriseId())).setProjection(
						Projections.count("certificateId"));
				int i = certificateService.getCountByCriteria(dc);
				if (i >= CommonConst.CERTCOUNT) {
					addFieldError("addState", "对不起，您目前还不是高级会员，证书的发布数量不能超过"
							+ CommonConst.CERTCOUNT
							+ "条!<a href=\"/user/upgrade/show\">升级后将不受限制</a>");
					return INPUT;
				}
			}
		}
		if (!CheckKey.checkKey(entity.getName())) {
			this.addFieldError("name", "存在非法字符！");
			return INPUT;
		}
		if (!CheckKey.checkKey(entity.getOrganize())) {
			this.addFieldError("organize", "存在非法字符！");
			return INPUT;
		}
		Calendar cal = Calendar.getInstance();
		AbcEnterprise ent = (AbcEnterprise) getSession().getAttribute(
				"abbccEnterprise");
		entity.setEnterpriseId(ent.getEnterpriseId());
		entity.setState(CommonConst.STATEINIT);
		entity.setAddTime(cal.getTime());
		entity.setSort(newSort());
		certificateService.save(entity);
		logService.savaLog("证书", entity.getName(), CommonConst.LOGSAVE);

		if (!("".equals(picPath))) {
			InetAddress localhost = InetAddress.getLocalHost();
			String ip = localhost.getHostAddress();
			AbcAttachment att = new AbcAttachment();
			att.setBelongType(ModelType.AJ);
			att.setUserId(this.getCurrentUser().getUserId());
			att.setBelongId(entity.getCertificateId());
			att.setType(CommonConst.CERTIFICATEPICTURE);
			att.setServerPath(picPath);
			att.setUploadTime(new Date());
			att.setServerIp(ip);
			attachmentService.save(att);
			entity.setAttachmentId(att.getAttId());
			certificateService.saveOrUpdate(entity);
		}
		result = StringUtil.encode(CommonConst.ADDSUCCESS);
		return "returnResult";

	}

	// 显示修改证书页面
	public String showUpdateCertificate() {
		AbcCertificate cerUpdate = certificateService.findById(entity
				.getCertificateId());
		AbcAttachment att = new AbcAttachment();
		att.setBelongId(cerUpdate.getCertificateId());
		List list = attachmentService.findByExample(att);
		if (list == null) {
			return "showUpdateCertificate";
		}
		AbcAttachment attachment = (AbcAttachment) list.iterator().next();
		getRequest().setAttribute("cerPic", attachment);
		getRequest().setAttribute("Certificate", cerUpdate);
		return "showUpdateCertificate";

	}

	// 修改证书
	public String certificateUpdate() throws UnknownHostException
		 {
		if (!CheckKey.checkKey(entity.getName())) {
			this.addFieldError("name", "存在非法字符！");
			return INPUT;
		}
		if (!CheckKey.checkKey(entity.getOrganize())) {
			this.addFieldError("organize", "存在非法字符！");
			return INPUT;
		}
		AbcUser user = (AbcUser) getSession().getAttribute(
				CommonConst.SESSIONUSER);
		AbcAttachment att = new AbcAttachment();
		att.setBelongId(entity.getCertificateId());
		att.setBelongType(ModelType.AJ);
		List<AbcAttachment> list = attachmentService.findByExample(att);
		if (list.size() > 0) {
			list.get(0).setServerPath(picPath);
			attachmentService.saveOrUpdate(list.get(0));
		}
		AbcCertificate cert = certificateService.findById(entity
				.getCertificateId());
		cert.setCertificateId(entity.getCertificateId());
		cert.setType(entity.getType());
		cert.setName(entity.getName());
		cert.setOrganize(entity.getOrganize());
		certificateService.saveOrUpdate(cert);
		logService.savaLog("证书", entity.getName(), CommonConst.LOGUPDATE);
		result = StringUtil.encode(CommonConst.EDITSUCCESS);
		return "returnResult";

	}

	// 删除证书（更改状态）
	@Recycle
	public String delCertificate() {
		certificateService.delete(entity);
		logService.savaLog("证书", entity.getName(), CommonConst.LOGDEL);
		result = StringUtil.encode(CommonConst.DELSUCCESS);
		return "returnResult";

	}

	public String certificateDelWatermark() {
		AttachmentService attachmentService = (AttachmentService) BeansFactory
				.get("attachmentService");
		AbcAttachment attachment = new AbcAttachment();
		attachment.setBelongId(entity.getCertificateId());
		List list = attachmentService.findByExample(attachment);
		AbcAttachment att = (AbcAttachment) list.iterator().next();
		String[] name = att.getServerPath().split("\\.");
		String newName = name[0] + "_1." + name[1];
		ImageMagickHelper image = new ImageMagickHelper();
		image.addTextToPicture(att.getServerPath(), newName, "", 10, false);
		return "json";
	}

	public String step() {
		AbcUser user = getCurrentUser();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entity
				.getClass());
		detachedCriteria.add(Restrictions.eq("enterpriseId",
				user.getEnterpriseId()));

		if (stepType == 1) {
			detachedCriteria.addOrder(Order.asc("sort"));
			detachedCriteria.add(Restrictions.gt("sort", sourceSort));
		} else {
			detachedCriteria.addOrder(Order.desc("sort"));
			detachedCriteria.add(Restrictions.lt("sort", sourceSort));
		}
		pageList = certificateService
				.findPageByCriteria(detachedCriteria, 1, 0);
		for (AbcCertificate ac : (List<AbcCertificate>) pageList.getItems()) {
			changeSort(sourceCate, sourceSort, ac.getCertificateId(),
					ac.getSort());
		}
		return "returnResult";
	}

	private int[] changeSort(String sourceCate, int sourceSort,
			String targetCate, int targetSort) {
		if (sourceCate == null || sourceSort == 0 || targetCate == null
				|| targetSort == 0)
			return new int[0];
		String[] sql = new String[2];
		sql[0] = "update abc_certificate set sort=" + targetSort
				+ " where certificate_id='" + sourceCate + "'";
		sql[1] = "update abc_certificate set sort=" + sourceSort
				+ " where certificate_id='" + targetCate + "'";
		return certificateService.batchUpdate(sql);
	}

	private Integer newSort() {
		String hql = "select max(sort) from AbcCertificate";

		@SuppressWarnings("unchecked")
		List<Integer> list = (List<Integer>) certificateService.find(hql);
		Integer sort = null;
		if (list.size() > 0)
			sort = list.get(0);
		if (sort == null)
			sort = 1;
		else
			sort++;
		return sort;
	}

}
