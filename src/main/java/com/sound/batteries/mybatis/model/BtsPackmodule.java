package com.sound.batteries.mybatis.model;

import java.io.Serializable;

public class BtsPackmodule implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4963903732382527726L;
	private String moudlehandle;
	private String packhandle;
	private String packcode;
	private String moudlecode;
	private String moudlematerial;
	private String status;
	public String getMoudlehandle() {
		return moudlehandle;
	}
	public void setMoudlehandle(String moudlehandle) {
		this.moudlehandle = moudlehandle;
	}
	public String getPackhandle() {
		return packhandle;
	}
	public void setPackhandle(String packhandle) {
		this.packhandle = packhandle;
	}
	public String getPackcode() {
		return packcode;
	}
	public void setPackcode(String packcode) {
		this.packcode = packcode;
	}
	public String getMoudlecode() {
		return moudlecode;
	}
	public void setMoudlecode(String moudlecode) {
		this.moudlecode = moudlecode;
	}
	public String getMoudlematerial() {
		return moudlematerial;
	}
	public void setMoudlematerial(String moudlematerial) {
		this.moudlematerial = moudlematerial;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
    
}
