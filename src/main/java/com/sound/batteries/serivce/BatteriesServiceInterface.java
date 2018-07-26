package com.sound.batteries.serivce;

import java.io.File;
import java.util.List;

import com.sound.model.BtsBatteriesPackDetailInfoModel;

public interface BatteriesServiceInterface {
	
	/**
	 * 导入excel文档数据到数据库预览
	 * @author liulin
	 * @date 2018年7月25日 上午10:25:06
	 * @Description: 导入excel文档数据到数据库预览
	 * @param file
	 * @param site
	 * @param userName
	 * @throws Exception
	 */
	public String saveFileInfo(File file, String site, String userName) throws Exception ;
	
	/**
	 * 获取前台预览数据
	 * @author liulin
	 * @date 2018年7月25日 下午2:47:14
	 * @Description: TODO
	 * @param username
	 * @return
	 */
	public List<BtsBatteriesPackDetailInfoModel> getPreviewInfo(String username);
	
	/**
	 * 将数据从预览表保存到电芯信息表
	 * @author liulin
	 * @date 2018年7月25日 下午5:01:26
	 * @Description: TODO
	 * @param username
	 */
	public void saveBatteriesInfoFromPreview(String username);
	
	
	/**
	 * 上传电芯信息到吉利
	 * @author liulin
	 * @date 2018年7月25日 下午7:36:37
	 * @Description: TODO
	 */
	public void uploadInfo2JiLi(String username) throws Exception;
}
