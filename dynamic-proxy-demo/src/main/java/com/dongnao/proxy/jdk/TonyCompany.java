package com.dongnao.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TonyCompany {

	public static Object proxy(Object target) {
		return Proxy.newProxyInstance(target.getClass().getClassLoader(),
				target.getClass().getInterfaces(),
				new MyInvocationHandler(target));
	}

	private static class MyInvocationHandler implements InvocationHandler {
		private Object target;

		public MyInvocationHandler(Object target) {
			super();
			this.target = target;
		}

		public Object invoke(Object proxy, Method c, Object[] args)
				throws Throwable {
			// 前置增强
			doSomethingBefore();
			// 真正服务是有被代理对象提供的
			Object res = c.invoke(target, args);

			// 后置增强
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
}
