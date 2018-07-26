package com.sound.batteries.mybatis.model;

import java.io.Serializable;

/**
 * 物料信息
 * @author liulin
 * @date 2018年7月25日 下午8:22:47
 * @Description: TODO
 */
public class Metrial implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8221911598163926966L;
	
	private String handle;
	
	private String plant;
	
	private String material;
	
	private String matdescription;
	
	private String systemcode;
	
	private String packmodle;
	
	private String systemid;
	
	private String systemmodelid;
	
	private String modelid;
	
	private String cellmodelid;
	
	private String status;
	
	private String createtime;
	
	private String createuser;
	
	private String changetime;
	
	private String changeuser;

	public String getHandle() {
		return handle;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}

	public String getPlant() {
		return plant;
	}

	public void setPlant(String plant) {
		this.plant = plant;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getMatdescription() {
		return matdescription;
	}

	public void setMatdescription(String matdescription) {
		this.matdescription = matdescription;
	}

	public String getSystemcode() {
		return systemcode;
	}

	public void setSystemcode(String systemcode) {
		this.systemcode = systemcode;
	}

	public String getPackmodle() {
		return packmodle;
	}

	public void setPackmodle(String packmodle) {
		this.packmodle = packmodle;
	}

	public String getSystemid() {
		return systemid;
	}

	public void setSystemid(String systemid) {
		this.systemid = systemid;
	}

	public String getSystemmodelid() {
		return systemmodelid;
	}

	public void setSystemmodelid(String systemmodelid) {
		this.systemmodelid = systemmodelid;
	}

	public String getModelid() {
		return modelid;
	}

	public void setModelid(String modelid) {
		this.modelid = modelid;
	}

	public String getCellmodelid() {
		return cellmodelid;
	}

	public void setCellmodelid(String cellmodelid) {
		this.cellmodelid = cellmodelid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public String getChangetime() {
		return changetime;
	}

	public void setChangetime(String changetime) {
		this.changetime = changetime;
	}

	public String getChangeuser() {
		return changeuser;
	}

	public void setChangeuser(String changeuser) {
		this.changeuser = changeuser;
	}
	
	

}
