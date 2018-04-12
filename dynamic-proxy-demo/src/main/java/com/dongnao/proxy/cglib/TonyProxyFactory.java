package com.dongnao.proxy.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.CallbackHelper;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import net.sf.cglib.proxy.NoOp;

public class TonyProxyFactory {

	private static Enhancer e = new Enhancer();

	private static MethodInterceptor mi = new MethodInterceptor() {

		public Object intercept(Object proxy, Method method, Object[] args,
				MethodProxy methodProxy) throws Throwable {

			System.out.println("**************** " + method.getName());
			// 前置增强
			doSomethingBefore();
			// 真正的服务
			Object res = methodProxy.invokeSuper(proxy, args);

			// 后置增强
			doSomethingAfter();
			return res;
		}

		private void doSomethingBefore() {
			System.out.println("老板你好，这个我试过了，很不错，推荐给你！");
		}

		private void doSomethingAfter() {
			System.out.println("老板你觉得怎样？ 欢迎下次.....");
		}
	};

	static {
		e.setCallback(mi);
	}

	public static Object proxy(Class<?> superClass) {
		CallbackHelper ch = new CallbackHelper(superClass, new Class[0]) {

			@Override
			protected Object getCallback(Method method) {
				if (method.getDeclaringClass() != Object.class) {
					if (method.getName().equals("show")) {
						return NoOp.INSTANCE;
					}
					return mi;
				}

				return NoOp.INSTANCE;
			}
		};
		e.setSuperclass(superClass);
		e.setCallbackFilter(ch);
		e.setCallbacks(ch.getCallbacks());

		return e.create();
	}

	public static Object proxyInterface(Class<?> interfaces) {
		e.setSuperclass(null);
		e.setInterfaces(new Class[] { interfaces });
		return e.create();
	}
}
