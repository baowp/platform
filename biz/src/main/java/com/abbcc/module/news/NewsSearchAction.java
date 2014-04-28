package com.abbcc.module.news;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcNews;
import com.abbcc.news.models.NewsNews;
import com.abbcc.news.service.NewsNewsService;
import com.abbcc.util.StringUtil;

public class NewsSearchAction extends BaseAction<AbcNews> {
	public String searchKey;
	private NewsNewsService newsNewsService;
	public void setNewsNewsService(NewsNewsService newsNewsService) {
		this.newsNewsService = newsNewsService;
	}
	public String term;
	private Set<String> nameSet;
	public Set<String> getNameSet() {
		return nameSet;
	}


	public String byName() throws Exception{
		String[] name={"title"};
		@SuppressWarnings("unchecked")
		List<NewsNews> news = newsNewsService.findUserByParamsByPage(name,term, NewsNews.class, page, DEFAULT_PAGESIZE);
		nameSet = new HashSet<String>();
		int i=0;
		for(NewsNews an:news){
			if(i>10)
				break;
			if(StringUtil.isNotBlank(an.getTitle())){
				nameSet.add(an.getTitle());
				i++;
			}
		}
		return SUCCESS;
		
	}


}
