package com.sound.batteries.mybatis.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Repository;

import com.sound.batteries.mybatis.dao.MetrialDao;
import com.sound.batteries.mybatis.mapper.MetrialMapper;
import com.sound.batteries.mybatis.model.Metrial;

@Repository("defaultMetrialDao")
public class DefaultMetrialDao implements MetrialDao {

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
	public Metrial getMetrialbyCode(String material) {
		MetrialMapper mapper = this.sqlSession.getMapper(MetrialMapper.class);
		return mapper.getMetrialbyCode(material);
	}

}
