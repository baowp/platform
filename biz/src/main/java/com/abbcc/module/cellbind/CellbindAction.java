package com.abbcc.module.cellbind;

import java.util.Date;
import java.util.List;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcCellbind;
import com.abbcc.models.AbcCellserver;
import com.abbcc.service.CellbindService;
import com.abbcc.service.CellserverService;
import com.abbcc.service.LogService;
import com.abbcc.util.StringUtil;

public class CellbindAction extends BaseAction<AbcCellbind> {
	private CellbindService cellbindService;
	private CellserverService cellserverService;
	private LogService logService;
	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public String check;

	public void setCellbindService(CellbindService cellbindService) {
		this.cellbindService = cellbindService;
	}

	public void setCellserverService(CellserverService cellserverService) {
		this.cellserverService = cellserverService;
	}

	public String view() {
		AbcCellbind cell = new AbcCellbind();
		cell.setUserId(this.getCurrentUser().getUserId());
		cell.setState(CommonConst.STATENORMAL);
		List<AbcCellbind> list = cellbindService.findByExample(cell);
		if (list.size() != 0) {
			getRequest().setAttribute("cellphone", list.get(0).getCellphone());
			return "verifySuccess";
		}
		return VIEW;
	}

	public String bind() {
		if (!check.equals("true")) {
			this.addFieldError("cellphone", "对不起，您必须勾选同意并遵守服务！");
			return INPUT;
		}
		String verifyCode;
		AbcCellbind cell = new AbcCellbind();
		cell.setUserId(this.getCurrentUser().getUserId());
		List<AbcCellbind> list = cellbindService.findByExample(cell);
		if (list.size() == 0) {
			verifyCode = StringUtil.getRndString(5);
			entity.setUserId(this.getCurrentUser().getUserId());
			entity.setState(CommonConst.STATEINIT);
			entity.setBindTime(new Date());
			entity.setVerifyCode(verifyCode);
			cellbindService.save(entity);
		} else{
			if(!list.get(0).getCellphone().equals(entity.getCellphone())){
				AbcCellbind acb = cellbindService.findById(list.get(0).getCellbindId());
				acb.setCellphone(entity.getCellphone());
				acb.setCellbindId(list.get(0).getCellbindId());
				cellbindService.update(acb);
				
			}
			List<AbcCellbind> cell2 = cellbindService.findByExample(cell);
			if(cell2.size()>0)
				verifyCode = cell2.get(0).getVerifyCode();
			else
				verifyCode = StringUtil.getRndString(5);
		}
		log.info("手机验证码是" + verifyCode);
		return "bind";
	}

	public String verify() {
		AbcCellbind cell = new AbcCellbind();
		cell.setUserId(this.getCurrentUser().getUserId());
		List<AbcCellbind> cell2 = cellbindService.findByExample(cell);
		if (cell2.size()>0&&entity.getVerifyCode().equals(cell2.get(0).getVerifyCode())) {
			// 保存到SERVER表里
			AbcCellserver cellserver = new AbcCellserver();
			cellserver.setCellbindId(cell2.get(0).getCellbindId());
			cellserver.setCellphone(cell2.get(0).getCellphone());
			cellserver.setAddTime(new Date());
			// cellserver.setType(type)
			cellserverService.save(cellserver);
			// 修改cellbind表里的状态
			AbcCellbind cellbing = cellbindService.findById(cellserver
					.getCellbindId());
			cellbing.setCellbindId(cellserver.getCellbindId());
			cellbing.setState(CommonConst.STATENORMAL);
			cellbindService.update(cellbing);
			logService.savaLog("手机绑定","", CommonConst.LOGUPDATE);
			getRequest().setAttribute("cellphone", cell2.get(0).getCellphone());
			return "verifySuccess";
		} else {
			this.addFieldError("verifyCode", "对不起，您的输入的验证码有误<a href=" + "#"
					+ ">点此重发</a>！");
			return INPUT;
		}
	}

	public String logout() {
		AbcCellbind cell = new AbcCellbind();
		cell.setUserId(this.getCurrentUser().getUserId());
		cell.setState(CommonConst.STATENORMAL);
		List<AbcCellbind> cell2 = cellbindService.findByExample(cell);
		if (cell2.size() != 0) {
			AbcCellbind cellbing = cellbindService.findById(cell2.get(0)
					.getCellbindId());
			cellbing.setCellbindId(cell2.get(0).getCellbindId());
			cellbing.setState(CommonConst.STATEINIT);
			cellbing.setVerifyCode(StringUtil.getRndString(5));
			cellbindService.update(cellbing);
			return "returnshow";
		}
		return ERROR;
	}

}
