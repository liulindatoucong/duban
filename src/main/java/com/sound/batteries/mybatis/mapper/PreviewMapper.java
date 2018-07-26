package com.sound.batteries.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sound.batteries.mybatis.model.BtsBatteriesPackDetailInfo;
import com.sound.batteries.mybatis.model.BtsMoudlecellPreview;
import com.sound.batteries.mybatis.model.BtsPackcodePreview;
import com.sound.batteries.mybatis.model.BtsPackmodulePreview;

@Mapper
public interface PreviewMapper {
	
	public int insertModulecellPreviews (List<BtsMoudlecellPreview> btsmoudlecellpreviews);
	
	
	public int insertPackcodePreviews (List<BtsPackcodePreview> btspackcodepreviews);
	
	
	public int insertPackmodulePreviews (List<BtsPackmodulePreview> btspackmodulepreviews);
	
	
	public int deletePackcodePreviews(String username);
	
	public List<BtsMoudlecellPreview> checkModulecellPreviews();
	 
	public List<BtsPackcodePreview> checkPackcodePreviewsExists();
	
	public List<BtsPackmodulePreview> checkPackmodulePreviewsExists();
	
	public List<BtsBatteriesPackDetailInfo> getPreviewInfo(String username);
}
