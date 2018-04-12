package com.dongnao.proxy.cglib;

public class PayGame {

	public static interface A {
		void doSometing();
	}

	public static void main(String[] args) {
		TuHao th = new TuHao(1.8f);

		// Tony 代理对象产生
		TeacherCang tony = (TeacherCang) TonyProxyFactory
				.proxy(TeacherCang.class);

		th.dating(tony);

		TeacherChen tony1 = (TeacherChen) TonyProxyFactory
				.proxy(TeacherChen.class);

		tony1.dating('E');

		tony1.show();

		/*
		 * A a = (A) TonyProxyFactory.proxyInterface(A.class); a.doSometing();
		 */

		System.out.println(tony1.toString());
	}

}
