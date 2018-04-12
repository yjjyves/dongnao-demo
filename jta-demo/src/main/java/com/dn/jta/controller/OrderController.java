package com.dn.jta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dn.jta.service.OrderService;
import com.dn.jta.util.BeanUtil;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private BeanUtil beanUtil;

	@RequestMapping("query")
	public Object query(String customerId, int pageNum, int pageSize) {
		return this.orderService.pageQuery(customerId, pageNum, pageSize);

	}
}
