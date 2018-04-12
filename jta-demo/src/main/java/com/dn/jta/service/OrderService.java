package com.dn.jta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dn.jta.annotation.NeedSetFieldValue;
import com.dn.jta.dao.db1.OrderDao;
import com.dn.jta.model.Order;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class OrderService {

	@Autowired
	private OrderDao orderDao;

	@NeedSetFieldValue
	public Page<Order> pageQuery(String customerId, int pageNum, int pageSize) {
		Page<Order> page = PageHelper.startPage(pageNum, pageSize);
		this.orderDao.query(customerId);
		return page;
	}

}
