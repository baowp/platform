package com.abbcc.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.abbcc.util.IDUtil;


@Entity
@Table(name="GROUP_THEME")
public class GroupTheme implements Serializable {
	private static final long serialVersionUID = 1L;
	private String themeId;
	private String folder;
	private String name;

    public GroupTheme() {
    }


	@Id
	@GenericGenerator(name = "SEQ_GroupTheme", parameters = {
			@Parameter(name = "sequence", value = "SEQ_GroupTheme"),
			@Parameter(name = "prefix", value = "GroupTheme") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_GroupTheme")
	@Column(name="THEME_ID", unique = true, nullable = false)
	public String getThemeId() {
		return this.themeId;
	}
	public void setThemeId(String themeId) {
		this.themeId = themeId;
	}
	
	@Column(name="FOLDER")
	public String getFolder() {
		return this.folder;
	}
	public void setFolder(String folder) {
		this.folder = folder;
	}

	@Column(name="NAME")
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
}