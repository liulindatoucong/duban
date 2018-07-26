package com.sound.batteries.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.sound.batteries.mybatis.model.Metrial;

@Mapper
public interface MetrialMapper {
	public Metrial getMetrialbyCode(String material);
}
