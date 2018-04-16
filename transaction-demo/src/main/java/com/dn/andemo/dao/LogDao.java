package com.dn.andemo.dao;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.dn.andemo.model.Log;

@Mapper
@CacheNamespace
public interface LogDao {

	@Insert("insert t_log(id,log) values(#{id},#{log})")
	void insert(Log u);

}
