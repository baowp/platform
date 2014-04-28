package com.abbcc.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.lucene.analysis.cn.ChineseAnalyzer;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import com.abbcc.util.IDUtil;

@SuppressWarnings("serial")
@Indexed(index="abc")
@Entity
@Table(name = "ABC_TOPSEARCH")
public class AbcTopSearch  implements java.io.Serializable{
	private String searchId;
	@Field(name = "searchKey", store = Store.NO, index = Index.TOKENIZED, analyzer = @Analyzer(impl = ChineseAnalyzer.class))    
	private String searchKey;
	private String views;
	private String modelClass;
	private Date lastTime;
	private String addUser;
	private String domain;
	// Property accessors
	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_TOPSEARCH", parameters = {
			@Parameter(name = "sequence", value = "seq_topsearch"),
			@Parameter(name = "prefix", value = "topSearch") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_TOPSEARCH")
	@Column(name = "SEARCH_ID", unique = true, nullable = false, length = 64)
	public String getSearchId() {
		return searchId;
	}
	public void setSearchId(String searchId) {
		this.searchId = searchId;
	}
	@Column(name = "SEARCH_KEY")
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public String getViews() {
		return views;
	}
	public void setViews(String views) {
		this.views = views;
	}
	@Column(name = "MODEL_CLASS")
	public String getModelClass() {
		return modelClass;
	}
	public void setModelClass(String modelClass) {
		this.modelClass = modelClass;
	}
	@Column(name = "LAST_TIME")
	public Date getLastTime() {
		return lastTime;
	}
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	@Column(name = "ADD_USER")
	public String getAddUser() {
		return addUser;
	}
	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
}
