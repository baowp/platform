package com.abbcc.models;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

import com.abbcc.action.FileUploadAction;
import com.abbcc.common.CommonConst;
import com.abbcc.common.ConfConst;
import com.abbcc.service.AdminService;
import com.abbcc.service.UserService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.IDUtil;
import com.abbcc.util.constant.ModelType;

/**
 * AbcAttachment entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Indexed(index = "abc")
@Entity
@Table(name = "ABC_ATTACHMENT")
public class AbcAttachment implements java.io.Serializable {

	// Fields

	private String attId;
	private String userId;
	private ModelType belongType;
	private String belongId;
	private String type;
	private String state;
	private String storeType;
	private String serverIp;
	private String serverPath;
	private String filename;
	private String filedesc;
	private String content;
	private Date uploadTime;

	/** default constructor */
	public AbcAttachment() {
	}

	/** minimal constructor */
	public AbcAttachment(String attId) {
		this.attId = attId;
	}

	/** full constructor */
	public AbcAttachment(String attId, String userId, ModelType belongType,
			String belongId, String type, String state, String storeType,
			String serverIp, String serverPath, String filename,
			String filedesc, String content, Date uploadTime) {
		this.attId = attId;
		this.userId = userId;
		this.belongType = belongType;
		this.belongId = belongId;
		this.type = type;
		this.state = state;
		this.storeType = storeType;
		this.serverIp = serverIp;
		this.serverPath = serverPath;
		this.filename = filename;
		this.filedesc = filedesc;
		this.content = content;
		this.uploadTime = uploadTime;
	}

	// Property accessors
	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_Attachment", parameters = {
			@Parameter(name = "sequence", value = "SEQ_Attachment"),
			@Parameter(name = "prefix", value = "Attach") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_Attachment")
	@Column(name = "ATT_ID", unique = true, nullable = false, length = 64)
	public String getAttId() {
		return this.attId;
	}

	public void setAttId(String attId) {
		this.attId = attId;
	}

	@Column(name = "USER_ID", length = 64)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "BELONG_TYPE", length = 2)
	public ModelType getBelongType() {
		return this.belongType;
	}

	public void setBelongType(ModelType belongType) {
		this.belongType = belongType;
	}

	@Column(name = "BELONG_ID", length = 64)
	public String getBelongId() {
		return this.belongId;
	}

	public void setBelongId(String belongId) {
		this.belongId = belongId;
	}

	@Column(name = "TYPE", length = 2)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "STATE", length = 2)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "STORE_TYPE", length = 2)
	public String getStoreType() {
		return this.storeType;
	}

	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}

	@Column(name = "SERVER_IP", length = 200)
	public String getServerIp() {
		return this.serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	@Column(name = "SERVER_PATH", length = 800)
	public String getServerPath() {
		return this.serverPath;
	}

	public void setServerPath(String serverPath) {
		this.serverPath = serverPath;
	}

	@Column(name = "FILENAME", length = 200)
	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Column(name = "FILEDESC", length = 400)
	public String getFiledesc() {
		return this.filedesc;
	}

	public void setFiledesc(String filedesc) {
		this.filedesc = filedesc;
	}

	@Column(name = "CONTENT")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "UPLOAD_TIME")
	public Date getUploadTime() {
		return this.uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public AbcUser addUser() {
		UserService userService = (UserService) BeansFactory.get("userService");
		try {
			return userService.findById(this.getUserId());
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	public AbcAdmin addAdmin() {
		AdminService adminService = (AdminService) BeansFactory
				.get("adminService");
		try {
			return adminService.findById(this.getUserId());
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	/**
	 * 得到图片的url
	 * @return
	 */
	public String picUrl() {
		return ConfConst.FILE_SVR+ getServerPath() ;
	}

	/**
	 * 得到缩略图，i是缩略图的大小,1,2,3,4,5
	 * 
	 * @param i
	 * @return
	 */
	public String picUrl(int i) {
		
		String url = picUrl();
		String url2 = url.substring(url.lastIndexOf(".")+1,url.length());
		if(url2.equalsIgnoreCase("gif"))
			url = url.substring(0, url.lastIndexOf(".")) + "_" + i + ".gif";
		else
			url = url.substring(0, url.lastIndexOf(".")) + "_" + i + ".jpg";
		return url;
	}
	public String picUrl(Long i) {
		
		String url = picUrl();
		String url2 = url.substring(url.lastIndexOf(".")+1,url.length());
		if(url2.equalsIgnoreCase("gif"))
			url = url.substring(0, url.lastIndexOf(".")) + "_" + i + ".gif";
		else
			url = url.substring(0, url.lastIndexOf(".")) + "_" + i + ".jpg";
		return url;
	}
	
	public String serverPicUrl(int i) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String url = sdf.format(this.uploadTime)+serverPath.substring(serverPath.lastIndexOf("/"));
		String url2 = url.substring(url.lastIndexOf(".")+1,url.length());
		if(url2.equalsIgnoreCase("gif"))
			url = url.substring(0, url.lastIndexOf(".")) + "_" + i + ".gif";
		else
			url = url.substring(0, url.lastIndexOf(".")) + "_" + i + ".jpg";
		return url;
	}


}