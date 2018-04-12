package com.dn.jta.dao.db1;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dn.jta.model.Order;

@Mapper
@CacheNamespace
public interface OrderDao {

	List<Order> query(@Param("customerId") String customerId);
}
