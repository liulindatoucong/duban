package com.sound.batteries.mybatis.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Repository;

import com.sound.batteries.mybatis.dao.PreviewDao;
import com.sound.batteries.mybatis.mapper.PreviewMapper;
import com.sound.batteries.mybatis.model.BtsBatteriesPackDetailInfo;
import com.sound.batteries.mybatis.model.BtsMoudlecellPreview;
import com.sound.batteries.mybatis.model.BtsPackcodePreview;
import com.sound.batteries.mybatis.model.BtsPackmodulePreview;

@Repository("defaultPreviewDao")
public class DefaultPreviewDao implements PreviewDao {
	private SqlSession sqlSession;
	
	@Override
	public int insertModulecellPreviews(List<BtsMoudlecellPreview> btsmoudlecellpreviews) {
		PreviewMapper mapper = this.sqlSession.getMapper(PreviewMapper.class);
		return mapper.insertModulecellPreviews(btsmoudlecellpreviews);
	}

	@Override
	public int insertPackcodePreviews(List<BtsPackcodePreview> btspackcodepreviews) {
		PreviewMapper mapper = this.sqlSession.getMapper(PreviewMapper.class);
		return mapper.insertPackcodePreviews(btspackcodepreviews);
	}

	@Override
	public int insertPackmodulePreviews(List<BtsPackmodulePreview> btspackmodulepreviews) {
		PreviewMapper mapper = this.sqlSession.getMapper(PreviewMapper.class);
		return mapper.insertPackmodulePreviews(btspackmodulepreviews);
	}

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	@Autowired
	@Required
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public int deletePackcodePreviews(String username) {
		PreviewMapper mapper = this.sqlSession.getMapper(PreviewMapper.class);
		return mapper.deletePackcodePreviews(username);
	}


	@Override
	public List<BtsMoudlecellPreview> checkModulecellPreviews() {
		PreviewMapper mapper = this.sqlSession.getMapper(PreviewMapper.class);
		return mapper.checkModulecellPreviews();
	}

	@Override
	public List<BtsPackcodePreview> checkPackcodePreviewsExists() {
		PreviewMapper mapper = this.sqlSession.getMapper(PreviewMapper.class);
		return mapper.checkPackcodePreviewsExists();
	}

	@Override
	public List<BtsPackmodulePreview> checkPackmodulePreviewsExists() {
		PreviewMapper mapper = this.sqlSession.getMapper(PreviewMapper.class);
		return mapper.checkPackmodulePreviewsExists();
	}

	@Override
	public List<BtsBatteriesPackDetailInfo> getPreviewInfo(String username) {
		PreviewMapper mapper = this.sqlSession.getMapper(PreviewMapper.class);
		return mapper.getPreviewInfo(username);
	}
}
