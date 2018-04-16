package com.dn.volat;

public class VariableDemo {

	public static int COUNT = 0;

	private float length;

	public boolean func1(String name) {
		String str = null;
		str = name + length + COUNT;
		return str.length() > 20;
	}
}
