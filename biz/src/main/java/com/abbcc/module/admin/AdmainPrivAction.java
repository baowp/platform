package com.abbcc.module.admin;

import com.abbcc.action.BaseAction;
import com.abbcc.models.AbcAdminprivilege;

public class AdmainPrivAction extends BaseAction<AbcAdminprivilege>{
	public String mName;
	public String url;
	public String mId;
	public String savepriv(){
		AbcAdminprivilege p = new AbcAdminprivilege();
		p.setPname(mName);
		p.setUrl(url);
		p.setModuleId(mId);
		baseService.save(p);
		return LIST;
	}

}
