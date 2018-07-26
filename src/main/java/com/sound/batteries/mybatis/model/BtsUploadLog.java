package com.sound.batteries.mybatis.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 传输到吉利的日志对象
 * @author liulin
 * @date 2018年7月26日 上午10:45:16
 * @Description: TODO
 */
public class BtsUploadLog implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4181213241809840774L;

	private String handle;
	
	private String plant;
	
	private String requestmsg;
	
	private String responsemsg;
	
	private String ip;
	
	private String status;
	
	private Date starttime;
	
	private Date endtime;
	
	private String user;

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

	public String getRequestmsg() {
		return requestmsg;
	}

	public void setRequestmsg(String requestmsg) {
		this.requestmsg = requestmsg;
	}

	public String getResponsemsg() {
		return responsemsg;
	}

	public void setResponsemsg(String responsemsg) {
		this.responsemsg = responsemsg;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	
}
