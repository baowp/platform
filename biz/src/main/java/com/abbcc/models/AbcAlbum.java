package com.abbcc.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

import com.abbcc.common.CommonConst;
import com.abbcc.common.ConfConst;
import com.abbcc.models.interfaces.EnterpriseId;
import com.abbcc.service.AttachmentService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.IDUtil;

/**
 * AbcAlbum entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Indexed(index = "abc")
@Entity
@Table(name = "ABC_ALBUM")
public class AbcAlbum implements java.io.Serializable, EnterpriseId {

	// Fields

	private String albumId;
	private String belongId;
	private String blongType;
	private String name;
	private String adesc;
	private String tag;
	private Date addTime;
	private String userId;
	private String state;
	private String type;

	// Constructors
	/** default constructor */
	public AbcAlbum() {
	}

	/** minimal constructor */
	public AbcAlbum(String albumId) {
		this.albumId = albumId;
	}

	/** full constructor */
	public AbcAlbum(String albumId, String blongType, String name,
			String adesc, String tag, Date addTime, String userId,
			String state, String type) {
		this.albumId = albumId;
		this.blongType = blongType;
		this.name = name;
		this.adesc = adesc;
		this.tag = tag;
		this.addTime = addTime;
		this.userId = userId;
		this.state = state;
		this.type = type;
	}

	// Property accessors
	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_Album", parameters = {
			@Parameter(name = "sequence", value = "SEQ_Album"),
			@Parameter(name = "prefix", value = "Album") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_Album")
	@Column(name = "ALBUM_ID", unique = true, nullable = false, length = 64)
	public String getAlbumId() {
		return this.albumId;
	}

	public void setAlbumId(String albumId) {
		this.albumId = albumId;
	}

	@Transient
	public String getAlbumId2() {
		return albumId.replaceFirst("^[^1-9]+", "");
	}

	@Column(name = "belong_ID")
	public String getBelongId() {
		return belongId;
	}

	public void setBelongId(String belongId) {
		this.belongId = belongId;
	}

	@Transient
	public String getEnterpriseId() {
		return belongId;
	}

	public void setEnterpriseId(String enterpriseId) {
		belongId = enterpriseId;
	}

	@Column(name = "BLONG_TYPE", length = 2)
	public String getBlongType() {
		return this.blongType;
	}

	public void setBlongType(String blongType) {
		this.blongType = blongType;
	}

	@Column(name = "NAME", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "ADESC", length = 400)
	public String getAdesc() {
		return this.adesc;
	}

	public void setAdesc(String adesc) {
		this.adesc = adesc;
	}

	@Column(name = "TAG", length = 100)
	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Column(name = "ADD_TIME")
	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@Column(name = "USER_ID", length = 64)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "STATE", length = 2)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "TYPE", length = 2)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Transient
	public int getPicCount() {
		return picCount1();
	}

	@Transient
	public String getMainPic() {
		return picUrl(5);
	}

	public int picCount1() {
		AttachmentService attachmentService = (AttachmentService) BeansFactory
				.get("attachmentService");
		AbcAttachment aat = new AbcAttachment();
		aat.setBelongId(this.getAlbumId());
		aat.setState(CommonConst.STATENORMAL);
		return attachmentService.findByExample(aat).size();
	}

	public String picUrl() {
		String url = "";
		AttachmentService attachmentService = (AttachmentService) BeansFactory
				.get("attachmentService");
		String hql = "from AbcAttachment where belongId ='" + this.getAlbumId()
				+ "' and (state<'03' or state>'03') order by uploadTime";
		List<AbcAttachment> list = attachmentService.findByHql(hql);
		if (list.size() != 0) {
			url = list.get(list.size() - 1).getServerPath();
		}
		String[] url3 = url.split("\\.");
		if (url3.length != 0)
			url = url3[0] + "." + url3[1];
		return ConfConst.FILE_SVR + url;

	}

	public String picUrl(int i) {
		String url = "";
		AttachmentService attachmentService = (AttachmentService) BeansFactory
				.get("attachmentService");
		String hql = "from AbcAttachment where belongId ='" + this.getAlbumId()
				+ "' and (state<'03' or state>'03') order by uploadTime";
		List<AbcAttachment> list = attachmentService.findByHql(hql);
		if (list.size() != 0) {
			return list.get(list.size() - 1).picUrl(i);
		}

		return url;

	}

	public String flashUrl(String url) {
		return ConfConst.FILE_SVR + url;
	}

	public String picUrl5(String obj) {
		return ConfConst.FILE_SVR + obj.substring(0, obj.lastIndexOf("."))
				+ "_5" + obj.substring(obj.lastIndexOf("."), obj.length());
	}

}