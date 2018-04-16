package com.dn.volat;

import java.util.concurrent.TimeUnit;

/**
 * 通过状态标识来控制异步线程的结束
 *
 */
public class VisibilityDemo {

	// 状态标识
	private static volatile boolean is = true;

	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				int i = 0;
				while (VisibilityDemo.is) {
					// System.out.println(i++);

					i++;
				}
			}

		}).start();

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		VisibilityDemo.is = false; // 设置is为false，使上面的线程结束while循环
		System.out.println("被置为false了.");

		/*
		 * new Thread(new Runnable() {
		 * 
		 * @Override public void run() { System.out.println(VisibilityDemo.is);
		 * } }).start();
		 */
	}
}
