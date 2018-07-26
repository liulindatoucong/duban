package com.sound.batteries.mybatis.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Repository;

import com.sound.batteries.mybatis.dao.BatteriesInfoDao;
import com.sound.batteries.mybatis.mapper.BatteriesInfoMapper;
import com.sound.batteries.mybatis.model.BtsMoudlecell;
import com.sound.batteries.mybatis.model.BtsPackcode;
import com.sound.batteries.mybatis.model.BtsPackmodule;
import com.sound.batteries.mybatis.model.BtsUploadLog;

@Repository("defaultBatteriesInfoDao")
public class DefaultBatteriesInfoDao implements BatteriesInfoDao {
	private SqlSession sqlSession;
	
	public SqlSession getSqlSession() {
		return sqlSession;
	}

	@Autowired
	@Required
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public int savePackFromPreview(String username) {
		BatteriesInfoMapper mapper = this.sqlSession.getMapper(BatteriesInfoMapper.class);
		return mapper.savePackFromPreview(username);
	}

	@Override
	public int saveModuleFromPreview(String username) {
		BatteriesInfoMapper mapper = this.sqlSession.getMapper(BatteriesInfoMapper.class);
		return mapper.saveModuleFromPreview(username);
	}

	@Override
	public int saveCellFromPreview(String username) {
		BatteriesInfoMapper mapper = this.sqlSession.getMapper(BatteriesInfoMapper.class);
		return mapper.saveCellFromPreview(username);
	}

	@Override
	public List<BtsPackcode> getNeedUploadBtsPackcodeByUser(String username) {
		BatteriesInfoMapper mapper = this.sqlSession.getMapper(BatteriesInfoMapper.class);
		return mapper.getNeedUploadBtsPackcodeByUser(username);
	}

	@Override
	public List<BtsPackmodule> getNeedUploadBtsPackmoduleByPackHandle(String packhandle) {
		BatteriesInfoMapper mapper = this.sqlSession.getMapper(BatteriesInfoMapper.class);
		return mapper.getNeedUploadBtsPackmoduleByPackHandle(packhandle);
	}

	@Override
	public List<BtsMoudlecell> getNeedUploadbtsMoudlecellByModuleHandle(String modulehandle) {
		BatteriesInfoMapper mapper = this.sqlSession.getMapper(BatteriesInfoMapper.class);
		return mapper.getNeedUploadbtsMoudlecellByModuleHandle(modulehandle);
	}

	@Override
	public int insertUploadLog(BtsUploadLog btsUploadLog) {
		BatteriesInfoMapper mapper = this.sqlSession.getMapper(BatteriesInfoMapper.class);
		return mapper.insertUploadLog(btsUploadLog);
	}

	@Override
	public int updateNeedUploadBtsPackcode(String packhandle) {
		BatteriesInfoMapper mapper = this.sqlSession.getMapper(BatteriesInfoMapper.class);
		return mapper.updateNeedUploadBtsPackcode(packhandle);
	}

	@Override
	public int updateNeedUploadBtsPackmodule(List<String> modules) {
		BatteriesInfoMapper mapper = this.sqlSession.getMapper(BatteriesInfoMapper.class);
		return mapper.updateNeedUploadBtsPackmodule(modules);
	}

	@Override
	public int updateNeedUploadbtsMoudlecell(List<String> cells) {
		BatteriesInfoMapper mapper = this.sqlSession.getMapper(BatteriesInfoMapper.class);
		return mapper.updateNeedUploadbtsMoudlecell(cells);
	}
	
	
}
