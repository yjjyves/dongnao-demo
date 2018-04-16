package com.dongnao.zklock.demo1;

import java.util.concurrent.CountDownLatch;

public class DistrbutDemo {

	public static void main(String[] args) {
		// 模拟多个并发创建订单

		// 并发数
		int currs = 20;

		// 循环屏障
		// CyclicBarrier cb = new CyclicBarrier(currs);

		// 倒计数锁存器
		CountDownLatch cdl = new CountDownLatch(currs);

		for (int i = 0; i < currs; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					// 模拟创建订单
					OrderService os = new OrderServiceImpl();

					cdl.countDown();
					try {
						cdl.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					os.createOrder();

				}
			}).start();
		}
	}
}
