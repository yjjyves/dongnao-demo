package com.dongnao.proxy.jdk;

import java.lang.reflect.Proxy;

import com.dongnao.proxy.jdk.utils.ProxyUtils;

public class PlayGame {

	public static void main(String[] args) {
		TuHao sicong = new TuHao(1.8f);

		Girl tc = new TeacherCang();

		Girl tony = new Tony(tc);

		sicong.dating(tony);

		System.out.println("---------------动态代理 ————————————————————");

		Girl tony1 = (Girl) TonyCompany.proxy(tc);
		sicong.dating(tony1);

		Boy tchen = new TeacherChen();

		Boy tony2 = (Boy) TonyCompany.proxy(tchen);

		tony2.dating('E');

		System.out.println(tony2 instanceof Proxy);

		ProxyUtils.generateClassFile(Boy.class, tony2.getClass().getName());

	}

}
