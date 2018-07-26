package com.sound.batteries.mybatis.dao;

import java.util.List;

import com.sound.batteries.mybatis.model.BtsMoudlecell;
import com.sound.batteries.mybatis.model.BtsPackcode;
import com.sound.batteries.mybatis.model.BtsPackmodule;
import com.sound.batteries.mybatis.model.BtsUploadLog;

public interface BatteriesInfoDao {
	/**
	 * 
	 * @author liulin
	 * @date 2018年7月25日 下午4:54:04
	 * @Description: 从预览表获取数据保存到pack表
	 * @param username
	 * @return
	 */
	public int savePackFromPreview (String username);
	
	/**
	 * 
	 * @author liulin
	 * @date 2018年7月25日 下午4:54:26
	 * @Description: 从预览表获取数据保存到模块表
	 * @param username
	 * @return
	 */
	public int saveModuleFromPreview (String username);
	
	
	/**
	 * 
	 * @author liulin
	 * @date 2018年7月25日 下午4:54:26
	 * @Description: 从预览表获取数据保存到电芯表
	 * @param username
	 * @return
	 */
	public int saveCellFromPreview (String username);
	
	/**
	 * 获取当前需要上传的电芯包
	 * @author liulin
	 * @date 2018年7月25日 下午7:31:18
	 * @Description: TODO
	 * @param username
	 * @return
	 */
	public List<BtsPackcode> getNeedUploadBtsPackcodeByUser(String username);
	
	/**
	 * 获取当前需要上传的电芯模块
	 * @author liulin
	 * @date 2018年7月25日 下午7:31:50
	 * @Description: TODO
	 * @param packhandle
	 * @return
	 */
	public List<BtsPackmodule> getNeedUploadBtsPackmoduleByPackHandle(String packhandle);
	
	/**
	 * 获取当前需要上传的电芯
	 * @author liulin
	 * @date 2018年7月25日 下午7:32:01
	 * @Description: TODO
	 * @param modulehandle
	 * @return
	 */
	public List<BtsMoudlecell> getNeedUploadbtsMoudlecellByModuleHandle(String modulehandle);
	
	/**
	 * 插入传输吉利日志
	 * @author liulin
	 * @date 2018年7月26日 上午10:55:28
	 * @Description: TODO
	 * @param btsUploadLog
	 * @return
	 */
	public int insertUploadLog(BtsUploadLog btsUploadLog);
	
	/**
	 * 更新电池包状态
	 * @author liulin
	 * @date 2018年7月26日 上午11:05:44
	 * @Description: TODO
	 * @param packhandle
	 * @return
	 */
	public int updateNeedUploadBtsPackcode(String packhandle);
	
	/**
	 * 更新模块状态
	 * @author liulin
	 * @date 2018年7月26日 上午11:05:55
	 * @Description: TODO
	 * @param modules
	 * @return
	 */
	public int updateNeedUploadBtsPackmodule(List<String> modules);
	
	/**
	 * 更新电芯状态
	 * @author liulin
	 * @date 2018年7月26日 上午11:06:04
	 * @Description: TODO
	 * @param cells
	 * @return
	 */
	public int updateNeedUploadbtsMoudlecell(List<String> cells);
}
