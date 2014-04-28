package com.abbcc.module.product;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcProduct;
import com.abbcc.service.ProductService;
import com.abbcc.util.StringUtil;

public class ProductSearchAction extends BaseAction<AbcProduct>{
	private String searchKey;
	public String callback;
	public String getCallback() {
		return callback;
	}
	private ProductService productService;
	public String term;
	private Set<String> nameSet;
	public Set<String> getNameSet() {
		return nameSet;
	}
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	public String byName() throws Exception{
		String[] name={"name"};
		@SuppressWarnings("unchecked")
		List<AbcProduct> ents = productService.findUserByParamsByPage(name,term, AbcProduct.class,page, DEFAULT_PAGESIZE);
		for(int i=0;i<ents.size();i++){
			if(!(ents.get(i).getState().equals(CommonConst.STATENORMAL))){
				ents.remove(i);
			}
		}
		nameSet=new HashSet<String>();
		int i=0;
		for(AbcProduct product:ents){
			if(i>10)
				break;
			if(StringUtil.isNotBlank(product.getName())){
				nameSet.add(product.getName());
				i++;
			}
		}
		return JSON;
		
	}

}
