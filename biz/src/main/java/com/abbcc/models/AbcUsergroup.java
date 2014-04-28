package com.abbcc.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

import com.abbcc.service.UserService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.IDUtil;

/**
 * AbcUsergroup entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Indexed(index="abc")
@Entity
@Table(name = "ABC_USERGROUP")
public class AbcUsergroup implements java.io.Serializable {

	// Fields

	private String usergroupId;
	private String groupname;
	private String gdesc;
	private String type;
	private Date addTime;
	private String addAdmin;
	private String state;

	// Constructors

	/** default constructor */
	public AbcUsergroup() {
	}

	/** minimal constructor */
	public AbcUsergroup(String usergroupId) {
		this.usergroupId = usergroupId;
	}

	/** full constructor */
	public AbcUsergroup(String usergroupId, String groupname, String gdesc,
			String type, Date addTime, String addAdmin, String state) {
		this.usergroupId = usergroupId;
		this.groupname = groupname;
		this.gdesc = gdesc;
		this.type = type;
		this.addTime = addTime;
		this.addAdmin = addAdmin;
		this.state = state;
	}

	// Property accessors
	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_Usergroup", parameters = {
			@Parameter(name = "sequence", value = "SEQ_Usergroup"),
			@Parameter(name = "prefix", value = "Usergroup") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_Usergroup")
	@Column(name = "USERGROUP_ID", unique = true, nullable = false, length = 64)
	public String getUsergroupId() {
		return this.usergroupId;
	}

	public void setUsergroupId(String usergroupId) {
		this.usergroupId = usergroupId;
	}

	@Column(name = "GROUPNAME", length = 100)
	public String getGroupname() {
		return this.groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	@Column(name = "GDESC", length = 800)
	public String getGdesc() {
		return this.gdesc;
	}

	public void setGdesc(String gdesc) {
		this.gdesc = gdesc;
	}

	@Column(name = "TYPE", length = 1)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "ADD_TIME")
	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@Column(name = "ADD_ADMIN", length = 64)
	public String getAddAdmin() {
		return this.addAdmin;
	}

	public void setAddAdmin(String addAdmin) {
		this.addAdmin = addAdmin;
	}

	@Column(name = "STATE", length = 2)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public List<AbcUser> users(){
		UserService userService = (UserService)BeansFactory.get("userService");
		return userService.getUsersByGroupId(this.getUsergroupId());
		
	}

}