package com.dn.andemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dn.andemo.annotation.NeedSetFieldValue;
import com.dn.andemo.dao.OrderDao;
import com.dn.andemo.model.Order;
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
