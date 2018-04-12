package com.dongnao.proxy.cglib;

import java.lang.reflect.Method;

import com.dongnao.proxy.jdk.utils.ProxyUtils;

import net.sf.cglib.core.DefaultGeneratorStrategy;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class MainDemo {

	static class Test {
		private String sss;

		public String dotest(String mess) {
			System.out.println("Hello " + mess);
			return "Hello " + mess + " " + sss;
		}

		/*
		 * public Test(String sss) { super(); this.sss = sss; }
		 */
	}

	static byte[] byteClassfile;

	public static void main(String[] args) {
		Enhancer en = new Enhancer();
		en.setSuperclass(Test.class);
		en.setStrategy(new DefaultGeneratorStrategy() {
			protected byte[] transform(byte[] b) throws Exception {
				ProxyUtils.writeToFile(Test.class, b,
						this.getClassVisitor().getClassName());
				return b;
			}
		});
		en.setCallback(new MethodInterceptor() {

			public Object intercept(Object obj, Method method, Object[] args,
					MethodProxy proxy) throws Throwable {
				// String res1 = (String) proxy.invoke(obj, args);
				// String res2 = (String) proxy.invokeSuper(obj, args);
				System.out.println(obj.getClass().getName());
				String res2 = (String) proxy.invokeSuper(obj, args);
				return res2;
			}
		});

		Test proxy = (Test) en.create();

		System.out.println(proxy.getClass().getName());

		System.out.println(proxy.dotest("mike"));

		/*
		 * ProxyUtils.writeToFile(Test.class, byteClassfile,
		 * proxy.getClass().getName());
		 */
	}
}
