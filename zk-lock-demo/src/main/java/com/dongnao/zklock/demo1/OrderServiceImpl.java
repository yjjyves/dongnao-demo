package com.dongnao.zklock.demo1;

import java.util.concurrent.locks.Lock;

public class OrderServiceImpl implements OrderService {

	private static OrderCodeGenerator ocg = new OrderCodeGenerator();

	private static Lock lock = new ZKDistributeImproveLock("/QQQQ");// new
	// ReentrantLock();

	// 创建订单接口
	@Override
	public void createOrder() {

		String orderCode = null;

		try {
			lock.lock();
			orderCode = ocg.getOrderCode();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		/*
		 * synchronized (ocg) { orderCode = ocg.getOrderCode(); }
		 */
		// 获取订单号

		System.out.println(Thread.currentThread().getName() + " =============>"
				+ orderCode);

		// ……业务代码，此处省略100行代码

	}

}
