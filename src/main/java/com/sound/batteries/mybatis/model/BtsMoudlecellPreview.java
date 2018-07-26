package com.sound.batteries.mybatis.model;

import java.io.Serializable;

public class BtsMoudlecellPreview  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2508993724454967079L;
	private String cellhandle;
	private String moudlehandle;
	private String moudlecode;
	private String cellcode;
	private String cellmaterial;
	private String status;
	public String getCellhandle() {
		return cellhandle;
	}
	public void setCellhandle(String cellhandle) {
		this.cellhandle = cellhandle;
	}
	public String getMoudlehandle() {
		return moudlehandle;
	}
	public void setMoudlehandle(String moudlehandle) {
		this.moudlehandle = moudlehandle;
	}
	public String getMoudlecode() {
		return moudlecode;
	}
	public void setMoudlecode(String moudlecode) {
		this.moudlecode = moudlecode;
	}
	public String getCellcode() {
		return cellcode;
	}
	public void setCellcode(String cellcode) {
		this.cellcode = cellcode;
	}
	public String getCellmaterial() {
		return cellmaterial;
	}
	public void setCellmaterial(String cellmaterial) {
		this.cellmaterial = cellmaterial;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}

