package com.sound.model;

import java.io.Serializable;
import java.util.List;

public class BtsBatteriesPackDetailModelMsg implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4191622713595273589L;

	private String flag;
	
	private String msg;
	
	private String packNum;
	
	private String moduleNum;
	
	private String cellNum;
	
	private List<BtsBatteriesPackDetailInfoModel> detailinfos;

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<BtsBatteriesPackDetailInfoModel> getDetailinfos() {
		return detailinfos;
	}

	public void setDetailinfos(List<BtsBatteriesPackDetailInfoModel> detailinfos) {
		this.detailinfos = detailinfos;
	}

	public String getPackNum() {
		return packNum;
	}

	public void setPackNum(String packNum) {
		this.packNum = packNum;
	}

	public String getModuleNum() {
		return moduleNum;
	}

	public void setModuleNum(String moduleNum) {
		this.moduleNum = moduleNum;
	}

	public String getCellNum() {
		return cellNum;
	}

	public void setCellNum(String cellNum) {
		this.cellNum = cellNum;
	}
	
}
