package com.dongnao.zklock.demo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class DistributDemo {

	public static void main(String[] args) {
		// 并发数
		int currency = 20;
		// 倒计数锁存器
		// CountDownLatch cdl = new CountDownLatch(currency);

		// 循环屏障
		CyclicBarrier cb = new CyclicBarrier(currency);

		// 多线程模拟高并发
		for (int i = 0; i < currency; i++) {
			new Thread(new Runnable() {
				public void run() {

					OrderService orderService = new OrderServiceImpl();
					// 要同时执行创建订单。怎么让并发的线程同时执行创建订单？
					// 等待大家都到这里，然后在一起继续执行
					// System.out.println("---------我准备好---------------");

					// cdl.countDown();
					try {
						cb.await();
					} catch (InterruptedException | BrokenBarrierException e) {
						e.printStackTrace();
					}
					System.out.println("---------我出发了---------------");
					// 调用创建订单服务
					orderService.createOrder();
				}
			}).start();

		}
	}

}
