package com.abbcc.module.soa;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcNews;
import com.abbcc.models.AbcProduct;
import com.abbcc.models.AbcUser;
import com.abbcc.module.usersite.SiteBaseAction;
import com.abbcc.service.NewsService;
import com.abbcc.service.ProductService;
import com.abbcc.service.UserService;

/**
 * HtmlSoaSearchAction
 * @author RayStone
 *
 */
@SuppressWarnings("serial")
public class HtmlSoaSearchAction extends SiteBaseAction<AbcNews>{
	private String searchKey;
	private NewsService newsService;
	private ProductService productService;
	private UserService userService;
	

	/**
	 * 解码中文参数
	 * @param encodeStr
	 * @return
	 */
	private String decodeStr(String encodeStr) throws IOException{
		return URLDecoder.decode(URLDecoder.decode(encodeStr, "utf-8"), "utf-8");
	}
	
	/**
	 * 搜索新闻和产品
	 * @return
	 * @throws IOException
	 */
	public void search() throws IOException{
		try {
			AbcUser urlUser = userService.getUserByDomain(userDomain);
			if(urlUser==null){
				output(f+"('"+CommonConst.FAILURE+"', '不存在该公司！');");
			}
			else {
				String[] name_news = {"title","content"};
				List<AbcNews> news = newsService.findUserByParams(name_news, decodeStr(searchKey), AbcNews.class);
				List<Object> items_1 = new ArrayList<Object>();
				for(int i=0;i<news.size();i++){
					AbcNews newsObj = news.get(i);
					if((CommonConst.STATENORMAL.equals(newsObj.getState())) 
							&& (urlUser.getEnterpriseId().equals(newsObj.getEnterpriseId()))){
						ArrayList<Object> aList = new ArrayList<Object>();
						aList.add(newsObj.getTitle());
						aList.add(newsObj.htmlPath());
						aList.add(newsObj.shortContent());
						items_1.add(aList);
					}
				}
				JSONArray jsonArray_news = JSONArray.fromObject(items_1);
				String[] name_pro = {"name"};
				List<AbcProduct> ents = productService.findUserByParams(name_pro,searchKey, AbcProduct.class);
				List<Object> items_2 = new ArrayList<Object>();
				for(int i=0;i<ents.size();i++){
					AbcProduct proObj = ents.get(i);
					if((CommonConst.STATENORMAL.equals(proObj.getState()))
							&& (urlUser.getEnterpriseId().equals(proObj.getEnterpriseId()))){
						ArrayList<Object> aList = new ArrayList<Object>();
						aList.add(proObj.getName());
						aList.add(proObj.htmlPath());
						aList.add(proObj.productPic());
						items_2.add(aList);
					}
				}
				JSONArray jsonArray_pro = JSONArray.fromObject(items_2);
				output(f+"('"+CommonConst.SUCCESS+"', "+jsonArray_news.toString()+", "+jsonArray_pro.toString()+");");
			}
		} catch (Exception e) {
			// TODO: handle exception
			output(f+"('"+CommonConst.FAILURE+"', '"+e.toString()+"');");
			//this.result = f+"('"+e.toString()+"')";
		}
	}


	public NewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

}