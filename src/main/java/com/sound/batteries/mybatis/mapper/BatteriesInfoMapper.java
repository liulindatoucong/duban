package com.sound.batteries.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sound.batteries.mybatis.model.BtsMoudlecell;
import com.sound.batteries.mybatis.model.BtsPackcode;
import com.sound.batteries.mybatis.model.BtsPackmodule;
import com.sound.batteries.mybatis.model.BtsUploadLog;

@Mapper
public interface BatteriesInfoMapper {
	
	public int savePackFromPreview (String username);
	
	
	public int saveModuleFromPreview (String username);
	
	
	public int saveCellFromPreview (String username);
	
	
	public List<BtsPackcode> getNeedUploadBtsPackcodeByUser(String username);
	
	
	public List<BtsPackmodule> getNeedUploadBtsPackmoduleByPackHandle(String packhandle);
	
	
	public List<BtsMoudlecell> getNeedUploadbtsMoudlecellByModuleHandle(String modulehandle);
	
	
	public int insertUploadLog(BtsUploadLog btsUploadLog);
	
	
	public int updateNeedUploadBtsPackcode(String packhandle);
	
	
	public int updateNeedUploadBtsPackmodule(List<String> modules);
	
	
	public int updateNeedUploadbtsMoudlecell(List<String> cells);
}
