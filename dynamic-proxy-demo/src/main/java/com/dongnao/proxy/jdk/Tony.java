package com.dongnao.proxy.jdk;

public class Tony implements Girl {

	private Girl g;

	public Tony(Girl g) {
		super();
		this.g = g;
	}

	public boolean dating(float length) {
		// 提前做好 前置准备工作
		doSomethingBefore();

		boolean res = false;
		// 调用被代理对象的目标方法
		res = this.g.dating(length);

		// 后置的服务
		doSomethingAfter();
		return res;
	}

	private void doSomethingAfter() {
		System.out.println("你觉得怎么样？");
	}

	private void doSomethingBefore() {
		System.out.println("老板你好，这个我试过了，还不错，推荐给你！");
	}

}
