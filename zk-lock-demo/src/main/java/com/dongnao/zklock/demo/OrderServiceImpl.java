package com.dongnao.zklock.demo;

import java.util.concurrent.locks.Lock;

public class OrderServiceImpl implements OrderService {

	private static OrderCodeGenerator ocg = new OrderCodeGenerator();

	private static Lock lock = new ZKDistributeImproveLock();

	// 创建订单接口
	@Override
	public void createOrder() {

		String orderCode = null;
		/*
		 * synchronized (ocg) { // 获取订单号 orderCode = ocg.getOrderCode(); }
		 */

		try {
			lock.lock();
			// 获取订单号
			orderCode = ocg.getOrderCode();

		} finally {
			lock.unlock();
		}

		System.out.println(Thread.currentThread().getName() + " =============>"
				+ orderCode);

		// ……业务代码，此处省略100行代码

	}

}
