package com.abbcc.service;

import com.abbcc.models.GroupUserdefined;

public interface GroupUserdefinedService extends Service<GroupUserdefined>{
	public void cascadeDel(GroupUserdefined obj);
}
