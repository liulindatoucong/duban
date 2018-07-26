package com.sound.batteries.mybatis.dao;

import java.util.List;

import com.sound.batteries.mybatis.model.BtsBatteriesPackDetailInfo;
import com.sound.batteries.mybatis.model.BtsMoudlecellPreview;
import com.sound.batteries.mybatis.model.BtsPackcodePreview;
import com.sound.batteries.mybatis.model.BtsPackmodulePreview;

public interface PreviewDao {
	/**
	 * 
	 * @author liulin
	 * @date 2018年7月25日 上午9:33:51
	 * @Description: 插入电芯对象
	 * @param btsmoudlecellpreviews
	 * @return
	 */
	public int insertModulecellPreviews (List<BtsMoudlecellPreview> btsmoudlecellpreviews);
	
	/**
	 * 
	 * @author liulin
	 * @date 2018年7月25日 上午9:33:51
	 * @Description: 插入pack对象
	 * @param btsmoudlecellpreviews
	 * @return
	 */
	public int insertPackcodePreviews (List<BtsPackcodePreview> btspackcodepreviews);
	
	/**
	 * 
	 * @author liulin
	 * @date 2018年7月25日 上午9:35:24
	 * @Description: 插入电芯模块对象
	 * @param btspackmodulepreviews
	 * @return
	 */
	public int insertPackmodulePreviews (List<BtsPackmodulePreview> btspackmodulepreviews);
	/**
	 * 
	 * @author liulin
	 * @date 2018年7月25日 上午10:21:38
	 * @Description: 删除预览表pack对象
	 * @return
	 */
	public int deletePackcodePreviews(String username);
	
	/**
	 * 
	 * @author liulin
	 * @date 2018年7月25日 上午10:51:14
	 * @Description: 检查电芯是否存在以及状态
	 * @return
	 */
	public List<BtsMoudlecellPreview> checkModulecellPreviews();
	 
	/**
	 * 
	 * @author liulin
	 * @date 2018年7月25日 上午10:51:14
	 * @Description: 检查电池包是否存在以及状态
	 * @return
	 */
	public List<BtsPackcodePreview> checkPackcodePreviewsExists();
	
	/**
	 * 
	 * @author liulin
	 * @date 2018年7月25日 上午10:51:14
	 * @Description: 检查电芯模块是否存在以及状态
	 * @return
	 */
	public List<BtsPackmodulePreview> checkPackmodulePreviewsExists();
	
	/**
	 * 
	 * @author liulin
	 * @date 2018年7月25日 下午2:32:49
	 * @Description: 获取预览数据
	 * @param username
	 * @return
	 */
	public List<BtsBatteriesPackDetailInfo> getPreviewInfo(String username);
}
