package com.dongnao.proxy.cglib;

public class TeacherCang {

	public boolean dating(float length) {
		if (length >= 1.7f) {
			System.out.println("身高够，可以约！");
			return true;
		} else {
			System.out.println("身高不够，不可以约！");
			return false;
		}
	}
}
