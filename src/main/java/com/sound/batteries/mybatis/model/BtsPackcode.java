package com.sound.batteries.mybatis.model;

import java.io.Serializable;
import java.util.Date;

public class BtsPackcode implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8097149995234775198L;

	private String packhandle;
	
    private String plant;
    
    private String packcode;
    
    private String packmaterial;
    
    private String packorder;
    
    private String status;
    
    private Date createtime;
    
    private String createuser;
    
    private Date changetime;
    
    private String changeuser;
    
    private Date uploadtime;
    
    private String uploaduser;
    
	public String getPackhandle() {
		return packhandle;
	}
	public void setPackhandle(String packhandle) {
		this.packhandle = packhandle;
	}
	public String getPlant() {
		return plant;
	}
	public void setPlant(String plant) {
		this.plant = plant;
	}
	public String getPackcode() {
		return packcode;
	}
	public void setPackcode(String packcode) {
		this.packcode = packcode;
	}
	public String getPackmaterial() {
		return packmaterial;
	}
	public void setPackmaterial(String packmaterial) {
		this.packmaterial = packmaterial;
	}
	public String getPackorder() {
		return packorder;
	}
	public void setPackorder(String packorder) {
		this.packorder = packorder;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getCreateuser() {
		return createuser;
	}
	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}
	public Date getChangetime() {
		return changetime;
	}
	public void setChangetime(Date changetime) {
		this.changetime = changetime;
	}
	public String getChangeuser() {
		return changeuser;
	}
	public void setChangeuser(String changeuser) {
		this.changeuser = changeuser;
	}
	public Date getUploadtime() {
		return uploadtime;
	}
	public void setUploadtime(Date uploadtime) {
		this.uploadtime = uploadtime;
	}
	public String getUploaduser() {
		return uploaduser;
	}
}
