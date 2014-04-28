package com.abbcc.module.userNews;

import java.util.List;

import net.sf.json.JSONArray;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcNews;
import com.abbcc.models.AbcProduct;
import com.abbcc.service.NewsService;
import com.abbcc.service.ProductService;

public class NewsSearchAction extends BaseAction<AbcNews> {
	private String searchKey;
	private NewsService newsService;
	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public String byName() throws Exception{
		String[] name={"name","content"};
		List<AbcNews> news = newsService.findUserByParams(name,searchKey, AbcNews.class);
		for(int i=0;i<news.size();i++){
			if(!(news.get(i).getState().equals(CommonConst.STATENORMAL))){
				news.remove(i);
			}
		}
		JSONArray jsonArray = JSONArray.fromObject(news);
		this.result = jsonArray.toString();
		return SUCCESS;
		
	}


}
